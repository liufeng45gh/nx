<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
 <%--update-start--Author:jg_renjie  Date:20160320 for：#942 【组件封装】组织机构弹出模式，目前是列表，得改造成树方式--%>
   <link rel="stylesheet" href="plug-in/uploadify/css/uploadify.css" type="text/css"></link>
  <script type="text/javascript" src="plug-in/uploadify/jquery.uploadify-3.1.js"></script>
    <script>
		function openDepartmentSelect() {
			$.dialog.setting.zIndex = getzIndex(); 
			var orgIds = $("#orgIds").val();
			
			$.dialog({content: 'url:departController.do?departSelect&orgIds='+orgIds, zIndex: 2100, title: '组织机构列表', lock: true, width: '400px', height: '350px', opacity: 0.4, button: [
			   {name: '<t:mutiLang langKey="common.confirm"/>', callback: callbackDepartmentSelect, focus: true},
			   {name: '<t:mutiLang langKey="common.cancel"/>', callback: function (){}}
		   ]}).zindex();
		}
			
		function callbackDepartmentSelect() {
			  var iframe = this.iframe.contentWindow;
			  var treeObj = iframe.$.fn.zTree.getZTreeObj("departSelect");
			  var nodes = treeObj.getCheckedNodes(true);
			  if(nodes.length>0){
			  var ids='',names='';
			  for(i=0;i<nodes.length;i++){
			     var node = nodes[i];
			     ids += node.id+',';
			    names += node.name;
			 }
			 $('#departname').val(names);
			 $('#departname').blur();		
			 $('#orgIds').val(ids);		
			}
		}
		
		function callbackClean(){
			$('#departname').val('');
			 $('#orgIds').val('');	
		}
		
		function setOrgIds() {}
		$(function(){
			$("#departname").prev().hide();
		});
		
		 $(function() {
		      choosePic();
		  });
		 
		  function choosePic() {
			  	$('#insuraceImage_u').uploadify({
			  		buttonText : '选择头像',
						progressData : 'speed',
						multi : false,
						height : 25,
						overrideEvents : [ 'onDialogClose' ],
						fileTypeDesc : '文件格式:',
						fileTypeExts : '*.jpg;*,jpeg;*.png;*.gif;*.bmp;*.ico;*.tif',
						fileSizeLimit : '15MB',
						swf : 'plug-in/uploadify/uploadify.swf',
						uploader : 'userController.do?uploadPic&sessionId=${pageContext.session.id}',
						auto : false,
						onUploadSuccess : function(file, data, response) {
							if (data) {
								var d = $.parseJSON(data);
								if (d.success) {
									$("#prePic").attr("src",d.obj);
									$("#corporateDocumentsUpload").val(d.attributes.name);
									$('#insuraceImage_u').uploadify("upload", "*");
								}
							} 
						}
			  	});
			  }
		 
		  function uploadPic(){
				$('#insuraceImage_u').uploadify("upload","*");
			}
 <%--update-end--Author:zhangguoming  Date:20140825 for：添加组织机构combobox多选的处理方法--%>
    </script>
    <%--update-end--Author:jg_renjie  Date:20160320 for：#942 【组件封装】组织机构弹出模式，目前是列表，得改造成树方式--%>
