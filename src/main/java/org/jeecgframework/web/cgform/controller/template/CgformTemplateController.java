//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.controller.template;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jodd.io.StreamUtil;
import jodd.io.ZipUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
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
import org.jeecgframework.web.cgform.entity.template.CgformTemplateEntity;
import org.jeecgframework.web.cgform.service.template.CgformTemplateServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/cgformTemplateController"})
public class CgformTemplateController extends BaseController {
    private static final Logger logger = Logger.getLogger(CgformTemplateController.class);
    @Autowired
    private CgformTemplateServiceI cgformTemplateService;
    @Autowired
    private SystemService systemService;

    public CgformTemplateController() {
    }

    @RequestMapping(
            params = {"cgformTemplate"}
    )
    public ModelAndView cgformTemplate(HttpServletRequest request) {
        return new ModelAndView("jeecg/cgform/template/cgformTemplateList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(CgformTemplateEntity cgformTemplate, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(CgformTemplateEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, cgformTemplate, request.getParameterMap());
        cq.add();
        this.cgformTemplateService.getDataGridReturn(cq, true);
        List<CgformTemplateEntity> dataList = dataGrid.getResults();
        if(dataList != null && dataList.size() > 0) {
            Iterator var8 = dataList.iterator();

            while(var8.hasNext()) {
                CgformTemplateEntity entity = (CgformTemplateEntity)var8.next();
                entity.setTemplatePic("cgformTemplateController.do?showPic&code=" + entity.getTemplateCode() + "&path=" + entity.getTemplatePic());
            }
        }

        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(CgformTemplateEntity cgformTemplate, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        cgformTemplate = (CgformTemplateEntity)this.systemService.getEntity(CgformTemplateEntity.class, cgformTemplate.getId());
        message = "自定义模板删除成功";

        try {
            this.cgformTemplateService.delete(cgformTemplate);
            if(cgformTemplate.getTemplateCode() != null) {
                this.delTemplate(request, cgformTemplate.getTemplateCode());
            }

            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "自定义模板删除失败";
            throw new BusinessException(var6.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    private void delTemplate(HttpServletRequest request, String code) {
        String dirPath = this.getUploadBasePath(request) + File.separator + code;

        try {
            FileUtils.deleteDirectory(new File(dirPath));
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

    @RequestMapping(
            params = {"doBatchDel"}
    )
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "自定义模板删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                CgformTemplateEntity cgformTemplate = (CgformTemplateEntity)this.systemService.getEntity(CgformTemplateEntity.class, id);
                this.cgformTemplateService.delete(cgformTemplate);
                if(cgformTemplate.getTemplateCode() != null) {
                    this.delTemplate(request, cgformTemplate.getTemplateCode());
                }

                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "自定义模板删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(CgformTemplateEntity cgformTemplate, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "自定义模板添加成功";

        try {
            this.cgformTemplateService.save(cgformTemplate);
            String basePath = this.getUploadBasePath(request);
            File templeDir = new File(basePath + File.separator + cgformTemplate.getTemplateCode());
            if(!templeDir.exists()) {
                templeDir.mkdirs();
            }

            this.removeZipFile(basePath + File.separator + "temp" + File.separator + cgformTemplate.getTemplateZipName(), templeDir.getAbsolutePath());
            this.removeIndexFile(basePath + File.separator + "temp" + File.separator + cgformTemplate.getTemplatePic(), templeDir.getAbsolutePath());
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var7) {
            var7.printStackTrace();
            message = "自定义模板添加失败";
            throw new BusinessException(var7.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    private void removeIndexFile(String templateIndexPath, String templateDir) {
        File indexFile = new File(templateIndexPath);
        if(indexFile.exists() && !indexFile.isDirectory()) {
            File destDir = new File(templateDir + File.separator + "images");
            if(!destDir.exists()) {
                destDir.mkdirs();
            }

            File destIndexFile = new File(destDir, indexFile.getName());
            if(destIndexFile.exists()) {
                org.jeecgframework.core.util.FileUtils.delete(destIndexFile.getAbsolutePath());
            }

            try {
                FileCopyUtils.copy(indexFile, destIndexFile);
            } catch (IOException var10) {
                var10.printStackTrace();
            } finally {
                org.jeecgframework.core.util.FileUtils.delete(indexFile.getAbsolutePath());
            }
        }

    }

    private void removeZipFile(String zipFilePath, String templateDir) {
        File zipFile = new File(zipFilePath);
        if(zipFile.exists() && !zipFile.isDirectory()) {
            try {
                this.unZipFiles(zipFile, templateDir);
            } catch (IOException var8) {
                var8.printStackTrace();
            } finally {
                org.jeecgframework.core.util.FileUtils.delete(zipFilePath);
            }
        }

    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(CgformTemplateEntity cgformTemplate, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "自定义模板更新成功";
        CgformTemplateEntity t = (CgformTemplateEntity)this.cgformTemplateService.get(CgformTemplateEntity.class, cgformTemplate.getId());

        try {
            MyBeanUtils.copyBeanNotNull2Bean(cgformTemplate, t);
            String basePath = this.getUploadBasePath(request);
            File templeDir = new File(basePath + File.separator + t.getTemplateCode());
            if(!templeDir.exists()) {
                templeDir.mkdirs();
            }

            this.removeZipFile(basePath + File.separator + "temp" + File.separator + t.getTemplateZipName(), templeDir.getAbsolutePath());
            this.removeIndexFile(basePath + File.separator + "temp" + File.separator + t.getTemplatePic(), templeDir.getAbsolutePath());
            this.cgformTemplateService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var8) {
            var8.printStackTrace();
            message = "自定义模板更新失败";
            throw new BusinessException(var8.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(CgformTemplateEntity cgformTemplate, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(cgformTemplate.getId())) {
            cgformTemplate = (CgformTemplateEntity)this.cgformTemplateService.getEntity(CgformTemplateEntity.class, cgformTemplate.getId());
            req.setAttribute("cgformTemplatePage", cgformTemplate);
        }

        return new ModelAndView("jeecg/cgform/template/cgformTemplate-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(CgformTemplateEntity cgformTemplate, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(cgformTemplate.getId())) {
            cgformTemplate = (CgformTemplateEntity)this.cgformTemplateService.getEntity(CgformTemplateEntity.class, cgformTemplate.getId());
            req.setAttribute("cgformTemplatePage", cgformTemplate);
        }

        return new ModelAndView("jeecg/cgform/template/cgformTemplate-update");
    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "cgformTemplateController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(CgformTemplateEntity cgformTemplate, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(CgformTemplateEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, cgformTemplate, request.getParameterMap());
        List<CgformTemplateEntity> cgformTemplates = this.cgformTemplateService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "自定义模板");
        modelMap.put("entity", CgformTemplateEntity.class);
        modelMap.put("params", new ExportParams("自定义模板列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", cgformTemplates);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"checkTemplate"}
    )
    @ResponseBody
    public boolean checkTemplate(String id, HttpServletRequest request) {
        boolean flag = false;
        if(StringUtils.isNotBlank(id)) {
            CgformTemplateEntity entity = (CgformTemplateEntity)this.cgformTemplateService.getEntity(CgformTemplateEntity.class, id);
            if(entity != null && entity.getTemplateCode() != null) {
                File dirFile = new File(this.getUploadBasePath(request) + "/" + entity.getTemplateCode());
                if(dirFile.exists() && dirFile.isDirectory()) {
                    flag = true;
                }
            }
        }

        return flag;
    }

    @RequestMapping(
            params = {"downloadTemplate"}
    )
    public void downloadTemplate(String id, HttpServletRequest request, HttpServletResponse response) {
        if(StringUtils.isNotBlank(id)) {
            CgformTemplateEntity entity = (CgformTemplateEntity)this.cgformTemplateService.getEntity(CgformTemplateEntity.class, id);
            if(entity != null && entity.getTemplateCode() != null) {
                File zipFile = this.zipFile(entity.getTemplateCode(), request);
                if(zipFile != null && zipFile.exists()) {
                    FileInputStream inputStream = null;

                    try {
                        inputStream = new FileInputStream(zipFile);
                        this.downLoadFile(inputStream, entity.getTemplateName() + ".zip", zipFile.length(), response);
                    } catch (FileNotFoundException var16) {
                        var16.printStackTrace();
                    } finally {
                        if(inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException var15) {
                                var15.printStackTrace();
                            }
                        }

                        org.jeecgframework.core.util.FileUtils.delete(zipFile.getAbsolutePath());
                    }
                }
            }

        }
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
                List<CgformTemplateEntity> listCgformTemplateEntitys = ExcelImportUtil.importExcel(file.getInputStream(), CgformTemplateEntity.class, params);
                Iterator var12 = listCgformTemplateEntitys.iterator();

                while(var12.hasNext()) {
                    CgformTemplateEntity cgformTemplate = (CgformTemplateEntity)var12.next();
                    this.cgformTemplateService.save(cgformTemplate);
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
            params = {"uploadPic"}
    )
    @ResponseBody
    public AjaxJson uploadPic(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        File picTempFile = null;
        File tempDir = new File(this.getUploadBasePath(request), "temp");
        if(!tempDir.exists()) {
            tempDir.mkdirs();
        }

        for(Iterator var9 = fileMap.entrySet().iterator(); var9.hasNext(); j.setObj(picTempFile.getName())) {
            Entry<String, MultipartFile> entity = (Entry)var9.next();
            MultipartFile file = (MultipartFile)entity.getValue();
            picTempFile = new File(tempDir.getAbsolutePath(), "/index_" + request.getSession().getId() + "." + org.jeecgframework.core.util.FileUtils.getExtend(file.getOriginalFilename()));

            try {
                if(picTempFile.exists()) {
                    FileUtils.forceDelete(picTempFile);
                }

                FileCopyUtils.copy(file.getBytes(), picTempFile);
            } catch (Exception var12) {
                var12.printStackTrace();
                j.setMsg("预览图上传失败！");
                j.setSuccess(false);
            }
        }

        j.setMsg("图片上传成功！");
        j.setSuccess(true);
        return j;
    }

    @RequestMapping(
            params = {"uploadZip"}
    )
    @ResponseBody
    public AjaxJson uploadZip(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        File picTempFile = null;
        File tempDir = new File(this.getUploadBasePath(request), "temp");
        if(!tempDir.exists()) {
            tempDir.mkdirs();
        }

        for(Iterator var9 = fileMap.entrySet().iterator(); var9.hasNext(); j.setObj(picTempFile.getName())) {
            Entry<String, MultipartFile> entity = (Entry)var9.next();
            MultipartFile file = (MultipartFile)entity.getValue();
            picTempFile = new File(tempDir.getAbsolutePath(), "/zip_" + request.getSession().getId() + "." + org.jeecgframework.core.util.FileUtils.getExtend(file.getOriginalFilename()));

            try {
                if(picTempFile.exists()) {
                    FileUtils.forceDelete(picTempFile);
                }

                FileCopyUtils.copy(file.getBytes(), picTempFile);
            } catch (Exception var12) {
                var12.printStackTrace();
                j.setMsg("模板文件上传失败！");
                j.setSuccess(false);
            }
        }

        j.setMsg("模板文件上传成功！");
        j.setSuccess(true);
        return j;
    }

    @RequestMapping(
            params = {"showPic"}
    )
    public void showPic(HttpServletRequest request, String code, String path, HttpServletResponse response) {
        String defaultPath = "default.jpg";
        String defaultCode = "default/images/";
        if(path == null) {
            path = defaultPath;
            code = defaultCode;
        } else if(code == null) {
            code = "temp/";
        } else {
            code = code + "/images/";
        }

        FileInputStream fis = null;
        response.setContentType("image/" + org.jeecgframework.core.util.FileUtils.getExtend(path));

        try {
            OutputStream out = response.getOutputStream();
            File file = new File(this.getUploadBasePath(request), code + path);
            if(!file.exists() || file.isDirectory()) {
                file = new File(this.getUploadBasePath(request), defaultCode + defaultPath);
            }

            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception var19) {
            var19.printStackTrace();
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException var18) {
                    var18.printStackTrace();
                }
            }

        }

    }

    private String getUploadBasePath(HttpServletRequest request) {
        String path = this.getClass().getResource("/").getPath() + "online/template";
        return path;
    }

    @RequestMapping(
            params = {"checkCode"}
    )
    @ResponseBody
    public AjaxJson checkCode(String param) {
        Assert.notNull(param);
        AjaxJson j = new AjaxJson();
        Long count = this.cgformTemplateService.getCountForJdbcParam("select count(id) from cgform_template where template_code=?  ", new Object[]{param});
        if(count != null && count.longValue() > 0L) {
            j.setSuccess(false);
        } else {
            j.setSuccess(true);
        }

        return j;
    }

    @RequestMapping(
            params = {"getTemplate"}
    )
    @ResponseBody
    public AjaxJson getTemplate(String type) {
        Assert.notNull(type);
        AjaxJson j = new AjaxJson();
        j.setSuccess(true);
        j.setObj(this.cgformTemplateService.getTemplateListByType(type));
        return j;
    }

    private void unZipFiles(File zipFile, String descDir) throws IOException {
        ZipUtil.unzip(zipFile, new File(descDir));
    }

    private File zipFile(String templateCode, HttpServletRequest request) {
        String dirPath = this.getUploadBasePath(request) + "/" + templateCode;
        ZipOutputStream zos = null;
        File zipFile = null;

        try {
            File dir = new File(dirPath);
            if(dir.exists() && dir.isDirectory()) {
                zipFile = new File(dir + "_" + request.getSession().getId() + ".zip");
                File[] files = dir.listFiles();
                if(files != null) {
                    zos = ZipUtil.createZip(zipFile);
                    File[] var11 = files;
                    int var10 = files.length;

                    for(int var9 = 0; var9 < var10; ++var9) {
                        File file = var11[var9];
                        ZipUtil.addToZip(zos, file);
                    }
                }
            }
        } catch (IOException var15) {
            var15.printStackTrace();
        } finally {
            if(zos != null) {
                StreamUtil.close(zos);
            }

        }

        return zipFile;
    }

    private void downLoadFile(InputStream inputStream, String fileName, long size, HttpServletResponse response) {
        try {
            fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
        } catch (Exception var18) {
            var18.printStackTrace();
        }

        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        if(size > 0L) {
            response.addHeader("Content-Length", String.valueOf(size));
        }

        ServletOutputStream stream = null;

        try {
            stream = response.getOutputStream();
            FileCopyUtils.copy(inputStream, stream);
            stream.flush();
        } catch (IOException var17) {
            var17.printStackTrace();
        } finally {
            if(stream != null) {
                try {
                    stream.close();
                } catch (IOException var16) {
                    var16.printStackTrace();
                }
            }

        }

    }
}
