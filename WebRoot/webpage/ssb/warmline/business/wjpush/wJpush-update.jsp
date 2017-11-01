<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>w_jpush</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="wJpushController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${wJpushPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<%-- <tr>
						<td align="right">
							<label class="Validform_label">
								推送类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="jpushType" name="jpushType" type="text" style="width: 150px" class="inputxt"  value='${wJpushPage.jpushType}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">推送类型</label>
						</td>
					</tr> --%>
					<%-- <tr>
						<td align="right">
							<label class="Validform_label">
								内容:
							</label>
						</td>
						<td class="value">
						     	 <input id="content" name="content" type="text" style="width: 150px" class="inputxt"  value='${wJpushPage.content}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">内容</label>
						</td> --%>
					<%-- <tr>
						<td align="right">
							<label class="Validform_label">
								发送状态:
							</label>
						</td>
						<td class="value">
						     	 <input id="sendStatus" name="sendStatus" type="text" style="width: 150px" class="inputxt"  value='${wJpushPage.sendStatus}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发送状态</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								页面去向:
							</label>
						</td>
						<td class="value">
						     	 <input id="pageWhereabouts" name="pageWhereabouts" type="text" style="width: 150px" class="inputxt"  value='${wJpushPage.pageWhereabouts}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">页面去向</label>
						</td> --%>
					<tr>
						<td align="right">
							<label class="Validform_label">
								主标题:
							</label>
						</td>
						<td class="value">
						     	 <input id="mainTitle" name="mainTitle" type="text" style="width: 150px" class="inputxt"  value='${wJpushPage.mainTitle}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">主标题</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								标题:
							</label>
						</td>
						<td class="value">
						     	 <input id="title" name="title" type="text" style="width: 150px" class="inputxt"  value='${wJpushPage.title}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标题</label>
						</td>
					<%-- <tr>
						<td align="right">
							<label class="Validform_label">
								照片路径:
							</label>
						</td>
						<td class="value">
						     	 <input id="photoPath" name="photoPath" type="text" style="width: 150px" class="inputxt"  value='${wJpushPage.photoPath}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">照片路径</label>
						</td>
					</tr> --%>
					<%-- <tr>
						<td align="right">
							<label class="Validform_label">
								editLink:
							</label>
						</td>
						<td class="value">
						     	 <input id="editLink" name="editLink" type="text" style="width: 150px" class="inputxt"  value='${wJpushPage.editLink}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">editLink</label>
						</td> --%>
					<tr>
						<td align="right">
							<label class="Validform_label">
								推送内容:
							</label>
						</td>
						<td class="value">
						     	 <input id="text" name="text" type="text" style="width: 150px" class="inputxt"  value='${wJpushPage.text}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">推送内容</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/ssb/warmline/business/wjpush/wJpush.js"></script>		
