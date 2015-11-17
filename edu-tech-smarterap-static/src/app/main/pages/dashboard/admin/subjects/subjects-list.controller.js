angular.module('smarterap')

.controller("AdminSubjectsListController", AdminSubjectsListController);

function AdminSubjectsListController($http, $q, $timeout) {
    var ctrl = this;
    ctrl.selected = [];
    ctrl.listQuery = {
        'order': 'name',
        'limit': 20,
        'page': 0,
        'showFilter': false
    };

    init();

    ctrl.onOrderChange = function(order) {
        var deferred = $q.defer();
        $timeout(function() {
            deferred.resolve();
        }, 10);
        return deferred.promise;
    };

    function init() {
        var subjectDeferred = $q.defer();

        $http.get('/smarter-ap/api/subject/list')
            .then(
                function(response) {
                    ctrl.subjects = response.data;
                    subjectDeferred.resolve(ctrl.subjects);
                },
                function(response) {
                    ctrl.subjects = [];
                });
        ctrl.subjectDeferred = subjectDeferred.promise;
    }
}
