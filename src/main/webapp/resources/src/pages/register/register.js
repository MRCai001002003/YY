define(function(require) {
	var baseUrl=require('../../common/js/baseUrl/baseUrl');
    return function(app) {
        app.controller('registerControl', ['$scope', '$http','$state',
            function($scope, $http,$state) {
				$scope.params={};
				$scope.submit=function(){
					$http({
						url:baseUrl+'customer/saveOrUpdateCustomer',
						method:'post',
						data:$scope.params
					}).success(function(data){
						if(data.success){
							$state.go('registerInfo');
						}else{
							alert(data.msg);
						}
					})	
				}
            }
        ])
    }
})

//http://localhost:8080/customer/saveOrUpdateCustomer?name=cz&idcard=411524&qq=294&email=294@qq.com&customerid=4