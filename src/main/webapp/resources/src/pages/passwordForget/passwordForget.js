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
                    method: 'get'
                }).success(function(data) {

                })
            }
            $scope.submit = function() {
                $http({
                    url: baseUrl + 'index/doSetServerCode',
                    method: 'post',
                    data: $scope.params
                }).success(function(data) {

                })
            }
        }])
    }
})
