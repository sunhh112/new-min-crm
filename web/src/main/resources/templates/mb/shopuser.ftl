
<style  type="text/css">
</style>
<script type="text/javascript">
    var var_title ="员工信息";
    $('#addshopuser').linkbutton({});
    $('#winshopuserList').dialog({});
    $('#account_name_u').textbox({});
    $('#role_type_u').combobox({});
    $('#shopuserSaveCancel1').linkbutton({});
    $('#shopuserSaveSubmit1').linkbutton({});

    //#dlg-buttonsshopuser
    function initp2() {
        $('#category_id').combobox({
            valueField:'categoryId',
            textField:'categoryName',
            required:true,
            data:categoryDatas
        });

        $('#shopuserlist').datagrid({
            rownumbers:true,
            singleSelect:true,
            autoRowHeight:true,
            pagination:true,
            pageSize:50,
            striped: true, //行条纹化，即奇偶行使用不同背景色
            toolbar:'#tb2',
            loader: myloader2,
            loadMsg:'查询中，请稍后...',
            columns:[[
                {field:'accountName',title:'员工姓名',width:120},
                {field:'accountTel',title:'登录手机号',width:120},
                {field:'roleType',title:'权限',width:120,formatter:roleTypeName},
                {field:'createDate',title:'创建时间',width:150,formatter: formatDateYYYYMMDDhh},
                {field:'createUid',title:'创建人',width:100,formatter:getAccountName},
                {field:'操作',title:'操作',width:200,formatter:putAction3}

            ]],
            onLoadSuccess: function (data) {
                $('.actionclass2').linkbutton();
            }
        });
    }

    var myloader2 = function(param, success, error) {
        var obj = {}; //声明一个对象
        obj.page = param.page;
        obj.rows = param.rows;
        var shopId= $('#shop_id_u').val();
        $.ajax({
            type: "post",
            url: ctx+'/mb/shopuser/shopuserlist?shopId='+shopId,
            async: true, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
            contentType: 'application/json',
            dataType: 'JSON',
            data: JSON.stringify(obj),
            success: function(obj) {
                //console.log(obj.data)
                if (obj.success == true) {
                    if (obj.data.total > 0)
                        $('#querySpan2').html("");
                    else
                        $('#querySpan2').html("查询为空值");
                    success(obj.data);
                }
                else {
                    $('#querySpan2').html("查询异常，请联系管理员");
                    success(obj.data);
                }
            }
        });
    }

    function putAction3(v,row,index) {
        var r = "";
        r =  '<a href="#" onclick="shopuserEdit2(\'' + index + '\')" class="actionclass2" iconCls="icon-edit" plain="true">编辑</a>';
        if ( ${MinAccountVo.uid}!=row.uid ){
            r = r + '<a href="#" onclick="shopuserRemove2(\'' + index + '\')" class="actionclass2" iconCls="icon-remove" plain="true">移除</a>';
        }
        return r;
    }

    function getRow2(index){
        $('#shopuserlist').datagrid('selectRow',index);
        var rows = $('#shopuserlist').datagrid('getSelections');
        return rows[0];
    }

    function shopuserRemove2(index){
        $.messager.confirm("删除提示", "您确定要删除操作吗？", function (rt) {
            if(rt) {
                var row = getRow2(index);
                var obj = {}; //声明一个对象
                obj.roleId = row.roleId;
                $.ajax({
                    type: "post",
                    url: ctx+'/mb/shopuser/shopuserRemove',
                    async: true, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
                    contentType: 'application/json',
                    dataType: 'JSON',
                    data: JSON.stringify(obj),
                    success: function(obj) {
                        if (obj.success == true) {
                            alertMes2(var_title,"删除成功");
                        }else{
                            alert_autoClose(var_title,"删除失败!--"+obj.message);
                        }
                    }
                });
            }
        });
    }

    function shopuserEdit2(index) {
        var row = getRow2(index);
        $('#role_id_u').val(row.roleId);
        $('#uid_u').val(row.uid);
        $('#account_tel_u').textbox('setValue',row.accountTel);
        $('#role_type_u').combobox('setValue',row.roleType);
        $('#account_name_u').textbox('setValue',row.accountName);
        $("#verificationtd1").hide();
        $("#verificationtd2").hide();
        $("#account_tel_u").textbox({'disabled':true});
        if ( ${MinAccountVo.uid}==row.uid ){
            $("#role_type_u").combobox('readonly', true);
        }else{
            $("#role_type_u").combobox('readonly', false);
        }
        $('#winshopuserList').dialog('open');

    }
    //role_id uid account_tel account_tel_u  account_name_u
    function shopuserSave() {
        var uid = $('#uid_u').val() ;
        if(!$("#shopuserfm").form('enableValidation').form('validate')){
            return ;
        }
        if(uid==""){ //代表是新增
            var smsVerifyCode = $('#smsVerifyCode').textbox('getValue');
            if (smsVerifyCode == "") {
                alert_autoCloseError(var_title, "请输入短信验证码");
                return;
            }
        }

        var obj = {}; //声明一个对象
        obj.roleId = $('#role_id_u').val();
        obj.uid = uid;
        obj.shopId = $('#shop_id_u').val();
        obj.accountTel= $('#account_tel_u').textbox('getValue');
        obj.accountName= $('#account_name_u').textbox('getValue');
        obj.roleType= $('#role_type_u').textbox('getValue');
        obj.smsVerifyCode= smsVerifyCode;//短信验证码
       // obj.verifyCode =$('#verifyCode').textbox('getValue'); //动态验证吗
        $.ajax({
            type: "post",
            url: ctx+'/mb/shopuser/shopuserSave',
            async: true, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
            contentType: 'application/json',
            dataType: 'JSON',
            data: JSON.stringify(obj),
            success: function(o) {
                if (o.success == true) {
                   // alert(o.data+"mmm");
                    if(o.data!=""&&o.data!="null"&&o.data!=null) {
                        alertMes2(var_title, o.data);
                    }else{
                        alertMes2(var_title, "保存成功");
                    }
                }else{
                    alert_autoCloseError(var_title,"保存失败!--"+o.message);
                }
            }
        });
    }

    function  shopuserquery() {
        $('#shopuserlist').datagrid("reload",{});
    }
    function newAdd2(){
        $("#account_tel_u").textbox({'disabled':false});
        $("#role_type_u").combobox('readonly', false);
        $("#shopuserfm").form('clear');
        $("#verificationtd1").show();
        $("#verificationtd2").show();
        $('#verificationid').html('<a href="javascript:void(0)" iconcls="icon-ok" onclick="sendSms()" style="width: 120px">发送验证码</a>');
        $('#winshopuserList').dialog('open');
    }
    function alertMes2(title,mes) {
        alert_noClose(title,mes);
        $('#winshopuserList').dialog('close');
        $("#shopuserfm").form('clear');
        shopuserquery();
    }

    initp2();
