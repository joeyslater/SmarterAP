angular.module('smarterap')

.controller("TeacherCoursesDashboardController", TeacherCoursesDashboardController);

function TeacherCoursesDashboardController($http, $location, $document, $mdDialog, Ui) {
    var ctrl = this;

    ctrl.goToCourse = function(uid) {
        $location.url("/dashboard/course/" + uid);
    };

    ctrl.openAddNewCourseDialog = function($event) {
        $mdDialog.show({
            controller: AddNewCourseController,
            controllerAs: 'addNewCourse',
            templateUrl: 'main/pages/dashboard/teacher/courses/add-new-course.modal.tpl.html',
            parent: angular.element($document[0].body),
            targetEvent: $event,
            clickOutsideToClose: true
        });
    };

    $http.get('/smarter-ap/api/course/list').then(function(response) {
        ctrl.courses = response.data;
    }, function(response) {
        ctrl.courses = [{
            'uid': '1',
            'period': '3',
            'subject': 'AP Computer Science'
        }, {
            'uid': '2',
            'period': '4',
            'subject': 'AP Computer Science'
        }, {
            'uid': '3',
            'period': '5',
            'subject': 'AP Computer Science'
        }, {
            'uid': '4',
            'period': '3',
            'subject': 'AP Computer Science'
        }, {
            'uid': '5',
            'period': '4',
            'subject': 'AP Computer Science'
        }, {
            'uid': '6',
            'period': '5',
            'subject': 'AP Computer Science'
        }];
    });

    Ui.setHeaderTitle('Dashboard');

}
