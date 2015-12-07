angular.module('smarterap')

.controller("TeacherStudentsListController", TeacherStudentsListController);

function TeacherStudentsListController($timeout, $q, $http, $document, $mdDialog, $mdToast, PropertyService, $stateParams) {
    var ctrl = this;
    ctrl.listQuery = {
        order: 'username'
    };

    ctrl.getStudents = function() {
        $http.get('/smarter-ap/api/course/' + $stateParams.courseId + '/students')
            .then(
                function(response) {
                    ctrl.students = response.data;
                },
                function(response) {
                    ctrl.students = [];
                });

    };
    ctrl.getStudents();

    ctrl.onOrderChange = function(order) {
        var deferred = $q.defer();
        $timeout(function() {
            deferred.resolve();
        }, 10);
        return deferred.promise;
    };

    ctrl.openUpload = function() {
        angular.element($document[0].querySelector('#file-students-csv-input')).trigger('click');
    };

    ctrl.openAddStudentDialog = function($event) {
        $mdDialog.show({
            controller: AddStudentToCourseController,
            controllerAs: 'addStudent',
            templateUrl: 'main/pages/dashboard/teacher/students/add-student.modal.tpl.html',
            parent: angular.element($document[0].body),
            targetEvent: $event,
            clickOutsideToClose: true
        }).finally(function() {
            ctrl.getStudents();
        });
    };

    ctrl.uploadStudents = function($fileContent) {
        $http.post('smarter-ap/api/course/' + $stateParams.courseId + '/student/add', $fileContent, {
                withCredentials: true,
                'headers': {
                    'Content-Type': 'application/json'
                }
            })
            .then(
                function(response) {
                    ctrl.getStudents();
                    $mdToast.show($mdToast.simple().content('Successfully added.').hideDelay(2000));
                },
                function(response) {
                    $mdToast.show($mdToast.simple().content('Unable to add. Try again later.').hideDelay(2000));
                });
    };
}
