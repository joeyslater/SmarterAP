angular.module('smarterap')

.controller('LoginController', LoginController);

function LoginController($http, APP, $location, $state, Ui) {
    var ctrl = this;
    ctrl.title = APP.TITLE;
    ctrl.credentials = {
        'submit': 'Login'
    };

    ctrl.goTo = function(state) {
        $state.go(state);
    };

    ctrl.authenticate = function(credentials) {
        $http({
            method: 'POST',
            url: '/login',
            data: 'username=' + encodeURIComponent(credentials.username) + '&password=' + encodeURIComponent(credentials.password),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(function(response) {
            $http.get('/smarter-ap/api/account').then(function(response) {
                Ui.setUser(response.data);
            });
            console.log(response);
            console.log('success');
            $location.url("/dashboard");

        }, function(response) {
            console.log(response);
            console.log('failure');
        });

    };

}
