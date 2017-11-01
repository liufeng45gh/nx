<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>订单</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" href="plug-in/uploadify/css/uploadify.css" type="text/css"></link>
  <script type="text/javascript" src="plug-in/uploadify/jquery.uploadify-3.1.js"></script>
  <script type="text/javascript">
  $(function() { 
	  choosePic();
	  $("#PhotoUpload").attr("visibility","hidden");
	  $("#hiddens").hide();
	  $("#subtitle").removeAttr("datatype"); 
	  $("#description").removeAttr("datatype");
	  $("#startTime").removeAttr("datatype");
	  $("#endTime").removeAttr("datatype");
	  $('#seekStatus').change(function(){
		  if($(this).val()=='0'){
			  $("#hiddens").hide();
			  $("#subtitle").removeAttr("datatype"); 
			  $("#description").removeAttr("datatype");
			  $("#startTime").removeAttr("datatype");
			  $("#endTime").removeAttr("datatype");
		  }else{
			  $("#hiddens").show();
			  $("#subtitle").attr("datatype","*"); 
			  $("#description").attr("datatype","*"); 
			  $("#startTime").attr("datatype","*"); 
			  $("#endTime").attr("datatype","*"); 
		  }
	  });
  });
  function choosePic() {
  	$('#insuraceImage_u').uploadify({
  		buttonText : '选择照片',
			progressData : 'speed',
			multi : false,
			height : 25,
			overrideEvents : [ 'onDialogClose' ],
			fileTypeDesc : '文件格式:',
			fileTypeExts : '*.jpg;*,jpeg;*.png;*.gif;*.bmp;*.ico;*.tif',
			fileSizeLimit : '15MB',
			swf : 'plug-in/uploadify/uploadify.swf',
			uploader : 'wOrderController.do?uploadPic&sessionId=${pageContext.session.id}',
			auto : false,
			onUploadSuccess : function(file, data, response) {
				if (data) {
					var d = $.parseJSON(data);
					if (d.success) {
						$("#prePic").attr("src",d.obj);
						$("#corporateDocumentsUpload").val(d.obj);
						$("#photoss").val(d.attributes.wOrderPhotoId);
						$("#categoryImage").val(d.attributes.name);
						$('#insuraceImage_u').uploadify("upload", "*");
					}
				} 
			}
  	});
  }
  function uploadPic(){
		$('#insuraceImage_u').uploadify("upload","*");
	}
  function buttons(){
	 var territoryid = $('#territoryid').val();
	 if(territoryid == ""){
		 $.messager.alert('提示','请先选择位置！');
	 }else{
	  $.dialog({
			content:'url:wTerritoryCategoryController.do?selectionClassIfication&territoryid=' + territoryid, 
			zIndex: 2000, 
			title: '选择区域', 
			lock: true, 
			width: 600, 
			height: 600, 
			opacity: 0.4, 
			close:function() {
				 $(this).dialog("close");
			},
			button: [{name: '确定', callback: choiceConnectSingle},
			         {name:'<t:mutiLang langKey="common.cancel"/>', callback: function (){}}]
		}); 
	 }
  }
  
  
  function choiceConnectSingle(){
		var iframe = this.iframe.contentWindow;
		var currentId = iframe.$("#currentId").val(); 
		var categoryNames = iframe.$("#categoryNames").val();
		var prices = iframe.$("#prices").val();
		var currentId = iframe.$("#currentId").val();
		if(currentId == ""){
			$.messager.alert('提示','请选择区域');
		}else{
			$("#category").val(categoryNames);
			$("#price").val(prices);
		}
	}
  
  //************************************************************************************
  
  function choiceVirtualUser(){
	  var territoryid = $('#price').val();
		 if(price == ""){
			 $.messager.alert('提示','请填写价格！');
		 }else{
		  $.dialog({
				content:'url:wOrderController.do?choiceVirtualUser', 
				zIndex: 2000, 
				title: '选择发单人', 
				lock: true, 
				width: 600, 
				height: 600, 
				opacity: 0.4, 
				close:function() {
					 $(this).dialog("close");
				},
				button: [{name: '确定', callback: choiceVirtualUserSingle},
				         {name:'<t:mutiLang langKey="common.cancel"/>', callback: function (){}}]
			}); 
		 }
  	}
	  function choiceVirtualUserSingle(){
			var iframe = this.iframe.contentWindow;
			var VirtualUserId = iframe.$("#VirtualUserId").val(); 
			var VirtualUserName = iframe.$("#VirtualUserName").val();
			if(VirtualUserId == ""){
				$.messager.alert('提示','请选择虚拟发单人');
			}else{
				$("#VirtualUserId").val(VirtualUserId);
				$("#VirtualUserName").val(VirtualUserName);
			}
		}
	  
