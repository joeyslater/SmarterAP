angular.module('smarterap')

.controller('LoginController', LoginController);

function LoginController($rootScope, $http, APP, $location, $state, Ui, $auth, $user, STORMPATH_CONFIG) {
    var ctrl = this;
    ctrl.title = APP.TITLE;
    ctrl.credentials = {
        username: 'joeyslater',
        password: 'Password1'
    };

    ctrl.authenticate = function(credentials) {
        $http.post('/smarter-ap/authenticate', JSON.stringify(credentials), {
                withCredentials: true,
                'headers': {
                    'Content-Type': 'application/json'
                }
            })
            .then(
                function(response) {
                    $rootScope.$broadcast(STORMPATH_CONFIG.AUTHENTICATION_SUCCESS_EVENT_NAME, response);

                },
                function(response) {
                    ctrl.errorMessage = response.data.message;
                    $rootScope.$broadcast(STORMPATH_CONFIG.AUTHENTICATION_FAILURE_EVENT_NAME, response);
                });
    };

    $user.get()
        .then(function(user) {
            $state.go('dashboard.courses');
        })
        .catch(function(error) {});

}
