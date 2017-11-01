<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="wCommissionList" checkbox="false" fitColumns="true" title="佣金" actionUrl="wCommissionController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="用户id"  field="userId" hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="用户名称"  field="userName"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="手机号"  field="phone"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="金额"  field="amount"    queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="订单号"  field="orderNumber" query="true"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="接单人"  field="orderPersonName"   queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="接单人id"  field="orderPersonId"  hidden="true" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="佣金类别"  field="commissionType"  dictionary="commsType" queryMode="single"  width="120"></t:dgCol>
   	<t:dgCol title="订单类型"  field="seekStatus" dictionary="order" queryMode="single"  width="120"></t:dgCol>
               
   <%-- <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="wCommissionController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="wCommissionController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="wCommissionController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="wCommissionController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="wCommissionController.do?goUpdate" funname="detail"></t:dgToolBar>
   <%-- <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar> --%>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
<%--    <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/ssb/warmline/business/wcommission/wCommissionList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#wCommissionListtb").find("input[name='createTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#wCommissionListtb").find("input[name='createTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'wCommissionController.do?upload', "wCommissionList");
}

//导出
function ExportXls() {
	JeecgExcelExport("wCommissionController.do?exportXls","wCommissionList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("wCommissionController.do?exportXlsByT","wCommissionList");
}
 </script>