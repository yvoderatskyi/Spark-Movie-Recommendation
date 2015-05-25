angular.module('MoviesApp')
    .controller("MoviesListCtrl", function ($scope, $rootScope, $location, Movies) {
        $rootScope.PAGE = "movies-all";

        $scope.movies = Movies.query();

        $scope.sort = function (field) {
            $scope.sort.field = field;
            $scope.sort.order = !$scope.sort.order;
        };

        $scope.sort.field = 'id';
        $scope.sort.order = false;

        $scope.show = function (id) {
            $location.url('/movies/' + id);
        };
    })
    .controller("MoviesDetailsCtrl", function ($scope, $rootScope, $routeParams, Movies) {
        $rootScope.PAGE = "movies-details";

        $scope.movie = Movies.get({ id: $routeParams.id });
    })
    .controller("MoviesTopCtrl", function ($scope, $rootScope, $location, TopMovies) {
        $rootScope.PAGE = "movies-top";

        $scope.movies = TopMovies.query();

        $scope.sort = function (field) {
            $scope.sort.field = field;
            $scope.sort.order = !$scope.sort.order;
        };

        $scope.sort.field = 'id';
        $scope.sort.order = false;

        $scope.show = function (id) {
            $location.url('/movies/' + id);
        };
    })
    .controller("UsersListCtrl", function ($scope, $rootScope, $location, Users) {
        $rootScope.PAGE = "users-all";

        $scope.users = Users.query();

        $scope.sort = function (field) {
            $scope.sort.field = field;
            $scope.sort.order = !$scope.sort.order;
        };

        $scope.sort.field = 'id';
        $scope.sort.order = false;

        $scope.show = function (id) {
            $location.url('/users/' + id);
        };
    })
    .controller("UsersDetailsCtrl", function ($scope, $rootScope, $routeParams, Users) {
        $rootScope.PAGE = "users-details";

        $scope.user = Users.get({ id: $routeParams.id });
    })
    .controller("RecommendationsForUserCtrl", function ($scope, $location, RecommendationsForUser) {
        var id = $location.path().split('/')[2];
        $scope.ratings = RecommendationsForUser.query({ id: id });
        $scope.show = function (id) {
            $location.url('/movies/' + id);
        };
    })
    .controller("RatingForMovieCtrl", function ($scope, $location, Ratings) {
        var id = $location.path().split('/')[2];
        $scope.ratings = Ratings.forMovie({ id: id});
    })
    .controller("RatingForUserCtrl", function ($scope, $location, Ratings) {
        var id = $location.path().split('/')[2];
        $scope.ratings = Ratings.forUser({ id: id});
        $scope.show = function (id) {
            $location.url('/movies/' + id);
        };
    });
