//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.controller.wcapital;

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
import ssb.warmline.business.entity.wcapital.WCapitalEntity;
import ssb.warmline.business.entity.wcashaccount.WCashAccountEntity;
import ssb.warmline.business.service.wcapital.WCapitalServiceI;

@Controller
@RequestMapping({"/wCapitalController"})
public class WCapitalController extends BaseController {
    private static final Logger logger = Logger.getLogger(WCapitalController.class);
    @Autowired
    private WCapitalServiceI wCapitalService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    public WCapitalController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("ssb/warmline/business/wcapital/wCapitalList");
    }

    @RequestMapping(
            params = {"WithdrawalsList"}
    )
    public ModelAndView WithdrawalsList(HttpServletRequest request) {
        return new ModelAndView("ssb/warmline/business/wcapital/WithdrawalsList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(WCapitalEntity wCapital, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WCapitalEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wCapital, request.getParameterMap());
        cq.add();
        this.wCapitalService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"withdDatagrid"}
    )
    public void withdDatagrid(WCapitalEntity wCapital, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WCapitalEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wCapital, request.getParameterMap());

        try {
            cq.eq("type", "Withdrawals");
        } catch (Exception var13) {
            throw new BusinessException(var13.getMessage());
        }

        cq.add();
        this.wCapitalService.getDataGridReturn(cq, true);
        List<WCapitalEntity> results = dataGrid.getResults();
        List<WCapitalEntity> resultss = new ArrayList();

        for(int i = 0; i < results.size(); ++i) {
            wCapital = (WCapitalEntity)results.get(i);
            String userId = ((WCapitalEntity)results.get(i)).getUserId();
            List<WCashAccountEntity> cashAccountEntity = this.systemService.findByProperty(WCashAccountEntity.class, "userId", userId);
            if(cashAccountEntity.size() > 0) {
                Iterator var12 = cashAccountEntity.iterator();

                while(var12.hasNext()) {
                    WCashAccountEntity cashAccount = (WCashAccountEntity)var12.next();
                    wCapital.setRealName(cashAccount.getRealName());
                }
            }

            resultss.add(wCapital);
        }

        dataGrid.setResults(resultss);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(WCapitalEntity wCapital, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        wCapital = (WCapitalEntity)this.systemService.getEntity(WCapitalEntity.class, wCapital.getId());
        message = "资金流水删除成功";

        try {
            this.wCapitalService.delete(wCapital);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "资金流水删除失败";
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
        message = "资金流水删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                WCapitalEntity wCapital = (WCapitalEntity)this.systemService.getEntity(WCapitalEntity.class, id);
                this.wCapitalService.delete(wCapital);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "资金流水删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(WCapitalEntity wCapital, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "资金流水添加成功";

        try {
            this.wCapitalService.save(wCapital);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "资金流水添加失败";
            throw new BusinessException(var6.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(WCapitalEntity wCapital, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "资金流水更新成功";
        WCapitalEntity t = (WCapitalEntity)this.wCapitalService.get(WCapitalEntity.class, wCapital.getId());

        try {
            MyBeanUtils.copyBeanNotNull2Bean(wCapital, t);
            this.wCapitalService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var7) {
            var7.printStackTrace();
            message = "资金流水更新失败";
            throw new BusinessException(var7.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(WCapitalEntity wCapital, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wCapital.getId())) {
            wCapital = (WCapitalEntity)this.wCapitalService.getEntity(WCapitalEntity.class, wCapital.getId());
            req.setAttribute("wCapitalPage", wCapital);
        }

        return new ModelAndView("ssb/warmline/business/wcapital/wCapital-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(WCapitalEntity wCapital, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wCapital.getId())) {
            wCapital = (WCapitalEntity)this.wCapitalService.getEntity(WCapitalEntity.class, wCapital.getId());
            req.setAttribute("wCapitalPage", wCapital);
        }

        return new ModelAndView("ssb/warmline/business/wcapital/wCapital-update");
    }

    @RequestMapping(
            params = {"goUpdates"}
    )
    public ModelAndView goUpdates(WCapitalEntity wCapital, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wCapital.getId())) {
            wCapital = (WCapitalEntity)this.wCapitalService.getEntity(WCapitalEntity.class, wCapital.getId());
            req.setAttribute("wCapitalPage", wCapital);
        }

        return new ModelAndView("ssb/warmline/business/wcapital/withdrawals-update");
    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "wCapitalController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(WCapitalEntity wCapital, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(WCapitalEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wCapital, request.getParameterMap());
        List<WCapitalEntity> wCapitals = this.wCapitalService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "资金流水");
        modelMap.put("entity", WCapitalEntity.class);
        modelMap.put("params", new ExportParams("资金流水列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", wCapitals);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByT"}
    )
    public String exportXlsByT(WCapitalEntity wCapital, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "资金流水");
        modelMap.put("entity", WCapitalEntity.class);
        modelMap.put("params", new ExportParams("资金流水列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
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
                List<WCapitalEntity> listWCapitalEntitys = ExcelImportUtil.importExcel(file.getInputStream(), WCapitalEntity.class, params);
                Iterator var12 = listWCapitalEntitys.iterator();

                while(var12.hasNext()) {
                    WCapitalEntity wCapital = (WCapitalEntity)var12.next();
                    this.wCapitalService.save(wCapital);
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
    public List<WCapitalEntity> list() {
        List<WCapitalEntity> listWCapitals = this.wCapitalService.getList(WCapitalEntity.class);
        return listWCapitals;
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        WCapitalEntity task = (WCapitalEntity)this.wCapitalService.get(WCapitalEntity.class, id);
        return task == null?new ResponseEntity(HttpStatus.NOT_FOUND):new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(
            method = {RequestMethod.POST},
            consumes = {"application/json"}
    )
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody WCapitalEntity wCapital, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<WCapitalEntity>> failures = this.validator.validate(wCapital, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wCapitalService.save(wCapital);
            String id = wCapital.getId();
            URI uri = uriBuilder.path("/rest/wCapitalController/" + id).build().toUri();
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
    public ResponseEntity<?> update(@RequestBody WCapitalEntity wCapital) {
        Set<ConstraintViolation<WCapitalEntity>> failures = this.validator.validate(wCapital, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wCapitalService.saveOrUpdate(wCapital);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.DELETE}
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.wCapitalService.deleteEntityById(WCapitalEntity.class, id);
    }

    @RequestMapping(
            params = {"invoice"}
    )
    public ModelAndView invoice(WCapitalEntity WCapitalEntity, HttpServletRequest request) {
        String wCapitalId = request.getParameter("wCapitalId");
        if(StringUtil.isNotEmpty(wCapitalId)) {
            WCapitalEntity = (WCapitalEntity)this.wCapitalService.getEntity(WCapitalEntity.class, wCapitalId);
            if("".equals(WCapitalEntity.getApprovalType())) {
                if("alipay".equals(WCapitalEntity.getTransferType())) {
                    WCapitalEntity.setTransferType("支付宝");
                }

                if("weChat".equals(WCapitalEntity.getTransferType())) {
                    WCapitalEntity.setTransferType("微信");
                }

                if("bank".equals(WCapitalEntity.getTransferType())) {
                    WCapitalEntity.setTransferType("银行卡");
                }

                request.setAttribute("wCapitalPage", WCapitalEntity);
            } else {
                if("alipay".equals(WCapitalEntity.getTransferType())) {
                    WCapitalEntity.setTransferType("支付宝");
                }

                if("weChat".equals(WCapitalEntity.getTransferType())) {
                    WCapitalEntity.setTransferType("微信");
                }

                if("bank".equals(WCapitalEntity.getTransferType())) {
                    WCapitalEntity.setTransferType("银行卡");
                }

                request.setAttribute("wCapitalPage", WCapitalEntity);
            }
        } else {
            if("alipay".equals(WCapitalEntity.getTransferType())) {
                WCapitalEntity.setTransferType("支付宝");
            }

            if("weChat".equals(WCapitalEntity.getTransferType())) {
                WCapitalEntity.setTransferType("微信");
            }

            if("bank".equals(WCapitalEntity.getTransferType())) {
                WCapitalEntity.setTransferType("银行卡");
            }

            request.setAttribute("wCapitalPage", WCapitalEntity);
        }

        return new ModelAndView("ssb/warmline/business/wcapital/withdrawals-update2");
    }
}
