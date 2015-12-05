angular.module('smarterap');

// .directive('pagination', function() {
//     return {
//         restrict: 'A',
//         require: '?ngModel',
//         link: function(scope, element, attrs, ngModel) {
//             if (!ngModel) {
//                 $log.debug('Need ng-model with associaetd field.');
//                 return; // do nothing if no ng-model
//             }

//             ngModel.$render = function() {
//                 element.html($sce.getTrustedHtml(ngModel.$viewValue || ''));
//             };

//             element.on('blur keyup change', function() {
//                 scope.$evalAsync(read);
//             });
//             read();

//             function read() {
//                 ngModel.$setViewValue(element.html());
//             }
//         }
//     };
// })
