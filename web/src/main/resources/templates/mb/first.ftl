
<style  type="text/css">

</style>


<script type="text/javascript">
    var var_title ="首页";

    function initp() {

        $("#tablefirst").empty();
        $.ajax({
            type: "post",
            url: ctx+'/mb/customer/firstpage',
            async: true, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
            contentType: 'application/json',
            dataType: 'JSON',
           // data: JSON.stringify(obj),
            success: function(obj) {
                //alert(obj.success);
                if (obj.success == true) {
                    var data = obj.data;
                    for (var i = 0; i < data.length; i++) {
                        var ds = data[i];
                        var tr = "<tr style='width: 100%;padding-bottom: 20px'>";
                        for (var ii = 0; ii < ds.length; ii++) {
                            //alert(ds[ii].shop_id + " " + ds[ii].customernum);
                            var shopname = getshopName(ds[ii].shop_id);
                            var customernum = ds[ii].customernum;
                            if(customernum==undefined)
                                customernum = 0;
                            var goodsnum = ds[ii].goodsnum;
                            if(goodsnum==undefined)
                                goodsnum = 0;
                            var ordernum =ds[ii].ordernum;
                            if(ds[ii].ordernum==undefined)
                                ordernum = 0;
                            tr = tr + "<td style='padding-left: 20px;' style='' align='center'>"+
                            "<div class='ppp easyui-panel' title='"+shopname+"' style='width:400px;height:200px;padding:10px;'>"+
                            "<ul><li>会员总量:"+customernum+"人.</li>"+
                            "<li>商品总量:"+goodsnum+"个.</li>"+
                            "<li>订单总量"+ordernum+"个.</li>"+
                            "</ul></div></td>";
                       }
                       tr = tr + "</tr>";
                        $("#tablefirst").append(tr);
                       // $("#p1").panel();
                        $(".ppp").panel();
                    }
                }
            }
        });
    };

    initp();


</script>

<table id="tablefirst" style="padding-top: 20px;width: 80% ">


</table>






