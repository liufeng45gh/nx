<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <input type="hidden" id="currentId" value=""/>
   <input type="hidden" id="categoryNames" value=""/>
    <input type="hidden" id="prices" value=""/>
  <t:datagrid name="wTerritoryCategoryList" checkbox="false" fitColumns="false" title="类目区域中间表"
  	actionUrl="wTerritoryCategoryController.do?datagrids" idField="id" fit="true" queryMode="group" 
  	onClick="processRow(rowIndex,rowData)">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="分类名称"  field="categoryName"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="区域名称"  field="territoryName"    queryMode="group"  width="120"></t:dgCol>
   <%-- <t:dgCol title="分类id"  field="categoryId"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="区域id"  field="territoryId"    queryMode="group"  width="120"></t:dgCol> --%>
   <t:dgCol title="价钱范围"  field="price"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol  title="分类图片"  field="categoryImage"   image="true" imageSize="80,80"  queryMode="single" ></t:dgCol>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/qsb/business/zriskplanner/zRiskPlannerList.js"></script>		
 <script type="text/javascript">
 function processRow(rowIndex,rowData){
	 $("#currentId").val(rowData.id);
	 $("#categoryNames").val(rowData.categoryName);
	 $("#prices").val(rowData.price);
 }
 </script>