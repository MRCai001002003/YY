define(function(require) {
    var baseUrl = require('../../common/js/baseUrl/baseUrl');
    return function(app) {
        app.controller('menberCenterControl', ['$scope', '$http', function($scope, $http) {
			$scope.submit=function(){
				$http({
					url:baseUrl+'customer/getMenberCenter',
					method:'get',
				}).success(function(data){
					if(data.success){
						$scope.data=data;
					}else{
						alert(data.msg);
					}
				})	
			}
        
        }])
    }
})
