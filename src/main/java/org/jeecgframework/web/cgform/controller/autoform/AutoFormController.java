//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.controller.autoform;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.DynamicDBUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.cgform.entity.autoform.AutoFormDbEntity;
import org.jeecgframework.web.cgform.entity.autoform.AutoFormDbFieldEntity;
import org.jeecgframework.web.cgform.entity.autoform.AutoFormEntity;
import org.jeecgframework.web.cgform.entity.autoform.AutoFormParamEntity;
import org.jeecgframework.web.cgform.entity.autoform.AutoFormStyleEntity;
import org.jeecgframework.web.cgform.service.autoform.AutoFormDbServiceI;
import org.jeecgframework.web.cgform.service.autoform.AutoFormServiceI;
import org.jeecgframework.web.cgform.util.AutoFormCommUtil;
import org.jeecgframework.web.cgform.util.AutoFormTemplateParseUtil;
import org.jeecgframework.web.cgform.util.TemplateUtil;
import org.jeecgframework.web.system.pojo.base.DynamicDataSourceEntity;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.service.DynamicDataSourceServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/autoFormController"})
public class AutoFormController extends BaseController {
    private static final Logger logger = Logger.getLogger(AutoFormController.class);
    @Autowired
    private AutoFormServiceI autoFormService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private AutoFormDbServiceI autoFormDbService;
    @Autowired
    private DynamicDataSourceServiceI dynamicDataSourceServiceI;

    public AutoFormController() {
    }

