<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>订单</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="wOrderController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${wOrderPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								标题:
							</label>
						</td>
						<td class="value">
						     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt"  value='${wOrderPage.title}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标题</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								描述:
							</label>
						</td>
						<td class="value">
						     	 <input id="description" name="description" type="text" style="width: 150px" class="inputxt"  value='${wOrderPage.description}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">描述</label>
						</td>
					</tr>
					<tr>
						
						<%-- <td align="right">
							<label class="Validform_label">
								类别:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="category" type="list"
										 defaultVal="${wOrderPage.category}" hasLabel="false"  title="类别"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类别</label>
						</td>
						 --%>
						
						<td align="right">
							<label class="Validform_label">
								类别:
							</label>
						</td>
						<td class="value">
						     	 <input id="category" name="category" type="text" style="width: 150px" class="inputxt"  value='${wOrderPage.category}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类别</label>
						</td>
						
						
						<td align="right">
							<label class="Validform_label">
								价格:
							</label>
						</td>
						<td class="value">
						     	 <input id="price" name="price" type="text" style="width: 150px" class="inputxt"  value='${wOrderPage.price}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">价格</label>
						</td>
						
						
					</tr>
					<tr>
						
						<td align="right">
							<label class="Validform_label">
								发布人:
							</label>
						</td>
						<td class="value">
						    <input id="issuer" name="issuer" type="text" style="width: 150px" class="inputxt"  value='${wOrderPage.issuer}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发布人</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								联系电话:
							</label>
						</td>
						<td class="value">
						     	 <input id="phone" name="phone" type="text" style="width: 150px" class="inputxt"  value='${wOrderPage.phone}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系电话</label>
						</td>
						
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								位置信息:
							</label>
						</td>
						<td class="value">
						     	 <input id="location" name="location" type="text" style="width: 150px" class="inputxt"  value='${wOrderPage.location}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">位置信息</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								国家:
							</label>
						</td>
						<td class="value">
						     	 <input id="state" name="state" type="text" style="width: 150px" class="inputxt"  value='${wOrderPage.state}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">国家</label>
						</td>
						
						
					</tr>
					<tr>
						
						<td align="right">
							<label class="Validform_label">
								城市:
							</label>
						</td>
						<td class="value">
						     	 <input id="city" name="city" type="text" style="width: 150px" class="inputxt"  value='${wOrderPage.city}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">城市</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								订单状态:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="orderStatus" type="list"
										typeGroupCode="orderstatu" defaultVal="${wOrderPage.orderStatus}" hasLabel="false"  title="订单状态"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单状态</label>
						</td>
						
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								接单人姓名:
							</label>
						</td>
						<td class="value">
						     	 <input id="orderPersonName" name="orderPersonName" type="text" style="width: 150px" class="inputxt"  value='${wOrderPage.orderPersonName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">接单人姓名</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								接单人联系电话:
							</label>
						</td>
						<td class="value">
						     	 <input id="orderPersonPhone" name="orderPersonPhone" type="text" style="width: 150px" class="inputxt"  value='${wOrderPage.orderPersonPhone}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">接单人联系电话</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								求助类型:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="seekStatus" type="list"
										typeGroupCode="seekstatus" defaultVal="${wOrderPage.seekStatus}" hasLabel="false"  title="类别"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">求助类型</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								订单创建时间:
							</label>
						</td>
						<td class="value">
									  <input id="orderTime" name="orderTime" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" value='<fmt:formatDate value='${wOrderPage.orderTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单创建时间</label>
						</td>
					</tr>
					
					<tr>
						<td align="right">
							<label class="Validform_label">
								订单开始时间:
							</label>
						</td>
						<td class="value">
									  <input id="startTime" name="startTime" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" value='<fmt:formatDate value='${wOrderPage.startTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单开始时间</label>
						</td>
						
						<td align="right">
							<label class="Validform_label">
								订单结束时间:
							</label>
						</td>
						<td class="value">
							<input id="endTime" name="endTime" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" value='<fmt:formatDate value='${wOrderPage.endTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单结束时间</label>
						</td>
					</tr>
					<tr>
					<td align="right">
							<label class="Validform_label">
								支付状态:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="buyStatus" type="list"
										typeGroupCode="buyStatus" defaultVal="${wOrderPage.buyStatus}" hasLabel="false"  title="支付状态"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支付状态</label>
						</td>
					<%-- <td align="right">
							<label class="Validform_label">
								支付方式:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="paymentMode" type="list"
										typeGroupCode="paymentMode" defaultVal="${wOrderPage.paymentMode}" hasLabel="false"  title="支付方式"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支付方式</label>
						</td> --%>
					</tr>
					
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/ssb/warmline/business/worder/wOrder.js"></script>		
