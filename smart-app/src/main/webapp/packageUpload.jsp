<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 15-1-20
  Time: 下午2:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link rel="stylesheet" type="text/css" href="${ctx}/static/styles/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/styles/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/styles/demo/demo.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/styles/uploadify.css">

<script type="text/javascript" src="${ctx}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/jslib/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/static/jslib/easyui-lang-zh_CN.js"></script>
<%--<script type="text/javascript" src="${ctx}/static/js/packageInfo.js"></script>--%>
<script type="text/javascript" src="${ctx}/static/js/jsUtil.js"></script>
<script type="text/javascript" src="${ctx}/static/js/datagrid.js"></script>



<script type="text/javascript" src="${ctx}/static/jslib/jquery.uploadify-3.1.min.js"></script>


<script type="text/javascript">
    $(function() {
        $("#multiple_file_upload").uploadify({
            'height'        : 27,
            'width'         : 80,
            'buttonText'    : '浏 览',
            'swf'           : '${ctx}/static/image/uploadify.swf',
            'uploader'      : '${ctx}/packageInfoController/uploadifyApk.action',
            'auto'          : false,
            'fileTypeExts'  : '*.apk',
            'fileDesc'       : '支持格式:apk.',
            'queueSizeLimit' :1,
            'queueID'        : 'fileQueue',
//            'formData'      : {'userName':'','content':''},
            onUploadStart : function(file) {

                var isUpload = $('#fm').form('validate');
                if(!isUpload)
                    $('#' + file.id).remove();
            },
            onSelect : function(fileObj) {

                $("#showApkInfo").show();

                var fileAllName=fileObj.name;
                var fileName = fileAllName.substring(0,fileAllName.indexOf("_"));
                var versionCode = fileAllName.substring(fileAllName.indexOf("_")+1,fileAllName.lastIndexOf("."));


                var fileSize = 0;
                if (fileObj.size > 1024 * 1024)
                    fileSize = (Math.round(fileObj.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
                else
                    fileSize = (Math.round(fileObj.size * 100 / 1024) / 100).toString() + 'KB';

                $("#packageName").val(fileName);
                $("#packageSize").val(fileObj.size);
                $("#packageSizeShow").val(fileSize);
                $("#packageType").val(fileObj.type);
                $("#versionCode").val(versionCode);

                $.parser.parse($("#showApkInfo"));

            },
            onUploadSuccess : savePackageInfo

        });
    });

    /**
     * 上传升级包
     */
    function upLoadPackageInfo(){
//    $('#uploadPackageInfo').dialog('open').dialog('setTitle','添加升级包');
        $('#uploadApk').dialog('open').dialog('setTitle','添加升级包');
    }


   var  savePackageInfo = function(file,data,response){
       var path = eval('('+data+')');
       $("#packageLocationPrefix").val(path.obj.locationPrefix);
       $("#packageLocationPath").val(path.obj.locationPath);
       $("#sign").val(path.obj.sign);
        $('#fm').form('submit',{
            url:  getWebName()+'/packageInfoController/savePackageInfo.action',
            onSubmit: function(){
                return $(this).form('validate');
            },
            success: function(result){
//                alert(result);
                var result = eval('('+result+')');
                if (!result.success){
                    $.messager.show({
                        title: 'ERROR',
                        msg: result.msg
                    });
                } else {
                    $.messager.show({
                        title: 'SUCCESS',
                        msg: result.msg
                    });
                    $('#uploadApk').dialog('close');		// close the dialog
                    $('#dg').datagrid('reload');	        // reload the user data
                }
            }
        });
    }


    function rowformater(value,row,index)
    {
        return "<a href='#' mce_href='#' onclick='destroyPackageInfo(\""+row.id+"\")'>删除</a>";
    }

    function destroyPackageInfo(id){

        if (id){
            $.messager.confirm('确认','亲，确认删除记录吗?',function(r){
                if (r){
                    $.post(getWebName()+'/packageInfoController/deletePackageInfo.action',{id:id},function(result){
                        if (result.success){
                            $('#dg').datagrid('reload');	// reload the user data
                            $.messager.show({	// show error message
                                title: 'Success',
                                msg: result.msg
                            });
                        } else {
                            $.messager.show({	// show error message
                                title: 'Error',
                                msg: result.msg
                            });
                        }
                    },'json');
                }
            });
        }else{
            $.messager.show({
                title:'Message',
                msg:'请在记录中选择一条记录'
            });
        }


    }




    function formatPackageType(val,row){
        if(val == 'full'){
            return "完整升级";
        }else if(val == 'increment'){
            return "增量升级";
        }else{
            return "完整升级";
        }
    }

    function formatPackageInfoAuditStatus(val,row){
        if(val == 1){
            return "待审核";
        }else if(val == 2){
            return "审核通过";
        }else if(val == 3){
            return "审核失败";
        }
    }

    function formatPackageSize(val,row){
        var fileSize = 0;
        if (val > 1024 * 1024)
            fileSize = (Math.round(val * 100 / (1024 * 1024)) / 100).toString() + 'MB';
        else
            fileSize = (Math.round(val * 100 / 1024) / 100).toString() + 'KB';
        return fileSize;
    }

    function formatUpgradeState(val,row){
        if(val == 'none'){
            return "未指定";
        }else if(val == 'mandatory'){
            return "强制升级";
        }else if(val == 'select'){
            return "自选择";
        }else{
            return "强制升级";
        }
    }

    function formatPackageStatus(val,row){
        if(val == 'test'){
            return "测试状态";
        }else if(val == 'release'){
            return "发布状态";
        }else{
            return "发布状态";
        }
    }



</script>

<div id="toolbar">


    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upLoadPackageInfo()">上传升级包</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="aduitArtist(1)">审核不通过</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="aduitArtist(3)">审核通过</a>
    <%--<div id="mySearch" style="float:right">--%>
        <%--<label>搜索：</label><input class="easyui-searchbox" data-options="prompt:'请输入影人名字',searcher:doSearch" style="width:300px;">--%>
    <%--</div>--%>


</div>
<script>
    function doSearch(value){
        $('#dg').datagrid('load', { "searchValue": value });
//        alert('You input: ' + value);
    }
</script>



<table id="dg"  cellspacing="0" cellpadding="0" toolbar="#toolbar" fitColumns="false" url="${ctx}/packageInfoController/findPackageInfoList.action?component=datagrid">
    <thead>
    <tr>
        <th field="id" width="20">id</th>
        <th field="versionName" width="100">版本名称</th>
        <th field="packageName" width="60">软件标识</th>
        <th field="versionCode" width="50">版本号</th>
        <th field="packageType" width="60" formatter="formatPackageType">包类型</th>
        <th field="packageSize" width="60" formatter="formatPackageSize">大小</th>
        <th field="packageStatus" width="60" formatter="formatPackageStatus">包状态</th>
        <th field="upgradeState" width="60" formatter="formatUpgradeState">升级类型</th>
        <th field="packageLocationPrefix" width="100">升级地址前缀</th>
        <th field="packageLocationPath" width="100">升级地址路径</th>
        <th field="packageDesc" width="200">软件描述</th>
        <th field="sign" width="200">签名</th>
        <th field="createTime" width="150" formatter="formatDateTime">创建时间</th>
        <th field="auditStatus" width="50" formatter="formatPackageInfoAuditStatus">审核状态</th>
        <th data-options="field:'opt',width:80,formatter:  rowformater">操作</th>
        <!--<th field="ck" checkbox="true"></th>-->

    </tr>
    </thead>
</table>

<div id="uploadApk" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px" closed="true">
    <form id="fm" method="post"  novalidate>
        <input type="file" name="uploadify" id="multiple_file_upload" />
        <div id="fileQueue"></div>
        <hr>
        <div id="showApkInfo" style=" display:none">
            <table>
                <tr>
                    <td align="right">软件标识:</td>
                    <td><input id="packageName" name="packageName" class="easyui-validatebox textbox" required="true"></td>

                    <td align="right">版本号:</td>
                    <td><input id="versionCode" name="versionCode" class="easyui-validatebox textbox" required="true"></td>
                </tr>

                <tr>
                    <td align="right">显示名称:</td>
                    <td><input id="versionName" name="versionName" class="easyui-validatebox textbox" required="true"></td>

                    <td align="right">签名:</td>
                    <td><input id="sign" name="sign" class="easyui-validatebox textbox" readonly="true" style="border:0px;"></td>
                </tr>

                <tr>
                    <td align="right">升级类型:</td>
                    <td>
                        <select class="easyui-combobox textbox" id="packageType" name="packageType" data-options="editable:false" required="true"  style="width:100px;height: 20">
                            <option value="full" selected>完整升级</option>
                            <option value="increment">增量升级</option>
                        </select>
                    </td>

                    <td align="right">软件包大小:</td>
                    <td><input id="packageSizeShow" name="packageSizeShow" class="easyui-validatebox textbox" multiple="true" readonly="true"></td>

                </tr>

                <tr>
                    <td align="right">软件包状态:</td>
                    <td>
                        <select class="easyui-combobox" id="packageStatus" name="packageStatus" data-options="editable:false" required="true"  style="width:100px;height: 20">
                            <option value="test">测试状态</option>
                            <option value="release" selected>发布状态</option>
                        </select>
                    </td>

                    <td align="right">升级类型:</td>
                    <td>
                        <select class="easyui-combobox" id="upgradeState" name="upgradeState" data-options="editable:false" required="true"  style="width:100px;height: 20">
                            <option value="none">未指定</option>
                            <option value="mandatory" selected>强制升级</option>
                            <option value="select">自选择</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td align="right">描述:</td>
                    <td colspan="3">
                        <%--<input id="packageDesc" name="packageDesc" class="easyui-textbox" data-options="multiline:true" style="width:350px;height:100px">--%>
                        <%--<input  name="packageDesc" class="textbox" data-options="multiline:true" style="width:300px;height:100px">--%>
                            <textarea  id="packageDesc" name="packageDesc"  warp="virtual" style="width:350px;height:100px"></textarea>
                    </td>

                </tr>
                <div style=" display:none">
                    <input id="packageSize" name="packageSize" class="easyui-validatebox textbox" multiple="true" readonly="true">
                    <input id="packageLocationPrefix" name="packageLocationPrefix" class="easyui-validatebox" required="true">
                    <input id="packageLocationPath" name="packageLocationPath" class="easyui-validatebox" required="true">
                </div>

            </table>
        </div>

        <a href="javascript:void(0)" onclick="$('#multiple_file_upload').uploadify('upload','*')" class="easyui-linkbutton c6" iconCls="icon-ok" style="width:90px">开始上传</a>
        <a href="javascript:void(0)" onclick="$('#multiple_file_upload').uploadify('cancel', '*')" class="easyui-linkbutton c6" iconCls="icon-cancel" style="width:90px">取消上传</a>

    </form>

</div>




<style type="text/css">
    #fm{
        margin:0;
        padding:10px 30px;
    }
    .ftitle{
        font-size:14px;
        font-weight:bold;
        padding:5px 0;
        margin-bottom:10px;
        border-bottom:1px solid #ccc;
    }
    .fitem{
        margin-bottom:5px;
    }
    .fitem label{
        display:inline-block;
        width:80px;
    }
    .fitem input{
        width:160px;
    }
</style>