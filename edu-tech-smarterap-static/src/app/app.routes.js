angular.module('smarterap')

.config(function($urlRouterProvider, $stateProvider, $urlMatcherFactoryProvider, $locationProvider) {
    $urlMatcherFactoryProvider.strictMode(false);

    $stateProvider
        .state('homepage2', {
            url: '',
            templateUrl: 'main/pages/homepage/homepage.tpl.html'
        })
        .state('homepage', {
            url: '/',
            templateUrl: 'main/pages/homepage/homepage.tpl.html'
        })
        .state('login', {
            url: '/login',
            templateUrl: 'main/pages/login/login.tpl.html',
            controller: 'LoginController',
            controllerAs: 'login'
        })
        .state('registration', {
            url: '/registration',
            templateUrl: 'main/pages/registration/registration.tpl.html',
            controller: 'RegistrationController',
            controllerAs: 'registration'
        })
        .state('question-create', {
            url: '/question/new',
            templateUrl: 'main/pages/question/question-create.tpl.html',
            controller: 'QuestionCreateController',
            controllerAs: 'questionCreate',
            sp: {
                authenticate: true
            }
        })
        .state('dashboard', {
            url: '/dashboard',
            abstract: true,
            templateUrl: 'main/pages/dashboard/dashboard.tpl.html',
            controller: 'DashboardController',
            controllerAs: 'dashboard',
            sp: {
                authenticate: true
            }
        })
        .state('dashboard.courses', {
            url: "",
            templateUrl: 'main/pages/dashboard/teacher/courses/courses.tpl.html',
            controller: 'TeacherCoursesDashboardController',
            controllerAs: 'teacherCoursesDashboard',
            sp: {
                authenticate: true
            }
        })
        .state('dashboard.course', {
            url: "/course/:courseId",
            templateUrl: 'main/pages/dashboard/teacher/course/course.tpl.html',
            controller: 'TeacherCourseDashboardController',
            controllerAs: 'teacherCourseDashboard',
            sp: {
                authenticate: true
            }
        }).state('dashboard.assessment', {
            url: "/assessment",
            templateUrl: 'main/pages/dashboard/teacher/assessment/create-assessment.tpl.html',
            controller: 'CreateAssessmentController',
            controllerAs: 'createAssessment',
            sp: {
                authenticate: true
            }
        });
})


.run(function($rootScope, $state, $stormpath) {
    $stormpath.uiRouter({
        loginState: 'login',
        defaultPostLoginState: 'dashboard.courses'
    });

    $rootScope.$on('$sessionEnd', function() {
        $state.transitionTo('login');
    });
})


;
