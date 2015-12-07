angular.module('smarterap')

.controller("TeacherAssessmentDashboardController", TeacherAssessmentDashboardController);

function TeacherAssessmentDashboardController($q, $timeout, $state, $stateParams, $mdDialog, $document, $http, Ui) {
    var ctrl = this;
    Ui.setHeaderTitle('Assessment');

    init();

    function init() {
        ctrl.loadingAssessment = true;
        var assessmentDeferred = $q.defer();

        $http.get('/smarter-ap/api/assessment/teacher/' + $stateParams.assessmentId)
            .then(
                function(response) {
                    ctrl.assessment = response.data;
                    ctrl.assessment.questions = [{
                        'description': 'What is output by the following code?<br><br><span class="code">public class A <br>{<br>&nbsp; private static int count;<br><br>&nbsp; public A()<br>&nbsp; {<br>&nbsp;&nbsp;&nbsp; out.print(&quot;A&quot;);<br>&nbsp;&nbsp;&nbsp; count++;<br>&nbsp; }<br><br>&nbsp; public int getCount()<br>&nbsp; {<br>&nbsp;&nbsp;&nbsp; return count;<br>&nbsp; }<br><br></span>&nbsp;&nbsp;&nbsp; <span class="code comment">// other constructors and methods not shown</span><span class="code"><br>}<br><br>public class B extends A<br>{<br>&nbsp; public B()<br>&nbsp; {<br>&nbsp;&nbsp;&nbsp; out.print(&quot;B&quot;);<br>&nbsp; }<br>&nbsp; <br></span>&nbsp;&nbsp; <span class="code comment">// other constructors and methods not shown</span><span class="code"><br>}</span><br><br><span class="code comment">//client code</span><br><span class="code">A x = new B();<br>x = new B();<br>x = new B();<br>out.print( x.getCount() );</span><br>',
                        'hint': 'hintttt',
                        'subject': {
                            'name': 'Computer Science'
                        },
                        'difficulty': 3,
                        'tags': [{
                            name: 'Lists'
                        }],
                        'answers': [{
                            'uid': 0,
                            'text': '<span >are <span class="code">final</span> and <span class="code">static</span></span>',
                            'correct': true
                        }, {
                            'uid': 1,
                            'text': '<span >have private access</span>'
                        }, {
                            'uid': 2,

                            'text': '<span >have protected access</span>'
                        }, {
                            'uid': 3,

                            'text': '<span >must be initialized in the class implementing the interface</span>'
                        }]
                    }, {
                        'description': 'What is output by the following code?<br><br><span style="font-family:courier new;">public class A <br>{<br>&nbsp; public void printIt() <br>&nbsp; {<br>&nbsp;&nbsp;&nbsp; System.out.print(50);<br>&nbsp; }<br><br></span>&nbsp;&nbsp;&nbsp; // other constructors and methods not shown<span style="font-family:\'courier new\'"><br>}<br><br>public class B extends A<br>{<br>&nbsp; public void printIt() <br>&nbsp; {<br>&nbsp;&nbsp;&nbsp; System.out.print(25);<br>&nbsp;&nbsp;&nbsp; super.printIt();<br>&nbsp; }<br>&nbsp; <br></span>&nbsp;&nbsp; // other constructors and methods not shown<span style="font-family:\'courier new\'"><br>}</span><br><br>//client code<br><span style="font-family:\'courier new\'">A x = new A();<br>out.print( x.printIt() );</span><br>}',
                        'hint': 'hintttt 222',
                        'answers': [{
                            'text': 'test'
                        }]
                    }];

                    ctrl.loadingAssessment = false;
                    assessmentDeferred.resolve(ctrl.assessment);

                },
                function(response) {
                    ctrl.assessment = {};
                    ctrl.loadingAssessment = false;
                    assessmentDeferred.resolve(ctrl.assessment);

                });

        ctrl.assessmentDeferred = assessmentDeferred.promise;
    }

}
