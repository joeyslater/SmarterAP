angular.module('smarterap')

.controller("AddSubjectController ", AddSubjectController);

function AddSubjectController($http, $mdDialog, $mdToast, $state, Ui, $location) {
    var ctrl = this;

    ctrl.cancel = function() {
        $mdDialog.cancel();
    };

    ctrl.save = function() {
        $http.post('/smarter-ap/api/subject/new', angular.toJson(ctrl.currentSubject), {
                'headers': {
                    'Content-Type': 'application/json'
                }
            })
            .then(
                function(response) {
                    $mdDialog.hide();
                    $mdToast.show($mdToast.simple().content('Subject created.').hideDelay(2000));
                },
                function(response) {
                    $mdToast.show($mdToast.simple().content('Failed to add subject, try again.').hideDelay(3000));
                });
    };
}
