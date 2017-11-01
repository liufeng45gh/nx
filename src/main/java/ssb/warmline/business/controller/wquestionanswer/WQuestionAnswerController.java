//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.controller.wquestionanswer;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
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
import ssb.warmline.business.entity.wquestionanswer.WQuestionAnswerEntity;
import ssb.warmline.business.service.wquestionanswer.WQuestionAnswerServiceI;

@Controller
@RequestMapping({"/wQuestionAnswerController"})
public class WQuestionAnswerController extends BaseController {
    private static final Logger logger = Logger.getLogger(WQuestionAnswerController.class);
    @Autowired
    private WQuestionAnswerServiceI wQuestionAnswerService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    public WQuestionAnswerController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("ssb/warmline/business/wquestionanswer/wQuestionAnswerList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(WQuestionAnswerEntity wQuestionAnswer, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WQuestionAnswerEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wQuestionAnswer, request.getParameterMap());
        cq.add();
        this.wQuestionAnswerService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(WQuestionAnswerEntity wQuestionAnswer, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        wQuestionAnswer = (WQuestionAnswerEntity)this.systemService.getEntity(WQuestionAnswerEntity.class, wQuestionAnswer.getId());
        message = "w_question_answer删除成功";

        try {
            this.wQuestionAnswerService.delete(wQuestionAnswer);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "w_question_answer删除失败";
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
        message = "w_question_answer删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                WQuestionAnswerEntity wQuestionAnswer = (WQuestionAnswerEntity)this.systemService.getEntity(WQuestionAnswerEntity.class, id);
                this.wQuestionAnswerService.delete(wQuestionAnswer);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "w_question_answer删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(WQuestionAnswerEntity wQuestionAnswer, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "问答添加成功";

        try {
            String hql = " from WQuestionAnswerEntity ";
            List<WQuestionAnswerEntity> findListbySql = this.wQuestionAnswerService.findByQueryString(hql);
            if(findListbySql.size() >= 10) {
                message = "问题最多只能录入10条，可进行删除排序！";
                j.setMsg(message);
                return j;
            }

            WQuestionAnswerEntity findUniqueByProperty = (WQuestionAnswerEntity)this.wQuestionAnswerService.findUniqueByProperty(WQuestionAnswerEntity.class, "serialNumber", wQuestionAnswer.getSerialNumber());
            if(findUniqueByProperty != null || "".equals(findUniqueByProperty)) {
                message = "问题序号不能重复！";
                j.setMsg(message);
                return j;
            }

            wQuestionAnswer.setCreatTime(new Date());
            this.wQuestionAnswerService.save(wQuestionAnswer);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var8) {
            var8.printStackTrace();
            message = "问答添加失败";
            throw new BusinessException(var8.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(WQuestionAnswerEntity wQuestionAnswer, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "问答更新成功";
        WQuestionAnswerEntity t = (WQuestionAnswerEntity)this.wQuestionAnswerService.get(WQuestionAnswerEntity.class, wQuestionAnswer.getId());

        try {
            MyBeanUtils.copyBeanNotNull2Bean(wQuestionAnswer, t);
            this.wQuestionAnswerService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var7) {
            var7.printStackTrace();
            message = "问答更新失败";
            throw new BusinessException(var7.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(WQuestionAnswerEntity wQuestionAnswer, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wQuestionAnswer.getId())) {
            wQuestionAnswer = (WQuestionAnswerEntity)this.wQuestionAnswerService.getEntity(WQuestionAnswerEntity.class, wQuestionAnswer.getId());
            req.setAttribute("wQuestionAnswerPage", wQuestionAnswer);
        }

        return new ModelAndView("ssb/warmline/business/wquestionanswer/wQuestionAnswer-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(WQuestionAnswerEntity wQuestionAnswer, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wQuestionAnswer.getId())) {
            wQuestionAnswer = (WQuestionAnswerEntity)this.wQuestionAnswerService.getEntity(WQuestionAnswerEntity.class, wQuestionAnswer.getId());
            req.setAttribute("wQuestionAnswerPage", wQuestionAnswer);
        }

        return new ModelAndView("ssb/warmline/business/wquestionanswer/wQuestionAnswer-update");
    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "wQuestionAnswerController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(WQuestionAnswerEntity wQuestionAnswer, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(WQuestionAnswerEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wQuestionAnswer, request.getParameterMap());
        List<WQuestionAnswerEntity> wQuestionAnswers = this.wQuestionAnswerService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "w_question_answer");
        modelMap.put("entity", WQuestionAnswerEntity.class);
        modelMap.put("params", new ExportParams("w_question_answer列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", wQuestionAnswers);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByT"}
    )
    public String exportXlsByT(WQuestionAnswerEntity wQuestionAnswer, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "w_question_answer");
        modelMap.put("entity", WQuestionAnswerEntity.class);
        modelMap.put("params", new ExportParams("w_question_answer列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
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
                List<WQuestionAnswerEntity> listWQuestionAnswerEntitys = ExcelImportUtil.importExcel(file.getInputStream(), WQuestionAnswerEntity.class, params);
                Iterator var12 = listWQuestionAnswerEntitys.iterator();

                while(var12.hasNext()) {
                    WQuestionAnswerEntity wQuestionAnswer = (WQuestionAnswerEntity)var12.next();
                    this.wQuestionAnswerService.save(wQuestionAnswer);
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
    public List<WQuestionAnswerEntity> list() {
        List<WQuestionAnswerEntity> listWQuestionAnswers = this.wQuestionAnswerService.getList(WQuestionAnswerEntity.class);
        return listWQuestionAnswers;
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        WQuestionAnswerEntity task = (WQuestionAnswerEntity)this.wQuestionAnswerService.get(WQuestionAnswerEntity.class, id);
        return task == null?new ResponseEntity(HttpStatus.NOT_FOUND):new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(
            method = {RequestMethod.POST},
            consumes = {"application/json"}
    )
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody WQuestionAnswerEntity wQuestionAnswer, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<WQuestionAnswerEntity>> failures = this.validator.validate(wQuestionAnswer, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wQuestionAnswerService.save(wQuestionAnswer);
            String id = wQuestionAnswer.getId();
            URI uri = uriBuilder.path("/rest/wQuestionAnswerController/" + id).build().toUri();
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
    public ResponseEntity<?> update(@RequestBody WQuestionAnswerEntity wQuestionAnswer) {
        Set<ConstraintViolation<WQuestionAnswerEntity>> failures = this.validator.validate(wQuestionAnswer, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wQuestionAnswerService.saveOrUpdate(wQuestionAnswer);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.DELETE}
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.wQuestionAnswerService.deleteEntityById(WQuestionAnswerEntity.class, id);
    }
}
