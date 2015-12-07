angular.module('smarterap')

.controller("TeacherCoursesDashboardController", TeacherCoursesDashboardController);

function TeacherCoursesDashboardController($http, $location, $document, $mdDialog, $timeout, $mdToast, Ui) {
    var ctrl = this;
    ctrl.getBorderColor = Ui.getBorderColor;
    ctrl.loadingCourses = true;

    ctrl.openAddNewCourseDialog = function($event) {
        $mdDialog.show({
            controller: AddEditCourseController,
            controllerAs: 'addEditCourse',
            templateUrl: 'main/pages/dashboard/teacher/courses/add-edit-course.modal.tpl.html',
            parent: angular.element($document[0].body),
            targetEvent: $event,
            clickOutsideToClose: true,
            bindToController: true,
            locals: {
                course: null
            }
        });
    };


    ctrl.openEditCourseDialog = function(course, $event) {
        $mdDialog.show({
            controller: AddEditCourseController,
            controllerAs: 'addEditCourse',
            templateUrl: 'main/pages/dashboard/teacher/courses/add-edit-course.modal.tpl.html',
            parent: angular.element($document[0].body),
            targetEvent: $event,
            clickOutsideToClose: true,
            bindToController: true,

            locals: {
                course: course
            }
        });
    };

    $http.get('/smarter-ap/api/course/owned')
        .then(
            function(response) {
                ctrl.courses = response.data;
                ctrl.loadingCourses = false;
            },
            function(response) {
                ctrl.courses = [];
                ctrl.loadingCourses = false;
            });

    Ui.setHeaderTitle('Dashboard');


    ctrl.hasArchivedItems = function() {
        if (ctrl.courses) {
            for (var i = 0; i < ctrl.courses.length; i++) {
                if (!ctrl.courses[i].enabled) {
                    return true;
                }
            }
        }
        return false;
    };

    ctrl.enable = function(course, shouldEnable) {
        var copy = angular.copy(course);
        copy.enabled = !course.enabled;
        $http.post('smarter-ap/api/course/' + course.uid + '/', JSON.stringify(copy), {
                withCredentials: true,
                'headers': {
                    'Content-Type': 'application/json'
                }
            })
            .then(
                function(response) {
                    course.enabled = copy.enabled;
                },
                function(response) {
                    var action = course.enabled ? 'disable' : 'enable';
                    $mdToast.show($mdToast.simple().content('Unable to ' + action + '. Try again later.').hideDelay(2000));
                });

    };
}
