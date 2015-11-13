angular.module('smarterap')

.controller('DashboardController', DashboardController);

function DashboardController(Ui, $http, $state) {
    var ctrl = this;

    if (!Ui.getUser()) {
        $http.get('/smarter-ap/account')
            .then(function(response) {
                Ui.setUser(response.data);
                if (!response.data) {
                    $state.go('homepage');
                }
            }, function(response) {
                $state.go('homepage');
            });
    }
}