//********************************************************************************************************

 function choiceTerritoryPage(){
	 var territoryid = $('#territoryid').val();
	 if(territoryid == ""){
		 $.messager.alert('提示','请先选择位置！');
	 }else{
	  $.dialog({
			content:'url:wTerritoryController.do?territorys', 
			zIndex: 2000, 
			title: '选择区域', 
			lock: true, 
			width: 600, 
			height: 600, 
			opacity: 0.4, 
			close:function() {
				 $(this).dialog("close");
			},
			button: [{name: '确定', callback: choiceTerritory},
			         {name:'<t:mutiLang langKey="common.cancel"/>', callback: function (){}}]
		}); 
	 }
  }
  
  
 function choiceTerritory(){
	 var iframe = this.iframe.contentWindow;
		var strId = iframe.$("#strId").val();
		var strText = iframe.$("#strText").val(); 
		if(strId == ""){
			$.messager.alert('提示','请选择区域');
		}else{
			$("#territoryid").val(strId);
			$("#strText").val(strText);
			 $.ajax({
		         type: 'POST',
		         url : 'wOrderController.do?choiceTerritory',
		         dataType : 'json',
		         data : {'territoryid' :strId},
		         success : function(data){
						if(data.success) {
							
							return true;
						} else {
							 $.messager.alert('提示','不能填写父级区域！');
							 return false;
						} 
		         }
		     });
		}
	
}

  </script>
  <style type="text/css">  
            .one{  
                width:100px;  
                height:100px;  
                background:blue;  
                visibility:hidden;  
            }  
            .two{  
                width:100px;  
                height:100px;  
                background:red;  
            }  
        </style>  
  
