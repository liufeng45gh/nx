
 <%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>审核操作</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

  <script type="text/javascript">
  function saveWord(){
	var keyword= $("#keyword").val();
	  $.ajax({
	 		url:"keyWordController.do?doAdd",
	 		dataType:'json',
	 		data:{
	 			keyword:keyword
    		},
	 		type: "POST",
	 		success:function(data){
	 			$.messager.alert('提示',data);
	 		}
	 	});
  }
  </script>
</head>
<body style="overflow-y: hidden" scroll="no">
<div>
<table style="width: 900px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td class="value">	
			<textarea id="keyword" class="inputxt" rows="6" style="width: 900px; height: 400px;" name="keyword">${word}</textarea>
			</td>
		</tr>
		
		<tr>
			<td class="value">	
			<button style="background-color: #00A1DD; float: right;" type="button" onclick="saveWord()" >保存</button>
			<!-- <input style="background-color: #00A1DD; float: right;"  name="保存" type="submit"/> -->
			</td>
		</tr>
	</table>
</div>
	<%-- <input id="id" name="id" type="hidden" value="${fOrderAddress.id }"> --%>
	
		
</body>
		
