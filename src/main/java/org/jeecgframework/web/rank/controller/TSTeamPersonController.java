//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.rank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
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
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.cgform.engine.FreemarkerHelper;
import org.jeecgframework.web.rank.entity.TSTeamPersonEntity;
import org.jeecgframework.web.rank.service.TSTeamPersonServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/tSTeamPersonController"})
public class TSTeamPersonController extends BaseController {
    private static final Logger logger = Logger.getLogger(TSTeamPersonController.class);
    private final String FTL_Teachers = "clzcontext/template/cms/rank/html/teachers.ftl";
    private final String FTL_Teacher = "clzcontext/template/cms/rank/html/teacher.ftl";
    private final String FTL_Introduce = "clzcontext/template/cms/rank/html/introduce.ftl";
    @Autowired
    private TSTeamPersonServiceI tSTeamPersonService;
    @Autowired
    private SystemService systemService;
    private String message;

    public TSTeamPersonController() {
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @RequestMapping(
            params = {"tSTeamPerson"}
    )
    public ModelAndView tSTeamPerson(HttpServletRequest request) {
        return new ModelAndView("system/rank/tSTeamPersonList");
    }

    @RequestMapping(
            params = {"getTeacherList"}
    )
    public void getTeacherList(HttpServletRequest request, HttpServletResponse response) {
        FreemarkerHelper viewEngine = new FreemarkerHelper();
        Map<String, Object> map = new HashMap();
        List<TSTeamPersonEntity> teamPersonEntities = this.tSTeamPersonService.findByQueryString("from TSTeamPersonEntity order by isJoin desc, jionDate");
        String path = request.getContextPath();
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        map.put("teachers", teamPersonEntities);
        map.put("url", url);
        String html = viewEngine.parseTemplate("clzcontext/template/cms/rank/html/teachers.ftl", map);
        PrintWriter writer = null;

        try {
            response.setContentType("text/html");
            response.setHeader("Cache-Control", "no-store");
            writer = response.getWriter();
            writer.println(html);
            writer.flush();
        } catch (IOException var19) {
            var19.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception var18) {
                ;
            }

        }

    }

