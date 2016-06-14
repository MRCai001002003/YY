define(function(require) {
    var baseUrl = require('../../common/js/baseUrl/baseUrl');
    return function(app) {
        app.controller('validatyControl', ['$scope', '$http', '$state', 'cache', function($scope, $http, $state, cache) {
            $scope.params = {};
            $scope.submit = function() {
                angular.extend($scope.params, cache.get('validaty'))
                $http({
                    url: baseUrl + 'index/validateCode',
                    method: 'post',
                    data: $scope.params
                }).success(function(data) {
                    if (data.success) {
                        $state.go('registed');
                    } else {
                        alert(data.msg);
                    }
                })
            }
        }])
    }
})
