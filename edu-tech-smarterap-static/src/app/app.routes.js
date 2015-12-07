angular.module('smarterap')

.config(function($urlRouterProvider, $stateProvider, $urlMatcherFactoryProvider, $locationProvider) {
    $urlMatcherFactoryProvider.strictMode(false);

    $stateProvider
        .state('homepage2', {
            url: '',
            templateUrl: 'main/pages/homepage/homepage.tpl.html',
            redirectIfAuth: true
        })
        .state('homepage', {
            url: '/',
            templateUrl: 'main/pages/homepage/homepage.tpl.html',
            redirectIfAuth: true
        })
        .state('login', {
            url: '/login',
            templateUrl: 'main/pages/login/login.tpl.html',
            controller: 'LoginController',
            controllerAs: 'login',
            redirectIfAuth: true
        })
        .state('registration', {
            url: '/registration',
            templateUrl: 'main/pages/registration/registration.tpl.html',
            controller: 'RegistrationController',
            controllerAs: 'registration',
            redirectIfAuth: true
        })
        .state('resend-verification', {
            url: '/resend-verification',
            templateUrl: 'main/pages/resend-verification/resend-verification.tpl.html',
            controller: 'ResendVerificationController',
            controllerAs: 'resendVerification',
            redirectIfAuth: true
        })
        .state('reset-password', {
            url: '/reset-password',
            templateUrl: 'main/pages/reset-password/reset-password.tpl.html',
            controller: 'ResetPasswordController',
            controllerAs: 'resetPassword',
            redirectIfAuth: true
        })
        .state('dashboard.question-create', {
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
        .state('dashboard.default', {
            url: '',
            redirectTo: 'dashboard'
        })
        .state('dashboard.admin', {
            url: "/admin",
            templateUrl: 'main/pages/dashboard/admin/admin-dashboard.tpl.html',
            controller: 'AdminDashboardController',
            controllerAs: 'adminDashboard',
            sp: {
                authenticate: true,
                group: 'ADMIN'
            }
        })
        .state('dashboard.student', {
            url: "/student",
            templateUrl: 'main/pages/dashboard/student/courses/student-courses.tpl.html',
            controller: 'StudentCoursesDashboardController',
            controllerAs: 'studentCoursesDashboard',
            sp: {
                authenticate: true,
                group: 'STUDENT',
                waitForUser: true
            }
        })
        .state('dashboard.student-course', {
            url: "/student/course/:courseId",
            templateUrl: 'main/pages/dashboard/student/course/student-course.tpl.html',
            controller: 'StudentCourseDashboardController',
            controllerAs: 'studentCourseDashboard',
            sp: {
                authenticate: true,
                group: 'STUDENT',
                waitForUser: true
            }
        })
        .state('dashboard.student-assessment', {
            url: "/student/assessment/:assessmentId",
            templateUrl: 'main/pages/dashboard/student/assessment/student-assessment.tpl.html',
            controller: 'StudentAssessmentDashboardController',
            controllerAs: 'studentAssessment',
            sp: {
                authenticate: true,
                group: 'STUDENT',
                waitForUser: true
            }
        })
        .state('dashboard.teacher', {
            url: "/teacher",
            templateUrl: 'main/pages/dashboard/teacher/courses/teacher-courses.tpl.html',
            controller: 'TeacherCoursesDashboardController',
            controllerAs: 'teacherCoursesDashboard',
            sp: {
                authenticate: true,
                group: 'TEACHER',
                waitForUser: true
            }
        })
        .state('dashboard.teacher-course', {
            url: "/teacher/course/:courseId",
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
        }).state('dashboard.question-bank', {
            url: "/question-bank",
            templateUrl: 'main/pages/question/question-bank.tpl.html',
            controller: 'QuestionBankController',
            controllerAs: 'questionBank',
            sp: {
                authenticate: true
            }
        });
})

.run(function($rootScope, $state, $stormpath, $http, $timeout, Ui, UserService, STORMPATH_CONFIG) {
    UserService.isLoggedIn();

    $stormpath.uiRouter({
        loginState: 'login',
        defaultPostLoginState: 'dashboard.default'
    });

    $rootScope.$on('$sessionEnd', function() {
        $state.transitionTo('login');
    });

    $rootScope.$on('$stateChangeStart', function(evt, to, params) {
        if (to.redirectIfAuth) {
            UserService.isLoggedIn().then(
                function() {
                    evt.preventDefault();
                    $state.go('dashboard.default');
                });
        } else if (to.redirectTo) {
            evt.preventDefault();
            if (to.redirectTo === 'dashboard') {
                UserService.isLoggedIn().then(
                    function() {
                        $state.go(UserService.getDashboard(), params);
                    },
                    function() {
                        $state.go('login', params);
                    });
            } else {
                $state.go(to.redirectTo, params);
            }
        } else if (to.sp && to.sp.group) {
            UserService.isLoggedIn().then(
                function() {
                    if (!UserService.hasRole(to.sp.group)) {
                        evt.preventDefault();
                        $state.go('login', params);
                    }
                });
        }

    });


})


;
