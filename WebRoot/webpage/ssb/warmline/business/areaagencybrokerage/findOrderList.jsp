<%--  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
   <t:datagrid name="findOrderList" checkbox="false" fitColumns="true" title="历史订单" actionUrl="AreaAgencyBrokerage.do?applicantNumberDatagrid"
    idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="标题"  field="title"    queryMode="group"  ></t:dgCol>
   <t:dgCol title="订单号"  field="orderNumber"  query="true"    queryMode="single"   ></t:dgCol>
   <t:dgCol title="类别"  field="category" dictionary="category"   queryMode="single"  ></t:dgCol>
   <t:dgCol title="价格"  field="price"    queryMode="group"  ></t:dgCol>
   <t:dgCol title="发布人姓名"  field="issuer" query="true"     queryMode="single"  ></t:dgCol>
   <t:dgCol title="发布人电话"  field="phone"     queryMode="single"  ></t:dgCol>
   <t:dgCol title="位置信息"  field="location"    queryMode="group"  ></t:dgCol>
   <t:dgCol title="城市"  field="city"    queryMode="group" ></t:dgCol>
   <t:dgCol title="接单人姓名"  field="orderPersonName"     queryMode="single" ></t:dgCol>
   <t:dgCol title="接单人电话"  field="orderPersonPhone"     queryMode="single"  ></t:dgCol>
   <t:dgCol title="求助类型"  field="seekStatus" query="true"  dictionary="seekstatus" queryMode="single"  ></t:dgCol>
  	<t:dgCol title="订单状态"  field="orderStatus" query="false"  dictionary="orderstatu"  queryMode="single"  ></t:dgCol>
  	<t:dgCol title="支付状态"  field="buyStatus"  query="false"   dictionary="buyStatus" queryMode="single"  ></t:dgCol>
  </t:datagrid>

 <script src = "webpage/ssb/warmline/business/worder/wOrderList.js"></script>		
 <script type="text/javascript">

 </script>
 

 
  --%>