angular.module('smarterap')

// Probably should have a better name
.service('PropertyService', PropertyService);

function PropertyService($http) {
    var service = {};

    var course;

    service.getCourse = function() {
        return course;
    };

    service.setCourse = function(value) {
        course = value;
    };


    return service;
}
