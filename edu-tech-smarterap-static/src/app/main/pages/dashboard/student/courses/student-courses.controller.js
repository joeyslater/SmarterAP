angular.module('smarterap')

.controller("StudentCoursesDashboardController", StudentCoursesDashboardController);

function StudentCoursesDashboardController($http, $location, $document, $mdDialog, $timeout, Ui) {
    var ctrl = this;
    ctrl.getBorderColor = Ui.getBorderColor;

    init();

    function init() {
        Ui.setHeaderTitle('Dashboard');
        ctrl.loadingCourses = true;

        $http.get('/smarter-ap/api/course/registered')
            .then(
                function(response) {
                    ctrl.courses = response.data;
                    ctrl.loadingCourses = false;
                },
                function(response) {
                    ctrl.courses = [];
                    ctrl.loadingCourses = false;
                });
    }

}
