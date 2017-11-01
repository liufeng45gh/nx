//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.tag.core.easyui;

import com.google.gson.Gson;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.vo.easyui.ColumnValue;
import org.jeecgframework.tag.vo.easyui.DataGridColumn;
import org.jeecgframework.tag.vo.easyui.DataGridUrl;
import org.jeecgframework.tag.vo.easyui.OptTypeDirection;
import org.jeecgframework.web.system.pojo.base.TSOperation;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;

public class DataGridTag extends TagSupport {
    protected String fields = "";
    protected String searchFields = "";
    protected String name;
    protected String title;
    protected String idField = "id";
    protected boolean treegrid = false;
    protected List<DataGridUrl> urlList = new ArrayList();
    protected List<DataGridUrl> toolBarList = new ArrayList();
    protected List<DataGridColumn> columnList = new ArrayList();
    protected List<ColumnValue> columnValueList = new ArrayList();
    protected List<ColumnValue> columnStyleList = new ArrayList();
    public Map<String, Object> map;
    private String actionUrl;
    public int allCount;
    public int curPageNo;
    public int pageSize = 10;
    public boolean pagination = true;
    private String width;
    private String height;
    private boolean checkbox = false;
    private boolean showPageList = true;
    private boolean openFirstNode = false;
    private boolean fit = true;
    private boolean fitColumns = true;
    private String sortName;
    private String sortOrder = "asc";
    private boolean showRefresh = true;
    private boolean showText = true;
    private String style = "easyui";
    private String onLoadSuccess;
    private String onClick;
    private String onDblClick;
    private String queryMode = "single";
    private String entityName;
    private String rowStyler;
    private String extendParams;
    private boolean autoLoadData = true;
    private String langArg;
    private Boolean singleSelect;
    protected String cssTheme;
    private boolean queryBuilder = false;
    protected static Map<String, String> syscode = new HashMap();
    @Autowired
    private static SystemService systemService;

    static {
        syscode.put("class", "clazz");
    }

    public DataGridTag() {
    }

    public String getCssTheme() {
        return this.cssTheme;
    }

    public void setCssTheme(String cssTheme) {
        this.cssTheme = cssTheme;
    }

    public boolean isQueryBuilder() {
        return this.queryBuilder;
    }

    public void setQueryBuilder(boolean queryBulder) {
        this.queryBuilder = queryBulder;
    }

    public void setOnLoadSuccess(String onLoadSuccess) {
        this.onLoadSuccess = onLoadSuccess;
    }

    public void setOnClick(String onClick) {
        this.onClick = onClick;
    }

    public void setOnDblClick(String onDblClick) {
        this.onDblClick = onDblClick;
    }

    public void setShowText(boolean showText) {
        this.showText = showText;
    }

