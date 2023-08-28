
<style  type="text/css">

</style>


<script type="text/javascript">
    var var_title ="商品信息";

    function initcategory(categoryDatas) {
        $('#category_id').combobox({
            valueField:'categoryId',
            textField:'categoryName',
            required:true,
            data:categoryDatas
        });
    }

    function initp() {
        categorys(initcategory);
        $('#pp2').tooltip({
            position: 'bottom',
            content: '<div style="padding:5px;background:#eee;color:#000;width: 200px"> 同一商品根据版本号的不同可以维护多个,用以区分同一商品价格波动,默认为当前年月</div>',
            onShow: function(){
                $(this).tooltip('tip').css({
                    backgroundColor: '#fff000',
                    borderColor: '#ff0000',
                    boxShadow: '1px 1px 3px #292929'
                });
            },
            onPosition: function(){
                $(this).tooltip('tip').css('left', $(this).offset().left);
                $(this).tooltip('arrow').css('left', 20);
            }
        });



        $('#goodslist').datagrid({
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
                {field:'goodsId',title:'',width:120,hidden:true},
                {field:'categoryId',title:'商品分类',width:120,formatter:getCategoryName},
                {field:'goodsName',title:'商品名称',width:120},
                {field:'goodsVersion',title:'版本号',width:90},
                {field:'goodsPurchasePrice',title:'进价',width:70},
                {field:'goodsSalePrice',title:'售价',width:70},

                {field:'createDate',title:'创建时间',width:150,formatter: formatDateYYYYMMDDhh},
                {field:'createUid',title:'创建人',width:100,formatter:getAccountName},
                {field:'strDesc',title:'备注',width:150,formatter:formatDesc},
                {field:'操作',title:'操作',width:200,formatter:putAction}

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
        obj.goodsName= param.goodsName;
        $.ajax({
            type: "post",
            url: ctx+'/mb/goods/goodslist',
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

    function putAction(v,row,index) {
        return '<a href="#" onclick="goodsEdit(\''+index+'\')" class="actionclass" iconCls="icon-edit" plain="true">编辑</a>'+
                '<a href="#" onclick="goodsRemove(\''+index+'\')" class="actionclass" iconCls="icon-remove" plain="true">移除</a>';
    }

    function getRow(index){
        $('#goodslist').datagrid('selectRow',index);
        var rows = $('#goodslist').datagrid('getSelections');
        return rows[0];
    }

    function goodsRemove(index){
        $.messager.confirm("删除提示", "您确定要删除操作吗？", function (rt) {
            if(rt) {
                var row = getRow(index);
                var obj = {}; //声明一个对象
                obj.goodsId = row.goodsId;
                $.ajax({
                    type: "post",
                    url: ctx+'/mb/goods/goodsRemove',
                    async: true, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
                    contentType: 'application/json',
                    dataType: 'JSON',
                    data: JSON.stringify(obj),
                    success: function(obj) {
                        if (obj.success == true) {
                            alertMes(var_title,"删除成功");
                        }else{
                            alert_autoClose(var_title,"删除失败!--"+obj.message);
                        }
                    }
                });
            }
        });
    }

    function goodsEdit(index) {
        var row = getRow(index);
        $('#goods_id').val(row.goodsId);
        $('#goods_name').textbox('setValue',row.goodsName);
        $('#category_id').combobox('setValue',row.categoryId);
        $('#goods_sale_price').textbox('setValue',row.goodsSalePrice);
        $('#goods_purchase_price').textbox('setValue',row.goodsPurchasePrice);
        $('#goods_version').textbox('setValue',row.goodsVersion);
        $('#str_desc').textbox('setValue',row.strDesc);
        $('#wingoods').dialog('open');
    }
    function goodsSave() {
        if(!$("#goodsfm").form('enableValidation').form('validate')){
            return ;
        }
        var obj = {}; //声明一个对象
        obj.goodsId = $('#goods_id').val();
        obj.goodsName= $('#goods_name').textbox('getValue');
        obj.categoryId= $('#category_id').textbox('getValue');
        obj.goodsSalePrice= $('#goods_sale_price').textbox('getValue');
        obj.goodsPurchasePrice= $('#goods_purchase_price').textbox('getValue');
        obj.goodsVersion= $('#goods_version').textbox('getValue');
        obj.strDesc= $('#str_desc').textbox('getValue');
        //
        $.ajax({
            type: "post",
            url: ctx+'/mb/goods/goodsSave',
            async: true, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
            contentType: 'application/json',
            dataType: 'JSON',
            data: JSON.stringify(obj),
            success: function(o) {
                //console.log(obj.data)
                if (o.success == true) {
                    alertMes(var_title,"保存成功");
                }else{
                    alert_autoClose(var_title,"保存失败!--"+obj.message);
                }
            }
        });
    }

    function  goodsquery() {
        var goodsName =$('#goodsName').textbox('getValue');
        $('#goodslist').datagrid("reload",{goodsName:goodsName});

    }
    function newAdd(){
        $('#goods_version').textbox('setValue', doHandleYYYYMM());
        $('#goods_score').textbox('setValue',0);
        $('#wingoods').dialog('open');
    }
    function alertMes(title,mes) {
        alert_autoClose(title,mes);
        $('#wingoods').dialog('close');
        $("#goodsfm").form('clear');
        goodsquery();
    }

    initp();

   // categorys(callback);

</script>

<div style="padding: 2px">
    <div style="margin:1px 1px;"></div>
    <div id="tb" style="padding:1px;height:auto" >
        <div style="margin-bottom:5px" style="width:90%;" >
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newAdd()">新增商品</a>

            <input class="easyui-textbox" id="goodsName" style="width:170;padding:10px;" data-options="prompt:'请输入商品名称'">

            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="goodsquery()">查询</a>
            <span style="color: #c9302c" id="querySpan"> </span>
        </div>

    </div>
    <table id="goodslist"  title="" style="width:90%;height:600px">    </table>
</div>

<div id="wingoods" class="easyui-dialog" data-options="modal:true" title="商品信息"  style="width: 510px; padding: 10px 20px; height: 370px;"
     closed="true" buttons="#dlg-buttonsgoods">

    <form id="goodsfm" method="post" action="" style="margin-top: 0px; margin-left: 0px;">
        <table>
            <tr style="margin-top: 20px; ">
                <td class="fitem"><label>商品名称:</label></td>
                <td>
                    <input id="goods_id" type="hidden" />
                    <input id="goods_name" data-options="required:true"  style="width:160px;" class="easyui-textbox"  />
                </td>
            </tr>
            <tr style="margin-top: 20px; ">
                <td class="fitem"><label>商品分类:</label></td>
                <td>
                    <input id="category_id" name="category_id" value="" style="width:160px;" >

                </td>
            </tr>
            <tr style="margin-top: 20px; ">
                <td class="fitem"><label>版本号:</label></td>
                <td>

                    <input id="goods_version" type="text" data-options="required:true" class="easyui-textbox" style="width:160px;">
                    <label id="pp2" style="width:130px;padding:5px">
                        默认当前年月,详情..
                    </label>
                </td>
            </tr>
            <tr style="margin-top: 20px; ">
                <td class="fitem"><label>商品进价:</label></td>
                <td>
                    <input  id="goods_purchase_price" class="easyui-numberspinner" data-options="min:0,max:2000,required:true" style="width:100px;" value="0">

                </td>
            </tr>
            <tr style="margin-top: 20px; ">
                <td class="fitem"><label>商品售价:</label></td>
                <td>
                    <input  id="goods_sale_price" class="easyui-numberspinner" data-options="min:0,max:2000,required:true" style="width:100px;" value="0">

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
<div id="dlg-buttonsgoods" style="display: block">
    <a id="confirm2" href="javascript:void(0)" class="easyui-linkbutton c6" iconcls="icon-ok" onclick="goodsSave()" style="width: 90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#wingoods').dialog('close')" style="width: 90px">取消</a>
</div>
