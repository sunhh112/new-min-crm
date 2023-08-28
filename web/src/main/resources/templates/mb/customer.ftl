
<style  type="text/css">
    /*
    .datagrid-header-rownumber, .datagrid-cell-rownumber{
    datagrid-cell datagrid-cell-c10-customerTel
     datagrid-cell-rownumber
    */

    .grid-panel .datagrid-btable tr{height: 62px;}
    .exp{ border:1px dashed #00F}
</style>

<script type="text/javascript">

    function initp() {
        $('#customerlist').datagrid({
            rownumbers:true,
            singleSelect:true,
            autoRowHeight:true,
            pagination:true,
            fitColumns: true,//自适应宽度
            pageSize:10,
            striped: true, //行条纹化，即奇偶行使用不同背景色
            toolbar:'#tb',
            loader: myloader,
            loadMsg:'查询中，请稍后...',
            columns:[[
                {field:'customerId',title:'',width:120,hidden:true},
                {field:'customerName',title:'会员姓名',width:120},
                {field:'customerTel',title:'手机号',width:130},
                {field:'customerBirthday',title:'出生日期',width:110,formatter: formatDateYYYYMMDD},
                {field:'customerScore',title:'当前积分',width:70},
                {field:'createDate',title:'创建时间',width:150,formatter: formatDateYYYYMMDDhh},
                {field:'createUid',title:'创建人',width:100,formatter:getAccountName},
                {field:'strDesc',title:'备注',width:200,formatter:formatDesc},
                {field:'操作',title:'操作',width:310,formatter:putAction}

            ]],
            onLoadSuccess: function (data) {
                $('.actionclass').linkbutton();
            }

        });
    }

    function putAction(v,row,index) {
        return  '<a href="#" onclick="customerEdit(\''+index+'\')" class="actionclass" iconCls="icon-edit" plain="true">编辑</a>'+
                '<a href="#" onclick="customerRemove(\''+index+'\')" class="actionclass" iconCls="icon-remove" plain="true">移除</a>'+
                '<a href="#" onclick="opencustomerScore(\''+index+'\')" class="actionclass" iconCls="icon-redo" plain="true">积分/兑换详情</a>'
                ;
  //      return '<a onclick="loginBut()" class="actionclass" href="#" class="easyui-linkbutton" style="width:40%;" >登录</a>';
    }

    function getRow(index){
        $('#customerlist').datagrid('selectRow',index);
        var rows = $('#customerlist').datagrid('getSelections');
        return rows[0];
    }

    function customerRemove(index){
        $.messager.confirm("删除提示", "您确定要删除该操作？", function (rt) {
            if(rt) {
                var row = getRow(index);
                var obj = {}; //声明一个对象
                obj.customerId = row.customerId;
                $.ajax({
                    type: "post",
                    url: ctx+'/mb/customer/customerRemove',
                    async: true, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
                    contentType: 'application/json',
                    dataType: 'JSON',
                    data: JSON.stringify(obj),
                    success: function(obj) {
                        //console.log(obj.data)
                        if (obj.success == true) {
                            alertMes("用户信息","删除成功");
                        }else{
                            alert_autoClose("用户信息","删除失败!--"+obj.message);
                        }
                    }
                });
            }
        });
    }

    function alertMes(title,mes) {
            alert_autoClose(title,mes);
            $('#winCustomer').dialog('close');
            $("#customerfm").form('clear');
            customerquery();
    }
    function customerEdit(index) {
        var row = getRow(index);
        $('#customer_id').val(row.customerId);
       $('#customer_name').textbox('setValue',row.customerName);
       $('#customer_birthday').textbox('setValue',formatDateYYYYMMDD(row.customerBirthday));
       $('#customer_tel').textbox('setValue',row.customerTel);
       $('#init_score').textbox('setValue',row.initScore);
       $('#str_desc').textbox('setValue',row.strDesc);
        $('#winCustomer').dialog('open');
    }
    
    var myloader = function(param, success, error) {
        var obj = {}; //声明一个对象
       // obj.id = 10001; //后端要求每次请求传入一个ID
        obj.page = param.page;
        obj.rows = param.rows;
        obj.customerName= param.customerName;
        obj.customerTel= param.customerTel;
        $.ajax({
            type: "post",
            url: ctx+'/mb/customer/customerlist',
            async: true, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
            contentType: 'application/json',
            dataType: 'JSON',
            data: JSON.stringify(obj),
            success: function(obj) {
                //console.log(obj.data)
                if (obj.success == true) {
                    if (obj.data.total > 0)
                        $('#querySpan').html("");
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

    function customerSave() {
        if(!$("#customerfm").form('enableValidation').form('validate')){
            return ;
        }
        var obj = {}; //声明一个对象
        obj.customerId = $('#customer_id').val();
        obj.customerName= $('#customer_name').textbox('getValue');
        obj.customerBirthday=$('#customer_birthday').textbox('getValue');
        obj.customerTel=$('#customer_tel').textbox('getValue');
        obj.initScore=$('#init_score').textbox('getValue');
        obj.strDesc=$('#str_desc').textbox('getValue');
        $.ajax({
            type: "post",
            url: ctx+'/mb/customer/customerSave',
            async: true, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
            contentType: 'application/json',
            dataType: 'JSON',
            data: JSON.stringify(obj),
            success: function(o) {
                //console.log(obj.data)
                if (o.success == true) {
                    alertMes("用户信息","保存成功");
                }else{
                    alert_autoClose("用户信息","保存失败!--"+obj.message);
                }
            }
        });
    }

    function  customerquery() {
        var customerName =$('#customerName').textbox('getValue');
        var customerTel =$('#customerTel').textbox('getValue');
        $('#customerlist').datagrid("reload",{customerName:customerName,customerTel:customerTel });

    }
    function newAdd(){
        $('#init_score').textbox('setValue',0);
        $('#winCustomer').dialog('open');
    }
    initp();


    function opencustomerScore(index){
        var row = getRow(index);
        var customerName = row.customerName ;
        $('#wincustomerScore').dialog({title: "("+customerName+")积分/兑换详情"});
        $('#wincustomerScore').dialog('open');
        $('#wincustomerScore').load(ctx+"/mb/load?menupage=mb/orderScore&customerTel="+row.customerTel+"&customerName="+row.customerName);

        //$('#winscoreAddExchange1').dialog({});
    }



</script>
<div style="padding: 2px">
    <div style="margin:1px 1px;"></div>
    <div id="tb" style="padding:1px;height:auto" >
        <div style="margin-bottom:5px" style="width:90%;" >
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newAdd()">新增会员</a>
         <!--   <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>

            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
            -->
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input class="easyui-textbox" id="customerName" style="width:170;padding:10px;" data-options="prompt:'请输入会员姓名'">
            或
            <input class="easyui-textbox" id="customerTel" style="width:170;padding:10px;" data-options="prompt:'输入会员手机号查询'">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="customerquery()">查询</a>
            <span style="color: #c9302c" id="querySpan"> </span>
        </div>

    </div>

    <div class="grid-panel" style="width: 100%;height: 100%">
         <table id="customerlist"  title="" style="width:90%;height:600px">    </table>
    </div>
</div>

<div id="winCustomer" class="easyui-dialog" data-options="modal:true" title="会员信息"  style="width: 510px; padding: 10px 20px; height: 470px;"
     closed="true" buttons="#dlg-buttonsCustomer">
    <form id="customerfm" method="post" action="" style="margin-top: 0px; margin-left: 0px;">
        <table>
            <tr style="margin-top: 20px; ">
                <td class="fitem"><label>会员姓名:</label></td>
                <td >
                    <input id="customer_id" type="hidden" />
                    <input id="customer_name" data-options="required:true"  style="width:200px;" class="easyui-textbox"  />
                </td>
            </tr>
            <tr style="margin-top: 20px; ">
                <td class="fitem"><label>手机号:</label></td>
                <td>
                    <input id="customer_tel" data-options="required:true,prompt:'请输入正确的手机号码',validType:'telNum'" style="width:200px;"  class="easyui-textbox"  />
                </td>
            </tr>
            <tr style="margin-top: 20px; ">
                <td class="fitem"><label>出生日期:</label></td>
                <td>
                    <input id="customer_birthday" type="text"  data-options="formatter:myDateformatter,parser:myDateparser" class="easyui-datebox" style="width:200px;">
                </td>
            </tr>
            <tr style="margin-top: 20px; ">
                <td class="fitem"><label>初始积分:</label></td>
                <td>
                    <input  id="init_score" class="easyui-numberspinner" data-options="min:0,max:2000,required:true" style="width:120px;" value="0">
                </td>
            </tr>

            <tr style="margin-top: 20px; ">
                <td class="fitem"><label>备注:</label></td>
                <td>
                    <input id="str_desc" class="easyui-textbox" data-options="multiline:true" value="" style="width:300px;height:100px">
                </td>
            </tr>
        </table>
    </form>
</div>
<!--直接写提交取消的事件不需要在js中绑定-->
<div id="dlg-buttonsCustomer" style="display: block">
    <a id="confirm2" href="javascript:void(0)" class="easyui-linkbutton c6" iconcls="icon-ok" onclick="customerSave()" style="width: 110px">保存并关闭</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#winCustomer').dialog('close')" style="width: 90px">取消</a>
</div>


<div id="wincustomerScore" class="easyui-dialog" data-options="modal:true" title="积分详情"  style="width: 1400px; padding: 1px 3px; height: 730px;"
     closed="true" buttons="#dlg-buttonscustomerScore">

</div>
<div id="dlg-buttonscustomerScore" style="display: block">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#wincustomerScore').dialog('close')" style="width: 90px">取消</a>
</div>
