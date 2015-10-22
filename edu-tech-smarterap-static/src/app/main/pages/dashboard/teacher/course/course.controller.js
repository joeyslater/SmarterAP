angular.module('smarterap')

.controller("TeacherCourseDashboardController", TeacherCourseDashboardController);

function TeacherCourseDashboardController($http, $stateParams, $timeout, Ui) {
    var ctrl = this;

    $http.get('/smarter-ap/api/course/' + $stateParams.courseId).then(function(response) {
        ctrl.course = response.data;
    }, function(response) {
        ctrl.course = {
            'courseId': $stateParams.courseId,
            'title': 'AP Computer Science'
        };
    });

    ctrl.tabs = [{
        'title': 'Schedule'
    }, {
        'title': 'Assessments'
    }, {
        'title': 'Students',
        'template': 'main/pages/dashboard/teacher/students/student-list.tpl.html'
    }, {
        'title': 'Reports'
    }, {
        'title': 'About',
        'template': 'main/pages/dashboard/teacher/course/course-about.tpl.html'

    }];

    $timeout(function() {
        Ui.setHeaderTitle(ctrl.course.subject);
    });

}
