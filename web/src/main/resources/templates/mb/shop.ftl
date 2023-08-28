
<style  type="text/css">

</style>


<script type="text/javascript">
    var var_title ="商品信息";

    function initp() {
        $('#shoplist').datagrid({
            rownumbers:true,
            singleSelect:true,
            autoRowHeight:true,
            pagination:true,
            pageSize:20,
            striped: true, //行条纹化，即奇偶行使用不同背景色
            toolbar:'#tb',
            loader: myloader,
            loadMsg:'查询中，请稍后...',
            columns:[[
                {field:'shopId',title:'',width:120,hidden:true},
                {field:'shopName',title:'门店名称',width:120},
                {field:'shopAddress',title:'地址',width:370},
                {field:'roleType',title:'当前账号权限',width:100,formatter:roleTypeName},
                {field:'createDate',title:'创建时间',width:150,formatter: formatDateYYYYMMDDhh},
                {field:'createUid',title:'创建人',width:100,formatter:getAccountName},

                {field:'strDesc',title:'备注',width:100,formatter:formatDesc},
                {field:'操作',title:'操作',width:240,formatter:putAction}

            ]],
            onLoadSuccess: function (data) {
                $('.actionclass').linkbutton();
            }
        });
    }

    var myloader = function(param, success, error) {
        var obj = {}; //声明一个对象
        // obj.id = 10001; //后端要求每次请求传入一个ID
        obj.page = param.page;
        obj.rows = param.rows;
       // obj.shopName= param.shopName;
        $.ajax({
            type: "post",
            url: ctx+'/mb/shop/shoplist',
            async: true, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
            contentType: 'application/json',
            dataType: 'JSON',
            data: JSON.stringify(obj),
            success: function(obj) {
                //console.log(obj.data)
                if (obj.success == true) {
                    if (obj.data.total > 0) {
                        $('#querySpan').html("");
                        si = obj.data.total;
                    }
                    else
                        $('#querySpan').html("查询为空值");
                    success(obj.data);
                }
                else {
                    $('#querySpan').html("查询异常，请联系管理员");
                    success(obj.data);
                }
            }
        });
    }

    function putAction(v,row,index) {
        var r = "";
        if (row.roleType== 1) {
            r = '<a href="#" onclick="shopEdit(\'' + index + '\')" class="actionclass" iconCls="icon-edit" plain="true">编辑</a>';
            if (si > 1) {
                r = r + '<a href="#" onclick="shopRemove(\'' + index + '\')" class="actionclass" iconCls="icon-remove" plain="true">移除</a>';
            }
            r = r + '<a href="#" onclick="openshopuser(\'' + index + '\')" class="actionclass" iconCls="icon-redo" plain="true">员工管理</a>';
        }
        return r ;
    }

    function getRow(index){
        $('#shoplist').datagrid('selectRow',index);
        var rows = $('#shoplist').datagrid('getSelections');
        return rows[0];
    }

    function shopRemove(index){
        $.messager.confirm("删除提示", "您确定要删除操作吗？", function (rt) {
            if(rt) {
                var row = getRow(index);
                var obj = {}; //声明一个对象
                obj.shopId = row.shopId;
                $.ajax({
                    type: "post",
                    url: ctx+'/mb/shop/shopRemove',
                    async: true, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
                    contentType: 'application/json',
                    dataType: 'JSON',
                    data: JSON.stringify(obj),
                    success: function(obj) {
                        if (obj.success == true) {
                            alertMes(var_title,"删除成功");
                            getShopSessionList();
                        }else{
                            alert_autoClose(var_title,"删除失败!--"+obj.message);
                        }
                    }
                });
            }
        });
    }
    function openshopuser(index){
        var row = getRow(index);
       // $('#shop_id').val();orderScore
        $('#winshopuser').load(ctx+"/mb/load?menupage=mb/shopuser&shopId="+row.shopId);
        $('#winshopuser').dialog({title: "("+row.shopName+")员工信息"});
        $('#winshopuser').dialog('open');
    }

    function shopEdit(index) {
        var row = getRow(index);
        $('#shop_id').val(row.shopId);
        $('#shop_name').textbox('setValue',row.shopName);
        $('#shop_address').textbox('setValue',row.shopAddress);
        $('#str_desc').textbox('setValue',row.strDesc);
        $('#winshop').dialog('open');
    }
    function shopSave() {
        if(!$("#shopfm").form('enableValidation').form('validate')){
            return ;
        }
        var obj = {}; //声明一个对象
        obj.shopId = $('#shop_id').val();
        obj.shopName= $('#shop_name').textbox('getValue');
        obj.shopAddress= $('#shop_address').textbox('getValue');
        obj.strDesc= $('#str_desc').textbox('getValue');
        //
        $.ajax({
            type: "post",
            url: ctx+'/mb/shop/shopSave',
            async: true,
            contentType: 'application/json',
            dataType: 'JSON',
            data: JSON.stringify(obj),
            success: function(o) {
                //console.log(obj.data)
                if (o.success == true) {
                    alertMes(var_title,"保存成功");
                    getShopSessionList();
                }else{
                    alert_autoClose(var_title,"保存失败!--"+obj.message);
                }
            }
        });
    }
