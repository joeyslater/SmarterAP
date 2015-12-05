angular.module('smarterap')

.controller('LoginController', LoginController);

function LoginController($scope, $rootScope, $http, $timeout, APP, $state, Ui, UserService, $auth, $user, STORMPATH_CONFIG) {
    var ctrl = this;
    ctrl.loginLoading = false;
    ctrl.credentials = {
        username: 'joeyslater@gatech.edu',
        password: 'Password1'
    };

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
                        $rootScope.$broadcast(STORMPATH_CONFIG.AUTHENTICATION_SUCCESS_EVENT_NAME, response);
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

    $rootScope.$on(STORMPATH_CONFIG.AUTHENTICATION_SUCCESS_EVENT_NAME, function(data) {
        $http.get('/smarter-ap/account')
            .then(function(response) {
                UserService.setUser(response.data);
                $state.go(UserService.getDashboard());
            });
    });

    init();

    function init() {
        $http.get('/smarter-ap/account')
            .then(function(response) {
                UserService.setUser(response.data);
                $state.go(UserService.getDashboard());
            });
    }
}