</style>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="wOrderController.do?doOrderAdd" tiptype="1" >
		<input id="id" name="id" type="hidden" value="${wOrderPage.id }">
		<input title="接单人姓名"  name="orderPersonName"  value="${wOrderPage.orderPersonName }" type="hidden" ></input>
   		<input title="接单人电话"  name="orderPersonPhone" value="${wOrderPage.orderPersonPhone }" type="hidden" ></input>
    	<input title="接单人id"  name="orderPersonId"   value="${wOrderPage.orderPersonId }"	type="hidden" ></input>
    	<input title="订单状态"  name="orderStatus"  value="${wOrderPage.orderStatus }" type="hidden" ></input>
    	<input title="下单时间"  name="orderTime" value='<fmt:formatDate value='${wOrderPage.orderTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>'   type="hidden" ></input>
  		<input title="支付状态"  name="buyStatus"  value="${wOrderPage.buyStatus}"   type="hidden" ></input>
  		<input title="联系电话"  name="orderPhone"  value="${wOrderPage.orderPhone}"   type="hidden" ></input>
  		<input title="订单所属运营员"  name="subordinateAdmin"  value="${wOrderPage.subordinateAdmin }" type="hidden" ></input>
   		
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
			<div>
				<div class="form">
					<label class="Validform_label"> 发单类型：</label>
					<select name="seekStatus" id="seekStatus" atype="*">
            			<option value="0" <c:if test="${wOrderPage.seekStatus eq 0}">selected = "selected"</c:if>>紧急</option>
            			<option value="1" <c:if test="${wOrderPage.seekStatus eq 1}">selected = "selected"</c:if>>普通</option>
        			</select>
				</div>
				<br>
				<div class="form" id="title">
            		<label class="Validform_label"> 标题: </label>
            		<input id="title" name="title" type="text" style="width:400px" value="${wOrderPage.title}" class="inputxt" datatype="s1-16 *"/>
        		</div>
        		<br>
			<div id="hiddens">
				<!-- <div class="form" id="subtitle1">
            		<label class="Validform_label"> 要求: </label>
            		<input id="subtitle" name="subtitle" type="text" style="width:400px" class="inputxt" datatype="*">
        		</div>
        		<br> -->
        		<div class="form" id="description1">
            		<label class="Validform_label"> 描述: </label>
            		<input id="description" name="description" type="text" style="width:400px" value='${wOrderPage.description}' class="inputxt" datatype="*" >
        		</div>
        		<br>
        		<div class="form" id="time1">
				<label class="Validform_label">开始时间:</label>
					<input id="startTime" name="startTime" value='<fmt:formatDate value='${wOrderPage.startTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>' style="width: 150px" datatype="*" class="Wdate"
					onClick="WdatePicker(({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d'}))" /> 
					
					<label class="Validform_label">--- 结束时间:</label>
					<input id="endTime" name="endTime"  value='<fmt:formatDate value='${wOrderPage.endTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>' style="width: 150px" datatype="*" class="Wdate"
					onClick="WdatePicker(({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'}))" /> 
				</div>
				<br> 
			</div>
				<div class="form" id="location">
					<label class="Validform_label"> <t:mutiLang langKey="位置"/>:</label>
             	 	<input id="strText" name="city" type="text" style="width: 150px" class="inputxt"  value="${text}" readonly="readonly" datatype="*" />
	               	<input id="territoryid" name="territoryid"  type="hidden"  value="${id}"/>
	                <%-- <t:choose hiddenName="territoryid"  hiddenid="id" url="wTerritoryController.do?territorys" name="territoryLists"
                          icon="icon-search" title="位置选择" textname="text" isclear="true" isInit="true" ></t:choose> --%>
                          <a class="easyui-linkbutton"  onclick="choiceTerritoryPage()">选择位置</a> 
                          
                    <span class="Validform_checktip"></span>      
                     <label class="Validform_label"> 分类: </label>
            		<input id="category" name="category" type="text" style="width:150px"  readonly="readonly"  datatype="*" class="inputxt" value='${wOrderPage.category}'/>
            		<a class="easyui-linkbutton"  onclick="buttons()">选择分类</a>      
				</div>
				
        		<br>
        			<div class="form" id="issuer">
        			<label class="Validform_label"> 价格: </label>
            		<input id="price" name="price" type="text" datatype="*" style="width: 150px" value='${wOrderPage.price}' class="inputxt" />
        			<span class="Validform_checktip"></span><span class="Validform_checktip"></span><span class="Validform_checktip"></span>  
        			<span class="Validform_checktip"></span><span class="Validform_checktip"></span><span class="Validform_checktip"></span> 
        			<span class="Validform_checktip"></span><span class="Validform_checktip"></span><span class="Validform_checktip"></span>
        			<span class="Validform_checktip"></span><span class="Validform_checktip"></span><span class="Validform_checktip"></span>
        			<span class="Validform_checktip"></span><span class="Validform_checktip"></span><span class="Validform_checktip"></span>       
					 <label class="Validform_label"> 发单人: </label>
					 <input id="VirtualUserId" name="issuerId" value='${wOrderPage.issuerId}'type="hidden" style="width:150px"  readonly="readonly"  datatype="*" class="inputxt" value=""/>
            		<input id="VirtualUserName" name="issuer" value='${wOrderPage.issuer}' type="text" style="width:150px"  readonly="readonly"  datatype="*" class="inputxt" value=""/>
            		<a class="easyui-linkbutton"  onclick="choiceVirtualUser()">选择发单人</a>      
				</div>
				 <span class="Validform_checktip"></span>      
				<div class="form" id="phone">
            		<label class="Validform_label"> 手机号: </label>
            		<select name="phone"  style="width: 150px" datatype="*">
            			<option value="">请选择手机号</option>
            			<option value="18701406553" <c:if test="${wOrderPage.phone eq 18701406553}">selected = "selected"</c:if>>18701406553</option>
            			<option value="18811024655" <c:if test="${wOrderPage.phone eq 18811024655}">selected = "selected"</c:if>>18811024655</option>
            			<option value="13569409971" <c:if test="${wOrderPage.phone eq 13569409971}">selected = "selected"</c:if>>13569409971</option>
            			<option value="18701403132" <c:if test="${wOrderPage.phone eq 18701403132}">selected = "selected"</c:if>>18701403132</option>
            			<option value="13611194617" <c:if test="${wOrderPage.phone eq 13611194617}">selected = "selected"</c:if>>13611194617</option>
            			<option value="18410191260" <c:if test="${wOrderPage.phone eq 18410191260}">selected = "selected"</c:if>>18410191260</option>
            			<option value="18701405838" <c:if test="${wOrderPage.phone eq 18701405838}">selected = "selected"</c:if>>18701405838</option>
            		</select>
            		<span class="Validform_checktip"></span><span class="Validform_checktip"></span><span class="Validform_checktip"></span>  
        			<span class="Validform_checktip"></span><span class="Validform_checktip"></span><span class="Validform_checktip"></span> 
        			<span class="Validform_checktip"></span><span class="Validform_checktip"></span><span class="Validform_checktip"></span>
        			<span class="Validform_checktip"></span><span class="Validform_checktip"></span><span class="Validform_checktip"></span>
        			<span class="Validform_checktip"></span><span class="Validform_checktip"></span><span class="Validform_checktip"></span>
            		<label class="Validform_label"> 备注: </label>
						     	 <input id="remarks" name="remarks" type="text" value='${wOrderPage.remarks}' class="inputxt" >
        		</div>
        		<br> 
				 <div class="form" id="photos1">
		    		<label class="Validform_label"> <t:mutiLang langKey="图片上传"/>: </label>
			  		<td class="value" nowrap colspan="3">
			  		<input type="file" name="insuraceImage_u" id="insuraceImage_u" /> 
			 		 <a class="easyui-linkbutton" href="javascript:void(0)" onclick="uploadPic()">上传</a> 
			 		 <div class="Validform_label" >照片上传</div>
			 		 <input type="hidden" id="corporateDocumentsUpload" name="imgRootPath" value='${categoryImage}' datatype="*"/>
			 		 <input type="hidden" id="photoss" name="photoss" value='${wOrderPage.photos}'/>
			 		 <input type="hidden" id="categoryImage" name="categoryImage"  value='${categoryImage}' />
            		</td>
            		<td colspan="4">
					<img id="prePic" src="${imgRootPath}" alt="缩略图" width="100" height="100" />
				</td>
				</div>
				<div>
				
				</div>
				
			</div>
		</table>
	</t:formvalid>
 </body>
  <script src = "webpage/ssb/warmline/business/worder/wOrder.js"></script>		
