angular.module('MoviesApp', ['ngRoute', 'ngResource'])
    .config(function ($routeProvider, $locationProvider) {
        $routeProvider
            .when('/movies', {
                controller: 'MoviesListCtrl',
                templateUrl: '/assets/partial/movies/list.html'
            })
            .when('/movies/:id', {
                controller: 'MoviesDetailsCtrl',
                templateUrl: '/assets/partial/movies/details.html'
            })
            .when('/popular', {
                controller: 'MoviesTopCtrl',
                templateUrl: '/assets/partial/movies/list.html'
            })
            .when('/users', {
                controller: 'UsersListCtrl',
                templateUrl: '/assets/partial/users/list.html'
            })
            .when('/users/:id', {
                controller: 'UsersDetailsCtrl',
                templateUrl: '/assets/partial/users/details.html'
            })
            .otherwise('/movies');
        $locationProvider.html5Mode({
            enabled: true,
            requireBase: false
        });
    });