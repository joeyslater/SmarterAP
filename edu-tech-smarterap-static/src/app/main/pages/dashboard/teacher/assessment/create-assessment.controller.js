angular.module('smarterap')

.controller("CreateAssessmentController", CreateAssessmentController);

function CreateAssessmentController(Ui) {
    var ctrl = this;
    ctrl.newAssessment = {};
    ctrl.newAssessment.title = 'Untitled assessment';

    Ui.setHeaderTitle('Create Assessment');
}
