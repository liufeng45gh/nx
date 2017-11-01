<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <input type="hidden" id="currentId" value=""/>
  <t:datagrid name="tSBaseUserChoiceSingle" checkbox="false" fitColumns="true" title="接单人" 
  	actionUrl="tSBaseUserController.do?choiceSingleDatagrid&territoryId=${territoryId}" idField="id" fit="true" queryMode="group" 
  	onClick="processRow(rowIndex,rowData)">
    <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="真实用户名"  field="realname"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="昵称"  field="username"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="手机号"  field="phone"    queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="年龄"  field="age"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="用户角色"  field="userkey"    queryMode="group"  width="120"></t:dgCol>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 function processRow(rowIndex,rowData){
	 $("#currentId").val(rowData.id);
 }
 </script>