<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>f_push_client</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="fPushClientController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${fPushClientPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								创建时间:
							</label>
						</td>
						<td class="value">
									  <input id="createTime" name="createTime" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" value='<fmt:formatDate value='${fPushClientPage.createTime}' type="date" pattern="yyyy-MM-dd"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建时间</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户id:
							</label>
						</td>
						<td class="value">
						     	 <input id="uid" name="uid" type="text" style="width: 150px" class="inputxt"  value='${fPushClientPage.uid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户id</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								用户名:
							</label>
						</td>
						<td class="value">
						     	 <input id="username" name="username" type="text" style="width: 150px" class="inputxt"  value='${fPushClientPage.username}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户名</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								渠道id:
							</label>
						</td>
						<td class="value">
						     	 <input id="departId" name="departId" type="text" style="width: 150px" class="inputxt"  value='${fPushClientPage.departId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">渠道id</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								渠道名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="departName" name="departName" type="text" style="width: 150px" class="inputxt"  value='${fPushClientPage.departName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">渠道名称</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								令牌:
							</label>
						</td>
						<td class="value">
						     	 <input id="token" name="token" type="text" style="width: 150px" class="inputxt"  value='${fPushClientPage.token}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">令牌</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								客户端id:
							</label>
						</td>
						<td class="value">
						     	 <input id="clientId" name="clientId" type="text" style="width: 150px" class="inputxt"  value='${fPushClientPage.clientId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户端id</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								APPID:
							</label>
						</td>
						<td class="value">
						     	 <input id="appId" name="appId" type="text" style="width: 150px" class="inputxt"  value='${fPushClientPage.appId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">APPID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								平台标识:
							</label>
						</td>
						<td class="value">
						     	 <input id="appType" name="appType" type="text" style="width: 150px" class="inputxt"  value='${fPushClientPage.appType}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">平台标识</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								devicetoken:
							</label>
						</td>
						<td class="value">
						     	 <input id="devicetoken" name="devicetoken" type="text" style="width: 150px" class="inputxt"  value='${fPushClientPage.devicetoken}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">devicetoken</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/ssb/warmline/business/fpushclient/fPushClient.js"></script>		
