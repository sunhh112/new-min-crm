
<tr style="">
    <td class="fitem"><label>登录手机号:</label></td>
    <td>
        <input id="account_tel_u" data-options="required:true,prompt:'',validType:'telNum'" style="width:200px;" class="easyui-textbox"  />
    </td>
</tr>
<tr id="verificationtd1" style="height: 40px ">
    <td class="fitem"><label>动态验证码:</label></td>
    <td  valign="middle">
        <input id="verifyCode" data-options=""  style="width:140px;" class="easyui-textbox"  />
        <div style="display:inline;vertical-align: middle;">
            <img onclick="javascript:getvCode()" id="verifyimg" style="" height="23" width="72"/>
            <a href="javascript:void(0)"  onclick="getvCode()" style="width: 120px">换一张</a></div>
    </td>
</tr>
<tr id="verificationtd2" style=" ">
    <td class="fitem"><label>短信验证码:</label></td>
    <td>
        <input id="smsVerifyCode" data-options="" style="width:140px;" class="easyui-textbox"  />
        <div style="display:inline;" id="verificationid"> <a  href="javascript:void(0)" id="smsid"  onclick="sendSms()" style="width: 120px">发送验证码</a></div>
    </td>
</tr>

<script type="text/javascript">

    $('#smsVerifyCode').textbox({});
    $('#verifyCode').textbox({});
    $('#account_tel_u').textbox({});

    //getToken();
    var wait = 50;
    function timeOut() {
        if (wait == 0) {
            $('#verificationid').html('<a href="javascript:void(0)" id="smsid"  onclick="sendSms()" style="width: 120px">发送验证码</a>');
        }
        else {
            $('#verificationid').html('验证码发送成功,<font color="#a52a2a">'+wait+'</font>分钟后到期');
            setTimeout(function () {
                wait--;
                timeOut();
            }, 60000)
        }
    }
    /**
     *说明
     引入相关页面的
     手机号 account_tel_u
     动态验证码 verifyCode
     短信验证码 smsVerifyCode
     */

    function sendSms() {
        var vtype = "${vtype}";
        var tel = $('#account_tel_u').textbox('getValue');
        var verifyCode = $('#verifyCode').textbox('getValue');
        if(tel==""){
            alert_autoCloseError("短信验证码","请输入手机号");
            return;
        }
        if(verifyCode==""){
            alert_autoCloseError("短信验证码","请输入动态验证码");
            return;
        }
        $('#verificationid').html('正在发送....');
        var obj = {}; //声明一个对象
        obj.minute = wait;
        obj.tel = tel;
        obj.verifyCode = verifyCode;
        obj.vtype = vtype;
        $.ajax({
            type: "post",
            url: ctx+'/min/sendSms',
            async: true, //如果是在ajax方法外加载表格数据，必须【同步】方式提交
            contentType: 'application/json',
            dataType: 'JSON',
            data: JSON.stringify(obj),
            success: function(o) {
                if (o.success == true){
                    //  token = o.data;
                    if(o.code=="2")
                        $('#verificationid').html('验证码已在<font color="#a52a2a">'+o.data+'</font>成功发送，请查看手机短信')
                    else
                        timeOut();
                }else{
                    alert_autoCloseError("发送短信",o.message);
                    wait = 50;
                    $('#verificationid').html('<a href="javascript:void(0)" id="smsid" iconcls="icon-ok" onclick="sendSms()" style="width: 120px">发送验证码</a>');
                }
            }
        });
    }
    /**
     * 获取验证码
     * 将验证码写到login.html页面的id = verifyimg 的地方
     */
    function getvCode() {
        var getTimestamp = new Date().getTime();
        document.getElementById("verifyimg").src = ctx+"/min/verifyCode?timestamp="+getTimestamp;
    }
    getvCode();
</script>

