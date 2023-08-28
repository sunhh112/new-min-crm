
<#include "../headjs.ftl">
<#include "../headInsidejs.ftl">
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>mincrm</title>
</head>
<style  type="text/css">
    .datagrid-header,
    .datagrid-td-rownumber {
        background-color: #f5f5f5;
        background: -webkit-linear-gradient(top,#f5f5f5 0,#f5f5f5 100%);
        background: -moz-linear-gradient(top,#f5f5f5 0,#f5f5f5 100%);
        background: -o-linear-gradient(top,#f5f5f5 0,#f5f5f5 100%);
        background: linear-gradient(to bottom,#f5f5f5 0,#f5f5f5 100%);
        background-repeat: repeat-x;
        filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#f5f5f5,endColorstr=#f5f5f5,GradientType=0);
    }

    .panel-header {
        background-color: #EFF5FF;
        background: -webkit-linear-gradient(top,#EFF5FF 0,#EFF5FF 100%);
        background: -moz-linear-gradient(top,#EFF5FF 0,#EFF5FF 100%);
        background: -o-linear-gradient(top,#EFF5FF 0,#EFF5FF 100%);
        background: linear-gradient(to bottom,#EFF5FF 0,#EFF5FF 100%);
        background-repeat: repeat-x;
        filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#EFF5FF,endColorstr=#EFF5FF,GradientType=0);
    }

    #westdiv .panel-body-noheader {
        border-top-width: 0px;
        border-right-width: 0px;
        border-left-width: 0px;
        border-bottom-width: 0px;
    }
    .cat1{
        font-family: STXinwei;
        color:#FF891F;
    }
</style>

<script type="text/javascript">

    $(function() {

        $('#menulist').datalist({
                onSelect: function () {
                    var value=$("#menulist").datalist("getSelected");
                    $("#index_centerid").panel("refresh", "${ctx}/crm/load?menupage="+value.value);
                    //1、获取某个区域面板，如center
                    var temp = $('#index_main').layout('panel','center');
                    //2、给区域面板title赋值
                    temp.panel('setTitle',value.text);
                }
            });
            $('#menulist').datalist('checkRow', 1);
    });

    function loginOut() {
            $.messager.confirm("注销", "您确定要注销吗？", function (rt) {
                if(rt) {
                    $.ajax({
                        type: "post",
                        url: '${ctx}/crm/loginOut',
                        async: false, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
                        contentType: 'application/json',
                        dataType: 'JSON',
                        //data: JSON.stringify(obj),
                        success: function(obj) {
                            if (obj.success == true) {
                                window.location="${ctx}/min/login";
                            }else{
                                alert_autoClose(var_title,"注销失败!--"+obj.message);
                            }
                        }
                    });
                }
            });
    }

    function openuserCenter(){
        //$("#role_type_u").combobox('readonly', false);
        $("#userfm_account_name").form('clear');
        $("#userfmpas").form('clear');
        $('#uid_f').val(${CrmAccountVo.uid});
        $("#account_name_f").textbox('setValue',"${CrmAccountVo.accountName}");
        $('#winuserList').dialog('open');
    }

    function fuserupdate() {
        if(!$("#userfm_account_name").form('enableValidation').form('validate')){
            return ;
        }
        var obj = {}; //声明一个对象
        obj.uid = $('#uid_f').val();
        obj.accountName = $('#account_name_f').textbox('getValue');
        $.ajax({
            type: "post",
            url: ctx+'/mb/shopuser/fuserupdate',
            async: true, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
            contentType: 'application/json',
            dataType: 'JSON',
            data: JSON.stringify(obj),
            success: function(o) {
                if (o.success == true) {
                    alertMesIndex("个人姓名","姓名修改成功");
                    sleep(2000);
                    location.reload(); // $("#accountNamehref").text($('#account_name_f').textbox('getValue'));
                }else{
                    alert_autoClose(var_title,"保存失败!--"+obj.message);
                }
            }
        });
    }

    function fuserPasUpdate() {
        if(!$("#userfmpas").form('enableValidation').form('validate')){
            return ;
        }
        var obj = {}; //声明一个对象
        var accountPas =$('#old_pas').passwordbox('getValue');
        var new_pas1 =$('#new_pas1').passwordbox('getValue');
        var new_pas2 =$('#new_pas2').passwordbox('getValue');
        if(new_pas1!=new_pas2){
            alert_autoClose("修改密码","新密码与确认密码不一致");
            return;
        }
        obj.uid = $('#uid_f').val();
        obj.accountPas = accountPas;
        obj.newPas1 =new_pas1;
        obj.newPas2 = new_pas2;

        $.ajax({
            type: "post",
            url: ctx+'/mb/shopuser/fuserPasUpdate',
            async: true, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
            contentType: 'application/json',
            dataType: 'JSON',
            data: JSON.stringify(obj),
            success: function(o) {
                if (o.success == true) {
                    alertMesIndex("密码","密码修改成功");
                    //sleep(2000);
                    //location.reload(); // $("#accountNamehref").text($('#account_name_f').textbox('getValue'));
                }else{
                    alert_autoCloseError("密码","密码修改失败,请确认老密码是否正确?");
                }
            }
        });
    }

    function alertMesIndex(title,mes) {
        alert_autoClose(title,mes);
        $('#winuserList').dialog('close');
    }


</script>
<body id="index_main" class="easyui-layout" style="">
<div data-options="region:'north',border:false" style="height:60px;padding:1px">
   <table width="100%" style="height:58px" bgcolor="#2c3b41" class="cat1">
        <tr ><td  width="60%" style="font-size: 20px" class="cat1">&nbsp;&nbsp;MinCRM<br><div class="cat1" style="font-size: 14">&nbsp;&nbsp;迷你会员管理系统</div></td>
            <td width="20%">
                <label>火龙xxx科技11:</label>

            </td>
            <td  width="20%" >
            <a href="#" class="easyui-linkbutton cat1" iconCls="icon-man" plain="true" onclick="openuserCenter()" id="accountNamehref">${CrmAccountVo.accountName}</a>
            <a href="#" class="easyui-linkbutton cat1" iconCls="icon-no" plain="true" onclick="loginOut()">注销</a>
        </td></tr>
    </table>

</div>
<div id="westdiv" data-options="region:'west',split:true,title:'功能'" style="width:170px;padding:0px;">

   <ul id="menulist" class="easyui-datalist" lines="" style="width:100%;height:450px;">
       <li value="crm/first">首页</li>
       <li value="crm/customer">客户管理</li>

   </ul>

</div>

<div data-options="region:'south',border:false" style="height:30px;padding:5px;">
    <table  width="100%" style="height:20px;font-size: 12px" >
        <tr >
            <td width="20%">
            </td>
            <td width="60%" align="center" >
                <label style="font-size: 12px">京ICP备15054550号 &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp; 联系邮箱:gsh_112@163.com </label>
            </td>
            <td width="20%">
            </td>
        </tr>
    </table>
</div>

<div data-options="region:'center',title:'Center123'" id="index_centerid">

</div>

<div id="winuserList" class="easyui-dialog" data-options="modal:true" title="个人中心"  style="width: 510px; padding: 10px 20px; height: 370px;"
     closed="true" buttons="#dlg-buttonsuser">
    <form id="userfm_account_name" method="post" action="" style="">
        <table>
                <tr style="margin-top: 20px; ">
                    <td class="fitem"><label>登录手机号:</label></td>
                    <td>
                        <input id="uid_f" type="hidden" value="${CrmAccountVo.uid}" />
                            ${CrmAccountVo.accountTel}
                    </td>
                </tr>

                <tr style="margin-top: 20px; ">
                    <td class="fitem"><label>姓名:</label></td>
                    <td>
                        <input id="account_name_f" data-options="required:true" style="width:160px;" class="easyui-textbox" />
                        <a id="shopuserSaveSubmit" href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-ok" onclick="fuserupdate()" >修改姓名</a>
                    </td>
                </tr>

        </table>
        </form>
    --------------------------------------------------------------------------------
        <form id="userfmpas" method="post" action="" style="">
            <table>
                <tr style="margin-top: 20px; ">
                    <td class="fitem"><label>老密码:</label></td>
                    <td>
                        <input id="old_pas" class="easyui-passwordbox" data-options="required:true" prompt="" iconWidth="28" style="width:170px;height:34px;padding:10px">
                    </td>
                </tr>
                <tr>
                    <td>新密码:</td>
                    <td>
                        <input id="new_pas1" class="easyui-passwordbox" data-options="required:true" prompt="" iconWidth="28" style="width:170px;height:34px;padding:10px">
                    </td>
                </tr>
                <tr>
                    <td>确认密码:</td>
                    <td>
                        <input id="new_pas2" class="easyui-passwordbox" data-options="required:true" prompt="" iconWidth="28" style="width:170px;height:34px;padding:10px">
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td align="right">
                        <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-ok" onclick="fuserPasUpdate()" >修改密码</a>
                    </td>
                </tr>
        </table>
    </form>
</div>
<!--直接写提交取消的事件不需要在js中绑定-->
<div id="dlg-buttonsuser" style="display: block">

    <a id="shopuserSaveCancel"  href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#winuserList').dialog('close')" style="width: 90px">取消</a>
</div>



</body>

</html>