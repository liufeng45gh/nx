//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.controller.wcommission;

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
import ssb.warmline.business.entity.wcommission.WCommissionEntity;
import ssb.warmline.business.service.wcommission.WCommissionServiceI;

@Controller
@RequestMapping({"/wCommissionController"})
public class WCommissionController extends BaseController {
    private static final Logger logger = Logger.getLogger(WCommissionController.class);
    @Autowired
    private WCommissionServiceI wCommissionService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    public WCommissionController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("ssb/warmline/business/wcommission/wCommissionList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(WCommissionEntity wCommission, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WCommissionEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wCommission, request.getParameterMap());
        cq.add();
        this.wCommissionService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(WCommissionEntity wCommission, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        wCommission = (WCommissionEntity)this.systemService.getEntity(WCommissionEntity.class, wCommission.getId());
        message = "佣金删除成功";

        try {
            this.wCommissionService.delete(wCommission);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "佣金删除失败";
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
        message = "佣金删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                WCommissionEntity wCommission = (WCommissionEntity)this.systemService.getEntity(WCommissionEntity.class, id);
                this.wCommissionService.delete(wCommission);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "佣金删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(WCommissionEntity wCommission, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "佣金添加成功";

        try {
            this.wCommissionService.save(wCommission);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "佣金添加失败";
            throw new BusinessException(var6.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(WCommissionEntity wCommission, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "佣金更新成功";
        WCommissionEntity t = (WCommissionEntity)this.wCommissionService.get(WCommissionEntity.class, wCommission.getId());

        try {
            MyBeanUtils.copyBeanNotNull2Bean(wCommission, t);
            this.wCommissionService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var7) {
            var7.printStackTrace();
            message = "佣金更新失败";
            throw new BusinessException(var7.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(WCommissionEntity wCommission, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wCommission.getId())) {
            wCommission = (WCommissionEntity)this.wCommissionService.getEntity(WCommissionEntity.class, wCommission.getId());
            req.setAttribute("wCommissionPage", wCommission);
        }

        return new ModelAndView("ssb/warmline/business/wcommission/wCommission-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(WCommissionEntity wCommission, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wCommission.getId())) {
            wCommission = (WCommissionEntity)this.wCommissionService.getEntity(WCommissionEntity.class, wCommission.getId());
            req.setAttribute("wCommissionPage", wCommission);
        }

        return new ModelAndView("ssb/warmline/business/wcommission/wCommission-update");
    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "wCommissionController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(WCommissionEntity wCommission, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(WCommissionEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wCommission, request.getParameterMap());
        List<WCommissionEntity> wCommissions = this.wCommissionService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "佣金");
        modelMap.put("entity", WCommissionEntity.class);
        modelMap.put("params", new ExportParams("佣金列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", wCommissions);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByT"}
    )
    public String exportXlsByT(WCommissionEntity wCommission, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "佣金");
        modelMap.put("entity", WCommissionEntity.class);
        modelMap.put("params", new ExportParams("佣金列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
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
                List<WCommissionEntity> listWCommissionEntitys = ExcelImportUtil.importExcel(file.getInputStream(), WCommissionEntity.class, params);
                Iterator var12 = listWCommissionEntitys.iterator();

                while(var12.hasNext()) {
                    WCommissionEntity wCommission = (WCommissionEntity)var12.next();
                    this.wCommissionService.save(wCommission);
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
    public List<WCommissionEntity> list() {
        List<WCommissionEntity> listWCommissions = this.wCommissionService.getList(WCommissionEntity.class);
        return listWCommissions;
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        WCommissionEntity task = (WCommissionEntity)this.wCommissionService.get(WCommissionEntity.class, id);
        return task == null?new ResponseEntity(HttpStatus.NOT_FOUND):new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(
            method = {RequestMethod.POST},
            consumes = {"application/json"}
    )
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody WCommissionEntity wCommission, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<WCommissionEntity>> failures = this.validator.validate(wCommission, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wCommissionService.save(wCommission);
            String id = wCommission.getId();
            URI uri = uriBuilder.path("/rest/wCommissionController/" + id).build().toUri();
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
    public ResponseEntity<?> update(@RequestBody WCommissionEntity wCommission) {
        Set<ConstraintViolation<WCommissionEntity>> failures = this.validator.validate(wCommission, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wCommissionService.saveOrUpdate(wCommission);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.DELETE}
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.wCommissionService.deleteEntityById(WCommissionEntity.class, id);
    }
}
