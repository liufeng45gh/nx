<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

   <t:datagrid name="orderUsers" checkbox="false" fitColumns="true" title="发布人信息" actionUrl="userController.do?orderUserDatagrid"
    idField="id" fit="true" queryMode="group">
   <t:dgCol title="common.id" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="common.username" sortable="false" field="userName"  width="80" ></t:dgCol>
	<t:dgCol title="common.real.name" field="realName" query="false"  width="80"></t:dgCol>
	<t:dgCol title="common.role" field="userKey"  width="80" ></t:dgCol>
	<t:dgCol title="common.createtime" field="createDate"  formatter="yyyy-MM-dd hh:mm:ss"  width="140" ></t:dgCol>
	<t:dgCol title="年龄"  field="age"    queryMode="group"  width="60" ></t:dgCol>
	<t:dgCol title="证件类型"  field="documentType"   queryMode="group"   width="80"></t:dgCol>
	<t:dgCol title="证件号"  field="identificationNumber"   queryMode="group"  width="160"></t:dgCol>
	<t:dgCol title="手机号"  field="phone"    queryMode="group"  width="100"></t:dgCol>
	<t:dgCol title="银行卡号"  field="bankCard"    hidden="true"  queryMode="group"  width="120" ></t:dgCol>
	<t:dgCol title="职业范围"  field="occupationScope"  hidden="true"  queryMode="group"  width="120" ></t:dgCol>
	<t:dgCol title="国籍"  field="nationality"  hidden="true"  queryMode="group"  width="120" ></t:dgCol>
	<t:dgCol title="城市"  field="city"   queryMode="group"  width="120" ></t:dgCol>
	<t:dgCol title="母语"  field="motherTongue"    queryMode="group"  width="80"></t:dgCol>
	<t:dgCol title="第二语言"  field="secondLanguage"    queryMode="group"  width="80" ></t:dgCol>
	<t:dgCol title="职业"  field="occupation"   hidden="true"  queryMode="group"  width="120" ></t:dgCol>
	<t:dgCol title="接发单数"  field="singular"  queryMode="group"   width="80"></t:dgCol>
	<t:dgCol title="被举报次数"  field="reportCount"   hidden="true"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="用户类别"  field="userType" dictionary="userType" hidden="true"   queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="审核状态"  field="auditStatus"  hidden="true"   queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="标签"  field="label"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
  </t:datagrid>

 <script src = "webpage/ssb/warmline/business/worder/wOrderList.js"></script>		
 <script type="text/javascript">

 </script>
 