</head>
<body  scroll="no">
<%--update-start--Author:zhangguoming  Date:20140825 for：格式化页面代码 并 添加组织机构combobox多选框--%>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="userController.do?saveUser2" beforeSubmit="setOrgIds">
	<input id="id" name="id" type="hidden" value="${user.id }">
	<input id="subordinateAdmin" name="subordinateAdmin" type="hidden" value="${user.subordinateAdmin }">
	<input id="userType" name="userType" type="hidden" value="1">
	
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="25%" nowrap>
                <label class="Validform_label">  <t:mutiLang langKey="昵称"/>:  </label>
            </td>
			<td class="value" width="85%">
                <c:if test="${user.id!=null }"> ${user.userName } </c:if>
                <c:if test="${user.id==null }">
                    <input id="userName" class="inputxt" name="userName" validType="t_s_base_user,userName,id" value="${user.userName}" datatype="s2-10" />
                    <span class="Validform_checktip"> <t:mutiLang langKey="username.rang2to10"/></span>
                </c:if>
            </td>
		
			<td align="right" width="10%" nowrap><label class="Validform_label"> <t:mutiLang langKey="真实姓名"/>: </label></td>
			<td class="value" width="10%">
                <input id="realName" class="inputxt" name="realName" value="${user.realName }" datatype="s2-10">
                <span class="Validform_checktip"><t:mutiLang langKey="fill.realname"/></span>
            </td>
		</tr>
		
		<c:if test="${user.id==null }">
			<tr>
				<td align="right"><label class="Validform_label"> <t:mutiLang langKey="密码"/>: </label></td>
				<td class="value">
                    <input type="password" class="inputxt" value="" name="password" plugin="passwordStrength" datatype="*6-18" errormsg="" />
                    <span class="passwordStrength" style="display: none;">
                        <span><t:mutiLang langKey="common.weak"/></span>
                        <span><t:mutiLang langKey="common.middle"/></span>
                        <span class="last"><t:mutiLang langKey="common.strong"/></span>
                    </span>
                    <span class="Validform_checktip"> <t:mutiLang langKey="password.rang6to18"/></span>
                </td>
			
				<td align="right"><label class="Validform_label"> <t:mutiLang langKey="重复密码"/>: </label></td>
				<td class="value">
                    <input id="repassword" class="inputxt" type="password" value="${user.password}" recheck="password" datatype="*6-18" errormsg="两次输入的密码不一致！">
                    <span class="Validform_checktip"><t:mutiLang langKey="common.repeat.password"/></span>
                </td>
			</tr>
		</c:if>
		<tr>
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="角色"/>: </label></td>
			<td class="value" nowrap>
                <input name="roleid" name="roleid" type="hidden" value="${id}" id="roleid">
                <input name="roleName" class="inputxt" value="${roleName }" id="roleName" readonly="readonly" datatype="*" />
                <t:choose hiddenName="roleid" hiddenid="id" url="userController.do?fictitiousUserRoles" name="roleList"
                          icon="icon-search" title="common.role.list" textname="roleName" isclear="true" isInit="true"></t:choose>
                <span class="Validform_checktip"><%-- <t:mutiLang langKey="role.muti.select"/> --%></span>
            </td>
		</tr>
		
		
		<tr>
		    <td align="right"><label class="Validform_label"> <t:mutiLang langKey="图片上传"/>: </label></td>
			<td class="value" nowrap colspan="1">
			  	<input type="file" name="insuraceImage_u" id="insuraceImage_u" /> 
			 	<a class="easyui-linkbutton" href="javascript:void(0)" onclick="uploadPic()">上传</a> 
			 	<input type="hidden" id="corporateDocumentsUpload" name="headPortrait" value="${user.headPortrait}" datatype="*"/>
            </td>
            <td colspan="3">
				<img id="prePic" src="${user.headPortrait}" alt="缩略图" width="100" height="100" />
		</td>	
		</tr>
		<%--<tr>
		 <td align="right" nowrap><label class="Validform_label">  <t:mutiLang langKey="电话区号"/>: </label></td>
			<td class="value">
			<t:comboBox url="wTerritoryController.do?combox" name="areaCode" text="text" id="id" multiple="false" selectedValue="${user.areaCode}"></t:comboBox>
            </td> --%>
        	<%-- <td align="right" nowrap><label class="Validform_label">  <t:mutiLang langKey="手机号"/>: </label></td>
			<td class="value">
			<input class="inputxt" name="areaCode" value="86" type="hidden"/>
            		<select name="phone"  style="width: 150px" datatype="*">
            			<option value="">请选择手机号</option>
            			<option value="18701406553" <c:if test="${user.phone eq 18701406553}">selected = "selected"</c:if>>18701406553</option>
            			<option value="18811024655" <c:if test="${user.phone eq 18811024655}">selected = "selected"</c:if>>18811024655</option>
            			<option value="13569409971" <c:if test="${user.phone eq 13569409971}">selected = "selected"</c:if>>13569409971</option>
            			<option value="18701403132" <c:if test="${user.phone eq 18701403132}">selected = "selected"</c:if>>18701403132</option>
            			<option value="13611194617" <c:if test="${user.phone eq 13611194617}">selected = "selected"</c:if>>13611194617</option>
            			<option value="18410191260" <c:if test="${user.phone eq 18410191260}">selected = "selected"</c:if>>18410191260</option>
            			<option value="18701405838" <c:if test="${user.phone eq 18701405838}">selected = "selected"</c:if>>18701405838</option>
            		</select>
                <span class="Validform_checktip"></span>
            </td> 
        </tr>--%>
	</table>
</t:formvalid>
<%--update-end--Author:zhangguoming  Date:20140825 for：格式化页面代码 并 添加组织机构combobox多选框--%>
</body>