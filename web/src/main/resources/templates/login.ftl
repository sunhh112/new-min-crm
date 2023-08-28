<#include "headjs.ftl">

<head>

    <title>MinCRM免费的会员管理系统</title>
    <meta name=”description” content="母婴门店会员管理系统,免费会员管理系统,免费的母婴门店会员管理系统">
    <meta name=”keyword” content="母婴门店会员管理系统,免费会员管理系统,免费的母婴门店会员管理系统">
</head>
<script type="text/javascript">
    $(function() {
        $('#ck').checkbox({
            label: '<label style="font-size: 13px">记住密码:</label>',
            value: '1',
            labelPosition:'after',
            height:'18px',
            checked: true
        });
        $('#tel').textbox('setValue',$.cookie("minuser_tel"));
        $('#pas').val($.cookie("minuser_pas"));
        $('#pas').passwordbox({
            showEye: true
        });

    });
    function loginBut(){
        if(!$("#loginff").form('enableValidation').form('validate')){
            return ;
        }
        //  var tel =  $('#tel').getText();
        var tel =$('#tel').textbox('getValue');
        var pas =$('#pas').passwordbox('getValue');
        var ck = $('#ck').checkbox('options').checked;
        if(ck) {
            $.cookie("minuser_tel", tel, {expires: 7});
            $.cookie("minuser_pas", pas, {expires: 7});
        }
        // Ajax提交数据
        $.ajax({
            url: "${ctx}/min/loginOK",    // 提交到controller的url路径
            dateType:'json',
            contentType :'application/json',
            type: "post",    // 提交方式
            data:  JSON.stringify({"accountTel": tel, "accountPas": pas}),  // data为String类型，必须为 Key/Value 格式。
            success: function (data) {
                if (data.success == true) {
                    //跳转到系统首页
                    window.location="${ctx}/crm/index"
                } else {
                    alert_autoCloseError("登录",data.message);
                    //alert(data.code+","+data.message)
                }
            },
        });
    }

</script>

<html>
<body style="margin:0;padding:2px">
<#include "headpage.ftl">
<div class="div_body" style="padding-top: 20px">
    <h2 >登录</h2>
    <table>
        <tr><td width="400px"></td><td>
        <form id="loginff" method="post">
            <table cellpadding="5" style="">
                <#if RegisterMinAccountVo?? && RegisterMinAccountVo.accountTel !=''>
                    <tr >
                        <td colspan="2" align="center" ><font color="#a52a2a">注册成功请登录</font></td>
                    </tr>
                </#if>
                <tr >
                    <td>手机号:</td>
                    <td>
                        <input class="easyui-textbox" data-options="required:true,validType:'telNum'" id="tel" iconWidth="28" style="width:100%;height:34px;padding:10px;">
                    </td>
                </tr>
                <tr>
                    <td>密码:</td>
                    <td>
                        <input id="pas" data-options="required:true,prompt:''" type="text" iconWidth="28" style="width:100%;height:34px;padding:10px">
                    </td>
                </tr>
                    <tr >
                        <td colspan="2" >
                            <table style="width: 100%"><tr style="width: 100%"><td><input id="ck" /></td>
                                <td style="text-align: right">
                                      <a onclick="" href="${ctx}/min/findpas"   style="font-size: 13px" >忘记密码</a>
                                </td></tr></table>
                        </td>
                    </tr>
                <tr>
                    <td colspan="2" align="center">
                        <a onclick="loginBut()" href="#" class="easyui-linkbutton" style="width:40%;" >登录</a>
                        <a href="${ctx}/min/register" class="easyui-linkbutton" style="width:40%">注册</a>
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
<script type="text/javascript">

    if($("#menulist").length > 0) {
        window.location="${ctx}/min/login";
        window.close();
    }


</script>
