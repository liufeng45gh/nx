<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>w_jpush</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  $(function() { 
	  $("#hiddenss").hide();
	  $('#jpushType').change(function(){
		  if($(this).val()=='versionUpdate'){
			  $("#hiddens").hide();
			  $("#hiddenss").show();
			  $("#mainTitle").removeAttr("datatype"); 
			  $("#title").removeAttr("datatype");
			  $("#text").removeAttr("datatype");
		  }else{
			  $("#hiddens").show();
			  $("#hiddenss").hide();
			  $("#mainTitle").attr("datatype","*"); 
			  $("#title").attr("datatype","*"); 
			  $("#text").attr("datatype","*"); 
		  }
	  });
  });
  
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="wJpushController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${wJpushPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<div class="form">
					<label class="Validform_label"> 推送类型：</label>
					<select name="jpushType" id="jpushType" atype="*">
            			<option value="text">文本</option>
            			<option value="versionUpdate">版本更新</option>
        			</select>
        			<span class="Validform_checktip"></span>
				</div>
				<br>
			<div id="hiddens">
				<div class="form">
            		<label class="Validform_label"> 主标题: </label>
            		<input id="mainTitle" name="mainTitle" type="text" style="width:400px" class="inputxt" datatype="*"/>
        		</div>
        		<br>
				<div class="form" >
            		<label class="Validform_label"> 推送标题: </label>
            		<input id="title" name="title" type="text" style="width:400px" class="inputxt" datatype="*"/>
        		</div>
        		<br>
				<div class="form" >
            		<label class="Validform_label">推送内容:</label>
            		<input id="text" name="text" type="text" style="width:400px" class="inputxt" datatype="*"/>
        		</div>
			</div>
			<div id="hiddenss" >
				<h3 style="color: red;">温馨提示：版本更新时会推送固定格式，无需填写！</h3>
        	</div>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/ssb/warmline/business/wjpush/wJpush.js"></script>		
