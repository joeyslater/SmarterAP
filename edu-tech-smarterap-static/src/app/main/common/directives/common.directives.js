angular.module('smarterap')

//Create a WYSIWYG Editor
.directive('contenteditable', function($sce, $log) {
    return {
        restrict: 'A',
        require: '?ngModel',
        link: function(scope, element, attrs, ngModel) {
            if (!ngModel) {
                $log.debug('Need ng-model with associaetd field.');
                return; // do nothing if no ng-model
            }

            ngModel.$render = function() {
                element.html($sce.getTrustedHtml(ngModel.$viewValue || ''));
            };

            element.on('blur keyup change', function() {
                scope.$evalAsync(read);
            });
            read();

            function read() {
                ngModel.$setViewValue(element.html());
            }
        }
    };
})

//Create an upload button
.directive('upload', function($document) {
    return {
        restrict: 'A',
        link: function(scope, elem, attrs) {
            function uploadClickHandler() {
                angular.element($document[0].querySelector('#' + attrs.upload))[0].click();
            }

            elem.on('click', uploadClickHandler);

            scope.$on('$destroy', function() {
                elem.off('click', uploadClickHandler);
            });
        }
    };
})

.directive('uploadBlackboard', function($parse) {
    return {
        restrict: 'A',
        scope: false,
        link: function(scope, element, attrs) {
            var fn = $parse(attrs.uploadBlackboard);

            function changeHandler(onChangeEvent) {
                var reader = new FileReader();

                reader.onload = function(onLoadEvent) {
                    scope.$apply(function() {
                        fn(scope, {
                            $fileContent: onLoadEvent.target.result
                        });
                    });
                };

                reader.readAsText((onChangeEvent.srcElement || onChangeEvent.target).files[0]);
            }

            element.on('change', changeHandler);

            scope.$on('$destroy', function() {
                element.off('change', changeHandler);
            });
        }
    };
})

.directive('bottomBorder', function($document) {
    return {
        restrict: 'A',
        scope: {
            bottomBorder: '@'
        },
        link: function(scope, elem, attrs) {
            angular.element(elem).css('border-bottom', '4px solid ' + attrs.bottomBorder);
        }
    };
})

;
