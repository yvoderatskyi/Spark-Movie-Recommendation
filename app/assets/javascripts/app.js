angular.module('MoviesApp', ['ngRoute', 'ngResource'])
    .config(function ($routeProvider, $locationProvider) {
        $routeProvider
            .when('/movies', {
                controller: 'MoviesListCtrl',
                templateUrl: '/assets/views/movies-list.html'
            })
            .when('/movies/:id', {
                controller: 'MoviesDetailsCtrl',
                templateUrl: '/assets/views/movies-details.html'
            })
            .otherwise('/movies');
        $locationProvider.html5Mode(true);
    });