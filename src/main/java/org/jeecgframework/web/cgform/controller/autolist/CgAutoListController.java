//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.controller.autolist;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.enums.SysThemesEnum;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.JeecgDataAutorUtils;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.SysThemesUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.cgform.engine.FreemarkerHelper;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.entity.template.CgformTemplateEntity;
import org.jeecgframework.web.cgform.service.autolist.CgTableServiceI;
import org.jeecgframework.web.cgform.service.autolist.ConfigServiceI;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.jeecgframework.web.cgform.service.template.CgformTemplateServiceI;
import org.jeecgframework.web.cgform.util.QueryParamUtil;
import org.jeecgframework.web.cgform.util.TemplateUtil;
import org.jeecgframework.web.cgform.util.TemplateUtil.TemplateType;
import org.jeecgframework.web.system.pojo.base.DictEntity;
import org.jeecgframework.web.system.pojo.base.TSOperation;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/cgAutoListController"})
public class CgAutoListController extends BaseController {
    @Autowired
    private ConfigServiceI configService;
    @Autowired
    private CgTableServiceI cgTableService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private CgFormFieldServiceI cgFormFieldService;
    @Autowired
    private CgformTemplateServiceI cgformTemplateService;
    private static Logger log = Logger.getLogger(CgAutoListController.class);

    public CgAutoListController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public void list(String id, HttpServletRequest request, HttpServletResponse response) {
        long start = System.currentTimeMillis();
        String jversion = this.cgFormFieldService.getCgFormVersionByTableName(id);
        Map<String, Object> configs = this.configService.queryConfigs(id, jversion);
        FreemarkerHelper viewEngine = new FreemarkerHelper();
        Map<String, Object> paras = new HashMap();
        this.loadVars(configs, paras, request);
        String template = request.getParameter("olstylecode");
        if(StringUtils.isBlank(template)) {
            CgFormHeadEntity head = this.cgFormFieldService.getCgFormHeadByTableName(id);
            template = head.getFormTemplate();
            paras.put("_olstylecode", "");
        } else {
            paras.put("_olstylecode", template);
        }

        paras.put("this_olstylecode", template);
        CgformTemplateEntity entity = this.cgformTemplateService.findByCode(template);
        String html = viewEngine.parseTemplate(TemplateUtil.getTempletPath(entity, Integer.valueOf(0), TemplateType.LIST), paras);
        PrintWriter writer = null;

        try {
            response.setContentType("text/html");
            response.setHeader("Cache-Control", "no-store");
            writer = response.getWriter();
            writer.println(html);
            writer.flush();
        } catch (IOException var23) {
            var23.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception var22) {
                var22.printStackTrace();
            }

        }

