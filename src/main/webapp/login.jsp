<!DOCTYPE html>
<!--<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>-->
<html ng-app="app">

<head>
    <meta charset="UTF-8">
    <title>元玉数据</title>
    <meta content="telephone=no" name="format-detection">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" type="text/css" href="./resources/library/bootstrap/dist/css/bootstrap.min.css">
    <script src="./resources/library/angular/angular.min.js"></script>
    <script>
        (function() {
            var app = angular.module('app', []);
            app.config(['$httpProvider',
                function($httpProvider) {
                    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
                    $httpProvider.defaults.transformRequest = function(data) {
                        if (angular.isObject(data)) {
                            var arr = [];
                            for (var key in data) {
                                if (data[key]) arr.push(key + '=' + data[key]);
                            }
                            return arr.join('&');
                        }
                    };
                }
            ]);
            app.controller('formControl', ['$scope', '$http', function($scope, $http) {
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
        })()
    </script>
</head>

<body>
    <div class="container-fluid">
        <br>
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4>【聚信立&amp;机构】对接演示页面</h4>
                    </div>
                    <div ng-controller="formControl" class="panel-body">
                        <div class="row">
                            <div class="col-md-10 col-md-offset-1">
                                <form ng-submit="submit()" class="form-horizontal" novalidate name="apiForm">
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">当前网站</label>
                                        <div class="col-sm-8">
                                            <input type="text" required ng-model="params.currentWebsite" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">网站账号</label>
                                        <div class="col-sm-8">
                                            <input type="text" required ng-model="params.websiteAccount" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">服务密码</label>
                                        <div class="col-sm-8">
                                            <input type="text" required ng-model="params.pwd" class="form-control">
                                        </div>
                                    </div>
                                    <p>
                                        <button ng-disabled="apiForm.$invalid" class="btn btn-primary btn-block" type="submit">登录</button>
                                    </p>
                                    <div class="text-right">
                                        <a>忘记密码</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer"></div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>