angular.module('smarterap')

.factory('Ui', Ui);

function Ui($http) {
    var service = {};

    var headerTitle = "Dashboard";
    service.setHeaderTitle = function(title) {
        headerTitle = title;
    };
    service.getHeaderTitle = function() {
        return headerTitle;
    };

    var user;
    service.setUser = function(newUser) {
        user = newUser;
    };
    service.getUser = function() {
        return user;
    };

    return service;
}
