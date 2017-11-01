<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px; width: 1456px; height: 50px">
		<t:datagrid name="wOrderList" checkbox="false" fitColumns="false" title="历史订单" actionUrl="AreaAgencyBrokerage.do?AreaAgencyBrokerageDatagrid" 
 	 	idField="id" fit="true" queryMode="group">
		   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  ></t:dgCol>
		   <t:dgCol title="标题"  field="title"    queryMode="group" width="160" ></t:dgCol>
		   <t:dgCol title="订单号"  field="orderNumber"  query="true"    queryMode="single" width="110"   ></t:dgCol>
		   <t:dgCol title="类别"  field="category" dictionary="category"   queryMode="single" width="50" ></t:dgCol>
		   <t:dgCol title="价格"  field="price"    queryMode="group"  width="50"></t:dgCol>
		   <t:dgCol title="发布人姓名"  field="issuer" query="true"     queryMode="single"  width="100"></t:dgCol>
		   <t:dgCol title="发布人电话"  field="phone"     queryMode="single"  width="100"></t:dgCol>
		   <t:dgCol title="位置信息"  field="location"    queryMode="group" width="220" ></t:dgCol>
		   <t:dgCol title="城市"  field="city"    queryMode="group" width="120" ></t:dgCol>
		   <t:dgCol title="接单人姓名"  field="orderPersonName"     queryMode="single" width="100"></t:dgCol>
		   <t:dgCol title="接单人电话"  field="orderPersonPhone"     queryMode="single" width="100" ></t:dgCol>
		   <t:dgCol title="发单区域"  field="territoryName" queryMode="single" width="100" ></t:dgCol>
		   <t:dgCol title="求助类型"  field="seekStatus" query="true"  dictionary="seekstatus" queryMode="single" width="60" ></t:dgCol>
		  	<t:dgCol title="订单状态"  field="orderStatus" query="false"  dictionary="orderstatu"  queryMode="single"  width="100"></t:dgCol>
		  	<t:dgCol title="支付状态"  field="buyStatus"  query="false"   dictionary="buyStatus" queryMode="single"  width="100"></t:dgCol>
	  </t:datagrid>
	<div id="addDemoListtb" style="padding: 10px;height: 50px;width: 1320px;">
			<div style="float: left;">
		<table cellpadding="0" cellpadding="1" class="formtable" style="width: 1320px;height:40;">
  			<tr>
  			<input  name="territoryId" type="hidden" value="${territoryId}"/>
				<tr>
				<td align="right" style="text-align:center">
						<label class="Validform_label">
							所属区域：
						</label>
					</td>
					<td class="value" style="text-align:center">
					     	&nbsp;<h4 style="color: red;">${territoryName}</h4>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属区域</label>
					</td>
				
					<td align="right" style="text-align:center">
						<label class="Validform_label">
							订单总数量：
						</label>
					</td>
					<td class="value" style="text-align:center" >
					     	&nbsp;<h4 style="color: red;">${totalNumber}</h4>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单总数量</label>
					</td>
					
					<td align="right" style="text-align:center">
						<label class="Validform_label">
							返佣订单数量：
						</label>
					</td>
					<td class="value" style="text-align:center" >
					     	&nbsp;<h4 style="color: red;">${number}</h4>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">返佣订单数量</label>
					</td>
					<td align="right" style="text-align:center">
						<label class="Validform_label">
							返佣总金额：
						</label>
					</td>
					<td class="value" style="text-align:center">
					     	 &nbsp;<h4 style="color: red;"> ${totalSalary}</h4>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">佣金总额</label>
						</td>
					</tr>
					</tr>
			</table>
			</div>
		</div>
	</div>
  	
	</div>
	<div id="formContent" region="south">
	</div>
</div>
 <script src = "webpage/ssb/warmline/business/worder/wOrderList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#wOrderListtb").find("input[name='orderTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss '});});
 			$("#wOrderListtb").find("input[name='orderTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss '});});
 			$("#wOrderListtb").find("input[name='startTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss '});});
 			$("#wOrderListtb").find("input[name='startTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss '});});
 			$("#wOrderListtb").find("input[name='endTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss '});});
 			$("#wOrderListtb").find("input[name='endTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss '});});
 });
 </script>