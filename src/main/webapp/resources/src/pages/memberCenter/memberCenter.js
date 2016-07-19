define(function(require) {
    var baseUrl = require('../../common/js/baseUrl/baseUrl');
    return function(app) {
        app.controller('memberCenterControl', ['$scope', '$http', 'cache', function($scope, $http, cache) {
            $scope.name = cache.get('name');
            $scope.getTaoBaoInfo = function() {
                $http({
                    url: baseUrl + 'openLoginWin',
                    method: 'get'
                }).success(function(data) {
                    if (data.success) {
                        location.href = data;
                    }else{
                        alert(data.msg);
                    }
                })
            }
        }])
    }
})
