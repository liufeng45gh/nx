//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.controller.config;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
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
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.cgform.entity.config.CgFormIndexEntity;
import org.jeecgframework.web.cgform.service.config.CgFormIndexServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

@Controller
@RequestMapping({"/cgFormIndexController"})
public class CgFormIndexController extends BaseController {
    private static final Logger logger = Logger.getLogger(CgFormIndexController.class);
    @Autowired
    private CgFormIndexServiceI cgFormIndexService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    public CgFormIndexController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/jeecg/index/cgFormIndexList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(CgFormIndexEntity cgFormIndex, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(CgFormIndexEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, cgFormIndex, request.getParameterMap());
        cq.add();
        this.cgFormIndexService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(CgFormIndexEntity cgFormIndex, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        cgFormIndex = (CgFormIndexEntity)this.systemService.getEntity(CgFormIndexEntity.class, cgFormIndex.getId());
        message = "索引表删除成功";

        try {
            this.cgFormIndexService.delete(cgFormIndex);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "索引表删除失败";
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
        message = "索引表删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                CgFormIndexEntity cgFormIndex = (CgFormIndexEntity)this.systemService.getEntity(CgFormIndexEntity.class, id);
                this.cgFormIndexService.delete(cgFormIndex);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "索引表删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(CgFormIndexEntity cgFormIndex, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "索引表添加成功";

        try {
            this.cgFormIndexService.save(cgFormIndex);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "索引表添加失败";
            throw new BusinessException(var6.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(CgFormIndexEntity cgFormIndex, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "索引表更新成功";
        CgFormIndexEntity t = (CgFormIndexEntity)this.cgFormIndexService.get(CgFormIndexEntity.class, cgFormIndex.getId());

        try {
            MyBeanUtils.copyBeanNotNull2Bean(cgFormIndex, t);
            this.cgFormIndexService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var7) {
            var7.printStackTrace();
            message = "索引表更新失败";
            throw new BusinessException(var7.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(CgFormIndexEntity cgFormIndex, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(cgFormIndex.getId())) {
            cgFormIndex = (CgFormIndexEntity)this.cgFormIndexService.getEntity(CgFormIndexEntity.class, cgFormIndex.getId());
            req.setAttribute("cgFormIndexPage", cgFormIndex);
        }

        return new ModelAndView("com/jeecg/index/cgFormIndex-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(CgFormIndexEntity cgFormIndex, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(cgFormIndex.getId())) {
            cgFormIndex = (CgFormIndexEntity)this.cgFormIndexService.getEntity(CgFormIndexEntity.class, cgFormIndex.getId());
            req.setAttribute("cgFormIndexPage", cgFormIndex);
        }

        return new ModelAndView("com/jeecg/index/cgFormIndex-update");
    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "cgFormIndexController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(CgFormIndexEntity cgFormIndex, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(CgFormIndexEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, cgFormIndex, request.getParameterMap());
        List<CgFormIndexEntity> cgFormIndexs = this.cgFormIndexService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "索引表");
        modelMap.put("entity", CgFormIndexEntity.class);
        modelMap.put("params", new ExportParams("索引表列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", cgFormIndexs);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByT"}
    )
    public String exportXlsByT(CgFormIndexEntity cgFormIndex, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "索引表");
        modelMap.put("entity", CgFormIndexEntity.class);
        modelMap.put("params", new ExportParams("索引表列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
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
                List<CgFormIndexEntity> listCgFormIndexEntitys = ExcelImportUtil.importExcel(file.getInputStream(), CgFormIndexEntity.class, params);
                Iterator var12 = listCgFormIndexEntitys.iterator();

                while(var12.hasNext()) {
                    CgFormIndexEntity cgFormIndex = (CgFormIndexEntity)var12.next();
                    this.cgFormIndexService.save(cgFormIndex);
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
    public List<CgFormIndexEntity> list() {
        List<CgFormIndexEntity> listCgFormIndexs = this.cgFormIndexService.getList(CgFormIndexEntity.class);
        return listCgFormIndexs;
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        CgFormIndexEntity task = (CgFormIndexEntity)this.cgFormIndexService.get(CgFormIndexEntity.class, id);
        return task == null?new ResponseEntity(HttpStatus.NOT_FOUND):new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(
            method = {RequestMethod.POST},
            consumes = {"application/json"}
    )
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody CgFormIndexEntity cgFormIndex, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<CgFormIndexEntity>> failures = this.validator.validate(cgFormIndex, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.cgFormIndexService.save(cgFormIndex);
            String id = cgFormIndex.getId();
            URI uri = uriBuilder.path("/rest/cgFormIndexController/" + id).build().toUri();
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
    public ResponseEntity<?> update(@RequestBody CgFormIndexEntity cgFormIndex) {
        Set<ConstraintViolation<CgFormIndexEntity>> failures = this.validator.validate(cgFormIndex, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.cgFormIndexService.saveOrUpdate(cgFormIndex);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.DELETE}
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.cgFormIndexService.deleteEntityById(CgFormIndexEntity.class, id);
    }

    @RequestMapping(
            params = {"getIndexList"}
    )
    @ResponseBody
    public List<CgFormIndexEntity> getColumnList(CgFormIndexEntity cgFormHead, String type, HttpServletRequest req) {
        List<CgFormIndexEntity> columnList = new ArrayList();
        if(StringUtil.isNotEmpty(cgFormHead.getId())) {
            CriteriaQuery cq = new CriteriaQuery(CgFormIndexEntity.class);
            cq.eq("table.id", cgFormHead.getId());
            cq.add();
            columnList = this.cgFormIndexService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        }

        return (List)columnList;
    }
}
