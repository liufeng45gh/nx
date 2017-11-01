<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>订单日志</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="wOrderRecordController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${wOrderRecordPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								订单号:
							</label>
						</td>
						<td class="value">
						     	 <input id="orderNumber" name="orderNumber" type="text" style="width: 150px" class="inputxt"  value='${wOrderRecordPage.orderNumber}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								订单金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="amount" name="amount" type="text" style="width: 150px" class="inputxt"  value='${wOrderRecordPage.amount}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								订单交易类型 :
							</label>
						</td>
						<td class="value">
						     	 <input id="orderType" name="orderType" type="text" style="width: 150px" class="inputxt"  value='${wOrderRecordPage.orderType}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单交易类型 </label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								描述:
							</label>
						</td>
						<td class="value">
						     	 <input id="description" name="description" type="text" style="width: 150px" class="inputxt"  value='${wOrderRecordPage.description}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">描述</label>
						</td>
					</tr>
					
					<tr>
						<td align="right">
							<label class="Validform_label">
								发布人:
							</label>
						</td>
						<td class="value">
						     	 <input id="issuer" name="issuer" type="text" style="width: 150px" class="inputxt"  value='${wOrderRecordPage.issuer}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发布人</label>
						</td>
					</tr>
					
					
					<tr>
						<td align="right">
							<label class="Validform_label">
								手机号:
							</label>
						</td>
						<td class="value">
						     	 <input id="phone" name="phone" type="text" style="width: 150px" class="inputxt"  value='${wOrderRecordPage.phone}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">手机号</label>
						</td>
					</tr>
					
					
					<tr>
						<td align="right">
							<label class="Validform_label">
								支付状态:
							</label>
						</td>
						<td class="value">
						     	 <input id="buyStatus" name="buyStatus" 
						     	 typeGroupCode="buyStatus" type="text" style="width: 150px" class="inputxt"  value='${wOrderRecordPage.buyStatus}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支付状态</label>
						</td>
					</tr>
					
					
					<tr>
						<td align="right">
							<label class="Validform_label">
								描述:
							</label>
						</td>
						<td class="value">
						     	 <input id="description" name="description" type="text" style="width: 150px" class="inputxt"  value='${wOrderRecordPage.description}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">描述</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								创建时间:
							</label>
						</td>
						<td class="value">
									  <input id="createTime" name="createTime" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value='<fmt:formatDate value='${wOrderRecordPage.createTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建时间</label>
						</td>
					</tr>
					
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/ssb/warmline/business/worderrecord/wOrderRecord.js"></script>		
