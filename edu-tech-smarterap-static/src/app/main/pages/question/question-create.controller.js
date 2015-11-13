angular.module('smarterap')

.controller('QuestionCreateController', QuestionCreateController);

function QuestionCreateController($http) {
    var ctrl = this;
    ctrl.showHint = false;
    getSubjects();

    ctrl.print = function() {
        console.log(ctrl.question);
    };

    ctrl.question = {
        'description': '',
        'tags': ['test', 'lists'],
        'answers': [{
            'test': 'test'
        }],
        'points': 3,
        'difficulty': 3
    };

    ctrl.toolbarOptions = [
        ['bold', 'italics', 'underline', 'strikeThrough', 'ul', 'ol']
    ];

    ctrl.sortableOptions = {
        containment: '#question-create-editor-answers-list'
    };

    ctrl.addNewAnswer = function() {
        ctrl.question.answers.push({});
    };

    ctrl.toggleShowHint = function() {
        ctrl.showHint = !ctrl.showHint;
    };

    ctrl.searchForTags = function() {
        return [{
            'title': 'Lists'
        }];
    };

    function getSubjects() {
        $http({
                method: 'GET',
                url: '/smarter-ap/api/subjects/list',
                responseType: "json",
            })
            .then(
                function(response) {
                    ctrl.subjects = [{
                        'uid': -1,
                        'title': 'AP Computer Science'
                    }];
                },
                function(response) {
                    ctrl.subjects = [{
                        'uid': -1,
                        'title': 'AP Computer Science'
                    }, {
                        'uid': 0,
                        'title': 'AP Biology'
                    }];
                });
    }


}
