//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.controller.worderrecord;

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
import ssb.warmline.business.entity.worderrecord.WOrderRecordEntity;
import ssb.warmline.business.service.worderrecord.WOrderRecordServiceI;

@Controller
@RequestMapping({"/wOrderRecordController"})
public class WOrderRecordController extends BaseController {
    private static final Logger logger = Logger.getLogger(WOrderRecordController.class);
    @Autowired
    private WOrderRecordServiceI wOrderRecordService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    public WOrderRecordController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("ssb/warmline/business/worderrecord/wOrderRecordList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(WOrderRecordEntity wOrderRecord, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WOrderRecordEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wOrderRecord, request.getParameterMap());
        cq.add();
        this.wOrderRecordService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(WOrderRecordEntity wOrderRecord, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        wOrderRecord = (WOrderRecordEntity)this.systemService.getEntity(WOrderRecordEntity.class, wOrderRecord.getId());
        message = "订单日志删除成功";

        try {
            this.wOrderRecordService.delete(wOrderRecord);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "订单日志删除失败";
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
        message = "订单日志删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                WOrderRecordEntity wOrderRecord = (WOrderRecordEntity)this.systemService.getEntity(WOrderRecordEntity.class, id);
                this.wOrderRecordService.delete(wOrderRecord);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "订单日志删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(WOrderRecordEntity wOrderRecord, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "订单日志添加成功";

        try {
            this.wOrderRecordService.save(wOrderRecord);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "订单日志添加失败";
            throw new BusinessException(var6.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(WOrderRecordEntity wOrderRecord, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "订单日志更新成功";
        WOrderRecordEntity t = (WOrderRecordEntity)this.wOrderRecordService.get(WOrderRecordEntity.class, wOrderRecord.getId());

        try {
            MyBeanUtils.copyBeanNotNull2Bean(wOrderRecord, t);
            this.wOrderRecordService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var7) {
            var7.printStackTrace();
            message = "订单日志更新失败";
            throw new BusinessException(var7.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(WOrderRecordEntity wOrderRecord, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wOrderRecord.getId())) {
            wOrderRecord = (WOrderRecordEntity)this.wOrderRecordService.getEntity(WOrderRecordEntity.class, wOrderRecord.getId());
            req.setAttribute("wOrderRecordPage", wOrderRecord);
        }

        return new ModelAndView("ssb/warmline/business/worderrecord/wOrderRecord-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(WOrderRecordEntity wOrderRecord, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wOrderRecord.getId())) {
            wOrderRecord = (WOrderRecordEntity)this.wOrderRecordService.getEntity(WOrderRecordEntity.class, wOrderRecord.getId());
            req.setAttribute("wOrderRecordPage", wOrderRecord);
        }

        return new ModelAndView("ssb/warmline/business/worderrecord/wOrderRecord-update");
    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "wOrderRecordController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(WOrderRecordEntity wOrderRecord, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(WOrderRecordEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wOrderRecord, request.getParameterMap());
        List<WOrderRecordEntity> wOrderRecords = this.wOrderRecordService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "订单日志");
        modelMap.put("entity", WOrderRecordEntity.class);
        modelMap.put("params", new ExportParams("订单日志列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", wOrderRecords);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByT"}
    )
    public String exportXlsByT(WOrderRecordEntity wOrderRecord, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "订单日志");
        modelMap.put("entity", WOrderRecordEntity.class);
        modelMap.put("params", new ExportParams("订单日志列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
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
                List<WOrderRecordEntity> listWOrderRecordEntitys = ExcelImportUtil.importExcel(file.getInputStream(), WOrderRecordEntity.class, params);
                Iterator var12 = listWOrderRecordEntitys.iterator();

                while(var12.hasNext()) {
                    WOrderRecordEntity wOrderRecord = (WOrderRecordEntity)var12.next();
                    this.wOrderRecordService.save(wOrderRecord);
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
    public List<WOrderRecordEntity> list() {
        List<WOrderRecordEntity> listWOrderRecords = this.wOrderRecordService.getList(WOrderRecordEntity.class);
        return listWOrderRecords;
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        WOrderRecordEntity task = (WOrderRecordEntity)this.wOrderRecordService.get(WOrderRecordEntity.class, id);
        return task == null?new ResponseEntity(HttpStatus.NOT_FOUND):new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(
            method = {RequestMethod.POST},
            consumes = {"application/json"}
    )
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody WOrderRecordEntity wOrderRecord, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<WOrderRecordEntity>> failures = this.validator.validate(wOrderRecord, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wOrderRecordService.save(wOrderRecord);
            String id = wOrderRecord.getId();
            URI uri = uriBuilder.path("/rest/wOrderRecordController/" + id).build().toUri();
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
    public ResponseEntity<?> update(@RequestBody WOrderRecordEntity wOrderRecord) {
        Set<ConstraintViolation<WOrderRecordEntity>> failures = this.validator.validate(wOrderRecord, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wOrderRecordService.saveOrUpdate(wOrderRecord);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.DELETE}
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.wOrderRecordService.deleteEntityById(WOrderRecordEntity.class, id);
    }
}
