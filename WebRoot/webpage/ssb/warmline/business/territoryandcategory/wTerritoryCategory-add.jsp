<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>类目区域中间表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" href="/../warmline/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="/../warmline/js/jquery.ztree.core.js"></script>
	 <link rel="stylesheet" href="/plug-in/uploadify/css/uploadify.css" type="text/css"></link>
  <script type="text/javascript" src="/plug-in/uploadify/jquery.uploadify-3.1.js"></script>
 
	 <script type="text/javascript">
	 $(function() {
	      choosePic();
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
				uploader : 'wTerritoryCategoryController.do?uploadPic&sessionId=${pageContext.session.id}',
				auto : false,
				onUploadSuccess : function(file, data, response) {
					if (data) {
						var d = $.parseJSON(data);
						if (d.success) {
							$("#prePic").attr("src",d.obj);
							$("#corporateDocumentsUpload").val(d.obj);
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
//-------------------------------------------------------------------------------------------------------------------------------
	  
	//类目
	  var setting = {
	  		async: {
	  			enable: true,
	  			url:"wTerritoryCategoryController.do?categoryList",
	  			autoParam:["id"],
	  			//otherParam:{"id":"0"},
	  			 type : "POST",
	  				dataType : "json",
	  			//dataFilter: ajaxDataFilter
	  		},  
	  		view: {
	  			dblClickExpand: false,
	  			selectedMulti:false
	  		},
	  		/* data: {
	  			simpleData: {
	  				enable: true
	  			}
	  		}, */
	  		data: { // 必须使用data  
	              simpleData: {
	                  enable: true,
	                  idKey: "id", // id编号命名 默认  
	                  pIdKey: "pId", // 父id编号命名 默认   
	                  rootPId: "0"   // 用于修正根节点父节点数据，即 pIdKey 指定的属性值  
	              },
	              key:{
	              	name:"name"
	              }
	          },
	  		callback: {
	  			beforeClick: beforeClick,
	  			onClick: onClick_category
	  		}
	  	};

	  //区域
	  var setting_territory = {
	  		async: {
	  			enable: true,
	  			url:"wTerritoryCategoryController.do?territoryList",
	  			autoParam:["id"],
	  			//otherParam:{"id":"0"},
	  			 type : "POST",
	  				dataType : "json",
	  			//dataFilter: ajaxDataFilter
	  		},  
	  		view: {
	  			dblClickExpand: false,
	  			selectedMulti:false
	  		},
	  	
	  		data: { // 必须使用data  
	              simpleData: {
	                  enable: true,
	                  idKey: "id", // id编号命名 默认  
	                  pIdKey: "pId", // 父id编号命名 默认   
	                  rootPId: "00"   // 用于修正根节点父节点数据，即 pIdKey 指定的属性值  
	              },
	              key:{
	              	name:"name"
	              }
	          },
	  		callback: {
	  			beforeClick: beforeClick,
	  			onClick: onClick_territory
	  		}
	  	};

	  //-------------------------------------------------------------------------------------------------------------------------------------------
	  	function beforeClick(treeId, treeNode) {
	  		var check = (treeNode && !treeNode.isParent);
	  		if (!check) alert("只能选择最小子级节点");
	  		return check;
	  	}
	  	
	  	var objId=null;
	  	var objName=null;
	  	var objPid=null;
	  	
	  //-------------------------------------------------------------------------------------------------------------------------------------------
	  	function onClick_category(e, treeId, treeNode) {
	  		var zTree = $.fn.zTree.getZTreeObj("treeDemo_category"),
	  		nodes = zTree.getSelectedNodes(),
	  		v = "";
	  		z="";
	  		x="";
	  		nodes.sort(function compare(a,b){return a.id-b.id;});
	  		for (var i=0, l=nodes.length; i<l; i++) {
	  			v = nodes[i].id +",";
	  			z = nodes[i].name + ",";
	  			x = nodes[i].pId + ",";
	  		}
	  		
	  		v = v.substring(0, v.length-1);
	  		z= z.substring(0, z.length-1);
	  		x= x.substring(0, x.length-1);
	  		var objIdTemp = $("#"+objId);
	  		var objNameTemp = $("#"+objName);
	  		var objPidTemp = $("#"+objPid);
	  		objIdTemp.attr("value",v);
	  		objNameTemp.attr("value",z);
	  		objPidTemp.attr("value",x);
	  	}
	  	
	  	function onClick_territory(e, treeId, treeNode) {
	  		var zTree = $.fn.zTree.getZTreeObj("treeDemo_territory"),
	  		nodes = zTree.getSelectedNodes(),
	  		v = "";
	  		z="";
	  		x="";
	  		nodes.sort(function compare(a,b){return a.id-b.id;});
	  		for (var i=0, l=nodes.length; i<l; i++) {
	  			v = nodes[i].id +",";
	  			z = nodes[i].name + ",";
	  			x = nodes[i].pId + ",";
	  		}
	  		
	  		v = v.substring(0, v.length-1);
	  		z= z.substring(0, z.length-1);
	  		x= x.substring(0, x.length-1);
	  		var objIdTemp = $("#"+objId);
	  		var objNameTemp = $("#"+objName);
	  		var objPidTemp = $("#"+objPid);
	  		objIdTemp.attr("value",v);
	  		objNameTemp.attr("value",z);
	  		objPidTemp.attr("value",x);
	  	}
	  //-------------------------------------------------------------------------------------------------------------------------------------------	
	  	 //区域
	  	function showMenu_territory(id,name,pId) {
	  		
	  		 objId=null;
	  		 objName=null;
	  		 objPid=null;
	  		 objId=id;
	  		 objName=name;
	  		 objPid=pId;
	  		var cityObj = $("#"+objName);
	  		var cityOffset = $("#"+objName).offset();
	  		$("#menuContent_territory").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

	  		$("body").bind("mousedown", onBodyDown_territory);
	  	}
	  //-------------------------------------------------------------------------------------------------------------------------------------------
	  	//类目
	  	function showMenu_category(id,name,pId) {
	  		
	  		 objId=null;
	  		 objName=null;
	  		 objPid=null;
	  		 objId=id;
	  		 objName=name;
	  		 objPid=pId;
	  		var cityObj = $("#"+objName);
	  		var cityOffset = $("#"+objName).offset();
	  		$("#menuContent_category").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

	  		$("body").bind("mousedown", onBodyDown_category);
	  	}
	  //-------------------------------------------------------------------------------------------------------------------------------------------	
	  	
	  	function hideMenu_category() {
	  		$("#menuContent_category").fadeOut("fast");
	  		$("body").unbind("mousedown", onBodyDown_category);
	  	}
	  	
	  	function hideMenu_territory() {
	  		$("#menuContent_territory").fadeOut("fast");
	  		$("body").unbind("mousedown", onBodyDown_territory);
	  	}
	  //-------------------------------------------------------------------------------------------------------------------------------------------	
	  	function onBodyDown_category(event) {
	  		if (!(event.target.id == "menuBtn" || event.target.id == "menuContent_category" || $(event.target).parents("#menuContent_category").length>0)) {
	  			hideMenu_category();
	  		}
	  	}
	  	
	  	function onBodyDown_territory(event) {
	  		if (!(event.target.id == "menuBtn" || event.target.id == "menuContent_territory" || $(event.target).parents("#menuContent_territory").length>0)) {
	  			hideMenu_territory();
	  		}
	  	}
	  //-------------------------------------------------------------------------------------------------------------------------------------------

		$(document).ready(function(){
			//$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			$.fn.zTree.init($("#treeDemo_category"), setting);
			$.fn.zTree.init($("#treeDemo_territory"), setting_territory);
		}); 
	</SCRIPT>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="wTerritoryCategoryController.do?doAdd" tiptype="2" >
				<input id="id" name="id" type="hidden" value="${wTerritoryCategoryPage.id }">
				
				<input id="categoryId" type="hidden"  name="categoryId" type="text" style="width: 150px" class="inputxt" >
		     	<input id="territoryId" type="hidden" name="territoryId" type="text" style="width: 150px" class="inputxt" >
		     	<input id="categoryparentid" type="hidden" name="categoryparentid" type="text" style="width: 150px" class="inputxt" >
		     	<input id="territoryparentid" type="hidden" name="territoryparentid" type="text" style="width: 150px" class="inputxt" >
				
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">分类名称:</label>
		     	 <input id="categoryName" name="categoryName" type="text" style="width: 150px" class="inputxt" >
		     	 &nbsp;<a id="menuBtn" href="#" onclick="showMenu_category('categoryId','categoryName','categoryparentid'); return false;">选择</a>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">区域名称:</label>
		     	 <input id="territoryName" name="territoryName" type="text" style="width: 150px" class="inputxt" >
		     	  &nbsp;<a id="menuBtn" href="#" onclick="showMenu_territory('territoryId','territoryName','territoryparentid'); return false;">选择</a>
		      <span class="Validform_checktip"></span>
		    </div>
			
		     	 
		    
			<div class="form" >
		      <label class="Validform_label">价钱范围:</label>
		     	 <input id="price" name="price" type="text" style="width: 150px" class="inputxt" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
		 							<!-- 图片上传 -->
		<tr> 
		    <td align="right"><label class="Validform_label"> <t:mutiLang langKey="图片上传"/>: </label></td>
			  <td class="value" nowrap colspan="3">
			  
			  <input type="file" name="insuraceImage_u" id="insuraceImage_u" /> 
			  <a class="easyui-linkbutton" href="javascript:void(0)" onclick="uploadPic()">上传</a> 
			  <input type="hidden" id="corporateDocumentsUpload" name="imgRootPath" />
			  <input type="hidden" id="categoryImage" name="categoryImage" />
            </td>
		</tr>
		
		<tr>
		<td colspan="4">
		<img id="prePic" src="${imgRootPath}" alt="缩略图" width="100" height="100" />
		</td>
		</tr>
	    </fieldset>
  </t:formvalid>
   <div id="menuContent_category" class="menuContent" style="display:none; position: absolute;">
		<ul id="treeDemo_category" class="ztree" style="margin-top:0; width:160px; background-color: #F4F4F4;"></ul>
	</div>
	
	 <div id="menuContent_territory" class="menuContent" style="display:none; position: absolute;">
		<ul id="treeDemo_territory" class="ztree" style="margin-top:0; width:160px; background-color: #F4F4F4;"></ul>
	</div>
 </body>
  <script type="text/javascript"  src = "/webpage/ssb/warmline/business/territoryandcategory/wTerritoryCategory.js"></script> 	
	