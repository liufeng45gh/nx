//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.controller.mobileloginlog;

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
import ssb.warmline.business.entity.mobileloginlog.MobileLoginLogEntity;
import ssb.warmline.business.service.mobileloginlog.MobileLoginLogServiceI;

@Controller
@RequestMapping({"/mobileLoginLogController"})
public class MobileLoginLogController extends BaseController {
    private static final Logger logger = Logger.getLogger(MobileLoginLogController.class);
    @Autowired
    private MobileLoginLogServiceI mobileLoginLogService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    public MobileLoginLogController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("ssb/warmline/business/mobileloginlog/mobileLoginLogList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(MobileLoginLogEntity mobileLoginLog, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(MobileLoginLogEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, mobileLoginLog, request.getParameterMap());
        cq.add();
        this.mobileLoginLogService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(MobileLoginLogEntity mobileLoginLog, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        mobileLoginLog = (MobileLoginLogEntity)this.systemService.getEntity(MobileLoginLogEntity.class, mobileLoginLog.getId());
        message = "mobile_login_log删除成功";

        try {
            this.mobileLoginLogService.delete(mobileLoginLog);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "mobile_login_log删除失败";
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
        message = "mobile_login_log删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                MobileLoginLogEntity mobileLoginLog = (MobileLoginLogEntity)this.systemService.getEntity(MobileLoginLogEntity.class, id);
                this.mobileLoginLogService.delete(mobileLoginLog);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "mobile_login_log删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(MobileLoginLogEntity mobileLoginLog, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "mobile_login_log添加成功";

        try {
            this.mobileLoginLogService.save(mobileLoginLog);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "mobile_login_log添加失败";
            throw new BusinessException(var6.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(MobileLoginLogEntity mobileLoginLog, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "mobile_login_log更新成功";
        MobileLoginLogEntity t = (MobileLoginLogEntity)this.mobileLoginLogService.get(MobileLoginLogEntity.class, mobileLoginLog.getId());

        try {
            MyBeanUtils.copyBeanNotNull2Bean(mobileLoginLog, t);
            this.mobileLoginLogService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var7) {
            var7.printStackTrace();
            message = "mobile_login_log更新失败";
            throw new BusinessException(var7.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(MobileLoginLogEntity mobileLoginLog, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(mobileLoginLog.getId())) {
            mobileLoginLog = (MobileLoginLogEntity)this.mobileLoginLogService.getEntity(MobileLoginLogEntity.class, mobileLoginLog.getId());
            req.setAttribute("mobileLoginLogPage", mobileLoginLog);
        }

        return new ModelAndView("ssb/warmline/business/mobileloginlog/mobileLoginLog-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(MobileLoginLogEntity mobileLoginLog, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(mobileLoginLog.getId())) {
            mobileLoginLog = (MobileLoginLogEntity)this.mobileLoginLogService.getEntity(MobileLoginLogEntity.class, mobileLoginLog.getId());
            req.setAttribute("mobileLoginLogPage", mobileLoginLog);
        }

        return new ModelAndView("ssb/warmline/business/mobileloginlog/mobileLoginLog-update");
    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "mobileLoginLogController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(MobileLoginLogEntity mobileLoginLog, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(MobileLoginLogEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, mobileLoginLog, request.getParameterMap());
        List<MobileLoginLogEntity> mobileLoginLogs = this.mobileLoginLogService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "mobile_login_log");
        modelMap.put("entity", MobileLoginLogEntity.class);
        modelMap.put("params", new ExportParams("mobile_login_log列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", mobileLoginLogs);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByT"}
    )
    public String exportXlsByT(MobileLoginLogEntity mobileLoginLog, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "mobile_login_log");
        modelMap.put("entity", MobileLoginLogEntity.class);
        modelMap.put("params", new ExportParams("mobile_login_log列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
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
                List<MobileLoginLogEntity> listMobileLoginLogEntitys = ExcelImportUtil.importExcel(file.getInputStream(), MobileLoginLogEntity.class, params);
                Iterator var12 = listMobileLoginLogEntitys.iterator();

                while(var12.hasNext()) {
                    MobileLoginLogEntity mobileLoginLog = (MobileLoginLogEntity)var12.next();
                    this.mobileLoginLogService.save(mobileLoginLog);
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
    public List<MobileLoginLogEntity> list() {
        List<MobileLoginLogEntity> listMobileLoginLogs = this.mobileLoginLogService.getList(MobileLoginLogEntity.class);
        return listMobileLoginLogs;
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        MobileLoginLogEntity task = (MobileLoginLogEntity)this.mobileLoginLogService.get(MobileLoginLogEntity.class, id);
        return task == null?new ResponseEntity(HttpStatus.NOT_FOUND):new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(
            method = {RequestMethod.POST},
            consumes = {"application/json"}
    )
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody MobileLoginLogEntity mobileLoginLog, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<MobileLoginLogEntity>> failures = this.validator.validate(mobileLoginLog, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.mobileLoginLogService.save(mobileLoginLog);
            String id = mobileLoginLog.getId();
            URI uri = uriBuilder.path("/rest/mobileLoginLogController/" + id).build().toUri();
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
    public ResponseEntity<?> update(@RequestBody MobileLoginLogEntity mobileLoginLog) {
        Set<ConstraintViolation<MobileLoginLogEntity>> failures = this.validator.validate(mobileLoginLog, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.mobileLoginLogService.saveOrUpdate(mobileLoginLog);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.DELETE}
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.mobileLoginLogService.deleteEntityById(MobileLoginLogEntity.class, id);
    }
}
