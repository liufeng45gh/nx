<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>区域管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="wTerritoryController.do?doUpdate" tiptype="1" >
				<input id="id" name="id" type="hidden" value="${wTerritoryPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">区域编码:</label>
		     	 <input id="territorycode" name="territorycode" type="text" style="width: 150px" class="inputxt"datatype="*"  value='${wTerritoryPage.territorycode}'>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">区域等级:</label>
		     	 <input id="territorylevel" name="territorylevel" type="text" style="width: 150px" class="inputxt"datatype="*"  value='${wTerritoryPage.territorylevel}'>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">区域名字:</label>
		     	 <input id="territoryname" name="territoryname" type="text" style="width: 150px" class="inputxt"datatype="*"  value='${wTerritoryPage.territoryname}'>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">区域拼音:</label>
		     	 <input id="territoryPinyin" name="territoryPinyin" type="text" style="width: 150px" class="inputxt"  value='${wTerritoryPage.territoryPinyin}'>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">区域排序:</label>
		     	 <input id="territorysort" name="territorysort" type="text" style="width: 150px" class="inputxt"datatype="*"  value='${wTerritoryPage.territorysort}'>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">坐标纬线:</label>
		     	 <input id="xWgs84" name="xWgs84" type="text" style="width: 150px" class="inputxt"datatype="*"  value='${wTerritoryPage.xWgs84}'>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">经线:</label>
		     	 <input id="yWgs84" name="yWgs84" type="text" style="width: 150px" class="inputxt"datatype="*"  value='${wTerritoryPage.yWgs84}'>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">区域父id:</label>
		     	 <input id="territoryparentid" name="territoryparentid" type="text" style="width: 150px" class="inputxt"  value='${wTerritoryPage.territoryparentid}'>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">是否为父节点:</label>
		     	 <input id="isparent" name="isparent" type="text" style="width: 150px" class="inputxt"  value='${wTerritoryPage.isparent}'>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">是否展开节点:</label>
		     	 <input id="open" name="open" type="text" style="width: 150px" class="inputxt"  value='${wTerritoryPage.open}'>
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>
  <script src = "webpage/ssb/warmline/business/wterritory/wTerritory.js"></script>		