        long end = System.currentTimeMillis();
        log.debug("动态列表生成耗时：" + (end - start) + " ms");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(String configId, String page, String field, String rows, String sort, String order, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        Object dataRuleSql = JeecgDataAutorUtils.loadDataSearchConditonSQLString();
        long start = System.currentTimeMillis();
        String jversion = this.cgFormFieldService.getCgFormVersionByTableName(configId);
        Map<String, Object> configs = this.configService.queryConfigs(configId, jversion);
        String table = (String)configs.get("tableName");
        Map params = new HashMap();
        List<CgFormFieldEntity> beans = (List)configs.get("fileds");
        Map<String, String[]> fieldMap = new HashMap();
        Iterator var20 = beans.iterator();

        while(var20.hasNext()) {
            CgFormFieldEntity b = (CgFormFieldEntity)var20.next();
            QueryParamUtil.loadQueryParams(request, b, params);
            fieldMap.put(b.getFieldName(), new String[]{b.getType(), b.getFieldDefault()});
        }

        boolean isTree = configs.get("config_istree") == null?false:"Y".equalsIgnoreCase(configs.get("config_istree").toString());
        String treeId = request.getParameter("id");
        String parentIdFieldName = null;
        String parentIdDefault = null;
        String parentIdFieldType = null;
        if(isTree) {
            parentIdFieldName = configs.get("tree_parentid_fieldname").toString();
            parentIdFieldType = ((String[])fieldMap.get(parentIdFieldName))[0];
            parentIdDefault = ((String[])fieldMap.get(parentIdFieldName))[1];
            if("null".equalsIgnoreCase(parentIdDefault)) {
                parentIdDefault = null;
            }

            if(treeId == null) {
                treeId = parentIdDefault;
            } else if(parentIdFieldType.equalsIgnoreCase("String")) {
                treeId = "'" + treeId + "'";
            }

            if(oConvertUtils.isEmpty(treeId)) {
                params.put(parentIdFieldName, " is null");
            } else {
                params.put(parentIdFieldName, "=" + treeId);
            }
        }

        int p = page == null?1:Integer.parseInt(page);
        int r = rows == null?99999:Integer.parseInt(rows);
        List<Map<String, Object>> result = null;
        if(isTree && treeId != null) {
            result = this.cgTableService.querySingle(table, field.toString(), params, sort, order, 1, 500);
        } else {
            result = this.cgTableService.querySingle(table, field.toString(), params, sort, order, p, r);
        }

        if(isTree) {
            this.cgTableService.treeFromResultHandle(table, parentIdFieldName, parentIdFieldType, result);
        }

        Map<String, Object> dicMap = new HashMap();
        Iterator var29 = beans.iterator();

        label275:
        while(true) {
            CgFormFieldEntity b;
            List dicList;
            do {
                if(!var29.hasNext()) {
                    Long size = this.cgTableService.getQuerySingleSize(table, field, params);
                    this.dealDic(result, beans);
                    response.setContentType("application/json");
                    response.setHeader("Cache-Control", "no-store");
                    PrintWriter writer = null;

                    try {
                        writer = response.getWriter();
                        if(isTree && treeId != null) {
                            writer.println(QueryParamUtil.getJson(result));
                        } else {
                            writer.println(QueryParamUtil.getJson(result, size));
                        }

                        writer.flush();
                    } catch (IOException var48) {
                        var48.printStackTrace();
                    } finally {
                        try {
                            writer.close();
                        } catch (Exception var47) {
                            ;
                        }

                    }

                    long end = System.currentTimeMillis();
                    log.debug("动态列表查询耗时：" + (end - start) + " ms");
                    return;
                }

                b = (CgFormFieldEntity)var29.next();
                this.loadDic(dicMap, b);
                dicList = (List)dicMap.get("field_dictlist");
            } while(dicList.size() <= 0);

            Iterator var32 = result.iterator();

            while(true) {
                Map resultMap;
                StringBuffer sb;
                String[] arrayVal;
                do {
                    String value;
                    do {
                        if(!var32.hasNext()) {
                            continue label275;
                        }

                        resultMap = (Map)var32.next();
                        sb = new StringBuffer();
                        value = (String)resultMap.get(b.getFieldName());
                    } while(oConvertUtils.isEmpty(value));

                    arrayVal = value.split(",");
                } while(arrayVal.length <= 1);

                String[] var39 = arrayVal;
                int var38 = arrayVal.length;

                for(int var37 = 0; var37 < var38; ++var37) {
                    String val = var39[var37];
                    Iterator var41 = dicList.iterator();

                    while(var41.hasNext()) {
                        DictEntity dictEntity = (DictEntity)var41.next();
                        if(val.equals(dictEntity.getTypecode())) {
                            sb.append(dictEntity.getTypename());
                            sb.append(",");
                        }
                    }
                }

                resultMap.put(b.getFieldName(), sb.toString().substring(0, sb.toString().length() - 1));
            }
        }
    }

