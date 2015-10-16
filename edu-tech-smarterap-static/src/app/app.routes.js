angular.module('smarterap')

.config(function($urlRouterProvider, $stateProvider) {
    $urlRouterProvider.otherwise("/");
    $stateProvider
        .state('login', {
            url: '/login',
            templateUrl: 'main/pages/login/login.tpl.html',
            controller: 'LoginController',
            controllerAs: 'login'
        })
        .state('signup', {
            url: '/signup',
            templateUrl: 'main/pages/registration/registration.tpl.html',
            controller: 'RegistrationController',
            controllerAs: 'registration'
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
