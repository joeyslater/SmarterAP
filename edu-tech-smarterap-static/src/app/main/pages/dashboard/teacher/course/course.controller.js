angular.module('smarterap')

.controller("TeacherCourseDashboardController", TeacherCourseDashboardController);

function TeacherCourseDashboardController($http, $stateParams, $timeout, Ui, $state) {
    var ctrl = this;

    $http.get('/smarter-ap/api/course/' + $stateParams.courseId).then(function(response) {
        ctrl.course = response.data;
        $timeout(function() {
            Ui.setHeaderTitle(ctrl.course.name);
        });
    }, function(response) {
        ctrl.course = {
            'courseId': $stateParams.courseId,
            'title': 'AP Computer Science'
        };
    });

    ctrl.goTo = function(state) {
        $state.go(state);
    };

    ctrl.tabs = [{
        'title': 'Home',
        'template': 'main/pages/dashboard/teacher/course/course-home.tpl.html'
    }, {
        'title': 'Assessments',
        'template': 'main/pages/dashboard/teacher/assessment/assessments-list.tpl.html'
    }, {
        'title': 'Students',
        'template': 'main/pages/dashboard/teacher/students/student-list.tpl.html'
    }, {
        'title': 'Reports',
        'template': 'main/pages/dashboard/teacher/reports/reports.tpl.html'
    }];

}
