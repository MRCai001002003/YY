define(function(require) {
    var baseUrl = require('../../common/js/baseUrl/baseUrl');
    return function(app) {
        app.controller('loginControl', ['$scope', '$http', '$state', 'cache', function($scope, $http, $state, cache) {
            $scope.params = {
                account: cache.get('account')
            };
            $scope.submit = function() {
                $http({
                    url: baseUrl + 'index/loginSite',
                    method: 'post',
                    data: $scope.params
                }).success(function(data) {
                    if (data.success) {
                        cache.set('validaty', data.data);
                        $state.go('validaty');
                    } else {
                        alert(data.msg);
                    }
                })
            }
        }])
    }
})
