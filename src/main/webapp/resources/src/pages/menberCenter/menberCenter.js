define(function (require) {
    var baseUrl = require('../../common/js/baseUrl/baseUrl');
    return function (app) {
        app.controller('menberCenterControl', ['$scope', '$http', 'cache', function ($scope, $http, cache) {
            $scope.name = cache.get('name');
        }])
    }
})
