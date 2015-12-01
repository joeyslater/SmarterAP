angular.module('smarterap')

.controller("CreateAssessmentController", CreateAssessmentController);

function CreateAssessmentController($mdDialog, Ui) {
    var ctrl = this;
    ctrl.newAssessment = {};
    ctrl.newAssessment.title = 'Untitled assessment';

    ctrl.cancel = function() {
        $mdDialog.cancel();
    };
}
