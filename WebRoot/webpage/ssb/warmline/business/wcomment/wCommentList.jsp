<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="wCommentList" checkbox="true" fitColumns="false" title="评论表" actionUrl="wCommentController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="评星个数"  field="commentStar"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="评论时间"  field="commentTime" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="评论内容"  field="content"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="发布人"  field="issuer"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="发布人id"  field="issuerId"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="wCommentController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="wCommentController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="wCommentController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="wCommentController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="wCommentController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/ssb/warmline/business/wcomment/wCommentList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#wCommentListtb").find("input[name='commentTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#wCommentListtb").find("input[name='commentTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'wCommentController.do?upload', "wCommentList");
}

//导出
function ExportXls() {
	JeecgExcelExport("wCommentController.do?exportXls","wCommentList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("wCommentController.do?exportXlsByT","wCommentList");
}
 </script>