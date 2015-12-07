angular.module('smarterap')

.controller('ResendVerificationController', ResendVerificationController);

function ResendVerificationController($http, $state, $log, $mdToast) {
    var ctrl = this;

    ctrl.sendRequest = function(user) {
        var reqError = 'Error (Resend Verification): ';
        if (!email) {
            console.log(reqError + 'Email not provided: ' + user.email);
        } else {
            $http.post('/smarter-ap/password/resendVerification', user.email, {
                    'headers': {
                        'Content-Type': 'application/json'
                    }
                })
                .then(
                    function(response) {
                        $mdToast.show($mdToast.simple().content('Successfully resent verification email.').hideDelay(3000));
                        $state.go('login');
                    },
                    function(response) {
                        $log.error(response);
                    });
        }
    };
}
