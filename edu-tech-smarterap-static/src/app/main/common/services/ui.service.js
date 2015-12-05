angular.module('smarterap')

.factory('Ui', Ui);

function Ui() {
    var service = {};

    var headerTitle = "Dashboard";
    service.setHeaderTitle = function(value) {
        headerTitle = value;
    };
    service.getHeaderTitle = function() {
        return headerTitle;
    };

    return service;
}
