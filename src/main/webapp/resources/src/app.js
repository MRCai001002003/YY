define(function (require) {
    //加载框架
    var angular = require('angular');
    require('ui.router');
    require('angular.animate');
    require('angular.sanitize');
    require('angular.messages');



    //初始化
    var app = angular.module("app", ['ui.router', 'ngSanitize', 'ngAnimate', 'ngMessages']);
    app.config(['$stateProvider', '$urlRouterProvider', '$httpProvider',
        function ($stateProvider, $urlRouterProvider, $httpProvider) {
            //未登录拦截
            //$httpProvider.interceptors.push('logoutInterceptor');
            $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
            $httpProvider.defaults.transformRequest = function (data) {
                if (angular.isObject(data)) {
                    var arr = [];
                    for (var key in data) {
                        if (data[key]) arr.push(key + '=' + data[key]);
                    }
                    return arr.join('&');
                }
            };


            //配置路由
            $urlRouterProvider.when('', '/home');
            $urlRouterProvider.otherwise('/home');

            $stateProvider.state("home", {
                url: '/home',
                templateUrl: require.toUrl('./pages/home/home.html'),
                controller: 'homeControl'
            }).state('register', {
                url: '/register',
                templateUrl: require.toUrl('./pages/register/register.html'),
                controller: 'registerControl'
            }).state('registerInfo', {
                url: '/registerInfo',
                templateUrl: require.toUrl('./pages/registerInfo/registerInfo.html'),
                controller: 'registerInfoControl'
            }).state('registed', {
                url: '/registed',
                templateUrl: require.toUrl('./pages/registed/registed.html'),
                controller: 'registedControl'
            }).state('certificate', {
                url: '/certificate',
                templateUrl: require.toUrl('./pages/article/certificate.html')
            }).state('manageService', {
                url: '/manageService',
                templateUrl: require.toUrl('./pages/article/manageService.html')
            }).state('login', {
                url: '/login',
                templateUrl: require.toUrl('./pages/login/login.html'),
                controller: 'loginControl'
            }).state('validaty', {
                url: '/validaty',
                templateUrl: require.toUrl('./pages/validaty/validaty.html'),
                controller: 'validatyControl'
            }).state('bindCard', {
                url: '/bindCard',
                templateUrl: require.toUrl('./pages/bindCard/bindCard.html'),
                controller: 'bindCardControl'
            }).state('bindSuccess', {
                url: '/bindSuccess',
                templateUrl: require.toUrl('./pages/bindSuccess/bindSuccess.html'),
                controller: 'bindSuccessControl'
            }).state('info', {
                url: '/info',
                templateUrl: require.toUrl('./pages/info/info.html'),
                controller: 'infoControl'
            }).state('infoEdit', {
                url: '/infoEdit',
                templateUrl: require.toUrl('./pages/infoEdit/infoEdit.html'),
                controller: 'infoEditControl'
            }).state('loan', {
                url: '/loan',
                templateUrl: require.toUrl('./pages/loan/loan.html'),
                controller: 'loanControl'
            }).state('loanOk', {
                url: '/loanOk',
                templateUrl: require.toUrl('./pages/loanOk/loanOk.html'),
                controller: 'loanOkControl'
            }).state('repayment', {
                url: '/repayment',
                templateUrl: require.toUrl('./pages/repayment/repayment.html'),
                controller: 'repaymentControl'
            }).state('setbacks', {
                url: '/setbacks',
                templateUrl: require.toUrl('./pages/setbacks/setbacks.html'),
                controller: 'setbacksControl'
            }).state('passwordForget', {
                url: '/passwordForget',
                templateUrl: require.toUrl('./pages/passwordForget/passwordForget.html'),
                controller: 'passwordForgetControl'
            }).state('menberCenter', {
                url: '/menberCenter',
                templateUrl: require.toUrl('./pages/menberCenter/menberCenter.html'),
                controller: 'menberCenterControl'
            }).state('taobaoLogin', {
                url: '/taobaoLogin',
                templateUrl: require.toUrl('./pages/taobaoLogin/taobaoLogin.html'),
                controller: 'taobaoLoginControl'
            }).state('taobaoVerifyCode', {
                url: '/taobaoVerifyCode',
                templateUrl: require.toUrl('./pages/taobaoVerifyCode/taobaoVerifyCode.html'),
                controller: 'taobaoVerifyCodeControl'
            }).state('userLogin', {
                url: '/userLogin',
                templateUrl: require.toUrl('./pages/userLogin/userLogin.html'),
                controller: 'userLoginControl'
            }).state('loanOrders', {
                url: '/loanOrders',
                templateUrl: require.toUrl('./pages/loanOrders/loanOrders.html'),
                controller: 'loanOrdersControl'
            })

        }
    ]);

    require('./pages/service')(app);
    //    //    加载指令
    require('./pages/filter')(app);
    //
    require('./pages/directive')(app);
    //
    //    //加载控制器

    require('./pages/home/home')(app);
    require('./pages/register/register')(app);
    require('./pages/registerInfo/registerInfo')(app);
    require('./pages/registed/registed')(app);
    require('./pages/login/login')(app);
    require('./pages/passwordForget/passwordForget')(app);
    require('./pages/validaty/validaty')(app);
    require('./pages/bindCard/bindCard')(app);
    require('./pages/bindSuccess/bindSuccess')(app);
    require('./pages/info/info')(app);
    require('./pages/infoEdit/infoEdit')(app);
    require('./pages/loan/loan')(app);
    require('./pages/loanOk/loanOk')(app);
    require('./pages/repayment/repayment')(app);
    require('./pages/setbacks/setbacks')(app);
    require('./pages/menberCenter/menberCenter')(app);
    require('./pages/taobaoLogin/taobaoLogin')(app);
    require('./pages/taobaoVerifyCode/taobaoVerifyCode')(app);
    require('./pages/userLogin/userLogin')(app);
    require('./pages/loanOrders/loanOrders')(app);

    //启动应用
    angular.element(document).ready(function () {
        var alertEle = document.getElementById('alert');
        var alertEleOldClassName = alertEle.className;
        var isAlert = false;
        var alertCallback = null;
        window.alert = function (msg, callback) {
            alertEle.style.display = 'block';
            setTimeout(function () {
                alertEle.className = alertEleOldClassName + ' modal-show';
                isAlert = true;
            }, 0);
            alertEle.getElementsByTagName('p')[0].innerText = msg;
            alertCallback = callback;
        };
        alertEle.getElementsByTagName('button')[0].onclick = function () {
            isAlert = false;
            alertEle.className = alertEleOldClassName;
            alertCallback && alertCallback();
            alertCallback = null;
        };

        function hide() {
            if (!isAlert) {
                alertEle.style.display = 'none';
            }
        }
        alertEle.addEventListener('webkitTransitionEnd', hide);
        alertEle.addEventListener('transitionend', hide);

        var bodyEle = document.body;
        window.prompt = function (msg) {
            var promptObj = document.createElement('div');
            bodyEle.appendChild(promptObj);
            promptObj.className = 'prompt-msg';
            promptObj.innerHTML = '<div class="prompt"><p class="text-overflow fl"></p></div>';
            var msgBox = promptObj.getElementsByTagName('p')[0];
            var msgDiv = promptObj.getElementsByTagName('div')[0];
            msgBox.innerText = msg;
            var width = msgBox.offsetWidth;
            msgDiv.style.width = width + 'px';
            msgDiv.style.marginLeft = width / -2 + 'px';

            function hide() {
                bodyEle.removeChild(promptObj);
                promptObj = msgBox = msgDiv = null;
            }
            promptObj.addEventListener('webkitAnimationEnd', hide);
            promptObj.addEventListener('animationend', hide);
        };



        var ele = document.getElementById('page-wrap');
        app.run(['$rootScope', '$location', '$state',

            function ($rootScope, $location, $state) {
                $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
                    var animateObj = {
                        home: {
                            register: 'slideInRight slideOutLeft',
                            certificate: 'slideInRight slideOutLeft',
                            manageService: 'slideInRight slideOutLeft',
                            userLogin: 'slideInRight slideOutLeft'
                        },
                        register: {
                            home: 'slideInLeft slideOutRight',
                            registerInfo: 'slideInRight slideOutLeft'
                        },
                        registerInfo: {
                            register: 'slideInLeft slideOutRight',
                            registed: 'slideInRight slideOutLeft',
                            login: 'slideInRight slideOutLeft'
                        },
                        login: {
                            registerInfo: 'slideInLeft slideOutRight',
                            validaty: 'slideInRight slideOutLeft',
                            passwordForget: 'slideInRight slideOutLeft'
                        },
                        passwordForget: {
                            login: 'slideInLeft slideOutRight'
                        },
                        validaty: {
                            login: 'slideInLeft slideOutRight'
                        },
                        registed: {
                            registerInfo: 'slideInLeft slideOutRight'
                        },
                        certificate: {
                            home: 'slideInLeft slideOutRight'
                        },
                        manageService: {
                            home: 'slideInLeft slideOutRight'
                        },
                        taobaoLogin: {
                            taobaoVerifyCode: 'slideInRight slideOutLeft'
                        },
                        taobaoVerifyCode: {
                            taobaoLogin: 'slideInLeft slideOutRight'
                        },
                        userLogin: {
                            home: 'slideInLeft slideOutRight',
                            menberCenter: 'slideInRight slideOutLeft'
                        },
                        menberCenter: {
                            userLogin: 'slideInLeft slideOutRight',
                            info: 'slideInRight slideOutLeft',
                            loanOrders: 'slideInRight slideOutLeft'
                        },
                        info: {
                            menberCenter: 'slideInLeft slideOutRight'
                        },
                        loanOrders: {
                            menberCenter: 'slideInLeft slideOutRight'
                        }
                    };
                    var animateClass = animateObj[fromState.name] || '';
                    animateClass = animateClass[toState.name] || '';
                    ele.className = 'page-wrap animated ui-view-container ' + animateClass;
                });
            }
        ]);
        //console.log(app);
        angular.bootstrap(document, ['app']);
    })
})
