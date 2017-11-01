//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.controller.worderphotomain;

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
import ssb.warmline.business.entity.worderphotomain.WOrderPhotoMainEntity;
import ssb.warmline.business.service.worderphotomain.WOrderPhotoMainServiceI;

@Controller
@RequestMapping({"/wOrderPhotoMainController"})
public class WOrderPhotoMainController extends BaseController {
    private static final Logger logger = Logger.getLogger(WOrderPhotoMainController.class);
    @Autowired
    private WOrderPhotoMainServiceI wOrderPhotoMainService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    public WOrderPhotoMainController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("ssb/warmline/business/worderphotomain/wOrderPhotoMainList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(WOrderPhotoMainEntity wOrderPhotoMain, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WOrderPhotoMainEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wOrderPhotoMain, request.getParameterMap());
        cq.add();
        this.wOrderPhotoMainService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(WOrderPhotoMainEntity wOrderPhotoMain, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        wOrderPhotoMain = (WOrderPhotoMainEntity)this.systemService.getEntity(WOrderPhotoMainEntity.class, wOrderPhotoMain.getId());
        message = "w_order_photo_main删除成功";

        try {
            this.wOrderPhotoMainService.delete(wOrderPhotoMain);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "w_order_photo_main删除失败";
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
        message = "w_order_photo_main删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                WOrderPhotoMainEntity wOrderPhotoMain = (WOrderPhotoMainEntity)this.systemService.getEntity(WOrderPhotoMainEntity.class, id);
                this.wOrderPhotoMainService.delete(wOrderPhotoMain);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "w_order_photo_main删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(WOrderPhotoMainEntity wOrderPhotoMain, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "w_order_photo_main添加成功";

        try {
            this.wOrderPhotoMainService.save(wOrderPhotoMain);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "w_order_photo_main添加失败";
            throw new BusinessException(var6.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(WOrderPhotoMainEntity wOrderPhotoMain, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "w_order_photo_main更新成功";
        WOrderPhotoMainEntity t = (WOrderPhotoMainEntity)this.wOrderPhotoMainService.get(WOrderPhotoMainEntity.class, wOrderPhotoMain.getId());

        try {
            MyBeanUtils.copyBeanNotNull2Bean(wOrderPhotoMain, t);
            this.wOrderPhotoMainService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var7) {
            var7.printStackTrace();
            message = "w_order_photo_main更新失败";
            throw new BusinessException(var7.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(WOrderPhotoMainEntity wOrderPhotoMain, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wOrderPhotoMain.getId())) {
            wOrderPhotoMain = (WOrderPhotoMainEntity)this.wOrderPhotoMainService.getEntity(WOrderPhotoMainEntity.class, wOrderPhotoMain.getId());
            req.setAttribute("wOrderPhotoMainPage", wOrderPhotoMain);
        }

        return new ModelAndView("ssb/warmline/business/worderphotomain/wOrderPhotoMain-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(WOrderPhotoMainEntity wOrderPhotoMain, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wOrderPhotoMain.getId())) {
            wOrderPhotoMain = (WOrderPhotoMainEntity)this.wOrderPhotoMainService.getEntity(WOrderPhotoMainEntity.class, wOrderPhotoMain.getId());
            req.setAttribute("wOrderPhotoMainPage", wOrderPhotoMain);
        }

        return new ModelAndView("ssb/warmline/business/worderphotomain/wOrderPhotoMain-update");
    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "wOrderPhotoMainController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(WOrderPhotoMainEntity wOrderPhotoMain, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(WOrderPhotoMainEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wOrderPhotoMain, request.getParameterMap());
        List<WOrderPhotoMainEntity> wOrderPhotoMains = this.wOrderPhotoMainService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "w_order_photo_main");
        modelMap.put("entity", WOrderPhotoMainEntity.class);
        modelMap.put("params", new ExportParams("w_order_photo_main列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", wOrderPhotoMains);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByT"}
    )
    public String exportXlsByT(WOrderPhotoMainEntity wOrderPhotoMain, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "w_order_photo_main");
        modelMap.put("entity", WOrderPhotoMainEntity.class);
        modelMap.put("params", new ExportParams("w_order_photo_main列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
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
                List<WOrderPhotoMainEntity> listWOrderPhotoMainEntitys = ExcelImportUtil.importExcel(file.getInputStream(), WOrderPhotoMainEntity.class, params);
                Iterator var12 = listWOrderPhotoMainEntitys.iterator();

                while(var12.hasNext()) {
                    WOrderPhotoMainEntity wOrderPhotoMain = (WOrderPhotoMainEntity)var12.next();
                    this.wOrderPhotoMainService.save(wOrderPhotoMain);
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
    public List<WOrderPhotoMainEntity> list() {
        List<WOrderPhotoMainEntity> listWOrderPhotoMains = this.wOrderPhotoMainService.getList(WOrderPhotoMainEntity.class);
        return listWOrderPhotoMains;
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        WOrderPhotoMainEntity task = (WOrderPhotoMainEntity)this.wOrderPhotoMainService.get(WOrderPhotoMainEntity.class, id);
        return task == null?new ResponseEntity(HttpStatus.NOT_FOUND):new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(
            method = {RequestMethod.POST},
            consumes = {"application/json"}
    )
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody WOrderPhotoMainEntity wOrderPhotoMain, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<WOrderPhotoMainEntity>> failures = this.validator.validate(wOrderPhotoMain, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wOrderPhotoMainService.save(wOrderPhotoMain);
            String id = wOrderPhotoMain.getId();
            URI uri = uriBuilder.path("/rest/wOrderPhotoMainController/" + id).build().toUri();
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
    public ResponseEntity<?> update(@RequestBody WOrderPhotoMainEntity wOrderPhotoMain) {
        Set<ConstraintViolation<WOrderPhotoMainEntity>> failures = this.validator.validate(wOrderPhotoMain, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wOrderPhotoMainService.saveOrUpdate(wOrderPhotoMain);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.DELETE}
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.wOrderPhotoMainService.deleteEntityById(WOrderPhotoMainEntity.class, id);
    }
}