</script>




<div style="padding: 2px">
    <div style="margin:1px 1px;"></div>
    <div id="tb2" style="padding:1px;height:auto" >
        <div style="margin-bottom:5px" style="width:90%;" >
            <input id="shop_id_u" type="hidden" value="${shopId1}"/>
                 <a href="#" id="addshopuser" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newAdd2()">新增员工</a>
        </div>
    </div>
    <table id="shopuserlist"  title="" style="width:100%;height:540px"></table>
</div>

<div id="winshopuserList" class="easyui-dialog" data-options="modal:true" title="员工信息"  style="width: 610px; padding: 10px 20px; height: 370px;"
     closed="true" buttons="#dlg-buttonsshopuser1">
    <form id="shopuserfm" method="post" action="" style="margin-top: 0px; margin-left: 0px;">
        <table>
            <tr style="margin-top: 20px; ">
                <td class="fitem"><label>员工姓名:</label></td>
                <td>
                    <input id="role_id_u" type="hidden" />
                    <input id="uid_u" type="hidden" />
                    <input id="account_name_u" data-options="required:true"  style="width:200px;" class="easyui-textbox"  />
                </td>
            </tr>
        <#assign vtype = "3"><!-- //'1 用户注册，2 找回密码 ，3 添加员工', -->
        <#include "../verification_code.ftl">
            <!--
            <tr id="verificationtd" style="margin-top: 20px;">
                <td class="fitem"><label>验证码:</label></td>
                <td>
                    <input id="verificationCode" data-options="" style="width:100px;" class="easyui-textbox"  />
                </td>
            </tr>
-->
            <tr style="margin-top: 20px; ">
                <td class="fitem"><label>权限:</label></td>
                <td>
                    <select id="role_type_u" class="easyui-combobox" data-options="required:true"  name="role_type_u" style="width:200px;">
                        <option value="0">普通员工</option>
                        <option value="1">店铺管理员</option>
                    </select>
                </td>
            </tr>

        </table>
    </form>
</div>
<!--直接写提交取消的事件不需要在js中绑定-->
<div id="dlg-buttonsshopuser1" style="display: block">
    <a id="shopuserSaveSubmit1" href="javascript:void(0)" class="easyui-linkbutton c6" iconcls="icon-ok" onclick="shopuserSave()" style="width: 90px">保存</a>
    <a id="shopuserSaveCancel1"  href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#winshopuserList').dialog('close')" style="width: 90px">取消</a>
</div>
