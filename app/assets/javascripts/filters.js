angular.module('MoviesApp')
    .filter('cleanTitle', function () {
       return function (input) {
           return input.replace(/\s\(\d{4}\)/g, '');
       };
    });