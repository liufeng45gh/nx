<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="wOrderRecordList" checkbox="false" fitColumns="false" title="订单日志" actionUrl="wOrderRecordController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="订单id"  field="orderId" hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="订单号"  field="orderNumber"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="订单金额"  field="amount"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="订单交易类型 "  field="orderType"    dictionary="order"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="发布人id"  field="issuerId"  hidden="true"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="发布人"  field="issuer"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="发布人手机号"  field="phone"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="支付状态"  field="buyStatus"  dictionary="buyStatus" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createTime" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="group"  width="140"></t:dgCol>
   <t:dgCol title="描述"  field="description"    queryMode="group"  width="120"></t:dgCol>
 <%--   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="wOrderRecordController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="wOrderRecordController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="wOrderRecordController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="wOrderRecordController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="wOrderRecordController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/ssb/warmline/business/worderrecord/wOrderRecordList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#wOrderRecordListtb").find("input[name='createTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#wOrderRecordListtb").find("input[name='createTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'wOrderRecordController.do?upload', "wOrderRecordList");
}

//导出·
function ExportXls() {
	JeecgExcelExport("wOrderRecordController.do?exportXls","wOrderRecordList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("wOrderRecordController.do?exportXlsByT","wOrderRecordList");
}
 </script>