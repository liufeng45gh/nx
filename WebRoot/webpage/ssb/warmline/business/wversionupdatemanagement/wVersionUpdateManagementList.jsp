<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="wVersionUpdateManagementList" checkbox="true" fitColumns="false" title="版本更新管理" actionUrl="wVersionUpdateManagementController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="版本名称"  field="versionName"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="版本号"  field="versionNumber"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="版本描述"  field="versionDescription"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="下载url"  field="uploadUrl"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="下载地址"  field="downloadUrl"  hidden="true"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否是当前字段"  field="currentField"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="appId"  field="appid"  hidden="true"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="wVersionUpdateManagementController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="wVersionUpdateManagementController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="wVersionUpdateManagementController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="wVersionUpdateManagementController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="wVersionUpdateManagementController.do?goUpdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/ssb/warmline/business/wversionupdatemanagement/wVersionUpdateManagementList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#wVersionUpdateManagementListtb").find("input[name='createDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#wVersionUpdateManagementListtb").find("input[name='createDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'wVersionUpdateManagementController.do?upload', "wVersionUpdateManagementList");
}

//导出
function ExportXls() {
	JeecgExcelExport("wVersionUpdateManagementController.do?exportXls","wVersionUpdateManagementList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("wVersionUpdateManagementController.do?exportXlsByT","wVersionUpdateManagementList");
}
 </script>