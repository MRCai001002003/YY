define(["require","../../common/js/baseUrl/baseUrl"],function(e){var t=e("../../common/js/baseUrl/baseUrl");return function(e){e.controller("memberCenterControl",["$scope","$http","cache",function(e,n,r){e.name=r.get("name"),e.getTaoBaoInfo=function(){n({url:t+"openLoginWin",method:"get"}).success(function(e){e.success?location.href=e.data:alert(e.msg)})}}])}});
