<#assign ctx=Request.request.getContextPath()>
<head>
<link rel="shortcut icon" href="${ctx}/favicon.ico" type="image/x-icon">
<link rel="icon" href="${ctx}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${ctx}/jquery-easyui-1.9.15/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/jquery-easyui-1.9.15/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/jquery-easyui-1.9.15/demo/demo.css">
    <script type="text/javascript" src="${ctx}/jquery-easyui-1.9.15/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/jquery-easyui-1.9.15/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/jquery-easyui-1.9.15/locale/easyui-lang-zh_CN.js" ></script>

<script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
</head>
<style>
    body,div{ font: 14px/1.5 Tahoma,Helvetica,Arial,'宋体',sans-serif;}
/*单选按钮**/
    .choose-box{
        padding-left:10px;
        position:relative;
        display:inline-block;
        height:40px;
        line-height:40px;
    }
    .choose-box input[type="radio"]::before{
        content:'';
        display:inline-block;
        width:10px;
        height:10px;
        border-radius:50%;
        border:1px solid #0069AB;
        margin-right:15px;
        position:absolute;
        top:15px;
        left:0;
    }
    .choose-box input[type="radio"]:checked::before{
        background-clip: content-box;
        background-color:#0069AB;
        width:6px;
        height:6px;
        padding:2px;
    }
    .choose-box input[type="radio"]{
        width:0;
        height:0;
    }
    .cat1{
        font-family: STXinwei;
        color:#FF891F;
    }

