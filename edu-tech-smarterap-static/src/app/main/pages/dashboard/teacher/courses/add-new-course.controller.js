angular.module('smarterap')

.controller("AddNewCourseController", AddNewCourseController);

function AddNewCourseController($http, $mdDialog, $mdToast, $state) {
    var ctrl = this;
    ctrl.newCourse = {};
    getSubjects();

    ctrl.cancel = function() {
        $mdDialog.cancel();
    };

    ctrl.save = function() {
        $http({
            method: 'POST',
            url: '/smarter-ap/api/course/new',
            data: ctrl.newCourse,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(
            function(response) {
                $state.go('dashboard.course', {
                    'courseId': response.data.uid
                });
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
                    ctrl.subjects = [{
                        'uid': '1',
                        'name': 'AP Computer Science'
                    }, {
                        'uid': '2',
                        'name': 'AP Biology'
                    }, {
                        'uid': '3',
                        'name': 'AP Calculus AB'
                    }];
                },
                function(response) {
                    ctrl.subjects = [{
                        'uid': '1',
                        'name': 'AP Computer Science'
                    }, {
                        'uid': '2',
                        'name': 'AP Biology'
                    }, {
                        'uid': '3',
                        'name': 'AP Calculus AB'
                    }];
                });
    }
}
