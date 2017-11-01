//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.demo.controller.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.pojo.base.TSDemo;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

@Controller
@RequestMapping({"/demoController"})
public class DemoController extends BaseController {
    private static final Logger logger = Logger.getLogger(DemoController.class);
    private SystemService systemService;

    public DemoController() {
    }

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    @RequestMapping(
            params = {"aorudemo"}
    )
    public ModelAndView aorudemo(TSDemo demo, HttpServletRequest request) {
        String type = oConvertUtils.getString(request.getParameter("type"));
        if(demo.getId() != null) {
            demo = (TSDemo)this.systemService.getEntity(TSDemo.class, demo.getId());
            request.setAttribute("demo", demo);
        }

        return type.equals("table")?new ModelAndView("jeecg/demo/base/tabledemo"):(type.equals("tableupdate")?new ModelAndView("jeecg/demo/base/demoTab"):new ModelAndView("jeecg/demo/base/demo"));
    }

    @RequestMapping(
            params = {"pDemoList"}
    )
    @ResponseBody
    public List<ComboTree> pDemoList(HttpServletRequest request, ComboTree comboTree) {
        CriteriaQuery cq = new CriteriaQuery(TSDemo.class);
        if(comboTree.getId() != null) {
            cq.eq("TSDemo.id", comboTree.getId());
        }

        if(comboTree.getId() == null) {
            cq.isNull("TSDemo");
        }

        cq.add();
        List<TSDemo> demoList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        new ArrayList();
        ComboTreeModel comboTreeModel = new ComboTreeModel("id", "demotitle", "tsDemos", "demourl");
        List<ComboTree> comboTrees = this.systemService.ComboTree(demoList, comboTreeModel, (List)null, false);
        return comboTrees;
    }

    @RequestMapping(
            params = {"demoTurn"}
    )
    @ResponseBody
    public String demoTurn(String id) {
        String code = ((TSDemo)this.systemService.get(TSDemo.class, id)).getDemocode();
        return HtmlUtils.htmlUnescape(code);
    }

    @RequestMapping(
            params = {"demoIframe"}
    )
    public ModelAndView demoIframe(HttpServletRequest request) {
        CriteriaQuery cq = new CriteriaQuery(TSDemo.class);
        cq.isNull("TSDemo.id");
        cq.add();
        List<TSDemo> demoList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        request.setAttribute("demoList", demoList);
        return new ModelAndView("jeecg/demo/base/demoIframe");
    }

    @RequestMapping(
            params = {"demoList"}
    )
    public ModelAndView demoList(HttpServletRequest request) {
        return new ModelAndView("jeecg/demo/base/demoList");
    }

    @RequestMapping(
            params = {"demoGrid"}
    )
    @ResponseBody
    public List<TreeGrid> demoGrid(HttpServletRequest request, TreeGrid treegrid) {
        CriteriaQuery cq = new CriteriaQuery(TSDemo.class);
        if(treegrid.getId() != null) {
            cq.eq("TSDemo.id", treegrid.getId());
        }

        if(treegrid.getId() == null) {
            cq.isNull("TSDemo");
        }

        cq.add();
        List<TSDemo> demoList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        TreeGridModel treeGridModel = new TreeGridModel();
        treeGridModel.setTextField("demotitle");
        treeGridModel.setParentText("TSDemo_demotitle");
        treeGridModel.setParentId("TSDemo_id");
        treeGridModel.setSrc("demourl");
        treeGridModel.setIdField("id");
        treeGridModel.setChildList("tsDemos");
        List<TreeGrid> treeGrids = this.systemService.treegrid(demoList, treeGridModel);
        return treeGrids;
    }

    @RequestMapping(
            params = {"demoCode"}
    )
    public ModelAndView demoCode(TSDemo demo, HttpServletRequest request) {
        List<TSDemo> list = this.systemService.getList(TSDemo.class);
        demo = (TSDemo)list.get(0);
        request.setAttribute("demo", demo);
        return new ModelAndView("jeecg/demo/base/democode");
    }

    @RequestMapping(
            params = {"getDemo"}
    )
    @ResponseBody
    public AjaxJson getDemo(HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        String id = StringUtil.getEncodePra(req.getParameter("id"));
        String floor = "";
        if(StringUtil.isNotEmpty(id)) {
            if("ThreeLevelLinkage".equals(id)) {
                floor = floor + "省：<select name=\"province\" id=\"provinceid\"></select>&nbsp;&nbsp;";
                floor = floor + "市：<select name=\"city\" id=\"cityid\"></select>&nbsp;&nbsp;";
                floor = floor + "县：<select name=\"county\" id=\"countyid\"></select>&nbsp;&nbsp;";
            } else {
                CriteriaQuery cq = new CriteriaQuery(TSFunction.class);
                cq.eq("TSFunction.id", id);
                cq.add();
                List<TSFunction> functions = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
                TSFunction function;
                if(functions.size() > 0) {
                    for(Iterator var8 = functions.iterator(); var8.hasNext(); floor = floor + "<input type=\"checkbox\"  name=\"floornum\" id=\"floornum\" value=\"" + function.getId() + "\">" + MutiLangUtil.getMutiLangInstance().getLang(function.getFunctionName()) + "&nbsp;&nbsp;") {
                        function = (TSFunction)var8.next();
                    }
                } else {
                    floor = floor + "没有子项目!";
                }
            }
        }

        j.setMsg(floor);
        return j;
    }

