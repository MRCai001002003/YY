define(function(require) {
    var baseUrl = require('../../common/js/baseUrl/baseUrl');
    return function(app) {
        app.controller('registerInfoControl', ['$scope', '$http', '$state',
            function($scope, $http, $state) {
                $scope.params = {};
                $scope.isShowSuffix = false;
                $scope.testSuffix = function(value) {
                    $scope.isShowSuffix = (value || '').indexOf('@') > -1;
                }
                $scope.submit = function() {
                    if ($scope.isShowSuffix) {
                        $scope.params.email += $scope.emailSuffix;
                    }
                    $http({
                        url: baseUrl + 'customer/saveOrUpdateCustomerPersonal',
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
            }
        ])
    }
})
