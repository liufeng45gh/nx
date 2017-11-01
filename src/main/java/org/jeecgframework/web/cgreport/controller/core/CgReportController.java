//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgreport.controller.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.enums.SysThemesEnum;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.DynamicDBUtil;
import org.jeecgframework.core.util.SqlUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.SysThemesUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.cgform.engine.FreemarkerHelper;
import org.jeecgframework.web.cgreport.exception.CgReportNotFoundException;
import org.jeecgframework.web.cgreport.service.core.CgReportServiceI;
import org.jeecgframework.web.cgreport.util.CgReportQueryParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/cgReportController"})
public class CgReportController extends BaseController {
    @Autowired
    private CgReportServiceI cgReportService;

    public CgReportController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public void list(String id, HttpServletRequest request, HttpServletResponse response) {
        Map cgReportMap = null;

        try {
            cgReportMap = this.cgReportService.queryCgReportConfig(id);
        } catch (Exception var20) {
            throw new CgReportNotFoundException("动态报表配置不存在!");
        }

        FreemarkerHelper viewEngine = new FreemarkerHelper();
        this.loadVars(cgReportMap, request);
        cgReportMap.put("config_iframe", this.getHtmlHead(request));
        String html = viewEngine.parseTemplate("/org/jeecgframework/web/cgreport/engine/core/cgreportlist.ftl", cgReportMap);
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
        return sb.toString();
    }

    @RequestMapping(
            params = {"popup"}
    )
    public void popup(String id, HttpServletRequest request, HttpServletResponse response) {
        Map cgReportMap = null;

        try {
            cgReportMap = this.cgReportService.queryCgReportConfig(id);
        } catch (Exception var20) {
            throw new CgReportNotFoundException("动态报表配置不存在!");
        }

        FreemarkerHelper viewEngine = new FreemarkerHelper();
        this.loadVars(cgReportMap, request);
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

    private void loadVars(Map<String, Object> cgReportMap, HttpServletRequest request) {
        Map mainM = (Map)cgReportMap.get("main");
        List<Map<String, Object>> fieldList = (List)cgReportMap.get("items");
        List<String> paramList = (List)cgReportMap.get("params");
        List<Map<String, Object>> queryList = new ArrayList(0);
        Iterator var8 = fieldList.iterator();

        while(var8.hasNext()) {
            Map<String, Object> fl = (Map)var8.next();
            fl.put("field_name", ((String)fl.get("field_name")).toLowerCase());
            String isQuery = (String)fl.get("search_flag");
            if("Y".equalsIgnoreCase(isQuery)) {
                this.loadDic(fl, fl);
                queryList.add(fl);
            }
        }

        StringBuilder sb = new StringBuilder("");
        if(paramList != null && paramList.size() > 0) {
            queryList = new ArrayList(0);
            Iterator var13 = paramList.iterator();

            while(var13.hasNext()) {
                String param = (String)var13.next();
                sb.append("&").append(param).append("=");
                String value = request.getParameter(param);
                if(StringUtil.isNotEmpty(value)) {
                    sb.append(value);
                }
            }
        }

        cgReportMap.put("config_id", mainM.get("code"));
        cgReportMap.put("config_name", mainM.get("name"));
        cgReportMap.put("config_fieldList", fieldList);
        cgReportMap.put("config_queryList", queryList);
        cgReportMap.put("config_params", sb.toString());
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

            List<Map<String, Object>> dicDatas = this.queryDic(dict_code);
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
            params = {"datagrid"}
    )
    public void datagrid(String configId, String page, String field, String rows, HttpServletRequest request, HttpServletResponse response) {
        Map cgReportMap = null;

        try {
            cgReportMap = this.cgReportService.queryCgReportConfig(configId);
            if(cgReportMap.size() <= 0) {
                throw new CgReportNotFoundException("动态报表配置不存在!");
            }
        } catch (Exception var31) {
            throw new CgReportNotFoundException("查找动态报表配置失败!" + var31.getMessage());
        }

        Map configM = (Map)cgReportMap.get("main");
        String querySql = (String)configM.get("cgreport_sql");
        List<Map<String, Object>> items = (List)cgReportMap.get("items");
        List<String> paramList = (List)cgReportMap.get("params");
        Map queryparams = new LinkedHashMap();
        Iterator var14;
        String dbKey;
        String param;
        if(paramList != null && paramList.size() > 0) {
            for(var14 = paramList.iterator(); var14.hasNext(); querySql = querySql.replace("${" + param + "}", dbKey)) {
                param = (String)var14.next();
                dbKey = request.getParameter(param);
                dbKey = dbKey == null?"":dbKey;
            }
        } else {
            var14 = items.iterator();

            while(var14.hasNext()) {
                Map<String, Object> item = (Map)var14.next();
                dbKey = (String)item.get("search_flag");
                if("Y".equalsIgnoreCase(dbKey)) {
                    CgReportQueryParamUtil.loadQueryParams(request, item, queryparams);
                }
            }
        }

        int p = page == null?1:Integer.parseInt(page);
        int r = rows == null?99999:Integer.parseInt(rows);
        dbKey = (String)configM.get("db_source");
        List<Map<String, Object>> result = null;
        Long size = Long.valueOf(0L);
        if(StringUtils.isNotBlank(dbKey)) {
            result = DynamicDBUtil.findList(dbKey, SqlUtil.jeecgCreatePageSql(dbKey, querySql, queryparams, p, r), new Object[0]);
            Map map = (Map)DynamicDBUtil.findOne(dbKey, SqlUtil.getCountSql(querySql, (Map)null), new Object[0]);
            if(map.get("COUNT(*)") instanceof BigDecimal) {
                BigDecimal count = (BigDecimal)map.get("COUNT(*)");
                size = Long.valueOf(count.longValue());
            } else {
                size = (Long)map.get("COUNT(*)");
            }
        } else {
            result = this.cgReportService.queryByCgReportSql(querySql, queryparams, p, r);
            size = Long.valueOf(this.cgReportService.countQueryByCgReportSql(querySql, queryparams));
        }

        this.dealDic(result, items);
        this.dealReplace(result, items);
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        PrintWriter writer = null;

        try {
            writer = response.getWriter();
            writer.println(CgReportQueryParamUtil.getJson(result, size));
            writer.flush();
        } catch (IOException var29) {
            var29.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception var28) {
                ;
            }

        }

    }

