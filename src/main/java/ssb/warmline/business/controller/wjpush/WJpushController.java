//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.controller.wjpush;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
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
import ssb.warmline.business.commons.enums.pushCategory;
import ssb.warmline.business.entity.whelpmessage.WHelpMessageEntity;
import ssb.warmline.business.entity.wjpush.WJpushEntity;
import ssb.warmline.business.entity.wversionupdatemanagement.WVersionUpdateManagementEntity;
import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
import ssb.warmline.business.service.whelpmessage.WHelpMessageServiceI;
import ssb.warmline.business.service.wjpush.WJpushServiceI;
import ssb.warmline.business.service.wversionupdatemanagement.WVersionUpdateManagementServiceI;
import ssb.warmline.business.utils.JpushClientUtil;

@Controller
@RequestMapping({"/wJpushController"})
public class WJpushController extends BaseController {
    private static final Logger logger = Logger.getLogger(WJpushController.class);
    @Autowired
    private WJpushServiceI wJpushService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private TSBaseUserServiceI tSBaseUserService;
    @Autowired
    private Validator validator;
    @Autowired
    private WHelpMessageServiceI wHelpMessageService;
    @Autowired
    private WVersionUpdateManagementServiceI wVersionUpdateManagementService;
    private static final Logger LOG = Logger.getLogger(WJpushController.class);
    private static final String appKey = "a63545fccaa2de66e1fe585b";
    private static final String masterSecret = "0a64dac040059403d260c7e7";

