angular.module('MoviesApp')
    .factory('Movies', function ($resource) {
       return $resource('/api/movies/:id', { id: '@id' }, {
           'update': { method: 'PUT' }
       });
    })
    .factory('TopMovies', function ($resource) {
        return $resource('/api/movies/top/:id', { id: '@id' }, {
            'update': { method: 'PUT' }
        });
    })
    .factory('Users', function ($resource) {
        return $resource('/api/users/:id', { id: '@id' }, {
            'update': { method: 'PUT' }
        });
    })
    .factory('RecommendationsForUser', function ($resource) {
        return $resource('/api/recommend/:id', { id: '@id' });
    })
    .factory('RatingsForMovie', function ($resource) {
        return $resource('/api/ratings/movie/:id', { id: '@id' });
    })
    .factory('RatingsForUser', function ($resource) {
        return $resource('/api/ratings/user/:id', { id: '@id' });
    });
