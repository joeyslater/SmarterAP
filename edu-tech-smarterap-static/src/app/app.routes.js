angular.module('smarterap')

.config(function($urlRouterProvider, $stateProvider, $urlMatcherFactoryProvider) {
    $urlMatcherFactoryProvider.strictMode(false);

    $stateProvider
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
            controllerAs: 'questionCreate'
        })
        .state('dashboard', {
            url: '/dashboard',
            abstract: true,
            templateUrl: 'main/pages/dashboard/dashboard.tpl.html',
            controller: 'DashboardController',
            controllerAs: 'dashboard'
        })
        .state('dashboard.courses', {
            url: "",
            templateUrl: 'main/pages/dashboard/teacher/courses/courses.tpl.html',
            controller: 'TeacherCoursesDashboardController',
            controllerAs: 'teacherCoursesDashboard'
        })
        .state('dashboard.course', {
            url: "/course/:courseId",
            templateUrl: 'main/pages/dashboard/teacher/course/course.tpl.html',
            controller: 'TeacherCourseDashboardController',
            controllerAs: 'teacherCourseDashboard'
        }).state('dashboard.assessment', {
            url: "/assessment",
            templateUrl: 'main/pages/dashboard/teacher/assessment/create-assessment.tpl.html',
            controller: 'CreateAssessmentController',
            controllerAs: 'createAssessment'
        })

    ;
})

;
