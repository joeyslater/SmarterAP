angular.module('smarterap')

.controller("TeacherCoursesDashboardController", TeacherCoursesDashboardController);

function TeacherCoursesDashboardController($http, $location, Ui) {
    var ctrl = this;

    ctrl.goToCourse = function(uid) {
        $location.url("/dashboard/course/" + uid);
    };

    $http.get('/smarter-ap/api/course/list').then(function(response) {
        ctrl.courses = response.data;
    }, function(response) {
        ctrl.courses = [{
            'uid': '1',
            'period': '3',
            'title': 'AP Computer Science'
        }, {
            'uid': '2',
            'period': '4',
            'title': 'AP Computer Science'
        }, {
            'uid': '3',
            'period': '5',
            'title': 'AP Computer Science'
        }, {
            'uid': '4',
            'period': '3',
            'title': 'AP Computer Science'
        }, {
            'uid': '5',
            'period': '4',
            'title': 'AP Computer Science'
        }, {
            'uid': '6',
            'period': '5',
            'title': 'AP Computer Science'
        }];
    });

    Ui.setHeaderTitle('Dashboard');

}
