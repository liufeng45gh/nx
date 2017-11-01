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
						<%-- <td align="right">
							<label class="Validform_label">
								交易用户id:
							</label>
						</td>
						<td class="value">
						     	 <input id="userId" name="userId" type="text" style="width: 150px" class="inputxt"  value='${wCapitalPage.userId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">交易用户id</label>
						</td> --%>
						<td align="right">
							<label class="Validform_label">
								交易用户名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="userName" name="userName" type="text" style="width: 150px" class="inputxt"  value='${wCapitalPage.userName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">交易用户名称</label>
						</td>
						
						<td align="right">
							<label class="Validform_label">
								描述:
							</label>
						</td>
						<td class="value">
						     	 <input id="description" name="description" type="text" style="width: 150px" class="inputxt"  value='${wCapitalPage.description}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">描述</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								交易时间:
							</label>
						</td>
						<td class="value">
									  <input id="tradeTime" name="tradeTime" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" value='<fmt:formatDate value='${wCapitalPage.tradeTime}' type="date" pattern="yyyy-MM-dd"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">交易时间</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								支付方式:
							</label>
						</td>
						<td class="value">
						     	 <input id="payMethod" name="payMethod" type="text" style="width: 150px" class="inputxt"  value='${wCapitalPage.payMethod}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支付方式</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								交易金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="amout" name="amout" type="text" style="width: 150px" class="inputxt"  value='${wCapitalPage.amout}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">交易金额</label>
						</td>
						
						<td align="right">
							<label class="Validform_label">
								资金流水类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="type" name="type" type="text" style="width: 150px" class="inputxt"  value='${wCapitalPage.type}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">资金流水类型</label>
						</td>
					</tr>
					
					<tr>
						<td align="right">
							<label class="Validform_label">
								审核时间:
							</label>
						</td>
						<td class="value">
									  <input id="examineTime" name="examineTime" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" value='<fmt:formatDate value='${wCapitalPage.examineTime}' type="date" pattern="yyyy-MM-dd"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审核时间</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								审核人:
							</label>
						</td>
						<td class="value">
						     	 <input id="examineName" name="examineName" type="text" style="width: 150px" class="inputxt"  value='${wCapitalPage.examineName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审核人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								审批时间:
							</label>
						</td>
						<td class="value">
									  <input id="approvalTime" name="approvalTime" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()" value='<fmt:formatDate value='${wCapitalPage.approvalTime}' type="date" pattern="yyyy-MM-dd"/>'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批时间</label>
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
								审批状态:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="approvalType" type="list"
										typeGroupCode="" defaultVal="${wCapitalPage.approvalType}" hasLabel="false"  title="审批状态"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批状态</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						     	 <input id="remarks" name="remarks" type="text" style="width: 150px" class="inputxt"  value='${wCapitalPage.remarks}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/ssb/warmline/business/wcapital/wCapital.js"></script>		