    private void dealDic(List<Map<String, Object>> result, List<CgFormFieldEntity> beans) {
        Iterator var4 = beans.iterator();

        while(true) {
            CgFormFieldEntity bean;
            String dicTable;
            String dicCode;
            String dicText;
            do {
                do {
                    if(!var4.hasNext()) {
                        return;
                    }

                    bean = (CgFormFieldEntity)var4.next();
                    dicTable = bean.getDictTable();
                    dicCode = bean.getDictField();
                    dicText = bean.getDictText();
                } while(StringUtil.isEmpty(dicTable) && StringUtil.isEmpty(dicCode));
            } while(bean.getShowType().equals("popup"));

            List<DictEntity> dicDataList = this.queryDic(dicTable, dicCode, dicText);
            Iterator var10 = result.iterator();

            while(var10.hasNext()) {
                Map r = (Map)var10.next();
                String value = String.valueOf(r.get(bean.getFieldName()));
                Iterator var13 = dicDataList.iterator();

                while(var13.hasNext()) {
                    DictEntity dictEntity = (DictEntity)var13.next();
                    if(value.equalsIgnoreCase(dictEntity.getTypecode())) {
                        r.put(bean.getFieldName(), MutiLangUtil.getMutiLangInstance().getLang(dictEntity.getTypename()));
                    }
                }
            }
        }
    }

