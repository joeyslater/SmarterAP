angular.module('smarterap')

.controller('LoginController', LoginController);

function LoginController($rootScope, APP) {
	var ctrl = this;
	ctrl.title = APP.TITLE;
	ctrl.credentials = {};

	ctrl.authenticate = function() {

	};

}
