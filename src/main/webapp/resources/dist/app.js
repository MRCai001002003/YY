define("pages/service",["require"],function(e){return function(e){e.service("cache",function(){var e={};this.set=function(t,n){e[t]=n},this.get=function(t){return e[t]}}),e.factory("validatePattern",function(){return{bankCard:function(e){if(e.length<14||e.length>19)return!1;var t=/^\d*$/;if(!t.exec(e))return!1;var n="10,18,30,35,36,37,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,58,60,62,65,68,69,84,87,88,94,95,98,99";if(n.indexOf(e.substring(0,2))==-1)return!1;var r=e.substr(e.length-1,1),i=e.substr(0,e.length-1),s=new Array;for(var o=i.length-1;o>-1;o--)s.push(i.substr(o,1));var u=[],a=[],f=new Array;for(var l=0;l<s.length;l++)(l+1)%2==1?parseInt(s[l])*2<9?u.push(parseInt(s[l])*2):a.push(parseInt(s[l])*2):f.push(s[l]);var c=[],h=[];for(var p=0;p<a.length;p++)c.push(parseInt(a[p])%10),h.push(parseInt(a[p])/10);var d=0,v=0,m=0,g=0,y=0;for(var b=0;b<u.length;b++)d+=parseInt(u[b]);for(var w=0;w<f.length;w++)v+=parseInt(f[w]);for(var E=0;E<c.length;E++)m+=parseInt(c[E]),g+=parseInt(h[E]);y=parseInt(d)+parseInt(v)+parseInt(m)+parseInt(g);var S=parseInt(y)%10==0?10:parseInt(y)%10,x=10-S;return r==x?!0:!1},idcard:function(e){function t(e){e=s(e.replace(/ /g,""));if(e.length==15)return i(e);if(e.length==18){var t=e.split("");return r(e)&&n(t)?!0:!1}return!1}function n(e){var t=0;e[17].toLowerCase()=="x"&&(e[17]=10);for(var n=0;n<17;n++)t+=o[n]*e[n];return valCodePosition=t%11,e[17]==u[valCodePosition]?!0:!1}function r(e){var t=e.substring(6,10),n=e.substring(10,12),r=e.substring(12,14),i=new Date(t,parseFloat(n)-1,parseFloat(r));return i.getFullYear()!=parseFloat(t)||i.getMonth()!=parseFloat(n)-1||i.getDate()!=parseFloat(r)?!1:!0}function i(e){var t=e.substring(6,8),n=e.substring(8,10),r=e.substring(10,12),i=new Date(t,parseFloat(n)-1,parseFloat(r));return i.getYear()!=parseFloat(t)||i.getMonth()!=parseFloat(n)-1||i.getDate()!=parseFloat(r)?!1:!0}function s(e){return e.replace(/(^\s*)|(\s*$)/g,"")}var o=[7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1],u=[1,0,10,9,8,7,6,5,4,3,2];return t(e)}}}),e.factory("logoutInterceptor",["$q",function(e){var t=document.getElementById("progress-bar"),n={request:function(e){return t.style.display="block",e},response:function(e){return t.style.display="none",typeof e.data=="object"&&/login failed/.test(e.data.msg)?(location.replace("#/userLogin"),{}):e},requestError:function(t){return e.reject(t)},responseError:function(e){return e}};return n}])}}),define("pages/filter",[],function(){return function(e){}}),define("common/js/baseUrl/baseUrl",[],function(){return"./"}),define("pages/directive",["require","iScroll","hammer","angular","../common/js/baseUrl/baseUrl"],function(e){var t=e("iScroll"),n=e("hammer"),r=e("angular"),i=e("../common/js/baseUrl/baseUrl");return function(e){e.directive("tap",function(){return function(e,t,r,i){var s=new n(t[0]);s.on("tap",function(){e.$apply(r.tap),t.attr("href")&&t.attr("href")!="javascript:;"&&(location.href=r.href)})}}),e.directive("formValidated",function(){return{restrict:"A",require:"^?form",link:function(e,t,n,r){var i=t[0];t.on("submit",function(){var t=!1;for(var s=0;s<i.length;s++){var o=i[s].name;o&&r[o]&&r[o].$invalid&&(t=!0,r[o].$pristine=!1)}return t?e.$apply():e.$apply(n.formValidated),!1})}}}),e.directive("doValidate",function(){return{restrict:"A",require:"^?form",link:function(e,t,r,i){var s=new n(t[0]);s.on("tap",function(){var n=!1;e.isDisabled=!1;var s=r.doValidate;s&&i[s]&&(i[s].$invalid?(n=!0,i[s].$pristine=!1,t.attr("is-disabled","true")):t.attr("is-disabled","false"));if(n)return e.$apply(),!1})}}}),e.directive("bankCard",["validatePattern",function(e){return{restrict:"A",scope:!0,require:"ngModel",link:function(t,n,r,i){i.$parsers.unshift(function(t){var r=n[0].selectionStart,s=t.split("");s.splice(r,0,"index");var o="",u=0;for(var a=0;a<s.length;a++)s[a]=="index"&&(r=o.length),/\d/.test(s[a])&&(o.length&&(o.length-u)%4==0&&(o+=" ",u++),o+=s[a]);n.val(o),n[0].setSelectionRange(r,r);var f=o.replace(/\s/g,"");return e.bankCard(f)?(i.$setValidity("bankCard",!0),f):(i.$setValidity("bankCard",!1),undefined)})}}}]),e.directive("idcard",["validatePattern",function(e){return{restrict:"A",scope:!0,require:"ngModel",link:function(t,n,r,i){i.$parsers.unshift(function(t){var r=n[0].selectionStart,s=t.split(""),o=s[s.length-1];s.splice(r,0,"index");var u="",a=0;for(var f=0;f<s.length;f++){if(s[f]==="index"){r=u.length;continue}/\d/.test(s[f])&&(u.length>5&&(u.length+2-a)%4===0&&(u+=" ",a++),u+=s[f])}/x/i.test(o)&&(u+=o,r++),n.val(u),n[0].setSelectionRange(r,r);var l=u.replace(/\s/g,"");return e.idcard(l)?(i.$setValidity("idcard",!0),l):(i.$setValidity("idcard",!1),undefined)})}}}]),e.directive("phone",function(){return{restrict:"A",scope:!0,require:"ngModel",link:function(e,t,n,r){r.$parsers.unshift(function(e){var n=t[0].selectionStart,i=e.split("");i.splice(n,0,"index");var s="",o=0;for(var u=0;u<i.length;u++)i[u]==="index"&&(n=s.length),/\d/.test(i[u])&&((s.length-o+1)%4==0&&(s+=" ",o++),s+=i[u]);t.val(s),t[0].setSelectionRange(n,n);var a=s.replace(/\D/g,"");return/^1[345678]\d{9}$/.test(a)?(r.$setValidity("phone",!0),a):(r.$setValidity("phone",!1),undefined)})}}}),e.directive("checkVerifyCode",["cache",function(e){var t=60;return e.set("timer",t),{restrict:"A",scope:!0,require:"^?form",link:function(r,i,s,o){function u(){i.html(f+"秒");var n=setInterval(function(){f--;if(f<0){f=t,e.set("timer",f),i.html(a),clearInterval(n),n=null;return}i.html(f+"秒"),e.set("timer",f)},1e3)}var a=i.html(),f=e.get("timer");f!==t&&u();var l=new n(i[0]);l.on("tap",function(){if(f!==t)return;var e=s.doValidate;e&&o[e]?o[e].$invalid||(r.$apply(s.checkVerifyCode),u()):(r.$apply(s.checkVerifyCode),u())})}}}]),e.directive("repeatKey",function(){return{require:"ngModel",link:function(e,t,n,r){var i=t.inheritedData("$formController")[n.repeatKey];r.$parsers.push(function(e){if(e===i.$viewValue)return r.$setValidity("repeatKey",!0),e;r.$setValidity("repeatKey",!1)}),i.$parsers.push(function(e){return r.$setValidity("repeatKey",e===r.$viewValue),e})}}}),e.directive("iscroll",["$parse",function(e){return{link:function(e,n,r){n[0].children[0].style.minHeight=n[0].offsetHeight+1+"px";var i=new t(n[0],{probeType:2,scrollbars:!0,fadeScrollbars:!0,shrinkScrollbars:"clip"})}}}]),e.directive("modal",function(){return{restrict:"A",link:function(e,t,n){var r=!1;t.on("webkitTransitionEnd transitionend",function(){!r&&t.css("display","none")}),e.$watch(n.modal,function(e){r=e,e?(t.css("display","block"),setTimeout(function(){t.addClass("modal-show")},0)):t.removeClass("modal-show")})}}}),e.directive("testCode",["$http","$state","$parse","baseUrl",function(e,t,r,i){var s=!0;return{restrict:"A",link:function(e,i,o){s=!0;var u=!1,a=new n(i[0]);a.on("tap",function(){var n=r(o.verifyCodeState)(e);if(n)t.go(o.targetPage,r(o.stateParams)(e));else{e.$apply(o.testCode);if(u)return;u=!0;var i=e.$watch(o.verifyCodeState,function(n){n&&s&&(i(),s=!1,t.go(o.targetPage,r(o.stateParams)(e)))})}return!1})}}}]),e.directive("cancelBubble",function(){return{restrict:"A",link:function(e,t,r){var i=new n(t[0]);i.on("tap",function(e){e.srcEvent.stopPropagation(),e.cancelBubble=!0})}}}),e.directive("backPage",["$state","$stateParams",function(e,t){return{restrict:"A",link:function(r,i,s){var o=new n(i[0]);o.on("tap",function(){history.length>1?history.back():e.go(s.backPage,t)})}}}])}}),define("pages/home/home",["require","../../common/js/baseUrl/baseUrl"],function(e){var t=e("../../common/js/baseUrl/baseUrl");return function(e){e.controller("homeControl",["$scope","$http","$state","cache",function(e,n,r,i){e.params={isAccept:!0},e.submit=function(){n({url:t+"customer/saveCustomerLoan",method:"post",data:e.params}).success(function(e){if(e.success)switch(e.data){case"DRAFT":r.go("register");break;case"BASIC":r.go("registerInfo");break;case"exist":r.go("registed");break;default:r.go("register")}else alert(e.msg)})},e.getVerifyCode=function(){n({url:t+"sms/sendVerificationCode",method:"post",data:{cellPhone:e.params.cellPhone}}).success(function(t){t.success?i.set("phone",e.params.cellPhone):alert(t.msg)})}}])}}),define("pages/register/register",["require","../../common/js/baseUrl/baseUrl"],function(e){var t=e("../../common/js/baseUrl/baseUrl");return function(e){e.controller("registerControl",["$scope","$http","$state",function(e,n,r){e.params={},e.submit=function(){n({url:t+"customer/filter/saveOrUpdateCustomer",method:"post",data:e.params}).success(function(e){e.success?r.go("registerInfo"):alert(e.msg)})}}])}}),define("pages/registerInfo/registerInfo",["require","../../common/js/baseUrl/baseUrl"],function(e){var t=e("../../common/js/baseUrl/baseUrl");return function(e){e.controller("registerInfoControl",["$scope","$http","$state","cache",function(e,n,r,i){var s=["@qq.com","@163.com","@126.com","@139.com","@sina.com","@hotmail.com","@aliyun.com","@sohu.com"];e.params={},e.isShowSuffix=!1,e.emailList=s,e.isEmail=!0,e.addSuffix=function(t){e.params.email+=t,e.isShowSuffix=!1,e.isEmail=!0},e.testSuffix=function(t){if(!t){e.isShowSuffix=!1;return}var n=(t||"").indexOf("@");if(n==-1)e.emailList=s,e.isShowSuffix=!0;else{var r=[],i=new RegExp("^"+t.substring(n,t.length));s.forEach(function(e){i.test(e)&&r.push(e.replace(i,""))}),e.emailList=r,e.isShowSuffix=!!r.length}},e.submit=function(){if(e.params.email&&!/^\s+$/.test(e.params.email)&&!/^[^\s@]+@[^\s@]+\.\S+$/.test(e.params.email)){e.isEmail=!1;return}n({url:t+"customer/filter/saveOrUpdateCustomerPersonal",method:"post",data:e.params}).success(function(e){if(e.success){if(e.data.is_get_juxinli_data){i.set("account",e.data.account),r.go("login");return}r.go("registed")}else alert(e.msg)})}}])}}),define("pages/registed/registed",[],function(){return function(e){e.controller("registedControl",["$scope","$http",function(e,t){}])}}),define("pages/login/login",["require","../../common/js/baseUrl/baseUrl"],function(e){var t=e("../../common/js/baseUrl/baseUrl");return function(e){e.controller("loginControl",["$scope","$http","$state","cache",function(e,n,r,i){e.params={account:i.get("account")},e.submit=function(){n({url:t+"index/loginSite",method:"post",data:e.params}).success(function(e){e.success?(i.set("validaty",e.data),r.go("validaty")):alert(e.msg)})}}])}}),define("pages/passwordForget/passwordForget",["require","../../common/js/baseUrl/baseUrl"],function(e){var t=e("../../common/js/baseUrl/baseUrl");return function(e){e.controller("passwordForgetControl",["$scope","$http",function(e,t){}])}}),define("pages/validaty/validaty",["require","../../common/js/baseUrl/baseUrl"],function(e){var t=e("../../common/js/baseUrl/baseUrl");return function(e){e.controller("validatyControl",["$scope","$http","$state","cache",function(e,n,r,i){e.params={},e.submit=function(){var s=i.get("validaty")||{};angular.extend(s,e.params),n({url:t+"index/validateCode",method:"post",data:s}).success(function(e){e.success?r.go("registed"):alert(e.msg)})}}])}}),define("pages/bindCard/bindCard",["require","../../common/js/baseUrl/baseUrl"],function(e){var t=e("../../common/js/baseUrl/baseUrl");return function(e){e.controller("bindControl",["$scope","$http",function(e,t){}])}}),define("pages/bindSuccess/bindSuccess",["require","../../common/js/baseUrl/baseUrl"],function(e){var t=e("../../common/js/baseUrl/baseUrl");return function(e){e.controller("bindSuccessControl",["$scope","$http",function(e,t){}])}}),define("pages/info/info",["require","../../common/js/baseUrl/baseUrl"],function(e){var t=e("../../common/js/baseUrl/baseUrl");return function(e){e.controller("infoControl",["$scope","$http",function(e,n){n({url:t+"customer/filter/getMenberCenter",method:"get"}).success(function(t){e.data=t,t.success||alert(t.msg)})}])}}),define("pages/infoEdit/infoEdit",["require","../../common/js/baseUrl/baseUrl"],function(e){var t=e("../../common/js/baseUrl/baseUrl");return function(e){e.controller("infoEditControl",["$scope","$http",function(e,t){}])}}),define("pages/loan/loan",["require","../../common/js/baseUrl/baseUrl"],function(e){var t=e("../../common/js/baseUrl/baseUrl");return function(e){e.controller("loanControl",["$scope","$http",function(e,t){}])}}),define("pages/loanOk/loanOk",["require","../../common/js/baseUrl/baseUrl"],function(e){var t=e("../../common/js/baseUrl/baseUrl");return function(e){e.controller("loanOkControl",["$scope","$http",function(e,t){}])}}),define("pages/repayment/repayment",["require","../../common/js/baseUrl/baseUrl"],function(e){var t=e("../../common/js/baseUrl/baseUrl");return function(e){e.controller("repaymentControl",["$scope","$http",function(e,t){}])}}),define("pages/setbacks/setbacks",["require","../../common/js/baseUrl/baseUrl"],function(e){var t=e("../../common/js/baseUrl/baseUrl");return function(e){e.controller("setbacksControl",["$scope","$http",function(e,t){}])}}),define("pages/memberCenter/memberCenter",["require","../../common/js/baseUrl/baseUrl"],function(e){var t=e("../../common/js/baseUrl/baseUrl");return function(e){e.controller("memberCenterControl",["$scope","$http","cache",function(e,n,r){e.name=r.get("name"),e.getTaoBaoInfo=function(){n({url:t+"openLoginWin",method:"get"}).success(function(e){e.success?location.href=e:alert(e.msg)})}}])}}),define("pages/taobaoLogin/taobaoLogin",["require","../../common/js/baseUrl/baseUrl"],function(e){var t=e("../../common/js/baseUrl/baseUrl");return function(e){e.controller("taobaoLoginControl",["$scope","$http",function(e,n){e.submit=function(){n({url:t+"index/getReceiptAddress",method:"post",data:e.params}).success(function(e){e.success||alert(e.msg)})}}])}}),define("pages/taobaoVerifyCode/taobaoVerifyCode",["require","../../common/js/baseUrl/baseUrl"],function(e){var t=e("../../common/js/baseUrl/baseUrl");return function(e){e.controller("taobaoVerifyCodeControl",["$scope","$http",function(e,n){e.submit=function(){n({url:t+"index/getReceiptAddress",method:"post",data:e.params}).success(function(e){e.success||alert(e.msg)})}}])}}),define("pages/userLogin/userLogin",["require","../../common/js/baseUrl/baseUrl"],function(e){var t=e("../../common/js/baseUrl/baseUrl");return function(e){e.controller("userLoginControl",["$scope","$http","$state","cache",function(e,n,r,i){e.params={},e.submit=function(){n({url:t+"customer/userLogin",method:"post",data:e.params}).success(function(e){if(e.success){i.set("name",e.data.name);switch(e.data.customerStatus){case"DRAFT":r.go("register");break;case"BASIC":r.go("registerInfo");break;default:r.go("memberCenter")}}else alert(e.msg)})},e.getVerifyCode=function(){n({url:t+"sms/sendLoginCode",method:"post",data:{cellPhone:e.params.cellPhone}}).success(function(e){e.success||alert(e.msg)})}}])}}),define("pages/loanOrders/loanOrders",["require","../../common/js/baseUrl/baseUrl"],function(e){var t=e("../../common/js/baseUrl/baseUrl");return function(e){e.controller("loanOrdersControl",["$scope","$http",function(e,n){n({url:t+"loadOrder/filter/getLoadOrders",method:"get"}).success(function(t){e.data=t,t.success||alert(t.msg)})}])}}),define("app",["require","angular","ui.router","angular.animate","angular.sanitize","angular.messages","./pages/service","./pages/filter","./pages/directive","./pages/home/home","./pages/register/register","./pages/registerInfo/registerInfo","./pages/registed/registed","./pages/login/login","./pages/passwordForget/passwordForget","./pages/validaty/validaty","./pages/bindCard/bindCard","./pages/bindSuccess/bindSuccess","./pages/info/info","./pages/infoEdit/infoEdit","./pages/loan/loan","./pages/loanOk/loanOk","./pages/repayment/repayment","./pages/setbacks/setbacks","./pages/memberCenter/memberCenter","./pages/taobaoLogin/taobaoLogin","./pages/taobaoVerifyCode/taobaoVerifyCode","./pages/userLogin/userLogin","./pages/loanOrders/loanOrders"],function(e){var t=e("angular");e("ui.router"),e("angular.animate"),e("angular.sanitize"),e("angular.messages");var n=t.module("app",["ui.router","ngSanitize","ngAnimate","ngMessages"]);n.config(["$stateProvider","$urlRouterProvider","$httpProvider",function(n,r,i){i.interceptors.push("logoutInterceptor"),i.defaults.headers.post["Content-Type"]="application/x-www-form-urlencoded;charset=UTF-8",i.defaults.transformRequest=function(e){if(t.isObject(e)){var n=[];for(var r in e)e[r]&&n.push(r+"="+e[r]);return n.join("&")}},r.when("","/home"),r.otherwise("/home"),n.state("home",{url:"/home",templateUrl:e.toUrl("./pages/home/home.html"),controller:"homeControl"}).state("register",{url:"/register",templateUrl:e.toUrl("./pages/register/register.html"),controller:"registerControl"}).state("registerInfo",{url:"/registerInfo",templateUrl:e.toUrl("./pages/registerInfo/registerInfo.html"),controller:"registerInfoControl"}).state("registed",{url:"/registed",templateUrl:e.toUrl("./pages/registed/registed.html"),controller:"registedControl"}).state("certificate",{url:"/certificate",templateUrl:e.toUrl("./pages/article/certificate.html")}).state("manageService",{url:"/manageService",templateUrl:e.toUrl("./pages/article/manageService.html")}).state("login",{url:"/login",templateUrl:e.toUrl("./pages/login/login.html"),controller:"loginControl"}).state("validaty",{url:"/validaty",templateUrl:e.toUrl("./pages/validaty/validaty.html"),controller:"validatyControl"}).state("bindCard",{url:"/bindCard",templateUrl:e.toUrl("./pages/bindCard/bindCard.html"),controller:"bindCardControl"}).state("bindSuccess",{url:"/bindSuccess",templateUrl:e.toUrl("./pages/bindSuccess/bindSuccess.html"),controller:"bindSuccessControl"}).state("info",{url:"/info",templateUrl:e.toUrl("./pages/info/info.html"),controller:"infoControl"}).state("infoEdit",{url:"/infoEdit",templateUrl:e.toUrl("./pages/infoEdit/infoEdit.html"),controller:"infoEditControl"}).state("loan",{url:"/loan",templateUrl:e.toUrl("./pages/loan/loan.html"),controller:"loanControl"}).state("loanOk",{url:"/loanOk",templateUrl:e.toUrl("./pages/loanOk/loanOk.html"),controller:"loanOkControl"}).state("repayment",{url:"/repayment",templateUrl:e.toUrl("./pages/repayment/repayment.html"),controller:"repaymentControl"}).state("setbacks",{url:"/setbacks",templateUrl:e.toUrl("./pages/setbacks/setbacks.html"),controller:"setbacksControl"}).state("passwordForget",{url:"/passwordForget",templateUrl:e.toUrl("./pages/passwordForget/passwordForget.html"),controller:"passwordForgetControl"}).state("memberCenter",{url:"/memberCenter",templateUrl:e.toUrl("./pages/memberCenter/memberCenter.html"),controller:"memberCenterControl"}).state("taobaoLogin",{url:"/taobaoLogin",templateUrl:e.toUrl("./pages/taobaoLogin/taobaoLogin.html"),controller:"taobaoLoginControl"}).state("taobaoVerifyCode",{url:"/taobaoVerifyCode",templateUrl:e.toUrl("./pages/taobaoVerifyCode/taobaoVerifyCode.html"),controller:"taobaoVerifyCodeControl"}).state("userLogin",{url:"/userLogin",templateUrl:e.toUrl("./pages/userLogin/userLogin.html"),controller:"userLoginControl"}).state("loanOrders",{url:"/loanOrders",templateUrl:e.toUrl("./pages/loanOrders/loanOrders.html"),controller:"loanOrdersControl"})}]),e("./pages/service")(n),e("./pages/filter")(n),e("./pages/directive")(n),e("./pages/home/home")(n),e("./pages/register/register")(n),e("./pages/registerInfo/registerInfo")(n),e("./pages/registed/registed")(n),e("./pages/login/login")(n),e("./pages/passwordForget/passwordForget")(n),e("./pages/validaty/validaty")(n),e("./pages/bindCard/bindCard")(n),e("./pages/bindSuccess/bindSuccess")(n),e("./pages/info/info")(n),e("./pages/infoEdit/infoEdit")(n),e("./pages/loan/loan")(n),e("./pages/loanOk/loanOk")(n),e("./pages/repayment/repayment")(n),e("./pages/setbacks/setbacks")(n),e("./pages/memberCenter/memberCenter")(n),e("./pages/taobaoLogin/taobaoLogin")(n),e("./pages/taobaoVerifyCode/taobaoVerifyCode")(n),e("./pages/userLogin/userLogin")(n),e("./pages/loanOrders/loanOrders")(n),t.element(document).ready(function(){function o(){i||(e.style.display="none")}var e=document.getElementById("alert"),r=e.className,i=!1,s=null;window.alert=function(t,n){e.style.display="block",setTimeout(function(){e.className=r+" modal-show",i=!0},0),e.getElementsByTagName("p")[0].innerText=t,s=n},e.getElementsByTagName("button")[0].onclick=function(){i=!1,e.className=r,s&&s(),s=null},e.addEventListener("webkitTransitionEnd",o),e.addEventListener("transitionend",o);var u=document.body;window.prompt=function(e){function s(){u.removeChild(t),t=n=r=null}var t=document.createElement("div");u.appendChild(t),t.className="prompt-msg",t.innerHTML='<div class="prompt"><p class="text-overflow fl"></p></div>';var n=t.getElementsByTagName("p")[0],r=t.getElementsByTagName("div")[0];n.innerText=e;var i=n.offsetWidth;r.style.width=i+"px",r.style.marginLeft=i/-2+"px",t.addEventListener("webkitAnimationEnd",s),t.addEventListener("animationend",s)};var a=document.getElementById("page-wrap");n.run(["$rootScope","$location","$state",function(e,t,n){e.$on("$stateChangeStart",function(e,t,n,r,i){var s="slideInRight slideOutLeft",o="slideInLeft slideOutRight",u={home:{register:s,certificate:s,manageService:s},register:{home:o,registerInfo:s},registerInfo:{register:o,registed:s,login:s},login:{registerInfo:o,validaty:s,passwordForget:s},passwordForget:{login:o},validaty:{login:o},registed:{registerInfo:o},certificate:{home:o},manageService:{home:o},taobaoLogin:{taobaoVerifyCode:s},taobaoVerifyCode:{taobaoLogin:o},memberCenter:{userLogin:o,info:s,loanOrders:s},info:{memberCenter:o},loanOrders:{memberCenter:o}};if(r.name==="userLogin")f="zoomOut fadeInUp";else if(t.name==="userLogin")f="fadeOut zoomIn";else{var f=u[r.name]||"";f=f[t.name]||""}a.className="page-wrap animated ui-view-container "+f})}]),t.bootstrap(document,["app"])})});
