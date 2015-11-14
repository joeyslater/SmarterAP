angular.module('smarterap')

.controller("AddNewCourseController", AddNewCourseController);

function AddNewCourseController($http, $mdDialog, $mdToast, $state, Ui, $location) {
    var ctrl = this;
    ctrl.newCourse = {};
    console.log("test");
    getSubjects();

    ctrl.cancel = function() {
        $mdDialog.cancel();
    };

    ctrl.save = function() {
        $http.post('/smarter-ap/api/course/new', angular.toJson(ctrl.newCourse), {
                'headers': {
                    'Content-Type': 'application/json'
                }
            })
            .then(
                function(response) {
                    $location.url("/dashboard/course/" + response.data.data.uid);
                    $mdDialog.hide();
                    $mdToast.show($mdToast.simple().content('Course created.').hideDelay(2000));
                },
                function(response) {
                    $mdToast.show($mdToast.simple().content('Failed to add course, try again.').hideDelay(3000));
                });
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
