angular.module('smarterap')

.controller("TeacherCourseDashboardController", TeacherCourseDashboardController);

function TeacherCourseDashboardController($http, $stateParams, $timeout, Ui, $state, $mdDialog, $document, PropertyService) {
    var ctrl = this;

    $http.get('/smarter-ap/api/course/' + $stateParams.courseId)
        .then(
            function(response) {
                ctrl.course = response.data;

                $timeout(function() {
                    Ui.setHeaderTitle(ctrl.course.name);
                    PropertyService.setCourse(ctrl.course);
                });
            },
            function(response) {
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

    ctrl.addNewAssessment = function($event) {
        $mdDialog.show({
            controller: CreateAssessmentController,
            controllerAs: 'createAssessment',
            templateUrl: 'main/pages/dashboard/teacher/assessment/create-assessment.modal.tpl.html',
            parent: angular.element($document[0].body),
            targetEvent: $event,
            clickOutsideToClose: true
        });
    };

}
