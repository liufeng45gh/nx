//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.system.controller.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.NumberComparator;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.pojo.base.TSDataRule;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSIcon;
import org.jeecgframework.web.system.pojo.base.TSOperation;
import org.jeecgframework.web.system.pojo.base.TSRoleFunction;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/functionController"})
public class FunctionController extends BaseController {
    private static final Logger logger = Logger.getLogger(FunctionController.class);
    private UserService userService;
    private SystemService systemService;

    public FunctionController() {
    }

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    public UserService getUserService() {
        return this.userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            params = {"function"}
    )
    public ModelAndView function() {
        return new ModelAndView("system/function/functionList");
    }

    @RequestMapping(
            params = {"operation"}
    )
    public ModelAndView operation(HttpServletRequest request, String functionId) {
        request.setAttribute("functionId", functionId);
        return new ModelAndView("system/operation/operationList");
    }

    @RequestMapping(
            params = {"dataRule"}
    )
    public ModelAndView operationData(HttpServletRequest request, String functionId) {
        request.setAttribute("functionId", functionId);
        return new ModelAndView("system/dataRule/ruleDataList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSFunction.class, dataGrid);
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"opdategrid"}
    )
    public void opdategrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSOperation.class, dataGrid);
        String functionId = oConvertUtils.getString(request.getParameter("functionId"));
        cq.eq("TSFunction.id", functionId);
        cq.add();
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"del"}
    )
    @ResponseBody
    public AjaxJson del(TSFunction function, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        function = (TSFunction)this.systemService.getEntity(TSFunction.class, function.getId());
        message = MutiLangUtil.paramDelSuccess("common.menu");
        this.systemService.updateBySqlString("delete from t_s_role_function where functionid='" + function.getId() + "'");

        try {
            this.systemService.delete(function);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = MutiLangUtil.getMutiLangInstance().getLang("common.menu.del.fail");
        }

        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"delop"}
    )
    @ResponseBody
    public AjaxJson delop(TSOperation operation, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        operation = (TSOperation)this.systemService.getEntity(TSOperation.class, operation.getId());
        message = MutiLangUtil.paramDelSuccess("common.operation");
        this.userService.delete(operation);
        String operationId = operation.getId();
        String hql = "from TSRoleFunction rolefun where rolefun.operation like '%" + operationId + "%'";
        List<TSRoleFunction> roleFunctions = this.userService.findByQueryString(hql);
        Iterator var9 = roleFunctions.iterator();

        while(var9.hasNext()) {
            TSRoleFunction roleFunction = (TSRoleFunction)var9.next();
            String newOper = roleFunction.getOperation().replace(operationId + ",", "");
            if(roleFunction.getOperation().length() == newOper.length()) {
                newOper = roleFunction.getOperation().replace(operationId, "");
            }

            roleFunction.setOperation(newOper);
            this.userService.updateEntitie(roleFunction);
        }

        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        j.setMsg(message);
        return j;
    }

    private void updateSubFunction(List<TSFunction> subFunction, TSFunction parent) {
        if(subFunction.size() != 0) {
            Iterator var4 = subFunction.iterator();

            while(var4.hasNext()) {
                TSFunction tsFunction = (TSFunction)var4.next();
                tsFunction.setFunctionLevel(Short.valueOf(String.valueOf(parent.getFunctionLevel().shortValue() + 1)));
                this.systemService.saveOrUpdate(tsFunction);
                List<TSFunction> subFunction1 = this.systemService.findByProperty(TSFunction.class, "TSFunction.id", tsFunction.getId());
                this.updateSubFunction(subFunction1, tsFunction);
            }

        }
    }

    @RequestMapping(
            params = {"saveFunction"}
    )
    @ResponseBody
    public AjaxJson saveFunction(TSFunction function, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        function.setFunctionUrl(function.getFunctionUrl().trim());
        String functionOrder = function.getFunctionOrder();
        if(StringUtils.isEmpty(functionOrder)) {
            function.setFunctionOrder("0");
        }

        if(function.getTSFunction().getId().equals("")) {
            function.setTSFunction((TSFunction)null);
        } else {
            TSFunction parent = (TSFunction)this.systemService.getEntity(TSFunction.class, function.getTSFunction().getId());
            function.setFunctionLevel(Short.valueOf(String.valueOf(parent.getFunctionLevel().shortValue() + 1)));
        }

        List functionList;
        if(StringUtil.isNotEmpty(function.getId())) {
            message = MutiLangUtil.paramUpdSuccess("common.menu");
            this.userService.saveOrUpdate(function);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
            functionList = this.systemService.findByProperty(TSFunction.class, "TSFunction.id", function.getId());
            this.updateSubFunction(functionList, function);
            this.systemService.flushRoleFunciton(function.getId(), function);
        } else {
            if(function.getFunctionLevel().equals(Globals.Function_Leave_ONE)) {
                functionList = this.systemService.findByProperty(TSFunction.class, "functionLevel", Globals.Function_Leave_ONE);
                function.setFunctionOrder(function.getFunctionOrder());
            } else {
                functionList = this.systemService.findByProperty(TSFunction.class, "functionLevel", Globals.Function_Leave_TWO);
                function.setFunctionOrder(function.getFunctionOrder());
            }

            message = MutiLangUtil.paramAddSuccess("common.menu");
            this.systemService.save(function);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"saveop"}
    )
    @ResponseBody
    public AjaxJson saveop(TSOperation operation, HttpServletRequest request) {
        String message = null;
        String pid = request.getParameter("TSFunction.id");
        if(pid.equals("")) {
            operation.setTSFunction((TSFunction)null);
        }

        AjaxJson j = new AjaxJson();
        if(StringUtil.isNotEmpty(operation.getId())) {
            message = MutiLangUtil.paramUpdSuccess("common.operation");
            this.userService.saveOrUpdate(operation);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } else {
            message = MutiLangUtil.paramAddSuccess("common.operation");
            this.userService.save(operation);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"addorupdate"}
    )
    public ModelAndView addorupdate(TSFunction function, HttpServletRequest req) {
        String functionid = req.getParameter("id");
        List<TSFunction> fuinctionlist = this.systemService.getList(TSFunction.class);
        req.setAttribute("flist", fuinctionlist);
        List<TSIcon> iconlist = this.systemService.findByQueryString("from TSIcon where iconType != 3");
        req.setAttribute("iconlist", iconlist);
        List<TSIcon> iconDeskList = this.systemService.findByQueryString("from TSIcon where iconType = 3");
        req.setAttribute("iconDeskList", iconDeskList);
        if(functionid != null) {
            function = (TSFunction)this.systemService.getEntity(TSFunction.class, functionid);
            req.setAttribute("function", function);
        }

        if(function.getTSFunction() != null && function.getTSFunction().getId() != null) {
            function.setFunctionLevel((short) 1);
            function.setTSFunction((TSFunction)this.systemService.getEntity(TSFunction.class, function.getTSFunction().getId()));
            req.setAttribute("function", function);
        }

        return new ModelAndView("system/function/function");
    }

    @RequestMapping(
            params = {"addorupdateop"}
    )
    public ModelAndView addorupdateop(TSOperation operation, HttpServletRequest req) {
        List<TSIcon> iconlist = this.systemService.getList(TSIcon.class);
        req.setAttribute("iconlist", iconlist);
        if(operation.getId() != null) {
            operation = (TSOperation)this.systemService.getEntity(TSOperation.class, operation.getId());
            req.setAttribute("operation", operation);
        }

        String functionId = oConvertUtils.getString(req.getParameter("functionId"));
        req.setAttribute("functionId", functionId);
        return new ModelAndView("system/operation/operation");
    }

    @RequestMapping(
            params = {"functionGrid"}
    )
    @ResponseBody
    public List<TreeGrid> functionGrid(HttpServletRequest request, TreeGrid treegrid) {
        CriteriaQuery cq = new CriteriaQuery(TSFunction.class);
        String selfId = request.getParameter("selfId");
        if(selfId != null) {
            cq.notEq("id", selfId);
        }

        if(treegrid.getId() != null) {
            cq.eq("TSFunction.id", treegrid.getId());
        }

        if(treegrid.getId() == null) {
            cq.isNull("TSFunction");
        }

        cq.notEq("id", "2c98d8815a1b7b63015a1b9fb7420012");
        cq.notEq("id", "8a8ab0b246dc81120146dc8180d7001c");
        cq.notEq("id", "8a8ab0b246dc81120146dc8180ce0019");
        cq.addOrder("functionOrder", SortDirection.asc);
        cq.add();
        cq = HqlGenerateUtil.getDataAuthorConditionHql(cq, new TSFunction());
        cq.add();
        List<TSFunction> functionList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        Collections.sort(functionList, new NumberComparator());
        new ArrayList();
        TreeGridModel treeGridModel = new TreeGridModel();
        treeGridModel.setIcon("TSIcon_iconPath");
        treeGridModel.setTextField("functionName");
        treeGridModel.setParentText("TSFunction_functionName");
        treeGridModel.setParentId("TSFunction_id");
        treeGridModel.setSrc("functionUrl");
        treeGridModel.setIdField("id");
        treeGridModel.setChildList("TSFunctions");
        treeGridModel.setOrder("functionOrder");
        treeGridModel.setFunctionType("functionType");
        List<TreeGrid> treeGrids = this.systemService.treegrid(functionList, treeGridModel);
        MutiLangUtil.setMutiTree(treeGrids);
        return treeGrids;
    }

    @RequestMapping(
            params = {"functionList"}
    )
    @ResponseBody
    public void functionList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSFunction.class, dataGrid);
        String id = oConvertUtils.getString(request.getParameter("id"));
        cq.isNull("TSFunction");
        if(id != null) {
            cq.eq("TSFunction.id", id);
        }

        cq.add();
        List<TSFunction> functionList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        new ArrayList();
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"setPFunction"}
    )
    @ResponseBody
    public List<ComboTree> setPFunction(HttpServletRequest request, ComboTree comboTree) {
        CriteriaQuery cq = new CriteriaQuery(TSFunction.class);
        if(request.getParameter("selfId") != null) {
            cq.notEq("id", request.getParameter("selfId"));
        }

        if(comboTree.getId() != null) {
            cq.eq("TSFunction.id", comboTree.getId());
        }

        if(comboTree.getId() == null) {
            cq.isNull("TSFunction");
        }

        cq.add();
        List<TSFunction> functionList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        new ArrayList();
        ComboTreeModel comboTreeModel = new ComboTreeModel("id", "functionName", "TSFunctions");
        List<ComboTree> comboTrees = this.systemService.ComboTree(functionList, comboTreeModel, (List)null, false);
        MutiLangUtil.setMutiTree(comboTrees);
        return comboTrees;
    }

    @RequestMapping(
            params = {"searchApp"}
    )
    public ModelAndView searchApp(TSFunction function, HttpServletRequest req) {
        String name = req.getParameter("name");
        String menuListMap = "";
        CriteriaQuery cq = new CriteriaQuery(TSFunction.class);
        cq.notEq("functionLevel", Short.valueOf("0"));
        if(name != null && !"".equals(name)) {
            String name1 = "%" + name + "%";
            cq.like("functionName", name1);
        } else {
            cq.isNull("TSFunction");
        }

        cq.add();
        List<TSFunction> functionList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        if(functionList.size() > 0 && functionList != null) {
            for(int i = 0; i < functionList.size(); ++i) {
                String icon = "";
                if(!"".equals(((TSFunction)functionList.get(i)).getTSIconDesk()) && ((TSFunction)functionList.get(i)).getTSIconDesk() != null) {
                    icon = ((TSFunction)functionList.get(i)).getTSIconDesk().getIconPath();
                } else {
                    icon = "plug-in/sliding/icon/default.png";
                }

                menuListMap = menuListMap + "<div type='" + i + 1 + "' class='menuSearch_Info' id='" + ((TSFunction)functionList.get(i)).getId() + "' title='" + ((TSFunction)functionList.get(i)).getFunctionName() + "' url='" + ((TSFunction)functionList.get(i)).getFunctionUrl() + "' icon='" + icon + "' style='float:left;left: 0px; top: 0px;margin-left: 30px;margin-top: 20px;'>" + "<div ><img alt='" + ((TSFunction)functionList.get(i)).getFunctionName() + "' src='" + icon + "'></div>" + "<div class='appButton_appName_inner1' style='color:#333333;'>" + ((TSFunction)functionList.get(i)).getFunctionName() + "</div>" + "<div class='appButton_appName_inner_right1'></div>" + "</div>";
            }
        } else {
            menuListMap = menuListMap + "很遗憾，在系统中没有检索到与“" + name + "”相关的信息！";
        }

        req.setAttribute("menuListMap", menuListMap);
        return new ModelAndView("system/function/menuAppList");
    }

    @RequestMapping(
            params = {"addorupdaterule"}
    )
    public ModelAndView addorupdaterule(TSDataRule operation, HttpServletRequest req) {
        List<TSIcon> iconlist = this.systemService.getList(TSIcon.class);
        req.setAttribute("iconlist", iconlist);
        if(operation.getId() != null) {
            operation = (TSDataRule)this.systemService.getEntity(TSDataRule.class, operation.getId());
            req.setAttribute("operation", operation);
        }

        String functionId = oConvertUtils.getString(req.getParameter("functionId"));
        req.setAttribute("functionId", functionId);
        return new ModelAndView("system/dataRule/ruleData");
    }

    @RequestMapping(
            params = {"ruledategrid"}
    )
    public void ruledategrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSDataRule.class, dataGrid);
        String functionId = oConvertUtils.getString(request.getParameter("functionId"));
        cq.eq("TSFunction.id", functionId);
        cq.add();
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"delrule"}
    )
    @ResponseBody
    public AjaxJson delrule(TSDataRule operation, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        operation = (TSDataRule)this.systemService.getEntity(TSDataRule.class, operation.getId());
        message = MutiLangUtil.paramDelSuccess("common.operation");
        this.userService.delete(operation);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"saverule"}
    )
    @ResponseBody
    public AjaxJson saverule(TSDataRule operation, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        if(StringUtil.isNotEmpty(operation.getId())) {
            message = MutiLangUtil.paramUpdSuccess("common.operation");
            this.userService.saveOrUpdate(operation);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } else if(this.justHaveDataRule(operation) == 0) {
            message = MutiLangUtil.paramAddSuccess("common.operation");
            this.userService.save(operation);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } else {
            message = "操作 字段规则已存在";
        }

        j.setMsg(message);
        return j;
    }

    public int justHaveDataRule(TSDataRule dataRule) {
        String sql = "SELECT id FROM t_s_data_rule WHERE functionId='" + dataRule.getTSFunction().getId() + "' AND rule_column='" + dataRule.getRuleColumn() + "' AND rule_conditions='" + dataRule.getRuleConditions() + "'";
        List<String> hasOperList = this.systemService.findListbySql(sql);
        return hasOperList.size();
    }
}
