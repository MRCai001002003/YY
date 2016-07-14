define(function (require) {
    var baseUrl = require('../../common/js/baseUrl/baseUrl');
    return function (app) {
        app.controller('userLoginControl', ['$scope', '$http', '$state', 'cache', function ($scope, $http, $state, cache) {
            $scope.submit = function () {
                $http({
                    url: baseUrl + 'customer/userLogin',
                    method: 'post',
                    data: $scope.params
                }).success(function (data) {
                    if (data.success) {
                        cache.set('phone', data.data.cellPhone);
                        switch (data.data.customerStatus) {
                            case 'register':
                                $state.go('register');
                                break;
                            case 'BASIC':
                                $state.go('registerInfo');
                                break;
                            default:
                                $state.go('menberCenter');
                        }
                    } else {
                        alert(data.msg);
                    }
                })
            }
            $scope.getVerifyCode = function () {
                $http({
                    url: baseUrl + 'sms/sendLoginCode',
                    method: 'post',
                    data: {
                        cellPhone: $scope.params.cellPhone
                    }
                }).success(function (data) {
                    if (!data.success) {
                        alert(data.msg);
                    }
                })
            }
        }])
    }
})
