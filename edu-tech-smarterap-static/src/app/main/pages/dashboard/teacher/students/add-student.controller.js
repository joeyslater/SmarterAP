angular.module('smarterap')

.controller("AddStudentToCourseController", AddStudentToCourseController);

function AddStudentToCourseController($http, $mdDialog, $mdToast, $state, $stateParams, Ui) {
    var ctrl = this;
    ctrl.form = {};
    ctrl.cancel = function() {
        $mdDialog.cancel();
    };

    ctrl.save = function() {
        $http.post('smarter-ap/api/course/' + $stateParams.courseId + '/student/add', ctrl.form.email, {
                withCredentials: true,
                'headers': {
                    'Content-Type': 'application/json'
                }
            })
            .then(
                function(response) {
                    $mdDialog.hide();
                    $mdToast.show($mdToast.simple().content('Successfully added.').hideDelay(2000));
                },
                function(response) {
                    $mdToast.show($mdToast.simple().content('Unable to add. Try again later.').hideDelay(2000));
                });
    };
}
