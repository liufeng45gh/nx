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
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
			<div>
				<div class="form">
					<label class="Validform_label"> 发单类型：</label>
					<select name="seekStatus" id="seekStatus" atype="*">
            			<option value="0">紧急</option>
            			<option value="1">普通</option>
        			</select>
				</div>
				<br>
				<div class="form" id="title">
            		<label class="Validform_label"> 标题: </label>
            		<input id="title" name="title" type="text" style="width:400px" class="inputxt" datatype="s1-16 *"/>
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
            		<input id="description" name="description" type="text" style="width:400px" class="inputxt" datatype="*" >
        		</div>
        		<br>
        		<div class="form" id="time1">
				<label class="Validform_label">开始时间:</label>
					<input id="startTime" name="startTime" type="text" style="width: 150px" datatype="*" class="Wdate"
					onClick="WdatePicker(({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d'}))" /> 
					
					<label class="Validform_label">--- 结束时间:</label>
					<input id="endTime" name="endTime" type="text" style="width: 150px" datatype="*" class="Wdate"
					onClick="WdatePicker(({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'}))" /> 
				</div>
				<br> 
			</div>
				<div class="form" id="location">
					<label class="Validform_label"> <t:mutiLang langKey="位置"/>:</label>
             	 	<input id="text" name="city" type="text" style="width: 150px" class="inputxt"  value="${text}" readonly="readonly" datatype="*" />
	               	<input name="territoryid" type="hidden" value="${id}" id="territoryid"/>
	                <t:choose hiddenName="territoryid"  hiddenid="id" url="wTerritoryController.do?territorys" name="territoryLists"
                          icon="icon-search" title="位置选择" textname="text" isclear="true" isInit="true"></t:choose>
                          
                    <span class="Validform_checktip"></span>      
                     <label class="Validform_label"> 分类: </label>
            		<input id="category" name="category" type="text" style="width:150px"  readonly="readonly"  datatype="*" class="inputxt" value=""/>
            		<a class="easyui-linkbutton"  onclick="buttons()">选择分类</a>      
				</div>
				
        		<br>
        			<div class="form" id="issuer">
        			<label class="Validform_label"> 价格: </label>
            		<input id="price" name="price" type="text" datatype="*" style="width: 150px" class="inputxt" />
        			<span class="Validform_checktip"></span><span class="Validform_checktip"></span><span class="Validform_checktip"></span>  
        			<span class="Validform_checktip"></span><span class="Validform_checktip"></span><span class="Validform_checktip"></span> 
        			<span class="Validform_checktip"></span><span class="Validform_checktip"></span><span class="Validform_checktip"></span>
        			<span class="Validform_checktip"></span><span class="Validform_checktip"></span><span class="Validform_checktip"></span>
        			<span class="Validform_checktip"></span><span class="Validform_checktip"></span><span class="Validform_checktip"></span>
					 <label class="Validform_label"> 发单人: </label>
					 <input id="VirtualUserId" name="issuerId" type="hidden" style="width:150px"  readonly="readonly"  datatype="*" class="inputxt" value=""/>
            		<input id="VirtualUserName" name="issuer" type="text" style="width:150px"  readonly="readonly"  datatype="*" class="inputxt" value=""/>
            		<a class="easyui-linkbutton"  onclick="choiceVirtualUser()">选择发单人</a>      
				</div>
				<br>
				<div class="form" id="phone">
            		<label class="Validform_label">手机号: </label>
            		<select name="phone"  style="width: 150px" datatype="*">
            			<option value="">请选择手机号</option>
            			<option value="18701406553">18701406553</option>
            			<option value="18811024655">18811024655</option>
            			<option value="13569409971">13569409971</option>
            			<option value="18701403132">18701403132</option>
            			<option value="13611194617">13611194617</option>
            			<option value="18410191260">18410191260</option>
            			<option value="18701405838">18701405838</option>
            		</select>
            		<span class="Validform_checktip"></span><span class="Validform_checktip"></span><span class="Validform_checktip"></span>  
        			<span class="Validform_checktip"></span><span class="Validform_checktip"></span><span class="Validform_checktip"></span> 
        			<span class="Validform_checktip"></span><span class="Validform_checktip"></span><span class="Validform_checktip"></span>
        			<span class="Validform_checktip"></span><span class="Validform_checktip"></span><span class="Validform_checktip"></span>
        			<span class="Validform_checktip"></span><span class="Validform_checktip"></span><span class="Validform_checktip"></span>
            		<label class="Validform_label"> 备注: </label>
						     	 <input id="remarks" name="remarks" type="text"  class="inputxt" >
        		</div>
        		<br> 
				 <div class="form" id="photos1">
		    		<label class="Validform_label"> <t:mutiLang langKey="图片上传"/>: </label>
			  		<td class="value" nowrap colspan="3">
			  		<input type="file" name="insuraceImage_u" id="insuraceImage_u" /> 
			 		 <a class="easyui-linkbutton" href="javascript:void(0)" onclick="uploadPic()">上传</a> 
			 		 <div class="Validform_label" >照片上传</div>
			 		 <input type="hidden" id="corporateDocumentsUpload" name="imgRootPath"  datatype="*"/>
			 		 <input type="hidden" id="photoss" name="photoss" />
			 		 <input type="hidden" id="categoryImage" name="categoryImage" />
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
