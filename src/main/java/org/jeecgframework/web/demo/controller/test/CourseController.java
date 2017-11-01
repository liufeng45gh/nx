//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.demo.controller.test;

import java.io.IOException;
import java.util.HashMap;
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
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.demo.entity.test.CourseEntity;
import org.jeecgframework.web.demo.service.test.CourseServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping({"/courseController"})
public class CourseController extends BaseController {
    private static final Logger logger = Logger.getLogger(CourseController.class);
    private static final String COURSE_LIST_PAGE = "jeecg/demo/test/courseList";
    private static final String COURSE_ADD_OR_UPDATE_PAGE = "jeecg/demo/test/course";
    private static final String STUDENT_LIST_PAGE = "jeecg/demo/test/CourseStudentList";
    private static final String COURSE_UPLOAD_PAGE = "jeecg/demo/test/courseUpload";
    @Autowired
    private CourseServiceI courseService;
    @Autowired
    private SystemService systemService;

    public CourseController() {
    }

    @RequestMapping(
            params = {"course"}
    )
    public String course(HttpServletRequest request) {
        return "jeecg/demo/test/courseList";
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(CourseEntity course, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(CourseEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, course, request.getParameterMap());
        this.courseService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"del"}
    )
    @ResponseBody
    public AjaxJson del(CourseEntity course, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        course = (CourseEntity)this.systemService.getEntity(CourseEntity.class, course.getId());
        j.setMsg("课程删除成功");
        this.courseService.delete(course);
        this.systemService.addLog(j.getMsg(), Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        return j;
    }

    @RequestMapping(
            params = {"save"}
    )
    @ResponseBody
    public AjaxJson save(CourseEntity course, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        if(StringUtil.isNotEmpty(course.getId())) {
            j.setMsg("课程更新成功");

            try {
                this.courseService.updateCourse(course);
                this.systemService.addLog(j.getMsg(), Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
            } catch (Exception var5) {
                var5.printStackTrace();
                j.setMsg("课程更新失败");
            }
        } else {
            j.setMsg("课程添加成功");
            this.courseService.saveCourse(course);
            this.systemService.addLog(j.getMsg(), Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        }

        return j;
    }

    @RequestMapping(
            params = {"addorupdate"}
    )
    public String addorupdate(CourseEntity course, ModelMap map) {
        if(StringUtil.isNotEmpty(course.getId())) {
            course = (CourseEntity)this.courseService.getEntity(CourseEntity.class, course.getId());
            map.put("coursePage", course);
        }

        return "jeecg/demo/test/course";
    }

    @RequestMapping(
            params = {"studentsList"}
    )
    public String studentsList(CourseEntity course, ModelMap map) {
        if(StringUtil.isNotEmpty(course.getId())) {
            course = (CourseEntity)this.courseService.getEntity(CourseEntity.class, course.getId());
            map.put("studentsList", course.getStudents());
        }

        return "jeecg/demo/test/CourseStudentList";
    }

    @RequestMapping(
            params = {"upload"}
    )
    public String upload(HttpServletRequest req) {
        return "jeecg/demo/test/courseUpload";
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(CourseEntity course, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap map) {
        CriteriaQuery cq = new CriteriaQuery(CourseEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, course, request.getParameterMap());
        List<CourseEntity> courses = this.courseService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        map.put("fileName", "用户信息");
        map.put("entity", CourseEntity.class);
        map.put("params", new ExportParams("课程列表", "导出人:Jeecg", "导出信息"));
        map.put("data", courses);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByTest"}
    )
    public String exportXlsByTest(CourseEntity course, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        Map<String, Object> map = new HashMap();
        map.put("month", Integer.valueOf(10));

        for(int i = 1; i < 8; ++i) {
            Map<String, Object> temp = new HashMap();
            temp.put("per", Integer.valueOf(i * 10));
            temp.put("mon", Integer.valueOf(i * 1000));
            temp.put("summon", Integer.valueOf(i * 10000));
            map.put("i" + i, temp);
        }

        modelMap.put("fileName", "工资统计信息");
        modelMap.put("params", new TemplateExportParams("export/template/exportTemp.xls", new Integer[]{Integer.valueOf(1)}));
        modelMap.put("map", map);
        return "jeecgTemplateExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByT"}
    )
    public String exportXlsByT(CourseEntity course, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(CourseEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, course, request.getParameterMap());
        List<CourseEntity> courses = this.courseService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        Map<String, Object> map = new HashMap();
        map.put("year", "2013");
        map.put("sunCourses", Integer.valueOf(courses.size()));
        Map<String, Object> obj = new HashMap();
        map.put("obj", obj);
        obj.put("name", Integer.valueOf(courses.size()));
        modelMap.put("fileName", "课程信息");
        modelMap.put("params", new TemplateExportParams("export/template/exportTemp.xls", new Integer[0]));
        modelMap.put("map", map);
        modelMap.put("entity", CourseEntity.class);
        modelMap.put("list", courses);
        return "jeecgTemplateExcelView";
    }

    @RequestMapping(
            params = {"exportDocByT"}
    )
    public String exportDocByT(CourseEntity course, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(CourseEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, course, request.getParameterMap());
        List<CourseEntity> courses = this.courseService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        Map<String, Object> map = new HashMap();
        map.put("Q1", "289782002");
        map.put("Q2", "106259349");
        map.put("Q3", "106838471");
        map.put("w1", "175449166");
        map.put("w2", "287090836");
        map.put("Author", "scott");
        map.put("email", "scott@jeecg.org");
        map.put("list", courses);
        modelMap.put("fileName", "Word测试");
        modelMap.put("map", map);
        modelMap.put("url", "export/template/test.docx");
        return "jeecgTemplateWordView";
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
            params.setHeadRows(2);
            params.setNeedSave(true);

            try {
                List<CourseEntity> listCourses = ExcelImportUtil.importExcel(file.getInputStream(), CourseEntity.class, params);
                Iterator var12 = listCourses.iterator();

                while(var12.hasNext()) {
                    CourseEntity course = (CourseEntity)var12.next();
                    if(course.getName() != null) {
                        this.courseService.saveCourse(course);
                    }
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
}