    public void setPagination(boolean pagination) {
        this.pagination = pagination;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTreegrid(boolean treegrid) {
        this.treegrid = treegrid;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFit(boolean fit) {
        this.fit = fit;
    }

    public void setShowPageList(boolean showPageList) {
        this.showPageList = showPageList;
    }

    public void setShowRefresh(boolean showRefresh) {
        this.showRefresh = showRefresh;
    }

    public void setSingleSelect(Boolean singleSelect) {
        this.singleSelect = singleSelect;
    }

    public void setConfUrl(String url, String title, String message, String exp, String operationCode, String urlStyle) {
        DataGridUrl dataGridUrl = new DataGridUrl();
        dataGridUrl.setTitle(title);
        dataGridUrl.setUrl(url);
        dataGridUrl.setType(OptTypeDirection.Confirm);
        dataGridUrl.setMessage(message);
        dataGridUrl.setExp(exp);
        dataGridUrl.setUrlStyle(urlStyle);
        this.installOperationCode(dataGridUrl, operationCode, this.urlList);
    }

    public void setDelUrl(String url, String title, String message, String exp, String funname, String operationCode, String urlStyle) {
        DataGridUrl dataGridUrl = new DataGridUrl();
        dataGridUrl.setTitle(title);
        dataGridUrl.setUrl(url);
        dataGridUrl.setType(OptTypeDirection.Del);
        dataGridUrl.setMessage(message);
        dataGridUrl.setExp(exp);
        dataGridUrl.setFunname(funname);
        dataGridUrl.setUrlStyle(urlStyle);
        this.installOperationCode(dataGridUrl, operationCode, this.urlList);
    }

    public void setDefUrl(String url, String title, String exp, String operationCode, String urlStyle) {
        DataGridUrl dataGridUrl = new DataGridUrl();
        dataGridUrl.setTitle(title);
        dataGridUrl.setUrl(url);
        dataGridUrl.setType(OptTypeDirection.Deff);
        dataGridUrl.setExp(exp);
        dataGridUrl.setUrlStyle(urlStyle);
        this.installOperationCode(dataGridUrl, operationCode, this.urlList);
    }

    public void setToolbar(String url, String title, String icon, String exp, String onclick, String funname, String operationCode, String width2, String height2) {
        DataGridUrl dataGridUrl = new DataGridUrl();
        dataGridUrl.setTitle(title);
        dataGridUrl.setUrl(url);
        dataGridUrl.setType(OptTypeDirection.ToolBar);
        dataGridUrl.setIcon(icon);
        dataGridUrl.setOnclick(onclick);
        dataGridUrl.setExp(exp);
        dataGridUrl.setFunname(funname);
        dataGridUrl.setWidth(String.valueOf(width2));
        dataGridUrl.setHeight(String.valueOf(height2));
        this.installOperationCode(dataGridUrl, operationCode, this.toolBarList);
    }

    public void setFunUrl(String title, String exp, String funname, String operationCode, String urlStyle) {
        DataGridUrl dataGridUrl = new DataGridUrl();
        dataGridUrl.setTitle(title);
        dataGridUrl.setType(OptTypeDirection.Fun);
        dataGridUrl.setExp(exp);
        dataGridUrl.setFunname(funname);
        dataGridUrl.setUrlStyle(urlStyle);
        this.installOperationCode(dataGridUrl, operationCode, this.urlList);
    }

    public void setOpenUrl(String url, String title, String width, String height, String exp, String operationCode, String openModel, String urlStyle) {
        DataGridUrl dataGridUrl = new DataGridUrl();
        dataGridUrl.setTitle(title);
        dataGridUrl.setUrl(url);
        dataGridUrl.setWidth(width);
        dataGridUrl.setHeight(height);
        dataGridUrl.setType(OptTypeDirection.valueOf(openModel));
        dataGridUrl.setExp(exp);
        dataGridUrl.setUrlStyle(urlStyle);
        this.installOperationCode(dataGridUrl, operationCode, this.urlList);
    }

    public void setColumn(String title, String field, Integer width, Integer showLen, String rowspan, String colspan, String align, boolean sortable, boolean checkbox, String formatter, String formatterjs, boolean hidden, String replace, String treefield, boolean image, String imageSize, boolean query, String url, String funname, String arg, String queryMode, String dictionary, boolean popup, boolean frozenColumn, String extend, String style, String downloadName, boolean isAuto, String extendParams, String editor) {
        DataGridColumn dataGridColumn = new DataGridColumn();
        dataGridColumn.setAlign(align);
        dataGridColumn.setCheckbox(checkbox);
        dataGridColumn.setColspan(colspan);
        dataGridColumn.setField(field);
        dataGridColumn.setFormatter(formatter);
        dataGridColumn.setFormatterjs(formatterjs);
        dataGridColumn.setHidden(hidden);
        dataGridColumn.setRowspan(rowspan);
        dataGridColumn.setSortable(sortable);
        dataGridColumn.setTitle(title);
        dataGridColumn.setWidth(width);
        dataGridColumn.setShowLen(showLen);
        dataGridColumn.setTreefield(treefield);
        dataGridColumn.setImage(image);
        dataGridColumn.setImageSize(imageSize);
        dataGridColumn.setReplace(replace);
        dataGridColumn.setQuery(query);
        dataGridColumn.setUrl(url);
        dataGridColumn.setFunname(funname);
        dataGridColumn.setArg(arg);
        dataGridColumn.setQueryMode(queryMode);
        dataGridColumn.setDictionary(dictionary);
        dataGridColumn.setPopup(popup);
        dataGridColumn.setFrozenColumn(frozenColumn);
        dataGridColumn.setExtend(extend);
        dataGridColumn.setStyle(style);
        dataGridColumn.setDownloadName(downloadName);
        dataGridColumn.setAutocomplete(isAuto);
        dataGridColumn.setExtendParams(extendParams);
        dataGridColumn.setEditor(editor);
        this.columnList.add(dataGridColumn);
        Set<String> operationCodes = (Set)super.pageContext.getRequest().getAttribute("operationCodes");
        String text;
        if(operationCodes != null) {
            Iterator var34 = operationCodes.iterator();

            while(var34.hasNext()) {
                text = (String)var34.next();
                if(oConvertUtils.isEmpty(text)) {
                    break;
                }

                systemService = (SystemService)ApplicationContextUtil.getContext().getBean(SystemService.class);
                TSOperation operation = (TSOperation)systemService.getEntity(TSOperation.class, text);
                if(operation.getOperationcode().equals(field)) {
                    this.columnList.remove(dataGridColumn);
                }
            }
        }

        if(field != "opt") {
            this.fields = this.fields + field + ",";
            if("group".equals(queryMode)) {
                this.searchFields = this.searchFields + field + "," + field + "_begin," + field + "_end,";
            } else {
                this.searchFields = this.searchFields + field + ",";
            }
        }

        String string = null;
        int var38;
        String[] temp;
        //String text;
        String value;
        if(StringUtil.isNotEmpty(replace)) {
            temp = replace.split(",");
            text = "";
            value = "";
            //string = "";
            String[] var40 = temp;
            int var39 = temp.length;

            for(var38 = 0; var38 < var39; ++var38) {
                string = var40[var38];
                text = string.substring(0, string.indexOf("_"));
                value = value + MutiLangUtil.getMutiLangInstance().getLang(text) + ",";
                string = string + string.substring(string.indexOf("_") + 1) + ",";
            }

            this.setColumn(field, value, string);
        }

        if(!StringUtils.isBlank(dictionary) && !popup) {
            if(dictionary.contains(",")) {
                temp = dictionary.split(",");
                text = "";
                value = "";
                string = "select " + temp[1] + " as field," + temp[2] + " as text from " + temp[0];
                systemService = (SystemService)ApplicationContextUtil.getContext().getBean(SystemService.class);
                List<Map<String, Object>> list = systemService.findForJdbc(string, new Object[0]);

                Map map;
                for(Iterator var50 = list.iterator(); var50.hasNext(); value = value + map.get("field") + ",") {
                    map = (Map)var50.next();
                    text = text + map.get("text") + ",";
                }

                if(list.size() > 0) {
                    this.setColumn(field, text, value);
                }
            } else {
                text = "";
                text = "";
                List<TSType> typeList = (List)ResourceUtil.allTypes.get(dictionary.toLowerCase());
                if(typeList != null && !typeList.isEmpty()) {
                    TSType type;
                    for(Iterator var47 = typeList.iterator(); var47.hasNext(); text = text + type.getTypecode() + ",") {
                        type = (TSType)var47.next();
                        text = text + MutiLangUtil.doMutiLang(type.getTypename(), "") + ",";
                    }

                    this.setColumn(field, text, text);
                }
            }
        }

        if(StringUtil.isNotEmpty(style)) {
            temp = style.split(",");
            text = "";
            value = "";
            if(temp.length == 1 && temp[0].indexOf("_") == -1) {
                text = temp[0];
            } else {
                String[] var51 = temp;
                var38 = temp.length;

                for(int var48 = 0; var48 < var38; ++var48) {
                    string = var51[var48];
                    text = text + string.substring(0, string.indexOf("_")) + ",";
                    value = value + string.substring(string.indexOf("_") + 1) + ",";
                }
            }

            this.setStyleColumn(field, text, value);
        }

    }

    private void setStyleColumn(String field, String text, String value) {
        ColumnValue columnValue = new ColumnValue();
        columnValue.setName(field);
        columnValue.setText(text);
        columnValue.setValue(value);
        this.columnStyleList.add(columnValue);
    }

    public void setColumn(String name, String text, String value) {
        ColumnValue columnValue = new ColumnValue();
        columnValue.setName(name);
        columnValue.setText(text);
        columnValue.setValue(value);
        this.columnValueList.add(columnValue);
    }

    public int doStartTag() throws JspTagException {
        return 6;
    }

    public int doEndTag() throws JspException {
        JspWriter out = null;

        try {
            this.title = MutiLangUtil.doMutiLang(this.title, this.langArg);
            out = this.pageContext.getOut();
            if(this.style.equals("easyui")) {
                out.print(this.end().toString());
                out.flush();
            } else {
                out.print(this.datatables().toString());
                out.flush();
            }
        } catch (IOException var11) {
            var11.printStackTrace();
        } finally {
            if(out != null) {
                try {
                    out.clearBuffer();
                    this.end().setLength(0);
                    this.urlList.clear();
                    this.toolBarList.clear();
                    this.columnValueList.clear();
                    this.columnStyleList.clear();
                    this.columnList.clear();
                    this.fields = "";
                    this.searchFields = "";
                } catch (Exception var10) {
                    var10.printStackTrace();
                }
            }

        }

        return 6;
    }

    public StringBuffer datatables() {
        StringBuffer sb = new StringBuffer();
        sb.append("<script type=\"text/javascript\">");
        sb.append("$(document).ready(function() {");
        sb.append("var oTable = $('#userList').dataTable({");
        sb.append("\"bProcessing\" : true,");
        sb.append("\"bPaginate\" : true,");
        sb.append("\"sPaginationType\" : \"full_numbers\",");
        sb.append("\"bFilter\" : true,");
        sb.append("\"bSort\" : true, ");
        sb.append("\"bAutoWidth\" : true,");
        sb.append("\"bLengthChange\" : true,");
        sb.append("\"bInfo\" : true,");
        sb.append("\"sAjaxSource\" : \"userController.do?test\",");
        sb.append("\"bServerSide\" : true,");
        sb.append("\"oLanguage\" : {\"sLengthMenu\" : \" _MENU_ 条记录\",\"sZeroRecords\" : \"没有检索到数据\",\"sInfo\" : \"第 _START_ 至 _END_ 条数据 共 _TOTAL_ 条\",\"sInfoEmtpy\" : \"没有数据\",\"sProcessing\" : \"正在加载数据...\",\"sSearch\" : \"搜索\",\"oPaginate\" : {\"sFirst\" : \"首页\",\"sPrevious\" : \"前页\", \"sNext\" : \"后页\",\"sLast\" : \"尾页\"}},");
        sb.append("\"fnServerData\" : function(sSource, aoData, fnCallback, oSettings) {");
        sb.append("oSettings.jqXHR = $.ajax({\"dataType\" : 'json',\"type\" : \"POST\",\"url\" : sSource,\"data\" : aoData,\"success\" : fnCallback});},");
        sb.append("\"aoColumns\" : [ ");
        int i = 0;
        Iterator var4 = this.columnList.iterator();

        while(var4.hasNext()) {
            DataGridColumn column = (DataGridColumn)var4.next();
            ++i;
            sb.append("{");
            sb.append("\"sTitle\":\"" + column.getTitle() + "\"");
            if(column.getField().equals("opt")) {
                sb.append(",\"mData\":\"" + this.idField + "\"");
                sb.append(",\"sWidth\":\"20%\"");
                sb.append(",\"bSortable\":false");
                sb.append(",\"bSearchable\":false");
                sb.append(",\"mRender\" : function(data, type, rec) {");
                this.getOptUrl(sb);
                sb.append("}");
            } else {
                int colwidth = column.getWidth() == null?column.getTitle().length() * 15:column.getWidth().intValue();
                sb.append(",\"sName\":\"" + column.getField() + "\"");
                sb.append(",\"mDataProp\":\"" + column.getField() + "\"");
                sb.append(",\"mData\":\"" + column.getField() + "\"");
                sb.append(",\"sWidth\":\"" + colwidth + "\"");
                sb.append(",\"bSortable\":" + column.isSortable());
                sb.append(",\"bVisible\":" + !column.isHidden());
                sb.append(",\"bSearchable\":" + column.isQuery());
            }

            sb.append("}");
            if(i < this.columnList.size()) {
                sb.append(",");
            }
        }

        sb.append("]});});</script>");
        sb.append("<table width=\"100%\"  class=\"" + this.style + "\" id=\"" + this.name + "\" toolbar=\"#" + this.name + "tb\"></table>");
        return sb;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public StringBuffer end() {
        String grid = "";
        StringBuffer sb = new StringBuffer();
        this.width = this.width == null?"auto":this.width;
        this.height = this.height == null?"auto":this.height;
        sb.append("<script type=\"text/javascript\">");
        sb.append("$(function(){  storage=$.localStorage;if(!storage)storage=$.cookieStorage;");
        sb.append(this.getNoAuthOperButton());
        if(this.treegrid) {
            grid = "treegrid";
            sb.append("$('#" + this.name + "').treegrid({");
            sb.append("idField:'id',");
            sb.append("treeField:'text',");
        } else {
            grid = "datagrid";
            sb.append("$('#" + this.name + "').datagrid({");
            sb.append("idField: '" + this.idField + "',");
        }

        if(this.title != null) {
            sb.append("title: '" + this.title + "',");
        }

        if(this.autoLoadData) {
            sb.append("url:'" + this.actionUrl + "&field=" + this.fields + "',");
        } else {
            sb.append("url:'',");
        }

        if(StringUtils.isNotEmpty(this.rowStyler)) {
            sb.append("rowStyler: function(index,row){ return " + this.rowStyler + "(index,row);},");
        }

        if(StringUtils.isNotEmpty(this.extendParams)) {
            sb.append(this.extendParams);
        }

        if(this.fit) {
            sb.append("fit:true,");
        } else {
            sb.append("fit:false,");
        }

        sb.append(StringUtil.replaceAll("loadMsg: '{0}',", "{0}", MutiLangUtil.getMutiLangInstance().getLang("common.data.loading")));
        sb.append("pageSize: " + this.pageSize + ",");
        sb.append("pagination:" + this.pagination + ",");
        sb.append("pageList:[" + this.pageSize * 1 + "," + this.pageSize * 2 + "," + this.pageSize * 3 + "],");
        if(StringUtils.isNotBlank(this.sortName)) {
            sb.append("sortName:'" + this.sortName + "',");
        }

        sb.append("sortOrder:'" + this.sortOrder + "',");
        sb.append("rownumbers:true,");
        if(this.singleSelect == null) {
            sb.append("singleSelect:" + !this.checkbox + ",");
        } else {
            sb.append("singleSelect:" + this.singleSelect + ",");
        }

        if(this.fitColumns) {
            sb.append("fitColumns:true,");
        } else {
            sb.append("fitColumns:false,");
        }

        sb.append("striped:true,showFooter:true,");
        sb.append("frozenColumns:[[");
        this.getField(sb, 0);
        sb.append("]],");
        sb.append("columns:[[");
        this.getField(sb);
        sb.append("]],");
        sb.append("onLoadSuccess:function(data){$(\"#" + this.name + "\")." + grid + "(\"clearSelections\");");
        if(this.openFirstNode && this.treegrid) {
            sb.append(" if(data==null){");
            sb.append(" var firstNode = $('#" + this.name + "').treegrid('getRoots')[0];");
            sb.append(" $('#" + this.name + "').treegrid('expand',firstNode.id)}");
        }

        if(StringUtil.isNotEmpty(this.onLoadSuccess)) {
            sb.append(this.onLoadSuccess + "(data);");
        }

        sb.append("},");
        if(StringUtil.isNotEmpty(this.onDblClick)) {
            sb.append("onDblClickRow:function(rowIndex,rowData){" + this.onDblClick + "(rowIndex,rowData);},");
        }

        if(this.treegrid) {
            sb.append("onClickRow:function(rowData){");
        } else {
            sb.append("onClickRow:function(rowIndex,rowData){");
        }

        sb.append("rowid=rowData.id;");
        sb.append("gridname='" + this.name + "';");
        if(StringUtil.isNotEmpty(this.onClick)) {
            if(this.treegrid) {
                sb.append(this.onClick + "(rowData);");
            } else {
                sb.append(this.onClick + "(rowIndex,rowData);");
            }
        }

        sb.append("}");
        sb.append("});");
        this.setPager(sb, grid);
        sb.append("try{restoreheader();}catch(ex){}");
        sb.append("});");
        sb.append("function reloadTable(){");
        sb.append("try{");
        sb.append("\t$('#'+gridname).datagrid('reload');");
        sb.append("\t$('#'+gridname).treegrid('reload');");
        sb.append("}catch(ex){}");
        sb.append("}");
        sb.append("function reload" + this.name + "(){" + "$('#" + this.name + "')." + grid + "('reload');" + "}");
        sb.append("function get" + this.name + "Selected(field){return getSelected(field);}");
        sb.append("function getSelected(field){var row = $('#'+gridname)." + grid + "('getSelected');" + "if(row!=null)" + "{" + "value= row[field];" + "}" + "else" + "{" + "value='';" + "}" + "return value;" + "}");
        sb.append("function get" + this.name + "Selections(field){" + "var ids = [];" + "var rows = $('#" + this.name + "')." + grid + "('getSelections');" + "for(var i=0;i<rows.length;i++){" + "ids.push(rows[i][field]);" + "}" + "ids.join(',');" + "return ids" + "};");
        sb.append("function getSelectRows(){");
        sb.append("\treturn $('#" + this.name + "').datagrid('getChecked');");
        sb.append("}");
        sb.append(" function saveHeader(){");
        sb.append(" var columnsFields =null;var easyextends=false;try{columnsFields = $('#" + this.name + "').datagrid('getColumns');easyextends=true;");
        sb.append("}catch(e){columnsFields =$('#" + this.name + "').datagrid('getColumnFields');}");
        sb.append("\tvar cols = storage.get( '" + this.name + "hiddenColumns');var init=true;\tif(cols){init =false;} " + "var hiddencolumns = [];for(var i=0;i< columnsFields.length;i++) {if(easyextends){");
        sb.append("hiddencolumns.push({field:columnsFields[i].field,hidden:columnsFields[i].hidden});}else{");
        sb.append(" var columsDetail = $('#" + this.name + "').datagrid(\"getColumnOption\", columnsFields[i]); ");
        sb.append("if(init){hiddencolumns.push({field:columsDetail.field,hidden:columsDetail.hidden,visible:(columsDetail.hidden==true?false:true)});}else{");
        sb.append("for(var j=0;j<cols.length;j++){");
        sb.append("\t\tif(cols[j].field==columsDetail.field){");
        sb.append("\t\t\t\t\thiddencolumns.push({field:columsDetail.field,hidden:columsDetail.hidden,visible:cols[j].visible});");
        sb.append("\t\t}");
        sb.append("}");
        sb.append("}} }");
        sb.append("storage.set( '" + this.name + "hiddenColumns',JSON.stringify(hiddencolumns));");
        sb.append("}");
        sb.append("function restoreheader(){");
        sb.append("var cols = storage.get( '" + this.name + "hiddenColumns');if(!cols)return;");
        sb.append("for(var i=0;i<cols.length;i++){");
        sb.append("\ttry{");
        sb.append("if(cols.visible!=false)$('#" + this.name + "').datagrid((cols[i].hidden==true?'hideColumn':'showColumn'),cols[i].field);");
        sb.append("}catch(e){");
        sb.append("}");
        sb.append("}");
        sb.append("}");
        sb.append("function resetheader(){");
        sb.append("var cols = storage.get( '" + this.name + "hiddenColumns');if(!cols)return;");
        sb.append("for(var i=0;i<cols.length;i++){");
        sb.append("\ttry{");
        sb.append("  $('#" + this.name + "').datagrid((cols.visible==false?'hideColumn':'showColumn'),cols[i].field);");
        sb.append("}catch(e){");
        sb.append("}");
        sb.append("}");
        sb.append("}");
        if(this.columnList.size() > 0) {
            sb.append("function " + this.name + "search(){");
            sb.append("if($(\"#" + this.name + "Form\").Validform({tiptype:3}).check()){");
            sb.append("var queryParams=$('#" + this.name + "').datagrid('options').queryParams;");
            sb.append("$('#" + this.name + "tb').find('*').each(function(){queryParams[$(this).attr('name')]=$(this).val();});");
            sb.append("$('#" + this.name + "')." + grid + "({url:'" + this.actionUrl + "&field=" + this.searchFields + "',pageNumber:1});" + "}}");
            sb.append("function dosearch(params){");
            sb.append("var jsonparams=$.parseJSON(params);");
            sb.append("$('#" + this.name + "')." + grid + "({url:'" + this.actionUrl + "&field=" + this.searchFields + "',queryParams:jsonparams});" + "}");
            this.searchboxFun(sb, grid);
            sb.append("function EnterPress(e){");
            sb.append("var e = e || window.event;");
            sb.append("if(e.keyCode == 13){ ");
            sb.append(this.name + "search();");
            sb.append("}}");
            sb.append("function searchReset(name){");
            sb.append(" $(\"#\"+name+\"tb\").find(\":input\").val(\"\");");
            sb.append("var queryParams=$('#" + this.name + "').datagrid('options').queryParams;");
            sb.append("$('#" + this.name + "tb').find('*').each(function(){queryParams[$(this).attr('name')]=$(this).val();});");
            sb.append("$('#" + this.name + "')." + grid + "({url:'" + this.actionUrl + "&field=" + this.searchFields + "',pageNumber:1});");
            sb.append("}");
        }

        sb.append("</script>");
        sb.append("<table width=\"100%\"   id=\"" + this.name + "\" toolbar=\"#" + this.name + "tb\"></table>");
        sb.append("<div id=\"" + this.name + "tb\" style=\"padding:3px; height: auto\">");
        DataGridColumn col;
        Iterator var4;
        String sql;
        if(this.hasQueryColum(this.columnList)) {
            sb.append("<div name=\"searchColums\">");
            sb.append("<input  id=\"_sqlbuilder\" name=\"sqlbuilder\"   type=\"hidden\" />");
            sb.append("<form id='" + this.name + "Form'>");
            sb.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/style.css\" type=\"text/css\">");
            sb.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/tablefrom.css\" type=\"text/css\">");
            sb.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js\"></script>");
            sb.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/Validform_Datatype_zh-cn.js\"></script>");
            sb.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/datatype_zh-cn.js\"></script>");
            if("group".equals(this.getQueryMode())) {
                var4 = this.columnList.iterator();

                label220:
                while(true) {
                    do {
                        if(!var4.hasNext()) {
                            break label220;
                        }

                        col = (DataGridColumn)var4.next();
                    } while(!col.isQuery());

                    sb.append("<span style=\"display:-moz-inline-box;display:inline-block;\">");
                    sb.append("<span style=\"vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; \" title=\"" + col.getTitle() + "\">" + col.getTitle() + "：</span>");
                    if(!"single".equals(col.getQueryMode())) {
                        if("group".equals(col.getQueryMode())) {
                            sb.append("<input type=\"text\" name=\"" + col.getField() + "_begin\"  style=\"width: 94px\" " + this.extendAttribute(col.getExtend()) + " class=\"inuptxt\"/>");
                            sb.append("<span style=\"display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;\">~</span>");
                            sb.append("<input type=\"text\" name=\"" + col.getField() + "_end\"  style=\"width: 94px\" " + this.extendAttribute(col.getExtend()) + " class=\"inuptxt\"/>");
                        }
                    } else {
                        String[] dic;
                        if(!StringUtil.isEmpty(col.getReplace())) {
                            sb.append("<select name=\"" + col.getField().replaceAll("_", "\\.") + "\" WIDTH=\"100\" style=\"width: 104px\"> ");
                            sb.append(StringUtil.replaceAll("<option value =\"\" >{0}</option>", "{0}", MutiLangUtil.getMutiLangInstance().getLang("common.please.select")));
                            dic = col.getReplace().split(",");
                            sql = "";
                            String value = "";
                            String[] var11 = dic;
                            int var10 = dic.length;

                            for(int var21 = 0; var21 < var10; ++var21) {
                                String string = var11[var21];
                                String lang_key = string.split("_")[0];
                                sql = MutiLangUtil.getMutiLangInstance().getLang(lang_key);
                                value = string.split("_")[1];
                                sb.append("<option value =\"" + value + "\">" + sql + "</option>");
                            }

                            sb.append("</select>");
                        } else if(!StringUtil.isEmpty(col.getDictionary())) {
                            if(col.getDictionary().contains(",") && col.isPopup()) {
                                dic = col.getDictionary().split(",");
                                (new StringBuilder("select ")).append(dic[1]).append(" as field,").append(dic[2]).append(" as text from ").append(dic[0]).toString();
                                sb.append("<input type=\"text\" name=\"" + col.getField().replaceAll("_", "\\.") + "\" style=\"width: 100px\" class=\"searchbox-inputtext\" value=\"\" onClick=\"inputClick(this,'" + dic[1] + "','" + dic[0] + "');\" /> ");
                            } else if(col.getDictionary().contains(",") && !col.isPopup()) {
                                dic = col.getDictionary().split(",");
                                sql = "select " + dic[1] + " as field," + dic[2] + " as text from " + dic[0];
                                systemService = (SystemService)ApplicationContextUtil.getContext().getBean(SystemService.class);
                                List<Map<String, Object>> list = systemService.findForJdbc(sql, new Object[0]);
                                sb.append("<select name=\"" + col.getField().replaceAll("_", "\\.") + "\" WIDTH=\"100\" style=\"width: 104px\"> ");
                                sb.append(StringUtil.replaceAll("<option value =\"\" >{0}</option>", "{0}", MutiLangUtil.getMutiLangInstance().getLang("common.please.select")));
                                Iterator var9 = list.iterator();

                                while(var9.hasNext()) {
                                    Map<String, Object> map = (Map)var9.next();
                                    sb.append(" <option value=\"" + map.get("field") + "\">");
                                    sb.append(map.get("text"));
                                    sb.append(" </option>");
                                }

                                sb.append("</select>");
                            } else {
                                Map<String, List<TSType>> typedatas = ResourceUtil.allTypes;
                                List<TSType> types = (List)typedatas.get(col.getDictionary().toLowerCase());
                                sb.append("<select name=\"" + col.getField().replaceAll("_", "\\.") + "\" WIDTH=\"100\" style=\"width: 104px\"> ");
                                sb.append(StringUtil.replaceAll("<option value =\"\" >{0}</option>", "{0}", MutiLangUtil.getMutiLangInstance().getLang("common.please.select")));
                                if(types != null) {
                                    Iterator var8 = types.iterator();

                                    while(var8.hasNext()) {
                                        TSType type = (TSType)var8.next();
                                        sb.append(" <option value=\"" + type.getTypecode() + "\">");
                                        sb.append(MutiLangUtil.getMutiLangInstance().getLang(type.getTypename()));
                                        sb.append(" </option>");
                                    }
                                }

                                sb.append("</select>");
                            }
                        } else if(col.isAutocomplete()) {
                            sb.append(this.getAutoSpan(col.getField().replaceAll("_", "\\."), this.extendAttribute(col.getExtend())));
                        } else {
                            sb.append("<input onkeypress=\"EnterPress(event)\" onkeydown=\"EnterPress()\"  type=\"text\" name=\"" + col.getField().replaceAll("_", "\\.") + "\"  " + this.extendAttribute(col.getExtend()) + "  class=\"inuptxt\" style=\"width: 100px\" />");
                        }
                    }

                    sb.append("</span>");
                }
            }

