angular.module('smarterap')

.controller('DashboardController', DashboardController);

function DashboardController(Ui, $http) {

    if (!Ui.getUser()) {
        $http.get('/smarter-ap/api/account').then(function(response) {
            Ui.setUser(response.data);
        });
    }

}
