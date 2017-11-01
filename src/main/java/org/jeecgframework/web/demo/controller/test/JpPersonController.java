//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.demo.controller.test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.UUIDGenerator;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.demo.entity.test.JpPersonEntity;
import org.jeecgframework.web.demo.service.test.JpPersonServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/jpPersonController"})
public class JpPersonController extends BaseController {
    private static final Logger logger = Logger.getLogger(JpPersonController.class);
    @Autowired
    private JpPersonServiceI jpPersonService;
    @Autowired
    private SystemService systemService;

    public JpPersonController() {
    }

    @RequestMapping(
            params = {"jpPerson"}
    )
    public ModelAndView jpPerson(HttpServletRequest request) {
        return new ModelAndView("jeecg/demo/test/jpPersonList");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        List<JpPersonEntity> jpPersons = this.jpPersonService.loadAll(JpPersonEntity.class);
        map.put("fileName", "用户信息");
        map.put("entity", JpPersonEntity.class);
        map.put("params", new ExportParams((String)null, (String)null, "导出信息"));
        map.put("data", jpPersons);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"goImplXls"}
    )
    public ModelAndView goImplXls(HttpServletRequest request) {
        return new ModelAndView("jeecg/demo/test/upload");
    }

    @RequestMapping(
            params = {"implXls"}
    )
    @ResponseBody
    public AjaxJson implXls(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest)request;
        MultipartFile file = mulRequest.getFile("filedata");

        try {
            boolean isSuccess = true;
            List<JpPersonEntity> listPersons = ExcelImportUtil.importExcel(file.getInputStream(), JpPersonEntity.class, new ImportParams());
            Iterator var9 = listPersons.iterator();

            while(var9.hasNext()) {
                JpPersonEntity person = (JpPersonEntity)var9.next();
                person.setId(UUIDGenerator.generate());
                if(person.getAge() == null || person.getCreatedt() == null || person.getSalary() == null) {
                    isSuccess = false;
                    break;
                }

                this.jpPersonService.save(person);
            }

            if(isSuccess) {
                j.setMsg("文件导入成功！");
            } else {
                j.setMsg("文件格式不正确，导入失败！");
            }
        } catch (IOException var10) {
            j.setMsg("文件导入失败！");
            logger.error(ExceptionUtil.getExceptionMessage(var10));
        } catch (Exception var11) {
            j.setMsg("文件格式不正确，导入失败！");
        }

        return j;
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

            try {
                List<JpPersonEntity> listPersons = ExcelImportUtil.importExcel(file.getInputStream(), JpPersonEntity.class, new ImportParams());
                Iterator var11 = listPersons.iterator();

                while(var11.hasNext()) {
                    JpPersonEntity person = (JpPersonEntity)var11.next();
                    if(person.getAge() != null) {
                        person.setId(UUIDGenerator.generate());
                        this.jpPersonService.save(person);
                    }
                }

                j.setMsg("文件导入成功！");
            } catch (Exception var12) {
                j.setMsg("文件导入失败！");
                logger.error(ExceptionUtil.getExceptionMessage(var12));
            }
        }

        return j;
    }

    @RequestMapping({"check"})
    public void check(HttpServletRequest request, HttpServletResponse response) {
        try {
            if("open".equals(request.getSession().getAttribute("state"))) {
                request.getSession().setAttribute("state", (Object)null);
                response.getWriter().write("true");
                response.getWriter().flush();
            } else {
                response.getWriter().write("false");
                response.getWriter().flush();
            }
        } catch (IOException var12) {
            ;
        } finally {
            try {
                response.getWriter().close();
            } catch (Exception var11) {
                ;
            }

        }

    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(JpPersonEntity.class, dataGrid);
        this.jpPersonService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"del"}
    )
    @ResponseBody
    public AjaxJson del(JpPersonEntity jpPerson, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        jpPerson = (JpPersonEntity)this.systemService.getEntity(JpPersonEntity.class, jpPerson.getId());
        message = "删除成功";
        this.jpPersonService.delete(jpPerson);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"save"}
    )
    @ResponseBody
    public AjaxJson save(JpPersonEntity jpPerson, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        if(StringUtil.isNotEmpty(jpPerson.getId())) {
            message = "更新成功";
            this.jpPersonService.saveOrUpdate(jpPerson);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } else {
            message = "添加成功";
            this.jpPersonService.save(jpPerson);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        }

        return j;
    }

    @RequestMapping(
            params = {"addorupdate"}
    )
    public ModelAndView addorupdate(JpPersonEntity jpPerson, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(jpPerson.getId())) {
            jpPerson = (JpPersonEntity)this.jpPersonService.getEntity(JpPersonEntity.class, jpPerson.getId());
            req.setAttribute("jpPersonPage", jpPerson);
        }

        return new ModelAndView("jeecg/demo/test/jpPerson");
    }
}
