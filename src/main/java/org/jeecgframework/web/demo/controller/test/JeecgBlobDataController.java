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
import org.jeecgframework.web.demo.entity.test.JeecgBlobDataEntity;
import org.jeecgframework.web.demo.service.test.JeecgBlobDataServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/jeecgBlobDataController"})
public class JeecgBlobDataController extends BaseController {
    private static final Logger logger = Logger.getLogger(JeecgBlobDataController.class);
    @Autowired
    private JeecgBlobDataServiceI jeecgBlobDataService;
    @Autowired
    private SystemService systemService;

    public JeecgBlobDataController() {
    }

    @RequestMapping(
            params = {"jeecgBlobData"}
    )
    public ModelAndView jeecgBlobData(HttpServletRequest request) {
        return new ModelAndView("jeecg/demo/test/jeecgBlobDataList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(JeecgBlobDataEntity jeecgBlobData, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(JeecgBlobDataEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, jeecgBlobData);
        this.jeecgBlobDataService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"del"}
    )
    @ResponseBody
    public AjaxJson del(JeecgBlobDataEntity jeecgBlobData, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        jeecgBlobData = (JeecgBlobDataEntity)this.systemService.getEntity(JeecgBlobDataEntity.class, jeecgBlobData.getId());
        message = "删除成功";
        this.jeecgBlobDataService.delete(jeecgBlobData);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"download"}
    )
    public void exportXls(HttpServletRequest request, String fileId, HttpServletResponse response) {
        JeecgBlobDataEntity obj = (JeecgBlobDataEntity)this.systemService.getEntity(JeecgBlobDataEntity.class, fileId);

        try {
            Blob attachment = obj.getAttachmentcontent();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String((obj.getAttachmenttitle() + "." + obj.getExtend()).getBytes("GBK"), "ISO8859-1"));
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
            params = {"upload"}
    )
    @ResponseBody
    public AjaxJson upload(HttpServletRequest request, String documentTitle, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        Iterator var8 = fileMap.entrySet().iterator();

        while(var8.hasNext()) {
            Entry<String, MultipartFile> entity = (Entry)var8.next();
            MultipartFile file = (MultipartFile)entity.getValue();

            try {
                this.jeecgBlobDataService.saveObj(documentTitle, file);
                j.setMsg("文件导入成功！");
            } catch (Exception var11) {
                j.setMsg("文件导入失败！");
                logger.error(ExceptionUtil.getExceptionMessage(var11));
            }
        }

        return j;
    }

    @RequestMapping(
            params = {"save"}
    )
    @ResponseBody
    public AjaxJson save(JeecgBlobDataEntity jeecgBlobData, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        if(StringUtil.isNotEmpty(jeecgBlobData.getId())) {
            message = "更新成功";
            JeecgBlobDataEntity t = (JeecgBlobDataEntity)this.jeecgBlobDataService.get(JeecgBlobDataEntity.class, jeecgBlobData.getId());

            try {
                MyBeanUtils.copyBeanNotNull2Bean(jeecgBlobData, t);
                this.jeecgBlobDataService.saveOrUpdate(t);
                this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
            } catch (Exception var7) {
                var7.printStackTrace();
            }
        } else {
            message = "添加成功";
            this.jeecgBlobDataService.save(jeecgBlobData);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        }

        return j;
    }

    @RequestMapping(
            params = {"addorupdate"}
    )
    public ModelAndView addorupdate(JeecgBlobDataEntity jeecgBlobData, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(jeecgBlobData.getId())) {
            jeecgBlobData = (JeecgBlobDataEntity)this.jeecgBlobDataService.getEntity(JeecgBlobDataEntity.class, jeecgBlobData.getId());
            req.setAttribute("jeecgBlobDataPage", jeecgBlobData);
        }

        return new ModelAndView("jeecg/demo/test/jeecgBlobData");
    }
}
