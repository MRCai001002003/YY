define(function(require) {
    var baseUrl = require('../../common/js/baseUrl/baseUrl');
    return function(app) {
        app.controller('validatyControl', ['$scope', '$http', '$state', 'cache', function($scope, $http, $state, cache) {
            $scope.params = {};
            $scope.submit = function() {
                var obj = cache.get('validaty') || {};
                angular.extend(obj, $scope.params)
                $http({
                    url: baseUrl + 'index/validateCode',
                    method: 'post',
                    data: obj
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
