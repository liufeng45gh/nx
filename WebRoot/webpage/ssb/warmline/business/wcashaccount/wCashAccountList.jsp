<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="wCashAccountList" checkbox="true" fitColumns="false" title="w_cash_account" actionUrl="wCashAccountController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="用户id"  field="userId"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="用户名"  field="userName"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="支付宝状态 0 未绑定  1绑定"  field="alipayBindingState"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="支付宝账户"  field="alipayAccount"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="微信绑定状态  0 未绑定  1绑定"  field="wechatState"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="微信账户"  field="wechatAccount"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="银行卡绑定状态 0 未绑定 1绑定"  field="bankCardState"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="持卡人"  field="cardholder"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="银行卡号"  field="bankCard"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="银行卡类型"  field="cardType"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="银行卡预留手机号"  field="reservePhone"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="wCashAccountController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="wCashAccountController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="wCashAccountController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="wCashAccountController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="wCashAccountController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/ssb/warmline/business/wcashaccount/wCashAccountList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'wCashAccountController.do?upload', "wCashAccountList");
}

//导出
function ExportXls() {
	JeecgExcelExport("wCashAccountController.do?exportXls","wCashAccountList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("wCashAccountController.do?exportXlsByT","wCashAccountList");
}
 </script>