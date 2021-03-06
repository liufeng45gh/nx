<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
   <title>版本号更新管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" href="plug-in/uploadify/css/uploadify.css" type="text/css"></link>
  <script type="text/javascript" src="plug-in/uploadify/jquery.uploadify-3.1.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
   $(function() { 
	  choosePic();
  });
   function choosePic() {
	  	$('#insuraceImage_u').uploadify({
	  		buttonText : '上传APP',
				progressData : 'speed',
				multi : false,
				height : 25,
				overrideEvents : [ 'onDialogClose' ],
				fileTypeDesc : '文件格式:',
				fileTypeExts : '*.*',
				fileSizeLimit : '50MB',
				swf : 'plug-in/uploadify/uploadify.swf',
				uploader : 'wVersionUpdateManagementController.do?uploadPic&sessionId=${pageContext.session.id}',
				auto : false,
				onUploadSuccess : function(file, data, response) {
					if (data) {
						var d = $.parseJSON(data);
						if (d.success) {
							$("#prePic").attr("src",d.obj);
							$("#corporateDocumentsUpload").val(d.obj);
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="wVersionUpdateManagementController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${fVersionUpdateManagementPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${wVersionUpdateManagementPage.createDate }">
		
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							版本号:
						</label>
					</td>
					<td class="value">
					     	 <input id="versionNumber" name="versionNumber" type="text" style="width: 150px" class="inputxt" datatype="n">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">版本号</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							版本名称:
						</label>
					</td>
					<td class="value">
						 <input id="versionName" name="versionName" type="text" style="width: 150px" class="inputxt" datatype="*">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">版本名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							版本描述:
						</label>
					</td>
					<td class="value">
						  	 <textarea style="width:600px;" class="inputxt" rows="6" id="versionDescription" name="versionDescription"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">版本描述</label>
						</td>
				</tr>
				<tr> 
				    <td align="right"><label class="Validform_label"> <t:mutiLang langKey="上传APP"/>: </label></td>
					 <td class="value" nowrap>
<%-- 						  <img id="prePic" src="${user.uploadUrl}" alt="上传APP" width="100" height="100" /> --%>
						  <input type="file" name="insuraceImage_u" id="insuraceImage_u" /> 
						  <a class="easyui-linkbutton" href="javascript:void(0)" onclick="uploadPic()">上传</a> 
						  <input type="hidden" id="corporateDocumentsUpload" name="uploadUrl" datatype="*"/>
		            </td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/ssb/warmline/business/wversionupdatemanagement/wVersionUpdateManagement.js"></script>		
