<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>w_order_photo_main</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="wOrderPhotoMainController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${wOrderPhotoMainPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							照片名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="photoName" name="photoName" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">照片名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							照片路径:
						</label>
					</td>
					<td class="value">
					     	 <input id="photoUrl" name="photoUrl" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">照片路径</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							订单id:
						</label>
					</td>
					<td class="value">
					     	 <input id="orderId" name="orderId" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单id</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户id:
						</label>
					</td>
					<td class="value">
					     	 <input id="uid" name="uid" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户id</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							创建日期:
						</label>
					</td>
					<td class="value">
							   <input id="createTime" name="createTime" type="text" style="width: 150px" 
					      						class="Wdate" onClick="WdatePicker()"
>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建日期</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							照片类型（0 头像 和 1 普通）:
						</label>
					</td>
					<td class="value">
					     	 <input id="photoType" name="photoType" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">照片类型（0 头像 和 1 普通）</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/ssb/warmline/business/worderphotomain/wOrderPhotoMain.js"></script>		
