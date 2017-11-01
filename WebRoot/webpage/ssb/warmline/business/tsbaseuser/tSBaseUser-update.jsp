<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>t_s_base_user</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tSBaseUserController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${tSBaseUserPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								activitisync:
							</label>
						</td>
						<td class="value">
						     	 <input id="activitisync" name="activitisync" type="text" style="width: 150px" class="inputxt"  value='${tSBaseUserPage.activitisync}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">activitisync</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								browser:
							</label>
						</td>
						<td class="value">
						     	 <input id="browser" name="browser" type="text" style="width: 150px" class="inputxt"  value='${tSBaseUserPage.browser}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">browser</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								password:
							</label>
						</td>
						<td class="value">
						     	 <input id="password" name="password" type="text" style="width: 150px" class="inputxt"  value='${tSBaseUserPage.password}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">password</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								realname:
							</label>
						</td>
						<td class="value">
						     	 <input id="realname" name="realname" type="text" style="width: 150px" class="inputxt"  value='${tSBaseUserPage.realname}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">realname</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								signature:
							</label>
						</td>
						<td class="value">
						  	 	<textarea id="signature" style="width:600px;" class="inputxt" rows="6" name="signature">${tSBaseUserPage.signature}</textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">signature</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								status:
							</label>
						</td>
						<td class="value">
						     	 <input id="status" name="status" type="text" style="width: 150px" class="inputxt"  value='${tSBaseUserPage.status}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">status</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								userkey:
							</label>
						</td>
						<td class="value">
						     	 <input id="userkey" name="userkey" type="text" style="width: 150px" class="inputxt"  value='${tSBaseUserPage.userkey}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">userkey</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								username:
							</label>
						</td>
						<td class="value">
						     	 <input id="username" name="username" type="text" style="width: 150px" class="inputxt" datatype="*" value='${tSBaseUserPage.username}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">username</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								departid:
							</label>
						</td>
						<td class="value">
						     	 <input id="departid" name="departid" type="text" style="width: 150px" class="inputxt"  value='${tSBaseUserPage.departid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">departid</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								删除状态:
							</label>
						</td>
						<td class="value">
						     	 <input id="deleteFlag" name="deleteFlag" type="text" style="width: 150px" class="inputxt"  value='${tSBaseUserPage.deleteFlag}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">删除状态</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/qsb/business/tsbaseuser/tSBaseUser.js"></script>		
