angular.module('smarterap')

.controller("TeacherAssessmentsListController", TeacherAssessmentsListController);

function TeacherAssessmentsListController($q, $timeout, $state) {
    var ctrl = this;
    ctrl.selected = [];
    ctrl.listQuery = {
        order: 'openDate'
    };

    ctrl.goTo = function(state) {
        $state.go(state);
    };

    ctrl.assessments = [{
        'uid': 1,
        'name': 'Friday Quiz #1',
        'openDate': new Date(2015, 10, 12),
        'dueDate': new Date(2015, 10, 14),
        'submitted': 5
    }, {
        'uid': 2,
        'name': 'Friday Quiz #2',
        'openDate': new Date(2015, 10, 17),
        'dueDate': new Date(2015, 10, 18),
        'submitted': 0
    }, {
        'uid': 4,
        'name': 'Thanksgiving Blitz',
        'openDate': new Date(2015, 10, 24),
        'dueDate': new Date(2015, 10, 26),
        'submitted': 2
    }, {
        'uid': 3,
        'name': 'Arrays & Lists Quiz',
        'openDate': new Date(2015, 10, 10),
        'dueDate': new Date(2015, 10, 11),
        'submitted': 10
    }, {
        'uid': 5,
        'name': 'Time to Q-q-q-q-quiz',
        'openDate': new Date(2015, 10, 08),
        'dueDate': new Date(2015, 10, 10),
        'submitted': 22
    }, {
        'uid': 6,
        'name': 'Boolean Recap',
        'openDate': new Date(2015, 10, 16),
        'dueDate': new Date(2015, 10, 17),
        'submitted': 15
    }];

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
            controller: AddNewAssessmentController,
            controllerAs: 'addAssessment',
            templateUrl: 'main/pages/dashboard/teacher/assessment/add-assessment.modal.tpl.html',
            parent: angular.element($document[0].body),
            targetEvent: $event,
            clickOutsideToClose: true
        });
    };

}
