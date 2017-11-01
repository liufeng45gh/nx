<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>w_question_answer</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="wQuestionAnswerController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${wQuestionAnswerPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							问题:
						</label>
					</td>
					<td class="value">
					     	 <textarea style="width:400px;" class="inputxt"  type="text" rows="6" id="problem" name="problem"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">问题</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							答案:
						</label>
					</td>
					<td class="value">
					     	 <textarea style="width:400px;" class="inputxt"   type="text" rows="6" id="answer" name="answer"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">答案</label>
						</td>
				</tr>
				<!-- <tr>
					<td align="right">
						<label class="Validform_label">
							创建时间:
						</label>
					</td>
					<td class="value">
							   <input id="creatTime" name="creatTime" type="text" style="width: 150px" 
					      						 class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">创建时间</label>
						</td>
				</tr> -->
				<tr>
					<td align="right">
						<label class="Validform_label">
							序号:
						</label>
					</td>
					<td class="value">
					     	 <input id="serialNumber" name="serialNumber" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">序号</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/ssb/warmline/business/wquestionanswer/wQuestionAnswer.js"></script>		
