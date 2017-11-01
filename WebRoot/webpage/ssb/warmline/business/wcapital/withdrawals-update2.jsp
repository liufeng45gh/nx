<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>提现管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="wCapitalController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${wCapitalPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								申请用户:
							</label>
						</td>
						<td class="value">
						     	 <input id="userName" name="userName" type="text" style="width: 150px"  disabled="true" class="inputxt"  value='${wCapitalPage.userName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">交易用户名称</label>
						</td>
						
						<td align="right">
							<label class="Validform_label">
								申请时间:
							</label>
						</td>
						<td class="value">
									  <input id="tradeTime" name="tradeTime" type="text" style="width: 150px" disabled="true"  class="Wdate" onClick="WdatePicker()" value='<fmt:formatDate value='${wCapitalPage.tradeTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">交易时间</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								账户类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="transferType" name="transferType" type="text" style="width: 150px"  disabled="true" class="inputxt"  value='${wCapitalPage.transferType}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">账户类型</label>
						</td>
						
						
						
						<td align="right">
							<label class="Validform_label">
								收款账户:
							</label>
						</td>
						<td class="value">
						     	 <input id="intoAccount" name="intoAccount" type="text" style="width: 150px" class="inputxt"  disabled="true" value='${wCapitalPage.intoAccount}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收款账户</label>
						</td>
						
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								提现金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="amout" name="amout" type="text" style="width: 150px"  disabled="true" class="inputxt"  value='${wCapitalPage.amout}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">提现金额</label>
						</td>
						
						<td align="right">
							<label class="Validform_label">
								审批人:
							</label>
						</td>
						<td class="value">
						     	 <input id="approvalName" name="approvalName" type="text" style="width: 150px" class="inputxt"  value='${wCapitalPage.approvalName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批人</label>
						</td>
						
					</tr>
					
					<tr>
						
						<td align="right">
							<label class="Validform_label">
								审批时间:
							</label>
						</td>
						<td class="value">
									  <input id="approvalTime" name="approvalTime" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" value='<fmt:formatDate value='${wCapitalPage.approvalTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批时间</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								审批状态:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="approvalType" type="list"
										typeGroupCode="approvtype" defaultVal="${wCapitalPage.approvalType}" hasLabel="false"  title="审批状态"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批状态</label>
						</td>
					</tr>
					
					<tr>
						
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value" colspan="3">
						  <textarea id="remarks" name="remarks" class="inputxt" name="remarks" type="text"   style="margin: 0px; width: 501px; height: 80px;">${wCapitalPage.remarks}</textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/ssb/warmline/business/wcapital/wCapital.js"></script>		
