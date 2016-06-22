define(function(require) {
    var baseUrl = require('../../common/js/baseUrl/baseUrl');
    return function(app) {
        app.controller('passwordForgetControl', ['$scope', '$http', 'cache', function($scope, $http, cache) {
            $scope.getVerifyCode = function() {
                $scope.params = {
                    cellPhone: cache.get('phone')
                };
                $http({
                    url: baseUrl + 'index/getValidateCode',
                    params:{cellPhone: scope.params.cellPhone},
                    method: 'get'
                }).success(function(data) {
                	if(!data.success){
                		alert(data.msg)
                	}
                })
            }
            $scope.submit = function() {
                $http({
                    url: baseUrl + 'index/doSetServerCode',
                    method: 'post',
                    data: $scope.params
                }).success(function(data) {
                	if(!data.success){
                		alert(data.msg)
                	}
                })
            }
        }])
    }
})
