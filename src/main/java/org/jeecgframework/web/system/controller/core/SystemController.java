//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.system.controller.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.common.model.json.ValidForm;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.extend.hqlsearch.parse.ObjectParseUtil;
import org.jeecgframework.core.extend.hqlsearch.parse.PageValueConvertRuleEnum;
import org.jeecgframework.core.extend.hqlsearch.parse.vo.HqlRuleEnum;
import org.jeecgframework.core.util.JSONHelper;
import org.jeecgframework.core.util.ListUtils;
import org.jeecgframework.core.util.MutiLangSqlCriteriaUtil;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.SetListSort;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.manager.ClientSort;
import org.jeecgframework.web.system.pojo.base.Client;
import org.jeecgframework.web.system.pojo.base.DataLogDiff;
import org.jeecgframework.web.system.pojo.base.TSDatalogEntity;
import org.jeecgframework.web.system.pojo.base.TSDocument;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleFunction;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.pojo.base.TSVersion;
import org.jeecgframework.web.system.service.MutiLangServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ssb.warmline.business.commons.utils.DateUtils;

@Controller
@RequestMapping({"/systemController"})
public class SystemController extends BaseController {
    private static final Logger logger = Logger.getLogger(SystemController.class);
    private UserService userService;
    private SystemService systemService;
    private MutiLangServiceI mutiLangService;

    public SystemController() {
    }

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    @Autowired
    public void setMutiLangService(MutiLangServiceI mutiLangService) {
        this.mutiLangService = mutiLangService;
    }

    public UserService getUserService() {
        return this.userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            params = {"druid"}
    )
    public ModelAndView druid() {
        return new ModelAndView(new RedirectView("druid/index.html"));
    }

    @RequestMapping(
            params = {"typeGroupTabs"}
    )
    public ModelAndView typeGroupTabs(HttpServletRequest request) {
        List<TSTypegroup> typegroupList = this.systemService.loadAll(TSTypegroup.class);
        request.setAttribute("typegroupList", typegroupList);
        return new ModelAndView("system/type/typeGroupTabs");
    }

    @RequestMapping(
            params = {"typeGroupList"}
    )
    public ModelAndView typeGroupList(HttpServletRequest request) {
        return new ModelAndView("system/type/typeGroupList");
    }

    @RequestMapping(
            params = {"typeList"}
    )
    public ModelAndView typeList(HttpServletRequest request) {
        String typegroupid = request.getParameter("typegroupid");
        TSTypegroup typegroup = (TSTypegroup)this.systemService.getEntity(TSTypegroup.class, typegroupid);
        request.setAttribute("typegroup", typegroup);
        return new ModelAndView("system/type/typeList");
    }

