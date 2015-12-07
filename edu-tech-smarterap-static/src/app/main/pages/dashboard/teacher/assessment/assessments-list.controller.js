angular.module('smarterap')

.controller("TeacherAssessmentsListController", TeacherAssessmentsListController);

function TeacherAssessmentsListController($q, $timeout, $state, $mdDialog, $document, $http) {
    var ctrl = this;
    ctrl.selected = [];
    ctrl.listQuery = {
        order: 'openDate'
    };

    ctrl.onOrderChange = function(order) {
        var deferred = $q.defer();
        $timeout(function() {
            deferred.resolve();
        }, 10);
        return deferred.promise;
    };

    ctrl.enterAssessment = function(uid) {
        console.log(uid);
    };

    ctrl.openAddAssessmentDialog = function($event) {
        $mdDialog.show({
            controller: CreateAssessmentController,
            controllerAs: 'createAssessment',
            templateUrl: 'main/pages/dashboard/teacher/assessment/create-assessment.modal.tpl.html',
            parent: angular.element($document[0].body),
            targetEvent: $event,
            clickOutsideToClose: true
        });
    };

}
