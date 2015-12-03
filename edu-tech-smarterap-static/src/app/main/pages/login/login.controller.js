angular.module('smarterap')

.controller('LoginController', LoginController);

function LoginController($scope, $rootScope, $http, APP, $location, $state, Ui, $auth, $user, STORMPATH_CONFIG) {
    var ctrl = this;
	console.log('Starting LoginController');
    ctrl.title = APP.TITLE;
    ctrl.credentials = {
        username: 'joeyslater@gatech.edu',
        password: 'Password1'
    };

	ctrl.goTo = function(state) {
		$state.go(state);
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
                    $location.url('/dashboard');
                },
                function(response) {
                    ctrl.errorMessage = response.data.message;
                    $rootScope.$broadcast(STORMPATH_CONFIG.AUTHENTICATION_FAILURE_EVENT_NAME, response);
                });
    };

    $rootScope.$watch(STORMPATH_CONFIG.AUTHENTICATION_SUCCESS_EVENT_NAME, function() {
        $location.url('/dashboard');
    });

    $user.get()
        .then(function(user) {
            $location.url('/dashboard');
        })
        .catch(function(error) {});

}