    public WJpushController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("ssb/warmline/business/wjpush/wJpushList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(WJpushEntity wJpush, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WJpushEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wJpush, request.getParameterMap());
        cq.add();
        this.wJpushService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(WJpushEntity wJpush, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        wJpush = (WJpushEntity)this.systemService.getEntity(WJpushEntity.class, wJpush.getId());
        message = "w_jpush删除成功";

        try {
            this.wJpushService.delete(wJpush);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "w_jpush删除失败";
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
        message = "w_jpush删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                WJpushEntity wJpush = (WJpushEntity)this.systemService.getEntity(WJpushEntity.class, id);
                this.wJpushService.delete(wJpush);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "w_jpush删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(WJpushEntity wJpush, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "w_jpush添加成功";

        try {
            if("text".equals(wJpush.getJpushType())) {
                if("".equals(wJpush.getMainTitle()) && wJpush.getMainTitle() == null) {
                    message = "录入失败，没有填写主标题！";
                    j.setMsg(message);
                    return j;
                }

                if("".equals(wJpush.getTitle()) && wJpush.getTitle() == null) {
                    message = "录入失败，没有填写标题！";
                    j.setMsg(message);
                    return j;
                }

                if("".equals(wJpush.getText()) && wJpush.getText() == null) {
                    message = "录入失败，没有填写内容！";
                    j.setMsg(message);
                    return j;
                }
            } else {
                if(!"versionUpdate".equals(wJpush.getJpushType())) {
                    message = "录入失败，没有选择推送类型！";
                    j.setMsg(message);
                    return j;
                }

                WVersionUpdateManagementEntity findUniqueByProperty = (WVersionUpdateManagementEntity)this.wVersionUpdateManagementService.findUniqueByProperty(WVersionUpdateManagementEntity.class, "currentField", "true");
                wJpush.setMainTitle("有新的版本更新了！");
                wJpush.setTitle("更新版本号:" + findUniqueByProperty.getVersionNumber());
                wJpush.setText("小伙伴们有新的版本更新了！请查收!");
                wJpush.setEditLink(findUniqueByProperty.getUploadUrl());
            }

            this.wJpushService.save(wJpush);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "w_jpush添加失败";
            throw new BusinessException(var6.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(WJpushEntity wJpush, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "w_jpush更新成功";
        WJpushEntity t = (WJpushEntity)this.wJpushService.get(WJpushEntity.class, wJpush.getId());

        try {
            MyBeanUtils.copyBeanNotNull2Bean(wJpush, t);
            this.wJpushService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var7) {
            var7.printStackTrace();
            message = "w_jpush更新失败";
            throw new BusinessException(var7.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(WJpushEntity wJpush, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wJpush.getId())) {
            wJpush = (WJpushEntity)this.wJpushService.getEntity(WJpushEntity.class, wJpush.getId());
            req.setAttribute("wJpushPage", wJpush);
        }

        return new ModelAndView("ssb/warmline/business/wjpush/wJpush-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(WJpushEntity wJpush, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wJpush.getId())) {
            wJpush = (WJpushEntity)this.wJpushService.getEntity(WJpushEntity.class, wJpush.getId());
            req.setAttribute("wJpushPage", wJpush);
        }

        return new ModelAndView("ssb/warmline/business/wjpush/wJpush-update");
    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "wJpushController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(WJpushEntity wJpush, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(WJpushEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wJpush, request.getParameterMap());
        List<WJpushEntity> wJpushs = this.wJpushService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "w_jpush");
        modelMap.put("entity", WJpushEntity.class);
        modelMap.put("params", new ExportParams("w_jpush列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", wJpushs);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByT"}
    )
    public String exportXlsByT(WJpushEntity wJpush, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "w_jpush");
        modelMap.put("entity", WJpushEntity.class);
        modelMap.put("params", new ExportParams("w_jpush列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
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
                List<WJpushEntity> listWJpushEntitys = ExcelImportUtil.importExcel(file.getInputStream(), WJpushEntity.class, params);
                Iterator var12 = listWJpushEntitys.iterator();

                while(var12.hasNext()) {
                    WJpushEntity wJpush = (WJpushEntity)var12.next();
                    this.wJpushService.save(wJpush);
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
    public List<WJpushEntity> list() {
        List<WJpushEntity> listWJpushs = this.wJpushService.getList(WJpushEntity.class);
        return listWJpushs;
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        WJpushEntity task = (WJpushEntity)this.wJpushService.get(WJpushEntity.class, id);
        return task == null?new ResponseEntity(HttpStatus.NOT_FOUND):new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(
            method = {RequestMethod.POST},
            consumes = {"application/json"}
    )
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody WJpushEntity wJpush, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<WJpushEntity>> failures = this.validator.validate(wJpush, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wJpushService.save(wJpush);
            String id = wJpush.getId();
            URI uri = uriBuilder.path("/rest/wJpushController/" + id).build().toUri();
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
    public ResponseEntity<?> update(@RequestBody WJpushEntity wJpush) {
        Set<ConstraintViolation<WJpushEntity>> failures = this.validator.validate(wJpush, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wJpushService.saveOrUpdate(wJpush);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.DELETE}
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.wJpushService.deleteEntityById(WJpushEntity.class, id);
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson testSendPush(WJpushEntity wJpush, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "w_jpush更新成功";
        String TITLE = "fghh";
        String ALERT = "fgh";
        String MSG_CONTENT = "Test from API Example - msgContent";
        String TAG = "tag_api";
        JPushClient jpushClient = new JPushClient("0a64dac040059403d260c7e7", "a63545fccaa2de66e1fe585b", 3);
        PushPayload payload = PushPayload.alertAll(ALERT);

        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);
        } catch (APIConnectionException var12) {
            LOG.error("Connection error. Should retry later. ", var12);
        } catch (APIRequestException var13) {
            LOG.error("Error response from JPush server. Should review and fix it. ", var13);
            LOG.info("HTTP Status: " + var13.getStatus());
            LOG.info("Error Code: " + var13.getErrorCode());
            LOG.info("Error Message: " + var13.getErrorMessage());
            LOG.info("Msg ID: " + var13.getMsgId());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doPush"}
    )
    @ResponseBody
    public AjaxJson doPush(WJpushEntity wJpush, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String message = null;
        String pushId = request.getParameter("pushId");
        WJpushEntity findUniqueByProperty = (WJpushEntity)this.wJpushService.findUniqueByProperty(WJpushEntity.class, "id", pushId);

        try {
            JSONObject json = new JSONObject();
            if("text".equals(findUniqueByProperty.getJpushType())) {
                json.put("helpMessageState", "text");
            } else if("versionUpdate".equals(findUniqueByProperty.getJpushType())) {
                json.put("helpMessageState", "versionUpdate");
            }

            json.put("title", findUniqueByProperty.getTitle());
            json.put("text", findUniqueByProperty.getText());
            String extrasparam = json.toString();
            List<String> androidIds = new ArrayList();
            List<TSBaseUser> tSBaseUser = this.tSBaseUserService.findByProperty(TSBaseUser.class, "appType", "Android");
            if(tSBaseUser.size() > 0) {
                for(int i = 0; i < tSBaseUser.size(); ++i) {
                    TSBaseUser tsBaseUser2 = (TSBaseUser)tSBaseUser.get(i);
                    androidIds.add(tsBaseUser2.getPhone());
                }
            }

            JpushClientUtil.jiGuangPushs(extrasparam, androidIds);
            WHelpMessageEntity wHelpMessage = new WHelpMessageEntity();
            wHelpMessage.setId(UUID.randomUUID().toString());
            wHelpMessage.setContent(findUniqueByProperty.getText());
            wHelpMessage.setTitle(findUniqueByProperty.getTitle());
            wHelpMessage.setMessageType(HelpMessageType.SYSTEM.toString());
            wHelpMessage.setMessageState(HelpMessageState.SYSTEM.toString());
            wHelpMessage.setPushCategory(pushCategory.WHOLE.toString());
            wHelpMessage.setReadingState("No");
            if("text".equals(findUniqueByProperty.getJpushType())) {
                wHelpMessage.setMessageStatus(HelpMessageType.TEXT.toString());
            } else if("versionUpdate".equals(findUniqueByProperty.getJpushType())) {
                wHelpMessage.setMessageStatus(HelpMessageType.VERSIONUPDATE.toString());
            }

            wHelpMessage.setVersionPath(findUniqueByProperty.getEditLink());
            this.wHelpMessageService.save(wHelpMessage);
            message = "推送成功!";
        } catch (Exception var13) {
            var13.printStackTrace();
            message = "推送失败!";
        }

        j.setObj(message);
        return j;
    }

    @RequestMapping(
            params = {"pushMan"}
    )
    public ModelAndView pushMan(HttpServletRequest request, String status) {
        String jpushId = request.getParameter("jpushId");
        System.out.println(jpushId);
        request.setAttribute("jpushId", jpushId);
        return new ModelAndView("ssb/warmline/business/wjpush/jupshUser");
    }

    @RequestMapping(
            params = {"doPushAppoint"}
    )
    @ResponseBody
    public AjaxJson doPushAppoint(HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String message = null;
        String jpushId = request.getParameter("jpushId");
        String phones = request.getParameter("phones");
        WJpushEntity findUniqueByProperty = (WJpushEntity)this.wJpushService.findUniqueByProperty(WJpushEntity.class, "id", jpushId);

        try {
            JSONObject json = new JSONObject();
            if("text".equals(findUniqueByProperty.getJpushType())) {
                json.put("helpMessageState", "text");
            } else if("versionUpdate".equals(findUniqueByProperty.getJpushType())) {
                json.put("helpMessageState", "versionUpdate");
            }

            json.put("title", findUniqueByProperty.getTitle());
            json.put("text", findUniqueByProperty.getText());
            String extrasparam = json.toString();
            List<String> androidIds = new ArrayList();
            List<String> iosIds = new ArrayList();
            String[] split = phones.split(",");
            String[] var15 = split;
            int var14 = split.length;

            for(int var13 = 0; var13 < var14; ++var13) {
                String a = var15[var13];
                TSBaseUser tSBaseUser = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "phone", a);
                if("IOS".equals(tSBaseUser.getAppType())) {
                    iosIds.add(a);
                } else if("Android".equals(tSBaseUser.getAppType())) {
                    androidIds.add(a);
                }
            }

            JpushClientUtil.jiGuangPushs(extrasparam, androidIds);
            WHelpMessageEntity wHelpMessage = new WHelpMessageEntity();
            wHelpMessage.setId(UUID.randomUUID().toString());
            wHelpMessage.setContent(findUniqueByProperty.getText());
            wHelpMessage.setTitle(findUniqueByProperty.getTitle());
            wHelpMessage.setMessageType(HelpMessageType.SYSTEM.toString());
            wHelpMessage.setMessageState(HelpMessageState.SYSTEM.toString());
            wHelpMessage.setReadingState("No");
            wHelpMessage.setPushCategory(pushCategory.PERSONAL.toString());
            wHelpMessage.setPersonalInformation(phones);
            if("text".equals(findUniqueByProperty.getJpushType())) {
                wHelpMessage.setMessageStatus(HelpMessageType.TEXT.toString());
            } else if("versionUpdate".equals(findUniqueByProperty.getJpushType())) {
                wHelpMessage.setMessageStatus(HelpMessageType.VERSIONUPDATE.toString());
            }

            wHelpMessage.setVersionPath(findUniqueByProperty.getEditLink());
            this.wHelpMessageService.save(wHelpMessage);
            message = "推送成功!";
            if("text".equals(findUniqueByProperty.getJpushType())) {
                JpushClientUtil.jiGuangPushIoss(extrasparam, findUniqueByProperty.getTitle(), findUniqueByProperty.getText(), iosIds);
                message = "推送成功!";
            }
        } catch (Exception var17) {
            var17.printStackTrace();
            message = "推送失败!";
        }

        j.setObj(message);
        return j;
    }
}
