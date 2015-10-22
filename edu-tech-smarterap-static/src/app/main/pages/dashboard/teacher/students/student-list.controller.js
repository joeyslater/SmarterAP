angular.module('smarterap')

.controller("TeacherStudentsListController", TeacherStudentsListController);

function TeacherStudentsListController($timeout, $q) {
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
