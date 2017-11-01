  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
   <t:datagrid name="applicantNumber" checkbox="false" fitColumns="true" title="申请人" actionUrl="wHelpMessageController.do?applicantNumberDatagrid"
    idField="id" fit="true" queryMode="group">
     <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="申请人id"  field="helpPeopleId" hidden="true"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="申请人"  field="helpPeople"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="申请时间"  field="createDate"  formatter="yyyy-MM-dd hh:mm:ss"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="真实姓名"  field="realName"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="手机区号"  field="areaCode"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="手机号"  field="phone"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="性别"  field="gender"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="年龄"  field="age"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="申请状态"  field="messageType"  dictionary="messageTyp"  width="120"></t:dgCol>
  </t:datagrid>
 <script src = "webpage/ssb/warmline/business/worder/wOrderList.js"></script>		
 <script type="text/javascript">

 </script>
 

 
 
 
 

 