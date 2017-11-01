<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="wCapitalList" checkbox="false" fitColumns="true" title="提现管理" actionUrl="wCapitalController.do?withdDatagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="提现发起人"  field="userName"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="提现时间"  field="tradeTime" formatter="yyyy-MM-dd hh:mm:ss"  query="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="提现金额"  field="amout" width="120"></t:dgCol>
    <t:dgCol title="真实用户"  field="realName"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="账户类型"  field="transferType"    dictionary="transType" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="收款账户"  field="intoAccount"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="审核时间"  field="approvalTime" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="审批人"  field="approvalName"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="审批状态"  field="approvalType"  dictionary="approvtype"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remarks"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
    <t:dgFunOpt funname="examine(id)" operationCode="examine"  title="提现审核"  ></t:dgFunOpt>
   <%-- <t:dgDelOpt title="删除" url="wCapitalController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="wCapitalController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="wCapitalController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="wCapitalController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="wCapitalController.do?goUpdates" funname="detail"></t:dgToolBar>
  <%--  <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar> --%>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/ssb/warmline/business/wcapital/wCapitalList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#wCapitalListtb").find("input[name='tradeTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd hh:mm:ss'});});
 			$("#wCapitalListtb").find("input[name='tradeTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd hh:mm:ss'});});
 			$("#wCapitalListtb").find("input[name='approvalTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd hh:mm:ss'});});
 			$("#wCapitalListtb").find("input[name='approvalTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd hh:mm:ss'});});
 });
 
 
//提现审核
 function examine(id){
	 	createwindow('提现审核', 'wCapitalController.do?invoice&wCapitalId=' + id);
 } 
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'wCapitalController.do?upload', "wCapitalList");
}

//导出
function ExportXls() {
	JeecgExcelExport("wCapitalController.do?exportXls","wCapitalList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("wCapitalController.do?exportXlsByT","wCapitalList");
}
 </script>