    @RequestMapping(
            params = {"autoForm"}
    )
    public ModelAndView autoForm(HttpServletRequest request) {
        return new ModelAndView("jeecg/cgform/autoform/autoFormList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(AutoFormEntity autoForm, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(AutoFormEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, autoForm, request.getParameterMap());
        cq.add();
        this.autoFormService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(AutoFormEntity autoForm, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        autoForm = (AutoFormEntity)this.systemService.getEntity(AutoFormEntity.class, autoForm.getId());
        String message = "表单表删除成功";

        try {
            this.autoFormService.delete(autoForm);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            this.delFormDb(autoForm.getId());
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "表单表删除失败";
            throw new BusinessException(var6.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doBatchDel"}
    )
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String message = "表单表删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                AutoFormEntity autoForm = (AutoFormEntity)this.systemService.getEntity(AutoFormEntity.class, id);
                this.autoFormService.delete(autoForm);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
                this.delFormDb(id);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "表单表删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(AutoFormEntity autoForm, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String message = "表单表添加成功";

        try {
            if(StringUtils.isNotBlank(autoForm.getFormContent())) {
                JSONObject jsonObj = JSONObject.fromObject(autoForm.getFormContent());
                String html = (String)jsonObj.get("template");
                autoForm.setFormContent(html);
                autoForm.setFormParse(autoForm.getFormContent());
            }

            autoForm.setCreateDate(new Date());
            autoForm.setCreateName(ResourceUtil.getSessionUserName().getRealName());
            autoForm.setCreateBy(ResourceUtil.getSessionUserName().getUserName());
            this.autoFormService.save(autoForm);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var7) {
            var7.printStackTrace();
            message = "表单表添加失败";
            throw new BusinessException(var7.getMessage());
        }

        j.setMsg(autoForm.getId());
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(AutoFormEntity autoForm, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String message = "";

        try {
            Map<String, Object> attributes = new HashMap();
            if(StringUtils.isNotBlank(autoForm.getId())) {
                AutoFormEntity t = (AutoFormEntity)this.autoFormService.get(AutoFormEntity.class, autoForm.getId());
                MyBeanUtils.copyBeanNotNull2Bean(autoForm, t);
                if(StringUtils.isNotBlank(autoForm.getFormContent())) {
                    JSONObject jsonObj = JSONObject.fromObject(autoForm.getFormContent());
                    String html = (String)jsonObj.get("template");
                    this.validateId(html);
                    t.setFormContent(html);
                    t.setFormParse(autoForm.getFormContent());
                }

                autoForm.setUpdateDate(new Date());
                autoForm.setUpdateName(ResourceUtil.getSessionUserName().getRealName());
                autoForm.setUpdateBy(ResourceUtil.getSessionUserName().getUserName());
                this.autoFormService.saveOrUpdate(t);
                attributes.put("id", t.getId());
                j.setAttributes(attributes);
                message = "表单更新成功";
                if(StringUtils.isBlank(autoForm.getMainTableSource())) {
                    message = message + ",主数据源为空，请选择主数据源！";
                }
            } else {
                if(StringUtils.isNotBlank(autoForm.getFormContent())) {
                    JSONObject jsonObj = JSONObject.fromObject(autoForm.getFormContent());
                    String html = (String)jsonObj.get("template");
                    this.validateId(html);
                    autoForm.setFormContent(html);
                    autoForm.setFormParse(autoForm.getFormContent());
                }

                this.autoFormService.save(autoForm);
                attributes.put("id", autoForm.getId());
                j.setAttributes(attributes);
                message = "表单添加成功";
                if(StringUtils.isBlank(autoForm.getMainTableSource())) {
                    message = message + ",主数据源为空，请选择主数据源！";
                }
            }

            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var9) {
            j.setSuccess(false);
            message = "表单保存失败!" + var9.getMessage();
        }

        j.setMsg(message);
        return j;
    }

    private void validateId(String html) {
        if(!StringUtil.isEmpty(html)) {
            Document doc = Jsoup.parse(html);
            Map<String, String> map = new HashMap();
            Elements IDs = doc.select("input[name$=.ID]");
            Iterator var6 = IDs.iterator();

            while(var6.hasNext()) {
                Element el = (Element)var6.next();
                String name = el.attr("name");
                if(map.get(name) != null) {
                    throw new BusinessException("主键ID有重复【" + name + "】,编辑器进入HTML模式删除重复的主键");
                }

                map.put(name, name);
            }

        }
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(AutoFormEntity autoForm, HttpServletRequest req) {
        JSONArray jsonArray = JSONArray.fromObject(this.getStyleList(""));
        req.setAttribute("styleSelect", jsonArray.toString());
        return new ModelAndView("jeecg/cgform/autoform/autoForm-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(AutoFormEntity autoForm, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(autoForm.getId())) {
            autoForm = (AutoFormEntity)this.autoFormService.getEntity(AutoFormEntity.class, autoForm.getId());
            req.setAttribute("autoFormPage", autoForm);
        }

        JSONArray jsonArray = JSONArray.fromObject(this.getStyleList(autoForm.getFormStyleId()));
        req.setAttribute("styleSelect", jsonArray.toString());
        JSONArray dbArray = JSONArray.fromObject(this.getFormDbList(autoForm.getId()));
        req.setAttribute("dbDate", dbArray.toString());
        return new ModelAndView("jeecg/cgform/autoform/autoForm-update");
    }

    private List<Map<String, Object>> getStyleList(String styleid) {
        String hql = "from AutoFormStyleEntity";
        List<AutoFormStyleEntity> list = this.systemService.findHql(hql, new Object[0]);
        List<Map<String, Object>> dateList = new ArrayList();
        Map<String, Object> map = null;

        for(Iterator var7 = list.iterator(); var7.hasNext(); dateList.add(map)) {
            AutoFormStyleEntity style = (AutoFormStyleEntity)var7.next();
            map = new HashMap();
            map.put("id", style.getId());
            map.put("name", style.getStyleDesc());
            if(StringUtils.isNotBlank(styleid) && styleid.equals(style.getId())) {
                map.put("checked", Boolean.valueOf(true));
            } else {
                map.put("checked", Boolean.valueOf(false));
            }
        }

        return dateList;
    }

    @RequestMapping(
            params = {"treeReload"}
    )
    @ResponseBody
    public AjaxJson treeReload(AutoFormDbEntity autoFormdb, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        JSONArray jsonArray = JSONArray.fromObject(this.getFormDbList(autoFormdb.getAutoFormId()));
        j.setMsg(jsonArray.toString());
        return j;
    }

    private List<Map<String, Object>> getFormDbList(String autoFormId) {
        List<AutoFormDbEntity> list = this.systemService.findByProperty(AutoFormDbEntity.class, "autoFormId", autoFormId);
        List<Map<String, Object>> dateList = new ArrayList();
        Map<String, Object> map = null;
        Iterator var6 = list.iterator();

        while(true) {
            while(var6.hasNext()) {
                AutoFormDbEntity dbForm = (AutoFormDbEntity)var6.next();
                map = new HashMap();
                map.put("id", dbForm.getId());
                map.put("name", dbForm.getDbChName() + "(" + dbForm.getDbName() + ")");
                map.put("dbCode", dbForm.getDbName());
                map.put("pid", "0");
                dateList.add(map);
                List fieldlist;
                AutoFormDbFieldEntity field;
                Iterator var9;
                if("table".equals(dbForm.getDbType())) {
                    fieldlist = this.systemService.findByProperty(AutoFormDbFieldEntity.class, "autoFormDbId", dbForm.getId());
                    var9 = fieldlist.iterator();

                    while(var9.hasNext()) {
                        field = (AutoFormDbFieldEntity)var9.next();
                        map = new HashMap();
                        map.put("id", field.getId());
                        map.put("name", !StringUtils.isBlank(field.getFieldText()) && !"null".equals(field.getFieldText())?field.getFieldText() + "(" + field.getFieldName() + ")":field.getFieldName());
                        map.put("pId", dbForm.getId());
                        map.put("nocheck", Boolean.valueOf(true));
                        dateList.add(map);
                    }
                } else if("sql".equals(dbForm.getDbType())) {
                    fieldlist = this.systemService.findByProperty(AutoFormDbFieldEntity.class, "autoFormDbId", dbForm.getId());
                    if(fieldlist.size() <= 0) {
                        if(StringUtils.isNotBlank(dbForm.getDbDynSql())) {
                            List<String> files = this.autoFormDbService.getSqlFields(dbForm.getDbDynSql());
                            Iterator var10 = files.iterator();

                            while(var10.hasNext()) {
                                String filed = (String)var10.next();
                                map = new HashMap();
                                map.put("id", "");
                                map.put("name", filed);
                                map.put("pId", dbForm.getId());
                                map.put("nocheck", Boolean.valueOf(true));
                                dateList.add(map);
                            }
                        }
                    } else {
                        var9 = fieldlist.iterator();

                        while(var9.hasNext()) {
                            field = (AutoFormDbFieldEntity)var9.next();
                            map = new HashMap();
                            map.put("id", field.getId());
                            map.put("name", !StringUtils.isBlank(field.getFieldText()) && !"null".equals(field.getFieldText())?field.getFieldText() + "(" + field.getFieldName() + ")":field.getFieldName());
                            map.put("pId", dbForm.getId());
                            map.put("nocheck", Boolean.valueOf(true));
                            dateList.add(map);
                        }
                    }
                }
            }

            return dateList;
        }
    }

    @RequestMapping(
            params = {"docreateCode"}
    )
    @ResponseBody
    public AjaxJson docreateCode(String formdbId, String styleId, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        AutoFormStyleEntity styleEntity = (AutoFormStyleEntity)this.systemService.get(AutoFormStyleEntity.class, styleId);
        String content = styleEntity.getStyleContent();
        String[] ids = formdbId.split(",");
        List<Map<String, Object>> dsList = new ArrayList();
        String autoFormDbId;
        if(ids.length > 0) {
            String[] var12 = ids;
            int var11 = ids.length;

            for(int var10 = 0; var10 < var11; ++var10) {
                autoFormDbId = var12[var10];
                Map<String, Object> dsData = new HashMap();
                AutoFormDbEntity db = (AutoFormDbEntity)this.systemService.findUniqueByProperty(AutoFormDbEntity.class, "id", autoFormDbId);
                dsData.put("dsName", db.getDbName());
                dsList.add(dsData);
                String hql;
                List columns;
                if("table".equals(db.getDbType())) {
                    hql = "select new Map(t.fieldName as fieldName,t.fieldText as fieldText) from AutoFormDbFieldEntity t where t.autoFormDbId=?";
                    columns = this.systemService.findHql(hql, new Object[]{autoFormDbId});
                    dsData.put("columns", columns);
                } else if("sql".equals(db.getDbType())) {
                    hql = "select new Map(t.fieldName as fieldName,t.fieldText as fieldText) from AutoFormDbFieldEntity t where t.autoFormDbId=?";
                    columns = this.systemService.findHql(hql, new Object[]{autoFormDbId});
                    if(columns.size() > 0) {
                        dsData.put("columns", columns);
                    } else {
                        List<String> fileds = this.autoFormDbService.getSqlFields(db.getDbDynSql());
                        List<Map<String, Object>> column = new ArrayList();
                        Iterator var20 = fileds.iterator();

                        while(var20.hasNext()) {
                            String filed = (String)var20.next();
                            Map<String, Object> map = new HashMap();
                            map.put("fieldName", filed.toLowerCase());
                            column.add(map);
                        }

                        dsData.put("columns", column);
                    }
                }
            }
        }

        autoFormDbId = this.createFtl(content, dsList);
        j.setMsg(autoFormDbId.replaceAll("\"", "&quot;"));
        return j;
    }

    private String createFtl(String content, List<Map<String, Object>> dsList) {
        String result = "";

        try {
            Configuration cfg = new Configuration();
            StringTemplateLoader stringLoader = new StringTemplateLoader();
            stringLoader.putTemplate("autoformTemplete", content);
            cfg.setTemplateLoader(stringLoader);
            Template template = cfg.getTemplate("autoformTemplete", "utf-8");
            Map<String, Object> root = new HashMap();
            root.put("dsList", dsList);
            StringWriter writer = new StringWriter();
            template.process(root, writer);
            result = writer.toString();
        } catch (TemplateException var9) {
            var9.printStackTrace();
        } catch (IOException var10) {
            var10.printStackTrace();
        }

        return result;
    }

    @RequestMapping(
            params = {"parse"}
    )
    public ModelAndView parse(AutoFormEntity autoForm, HttpServletRequest req) {
        if(StringUtils.isNotBlank(autoForm.getId())) {
            autoForm = (AutoFormEntity)this.systemService.findUniqueByProperty(AutoFormEntity.class, "id", autoForm.getId());
            List<String> paramList = this.getFormParams(autoForm.getId());
            List<AutoFormDbEntity> autoList = this.systemService.findByProperty(AutoFormDbEntity.class, "dbName", autoForm.getMainTableSource());
            if(autoList != null && autoList.size() > 0) {
                AutoFormDbEntity autoFormDbEntity = (AutoFormDbEntity)autoList.get(0);
                req.setAttribute("dbKey", autoFormDbEntity.getDbKey());
            }

            req.setAttribute("paramList", paramList);
            req.setAttribute("autoFormPage", autoForm);
        }

        return new ModelAndView("jeecg/cgform/autoform/autoForm-view");
    }

    private List<String> getFormParams(String fromId) {
        List<String> paramList = new ArrayList();
        List<AutoFormDbEntity> formDbList = this.systemService.findByProperty(AutoFormDbEntity.class, "autoFormId", fromId);
        Iterator var5 = formDbList.iterator();

        while(true) {
            List params;
            label27:
            do {
                while(var5.hasNext()) {
                    AutoFormDbEntity dbEntity = (AutoFormDbEntity)var5.next();
                    if("sql".equals(dbEntity.getDbType())) {
                        params = this.systemService.findByProperty(AutoFormParamEntity.class, "autoFormDbId", dbEntity.getId());
                        continue label27;
                    }

                    if("table".equals(dbEntity.getDbType())) {
                        paramList.add("id");
                    }
                }

                return paramList;
            } while(params.size() <= 0);

            Iterator var8 = params.iterator();

            while(var8.hasNext()) {
                AutoFormParamEntity entity = (AutoFormParamEntity)var8.next();
                if(!paramList.contains(entity.getParamName())) {
                    paramList.add(entity.getParamName());
                }
            }
        }
    }

    @RequestMapping(
            params = {"viewContent"}
    )
    public ModelAndView viewContent(AutoFormEntity autoForm, HttpServletRequest req) {
        String message = "";

        try {
            Map<String, String[]> tem = req.getParameterMap();
            Map<String, Object> paramMap = new HashMap();
            Iterator var7 = tem.keySet().iterator();

            String op;
            while(var7.hasNext()) {
                op = (String)var7.next();
                paramMap.put(op, tem.get(op));
            }

            op = req.getParameter("op");
            if(StringUtil.isEmpty(op)) {
                op = AutoFormTemplateParseUtil.OP_VIEW;
            }

            Map<String, List<Map<String, Object>>> paras = new HashMap();
            if(StringUtils.isNotBlank(autoForm.getFormName())) {
                autoForm = (AutoFormEntity)this.systemService.findUniqueByProperty(AutoFormEntity.class, "formName", autoForm.getFormName());
                if(autoForm == null) {
                    return (new ModelAndView("jeecg/cgform/autoform/autoForm-error")).addObject("message", "表单信息获取失败！");
                } else {
                    List<String> paramList = this.getFormParams(autoForm.getId());
                    String ftlContent;
                    if(paramList != null && paramList.size() > 0) {
                        Iterator var10 = paramList.iterator();

                        while(var10.hasNext()) {
                            ftlContent = (String)var10.next();
                            String paramValue = req.getParameter(ftlContent);
                            if(paramValue == null) {
                                return (new ModelAndView("jeecg/cgform/autoform/autoForm-error")).addObject("message", "缺少表单参数：" + ftlContent);
                            }
                        }
                    }

                    req.setAttribute("formName", autoForm.getFormName());
                    req.setAttribute("param", paramMap);
                    ftlContent = autoForm.getFormParse();
                    //Map paras = null;
                    if(AutoFormTemplateParseUtil.OP_VIEW.equals(op)) {
                        paras = this.getFormData(autoForm, paramMap);
                        ftlContent = AutoFormTemplateParseUtil.parseHtmlForView(ftlContent, paras);
                    } else {
                        if(AutoFormTemplateParseUtil.OP_ADD.equals(op)) {
                            ftlContent = AutoFormTemplateParseUtil.parseHtmlForAdd(ftlContent, paras);
                            req.setAttribute("formContent", ftlContent);
                            req.setAttribute("op", op);
                            return new ModelAndView("jeecg/cgform/autoform/autoForm-review-add");
                        }

                        if(AutoFormTemplateParseUtil.OP_UPDATE.equals(op)) {
                            paras = this.getFormData(autoForm, paramMap);
                            String id = req.getParameter("id");
                            if(StringUtil.isEmpty(id)) {
                                throw new BusinessException("参数id不能为空！");
                            }

                            ftlContent = AutoFormTemplateParseUtil.parseHtmlForUpdate(ftlContent, paras);
                            req.setAttribute("formContent", ftlContent);
                            req.setAttribute("op", op);
                            return new ModelAndView("jeecg/cgform/autoform/autoForm-review-update");
                        }

                        if(AutoFormTemplateParseUtil.OP_ADD_OR_UPDATE.equals(op)) {
                            paras = this.getFormData(autoForm, paramMap);
                            ftlContent = AutoFormTemplateParseUtil.parseHtmlForAddOrUpdate(ftlContent, paras);
                            req.setAttribute("formContent", ftlContent);
                            req.setAttribute("op", op);
                            return new ModelAndView("jeecg/cgform/autoform/autoForm-review-addorupdate");
                        }
                    }

                    req.setAttribute("formContent", ftlContent);
                    req.setAttribute("op", op);
                    return new ModelAndView("jeecg/cgform/autoform/autoForm-review");
                }
            } else {
                return (new ModelAndView("jeecg/cgform/autoform/autoForm-error")).addObject("message", "formName不能为空！");
            }
        } catch (Exception var12) {
            var12.printStackTrace();
            message = "表单添加失败：" + var12.getMessage();
            return (new ModelAndView("jeecg/cgform/autoform/autoForm-error")).addObject("message", message);
        }
    }

    private Map<String, List<Map<String, Object>>> getFormData(AutoFormEntity autoForm, Map<String, Object> paramMap) {
        String message = "";
        Map<String, List<Map<String, Object>>> paras = new HashMap();
        List<AutoFormDbEntity> formDbList = this.systemService.findByProperty(AutoFormDbEntity.class, "autoFormId", autoForm.getId());
        Iterator var7 = formDbList.iterator();

        while(true) {
            while(var7.hasNext()) {
                AutoFormDbEntity formDb = (AutoFormDbEntity)var7.next();
                List<Map<String, Object>> data = new ArrayList();
                List<Map<String, Object>> typeList = this.getColumnTypes(formDb.getTbDbTableName(), formDb.getDbKey());
                String dbDynSql;
                List autoFormDbFieldEntityList;
                Object value;
                String[] paramValue;
                String dbKey;
                if("table".equals(formDb.getDbType())) {
                    dbDynSql = "from AutoFormDbFieldEntity where 1 = 1 AND aUTO_FORM_DB_ID = ? ";

                    try {
                        autoFormDbFieldEntityList = this.systemService.findHql(dbDynSql, new Object[]{formDb.getId()});
                        if(autoFormDbFieldEntityList.size() <= 0) {
                            message = "表属性配置有误！";
                            throw new BusinessException(message);
                        }

                        StringBuffer hqlTable = (new StringBuffer()).append("select ");
                        Iterator var29 = autoFormDbFieldEntityList.iterator();

                        while(var29.hasNext()) {
                            AutoFormDbFieldEntity autoFormDbFieldEntity = (AutoFormDbFieldEntity)var29.next();
                            boolean flag = false;
                            Iterator var17 = typeList.iterator();

                            while(var17.hasNext()) {
                                Map<String, Object> typeMap = (Map)var17.next();
                                String dataType = typeMap.get("dataType").toString().toUpperCase();
                                String columnNm = typeMap.get("columnNm").toString().toUpperCase();
                                if(dataType.contains("BLOB") && columnNm.equals(autoFormDbFieldEntity.getFieldName().toUpperCase())) {
                                    hqlTable.append("CONVERT(GROUP_CONCAT(" + autoFormDbFieldEntity.getFieldName() + ") USING utf8) as " + autoFormDbFieldEntity.getFieldName() + ",");
                                    flag = true;
                                }
                            }

                            if(!flag) {
                                hqlTable.append(autoFormDbFieldEntity.getFieldName() + ",");
                            }
                        }

                        hqlTable.deleteCharAt(hqlTable.length() - 1).append(" from " + formDb.getDbTableName());
                        dbKey = "";
                        value = paramMap.get("id");
                        if(value instanceof String[]) {
                            paramValue = (String[])value;
                            dbKey = paramValue[0];
                        } else {
                            dbKey = value.toString();
                        }

                        hqlTable.append(" where ID ='").append(dbKey).append("'");
                        if("".equals(formDb.getDbKey())) {
                            data = this.systemService.findForJdbc(hqlTable.toString(), new Object[0]);
                        } else {
                            DynamicDataSourceEntity dynamicDataSourceEntity = this.dynamicDataSourceServiceI.getDynamicDataSourceEntityForDbKey(formDb.getDbKey());
                            if(dynamicDataSourceEntity != null) {
                                data = DynamicDBUtil.findList(formDb.getDbKey(), hqlTable.toString(), new Object[0]);
                            }
                        }

                        paras.put(formDb.getDbName(), data);
                    } catch (Exception var21) {
                        logger.info(var21.getMessage());
                    }
                } else if("sql".equals(formDb.getDbType())) {
                    dbDynSql = formDb.getDbDynSql();
                    autoFormDbFieldEntityList = this.autoFormDbService.getSqlParams(dbDynSql);
                    Iterator var13 = autoFormDbFieldEntityList.iterator();

                    while(var13.hasNext()) {
                        String param = (String)var13.next();
                        value = paramMap.get(param);
                        if(value instanceof String[]) {
                            paramValue = (String[])value;
                            dbDynSql = dbDynSql.replaceAll("\\$\\{" + param + "\\}", paramValue[0]);
                        } else {
                            String paramValueTemp = value.toString();
                            dbDynSql = dbDynSql.replaceAll("\\$\\{" + param + "\\}", paramValueTemp);
                        }
                    }

                    if(dbDynSql.contains("\\$")) {
                        message = "动态SQL数据查询失败！";
                        throw new BusinessException(message);
                    }

                    //List data;
                    try {
                        Object dbKeys = paramMap.get("dbKey");
                        if(dbKeys != null && dbKeys != "") {
                            if(dbKeys instanceof String) {
                                dbKey = dbKeys.toString();
                                data = DynamicDBUtil.findList(dbKey, dbDynSql, new Object[0]);
                            } else {
                                String[] keys = (String[])dbKeys;
                                dbKey = keys[0];
                                data = DynamicDBUtil.findList(dbKey, dbDynSql, new Object[0]);
                            }
                        } else {
                            data = this.systemService.findForJdbc(dbDynSql, new Object[0]);
                        }
                    } catch (Exception var20) {
                        logger.info(var20.getMessage());
                        message = "动态SQL数据查询失败！";
                        throw new BusinessException(message);
                    }

                    paras.put(formDb.getDbName(), this.formatData(typeList, data));
                }
            }

            return paras;
        }
    }

    private List<Map<String, Object>> getColumnTypes(String dbTableNm, String dbkey) {
        new ArrayList();
        String sql = "select  DATA_TYPE as dataType,COLUMN_NAME as columnNm from information_schema.COLUMNS where TABLE_NAME='" + dbTableNm + "'";
        List list;
        if(StringUtils.isNotBlank(dbkey)) {
            list = DynamicDBUtil.findList(dbkey, sql, new Object[0]);
        } else {
            list = this.systemService.findForJdbc(sql, new Object[0]);
        }

        return list;
    }

    private List<Map<String, Object>> formatData(List<Map<String, Object>> typeList, List<Map<String, Object>> data) {
        Iterator var4 = data.iterator();

        while(var4.hasNext()) {
            Map<String, Object> hashmap = (Map)var4.next();
            Entry entry = null;
            Iterator it = hashmap.entrySet().iterator();

            while(it.hasNext()) {
                entry = (Entry)it.next();
                Iterator var8 = typeList.iterator();

                while(var8.hasNext()) {
                    Map<String, Object> typeMap = (Map)var8.next();
                    String dataType = typeMap.get("dataType").toString().toUpperCase();
                    String columnNm = typeMap.get("columnNm").toString().toUpperCase();
                    if(dataType.contains("BLOB") && columnNm.equals(entry.getKey().toString().toUpperCase())) {
                        try {
                            String srt2 = new String((byte[])entry.getValue(), "UTF-8");
                            hashmap.put(entry.getKey().toString(), srt2);
                        } catch (UnsupportedEncodingException var13) {
                            var13.printStackTrace();
                        }
                    }
                }
            }
        }

        return data;
    }

    @RequestMapping(
            params = {"review"}
    )
    public ModelAndView review(AutoFormEntity autoForm, HttpServletRequest req) {
        TemplateUtil tool = new TemplateUtil();
        Map<String, Object> map = tool.processor(autoForm.getFormContent());
        req.setAttribute("formContent", map.get("parseHtml"));
        return new ModelAndView("jeecg/cgform/autoform/autoForm-review");
    }

    @ResponseBody
    @RequestMapping(
            params = {"getFormDb"}
    )
    public AjaxJson getFormDb(AutoFormDbEntity dbForm, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        String message = "";
        if(StringUtils.isNotBlank(dbForm.getAutoFormId())) {
            List<AutoFormDbEntity> dbFormList = this.systemService.findByProperty(AutoFormDbEntity.class, "autoFormId", dbForm.getAutoFormId());
            if(dbFormList.size() > 0) {
                message = "<option value='' selected='selected'>请选择数据源</option>";

                AutoFormDbEntity entity;
                for(Iterator var7 = dbFormList.iterator(); var7.hasNext(); message = message + "<option value='" + entity.getDbName() + "'>" + (StringUtils.isBlank(entity.getDbChName())?entity.getDbName():entity.getDbChName()) + "</option>") {
                    entity = (AutoFormDbEntity)var7.next();
                }

                j.setSuccess(true);
                j.setMsg(message);
            } else {
                j.setSuccess(false);
                message = "<option value='' selected='selected'>请先添加数据源</option>";
                j.setMsg(message);
            }
        } else {
            j.setSuccess(false);
            message = "<option  value='' selected='selected'>请先添加数据源</option>";
            j.setMsg(message);
        }

        return j;
    }

    @ResponseBody
    @RequestMapping(
            params = {"getFormField"}
    )
    public AjaxJson getFormField(AutoFormDbEntity dbForm, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        String message = "";
        if(StringUtils.isNotBlank(dbForm.getDbName()) && StringUtils.isNotBlank(dbForm.getAutoFormId())) {
            String hqlList = "from AutoFormDbEntity t where t.dbName = ? and autoFormId = ?";
            List<AutoFormDbEntity> list = this.systemService.findHql(hqlList, new Object[]{dbForm.getDbName(), dbForm.getAutoFormId()});
            if(list.size() == 1) {
                dbForm = (AutoFormDbEntity)list.get(0);
                List<Map<String, Object>> columns = new ArrayList();
                String hql;
                if("table".equals(dbForm.getDbType())) {
                    hql = "select new Map(t.fieldName as fieldName,t.fieldText as fieldText) from AutoFormDbFieldEntity t where t.autoFormDbId=?";
                    columns = this.systemService.findHql(hql, new Object[]{dbForm.getId()});
                } else if("sql".equals(dbForm.getDbType())) {
                    hql = "select new Map(t.fieldName as fieldName,t.fieldText as fieldText) from AutoFormDbFieldEntity t where t.autoFormDbId=?";
                    columns = this.systemService.findHql(hql, new Object[]{dbForm.getId()});
                    if(((List)columns).size() == 0) {
                        List<String> fileds = this.autoFormDbService.getSqlFields(dbForm.getDbDynSql());
                        Iterator var11 = fileds.iterator();

                        while(var11.hasNext()) {
                            String filed = (String)var11.next();
                            Map<String, Object> map = new HashMap();
                            map.put("fieldName", filed);
                            ((List)columns).add(map);
                        }
                    }
                }

                if(((List)columns).size() > 0) {
                    message = "<option value='' selected='selected'>请选择字段</option>";

                    Map map;
                    for(Iterator var14 = ((List)columns).iterator(); var14.hasNext(); message = message + "<option value='" + map.get("fieldName") + "'>" + (StringUtils.isBlank((String)map.get("fieldText"))?map.get("fieldName"):map.get("fieldText")) + "</option>") {
                        map = (Map)var14.next();
                    }

                    j.setSuccess(true);
                    j.setMsg(message);
                } else {
                    j.setSuccess(false);
                    message = "<option value='' selected='selected'>请先添加字段</option>";
                    j.setMsg(message);
                }
            } else {
                j.setSuccess(false);
                message = "<option value='' selected='selected'>请先选择数据源</option>";
                j.setMsg(message);
            }
        }

        return j;
    }

    @ResponseBody
    @RequestMapping(
            params = {"getTrList"}
    )
    public AjaxJson getTrList(AutoFormDbEntity dbForm, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        String message = "";
        Map<String, Object> attributes = new HashMap();
        if(StringUtils.isNotBlank(dbForm.getDbName()) && StringUtils.isNotBlank(dbForm.getAutoFormId())) {
            String hqlList = "from AutoFormDbEntity t where t.dbName = ? and autoFormId = ?";
            List<AutoFormDbEntity> list = this.systemService.findHql(hqlList, new Object[]{dbForm.getDbName(), dbForm.getAutoFormId()});
            if(list.size() == 1) {
                dbForm = (AutoFormDbEntity)list.get(0);
                if(StringUtils.isNotBlank(dbForm.getDbChName())) {
                    attributes.put("dbName", dbForm.getDbChName());
                } else {
                    attributes.put("dbName", dbForm.getDbName());
                }

                List<Map<String, Object>> columns = new ArrayList();
                String hql;
                String headStr;
                if("table".equals(dbForm.getDbType())) {
                    hql = "select new Map(t.fieldName as fieldName,t.fieldText as fieldText) from AutoFormDbFieldEntity t where t.autoFormDbId=?";
                    columns = this.systemService.findHql(hql, new Object[]{dbForm.getId()});
                } else if("sql".equals(dbForm.getDbType())) {
                    hql = "select new Map(t.fieldName as fieldName,t.fieldText as fieldText) from AutoFormDbFieldEntity t where t.autoFormDbId=?";
                    columns = this.systemService.findHql(hql, new Object[]{dbForm.getId()});
                    if(((List)columns).size() == 0) {
                        List<String> fileds = this.autoFormDbService.getSqlFields(dbForm.getDbDynSql());
                        Iterator var12 = fileds.iterator();

                        while(var12.hasNext()) {
                            headStr = (String)var12.next();
                            Map<String, Object> map = new HashMap();
                            map.put("fieldName", headStr);
                            ((List)columns).add(map);
                        }
                    }
                }

                hql = null;
                StringBuilder trList = new StringBuilder();
                headStr = "";
                String columnFiledNm = "";
                String optionFiledNm = "";
                String columnFiledTx = "";
                if(((List)columns).size() <= 0) {
                    message = "<option value=\"\">无字段</option>";
                } else {
                    StringBuffer datatypeList = new StringBuffer();
                    datatypeList.append("<option value=\"\" selected>无</option>");
                    List<TSType> typeList = this.systemService.findByProperty(TSType.class, "TSTypegroup.id", "4028838850c35b6a0150c37251e00002");
                    Iterator var18 = typeList.iterator();

                    while(var18.hasNext()) {
                        TSType type = (TSType)var18.next();
                        datatypeList.append("<option value=\"" + type.getTypecode() + "\">" + type.getTypename() + "</option>");
                    }

                    StringBuffer unitOptions = new StringBuffer();
                    unitOptions.append("<option value=\"#\" selected>无</option>");
                    List<TSType> unitList = this.systemService.findByProperty(TSType.class, "TSTypegroup.id", "4028ab8c5134f1ed0151350f08d90003");
                    Iterator var20 = unitList.iterator();

                    while(var20.hasNext()) {
                        TSType type = (TSType)var20.next();
                        unitOptions.append("<option value=\"" + type.getTypecode() + "\">" + type.getTypename() + "</option>");
                    }

                    int i = 0;

                    while(true) {
                        if(i >= ((List)columns).size()) {
                            trList.append("<script type=\"text/javascript\">");
                            trList.append("function changeValue(id,value,dbFormId){");
                            trList.append(" var index = id.split('_')[1];");
                            trList.append("$.post(");
                            trList.append("'autoFormController.do?getHead',");
                            trList.append("{column:value,autoFormId:dbFormId},");
                            trList.append("function(data){");
                            trList.append("var d = $.parseJSON(data);");
                            trList.append("if(d.success){");
                            trList.append("if(value == 'ID' || value == 'id'){$('#isHide_'+(index)).attr('checked',true);}");
                            trList.append("if(d.msg == null || d.msg == ''){");
                            trList.append("$('#item_'+(index)).val(index);");
                            trList.append("}else{");
                            trList.append("$('#item_'+(index)).val(d.msg);");
                            trList.append("}}");
                            trList.append("});");
                            trList.append("}");
                            trList.append("$(function(){");
                            trList.append("$(\".delrow\").live(\"click\",function(){$(this).parent().parent().remove();resetTrNum();});");
                            trList.append("});");
                            trList.append("</script>");
                            message = message + trList.toString();
                            break;
                        }

                        Map<String, Object> columnMap = (Map)((List)columns).get(i);
                        columnFiledNm = columnMap.get("fieldName").toString();
                        columnFiledTx = columnMap.get("fieldText") == null?"":columnMap.get("fieldText").toString();
                        if(StringUtils.isNotBlank(columnFiledTx)) {
                            headStr = columnMap.get("fieldText").toString();
                        } else {
                            headStr = columnFiledNm;
                        }

                        StringBuilder options = new StringBuilder();

                        for(int m = 0; m < ((List)columns).size(); ++m) {
                            Map<String, Object> map = (Map)((List)columns).get(m);
                            optionFiledNm = map.get("fieldName").toString();
                            if(optionFiledNm.equals(columnFiledNm)) {
                                options.append("<option value=\"" + map.get("fieldName") + "\" selected>" + map.get("fieldName") + "</option>");
                            } else {
                                options.append("<option value=\"" + map.get("fieldName") + "\">" + map.get("fieldName") + "</option>");
                            }
                        }

                        if("ID".equals(columnFiledNm.toUpperCase())) {
                            trList.append("<tr>");
                            trList.append("<td><span class='badge'>" + (i + 1) + "</span></td>");
                            trList.append("<td title='Tab键切换输入框'><input id='item_" + (i + 1) + "' type='text' class='input-mini' value='" + headStr + "' style=\"width: 90%\" disabled></td>");
                            trList.append("<td title='Tab键切换输入框'>");
                            trList.append("<select id='coltype_" + (i + 1) + "' class='input-medium' style=\"width: 100%\" disabled>");
                            trList.append("<option value='text'>单行输入框</option>");
                            trList.append("<option value='textarea'>多行输入框</option>");
                            trList.append("<option value='radio'>单选按钮</option>");
                            trList.append("<option value='select'>下拉框</option>");
                            trList.append("<option value='checkbox'>复选框</option>");
                            trList.append("<option value='popup'>popup控件</option>");
                            trList.append("</select>");
                            trList.append("</td>");
                            trList.append("<td title='Tab键切换输入框'> <label><select id='field_" + (i + 1) + "' class=\"input-medium\"  onchange=\"changeValue(this.id,this.value,'" + dbForm.getId() + "')\" style=\"width: 100%\" disabled>");
                            trList.append(options);
                            trList.append("</select></label> </td>");
                            trList.append("<td title='Tab键切换输入框'> <label><input type='text' style=\"width:40px\" id='length_" + (i + 1) + "' value='157' disabled><span style='font-size:18px;'>px</span> </label> </td>");
                            trList.append("<td title='Tab键切换输入框'>");
                            trList.append("<select id='unit_" + (i + 1) + "' class='input-medium' style=\"width: 100%\" disabled>");
                            trList.append(unitOptions);
                            trList.append("</select>");
                            trList.append("</td>");
                            trList.append("<td title='Tab键切换输入框'> <label><input type='text' class='input-mini' id='dict_" + (i + 1) + "' value='' disabled> </label> </td>");
                            trList.append("<td title='Tab键切换输入框'> <label> <input type='checkbox' id='sum_" + (i + 1) + "' class='csum' value='3' disabled> </label> </td>");
                            trList.append("<td title='Tab键切换输入框'><input id='colvalue_" + (i + 1) + "'  type='text' class='input-mini' value='' disabled></td>");
                            trList.append("<td title='Tab键切换输入框'><select id='ruletype_" + (i + 1) + "' class='input-medium' style=\"width: 100%\" disabled>" + datatypeList.toString() + "</select></td>");
                            trList.append("<td title='Tab键切换输入框'><label> <input type=\"checkbox\" id='isHide_" + (i + 1) + "' value=\"1\"");
                            trList.append("checked=\"checked\"");
                            trList.append(" disabled> </label> </td>");
                            trList.append("<td></td>");
                            trList.append("</tr>");
                        } else {
                            trList.append("<tr>");
                            trList.append("<td><span class='badge'>" + (i + 1) + "</span></td>");
                            trList.append("<td title='Tab键切换输入框'><input id='item_" + (i + 1) + "' type='text' class='input-mini' value='" + headStr + "' style=\"width: 90%\"></td>");
                            trList.append("<td title='Tab键切换输入框'>");
                            trList.append("<select id='coltype_" + (i + 1) + "' class='input-medium' style=\"width: 100%\">");
                            trList.append("<option value='text'>单行输入框</option>");
                            trList.append("<option value='textarea'>多行输入框</option>");
                            trList.append("<option value='radio'>单选按钮</option>");
                            trList.append("<option value='select'>下拉框</option>");
                            trList.append("<option value='checkbox'>复选框</option>");
                            trList.append("<option value='popup'>popup控件</option>");
                            trList.append("</select>");
                            trList.append("</td>");
                            trList.append("<td title='Tab键切换输入框'> <label><select id='field_" + (i + 1) + "' class=\"input-medium\"  onchange=\"changeValue(this.id,this.value,'" + dbForm.getId() + "')\" style=\"width: 100%\">");
                            trList.append(options);
                            trList.append("</select></label> </td>");
                            trList.append("<td title='Tab键切换输入框'> <label><input type='text' style=\"width:40px\" id='length_" + (i + 1) + "' value='157'><span style='font-size:18px;'>px</span> </label> </td>");
                            trList.append("<td title='Tab键切换输入框'>");
                            trList.append("<select id='unit_" + (i + 1) + "' class='input-medium' style=\"width: 100%\">");
                            trList.append(unitOptions);
                            trList.append("</select>");
                            trList.append("</td>");
                            trList.append("<td title='Tab键切换输入框'> <label><input type='text' class='input-mini' id='dict_" + (i + 1) + "' value=''> </label> </td>");
                            trList.append("<td title='Tab键切换输入框'> <label> <input type='checkbox' id='sum_" + (i + 1) + "' class='csum' value='3'> </label> </td>");
                            trList.append("<td title='Tab键切换输入框'><input id='colvalue_" + (i + 1) + "'  type='text' class='input-mini' value=''/></td>");
                            trList.append("<td title='Tab键切换输入框'><select id='ruletype_" + (i + 1) + "' class='input-medium' style=\"width: 100%\">" + datatypeList.toString() + "</select></td>");
                            trList.append("<td title='Tab键切换输入框'><label> <input type=\"checkbox\" id='isHide_" + (i + 1) + "' value=\"1\"");
                            trList.append("> </label> </td>");
                            trList.append("<td><button class='btn btn-small btn-success delrow' type='button'>删除</button></td>");
                            trList.append("</tr>");
                        }

                        ++i;
                    }
                }

                j.setSuccess(true);
                attributes.put("tableHtml", message);
                j.setAttributes(attributes);
            } else {
                j.setSuccess(true);
                attributes.put("dbName", "");
                attributes.put("tableHtml", "");
            }
        } else {
            j.setSuccess(false);
        }

        return j;
    }

    @RequestMapping(
            params = {"getHead"}
    )
    @ResponseBody
    public AjaxJson getHead(String column, String autoFormId, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String message = "";

        try {
            String hqlList = "from AutoFormDbFieldEntity t where t.fieldName = ? and t.autoFormDbId = ?";
            List<AutoFormDbFieldEntity> list = this.systemService.findHql(hqlList, new Object[]{column, autoFormId});
            if(list.size() > 0) {
                AutoFormDbFieldEntity entry = (AutoFormDbFieldEntity)list.get(0);
                if(StringUtils.isNotBlank(entry.getFieldText())) {
                    message = entry.getFieldText();
                }
            }

            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var9) {
            var9.printStackTrace();
            message = "表头获取失败";
            throw new BusinessException(var9.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"addForm"}
    )
    @ResponseBody
    public AjaxJson addForm(HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String message = "";

        try {
            Map paramData = request.getParameterMap();
            String formName = request.getParameter("formName");
            if(paramData != null) {
                Map<String, Map<String, Object>> dataMap = AutoFormCommUtil.mapConvert(paramData);
                Map<String, Object> param = (Map)dataMap.get("param");
                AutoFormEntity autoForm = (AutoFormEntity)this.systemService.findUniqueByProperty(AutoFormEntity.class, "formName", formName);
                new HashMap();
                Map<String, List<Map<String, Object>>> oldDataMap = this.getFormData(autoForm, param);
                String id = this.autoFormService.doUpdateTable(formName, dataMap, oldDataMap);
                j.setObj(id);
                message = "表单添加成功";
            }
        } catch (Exception var11) {
            var11.printStackTrace();
            j.setSuccess(false);
            message = "表单添加失败：" + var11.getMessage();
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"updateForm"}
    )
    @ResponseBody
    public AjaxJson updateForm(HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String message = "表单更新成功";

        try {
            Map paramData = request.getParameterMap();
            String formName = request.getParameter("formName");
            if(paramData != null) {
                Map<String, Map<String, Object>> dataMap = AutoFormCommUtil.mapConvert(paramData);
                Map<String, Object> param = (Map)dataMap.get("param");
                AutoFormEntity autoForm = (AutoFormEntity)this.systemService.findUniqueByProperty(AutoFormEntity.class, "formName", formName);
                new HashMap();
                Map<String, List<Map<String, Object>>> oldDataMap = this.getFormData(autoForm, param);
                this.autoFormService.doUpdateTable(formName, dataMap, oldDataMap);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            j.setSuccess(false);
            message = "表单更新失败：" + var10.getMessage();
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"addorupdateForm"}
    )
    @ResponseBody
    public AjaxJson addorupdateForm(HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String message = "表单保存成功";

        try {
            Map paramData = request.getParameterMap();
            String formName = request.getParameter("formName");
            if(paramData != null) {
                Map<String, Map<String, Object>> dataMap = AutoFormCommUtil.mapConvert(paramData);
                Map<String, Object> param = (Map)dataMap.get("param");
                AutoFormEntity autoForm = (AutoFormEntity)this.systemService.findUniqueByProperty(AutoFormEntity.class, "formName", formName);
                new HashMap();
                Map<String, List<Map<String, Object>>> oldDataMap = this.getFormData(autoForm, param);
                this.autoFormService.doUpdateTable(formName, dataMap, oldDataMap);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            j.setSuccess(false);
            message = "表单保存失败";
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"checkTbCode"}
    )
    @ResponseBody
    public com.alibaba.fastjson.JSONObject checkTbCode(String param, String cVal) {
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        if(org.apache.commons.lang.StringUtils.isNotBlank(cVal) && cVal.equals(param)) {
            jsonObject.put("info", "验证通过！");
            jsonObject.put("status", "y");
            return jsonObject;
        } else {
            new ArrayList();
            String hql = "from AutoFormEntity t where t.formName = ?";
            List<AutoFormEntity> list = this.systemService.findHql(hql, new Object[]{param});
            if(list.size() > 0) {
                jsonObject.put("status", "n");
                jsonObject.put("info", "编码重复，请重新输入！");
                return jsonObject;
            } else {
                jsonObject.put("info", "验证通过！");
                jsonObject.put("status", "y");
                return jsonObject;
            }
        }
    }

    private void delFormDb(String autoFormId) {
        List<AutoFormDbEntity> list = this.systemService.findByProperty(AutoFormDbEntity.class, "autoFormId", autoFormId);
        if(list != null && list.size() > 0) {
            Iterator var4 = list.iterator();

            while(var4.hasNext()) {
                AutoFormDbEntity dbForm = (AutoFormDbEntity)var4.next();
                this.systemService.updateBySqlString("delete from auto_form_db_field where auto_form_db_id='" + dbForm.getId() + "'");
                this.systemService.delete(dbForm);
            }
        }

    }
}
