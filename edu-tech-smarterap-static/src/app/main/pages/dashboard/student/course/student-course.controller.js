angular.module('smarterap')

.controller("StudentCourseDashboardController", StudentCourseDashboardController);

function StudentCourseDashboardController($q, $timeout, $state, $stateParams, $mdDialog, $document, $http) {
    var ctrl = this;

    init();

    function init() {
        ctrl.loadingCourse = true;
        ctrl.assessmentsLoading = true;
        var assessmentsDeferred = $q.defer();

        $http.get('/smarter-ap/api/course/' + $stateParams.courseId)
            .then(
                function(response) {
                    ctrl.course = response.data;
                    ctrl.loadingCourse = false;
                },
                function(response) {
                    ctrl.course = [];
                    ctrl.loadingCourse = false;
                });

        $http.get('/smarter-ap/api/assessment/student/course/' + $stateParams.courseId)
            .then(
                function(response) {
                    ctrl.assessments = response.data;
                    ctrl.assessmentsLoading = false;
                    assessmentsDeferred.resolve(ctrl.assessments);
                },
                function(response) {
                    ctrl.assessments = [];
                    ctrl.assessmentsLoading = false;
                    assessmentsDeferred.resolve(ctrl.assessments);

                });
        ctrl.assessmentsDeferred = assessmentsDeferred.promise;
    }

    ctrl.startAssessment = function(uid) {
        //TODO Mark when assignment has been started; therefore, it can be submitted the once.
        $state.go('dashboard.student-assessment', {
            'assessmentId': uid
        });
    };
}
