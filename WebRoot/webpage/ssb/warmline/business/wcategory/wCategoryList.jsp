<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

	<link rel="stylesheet" href="/../warmline/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="/../warmline/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="/../warmline/js/jquery.ztree.core.js"></script>
	<!--  <script type="text/javascript" src="../../../js/jquery.ztree.excheck.js"></script>
	  <script type="text/javascript" src="../../../js/jquery.ztree.exedit.js"></script>-->
<SCRIPT type="text/javascript">
	var setting = {
			async: {
				enable: true,
				url:"../asyncData/getNodes.php",
				autoParam:["id", "name=n", "level=lv"],
				otherParam:{"otherParam":"zTreeAsyncTest"},
				dataFilter: filter
			},
			view: {expandSpeed:"",
				addHoverDom: addHoverDom,
				removeHoverDom: removeHoverDom,
				selectedMulti: false
			},
			edit: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeRemove: beforeRemove,
				beforeRename: beforeRename
			}
		};

		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}
		function beforeRemove(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.selectNode(treeNode);
			return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
		}		
		function beforeRename(treeId, treeNode, newName) {
			if (newName.length == 0) {
				setTimeout(function() {
					var zTree = $.fn.zTree.getZTreeObj("treeDemo");
					zTree.cancelEditName();
					alert("节点名称不能为空.");
				}, 0);
				return false;
			}
			return true;
		}

		var newCount = 1;
		function addHoverDom(treeId, treeNode) {
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
				+ "' title='add node' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addBtn_"+treeNode.tId);
			if (btn) btn.bind("click", function(){
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
				return false;
			});
		};
		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_"+treeNode.tId).unbind().remove();
		};

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting);
		});
</SCRIPT>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <%-- <t:datagrid name="wCategoryList" checkbox="true" fitColumns="false" title="分类表" actionUrl="wCategoryController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="类别名称"  field="categoryName"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="categoryCode"  field="categoryCode"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="类别排序"  field="categorySort"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="类别父id"  field="categoryParentid"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="类别图片"  field="categoryImage"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="wCategoryController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="wCategoryController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="wCategoryController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="wCategoryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="wCategoryController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid> --%>
  </div>
 </div>
 <script src = "webpage/ssb/warmline/business/wcategroy/wCategoryList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 		
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'wCategoryController.do?upload', "wCategoryList");
}

//导出
function ExportXls() {
	JeecgExcelExport("wCategoryController.do?exportXls","wCategoryList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("wCategoryController.do?exportXlsByT","wCategoryList");
}
 </script>