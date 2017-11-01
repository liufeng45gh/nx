<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="wTerritoryCategoryList" checkbox="true" fitColumns="false" title="类目区域中间表" actionUrl="wTerritoryCategoryController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="分类名称"  field="categoryName"   query="true"   width="120"></t:dgCol>
   <t:dgCol title="区域名称"  field="territoryName"  query="true"   width="120"></t:dgCol>
   <%-- <t:dgCol title="分类id"  field="categoryId"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="区域id"  field="territoryId"    queryMode="group"  width="120"></t:dgCol> --%>
   <t:dgCol title="价钱范围"  field="price"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol  title="分类图片"  field="categoryImage"   image="true" imageSize="80,80"  queryMode="single" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="wTerritoryCategoryController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="wTerritoryCategoryController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="wTerritoryCategoryController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="wTerritoryCategoryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
  <%--  <t:dgToolBar title="查看" icon="icon-search" url="wTerritoryCategoryController.do?goUpdate" funname="detail"></t:dgToolBar> --%>
  <%--  <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/ssb/warmline/business/territoryandcategory/wTerritoryCategoryList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'wTerritoryCategoryController.do?upload', "wTerritoryCategoryList");
}

//导出
function ExportXls() {
	JeecgExcelExport("wTerritoryCategoryController.do?exportXls","wTerritoryCategoryList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("wTerritoryCategoryController.do?exportXlsByT","wTerritoryCategoryList");
}
 </script>