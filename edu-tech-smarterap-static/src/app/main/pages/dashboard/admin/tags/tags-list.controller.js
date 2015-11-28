angular.module('smarterap')

.controller("AdminTagsListController", AdminTagsListController);

function AdminTagsListController($http, $q, $timeout, $mdDialog, $document) {
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
        var tagDeferred = $q.defer();

        $http.get('/smarter-ap/api/tag/list')
            .then(
                function(response) {
                    ctrl.tags = response.data;
                    tagDeferred.resolve(ctrl.tags);
                },
                function(response) {
                    ctrl.tags = [];
                });
        ctrl.tagDeferred = tagDeferred.promise;
    }

    ctrl.openAddNewTagDialog = function($event) {
        $mdDialog.show({
            controller: AddTagController,
            controllerAs: 'addTag',
            templateUrl: 'main/pages/dashboard/admin/tags/add-tag.modal.tpl.html',
            parent: angular.element($document[0].body),
            targetEvent: $event,
            clickOutsideToClose: true
        }).finally(function() {
            init();
        });
    };
}