    @RequestMapping(
            params = {"getTeacher"}
    )
    public void getTeacher(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
        FreemarkerHelper viewEngine = new FreemarkerHelper();
        TSTeamPersonEntity teamPersonEntity = (TSTeamPersonEntity)this.tSTeamPersonService.get(TSTeamPersonEntity.class, id);
        String path = request.getContextPath();
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        Map<String, Object> map = new HashMap();
        map.put("teacher", teamPersonEntity);
        map.put("url", url);
        String html = viewEngine.parseTemplate("clzcontext/template/cms/rank/html/teacher.ftl", map);
        PrintWriter writer = null;

        try {
            response.setContentType("text/html");
            response.setHeader("Cache-Control", "no-store");
            writer = response.getWriter();
            writer.println(html);
            writer.flush();
        } catch (IOException var20) {
            var20.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception var19) {
                ;
            }

        }

    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(TSTeamPersonEntity tSTeamPerson, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSTeamPersonEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, tSTeamPerson, request.getParameterMap());
        cq.add();
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(TSTeamPersonEntity tSTeamPerson, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        tSTeamPerson = (TSTeamPersonEntity)this.systemService.getEntity(TSTeamPersonEntity.class, tSTeamPerson.getId());
        this.message = "团队人员榜删除成功";

        try {
            this.tSTeamPersonService.delete(tSTeamPerson);
            this.systemService.addLog(this.message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var5) {
            var5.printStackTrace();
            this.message = "团队人员榜删除失败";
            throw new BusinessException(var5.getMessage());
        }

        j.setMsg(this.message);
        return j;
    }

    @RequestMapping(
            params = {"doBatchDel"}
    )
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        this.message = "团队人员榜删除成功";

        try {
            String[] var7;
            int var6 = (var7 = ids.split(",")).length;

            for(int var5 = 0; var5 < var6; ++var5) {
                String id = var7[var5];
                TSTeamPersonEntity tSTeamPerson = (TSTeamPersonEntity)this.systemService.getEntity(TSTeamPersonEntity.class, id);
                this.tSTeamPersonService.delete(tSTeamPerson);
                this.systemService.addLog(this.message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var9) {
            var9.printStackTrace();
            this.message = "团队人员榜删除失败";
            throw new BusinessException(var9.getMessage());
        }

        j.setMsg(this.message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(TSTeamPersonEntity tSTeamPerson, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        this.message = "团队人员榜添加成功";

        try {
            this.tSTeamPersonService.save(tSTeamPerson);
            this.systemService.addLog(this.message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var5) {
            var5.printStackTrace();
            this.message = "团队人员榜添加失败";
            throw new BusinessException(var5.getMessage());
        }

        j.setMsg(this.message);
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(TSTeamPersonEntity tSTeamPerson, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        this.message = "团队人员榜更新成功";
        TSTeamPersonEntity t = (TSTeamPersonEntity)this.tSTeamPersonService.get(TSTeamPersonEntity.class, tSTeamPerson.getId());

        try {
            MyBeanUtils.copyBeanNotNull2Bean(tSTeamPerson, t);
            this.tSTeamPersonService.saveOrUpdate(t);
            this.systemService.addLog(this.message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            this.message = "团队人员榜更新失败";
            throw new BusinessException(var6.getMessage());
        }

        j.setMsg(this.message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(TSTeamPersonEntity tSTeamPerson, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(tSTeamPerson.getId())) {
            tSTeamPerson = (TSTeamPersonEntity)this.tSTeamPersonService.getEntity(TSTeamPersonEntity.class, tSTeamPerson.getId());
            req.setAttribute("tSTeamPersonPage", tSTeamPerson);
        }

        return new ModelAndView("system/rank/tSTeamPerson-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(TSTeamPersonEntity tSTeamPerson, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(tSTeamPerson.getId())) {
            tSTeamPerson = (TSTeamPersonEntity)this.tSTeamPersonService.getEntity(TSTeamPersonEntity.class, tSTeamPerson.getId());
            req.setAttribute("tSTeamPersonPage", tSTeamPerson);
        }

        return new ModelAndView("system/rank/tSTeamPerson-update");
    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "tSTeamPersonController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(TSTeamPersonEntity tSTeamPerson, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(TSTeamPersonEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, tSTeamPerson, request.getParameterMap());
        List<TSTeamPersonEntity> tSTeamPersons = this.tSTeamPersonService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "团队人员榜");
        modelMap.put("entity", TSTeamPersonEntity.class);
        modelMap.put("params", new ExportParams("团队人员榜列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", tSTeamPersons);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByT"}
    )
    public String exportXlsByT(TSTeamPersonEntity tSTeamPerson, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "团队人员榜");
        modelMap.put("params", new TemplateExportParams("Excel模板地址", new Integer[0]));
        modelMap.put("map", (Object)null);
        modelMap.put("entity", TSTeamPersonEntity.class);
        modelMap.put("list", (Object)null);
        return "jeecgTemplateExcelView";
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
                List<TSTeamPersonEntity> listTSTeamPersonEntitys = ExcelImportUtil.importExcel(file.getInputStream(), TSTeamPersonEntity.class, params);
                Iterator var12 = listTSTeamPersonEntitys.iterator();

                while(var12.hasNext()) {
                    TSTeamPersonEntity tSTeamPerson = (TSTeamPersonEntity)var12.next();
                    this.tSTeamPersonService.save(tSTeamPerson);
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
            params = {"introduce"}
    )
    public void introduce(HttpServletRequest request, HttpServletResponse response) {
        FreemarkerHelper viewEngine = new FreemarkerHelper();
        String path = request.getContextPath();
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        Map<String, Object> map = new HashMap();
        map.put("url", url);
        String html = viewEngine.parseTemplate("clzcontext/template/cms/rank/html/introduce.ftl", map);
        PrintWriter writer = null;

        try {
            response.setContentType("text/html");
            response.setHeader("Cache-Control", "no-store");
            writer = response.getWriter();
            writer.println(html);
            writer.flush();
        } catch (IOException var18) {
            var18.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception var17) {
                ;
            }

        }

    }
}
