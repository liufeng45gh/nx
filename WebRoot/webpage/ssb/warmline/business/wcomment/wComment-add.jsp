<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>评论表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="wCommentController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${wCommentPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							评星个数:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="commentStar" type="list"
									typeGroupCode="" defaultVal="${wCommentPage.commentStar}" hasLabel="false"  title="评星个数"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">评星个数</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							评论时间:
						</label>
					</td>
					<td class="value">
							   <input id="commentTime" name="commentTime" type="text" style="width: 150px" 
					      						class="Wdate" onClick="WdatePicker()"
>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">评论时间</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							评论内容:
						</label>
					</td>
					<td class="value">
					     	 <input id="content" name="content" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">评论内容</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							发布人:
						</label>
					</td>
					<td class="value">
					     	 <input id="issuer" name="issuer" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发布人</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							发布人id:
						</label>
					</td>
					<td class="value">
					     	 <input id="issuerId" name="issuerId" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发布人id</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/ssb/warmline/business/wcomment/wComment.js"></script>		