    @RequestMapping(
            params = {"uploadTabs"}
    )
    public ModelAndView uploadTabs(HttpServletRequest request) {
        return new ModelAndView("jeecg/demo/base/upload/uploadTabs");
    }

    @RequestMapping(
            params = {"imgViewTabs"}
    )
    public ModelAndView imgViewTabs(HttpServletRequest request) {
        return new ModelAndView("jeecg/demo/base/picview/imgViewTabs");
    }

    @RequestMapping(
            params = {"formTabs"}
    )
    public ModelAndView formTabs(HttpServletRequest request) {
        return new ModelAndView("jeecg/demo/base/formvalid/formTabs");
    }

    @RequestMapping(
            params = {"templeteTabs"}
    )
    public ModelAndView templeteTabs(HttpServletRequest request) {
        return new ModelAndView("jeecg/demo/base/template/templateiframe");
    }

    @RequestMapping(
            params = {"autoupload"}
    )
    public ModelAndView autoupload(HttpServletRequest request) {
        String turn = oConvertUtils.getString(request.getParameter("turn"));
        return new ModelAndView("jeecg/demo/base/" + turn);
    }

    @RequestMapping(
            params = {"select"}
    )
    public ModelAndView select(HttpServletRequest request) {
        CriteriaQuery cq2 = new CriteriaQuery(TSFunction.class);
        cq2.eq("functionLevel", Globals.Function_Leave_ONE);
        cq2.add();
        List<TSFunction> funList = this.systemService.getListByCriteriaQuery(cq2, Boolean.valueOf(true));
        request.setAttribute("funList", funList);
        return new ModelAndView("jeecg/demo/base/AJAX/select");
    }

    @RequestMapping(
            params = {"dictSelect"}
    )
    public ModelAndView dictSelect(HttpServletRequest request) {
        request.setAttribute("process", "default");
        return new ModelAndView("jeecg/demo/base/dict/dictSelect");
    }

    @RequestMapping(
            params = {"mapDemo"}
    )
    public ModelAndView mapDemo(HttpServletRequest request) {
        return new ModelAndView("jeecg/demo/base/map/mapDemo2");
    }

    @RequestMapping(
            params = {"saveDemo"}
    )
    @ResponseBody
    public AjaxJson saveDemo(TSDemo demo, HttpServletRequest request) throws Exception {
        String message = null;
        AjaxJson j = new AjaxJson();
        if(!StringUtil.isEmpty(demo.getId())) {
            message = "Demo维护例子: " + demo.getDemotitle() + "被更新成功";
            TSDemo entity = (TSDemo)this.systemService.get(TSDemo.class, demo.getId());
            MyBeanUtils.copyBeanNotNull2Bean(demo, entity);
            if(demo.getTSDemo() == null || StringUtil.isEmpty(demo.getTSDemo().getId())) {
                entity.setTSDemo((TSDemo)null);
            }

            this.systemService.saveOrUpdate(entity);
        } else {
            message = "Demo例子: " + demo.getDemotitle() + "被添加成功";
            if(demo.getTSDemo() == null || StringUtil.isEmpty(demo.getTSDemo().getId())) {
                demo.setTSDemo((TSDemo)null);
            }

            this.systemService.save(demo);
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"delDemo"}
    )
    @ResponseBody
    public AjaxJson del(TSDemo demo, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        demo = (TSDemo)this.systemService.getEntity(TSDemo.class, demo.getId());
        message = "Demo: " + demo.getDemotitle() + "被删除 成功";
        this.systemService.delete(demo);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"demoLayoutList"}
    )
    public ModelAndView demoLayout(HttpServletRequest request) {
        CriteriaQuery cq = new CriteriaQuery(TSDemo.class);
        cq.isNull("TSDemo.id");
        cq.add();
        List<TSDemo> demoList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        request.setAttribute("demoList", demoList);
        return new ModelAndView("jeecg/demo/base/layout/demoLayoutList");
    }

    @RequestMapping(
            params = {"demoLayout"}
    )
    public ModelAndView aorudemoLayout(TSDemo demo, HttpServletRequest request) {
        if(demo.getId() != null) {
            demo = (TSDemo)this.systemService.getEntity(TSDemo.class, demo.getId());
            request.setAttribute("demo", demo);
        }

        return new ModelAndView("jeecg/demo/base/layout/demoLayout");
    }

    @RequestMapping(
            params = {"eSign"}
    )
    public ModelAndView eSignDemo(HttpServletRequest request) {
        return new ModelAndView("jeecg/demo/test/zsign");
    }
}
