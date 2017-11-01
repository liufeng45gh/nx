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
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.enums.SysThemesEnum;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.SysThemesUtil;
import org.jeecgframework.web.cgform.engine.FreemarkerHelper;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.entity.template.CgformTemplateEntity;
import org.jeecgframework.web.cgform.service.autolist.CgTableServiceI;
import org.jeecgframework.web.cgform.service.autolist.ConfigServiceI;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.jeecgframework.web.cgform.service.template.CgformTemplateServiceI;
import org.jeecgframework.web.cgform.util.TemplateUtil;
import org.jeecgframework.web.cgform.util.TemplateUtil.TemplateType;
import org.jeecgframework.web.system.pojo.base.DictEntity;
import org.jeecgframework.web.system.pojo.base.TSOperation;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/cgform"})
public class CgAutoListRestController extends BaseController {
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
    private static Logger log = Logger.getLogger(CgAutoListRestController.class);

    public CgAutoListRestController() {
    }

    @RequestMapping(
            value = {"/list/{id}"},
            method = {RequestMethod.GET}
    )
    public void list(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        long start = System.currentTimeMillis();
        String jversion = this.cgFormFieldService.getCgFormVersionByTableName(id);
        Map<String, Object> configs = this.configService.queryConfigs(id, jversion);
        FreemarkerHelper viewEngine = new FreemarkerHelper();
        Map<String, Object> paras = new HashMap();
        this.loadVars(configs, paras, request);
        CgFormHeadEntity head = this.cgFormFieldService.getCgFormHeadByTableName(id);
        CgformTemplateEntity entity = this.cgformTemplateService.findByCode(head.getFormTemplate());
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
                ;
            }

        }

        long end = System.currentTimeMillis();
        log.debug("动态列表生成耗时：" + (end - start) + " ms");
    }

    @RequestMapping(
            value = {"/list/{configId}/{id}"},
            method = {RequestMethod.DELETE}
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
            value = {"/list/{configId}/{ids}"},
            method = {RequestMethod.DELETE}
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
        sb.append("<base href=\"" + request.getContextPath() + "/\"/>");
        if(!request.getPathInfo().contains("isHref")) {
            SysThemesEnum sysThemesEnum = SysThemesUtil.getSysTheme(request);
            sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery/jquery-1.8.3.js\"></script>");
            sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/dataformat.js\"></script>");
            sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"plug-in/accordion/css/accordion.css\">");
            sb.append(SysThemesUtil.getEasyUiTheme(sysThemesEnum));
            sb.append("<link rel=\"stylesheet\" href=\"plug-in/easyui/themes/icon.css\" type=\"text/css\"></link>");
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
