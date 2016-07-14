define(function(require) {
    var baseUrl = require('../../common/js/baseUrl/baseUrl');
    return function(app) {
        app.controller('userLoginControl', ['$scope', '$http', function($scope, $http) {
            $scope.submit = function() {
                $http({
                    url: baseUrl + 'customer/userLogin',
                    method: 'post',
                    data: $scope.params
                }).success(function(data) {
                    if (data.success) {
                        $state.go('menberCenter');
                    } else {
                        alert(data.msg);
                    }
                })
            }
        }])
    }
})
