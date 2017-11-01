<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="system_territory_territoryList" class="easyui-layout" fit="true">
<div region="center" style="padding:0px;border:0px">
<t:datagrid name="territoryList" title="area.manage" actionUrl="wTerritoryController.do?datagrid" idField="id" treegrid="true" pagination="false">
	<t:dgCol title="common.id" field="id" treefield="id" hidden="true"></t:dgCol>
	<t:dgCol title="area.name" field="territoryName" treefield="text"></t:dgCol>
	<t:dgCol title="area.code" field="territorySrc" treefield="src"></t:dgCol>
	<t:dgCol title="display.order" field="territorySort" treefield="order"></t:dgCol>
	<t:dgCol title="common.operation" field="opt"></t:dgCol>
	<t:dgDelOpt url="wTerritoryController.do?del&id={id}" title="common.delete"></t:dgDelOpt>
	<t:dgToolBar title="common.add.param" langArg="common.area" icon="icon-add" url="wTerritoryController.do?addorupdate" width="600" height="700"  funname="addFun"></t:dgToolBar>
	<t:dgToolBar title="common.edit.param" langArg="common.area" icon="icon-edit" url="wTerritoryController.do?addorupdate" width="600" height="700" funname="update"></t:dgToolBar>
</t:datagrid></div>
</div>

<script type="text/javascript">
$(function() {
	var li_east = 0;
});
function addFun(title,url, id) {
	var rowData = $('#'+id).datagrid('getSelected');
	if (rowData) {
		url += '&WTerritoryEntity.id='+rowData.id;
	}
	add(title,url,'territoryList');
}
</script>

