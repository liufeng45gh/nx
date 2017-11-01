<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="mobileLoginLogList" checkbox="true" fitColumns="false" title="mobile_login_log" actionUrl="mobileLoginLogController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="用户名"  field="username"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="渠道名称"  field="departName"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="渠道id"  field="departid"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="ip地址"  field="ip"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="登录时间"  field="loginTime" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="来源"  field="userAgent"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="真实姓名"  field="realname"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="agentType"  field="agentType"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="appType"  field="appType"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="mobileLoginLogController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="mobileLoginLogController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="mobileLoginLogController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="mobileLoginLogController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="mobileLoginLogController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/ssb/warmline/business/mobileloginlog/mobileLoginLogList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#mobileLoginLogListtb").find("input[name='loginTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#mobileLoginLogListtb").find("input[name='loginTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'mobileLoginLogController.do?upload', "mobileLoginLogList");
}

//导出
function ExportXls() {
	JeecgExcelExport("mobileLoginLogController.do?exportXls","mobileLoginLogList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("mobileLoginLogController.do?exportXlsByT","mobileLoginLogList");
}
 </script>