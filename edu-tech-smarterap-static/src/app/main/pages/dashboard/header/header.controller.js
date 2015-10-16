angular.module('smarterap')

.controller("HeaderController", HeaderController);

function HeaderController(Ui) {
    var ctrl = this;
    ctrl.headerTitle = Ui.getHeaderTitle;
}
