'use strict';

const fs = require('fs');
require('shelljs/global');

angular.module('app')
    .controller('AppCtrl', AppCtrl);

function AppCtrl($scope) {
    // config
    $scope.app = {
        name: 'Plagarism Detector',
        version: '1.0.0',
        settings: {
            themeID: 1,
            navbarHeaderColor: 'bg-black',
            navbarCollapseColor: 'bg-white-only',
            asideColor: 'bg-black',
            headerFixed: true,
            asideFixed: false,
            asideFolded: false,
            asideDock: false,
            container: false
        }

    };

}
