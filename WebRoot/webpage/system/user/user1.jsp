<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<t:base type="jquery,easyui,tools"></t:base>
 <%--update-start--Author:jg_renjie  Date:20160320 for：#942 【组件封装】组织机构弹出模式，目前是列表，得改造成树方式--%>
    <%--update-start--Author:zhangguoming  Date:20140825 for：添加组织机构combobox多选的处理方法--%>
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
 <%--update-end--Author:zhangguoming  Date:20140825 for：添加组织机构combobox多选的处理方法--%>
    </script>
    <%--update-end--Author:jg_renjie  Date:20160320 for：#942 【组件封装】组织机构弹出模式，目前是列表，得改造成树方式--%>
</head>
<body  scroll="no">
<%--update-start--Author:zhangguoming  Date:20140825 for：格式化页面代码 并 添加组织机构combobox多选框--%>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="userController.do?saveUser1" beforeSubmit="setOrgIds">
	<input id="id" name="id" type="hidden" value="${user.id }">
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="25%" nowrap>
                <label class="Validform_label">  <t:mutiLang langKey="昵称"/>:  </label>
            </td>
			<td class="value" width="85%">
                <c:if test="${user.id!=null }"> ${user.userName } </c:if>
                <c:if test="${user.id==null }">
                    <input id="userName" class="inputxt" name="userName" validType="t_s_base_user,userName,id" value="${user.userName }" datatype="s2-10" />
                    <span class="Validform_checktip"> <t:mutiLang langKey="username.rang2to10"/></span>
                </c:if>
            </td>
		
			<td align="right" width="10%" nowrap><label class="Validform_label"> <t:mutiLang langKey="真实姓名"/>: </label></td>
			<td class="value" width="10%">
                <input id="realName" class="inputxt" name="realName" value="${user.realName }" datatype="s2-10">
                <span class="Validform_checktip"><t:mutiLang langKey="fill.realname"/></span>
            </td>
		</tr>
		
		<%-- <!-- 图片上传 -->
		<tr> 
		    <td align="right"><label class="Validform_label"> <t:mutiLang langKey="头像"/>: </label></td>
			  <td class="value" nowrap colspan="3">
			  
			  <input type="file" name="insuraceImage_u" id="insuraceImage_u" /> 
			  <a class="easyui-linkbutton" href="javascript:void(0)" onclick="uploadPic()">上传</a> 
			  <input type="hidden" id="corporateDocumentsUpload" name="headPortrait" />
            </td>
		</tr>
		
		<tr>
		<td colspan="4">
		<img id="prePic" src="${imgRootPath}" alt="缩略图" width="600" height="120" />
		</td>
		</tr> --%>
		
		
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
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="组织机构"/>: </label></td>
			<td class="value">
				 <%--update-start--Author:jg_renjie  Date:20160320 for：#942 【组件封装】组织机构弹出模式，目前是列表，得改造成树方式--%>
                <%--update-start--Author:zhangguoming  Date:20140826 for：将combobox修改为combotree--%>
                <%--<select class="easyui-combobox" data-options="multiple:true, editable: false" id="orgSelect" datatype="*">--%>
                <%--<select class="easyui-combotree" data-options="url:'departController.do?getOrgTree', multiple:true, cascadeCheck:false"
                        id="orgSelect" name="orgSelect" datatype="select1">
                update-end--Author:zhangguoming  Date:20140826 for：将combobox修改为combotree
                    <c:forEach items="${departList}" var="depart">
                        <option value="${depart.id }">${depart.departname}</option>
                    </c:forEach>
                </select> --%>
                <%--  <t:departSelect departId="${tsDepart.id }" departName="${tsDepart.departname }"></t:departSelect>--%>
                
                <input id="departname" name="departname" type="text" readonly="readonly" class="inputxt" datatype="*" value="${departname}">
                <input id="orgIds" name="orgIds" type="hidden" value="${orgIds}">
                <a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" id="departSearch" onclick="openDepartmentSelect()">选择</a>
                <a href="#" class="easyui-linkbutton" plain="true" icon="icon-redo" id="departRedo" onclick="callbackClean()">清空</a>
                 <%--update-end--Author:jg_renjie  Date:20160320 for：#942 【组件封装】组织机构弹出模式，目前是列表，得改造成树方式--%>
                <span class="Validform_checktip"><%-- <t:mutiLang langKey="please.muti.department"/> --%></span>
            </td>
		
			<td align="right"><label class="Validform_label"> <t:mutiLang langKey="角色"/>: </label></td>
			<td class="value" nowrap>
                <input name="roleid" name="roleid" type="hidden" value="${id}" id="roleid">
                <input name="roleName" class="inputxt" value="${roleName }" id="roleName" readonly="readonly" datatype="*" />
                <t:choose hiddenName="roleid" hiddenid="id" url="userController.do?roles" name="roleList"
                          icon="icon-search" title="common.role.list" textname="roleName" isclear="true" isInit="true" ></t:choose>
                <span class="Validform_checktip"><%-- <t:mutiLang langKey="role.muti.select"/> --%></span>
            </td>
		</tr>
		<tr>
			<td align="right" nowrap><label class="Validform_label">  <t:mutiLang langKey="电话区号"/>: </label></td>
			<td class="value">
			<t:comboBox url="wTerritoryController.do?combox" name="areaCode" text="text" id="id" multiple="false" selectedValue="${user.areaCode}"></t:comboBox>
            </td>
        	<td align="right" nowrap><label class="Validform_label">  <t:mutiLang langKey="手机号"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="phone" value="${user.phone}"  datatype="*">
                <span class="Validform_checktip"></span>
            </td>
            <%-- <td align="right" nowrap><label class="Validform_label">  <t:mutiLang langKey="年龄"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="age" value="${user.age}"  datatype="*">
                <span class="Validform_checktip"></span>
            </td> --%>
        </tr>
		
		<%-- <tr>
			<td align="right" nowrap><label class="Validform_label">  <t:mutiLang langKey="国籍"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="nationality" value="${user.nationality}" datatype="*">
                <span class="Validform_checktip"></span>
            </td>
             <td align="right" nowrap><label class="Validform_label">  <t:mutiLang langKey="城市"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="city" value="${user.city}" datatype="*">
                <span class="Validform_checktip"></span>
            </td>
            
         </tr>
         <tr>
            <td align="right" nowrap><label class="Validform_label">  <t:mutiLang langKey="母语"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="motherTongue" value="${user.motherTongue}" datatype="*">
                <span class="Validform_checktip"></span>
            </td>
            
             <td align="right" nowrap><label class="Validform_label">  <t:mutiLang langKey="第二语言"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="secondLanguage" value="${user.secondLanguage}" datatype="*">
                <span class="Validform_checktip"></span>
            </td>
            
          </tr>
              <td align="right" nowrap><label class="Validform_label">  <t:mutiLang langKey="所属管理员"/>: </label></td>
				<td class="value">
                <input class="inputxt" name="subordinateAdmin" value="${user.subordinateAdmin}">
                <span class="Validform_checktip"></span>
            </td>
       
       <tr>
            <td align="right" nowrap><label class="Validform_label">  <t:mutiLang langKey="证件类型"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="documentType" value="${user.documentType}" datatype="*">
                <span class="Validform_checktip"></span>
            </td>
			<td align="right" nowrap><label class="Validform_label">  <t:mutiLang langKey="证件号码"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="identificationNumber" value="${user.identificationNumber}" datatype="*">
                <span class="Validform_checktip"></span>
            </td> 
        </tr> --%>
            
	<%-- 	</tr>
		
		
		
		<tr>
			<td align="right" nowrap><label class="Validform_label">  <t:mutiLang langKey="银行卡号"/>: </label></td>
<!-- 			<td class="value"> -->
                <input class="inputxt" name="bankCard" value="${bankCard}">
<!--                 <span class="Validform_checktip"></span> -->
<!--             </td> -->
		</tr>
		
		
		<tr>
            
          
		</tr>


		<tr>
			
            
            
		</tr>
		
		<tr>
			
		</tr>
		
		
		<tr>
			
            
            <td align="right" nowrap><label class="Validform_label">  <t:mutiLang langKey="被举报次数"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="reportCount" value="${reportCount}">
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		
		
		<tr>
			
            
		</tr>
		
		
		<tr>
			<td align="right" nowrap><label class="Validform_label">  <t:mutiLang langKey="审核状态"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="auditStatus" value="${auditStatus}">
                <span class="Validform_checktip"></span>
            </td>
            
            <td align="right" nowrap><label class="Validform_label">  <t:mutiLang langKey="标签"/>: </label></td>
			<td class="value">
                <input class="inputxt" name="label" value="${label}">
                <span class="Validform_checktip"></span>
            </td>
		</tr>
		
		<tr>
			
		</tr> --%>
	</table>
</t:formvalid>
<%--update-end--Author:zhangguoming  Date:20140825 for：格式化页面代码 并 添加组织机构combobox多选框--%>
</body>