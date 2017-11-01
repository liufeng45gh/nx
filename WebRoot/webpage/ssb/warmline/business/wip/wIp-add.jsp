<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>w_ip</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="wIpController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${wIpPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							phone:
						</label>
					</td>
					<td class="value">
					     	 <input id="phone" name="phone" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">phone</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							ip:
						</label>
					</td>
					<td class="value">
					     	 <input id="ip" name="ip" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">ip</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							count:
						</label>
					</td>
					<td class="value">
					     	 <input id="count" name="count" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">count</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							date:
						</label>
					</td>
					<td class="value">
							   <input id="date" name="date" type="text" style="width: 150px" 
					      						class="Wdate" onClick="WdatePicker()"
>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">date</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							type:
						</label>
					</td>
					<td class="value">
					     	 <input id="type" name="type" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">type</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/ssb/warmline/business/wip/wIp.js"></script>		
