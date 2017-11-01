//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.controller.whelpmessage;

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import net.sf.json.JSONObject;
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
import ssb.warmline.business.commons.enums.HelpMessageState;
import ssb.warmline.business.commons.enums.HelpMessageType;
import ssb.warmline.business.commons.enums.ReadingState;
import ssb.warmline.business.entity.whelpmessage.WHelpMessageEntity;
import ssb.warmline.business.service.whelpmessage.WHelpMessageServiceI;

@Controller
@RequestMapping({"/wHelpMessageController"})
public class WHelpMessageController extends BaseController {
    private static final Logger logger = Logger.getLogger(WHelpMessageController.class);
    @Autowired
    private WHelpMessageServiceI wHelpMessageService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    public WHelpMessageController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("ssb/warmline/business/whelpmessage/wHelpMessageList");
    }

    @RequestMapping(
            params = {"Seeklist"}
    )
    public ModelAndView Seeklist(HttpServletRequest request) {
        return new ModelAndView("ssb/warmline/business/whelpmessage/SeekMessageList");
    }

    @RequestMapping(
            params = {"hellplist"}
    )
    public ModelAndView hellplist(HttpServletRequest request) {
        return new ModelAndView("ssb/warmline/business/whelpmessage/hellpMessageList");
    }

    @RequestMapping(
            params = {"applicantNumber"}
    )
    public String applicantNumber(HttpServletRequest request, String status) {
        HttpSession session = request.getSession();
        String orderId = request.getParameter("orderId");
        session.setAttribute("orderId", orderId);
        return "ssb/warmline/business/whelpmessage/applicantNumber";
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(WHelpMessageEntity wHelpMessage, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WHelpMessageEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wHelpMessage, request.getParameterMap());

        try {
            cq.eq("messageType", "system");
        } catch (Exception var7) {
            throw new BusinessException(var7.getMessage());
        }

        cq.add();
        this.wHelpMessageService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"seekMessageDatagrid"}
    )
    public void seekMessageDatagrid(WHelpMessageEntity wHelpMessage, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WHelpMessageEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wHelpMessage, request.getParameterMap());

        try {
            cq.eq("messageState", "SEEKHELP");
        } catch (Exception var7) {
            throw new BusinessException(var7.getMessage());
        }

        cq.add();
        this.wHelpMessageService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"hellpMessageDatagrid"}
    )
    public void hellpMessageDatagrid(WHelpMessageEntity wHelpMessage, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WHelpMessageEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wHelpMessage, request.getParameterMap());

        try {
            cq.eq("messageState", "HELP");
        } catch (Exception var7) {
            throw new BusinessException(var7.getMessage());
        }

        cq.add();
        this.wHelpMessageService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"applicantNumberDatagrid"}
    )
    public void applicantNumberDatagrid(WHelpMessageEntity wHelpMessage, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        String orderId = (String)request.getSession().getAttribute("orderId");
        JSONObject jObject = this.wHelpMessageService.applicantNumberDatagrid(wHelpMessage, dataGrid, orderId);
        this.responseDatagrid(response, jObject);
    }

    public void responseDatagrid(HttpServletResponse response, JSONObject jObject) {
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        PrintWriter pw = null;

        try {
            pw = response.getWriter();
            pw.write(jObject.toString());
            pw.flush();
        } catch (IOException var13) {
            var13.printStackTrace();
        } finally {
            try {
                pw.close();
            } catch (Exception var12) {
                ;
            }

        }

    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(WHelpMessageEntity wHelpMessage, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        wHelpMessage = (WHelpMessageEntity)this.systemService.getEntity(WHelpMessageEntity.class, wHelpMessage.getId());
        message = "消息删除成功";

        try {
            this.wHelpMessageService.delete(wHelpMessage);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "消息删除失败";
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
        message = "消息删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                WHelpMessageEntity wHelpMessage = (WHelpMessageEntity)this.systemService.getEntity(WHelpMessageEntity.class, id);
                this.wHelpMessageService.delete(wHelpMessage);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "消息删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(WHelpMessageEntity wHelpMessage, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        wHelpMessage.setMessageState(HelpMessageState.SYSTEM.toString());
        wHelpMessage.setCreateDate(new Date());
        wHelpMessage.setMessageType(HelpMessageType.SYSTEM.toString());
        wHelpMessage.setReadingState(ReadingState.No.toString());
        message = "消息添加成功";

        try {
            this.wHelpMessageService.save(wHelpMessage);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "消息添加失败";
            throw new BusinessException(var6.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(WHelpMessageEntity wHelpMessage, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "消息更新成功";
        WHelpMessageEntity t = (WHelpMessageEntity)this.wHelpMessageService.get(WHelpMessageEntity.class, wHelpMessage.getId());

        try {
            MyBeanUtils.copyBeanNotNull2Bean(wHelpMessage, t);
            this.wHelpMessageService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var7) {
            var7.printStackTrace();
            message = "消息更新失败";
            throw new BusinessException(var7.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(WHelpMessageEntity wHelpMessage, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wHelpMessage.getId())) {
            wHelpMessage = (WHelpMessageEntity)this.wHelpMessageService.getEntity(WHelpMessageEntity.class, wHelpMessage.getId());
            req.setAttribute("wHelpMessagePage", wHelpMessage);
        }

        return new ModelAndView("ssb/warmline/business/whelpmessage/wHelpMessage-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(WHelpMessageEntity wHelpMessage, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wHelpMessage.getId())) {
            wHelpMessage = (WHelpMessageEntity)this.wHelpMessageService.getEntity(WHelpMessageEntity.class, wHelpMessage.getId());
            req.setAttribute("wHelpMessagePage", wHelpMessage);
        }

        return new ModelAndView("ssb/warmline/business/whelpmessage/wHelpMessage-update");
    }

    @RequestMapping(
            params = {"goSystemUpdate"}
    )
    public ModelAndView goSystemUpdate(WHelpMessageEntity wHelpMessage, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wHelpMessage.getId())) {
            wHelpMessage = (WHelpMessageEntity)this.wHelpMessageService.getEntity(WHelpMessageEntity.class, wHelpMessage.getId());
            req.setAttribute("wHelpMessagePage", wHelpMessage);
        }

        return new ModelAndView("ssb/warmline/business/whelpmessage/wHelpMessage-update2");
    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "wHelpMessageController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(WHelpMessageEntity wHelpMessage, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(WHelpMessageEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wHelpMessage, request.getParameterMap());
        List<WHelpMessageEntity> wHelpMessages = this.wHelpMessageService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "消息");
        modelMap.put("entity", WHelpMessageEntity.class);
        modelMap.put("params", new ExportParams("消息列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", wHelpMessages);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByT"}
    )
    public String exportXlsByT(WHelpMessageEntity wHelpMessage, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "消息");
        modelMap.put("entity", WHelpMessageEntity.class);
        modelMap.put("params", new ExportParams("消息列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
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
                List<WHelpMessageEntity> listWHelpMessageEntitys = ExcelImportUtil.importExcel(file.getInputStream(), WHelpMessageEntity.class, params);
                Iterator var12 = listWHelpMessageEntitys.iterator();

                while(var12.hasNext()) {
                    WHelpMessageEntity wHelpMessage = (WHelpMessageEntity)var12.next();
                    this.wHelpMessageService.save(wHelpMessage);
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
    public List<WHelpMessageEntity> list() {
        List<WHelpMessageEntity> listWHelpMessages = this.wHelpMessageService.getList(WHelpMessageEntity.class);
        return listWHelpMessages;
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        WHelpMessageEntity task = (WHelpMessageEntity)this.wHelpMessageService.get(WHelpMessageEntity.class, id);
        return task == null?new ResponseEntity(HttpStatus.NOT_FOUND):new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(
            method = {RequestMethod.POST},
            consumes = {"application/json"}
    )
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody WHelpMessageEntity wHelpMessage, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<WHelpMessageEntity>> failures = this.validator.validate(wHelpMessage, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wHelpMessageService.save(wHelpMessage);
            String id = wHelpMessage.getId();
            URI uri = uriBuilder.path("/rest/wHelpMessageController/" + id).build().toUri();
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
    public ResponseEntity<?> update(@RequestBody WHelpMessageEntity wHelpMessage) {
        Set<ConstraintViolation<WHelpMessageEntity>> failures = this.validator.validate(wHelpMessage, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wHelpMessageService.saveOrUpdate(wHelpMessage);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.DELETE}
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.wHelpMessageService.deleteEntityById(WHelpMessageEntity.class, id);
    }
}
