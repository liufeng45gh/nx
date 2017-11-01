<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="wQuestionAnswerList" checkbox="true" fitColumns="false" title="问答管理" actionUrl="wQuestionAnswerController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="问题"  field="problem"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="答案"  field="answer"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="creatTime" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="序号"  field="serialNumber"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="wQuestionAnswerController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="wQuestionAnswerController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="wQuestionAnswerController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="wQuestionAnswerController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="wQuestionAnswerController.do?goUpdate" funname="detail"></t:dgToolBar>
   <%-- <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/ssb/warmline/business/wquestionanswer/wQuestionAnswerList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#wQuestionAnswerListtb").find("input[name='creatTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#wQuestionAnswerListtb").find("input[name='creatTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'wQuestionAnswerController.do?upload', "wQuestionAnswerList");
}

//导出
function ExportXls() {
	JeecgExcelExport("wQuestionAnswerController.do?exportXls","wQuestionAnswerList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("wQuestionAnswerController.do?exportXlsByT","wQuestionAnswerList");
}
 </script>