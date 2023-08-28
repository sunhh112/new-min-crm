<#assign ctx=Request.request.getContextPath()>
<head>

</head>
<style>


</style>
<script type="text/javascript">

    function getAccountName(value){
        //var accountName = "";
        <#if shopAccountMaps?exists>
            <#assign keys=shopAccountMaps?keys/>
            <#list keys as key>
                if(value = '${key}') {
                    return '${shopAccountMaps[key]!""}';
                }
            </#list>
        </#if>
        return value ;
    }
//分类集合
    function categoryList(){
        var categoryDatas = [];
        <#if mbCategoryList?exists>
            <#list mbCategoryList as category>
                categoryDatas.push({ "categoryId": '${category.categoryId}', "categoryName": '${category.categoryName}' });
            </#list>
        </#if>
        return categoryDatas;
    }

////传入分类id 返回分类的名称  弃用 有getCategoryName代替
    function getCategoryNameDel(v){
        <#if mbCategoryList?exists>
            <#list mbCategoryList as category>
                if(v == ""+${category.categoryId}) {
                    // alert(v);
                    return '${category.categoryName!""}';
                }
            </#list>
        </#if>
        return v ;
    }

    var shopDatas = [];
    function shoplists() {
        $('#shoplists').combobox({
            valueField:'cshopId',
            textField:'cshopName',
            required:true,
            prompt:'请选择门店',
            onClick: setCurrentShop,
            data:shopDatas
        });

    }

    function roleTypeName(v,row,index) {
        if(v==1) return '店铺管理员' ;//'0 普通员工，1 店铺管理员'
        if(v==0) return '普通员工' ;
        return v;
    }



</script>
