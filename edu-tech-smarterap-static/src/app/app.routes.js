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
		.state('reset-password', {
			url: '/reset-password',
			templateUrl: 'main/pages/reset-password/reset-password.tpl.html',
			controller: 'ResetPasswordController',
			controllerAs: 'resetPassword'
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
            controllerAs: 'dashboard'
        })
        .state('dashboard.admin', {
            url: "",
            templateUrl: 'main/pages/dashboard/admin/admin-dashboard.tpl.html',
            controller: 'AdminDashboardController',
            controllerAs: 'adminDashboard',
            sp: {
                authenticate: true,
                group: 'ADMIN',
                waitForUser: true
            }
        })
        .state('dashboard.student', {
            url: "",
            templateUrl: 'main/pages/dashboard/student/courses/student-courses.tpl.html',
            controller: 'StudentCoursesDashboardController',
            controllerAs: 'studentCoursesDashboard',
            sp: {
                authenticate: true,
                group: 'STUDENT',
                waitForUser: true
            }
        })
        .state('dashboard.teacher', {
            url: "",
            templateUrl: 'main/pages/dashboard/teacher/courses/teacher-courses.tpl.html',
            controller: 'TeacherCoursesDashboardController',
            controllerAs: 'teacherCoursesDashboard',
            sp: {
                authenticate: true,
                group: 'TEACHER',
                waitForUser: true
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
        }).state('dashboard.teacher-assessment', {
            url: "/assessment",
            templateUrl: 'main/pages/dashboard/teacher/assessment/create-assessment.tpl.html',
            controller: 'CreateAssessmentController',
            controllerAs: 'createAssessment',
            sp: {
                authenticate: true
            }
        });
})

.run(function($rootScope, $state, $stormpath, Ui) {
    $stormpath.uiRouter({
        loginState: 'login'
    });

    $rootScope.$on('$sessionEnd', function() {
        $state.transitionTo('login');
    });
})


;
