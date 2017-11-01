<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="wOrderPhotoMainList" checkbox="true" fitColumns="false" title="w_order_photo_main" actionUrl="wOrderPhotoMainController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="照片名称"  field="photoName"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="照片路径"  field="photoUrl"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="订单id"  field="orderId"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="用户id"  field="uid"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createTime" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="照片类型（0 头像 和 1 普通）"  field="photoType"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="wOrderPhotoMainController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="wOrderPhotoMainController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="wOrderPhotoMainController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="wOrderPhotoMainController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="wOrderPhotoMainController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/ssb/warmline/business/worderphotomain/wOrderPhotoMainList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#wOrderPhotoMainListtb").find("input[name='createTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#wOrderPhotoMainListtb").find("input[name='createTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'wOrderPhotoMainController.do?upload', "wOrderPhotoMainList");
}

//导出
function ExportXls() {
	JeecgExcelExport("wOrderPhotoMainController.do?exportXls","wOrderPhotoMainList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("wOrderPhotoMainController.do?exportXlsByT","wOrderPhotoMainList");
}
 </script>