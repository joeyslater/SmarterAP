angular.module('smarterap')

.controller('QuestionCreateController', QuestionCreateController);

function QuestionCreateController() {
    var ctrl = this;

    ctrl.question = {
        'description': 'Test'
    };
    ctrl.toolbarOptions = [
        ['bold', 'italics', 'underline', 'strikeThrough', 'ul', 'ol']
    ];
}
