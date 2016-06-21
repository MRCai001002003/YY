define(function(require) {
    var baseUrl = require('../../common/js/baseUrl/baseUrl');
    return function(app) {
        app.controller('passwordForgetControl', ['$scope', '$http', function($scope, $http) {
            $scope.getVerifyCode = function() {
                $http({
                    url: baseUrl + 'index/getValidateCode',
                    method: 'get'
                }).success(function(data) {

                })
            }
        }])
    }
})
