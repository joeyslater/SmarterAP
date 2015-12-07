angular.module('smarterap')

.service('UserService', UserService);

function UserService($http, $q) {
    var service = {};

    var user;
    service.setUser = function(value) {
        user = value;
        if (user && user.securityRoles.length > 0) {
            user.securityRoles.sort();
            service.setRole(user.securityRoles[0]);
        } else {
            service.setRole();
        }
    };
    service.getUser = function() {
        return user;
    };
    service.isLoggedIn = function() {
        var deferred = $q.defer();
        if (user) {
            deferred.resolve();
        } else {
            $http.get('/smarter-ap/account')
                .then(
                    function(response) {
                        service.setUser(response.data);
                        deferred.resolve();
                    },
                    function() {
                        deferred.reject();
                    });
        }
        return deferred.promise;
    };

    service.getDashboard = function() {
        return role ? 'dashboard.' + role.toLowerCase() : 'dashboard.default';
    };

    var role;
    service.setRole = function(value) {
        role = value;
    };
    service.getRole = function() {
        return role;
    };
    service.isInRole = function(value) {
        return role === value;
    };
    service.hasRole = function(value) {
        return user ? user.securityRoles.indexOf(value) > -1 : false;
    };

    return service;
}