    @RequestMapping(
            params = {"del"}
    )
    @ResponseBody
    public AjaxJson del(String configId, String id, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String jversion = this.cgFormFieldService.getCgFormVersionByTableName(configId);
        String table = (String)this.configService.queryConfigs(configId, jversion).get("tableName");
        this.cgTableService.delete(table, id);
        String message = "删除成功";
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"delBatch"}
    )
    @ResponseBody
    public AjaxJson delBatch(String configId, String ids, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String jversion = this.cgFormFieldService.getCgFormVersionByTableName(configId);
        String table = (String)this.configService.queryConfigs(configId, jversion).get("tableName");
        String message = "删除成功";

        try {
            String[] id = ids.split(",");
            this.cgTableService.deleteBatch(table, id);
        } catch (Exception var9) {
            message = var9.getMessage();
        }

        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        j.setMsg(message);
        return j;
    }

    private Map loadVars(Map<String, Object> configs, Map<String, Object> paras, HttpServletRequest request) {
        paras.putAll(configs);
        List<Map> fieldList = new ArrayList();
        List<Map> queryList = new ArrayList();
        StringBuilder fileds = new StringBuilder();
        StringBuilder initQuery = new StringBuilder();
        Set<String> operationCodes = (Set)request.getAttribute("operationCodes");
        Map<String, TSOperation> operationCodesMap = new HashMap();
        if(operationCodes != null) {
            Iterator var12 = operationCodes.iterator();

            while(var12.hasNext()) {
                String id = (String)var12.next();
                TSOperation tsOperation = (TSOperation)this.systemService.getEntity(TSOperation.class, id);
                if(tsOperation != null && tsOperation.getOperationType().shortValue() == 0 && tsOperation.getStatus().shortValue() == 0) {
                    operationCodesMap.put(tsOperation.getOperationcode(), tsOperation);
                }
            }
        }

        Iterator var16 = ((List)configs.get("fileds")).iterator();

        while(var16.hasNext()) {
            CgFormFieldEntity bean = (CgFormFieldEntity)var16.next();
            if(!operationCodesMap.containsKey(bean.getFieldName())) {
                Map fm = new HashMap();
                fm.put("field_id", bean.getFieldName());
                fm.put("field_title", bean.getContent());
                String isShowList = bean.getIsShowList();
                if(StringUtil.isEmpty(isShowList)) {
                    if("id".equalsIgnoreCase(bean.getFieldName())) {
                        isShowList = "N";
                    } else {
                        isShowList = "Y";
                    }
                }

                fm.put("field_isShow", isShowList);
                fm.put("field_queryMode", bean.getQueryMode());
                fm.put("field_showType", bean.getShowType());
                fm.put("field_type", bean.getType());
                fm.put("field_length", bean.getFieldLength() == null?"120":bean.getFieldLength());
                fm.put("field_href", bean.getFieldHref() == null?"":bean.getFieldHref());
                this.loadDic(fm, bean);
                fieldList.add(fm);
                if("Y".equals(bean.getIsQuery())) {
                    Map fmq = new HashMap();
                    fmq.put("field_id", bean.getFieldName());
                    fmq.put("field_title", bean.getContent());
                    fmq.put("field_queryMode", bean.getQueryMode());
                    fmq.put("field_type", bean.getType());
                    fmq.put("field_showType", bean.getShowType());
                    fmq.put("field_dictField", bean.getDictField());
                    fmq.put("field_dictTable", bean.getDictTable());
                    fmq.put("field_isQuery", "Y");
                    this.loadDefaultValue(fmq, bean, request);
                    this.loadDic(fmq, bean);
                    queryList.add(fmq);
                }

                this.loadUrlDataFilter(queryList, bean, request);
                this.loadInitQuery(initQuery, bean, request);
                fileds.append(bean.getFieldName()).append(",");
            }
        }

        this.loadAuth(paras, request);
        this.loadIframeConfig(paras, request);
        paras.put("config_fieldList", fieldList);
        paras.put("config_queryList", queryList);
        paras.put("fileds", fileds);
        paras.put("initquery", initQuery);
        return paras;
    }

    private void loadIframeConfig(Map<String, Object> paras, HttpServletRequest request) {
        HttpSession session = ContextHolderUtils.getSession();
        String lang = (String)session.getAttribute("lang");
        StringBuilder sb = new StringBuilder("");
        if(!request.getQueryString().contains("isHref")) {
            SysThemesEnum sysThemesEnum = SysThemesUtil.getSysTheme(request);
            sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery/jquery-1.8.3.js\"></script>");
            sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/dataformat.js\"></script>");
            sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"plug-in/accordion/css/accordion.css\">");
            sb.append(SysThemesUtil.getEasyUiTheme(sysThemesEnum));
            sb.append(SysThemesUtil.getEasyUiIconTheme(sysThemesEnum));
            sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"plug-in/accordion/css/icons.css\">");
            sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/jquery.easyui.min.1.3.2.js\"></script>");
            sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/locale/zh-cn.js\"></script>");
            sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/syUtil.js\"></script>");
            sb.append("<script type=\"text/javascript\" src=\"plug-in/My97DatePicker/WdatePicker.js\"></script>");
            sb.append(SysThemesUtil.getCommonTheme(sysThemesEnum));
            sb.append(SysThemesUtil.getLhgdialogTheme(sysThemesEnum));
            sb.append(StringUtil.replace("<script type=\"text/javascript\" src=\"plug-in/tools/curdtools_{0}.js\"></script>", "{0}", lang));
            sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/easyuiextend.js\"></script>");
            sb.append(SysThemesUtil.getEasyUiMainTheme(sysThemesEnum));
        }

        paras.put("config_iframe", sb.toString());
    }

    private void loadAuth(Map<String, Object> paras, HttpServletRequest request) {
        List<TSOperation> nolist = (List)request.getAttribute("noauto_operationCodes");
        if(ResourceUtil.getSessionUserName().getUserName().equals("admin") || !Globals.BUTTON_AUTHORITY_CHECK) {
            nolist = null;
        }

        List<String> list = new ArrayList();
        String nolistStr = "";
        if(nolist != null) {
            Iterator var7 = nolist.iterator();

            while(var7.hasNext()) {
                TSOperation operation = (TSOperation)var7.next();
                nolistStr = nolistStr + operation.getOperationcode();
                nolistStr = nolistStr + ",";
                list.add(operation.getOperationcode());
            }
        }

        paras.put("config_nolist", list);
        paras.put("config_noliststr", nolistStr == null?"":nolistStr);
    }

    private void loadInitQuery(StringBuilder initQuery, CgFormFieldEntity bean, HttpServletRequest request) {
        if(!bean.getFieldName().equalsIgnoreCase("id")) {
            String paramV = request.getParameter(bean.getFieldName());
            String paramVbegin = request.getParameter(bean.getFieldName() + "_begin");
            String paramVend = request.getParameter(bean.getFieldName() + "_end");
            paramV = this.getSystemValue(paramV);
            if(!StringUtil.isEmpty(paramV)) {
                initQuery.append("&" + bean.getFieldName() + "=" + paramV);
            }

            if(!StringUtil.isEmpty(paramVbegin)) {
                initQuery.append("&" + bean.getFieldName() + "_begin=" + paramVbegin);
            }

            if(!StringUtil.isEmpty(paramVend)) {
                initQuery.append("&" + bean.getFieldName() + "_end=" + paramVend);
            }

        }
    }

    private void loadUrlDataFilter(List<Map> queryList, CgFormFieldEntity bean, HttpServletRequest request) {
        String paramV = request.getParameter(bean.getFieldName());
        String paramVbegin = request.getParameter(bean.getFieldName() + "_begin");
        String paramVend = request.getParameter(bean.getFieldName() + "_end");
        if(!bean.getFieldName().equalsIgnoreCase("id")) {
            Iterator var8 = queryList.iterator();

            while(var8.hasNext()) {
                Map mq = (Map)var8.next();
                if(mq.containsValue(bean.getFieldName())) {
                    return;
                }
            }

            if(!StringUtil.isEmpty(paramV) || !StringUtil.isEmpty(paramVbegin) || !StringUtil.isEmpty(paramVend)) {
                Map fmq = new HashMap();
                fmq.put("field_id", bean.getFieldName());
                fmq.put("field_title", bean.getContent());
                fmq.put("field_queryMode", bean.getQueryMode());
                fmq.put("field_type", bean.getType());
                fmq.put("field_isQuery", "N");
                paramV = this.getSystemValue(paramV);
                fmq.put("field_value", paramV);
                paramVend = this.getSystemValue(paramVend);
                fmq.put("field_value_begin", StringUtil.isEmpty(paramVbegin)?"":paramVbegin);
                fmq.put("field_value_end", StringUtil.isEmpty(paramVend)?"":paramVend);
                queryList.add(fmq);
            }

        }
    }

    private void loadDefaultValue(Map fmq, CgFormFieldEntity bean, HttpServletRequest request) {
        String paramV;
        if(bean.getQueryMode().equalsIgnoreCase("single")) {
            paramV = request.getParameter(bean.getFieldName());
            if(!StringUtil.isEmpty(paramV)) {
                paramV = this.getSystemValue(paramV);
                fmq.put("field_value", paramV);
            }
        } else if(bean.getQueryMode().equalsIgnoreCase("group")) {
            paramV = request.getParameter(bean.getFieldName() + "_begin");
            String paramVend = request.getParameter(bean.getFieldName() + "_end");
            fmq.put("field_value_begin", StringUtil.isEmpty(paramV)?"":paramV);
            fmq.put("field_value_end", StringUtil.isEmpty(paramVend)?"":paramVend);
        }

    }

    private void loadDic(Map m, CgFormFieldEntity bean) {
        String dicT = bean.getDictTable();
        String dicF = bean.getDictField();
        String dicText = bean.getDictText();
        if(StringUtil.isEmpty(dicT) && StringUtil.isEmpty(dicF)) {
            m.put("field_dictlist", new ArrayList(0));
        } else {
            if(!bean.getShowType().equals("popup")) {
                List<DictEntity> dicDatas = this.queryDic(dicT, dicF, dicText);
                m.put("field_dictlist", dicDatas);
            } else {
                m.put("field_dictlist", new ArrayList(0));
            }

        }
    }

    private List<DictEntity> queryDic(String dicTable, String dicCode, String dicText) {
        return this.systemService.queryDict(dicTable, dicCode, dicText);
    }

    private String getSystemValue(String sysVarName) {
        if(StringUtil.isEmpty(sysVarName)) {
            return sysVarName;
        } else if(sysVarName.contains("{") && sysVarName.contains("}")) {
            sysVarName = sysVarName.replaceAll("\\{", "");
            sysVarName = sysVarName.replaceAll("\\}", "");
            sysVarName = sysVarName.replace("sys.", "");
            return ResourceUtil.converRuleValue(sysVarName);
        } else {
            return sysVarName;
        }
    }
}