</style>
<script type="text/javascript">
    var ctx = window.ctx = "${ctx}";

    //参数n为休眠时间，单位为毫秒:
    function sleep(n) {
        var start = new Date().getTime();
        while (true) {
            if (new Date().getTime() - start > n) {
                break;
            }
        }
    }

    //修改日历框的显示格式
    function myDateformatter(date){
        var y = date.getFullYear();
        var m = date.getMonth()+1;
        var d = date.getDate();
        return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
    }
    function myDateparser(s){
        if (!s) return new Date();
        var ss = (s.split('-'));
        var y = parseInt(ss[0],10);
        var m = parseInt(ss[1],10);
        var d = parseInt(ss[2],10);
        if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
            return new Date(y,m-1,d);
        } else {
            return new Date();
        }
    }

    Date.prototype.format = function (format) {
        var o = {
            "M+": this.getMonth() + 1, // month
            "d+": this.getDate(), // day
            "h+": this.getHours(), // hour
            "m+": this.getMinutes(), // minute
            "s+": this.getSeconds(), // second
            "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
            "S": this.getMilliseconds()
            // millisecond
        }

        if (/(y+)/.test(format))
            format = format.replace(RegExp.$1, (this.getFullYear() + "")
                    .substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(format))
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        return format;
    }
    function formatDateYYYYMMDDhh(value) {
        value = value.slice(0, value.indexOf("."));//将最后一个.和后面的内容截掉
        var date = new Date(value);
        return date.format("yyyy-MM-dd hh:mm");
    }
    function formatDateYYYYMMDD(value) {
        if (value == null || value == '') {
            return '';
        }
        var dt;
        value = value.slice(0, value.indexOf("."));//将最后一个.和后面的内容截掉
        if (value instanceof Date) {
            dt = value;
        } else {
            dt = new Date(value);
        }
        return dt.format("yyyy-MM-dd");
    }

    $.extend($.fn.validatebox.defaults.rules, {
        phoneNum: { //验证手机号
            validator: function(value, param){
                return /^1[3-8]+\d{9}$/.test(value);
            },
            message: '请输入正确的手机号码。'
        },
        telNum:{ //既验证手机号，又验证座机号
            validator: function(value, param){
                return /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^(()|(\d{3}\-))?(1[358]\d{9})$)/.test(value);
            },
            message: '请输入正确的手机号码。'
        }
    });


    function alert_noClose(title,msg){
        $.messager.alert(title, msg, 'info');
    }
    function alert_autoClose(title,msg){
        $.messager.show({
            title:title,
            msg:msg,
            timeout:4000,
            showType:'slide'
        });
    }
    function alert_autoCloseError(title,msg) {
        $.messager.alert(title, msg, 'error');
    }

    function getCategoryName(v){
       $.each(categoryDatasAll, function(i,item){
            if(item.categoryId==v) {
                v = item.categoryName;
                return ;
            }
           // return v;
        });
        return v;
    }
    var categoryDatas = [];
    var categoryDatasAll = [];
    function categorys(callback) {
        categoryDatas = [];
        categoryDatasAll = [];
        $.ajax({
            type: "post",
            url: ctx+'/mb/category/categorys',
            async: true, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
            contentType: 'application/json',
            dataType: 'JSON',
            //data: JSON.stringify(obj),
            success: function(o) {
                //console.log(obj.data)

                if (o.success == true) {
                    var data = o.data;
                    $.each(data, function(i,item){
                        categoryDatasAll.push({"categoryId": item.categoryId, "categoryName": item.categoryName});
                        //alert(item.activeFlag+"ccc");
                        //alert(item.categoryId);
                        if(item.activeFlag==1) {
                            categoryDatas.push({"categoryId": item.categoryId, "categoryName": item.categoryName});
                        }
                    });
                    callback(categoryDatas);
                }
            }
        });
    }

    function doHandleYYYYMM() {
        var myDate = new Date();
        var tYear = myDate.getFullYear();
        var tMonth = myDate.getMonth();
        var m = tMonth + 1;
        if (m.toString().length == 1) {
            m = "0" + m;
        }
        return tYear +""+ m;
    }
    function doHandleYear(tYear) {
        var myDate = new Date();
        var tYear = myDate.getFullYear();
        return tYear;
    }
    function doHandleMonth() {
        var myDate = new Date();
        var tMonth = myDate.getMonth();
        var m = tMonth + 1;
        if (m.toString().length == 1) {
            m = "0" + m;
        }
        return m;
    }
    function formatDesc(v,row,index) {
        var v2 = v ;
        if(v!=null&&v!=""&&v.length>5){
            v2 =v.substring(0,5)+"...";
        }
        if(v2== null || v2=="null") v2 = "";
        return '<label title="'+v+'" class="easyui-tooltip" style="width:130px;padding:5px" >'+v2+'</label>';
    }

    function setCurrentShop(rec) {
        var shopId =  rec.cshopId;
        $.ajax({
            type: "post",
            url: ctx+'/mb/shop/setCurrentShop?shopId='+shopId,
            async: true,
            contentType: 'application/json',
            dataType: 'JSON',
            // data: JSON.stringify(obj),
            success: function(o) {
                if (o.success == true) {
                    //window.location.reload();
                    location.reload();
                }
            }
        });
    };

    function roleTypeName(v,row,index) {
        if(v==1) return '店铺管理员' ;//'0 普通员工，1 店铺管理员'
        if(v==0) return '普通员工' ;
        return v;
    }
    /*
    var token = "";
    function  getToken() {
        $.ajax({
            type: "post",
            url: ctx+'/min/getToken',
            async: true, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
            contentType: 'application/json',
            dataType: 'JSON',
            success: function(o) {
                if (o.success == true) {
                    token = o.data;
                }else{
                    token = "";
                }
            }
        });
    }*/

    var wait = 50;
    function timeOut() {
        // wait = 50;
        if (wait == 0) {
            $('#verificationid').html('<a href="javascript:void(0)" onclick="sendSms()" style="width: 120px">发送验证码</a>');
        } else {
            $('#verificationid').html('验证码发送成功,<font color="#a52a2a">'+wait+'</font>分钟后到期');
            setTimeout(function () {
                wait--;
                timeOut();
            }, 60000)
        }
    }

</script>
