<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<t:datagrid name="userList" title="真实姓名" actionUrl="userController.do?realnamedatagrid" 
    fit="true" fitColumns="true" idField="id" queryMode="group" sortName="id" sortOrder="desc">
	<t:dgCol title="common.id" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="昵称" field="userName"   query="true" queryMode="single" ></t:dgCol>
    <%--update-start--Author:zhangguoming  Date:20140827 for：通过用户对象的关联属性值获取组织机构名称（多对多关联）--%>
	<%--<t:dgCol title="common.department" field="TSDepart_id" query="true" replace="${departsReplace}"></t:dgCol>--%>
    <%--update-end--Author:zhangguoming  Date:20140827 for：通过用户对象的关联属性值获取组织机构名称（多对多关联）--%>
	<t:dgCol title="common.real.name" field="realName" query="false"></t:dgCol>
	<t:dgCol title="common.role" field="userKey" ></t:dgCol>
	<t:dgCol title="代理区域" field="territoryName" width="80"></t:dgCol>
	<t:dgCol title="common.createtime" field="createDate"  formatter="yyyy-MM-dd hh:mm:ss" ></t:dgCol>
	<t:dgCol title="头像"  field="headPortrait"  image="true" imageSize="80,80"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="年龄"  field="age"    queryMode="group"  width="60"></t:dgCol>
	<t:dgCol title="证件类型"  field="documentType"   queryMode="group"  width="80"></t:dgCol>
	<t:dgCol title="证件号"  field="identificationNumber" query="true"  queryMode="single"  width="140"></t:dgCol>
	<t:dgCol title="手机号"  field="phone"   query="true" queryMode="single"  width="100"></t:dgCol>
	<t:dgCol title="城市"  field="city"  query="true"  queryMode="single"  width="80"></t:dgCol>
	<t:dgCol title="母语"  field="motherTongue"    queryMode="group"  width="80"></t:dgCol>
	<t:dgCol title="第二语言"  field="secondLanguage"    queryMode="group"  width="80"></t:dgCol>
	<t:dgCol title="接发单数"  field="singular"   queryMode="group"  width="80"></t:dgCol>
	<c:if test="${userKey == null}">
		<t:dgCol title="common.operation" field="opt"></t:dgCol>
		<t:dgDelOpt title="common.delete" url="userController.do?deleUser&id={id}&userName={userName}" />
	</c:if>
	
 	<t:dgToolBar title="用户录入" langArg="common.user" icon="icon-add" url="userController.do?addorupdate" funname="add"></t:dgToolBar> 
	<t:dgToolBar title="用户编辑" langArg="common.user" icon="icon-edit" url="userController.do?addorupdate" funname="update"></t:dgToolBar> 
	<t:dgToolBar title="密码重置" icon="icon-edit" url="userController.do?changepasswordforuser" funname="update"></t:dgToolBar>
	<t:dgToolBar title="锁定用户" icon="icon-edit" url="userController.do?lock&lockvalue=0" funname="lockObj"></t:dgToolBar>
	<t:dgToolBar title="激活用户" icon="icon-edit" url="userController.do?lock&lockvalue=1" funname="unlockObj"></t:dgToolBar>
	
</t:datagrid>
<script type="text/javascript">

function lockObj(title,url, id) {

	gridname=id;
	var rowsData = $('#'+id).datagrid('getSelections');
	if (!rowsData || rowsData.length==0) {
		tip('<t:mutiLang langKey="common.please.select.edit.item"/>');
		return;
	}
		url += '&id='+rowsData[0].id;

	$.dialog.confirm('<t:mutiLang langKey="common.lock.user.tips"/>', function(){
		lockuploadify(url, '&id');
	}, function(){
	});
}
function unlockObj(title,url, id) {

	gridname=id;
	var rowsData = $('#'+id).datagrid('getSelections');
	if (!rowsData || rowsData.length==0) {
		tip('<t:mutiLang langKey="common.please.select.edit.item"/>');
		return;
	}
		url += '&id='+rowsData[0].id;

	$.dialog.confirm('<t:mutiLang langKey="common.unlock.user.tips"/>', function(){
		lockuploadify(url, '&id');
	}, function(){
	});
}


function lockuploadify(url, id) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		url : url,// 请求的action路径
		error : function() {// 请求失败处理函数
		
		
		},
		success : function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
			var msg = d.msg;
				tip(msg);
				reloadTable();
			}
			
		}
	});
}
</script>

<%--update-start--Author:zhangguoming  Date:20140827 for：添加 组织机构查询条件：弹出 选择组织机构列表 相关操作--%>
<%--<a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" onClick="choose_297e201048183a730148183ad85c0001()">选择</a>--%>
<%--<a href="#" class="easyui-linkbutton" plain="true" icon="icon-redo" onClick="clearAll_297e201048183a730148183ad85c0001();">清空</a>--%>
<script type="text/javascript">
//    var windowapi = frameElement.api, W = windowapi.opener;
    function choose_297e201048183a730148183ad85c0001() {
        if (typeof(windowapi) == 'undefined') {
            $.dialog({content: 'url:departController.do?departSelect', zIndex: 2100, title: '<t:mutiLang langKey="common.department.list"/>', lock: true, width: 400, height: 350, opacity: 0.4, button: [
                {name: '<t:mutiLang langKey="common.confirm"/>', callback: clickcallback_297e201048183a730148183ad85c0001, focus: true},
                {name: '<t:mutiLang langKey="common.cancel"/>', callback: function () {
                }}
            ]});
        } else {
            $.dialog({content: 'url:departController.do?departSelect', zIndex: 2100, title: '<t:mutiLang langKey="common.department.list"/>', lock: true, parent: windowapi, width: 400, height: 350, left: '85%', top: '65%', opacity: 0.4, button: [
                {name: '<t:mutiLang langKey="common.confirm"/>', callback: clickcallback_297e201048183a730148183ad85c0001, focus: true},
                {name: '<t:mutiLang langKey="common.cancel"/>', callback: function () {
                }}
            ]});
        }
    }
    function clearAll_297e201048183a730148183ad85c0001() {
        if ($('#departname').length >= 1) {
            $('#departname').val('');
            $('#departname').blur();
        }
        if ($("input[name='departname']").length >= 1) {
            $("input[name='departname']").val('');
            $("input[name='departname']").blur();
        }
        $('#orgIds').val("");
    }
    function clickcallback_297e201048183a730148183ad85c0001() {
        iframe = this.iframe.contentWindow;
        var departname = iframe.getdepartListSelections('text');
        if ($('#departname').length >= 1) {
            $('#departname').val(departname);
            $('#departname').blur();
        }
        if ($("input[name='departname']").length >= 1) {
            $("input[name='departname']").val(departname);
            $("input[name='departname']").blur();
        }
        var id = iframe.getdepartListSelections('id');
        if (id !== undefined && id != "") {
            $('#orgIds').val(id);
            $("input[name='orgIds']").val(id);
        }
    }

	//导入
	function ImportXls() {
		openuploadwin('Excel导入', 'userController.do?upload', "userList");
	}

	//导出
	function ExportXls() {
		JeecgExcelExport("userController.do?exportXls", "userList");
	}

	//模板下载
	function ExportXlsByT() {
		JeecgExcelExport("userController.do?exportXlsByT", "userList");
	}
</script>
<%--update-end--Author:zhangguoming  Date:20140827 for：添加 组织机构查询条件：弹出 选择组织机构列表 相关操作--%>
