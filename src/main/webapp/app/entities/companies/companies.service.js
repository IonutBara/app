(function() {
    'use strict';

    angular
        .module('platformWebApp')
        .factory('CompaniesFactory', CompaniesFactory);

    CompaniesFactory.$inject = ['$resource'];

    function CompaniesFactory ($resource) {
        var service = $resource('api/companies/:id', {id:'@id'}, {
            query: {method: 'GET', isArray: true, cancellable: true},
            'get': { method: 'GET', params: {}, isArray: false,
                interceptor: {
                    response: function(response) {
                        // expose response
                        return response;
                    }
                }
            },
            'save': { method: 'POST' },
            'update' : { method: 'PUT' },
            'delete': { method: 'DELETE' }
        });

        return service;
    }
})();



/*
app.factory('Notes', ['$resource', function($resource) {
return $resource('/notes/:id', null,
    {
        'update': { method:'PUT' }
    });
}]);*/
