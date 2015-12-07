angular.module('smarterap')

.controller("AdminUsersListController", AdminUsersListController);

function AdminUsersListController($http, $q, $timeout, $mdToast, $mdDialog, $document) {
    var ctrl = this;
    ctrl.loadingUsers = true;
    ctrl.selected = [];
    ctrl.listQuery = {
        'order': 'surname'
    };

    init();

    ctrl.onOrderChange = function(order) {
        var deferred = $q.defer();
        $timeout(function() {
            deferred.resolve();
        }, 10);
        return deferred.promise;
    };

    function init() {
        var userDeferred = $q.defer();

        $http.get('/smarter-ap/api/admin/users')
            .then(
                function(response) {
                    ctrl.users = response.data;
                    ctrl.loadingUsers = false;
                    userDeferred.resolve(ctrl.users);
                },
                function(response) {
                    ctrl.user = [];
                    ctrl.loadingUsers = false;
                });
        ctrl.userDeferred = userDeferred.promise;
    }

    ctrl.updateStatus = function(user) {
        $http.post('/smarter-ap/api/admin/user/update', angular.toJson(user))
            .then(
                function(response) {
                    init();
                },
                function(response) {

                });
    };

    ctrl.resetPassword = function(user) {
        console.log('test');
        $http.post('/smarter-ap/api/admin/user/password', angular.toJson(user))
            .then(
                function(response) {
                    $mdToast.show($mdToast.simple().content('Reset password request sent.').hideDelay(2000));
                },
                function(response) {
                    $mdToast.show($mdToast.simple().content('Reset password request not sent.').hideDelay(2000));
                });
    };

    ctrl.resendVerification = function(user) {
        $http.post('/smarter-ap/api/admin/user/verification', angular.toJson(user))
            .then(
                function(response) {
                    $mdToast.show($mdToast.simple().content('Verification request sent.').hideDelay(2000));
                },
                function(response) {
                    $mdToast.show($mdToast.simple().content('Verification request not sent.').hideDelay(2000));
                });
    };

    ctrl.updateGroupDialog = function($event, user) {
        $mdDialog.show({
            controller: UserGroupModalController,
            controllerAs: 'userGroup',
            templateUrl: 'main/pages/dashboard/admin/users/users-group.modal.tpl.html',
            parent: angular.element($document[0].body),
            targetEvent: $event,
            clickOutsideToClose: true,
            bindToController: true,
            locals: {
                user: user
            }
        }).finally(function() {
            init();
        });
    };


}
