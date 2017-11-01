<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="wTerritoryList" checkbox="true" fitColumns="false" title="区域管理" actionUrl="wTerritoryController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="区域编码"  field="territorycode"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="区域等级"  field="territorylevel"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="区域名字"  field="territoryname"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="区域拼音"  field="territoryPinyin"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="区域排序"  field="territorysort"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="坐标纬线"  field="xWgs84"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="经线"  field="yWgs84"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="区域父id"  field="territoryparentid"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否为父节点"  field="isparent"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否展开节点"  field="open"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="wTerritoryController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="wTerritoryController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="wTerritoryController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="wTerritoryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="wTerritoryController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/ssb/warmline/business/wterritory/wTerritoryList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'wTerritoryController.do?upload', "wTerritoryList");
}

//导出
function ExportXls() {
	JeecgExcelExport("wTerritoryController.do?exportXls","wTerritoryList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("wTerritoryController.do?exportXlsByT","wTerritoryList");
}
 </script>