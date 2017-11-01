//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.onlinedoc.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.onlinedoc.entity.OnlineDocEntity;
import org.jeecgframework.web.onlinedoc.service.OnlineDocServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/onlineDocController"})
public class OnlineDocController extends BaseController {
    private static final Logger logger = Logger.getLogger(OnlineDocController.class);
    @Autowired
    private OnlineDocServiceI onlineDocService;
    @Autowired
    private SystemService systemService;

    public OnlineDocController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("jeecg/onlinedoc/onlineDocList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(OnlineDocEntity onlineDoc, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(OnlineDocEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, onlineDoc, request.getParameterMap());
        cq.add();
        this.onlineDocService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(OnlineDocEntity onlineDoc, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        onlineDoc = (OnlineDocEntity)this.systemService.getEntity(OnlineDocEntity.class, onlineDoc.getId());
        message = "在线文档删除成功";

        try {
            this.onlineDocService.delete(onlineDoc);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "在线文档删除失败";
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
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "在线文档删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                OnlineDocEntity onlineDoc = (OnlineDocEntity)this.systemService.getEntity(OnlineDocEntity.class, id);
                this.onlineDocService.delete(onlineDoc);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "在线文档删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(OnlineDocEntity onlineDoc, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "在线文档添加成功";

        try {
            this.onlineDocService.save(onlineDoc);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "在线文档添加失败";
            throw new BusinessException(var6.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(OnlineDocEntity onlineDoc, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "在线文档更新成功";
        OnlineDocEntity t = (OnlineDocEntity)this.onlineDocService.get(OnlineDocEntity.class, onlineDoc.getId());

        try {
            MyBeanUtils.copyBeanNotNull2Bean(onlineDoc, t);
            this.onlineDocService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var7) {
            var7.printStackTrace();
            message = "在线文档更新失败";
            throw new BusinessException(var7.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(OnlineDocEntity onlineDoc, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(onlineDoc.getId())) {
            onlineDoc = (OnlineDocEntity)this.onlineDocService.getEntity(OnlineDocEntity.class, onlineDoc.getId());
            req.setAttribute("onlineDocPage", onlineDoc);
        }

        return new ModelAndView("jeecg/onlinedoc/onlineDoc-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(OnlineDocEntity onlineDoc, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(onlineDoc.getId())) {
            onlineDoc = (OnlineDocEntity)this.onlineDocService.getEntity(OnlineDocEntity.class, onlineDoc.getId());
            req.setAttribute("onlineDocPage", onlineDoc);
        }

        return new ModelAndView("jeecg/onlinedoc/onlineDoc-update");
    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "onlineDocController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(OnlineDocEntity onlineDoc, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(OnlineDocEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, onlineDoc, request.getParameterMap());
        List<OnlineDocEntity> onlineDocs = this.onlineDocService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "在线文档");
        modelMap.put("entity", OnlineDocEntity.class);
        modelMap.put("params", new ExportParams("在线文档列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", onlineDocs);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByT"}
    )
    public String exportXlsByT(OnlineDocEntity onlineDoc, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "在线文档");
        modelMap.put("entity", OnlineDocEntity.class);
        modelMap.put("params", new ExportParams("在线文档列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"importExcel"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        Iterator var7 = fileMap.entrySet().iterator();

        while(var7.hasNext()) {
            Entry<String, MultipartFile> entity = (Entry)var7.next();
            MultipartFile file = (MultipartFile)entity.getValue();
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);

            try {
                List<OnlineDocEntity> listOnlineDocEntitys = ExcelImportUtil.importExcel(file.getInputStream(), OnlineDocEntity.class, params);
                Iterator var12 = listOnlineDocEntitys.iterator();

                while(var12.hasNext()) {
                    OnlineDocEntity onlineDoc = (OnlineDocEntity)var12.next();
                    this.onlineDocService.save(onlineDoc);
                }

                j.setMsg("文件导入成功！");
            } catch (Exception var21) {
                j.setMsg("文件导入失败！");
                logger.error(ExceptionUtil.getExceptionMessage(var21));
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException var20) {
                    var20.printStackTrace();
                }

            }
        }

        return j;
    }

    @RequestMapping(
            params = {"ajaxUpload"}
    )
    @ResponseBody
    public String ajaxUpload(HttpServletRequest request) throws IllegalStateException, IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        String fileName = "";
        String uploadPath = "upload/";
        String path = request.getSession().getServletContext().getRealPath("/") + uploadPath;
        String realPath = "";
        String oldName = "";
        Iterator it = multipartRequest.getFileNames();

        while(it.hasNext()) {
            String key = (String)it.next();
            MultipartFile mulfile = multipartRequest.getFile(key);
            fileName = mulfile.getOriginalFilename();
            oldName = fileName.substring(0, fileName.lastIndexOf("."));
            fileName = this.rewriteFileName(fileName);
            File file = new File(path + fileName);
            mulfile.transferTo(file);
        }

        realPath = "{\"path\":\"" + uploadPath + fileName + "\",\"oldName\":\"" + oldName + "\",\"newName\":\"" + fileName + "\"}";
        return realPath;
    }

    private String rewriteFileName(String fileName) {
        int pointIndex = fileName.lastIndexOf(".");
        StringBuffer fileNameBuffer = new StringBuffer();
        fileNameBuffer.append((new Date()).getTime() + "_" + fileName.substring(0, pointIndex));
        fileNameBuffer.append(fileName.substring(pointIndex));
        return fileNameBuffer.toString();
    }
}
