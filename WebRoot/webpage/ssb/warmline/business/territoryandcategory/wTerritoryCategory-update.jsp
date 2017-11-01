<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>类目区域中间表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
   <link rel="stylesheet" href="plug-in/uploadify/css/uploadify.css" type="text/css"></link>
  <script type="text/javascript" src="plug-in/uploadify/jquery.uploadify-3.1.js"></script>
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
  
  
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="wTerritoryCategoryController.do?doUpdate" tiptype="1" >
				<input id="id" name="id" type="hidden" value="${wTerritoryCategoryPage.id }">
				<input id="categoryId" name="categoryId" type="hidden" value='${wTerritoryCategoryPage.categoryId}'>
				<input id="territoryId" name="territoryId" type="hidden" value='${wTerritoryCategoryPage.territoryId}'>
				<input id="categoryparentid" name="categoryparentid" type="hidden" value='${wTerritoryCategoryPage.categoryparentid}'>
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">分类名称:</label>
		     	 <input id="categoryName" name="categoryName" type="text" style="width: 150px" class="inputxt" readonly="readonly" value='${wTerritoryCategoryPage.categoryName}'>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">区域名称:</label>
		     	 <input id="territoryName" name="territoryName" type="text" style="width: 150px" class="inputxt" readonly="readonly" value='${wTerritoryCategoryPage.territoryName}'>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">价钱范围:</label>
		     	 <input id="price" name="price" type="text" style="width: 150px" class="inputxt"datatype="*"  value='${wTerritoryCategoryPage.price}'>
		      <span class="Validform_checktip"></span>
		    </div>
			
			<tr> 
			    <td align="right"><label class="Validform_label"> <t:mutiLang langKey="图片上传"/>: </label></td>
				  <td class="value" nowrap colspan="3">
				  
				  <input type="file" name="insuraceImage_u" id="insuraceImage_u" /> 
				  <a class="easyui-linkbutton" href="javascript:void(0)" onclick="uploadPic()">上传</a> 
				  <input type="hidden" id="corporateDocumentsUpload" name="imgRootPath" />
				  <input type="hidden" id="categoryImage" name="categoryImage" value='${wTerritoryCategoryPage.categoryImage}'/>
	            </td>
			</tr>
		
			<tr>
				<td colspan="4">
				<img id="prePic" src="${imgRootPath}" alt="缩略图" width="100" height="100" />
				</td>
			</tr>
	    </fieldset>
  </t:formvalid>
 </body>
  <script src = "webpage/ssb/warmline/business/territoryandcategory/wTerritoryCategory.js"></script>		
