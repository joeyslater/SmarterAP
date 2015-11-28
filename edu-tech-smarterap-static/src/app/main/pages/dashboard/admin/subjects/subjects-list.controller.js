angular.module('smarterap')

.controller("AdminSubjectsListController", AdminSubjectsListController);

function AdminSubjectsListController($http, $q, $timeout, $document, $mdDialog, $mdToast) {
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

    ctrl.delete = function(uid) {
        var subjectDeferred = $q.defer();
        $http.post('/smarter-ap/api/subject/' + uid + '/delete')
            .then(
                function(response) {
                    init();
                },
                function(response) {
                    $mdToast.show($mdToast.simple().content('Failed to delete subject, try again.').hideDelay(3000));
                });
        ctrl.subjectDeferred = subjectDeferred.promise;
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
        $http.get('/smarter-ap/api/subject/count').then(function(response) {
            ctrl.count = response.data;
        });
        ctrl.subjectDeferred = subjectDeferred.promise;
    }

    ctrl.openAddNewSubjectDialog = function($event) {
        console.log('test');
        $mdDialog.show({
            controller: AddSubjectController,
            controllerAs: 'addSubject',
            templateUrl: 'main/pages/dashboard/admin/subjects/add-subject.modal.tpl.html',
            parent: angular.element($document[0].body),
            targetEvent: $event,
            clickOutsideToClose: true
        }).finally(function() {
            init();
        });
    };

}
