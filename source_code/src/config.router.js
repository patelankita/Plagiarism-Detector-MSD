'use strict';
/**
 * Config for the router
 */
angular.module('app')
    .run(
        ['$rootScope', '$state', '$stateParams',
            function ($rootScope, $state, $stateParams) {
                $rootScope.$state = $state;
                $rootScope.$stateParams = $stateParams;
            }
        ]
    )
    .config(
        function ($stateProvider, $urlRouterProvider) {
            const layout = "views/home_page.html";
            $urlRouterProvider
                .otherwise('/app/dashboard');
            $stateProvider
                .state('app', {
                    abstract: true,
                    url: '/app',
                    templateUrl: 'views/templates/home_page.html'
                })
                .state('dashboard', {
                    url: '/dashboard',
                    templateUrl: 'views/templates/dashboard.html'
                })
                .state('app.dashboard', {
                    url: '/dashboard',
                    templateUrl: 'views/templates/dashboard.html'
                })
                .state('statistics', {
                    url: '/statistics',
                    templateUrl: 'views/templates/statistics.html'
                });
        });
