angular.module('smarterap')

.controller("UserGroupModalController ", UserGroupModalController);

function UserGroupModalController($http, $mdDialog, $mdToast, $state, Ui, $location, user) {
    var ctrl = this;

    console.log(user);

    ctrl.admin = user.securityRoles.indexOf('ADMIN') > -1;
    ctrl.student = user.securityRoles.indexOf('STUDENT') > -1;
    ctrl.teacher = user.securityRoles.indexOf('TEACHER') > -1;


    ctrl.cancel = function() {
        $mdDialog.cancel();
    };

    ctrl.save = function() {
        //TODO code is inefficient, but it's late... so fix this
        var userToUpload = angular.copy(user);
        userToUpload.securityRoles = [];
        if (ctrl.admin) {
            userToUpload.securityRoles.push("ADMIN");
        }
        if (ctrl.student) {
            userToUpload.securityRoles.push("STUDENT");
        }
        if (ctrl.teacher) {
            userToUpload.securityRoles.push("TEACHER");
        }
        if (userToUpload.securityRoles.length === 0) {
            userToUpload.securityRoles.push("STUDENT");
        }

        $http.post('/smarter-ap/api/admin/user/update', angular.toJson(userToUpload), {
                'headers': {
                    'Content-Type': 'application/json'
                }
            })
            .then(
                function(response) {
                    $mdDialog.hide();
                    $mdToast.show($mdToast.simple().content('User updated.').hideDelay(2000));
                },
                function(response) {
                    $mdToast.show($mdToast.simple().content('User not updated.').hideDelay(3000));
                });
    };
}