            sb.append("</form></div>");
        }

        if(this.toolBarList.size() == 0 && !this.hasQueryColum(this.columnList)) {
            sb.append("<div style=\"height:0px;\" >");
        } else {
            sb.append("<div style=\"height:30px;\" class=\"datagrid-toolbar\">");
        }

        sb.append("<span style=\"float:left;\" >");
        DataGridUrl toolBar;
        if(this.toolBarList.size() > 0) {
            for(var4 = this.toolBarList.iterator(); var4.hasNext(); sb.append(">" + toolBar.getTitle() + "</a>")) {
                toolBar = (DataGridUrl)var4.next();
                sb.append("<a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"" + toolBar.getIcon() + "\" ");
                if(StringUtil.isNotEmpty(toolBar.getOnclick())) {
                    sb.append("onclick=" + toolBar.getOnclick());
                } else {
                    sb.append("onclick=\"" + toolBar.getFunname() + "(");
                    if(!toolBar.getFunname().equals("doSubmit")) {
                        sb.append("'" + toolBar.getTitle() + "',");
                    }

                    String width = toolBar.getWidth().contains("%")?"'" + toolBar.getWidth() + "'":toolBar.getWidth();
                    sql = toolBar.getHeight().contains("%")?"'" + toolBar.getHeight() + "'":toolBar.getHeight();
                    sb.append("'" + toolBar.getUrl() + "','" + this.name + "'," + width + "," + sql + ")\"");
                }
            }
        }

        sb.append("</span>");
        if("group".equals(this.getQueryMode()) && this.hasQueryColum(this.columnList)) {
            sb.append("<span style=\"float:right\">");
            sb.append("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" onclick=\"" + this.name + StringUtil.replaceAll("search()\">{0}</a>", "{0}", MutiLangUtil.getMutiLangInstance().getLang("common.query")));
            sb.append("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-reload\" onclick=\"searchReset('" + this.name + StringUtil.replaceAll("')\">{0}</a>", "{0}", MutiLangUtil.getMutiLangInstance().getLang("common.reset")));
            if(this.queryBuilder) {
                sb.append("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" onclick=\"queryBuilder('" + StringUtil.replaceAll("')\">{0}</a>", "{0}", MutiLangUtil.getMutiLangInstance().getLang("common.querybuilder")));
            }

            sb.append("</span>");
        } else if("single".equals(this.getQueryMode()) && this.hasQueryColum(this.columnList)) {
            sb.append("<span style=\"float:right\">");
            sb.append("<input id=\"" + this.name + "searchbox\" class=\"easyui-searchbox\"  data-options=\"searcher:" + this.name + StringUtil.replaceAll("searchbox,prompt:'{0}',menu:'#", "{0}", MutiLangUtil.getMutiLangInstance().getLang("common.please.input.keyword")) + this.name + "mm'\"></input>");
            sb.append("<div id=\"" + this.name + "mm\" style=\"width:120px\">");
            var4 = this.columnList.iterator();

            while(var4.hasNext()) {
                col = (DataGridColumn)var4.next();
                if(col.isQuery()) {
                    sb.append("<div data-options=\"name:'" + col.getField().replaceAll("_", "\\.") + "',iconCls:'icon-ok' " + this.extendAttribute(col.getExtend()) + " \">" + col.getTitle() + "</div>  ");
                }
            }

            sb.append("</div>");
            sb.append("</span>");
        }

        sb.append("</div>");
        if(this.queryBuilder) {
            this.addQueryBuilder(sb, "easyui-linkbutton");
        }

        return sb;
    }

    private String extendAttribute(String field) {
        if(StringUtil.isEmpty(field)) {
            return "";
        } else {
            field = this.dealSyscode(field, 1);
            StringBuilder re = new StringBuilder();

            try {
                JSONObject obj = JSONObject.fromObject(field);
                Iterator it = obj.keys();

                while(it.hasNext()) {
                    String key = String.valueOf(it.next());
                    JSONObject nextObj = null;

                    try {
                        nextObj = (JSONObject)obj.get(key);
                        Iterator itvalue = nextObj.keys();
                        re.append(key + "=" + "\"");
                        String onlykey;
                        if(nextObj.size() <= 1) {
                            onlykey = String.valueOf(itvalue.next());
                            if("value".equals(onlykey)) {
                                re.append("" + nextObj.get(onlykey));
                            } else {
                                re.append(onlykey + ":" + nextObj.get(onlykey));
                            }
                        } else {
                            while(itvalue.hasNext()) {
                                onlykey = String.valueOf(itvalue.next());
                                String multvalue = nextObj.getString(onlykey);
                                re.append(onlykey + ":" + multvalue + ";");
                            }

                            re.deleteCharAt(re.length() - 1);
                        }

                        re.append("\" ");
                    } catch (Exception var10) {
                        re.append(key + "=" + "\"");
                        re.append(obj.get(key) + "\"");
                        re.append("\" ");
                    }
                }
            } catch (Exception var11) {
                var11.printStackTrace();
                return "";
            }

            return this.dealSyscode(re.toString(), 2);
        }
    }

    private String extendAttributeOld(String field) {
        StringBuffer sb = new StringBuffer();
        if(!StringUtils.isBlank(field)) {
            Gson gson = new Gson();
            Map<String, String> mp = (Map)gson.fromJson(field, Map.class);
            Iterator var6 = mp.entrySet().iterator();

            while(var6.hasNext()) {
                Entry<String, String> entry = (Entry)var6.next();
                sb.append((String)entry.getKey() + "=" + gson.toJson(entry.getValue()) + "\"");
            }
        }

        return sb.toString();
    }

    private String dealSyscode(String field, int flag) {
        String change = field;
        Iterator it = syscode.keySet().iterator();

        while(it.hasNext()) {
            String key = String.valueOf(it.next());
            String value = String.valueOf(syscode.get(key));
            if(flag == 1) {
                change = field.replaceAll(key, value);
            } else if(flag == 2) {
                change = field.replaceAll(value, key);
            }
        }

        return change;
    }

    protected boolean hasQueryColum(List<DataGridColumn> columnList) {
        boolean hasQuery = false;
        Iterator var4 = columnList.iterator();

        while(var4.hasNext()) {
            DataGridColumn col = (DataGridColumn)var4.next();
            if(col.isQuery()) {
                hasQuery = true;
            }
        }

        return hasQuery;
    }

    protected void getOptUrl(StringBuffer sb) {
        sb.append("if(!rec.id){return '';}");
        List<DataGridUrl> list = this.urlList;
        sb.append("var href='';");
        Iterator var4 = list.iterator();

        while(true) {
            String exp;
            do {
                if(!var4.hasNext()) {
                    sb.append("return href;");
                    return;
                }

                DataGridUrl dataGridUrl = (DataGridUrl)var4.next();
                String url = dataGridUrl.getUrl();
                new MessageFormat("");
                String name;
                int var10;
                int var11;
                String[] var12;
                if(dataGridUrl.getValue() != null) {
                    String[] testvalue = dataGridUrl.getValue().split(",");
                    List value = new ArrayList();
                    var12 = testvalue;
                    var11 = testvalue.length;

                    for(var10 = 0; var10 < var11; ++var10) {
                        name = var12[var10];
                        value.add("\"+rec." + name + " +\"");
                    }

                    url = MessageFormat.format(url, value.toArray());
                }

                if(url != null && dataGridUrl.getValue() == null) {
                    url = this.formatUrl(url);
                }

                exp = dataGridUrl.getExp();
                if(StringUtil.isNotEmpty(exp)) {
                    String[] ShowbyFields = exp.split("&&");
                    var12 = ShowbyFields;
                    var11 = ShowbyFields.length;

                    for(var10 = 0; var10 < var11; ++var10) {
                        name = var12[var10];
                        int beginIndex = name.indexOf("#");
                        int endIndex = name.lastIndexOf("#");
                        String exptype = name.substring(beginIndex + 1, endIndex);
                        String field = name.substring(0, beginIndex);
                        String[] values = name.substring(endIndex + 1, name.length()).split(",");
                        String value = "";

                        for(int i = 0; i < values.length; ++i) {
                            value = value + "'" + values[i] + "'";
                            if(i < values.length - 1) {
                                value = value + ",";
                            }
                        }

                        if("eq".equals(exptype)) {
                            sb.append("if($.inArray(rec." + field + ",[" + value + "])>=0){");
                        }

                        if("ne".equals(exptype)) {
                            sb.append("if($.inArray(rec." + field + ",[" + value + "])<0){");
                        }

                        if("empty".equals(exptype) && value.equals("'true'")) {
                            sb.append("if(rec." + field + "==''){");
                        }

                        if("empty".equals(exptype) && value.equals("'false'")) {
                            sb.append("if(rec." + field + "!=''){");
                        }
                    }
                }

                StringBuffer style = new StringBuffer();
                if(!StringUtil.isEmpty(dataGridUrl.getUrlStyle())) {
                    style.append(" style='");
                    style.append(dataGridUrl.getUrlStyle());
                    style.append("' ");
                }

                if(OptTypeDirection.Confirm.equals(dataGridUrl.getType())) {
                    sb.append("href+=\"[<a href='#' onclick=confirm('" + url + "','" + dataGridUrl.getMessage() + "','" + this.name + "')" + style.toString() + "> \";");
                }

                if(OptTypeDirection.Del.equals(dataGridUrl.getType())) {
                    sb.append("href+=\"[<a href='#' onclick=delObj('" + url + "','" + this.name + "')" + style.toString() + ">\";");
                }

                if(OptTypeDirection.Fun.equals(dataGridUrl.getType())) {
                    name = TagUtil.getFunction(dataGridUrl.getFunname());
                    String parmars = TagUtil.getFunParams(dataGridUrl.getFunname());
                    sb.append("href+=\"[<a href='#' onclick=" + name + "(" + parmars + ")" + style.toString() + ">\";");
                }

                if(OptTypeDirection.OpenWin.equals(dataGridUrl.getType())) {
                    sb.append("href+=\"[<a href='#' onclick=openwindow('" + dataGridUrl.getTitle() + "','" + url + "','" + this.name + "'," + dataGridUrl.getWidth() + "," + dataGridUrl.getHeight() + ")" + style.toString() + ">\";");
                }

                if(OptTypeDirection.Deff.equals(dataGridUrl.getType())) {
                    sb.append("href+=\"[<a href='" + url + "' title='" + dataGridUrl.getTitle() + "'" + style.toString() + ">\";");
                }

                if(OptTypeDirection.OpenTab.equals(dataGridUrl.getType())) {
                    sb.append("href+=\"[<a href='#' onclick=addOneTab('" + dataGridUrl.getTitle() + "','" + url + "')>\";");
                }

                sb.append("href+=\"" + dataGridUrl.getTitle() + "</a>]\";");
            } while(!StringUtil.isNotEmpty(exp));

            for(int i = 0; i < exp.split("&&").length; ++i) {
                sb.append("}");
            }
        }
    }

    protected void getFun(StringBuffer sb, DataGridColumn column) {
        String url = column.getUrl();
        url = this.formatUrl(url);
        sb.append("var href=\"<a style='color:red' href='#' onclick=" + column.getFunname() + "('" + column.getTitle() + "','" + url + "')>\";");
        sb.append("return href+value+'</a>';");
    }

    protected String formatUrl(String url) {
        new MessageFormat("");
        String parurlvalue = "";
        if(url.indexOf("&") >= 0) {
            String beforeurl = url.substring(0, url.indexOf("&"));
            String parurl = url.substring(url.indexOf("&") + 1, url.length());
            String[] pras = parurl.split("&");
            List value = new ArrayList();
            int j = 0;

            for(int i = 0; i < pras.length; ++i) {
                if(pras[i].indexOf("{") < 0 && pras[i].indexOf("#") < 0) {
                    parurlvalue = parurlvalue + "&" + pras[i];
                } else {
                    String field = pras[i].substring(pras[i].indexOf("{") + 1, pras[i].lastIndexOf("}"));
                    parurlvalue = parurlvalue + "&" + pras[i].replace("{" + field + "}", "{" + j + "}");
                    value.add("\"+rec." + field + " +\"");
                    ++j;
                }
            }

            url = MessageFormat.format(beforeurl + parurlvalue, value.toArray());
        }

        return url;
    }

    protected void getField(StringBuffer sb) {
        this.getField(sb, 1);
    }

    protected void getField(StringBuffer sb, int frozen) {
        if(this.checkbox && frozen == 0) {
            sb.append("{field:'ck',checkbox:'true'},");
        }

        int i = 0;
        Iterator var5 = this.columnList.iterator();

        while(true) {
            DataGridColumn column;
            do {
                if(!var5.hasNext()) {
                    return;
                }

                column = (DataGridColumn)var5.next();
                ++i;
            } while((!column.isFrozenColumn() || frozen != 0) && (column.isFrozenColumn() || frozen != 1));

            String field;
            if(this.treegrid) {
                field = column.getTreefield();
            } else {
                field = column.getField();
            }

            sb.append("{field:'" + field + "',title:'" + column.getTitle() + "'");
            if(column.getWidth() != null) {
                sb.append(",width:" + column.getWidth());
            }

            if(column.getAlign() != null) {
                sb.append(",align:'" + column.getAlign() + "'");
            }

            if(StringUtils.isNotEmpty(column.getExtendParams())) {
                sb.append("," + column.getExtendParams().substring(0, column.getExtendParams().length() - 1));
            }

            if(column.isHidden()) {
                sb.append(",hidden:true");
            }

            if(!this.treegrid && column.isSortable() && field.indexOf("_") <= 0 && field != "opt") {
                sb.append(",sortable:" + column.isSortable());
            }

            String testString;
            ColumnValue columnValue;
            Iterator var9;
            String[] value;
            String[] text;
            int j;
            if(column.getFormatterjs() != null) {
                sb.append(",formatter:function(value,rec,index){");
                sb.append(" return " + column.getFormatterjs() + "(value,rec,index);}");
            } else if(column.isImage()) {
                if(column.getImageSize() != null) {
                    String[] tld = column.getImageSize().split(",");
                    sb.append(",formatter:function(value,rec,index){");
                    sb.append(" return '<img width=\"" + tld[0] + "\" height=\"" + tld[1] + "\" border=\"0\" src=\"'+value+'\"/>';}");
                    testString = null;
                } else {
                    sb.append(",formatter:function(value,rec,index){");
                    sb.append(" return '<img border=\"0\" src=\"'+value+'\"/>';}");
                }
            } else if(column.getDownloadName() != null) {
                sb.append(",formatter:function(value,rec,index){");
                sb.append(" return '<a target=\"_blank\" href=\"'+value+'\">" + column.getDownloadName() + "</a>';}");
            } else if(column.getUrl() != null) {
                sb.append(",formatter:function(value,rec,index){");
                this.getFun(sb, column);
                sb.append("}");
            } else if(column.getField().equals("opt")) {
                sb.append(",formatter:function(value,rec,index){");
                this.getOptUrl(sb);
                sb.append("}");
            } else if(column.getFormatter() != null) {
                sb.append(",formatter:function(value,rec,index){");
                sb.append(" return new Date().format('" + column.getFormatter() + "',value);}");
            } else if(column.getShowLen() != null) {
                sb.append(",formatter:function(value,rec,index){");
                sb.append(" if(value==undefined) {return ''} ");
                sb.append(" if(value.length<=");
                sb.append(column.getShowLen());
                sb.append(") {return value}");
                sb.append(" else{ return '<a title= '+value+'>'+ value.substring(0,");
                sb.append(column.getShowLen());
                sb.append(")+'...';}}");
            } else if(this.columnValueList.size() > 0 && !column.getField().equals("opt")) {
                testString = "";
                var9 = this.columnValueList.iterator();

                label148:
                while(true) {
                    do {
                        if(!var9.hasNext()) {
                            break label148;
                        }

                        columnValue = (ColumnValue)var9.next();
                    } while(!columnValue.getName().equals(column.getField()));

                    value = columnValue.getValue().split(",");
                    text = columnValue.getText().split(",");
                    sb.append(",formatter:function(value,rec,index){");
                    sb.append("if(value==undefined) return '';");
                    sb.append("var valArray = value.split(',');");
                    sb.append("if(valArray.length > 1){");
                    sb.append("var checkboxValue = '';");
                    sb.append("for(var k=0; k<valArray.length; k++){");

                    for(j = 0; j < value.length; ++j) {
                        sb.append("if(valArray[k] == '" + value[j] + "'){ checkboxValue = checkboxValue + '" + text[j] + "' + ',';}");
                    }

                    sb.append("}");
                    sb.append("return checkboxValue.substring(0,checkboxValue.length-1);");
                    sb.append("}");
                    sb.append("else{");

                    for(j = 0; j < value.length; ++j) {
                        testString = testString + "if(value=='" + value[j] + "'){return '" + text[j] + "';}";
                    }

                    sb.append(testString);
                    sb.append("else{return value;}");
                    sb.append("}");
                    sb.append("}");
                }
            }

            if(this.columnStyleList.size() > 0 && !column.getField().equals("opt")) {
                testString = "";
                var9 = this.columnStyleList.iterator();

                label174:
                while(true) {
                    do {
                        if(!var9.hasNext()) {
                            break label174;
                        }

                        columnValue = (ColumnValue)var9.next();
                    } while(!columnValue.getName().equals(column.getField()));

                    value = columnValue.getValue().split(",");
                    text = columnValue.getText().split(",");
                    sb.append(",styler:function(value,rec,index){");
                    if((value.length == 0 || StringUtils.isEmpty(value[0])) && text.length == 1) {
                        if(text[0].indexOf("(") > -1) {
                            testString = " return '" + text[0].replace("(", "(value,rec,index") + "'";
                        } else {
                            testString = " return '" + text[0] + "'";
                        }
                    } else {
                        for(j = 0; j < value.length; ++j) {
                            testString = testString + "if(value=='" + value[j] + "'){return '" + text[j] + "'}";
                        }
                    }

                    sb.append(testString);
                    sb.append("}");
                }
            }

            sb.append("}");
            if(i < this.columnList.size()) {
                sb.append(",");
            }
        }
    }

    protected void setPager(StringBuffer sb, String grid) {
        sb.append("$('#" + this.name + "')." + grid + "('getPager').pagination({");
        sb.append("beforePageText:'',afterPageText:'/{pages}',");
        if(this.showText) {
            sb.append("displayMsg:'{from}-{to}" + MutiLangUtil.getMutiLangInstance().getLang("common.total") + " {total}" + MutiLangUtil.getMutiLangInstance().getLang("common.item") + "',");
        } else {
            sb.append("displayMsg:'',");
        }

        if(this.showPageList) {
            sb.append("showPageList:true,");
        } else {
            sb.append("showPageList:false,");
        }

        sb.append("showRefresh:" + this.showRefresh);
        sb.append("});");
        sb.append("$('#" + this.name + "')." + grid + "('getPager').pagination({");
        sb.append("onBeforeRefresh:function(pageNumber, pageSize){ $(this).pagination('loading');$(this).pagination('loaded'); }");
        sb.append("});");
    }

    protected void searchboxFun(StringBuffer sb, String grid) {
        sb.append("function " + this.name + "searchbox(value,name){");
        sb.append("var queryParams=$('#" + this.name + "').datagrid('options').queryParams;");
        sb.append("queryParams[name]=value;queryParams.searchfield=name;$('#" + this.name + "')." + grid + "('reload');}");
        sb.append("$('#" + this.name + "searchbox').searchbox({");
        sb.append("searcher:function(value,name){");
        sb.append(this.name + "searchbox(value,name);");
        sb.append("},");
        sb.append("menu:'#" + this.name + "mm',");
        sb.append(StringUtil.replaceAll("prompt:'{0}'", "{0}", MutiLangUtil.getMutiLangInstance().getLang("common.please.input.query.keyword")));
        sb.append("});");
    }

    public String getNoAuthOperButton() {
        StringBuffer sb = new StringBuffer();
        if(!ResourceUtil.getSessionUserName().getUserName().equals("admin") && Globals.BUTTON_AUTHORITY_CHECK) {
            Set<String> operationCodes = (Set)super.pageContext.getRequest().getAttribute("operationCodes");
            if(operationCodes != null) {
                Iterator var4 = operationCodes.iterator();

                while(true) {
                    TSOperation operation;
                    do {
                        if(!var4.hasNext()) {
                            return sb.toString();
                        }

                        String MyoperationCode = (String)var4.next();
                        if(oConvertUtils.isEmpty(MyoperationCode)) {
                            return sb.toString();
                        }

                        systemService = (SystemService)ApplicationContextUtil.getContext().getBean(SystemService.class);
                        operation = (TSOperation)systemService.getEntity(TSOperation.class, MyoperationCode);
                    } while(!operation.getOperationcode().startsWith(".") && !operation.getOperationcode().startsWith("#"));

                    if(operation.getOperationType().intValue() == Globals.OPERATION_TYPE_HIDE.shortValue()) {
                        sb.append("$(\"" + operation.getOperationcode().replaceAll(" ", "") + "\").hide();");
                    } else {
                        sb.append("$(\"" + operation.getOperationcode().replaceAll(" ", "") + "\").attr(\"disabled\",\"disabled\");");
                        sb.append("$(\"" + operation.getOperationcode().replaceAll(" ", "") + "\").find(\":input\").attr(\"disabled\",\"disabled\");");
                    }
                }
            }
        }

        return sb.toString();
    }

    private void installOperationCode(DataGridUrl dataGridUrl, String operationCode, List optList) {
        if(!ResourceUtil.getSessionUserName().getUserName().equals("admin") && Globals.BUTTON_AUTHORITY_CHECK) {
            if(!oConvertUtils.isEmpty(operationCode)) {
                Set<String> operationCodes = (Set)super.pageContext.getRequest().getAttribute("operationCodes");
                if(operationCodes != null) {
                    List<String> operationCodesStr = new ArrayList();
                    Iterator var7 = operationCodes.iterator();

                    while(var7.hasNext()) {
                        String MyoperationCode = (String)var7.next();
                        if(oConvertUtils.isEmpty(MyoperationCode)) {
                            break;
                        }

                        systemService = (SystemService)ApplicationContextUtil.getContext().getBean(SystemService.class);
                        TSOperation operation = (TSOperation)systemService.getEntity(TSOperation.class, MyoperationCode);
                        operationCodesStr.add(operation.getOperationcode());
                    }

                    if(!operationCodesStr.contains(operationCode)) {
                        optList.add(dataGridUrl);
                    }
                }
            } else {
                optList.add(dataGridUrl);
            }
        } else {
            optList.add(dataGridUrl);
        }

    }

    private String getAutoSpan(String filed, String extend) {
        String id = filed.replaceAll("\\.", "_");
        StringBuffer nsb = new StringBuffer();
        nsb.append("<script type=\"text/javascript\">");
        nsb.append("$(document).ready(function() {").append("$(\"#" + this.getEntityName() + "_" + id + "\").autocomplete(\"commonController.do?getAutoList\",{").append("max: 5,minChars: 2,width: 200,scrollHeight: 100,matchContains: true,autoFill: false,extraParams:{").append("featureClass : \"P\",style : \"full\",\tmaxRows : 10,labelField : \"" + filed + "\",valueField : \"" + filed + "\",").append("searchField : \"" + filed + "\",entityName : \"" + this.getEntityName() + "\",trem: function(){return $(\"#" + this.getEntityName() + "_" + id + "\").val();}}");
        nsb.append(",parse:function(data){return jeecgAutoParse.call(this,data);}");
        nsb.append(",formatItem:function(row, i, max){return row['" + filed + "'];} ");
        nsb.append("}).result(function (event, row, formatted) {");
        nsb.append("$(\"#" + this.getEntityName() + "_" + id + "\").val(row['" + filed + "']);}); });").append("</script>").append("<input class=\"inuptxt\" type=\"text\" id=\"" + this.getEntityName() + "_" + id + "\" name=\"" + filed + "\"  " + extend + StringUtil.replace(" nullmsg=\"\" errormsg=\"{0}\"/>", "{0}", MutiLangUtil.getMutiLangInstance().getLang("input.error")));
        return nsb.toString();
    }

    private String getEntityName() {
        if(StringUtils.isEmpty(this.entityName)) {
            this.entityName = this.actionUrl.substring(0, this.actionUrl.indexOf("Controller"));
            this.entityName = String.valueOf(this.entityName.charAt(0)).toUpperCase() + this.entityName.substring(1) + "Entity";
        }

        return this.entityName;
    }

    public boolean isFitColumns() {
        return this.fitColumns;
    }

    public void setFitColumns(boolean fitColumns) {
        this.fitColumns = fitColumns;
    }

    public String getSortName() {
        return this.sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getQueryMode() {
        return this.queryMode;
    }

    public void setQueryMode(String queryMode) {
        this.queryMode = queryMode;
    }

    public boolean isAutoLoadData() {
        return this.autoLoadData;
    }

    public void setAutoLoadData(boolean autoLoadData) {
        this.autoLoadData = autoLoadData;
    }

    public void setOpenFirstNode(boolean openFirstNode) {
        this.openFirstNode = openFirstNode;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public void setRowStyler(String rowStyler) {
        this.rowStyler = rowStyler;
    }

    public void setExtendParams(String extendParams) {
        this.extendParams = extendParams;
    }

    public void setLangArg(String langArg) {
        this.langArg = langArg;
    }

    public StringBuffer aceStyleTable() {
        String grid = "";
        StringBuffer sb = new StringBuffer();
        this.width = this.width == null?"auto":this.width;
        this.height = this.height == null?"auto":this.height;
        sb.append("<script type=\"text/javascript\">");
        sb.append("$(function(){  storage=$.localStorage;if(!storage)storage=$.cookieStorage;");
        sb.append(this.getNoAuthOperButton());
        if(this.treegrid) {
            grid = "treegrid";
            sb.append("$('#" + this.name + "').treegrid({");
            sb.append("idField:'id',");
            sb.append("treeField:'text',");
        } else {
            grid = "datagrid";
            sb.append("$('#" + this.name + "').datagrid({");
            sb.append("idField: '" + this.idField + "',");
        }

        if(this.title != null) {
            sb.append("title: '" + this.title + "',");
        }

        if(this.autoLoadData) {
            sb.append("url:'" + this.actionUrl + "&field=" + this.fields + "',");
        } else {
            sb.append("url:'',");
        }

        if(StringUtils.isNotEmpty(this.rowStyler)) {
            sb.append("rowStyler: function(index,row){ return " + this.rowStyler + "(index,row);},");
        }

        if(StringUtils.isNotEmpty(this.extendParams)) {
            sb.append(this.extendParams);
        }

        if(this.fit) {
            sb.append("fit:true,");
        } else {
            sb.append("fit:false,");
        }

        sb.append(StringUtil.replaceAll("loadMsg: '{0}',", "{0}", MutiLangUtil.getMutiLangInstance().getLang("common.data.loading")));
        sb.append("striped:true,pageSize: " + this.pageSize + ",");
        sb.append("pagination:" + this.pagination + ",");
        sb.append("pageList:[" + this.pageSize * 1 + "," + this.pageSize * 2 + "," + this.pageSize * 3 + "],");
        if(StringUtils.isNotBlank(this.sortName)) {
            sb.append("sortName:'" + this.sortName + "',");
        }

        sb.append("sortOrder:'" + this.sortOrder + "',");
        sb.append("rownumbers:true,");
        if(this.singleSelect == null) {
            sb.append("singleSelect:" + !this.checkbox + ",");
        } else {
            sb.append("singleSelect:" + this.singleSelect + ",");
        }

        if(this.fitColumns) {
            sb.append("fitColumns:true,");
        } else {
            sb.append("fitColumns:false,");
        }

        sb.append("showFooter:true,");
        sb.append("frozenColumns:[[");
        this.getField(sb, 0);
        sb.append("]],");
        sb.append("columns:[[");
        this.getField(sb);
        sb.append("]],");
        sb.append("onLoadSuccess:function(data){$(\"#" + this.name + "\")." + grid + "(\"clearSelections\");");
        if(this.openFirstNode && this.treegrid) {
            sb.append(" if(data==null){");
            sb.append(" var firstNode = $('#" + this.name + "').treegrid('getRoots')[0];");
            sb.append(" $('#" + this.name + "').treegrid('expand',firstNode.id)}");
        }

        if(StringUtil.isNotEmpty(this.onLoadSuccess)) {
            sb.append(this.onLoadSuccess + "(data);");
        }

        sb.append("},");
        if(StringUtil.isNotEmpty(this.onDblClick)) {
            sb.append("onDblClickRow:function(rowIndex,rowData){" + this.onDblClick + "(rowIndex,rowData);},");
        }

        if(this.treegrid) {
            sb.append("onClickRow:function(rowData){");
        } else {
            sb.append("onClickRow:function(rowIndex,rowData){");
        }

        sb.append("rowid=rowData.id;");
        sb.append("gridname='" + this.name + "';");
        if(StringUtil.isNotEmpty(this.onClick)) {
            if(this.treegrid) {
                sb.append(this.onClick + "(rowData);");
            } else {
                sb.append(this.onClick + "(rowIndex,rowData);");
            }
        }

        sb.append("}");
        sb.append("});");
        this.setPager(sb, grid);
        sb.append("try{restoreheader();}catch(ex){}");
        sb.append("});");
        sb.append("function reloadTable(){");
        sb.append("try{");
        sb.append("\t$('#'+gridname).datagrid('reload');");
        sb.append("\t$('#'+gridname).treegrid('reload');");
        sb.append("}catch(ex){}");
        sb.append("}");
        sb.append("function reload" + this.name + "(){" + "$('#" + this.name + "')." + grid + "('reload');" + "}");
        sb.append("function get" + this.name + "Selected(field){return getSelected(field);}");
        sb.append("function getSelected(field){var row = $('#'+gridname)." + grid + "('getSelected');" + "if(row!=null)" + "{" + "value= row[field];" + "}" + "else" + "{" + "value='';" + "}" + "return value;" + "}");
        sb.append("function get" + this.name + "Selections(field){" + "var ids = [];" + "var rows = $('#" + this.name + "')." + grid + "('getSelections');" + "for(var i=0;i<rows.length;i++){" + "ids.push(rows[i][field]);" + "}" + "ids.join(',');" + "return ids" + "};");
        sb.append("function getSelectRows(){");
        sb.append("\treturn $('#" + this.name + "').datagrid('getChecked');}");
        sb.append(" function saveHeader(){");
        sb.append(" var columnsFields =null;var easyextends=false;try{columnsFields = $('#" + this.name + "').datagrid('getColumns');easyextends=true;");
        sb.append("}catch(e){columnsFields =$('#" + this.name + "').datagrid('getColumnFields');}");
        sb.append("\tvar cols = storage.get( '" + this.name + "hiddenColumns');var init=true;\tif(cols){init =false;} " + "var hiddencolumns = [];for(var i=0;i< columnsFields.length;i++) {if(easyextends){");
        sb.append("hiddencolumns.push({field:columnsFields[i].field,hidden:columnsFields[i].hidden});}else{");
        sb.append(" var columsDetail = $('#" + this.name + "').datagrid(\"getColumnOption\", columnsFields[i]); ");
        sb.append("if(init){hiddencolumns.push({field:columsDetail.field,hidden:columsDetail.hidden,visible:(columsDetail.hidden==true?false:true)});}else{");
        sb.append("for(var j=0;j<cols.length;j++){");
        sb.append("\t\tif(cols[j].field==columsDetail.field){");
        sb.append("\t\t\t\t\thiddencolumns.push({field:columsDetail.field,hidden:columsDetail.hidden,visible:cols[j].visible});");
        sb.append("\t\t}");
        sb.append("}");
        sb.append("}} }");
        sb.append("storage.set( '" + this.name + "hiddenColumns',JSON.stringify(hiddencolumns));");
        sb.append("}");
        sb.append("function restoreheader(){");
        sb.append("var cols = storage.get( '" + this.name + "hiddenColumns');if(!cols)return;");
        sb.append("for(var i=0;i<cols.length;i++){");
        sb.append("\ttry{");
        sb.append("if(cols.visible!=false)$('#" + this.name + "').datagrid((cols[i].hidden==true?'hideColumn':'showColumn'),cols[i].field);");
        sb.append("}catch(e){");
        sb.append("}");
        sb.append("}");
        sb.append("}");
        sb.append("function resetheader(){");
        sb.append("var cols = storage.get( '" + this.name + "hiddenColumns');if(!cols)return;");
        sb.append("for(var i=0;i<cols.length;i++){");
        sb.append("\ttry{");
        sb.append("  $('#" + this.name + "').datagrid((cols.visible==false?'hideColumn':'showColumn'),cols[i].field);");
        sb.append("}catch(e){");
        sb.append("}");
        sb.append("}");
        sb.append("}");
        if(this.columnList.size() > 0) {
            sb.append("function " + this.name + "search(){");
            sb.append("var queryParams=$('#" + this.name + "').datagrid('options').queryParams;");
            sb.append("$('#" + this.name + "tb').find('*').each(function(){queryParams[$(this).attr('name')]=$(this).val();});");
            sb.append("$('#" + this.name + "')." + grid + "({url:'" + this.actionUrl + "&field=" + this.searchFields + "',pageNumber:1});" + "}");
            sb.append("function dosearch(params){");
            sb.append("var jsonparams=$.parseJSON(params);");
            sb.append("$('#" + this.name + "')." + grid + "({url:'" + this.actionUrl + "&field=" + this.searchFields + "',queryParams:jsonparams});" + "}");
            this.searchboxFun(sb, grid);
            sb.append("function EnterPress(e){");
            sb.append("var e = e || window.event;");
            sb.append("if(e.keyCode == 13){ ");
            sb.append(this.name + "search();");
            sb.append("}}");
            sb.append("function searchReset(name){");
            sb.append(" $(\"#" + this.name + "tb\").find(\":input\").val(\"\");");
            String func = this.name.trim() + "search();";
            sb.append(func);
            sb.append("}");
        }

        sb.append("</script>");
        sb.append("<table width=\"100%\"   id=\"" + this.name + "\" toolbar=\"#" + this.name + "tb\"></table>");
        sb.append("<div id=\"" + this.name + "tb\" style=\"padding:3px; height: auto\">");
        Iterator var4;
        DataGridColumn col;
        String sql;
        if(this.hasQueryColum(this.columnList)) {
            sb.append("<div name=\"searchColums\">");
            sb.append("<input  id=\"_sqlbuilder\" name=\"sqlbuilder\"   type=\"hidden\" />");
            if("group".equals(this.getQueryMode())) {
                var4 = this.columnList.iterator();

                label210:
                while(true) {
                    do {
                        if(!var4.hasNext()) {
                            break label210;
                        }

                        col = (DataGridColumn)var4.next();
                    } while(!col.isQuery());

                    sb.append("<span style=\"display:-moz-inline-box;display:inline-block;\">");
                    sb.append("<span style=\"vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; \" title=\"" + col.getTitle() + "\">" + col.getTitle() + "：</span>");
                    if(!"single".equals(col.getQueryMode())) {
                        if("group".equals(col.getQueryMode())) {
                            sb.append("<input type=\"text\" name=\"" + col.getField() + "_begin\"   " + this.extendAttribute(col.getExtend()) + " class=\"inuptxt\"/>");
                            sb.append("<span style=\"display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;\">~</span>");
                            sb.append("<input type=\"text\" name=\"" + col.getField() + "_end\"   " + this.extendAttribute(col.getExtend()) + " class=\"inuptxt\"/>");
                        }
                    } else {
                        String[] dic;
                        if(!StringUtil.isEmpty(col.getReplace())) {
                            sb.append("<select name=\"" + col.getField().replaceAll("_", "\\.") + "\" WIDTH=\"100\" style=\"width: 104px\"> ");
                            sb.append(StringUtil.replaceAll("<option value =\"\" >{0}</option>", "{0}", MutiLangUtil.getMutiLangInstance().getLang("common.please.select")));
                            dic = col.getReplace().split(",");
                            sql = "";
                            String value = "";
                            String[] var11 = dic;
                            int var10 = dic.length;

                            for(int var22 = 0; var22 < var10; ++var22) {
                                String string = var11[var22];
                                String lang_key = string.split("_")[0];
                                sql = MutiLangUtil.getMutiLangInstance().getLang(lang_key);
                                value = string.split("_")[1];
                                sb.append("<option value =\"" + value + "\">" + sql + "</option>");
                            }

                            sb.append("</select>");
                        } else if(StringUtil.isEmpty(col.getDictionary())) {
                            if(col.isAutocomplete()) {
                                sb.append(this.getAutoSpan(col.getField().replaceAll("_", "\\."), this.extendAttribute(col.getExtend())));
                            } else {
                                sb.append("<input onkeypress=\"EnterPress(event)\" onkeydown=\"EnterPress()\"  type=\"text\" name=\"" + col.getField().replaceAll("_", "\\.") + "\"  " + this.extendAttribute(col.getExtend()) + " class=\"inuptxt\"/>");
                            }
                        } else if(col.getDictionary().contains(",")) {
                            dic = col.getDictionary().split(",");
                            sql = "select " + dic[1] + " as field," + dic[2] + " as text from " + dic[0];
                            systemService = (SystemService)ApplicationContextUtil.getContext().getBean(SystemService.class);
                            List<Map<String, Object>> list = systemService.findForJdbc(sql, new Object[0]);
                            sb.append("<select name=\"" + col.getField().replaceAll("_", "\\.") + "\" WIDTH=\"100\" style=\"width: 104px\"> ");
                            sb.append(StringUtil.replaceAll("<option value =\"\" >{0}</option>", "{0}", MutiLangUtil.getMutiLangInstance().getLang("common.please.select")));
                            Iterator var9 = list.iterator();

                            while(var9.hasNext()) {
                                Map<String, Object> map = (Map)var9.next();
                                sb.append(" <option value=\"" + map.get("field") + "\">");
                                sb.append(map.get("text"));
                                sb.append(" </option>");
                            }

                            sb.append("</select>");
                        } else {
                            Map<String, List<TSType>> typedatas = ResourceUtil.allTypes;
                            List<TSType> types = (List)typedatas.get(col.getDictionary().toLowerCase());
                            sb.append("<select name=\"" + col.getField().replaceAll("_", "\\.") + "\" WIDTH=\"100\" style=\"width: 104px\"> ");
                            sb.append(StringUtil.replaceAll("<option value =\"\" >{0}</option>", "{0}", MutiLangUtil.getMutiLangInstance().getLang("common.please.select")));
                            Iterator var8 = types.iterator();

                            while(var8.hasNext()) {
                                TSType type = (TSType)var8.next();
                                sb.append(" <option value=\"" + type.getTypecode() + "\">");
                                sb.append(MutiLangUtil.getMutiLangInstance().getLang(type.getTypename()));
                                sb.append(" </option>");
                            }

                            sb.append("</select>");
                        }
                    }

                    sb.append("</span>");
                }
            }

            sb.append("</div>");
        }

        if(this.toolBarList.size() == 0 && !this.hasQueryColum(this.columnList)) {
            sb.append("<div style=\"height:0px;\" >");
        } else {
            sb.append("<div style=\"height:30px;\" class=\"datagrid-toolbar\">");
        }

        sb.append("<span style=\"float:left;\" >");
        DataGridUrl toolBar;
        if(this.toolBarList.size() > 0) {
            for(var4 = this.toolBarList.iterator(); var4.hasNext(); sb.append(">" + toolBar.getTitle() + "</a>")) {
                toolBar = (DataGridUrl)var4.next();
                sb.append("<a href=\"#\" class=\"button\" plain=\"true\" icon=\"" + toolBar.getIcon() + "\" ");
                if(StringUtil.isNotEmpty(toolBar.getOnclick())) {
                    sb.append("onclick=" + toolBar.getOnclick());
                } else {
                    sb.append("onclick=\"" + toolBar.getFunname() + "(");
                    if(!toolBar.getFunname().equals("doSubmit")) {
                        sb.append("'" + toolBar.getTitle() + "',");
                    }

                    String width = toolBar.getWidth().contains("%")?"'" + toolBar.getWidth() + "'":toolBar.getWidth();
                    sql = toolBar.getHeight().contains("%")?"'" + toolBar.getHeight() + "'":toolBar.getHeight();
                    sb.append("'" + toolBar.getUrl() + "','" + this.name + "'," + width + "," + sql + ")\"");
                }
            }
        }

        sb.append("</span>");
        if("group".equals(this.getQueryMode()) && this.hasQueryColum(this.columnList)) {
            sb.append("<span style=\"float:right\">");
            sb.append("<a href=\"#\" class=\"button\" iconCls=\"icon-search\" onclick=\"" + this.name + StringUtil.replaceAll("search()\">{0}</a>", "{0}", MutiLangUtil.getMutiLangInstance().getLang("common.query")));
            sb.append("<a href=\"#\" class=\"button\" iconCls=\"icon-reload\" onclick=\"searchReset('" + this.name + StringUtil.replaceAll("')\">{0}</a>", "{0}", MutiLangUtil.getMutiLangInstance().getLang("common.reset")));
            if(this.queryBuilder) {
                sb.append("<a href=\"#\" class=\"button\" iconCls=\"icon-search\" onclick=\"queryBuilder('" + StringUtil.replaceAll("')\">{0}</a>", "{0}", MutiLangUtil.getMutiLangInstance().getLang("common.querybuilder")));
            }

            sb.append("</span>");
        } else if("single".equals(this.getQueryMode()) && this.hasQueryColum(this.columnList)) {
            sb.append("<span style=\"float:right\">");
            sb.append("<input id=\"" + this.name + "searchbox\" class=\"easyui-searchbox\"  data-options=\"searcher:" + this.name + StringUtil.replaceAll("searchbox,prompt:'{0}',menu:'#", "{0}", MutiLangUtil.getMutiLangInstance().getLang("common.please.input.keyword")) + this.name + "mm'\"></input>");
            sb.append("<div id=\"" + this.name + "mm\" style=\"width:120px\">");
            var4 = this.columnList.iterator();

            while(var4.hasNext()) {
                col = (DataGridColumn)var4.next();
                if(col.isQuery()) {
                    sb.append("<div data-options=\"name:'" + col.getField().replaceAll("_", "\\.") + "',iconCls:'icon-ok' " + this.extendAttribute(col.getExtend()) + " \">" + col.getTitle() + "</div>  ");
                }
            }

            sb.append("</div>");
            sb.append("</span>");
        }

        sb.append("</div>");
        if(this.queryBuilder) {
            this.addQueryBuilder(sb, "button");
        }

        return sb;
    }

    private void appendLine(StringBuffer sb, String str) {
        String format = "\r\n";
        sb.append(str).append(format);
    }

    private void addQueryBuilder(StringBuffer sb, String buttonSytle) {
        this.appendLine(sb, "<div style=\"position:relative;overflow:auto;\">");
        this.appendLine(sb, "<div id=\"" + this.name + "_qbwin\" class=\"easyui-window\" data-options=\"closed:true,title:'高级查询构造器'\" style=\"width:600px;height:370px;padding:0px\">");
        this.appendLine(sb, "\t<div class=\"easyui-layout\" data-options=\"fit:true\">");
        this.appendLine(sb, "\t\t<div data-options=\"region:'east',split:true\" style=\"width:130px\"><div class=\"easyui-accordion\" style=\"width:120px;height:300px;\">");
        this.appendLine(sb, "<div title=\"查询历史\" data-options=\"iconCls:'icon-search'\" style=\"padding:0px;\">");
        this.appendLine(sb, "\t<ul id=\"" + this.name + "tt\" class=\"easyui-tree\" data-options=\"onClick:function(node){");
        this.appendLine(sb, "historyQuery( node.id);  ");
        this.appendLine(sb, "},ondbClick: function(node){");
        this.appendLine(sb, "$(this).tree('beginEdit',node.target);");
        this.appendLine(sb, "},onContextMenu: function(e,node){");
        this.appendLine(sb, "\t\te.preventDefault();");
        this.appendLine(sb, "\t\t$(this).tree('select',node.target);");
        this.appendLine(sb, "\t\t$('#" + this.name + "mmTree').menu('show',{");
        this.appendLine(sb, "\t\t\tleft: e.pageX,");
        this.appendLine(sb, "\t\t\ttop: e.pageY");
        this.appendLine(sb, "\t\t});");
        this.appendLine(sb, "\t},  ");
        this.appendLine(sb, " onAfterEdit:function(node){  ");
        this.appendLine(sb, "    if(node.text!=''){ " + this.name + "his[node.id].name=node.text; saveHistory();}\t}");
        this.appendLine(sb, "\">");
        this.appendLine(sb, "\t</ul>");
        this.appendLine(sb, "</div>");
        this.appendLine(sb, "</div></div>");
        this.appendLine(sb, "\t\t<div data-options=\"region:'center'\" style=\"padding:0px;\">");
        this.appendLine(sb, "\t\t\t<table id=\"" + this.name + "tg\" class=\"easyui-treegrid\" title=\"查询条件编辑\" style=\"width:450px;height:300px;\"");
        this.appendLine(sb, "\t\tdata-options=\"");
        this.appendLine(sb, "\t\t\ticonCls: 'icon-ok',");
        this.appendLine(sb, "\t\t\trownumbers: true,");
        this.appendLine(sb, "\t\t\tanimate: true,");
        this.appendLine(sb, "\t\t\tfitColumns: true,");
        this.appendLine(sb, "\t\t\t//url: 'sqlbuilder.json',//可以预加载条件\r\n");
        this.appendLine(sb, "\t\t\tmethod: 'get',");
        this.appendLine(sb, "\t\t\tidField: 'id',");
        this.appendLine(sb, "autoEditing: true,  ");
        this.appendLine(sb, "extEditing: false, ");
        this.appendLine(sb, "singleEditing: false ,");
        this.appendLine(sb, "\t\t\ttreeField: 'field',toolbar:toolbar,onContextMenu: onContextMenu");
        this.appendLine(sb, "\t\t\">");
        this.appendLine(sb, "<thead>");
        this.appendLine(sb, "\t<tr>");
        sb.append("\t<th data-options=\"field:'relation',width:18,formatter:function(value,row){");
        this.appendLine(sb, "\t\t\t\treturn value=='and'?'并且':'或者';");
        this.appendLine(sb, "\t\t\t},editor:{");
        this.appendLine(sb, "\t\t\t\ttype:'combobox',");
        this.appendLine(sb, "\t\t\t\toptions:{");
        this.appendLine(sb, "\t\t\t\tvalueField:'relationId',");
        this.appendLine(sb, "\t\t\t\t\t\ttextField:'relationName',");
        this.appendLine(sb, "\t\t\t\t\t\tdata:  ");
        this.appendLine(sb, "\t\t\t\t\t\t[  ");
        this.appendLine(sb, "\t\t\t\t\t\t{'relationId':'and','relationName':'并且'},  ");
        this.appendLine(sb, "\t\t\t\t\t\t{'relationId':'or','relationName':'或者'}  ");
        this.appendLine(sb, "\t\t\t\t\t\t],  ");
        this.appendLine(sb, "\t\t\t\t\t\trequired:true");
        this.appendLine(sb, "\t\t\t\t\t}}\">关系</th>");
        sb.append("\t\t<th data-options=\"field:'field',width:30,formatter:function(value,row){");
        this.appendLine(sb, "\t\t\tvar data= ");
        StringBuffer fieldArray = new StringBuffer();
        fieldArray.append("\t[  ");

        for(int i = 0; i < this.columnList.size(); ++i) {
            DataGridColumn col = (DataGridColumn)this.columnList.get(i);
            if(!"opt".equals(col.getField())) {
                fieldArray.append("\t{'fieldId':'" + this.getDBFieldName(col.getField()) + "','fieldName':'" + col.getTitle() + "'");
                if(col.getEditor() != null) {
                    fieldArray.append(",editor:'" + col.getEditor() + "'");
                }

                fieldArray.append("}");
                if(i < this.columnList.size() - 1) {
                    fieldArray.append(",");
                }
            }
        }

        fieldArray.append("]");
        sb.append(fieldArray).append(";");
        this.appendLine(sb, "for(var i=0;i<data.length;i++){");
        this.appendLine(sb, "if(value == data[i]['fieldId']){");
        this.appendLine(sb, "return data[i]['fieldName'];");
        this.appendLine(sb, "}");
        this.appendLine(sb, "}");
        this.appendLine(sb, "return value;");
        this.appendLine(sb, "},editor:{");
        this.appendLine(sb, "type:'combobox',");
        this.appendLine(sb, "\toptions:{");
        this.appendLine(sb, "valueField:'fieldId',");
        this.appendLine(sb, "textField:'fieldName',");
        this.appendLine(sb, "data:  ");
        sb.append(fieldArray);
        this.appendLine(sb, " , ");
        this.appendLine(sb, "required:true,onSelect : function(record) {");
        this.appendLine(sb, "var opts = $('#" + this.name + "tg').treegrid('getColumnOption','value');");
        this.appendLine(sb, "\tif(record.editor){");
        this.appendLine(sb, "\t\t\topts.editor=record.editor;");
        this.appendLine(sb, "\t}else{");
        this.appendLine(sb, "\t\t\topts.editor='text';");
        this.appendLine(sb, "\t}");
        this.appendLine(sb, "\tvar tr = $(this).closest('tr.datagrid-row');");
        this.appendLine(sb, "\tvar index = parseInt(tr.attr('node-id'));");
        this.appendLine(sb, "\t$('#" + this.name + "tg').treegrid('endEdit', index);");
        this.appendLine(sb, "\t$('#" + this.name + "tg').treegrid('beginEdit', index);");
        this.appendLine(sb, "}");
        this.appendLine(sb, "}}\">字段</th>");
        this.appendLine(sb, "<th data-options=\"field:'condition',width:20,align:'right',formatter:function(value,row){");
        this.appendLine(sb, "\t\t\t\t\t\t\tvar data=  ");
        this.appendLine(sb, "\t\t\t\t\t[  ");
        Map<String, List<TSType>> typedatas = ResourceUtil.allTypes;
        List<TSType> types = (List)typedatas.get("rulecon");
        int i;
        TSType type;
        if(types != null) {
            for(i = 0; i < types.size(); ++i) {
                type = (TSType)types.get(i);
                this.appendLine(sb, " {'conditionId':'" + type.getTypecode() + "','conditionName':'" + MutiLangUtil.getMutiLangInstance().getLang(type.getTypename()) + "'}");
                if(i < types.size() - 1) {
                    this.appendLine(sb, ",");
                }
            }
        }

        this.appendLine(sb, "];");
        this.appendLine(sb, "\tfor(var i=0;i<data.length;i++){");
        this.appendLine(sb, "\t\t\tif(value == data[i]['conditionId']){");
        this.appendLine(sb, "\t\t\treturn data[i]['conditionName'];");
        this.appendLine(sb, "\t\t\t}");
        this.appendLine(sb, "\t\t}");
        this.appendLine(sb, "\t\treturn value;");
        this.appendLine(sb, "\t\t},editor:{");
        this.appendLine(sb, "\t\ttype:'combobox',");
        this.appendLine(sb, "\t\toptions:{");
        this.appendLine(sb, "\t\t\tvalueField:'conditionId',");
        this.appendLine(sb, "\t\t\t\ttextField:'conditionName',\t");
        this.appendLine(sb, "\t\t\tdata:  ");
        this.appendLine(sb, "[");
        if(types != null) {
            for(i = 0; i < types.size(); ++i) {
                type = (TSType)types.get(i);
                this.appendLine(sb, " {'conditionId':'" + type.getTypecode() + "','conditionName':'" + MutiLangUtil.getMutiLangInstance().getLang(type.getTypename()) + "'}");
                if(i < types.size() - 1) {
                    this.appendLine(sb, ",");
                }
            }
        }

        this.appendLine(sb, "\t\t\t\t],  ");
        this.appendLine(sb, "\t\t\t\trequired:true");
        this.appendLine(sb, "\t\t\t}}\">条件</th>");
        sb.append("\t<th data-options=\"field:'value',width:30,editor:'text'\">值</th>");
        this.appendLine(sb, "<th data-options=\"field:'opt',width:30,formatter:function(value,row){");
        this.appendLine(sb, "\treturn '<a  onclick=\\'removeIt('+row.id+')\\' >删除</a>';}\">操作</th>");
        this.appendLine(sb, "\t\t</tr>");
        this.appendLine(sb, "\t</thead>");
        this.appendLine(sb, "\t</table>");
        this.appendLine(sb, "</div>");
        this.appendLine(sb, "<div data-options=\"region:'south',border:false\" style=\"text-align:right;padding:5px 0 0;\">");
        this.appendLine(sb, "<a class=\"" + buttonSytle + "\" data-options=\"iconCls:'icon-ok'\" href=\"javascript:void(0)\" onclick=\"javascript:queryBuilderSearch()\">确定</a>");
        this.appendLine(sb, "<a class=\"" + buttonSytle + "\" data-options=\"iconCls:'icon-cancel'\" href=\"javascript:void(0)\" onclick=\"javascript:$('#" + this.name + "_qbwin').window('close')\">取消</a>");
        this.appendLine(sb, "\t\t</div>");
        this.appendLine(sb, "\t</div>\t");
        this.appendLine(sb, "</div>\t\t");
        this.appendLine(sb, "</div>");
        this.appendLine(sb, "<div id=\"mm\" class=\"easyui-menu\" style=\"width:120px;\">");
        this.appendLine(sb, "\t<div onclick=\"append()\" data-options=\"iconCls:'icon-add'\">添加</div>");
        this.appendLine(sb, "\t<div onclick=\"edit()\" data-options=\"iconCls:'icon-edit'\">编辑</div>");
        this.appendLine(sb, "\t<div onclick=\"save()\" data-options=\"iconCls:'icon-save'\">保存</div>");
        this.appendLine(sb, "\t<div onclick=\"removeIt()\" data-options=\"iconCls:'icon-remove'\">删除</div>");
        this.appendLine(sb, "\t<div class=\"menu-sep\"></div>");
        this.appendLine(sb, "\t<div onclick=\"cancel()\">取消</div>");
        this.appendLine(sb, "<div onclick=\"expand()\">Expand</div>");
        this.appendLine(sb, "</div><div id=\"" + this.name + "mmTree\" class=\"easyui-menu\" style=\"width:100px;\">");
        this.appendLine(sb, "<div onclick=\"editTree()\" data-options=\"iconCls:'icon-edit'\">编辑</div>");
        this.appendLine(sb, "<div onclick=\"deleteTree()\" data-options=\"iconCls:'icon-remove'\">删除</div></div>");
        this.appendLine(sb, "<script type=\"text/javascript\">");
        this.appendLine(sb, "var toolbar = [{");
        this.appendLine(sb, "\ttext:'',");
        this.appendLine(sb, "\ticonCls:'icon-add',");
        this.appendLine(sb, "\thandler:function(){append();}");
        this.appendLine(sb, "},{");
        this.appendLine(sb, "\ttext:'',");
        this.appendLine(sb, "\ticonCls:'icon-edit',");
        this.appendLine(sb, "\thandler:function(){edit();}");
        this.appendLine(sb, "},{");
        this.appendLine(sb, "\ttext:'',");
        this.appendLine(sb, "\ticonCls:'icon-remove',");
        this.appendLine(sb, "\thandler:function(){removeIt();}");
        this.appendLine(sb, "},'-',{");
        this.appendLine(sb, "\ttext:'',");
        this.appendLine(sb, "\ticonCls:'icon-save',");
        this.appendLine(sb, "\thandler:function(){save();}");
        this.appendLine(sb, "\t}];");
        this.appendLine(sb, "function onContextMenu(e,row){");
        this.appendLine(sb, "\te.preventDefault();");
        this.appendLine(sb, "\t$(this).treegrid('select', row.id);");
        this.appendLine(sb, "\t$('#mm').menu('show',{");
        this.appendLine(sb, "\t\tleft: e.pageX,");
        this.appendLine(sb, "\t\ttop: e.pageY");
        this.appendLine(sb, "\t});");
        this.appendLine(sb, "}");
        this.appendLine(sb, "\tvar idIndex = 100;");
        this.appendLine(sb, "function append(){");
        this.appendLine(sb, "\tidIndex++;");
        this.appendLine(sb, "\tvar node = $('#" + this.name + "tg').treegrid('getSelected');");
        this.appendLine(sb, "\t$('#" + this.name + "tg').treegrid('append',{");
        this.appendLine(sb, "\t\tdata: [{");
        this.appendLine(sb, "\t\t\tid: idIndex,");
        this.appendLine(sb, "\t\t\tfield: '',");
        this.appendLine(sb, "\t\tcondition:'like',");
        this.appendLine(sb, "\t\tvalue: '%a%',");
        this.appendLine(sb, "\t\trelation: 'and'");
        this.appendLine(sb, "\t\t\t\t}]");
        this.appendLine(sb, "});$('#" + this.name + "tg').datagrid('beginEdit',idIndex);");
        this.appendLine(sb, "}");
        this.appendLine(sb, "\t\tfunction removeIt(id){");
        this.appendLine(sb, "var node = $('#" + this.name + "tg').treegrid('getSelected');");
        this.appendLine(sb, "if(id){");
        this.appendLine(sb, "$('#" + this.name + "tg').treegrid('remove', id);");
        this.appendLine(sb, "}else if(node){\t$('#" + this.name + "tg').treegrid('remove', node.id);");
        this.appendLine(sb, "}");
        this.appendLine(sb, "}");
        this.appendLine(sb, "function collapse(){");
        this.appendLine(sb, "\tvar node = $('#" + this.name + "tg').treegrid('getSelected');");
        this.appendLine(sb, "if(node){");
        this.appendLine(sb, "\t$('#" + this.name + "tg').treegrid('collapse', node.id);");
        this.appendLine(sb, "}");
        this.appendLine(sb, "}");
        this.appendLine(sb, "function expand(){");
        this.appendLine(sb, "var node = $('#" + this.name + "tg').treegrid('getSelected');");
        this.appendLine(sb, "if(node){");
        this.appendLine(sb, "\t$('#" + this.name + "tg').treegrid('expand', node.id);");
        this.appendLine(sb, "}");
        this.appendLine(sb, "}");
        this.appendLine(sb, "var editingId;");
        this.appendLine(sb, "function edit(id){");
        this.appendLine(sb, "var row = $('#" + this.name + "tg').treegrid('getSelected');");
        this.appendLine(sb, "if(id){\t$('#" + this.name + "tg').treegrid('beginEdit', id);}else if(row){");
        this.appendLine(sb, "\t$('#" + this.name + "tg').treegrid('beginEdit', row.id);");
        this.appendLine(sb, "}");
        this.appendLine(sb, "}");
        this.appendLine(sb, "function save(){");
        this.appendLine(sb, "\tvar t = $('#" + this.name + "tg');");
        this.appendLine(sb, "\tvar nodes = t.treegrid('getRoots');");
        this.appendLine(sb, "\tfor (var i = 0; i < nodes.length; i++) {");
        this.appendLine(sb, "\tt.treegrid('endEdit',nodes[i].id);}");
        this.appendLine(sb, "\t}");
        this.appendLine(sb, "function cancel(){");
        this.appendLine(sb, "\tvar t = $('#" + this.name + "tg');");
        this.appendLine(sb, "var nodes = t.treegrid('getRoots');for (var i = 0; i < nodes.length; i++) {t.treegrid('cancelEdit',nodes[i].id);}");
        this.appendLine(sb, "}");
        this.appendLine(sb, "var " + this.name + "his=new Array();");
        this.appendLine(sb, " function historyQuery(index) {");
        this.appendLine(sb, "\t  var data  = { rows:JSON.parse(" + this.name + "his[index].json)};  ");
        this.appendLine(sb, "\t    var t = $('#" + this.name + "tg');");
        this.appendLine(sb, "\t\tvar data = t.treegrid('loadData',data);");
        this.appendLine(sb, "\t\t$('#_sqlbuilder').val( " + this.name + "his[index].json);   ");
        this.appendLine(sb, "\t\t" + this.name + "search();");
        this.appendLine(sb, "\t}");
        this.appendLine(sb, "function view(){");
        this.appendLine(sb, "save();");
        this.appendLine(sb, "var t = $('#" + this.name + "tg');");
        this.appendLine(sb, "var data = t.treegrid('getData');");
        this.appendLine(sb, "return   JSON.stringify(data) ;");
        this.appendLine(sb, "}");
        this.appendLine(sb, "\t function queryBuilder() {");
        this.appendLine(sb, "\t$('#" + this.name + "_qbwin').window('open');");
        this.appendLine(sb, "}");
        this.appendLine(sb, "function queryBuilderSearch() {");
        this.appendLine(sb, "         var json =  view();");
        this.appendLine(sb, "\t$('#_sqlbuilder').val(json);  ");
        this.appendLine(sb, "\tvar isnew=true;");
        this.appendLine(sb, "for(var i=0;i< " + this.name + "his.length;i++){");
        this.appendLine(sb, "\tif(" + this.name + "his[i]&&" + this.name + "his[i].json==json){");
        this.appendLine(sb, "\t\tisnew=false;");
        this.appendLine(sb, "\t}");
        this.appendLine(sb, "}");
        this.appendLine(sb, "if(isnew){");
        this.appendLine(sb, " " + this.name + "his.push({name:'Query'+" + this.name + "his.length,json:json});saveHistory();");
        this.appendLine(sb, "var name= 'Query'+( " + this.name + "his.length-1);");
        this.appendLine(sb, "\tvar name= 'Query'+(" + this.name + "his.length-1);");
        this.appendLine(sb, "appendTree(" + this.name + "his.length-1,name);");
        this.appendLine(sb, "}");
        this.appendLine(sb, "\t" + this.name + "search();");
        this.appendLine(sb, " }");
        this.appendLine(sb, " $(document).ready(function(){ ");
        this.appendLine(sb, " storage=$.localStorage;if(!storage)storage=$.cookieStorage;");
        this.appendLine(sb, "\tvar _qhistory = storage.get('" + this.name + "_query_history');");
        this.appendLine(sb, " if(_qhistory){");
        this.appendLine(sb, " " + this.name + "his=_qhistory;");
        this.appendLine(sb, " \tfor(var i=0;i< " + this.name + "his.length;i++){");
        this.appendLine(sb, " \t\tif(" + this.name + "his[i])appendTree(i," + this.name + "his[i].name);");
        this.appendLine(sb, " \t}restoreheader();");
        this.appendLine(sb, " }});");
        this.appendLine(sb, "function saveHistory(){");
        this.appendLine(sb, "\tvar history=new Array();");
        this.appendLine(sb, "\tfor(var i=0;i<" + this.name + "his.length;i++){");
        this.appendLine(sb, "\t\tif(" + this.name + "his[i]){");
        this.appendLine(sb, "\t\t\thistory.push(" + this.name + "his[i]);");
        this.appendLine(sb, "\t\t}");
        this.appendLine(sb, "\t}");
        this.appendLine(sb, "\tstorage.set( '" + this.name + "_query_history',JSON.stringify(history));");
        this.appendLine(sb, "}");
        this.appendLine(sb, "function deleteTree(){");
        this.appendLine(sb, "\tvar tree = $('#" + this.name + "tt');var node= tree.tree('getSelected');");
        this.appendLine(sb, "\t" + this.name + "his[node.id]=null;saveHistory();");
        this.appendLine(sb, "\ttree.tree('remove', node.target);");
        this.appendLine(sb, "}");
        this.appendLine(sb, "function editTree(){");
        this.appendLine(sb, "\tvar node = $('#" + this.name + "tt').tree('getSelected');");
        this.appendLine(sb, "\t$('#" + this.name + "tt').tree('beginEdit',node.target);");
        this.appendLine(sb, "\tsaveHistory();");
        this.appendLine(sb, "}");
        this.appendLine(sb, "function appendTree(id,name){");
        this.appendLine(sb, "\t$('#" + this.name + "tt').tree('append',{");
        this.appendLine(sb, "\tdata:[{");
        this.appendLine(sb, "id : id,");
        this.appendLine(sb, "text :name");
        this.appendLine(sb, "\t}]");
        this.appendLine(sb, "});");
        this.appendLine(sb, "}");
        this.appendLine(sb, "</script>");
    }

    String getDBFieldName(String fieldName) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < fieldName.length(); ++i) {
            char c = fieldName.charAt(i);
            if(c <= 90 && c >= 65) {
                sb.append("_").append((char)(c + 32));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
