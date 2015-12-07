angular.module('smarterap')

.controller('QuestionCreateController', QuestionCreateController);

function QuestionCreateController($http, $q, $mdToast, $state, Ui, PropertyService) {
    var ctrl = this;
    ctrl.getBorderColor = Ui.getBorderColor;
    Ui.setHeaderTitle('Create New Question');

    ctrl.showHint = false;
    ctrl.searchText = "";

    ctrl.questions = PropertyService.getQuestions();
    if (!ctrl.questions) {
        ctrl.questions = [{
            'tags': [],
            'answers': [{}],
            'difficulty': 3
        }];
    } else {
        ctrl.questions = ctrl.questions.splice(1, 6);
    }

    ctrl.sortableOptions = {
        containment: '#question-create-editor-answers-list'
    };

    ctrl.addNewAnswer = function(question) {
        if (question.answers) {
            question.answers.push({});
        } else {
            question.answers = [{}];
        }
    };

    ctrl.setCorrectAnswer = function(question, answer) {
        for (var i = 0; i < question.answers.length; i++) {
            question.answers[i].correct = false;
        }
        answer.correct = true;
    };

    ctrl.removeAnswer = function(question, index) {
        question.answers.splice(index, 1);
        if (question.answers.length === 0) {
            question.answers.push({});
        }
    };

    ctrl.toggleShowHint = function(question) {
        question.showHint = !question.showHint;
    };

    ctrl.clearTags = function(question) {
        question.tags.length = 0;
    };

    ctrl.querySearch = function(question, searchText) {
        var deferred = $q.defer();
        $http.get('/smarter-ap/api/tag/query?subjectId=' + question.subject.uid + '&q=' + searchText)
            .then(
                function(response) {
                    deferred.resolve(response.data);
                },
                function(response) {
                    deferred.resolve([]);
                });
        return deferred.promise;
    };

    ctrl.getSubjects = function() {
        var deferred = $q.defer();
        if (ctrl.subjects) {
            deferred.resolve();
        } else {
            $http.get('/smarter-ap/api/subject/list')
                .then(
                    function(response) {
                        ctrl.subjects = response.data;
                        deferred.resolve();
                    },
                    function(response) {
                        ctrl.subjects = [];
                        deferred.reject();
                    });
        }
        return deferred.promise;
    };

    ctrl.submit = function() {
        for (var i = 0; i < ctrl.questions.length; i++) {
            for (var l = ctrl.questions[i].answers.length - 1; l > -1; l--) {
                if (!ctrl.questions[i].answers[l].text) {
                    ctrl.questions[i].answers.splice(l, 1);
                }
            }
            for (var j = 0; j < ctrl.questions[i].answers.length; j++) {
                ctrl.questions[i].answers[j].order = j;
            }
        }
        if (ctrl.questions.length > 1) {
            $http.post('/smarter-ap/api/question/merge', angular.toJson(ctrl.questions), {
                    'headers': {
                        'Content-Type': 'application/json'
                    }
                })
                .then(createCourseSuccess, createCourseFailure);
        } else {
            $http.post('/smarter-ap/api/question/new', angular.toJson(ctrl.questions[0]), {
                    'headers': {
                        'Content-Type': 'application/json'
                    }
                })
                .then(createCourseSuccess, createCourseFailure);
        }
    };

    function createCourseSuccess(response) {
        $mdToast.show($mdToast.simple().content('Question created.').hideDelay(2000));
        PropertyService.setQuestions(null);
        $state.go('dashboard.question-bank');
    }

    function createCourseFailure(response) {
        $mdToast.show($mdToast.simple().content('Failed to add question, try again.').hideDelay(3000));
    }
}
