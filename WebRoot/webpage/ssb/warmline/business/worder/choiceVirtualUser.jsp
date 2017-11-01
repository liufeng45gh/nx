<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <input type="hidden" id="VirtualUserId" value=""/>
   <input type="hidden" id="VirtualUserName" value=""/>
  <t:datagrid name="fictitiousNameList" checkbox="false" fitColumns="false" title="虚拟用户表"
  	actionUrl="userController.do?fictitiousNameDatagrid" idField="id" fit="true" queryMode="group" 
  	onClick="processRow(rowIndex,rowData)">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="common.id" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="common.username" sortable="false" field="userName" query="true"></t:dgCol>
	<t:dgCol title="common.real.name" field="realName" query="false"></t:dgCol>
	<t:dgCol title="common.role" field="userKey" ></t:dgCol>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 function processRow(rowIndex,rowData){
	 $("#VirtualUserId").val(rowData.id);
	 $("#VirtualUserName").val(rowData.userName);
 }
 </script>