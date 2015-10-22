angular.module('smarterap')

.controller('RegistrationController', RegistrationController);

function RegistrationController($http, APP, $location, $state) {
    var ctrl = this;
    ctrl.title = APP.TITLE;

    ctrl.credentials = {
        'submit': 'Login'
    };

    ctrl.goTo = function(state) {
        $state.go(state);
    };

    ctrl.register = function(credentials) {
        if (credentials.password === credentials.confirmPassword) {
            console.log("Add Things");
            $http({
                method: 'POST',
                url: '/smarter-ap/register',
                data: {
                    'user': credentials,
                    'password': credentials.password
                },
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(function(response) {
                console.log(response);
                console.log('success');
                $location.url("/dashboard");

            }, function(response) {
                console.log(response);
                console.log('failure');
            });
        } else {
            console.log('test');
        }


    };

}
