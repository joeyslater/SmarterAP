angular.module('smarterap')

.controller("TeacherStudentsListController", TeacherStudentsListController);

function TeacherStudentsListController($timeout, $q, $document) {
    var ctrl = this;

    ctrl.selected = [];
    ctrl.listQuery = {
        order: 'username'
    };

    ctrl.onOrderChange = function(order) {
        var deferred = $q.defer();
        $timeout(function() {
            deferred.resolve();
        }, 10);
        return deferred.promise;
    };


    ctrl.openAddNewStudentDialog = function($event) {
        $mdDialog.show({
            controller: AddNewStudentontroller,
            controllerAs: 'addNewStudent',
            templateUrl: 'main/pages/dashboard/teacher/courses/add-new-student.modal.tpl.html',
            parent: angular.element($document[0].body),
            targetEvent: $event,
            clickOutsideToClose: true
        });
    };

    ctrl.openUpload = function() {
        console.log('test');
        angular.element($document[0].querySelector('#file-students-csv-input')).trigger('click');
    };

    ctrl.students = [{
        'givenName': 'Joey',
        'surname': 'Slater',
        'emailAddress': 'jslater0808@gmail.com',
        'username': 'joeyslater'
    }, {
        'givenName': 'Scott',
        'surname': 'Leitstein',
        'emailAddress': 'test@gmail.com',
        'username': 'demo_user'
    }, {
        'givenName': 'Tricia',
        'surname': 'Flynn',
        'emailAddress': 'test1@gmail.com',
        'username': 'test1'
    }];


}
