define(function(require) {
	var baseUrl=require('../../common/js/baseUrl/baseUrl');
    return function(app) {
        app.controller('registerInfoControl', ['$scope', '$http','$state',
            function($scope, $http,$state) {
				$scope.params={};
				$scope.submit=function(){
					$http({
						url:baseUrl+'customer/saveOrUpdateCustomerPersonal',
						method:'post',
						data:$scope.params
					}).success(function(data){
						if(data.success){
							$state.go('registed');
						}else{
							alert(data.msg);
						}
					})	
				}
            }
        ])
    }
})
