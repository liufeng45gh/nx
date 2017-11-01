<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <input type="hidden" id="currentId" value=""/>
   <input id="jpushId" name="jpushId" type="hidden" value="${jpushId}">
  <t:datagrid name="jupshUser" checkbox="true"  fitColumns="true" title="推送人" 
  	actionUrl="tSBaseUserController.do?loadPushPerson" idField="id" fit="true" queryMode="group" 
   onClick="processRow(rowIndex,rowData)" >
    <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="60"></t:dgCol>
   <t:dgCol title="真实用户名"  field="realname"    queryMode="group"  width="60"></t:dgCol>
   <t:dgCol title="昵称"  field="username"    queryMode="group"  width="60"></t:dgCol>
   <t:dgCol title="手机号"  field="phone"    queryMode="group"  width="60"></t:dgCol>
    <t:dgCol title="年龄"  field="age"    queryMode="group"  width="60"></t:dgCol>
   <t:dgCol title="角色"  field="userkey"    queryMode="group"  width="60"></t:dgCol>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/ssb/warmline/business/wjpush/wJpushList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
	$(".datagrid-header-check").hide();
 }); 
 var array=new Array();
 function processRow(rowIndex,rowData){
	 /* $(".datagrid-btable"). */
	 var ids = rowData.phone;
		var idArr = ids.split(",");
		for(var i=0;i<idArr.length;i++){
			if(idArr[i]!=""){
				if(contains(array, ids)){
					removeByValue(array,ids);
				}else{
					array.push(ids);
				}
				
			}
		}
	$("#currentId").val(array); 
	$("#jpushId").val($("#jpushId").val()); 
	
 }
 
 function contains(arr, obj) {  
	    var i = arr.length;  
	    while (i--) {  
	        if (arr[i] === obj) {  
	            return true;  
	        }  
	    }  
	    return false;  
	}  
 
 function removeByValue(arr, val) {
	  for(var i=0; i<arr.length; i++) {
	    if(arr[i] == val) {
	      arr.splice(i, 1);
	      break;
	    }
	  }
	}
/*  function removeChange(id){
	 for(var a=0 ;a<array.lenth();a++){
		 if(id==array[a]){
			 array.
		 }
	 }
 } */
 </script>