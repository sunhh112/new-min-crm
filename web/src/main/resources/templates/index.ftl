
<#include "headjs.ftl">
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>MinCRM管理系统</title>
    <meta name=”description” content="抖音，快手，小红书多平台自动分发功能">
    <meta name=”keyword” content="抖音，快手，小红书多平台自动分发功能">
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
    <table style="width: 100%">
        <tr><td><p>MinCRM管理平台</p> </td>
        <td style="width: 100px" align="right">
            <a onclick="" href="${ctx}/min/login"   style="font-size: 13px" >登录</a>/
            <a onclick="" href="${ctx}/min/register"   style="font-size: 13px" >注册</a>
        </td>
    </tr></table>
    <div>
        <ul>
            <li>欢迎使用MinCRM。</li>
            <li>抖音，快手，小红书多平台自动分发功能。</li>
        </ul>
    </div>
    <div style="width: 100%">
        <h3>平台简介：</h3>
        <table style="width: 100%">
            <tr style="width: 100%">
                <td style="text-align: center;width: 100%"><img style="vertical-align:middle;"  src="${ctx}/img/index.png" height="520px" ></td>

            </tr>

        </table>
    </div>

</div>


<#include "bottompage.ftl">
</body>
</html>


