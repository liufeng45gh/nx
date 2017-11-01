//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.controller.wphonecode;

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
import ssb.warmline.business.entity.wphonecode.WPhoneCodeEntity;
import ssb.warmline.business.service.wphonecode.WPhoneCodeServiceI;

@Controller
@RequestMapping({"/wPhoneCodeController"})
public class WPhoneCodeController extends BaseController {
    private static final Logger logger = Logger.getLogger(WPhoneCodeController.class);
    @Autowired
    private WPhoneCodeServiceI wPhoneCodeService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    public WPhoneCodeController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("ssb/warmline/business/wphonecode/wPhoneCodeList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(WPhoneCodeEntity wPhoneCode, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WPhoneCodeEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wPhoneCode, request.getParameterMap());
        cq.add();
        this.wPhoneCodeService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(WPhoneCodeEntity wPhoneCode, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        wPhoneCode = (WPhoneCodeEntity)this.systemService.getEntity(WPhoneCodeEntity.class, wPhoneCode.getId());
        message = "w_phone_code删除成功";

        try {
            this.wPhoneCodeService.delete(wPhoneCode);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "w_phone_code删除失败";
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
        message = "w_phone_code删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                WPhoneCodeEntity wPhoneCode = (WPhoneCodeEntity)this.systemService.getEntity(WPhoneCodeEntity.class, id);
                this.wPhoneCodeService.delete(wPhoneCode);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "w_phone_code删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(WPhoneCodeEntity wPhoneCode, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "w_phone_code添加成功";

        try {
            this.wPhoneCodeService.save(wPhoneCode);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "w_phone_code添加失败";
            throw new BusinessException(var6.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(WPhoneCodeEntity wPhoneCode, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "w_phone_code更新成功";
        WPhoneCodeEntity t = (WPhoneCodeEntity)this.wPhoneCodeService.get(WPhoneCodeEntity.class, wPhoneCode.getId());

        try {
            MyBeanUtils.copyBeanNotNull2Bean(wPhoneCode, t);
            this.wPhoneCodeService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var7) {
            var7.printStackTrace();
            message = "w_phone_code更新失败";
            throw new BusinessException(var7.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(WPhoneCodeEntity wPhoneCode, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wPhoneCode.getId())) {
            wPhoneCode = (WPhoneCodeEntity)this.wPhoneCodeService.getEntity(WPhoneCodeEntity.class, wPhoneCode.getId());
            req.setAttribute("wPhoneCodePage", wPhoneCode);
        }

        return new ModelAndView("ssb/warmline/business/wphonecode/wPhoneCode-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(WPhoneCodeEntity wPhoneCode, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wPhoneCode.getId())) {
            wPhoneCode = (WPhoneCodeEntity)this.wPhoneCodeService.getEntity(WPhoneCodeEntity.class, wPhoneCode.getId());
            req.setAttribute("wPhoneCodePage", wPhoneCode);
        }

        return new ModelAndView("ssb/warmline/business/wphonecode/wPhoneCode-update");
    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "wPhoneCodeController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(WPhoneCodeEntity wPhoneCode, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(WPhoneCodeEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wPhoneCode, request.getParameterMap());
        List<WPhoneCodeEntity> wPhoneCodes = this.wPhoneCodeService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "w_phone_code");
        modelMap.put("entity", WPhoneCodeEntity.class);
        modelMap.put("params", new ExportParams("w_phone_code列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", wPhoneCodes);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByT"}
    )
    public String exportXlsByT(WPhoneCodeEntity wPhoneCode, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "w_phone_code");
        modelMap.put("entity", WPhoneCodeEntity.class);
        modelMap.put("params", new ExportParams("w_phone_code列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
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
                List<WPhoneCodeEntity> listWPhoneCodeEntitys = ExcelImportUtil.importExcel(file.getInputStream(), WPhoneCodeEntity.class, params);
                Iterator var12 = listWPhoneCodeEntitys.iterator();

                while(var12.hasNext()) {
                    WPhoneCodeEntity wPhoneCode = (WPhoneCodeEntity)var12.next();
                    this.wPhoneCodeService.save(wPhoneCode);
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
    public List<WPhoneCodeEntity> list() {
        List<WPhoneCodeEntity> listWPhoneCodes = this.wPhoneCodeService.getList(WPhoneCodeEntity.class);
        return listWPhoneCodes;
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        WPhoneCodeEntity task = (WPhoneCodeEntity)this.wPhoneCodeService.get(WPhoneCodeEntity.class, id);
        return task == null?new ResponseEntity(HttpStatus.NOT_FOUND):new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(
            method = {RequestMethod.POST},
            consumes = {"application/json"}
    )
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody WPhoneCodeEntity wPhoneCode, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<WPhoneCodeEntity>> failures = this.validator.validate(wPhoneCode, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wPhoneCodeService.save(wPhoneCode);
            String id = wPhoneCode.getId();
            URI uri = uriBuilder.path("/rest/wPhoneCodeController/" + id).build().toUri();
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
    public ResponseEntity<?> update(@RequestBody WPhoneCodeEntity wPhoneCode) {
        Set<ConstraintViolation<WPhoneCodeEntity>> failures = this.validator.validate(wPhoneCode, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wPhoneCodeService.saveOrUpdate(wPhoneCode);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.DELETE}
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.wPhoneCodeService.deleteEntityById(WPhoneCodeEntity.class, id);
    }
}
