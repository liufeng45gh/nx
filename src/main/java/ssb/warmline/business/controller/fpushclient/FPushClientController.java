//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.controller.fpushclient;

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
import ssb.warmline.business.entity.fpushclient.FPushClientEntity;
import ssb.warmline.business.service.fpushclient.FPushClientServiceI;

@Controller
@RequestMapping({"/fPushClientController"})
public class FPushClientController extends BaseController {
    private static final Logger logger = Logger.getLogger(FPushClientController.class);
    @Autowired
    private FPushClientServiceI fPushClientService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    public FPushClientController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("ssb/warmline/business/fpushclient/fPushClientList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(FPushClientEntity fPushClient, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(FPushClientEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, fPushClient, request.getParameterMap());
        cq.add();
        this.fPushClientService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(FPushClientEntity fPushClient, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        fPushClient = (FPushClientEntity)this.systemService.getEntity(FPushClientEntity.class, fPushClient.getId());
        message = "f_push_client删除成功";

        try {
            this.fPushClientService.delete(fPushClient);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "f_push_client删除失败";
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
        message = "f_push_client删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                FPushClientEntity fPushClient = (FPushClientEntity)this.systemService.getEntity(FPushClientEntity.class, id);
                this.fPushClientService.delete(fPushClient);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "f_push_client删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(FPushClientEntity fPushClient, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "f_push_client添加成功";

        try {
            this.fPushClientService.save(fPushClient);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "f_push_client添加失败";
            throw new BusinessException(var6.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(FPushClientEntity fPushClient, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "f_push_client更新成功";
        FPushClientEntity t = (FPushClientEntity)this.fPushClientService.get(FPushClientEntity.class, fPushClient.getId());

        try {
            MyBeanUtils.copyBeanNotNull2Bean(fPushClient, t);
            this.fPushClientService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var7) {
            var7.printStackTrace();
            message = "f_push_client更新失败";
            throw new BusinessException(var7.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(FPushClientEntity fPushClient, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(fPushClient.getId())) {
            fPushClient = (FPushClientEntity)this.fPushClientService.getEntity(FPushClientEntity.class, fPushClient.getId());
            req.setAttribute("fPushClientPage", fPushClient);
        }

        return new ModelAndView("ssb/warmline/business/fpushclient/fPushClient-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(FPushClientEntity fPushClient, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(fPushClient.getId())) {
            fPushClient = (FPushClientEntity)this.fPushClientService.getEntity(FPushClientEntity.class, fPushClient.getId());
            req.setAttribute("fPushClientPage", fPushClient);
        }

        return new ModelAndView("ssb/warmline/business/fpushclient/fPushClient-update");
    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "fPushClientController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(FPushClientEntity fPushClient, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(FPushClientEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, fPushClient, request.getParameterMap());
        List<FPushClientEntity> fPushClients = this.fPushClientService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "f_push_client");
        modelMap.put("entity", FPushClientEntity.class);
        modelMap.put("params", new ExportParams("f_push_client列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", fPushClients);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByT"}
    )
    public String exportXlsByT(FPushClientEntity fPushClient, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "f_push_client");
        modelMap.put("entity", FPushClientEntity.class);
        modelMap.put("params", new ExportParams("f_push_client列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
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
                List<FPushClientEntity> listFPushClientEntitys = ExcelImportUtil.importExcel(file.getInputStream(), FPushClientEntity.class, params);
                Iterator var12 = listFPushClientEntitys.iterator();

                while(var12.hasNext()) {
                    FPushClientEntity fPushClient = (FPushClientEntity)var12.next();
                    this.fPushClientService.save(fPushClient);
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
    public List<FPushClientEntity> list() {
        List<FPushClientEntity> listFPushClients = this.fPushClientService.getList(FPushClientEntity.class);
        return listFPushClients;
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        FPushClientEntity task = (FPushClientEntity)this.fPushClientService.get(FPushClientEntity.class, id);
        return task == null?new ResponseEntity(HttpStatus.NOT_FOUND):new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(
            method = {RequestMethod.POST},
            consumes = {"application/json"}
    )
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody FPushClientEntity fPushClient, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<FPushClientEntity>> failures = this.validator.validate(fPushClient, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.fPushClientService.save(fPushClient);
            String id = fPushClient.getId();
            URI uri = uriBuilder.path("/rest/fPushClientController/" + id).build().toUri();
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
    public ResponseEntity<?> update(@RequestBody FPushClientEntity fPushClient) {
        Set<ConstraintViolation<FPushClientEntity>> failures = this.validator.validate(fPushClient, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.fPushClientService.saveOrUpdate(fPushClient);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.DELETE}
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.fPushClientService.deleteEntityById(FPushClientEntity.class, id);
    }
}
