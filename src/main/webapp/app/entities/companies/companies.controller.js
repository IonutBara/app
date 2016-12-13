'use strict';

var myApp = angular.module('platformWebApp',['ui.bootstrap']);

myApp.controller('CompaniesController', ['$scope', '$state', '$rootScope', '$routeParams', 'CompaniesFactory',
    function($scope, $state, $rootScope, $routeParams, CompaniesFactory) {
    var vm = this;

    $scope.companies = CompaniesFactory.query().success(function(data) {
         console.log('Companies =>',data);
         $scope.success = true;
       })
       .error(function(data, status) {
         console.error('Repos error', status, data);
         $scope.error =  true;
       })
       .finally(function() {
         console.log("finally finished listing companies");
       });



}]);



/*
// In our controller we get the ID from the URL using ngRoute and $routeParams
// We pass in $routeParams and our Notes factory along with $scope
app.controller('NotesCtrl', ['$scope', '$routeParams', 'Notes',
                                   function($scope, $routeParams, Notes) {
// First get a note object from the factory
var note = Notes.get({ id:$routeParams.id });
$id = note.id;

// Now call update passing in the ID first then the object you are updating
Notes.update({ id:$id }, note);*/
