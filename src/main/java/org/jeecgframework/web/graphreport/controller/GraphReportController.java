//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.graphreport.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.enums.SysThemesEnum;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.SysThemesUtil;
import org.jeecgframework.web.cgform.engine.FreemarkerHelper;
import org.jeecgframework.web.cgreport.exception.CgReportNotFoundException;
import org.jeecgframework.web.cgreport.service.excel.CgReportExcelServiceI;
import org.jeecgframework.web.cgreport.util.CgReportQueryParamUtil;
import org.jeecgframework.web.graphreport.service.core.GraphReportServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/graphReportController"})
public class GraphReportController extends BaseController {
    @Autowired
    private GraphReportServiceI graphReportService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private CgReportExcelServiceI cgReportExcelService;

    public GraphReportController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public void list(String id, HttpServletRequest request, HttpServletResponse response) {
        Map cgReportMap = null;

        try {
            cgReportMap = this.graphReportService.queryCgReportConfig(id);
        } catch (Exception var20) {
            throw new CgReportNotFoundException("动态报表配置不存在!");
        }

        FreemarkerHelper viewEngine = new FreemarkerHelper();
        this.loadVars(cgReportMap);
        cgReportMap.put("config_iframe", this.getHtmlHead(request));
        String html = viewEngine.parseTemplate("/org/jeecgframework/web/graphreport/engine/core/graphreportlist.ftl", cgReportMap);
        PrintWriter writer = null;

        try {
            response.setContentType("text/html");
            response.setHeader("Cache-Control", "no-store");
            writer = response.getWriter();
            writer.println(html);
            writer.flush();
        } catch (IOException var18) {
            var18.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception var17) {
                ;
            }

        }

    }

    private String getHtmlHead(HttpServletRequest request) {
        HttpSession session = ContextHolderUtils.getSession();
        String lang = (String)session.getAttribute("lang");
        StringBuilder sb = new StringBuilder("");
        SysThemesEnum sysThemesEnum = SysThemesUtil.getSysTheme(request);
        sb.append(SysThemesUtil.getReportTheme(sysThemesEnum));
        sb.append(SysThemesUtil.getCommonTheme(sysThemesEnum));
        sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery/jquery-1.8.3.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/dataformat.js\"></script>");
        sb.append(SysThemesUtil.getEasyUiTheme(sysThemesEnum));
        sb.append("<link rel=\"stylesheet\" href=\"plug-in/easyui/themes/icon.css\" type=\"text/css\"></link>");
        sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"plug-in/accordion/css/accordion.css\">");
        sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"plug-in/accordion/css/icons.css\">");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/jquery.easyui.min.1.3.2.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/locale/zh-cn.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/syUtil.js\"></script>");
        sb.append(SysThemesUtil.getLhgdialogTheme(sysThemesEnum));
        sb.append(StringUtil.replace("<script type=\"text/javascript\" src=\"plug-in/tools/curdtools_{0}.js\"></script>", "{0}", lang));
        sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/easyuiextend.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/extends/datagrid-scrollview.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/My97DatePicker/WdatePicker.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/graphreport/highcharts3.0.6.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/graphreport/spin.min.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/graphreport/report.js\"></script>");
        return sb.toString();
    }

    @RequestMapping(
            params = {"popup"}
    )
    public void popup(String id, HttpServletRequest request, HttpServletResponse response) {
        Map cgReportMap = null;

        try {
            cgReportMap = this.graphReportService.queryCgReportConfig(id);
        } catch (Exception var20) {
            throw new CgReportNotFoundException("动态报表配置不存在!");
        }

        FreemarkerHelper viewEngine = new FreemarkerHelper();
        this.loadVars(cgReportMap);
        cgReportMap.put("config_iframe", this.getHtmlHead(request));
        String html = viewEngine.parseTemplate("/org/jeecgframework/web/cgreport/engine/core/cgreportlistpopup.ftl", cgReportMap);
        PrintWriter writer = null;

        try {
            response.setContentType("text/html");
            response.setHeader("Cache-Control", "no-store");
            writer = response.getWriter();
            writer.println(html);
            writer.flush();
        } catch (IOException var18) {
            var18.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception var17) {
                ;
            }

        }

    }

    private void loadVars(Map<String, Object> cgReportMap) {
        Map mainM = (Map)cgReportMap.get("main");
        List<Map<String, Object>> fieldList = (List)cgReportMap.get("items");
        List<Map<String, Object>> queryList = new ArrayList(0);
        List<Map<String, Object>> graphList = new ArrayList(0);
        Set<String> tabSet = new HashSet();
        List<String> tabList = new ArrayList();
        Iterator var9 = fieldList.iterator();

        while(true) {
            Map fl;
            do {
                if(!var9.hasNext()) {
                    cgReportMap.put("config_id", mainM.get("code"));
                    cgReportMap.put("config_name", mainM.get("name"));
                    cgReportMap.put("config_fieldList", fieldList);
                    cgReportMap.put("config_queryList", queryList);
                    cgReportMap.put("graphList", graphList);
                    cgReportMap.put("tabList", tabList);
                    return;
                }

                fl = (Map)var9.next();
                fl.put("field_name", ((String)fl.get("field_name")).toLowerCase());
                String isQuery = (String)fl.get("search_flag");
                if("Y".equalsIgnoreCase(isQuery)) {
                    this.loadDic(fl, fl);
                    queryList.add(fl);
                }
            } while(!"y".equals(fl.get("is_graph")) && !"Y".equals(fl.get("is_graph")));

            graphList.add(fl);
            String tabName = fl.get("tab_name") == null?"":fl.get("tab_name").toString();
            if(!tabSet.contains(tabName)) {
                tabList.add(tabName);
                tabSet.add(tabName);
            }
        }
    }

    private void dealDic(List<Map<String, Object>> result, List<Map<String, Object>> beans) {
        Iterator var4 = beans.iterator();

        while(true) {
            Map bean;
            String dict_code;
            do {
                if(!var4.hasNext()) {
                    return;
                }

                bean = (Map)var4.next();
                dict_code = (String)bean.get("dict_code");
            } while(StringUtil.isEmpty(dict_code));

            List<Map<String, Object>> dicDatas = this.queryDicBySQL(dict_code);
            Iterator var8 = result.iterator();

            while(var8.hasNext()) {
                Map r = (Map)var8.next();
                String value = String.valueOf(r.get(bean.get("field_name")));
                Iterator var11 = dicDatas.iterator();

                while(var11.hasNext()) {
                    Map m = (Map)var11.next();
                    String typecode = String.valueOf(m.get("typecode"));
                    String typename = String.valueOf(m.get("typename"));
                    if(value.equalsIgnoreCase(typecode)) {
                        r.put(bean.get("field_name"), typename);
                    }
                }
            }
        }
    }

    private List<Map<String, Object>> queryDicBySQL(String dictCodeOrSQL) {
        List<Map<String, Object>> dicDatas = null;
        dictCodeOrSQL = dictCodeOrSQL.trim();
        if(dictCodeOrSQL.toLowerCase().startsWith("select ")) {
            dictCodeOrSQL = dictCodeOrSQL.replaceAll("'[kK][eE][yY]'", "typecode").replaceAll("'[vV][aA][lL][uU][eE]'", "typename");
            dicDatas = this.systemService.findForJdbc(dictCodeOrSQL, (Object[])null);
        } else {
            dicDatas = this.queryDic(dictCodeOrSQL);
        }

        return dicDatas;
    }

    private void dealReplace(List<Map<String, Object>> result, List<Map<String, Object>> beans) {
        Iterator var4 = beans.iterator();

        while(var4.hasNext()) {
            Map bean = (Map)var4.next();

            try {
                String replace = (String)bean.get("replace_value");
                if(!StringUtil.isEmpty(replace)) {
                    String[] groups = replace.split(",");
                    String[] var10 = groups;
                    int var9 = groups.length;

                    for(int var8 = 0; var8 < var9; ++var8) {
                        String g = var10[var8];
                        String[] items = g.split("_");
                        String v = items[0];
                        String txt = items[1];
                        Iterator var15 = result.iterator();

                        while(var15.hasNext()) {
                            Map r = (Map)var15.next();
                            String value = String.valueOf(r.get(bean.get("field_name")));
                            if(value.equalsIgnoreCase(v)) {
                                r.put(bean.get("field_name"), txt);
                            }
                        }
                    }
                }
            } catch (Exception var17) {
                var17.printStackTrace();
                throw new BusinessException("取值表达式不正确");
            }
        }

    }

    @RequestMapping(
            params = {"datagridGraph"}
    )
    public void datagridGraph(String configId, HttpServletRequest request, HttpServletResponse response) {
        Map cgReportMap = null;

        try {
            cgReportMap = this.graphReportService.queryCgReportConfig(configId);
            if(cgReportMap.size() <= 0) {
                throw new CgReportNotFoundException("动态报表配置不存在!");
            }
        } catch (Exception var26) {
            throw new CgReportNotFoundException("查找动态报表配置失败!" + var26.getMessage());
        }

        PrintWriter writer = null;

        try {
            Map configM = (Map)cgReportMap.get("main");
            String querySql = (String)configM.get("CGR_SQL");
            List<Map<String, Object>> items = (List)cgReportMap.get("items");
            Map queryparams = new LinkedHashMap();
            Iterator var11 = items.iterator();

            while(var11.hasNext()) {
                Map<String, Object> item = (Map)var11.next();
                String isQuery = (String)item.get("search_flag");
                if("Y".equalsIgnoreCase(isQuery)) {
                    CgReportQueryParamUtil.loadQueryParams(request, item, queryparams);
                }
            }

            List<Map<String, Object>> result = this.graphReportService.queryByCgReportSql(querySql, queryparams, -1, -1);
            this.dealDic(result, items);
            this.dealReplace(result, items);
            List<String> fields = new ArrayList();
            List<Map<String, Object>> configItems = (List)cgReportMap.get("items");
            Iterator var14 = configItems.iterator();

            while(var14.hasNext()) {
                Map<String, Object> map = (Map)var14.next();
                if("Y".equals(map.get("is_show"))) {
                    fields.add(map.get("field_txt").toString());
                    fields.add(map.get("field_name").toString());
                }
            }

            if(!this.exportExecel(request, response, configM.get("content").toString(), configM.get("content").toString(), (String[])fields.toArray(new String[fields.size()]), result, (Map)null)) {
                response.setContentType("application/json");
                response.setHeader("Cache-Control", "no-store");
                writer = response.getWriter();
                writer.println(CgReportQueryParamUtil.getJson(result, Long.valueOf(-1L)));
                writer.flush();
                return;
            }
        } catch (IOException var24) {
            var24.printStackTrace();
            throw new RuntimeException(var24);
        } finally {
            try {
                writer.close();
            } catch (Exception var23) {
                ;
            }

        }

    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(String configId, String page, String field, String rows, HttpServletRequest request, HttpServletResponse response) {
        Map cgReportMap = null;

        try {
            cgReportMap = this.graphReportService.queryCgReportConfig(configId);
            if(cgReportMap.size() <= 0) {
                throw new CgReportNotFoundException("动态报表配置不存在!");
            }
        } catch (Exception var30) {
            throw new CgReportNotFoundException("查找动态报表配置失败!" + var30.getMessage());
        }

        Map configM = (Map)cgReportMap.get("main");
        String querySql = (String)configM.get("cgreport_sql");
        List<Map<String, Object>> items = (List)cgReportMap.get("items");
        Map queryparams = new LinkedHashMap();
        Iterator var13 = items.iterator();

        while(var13.hasNext()) {
            Map<String, Object> item = (Map)var13.next();
            String isQuery = (String)item.get("search_flag");
            if("Y".equalsIgnoreCase(isQuery)) {
                CgReportQueryParamUtil.loadQueryParams(request, item, queryparams);
            }
        }

        int p = page == null?1:Integer.parseInt(page);
        int r = rows == null?99999:Integer.parseInt(rows);
        List<Map<String, Object>> result = this.graphReportService.queryByCgReportSql(querySql, queryparams, p, r);
        long size = this.graphReportService.countQueryByCgReportSql(querySql, queryparams);
        this.dealDic(result, items);
        this.dealReplace(result, items);
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        PrintWriter writer = null;

        try {
            writer = response.getWriter();
            writer.println(CgReportQueryParamUtil.getJson(result, Long.valueOf(size)));
            writer.flush();
        } catch (IOException var28) {
            var28.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception var27) {
                ;
            }

        }

    }

    @RequestMapping(
            params = {"getFields"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public Object getSqlFields(String sql) {
        List<String> result = null;
        HashMap reJson = new HashMap();

        try {
            result = this.graphReportService.getSqlFields(sql);
        } catch (Exception var6) {
            var6.printStackTrace();
            String errorInfo = "解析失败!<br><br>失败原因：";
            errorInfo = errorInfo + var6.getMessage();
            reJson.put("status", "error");
            reJson.put("datas", errorInfo);
            return reJson;
        }

        reJson.put("status", "success");
        reJson.put("datas", result);
        return reJson;
    }

    private void loadDic(Map m, Map<String, Object> cgReportMap) {
        String dict_code = (String)cgReportMap.get("dict_code");
        if(StringUtil.isEmpty(dict_code)) {
            m.put("field_dictlist", new ArrayList(0));
        } else {
            List<Map<String, Object>> dicDatas = this.queryDicBySQL(dict_code);
            m.put("field_dictlist", dicDatas);
        }
    }

    private List<Map<String, Object>> queryDic(String diccode) {
        StringBuilder dicSql = new StringBuilder();
        dicSql.append(" SELECT TYPECODE,TYPENAME FROM");
        dicSql.append(" t_s_type");
        dicSql.append(" WHERE TYPEGROUPID = ");
        dicSql.append(" (SELECT ID FROM t_s_typegroup WHERE TYPEGROUPCODE = '" + diccode + "' )");
        List<Map<String, Object>> dicDatas = this.graphReportService.findForJdbc(dicSql.toString(), new Object[0]);
        return dicDatas;
    }

    private boolean exportExecel(HttpServletRequest request, HttpServletResponse response, String title, String tagName, String[] fields, List<Map<String, Object>> list, Map<String, Object> params) {
        if(!"1".equals(request.getParameter("export"))) {
            return false;
        } else {
            if(params == null) {
                new HashMap();
            }

            if(StringUtil.isEmpty(tagName)) {
                tagName = title;
            }

            response.setContentType("application/vnd.ms-excel");
            ServletOutputStream fOut = null;

            try {
                String browse = BrowserUtils.checkBrowse(request);
                String workbookName;
                if("MSIE".equalsIgnoreCase(browse.substring(0, 4))) {
                    response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(title, "UTF-8") + ".xls");
                } else {
                    workbookName = new String(title.getBytes("UTF-8"), "ISO8859-1");
                    response.setHeader("content-disposition", "attachment;filename=" + workbookName + ".xls");
                }

                workbookName = null;
                List<Map<String, Object>> fieldList = new ArrayList();

                for(int i = 0; i < fields.length; ++i) {
                    Map<String, Object> map = new HashMap();
                    map.put("field_txt", fields[i]);
                    map.put("field_name", fields[i + 1]);
                    fieldList.add(map);
                    ++i;
                }

                HSSFWorkbook workbook = this.cgReportExcelService.exportExcel(tagName, fieldList, list);
                fOut = response.getOutputStream();
                workbook.write(fOut);
            } catch (UnsupportedEncodingException var24) {
                var24.printStackTrace();
            } catch (Exception var25) {
                var25.printStackTrace();
            } finally {
                try {
                    fOut.flush();
                    fOut.close();
                } catch (IOException var23) {
                    var23.printStackTrace();
                }

            }

            return true;
        }
    }
}
