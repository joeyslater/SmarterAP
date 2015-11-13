angular.module('smarterap')

.controller('RegistrationController', RegistrationController);

function RegistrationController($http, APP, $location, $state) {
    var ctrl = this;
    ctrl.title = APP.TITLE;

    ctrl.credentials = {};

    ctrl.goTo = function(state) {
        $state.go(state);
    };

    ctrl.register = function(credentials) {
        console.log(credentials);
        console.log(JSON.stringify(credentials));
        $http.post('/smarter-ap/register', JSON.stringify(credentials), {
                'headers': {
                    'Content-Type': 'application/json'
                }
            })
            .then(
                function(response) {
                    console.log(response);
                    console.log('success');
                    //$location.url("/dashboard");
                },
                function(response) {
                    console.log(response);
                    console.log('failure');
                });
    };
}
