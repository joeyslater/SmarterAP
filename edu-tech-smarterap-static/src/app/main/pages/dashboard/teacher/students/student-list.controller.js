angular.module('smarterap')

.controller("TeacherStudentsListController", TeacherStudentsListController);

function TeacherStudentsListController() {
    var ctrl = this;
    ctrl.students = [{
        'name': 'Joey Slater'
    }, {
        'name': 'Scott Leitstein'
    }, {
        'name': 'Tricia Flynn'
    }];


}
