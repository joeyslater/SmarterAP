angular.module('smarterap')

.factory('Ui', Ui);

function Ui() {
    var service = {};

    // Header Itmes
    var headerTitle = "Dashboard";
    service.setHeaderTitle = function(value) {
        headerTitle = value;
    };
    service.getHeaderTitle = function() {
        return headerTitle;
    };

    // Color Oriented Items
    service.getBorderColor = function($index) {
        return colors[$index % colors.length];
    };

    var colors = [
        "#3F51B5", "#FF5722", "#9C27B0", "#E91E63", "#FFC107", "#4CAF50", "#3F51B5", "#673AB7", "#F44336", "#009688"
    ];
    colors = shuffle(colors);

    function shuffle(array) {
        var currentIndex = array.length,
            temporaryValue, randomIndex;

        // While there remain elements to shuffle...
        while (0 !== currentIndex) {

            // Pick a remaining element...
            randomIndex = Math.floor(Math.random() * currentIndex);
            currentIndex -= 1;

            // And swap it with the current element.
            temporaryValue = array[currentIndex];
            array[currentIndex] = array[randomIndex];
            array[randomIndex] = temporaryValue;
        }

        return array;
    }


    return service;
}
