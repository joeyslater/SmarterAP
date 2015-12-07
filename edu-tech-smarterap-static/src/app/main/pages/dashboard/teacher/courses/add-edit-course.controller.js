angular.module('smarterap')

.controller("AddEditCourseController", AddEditCourseController);

function AddEditCourseController($http, $mdDialog, $mdToast, $state, Ui, course) {
    var ctrl = this;
    var copy = angular.copy(course);
    ctrl.currentCourse = copy ? copy : {};
    getSubjects();

    ctrl.cancel = function() {
        $mdDialog.cancel();
    };

    ctrl.save = function() {
        if (ctrl.currentCourse.uid) {
            $http.post('smarter-ap/api/course/' + ctrl.currentCourse.uid + '/', JSON.stringify(ctrl.currentCourse), {
                    withCredentials: true,
                    'headers': {
                        'Content-Type': 'application/json'
                    }
                })
                .then(
                    function(response) {
                        course.name = ctrl.currentCourse.name;
                        course.section = ctrl.currentCourse.section;
                        course.subject = ctrl.currentCourse.subject;

                        $mdDialog.hide();
                        $mdToast.show($mdToast.simple().content('Successfully updated.').hideDelay(2000));
                    },
                    function(response) {
                        $mdToast.show($mdToast.simple().content('Unable to edit. Try again later.').hideDelay(2000));
                    });
        } else {
            $http.post('/smarter-ap/api/course/new', angular.toJson(ctrl.currentCourse), {
                    'headers': {
                        'Content-Type': 'application/json'
                    }
                })
                .then(
                    function(response) {
                        $state.go('dashboard.teacher-course', {
                            'courseId': response.data.data.uid
                        });
                        $mdDialog.hide();
                        $mdToast.show($mdToast.simple().content('Course created.').hideDelay(2000));
                    },
                    function(response) {
                        ctrl.currentCourse = copy;
                        $mdToast.show($mdToast.simple().content('Failed to add course, try again.').hideDelay(3000));
                    });
        }
    };

    function getSubjects() {
        $http.get('/smarter-ap/api/subject/list')
            .then(
                function(response) {
                    ctrl.subjects = response.data;
                    if (ctrl.subjects.length === 0) {
                        ctrl.subjects = [{
                            uid: -1,
                            name: 'AP Computer Science',
                            category: 'Math and Computer Science'
                        }];
                    }
                },
                function(response) {
                    ctrl.subjects = [{
                        uid: -1,
                        name: 'AP Computer Science',
                        category: 'Math and Computer Science'
                    }];
                });
    }
}
