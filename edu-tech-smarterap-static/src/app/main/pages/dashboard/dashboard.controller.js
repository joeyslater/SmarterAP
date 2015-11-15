angular.module('smarterap')

.controller('DashboardController', DashboardController);

function DashboardController(Ui, $http, $state) {
    var ctrl = this;

    if (!Ui.getUser()) {
        $http.get('/smarter-ap/account')
            .then(function(response) {
                Ui.setUser(response.data);
                updateView();
                if (!response.data) {
                    $state.go('homepage');
                }
            }, function(response) {
                $state.go('homepage');
            });
    } else {
        updateView();
    }

    updateView();

    function updateView() {
        if (Ui.getRole()) {
            $state.go('dashboard.' + Ui.getRole().toLowerCase());
        }
    }
}
