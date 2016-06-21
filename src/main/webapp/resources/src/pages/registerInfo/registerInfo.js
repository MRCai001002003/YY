define(function(require) {
    var baseUrl = require('../../common/js/baseUrl/baseUrl');
    return function(app) {
        app.controller('registerInfoControl', ['$scope', '$http', '$state', 'cache',
            function($scope, $http, $state, cache) {
                var emailList = ['@qq.com', '@163.com', '@126.com', '@139.com', '@sina.com', '@hotmail.com', '@aliyun.com', '@sohu.com'];
                $scope.params = {};
                $scope.isShowSuffix = false;
                $scope.emailList = emailList;
                $scope.isEmail = true;

                $scope.addSuffix = function(text) {
                    $scope.params.email += text;
                    $scope.isShowSuffix = false;
                    $scope.isEmail = true;
                };

                $scope.testSuffix = function(value) {
                    if (!value) {
                        $scope.isShowSuffix = false;
                        return;
                    }
                    var index = (value || '').indexOf('@');
                    if (index == -1) {
                        $scope.emailList = emailList;
                        $scope.isShowSuffix = true;
                    } else {
                        var arr = [];
                        var reg = new RegExp('^' + value.substring(index, value.length));
                        emailList.forEach(function(item) {
                            if (reg.test(item)) {
                                arr.push(item.replace(reg, ''));
                            }
                        })
                        $scope.emailList = arr;
                        $scope.isShowSuffix = !!arr.length;
                    }
                }
                $scope.submit = function() {
                    if ($scope.params.email && !/^\s+$/.test($scope.params.email)) {
                        if (!/^[^\s@]+@[^\s@]+\.\S+$/.test($scope.params.email)) {
                            $scope.isEmail = false;
                            return;
                        }
                    }

                    $http({
                        url: baseUrl + 'customer/saveOrUpdateCustomerPersonal',
                        method: 'post',
                        data: $scope.params
                    }).success(function(data) {
                        if (data.success) {
                            if (data.data.is_get_juxinli_data) {
                                cache.set('account', data.data.account);
                                $state.go('login');
                                return;
                            }
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