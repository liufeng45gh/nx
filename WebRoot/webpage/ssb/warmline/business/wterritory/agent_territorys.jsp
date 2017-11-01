<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="system_territory_territoryList" class="easyui-layout" fit="true">
<div region="center" style="padding:0px;border:0px">
<t:datagrid name="agent_territorys"  title="area.manage" actionUrl="wTerritoryController.do?datagrid_agent" idField="id" singleSelect="true"  showRefresh="false"   treegrid="true"  pagination="false">
	<t:dgCol title="common.id" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="area.name" field="territoryName" treefield="text"></t:dgCol>
	<t:dgCol title="area.code" field="territorySrc" treefield="src"></t:dgCol>
	<t:dgCol title="display.order"  hidden="true" field="isparent" treefield="order"></t:dgCol>
</t:datagrid>
</div>
</div>

<script type="text/javascript">
$(function() {
	var li_east = 0;
});


function addFun(rowData){
	var str=rowData.order;
	if(str){
		$.messager.alert('提示',"不能选择父级节点！");
	}
}
/* function addFun(title,url, id) {
	var rowData = $('#'+id).datagrid('getSelected');
	alert(rowData);
	if (rowData) {
		url += '&WTerritoryEntity.id='+rowData.id;
	}
	add(title,url,'territoryLists');
} */
</script>

<script type="text/javascript">
function initCheck(data){
	var ids = "${ids}";
	var idArr = ids.split(",");
	for(var i=0;i<idArr.length;i++){
		if(idArr[i]!=""){
			$("#roleList").datagrid("selectRecord",idArr[i]);
		}
	}
}
</script>
