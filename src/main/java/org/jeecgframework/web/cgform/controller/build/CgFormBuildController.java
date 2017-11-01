//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.controller.build;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.enums.SysThemesEnum;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.SysThemesUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.cgform.common.CommUtils;
import org.jeecgframework.web.cgform.engine.FreemarkerHelper;
import org.jeecgframework.web.cgform.engine.TempletContext;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.entity.template.CgformTemplateEntity;
import org.jeecgframework.web.cgform.entity.upload.CgUploadEntity;
import org.jeecgframework.web.cgform.exception.BusinessException;
import org.jeecgframework.web.cgform.service.build.DataBaseService;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.jeecgframework.web.cgform.service.template.CgformTemplateServiceI;
import org.jeecgframework.web.cgform.util.TemplateUtil;
import org.jeecgframework.web.cgform.util.TemplateUtil.TemplateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/cgFormBuildController"})
public class CgFormBuildController extends BaseController {
    private static final Logger logger = Logger.getLogger(CgFormBuildController.class);
    @Autowired
    private TempletContext templetContext;
    @Autowired
    private DataBaseService dataBaseService;
    @Autowired
    private CgformTemplateServiceI cgformTemplateService;
    @Autowired
    private CgFormFieldServiceI cgFormFieldService;

    public CgFormBuildController() {
    }

    @RequestMapping(
            params = {"goAddFtlForm"}
    )
    public void goAddFtlForm(HttpServletRequest request, HttpServletResponse response) {
        this.ftlForm(request, response);
    }

    @RequestMapping(
            params = {"goUpdateFtlForm"}
    )
    public void goUpdateFtlForm(HttpServletRequest request, HttpServletResponse response) {
        this.ftlForm(request, response);
    }

    @RequestMapping(
            params = {"goDatilFtlForm"}
    )
    public void goDatilFtlForm(HttpServletRequest request, HttpServletResponse response) {
        this.ftlForm(request, response);
    }

    @RequestMapping(
            params = {"mobileForm"}
    )
    public void mobileForm(HttpServletRequest request, HttpServletResponse response) {
        String tableName = request.getParameter("tableName");
        String sql = "select form_template_mobile from cgform_head where table_name = '" + tableName + "'";
        Map<String, Object> mp = this.cgFormFieldService.findOneForJdbc(sql, new Object[0]);
        if(mp.containsKey("form_template_mobile") && oConvertUtils.isNotEmpty(mp.get("form_template_mobile"))) {
            String urlTemplateName = request.getParameter("olstylecode");
            if(oConvertUtils.isEmpty(urlTemplateName)) {
                request.setAttribute("olstylecode", mp.get("form_template_mobile").toString().trim());
            }
        }

        this.ftlForm(request, response);
    }

    @RequestMapping(
            params = {"ftlForm"}
    )
    public void ftlForm(HttpServletRequest request, HttpServletResponse response) {
        try {
            long start = System.currentTimeMillis();
            String tableName = request.getParameter("tableName");
            new HashMap();
            String id = request.getParameter("id");
            String mode = request.getParameter("mode");
            String templateName = tableName + "_";
            TemplateType templateType = TemplateType.LIST;
            if(StringUtils.isBlank(id)) {
                templateName = templateName + TemplateType.ADD.getName();
                templateType = TemplateType.ADD;
            } else if("read".equals(mode)) {
                templateName = templateName + TemplateType.DETAIL.getName();
                templateType = TemplateType.DETAIL;
            } else {
                templateName = templateName + TemplateType.UPDATE.getName();
                templateType = TemplateType.UPDATE;
            }

            String version = this.cgFormFieldService.getCgFormVersionByTableName(tableName);
            Map configData = this.cgFormFieldService.getFtlFormConfig(tableName, version);
            Map<String, Object> data = new HashMap(configData);
            CgFormHeadEntity head = (CgFormHeadEntity)data.get("head");
            Map<String, Object> dataForm = new HashMap();
            if(StringUtils.isNotEmpty(id)) {
                dataForm = this.dataBaseService.findOneForJdbc(tableName, id);
            }

            Iterator it = ((Map)dataForm).entrySet().iterator();

            String content;
            while(it.hasNext()) {
                Entry entry = (Entry)it.next();
                content = (String)entry.getKey();
                Object ov = entry.getValue();
                data.put(content, ov);
            }

            Map<String, Object> tableData = new HashMap();
            tableData.put(tableName, dataForm);
            if(StringUtils.isNotEmpty(id) && head.getJformType().intValue() == 2) {
                content = head.getSubTableStr();
                if(StringUtils.isNotEmpty(content)) {
                    String[] subTables = content.split(",");
                    new ArrayList();
                    String[] var23 = subTables;
                    int var22 = subTables.length;

                    for(int var21 = 0; var21 < var22; ++var21) {
                        String subTable = var23[var21];
                        List<Map<String, Object>> subTableData = this.cgFormFieldService.getSubTableData(tableName, subTable, id);
                        tableData.put(subTable, subTableData);
                    }
                }
            }

            data.put("data", tableData);
            data.put("id", id);
            data.put("head", head);
            data.put("config_iframe", this.getHtmlHead(request));
            this.pushFiles(data, id);
            this.pushImages(data, id);
            content = null;
            response.setContentType("text/html;charset=utf-8");
            String urlTemplateName = request.getParameter("olstylecode");
            if(oConvertUtils.isEmpty(urlTemplateName)) {
                urlTemplateName = (String)request.getAttribute("olstylecode");
            }

            if(StringUtils.isNotBlank(urlTemplateName)) {
                data.put("this_olstylecode", urlTemplateName);
                LogUtil.debug("-------------urlTemplateName-----------" + urlTemplateName);
                content = this.getUrlTemplate(urlTemplateName, templateType, data);
            } else {
                data.put("this_olstylecode", head.getFormTemplate());
                LogUtil.debug("-------------formTemplate-----------" + head.getFormTemplate());
                content = this.getTableTemplate(templateName, request, data);
            }

            response.getWriter().print(content);
            response.getWriter().flush();
            long end = System.currentTimeMillis();
            logger.debug("自定义表单生成耗时：" + (end - start) + " ms");
        } catch (IOException var32) {
            var32.printStackTrace();
        } finally {
            try {
                response.getWriter().close();
            } catch (IOException var31) {
                var31.printStackTrace();
            }

        }

    }

