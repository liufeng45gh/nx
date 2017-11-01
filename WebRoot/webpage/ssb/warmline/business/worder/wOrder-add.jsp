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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="wOrderController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${wOrderPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							标题:
						</label>
					</td>
					<td class="value">
					     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标题</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							副标题:
						</label>
					</td>
					<td class="value">
					     	 <input id="subtitle" name="subtitle" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">副标题</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							描述:
						</label>
					</td>
					<td class="value">
					     	 <input id="description" name="description" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">描述</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							类别:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="category" type="list"
									typeGroupCode="category" defaultVal="${wOrderPage.category}" hasLabel="false"  title="类别"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类别</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							相片:
						</label>
					</td>
					<td class="value">
					     	 <input id="photos" name="photos" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">相片</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							时长:
						</label>
					</td>
					<td class="value">
					     	 <input id="time" name="time" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">时长</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							价格:
						</label>
					</td>
					<td class="value">
					     	 <input id="price" name="price" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">价格</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							发布人:
						</label>
					</td>
					<td class="value">
					     	 <input id="issuer" name="issuer" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发布人</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							联系电话:
						</label>
					</td>
					<td class="value">
					     	 <input id="phone" name="phone" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系电话</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							位置信息:
						</label>
					</td>
					<td class="value">
					     	 <input id="location" name="location" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">位置信息</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							国家:
						</label>
					</td>
					<td class="value">
					     	 <input id="state" name="state" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">国家</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							城市:
						</label>
					</td>
					<td class="value">
					     	 <input id="city" name="city" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">城市</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							认证状态:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="certification" type="list"
									typeGroupCode="certificat" defaultVal="${wOrderPage.certification}" hasLabel="false"  title="认证状态"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">认证状态</label>
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
					     	 <input id="orderPersonName" name="orderPersonName" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">接单人姓名</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							接单人联系电话:
						</label>
					</td>
					<td class="value">
					     	 <input id="orderPersonPhone" name="orderPersonPhone" type="text" style="width: 150px" class="inputxt" >
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
								typeGroupCode="seekstatus" defaultVal="${wOrderPage.seekStatus}" hasLabel="false"  title="求助类型"></t:dictSelect>     
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">求助类型</label>
					</td>
						
					<td align="right">
						<label class="Validform_label">
							发布人id:
						</label>
					</td>
					<td class="value">
					     	 <input id="issuerId" name="issuerId" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发布人id</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							接单人id:
						</label>
					</td>
					<td class="value">
					     	 <input id="orderPersonId" name="orderPersonId" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">接单人id</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							订单创建时间:
						</label>
					</td>
					<td class="value">
							   <input id="orderTime" name="orderTime" type="text" style="width: 150px" 
					      						class="Wdate" onClick="WdatePicker()"
>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单创建时间</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							时间类型:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="timeType" type="list"
									typeGroupCode="timetype" defaultVal="${wOrderPage.timeType}" hasLabel="false"  title="时间类型"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">时间类型</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/ssb/warmline/business/worder/wOrder.js"></script>		
