angular.module('smarterap')

.controller("AddTagController", AddTagController);

function AddTagController($http, $mdDialog, $mdToast, $state, Ui, $location) {
    var ctrl = this;
    getSubjects();

    ctrl.cancel = function() {
        $mdDialog.cancel();
    };

    ctrl.save = function() {
        $http.post('/smarter-ap/api/tag/new', angular.toJson(ctrl.currentTag), {
                withCredentials: true,
                'headers': {
                    'Content-Type': 'application/json'
                }
            })
            .then(
                function(response) {
                    $mdDialog.hide();
                    $mdToast.show($mdToast.simple().content('Tag created.').hideDelay(2000));
                },
                function(response) {
                    $mdToast.show($mdToast.simple().content('Failed to add tag, try again.').hideDelay(3000));
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
