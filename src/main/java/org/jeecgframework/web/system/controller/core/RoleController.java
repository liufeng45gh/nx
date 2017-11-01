//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.system.controller.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.common.model.json.ValidForm;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.NumberComparator;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.SetListSort;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.pojo.base.TSDataRule;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSOperation;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleFunction;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
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
@RequestMapping({"/roleController"})
public class RoleController extends BaseController {
    private static final Logger logger = Logger.getLogger(RoleController.class);
    private UserService userService;
    private SystemService systemService;

    public RoleController() {
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
            params = {"role"}
    )
    public ModelAndView role() {
        return new ModelAndView("system/role/roleList");
    }

    @RequestMapping(
            params = {"roleGrid"}
    )
    public void roleGrid(TSRole role, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSRole.class, dataGrid);
        HqlGenerateUtil.installHql(cq, role);
        cq.add();
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"delRole"}
    )
    @ResponseBody
    public AjaxJson delRole(TSRole role, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        int count = this.userService.getUsersOfThisRole(role.getId());
        if(count == 0) {
            this.delRoleFunction(role);
            role = (TSRole)this.systemService.getEntity(TSRole.class, role.getId());
            this.userService.delete(role);
            message = "角色: " + role.getRoleName() + "被删除成功";
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } else {
            message = "角色: 仍被用户使用，请先删除关联关系";
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"checkRole"}
    )
    @ResponseBody
    public ValidForm checkRole(TSRole role, HttpServletRequest request, HttpServletResponse response) {
        ValidForm v = new ValidForm();
        String roleCode = oConvertUtils.getString(request.getParameter("param"));
        String code = oConvertUtils.getString(request.getParameter("code"));
        List<TSRole> roles = this.systemService.findByProperty(TSRole.class, "roleCode", roleCode);
        if(roles.size() > 0 && !code.equals(roleCode)) {
            v.setInfo("角色编码已存在");
            v.setStatus("n");
        }

        return v;
    }

    protected void delRoleFunction(TSRole role) {
        List<TSRoleFunction> roleFunctions = this.systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
        if(roleFunctions.size() > 0) {
            Iterator var4 = roleFunctions.iterator();

            while(var4.hasNext()) {
                TSRoleFunction tsRoleFunction = (TSRoleFunction)var4.next();
                this.systemService.delete(tsRoleFunction);
            }
        }

        List<TSRoleUser> roleUsers = this.systemService.findByProperty(TSRoleUser.class, "TSRole.id", role.getId());
        Iterator var5 = roleUsers.iterator();

        while(var5.hasNext()) {
            TSRoleUser tsRoleUser = (TSRoleUser)var5.next();
            this.systemService.delete(tsRoleUser);
        }

    }

    @RequestMapping(
            params = {"saveRole"}
    )
    @ResponseBody
    public AjaxJson saveRole(TSRole role, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        if(StringUtil.isNotEmpty(role.getId())) {
            message = "角色: " + role.getRoleName() + "被更新成功";
            this.userService.saveOrUpdate(role);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } else {
            message = "角色: " + role.getRoleName() + "被添加成功";
            this.userService.save(role);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        }

        return j;
    }

    @RequestMapping(
            params = {"fun"}
    )
    public ModelAndView fun(HttpServletRequest request) {
        String roleId = request.getParameter("roleId");
        request.setAttribute("roleId", roleId);
        return new ModelAndView("system/role/roleSet");
    }

    @RequestMapping(
            params = {"userList"}
    )
    public ModelAndView userList(HttpServletRequest request) {
        request.setAttribute("roleId", request.getParameter("roleId"));
        return new ModelAndView("system/role/roleUserList");
    }

    @RequestMapping(
            params = {"roleUserDatagrid"}
    )
    public void roleUserDatagrid(TSUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
        String roleId = request.getParameter("roleId");
        List<TSRoleUser> roleUser = this.systemService.findByProperty(TSRoleUser.class, "TSRole.id", roleId);
        Criterion cc = null;
        if(roleUser.size() > 0) {
            for(int i = 0; i < roleUser.size(); ++i) {
                if(i == 0) {
                    cc = Restrictions.eq("id", ((TSRoleUser)roleUser.get(i)).getTSUser().getId());
                } else {
                    cc = cq.getor((Criterion)cc, Restrictions.eq("id", ((TSRoleUser)roleUser.get(i)).getTSUser().getId()));
                }
            }
        } else {
            cc = Restrictions.eq("id", "-1");
        }

        cq.add((Criterion)cc);
        HqlGenerateUtil.installHql(cq, user);
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"getUserList"}
    )
    @ResponseBody
    public List<ComboTree> getUserList(TSUser user, HttpServletRequest request, ComboTree comboTree) {
        new ArrayList();
        String roleId = request.getParameter("roleId");
        List<TSUser> loginActionlist = new ArrayList();
        if(user != null) {
            List<TSRoleUser> roleUser = this.systemService.findByProperty(TSRoleUser.class, "TSRole.id", roleId);
            if(roleUser.size() > 0) {
                Iterator var9 = roleUser.iterator();

                while(var9.hasNext()) {
                    TSRoleUser ru = (TSRoleUser)var9.next();
                    loginActionlist.add(ru.getTSUser());
                }
            }
        }

        ComboTreeModel comboTreeModel = new ComboTreeModel("id", "userName", "TSUser");
        List<ComboTree> comboTrees = this.systemService.ComboTree(loginActionlist, comboTreeModel, loginActionlist, false);
        return comboTrees;
    }

    @RequestMapping(
            params = {"roleTree"}
    )
    public ModelAndView roleTree(HttpServletRequest request) {
        request.setAttribute("orgId", request.getParameter("orgId"));
        return new ModelAndView("system/role/roleTree");
    }

    @RequestMapping(
            params = {"getRoleTree"}
    )
    @ResponseBody
    public List<ComboTree> getRoleTree(HttpServletRequest request) {
        ComboTreeModel comboTreeModel = new ComboTreeModel("id", "roleName", "");
        String orgId = request.getParameter("orgId");
        List<TSRole[]> orgRoleArrList = this.systemService.findHql("from TSRole r, TSRoleOrg ro, TSDepart o WHERE r.id=ro.tsRole.id AND ro.tsDepart.id=o.id AND o.id=?", new Object[]{orgId});
        List<TSRole> orgRoleList = new ArrayList();
        Iterator var7 = orgRoleArrList.iterator();

        while(var7.hasNext()) {
            Object[] roleArr = (Object[])var7.next();
            orgRoleList.add((TSRole)roleArr[0]);
        }

        List<Object> allRoleList = this.systemService.getList(TSRole.class);
        List<ComboTree> comboTrees = this.systemService.ComboTree(allRoleList, comboTreeModel, orgRoleList, false);
        return comboTrees;
    }

    @RequestMapping(
            params = {"setAuthority"}
    )
    @ResponseBody
    public List<ComboTree> setAuthority(TSRole role, HttpServletRequest request, ComboTree comboTree) {
        CriteriaQuery cq = new CriteriaQuery(TSFunction.class);
        if(comboTree.getId() != null) {
            cq.eq("TSFunction.id", comboTree.getId());
        }

        if(comboTree.getId() == null) {
            cq.isNull("TSFunction");
        }

        cq.notEq("id", "2c98d8815a1b7b63015a1b9fb7420012");
        cq.notEq("id", "8a8ab0b246dc81120146dc8180d7001c");
        cq.notEq("id", "8a8ab0b246dc81120146dc8180ce0019");
        cq.notEq("functionLevel", Short.valueOf(Short.parseShort("-1")));
        cq.add();
        List<TSFunction> functionList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        Collections.sort(functionList, new NumberComparator());
        new ArrayList();
        String roleId = request.getParameter("roleId");
        List<TSFunction> loginActionlist = new ArrayList();
        role = (TSRole)this.systemService.get(TSRole.class, roleId);
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

            roleFunctionList.clear();
        }

        ComboTreeModel comboTreeModel = new ComboTreeModel("id", "functionName", "TSFunctions");
        List<ComboTree> comboTrees = this.systemService.ComboTree(functionList, comboTreeModel, loginActionlist, true);
        MutiLangUtil.setMutiComboTree(comboTrees);
        functionList.clear();
        loginActionlist.clear();
        return comboTrees;
    }

    @RequestMapping(
            params = {"updateAuthority"}
    )
    @ResponseBody
    public AjaxJson updateAuthority(HttpServletRequest request) {
        AjaxJson j = new AjaxJson();

        try {
            String roleId = request.getParameter("roleId");
            String rolefunction = request.getParameter("rolefunctions");
            TSRole role = (TSRole)this.systemService.get(TSRole.class, roleId);
            List<TSRoleFunction> roleFunctionList = this.systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
            Map<String, TSRoleFunction> map = new HashMap();
            Iterator var9 = roleFunctionList.iterator();

            while(var9.hasNext()) {
                TSRoleFunction functionOfRole = (TSRoleFunction)var9.next();
                map.put(functionOfRole.getTSFunction().getId(), functionOfRole);
            }

            String[] roleFunctions = rolefunction.split(",");
            Set<String> set = new HashSet();
            String[] var13 = roleFunctions;
            int var12 = roleFunctions.length;

            for(int var11 = 0; var11 < var12; ++var11) {
                String s = var13[var11];
                set.add(s);
            }

            this.updateCompare(set, role, map);
            j.setMsg("权限更新成功");
        } catch (Exception var14) {
            logger.error(ExceptionUtil.getExceptionMessage(var14));
            j.setMsg("权限更新失败");
        }

        return j;
    }

    private void updateCompare(Set<String> set, TSRole role, Map<String, TSRoleFunction> map) {
        List<TSRoleFunction> entitys = new ArrayList();
        List<TSRoleFunction> deleteEntitys = new ArrayList();
        Iterator it = set.iterator();

        while(it.hasNext()) {
            String s = (String)it.next();
            if(map.containsKey(s)) {
                map.remove(s);
            } else {
                TSRoleFunction rf = new TSRoleFunction();
                TSFunction f = (TSFunction)this.systemService.get(TSFunction.class, s);
                rf.setTSFunction(f);
                rf.setTSRole(role);
                entitys.add(rf);
            }
        }

        Collection<TSRoleFunction> collection = map.values();
        it = collection.iterator();

        while(it.hasNext()) {
            deleteEntitys.add((TSRoleFunction)it.next());
        }

        this.systemService.batchSave(entitys);
        this.systemService.deleteAllEntitie(deleteEntitys);
    }

    @RequestMapping(
            params = {"addorupdate"}
    )
    public ModelAndView addorupdate(TSRole role, HttpServletRequest req) {
        if(role.getId() != null) {
            role = (TSRole)this.systemService.getEntity(TSRole.class, role.getId());
            req.setAttribute("role", role);
        }

        return new ModelAndView("system/role/role");
    }

    @RequestMapping(
            params = {"setOperate"}
    )
    @ResponseBody
    public List<TreeGrid> setOperate(HttpServletRequest request, TreeGrid treegrid) {
        String roleId = request.getParameter("roleId");
        CriteriaQuery cq = new CriteriaQuery(TSFunction.class);
        if(treegrid.getId() != null) {
            cq.eq("TSFunction.id", treegrid.getId());
        }

        if(treegrid.getId() == null) {
            cq.isNull("TSFunction");
        }

        cq.add();
        List<TSFunction> functionList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        new ArrayList();
        Collections.sort(functionList, new SetListSort());
        TreeGridModel treeGridModel = new TreeGridModel();
        treeGridModel.setRoleid(roleId);
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
        String roleId = request.getParameter("roleId");
        this.clearp(roleId);
        String[] fun_op = fop.split(",");
        String aa = "";
        String bb = "";
        if(fun_op.length == 1) {
            bb = fun_op[0].split("_")[1];
            aa = fun_op[0].split("_")[0];
            this.savep(roleId, bb, aa);
        } else {
            for(int i = 0; i < fun_op.length; ++i) {
                String cc = fun_op[i].split("_")[0];
                if(i > 0 && bb.equals(fun_op[i].split("_")[1])) {
                    aa = aa + "," + cc;
                    if(i == fun_op.length - 1) {
                        this.savep(roleId, bb, aa);
                    }
                } else if(i > 0) {
                    this.savep(roleId, bb, aa);
                    aa = fun_op[i].split("_")[0];
                    if(i == fun_op.length - 1) {
                        bb = fun_op[i].split("_")[1];
                        this.savep(roleId, bb, aa);
                    }
                } else {
                    aa = fun_op[i].split("_")[0];
                }

                bb = fun_op[i].split("_")[1];
            }
        }

        return j;
    }

    public void savep(String roleId, String functionid, String ids) {
        CriteriaQuery cq = new CriteriaQuery(TSRoleFunction.class);
        cq.eq("TSRole.id", roleId);
        cq.eq("TSFunction.id", functionid);
        cq.add();
        List<TSRoleFunction> rFunctions = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        if(rFunctions.size() > 0) {
            TSRoleFunction roleFunction = (TSRoleFunction)rFunctions.get(0);
            roleFunction.setOperation(ids);
            this.systemService.saveOrUpdate(roleFunction);
        }

    }

    public void clearp(String roleId) {
        List<TSRoleFunction> rFunctions = this.systemService.findByProperty(TSRoleFunction.class, "TSRole.id", roleId);
        if(rFunctions.size() > 0) {
            Iterator var4 = rFunctions.iterator();

            while(var4.hasNext()) {
                TSRoleFunction tRoleFunction = (TSRoleFunction)var4.next();
                tRoleFunction.setOperation((String)null);
                this.systemService.saveOrUpdate(tRoleFunction);
            }
        }

    }

    @RequestMapping(
            params = {"operationListForFunction"}
    )
    public ModelAndView operationListForFunction(HttpServletRequest request, String functionId, String roleId) {
        CriteriaQuery cq = new CriteriaQuery(TSOperation.class);
        cq.eq("TSFunction.id", functionId);
        cq.eq("status", Short.valueOf("0"));
        cq.add();
        List<TSOperation> operationList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        Set<String> operationCodes = this.systemService.getOperationCodesByRoleIdAndFunctionId(roleId, functionId);
        request.setAttribute("operationList", operationList);
        request.setAttribute("operationcodes", operationCodes);
        request.setAttribute("functionId", functionId);
        return new ModelAndView("system/role/operationListForFunction");
    }

    @RequestMapping(
            params = {"updateOperation"}
    )
    @ResponseBody
    public AjaxJson updateOperation(HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String roleId = request.getParameter("roleId");
        String functionId = request.getParameter("functionId");
        String operationcodes = null;

        try {
            operationcodes = URLDecoder.decode(request.getParameter("operationcodes"), "utf-8");
        } catch (UnsupportedEncodingException var9) {
            var9.printStackTrace();
        }

        CriteriaQuery cq1 = new CriteriaQuery(TSRoleFunction.class);
        cq1.eq("TSRole.id", roleId);
        cq1.eq("TSFunction.id", functionId);
        cq1.add();
        List<TSRoleFunction> rFunctions = this.systemService.getListByCriteriaQuery(cq1, Boolean.valueOf(false));
        if(rFunctions != null && rFunctions.size() > 0) {
            TSRoleFunction tsRoleFunction = (TSRoleFunction)rFunctions.get(0);
            tsRoleFunction.setOperation(operationcodes);
            this.systemService.saveOrUpdate(tsRoleFunction);
        }

        j.setMsg("按钮权限更新成功");
        return j;
    }

    @RequestMapping(
            params = {"dataRuleListForFunction"}
    )
    public ModelAndView dataRuleListForFunction(HttpServletRequest request, String functionId, String roleId) {
        CriteriaQuery cq = new CriteriaQuery(TSDataRule.class);
        cq.eq("TSFunction.id", functionId);
        cq.add();
        List<TSDataRule> dataRuleList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        Set<String> dataRulecodes = this.systemService.getOperationCodesByRoleIdAndruleDataId(roleId, functionId);
        request.setAttribute("dataRuleList", dataRuleList);
        request.setAttribute("dataRulecodes", dataRulecodes);
        request.setAttribute("functionId", functionId);
        return new ModelAndView("system/role/dataRuleListForFunction");
    }

    @RequestMapping(
            params = {"updateDataRule"}
    )
    @ResponseBody
    public AjaxJson updateDataRule(HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String roleId = request.getParameter("roleId");
        String functionId = request.getParameter("functionId");
        String dataRulecodes = null;

        try {
            dataRulecodes = URLDecoder.decode(request.getParameter("dataRulecodes"), "utf-8");
        } catch (UnsupportedEncodingException var9) {
            var9.printStackTrace();
        }

        CriteriaQuery cq1 = new CriteriaQuery(TSRoleFunction.class);
        cq1.eq("TSRole.id", roleId);
        cq1.eq("TSFunction.id", functionId);
        cq1.add();
        List<TSRoleFunction> rFunctions = this.systemService.getListByCriteriaQuery(cq1, Boolean.valueOf(false));
        if(rFunctions != null && rFunctions.size() > 0) {
            TSRoleFunction tsRoleFunction = (TSRoleFunction)rFunctions.get(0);
            tsRoleFunction.setDataRule(dataRulecodes);
            this.systemService.saveOrUpdate(tsRoleFunction);
        }

        j.setMsg("数据权限更新成功");
        return j;
    }

    @RequestMapping(
            params = {"goAddUserToRole"}
    )
    public ModelAndView goAddUserToOrg(HttpServletRequest req) {
        return new ModelAndView("system/role/noCurRoleUserList");
    }

    @RequestMapping(
            params = {"addUserToRoleList"}
    )
    public void addUserToOrgList(TSUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        String roleId = request.getParameter("roleId");
        CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
        HqlGenerateUtil.installHql(cq, user);
        CriteriaQuery subCq = new CriteriaQuery(TSRoleUser.class);
        subCq.setProjection(Property.forName("TSUser.id"));
        subCq.eq("TSRole.id", roleId);
        subCq.add();
        cq.add(Property.forName("id").notIn(subCq.getDetachedCriteria()));
        cq.add();
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doAddUserToRole"}
    )
    @ResponseBody
    public AjaxJson doAddUserToOrg(HttpServletRequest req) {
        String message = null;
        AjaxJson j = new AjaxJson();
        TSRole role = (TSRole)this.systemService.getEntity(TSRole.class, req.getParameter("roleId"));
        this.saveRoleUserList(req, role);
        message = MutiLangUtil.paramAddSuccess("common.user");
        j.setMsg(message);
        return j;
    }

    private void saveRoleUserList(HttpServletRequest request, TSRole role) {
        String userIds = oConvertUtils.getString(request.getParameter("userIds"));
        List<TSRoleUser> roleUserList = new ArrayList();
        List<String> userIdList = this.extractIdListByComma(userIds);
        Iterator var7 = userIdList.iterator();

        while(var7.hasNext()) {
            String userId = (String)var7.next();
            TSUser user = new TSUser();
            user.setId(userId);
            TSRoleUser roleUser = new TSRoleUser();
            roleUser.setTSUser(user);
            roleUser.setTSRole(role);
            roleUserList.add(roleUser);
        }

        if(!roleUserList.isEmpty()) {
            this.systemService.batchSave(roleUserList);
        }

    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "roleController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(TSRole tsRole, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        tsRole.setRoleName((String)null);
        CriteriaQuery cq = new CriteriaQuery(TSRole.class, dataGrid);
        HqlGenerateUtil.installHql(cq, tsRole, request.getParameterMap());
        List<TSRole> tsRoles = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "角色表");
        modelMap.put("entity", TSRole.class);
        modelMap.put("params", new ExportParams("角色表列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", tsRoles);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByT"}
    )
    public String exportXlsByT(TSRole tsRole, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "用户表");
        modelMap.put("entity", TSRole.class);
        modelMap.put("params", new ExportParams("用户表列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
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
                List<TSRole> tsRoles = ExcelImportUtil.importExcel(file.getInputStream(), TSRole.class, params);
                Iterator var12 = tsRoles.iterator();

                while(var12.hasNext()) {
                    TSRole tsRole = (TSRole)var12.next();
                    String roleCode = tsRole.getRoleCode();
                    List<TSRole> roles = this.systemService.findByProperty(TSRole.class, "roleCode", roleCode);
                    if(roles.size() != 0) {
                        TSRole role = (TSRole)roles.get(0);
                        MyBeanUtils.copyBeanNotNull2Bean(tsRole, role);
                        this.systemService.saveOrUpdate(role);
                    } else {
                        this.systemService.save(tsRole);
                    }
                }

                j.setMsg("文件导入成功！");
            } catch (Exception var24) {
                j.setMsg("文件导入失败！");
                logger.error(ExceptionUtil.getExceptionMessage(var24));
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException var23) {
                    var23.printStackTrace();
                }

            }
        }

        return j;
    }
}
