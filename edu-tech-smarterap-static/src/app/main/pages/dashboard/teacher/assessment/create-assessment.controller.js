angular.module('smarterap')

.controller("CreateAssessmentController", CreateAssessmentController);

function CreateAssessmentController($mdDialog, $http, $mdToast, Ui, PropertyService) {
    var ctrl = this;
    ctrl.newAssessment = {};
    ctrl.newAssessment.name = 'Untitled assessment';

    ctrl.cancel = function() {
        $mdDialog.cancel();
    };

    ctrl.save = function() {
        var course = PropertyService.getCourse();
        if (course) {
            ctrl.newAssessment.courseId = course.uid;
        }
        console.log(ctrl.newAssessment);
        $http.post('/smarter-ap/api/assessment/new', angular.toJson(ctrl.newAssessment), {
                withCredentials: true,
                'headers': {
                    'Content-Type': 'application/json'
                }
            })
            .then(
                function(response) {
                    $mdDialog.hide();
                    $mdToast.show($mdToast.simple().content('Assessment created.').hideDelay(2000));
                },
                function(response) {
                    $mdToast.show($mdToast.simple().content('Failed to add Assessment, try again.').hideDelay(3000));
                });
    };

}
