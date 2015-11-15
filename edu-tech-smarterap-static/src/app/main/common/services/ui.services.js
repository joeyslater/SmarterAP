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
        user.securityRoles.sort();
        if (user.securityRoles.length > 0) {
            service.setRole(user.securityRoles[0]);
        }
    };
    service.getUser = function() {
        return user;
    };

    var role;
    service.setRole = function(newRole) {
        role = newRole;
    };
    service.getRole = function() {
        return role;
    };
    service.isInRole = function(roleToCheck) {
        return roleToCheck === role;
    };

    return service;
}
