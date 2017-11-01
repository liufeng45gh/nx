<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>审核操作</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="wOrderController.do?saveAuthor">
	<input id="id" name="id" type="hidden" value="${fOrderAddress.id }">
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
			
			<tr>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">					
						<textarea id="remarks" class="inputxt" rows="6" style="width: 400px; height: 32px;" name="remarks">${fOrderAddress.remarks}</textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
	</table>
</t:formvalid>
</body>