angular.module('smarterap')

.controller("TeacherCoursesDashboardController", TeacherCoursesDashboardController);

function TeacherCoursesDashboardController($http, $location, $document, $mdDialog, $timeout, Ui) {
    var ctrl = this;

    var bottomColors = [
        "#3F51B5", "#FF5722", "#9C27B0", "#E91E63", "#FFC107", "#4CAF50", "#3F51B5", "#673AB7", "#F44336", "#009688"
    ];

    bottomColors = shuffle(bottomColors);

    ctrl.goToCourse = function(uid) {
        $location.url("/dashboard/course/" + uid);
    };

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
            locals: {
                course: course
            }
        });
    };

    $http.get('/smarter-ap/api/course/owned')
        .then(
            function(response) {
                ctrl.courses = response.data;
            },
            function(response) {
                ctrl.courses = [];
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

    ctrl.getBorderColor = function($index) {
        return bottomColors[$index % bottomColors.length];
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
                    $mdToast.show($mdToast.simple().content('Unable to enable. Try again later.').hideDelay(2000));
                });

    };

    function shuffle(array) {
        var currentIndex = array.length,
            temporaryValue, randomIndex;

        // While there remain elements to shuffle...
        while (0 !== currentIndex) {

            // Pick a remaining element...
            randomIndex = Math.floor(Math.random() * currentIndex);
            currentIndex -= 1;

            // And swap it with the current element.
            temporaryValue = array[currentIndex];
            array[currentIndex] = array[randomIndex];
            array[randomIndex] = temporaryValue;
        }

        return array;
    }

}
