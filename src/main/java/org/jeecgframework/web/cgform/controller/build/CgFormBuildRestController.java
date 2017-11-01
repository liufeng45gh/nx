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
import org.jeecgframework.core.enums.SysThemesEnum;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.SysThemesUtil;
import org.jeecgframework.web.cgform.engine.TempletContext;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.entity.upload.CgUploadEntity;
import org.jeecgframework.web.cgform.service.build.DataBaseService;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.jeecgframework.web.cgform.util.TemplateUtil.TemplateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/cgform"})
public class CgFormBuildRestController extends BaseController {
    private static final Logger logger = Logger.getLogger(CgFormBuildRestController.class);
    @Autowired
    private TempletContext templetContext;
    @Autowired
    private DataBaseService dataBaseService;
    @Autowired
    private CgFormFieldServiceI cgFormFieldService;

    public CgFormBuildRestController() {
    }

    @RequestMapping(
            value = {"/form/{configId}"},
            method = {RequestMethod.GET}
    )
    public void ftlForm(@PathVariable String configId, HttpServletRequest request, HttpServletResponse response) {
        this.ftlForm(configId, (String)null, request, response);
    }

    @RequestMapping(
            value = {"/form/{configId}/{id}"},
            method = {RequestMethod.GET}
    )
    public void ftlForm(@PathVariable String configId, @PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        try {
            long start = System.currentTimeMillis();
            String tableName = configId;
            String mode = request.getParameter("mode");
            String templateName = configId + "_";
            if(StringUtils.isBlank(id)) {
                templateName = templateName + TemplateType.ADD.getName();
            } else if("read".equals(mode)) {
                templateName = templateName + TemplateType.DETAIL.getName();
            } else {
                templateName = templateName + TemplateType.UPDATE.getName();
            }

            String ftlVersion = request.getParameter("ftlVersion");
            Template template = this.templetContext.getTemplate(templateName, ftlVersion);
            StringWriter stringWriter = new StringWriter();
            BufferedWriter writer = new BufferedWriter(stringWriter);
            new HashMap();
            String version = this.cgFormFieldService.getCgFormVersionByTableName(configId);
            Map configData = this.cgFormFieldService.getFtlFormConfig(configId, version);
            Map<String, Object> data = new HashMap(configData);
            CgFormHeadEntity head = (CgFormHeadEntity)data.get("head");
            Map<String, Object> dataForm = new HashMap();
            if(StringUtils.isNotEmpty(id)) {
                dataForm = this.dataBaseService.findOneForJdbc(configId, id);
            }

            Iterator it = ((Map)dataForm).entrySet().iterator();

            String subTableStr;
            while(it.hasNext()) {
                Entry entry = (Entry)it.next();
                subTableStr = (String)entry.getKey();
                Object ov = entry.getValue();
                data.put(subTableStr, ov);
            }

            Map<String, Object> tableData = new HashMap();
            tableData.put(configId, dataForm);
            if(StringUtils.isNotEmpty(id) && head.getJformType().intValue() == 2) {
                subTableStr = head.getSubTableStr();
                if(StringUtils.isNotEmpty(subTableStr)) {
                    String[] subTables = subTableStr.split(",");
                    new ArrayList();
                    String[] var27 = subTables;
                    int var26 = subTables.length;

                    for(int var25 = 0; var25 < var26; ++var25) {
                        String subTable = var27[var25];
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
            template.process(data, writer);
            subTableStr = stringWriter.toString();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(subTableStr);
            response.getWriter().flush();
            long end = System.currentTimeMillis();
            logger.debug("自定义表单生成耗时：" + (end - start) + " ms");
        } catch (IOException var38) {
            var38.printStackTrace();
        } catch (TemplateException var39) {
            var39.printStackTrace();
        } finally {
            try {
                response.getWriter().close();
            } catch (Exception var37) {
                ;
            }

        }

    }

    private String getHtmlHead(HttpServletRequest request) {
        HttpSession session = ContextHolderUtils.getSession();
        String lang = (String)session.getAttribute("lang");
        StringBuilder sb = new StringBuilder("");
        sb.append("<base href=\"" + request.getContextPath() + "/\"/>");
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
}
