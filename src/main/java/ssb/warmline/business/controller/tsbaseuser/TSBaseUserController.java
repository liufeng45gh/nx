//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.controller.tsbaseuser;

import java.io.IOException;
import java.io.PrintWriter;
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
import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;

@Controller
@RequestMapping({"/tSBaseUserController"})
public class TSBaseUserController extends BaseController {
    private static final Logger logger = Logger.getLogger(TSBaseUserController.class);
    @Autowired
    private TSBaseUserServiceI tSBaseUserService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    public TSBaseUserController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/qsb/business/tsbaseuser/tSBaseUserList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(TSBaseUser tSBaseUser, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSBaseUser.class, dataGrid);
        HqlGenerateUtil.installHql(cq, tSBaseUser, request.getParameterMap());
        cq.add();
        this.tSBaseUserService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"datagridChall"}
    )
    public void datagridChall(TSBaseUser tSBaseUser, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSBaseUser.class, dataGrid);
        HqlGenerateUtil.installHql(cq, tSBaseUser, request.getParameterMap());

        try {
            cq.eq("userkey", "渠道商");
        } catch (Exception var7) {
            throw new BusinessException(var7.getMessage());
        }

        cq.add();
        this.tSBaseUserService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(TSBaseUser tSBaseUser, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        tSBaseUser = (TSBaseUser)this.systemService.getEntity(TSBaseUser.class, tSBaseUser.getId());
        message = "t_s_base_user删除成功";

        try {
            this.tSBaseUserService.delete(tSBaseUser);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "t_s_base_user删除失败";
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
        message = "t_s_base_user删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                TSBaseUser tSBaseUser = (TSBaseUser)this.systemService.getEntity(TSBaseUser.class, id);
                this.tSBaseUserService.delete(tSBaseUser);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "t_s_base_user删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(TSBaseUser tSBaseUser, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "t_s_base_user添加成功";

        try {
            this.tSBaseUserService.save(tSBaseUser);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "t_s_base_user添加失败";
            throw new BusinessException(var6.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(TSBaseUser tSBaseUser, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "t_s_base_user更新成功";
        TSBaseUser t = (TSBaseUser)this.tSBaseUserService.get(TSBaseUser.class, tSBaseUser.getId());

        try {
            MyBeanUtils.copyBeanNotNull2Bean(tSBaseUser, t);
            this.tSBaseUserService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var7) {
            var7.printStackTrace();
            message = "t_s_base_user更新失败";
            throw new BusinessException(var7.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(TSBaseUser tSBaseUser, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(tSBaseUser.getId())) {
            tSBaseUser = (TSBaseUser)this.tSBaseUserService.getEntity(TSBaseUser.class, tSBaseUser.getId());
            req.setAttribute("tSBaseUserPage", tSBaseUser);
        }

        return new ModelAndView("com/qsb/business/tsbaseuser/tSBaseUser-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(TSBaseUser tSBaseUser, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(tSBaseUser.getId())) {
            tSBaseUser = (TSBaseUser)this.tSBaseUserService.getEntity(TSBaseUser.class, tSBaseUser.getId());
            req.setAttribute("tSBaseUserPage", tSBaseUser);
        }

        return new ModelAndView("com/qsb/business/tsbaseuser/tSBaseUser-update");
    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "tSBaseUserController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(TSBaseUser tSBaseUser, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(TSBaseUser.class, dataGrid);
        HqlGenerateUtil.installHql(cq, tSBaseUser, request.getParameterMap());
        List<TSBaseUser> tSBaseUsers = this.tSBaseUserService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "t_s_base_user");
        modelMap.put("entity", TSBaseUser.class);
        modelMap.put("params", new ExportParams("t_s_base_user列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", tSBaseUsers);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByT"}
    )
    public String exportXlsByT(TSBaseUser tSBaseUser, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "t_s_base_user");
        modelMap.put("entity", TSBaseUser.class);
        modelMap.put("params", new ExportParams("t_s_base_user列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
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
                List<TSBaseUser> listTSBaseUsers = ExcelImportUtil.importExcel(file.getInputStream(), TSBaseUser.class, params);
                Iterator var12 = listTSBaseUsers.iterator();

                while(var12.hasNext()) {
                    TSBaseUser tSBaseUser = (TSBaseUser)var12.next();
                    this.tSBaseUserService.save(tSBaseUser);
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
    public List<TSBaseUser> list() {
        List<TSBaseUser> listTSBaseUsers = this.tSBaseUserService.getList(TSBaseUser.class);
        return listTSBaseUsers;
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        TSBaseUser task = (TSBaseUser)this.tSBaseUserService.get(TSBaseUser.class, id);
        return task == null?new ResponseEntity(HttpStatus.NOT_FOUND):new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(
            method = {RequestMethod.POST},
            consumes = {"application/json"}
    )
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody TSBaseUser tSBaseUser, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<TSBaseUser>> failures = this.validator.validate(tSBaseUser, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.tSBaseUserService.save(tSBaseUser);
            String id = tSBaseUser.getId();
            URI uri = uriBuilder.path("/rest/tSBaseUserController/" + id).build().toUri();
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
    public ResponseEntity<?> update(@RequestBody TSBaseUser tSBaseUser) {
        Set<ConstraintViolation<TSBaseUser>> failures = this.validator.validate(tSBaseUser, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.tSBaseUserService.saveOrUpdate(tSBaseUser);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.DELETE}
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.tSBaseUserService.deleteEntityById(TSBaseUser.class, id);
    }

    @RequestMapping(
            params = {"choiceSingleDatagrid"}
    )
    public void choiceSingleDatagrid(TSBaseUser tSBaseUser, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        //TSBaseUser tSBaseUser = ResourceUtil.getSessionUserName();
        String territoryId = request.getParameter("territoryId");
        if(territoryId != null && !"".equals(territoryId)) {
            tSBaseUser.setTerritoryId(territoryId);
        }

        JSONObject jObject = this.tSBaseUserService.tsBaseUserDatagrid(tSBaseUser, dataGrid);
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
            params = {"loadPushPerson"}
    )
    public void loadPushPerson(TSBaseUser tSBaseUser, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        JSONObject jObject = this.tSBaseUserService.tsBaseUserJupsh(tSBaseUser, dataGrid);
        this.responseDatagrid(response, jObject);
    }
}
