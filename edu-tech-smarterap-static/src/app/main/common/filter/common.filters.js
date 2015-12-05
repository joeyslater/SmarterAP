angular.module('smarterap')

.filter('capitalize', function() {
    return function(input) {
        return (!!input) ? input.charAt(0).toUpperCase() + input.substr(1).toLowerCase() : '';
    };
})

.filter('arrayToString', function() {
    return function(input, property) {
        var strings = [];
        for (var i = 0; i < input.length; i++) {
            strings.push(input[i][property]);
        }
        return strings.join(',');
    };
})


;
