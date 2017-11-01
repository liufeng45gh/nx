<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="fPushClientList" checkbox="true" fitColumns="false" title="f_push_client" actionUrl="fPushClientController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="用户id"  field="uid"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="用户名"  field="username"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="渠道id"  field="departId"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="渠道名称"  field="departName"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="令牌"  field="token"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="客户端id"  field="clientId"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="APPID"  field="appId"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="平台标识"  field="appType"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="devicetoken"  field="devicetoken"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="fPushClientController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="fPushClientController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="fPushClientController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="fPushClientController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="fPushClientController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/ssb/warmline/business/fpushclient/fPushClientList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#fPushClientListtb").find("input[name='createTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#fPushClientListtb").find("input[name='createTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'fPushClientController.do?upload', "fPushClientList");
}

//导出
function ExportXls() {
	JeecgExcelExport("fPushClientController.do?exportXls","fPushClientList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("fPushClientController.do?exportXlsByT","fPushClientList");
}
 </script>