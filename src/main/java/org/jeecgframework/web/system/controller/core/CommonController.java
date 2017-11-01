//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.system.controller.core;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.ImportFile;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.JSONHelper;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.ReflectHelper;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.easyui.Autocomplete;
import org.jeecgframework.web.system.pojo.base.TSAttachment;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/commonController"})
public class CommonController extends BaseController {
    private static final Logger logger = Logger.getLogger(CommonController.class);
    private SystemService systemService;

    public CommonController() {
    }

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    @RequestMapping(
            params = {"listTurn"}
    )
    public ModelAndView listTurn(HttpServletRequest request) {
        String turn = request.getParameter("turn");
        return new ModelAndView(turn);
    }

    @RequestMapping(
            params = {"openViewFile"}
    )
    public ModelAndView openViewFile(HttpServletRequest request) {
        String fileid = request.getParameter("fileid");
        String subclassname = oConvertUtils.getString(request.getParameter("subclassname"), "org.jeecgframework.web.system.pojo.base.TSAttachment");
        String contentfield = oConvertUtils.getString(request.getParameter("contentfield"));
        Class fileClass = MyClassLoader.getClassByScn(subclassname);
        Object fileobj = this.systemService.getEntity(fileClass, fileid);
        ReflectHelper reflectHelper = new ReflectHelper(fileobj);
        String extend = oConvertUtils.getString(reflectHelper.getMethodValue("extend"));
        String swfpath;
        if("dwg".equals(extend)) {
            swfpath = oConvertUtils.getString(reflectHelper.getMethodValue("realpath"));
            request.setAttribute("realpath", swfpath);
            return new ModelAndView("common/upload/dwgView");
        } else if(FileUtils.isPicture(extend)) {
            swfpath = oConvertUtils.getString(reflectHelper.getMethodValue("realpath"));
            request.setAttribute("realpath", swfpath);
            request.setAttribute("fileid", fileid);
            request.setAttribute("subclassname", subclassname);
            request.setAttribute("contentfield", contentfield);
            return new ModelAndView("common/upload/imageView");
        } else {
            swfpath = oConvertUtils.getString(reflectHelper.getMethodValue("swfpath"));
            request.setAttribute("swfpath", swfpath);
            return new ModelAndView("common/upload/swfView");
        }
    }

    @RequestMapping(
            params = {"viewFile"}
    )
    public void viewFile(HttpServletRequest request, HttpServletResponse response) {
        String fileid = oConvertUtils.getString(request.getParameter("fileid"));
        String subclassname = oConvertUtils.getString(request.getParameter("subclassname"), "com.jeecg.base.pojo.TSAttachment");
        Class fileClass = MyClassLoader.getClassByScn(subclassname);
        Object fileobj = this.systemService.getEntity(fileClass, fileid);
        ReflectHelper reflectHelper = new ReflectHelper(fileobj);
        UploadFile uploadFile = new UploadFile(request, response);
        String contentfield = oConvertUtils.getString(request.getParameter("contentfield"), uploadFile.getByteField());
        byte[] content = (byte[])reflectHelper.getMethodValue(contentfield);
        String path = oConvertUtils.getString(reflectHelper.getMethodValue("realpath"));
        String extend = oConvertUtils.getString(reflectHelper.getMethodValue("extend"));
        String attachmenttitle = oConvertUtils.getString(reflectHelper.getMethodValue("attachmenttitle"));
        uploadFile.setExtend(extend);
        uploadFile.setTitleField(attachmenttitle);
        uploadFile.setRealPath(path);
        uploadFile.setContent(content);
        this.systemService.viewOrDownloadFile(uploadFile);
    }

    @RequestMapping(
            params = {"importdata"}
    )
    public ModelAndView importdata() {
        return new ModelAndView("system/upload");
    }

    @RequestMapping(
            params = {"createxml"}
    )
    public void createxml(HttpServletRequest request, HttpServletResponse response) {
        String field = request.getParameter("field");
        String entityname = request.getParameter("entityname");
        ImportFile importFile = new ImportFile(request, response);
        importFile.setField(field);
        importFile.setEntityName(entityname);
        importFile.setFileName(entityname + ".bak");
        importFile.setEntityClass(MyClassLoader.getClassByScn(entityname));
        this.systemService.createXml(importFile);
    }

    @RequestMapping(
            params = {"parserXml"}
    )
    @ResponseBody
    public AjaxJson parserXml(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson json = new AjaxJson();
        String fileName = null;
        UploadFile uploadFile = new UploadFile(request);
        String ctxPath = request.getSession().getServletContext().getRealPath("");
        File file = new File(ctxPath);
        if(!file.exists()) {
            file.mkdir();
        }

        MultipartHttpServletRequest multipartRequest = uploadFile.getMultipartRequest();
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        Iterator var11 = fileMap.entrySet().iterator();

        while(var11.hasNext()) {
            Entry<String, MultipartFile> entity = (Entry)var11.next();
            MultipartFile mf = (MultipartFile)entity.getValue();
            fileName = mf.getOriginalFilename();
            String savePath = file.getPath() + "/" + fileName;
            File savefile = new File(savePath);

            try {
                FileCopyUtils.copy(mf.getBytes(), savefile);
            } catch (IOException var16) {
                var16.printStackTrace();
            }
        }

        this.systemService.parserXml(ctxPath + "/" + fileName);
        json.setSuccess(true);
        return json;
    }

