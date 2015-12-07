angular.module('smarterap')

.controller("QuestionBankController", QuestionBankController);

function QuestionBankController($mdDialog, $document, $q, $http, $timeout, $mdToast, $state, Ui, PropertyService) {
    var ctrl = this;
    ctrl.query = {
        tags: [],
        start: 0,
        currentPage: 0,
        num: 10,
        queryText: ''
    };
    ctrl.queryText = '';
    ctrl.pagination = {
        'currentPage': 1
    };

    Ui.setHeaderTitle('Question Bank');
    ctrl.expandQuery = function($event) {
        $timeout(function() {
            ctrl.showExpandedQuery = true;
        });
    };

    ctrl.closeQuery = function() {
        $timeout(function() {
            ctrl.showExpandedQuery = false;
        });
    };

    ctrl.clear = function() {
        ctrl.query.queryText = "";
        ctrl.query.tags = [];
        delete ctrl.query.subject;
        delete ctrl.query.difficulty;
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

    ctrl.search = function() {
        ctrl.getResults();
    };

    ctrl.queryTag = function(searchText) {
        var deferred = $q.defer();
        $http.get('/smarter-ap/api/tag/query?subjectId=' + ctrl.query.subject.uid + '&q=' + searchText)
            .then(
                function(response) {
                    deferred.resolve(response.data);
                },
                function(response) {
                    deferred.resolve([]);
                });
        return deferred.promise;
    };

    ctrl.getResults = function() {
        ctrl.loadingResults = true;
        ctrl.query.start = ((ctrl.query.currentPage || 1) - 1) * ctrl.query.num;
        $http.post('/smarter-ap/api/question/query', JSON.stringify(ctrl.query), {
                withCredentials: true,
                'headers': {
                    'Content-Type': 'application/json'
                }
            })
            .then(
                function(response) {
                    ctrl.results = response.data.left;
                    ctrl.count = response.data.right;
                    ctrl.loadingResults = false;
                    ctrl.closeQuery();
                },
                function() {
                    ctrl.loadingResults = false;
                }
            );
    };

    ctrl.getResults();

    ctrl.uploadQuestions = function($fileContent) {
        ctrl.loadingQuestions = true;
        $http.post('/smarter-ap/api/question/upload', JSON.stringify($fileContent), {
                withCredentials: true,
                'headers': {
                    'Content-Type': 'application/json'
                }
            })
            .then(
                function(response) {
                    $fileContent = undefined;
                    ctrl.loadingQuestions = false;
                    PropertyService.setQuestions(response.data);
                    $state.go('dashboard.question-create');
                },
                function() {
                    ctrl.loadingQuestions = false;
                    $mdToast.show($mdToast.simple().content('There was an error uploading.').hideDelay(2000));
                }
            );
    };

}
