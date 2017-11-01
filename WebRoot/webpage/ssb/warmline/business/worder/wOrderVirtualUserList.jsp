<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="wOrderList" checkbox="false" fitColumns="true" title="紧急订单" actionUrl="wOrderController.do?virtualUserDatagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="标题"  field="title"    queryMode="group" width="120" ></t:dgCol>
  <%--  <t:dgCol title="副标题"  field="subtitle"   hidden="true"  queryMode="group" width="120" ></t:dgCol> --%>
   <%-- <t:dgCol title="描述"  field="description"   hidden="true" queryMode="group" width="120" ></t:dgCol> --%>
       <t:dgCol title="订单号"  field="orderNumber"   query="true"  queryMode="single"  width="100"  ></t:dgCol>
   <t:dgCol title="类别"  field="category" dictionary="category"   queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="价格"  field="price"    queryMode="group"  width="50"></t:dgCol>
   <t:dgCol title="发布人姓名"  field="issuer"   query="true"  queryMode="single" width="80" ></t:dgCol>
   <t:dgCol title="发布人id"  field="issuerId"  hidden="true"   queryMode="group"  ></t:dgCol>
   <t:dgCol title="发布人电话"  field="phone"   query="true"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="位置信息"  field="location"    queryMode="group"  ></t:dgCol>
   <t:dgCol title="城市"  field="city"    queryMode="group" ></t:dgCol>
   <t:dgCol title="接单人姓名"  field="orderPersonName"   query="true"  queryMode="single" width="80"  ></t:dgCol>
   <t:dgCol title="接单人电话"  field="orderPersonPhone"  query="true"   queryMode="single" width="100"  ></t:dgCol>
   <t:dgCol title="求助类型"  field="seekStatus" dictionary="seekstatus" hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="接单人id"  field="orderPersonId"    hidden="true" queryMode="single"  ></t:dgCol>
   	<t:dgCol title="订单开始时间"  field="startTime" formatter="yyyy-MM-dd hh:mm:ss"   query="true" queryMode="group" width="130"  ></t:dgCol>
   	<t:dgCol title="订单结束时间"  field="endTime" formatter="yyyy-MM-dd hh:mm:ss"   query="true" queryMode="group" width="130"  ></t:dgCol>
  	<t:dgCol title="订单状态"  field="orderStatus" dictionary="orderstatu" query="true" width="80" queryMode="single"  ></t:dgCol>
  	<t:dgCol title="下单时间"  field="orderTime" formatter="yyyy-MM-dd hh:mm:ss"   query="true" queryMode="group" width="130"  ></t:dgCol>
  	<t:dgCol title="支付状态"  field="buyStatus"     dictionary="buyStatus" queryMode="single"  ></t:dgCol>
   <t:dgCol title="备注"  field="remarks" queryMode="single" ></t:dgCol>
   <t:dgCol title="操作" field="opt"></t:dgCol>
   <%-- <t:dgDelOpt title="删除" url="wOrderController.do?doDel&id={id}" /> --%>
   <t:dgFunOpt funname="choicePickOne(id)" exp="orderStatus#eq#ORDERSTATU_001" operationCode="choicePickOne"  title="选择接单人"   ></t:dgFunOpt>
   <t:dgFunOpt operationCode="szqm" funname="szqm(id)" title="备注" />
   <t:dgToolBar title="录入" icon="icon-add" url="wOrderController.do?goVirtualUserAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="wOrderController.do?goVirtualUserUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="wOrderController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
  <%--  <t:dgToolBar title="查看" icon="icon-search" url="wOrderController.do?goUpdate" funname="detail"></t:dgToolBar> --%>
   <%-- <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar> --%>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <%-- <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/ssb/warmline/business/worder/wOrderList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#wOrderListtb").find("input[name='orderTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd '});});
 			$("#wOrderListtb").find("input[name='orderTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd '});});
 			$("#wOrderListtb").find("input[name='startTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd '});});
 			$("#wOrderListtb").find("input[name='startTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd '});});
 			$("#wOrderListtb").find("input[name='endTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd '});});
 			$("#wOrderListtb").find("input[name='endTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd '});});
 });
 
 function choicePickOne(id) {
	 $.dialog({
			content:'url:wOrderController.do?choicePickOne&orderId=' + id, 
			zIndex: 2000, 
			title: '选择接单人', 
			lock: true, 
			width: 600, 
			height: 600, 
			opacity: 0.4, 
			close:function() {
				 $(this).dialog("close");
			},
			button: [{name: '选择接单人', callback: choiceConnectSingle},
			         {name:'<t:mutiLang langKey="common.cancel"/>', callback: function (){}}]
		}); 
	}
 
//
 function choiceConnectSingle(){
		var iframe = this.iframe.contentWindow;
		var userId = iframe.$("#currentId").val();
		if(userId == ""){
			$.messager.alert('提示','请选择接单人');
		}else{
		$.ajax({
	 		url:"wOrderController.do?choiceSingle&userId="+userId,
	 		dataType:'json',
	 		type: "POST",
	 		success:function(data){
	 			if(data.success == false){
	 				$.messager.alert('提示',data.msg);
	 			}else{
	 				window.location.reload();
	 			}
	 		}
	 	});
		}
	}
 
 
 
 function szqm(woId) {
		createwindow('备注', 'wOrderController.do?doCheck&id=' + woId);
}
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'wOrderController.do?upload', "wOrderList");
}

//导出
function ExportXls() {
	JeecgExcelExport("wOrderController.do?exportXls","wOrderList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("wOrderController.do?exportXlsByT","wOrderList");
}
 </script>