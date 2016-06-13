define(function() {
    return function(app) {
        app.controller('loginControl', ['$scope', '$http', function($scope, $http) {
            $scope.params = {};
            $scope.submit = function() {
                $http({
                    url: '/index/loginSite',
                    method: 'post',
                    data: $scope.params
                }).success(function(data) {

                })
            }
        }])
    }
})
