//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.controller.cgformftl;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.FormUtil;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.cgform.common.OfficeHtmlUtil;
import org.jeecgframework.web.cgform.entity.cgformftl.CgformFtlEntity;
import org.jeecgframework.web.cgform.service.cgformftl.CgformFtlServiceI;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.jeecgframework.web.cgform.util.TemplateUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import ssb.warmline.business.commons.utils.DateUtils;

@Controller
@RequestMapping({"/cgformFtlController"})
public class CgformFtlController extends BaseController {
    private static final Logger logger = Logger.getLogger(CgformFtlController.class);
    @Autowired
    private CgformFtlServiceI cgformFtlService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private CgFormFieldServiceI cgFormFieldService;

    public CgformFtlController() {
    }

    @RequestMapping(
            params = {"formEkeditor"}
    )
    public ModelAndView ckeditor(HttpServletRequest request, String id) {
        CgformFtlEntity t = (CgformFtlEntity)this.systemService.get(CgformFtlEntity.class, id);
        request.setAttribute("cgformFtlEntity", t);
        if(t.getFtlContent() == null) {
            request.setAttribute("contents", "");
        } else {
            request.setAttribute("contents", new String(t.getFtlContent()));
        }

        return request.getParameter("editorType") == null?new ModelAndView("jeecg/cgform/cgformftl/ckeditor"):new ModelAndView("jeecg/cgform/cgformftl/" + request.getParameter("editorType"));
    }

    @RequestMapping(
            params = {"saveFormEkeditor"}
    )
    @ResponseBody
    public AjaxJson saveCkeditor(HttpServletRequest request, CgformFtlEntity cgformFtlEntity, String contents) {
        AjaxJson j = new AjaxJson();
        if(StringUtil.isNotEmpty(cgformFtlEntity.getId())) {
            CgformFtlEntity t = (CgformFtlEntity)this.systemService.get(CgformFtlEntity.class, cgformFtlEntity.getId());

            try {
                MyBeanUtils.copyBeanNotNull2Bean(cgformFtlEntity, t);
                t.setFtlContent(contents);
                this.systemService.saveOrUpdate(t);
                j.setSuccess(true);
                j.setMsg("更新成功");
            } catch (Exception var7) {
                var7.printStackTrace();
                j.setSuccess(false);
                j.setMsg("更新失败");
            }
        }

        return j;
    }

