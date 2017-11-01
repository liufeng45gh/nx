<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="wPhoneCodeList" checkbox="true" fitColumns="false" title="w_phone_code" actionUrl="wPhoneCodeController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="phone"  field="phone"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="次数"  field="count"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="ip"  field="ip"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="creatDate" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="wPhoneCodeController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="wPhoneCodeController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="wPhoneCodeController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="wPhoneCodeController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="wPhoneCodeController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/ssb/warmline/business/wphonecode/wPhoneCodeList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#wPhoneCodeListtb").find("input[name='creatDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#wPhoneCodeListtb").find("input[name='creatDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'wPhoneCodeController.do?upload', "wPhoneCodeList");
}

//导出
function ExportXls() {
	JeecgExcelExport("wPhoneCodeController.do?exportXls","wPhoneCodeList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("wPhoneCodeController.do?exportXlsByT","wPhoneCodeList");
}
 </script>