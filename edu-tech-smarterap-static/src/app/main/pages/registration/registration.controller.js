angular.module('smarterap')

.controller('RegistrationController', RegistrationController);

function RegistrationController($http, APP, $location, $state) {
    var ctrl = this;
    ctrl.title = APP.TITLE;

    ctrl.credentials = {};

    ctrl.register = function(credentials) {
        var regError = 'Error (Registration): ';
        console.log(credentials);
        console.log(JSON.stringify(credentials));
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
                        console.log(response);
                        console.log('success');
                        //$location.url("/login");
                    },
                    function(response) {
                        console.log(response);
                        console.log('failure');
                    });
        }
    };
}
