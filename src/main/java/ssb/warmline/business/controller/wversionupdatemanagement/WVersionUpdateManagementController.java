//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.controller.wversionupdatemanagement;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import ssb.warmline.business.commons.utils.DateUtils;
import ssb.warmline.business.entity.wversionupdatemanagement.WVersionUpdateManagementEntity;
import ssb.warmline.business.service.wversionupdatemanagement.WVersionUpdateManagementServiceI;
import ssb.warmline.business.utils.PropertiesUtil;

@Controller
@RequestMapping({"/wVersionUpdateManagementController"})
public class WVersionUpdateManagementController extends BaseController {
    private static final Logger logger = Logger.getLogger(WVersionUpdateManagementController.class);
    static final String separator;
    @Autowired
    private WVersionUpdateManagementServiceI wVersionUpdateManagementService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    static {
        separator = File.separator;
    }

    public WVersionUpdateManagementController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("ssb/warmline/business/wversionupdatemanagement/wVersionUpdateManagementList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(WVersionUpdateManagementEntity wVersionUpdateManagement, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WVersionUpdateManagementEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wVersionUpdateManagement, request.getParameterMap());
        cq.add();
        this.wVersionUpdateManagementService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(WVersionUpdateManagementEntity wVersionUpdateManagement, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        wVersionUpdateManagement = (WVersionUpdateManagementEntity)this.systemService.getEntity(WVersionUpdateManagementEntity.class, wVersionUpdateManagement.getId());
        message = "w_version_update_management删除成功";

        try {
            this.wVersionUpdateManagementService.delete(wVersionUpdateManagement);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "w_version_update_management删除失败";
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
        message = "w_version_update_management删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                WVersionUpdateManagementEntity wVersionUpdateManagement = (WVersionUpdateManagementEntity)this.systemService.getEntity(WVersionUpdateManagementEntity.class, id);
                this.wVersionUpdateManagementService.delete(wVersionUpdateManagement);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "w_version_update_management删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(WVersionUpdateManagementEntity wVersionUpdateManagement, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "版本更新添加成功";

        try {
            List<WVersionUpdateManagementEntity> listFVersionUpdateManagements = this.wVersionUpdateManagementService.getList(WVersionUpdateManagementEntity.class);
            if(listFVersionUpdateManagements != null && listFVersionUpdateManagements.size() > 0) {
                for(int i = 0; i < listFVersionUpdateManagements.size(); ++i) {
                    WVersionUpdateManagementEntity fVersionUpdateManagementEntity = (WVersionUpdateManagementEntity)listFVersionUpdateManagements.get(i);
                    fVersionUpdateManagementEntity.setCurrentField("false");
                    this.systemService.updateEntitie(fVersionUpdateManagementEntity);
                }
            }

            wVersionUpdateManagement.setDownloadUrl(PropertiesUtil.getProperties("downloadUrl"));
            wVersionUpdateManagement.setCurrentField("true");
            this.wVersionUpdateManagementService.save(wVersionUpdateManagement);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
            this.wVersionUpdateManagementService.save(wVersionUpdateManagement);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var8) {
            var8.printStackTrace();
            message = "w_version_update_management添加失败";
            throw new BusinessException(var8.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(WVersionUpdateManagementEntity wVersionUpdateManagement, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "w_version_update_management更新成功";
        WVersionUpdateManagementEntity t = (WVersionUpdateManagementEntity)this.wVersionUpdateManagementService.get(WVersionUpdateManagementEntity.class, wVersionUpdateManagement.getId());

        try {
            MyBeanUtils.copyBeanNotNull2Bean(wVersionUpdateManagement, t);
            this.wVersionUpdateManagementService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var7) {
            var7.printStackTrace();
            message = "w_version_update_management更新失败";
            throw new BusinessException(var7.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(WVersionUpdateManagementEntity wVersionUpdateManagement, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wVersionUpdateManagement.getId())) {
            wVersionUpdateManagement = (WVersionUpdateManagementEntity)this.wVersionUpdateManagementService.getEntity(WVersionUpdateManagementEntity.class, wVersionUpdateManagement.getId());
            req.setAttribute("wVersionUpdateManagementPage", wVersionUpdateManagement);
        }

        return new ModelAndView("ssb/warmline/business/wversionupdatemanagement/wVersionUpdateManagement-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(WVersionUpdateManagementEntity wVersionUpdateManagement, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wVersionUpdateManagement.getId())) {
            wVersionUpdateManagement = (WVersionUpdateManagementEntity)this.wVersionUpdateManagementService.getEntity(WVersionUpdateManagementEntity.class, wVersionUpdateManagement.getId());
            req.setAttribute("wVersionUpdateManagementPage", wVersionUpdateManagement);
        }

        return new ModelAndView("ssb/warmline/business/wversionupdatemanagement/wVersionUpdateManagement-update");
    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "wVersionUpdateManagementController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(WVersionUpdateManagementEntity wVersionUpdateManagement, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(WVersionUpdateManagementEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wVersionUpdateManagement, request.getParameterMap());
        List<WVersionUpdateManagementEntity> wVersionUpdateManagements = this.wVersionUpdateManagementService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "w_version_update_management");
        modelMap.put("entity", WVersionUpdateManagementEntity.class);
        modelMap.put("params", new ExportParams("w_version_update_management列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", wVersionUpdateManagements);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByT"}
    )
    public String exportXlsByT(WVersionUpdateManagementEntity wVersionUpdateManagement, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "w_version_update_management");
        modelMap.put("entity", WVersionUpdateManagementEntity.class);
        modelMap.put("params", new ExportParams("w_version_update_management列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
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
                List<WVersionUpdateManagementEntity> listWVersionUpdateManagementEntitys = ExcelImportUtil.importExcel(file.getInputStream(), WVersionUpdateManagementEntity.class, params);
                Iterator var12 = listWVersionUpdateManagementEntitys.iterator();

                while(var12.hasNext()) {
                    WVersionUpdateManagementEntity wVersionUpdateManagement = (WVersionUpdateManagementEntity)var12.next();
                    this.wVersionUpdateManagementService.save(wVersionUpdateManagement);
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
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public List<WVersionUpdateManagementEntity> list() {
        List<WVersionUpdateManagementEntity> listWVersionUpdateManagements = this.wVersionUpdateManagementService.getList(WVersionUpdateManagementEntity.class);
        return listWVersionUpdateManagements;
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        WVersionUpdateManagementEntity task = (WVersionUpdateManagementEntity)this.wVersionUpdateManagementService.get(WVersionUpdateManagementEntity.class, id);
        return task == null?new ResponseEntity(HttpStatus.NOT_FOUND):new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(
            method = {RequestMethod.POST},
            consumes = {"application/json"}
    )
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody WVersionUpdateManagementEntity wVersionUpdateManagement, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<WVersionUpdateManagementEntity>> failures = this.validator.validate(wVersionUpdateManagement, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wVersionUpdateManagementService.save(wVersionUpdateManagement);
            String id = wVersionUpdateManagement.getId();
            URI uri = uriBuilder.path("/rest/wVersionUpdateManagementController/" + id).build().toUri();
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uri);
            return new ResponseEntity(headers, HttpStatus.CREATED);
        }
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.PUT},
            consumes = {"application/json"}
    )
    public ResponseEntity<?> update(@RequestBody WVersionUpdateManagementEntity wVersionUpdateManagement) {
        Set<ConstraintViolation<WVersionUpdateManagementEntity>> failures = this.validator.validate(wVersionUpdateManagement, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wVersionUpdateManagementService.saveOrUpdate(wVersionUpdateManagement);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.DELETE}
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.wVersionUpdateManagementService.deleteEntityById(WVersionUpdateManagementEntity.class, id);
    }

    @RequestMapping(
            params = {"uploadPic"}
    )
    @ResponseBody
    public AjaxJson uploadPic(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        String path_temp = request.getSession().getServletContext().getRealPath("");
        String path = path_temp.substring(0, path_temp.lastIndexOf(separator));
        String imgPath = separator + "upload" + separator + "files";
        String filePath = path + imgPath;
        File fileDir = new File(filePath);
        if(!fileDir.exists()) {
            fileDir.mkdirs();
        }

        File picTempFile = null;
        Iterator var13 = fileMap.entrySet().iterator();

        while(var13.hasNext()) {
            Entry<String, MultipartFile> entity = (Entry)var13.next();
            MultipartFile file = (MultipartFile)entity.getValue();
            String fileNametemp = FileUtils.getExtend(file.getOriginalFilename());
            if(!"".equals(fileNametemp)) {
                String fileName = DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(8) + "." + FileUtils.getExtend(file.getOriginalFilename());
                picTempFile = new File(fileDir, fileName);

                try {
                    if(picTempFile.exists()) {
                        org.apache.commons.io.FileUtils.forceDelete(picTempFile);
                    }

                    FileCopyUtils.copy(file.getBytes(), picTempFile);
                } catch (Exception var20) {
                    var20.printStackTrace();
                    j.setMsg("缩略图上传失败！");
                    j.setSuccess(false);
                }

                String domain = request.getServerName();
                String picname = "http://" + domain + "/upload/files/" + picTempFile.getName();
                j.setObj(picname);
                Map nameMap = new HashMap();
                nameMap.put("name", "/upload/files/" + picTempFile.getName());
                j.setAttributes(nameMap);
            }

            j.setMsg("缩略图上传成功！");
            j.setSuccess(true);
        }

        return j;
    }
}
