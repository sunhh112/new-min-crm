
<style  type="text/css">

</style>

<script type="text/javascript">
    var var_title ="商品分类";

    function initp() {
        $('#categorylist').datagrid({
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
                {field:'categoryId',title:'',width:120,hidden:true},
                {field:'categoryName',title:'分类名称',width:120},
                {field:'activeFlag',title:'状态',width:120,formatter:getstatus},
                {field:'createDate',title:'创建时间',width:150,formatter: formatDateYYYYMMDDhh},
                {field:'createUid',title:'创建人',width:100,formatter:getAccountName},

                {field:'操作',title:'操作',width:260,formatter:putAction}

            ]],
            onLoadSuccess: function (data) {
                $('.actionclass').linkbutton();
            }
        });
    }

    function getstatus(v){
        if(v=="2") return "已停用";
        if(v=="1") return "在用";
        return v ;
    }


    var myloader = function(param, success, error) {
        var obj = {}; //声明一个对象
        // obj.id = 10001; //后端要求每次请求传入一个ID
        obj.page = param.page;
        obj.rows = param.rows;
        obj.categoryName= param.categoryName;
        $.ajax({
            type: "post",
            url: ctx+'/mb/category/categorylist',
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
         var a =  '<a href="#" onclick="categoryEdit(\''+index+'\')" class="actionclass" iconCls="icon-edit" plain="true">编辑</a>';
         if(row.activeFlag=="1")
              a = a+  '<a href="#" onclick="categoryUpdateActive(\''+index+'\',2)" class="actionclass" iconCls="icon-remove" plain="true">移除</a>';
        if(row.activeFlag=="2")
            a = a+  '<a href="#" onclick="categoryUpdateActive(\''+index+'\',1)" class="actionclass" iconCls="icon-add" plain="true">启用</a>';
        return a;
    }

    function getRow(index){
        $('#categorylist').datagrid('selectRow',index);
        var rows = $('#categorylist').datagrid('getSelections');
        return rows[0];
    }

    function categoryUpdateActive(index,activeFlag){
        $.messager.confirm("删除提示", "您确定要删除该操作？", function (rt) {
            if(rt) {
                var row = getRow(index);
                var obj = {}; //声明一个对象
                obj.categoryId = row.categoryId;
                obj.activeFlag = activeFlag
                $.ajax({
                    type: "post",
                    url: ctx+'/mb/category/categoryUpdateActive',
                    async: true, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
                    contentType: 'application/json',
                    dataType: 'JSON',
                    data: JSON.stringify(obj),
                    success: function(obj) {
                        //console.log(obj.data)
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

    function categoryEdit(index) {
        var row = getRow(index);
        $('#category_id').val(row.categoryId);
        $('#category_name').textbox('setValue',row.categoryName);
        $('#wincategory').dialog('open');
    }
    function categorySave() {
        if(!$("#categoryfm").form('enableValidation').form('validate')){
            return ;
        }
        var obj = {}; //声明一个对象
        obj.categoryId = $('#category_id').val();
        obj.categoryName= $('#category_name').textbox('getValue');

        $.ajax({
            type: "post",
            url: ctx+'/mb/category/categorySave',
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

    function  categoryquery() {
        var categoryName =$('#categoryName').textbox('getValue');
        $('#categorylist').datagrid("reload",{categoryName:categoryName});

    }
    function newAdd(){
        $('#category_score').textbox('setValue',0);
        $('#wincategory').dialog('open');
    }
    function alertMes(title,mes) {
        alert_autoClose(title,mes);
        $('#wincategory').dialog('close');
        $("#categoryfm").form('clear');
        categoryquery();
    }

    initp();



</script>
<div style="padding: 2px">
    <div style="margin:1px 1px;"></div>
    <div id="tb" style="padding:1px;height:auto" >
        <div style="margin-bottom:5px" style="width:90%;" >
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newAdd()">新增商品分类</a>

            <input class="easyui-textbox" id="categoryName" style="width:170;padding:10px;" data-options="prompt:'请输入商品分类名称'">

            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="categoryquery()">查询</a>
            <span style="color: #c9302c" id="querySpan"> </span>
        </div>

    </div>
    <table id="categorylist"  title="" style="width:90%;height:600px">    </table>
</div>

<div id="wincategory" class="easyui-dialog" data-options="modal:true" title="商品分类"  style="width: 510px; padding: 10px 20px; height: 170px;"
     closed="true" buttons="#dlg-buttonscategory">

    <form id="categoryfm" method="post" action="" style="margin-top: 0px; margin-left: 0px;">
        <table>
            <tr style="margin-top: 20px; ">
                <td class="fitem"><label>商品分类:</label></td>
                <td>

                    <input id="category_id" type="hidden" />
                    <input id="category_name" data-options="required:true"  style="width:200px;" class="easyui-textbox"  />
                </td>
            </tr>

        </table>
    </form>
</div>
<!--直接写提交取消的事件不需要在js中绑定-->
<div id="dlg-buttonscategory" style="display: block">
    <a id="confirm2" href="javascript:void(0)" class="easyui-linkbutton c6" iconcls="icon-ok" onclick="categorySave()" style="width: 90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#wincategory').dialog('close')" style="width: 90px">取消</a>
</div>

