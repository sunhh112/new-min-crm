
<#include "headjs.ftl">
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>MinCRM免费的会员管理系统</title>
    <meta name=”description” content="母婴门店会员管理系统,免费会员管理系统,免费的母婴门店会员管理系统">
    <meta name=”keyword” content="母婴门店会员管理系统,免费会员管理系统,免费的母婴门店会员管理系统">
</head>
<script>
    var var_title = "密码修改";
    function findPasBut() {
            if(!$("#pasfm").form('enableValidation').form('validate')){
                return ;
            }
            var smsVerifyCode = $('#smsVerifyCode').textbox('getValue');
            if (smsVerifyCode == "") {
                alert_autoCloseError(var_title, "请输入短信验证码");
                return;
            }
            var obj = {}; //声明一个对象
            obj.tel= $('#account_tel_u').textbox('getValue');
            obj.smsVerifyCode= smsVerifyCode;//短信验证码
            $.ajax({
                type: "post",
                url: ctx+'/min/findPasBut',
                async: true, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
                contentType: 'application/json',
                dataType: 'JSON',
                data: JSON.stringify(obj),
                success: function(o) {
                    if (o.success == true) {
                        alert_noClose(var_title,o.data);
                    }else{
                        alert_autoCloseError(var_title,"修改失败!--"+o.message);
                    }
                }
            });
        }

</script>

<body style="margin:0;padding:2px">
<#include "headpage.ftl">

<div class="div_body" style="padding-top: 20px">
    <h2 >密码找回</h2>
    <div class="div_body"  style="text-align:center;position: relative;">
        <form id="pasfm" method="post" action="" style="margin-top: 0px; margin-left: 0px;">
            <table style=" "><tr><td width="18%"></td><td>
            <table style=" ">
                <#assign vtype = "2"><!-- //'1 用户注册，2 找回密码 ，3 添加员工', -->
                <#include "verification_code.ftl">
                <tr>
                    <td colspan="2" align="center" style="height: 60px">
                        <a href="#" onclick="findPasBut()" class="easyui-linkbutton" style="width: 100px">密码找回</a>
                        <a href="${ctx}/min/login"   style="font-size: 13px;padding-left: 30px" >进入登录页面</a>
                    </td>
                </tr>
            </table>
            </td><td width="18%"></td>
            </tr>
        </form>
    </div>

</div>
<#include "bottompage.ftl">
</body>
</html>