    @RequestMapping(
            params = {"getAutoList"}
    )
    public void getAutoList(HttpServletRequest request, HttpServletResponse response, Autocomplete autocomplete) {
        String jsonp = request.getParameter("jsonpcallback");
        String trem = StringUtil.getEncodePra(request.getParameter("trem"));
        autocomplete.setTrem(trem);
        List autoList = this.systemService.getAutoList(autocomplete);
        String labelFields = autocomplete.getLabelField();
        String[] fieldArr = labelFields.split(",");
        String valueField = autocomplete.getValueField();
        String[] allFieldArr = null;
        if(StringUtil.isNotEmpty(valueField)) {
            allFieldArr = new String[fieldArr.length + 1];

            for(int i = 0; i < fieldArr.length; ++i) {
                allFieldArr[i] = fieldArr[i];
            }

            allFieldArr[fieldArr.length] = valueField;
        }

        try {
            String str = TagUtil.getAutoList(autocomplete, autoList);
            str = "(" + str + ")";
            response.setContentType("application/json;charset=UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0L);
            response.getWriter().write(JSONHelper.listtojson(allFieldArr, allFieldArr.length, autoList));
            response.getWriter().flush();
        } catch (Exception var20) {
            var20.printStackTrace();
        } finally {
            try {
                response.getWriter().close();
            } catch (IOException var19) {
                ;
            }

        }

    }

    @RequestMapping(
            params = {"delObjFile"}
    )
    @ResponseBody
    public AjaxJson delObjFile(HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        String fileKey = oConvertUtils.getString(request.getParameter("fileKey"));
        TSAttachment attachment = (TSAttachment)this.systemService.getEntity(TSAttachment.class, fileKey);
        String subclassname = attachment.getSubclassname();
        Object objfile = this.systemService.getEntity(MyClassLoader.getClassByScn(subclassname), attachment.getId());
        message = attachment.getAttachmenttitle() + "删除成功";
        this.systemService.delete(objfile);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"objfileList"}
    )
    public ModelAndView objfileList(HttpServletRequest request) {
        Object object = null;
        String fileKey = oConvertUtils.getString(request.getParameter("fileKey"));
        TSAttachment attachment = (TSAttachment)this.systemService.getEntity(TSAttachment.class, fileKey);
        String businessKey = oConvertUtils.getString(request.getParameter("businessKey"));
        String busentityName = oConvertUtils.getString(request.getParameter("busentityName"));
        String typename = oConvertUtils.getString(request.getParameter("typename"));
        String typecode = oConvertUtils.getString(request.getParameter("typecode"));
        if(StringUtil.isNotEmpty(busentityName) && StringUtil.isNotEmpty(businessKey)) {
            object = this.systemService.get(MyClassLoader.getClassByScn(busentityName), businessKey);
            request.setAttribute("object", object);
            request.setAttribute("businessKey", businessKey);
        }

        if(attachment != null) {
            request.setAttribute("subclassname", attachment.getSubclassname());
        }

        request.setAttribute("fileKey", fileKey);
        request.setAttribute("typecode", typecode);
        request.setAttribute("typename", typename);
        request.setAttribute("typecode", typecode);
        return new ModelAndView("common/objfile/objfileList");
    }

    @RequestMapping(
            params = {"objfileGrid"}
    )
    public void objfileGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        String businessKey = oConvertUtils.getString(request.getParameter("businessKey"));
        String subclassname = oConvertUtils.getString(request.getParameter("subclassname"));
        String type = oConvertUtils.getString(request.getParameter("typename"));
        String code = oConvertUtils.getString(request.getParameter("typecode"));
        String filekey = oConvertUtils.getString(request.getParameter("filekey"));
        CriteriaQuery cq = new CriteriaQuery(MyClassLoader.getClassByScn(subclassname), dataGrid);
        cq.eq("businessKey", businessKey);
        if(StringUtil.isNotEmpty(type)) {
            cq.createAlias("TBInfotype", "TBInfotype");
            cq.eq("TBInfotype.typename", type);
        }

        if(StringUtil.isNotEmpty(filekey)) {
            cq.eq("id", filekey);
        }

        if(StringUtil.isNotEmpty(code)) {
            cq.createAlias("TBInfotype", "TBInfotype");
            cq.eq("TBInfotype.typecode", code);
        }

        cq.add();
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }
}
