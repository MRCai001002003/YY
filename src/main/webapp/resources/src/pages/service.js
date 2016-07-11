define(function (require) {
    return function (app) {
        app.service('cache', function () {
            var obj = {};
            this.set = function (key, value) {
                obj[key] = value;
            };
            this.get = function (key) {
                return obj[key];
            }
        })
        app.factory('validatePattern', function () {
            return {
                bankCard: function luhmCheck(bankno) {
                    if (bankno.length < 14 || bankno.length > 19) {
                        //$("#banknoInfo").html("银行卡号长度必须在16到19之间");
                        return false;
                    }
                    var num = /^\d*$/; //全数字
                    if (!num.exec(bankno)) {
                        //$("#banknoInfo").html("银行卡号必须全为数字");
                        return false;
                    }
                    //开头6位
                    var strBin = "10,18,30,35,36,37,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,58,60,62,65,68,69,84,87,88,94,95,98,99";
                    if (strBin.indexOf(bankno.substring(0, 2)) == -1) {
                        //$("#banknoInfo").html("银行卡号开头6位不符合规范");
                        return false;
                    }
                    var lastNum = bankno.substr(bankno.length - 1, 1); //取出最后一位（与luhm进行比较）

                    var first15Num = bankno.substr(0, bankno.length - 1); //前15或18位
                    var newArr = new Array();
                    for (var i = first15Num.length - 1; i > -1; i--) { //前15或18位倒序存进数组
                        newArr.push(first15Num.substr(i, 1));
                    }
                    var arrJiShu = []; //奇数位*2的积 <9
                    var arrJiShu2 = []; //奇数位*2的积 >9

                    var arrOuShu = new Array(); //偶数位数组
                    for (var j = 0; j < newArr.length; j++) {
                        if ((j + 1) % 2 == 1) { //奇数位
                            if (parseInt(newArr[j]) * 2 < 9)
                                arrJiShu.push(parseInt(newArr[j]) * 2);
                            else
                                arrJiShu2.push(parseInt(newArr[j]) * 2);
                        } else //偶数位
                            arrOuShu.push(newArr[j]);
                    }

                    var jishu_child1 = []; //奇数位*2 >9 的分割之后的数组个位数
                    var jishu_child2 = []; //奇数位*2 >9 的分割之后的数组十位数
                    for (var h = 0; h < arrJiShu2.length; h++) {
                        jishu_child1.push(parseInt(arrJiShu2[h]) % 10);
                        jishu_child2.push(parseInt(arrJiShu2[h]) / 10);
                    }

                    var sumJiShu = 0; //奇数位*2 < 9 的数组之和
                    var sumOuShu = 0; //偶数位数组之和
                    var sumJiShuChild1 = 0; //奇数位*2 >9 的分割之后的数组个位数之和
                    var sumJiShuChild2 = 0; //奇数位*2 >9 的分割之后的数组十位数之和
                    var sumTotal = 0;
                    for (var m = 0; m < arrJiShu.length; m++) {
                        sumJiShu = sumJiShu + parseInt(arrJiShu[m]);
                    }

                    for (var n = 0; n < arrOuShu.length; n++) {
                        sumOuShu = sumOuShu + parseInt(arrOuShu[n]);
                    }

                    for (var p = 0; p < jishu_child1.length; p++) {
                        sumJiShuChild1 = sumJiShuChild1 + parseInt(jishu_child1[p]);
                        sumJiShuChild2 = sumJiShuChild2 + parseInt(jishu_child2[p]);
                    }
                    //计算总和
                    sumTotal = parseInt(sumJiShu) + parseInt(sumOuShu) + parseInt(sumJiShuChild1) + parseInt(sumJiShuChild2);

                    //计算Luhm值
                    var k = parseInt(sumTotal) % 10 == 0 ? 10 : parseInt(sumTotal) % 10;
                    var luhm = 10 - k;

                    if (lastNum == luhm) {
                        return true;
                    } else {
                        return false;
                    }
                },
                idcard: function (value) {
                    var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1]; // 加权因子
                    var ValideCode = [1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2]; // 身份证验证位值.10代表X
                    return IdCardValidate(value);

                    function IdCardValidate(idCard) {
                        idCard = trim(idCard.replace(/ /g, "")); //去掉字符串头尾空格
                        if (idCard.length == 15) {
                            return isValidityBrithBy15IdCard(idCard); //进行15位身份证的验证
                        } else if (idCard.length == 18) {
                            var a_idCard = idCard.split(""); // 得到身份证数组
                            if (isValidityBrithBy18IdCard(idCard) && isTrueValidateCodeBy18IdCard(a_idCard)) { //进行18位身份证的基本验证和第18位的验证
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                    /**
                     * 判断身份证号码为18位时最后的验证位是否正确
                     * @param a_idCard 身份证号码数组
                     * @return
                     */
                    function isTrueValidateCodeBy18IdCard(a_idCard) {
                        var sum = 0; // 声明加权求和变量
                        if (a_idCard[17].toLowerCase() == 'x') {
                            a_idCard[17] = 10; // 将最后位为x的验证码替换为10方便后续操作
                        }
                        for (var i = 0; i < 17; i++) {
                            sum += Wi[i] * a_idCard[i]; // 加权求和
                        }
                        valCodePosition = sum % 11; // 得到验证码所位置
                        if (a_idCard[17] == ValideCode[valCodePosition]) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                    /**
                     * 验证18位数身份证号码中的生日是否是有效生日
                     * @param idCard 18位书身份证字符串
                     * @return
                     */
                    function isValidityBrithBy18IdCard(idCard18) {
                        var year = idCard18.substring(6, 10);
                        var month = idCard18.substring(10, 12);
                        var day = idCard18.substring(12, 14);
                        var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
                        // 这里用getFullYear()获取年份，避免千年虫问题
                        if (temp_date.getFullYear() != parseFloat(year) || temp_date.getMonth() != parseFloat(month) - 1 || temp_date.getDate() != parseFloat(day)) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    /**
                     * 验证15位数身份证号码中的生日是否是有效生日
                     * @param idCard15 15位书身份证字符串
                     * @return
                     */
                    function isValidityBrithBy15IdCard(idCard15) {
                        var year = idCard15.substring(6, 8);
                        var month = idCard15.substring(8, 10);
                        var day = idCard15.substring(10, 12);
                        var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
                        // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法
                        if (temp_date.getYear() != parseFloat(year) || temp_date.getMonth() != parseFloat(month) - 1 || temp_date.getDate() != parseFloat(day)) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    //去掉字符串头尾空格
                    function trim(str) {
                        return str.replace(/(^\s*)|(\s*$)/g, "");
                    }
                }
            }
        })
    }
})
