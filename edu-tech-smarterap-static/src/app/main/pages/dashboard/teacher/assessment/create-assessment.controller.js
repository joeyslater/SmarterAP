angular.module('smarterap')

.controller("CreateAssessmentController", CreateAssessmentController);

function CreateAssessmentController() {
    var ctrl = this;
    ctrl.newAssessment = {};
    ctrl.newAssessment.title = 'Untitled assessment';

}
