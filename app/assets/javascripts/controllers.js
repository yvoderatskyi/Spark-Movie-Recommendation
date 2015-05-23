angular.module('MoviesApp')
    .controller("MoviesListCtrl", function ($scope, $rootScope, $location, Movie) {
        $rootScope.PAGE = "movies-all";

        $scope.movies = Movie.query();

        $scope.sort = function (field) {
            $scope.sort.field = field;
            $scope.sort.order = !$scope.sort.order;
        };

        $scope.sort.order = false;

        $scope.show = function (id) {
            $location.url('/movies/' + id);
        };
    })
    .controller("MoviesDetailsCtrl", function ($scope, $routeParams, Movie) {
        $rootScope.PAGE = "movies-details";

        $scope.contact = Movie.get({ id: $routeParams.id });
    });
