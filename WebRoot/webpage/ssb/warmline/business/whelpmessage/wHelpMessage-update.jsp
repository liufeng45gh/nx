<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>消息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="wHelpMessageController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${wHelpMessagePage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								求助人:
							</label>
						</td>
						<td class="value">
						     	 <input id="seekHelpPeople" name="seekHelpPeople" type="text" style="width: 150px" class="inputxt"  value='${wHelpMessagePage.seekHelpPeople}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">求助人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								帮助人:
							</label>
						</td>
						<td class="value">
						     	 <input id="helpPeople" name="helpPeople" type="text" style="width: 150px" class="inputxt"  value='${wHelpMessagePage.helpPeople}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">帮助人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								内容:
							</label>
						</td>
						<td class="value">
						     	 <input id="content" name="content" type="text" style="width: 150px" class="inputxt"  value='${wHelpMessagePage.content}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">内容</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								创建时间:
							</label>
						</td>
						<td class="value">
									  <input id="createDate" name="createDate" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" value='<fmt:formatDate value='${wHelpMessagePage.createDate}' type="date" pattern="yyyy-MM-dd"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建时间</label>
						</td>
					</tr>
					<%-- <tr>
						<td align="right">
							<label class="Validform_label">
								消息类型 :
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="messageType" type="list"
										typeGroupCode="messageTyp" defaultVal="${wHelpMessagePage.messageType}" hasLabel="false"  title="消息类型 "></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">消息类型 </label>
						</td>
					</tr> --%>
					<tr>
						<td align="right">
							<label class="Validform_label">
								阅读状态 :
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="readingState" type="list"
										typeGroupCode="readingSta" defaultVal="${wHelpMessagePage.readingState}" hasLabel="false"  title="阅读状态 "></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">阅读状态 </label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/ssb/warmline/business/whelpmessage/wHelpMessage.js"></script>		
