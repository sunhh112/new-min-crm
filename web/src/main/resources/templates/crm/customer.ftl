<style type="text/css">

</style>


<script type="text/javascript">
    var var_title = "客户信息";
    var customerTypes = [];
    function initp() {
        $('#customerList').datagrid({
            rownumbers: true,
            singleSelect: true,
            autoRowHeight: true,
            pagination: true,
            pageSize: 20,
            striped: true, //行条纹化，即奇偶行使用不同背景色
            toolbar: '#tb',
            loader: myloader,
            loadMsg: '查询中，请稍后...',
            columns: [[
                {field: 'id', title: 'ID', width: 40},
                {field: 'tenantId', title: '', width: 20, hidden: true},
                {field: 'customerName', title: '客户名称', width: 200},
                {field: 'customerTel', title: '手机号', width: 120},
                {field: 'expirationTime', title: '过期时间', width: 150, formatter: formatDateYYYYMMDDhh},
                {field: 'customerType', title: '会员分类', width: 90,formatter: formatCustomerType},
                {field: 'ownerUid', title: '管理员', width: 90},
                {field: 'createUid', title: '创建人', width: 90, formatter: getAccountName},
                {field: 'remark', title: '备注', width: 150, formatter: formatDesc},
                {field: '操作', title: '操作', width: 200, formatter: putAction}

            ]],
            onLoadSuccess: function (data) {
                $('.actionclass').linkbutton();
            }
        });
        customerTypeList();

        $('#customer_type').combobox({
            valueField: 'val',
            textField: 'text',
            data: customerTypes
        });
    }

    function formatCustomerType(v,row,index) {
        for (var i = 0; i < customerTypes.length; ++i) { // do something with `substr[i]` }
            if (v==customerTypes[i].val)
                return customerTypes[i].text;
        }
        return v;
    }

    function customerTypeList() {
        <#if CustomerTypes?exists>
            <#list CustomerTypes as customerType>
                customerTypes.push({"val": '${customerType.val}', "text": '${customerType.text}'});
            </#list>
        </#if>
        return customerTypes;
    }

    var myloader = function (param, success, error) {
        var obj = {}; //声明一个对象
        obj.page = param.page;
        obj.rows = param.rows;
        $.ajax({
            type: "post",
            url: ctx + '/crm/customer/list',
            async: true, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
            contentType: 'application/json',
            dataType: 'JSON',
            data: JSON.stringify(obj),
            success: function (obj) {
                //console.log(obj.data)
                if (obj.success == true) {
                    success(obj.data);
                }
                else {
                    alert_autoClose(var_title, "查询异常，请联系管理员:" + obj.message);
                    success(obj.data);
                }
            }
        });
    }

    function putAction(v, row, index) {
        var r = "";
        if (row.roleType == 1) {
            r = '<a href="#" onclick="shopEdit(\'' + index + '\')" class="actionclass" iconCls="icon-edit" plain="true">编辑</a>';
            r = r + '<a href="#" onclick="openshopuser(\'' + index + '\')" class="actionclass" iconCls="icon-redo" plain="true">发布管理</a>';
        }
        return r;
    }

    function getRow(index) {
        $('#customerList').datagrid('selectRow', index);
        var rows = $('#customerList').datagrid('getSelections');
        return rows[0];
    }

    function openshopuser(index) {
        var row = getRow(index);
        // $('#shop_id').val();orderScore
        $('#winshopuser').load(ctx + "/mb/load?menupage=mb/shopuser&shopId=" + row.shopId);
        $('#winshopuser').dialog({title: "(" + row.shopName + ")员工信息"});
        $('#winshopuser').dialog('open');
    }

    function shopEdit(index) {
        var row = getRow(index);
        $('#shop_id').val(row.shopId);
        $('#shop_name').textbox('setValue', row.shopName);
        $('#shop_address').textbox('setValue', row.shopAddress);
        $('#str_desc').textbox('setValue', row.strDesc);
        $('#winshop').dialog('open');
    }
    function shopSave() {
        if (!$("#shopfm").form('enableValidation').form('validate')) {
            return;
        }
        var obj = {}; //声明一个对象
        obj.shopId = $('#shop_id').val();
        obj.shopName = $('#shop_name').textbox('getValue');
        obj.shopAddress = $('#shop_address').textbox('getValue');
        obj.strDesc = $('#str_desc').textbox('getValue');
        //
        $.ajax({
            type: "post",
            url: ctx + '/mb/shop/shopSave',
            async: true,
            contentType: 'application/json',
            dataType: 'JSON',
            data: JSON.stringify(obj),
            success: function (o) {
                //console.log(obj.data)
                if (o.success == true) {
                    alertMes(var_title, "保存成功");
                } else {
                    alert_autoClose(var_title, "保存失败!--" + obj.message);
                }
            }
        });
    }

    function shopquery() {
        // var shopName =$('#shopName').textbox('getValue');
        $('#customerList').datagrid("reload", {});
    }
    function newAdd() {
        $("#shopfm").form('clear');
        $('#winshop').dialog('open');
    }

    function alertMes(title, mes) {
        // shopsize();
        alert_autoClose(title, mes);
        $('#winshop').dialog('close');
        $("#shopfm").form('clear');
        shopquery();
    }
    initp();


</script>

<div style="padding: 2px">
    <div style="margin:1px 1px;"></div>
    <div id="tb" style="padding:1px;height:auto">
        <div style="margin:10px" style="width:90%;">

            <input class="easyui-textbox" id="shopName" style="width:170;padding:10px;" data-options="prompt:'请输入商品名称'">
            <input id="customer_type" name="customer_type" value="" style="width:120px;">

            <a href="#" style="margin-right:20px;margin-left: 15px" class="easyui-linkbutton" iconCls="icon-search"
               onclick="shopquery()">查询</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newAdd()">新增门店</a>

        </div>

    </div>
    <table id="customerList" title="" style="width:90%;height:600px"></table>
</div>

<div id="winshop" class="easyui-dialog" data-options="modal:true" title="门店信息"
     style="width: 510px; padding: 10px 20px; height: 370px;"
     closed="true" buttons="#dlg-buttonsshop">

    <form id="shopfm" method="post" action="" style="margin-top: 0px; margin-left: 0px;">
        <table>
            <tr style="margin-top: 20px; ">
                <td class="fitem"><label>门店名称:</label></td>
                <td>
                    <input id="shop_id" type="hidden"/>
                    <input id="shop_name" data-options="required:true" style="width:160px;" class="easyui-textbox"/>
                </td>
            </tr>
            <tr style="margin-top: 20px; ">
                <td class="fitem"><label>门店地址:</label></td>
                <td>
                    <input class="easyui-textbox" id="shop_address" value="" style="width:160px;">
                </td>
            </tr>

            <tr style="margin-top: 20px; ">
                <td class="fitem"><label>备注:</label></td>
                <td>
                    <input id="str_desc" class="easyui-textbox" data-options="multiline:true" value=""
                           style="width:220px;height:80px">
                </td>
            </tr>

        </table>
    </form>
</div>
<!--直接写提交取消的事件不需要在js中绑定-->
<div id="dlg-buttonsshop" style="display: block">
    <a id="confirm2" href="javascript:void(0)" class="easyui-linkbutton c6" iconcls="icon-ok" onclick="shopSave()"
       style="width: 90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel"
       onclick="javascript:$('#winshop').dialog('close')" style="width: 90px">取消</a>
</div>