    @RequestMapping(
            params = {"cgformFtl"}
    )
    public ModelAndView cgformFtl(HttpServletRequest request) {
        String formid = request.getParameter("formid");
        request.setAttribute("formid", formid);
        return new ModelAndView("jeecg/cgform/cgformftl/cgformFtlList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(CgformFtlEntity cgformFtl, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(CgformFtlEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, cgformFtl, request.getParameterMap());
        this.cgformFtlService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"del"}
    )
    @ResponseBody
    public AjaxJson del(CgformFtlEntity cgformFtl, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        cgformFtl = (CgformFtlEntity)this.systemService.getEntity(CgformFtlEntity.class, cgformFtl.getId());
        message = "删除成功";
        this.cgformFtlService.delete(cgformFtl);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"active"}
    )
    @ResponseBody
    public AjaxJson active(CgformFtlEntity cgformFtl, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();

        try {
            cgformFtl = (CgformFtlEntity)this.systemService.getEntity(CgformFtlEntity.class, cgformFtl.getId());
            if(!this.cgformFtlService.hasActive(cgformFtl.getCgformId())) {
                cgformFtl.setFtlStatus("1");
                this.cgformFtlService.saveOrUpdate(cgformFtl);
                message = "激活成功";
                this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
                j.setSuccess(true);
                j.setMsg(message);
            } else {
                message = "已有激活模板，请取消后再进行激活";
                j.setSuccess(true);
                j.setMsg(message);
            }
        } catch (Exception var6) {
            logger.info(var6.getMessage());
            message = "激活失败";
            j.setSuccess(false);
            j.setMsg(message);
        }

        return j;
    }

    @RequestMapping(
            params = {"cancleActive"}
    )
    @ResponseBody
    public AjaxJson cancleActive(CgformFtlEntity cgformFtl, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();

        try {
            cgformFtl = (CgformFtlEntity)this.systemService.getEntity(CgformFtlEntity.class, cgformFtl.getId());
            cgformFtl.setFtlStatus("0");
            this.cgformFtlService.saveOrUpdate(cgformFtl);
            message = "取消激活成功";
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
            j.setSuccess(true);
            j.setMsg(message);
        } catch (Exception var6) {
            logger.info(var6.getMessage());
            message = "取消激活失败";
            j.setSuccess(false);
            j.setMsg(message);
        }

        return j;
    }

    @RequestMapping(
            params = {"save"}
    )
    @ResponseBody
    public AjaxJson save(CgformFtlEntity cgformFtl, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        if(StringUtil.isNotEmpty(cgformFtl.getId())) {
            message = "更新成功";
            CgformFtlEntity t = (CgformFtlEntity)this.cgformFtlService.get(CgformFtlEntity.class, cgformFtl.getId());

            try {
                MyBeanUtils.copyBeanNotNull2Bean(cgformFtl, t);
                this.cgformFtlService.saveOrUpdate(t);
                this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
            } catch (Exception var7) {
                var7.printStackTrace();
            }
        } else {
            message = "添加成功";
            this.cgformFtlService.save(cgformFtl);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        }

        return j;
    }

    @RequestMapping(
            params = {"addorupdate"}
    )
    public ModelAndView addorupdate(CgformFtlEntity cgformFtl, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(cgformFtl.getId())) {
            cgformFtl = (CgformFtlEntity)this.cgformFtlService.getEntity(CgformFtlEntity.class, cgformFtl.getId());
        }

        HttpSession session = ContextHolderUtils.getSession();
        String lang = (String)session.getAttribute("lang");
        StringBuffer sb = new StringBuffer();
        sb.append("<html xmlns:m=\"http://schemas.microsoft.com/office/2004/12/omml\"><head><title></title>");
        sb.append("<link href=\"plug-in/easyui/themes/default/easyui.css\" id=\"easyuiTheme\" rel=\"stylesheet\" type=\"text/css\" />");
        sb.append("<link href=\"plug-in/easyui/themes/icon.css\" rel=\"stylesheet\" type=\"text/css\" />");
        sb.append("<link href=\"plug-in/accordion/css/accordion.css\" rel=\"stylesheet\" type=\"text/css\" />");
        sb.append("<link href=\"plug-in/Validform/css/style.css\" rel=\"stylesheet\" type=\"text/css\" />");
        sb.append("<link href=\"plug-in/Validform/css/tablefrom.css\" rel=\"stylesheet\" type=\"text/css\" />");
        sb.append("<style type=\"text/css\">body{font-size:12px;}table{border: 1px solid #000000;padding:0; ");
        sb.append("margin:0 auto;border-collapse: collapse;width:100%;align:right;}td {border: 1px solid ");
        sb.append("#000000;background: #fff;font-size:12px;padding: 3px 3px 3px 8px;color: #000000;word-break: keep-all;}");
        sb.append("</style></head><script type=\"text/javascript\" src=\"plug-in/jquery/jquery-1.8.3.js\">");
        sb.append("</script><script type=\"text/javascript\" src=\"plug-in/tools/dataformat.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/jquery.easyui.min.1.3.2.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/locale/zh-cn.js\"></script>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/syUtil.js\"></script><script ");
        sb.append("type=\"text/javascript\" src=\"plug-in/My97DatePicker/WdatePicker.js\"></script><script ");
        sb.append("type=\"text/javascript\" src=\"plug-in/lhgDialog/lhgdialog.min.js\"></script><script ");
        sb.append(StringUtil.replace("type=\"text/javascript\" src=\"plug-in/tools/curdtools_{0}.js\"></script><script type=\"text/javascript\" ", "{0}", lang));
        sb.append("src=\"plug-in/tools/easyuiextend.js\"></script><script type=\"text/javascript\" ");
        sb.append("src=\"plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js\"></script><script type=\"text/javascript\" ");
        sb.append("src=\"plug-in/Validform/js/Validform_Datatype_zh-cn.js\"></script><script type=\"text/javascript\" ");
        sb.append("src=\"plug-in/Validform/js/datatype_zh-cn.js\"></script><script type=\"text/javascript\" ");
        sb.append("src=\"plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js\"></script>");
        sb.append("<script type=\"text/javascript\">$(function(){$(\"#formobj\").Validform({tiptype:4,");
        sb.append("btnSubmit:\"#btn_sub\",btnReset:\"#btn_reset\",ajaxPost:true,usePlugin:{passwordstrength:");
        sb.append("{minLen:6,maxLen:18,trigger:function(obj,error){if(error){obj.parent().next().");
        sb.append("find(\".Validform_checktip\").show();obj.find(\".passwordStrength\").hide();}");
        sb.append("else{$(\".passwordStrength\").show();obj.parent().next().find(\".Validform_checktip\")");
        sb.append(".hide();}}}},callback:function(data){if(data.success");
        sb.append("==true){if(!neibuClickFlag){var win = frameElement.api.opener;frameElement.api.close();win.tip(data.msg);win.reloadTable();}else {alert(data.msg)}}else{if(data.responseText==''||");
        sb.append("data.responseText==undefined)$(\"#formobj\").html(data.msg);else $(\"#formobj\")");
        sb.append(".html(data.responseText); return false;}if(!neibuClickFlag){var win = frameElement.api.opener;win.reloadTable();}}});});</script><body>");
        sb.append("<div align=\"center\" id=\"sub_tr\" style=\"display: none;\"><input class=\"ui_state_highlight\" onclick=\"neibuClick()\" type=\"button\" value=\"提交\" /></div>");
        sb.append("</body>");
        sb.append("<script type=\"text/javascript\">$(function(){if(location.href.indexOf(\"mode=read\")!=-1){");
        sb.append("$('#formobj').find(':input').attr('disabled','disabled');}");
        sb.append("if(location.href.indexOf(\"mode=onbutton\")!=-1){$(\"#sub_tr\").show();} });");
        sb.append("var neibuClickFlag = false; function neibuClick() {neibuClickFlag = true;$('#btn_sub').trigger('click');}</script>");
        sb.append("<script type=\"text/javascript\">${js_plug_in?if_exists}</script></html>");
        req.setAttribute("cgformStr", sb);
        req.setAttribute("cgformFtlPage", cgformFtl);
        return "02".equals(cgformFtl.getEditorType())?new ModelAndView("jeecg/cgform/cgformftl/cgformFtlUEditor"):("03".equals(cgformFtl.getEditorType())?new ModelAndView("jeecg/cgform/cgformftl/cgformFtl"):new ModelAndView("jeecg/cgform/cgformftl/cgformFtlEditor"));
    }

    @RequestMapping(
            params = {"saveWordFiles"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson saveWordFiles(HttpServletRequest request, HttpServletResponse response, CgformFtlEntity cgformFtl) {
        String message = null;
        AjaxJson j = new AjaxJson();
        Map<String, Object> attributes = new HashMap();
        LogUtil.info("-------------------------step.1-------------------------------------");
        String fileKey = oConvertUtils.getString(request.getParameter("id"));
        String cgformId = oConvertUtils.getString(request.getParameter("cgformId"));
        String cgformName = oConvertUtils.getString(request.getParameter("cgformName"));
        String ftlStatus = oConvertUtils.getString(request.getParameter("ftlStatus"));
        if(oConvertUtils.isEmpty(ftlStatus)) {
            ftlStatus = "0";
        }

        if(StringUtil.isNotEmpty(fileKey)) {
            cgformFtl.setId(fileKey);
            cgformFtl = (CgformFtlEntity)this.systemService.getEntity(CgformFtlEntity.class, fileKey);
        } else {
            cgformFtl.setFtlVersion(Integer.valueOf(this.cgformFtlService.getNextVarsion(cgformId)));
        }

        LogUtil.info("-------------------------step.2-------------------------------------");
        cgformFtl.setCgformId(cgformId);
        cgformFtl.setCgformName(cgformName);
        cgformFtl.setFtlStatus(ftlStatus);
        UploadFile uploadFile = new UploadFile(request, cgformFtl);
        uploadFile.setCusPath("forms");
        message = null;

        try {
            uploadFile.getMultipartRequest().setCharacterEncoding("UTF-8");
            MultipartHttpServletRequest multipartRequest = uploadFile.getMultipartRequest();
            String uploadbasepath = uploadFile.getBasePath();
            if(uploadbasepath == null) {
                uploadbasepath = ResourceUtil.getConfigByName("uploadpath");
            }

            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            String path = uploadbasepath + "\\";
            String realPath = uploadFile.getMultipartRequest().getSession().getServletContext().getRealPath("\\") + path;
            File file = new File(realPath);
            if(!file.exists()) {
                file.mkdir();
            }

            if(uploadFile.getCusPath() != null) {
                realPath = realPath + uploadFile.getCusPath() + "\\";
                path = path + uploadFile.getCusPath() + "\\";
                file = new File(realPath);
                if(!file.exists()) {
                    file.mkdir();
                }
            } else {
                realPath = realPath + DateUtils.getDataString(DateUtils.yyyyMMdd) + "\\";
                path = path + DateUtils.getDataString(DateUtils.yyyyMMdd) + "\\";
                file = new File(realPath);
                if(!file.exists()) {
                    file.mkdir();
                }
            }

            LogUtil.info("-------------------------step.3-------------------------------------");
            String fileName = "";
            Iterator var20 = fileMap.entrySet().iterator();

            while(var20.hasNext()) {
                Entry<String, MultipartFile> entity = (Entry)var20.next();
                MultipartFile mf = (MultipartFile)entity.getValue();
                fileName = mf.getOriginalFilename();
                String extend = FileUtils.getExtend(fileName);
                String myfilename = "";
                String myhtmlfilename = "";
                String noextfilename = "";
                if(uploadFile.isRename()) {
                    noextfilename = DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(8);
                    myfilename = noextfilename + "." + extend;
                } else {
                    myfilename = fileName;
                }

                String savePath = realPath + myfilename;
                cgformFtl.setFtlWordUrl(fileName);
                File savefile = new File(savePath);
                FileCopyUtils.copy(mf.getBytes(), savefile);
                myhtmlfilename = realPath + noextfilename + ".html";
                String myftlfilename = realPath + noextfilename + ".ftl";
                LogUtil.info("-------------------------step.4-------------------------------------");
                OfficeHtmlUtil officeHtml = new OfficeHtmlUtil();
                officeHtml.wordToHtml(savePath, myhtmlfilename);
                String htmlStr = officeHtml.getInfo(myhtmlfilename);
                htmlStr = officeHtml.doHtml(htmlStr);
                officeHtml.stringToFile(htmlStr, myftlfilename);
                StringBuilder script = new StringBuilder("");
                script.append("<div align=\"center\" id=\"sub_tr\" style=\"display: none;\"><input class=\"ui_state_highlight\" onclick=\"neibuClick()\" type=\"button\" value=\"提交\" /></div>");
                script.append("</body>");
                script.append("<script type=\"text/javascript\">$(function(){if(location.href.indexOf(\"mode=read\")!=-1){");
                script.append("$('#formobj').find(':input').attr('disabled','disabled');}");
                script.append("if(location.href.indexOf(\"mode=onbutton\")!=-1){$(\"#sub_tr\").show();} });");
                script.append("var neibuClickFlag = false; function neibuClick() {neibuClickFlag = true;$('#btn_sub').trigger('click');}</script>");
                script.append("<script type=\"text/javascript\">");
                script.append("${js_plug_in?if_exists}");
                script.append("</script>");
                htmlStr = htmlStr.replace("</html>", script.toString() + "</html>");
                cgformFtl.setFtlContent(htmlStr);
                this.cgformFtlService.saveOrUpdate(cgformFtl);
                LogUtil.info("-------------------------step.5-------------------------------------");
            }
        } catch (Exception var32) {
            LogUtil.error(var32.toString());
            message = var32.toString();
        }

        attributes.put("id", cgformFtl.getId());
        if(StringUtil.isNotEmpty(message)) {
            j.setMsg("Word 模板上传失败," + message);
        } else {
            j.setMsg("Word 模板上传成功");
        }

        j.setAttributes(attributes);
        return j;
    }

    @RequestMapping(
            params = {"cgformFtl2"}
    )
    public ModelAndView cgformFtl2(HttpServletRequest request) {
        String formid = request.getParameter("formid");
        request.setAttribute("formid", formid);
        return new ModelAndView("jeecg/cgform/cgformftl/cgformFtlList2");
    }

    @RequestMapping(
            params = {"saveEditor"}
    )
    @ResponseBody
    public AjaxJson saveEditor(CgformFtlEntity cgformFtl, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String fileKey = oConvertUtils.getString(request.getParameter("id"));
        String cgformId = oConvertUtils.getString(request.getParameter("cgformId"));
        String cgformName = oConvertUtils.getString(request.getParameter("cgformName"));
        String ftlStatus = oConvertUtils.getString(request.getParameter("ftlStatus"));
        String ftlVersion = oConvertUtils.getString(request.getParameter("ftlVersion"));
        String ftlWordUrl = oConvertUtils.getString(request.getParameter("ftlWordUrl"));
        String createBy = oConvertUtils.getString(request.getParameter("createBy"));
        String createName = oConvertUtils.getString(request.getParameter("createName"));
        String createDate = oConvertUtils.getString(request.getParameter("createDate"));
        if(oConvertUtils.isEmpty(ftlStatus)) {
            ftlStatus = "0";
        }

        cgformFtl.setCgformId(cgformId);
        cgformFtl.setCgformName(cgformName);
        cgformFtl.setFtlStatus(ftlStatus);
        String ls_form;
        if(StringUtil.isNotEmpty(fileKey)) {
            cgformFtl.setId(fileKey);
            if(StringUtil.isNotEmpty(ftlVersion)) {
                cgformFtl.setFtlVersion(Integer.valueOf(ftlVersion));
            }

            if(StringUtil.isNotEmpty(ftlWordUrl)) {
                cgformFtl.setFtlWordUrl(ftlWordUrl);
            }

            if(StringUtil.isNotEmpty(createBy)) {
                cgformFtl.setCreateBy(createBy);
            }

            if(StringUtil.isNotEmpty(createName)) {
                cgformFtl.setCreateName(createName);
            }

            if(StringUtil.isNotEmpty(createDate)) {
                cgformFtl.setCreateDate(DateUtils.str2Date(createDate, DateUtils.date_sdf));
            }

            if(cgformFtl.getFtlContent() != null && cgformFtl.getFtlContent().indexOf("<form") < 0) {
                ls_form = "<form action=\"cgFormBuildController.do?saveOrUpdate\" id=\"formobj\" name=\"formobj\" method=\"post\"><input type=\"hidden\" name=\"tableName\" value=\"${tableName?if_exists?html}\" /><input type=\"hidden\" name=\"id\" value=\"${id?if_exists?html}\" /><input type=\"hidden\" id=\"btn_sub\" class=\"btn_sub\" />#{jform_hidden_field}<table";
                cgformFtl.setFtlContent(cgformFtl.getFtlContent().replace("<table", ls_form));
                cgformFtl.setFtlContent(cgformFtl.getFtlContent().replace("</table>", "</table></form>"));
            }

            this.cgformFtlService.saveOrUpdate(cgformFtl);
            j.setMsg("修改成功");
        } else {
            cgformFtl.setFtlVersion(Integer.valueOf(this.cgformFtlService.getNextVarsion(cgformId)));
            ls_form = "<form action=\"cgFormBuildController.do?saveOrUpdate\" id=\"formobj\" name=\"formobj\" method=\"post\"><input type=\"hidden\" name=\"tableName\" value=\"${tableName?if_exists?html}\" /><input type=\"hidden\" name=\"id\" value=\"${id?if_exists?html}\" /><input type=\"hidden\" id=\"btn_sub\" class=\"btn_sub\" />#{jform_hidden_field}<table";
            cgformFtl.setFtlContent(cgformFtl.getFtlContent().replace("<table", ls_form));
            cgformFtl.setFtlContent(cgformFtl.getFtlContent().replace("</table>", "</table></form>"));
            this.cgformFtlService.save(cgformFtl);
            j.setMsg("上传成功");
        }

        Map<String, Object> attributes = new HashMap();
        attributes.put("id", cgformFtl.getId());
        j.setAttributes(attributes);
        return j;
    }

    @RequestMapping(
            params = {"parseUeditorOld"}
    )
    @ResponseBody
    public AjaxJson parseUeditorOld(String parseForm, String action, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();

        try {
            new JSONObject();
            JSONObject json = JSONObject.fromObject(parseForm);
            message = FormUtil.GetHtml(json.getString("parse"), json.getString("data"), action);
            j.setMsg(message);
            j.setSuccess(true);
        } catch (Exception var7) {
            logger.info(var7.getMessage());
            var7.printStackTrace();
            message = "解析异常" + var7.getMessage();
            j.setSuccess(false);
            j.setMsg(message);
        }

        return j;
    }

    @RequestMapping(
            params = {"parseUeditor"}
    )
    @ResponseBody
    public AjaxJson parseUeditor(String parseForm, String action, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();

        try {
            if(StringUtils.isNotBlank(parseForm)) {
                TemplateUtil tool = new TemplateUtil();
                Map<String, Object> map = tool.processor(parseForm);
                j.setMsg(map.get("parseHtml").toString().replaceAll("\"", "&quot;"));
            } else {
                j.setMsg("");
            }

            j.setSuccess(true);
        } catch (Exception var8) {
            logger.info(var8.getMessage());
            var8.printStackTrace();
            message = "解析异常" + var8.getMessage();
            j.setSuccess(false);
            j.setMsg(message);
        }

        return j;
    }
}
