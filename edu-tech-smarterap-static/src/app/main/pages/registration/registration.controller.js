angular.module('smarterap')

.controller('RegistrationController', RegistrationController);

function RegistrationController($http, APP, $state, $mdToast) {
    var ctrl = this;

    ctrl.credentials = {};

    ctrl.register = function(credentials) {
        var regError = 'Error (Registration): ';
        if (credentials.password !== credentials.confirmedPassword) {
            console.log(regError + 'Passwords do not match! --> password: "' + credentials.password + "' != '" +
                credentials.confirmedPassword + "'");
        } else if (!credentials.givenName) {
            console.log(regError + 'First name is invalid!');
        } else if (!credentials.surname) {
            console.log(regError + 'Last name is invalid!');
        } else {
            $http.post('/smarter-ap/register', JSON.stringify(credentials), {
                    'headers': {
                        'Content-Type': 'application/json'
                    }
                })
                .then(
                    function(response) {
                        $mdToast.show($mdToast.simple().content('Successfully registered. Please see your email.').hideDelay(3000));
                        $state.go('login');
                    },
                    function(response) {
                        console.log(response);
                        console.log('failure');
                    });
        }
    };
}
