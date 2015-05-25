angular.module('MoviesApp')
    .factory('Movies', function ($resource) {
        return $resource('/api/movies/:id', {id: '@id'}, {
            top: {
                method: "GET",
                isArray: true,
                params: {
                    id: "top"
                }
            }
        });
    })
    .factory('Users', function ($resource) {
        return $resource('/api/users/:id', {id: '@id'});
    })
    .factory('Ratings', function ($resource) {
        return $resource('/api/ratings/:controller/:id',
            {
                controller: '@controller',
                id: '@id'
            }, {
                forUser: {
                    method: "GET",
                    isArray: true,
                    params: {
                        controller: "user"
                    }

                },
                forMovie: {
                    method: "GET",
                    isArray: true,
                    params: {
                        controller: "movie"
                    }
                },
                recommended: {
                    method: "GET",
                    isArray: true,
                    params: {
                        controller: "recommendation"
                    }
                }
            }
        );
    });
