angular.module('smarterap')

.controller('ResetPasswordController', ResetPasswordController);

function ResetPasswordController($http, $state, $log, $mdToast) {
    var ctrl = this;

    ctrl.sendRequest = function(user) {
        var reqError = 'Error (Reset Password): ';
        if (!email) {
            console.log(reqError + 'Email not provided: ' + user.email);
        } else {
            $http.post('/smarter-ap/password/reset', user.email, {
                    'headers': {
                        'Content-Type': 'application/json'
                    }
                })
                .then(
                    function(response) {
                        $mdToast.show($mdToast.simple().content('Successfully sent password change request.').hideDelay(3000));
                        $state.go('login');
                    },
                    function(response) {
                        $log.error(response);
                    });
        }
    };
}
