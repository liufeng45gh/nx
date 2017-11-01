<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>分类表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="wCategoryController.do?doAdd" tiptype="1" >
				<input id="id" name="id" type="hidden" value="${wCategoryPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">类别名称:</label>
		     	 <input id="categoryName" name="categoryName" type="text" style="width: 150px" class="inputxt" >
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">categoryCode:</label>
		     	 <input id="categoryCode" name="categoryCode" type="text" style="width: 150px" class="inputxt" >
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">类别排序:</label>
		     	 <input id="categorySort" name="categorySort" type="text" style="width: 150px" class="inputxt" >
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">类别父id:</label>
		     	 <input id="categoryParentid" name="categoryParentid" type="text" style="width: 150px" class="inputxt" >
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">类别图片:</label>
		     	 <input id="categoryImage" name="categoryImage" type="text" style="width: 150px" class="inputxt" >
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>
  <script src = "webpage/ssb/warmline/business/wcategroy/wCategory.js"></script>	
	