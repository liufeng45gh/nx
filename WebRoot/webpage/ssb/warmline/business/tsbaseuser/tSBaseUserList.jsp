<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="tSBaseUserList" checkbox="true" fitColumns="false" title="渠道商" actionUrl="tSBaseUserController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="activitisync"  field="activitisync"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="browser"  field="browser"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="password"  field="password"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="realname"  field="realname"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="signature"  field="signature"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="status"  field="status"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="userkey"  field="userkey"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="username"  field="username"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="departid"  field="departid"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="删除状态"  field="deleteFlag"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tSBaseUserController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="tSBaseUserController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tSBaseUserController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="tSBaseUserController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tSBaseUserController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/qsb/business/tsbaseuser/tSBaseUserList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'tSBaseUserController.do?upload', "tSBaseUserList");
}

//导出
function ExportXls() {
	JeecgExcelExport("tSBaseUserController.do?exportXls","tSBaseUserList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("tSBaseUserController.do?exportXlsByT","tSBaseUserList");
}
 </script>