    @RequestMapping(
            params = {"typeGroupGrid"}
    )
    public void typeGroupGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, TSTypegroup typegroup) {
        CriteriaQuery cq = new CriteriaQuery(TSTypegroup.class, dataGrid);
        String typegroupname = request.getParameter("typegroupname");
        if(typegroupname != null && typegroupname.trim().length() > 0) {
            typegroupname = typegroupname.trim();
            List<String> typegroupnameKeyList = this.systemService.findByQueryString("select typegroupname from TSTypegroup");
            if(typegroupname.lastIndexOf("*") == -1) {
                typegroupname = typegroupname + "*";
            }

            MutiLangSqlCriteriaUtil.assembleCondition(typegroupnameKeyList, cq, "typegroupname", typegroupname);
        }

        this.systemService.getDataGridReturn(cq, true);
        MutiLangUtil.setMutiLangValueForList(dataGrid.getResults(), new String[]{"typegroupname"});
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"formTree"}
    )
    @ResponseBody
    public List<ComboTree> formTree(HttpServletRequest request, ComboTree rootCombotree) {
        String typegroupCode = request.getParameter("typegroupCode");
        TSTypegroup group = (TSTypegroup)ResourceUtil.allTypeGroups.get(typegroupCode.toLowerCase());
        List<ComboTree> comboTrees = new ArrayList();
        Iterator var7 = ((List)ResourceUtil.allTypes.get(typegroupCode.toLowerCase())).iterator();

        while(var7.hasNext()) {
            TSType tsType = (TSType)var7.next();
            ComboTree combotree = new ComboTree();
            combotree.setId(tsType.getTypecode());
            combotree.setText(tsType.getTypename());
            comboTrees.add(combotree);
        }

        rootCombotree.setId(group.getTypegroupcode());
        rootCombotree.setText(group.getTypegroupname());
        rootCombotree.setChecked(Boolean.valueOf(false));
        rootCombotree.setChildren(comboTrees);
        return new ArrayList<ComboTree>() {
            {
                //this.add(var2);
            }
        };
    }

    @RequestMapping(
            params = {"typeGrid"}
    )
    public void typeGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        String typegroupid = request.getParameter("typegroupid");
        String typename = request.getParameter("typename");
        CriteriaQuery cq = new CriteriaQuery(TSType.class, dataGrid);
        cq.eq("TSTypegroup.id", typegroupid);
        cq.like("typename", typename);
        cq.add();
        this.systemService.getDataGridReturn(cq, true);
        MutiLangUtil.setMutiLangValueForList(dataGrid.getResults(), new String[]{"typename"});
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"goTypeGrid"}
    )
    public ModelAndView goTypeGrid(HttpServletRequest request) {
        String typegroupid = request.getParameter("typegroupid");
        request.setAttribute("typegroupid", typegroupid);
        return new ModelAndView("system/type/typeListForTypegroup");
    }

    /** @deprecated */
    @RequestMapping(
            params = {"typeGridTree"}
    )
    @ResponseBody
    @Deprecated
    public List<TreeGrid> typeGridTree(HttpServletRequest request, TreeGrid treegrid) {
        List<TreeGrid> treeGrids = new ArrayList();
        CriteriaQuery cq;
        if(treegrid.getId() != null) {
            cq = new CriteriaQuery(TSType.class);
            cq.eq("TSTypegroup.id", treegrid.getId().substring(1));
            cq.add();
            List<TSType> typeList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
            Iterator var7 = typeList.iterator();

            while(var7.hasNext()) {
                TSType obj = (TSType)var7.next();
                TreeGrid treeNode = new TreeGrid();
                treeNode.setId("T" + obj.getId());
                treeNode.setText(obj.getTypename());
                treeNode.setCode(obj.getTypecode());
                treeGrids.add(treeNode);
            }
        } else {
            cq = new CriteriaQuery(TSTypegroup.class);
            String typegroupcode = request.getParameter("typegroupcode");
            if(typegroupcode != null) {
                HqlRuleEnum rule = PageValueConvertRuleEnum.convert(typegroupcode);
                Object value = PageValueConvertRuleEnum.replaceValue(rule, typegroupcode);
                ObjectParseUtil.addCriteria(cq, "typegroupcode", rule, value);
                cq.add();
            }

            String typegroupname = request.getParameter("typegroupname");
            List typeGroupList;
            if(typegroupname != null && typegroupname.trim().length() > 0) {
                typegroupname = typegroupname.trim();
                typeGroupList = this.systemService.findByQueryString("select typegroupname from TSTypegroup");
                MutiLangSqlCriteriaUtil.assembleCondition(typeGroupList, cq, "typegroupname", typegroupname);
            }

            typeGroupList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
            Iterator var9 = typeGroupList.iterator();

            while(var9.hasNext()) {
                TSTypegroup obj = (TSTypegroup)var9.next();
                TreeGrid treeNode = new TreeGrid();
                treeNode.setId("G" + obj.getId());
                treeNode.setText(obj.getTypegroupname());
                treeNode.setCode(obj.getTypegroupcode());
                treeNode.setState("closed");
                treeGrids.add(treeNode);
            }
        }

        MutiLangUtil.setMutiTree(treeGrids);
        return treeGrids;
    }

    @RequestMapping(
            params = {"delTypeGridTree"}
    )
    @ResponseBody
    public AjaxJson delTypeGridTree(String id, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        if(id.startsWith("G")) {
            TSTypegroup typegroup = (TSTypegroup)this.systemService.getEntity(TSTypegroup.class, id.substring(1));
            message = "数据字典分组: " + this.mutiLangService.getLang(typegroup.getTypegroupname()) + "被删除 成功";
            this.systemService.delete(typegroup);
        } else {
            TSType type = (TSType)this.systemService.getEntity(TSType.class, id.substring(1));
            message = "数据字典类型: " + this.mutiLangService.getLang(type.getTypename()) + "被删除 成功";
            this.systemService.delete(type);
        }

        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        this.systemService.refleshTypeGroupCach();
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"delTypeGroup"}
    )
    @ResponseBody
    public AjaxJson delTypeGroup(TSTypegroup typegroup, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        typegroup = (TSTypegroup)this.systemService.getEntity(TSTypegroup.class, typegroup.getId());
        message = "类型分组: " + this.mutiLangService.getLang(typegroup.getTypegroupname()) + " 被删除 成功";
        if(ListUtils.isNullOrEmpty(typegroup.getTSTypes())) {
            this.systemService.delete(typegroup);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            this.systemService.refleshTypeGroupCach();
        } else {
            message = "类型分组: " + this.mutiLangService.getLang(typegroup.getTypegroupname()) + " 下有类型信息，不能删除！";
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"delType"}
    )
    @ResponseBody
    public AjaxJson delType(TSType type, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        type = (TSType)this.systemService.getEntity(TSType.class, type.getId());
        message = "类型: " + this.mutiLangService.getLang(type.getTypename()) + "被删除 成功";
        this.systemService.delete(type);
        this.systemService.refleshTypesCach(type);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"checkTypeGroup"}
    )
    @ResponseBody
    public ValidForm checkTypeGroup(HttpServletRequest request) {
        ValidForm v = new ValidForm();
        String typegroupcode = oConvertUtils.getString(request.getParameter("param"));
        String code = oConvertUtils.getString(request.getParameter("code"));
        List<TSTypegroup> typegroups = this.systemService.findByProperty(TSTypegroup.class, "typegroupcode", typegroupcode);
        if(typegroups.size() > 0 && !code.equals(typegroupcode)) {
            v.setInfo("分组已存在");
            v.setStatus("n");
        }

        return v;
    }

    @RequestMapping(
            params = {"saveTypeGroup"}
    )
    @ResponseBody
    public AjaxJson saveTypeGroup(TSTypegroup typegroup, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        if(StringUtil.isNotEmpty(typegroup.getId())) {
            message = "类型分组: " + this.mutiLangService.getLang(typegroup.getTypegroupname()) + "被更新成功";
            this.userService.saveOrUpdate(typegroup);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } else {
            message = "类型分组: " + this.mutiLangService.getLang(typegroup.getTypegroupname()) + "被添加成功";
            this.userService.save(typegroup);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        }

        this.systemService.refleshTypeGroupCach();
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"checkType"}
    )
    @ResponseBody
    public ValidForm checkType(HttpServletRequest request) {
        ValidForm v = new ValidForm();
        String typecode = oConvertUtils.getString(request.getParameter("param"));
        String code = oConvertUtils.getString(request.getParameter("code"));
        String typeGroupCode = oConvertUtils.getString(request.getParameter("typeGroupCode"));
        StringBuilder hql = (new StringBuilder("FROM ")).append(TSType.class.getName()).append(" AS entity WHERE 1=1 ");
        hql.append(" AND entity.TSTypegroup.typegroupcode =  '").append(typeGroupCode).append("'");
        hql.append(" AND entity.typecode =  '").append(typecode).append("'");
        List<Object> types = this.systemService.findByQueryString(hql.toString());
        if(types.size() > 0 && !code.equals(typecode)) {
            v.setInfo("类型已存在");
            v.setStatus("n");
        }

        return v;
    }

    @RequestMapping(
            params = {"saveType"}
    )
    @ResponseBody
    public AjaxJson saveType(TSType type, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        if(StringUtil.isNotEmpty(type.getId())) {
            message = "类型: " + this.mutiLangService.getLang(type.getTypename()) + "被更新成功";
            this.userService.saveOrUpdate(type);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } else {
            message = "类型: " + this.mutiLangService.getLang(type.getTypename()) + "被添加成功";
            this.userService.save(type);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        }

        this.systemService.refleshTypesCach(type);
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"aouTypeGroup"}
    )
    public ModelAndView aouTypeGroup(TSTypegroup typegroup, HttpServletRequest req) {
        if(typegroup.getId() != null) {
            typegroup = (TSTypegroup)this.systemService.getEntity(TSTypegroup.class, typegroup.getId());
            req.setAttribute("typegroup", typegroup);
        }

        return new ModelAndView("system/type/typegroup");
    }

    @RequestMapping(
            params = {"addorupdateType"}
    )
    public ModelAndView addorupdateType(TSType type, HttpServletRequest req) {
        String typegroupid = req.getParameter("typegroupid");
        req.setAttribute("typegroupid", typegroupid);
        TSTypegroup typegroup = (TSTypegroup)this.systemService.findUniqueByProperty(TSTypegroup.class, "id", typegroupid);
        String typegroupname = typegroup.getTypegroupname();
        req.setAttribute("typegroupname", this.mutiLangService.getLang(typegroupname));
        if(StringUtil.isNotEmpty(type.getId())) {
            type = (TSType)this.systemService.getEntity(TSType.class, type.getId());
            req.setAttribute("type", type);
        }

        return new ModelAndView("system/type/type");
    }

    @RequestMapping(
            params = {"depart"}
    )
    public ModelAndView depart() {
        return new ModelAndView("system/depart/departList");
    }

    private synchronized String getMaxLocalCode(String parentCode) {
        if(oConvertUtils.isEmpty(parentCode)) {
            parentCode = "";
        }

        int localCodeLength = parentCode.length() + 3;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT org_code FROM t_s_depart");
        if(ResourceUtil.getJdbcUrl().indexOf("sqlserver") != -1) {
            sb.append(" where LEN(org_code) = ").append(localCodeLength);
        } else {
            sb.append(" where LENGTH(org_code) = ").append(localCodeLength);
        }

        if(oConvertUtils.isNotEmpty(parentCode)) {
            sb.append(" and  org_code like '").append(parentCode).append("%'");
        }

        sb.append(" ORDER BY org_code DESC");
        List<Map<String, Object>> objMapList = this.systemService.findForJdbc(sb.toString(), 1, 1);
        String returnCode = null;
        if(objMapList != null && objMapList.size() > 0) {
            returnCode = (String)((Map)objMapList.get(0)).get("org_code");
        }

        return returnCode;
    }

    @RequestMapping(
            params = {"role"}
    )
    public ModelAndView role() {
        return new ModelAndView("system/role/roleList");
    }

    @RequestMapping(
            params = {"datagridRole"}
    )
    public void datagridRole(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSRole.class, dataGrid);
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"delRole"}
    )
    @ResponseBody
    public AjaxJson delRole(TSRole role, String ids, HttpServletRequest request) {
        String message = null;
        message = "角色: " + role.getRoleName() + "被删除成功";
        AjaxJson j = new AjaxJson();
        role = (TSRole)this.systemService.getEntity(TSRole.class, role.getId());
        this.userService.delete(role);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"saveRole"}
    )
    @ResponseBody
    public AjaxJson saveRole(TSRole role, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        if(role.getId() != null) {
            message = "角色: " + role.getRoleName() + "被更新成功";
            this.userService.saveOrUpdate(role);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } else {
            message = "角色: " + role.getRoleName() + "被添加成功";
            this.userService.saveOrUpdate(role);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"fun"}
    )
    public ModelAndView fun(HttpServletRequest request) {
        Integer roleid = Integer.valueOf(oConvertUtils.getInt(request.getParameter("roleid"), 0));
        request.setAttribute("roleid", roleid);
        return new ModelAndView("system/role/roleList");
    }

    @RequestMapping(
            params = {"setAuthority"}
    )
    @ResponseBody
    public List<ComboTree> setAuthority(TSRole role, HttpServletRequest request, ComboTree comboTree) {
        CriteriaQuery cq = new CriteriaQuery(TSFunction.class);
        if(comboTree.getId() != null) {
            cq.eq("TFunction.functionid", Integer.valueOf(oConvertUtils.getInt(comboTree.getId(), 0)));
        }

        if(comboTree.getId() == null) {
            cq.isNull("TFunction");
        }

        cq.add();
        List<TSFunction> functionList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        new ArrayList();
        Integer roleid = Integer.valueOf(oConvertUtils.getInt(request.getParameter("roleid"), 0));
        List<TSFunction> loginActionlist = new ArrayList();
        role = (TSRole)this.systemService.get(TSRole.class, roleid);
        if(role != null) {
            List<TSRoleFunction> roleFunctionList = this.systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
            if(roleFunctionList.size() > 0) {
                Iterator var11 = roleFunctionList.iterator();

                while(var11.hasNext()) {
                    TSRoleFunction roleFunction = (TSRoleFunction)var11.next();
                    TSFunction function = roleFunction.getTSFunction();
                    loginActionlist.add(function);
                }
            }
        }

        ComboTreeModel comboTreeModel = new ComboTreeModel("id", "functionName", "TSFunctions");
        List<ComboTree> comboTrees = this.systemService.ComboTree(functionList, comboTreeModel, loginActionlist, false);
        return comboTrees;
    }

    @RequestMapping(
            params = {"updateAuthority"}
    )
    public String updateAuthority(HttpServletRequest request) {
        Integer roleid = Integer.valueOf(oConvertUtils.getInt(request.getParameter("roleid"), 0));
        String rolefunction = request.getParameter("rolefunctions");
        TSRole role = (TSRole)this.systemService.get(TSRole.class, roleid);
        List<TSRoleFunction> roleFunctionList = this.systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
        this.systemService.deleteAllEntitie(roleFunctionList);
        String[] roleFunctions = null;
        if(rolefunction != "") {
            roleFunctions = rolefunction.split(",");
            String[] var10 = roleFunctions;
            int var9 = roleFunctions.length;

            for(int var8 = 0; var8 < var9; ++var8) {
                String s = var10[var8];
                TSRoleFunction rf = new TSRoleFunction();
                TSFunction f = (TSFunction)this.systemService.get(TSFunction.class, Integer.valueOf(s));
                rf.setTSFunction(f);
                rf.setTSRole(role);
                this.systemService.save(rf);
            }
        }

        return "system/role/roleList";
    }

    @RequestMapping(
            params = {"addorupdateRole"}
    )
    public ModelAndView addorupdateRole(TSRole role, HttpServletRequest req) {
        if(role.getId() != null) {
            role = (TSRole)this.systemService.getEntity(TSRole.class, role.getId());
            req.setAttribute("role", role);
        }

        return new ModelAndView("system/role/role");
    }

    @RequestMapping(
            params = {"operate"}
    )
    public ModelAndView operate(HttpServletRequest request) {
        String roleid = request.getParameter("roleid");
        request.setAttribute("roleid", roleid);
        return new ModelAndView("system/role/functionList");
    }

    @RequestMapping(
            params = {"setOperate"}
    )
    @ResponseBody
    public List<TreeGrid> setOperate(HttpServletRequest request, TreeGrid treegrid) {
        String roleid = request.getParameter("roleid");
        CriteriaQuery cq = new CriteriaQuery(TSFunction.class);
        if(treegrid.getId() != null) {
            cq.eq("TFunction.functionid", Integer.valueOf(oConvertUtils.getInt(treegrid.getId(), 0)));
        }

        if(treegrid.getId() == null) {
            cq.isNull("TFunction");
        }

        cq.add();
        List<TSFunction> functionList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        new ArrayList();
        Collections.sort(functionList, new SetListSort());
        TreeGridModel treeGridModel = new TreeGridModel();
        treeGridModel.setRoleid(roleid);
        List<TreeGrid> treeGrids = this.systemService.treegrid(functionList, treeGridModel);
        return treeGrids;
    }

    @RequestMapping(
            params = {"saveOperate"}
    )
    @ResponseBody
    public AjaxJson saveOperate(HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String fop = request.getParameter("fp");
        String roleid = request.getParameter("roleid");
        this.clearp(roleid);
        String[] fun_op = fop.split(",");
        String aa = "";
        String bb = "";
        if(fun_op.length == 1) {
            bb = fun_op[0].split("_")[1];
            aa = fun_op[0].split("_")[0];
            this.savep(roleid, bb, aa);
        } else {
            for(int i = 0; i < fun_op.length; ++i) {
                String cc = fun_op[i].split("_")[0];
                if(i > 0 && bb.equals(fun_op[i].split("_")[1])) {
                    aa = aa + "," + cc;
                    if(i == fun_op.length - 1) {
                        this.savep(roleid, bb, aa);
                    }
                } else if(i > 0) {
                    this.savep(roleid, bb, aa);
                    aa = fun_op[i].split("_")[0];
                    if(i == fun_op.length - 1) {
                        bb = fun_op[i].split("_")[1];
                        this.savep(roleid, bb, aa);
                    }
                } else {
                    aa = fun_op[i].split("_")[0];
                }

                bb = fun_op[i].split("_")[1];
            }
        }

        return j;
    }

    public void savep(String roleid, String functionid, String ids) {
        String hql = "from TRoleFunction t where t.TSRole.id=" + roleid + " " + "and t.TFunction.functionid=" + functionid;
        TSRoleFunction rFunction = (TSRoleFunction)this.systemService.singleResult(hql);
        if(rFunction != null) {
            rFunction.setOperation(ids);
            this.systemService.saveOrUpdate(rFunction);
        }

    }

    public void clearp(String roleid) {
        String hql = "from TRoleFunction t where t.TSRole.id=" + roleid;
        List<TSRoleFunction> rFunctions = this.systemService.findByQueryString(hql);
        if(rFunctions.size() > 0) {
            Iterator var5 = rFunctions.iterator();

            while(var5.hasNext()) {
                TSRoleFunction tRoleFunction = (TSRoleFunction)var5.next();
                tRoleFunction.setOperation((String)null);
                this.systemService.saveOrUpdate(tRoleFunction);
            }
        }

    }

    @RequestMapping(
            params = {"versionList"}
    )
    public void versionList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSVersion.class, dataGrid);
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"delVersion"}
    )
    @ResponseBody
    public AjaxJson delVersion(TSVersion version, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        version = (TSVersion)this.systemService.getEntity(TSVersion.class, version.getId());
        message = "版本：" + version.getVersionName() + "被删除 成功";
        this.systemService.delete(version);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        return j;
    }

    @RequestMapping(
            params = {"addversion"}
    )
    public ModelAndView addversion(HttpServletRequest req) {
        return new ModelAndView("system/version/version");
    }

    @RequestMapping(
            params = {"saveVersion"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson saveVersion(HttpServletRequest request) throws Exception {
        AjaxJson j = new AjaxJson();
        TSVersion version = new TSVersion();
        String versionName = request.getParameter("versionName");
        String versionCode = request.getParameter("versionCode");
        version.setVersionCode(versionCode);
        version.setVersionName(versionName);
        this.systemService.save(version);
        j.setMsg("版本保存成功");
        return j;
    }

    @RequestMapping(
            params = {"documentList"}
    )
    public void documentList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSDocument.class, dataGrid);
        String typecode = oConvertUtils.getString(request.getParameter("typecode"));
        cq.createAlias("TSType", "TSType");
        cq.eq("TSType.typecode", typecode);
        cq.add();
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"delDocument"}
    )
    @ResponseBody
    public AjaxJson delDocument(TSDocument document, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        document = (TSDocument)this.systemService.getEntity(TSDocument.class, document.getId());
        message = document.getDocumentTitle() + "被删除成功";
        this.userService.delete(document);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        j.setSuccess(true);
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"addFiles"}
    )
    public ModelAndView addFiles(HttpServletRequest req) {
        return new ModelAndView("system/document/files");
    }

    @RequestMapping(
            params = {"editFiles"}
    )
    public ModelAndView editFiles(TSDocument doc, ModelMap map) {
        if(StringUtil.isNotEmpty(doc.getId())) {
            doc = (TSDocument)this.systemService.getEntity(TSDocument.class, doc.getId());
            map.put("doc", doc);
        }

        return new ModelAndView("system/document/files");
    }

    @RequestMapping(
            params = {"saveFiles"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson saveFiles(HttpServletRequest request, HttpServletResponse response, TSDocument document) {
        AjaxJson j = new AjaxJson();
        Map<String, Object> attributes = new HashMap();
        TSTypegroup tsTypegroup = this.systemService.getTypeGroup("fieltype", "文档分类");
        TSType tsType = this.systemService.getType("files", "附件", tsTypegroup);
        String fileKey = oConvertUtils.getString(request.getParameter("fileKey"));
        String documentTitle = oConvertUtils.getString(request.getParameter("documentTitle"));
        if(StringUtil.isNotEmpty(fileKey)) {
            document.setId(fileKey);
            document = (TSDocument)this.systemService.getEntity(TSDocument.class, fileKey);
            document.setDocumentTitle(documentTitle);
        }

        document.setSubclassname(MyClassLoader.getPackPath(document));
        document.setCreatedate(DateUtils.gettimestamp());
        document.setTSType(tsType);
        UploadFile uploadFile = new UploadFile(request, document);
        uploadFile.setCusPath("files");
        uploadFile.setSwfpath("swfpath");
        document = (TSDocument)this.systemService.uploadFile(uploadFile);
        attributes.put("url", document.getRealpath());
        attributes.put("fileKey", document.getId());
        attributes.put("name", document.getAttachmenttitle());
        attributes.put("viewhref", "commonController.do?objfileList&fileKey=" + document.getId());
        attributes.put("delurl", "commonController.do?delObjFile&fileKey=" + document.getId());
        j.setMsg("文件添加成功");
        j.setAttributes(attributes);
        return j;
    }

    @RequestMapping(
            params = {"datagridOnline"}
    )
    public void datagridOnline(Client tSOnline, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        List<Client> onlines = new ArrayList();
        onlines.addAll(ClientManager.getInstance().getAllClient());
        dataGrid.setTotal(onlines.size());
        dataGrid.setResults(this.getClinetList(onlines, dataGrid));
        TagUtil.datagrid(response, dataGrid);
    }

    private List<Client> getClinetList(List<Client> onlines, DataGrid dataGrid) {
        Collections.sort(onlines, new ClientSort());
        List<Client> result = new ArrayList();

        for(int i = (dataGrid.getPage() - 1) * dataGrid.getRows(); i < onlines.size() && i < dataGrid.getPage() * dataGrid.getRows(); ++i) {
            result.add((Client)onlines.get(i));
        }

        return result;
    }

    @RequestMapping(
            params = {"commonUpload"}
    )
    public ModelAndView commonUpload(HttpServletRequest req) {
        return new ModelAndView("common/upload/uploadView");
    }

    @RequestMapping(
            params = {"dataLogList"}
    )
    public ModelAndView dataLogList(HttpServletRequest request) {
        return new ModelAndView("system/dataLog/dataLogList");
    }

    @RequestMapping(
            params = {"datagridDataLog"}
    )
    public void dataLogDatagrid(TSDatalogEntity datalogEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSDatalogEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, datalogEntity, request.getParameterMap());
        cq.add();
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"popDataContent"}
    )
    public ModelAndView popDataContent(ModelMap modelMap, @RequestParam String id, HttpServletRequest request) {
        TSDatalogEntity datalogEntity = (TSDatalogEntity)this.systemService.get(TSDatalogEntity.class, id);
        modelMap.put("dataContent", datalogEntity.getDataContent());
        return new ModelAndView("system/dataLog/popDataContent");
    }

    @RequestMapping(
            params = {"dataDiff"}
    )
    public ModelAndView dataDiff(HttpServletRequest request) {
        return new ModelAndView("system/dataLog/dataDiff");
    }

    @RequestMapping(
            params = {"getDataVersion"}
    )
    @ResponseBody
    public AjaxJson getDataVersion(@RequestParam String tableName, @RequestParam String dataId) {
        AjaxJson j = new AjaxJson();
        String hql = "from TSDatalogEntity where tableName = ? and dataId = ? order by versionNumber desc";
        List<TSDatalogEntity> datalogEntities = this.systemService.findHql(hql, new Object[]{tableName, dataId});
        if(datalogEntities.size() > 0) {
            j.setObj(datalogEntities);
        }

        return j;
    }

    @RequestMapping(
            params = {"diffDataVersion"}
    )
    public ModelAndView diffDataVersion(HttpServletRequest request, @RequestParam String id1, @RequestParam String id2) {
        String hql1 = "from TSDatalogEntity where id = '" + id1 + "'";
        TSDatalogEntity datalogEntity1 = (TSDatalogEntity)this.systemService.singleResult(hql1);
        String hql2 = "from TSDatalogEntity where id = '" + id2 + "'";
        TSDatalogEntity datalogEntity2 = (TSDatalogEntity)this.systemService.singleResult(hql2);
        if(datalogEntity1 != null && datalogEntity2 != null) {
            Integer version1 = datalogEntity1.getVersionNumber();
            Integer version2 = datalogEntity2.getVersionNumber();
            Map<String, Object> map1 = null;
            Map<String, Object> map2 = null;
            if(version1.intValue() < version2.intValue()) {
                map1 = JSONHelper.toHashMap(datalogEntity1.getDataContent().replaceAll("^\\[|\\]$", ""));
                map2 = JSONHelper.toHashMap(datalogEntity2.getDataContent().replaceAll("^\\[|\\]$", ""));
            } else {
                map1 = JSONHelper.toHashMap(datalogEntity2.getDataContent().replaceAll("^\\[|\\]$", ""));
                map2 = JSONHelper.toHashMap(datalogEntity1.getDataContent().replaceAll("^\\[|\\]$", ""));
            }

            Map<String, Object> mapAll = new HashMap();
            mapAll.putAll(map1);
            mapAll.putAll(map2);
            Set<String> set = mapAll.keySet();
            List<DataLogDiff> dataLogDiffs = new LinkedList();
            String value1 = null;
            String value2 = null;

            DataLogDiff dataLogDiff;
            for(Iterator var18 = set.iterator(); var18.hasNext(); dataLogDiffs.add(dataLogDiff)) {
                String string = (String)var18.next();
                dataLogDiff = new DataLogDiff();
                dataLogDiff.setName(string);
                if(map1.containsKey(string)) {
                    value1 = map1.get(string).toString();
                    if(value1 == null) {
                        dataLogDiff.setValue1("");
                    } else {
                        dataLogDiff.setValue1(value1);
                    }
                } else {
                    dataLogDiff.setValue1("");
                }

                if(map2.containsKey(string)) {
                    value2 = map2.get(string).toString();
                    if(value2 == null) {
                        dataLogDiff.setValue2("");
                    } else {
                        dataLogDiff.setValue2(value2);
                    }
                } else {
                    dataLogDiff.setValue2("");
                }

                if(value1 == null && value2 == null) {
                    dataLogDiff.setDiff("N");
                } else if(value1 != null && value2 != null) {
                    if(value1.equals(value2)) {
                        dataLogDiff.setDiff("N");
                    } else {
                        dataLogDiff.setDiff("Y");
                    }
                } else {
                    dataLogDiff.setDiff("Y");
                }
            }

            if(version1.intValue() < version2.intValue()) {
                request.setAttribute("versionNumber1", datalogEntity1.getVersionNumber());
                request.setAttribute("versionNumber2", datalogEntity2.getVersionNumber());
            } else {
                request.setAttribute("versionNumber1", datalogEntity2.getVersionNumber());
                request.setAttribute("versionNumber2", datalogEntity1.getVersionNumber());
            }

            request.setAttribute("dataLogDiffs", dataLogDiffs);
        }

        return new ModelAndView("system/dataLog/diffDataVersion");
    }
}
