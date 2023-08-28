
<style  type="text/css">
    .grid-panel .datagrid-btable tr{height: 62px;}
    .exp{ border:1px dashed #00F}
    body,div{ font: 14px/1.5 Tahoma,Helvetica,Arial,'宋体',sans-serif}
</style>


<script type="text/javascript">
    var var_title2 ="积分兑换";
    $('#winscoreAddExchange1').dialog({});
    $('#customerNameq').textbox({});
    $('#customerTelq').textbox({});
    $('#order_id_sc').textbox({});
    $('#customer_id_sc').textbox({});
    $('#goods_name_ex').textbox({});
    $('#str_desc_sc').textbox({});

    $('#exchange_score_ex').numberbox({});
    $('#goods_score_sc').numberbox({});
    $('#confirm23').linkbutton({});
    $('#confirm24').linkbutton({});
    $('#cancel22').linkbutton({});
    $('#scoreQuery').linkbutton({});

    $('#goods_fact_price_sc').numberbox({
        onChange: function(v) {
            $('#goods_score_sc').textbox('setValue',Math.ceil(v));
        }
    });


    $('#goods_id_sc').combobox({
        valueField:'goodsId',
        textField:'goodsName',
        required:true
    });
    /*
    $('#category_id').combobox({
        valueField:'categoryId',
        textField:'categoryName',
        required:true,
        data:categoryDatas
    });*/
    function initcategory(categoryDatas) {

        $('#category_id_sc').combobox({
            valueField:'categoryId',
            textField:'categoryName',
            required:true,
            data:categoryDatas
            ,  onSelect:selectCategory
        });

    }

    function initp3() {
        categorys(initcategory);
        <#if customerTel1?? && customerTel1 != "" &&customerTel1 !="null">
            $('#customerTelq').textbox('setValue','${customerTel1}');
            $("#customerTelq").textbox('readonly', true);
            $("#customerNameq").textbox('readonly', true);
            $('#customerNameq').textbox('setValue','${customerName1}');
        </#if>

        $('#pp22').tooltip({
            position: 'bottom',
            content: '<div style="padding:5px;background:#eee;color:#000;width: 200px">默认等于实际售价，1元1积分，可以修改。 </div>',
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

        $('#orderScorelist').datagrid({
            rownumbers:true,
            singleSelect:true,
            autoRowHeight:true,
            pagination:true,
            pageSize:10,
            striped: true, //行条纹化，即奇偶行使用不同背景色
            toolbar:'#tbScore',
            loader: myloader2,
            queryParams:queryparam(),
            loadMsg:'查询中，请稍后...',
            columns:[[
                {field:'scoreType',title:'分类',width:75,formatter:getscoreType},
                {field:'customerName',title:'会员姓名',width:93},
                {field:'customerTel',title:'手机号',width:115},
                {field:'customerScore',title:'总积分',width:62},
                {field:'goodsScore',title:'本次积分',width:62},
                {field:'categoryId',title:'商品分类',width:85,formatter:getCategoryName},
                {field:'goodsName',title:'商品名称',width:85},
                {field:'goodsVersion',title:'版本号',width:85},
                {field:'goodsFactPrice',title:'实际售价',width:50},
                {field:'createDate',title:'创建时间',width:140,formatter: formatDateYYYYMMDDhh},
                {field:'createUid',title:'创建人',width:80,formatter:getAccountName},
                {field:'strDesc',title:'备注',width:90,formatter:formatDesc},
                {field:'操作',title:'操作',width:140,formatter:putAction2}

            ]],
            onLoadSuccess: function (data) {
                $('.actionclass').linkbutton();
                $(".easyui-tooltip").tooltip({
                    onShow: function () {
                        (this).tooltip('tip').css({borderColor: '#000'  });
                    }
                });
            },
            rowStyler:function(index,row){
                //大于的时候，显示为黄色，100%的时候，显示为红色。
                if (row.scoreType == "2"){
                    return 'background-color:#FFAEB9;';
                }
            }
        });
    }

    function getscoreType(v){
        if(v=="0") return "初始积分";
        if(v=="1") return "购物积分";
        if(v=="2") return "兑换积分";
        return v ;
    }

    var myloader2 = function(param, success, error) {
        var obj = {}; //声明一个对象
        // obj.id = 10001; //后端要求每次请求传入一个ID
        obj.page = param.page;
        obj.rows = param.rows;
        obj.goodsName= param.goodsName;
        obj.customerTel= param.customerTel;
        obj.customerName= param.customerName;
        obj.goodsId= param.goodsId;
        obj.categoryId= param.categoryId;
        $.ajax({
            type: "post",
            url: ctx+'/mb/orderScore/orderScorelist',
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

    function putAction2(v,row,index) {
        var sb =  '<div><a href="#" onclick="scoreAddExchange(\''+index+'\')" class="actionclass" iconCls="icon-add" plain="true">新增积分/兑换</a>';
        if (row.scoreType != "0") {
            sb = sb + '<div ><a href="#" onclick="orderScoreEdit(\''+index+'\')" class="actionclass" iconCls="icon-edit" plain="true">编辑</a>';
            sb = sb + '<a href="#" onclick="orderScoreRemove(\'' + index + '\')" class="actionclass" iconCls="icon-remove" plain="true">移除</a></div>';
        }
        return sb
     }

    function getRow2(index){
        $('#orderScorelist').datagrid('selectRow',index);
        var rows = $('#orderScorelist').datagrid('getSelections');
        return rows[0];
    }

    function orderScoreRemove(index){
        $.messager.confirm("移除提示", "您确定要移除操作吗？", function (rt) {
            if(rt) {
                var row = getRow2(index);
                var obj = {}; //声明一个对象
                obj.orderId = row.orderId;
                obj.scoreType = row.scoreType;
                obj.customerId = row.customerId;
                $.ajax({
                    type: "post",
                    url: ctx+'/mb/orderScore/orderScoreRemove',
                    async: false, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
                    contentType: 'application/json',
                    dataType: 'JSON',
                    data: JSON.stringify(obj),
                    success: function(obj) {
                        if (obj.success == true) {
                            alertMesScore2(var_title2,"移除成功");
                        }else{
                            alert_autoClose(var_title2,"移除失败!--"+obj.message);
                        }
                    }
                });
            }
        });
    }

    function queryparam() {
        var customerTel =$('#customerTelq').textbox('getValue');
        var customerName =$('#customerNameq').textbox('getValue');
        var goodsId =$('#goods_id_q').textbox('getValue');
        var categoryId =$('#category_id_q').textbox('getValue');
        return   {customerTel:customerTel,customerName:customerName,categoryId:categoryId,goodsId:goodsId};
    }

    function  orderScorequery() {

        $('#orderScorelist').datagrid("reload",queryparam());
    }

    function alertMesScore2(title,mes) {
        alert_autoClose(title,mes);
        $('#winscoreAddExchange1').dialog('close');
        orderScorequery();
    }

    function scoreAdd(i) {
        vaildateInit();
        if(!$("#scoreAddExchangefm").form('enableValidation').form('validate')){
            return ;
        }
        var obj = {}; //声明一个对象
        obj.orderId = $('#order_id_sc').textbox('getValue');
        obj.customerId = $('#customer_id_sc').textbox('getValue');
        obj.categoryId= $('#category_id_sc').textbox('getValue');
        obj.goodsId=$('#goods_id_sc').textbox('getValue');
        obj.goodsFactPrice=$('#goods_fact_price_sc').textbox('getValue');
        obj.goodsScore=$('#goods_score_sc').textbox('getValue');
        obj.strDesc=$('#str_desc_sc').textbox('getValue');
        var goodsname = $('#goods_id_sc').combobox('getText'); // 所有选中的值对
        $.ajax({
            type: "post",
            url: ctx+'/mb/orderScore/scoreSave',
            async: false, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
            contentType: 'application/json',
            dataType: 'JSON',
            data: JSON.stringify(obj),
            success: function(o) {
                //console.log(obj.data)
                if (o.success == true) {
                    if(i==0) {
                        alertMesScore2("积分", "保存成功《" + goodsname + "》");
                        orderScorequery();
                    }else{
                        alert_autoClose("积分", "保存成功《" + goodsname + "》");
                        $('#category_id_sc').combobox('setValue', '');
                        $('#goods_id_sc').combobox('clear');
                        $('#goods_sale_price_sc').text('');
                        $('#goods_purchase_price_sc').text('');
                        $('#goods_fact_price_sc').textbox('setValue','');
                        $('#goods_score_sc').textbox('setValue','');
                        $('#str_desc_sc').textbox('setValue','');
                    }
                }else{
                    alert_autoClose("积分","保存失败!--"+obj.message);
                }
            }
        });
    }
    
    function vaildateInit() {
            var v = $("input[name='score_type1']:checked").val();
            if(v=='1') {
                $('#exchange_score_ex').textbox('setValue', '0');
                $('#goods_name_ex').textbox('setValue', '0');
            }
            else{
                $('#category_id_sc').combobox('setValue', '0');
                $('#goods_id_sc').combobox('setValue', '0');
                $('#goods_fact_price_sc').textbox('setValue','0');
                $('#goods_score_sc').textbox('setValue','0');
            }
    }
    function scoreExchange(i) {

        vaildateInit();
        if(!$("#scoreAddExchangefm").form('enableValidation').form('validate')){
            return ;
        }
        var obj = {}; //声明一个对象
        obj.exchangeId = $('#order_id_sc').textbox('getValue');
        obj.customerId = $('#customer_id_sc').textbox('getValue');
        obj.exchangeScore=$('#exchange_score_ex').textbox('getValue');
        obj.goodsName=$('#goods_name_ex').textbox('getValue');
        obj.strDesc=$('#str_desc_sc').textbox('getValue');
        var goodsName = $('#goods_name_ex').textbox('getValue'); // 所有选中的值对
        $.ajax({
            type: "post",
            url: ctx+'/mb/orderScore/scoreExchangeSave',
            async: false, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
            contentType: 'application/json',
            dataType: 'JSON',
            data: JSON.stringify(obj),
            success: function(o) {
                //console.log(obj.data)
                if (o.success == true) {
                    if(i==0) {
                        alertMesScore2("物品兑换", "保存成功《" + goodsName + "》");

                    }else{
                        alert_autoClose("物品兑换", "保存成功《" + goodsName + "》");
                        $('#exchange_score_ex').textbox('setValue','');
                        $('#goods_name_ex').textbox('setValue','');
                        $('#str_desc_sc').textbox('setValue','');

                    }
                }else{
                    alert_autoClose("物品兑换","保存失败!--"+obj.message);
                }
            }
        });
    }
    function scoreAddExchangeSave(i) {
        var value = $("input[name='score_type1']:checked").val();
        if(value=='1')
            scoreAdd(i)
        else
            scoreExchange(i);
    }

    function orderScoreEdit(index) {
        $('#category_id_sc').combobox('setValue', '');
        $('#goods_id_sc').combobox('setValue', '');
        $(".a22").show();
        $(".a11").show();
        var row = getRow2(index);
        $('#customer_id_sc').textbox('setValue',row.customerId);
        $('#customer_name_sc').text(row.customerName);
        $('#customer_tel_sc').text(row.customerTel);
        $('#exchange_score_ex').textbox('setValue',Math.abs(row.goodsScore));
        $('#goods_name_ex').textbox('setValue',row.goodsName);

        $('#category_id_sc').combobox('setValue', row.categoryId);
        $('#goods_id_sc').combobox('setValue', row.goodsId);

        $('#goods_purchase_price_sc').text(row.goodsPurchasePrice);
        $('#goods_sale_price_sc').text(row.goodsSalePrice);
        goodsFactPrice = row.goodsFactPrice;
        goodsScore =row.goodsScore;
        //$('#goods_fact_price_sc').textbox('setValue',row.goodsFactPrice);
       // $('#goods_score_sc').textbox('setValue',row.goodsScore);
        $('#str_desc_sc').textbox('setValue',row.strDesc);
        $('#order_id_sc').textbox('setValue',row.orderId);
        $("input[type=radio][name='score_type1'][value="+row.scoreType+"]").prop("checked",true);
        var value = $("input[name='score_type1']:checked").val();
        if(value=="1")
            $(".a22").hide();
        else
            $(".a11").hide();
        hideTr(row.scoreType);
        $('#winscoreAddExchange1').dialog('open');

    }
var goodsFactPrice ="" ;
var goodsScore="" ;
    function scoreAddExchange(index) {
        $(".a22").show();
        $(".a11").show();
        $("input[type=radio][name='score_type1'][value=1]").prop("checked",true);

        var row = getRow2(index);
        $('#customer_id_sc').textbox('setValue',row.customerId);
        $('#order_id_sc').textbox('setValue','');
        $('#customer_name_sc').text(row.customerName);
        $('#customer_tel_sc').text(row.customerTel);
        scoreType();
        $('#winscoreAddExchange1').dialog('open');
    }

    function  hideTr(v) {
        if(v=="1"){
            $(".a2").hide();
            $(".a1").show();
        }else{
            $(".a1").hide();
            $(".a2").show();
        }
    }

    function scoreType() {
        var value = $("input[name='score_type1']:checked").val();
            if(value=='1') {
                $('#category_id_sc').combobox('setValue', '');
                $('#goods_id_sc').combobox('clear');
                $('#goods_sale_price_sc').text('');
                $('#goods_purchase_price_sc').text('');
                $('#goods_fact_price_sc').textbox('setValue','');
                $('#goods_score_sc').textbox('setValue','');

            }
            else {
                $('#exchange_score_ex').textbox('setValue','');
                $('#goods_name_ex').textbox('setValue','');

            }
        hideTr(value);
    }

    function selectCategory(rec) {
        actionCategoryChange(rec.categoryId,$('#goods_id_sc'));
    }
    function selectCategoryq(rec) {
        actionCategoryChange(rec.categoryId,$('#goods_id_q'));
    }
    function actionCategoryChange(categoryId,jgoods_id){
        jgoods_id.combobox({
            valueField:'goodsId',
            textField:'goodsName',
            async: false,
            onSelect:selectGoods,
            url: ctx+'/mb/goods/goodsBycategoryList?categoryId='+categoryId,
            onLoadSuccess: function (rs) {
                data = rs;
                if (data.length <= 0) {  //判断如果子项中没有数据
                    $('#goods_id_sc').combobox('clear').combobox('setValue', '无商品');//设置子项中无子分类
                }
            }
        });
    }

    function selectGoods(res) {
        $.ajax({
            type: "post",
            url: ctx+'/mb/goods/goodsById?goodsId='+res.goodsId,
            async: false, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
            contentType: 'application/json',
            dataType: 'JSON',
            //data: JSON.stringify(obj),
            success: function(o) {
                //console.log(obj.data)
                if (o.success == true) {
                    var data = o.data;
                    $('#goods_purchase_price_sc').text(data.goodsPurchasePrice);
                    $('#goods_sale_price_sc').text(data.goodsSalePrice);
                    $('#goods_fact_price_sc').textbox('setValue',data.goodsSalePrice);
                    $('#goods_score_sc').textbox('setValue',data.goodsSalePrice);
                    if(goodsScore!=''){//编辑时使用
                        $('#goods_fact_price_sc').textbox('setValue',goodsFactPrice);
                        $('#goods_score_sc').textbox('setValue',goodsScore);
                    }
                    goodsFactPrice = "";
                    goodsScore = "";

                }else{
                    alert_autoClose("商品信息","查询失败!--"+obj.message);
                }
            }
        });
    }



    $('#category_id_q').combobox({
        valueField:'categoryId',
        textField:'categoryName',
        data:categoryDatas
        ,  onSelect:selectCategoryq
    });
    $('#goods_id_q').combobox({
        valueField:'goodsId',
        textField:'goodsName'
    });
    initp3();
</script>

<div style="padding: 2px">
    <div style="margin:1px 1px;"></div>
    <div id="tbScore" style="padding:1px;height:auto" >
        <div style="margin-bottom:5px" style="width:90%;" >
            查询条件:
            <input class="easyui-textbox" id="customerNameq" style="width:170;padding:10px;" data-options="prompt:'请输入会员名称'">&nbsp;或
            <input class="easyui-textbox" id="customerTelq" style="width:170;padding:10px;" data-options="prompt:'请输入会员手机号'">&nbsp;或
            <input id="category_id_q" name="category_id_q" value="" style="width:160px;" data-options="prompt:'请选择商品分类'">&nbsp;或
            <input id="goods_id_q" name="goods_id_q" value="" style="width:160px;" data-options="prompt:'请选择商品'">
            <a href="#" id="scoreQuery" class="easyui-linkbutton" iconCls="icon-search" onclick="orderScorequery()">查询</a>
            <span style="color: #c9302c" id="querySpan2"> </span>
        </div>

    </div>
    <!--
    <div class="grid-panel" style="width: 100%;height: 100%"> </div>-->
    <div class="grid-panel" style="width: 100%;height: 100%">
        <table id="orderScorelist"  title="" style="width:100%;height:610px">    </table>
    </div>
</div>

<div id="winscoreAddExchange1" class="easyui-dialog" data-options="modal:true" title="积分、兑换操作窗口"  style="width:520px; padding: 10px 20px; height:560px;"
     closed="true" buttons="#dlg-buttonsscoreAddExchange1">

    <form id="scoreAddExchangefm" method="post" action="" style="margin-top: 0px; margin-left: 0px;">
        <table>
            <tr style="height: 35px ">
                <td class="fitem"><label>操作类型:</label></td>
                <td >
                    &nbsp; &nbsp;
                    <label for="validatebox1" class="a11 choose-box"><input class="a11" type="radio" onclick="scoreType()" name="score_type1" value="1" id="validatebox1" />积分</label>
                    &nbsp; &nbsp; &nbsp; &nbsp;
                    <label for="validatebox2" class="a22 choose-box"><input class="a22" type="radio"  onclick="scoreType()"  name="score_type1" value="2" id="validatebox2"  />兑换</label>

                </td>
            </tr>

            <tr style="height: 35px ">
                <td class="fitem"><label>会员姓名:</label></td>
                <td style="background-color: #b3b3b3">
                    <label id="customer_name_sc"></label>
                    <input id="customer_id_sc" class="easyui-textbox" type="hidden" />
                    <input id="order_id_sc" class="easyui-textbox" type="hidden" />
                </td>
            </tr>
            <tr style="height: 35px ">
                <td class="fitem"><label>手机号:</label></td>
                <td style="background-color: #b3b3b3">
                    <label id="customer_tel_sc"  ></label>
                </td>
            </tr>
            <tr style="height: 35px " class="a1">
                <td class="fitem"><label>商品分类:</label></td>
                <td>
                    <input id="category_id_sc" name="category_id_sc" value="" style="width:210px;" >
                </td>
            </tr>
            <tr  style="height: 35px " class="a1">
                <td class="fitem" style="width: 35px"><label>商品名称(版本号):</label></td>
                <td>
                    <input id="goods_id_sc" name="goods_id_sc" value="" style="width:210px;" >
                </td>
            </tr>
            <tr  style="height: 35px " class="a1">
                <td class="fitem"><label>进货价:</label></td>
                <td style="background-color: #b3b3b3">
                    <label id="goods_purchase_price_sc" ></label>

                </td>
            </tr>
            <tr  style="height: 35px " class="a1">
                <td class="fitem"><label>建议售价:</label></td>
                <td style="background-color: #b3b3b3">
                    <label id="goods_sale_price_sc" ></label>
                </td>
            </tr>
            <tr  style="height: 35px " class="a1">
                <td class="fitem"><label>实际售价:</label></td>
                <td>
                    <input  type="text" id="goods_fact_price_sc"  class="easyui-numberbox"   data-options="required:true,min:0,precision:2"  style="width:150px;" >

                </td>
            </tr>
            <tr  style="height: 35px " class="a1">
                <td class="fitem"><label>应得积分:</label></td>
                <td>
                    <input  id="goods_score_sc" class="easyui-numberbox" data-options="required:true" style="width:150px;" value="">
                    <label id="pp22" style="width:130px;padding:5px">
                        默认等于实际售价..
                    </label>
                </td>
            </tr>
            <tr style="height: 35px " class="a2">
                <td class="fitem"><label>兑换物品:</label></td>
                <td>
                    <input id="goods_name_ex" data-options="required:true"  class="easyui-textbox" value="" style="width:160px;" >
                </td>
            </tr>
            <tr style="height: 35px " class="a2">
                <td class="fitem"><label>损耗积分:</label></td>
                <td>
                    <input id="exchange_score_ex" data-options="required:true" class="easyui-numberbox" value="" style="width:160px;" >
                </td>
            </tr>

            <tr style="height: 35px ">
                <td class="fitem"><label>备注:</label></td>
                <td>
                    <input id="str_desc_sc" class="easyui-textbox" data-options="multiline:true" value="" style="width:200px;height:70px">
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="dlg-buttonsscoreAddExchange1" style="display: block">
    <a id="confirm23" href="javascript:void(0)" class="easyui-linkbutton c6" iconcls="icon-ok" onclick="scoreAddExchangeSave(0)" style="width: 110px">保存并关闭</a>

    <!--<a id="confirm24" href="javascript:void(0)" class="easyui-linkbutton c6" iconcls="icon-ok" onclick="scoreAddExchangeSave(1)" style="width: 100px">继续保存</a>-->
    <a id="cancel22" href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="javascript:$('#winscoreAddExchange1').dialog('close')" style="width: 90px">取消</a>
</div>