    private String getUrlTemplate(String templateName, TemplateType templateType, Map dataMap) {
        String content = null;
        CgformTemplateEntity entity = this.cgformTemplateService.findByCode(templateName);
        if(entity != null) {
            FreemarkerHelper viewEngine = new FreemarkerHelper();
            dataMap.put("DictData", ApplicationContextUtil.getContext().getBean("dictDataTag"));
            content = viewEngine.parseTemplate(TemplateUtil.getTempletPath(entity, Integer.valueOf(0), templateType), dataMap);
        }

        return content;
    }

    private String getTableTemplate(String templateName, HttpServletRequest request, Map data) {
        StringWriter stringWriter = new StringWriter();
        BufferedWriter writer = new BufferedWriter(stringWriter);
        String ftlVersion = request.getParameter("ftlVersion");
        Template template = this.templetContext.getTemplate(templateName, ftlVersion);

        try {
            template.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
            template.setDateFormat("yyyy-MM-dd");
            template.setTimeFormat("HH:mm:ss");
            template.process(data, writer);
        } catch (TemplateException var9) {
            var9.printStackTrace();
        } catch (IOException var10) {
            var10.printStackTrace();
        }

        return stringWriter.toString();
    }

    private String getHtmlHead(HttpServletRequest request) {
        HttpSession session = ContextHolderUtils.getSession();
        String lang = (String)session.getAttribute("lang");
        StringBuilder sb = new StringBuilder("");
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
        sb.append("<link rel=\"stylesheet\" href=\"plug-in/uploadify/css/uploadify.css\" type=\"text/css\"></link>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/uploadify/jquery.uploadify-3.1.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/Map.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/Validform_Datatype_zh-cn.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/datatype_zh-cn.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js\"></script>");
        sb.append(SysThemesUtil.getValidformStyleTheme(sysThemesEnum));
        sb.append(SysThemesUtil.getValidformTablefrom(sysThemesEnum));
        sb.append("<link rel=\"stylesheet\" href=\"plug-in/umeditor/themes/default/css/umeditor.css\" type=\"text/css\"></link>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/umeditor/umeditor.config.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/umeditor/umeditor.min.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/umeditor/lang/zh-cn/zh-cn.js\"></script>");
        return sb.toString();
    }

    private void pushFiles(Map<String, Object> data, String id) {
        List<CgUploadEntity> uploadBeans = this.cgFormFieldService.findByProperty(CgUploadEntity.class, "cgformId", id);
        List<Map<String, Object>> files = new ArrayList(0);
        Iterator var6 = uploadBeans.iterator();

        while(var6.hasNext()) {
            CgUploadEntity b = (CgUploadEntity)var6.next();
            String title = b.getAttachmenttitle();
            String fileKey = b.getId();
            String path = b.getRealpath();
            String field = b.getCgformField();
            Map<String, Object> file = new HashMap();
            file.put("title", title);
            file.put("fileKey", fileKey);
            file.put("path", path);
            file.put("field", field == null?"":field);
            files.add(file);
        }

        data.put("filesList", files);
    }

    private void pushImages(Map<String, Object> data, String id) {
        List<CgUploadEntity> uploadBeans = this.cgFormFieldService.findByProperty(CgUploadEntity.class, "cgformId", id);
        List<Map<String, Object>> images = new ArrayList(0);
        Iterator var6 = uploadBeans.iterator();

        while(var6.hasNext()) {
            CgUploadEntity b = (CgUploadEntity)var6.next();
            String title = b.getAttachmenttitle();
            String fileKey = b.getId();
            String path = b.getRealpath();
            String field = b.getCgformField();
            Map<String, Object> image = new HashMap();
            image.put("title", title);
            image.put("fileKey", fileKey);
            image.put("path", path);
            image.put("field", field == null?"":field);
            images.add(image);
        }

        data.put("imageList", images);
    }

