define(function (require) {
    var baseUrl = require('../../common/js/baseUrl/baseUrl');
    return function (app) {
        app.controller('taobaoVerifyCodeControl', ['$scope', '$http',
            function ($scope, $http) {
                $scope.submit = function () {
                    $http({
                        url: baseUrl + 'index/getReceiptAddress',
                        method: 'post',
                        data: $scope.params
                    }).success(function (data) {
                        if (!data.success) {
                            alert(data.msg);
                        }
                    })
                }
            }
        ])
    }
})
