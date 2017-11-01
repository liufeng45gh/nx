<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="system_territory_territoryList" class="easyui-layout" fit="true">
<div region="center" style="padding:0px;border:0px">
<t:datagrid name="territoryLists" title="area.manage" actionUrl="wTerritoryController.do?datagrid_virtualOrder" idField="id" singleSelect="true" onClick="addFun(rowData)" showRefresh="false"   treegrid="true"  pagination="false">
	<t:dgCol title="common.id" field="id" treefield="id" hidden="true"></t:dgCol>
	<t:dgCol title="area.name" field="territoryName" treefield="text"></t:dgCol>
	<t:dgCol title="area.code" field="territorySrc" treefield="src"></t:dgCol>
	<t:dgCol  title="是否为父节点"  hidden="true" field="isparent" treefield="isparent"></t:dgCol>
	<t:dgCol title="父节点"  hidden="true" field="parentId" treefield="parentId"></t:dgCol>
	<input  title="是否为父节点" id="isparent" name="isparent"  type="hidden" />
	<input title="父节点" id="parentId" name="parentId"  type="hidden" />
	<input  title="名字" id="strText" name="text"  type="hidden" />
	<input title="id" id="strId" name="id"  type="hidden" />
</t:datagrid>
</div>
</div>

<script type="text/javascript">
$(function() {
	var li_east = 0;
});


function addFun(rowData){
	var strIsparent=rowData.isparent;
	var strParentId=rowData.parentId;
	var strText=rowData.text;
	var strId=rowData.id;
	 $("#isparent").val(strIsparent);
	 $("#parentId").val(strParentId);
	 $("#strText").val(strText);
	 $("#strId").val(strId);
	/* if(2 !=strId && 20 != strId && 2324 != strId && 802 != strId){
		if("true"==strIsparent){
			$.messager.alert('提示',"不能选择父级节点！");
		}else{
			
		}
	}  */
}

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
