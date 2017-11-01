<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="wHelpMessageList" checkbox="false" fitColumns="true" title="系统消息" actionUrl="wHelpMessageController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="求助人id"  field="seekHelpPeopleId"  hidden="true"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="求助人"  field="seekHelpPeople"   hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="帮助人id"  field="helpPeopleId"  hidden="true"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="帮助人"  field="helpPeople" hidden="true"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="内容"  field="content"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createDate" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="group"  width="140"></t:dgCol>
   <t:dgCol title="消息类型 "  field="messageType" hidden="true"  dictionary="messageTyp"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="消息状态"  field="messageState"  dictionary="messaState"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="阅读状态 "  field="readingState"  hidden="true"  dictionary="readingSta" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="wHelpMessageController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="wHelpMessageController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="wHelpMessageController.do?goSystemUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="wHelpMessageController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="wHelpMessageController.do?goUpdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/ssb/warmline/business/whelpmessage/wHelpMessageList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#wHelpMessageListtb").find("input[name='createDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#wHelpMessageListtb").find("input[name='createDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'wHelpMessageController.do?upload', "wHelpMessageList");
}

//导出
function ExportXls() {
	JeecgExcelExport("wHelpMessageController.do?exportXls","wHelpMessageList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("wHelpMessageController.do?exportXlsByT","wHelpMessageList");
}
 </script>