    @RequestMapping(
            params = {"saveOrUpdate"}
    )
    @ResponseBody
    public AjaxJson saveOrUpdate(HttpServletRequest request) throws Exception {
        String message = null;
        AjaxJson j = new AjaxJson();
        Map data = request.getParameterMap();
        if(data != null) {
            data = CommUtils.mapConvert(data);
            String tableName = (String)data.get("tableName");
            String id = (String)data.get("id");
            Iterator it = data.entrySet().iterator();

            Object pkValue;
            while(it.hasNext()) {
                Entry entry = (Entry)it.next();
                pkValue = entry.getKey();
                Object ov = entry.getValue() == null?"":entry.getValue();
                logger.debug("name:" + pkValue.toString() + ";value:" + ov.toString());
            }

            String[] filterName;
            if(StringUtils.isEmpty(id)) {
                filterName = new String[]{"tableName", "saveOrUpdate"};
                data = CommUtils.attributeMapFilter(data, filterName);

                try {
                    pkValue = null;
                    pkValue = this.dataBaseService.getPkValue(tableName);
                    data.put("id", pkValue);

                    try {
                        this.dataBaseService.insertTable(tableName, data);
                        j.setSuccess(true);
                        message = "业务提交成功";
                    } catch (Exception var12) {
                        j.setSuccess(false);
                        message = "业务提交失败";
                    }
                } catch (Exception var13) {
                    var13.printStackTrace();
                    j.setSuccess(false);
                    message = var13.getMessage();
                }
            } else {
                filterName = new String[]{"tableName", "saveOrUpdate", "id"};
                data = CommUtils.attributeMapFilter(data, filterName);

                try {
                    int num = this.dataBaseService.updateTable(tableName, id, data);
                    if(num > 0) {
                        j.setSuccess(true);
                        message = "业务更新成功";
                    } else {
                        j.setSuccess(false);
                        message = "业务更新失败";
                    }
                } catch (Exception var11) {
                    var11.printStackTrace();
                    j.setSuccess(false);
                    message = var11.getMessage();
                }
            }
        }

        j.setMsg(message);
        j.setObj(data);
        return j;
    }

    @RequestMapping(
            params = {"saveOrUpdateMore"}
    )
    @ResponseBody
    public AjaxJson saveOrUpdateMore(HttpServletRequest request) throws Exception {
        String message = null;
        AjaxJson j = new AjaxJson();
        Map data = request.getParameterMap();
        if(data != null) {
            data = CommUtils.mapConvert(data);
            String tableName = (String)data.get("tableName");
            String id = (String)data.get("id");
            Iterator it = data.entrySet().iterator();

            while(it.hasNext()) {
                Entry entry = (Entry)it.next();
                Object ok = entry.getKey();
                Object ov = entry.getValue() == null?"":entry.getValue();
                logger.debug("name:" + ok.toString() + ";value:" + ov.toString());
            }

            Map<String, List<Map<String, Object>>> mapMore = CommUtils.mapConvertMore(data, tableName);
            if(StringUtils.isEmpty(id)) {
                logger.info("一对多添加!!!!!");

                try {
                    Map<String, Object> result = this.dataBaseService.insertTableMore(mapMore, tableName);
                    data.put("id", result.get("id"));
                    j.setSuccess(true);
                    message = "添加成功";
                } catch (BusinessException var12) {
                    var12.printStackTrace();
                    j.setSuccess(false);
                    message = var12.getMessage();
                }
            } else {
                logger.info("一对多修改!!!!!");

                try {
                    this.dataBaseService.updateTableMore(mapMore, tableName);
                    j.setSuccess(true);
                    message = "更新成功";
                } catch (BusinessException var11) {
                    var11.printStackTrace();
                    j.setSuccess(false);
                    message = var11.getMessage();
                }
            }
        }

        j.setMsg(message);
        j.setObj(data);
        return j;
    }

    @RequestMapping(
            params = {"doButton"}
    )
    @ResponseBody
    public AjaxJson doButton(HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();

        try {
            String formId = request.getParameter("formId");
            String buttonCode = request.getParameter("buttonCode");
            String tableName = request.getParameter("tableName");
            String id = request.getParameter("id");
            Map<String, Object> data = this.dataBaseService.findOneForJdbc(tableName, id);
            if(data != null) {
                Iterator it = data.entrySet().iterator();

                while(it.hasNext()) {
                    Entry entry = (Entry)it.next();
                    Object ok = entry.getKey();
                    Object ov = entry.getValue() == null?"":entry.getValue();
                    logger.debug("name:" + ok.toString() + ";value:" + ov.toString());
                }

                data = CommUtils.mapConvert(data);
                this.dataBaseService.executeSqlExtend(formId, buttonCode, data);
                this.dataBaseService.executeJavaExtend(formId, buttonCode, data);
            }

            j.setSuccess(true);
            message = "操作成功";
        } catch (Exception var13) {
            var13.printStackTrace();
            message = "操作失败";
        }

        j.setMsg(message);
        return j;
    }
}
