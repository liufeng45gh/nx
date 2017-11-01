//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.demo.controller.test;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.demo.entity.test.WebOfficeEntity;
import org.jeecgframework.web.demo.service.test.WebOfficeServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/webOfficeController"})
public class WebOfficeController extends BaseController {
    private static final Logger logger = Logger.getLogger(WebOfficeController.class);
    @Autowired
    private WebOfficeServiceI webOfficeService;
    @Autowired
    private SystemService systemService;

    public WebOfficeController() {
    }

    @RequestMapping(
            params = {"webOffice"}
    )
    public ModelAndView webOffice(HttpServletRequest request) {
        return new ModelAndView("jeecg/demo/test/webOfficeList");
    }

    @RequestMapping(
            params = {"webOfficeSample"}
    )
    public ModelAndView webOfficeSample(HttpServletRequest request) {
        return new ModelAndView("jeecg/demo/test/webOfficeSample");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(WebOfficeEntity webOffice, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WebOfficeEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, webOffice, request.getParameterMap());
        this.webOfficeService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"del"}
    )
    @ResponseBody
    public AjaxJson del(WebOfficeEntity webOffice, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        webOffice = (WebOfficeEntity)this.systemService.getEntity(WebOfficeEntity.class, webOffice.getId());
        message = "删除成功";
        this.webOfficeService.delete(webOffice);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"save"}
    )
    @ResponseBody
    public AjaxJson save(WebOfficeEntity webOffice, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        if(StringUtil.isNotEmpty(webOffice.getId())) {
            message = "更新成功";
            WebOfficeEntity t = (WebOfficeEntity)this.webOfficeService.get(WebOfficeEntity.class, webOffice.getId());

            try {
                MyBeanUtils.copyBeanNotNull2Bean(webOffice, t);
                this.webOfficeService.saveOrUpdate(t);
                this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
            } catch (Exception var7) {
                var7.printStackTrace();
            }
        } else {
            message = "添加成功";
            this.webOfficeService.save(webOffice);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        }

        return j;
    }

    @RequestMapping(
            params = {"addorupdate"}
    )
    public ModelAndView addorupdate(WebOfficeEntity webOffice, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(webOffice.getId())) {
            webOffice = (WebOfficeEntity)this.webOfficeService.getEntity(WebOfficeEntity.class, webOffice.getId());
            req.setAttribute("webOfficePage", webOffice);
        }

        return new ModelAndView("jeecg/demo/test/webOffice");
    }

    @RequestMapping(
            params = {"newDocument"}
    )
    public ModelAndView newDocument(WebOfficeEntity webOffice, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(webOffice.getId())) {
            webOffice = (WebOfficeEntity)this.webOfficeService.getEntity(WebOfficeEntity.class, webOffice.getId());
            req.setAttribute("docId", webOffice.getId());
            req.setAttribute("fileType", webOffice.getDoctype());
            req.setAttribute("docData", webOffice);
        } else {
            req.setAttribute("docId", "");
            req.setAttribute("fileType", req.getParameter("fileType"));
        }

        return new ModelAndView("jeecg/demo/test/webOfficeEdit");
    }

    @RequestMapping(
            params = {"getDoc"}
    )
    public void getDoc(HttpServletRequest request, Integer fileId, HttpServletResponse response) {
        WebOfficeEntity obj = (WebOfficeEntity)this.systemService.getEntity(WebOfficeEntity.class, fileId);

        try {
            Blob attachment = obj.getDoccontent();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String((obj.getDoctitle() + "." + obj.getDoctype()).getBytes("GBK"), "ISO8859-1"));
            InputStream bis = attachment.getBinaryStream();
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];

            int bytesRead;
            long lTotalLen;
            for(lTotalLen = 0L; -1 != (bytesRead = bis.read(buff, 0, buff.length)); lTotalLen += (long)bytesRead) {
                bos.write(buff, 0, bytesRead);
            }

            response.setHeader("Content-Length", String.valueOf(lTotalLen));
            bos.flush();
            bos.close();
        } catch (Exception var12) {
            var12.printStackTrace();
        }

    }

    @RequestMapping(
            params = {"saveDoc"}
    )
    @ResponseBody
    public AjaxJson saveDoc(WebOfficeEntity webOffice, HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        Iterator var8 = fileMap.entrySet().iterator();

        while(var8.hasNext()) {
            Entry<String, MultipartFile> entity = (Entry)var8.next();
            MultipartFile file = (MultipartFile)entity.getValue();

            try {
                this.webOfficeService.saveObj(webOffice, file);
                j.setMsg("文件导入成功！");
            } catch (Exception var11) {
                j.setMsg("文件导入失败！");
                logger.error(ExceptionUtil.getExceptionMessage(var11));
            }
        }

        return j;
    }
}
