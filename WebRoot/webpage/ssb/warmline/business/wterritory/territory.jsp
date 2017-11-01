<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>地域信息</title>
<t:base type="jquery,easyui,tools"></t:base>
<script type="text/javascript">
		
	$(function() {
		$('#cc').combotree({
			url : 'wTerritoryController.do?setPTerritory',
			panelHeight:'auto',
			onClick: function(node){
				$("#territoryId").val(node.id);
			}
		});
		
		if($('#territorylevel').val()=='1'){
			$('#pfun').show();
		}else{
			$('#pfun').hide();
		}
		
		
		$('#territorylevel').change(function(){
			if($(this).val()=='1'){
				$('#pfun').show();
				var t = $('#cc').combotree('tree');
				var nodes = t.tree('getRoots');
				if(nodes.length>0){
					$('#cc').combotree('setValue', nodes[0].id);
					$("#territoryId").val(nodes[0].id);
				}
			}else{
				var t = $('#cc').combotree('tree');
				var node = t.tree('getSelected');
				if(node){
					$('#cc').combotree('setValue', null);
				}
				$('#pfun').hide();
			}
		});
	});
</script>
</head>
<body style="overflow-x: hidden; width:800px; height:900px;">
<t:formvalid formid="formobj" layout="div" dialog="true" refresh="true" action="wTerritoryController.do?saveTerritory">
	<input name="id" type="hidden" value="${wTerritoryEntity.id}">
	<input name="open" type="hidden" value="${wTerritoryEntity.open}">
	<input name="first" type="hidden" value="${wTerritoryEntity.first}">
	<input name="isparent" type="hidden" value="${wTerritoryEntity.isparent}">

	<fieldset class="step">
        <div class="form">
            <label class="Validform_label"> <t:mutiLang langKey="area.name"/>: </label>
            <input name="territoryname" class="inputxt" value="${wTerritoryEntity.territoryname}" datatype="s2-15">
            <span class="Validform_checktip"><t:mutiLang langKey="areaname.rang2to15"/></span>
        </div>
        <div class="form" id="funterritoryPinyin">
            <label class="Validform_label"> <t:mutiLang langKey="地域名称英文"/>: </label>
            <input name="territoryPinyin" class="inputxt" value="${wTerritoryEntity.territoryPinyin}" datatype="s2-15" >
        </div>
         <div class="form" id="funterritorydialects">
            <label class="Validform_label"> <t:mutiLang langKey="地域名称方言"/>: </label>
            <input name="territorydialects" class="inputxt" value="${wTerritoryEntity.territorydialects}" >
        </div>
        <div class="form">
            <label class="Validform_label"> <t:mutiLang langKey="area.level"/>: </label>
            <select name="territorylevel" id="territorylevel" datatype="*">
                <option value="0" <c:if test="${wTerritoryEntity.territorylevel eq 0}">selected="selected"</c:if>><t:mutiLang langKey="main.area"/></option>
                <option value="1" <c:if test="${wTerritoryEntity.territorylevel>0}"> selected="selected"</c:if>><t:mutiLang langKey="sub.area"/></option>
            </select>
            <span class="Validform_checktip"></span>
        </div>
        <div class="form">
            <label class="Validform_label"> <t:mutiLang langKey="是否为热门城市"/>: </label>
            <select name="ishotcity" id="ishotcity" datatype="*">
                <option value="0" <c:if test="${wTerritoryEntity.ishotcity eq 0}">selected="selected"</c:if>><t:mutiLang langKey="否"/></option>
                <option value="1" <c:if test="${wTerritoryEntity.ishotcity>0}"> selected="selected"</c:if>><t:mutiLang langKey="是"/></option>
            </select>
            <span class="Validform_checktip"></span>
        </div>
        <div class="form" id="pfun">
            <label class="Validform_label"> <t:mutiLang langKey="parent.area"/>: </label>
            <input id="cc"
                <c:if test="${wTerritoryEntity.WTerritoryEntity.territorylevel eq 0}"> value="${wTerritoryEntity.WTerritoryEntity.territoryname}"</c:if>
                <c:if test="${wTerritoryEntity.WTerritoryEntity.territorylevel > 0}"> value="${wTerritoryEntity.WTerritoryEntity.territoryname}"</c:if>>
            <input id="territoryId" name="wTerritoryEntity.id" style="display: none;" value="${wTerritoryEntity.WTerritoryEntity.id}">
        </div>
        <div class="form" id="funorder">
            <label class="Validform_label"> <t:mutiLang langKey="area.code"/>: </label>
            <input name="territorycode" class="inputxt"  value="${wTerritoryEntity.territorycode}" datatype="*6-16">
        </div>
        <div class="form" id="funorder">
            <label class="Validform_label"> <t:mutiLang langKey="display.order"/>: </label>
            <input name="territorysort" class="inputxt" value="${wTerritoryEntity.territorysort}" datatype="n1-3">
        </div>
         <div class="form" id="funxWgs84">
            <label class="Validform_label"> <t:mutiLang langKey="坐标纬线"/>: </label>
            <input name="xwgs84" class="inputxt" value="${wTerritoryEntity.xwgs84}" datatype="*">
        </div>
         <div class="form" id="funyWgs84">
            <label class="Validform_label"> <t:mutiLang langKey="坐标经线"/>: </label>
            <input name="ywgs84" class="inputxt" value="${wTerritoryEntity.ywgs84}" datatype="*">
        </div>
        
        <c:if test="${wTerritoryEntity.WTerritoryEntity eq null || wTerritoryEntity.WTerritoryEntity.territorylevel==0}">
        <div class="form" id="funyOutIn">
            <label class="Validform_label"> <t:mutiLang langKey="所属国家标识"/>: </label>
            <input name="out_in" class="inputxt" datatype="*" <c:if test="${wTerritoryEntity.out_in ne null}">readonly="readonly"</c:if> value="${wTerritoryEntity.out_in}">
        </div>
        <div class="form" id="funyAreacode">
            <label class="Validform_label"> <t:mutiLang langKey="电话区号"/>: </label>
            <input name="areacode" class="inputxt" datatype="*" <c:if test="${wTerritoryEntity.areacode ne null}">readonly="readonly"</c:if> value="${wTerritoryEntity.areacode}">
        </div>
        </c:if>
	</fieldset>
</t:formvalid>
</body>
</html>
