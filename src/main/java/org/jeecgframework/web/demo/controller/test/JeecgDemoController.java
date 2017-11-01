//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.demo.controller.test;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.annotation.config.AutoMenu;
import org.jeecgframework.core.annotation.config.AutoMenuOperation;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.demo.entity.test.CKEditorEntity;
import org.jeecgframework.web.demo.entity.test.JeecgDemo;
import org.jeecgframework.web.demo.entity.test.JeecgDemoPage;
import org.jeecgframework.web.demo.service.test.JeecgDemoServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/jeecgDemoController"})
@AutoMenu(
        name = "常用Demo",
        url = "jeecgDemoController.do?jeecgDemo"
)
public class JeecgDemoController extends BaseController {
    private static final Logger logger = Logger.getLogger(JeecgDemoController.class);
    @Autowired
    private JeecgDemoServiceI jeecgDemoService;
    @Autowired
    private SystemService systemService;

    public JeecgDemoController() {
    }

    @RequestMapping(
            params = {"popup"}
    )
    public ModelAndView popup(HttpServletRequest request) {
        return new ModelAndView("jeecg/demo/jeecgDemo/popup");
    }

    @RequestMapping(
            params = {"ckeditor"}
    )
    public ModelAndView ckeditor(HttpServletRequest request) {
        CKEditorEntity t = null;
        List<CKEditorEntity> ls = this.jeecgDemoService.loadAll(CKEditorEntity.class);
        if(ls != null && ls.size() > 0) {
            t = (CKEditorEntity)ls.get(0);
        } else {
            t = new CKEditorEntity();
        }

        request.setAttribute("cKEditorEntity", t);
        if(t.getContents() == null) {
            request.setAttribute("contents", "");
        } else {
            request.setAttribute("contents", new String(t.getContents()));
        }

        return new ModelAndView("jeecg/demo/jeecgDemo/ckeditor");
    }

    @RequestMapping(
            params = {"saveCkeditor"}
    )
    @ResponseBody
    public AjaxJson saveCkeditor(HttpServletRequest request, CKEditorEntity cKEditor, String contents) {
        AjaxJson j = new AjaxJson();
        if(StringUtil.isNotEmpty(cKEditor.getId())) {
            CKEditorEntity t = (CKEditorEntity)this.jeecgDemoService.get(CKEditorEntity.class, cKEditor.getId());

            try {
                MyBeanUtils.copyBeanNotNull2Bean(cKEditor, t);
                t.setContents(contents.getBytes());
                this.jeecgDemoService.saveOrUpdate(t);
                j.setMsg("更新成功");
            } catch (Exception var7) {
                var7.printStackTrace();
                j.setMsg("更新失败");
            }
        } else {
            cKEditor.setContents(contents.getBytes());
            this.jeecgDemoService.save(cKEditor);
        }

        return j;
    }

    @RequestMapping(
            params = {"jeecgDemo"}
    )
    public ModelAndView jeecgDemo(HttpServletRequest request) {
        return new ModelAndView("jeecg/demo/jeecgDemo/jeecgDemoList");
    }

