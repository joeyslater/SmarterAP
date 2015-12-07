angular.module('smarterap')

.controller('LoginController', LoginController);

function LoginController($scope, $rootScope, $http, $timeout, APP, $state, Ui, UserService, $auth, $user, STORMPATH_CONFIG) {
    var ctrl = this;
    ctrl.loginLoading = false;
    ctrl.credentials = {};

    ctrl.authenticate = function(credentials) {
        ctrl.loginLoading = true;
        $http.post('/smarter-ap/authenticate', JSON.stringify(credentials), {
                withCredentials: true,
                'headers': {
                    'Content-Type': 'application/json'
                }
            })
            .then(
                function(response) {
                    $timeout(function() {
                        UserService.isLoggedIn().then(function() {
                            $rootScope.$broadcast(STORMPATH_CONFIG.AUTHENTICATION_SUCCESS_EVENT_NAME, response);
                        });
                    });
                },
                function(response) {
                    $timeout(function() {
                        ctrl.loginLoading = false;
                        ctrl.errorMessage = response.data.message;
                        $rootScope.$broadcast(STORMPATH_CONFIG.AUTHENTICATION_FAILURE_EVENT_NAME, response);
                    });
                });
    };
}
