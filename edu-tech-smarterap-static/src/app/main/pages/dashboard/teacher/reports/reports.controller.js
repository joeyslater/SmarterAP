angular.module('smarterap')

.controller("TeacherReportsController", TeacherReportsController);

function TeacherReportsController() {
    var ctrl = this;

    ctrl.previousReports = [{
        'title': 'All Grades / All Students',
        'lastRun': new Date(),
        'students': ['Joey', 'Scott', 'Tricia']
    }, {
        'title': 'Assessment 1 Grades',
        'lastRun': new Date(),
        'students': ['Joey', 'Scott']
    }];
}
