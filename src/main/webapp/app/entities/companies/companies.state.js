(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('companies', {
            parent: 'entity',
            url: '/companies',
            data: {
                authorities: ['ROLE_ADMIN', 'ROLE_USER'],
                pageTitle: 'Companies'
            },
            views: {
                'content@': {
                   // templateUrl: 'app/entities/companies/companies.html',
                    templateUrl: '<h1>This Is A State</h1>',
                    controller: 'CompaniesController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();
