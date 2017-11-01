<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>w_cash_account</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="wCashAccountController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${wCashAccountPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户id:
						</label>
					</td>
					<td class="value">
					     	 <input id="userId" name="userId" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户id</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							用户名:
						</label>
					</td>
					<td class="value">
					     	 <input id="userName" name="userName" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户名</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							支付宝状态 0 未绑定  1绑定:
						</label>
					</td>
					<td class="value">
					     	 <input id="alipayBindingState" name="alipayBindingState" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支付宝状态 0 未绑定  1绑定</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							支付宝账户:
						</label>
					</td>
					<td class="value">
					     	 <input id="alipayAccount" name="alipayAccount" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">支付宝账户</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							微信绑定状态  0 未绑定  1绑定:
						</label>
					</td>
					<td class="value">
					     	 <input id="wechatState" name="wechatState" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">微信绑定状态  0 未绑定  1绑定</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							微信账户:
						</label>
					</td>
					<td class="value">
					     	 <input id="wechatAccount" name="wechatAccount" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">微信账户</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							银行卡绑定状态 0 未绑定 1绑定:
						</label>
					</td>
					<td class="value">
					     	 <input id="bankCardState" name="bankCardState" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">银行卡绑定状态 0 未绑定 1绑定</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							持卡人:
						</label>
					</td>
					<td class="value">
					     	 <input id="cardholder" name="cardholder" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">持卡人</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							银行卡号:
						</label>
					</td>
					<td class="value">
					     	 <input id="bankCard" name="bankCard" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">银行卡号</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							银行卡类型:
						</label>
					</td>
					<td class="value">
					     	 <input id="cardType" name="cardType" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">银行卡类型</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							银行卡预留手机号:
						</label>
					</td>
					<td class="value">
					     	 <input id="reservePhone" name="reservePhone" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">银行卡预留手机号</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/ssb/warmline/business/wcashaccount/wCashAccount.js"></script>		
