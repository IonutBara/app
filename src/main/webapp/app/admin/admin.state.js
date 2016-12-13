(function () {
    'use strict';

    angular
        .module('platformWebApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('admin', {
            abstract: true,
            parent: 'app'
        });
    }
})();
