<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<HTML>
<HEAD>
	<TITLE>ztree</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<!-- <link rel="stylesheet" href="../../../css/demo.css" type="text/css"> -->
	<link rel="stylesheet" href="/../warmline/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="/../warmline/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/../warmline/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="/../warmline/js/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="/../warmline/js/jquery.ztree.exedit.js"></script>
	<script type="text/javascript">

	var setting = {
			/* async: {
				enable: true,
				url:"wCategoryController.do?categoryList",
				autoParam:["id"],
				//otherParam:{"id":"0"},
				 type : "POST",
   				dataType : "json",
				dataFilter: ajaxDataFilter
			},  */
			view: {expandSpeed:"",
				addHoverDom: addHoverDom,
				removeHoverDom: removeHoverDom,
				selectedMulti: false
			},
			edit: {
				enable: true,
				showRemoveBtn: showRemoveBtn,
				showRenameBtn: showRenameBtn
			},
			 data: { // 必须使用data  
	                simpleData: {
	                    enable: true,
	                    idKey: "id", // id编号命名 默认  
	                    pIdKey: "categoryParentid", // 父id编号命名 默认   
	                    rootPId: "0"   // 用于修正根节点父节点数据，即 pIdKey 指定的属性值  
	                },
	                key:{
	                	name:"categoryName"
	                }
	            },
			callback: {
				beforeRemove: beforeRemove,
				beforeRename: beforeRename
				
			}
		};

		/**
		**同步加载树节点
		**/
		var zNodes =[];
		function createTree(){
			$.ajax({
				type : 'POST',
				url : 'wCategoryController.do?categoryList',
				dataType:'json',		
				success : function(data) {
					zNodes=data;
					$.fn.zTree.init($("#treeDemo"), setting, zNodes);
				}
			});
		}
	
		function showRemoveBtn(treeId, treeNode) {
			if(treeNode.id==0){
				return false;
			}else{
				return true;
			}
			
		}
		function showRenameBtn(treeId, treeNode) {
			if(treeNode.id==0){
				return false;
			}else{
				return true;
			}
			
		} 
	
		
		function ajaxDataFilter(treeId, parentNode, responseData) {
			if(null != responseData){
				for (var i=0, l=responseData.length; i<l; i++) {
					var id=responseData[i].id;
					 if(id==categoryParentid){
						 responseData[i].open=true;
					 }
					//responseData[i].categoryName = responseData[i].categoryName.replace(/\.n/g, '.');
				}
				return responseData;
			}else{
				return null;
			}
			
		}
		//删除节点
		function beforeRemove(treeId, treeNode) {
			var isParent=treeNode.isParent;
			var id=treeNode.id;
			
			var categoryParentid=treeNode.categoryParentid;
			var parentIsParent=treeNode.getParentNode().isParent;//判断父亲节点是否为父级节点
			var parentNode=treeNode.getParentNode();
			var treeNodeLen=parentNode.children.length;
			
			if(confirm("确认删除 节点 -- " + treeNode.categoryName + " 吗？")){
				$.ajax({
					type : 'POST',
					url : 'wCategoryController.do?doDel',
					data:{
						id:id,
						isParent:isParent,
						categoryParentid:categoryParentid,
						treeNodeLen:treeNodeLen,
						parentIsParent:parentIsParent
					},
					success : function(data) {
						return true;
					}
				});
				
			}else{
				return false;
			}
			
			
		}		
		//编辑节点
		function beforeRename(treeId, treeNode, newName) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			if (newName.length == 0) {
				setTimeout(function() {
					zTree.cancelEditName();
					alert("节点名称不能为空.");
				}, 0);
				return false;
			}else{
				var categoryParentid=treeNode.categoryParentid;
				var categoryName=newName;
				var preNodeCategoryCode=null;
				var preNodeCategorySort=null;
				//var preNodeCategoryLevel=treeNode.getParentNode().level;
				var parentIsParent=treeNode.getParentNode().isParent;//判断父亲节点是否为父级节点
				var isParent=treeNode.isParent;
				var id=treeNode.id;
				var categoryLevel=treeNode.level;
				
				if(null != treeNode.getPreNode()){
					preNodeCategoryCode=treeNode.getPreNode().categoryCode;
					preNodeCategorySort=treeNode.getPreNode().categorySort
					preNodeCategoryLevel=treeNode.getPreNode().categoryLevel;
				}else{
					preNodeCategoryCode=treeNode.getParentNode().categoryCode;//如果同级没有，就找父节点
					preNodeCategorySort=treeNode.getParentNode().categorySort;//如果同级没有，就找父节点
				}
				$.ajax({
					type : 'POST',
					url : 'wCategoryController.do?doAdd',
					dataType:'json',		
					data:{
						id:id,
						categoryLevel:categoryLevel,
						categoryParentid:categoryParentid,
						categoryName:categoryName,
						preNodeCategorySort:preNodeCategorySort,
						preNodeCategoryCode:preNodeCategoryCode,
						//preNodeCategoryLevel:preNodeCategoryLevel,
						parentIsParent:parentIsParent,
						isParent:isParent
					},
					success : function(data) {
						//createTree();
					}
				});
				return true;
			}
		}

		
		 
		//添加节点
		var newCount = 1;
		function addHoverDom(treeId, treeNode) {
			var sObj = $("#" + treeNode.tId + "_span");
			
			if(treeNode.level<2){
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
			}
			
		};
		//删除节点
		function removeHoverDom(treeId, treeNode) {
				$("#addBtn_"+treeNode.tId).unbind().remove();
		};

		
		
		$(document).ready(function(){
			//$.fn.zTree.init($("#treeDemo"), setting);
			//同步加载
			createTree();
			
		});	
	</script>
	<style type="text/css">
		.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	</style>
</HEAD>

<BODY>
<h1>分类树形结构</h1>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	
</div>
</BODY>
</HTML>