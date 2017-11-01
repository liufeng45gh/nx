<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="wJpushList" checkbox="false" fitColumns="false" title="消息推送" actionUrl="wJpushController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
  <%--  <t:dgCol title="内容"  field="content"    queryMode="group"  width="120"></t:dgCol> --%>
 <%--   <t:dgCol title="发送状态"  field="sendStatus"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="页面去向"  field="pageWhereabouts"    queryMode="group"  width="120"></t:dgCol> --%>
   <t:dgCol title="主标题"  field="mainTitle"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="标题"  field="title"    queryMode="group"  width="120"></t:dgCol>
  <%--  <t:dgCol title="照片路径"  field="photoPath"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="editLink"  field="editLink"    queryMode="group"  width="120"></t:dgCol> --%>
   <t:dgCol title="推送内容"  field="text"    queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="创建时间"  field="createTime" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt"></t:dgCol>
   <t:dgFunOpt funname="pushMessage(id)" title="推送" ></t:dgFunOpt>
    <t:dgFunOpt funname="pushMan(id)" operationCode="pushMan"  title="选择推送人"   ></t:dgFunOpt>
   <t:dgDelOpt title="删除" url="wJpushController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="wJpushController.do?goAdd" funname="add"></t:dgToolBar>
   <%-- <t:dgToolBar title="编辑" icon="icon-edit" url="wJpushController.do?goUpdate" funname="update"></t:dgToolBar> --%>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="wJpushController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="wJpushController.do?goUpdate" funname="detail"></t:dgToolBar>
   <%-- <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/ssb/warmline/business/wjpush/wJpushList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#wJpushListtb").find("input[name='createTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#wJpushListtb").find("input[name='createTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });

//消息推送
function pushMessage(pushId,li){
	$(".loadBg").show();
	$(".loadWrap").show();
		$.ajax({
	 		url:"wJpushController.do?doPush&pushId="+pushId,
	 		dataType:'json',
	 		type: "POST",
	 		success:function(data){
	 			$(".loadBg").hide();
	 			$(".loadWrap").hide();
	 			$.messager.alert('提示',data.obj);
	 		}
	 	});
	}


function pushMan(id) {
	 $.dialog({
			content:'url:wJpushController.do?pushMan&jpushId=' + id, 
			zIndex: 2000, 
			title: '选择推送人', 
			lock: true, 
			width: 600, 
			height: 600, 
			opacity: 0.4, 
			close:function() {
				 $(this).dialog("close");
			},
			button: [{name: '推送', callback: choiceConnectSingle},
			         {name:'<t:mutiLang langKey="common.cancel"/>', callback: function (){}}]
		}); 
	}
function choiceConnectSingle(){
	var iframe = this.iframe.contentWindow;
	var phones = iframe.$("#currentId").val();
	var jpushId = iframe.$("#jpushId").val();
	if(phones == ""){
		$.messager.alert('提示','请选择推送人');
	}else{
	$.ajax({
 		url:"wJpushController.do?doPushAppoint&phones="+phones+"&jpushId="+jpushId,
 		dataType:'json',
 		type: "POST",
 		success:function(data){
 			if(data.success == false){
 				$.messager.alert('提示',data.msg);
 			}else{
 				window.location.reload();
 			}
 		}
 	});
	}
}
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'wJpushController.do?upload', "wJpushList");
}

//导出
function ExportXls() {
	JeecgExcelExport("wJpushController.do?exportXls","wJpushList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("wJpushController.do?exportXlsByT","wJpushList");
}
 </script>