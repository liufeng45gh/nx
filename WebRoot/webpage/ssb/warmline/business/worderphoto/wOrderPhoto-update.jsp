<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>w_order_photo</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="wOrderPhotoController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${wOrderPhotoPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								订单id:
							</label>
						</td>
						<td class="value">
						     	 <input id="orderId" name="orderId" type="text" style="width: 150px" class="inputxt"  value='${wOrderPhotoPage.orderId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">订单id</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								photoName:
							</label>
						</td>
						<td class="value">
						     	 <input id="photoName" name="photoName" type="text" style="width: 150px" class="inputxt"  value='${wOrderPhotoPage.photoName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">photoName</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								照片路径:
							</label>
						</td>
						<td class="value">
						     	 <input id="photoUrl" name="photoUrl" type="text" style="width: 150px" class="inputxt"  value='${wOrderPhotoPage.photoUrl}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">照片路径</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								创建时间:
							</label>
						</td>
						<td class="value">
									  <input id="createTime" name="createTime" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" value='<fmt:formatDate value='${wOrderPhotoPage.createTime}' type="date" pattern="yyyy-MM-dd"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建时间</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/ssb/warmline/business/worderphoto/wOrderPhoto.js"></script>		
