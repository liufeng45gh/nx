//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.system.controller.core;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.pojo.base.TSTerritory;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/territoryController"})
public class TerritoryController extends BaseController {
    @Autowired
    private SystemService systemService;

    public TerritoryController() {
    }

    @RequestMapping(
            params = {"territory"}
    )
    public ModelAndView function() {
        return new ModelAndView("system/territory/territoryList");
    }

    @RequestMapping(
            params = {"territoryGrid"}
    )
    @ResponseBody
    public List<TreeGrid> territoryGrid(HttpServletRequest request, TreeGrid treegrid) {
        CriteriaQuery cq = new CriteriaQuery(TSTerritory.class);
        if(treegrid.getId() != null) {
            cq.eq("TSTerritory.id", treegrid.getId());
        }

        if(treegrid.getId() == null) {
            cq.eq("TSTerritory.id", "1");
        }

        cq.addOrder("territorySort", SortDirection.asc);
        cq.add();
        List<TSTerritory> territoryList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        new ArrayList();
        TreeGridModel treeGridModel = new TreeGridModel();
        treeGridModel.setIcon("");
        treeGridModel.setTextField("territoryName");
        treeGridModel.setParentText("TSTerritory_territoryName");
        treeGridModel.setParentId("TSTerritory_id");
        treeGridModel.setSrc("territoryCode");
        treeGridModel.setIdField("id");
        treeGridModel.setChildList("TSTerritorys");
        treeGridModel.setOrder("territorySort");
        List<TreeGrid> treeGrids = this.systemService.treegrid(territoryList, treeGridModel);
        return treeGrids;
    }

    @RequestMapping(
            params = {"addorupdate"}
    )
    public ModelAndView addorupdate(TSTerritory territory, HttpServletRequest req) {
        String functionid = req.getParameter("id");
        if(functionid != null) {
            territory = (TSTerritory)this.systemService.getEntity(TSTerritory.class, functionid);
            req.setAttribute("territory", territory);
        }

        if(territory.getTSTerritory() != null && territory.getTSTerritory().getId() != null) {
            territory.setTSTerritory((TSTerritory)this.systemService.getEntity(TSTerritory.class, territory.getTSTerritory().getId()));
            req.setAttribute("territory", territory);
        }

        return new ModelAndView("system/territory/territory");
    }

    @RequestMapping(
            params = {"setPTerritory"}
    )
    @ResponseBody
    public List<ComboTree> setPTerritory(HttpServletRequest request, ComboTree comboTree) {
        CriteriaQuery cq = new CriteriaQuery(TSTerritory.class);
        if(comboTree.getId() != null) {
            cq.eq("TSTerritory.id", comboTree.getId());
        }

        if(comboTree.getId() == null) {
            cq.isNull("TSTerritory");
        }

        cq.add();
        List<TSTerritory> territoryList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        new ArrayList();
        ComboTreeModel comboTreeModel = new ComboTreeModel("id", "territoryName", "TSTerritorys");
        List<ComboTree> comboTrees = this.systemService.ComboTree(territoryList, comboTreeModel, (List)null, false);
        return comboTrees;
    }

    @RequestMapping(
            params = {"saveTerritory"}
    )
    @ResponseBody
    public AjaxJson saveTerritory(TSTerritory territory, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        String functionOrder = territory.getTerritorySort();
        if(StringUtils.isEmpty(functionOrder)) {
            territory.setTerritorySort("0");
        }

        if(territory.getTSTerritory().getId().equals("")) {
            territory.setTSTerritory((TSTerritory)null);
        } else {
            TSTerritory parent = (TSTerritory)this.systemService.getEntity(TSTerritory.class, territory.getTSTerritory().getId());
            territory.setTerritoryLevel(Short.valueOf(String.valueOf(parent.getTerritoryLevel().shortValue() + 1)));
        }

        if(StringUtil.isNotEmpty(territory.getId())) {
            message = "地域: " + territory.getTerritoryName() + "被更新成功";
            this.systemService.saveOrUpdate(territory);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
            message = MutiLangUtil.paramUpdSuccess("common.area");
        } else {
            territory.setTerritorySort(territory.getTerritorySort());
            message = "地域: " + territory.getTerritoryName() + "被添加成功";
            this.systemService.save(territory);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
            message = MutiLangUtil.paramAddSuccess("common.area");
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"del"}
    )
    @ResponseBody
    public AjaxJson del(TSTerritory territory, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        territory = (TSTerritory)this.systemService.getEntity(TSTerritory.class, territory.getId());
        message = "地域: " + territory.getTerritoryName() + "被删除成功";
        this.systemService.delete(territory);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        message = MutiLangUtil.paramDelSuccess("common.area");
        j.setMsg(message);
        return j;
    }
}
