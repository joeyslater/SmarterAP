angular.module('smarterap')

	.controller('ResetPasswordController', ResetPasswordController);

function ResetPasswordController($http, APP, $location, $state) {
	var ctrl = this;
	ctrl.title = APP.TITLE;

	ctrl.goTo = function (state) {
		$state.go(state);
	};

	ctrl.sendRequest = function (user) {
		var reqError = 'Error (Reset Password): ';
		console.log(user.email);
		console.log(JSON.stringify(user.email));
		if (!email) {
			console.log(reqError + 'Email not provided: ' + user.email);
		} else {
			$http.post('/smarter-ap/password/reset', user.email, {
					'headers': {
						'Content-Type': 'application/json'
					}
				})
				.then(
					function (response) {
						console.log(response);
						console.log('success');
						$location.url('/login');
					},
					function (response) {
						console.log(response);
						console.log('failure');
					});
		}
	};
}
