define(function (require) {
    var baseUrl = require('../../common/js/baseUrl/baseUrl');
    return function (app) {
        app.controller('infoControl', ['$scope', '$http', function ($scope, $http) {
            $http({
                url: baseUrl + '',
                method: 'get'
            }).success(function (data) {
                $scope.data = data;
            })
        }])
    }
})