    @RequestMapping(
            params = {"jeecgDemo2"}
    )
    public ModelAndView jeecgDemo2(HttpServletRequest request) {
        request.setAttribute("status", "1");
        return new ModelAndView("jeecg/demo/jeecgDemo/jeecgDemoList2");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(JeecgDemo jeecgDemo, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(JeecgDemo.class, dataGrid);
        cq.addOrder("mobilePhone", SortDirection.asc);
        cq.addOrder("age", SortDirection.asc);
        HqlGenerateUtil.installHql(cq, jeecgDemo, request.getParameterMap());
        this.jeecgDemoService.getDataGridReturn(cq, true);
        String total_salary = String.valueOf(this.jeecgDemoService.findOneForJdbc("select sum(salary) as ssum from jeecg_demo", new Object[0]).get("ssum"));
        dataGrid.setFooter("salary:" + (total_salary.equalsIgnoreCase("null")?"0.0":total_salary) + ",age,email:合计");
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"datagrid2"}
    )
    public void datagrid2(JeecgDemo jeecgDemo, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(JeecgDemo.class, dataGrid);
        HqlGenerateUtil.installHql(cq, jeecgDemo, request.getParameterMap());
        this.jeecgDemoService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"combox"}
    )
    @ResponseBody
    public List<JeecgDemo> combox(HttpServletRequest request, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(JeecgDemo.class);
        List<JeecgDemo> ls = this.jeecgDemoService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        return ls;
    }

    @RequestMapping(
            params = {"del"}
    )
    @ResponseBody
    public AjaxJson del(JeecgDemo jeecgDemo, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        jeecgDemo = (JeecgDemo)this.systemService.getEntity(JeecgDemo.class, jeecgDemo.getId());
        message = "JeecgDemo例子: " + jeecgDemo.getUserName() + "被删除 成功";
        this.jeecgDemoService.delete(jeecgDemo);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"save"}
    )
    @ResponseBody
    @AutoMenuOperation(
            name = "添加",
            code = "add"
    )
    public AjaxJson save(JeecgDemo jeecgDemo, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        if(StringUtil.isNotEmpty(jeecgDemo.getId())) {
            message = "JeecgDemo例子: " + jeecgDemo.getUserName() + "被更新成功";
            JeecgDemo t = (JeecgDemo)this.jeecgDemoService.get(JeecgDemo.class, jeecgDemo.getId());

            try {
                MyBeanUtils.copyBeanNotNull2Bean(jeecgDemo, t);
                this.jeecgDemoService.saveOrUpdate(t);
                Gson gson = new Gson();
                this.systemService.addDataLog("jeecg_demo", t.getId(), gson.toJson(t));
                this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
            } catch (Exception var7) {
                var7.printStackTrace();
            }
        } else {
            message = "JeecgDemo例子: " + jeecgDemo.getUserName() + "被添加成功";
            jeecgDemo.setStatus("0");
            this.jeecgDemoService.save(jeecgDemo);
            Gson gson = new Gson();
            this.systemService.addDataLog("jeecg_demo", jeecgDemo.getId(), gson.toJson(jeecgDemo));
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        }

        return j;
    }

    @RequestMapping(
            params = {"saveAuthor"}
    )
    @ResponseBody
    public AjaxJson saveAuthor(JeecgDemo jeecgDemo, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        if(StringUtil.isNotEmpty(jeecgDemo.getId())) {
            message = "测试-用户申请成功";
            JeecgDemo t = (JeecgDemo)this.jeecgDemoService.get(JeecgDemo.class, jeecgDemo.getId());

            try {
                MyBeanUtils.copyBeanNotNull2Bean(jeecgDemo, t);
                t.setStatus("1");
                this.jeecgDemoService.saveOrUpdate(t);
                j.setMsg(message);
                this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
            } catch (Exception var7) {
                var7.printStackTrace();
            }
        }

        return j;
    }

    @RequestMapping(
            params = {"doCheck"}
    )
    public ModelAndView doCheck(HttpServletRequest request) {
        String id = request.getParameter("id");
        request.setAttribute("id", id);
        if(StringUtil.isNotEmpty(id)) {
            JeecgDemo jeecgDemo = (JeecgDemo)this.jeecgDemoService.getEntity(JeecgDemo.class, id);
            request.setAttribute("jeecgDemo", jeecgDemo);
        }

        return new ModelAndView("jeecg/demo/jeecgDemo/jeecgDemo-check");
    }

    @RequestMapping(
            params = {"doDeleteALLSelect"}
    )
    @ResponseBody
    public AjaxJson doDeleteALLSelect(JeecgDemo jeecgDemo, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        String ids = request.getParameter("ids");
        String[] entitys = ids.split(",");
        List<JeecgDemo> list = new ArrayList();

        for(int i = 0; i < entitys.length; ++i) {
            jeecgDemo = (JeecgDemo)this.systemService.getEntity(JeecgDemo.class, entitys[i]);
            list.add(jeecgDemo);
        }

        message = "删除成功";
        this.jeecgDemoService.deleteAllEntitie(list);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goDemo"}
    )
    public ModelAndView goDemo(HttpServletRequest request) {
        return new ModelAndView("jeecg/demo/jeecgDemo/" + request.getParameter("demoPage"));
    }

    @RequestMapping(
            params = {"saveRows"}
    )
    @ResponseBody
    public AjaxJson saveRows(JeecgDemoPage page) {
        String message = null;
        List<JeecgDemo> demos = page.getDemos();
        AjaxJson j = new AjaxJson();
        if(CollectionUtils.isNotEmpty(demos)) {
            Iterator var6 = demos.iterator();

            while(var6.hasNext()) {
                JeecgDemo jeecgDemo = (JeecgDemo)var6.next();
                if(StringUtil.isNotEmpty(jeecgDemo.getId())) {
                    JeecgDemo t = (JeecgDemo)this.jeecgDemoService.get(JeecgDemo.class, jeecgDemo.getId());

                    try {
                        message = "JeecgDemo例子: " + jeecgDemo.getUserName() + "被更新成功";
                        MyBeanUtils.copyBeanNotNull2Bean(jeecgDemo, t);
                        this.jeecgDemoService.saveOrUpdate(t);
                        this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
                    } catch (Exception var9) {
                        var9.printStackTrace();
                    }
                } else {
                    message = "JeecgDemo例子: " + jeecgDemo.getUserName() + "被添加成功";
                    jeecgDemo.setStatus("0");
                    this.jeecgDemoService.save(jeecgDemo);
                    this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
                }
            }
        }

        return j;
    }
}
