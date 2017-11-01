<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>mobile_login_log</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="mobileLoginLogController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${mobileLoginLogPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户名:
						</label>
					</td>
					<td class="value">
					     	 <input id="username" name="username" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户名</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							渠道名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="departName" name="departName" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">渠道名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							渠道id:
						</label>
					</td>
					<td class="value">
					     	 <input id="departid" name="departid" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">渠道id</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							ip地址:
						</label>
					</td>
					<td class="value">
					     	 <input id="ip" name="ip" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">ip地址</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							登录时间:
						</label>
					</td>
					<td class="value">
							   <input id="loginTime" name="loginTime" type="text" style="width: 150px" 
					      						class="Wdate" onClick="WdatePicker()"
>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">登录时间</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							来源:
						</label>
					</td>
					<td class="value">
					     	 <input id="userAgent" name="userAgent" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">来源</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							真实姓名:
						</label>
					</td>
					<td class="value">
					     	 <input id="realname" name="realname" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">真实姓名</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							agentType:
						</label>
					</td>
					<td class="value">
					     	 <input id="agentType" name="agentType" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">agentType</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							appType:
						</label>
					</td>
					<td class="value">
					     	 <input id="appType" name="appType" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">appType</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/ssb/warmline/business/mobileloginlog/mobileLoginLog.js"></script>		