    @RequestMapping(
            params = {"getFields"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public Object getSqlFields(String sql, String dbKey) {
        List<String> fields = null;
        List<String> params = null;
        HashMap reJson = new HashMap();

        try {
            fields = this.getFields(sql, dbKey);
            params = this.getSqlParams(sql);
        } catch (Exception var9) {
            var9.printStackTrace();
            String errorInfo = "解析失败!<br><br>失败原因：";
            int i = var9.getMessage().indexOf("Connection refused: connect");
            if(i != -1) {
                errorInfo = errorInfo + "数据源连接失败.";
            } else {
                errorInfo = errorInfo + "SQL语法错误.";
            }

            reJson.put("status", "error");
            reJson.put("datas", errorInfo);
            return reJson;
        }

        reJson.put("status", "success");
        reJson.put("fields", fields);
        reJson.put("params", params);
        return reJson;
    }

    private List<String> getFields(String sql, String dbKey) {
        List<String> fields = null;
        sql = this.getSql(sql);
        if(StringUtils.isNotBlank(dbKey)) {
            List<Map<String, Object>> dataList = DynamicDBUtil.findList(dbKey, SqlUtil.jeecgCreatePageSql(dbKey, sql, (Map)null, 1, 1), (Object[])null);
            if(dataList.size() < 1) {
                throw new BusinessException("该报表sql没有数据");
            }

            Set fieldsSet = ((Map)dataList.get(0)).keySet();
            fields = new ArrayList(fieldsSet);
        } else {
            fields = this.cgReportService.getSqlFields(sql);
        }

        return (List)fields;
    }

    private String getSql(String sql) {
        String regex = "\\$\\{\\w+\\}";
        Pattern p = Pattern.compile(regex);

        Matcher m;
        String whereParam;
        for(m = p.matcher(sql); m.find(); sql = sql.replace("'''", "''")) {
            whereParam = m.group();
            sql = sql.replace(whereParam, "'' or 1=1 or 1=''");
        }

        regex = "\\{\\w+\\}";
        p = Pattern.compile(regex);

        for(m = p.matcher(sql); m.find(); sql = sql.replace(whereParam, " 1=1 ")) {
            whereParam = m.group();
        }

        return sql;
    }

    public List<String> getSqlParams(String sql) {
        if(oConvertUtils.isEmpty(sql)) {
            return null;
        } else {
            List<String> params = new ArrayList();
            String regex = "\\$\\{\\w+\\}";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(sql);

            while(m.find()) {
                String whereParam = m.group();
                params.add(whereParam.substring(whereParam.indexOf("{") + 1, whereParam.indexOf("}")));
            }

            return params;
        }
    }

    private void loadDic(Map m, Map<String, Object> cgReportMap) {
        String dict_code = (String)cgReportMap.get("dict_code");
        if(StringUtil.isEmpty(dict_code)) {
            m.put("field_dictlist", new ArrayList(0));
        } else {
            List<Map<String, Object>> dicDatas = this.queryDic(dict_code);
            m.put("field_dictlist", dicDatas);
        }
    }

    private List<Map<String, Object>> queryDic(String diccode) {
        StringBuilder dicSql = new StringBuilder();
        dicSql.append(" SELECT TYPECODE,TYPENAME FROM");
        dicSql.append(" t_s_type");
        dicSql.append(" WHERE TYPEGROUPID = ");
        dicSql.append(" (SELECT ID FROM t_s_typegroup WHERE TYPEGROUPCODE = '" + diccode + "' )");
        List<Map<String, Object>> dicDatas = this.cgReportService.findForJdbc(dicSql.toString(), new Object[0]);
        return dicDatas;
    }
}