//刷新index的店铺列表
    function getShopSessionList() {
        $.ajax({
            type: "post",
            url: ctx+'/mb/shop/shopSessionList',
            async: true,
            contentType: 'application/json',
            dataType: 'JSON',
           // data: JSON.stringify(obj),
            success: function(o) {
                if (o.success == true) {
                    shopDatas = [];//这个在index页面已经定义
                    var data = o.data;
                    $.each(data, function(i,item){
                        shopDatas.push({"cshopId": item.shopId, "cshopName": item.shopName});
                    });
                    shoplists();
                }
            }
        });
    }

    function  shopquery() {
       // var shopName =$('#shopName').textbox('getValue');
        $('#shoplist').datagrid("reload",{});
    }
    function newAdd(){

        $("#shopfm").form('clear');
        $('#winshop').dialog('open');
    }
    var si = 0;
    //shopsize();
    function alertMes(title,mes) {
       // shopsize();
        alert_autoClose(title,mes);
        $('#winshop').dialog('close');
        $("#shopfm").form('clear');
        shopquery();
    }
    initp();

</script>

<div style="padding: 2px">
    <div style="margin:1px 1px;"></div>
    <div id="tb" style="padding:1px;height:auto" >
        <div style="margin-bottom:5px" style="width:90%;" >
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newAdd()">新增门店</a>
            <!--    <input class="easyui-textbox" id="shopName" style="width:170;padding:10px;" data-options="prompt:'请输入商品名称'">
                    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="shopquery()">查询</a>-->
            <span style="color: #c9302c" id="querySpan"> </span>
        </div>

    </div>
    <table id="shoplist"  title="" style="width:90%;height:600px">    </table>
</div>

<div id="winshop" class="easyui-dialog" data-options="modal:true" title="门店信息"  style="width: 510px; padding: 10px 20px; height: 370px;"
     closed="true" buttons="#dlg-buttonsshop">

    <form id="shopfm" method="post" action="" style="margin-top: 0px; margin-left: 0px;">
        <table>
            <tr style="margin-top: 20px; ">
                <td class="fitem"><label>门店名称:</label></td>
                <td>
                    <input id="shop_id" type="hidden" />
                    <input id="shop_name" data-options="required:true"  style="width:160px;" class="easyui-textbox"  />
                </td>
            </tr>
            <tr style="margin-top: 20px; ">
                <td class="fitem"><label>门店地址:</label></td>
                <td>
                    <input   class="easyui-textbox" id="shop_address" value="" style="width:160px;" >
                </td>
            </tr>

            <tr style="margin-top: 20px; ">
                <td class="fitem"><label>备注:</label></td>
                <td>
                    <input id="str_desc" class="easyui-textbox" data-options="multiline:true" value="" style="width:220px;height:80px">
                </td>
            </tr>

        </table>
    </form>
</div>
<!--直接写提交取消的事件不需要在js中绑定-->
<div id="dlg-buttonsshop" style="display: block">
    <a id="confirm2" href="javascript:void(0)" class="easyui-linkbutton c6" iconcls="icon-ok" onclick="shopSave()" style="width: 90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#winshop').dialog('close')" style="width: 90px">取消</a>
</div>

<div id="winshopuser" class="easyui-dialog" data-options="modal:true" title="员工信息"  style="width: 1100px; padding: 1px 3px; height: 630px;"
     closed="true" buttons="#dlg-buttonsshopuser">
  <!--  <div id="divshopuser"> </div>-->
</div>
<div id="dlg-buttonsshopuser" style="display: block">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#winshopuser').dialog('close')" style="width: 90px">取消</a>
</div>



