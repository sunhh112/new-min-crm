<#include "headjs.ftl">

<!DOCTYPE html>

<html>


<head>
    <title>MinCRM完费的会员管理系统</title>
    <meta name=”description” content="母婴门店会员管理系统,免费会员管理系统,免费的母婴门店会员管理系统">
    <meta name=”keyword” content="母婴门店会员管理系统,免费会员管理系统,免费的母婴门店会员管理系统">

</head>


<style>
    body,div{ font-size:14px;}

</style>
<script type="text/javascript">
    $(function() {

    });
    function registerBut(){
        if(!$("#registerfm").form('enableValidation').form('validate')){
            return ;
        }
        var tel =$('#account_tel_u').textbox('getValue');
        var pas =$('#pas').passwordbox('getValue');
        var pass = $('#pass').passwordbox('getValue');
        var smsVerifyCode = $('#smsVerifyCode').textbox('getValue');
        if (smsVerifyCode == "") {
            alert_autoCloseError(var_title, "请输入短信验证码");
            return;
        }
        if(pas != pass){
            alert_autoCloseError("注册页面","密码与确认密码不一致");
            return;
        }
        // Ajax提交数据
        $.ajax({
            url: "${ctx}/min/registerOK",    // 提交到controller的url路径
            dateType:'json',
            contentType :'application/json',
            type: "post",    // 提交方式
            data:  JSON.stringify({"accountTel": tel, "accountPas": pas,"smsVerifyCode": smsVerifyCode}),  // data为String类型，必须为 Key/Value 格式。
            success: function (data) {    // 请求成功后的回调函数，其中的参数data为controller返回的map,也就是说,@ResponseBody将返回的map转化为JSON格式的数据，然后通过data这个参数取JSON数据中的值
                if (data.success == true) {
                    //跳转到系统首页
                    window.location="${ctx}/min/login"
                } else {
                    alert_autoCloseError("注册页面",data.message)
                }
            },
        });
    }
</script>

<body style="margin:0;padding:2px">
<#include "headpage.ftl">
<div class="div_body" style="padding-top: 20px">
    <h2 >注册</h2>
    <table>
        <tr><td width="300px"></td><td>

                <form id="registerfm" method="post">
                    <table cellpadding="5" style="width: 100%" style="height: 100%">

                        <#assign vtype = "1"><!-- //'1 用户注册，2 找回密码 ，3 添加员工', -->
                        <#include "verification_code.ftl">
                        <tr>
                            <td>密码:</td>
                            <td>
                                <input id="pas" class="easyui-passwordbox" data-options="required:true,prompt:'密码'" prompt="" iconWidth="28" style="width:150px;height:34px;padding:10px">
                            </td>
                        </tr>
                        <tr>
                            <td nowrap>确认密码:</td>
                            <td>
                                <input id="pass" class="easyui-passwordbox" data-options="required:true,prompt:'确认密码'" prompt="" iconWidth="28" style="width:150px;height:34px;padding:10px">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center">

                                <a href="#" onclick="registerBut()" class="easyui-linkbutton" style="width:40%">注册</a>
                                <a href="${ctx}/min/login"   style="font-size: 13px" >进入登录页面</a>
                            </td>
                        </tr>
                     </table>
            </form>

        </td><td width="200px"></td>
        </tr>    </table>
</div>
<#include "bottompage.ftl">
</body>
</html>