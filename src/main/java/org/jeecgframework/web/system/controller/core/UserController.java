//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.system.controller.core;

import cn.jiguang.common.resp.ResponseWrapper;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboBox;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.ValidForm;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.enums.SysThemesEnum;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.ListtoMenu;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.SetListSort;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.SysThemesUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.DataTableReturn;
import org.jeecgframework.tag.vo.datatable.DataTables;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleFunction;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import ssb.warmline.business.commons.utils.DateUtils;
import ssb.warmline.business.commons.utils.JMessage.JMessageUserUtils;
import ssb.warmline.business.commons.utils.JMessage.UserPayload;
import ssb.warmline.business.commons.utils.JMessage.UserPayload.Builder;
import ssb.warmline.business.entity.wcashaccount.WCashAccountEntity;
import ssb.warmline.business.entity.wterritory.WTerritoryEntity;
import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
import ssb.warmline.business.service.whelpmessage.WHelpMessageServiceI;
import ssb.warmline.business.service.worder.WOrderServiceI;
import ssb.warmline.business.service.worderphoto.WOrderPhotoServiceI;
import ssb.warmline.business.service.worderphotomain.WOrderPhotoMainServiceI;
import ssb.warmline.business.utils.IDCardUtil;
import ssb.warmline.business.utils.PropertiesUtil;

@Controller
@RequestMapping({"/userController"})
public class UserController extends BaseController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    static final String separator;
    private UserService userService;
    private SystemService systemService;
    @Autowired
    private TSBaseUserServiceI TSBaseUserService;
    @Autowired
    private WOrderServiceI WOrderService;
    @Autowired
    private WHelpMessageServiceI WHelpMessageService;
    @Autowired
    private WOrderPhotoMainServiceI WOrderPhotoMainService;
    @Autowired
    private WOrderPhotoServiceI WOrderPhotoService;
    @Autowired
    private TSBaseUserServiceI tSBaseUserService;

    static {
        separator = File.separator;
    }

    public UserController() {
    }

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            params = {"menu"}
    )
    public void menu(HttpServletRequest request, HttpServletResponse response) {
        SetListSort sort = new SetListSort();
        TSUser u = ResourceUtil.getSessionUserName();
        Set<TSFunction> loginActionlist = new HashSet();
        List<TSRoleUser> rUsers = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", u.getId());
        Iterator var8 = rUsers.iterator();

        while(true) {
            List roleFunctionList;
            do {
                if(!var8.hasNext()) {
                    List<TSFunction> bigActionlist = new ArrayList();
                    List<TSFunction> smailActionlist = new ArrayList();
                    if(loginActionlist.size() > 0) {
                        Iterator var26 = loginActionlist.iterator();

                        while(var26.hasNext()) {
                            TSFunction function = (TSFunction)var26.next();
                            if(function.getFunctionLevel().shortValue() == 0) {
                                bigActionlist.add(function);
                            } else if(function.getFunctionLevel().shortValue() == 1) {
                                smailActionlist.add(function);
                            }
                        }
                    }

                    Collections.sort(bigActionlist, sort);
                    Collections.sort(smailActionlist, sort);
                    String logString = ListtoMenu.getMenu(bigActionlist, smailActionlist);

                    try {
                        response.getWriter().write(logString);
                        response.getWriter().flush();
                    } catch (IOException var20) {
                        var20.printStackTrace();
                    } finally {
                        try {
                            response.getWriter().close();
                        } catch (IOException var19) {
                            var19.printStackTrace();
                        }

                    }

                    return;
                }

                TSRoleUser ru = (TSRoleUser)var8.next();
                TSRole role = ru.getTSRole();
                roleFunctionList = this.systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
            } while(roleFunctionList.size() <= 0);

            Iterator var12 = roleFunctionList.iterator();

            while(var12.hasNext()) {
                TSRoleFunction roleFunction = (TSRoleFunction)var12.next();
                TSFunction function = roleFunction.getTSFunction();
                loginActionlist.add(function);
            }
        }
    }

    @RequestMapping(
            params = {"user"}
    )
    public String user(HttpServletRequest request) {
        return "system/user/userList";
    }

    @RequestMapping(
            params = {"fictitiousUser"}
    )
    public String fictitiousUser(HttpServletRequest request) {
        TSUser user = ResourceUtil.getSessionUserName();
        List<TSRoleUser> roleUsers = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
        TSRoleUser tsRU = (TSRoleUser)roleUsers.get(0);
        TSRole role = tsRU.getTSRole();
        String userKey = null;
        if("297e47fc5b8ea693015b8ea8c1690001".equals(role.getId())) {
            userKey = "ok";
        }

        request.setAttribute("userKey", userKey);
        return "system/realname/fictitiousUser";
    }

    @RequestMapping(
            params = {"orderUser"}
    )
    public String orderUser(HttpServletRequest request, String status) {
        HttpSession session = request.getSession();
        String issuerId = request.getParameter("issuerId");
        session.setAttribute("issuerId", issuerId);
        return "system/realname/orderUsers";
    }

    @RequestMapping(
            params = {"realuser"}
    )
    public String realuser(HttpServletRequest request) {
        TSUser user = ResourceUtil.getSessionUserName();
        String userKey = null;
        if("区域总代理".equals(user.getUserKey())) {
            userKey = "ok";
        }

        request.setAttribute("userKey", userKey);
        return "system/realname/realnameList";
    }

    @RequestMapping(
            params = {"user3"}
    )
    public String user3(HttpServletRequest request) {
        return "system/user/userList3";
    }

    @RequestMapping(
            params = {"userinfo"}
    )
    public String userinfo(HttpServletRequest request) {
        TSUser user = ResourceUtil.getSessionUserName();
        request.setAttribute("user", user);
        return "system/user/userinfo";
    }

    @RequestMapping(
            params = {"changepassword"}
    )
    public String changepassword(HttpServletRequest request) {
        TSUser user = ResourceUtil.getSessionUserName();
        request.setAttribute("user", user);
        return "system/user/changepassword";
    }

    @RequestMapping(
            params = {"savenewpwd"}
    )
    @ResponseBody
    public AjaxJson savenewpwd(HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        TSUser user = ResourceUtil.getSessionUserName();
        String password = oConvertUtils.getString(request.getParameter("password"));
        String newpassword = oConvertUtils.getString(request.getParameter("newpassword"));
        String pString = PasswordUtil.encrypt(user.getPhone(), password, PasswordUtil.getStaticSalt());
        if(!pString.equals(user.getPassword())) {
            j.setMsg("原密码不正确");
            j.setSuccess(false);
        } else {
            try {
                user.setPassword(PasswordUtil.encrypt(user.getPhone(), newpassword, PasswordUtil.getStaticSalt()));
            } catch (Exception var8) {
                var8.printStackTrace();
            }

            this.systemService.updateEntitie(user);
            j.setMsg("修改成功");
        }

        return j;
    }

    @RequestMapping(
            params = {"changepasswordforuser"}
    )
    public ModelAndView changepasswordforuser(TSUser user, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(user.getId())) {
            user = (TSUser)this.systemService.getEntity(TSUser.class, user.getId());
            req.setAttribute("user", user);
            this.idandname(req, user);
        }

        return new ModelAndView("system/user/adminchangepwd");
    }

    @RequestMapping(
            params = {"savenewpwdforuser"}
    )
    @ResponseBody
    public AjaxJson savenewpwdforuser(HttpServletRequest req) {
        String message = null;
        AjaxJson j = new AjaxJson();
        String id = oConvertUtils.getString(req.getParameter("id"));
        String password = oConvertUtils.getString(req.getParameter("password"));
        if(StringUtil.isNotEmpty(id)) {
            TSUser users = (TSUser)this.systemService.getEntity(TSUser.class, id);
            users.setPassword(PasswordUtil.encrypt(users.getPhone(), password, PasswordUtil.getStaticSalt()));
            users.setStatus(Globals.User_Normal);
            users.setActivitiSync(users.getActivitiSync());
            this.systemService.updateEntitie(users);
            message = "用户:密码重置成功";
            if(!"1".equals(users.getUserType())) {
                ResponseWrapper res = JMessageUserUtils.updatePassword(users.getPhone(), password);
                this.systemService.addLog(message + "---极光修改密码打印信息：" + res, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
            }
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"lock"}
    )
    @ResponseBody
    public AjaxJson lock(String id, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        String message = null;
        TSUser user = (TSUser)this.systemService.getEntity(TSUser.class, id);
        if("admin".equals(user.getUserName())) {
            message = "超级管理员[admin]不可操作";
            j.setMsg(message);
            return j;
        } else {
            String lockValue = req.getParameter("lockvalue");
            user.setStatus(new Short(lockValue));

            try {
                this.userService.updateEntitie(user);
                if("0".equals(lockValue)) {
                    message = "用户：" + user.getUserName() + "锁定成功!";
                } else if("1".equals(lockValue)) {
                    message = "用户：" + user.getUserName() + "激活成功!";
                }

                this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
            } catch (Exception var8) {
                message = "操作失败!";
            }

            j.setMsg(message);
            return j;
        }
    }

    @RequestMapping(
            params = {"role"}
    )
    @ResponseBody
    public List<ComboBox> role(HttpServletResponse response, HttpServletRequest request, ComboBox comboBox) {
        String id = request.getParameter("id");
        new ArrayList();
        List<TSRole> roles = new ArrayList();
        List roleUser;
        if(StringUtil.isNotEmpty(id)) {
            roleUser = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", id);
            if(roleUser.size() > 0) {
                Iterator var9 = roleUser.iterator();

                while(var9.hasNext()) {
                    TSRoleUser ru = (TSRoleUser)var9.next();
                    roles.add(ru.getTSRole());
                }
            }
        }

        roleUser = this.systemService.getList(TSRole.class);
        List<ComboBox> comboBoxs = TagUtil.getComboBox(roleUser, roles, comboBox);
        roleUser.clear();
        roles.clear();
        return comboBoxs;
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(TSUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
        HqlGenerateUtil.installHql(cq, user);
        Short[] userstate = new Short[]{Globals.User_Normal, Globals.User_ADMIN, Globals.User_Forbidden};
        cq.in("status", userstate);
        cq.eq("deleteFlag", Globals.Delete_Normal);
        String orgIds = request.getParameter("orgIds");
        this.extractIdListByComma(orgIds);
        cq.add();
        this.systemService.getDataGridReturn(cq, true);
        List<TSUser> cfeList = new ArrayList();
        Iterator var11 = dataGrid.getResults().iterator();

        while(true) {
            Object o;
            do {
                if(!var11.hasNext()) {
                    TagUtil.datagrid(response, dataGrid);
                    return;
                }

                o = var11.next();
            } while(!(o instanceof TSUser));

            TSUser cfe = (TSUser)o;
            if(cfe.getId() != null && !"".equals(cfe.getId())) {
                List<TSRoleUser> roleUser = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", cfe.getId());
                if(roleUser.size() > 0) {
                    String roleName = "";

                    TSRoleUser ru;
                    for(Iterator var16 = roleUser.iterator(); var16.hasNext(); roleName = roleName + ru.getTSRole().getRoleName() + ",") {
                        ru = (TSRoleUser)var16.next();
                    }

                    roleName = roleName.substring(0, roleName.length() - 1);
                    cfe.setUserKey(roleName);
                }
            }

            cfeList.add(cfe);
        }
    }

    @RequestMapping(
            params = {"generalDatagrId"}
    )
    public void generalDatagrId(TSUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
        HqlGenerateUtil.installHql(cq, user);
        Short[] userstate = new Short[]{Globals.User_Normal, Globals.User_ADMIN, Globals.User_Forbidden};
        TSUser currentUser = ResourceUtil.getSessionUserName();
        new ArrayList();
        String id = PropertiesUtil.getProperties();
        List<TSRoleUser> roleUsers = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", currentUser.getId());
        TSRoleUser tsRU = (TSRoleUser)roleUsers.get(0);
        TSRole role = tsRU.getTSRole();
        if("402881a85c858d85015c862c45bb0010".equals(role.getId())) {
            cq.eq("id", currentUser.getId());
        }

        cq.in("status", userstate);
        cq.eq("deleteFlag", Globals.Delete_Normal);
        cq.eq("userKey", "区域总代理");
        String orgIds = request.getParameter("orgIds");
        this.extractIdListByComma(orgIds);
        cq.add();
        this.systemService.getDataGridReturn(cq, true);
        List<TSUser> cfeList = new ArrayList();
        Iterator var16 = dataGrid.getResults().iterator();

        while(true) {
            Object o;
            Iterator var21;
            do {
                if(!var16.hasNext()) {
                    List<TSUser> results = dataGrid.getResults();
                    List<TSUser> resultss = new ArrayList();

                    for(int i = 0; i < results.size(); ++i) {
                        user = (TSUser)results.get(i);
                        String territoryId = ((TSUser)results.get(i)).getTerritoryId();
                        List<WTerritoryEntity> territoryEntity = this.systemService.findByProperty(WTerritoryEntity.class, "id", territoryId);
                        if(territoryEntity.size() > 0) {
                            var21 = territoryEntity.iterator();

                            while(var21.hasNext()) {
                                WTerritoryEntity cashAccount = (WTerritoryEntity)var21.next();
                                user.setTerritoryName(cashAccount.getTerritoryname());
                            }
                        }

                        resultss.add(user);
                    }

                    dataGrid.setResults(resultss);
                    TagUtil.datagrid(response, dataGrid);
                    return;
                }

                o = var16.next();
            } while(!(o instanceof TSUser));

            TSUser cfe = (TSUser)o;
            if(cfe.getId() != null && !"".equals(cfe.getId())) {
                List<TSRoleUser> roleUser = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", cfe.getId());
                if(roleUser.size() > 0) {
                    String roleName = "";

                    TSRoleUser ru;
                    for(var21 = roleUser.iterator(); var21.hasNext(); roleName = roleName + ru.getTSRole().getRoleName() + ",") {
                        ru = (TSRoleUser)var21.next();
                    }

                    roleName = roleName.substring(0, roleName.length() - 1);
                    cfe.setUserKey(roleName);
                }
            }

            cfeList.add(cfe);
        }
    }

    @RequestMapping(
            params = {"fictitiousNameDatagrid"}
    )
    public void fictitiousNameDatagrid(TSUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
        HqlGenerateUtil.installHql(cq, user);
        Short[] userstate = new Short[]{Globals.User_Normal, Globals.User_ADMIN, Globals.User_Forbidden};
        cq.in("status", userstate);
        cq.eq("userType", "1");
        TSUser currentUser = ResourceUtil.getSessionUserName();
        new ArrayList();
        String id = PropertiesUtil.getProperties();
        List<TSRoleUser> roleUsers = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", currentUser.getId());
        TSRoleUser tsRU = (TSRoleUser)roleUsers.get(0);
        TSRole role = tsRU.getTSRole();
        if("sysWorker".equals(role.getRoleCode())) {
            cq.eq("subordinateAdmin", currentUser.getId());
        }

        cq.eq("deleteFlag", Globals.Delete_Normal);
        String orgIds = request.getParameter("orgIds");
        this.extractIdListByComma(orgIds);
        cq.add();
        this.systemService.getDataGridReturn(cq, true);
        List<TSUser> cfeList = new ArrayList();
        Iterator var16 = dataGrid.getResults().iterator();

        while(true) {
            Object o;
            do {
                if(!var16.hasNext()) {
                    TagUtil.datagrid(response, dataGrid);
                    return;
                }

                o = var16.next();
            } while(!(o instanceof TSUser));

            TSUser cfe = (TSUser)o;
            if(cfe.getId() != null && !"".equals(cfe.getId())) {
                List<TSRoleUser> roleUser = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", cfe.getId());
                if(roleUser.size() > 0) {
                    String roleName = "";

                    TSRoleUser ru;
                    for(Iterator var21 = roleUser.iterator(); var21.hasNext(); roleName = roleName + ru.getTSRole().getRoleName() + ",") {
                        ru = (TSRoleUser)var21.next();
                    }

                    roleName = roleName.substring(0, roleName.length() - 1);
                    cfe.setUserKey(roleName);
                }
            }

            cfeList.add(cfe);
        }
    }

    @RequestMapping(
            params = {"orderUserDatagrid"}
    )
    public void orderUserDatagrid(TSUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
        HqlGenerateUtil.installHql(cq, user);
        Short[] userstate = new Short[]{Globals.User_Normal, Globals.User_ADMIN, Globals.User_Forbidden};
        String issuerId = (String)request.getSession().getAttribute("issuerId");
        cq.in("status", userstate);
        cq.eq("id", issuerId);
        cq.eq("deleteFlag", Globals.Delete_Normal);
        String orgIds = request.getParameter("orgIds");
        this.extractIdListByComma(orgIds);
        cq.add();
        this.systemService.getDataGridReturn(cq, true);
        List<TSUser> cfeList = new ArrayList();
        Iterator var12 = dataGrid.getResults().iterator();

        while(true) {
            Object o;
            do {
                if(!var12.hasNext()) {
                    TagUtil.datagrid(response, dataGrid);
                    return;
                }

                o = var12.next();
            } while(!(o instanceof TSUser));

            TSUser cfe = (TSUser)o;
            if(cfe.getId() != null && !"".equals(cfe.getId())) {
                List<TSRoleUser> roleUser = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", cfe.getId());
                if(roleUser.size() > 0) {
                    String roleName = "";

                    TSRoleUser ru;
                    for(Iterator var17 = roleUser.iterator(); var17.hasNext(); roleName = roleName + ru.getTSRole().getRoleName() + ",") {
                        ru = (TSRoleUser)var17.next();
                    }

                    roleName = roleName.substring(0, roleName.length() - 1);
                    cfe.setUserKey(roleName);
                }
            }

            cfeList.add(cfe);
        }
    }

    @RequestMapping(
            params = {"realnamedatagrid"}
    )
    public void realnamedatagrid(TSUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
        HqlGenerateUtil.installHql(cq, user);
        Short[] userstate = new Short[]{Globals.User_Normal, Globals.User_Forbidden};
        TSUser currentUser = ResourceUtil.getSessionUserName();
        new ArrayList();
        String id = PropertiesUtil.getProperties();
        List<TSRoleUser> roleUsers = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", currentUser.getId());
        TSRoleUser tsRU = (TSRoleUser)roleUsers.get(0);
        TSRole role = tsRU.getTSRole();
        if("402881a85c858d85015c862c45bb0010".equals(role.getId())) {
            String[] userKeys = new String[]{"区域负责人"};
            cq.in("userKey", userKeys);
            cq.eq("territoryId", currentUser.getTerritoryId());
            cq.eq("userType", "0");
        } else {
            cq.eq("userType", "0");
        }

        cq.in("status", userstate);
        cq.eq("deleteFlag", Globals.Delete_Normal);
        String orgIds = request.getParameter("orgIds");
        this.extractIdListByComma(orgIds);
        cq.add();
        this.systemService.getDataGridReturn(cq, true);
        List<TSUser> cfeList = new ArrayList();
        Iterator var16 = dataGrid.getResults().iterator();

        while(true) {
            Object o;
            Iterator var21;
            do {
                if(!var16.hasNext()) {
                    List<TSUser> results = dataGrid.getResults();
                    List<TSUser> resultss = new ArrayList();

                    for(int i = 0; i < results.size(); ++i) {
                        user = (TSUser)results.get(i);
                        String territoryId = ((TSUser)results.get(i)).getTerritoryId();
                        List<WTerritoryEntity> territoryEntity = this.systemService.findByProperty(WTerritoryEntity.class, "id", territoryId);
                        if(territoryEntity.size() > 0) {
                            var21 = territoryEntity.iterator();

                            while(var21.hasNext()) {
                                WTerritoryEntity cashAccount = (WTerritoryEntity)var21.next();
                                user.setTerritoryName(cashAccount.getTerritoryname());
                            }
                        }

                        resultss.add(user);
                    }

                    dataGrid.setResults(resultss);
                    TagUtil.datagrid(response, dataGrid);
                    return;
                }

                o = var16.next();
            } while(!(o instanceof TSUser));

            TSUser cfe = (TSUser)o;
            if(cfe.getId() != null && !"".equals(cfe.getId())) {
                List<TSRoleUser> roleUser = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", cfe.getId());
                if(roleUser.size() > 0) {
                    String roleName = "";

                    TSRoleUser ru;
                    for(var21 = roleUser.iterator(); var21.hasNext(); roleName = roleName + ru.getTSRole().getRoleName() + ",") {
                        ru = (TSRoleUser)var21.next();
                    }

                    roleName = roleName.substring(0, roleName.length() - 1);
                    cfe.setUserKey(roleName);
                }
            }

            cfeList.add(cfe);
        }
    }

    @RequestMapping(
            params = {"del"}
    )
    @ResponseBody
    public AjaxJson del(TSUser user, HttpServletRequest req) {
        String message = null;
        AjaxJson j = new AjaxJson();
        if("admin".equals(user.getUserName())) {
            message = "管理员[admin]不可删除";
            j.setMsg(message);
            return j;
        } else if("root".equals(user.getUserName())) {
            message = "超级管理员[root]不可删除";
            j.setMsg(message);
            return j;
        } else {
            user = (TSUser)this.systemService.getEntity(TSUser.class, user.getId());
            List<TSRoleUser> roleUser = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
            if(!user.getStatus().equals(Globals.User_ADMIN)) {
                user.setDeleteFlag(Globals.Delete_Forbidden);
                this.userService.updateEntitie(user);
                message = "用户：" + user.getUserName() + "删除成功";
                if(roleUser.size() > 0) {
                    this.delRoleUser(user);
                    this.userService.delete(user);
                    message = "用户：" + user.getUserName() + "删除成功";
                    this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
                } else {
                    this.userService.delete(user);
                    message = "用户：" + user.getUserName() + "删除成功";
                }
            } else {
                message = "超级管理员不可删除";
            }

            j.setMsg(message);
            return j;
        }
    }

    public void delRoleUser(TSUser user) {
        List<TSRoleUser> roleUserList = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
        if(roleUserList.size() >= 1) {
            Iterator var4 = roleUserList.iterator();

            while(var4.hasNext()) {
                TSRoleUser tRoleUser = (TSRoleUser)var4.next();
                this.systemService.delete(tRoleUser);
            }
        }

    }

    @RequestMapping(
            params = {"checkUser"}
    )
    @ResponseBody
    public ValidForm checkUser(HttpServletRequest request) {
        ValidForm v = new ValidForm();
        String userName = oConvertUtils.getString(request.getParameter("param"));
        String code = oConvertUtils.getString(request.getParameter("code"));
        List<TSUser> roles = this.systemService.findByProperty(TSUser.class, "userName", userName);
        if(roles.size() > 0 && !code.equals(userName)) {
            v.setInfo("用户名已存在");
            v.setStatus("n");
        }

        return v;
    }

    @RequestMapping(
            params = {"saveUser"}
    )
    @ResponseBody
    public AjaxJson saveUser(HttpServletRequest req, TSUser user) {
        String message = null;
        AjaxJson j = new AjaxJson();
        String roleid = oConvertUtils.getString(req.getParameter("roleid"));
        String password = oConvertUtils.getString(req.getParameter("password"));
        String orgIds = oConvertUtils.getString(req.getParameter("orgIds"));
        TSUser users;
        List ru;
        if(StringUtil.isNotEmpty(user.getId())) {
            users = (TSUser)this.systemService.getEntity(TSUser.class, user.getId());
            users.setEmail(user.getEmail());
            users.setOfficePhone(user.getOfficePhone());
            users.setMobilePhone(user.getMobilePhone());
            users.setCreateDate(new Date());
            users.setAreaCode(user.getAreaCode());
            users.setPhoneCountry(user.getPhoneCountry());
            users.setRealName(user.getRealName());
            users.setAge(user.getAge());
            users.setHeadPortrait(user.getHeadPortrait());
            users.setPhone(user.getPhone());
            users.setNationality(user.getNationality());
            users.setCity(user.getCity());
            users.setMotherTongue(user.getMotherTongue());
            users.setSecondLanguage(user.getSecondLanguage());
            users.setDocumentType(user.getDocumentType());
            users.setIdentificationNumber(user.getIdentificationNumber());
            users.setStatus(Globals.User_Normal);
            users.setActivitiSync(user.getActivitiSync());
            if(StringUtil.isNotEmpty(roleid)) {
                TSRole TSRole = (TSRole)this.systemService.findUniqueByProperty(TSRole.class, "id", roleid);
                users.setUserKey(TSRole.getRoleName());
            }

            this.systemService.saveOrUpdate(users);
            ru = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
            this.systemService.deleteAllEntitie(ru);
            message = "用户: " + users.getUserName() + "更新成功";
            if(StringUtil.isNotEmpty(roleid)) {
                this.saveRoleUser(users, roleid);
            }

            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } else {
            users = (TSUser)this.systemService.findUniqueByProperty(TSUser.class, "userName", user.getUserName());
            ru = this.systemService.findByProperty(TSBaseUser.class, "phone", user.getPhone());
            if(ru.size() > 0) {
                message = "手机号: " + users.getPhone() + "已经存在";
            } else if(users != null) {
                message = "用户: " + users.getUserName() + "已经存在";
            } else {
                user.setPassword(PasswordUtil.encrypt(user.getPhone(), password, PasswordUtil.getStaticSalt()));
                user.setStatus(Globals.User_Normal);
                user.setDeleteFlag(Globals.Delete_Normal);
                user.setCreateDate(new Date());
                user.setUserType("1");
                user.setSingular("0");
                user.setReportCount("0");
                if(StringUtil.isNotEmpty(roleid)) {
                    TSRole TSRole = (TSRole)this.systemService.findUniqueByProperty(TSRole.class, "id", roleid);
                    user.setUserKey(TSRole.getRoleName());
                }

                this.systemService.save(user);
                message = "用户: " + user.getUserName() + "添加成功";
                if(StringUtil.isNotEmpty(roleid)) {
                    this.saveRoleUser(user, roleid);
                }

                this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
            }
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"saveUser1"}
    )
    @ResponseBody
    public AjaxJson saveUser1(HttpServletRequest req, TSUser user) {
        String message = null;
        AjaxJson j = new AjaxJson();
        String roleid = oConvertUtils.getString(req.getParameter("roleid"));
        String password = oConvertUtils.getString(req.getParameter("password"));
        String orgIds = oConvertUtils.getString(req.getParameter("orgIds"));
        List<TSBaseUser> baseUser = this.systemService.findByProperty(TSBaseUser.class, "phone", user.getPhone());
        if(baseUser.size() > 0) {
            Iterator var10 = baseUser.iterator();

            while(var10.hasNext()) {
                TSBaseUser base = (TSBaseUser)var10.next();
                if(!base.getId().equals(user.getId()) && baseUser.size() > 0) {
                    message = "手机号: " + user.getPhone() + "已经存在";
                    j.setMsg(message);
                    return j;
                }
            }
        }

        TSUser currentUser = ResourceUtil.getSessionUserName();
        new ArrayList();
        String id = PropertiesUtil.getProperties();
        List<TSRoleUser> roleUsers = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", currentUser.getId());
        TSRoleUser tsRU = (TSRoleUser)roleUsers.get(0);
        TSRole role = tsRU.getTSRole();
        TSUser users;
        String[] roleidStr;
        String roleidTemp;
        if(StringUtil.isNotEmpty(user.getId())) {
            users = (TSUser)this.systemService.getEntity(TSUser.class, user.getId());
            users.setEmail(user.getEmail());
            users.setOfficePhone(user.getOfficePhone());
            users.setMobilePhone(user.getMobilePhone());
            users.setCreateDate(new Date());
            users.setRealName(user.getRealName());
            users.setPhone(user.getPhone());
            users.setUserType(user.getUserType());
            users.setStatus(Globals.User_Normal);
            users.setCertification(user.getCertification());
            users.setHeadPortrait(user.getHeadPortrait());
            users.setCity(user.getCity());
            roleidStr = roleid.split(",");
            roleidTemp = roleidStr[0];
            if("402881a85c858d85015c862c45bb0010".equals(role.getId())) {
                users.setTerritoryId(currentUser.getTerritoryId());
            } else if(!"297e47fc5b8ea693015b8ead75b4000e".equals(roleidTemp) && !"402881a85c858d85015c862c45bb0010".equals(roleidTemp)) {
                users.setTerritoryId((String)null);
                users.setCity((String)null);
            } else if("1".equals(user.getTerritoryId())) {
                users.setTerritoryId("2");
            } else if("19".equals(user.getTerritoryId())) {
                users.setTerritoryId("20");
            } else if("801".equals(user.getTerritoryId())) {
                users.setTerritoryId("802");
            } else if("2323".equals(user.getTerritoryId())) {
                users.setTerritoryId("2324");
            } else {
                users.setTerritoryId(user.getTerritoryId());
            }

            users.setActivitiSync(user.getActivitiSync());
            if(StringUtil.isNotEmpty(roleid)) {
                TSRole TSRole = (TSRole)this.systemService.findUniqueByProperty(TSRole.class, "id", roleid.replace(",", ""));
                users.setUserKey(TSRole.getRoleName());
            }

            this.systemService.saveOrUpdate(users);
            List<TSRoleUser> ru = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
            this.systemService.deleteAllEntitie(ru);
            message = "用户: " + users.getUserName() + "更新成功";
            if(StringUtil.isNotEmpty(roleid)) {
                this.saveRoleUser(users, roleid);
            }

            Builder bb = UserPayload.newBuilder();
            if(!"".equals(user.getUserName()) && user.getUserName() != null) {
                bb.setNickname(user.getUserName());
            } else {
                bb.setNickname(user.getUserName());
            }

            UserPayload uPayload = bb.build();
            ResponseWrapper ress = JMessageUserUtils.updateUserInfo(user.getPhone(), uPayload);
            System.out.println(ress);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } else {
            users = (TSUser)this.systemService.findUniqueByProperty(TSUser.class, "userName", user.getUserName());
            if(users != null) {
                message = "用户: " + users.getUserName() + "已经存在";
            } else {
                user.setPassword(PasswordUtil.encrypt(user.getPhone(), password, PasswordUtil.getStaticSalt()));
                user.setStatus(Globals.User_Normal);
                user.setDeleteFlag(Globals.Delete_Normal);
                user.setCreateDate(new Date());
                user.setCertification("0");
                user.setSingular("0");
                user.setReportCount("0");
                user.setCity(user.getCity());
                if(StringUtil.isNotEmpty(roleid)) {
                    TSRole TSRole = (TSRole)this.systemService.findUniqueByProperty(TSRole.class, "id", roleid);
                    user.setUserKey(TSRole.getRoleName());
                }

                roleidStr = roleid.split(",");
                roleidTemp = roleidStr[0];
                if("402881a85c858d85015c862c45bb0010".equals(role.getId())) {
                    user.setTerritoryId(currentUser.getTerritoryId());
                } else if(!"297e47fc5b8ea693015b8ead75b4000e".equals(roleidTemp) && !"402881a85c858d85015c862c45bb0010".equals(roleidTemp)) {
                    user.setTerritoryId((String)null);
                    user.setCity((String)null);
                } else if("1".equals(user.getTerritoryId())) {
                    user.setTerritoryId("2");
                } else if("19".equals(user.getTerritoryId())) {
                    user.setTerritoryId("20");
                } else if("801".equals(user.getTerritoryId())) {
                    user.setTerritoryId("802");
                } else if("2323".equals(user.getTerritoryId())) {
                    user.setTerritoryId("2324");
                } else {
                    user.setTerritoryId(user.getTerritoryId());
                }

                this.systemService.save(user);
                ResponseWrapper res = JMessageUserUtils.registerUsers(user.getPhone(), password);
                System.out.println(res);
            }

            message = "用户: " + user.getUserName() + "添加成功";
            if(StringUtil.isNotEmpty(roleid)) {
                this.saveRoleUser(user, roleid);
            }

            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"saveUser2"}
    )
    @ResponseBody
    public AjaxJson saveUser2(HttpServletRequest req, TSUser user) {
        String message = null;
        AjaxJson j = new AjaxJson();
        String roleid = oConvertUtils.getString(req.getParameter("roleid"));
        String password = oConvertUtils.getString(req.getParameter("password"));
        String orgIds = oConvertUtils.getString(req.getParameter("orgIds"));
        TSUser currentUser = ResourceUtil.getSessionUserName();
        new ArrayList();
        String id = PropertiesUtil.getProperties();
        List<TSRoleUser> roleUsers = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", currentUser.getId());
        TSRoleUser tsRU = (TSRoleUser)roleUsers.get(0);
        TSRole role = tsRU.getTSRole();
        TSUser users;
        String[] roleidStr;
        TSRole TSRole;
        if(StringUtil.isNotEmpty(user.getId())) {
            users = (TSUser)this.systemService.getEntity(TSUser.class, user.getId());
            users.setEmail(user.getEmail());
            users.setOfficePhone(user.getOfficePhone());
            users.setMobilePhone(user.getMobilePhone());
            users.setCreateDate(new Date());
            users.setCertification("1");
            users.setRealName(user.getRealName());
            users.setUserType(user.getUserType());
            users.setStatus(Globals.User_Normal);
            users.setActivitiSync(user.getActivitiSync());
            users.setHeadPortrait(user.getHeadPortrait());
            if(StringUtil.isNotEmpty(roleid)) {
                roleidStr = roleid.split(",");
                roleid = roleidStr[0];
                TSRole = (TSRole)this.systemService.findUniqueByProperty(TSRole.class, "id", roleid);
                users.setUserKey(TSRole.getRoleName());
            }

            users.setSubordinateAdmin(user.getSubordinateAdmin());
            users.setHeadPortrait(user.getHeadPortrait());
            this.systemService.saveOrUpdate(users);
            List<TSRoleUser> ru = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
            this.systemService.deleteAllEntitie(ru);
            message = "用户: " + users.getUserName() + "更新成功";
            if(StringUtil.isNotEmpty(roleid)) {
                this.saveRoleUser(users, roleid);
            }

            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } else if("sysWorker".equals(role.getRoleCode())) {
            users = (TSUser)this.systemService.findUniqueByProperty(TSUser.class, "userName", user.getUserName());
            if(users != null) {
                message = "用户: " + users.getUserName() + "已经存在";
            } else {
                user.setPassword(PasswordUtil.encrypt(user.getPhone(), password, PasswordUtil.getStaticSalt()));
                user.setStatus(Globals.User_Normal);
                user.setDeleteFlag(Globals.Delete_Normal);
                user.setCreateDate(new Date());
                user.setCertification("1");
                user.setSingular("0");
                user.setReportCount("0");
                user.setSubordinateAdmin(currentUser.getId());
                if(StringUtil.isNotEmpty(roleid)) {
                    roleidStr = roleid.split(",");
                    roleid = roleidStr[0];
                    TSRole = (TSRole)this.systemService.findUniqueByProperty(TSRole.class, "id", roleid);
                    user.setUserKey(TSRole.getRoleName());
                }

                this.systemService.save(user);
                message = "用户: " + user.getUserName() + "添加成功";
                if(StringUtil.isNotEmpty(roleid)) {
                    this.saveRoleUser(user, roleid);
                }

                this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
            }
        } else {
            message = "非运营人员不能添加虚拟用户";
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"saveUser3"}
    )
    @ResponseBody
    public AjaxJson saveUser3(HttpServletRequest req, TSUser user) throws ParseException {
        String message = null;
        AjaxJson j = new AjaxJson();
        String roleid = oConvertUtils.getString(req.getParameter("roleid"));
        String password = oConvertUtils.getString(req.getParameter("password"));
        List<TSBaseUser> baseUser = this.systemService.findByProperty(TSBaseUser.class, "phone", user.getPhone());
        if(baseUser.size() > 0) {
            Iterator var9 = baseUser.iterator();

            while(var9.hasNext()) {
                TSBaseUser base = (TSBaseUser)var9.next();
                if(!base.getId().equals(user.getId()) && baseUser.size() > 0) {
                    message = "手机号: " + user.getPhone() + "已经存在";
                    j.setMsg(message);
                    return j;
                }
            }
        }

        if("身份证".equals(user.getDocumentType())) {
            String idCard = IDCardUtil.IDCardValidate(user.getIdentificationNumber());
            if(!"".equals(idCard)) {
                message = "身份证号码错误，请填写正确身份证号码！";
                j.setMsg(message);
                return j;
            }

            user.setIdentificationNumber(user.getIdentificationNumber());
        }

        TSRole TSRole;
        TSUser users;
        String[] roleidStr;
        if(StringUtil.isNotEmpty(user.getId())) {
            users = (TSUser)this.systemService.getEntity(TSUser.class, user.getId());
            users.setEmail(user.getEmail());
            users.setOfficePhone(user.getOfficePhone());
            users.setMobilePhone(user.getMobilePhone());
            users.setCreateDate(new Date());
            users.setIdentificationNumber(user.getIdentificationNumber());
            users.setCertification(user.getCertification());
            users.setHeadPortrait(user.getHeadPortrait());
            users.setCity(user.getCity());
            if("1".equals(user.getTerritoryId())) {
                users.setTerritoryId("2");
            } else if("19".equals(user.getTerritoryId())) {
                users.setTerritoryId("20");
            } else if("801".equals(user.getTerritoryId())) {
                users.setTerritoryId("802");
            } else if("2323".equals(user.getTerritoryId())) {
                users.setTerritoryId("2324");
            } else {
                users.setTerritoryId(user.getTerritoryId());
            }

            users.setRealName(user.getRealName());
            users.setPhone(user.getPhone());
            users.setUserType(user.getUserType());
            users.setStatus(Globals.User_Normal);
            users.setActivitiSync(user.getActivitiSync());
            if(StringUtil.isNotEmpty(roleid)) {
                roleidStr = roleid.split(",");
                TSRole = (TSRole)this.systemService.findUniqueByProperty(TSRole.class, "id", roleidStr[0]);
                users.setUserKey(TSRole.getRoleName());
            }

            this.systemService.saveOrUpdate(users);
            List<TSRoleUser> ru = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
            this.systemService.deleteAllEntitie(ru);
            message = "用户: " + users.getUserName() + "更新成功";
            if(StringUtil.isNotEmpty(roleid)) {
                this.saveRoleUser(users, roleid);
            }

            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } else {
            users = (TSUser)this.systemService.findUniqueByProperty(TSUser.class, "userName", user.getUserName());
            if(users != null) {
                message = "用户: " + users.getUserName() + "已经存在";
            } else {
                user.setPassword(PasswordUtil.encrypt(user.getPhone(), password, PasswordUtil.getStaticSalt()));
                user.setStatus(Globals.User_Normal);
                user.setDeleteFlag(Globals.Delete_Normal);
                user.setCreateDate(new Date());
                user.setCertification("1");
                user.setSingular("0");
                user.setReportCount("0");
                user.setCity(user.getCity());
                if(StringUtil.isNotEmpty(roleid)) {
                    roleidStr = roleid.split(",");
                    TSRole = (TSRole)this.systemService.findUniqueByProperty(TSRole.class, "id", roleidStr[0]);
                    user.setUserKey(TSRole.getRoleName());
                }

                if("1".equals(user.getTerritoryId())) {
                    user.setTerritoryId("2");
                } else if("19".equals(user.getTerritoryId())) {
                    user.setTerritoryId("20");
                } else if("801".equals(user.getTerritoryId())) {
                    user.setTerritoryId("802");
                } else if("2323".equals(user.getTerritoryId())) {
                    user.setTerritoryId("2324");
                } else {
                    user.setTerritoryId(user.getTerritoryId());
                }

                this.systemService.save(user);
                ResponseWrapper res;
                if("1".equals(user.getUserType())) {
                    res = JMessageUserUtils.registerUsers(user.getPhone(), password);
                    System.out.println(res);
                } else if("0".equals(user.getUserType())) {
                    res = JMessageUserUtils.registerUsers(user.getPhone(), password);
                    System.out.println(res);
                }

                message = "用户: " + user.getUserName() + "添加成功";
                if(StringUtil.isNotEmpty(roleid)) {
                    this.saveRoleUser(user, roleid);
                }

                this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
            }
        }

        j.setMsg(message);
        return j;
    }

    protected void saveRoleUser(TSUser user, String roleidstr) {
        String[] roleids = roleidstr.split(",");

        for(int i = 0; i < roleids.length; ++i) {
            TSRoleUser rUser = new TSRoleUser();
            TSRole role = (TSRole)this.systemService.getEntity(TSRole.class, roleids[i]);
            rUser.setTSRole(role);
            rUser.setTSUser(user);
            this.systemService.save(rUser);
        }

    }

    @RequestMapping(
            params = {"roles"}
    )
    public ModelAndView roles(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("system/user/users");
        String ids = oConvertUtils.getString(request.getParameter("ids"));
        mv.addObject("ids", ids);
        return mv;
    }

    @RequestMapping(
            params = {"fictitiousUserRoles"}
    )
    public ModelAndView fictitiousUserRoles(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("system/user/users2");
        String ids = oConvertUtils.getString(request.getParameter("ids"));
        mv.addObject("ids", ids);
        return mv;
    }

    @RequestMapping(
            params = {"generalRoles"}
    )
    public ModelAndView generalRoles(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("system/user/generalUsers");
        String ids = oConvertUtils.getString(request.getParameter("ids"));
        mv.addObject("ids", ids);
        return mv;
    }

    @RequestMapping(
            params = {"datagridRole"}
    )
    public void datagridRole(TSRole tsRole, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSRole.class, dataGrid);
        TSUser currentUser = ResourceUtil.getSessionUserName();
        new ArrayList();
        String id = PropertiesUtil.getProperties();
        List<TSRoleUser> roleUsers = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", currentUser.getId());
        TSRoleUser tsRU = (TSRoleUser)roleUsers.get(0);
        TSRole role = tsRU.getTSRole();
        if("402881a85c858d85015c862c45bb0010".equals(role.getId())) {
            cq.eq("id", "297e47fc5b8ea693015b8ead75b4000e");
        }

        HqlGenerateUtil.installHql(cq, tsRole);
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"fictitiousUserRole"}
    )
    public void fictitiousUserRole(TSRole tsRole, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSRole.class, dataGrid);
        cq.eq("id", "297e47fc5b8ea693015b8eac8be5000a");
        HqlGenerateUtil.installHql(cq, tsRole);
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"generalDatagridRole"}
    )
    public void generalDatagridRole(TSRole tsRole, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSRole.class, dataGrid);
        cq.eq("id", "402881a85c858d85015c862c45bb0010");
        HqlGenerateUtil.installHql(cq, tsRole);
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"addorupdate1"}
    )
    public ModelAndView addorupdate1(TSUser user, HttpServletRequest req) {
        new ArrayList();
        if(StringUtil.isNotEmpty(user.getId())) {
            user = (TSUser)this.systemService.getEntity(TSUser.class, user.getId());
            req.setAttribute("user", user);
            this.idandname(req, user);
            this.getOrgInfos(req, user);
        }

        return new ModelAndView("system/user/user1");
    }

    @RequestMapping(
            params = {"addorupdate3"}
    )
    public ModelAndView addorupdate3(TSUser user, HttpServletRequest req) {
        new ArrayList();
        if(StringUtil.isNotEmpty(user.getId())) {
            user = (TSUser)this.systemService.getEntity(TSUser.class, user.getId());
            req.setAttribute("user", user);
            req.setAttribute("territoryId", user.getTerritoryId());
            req.setAttribute("text", user.getCity());
            this.idandname(req, user);
            this.getOrgInfos(req, user);
        }

        return new ModelAndView("system/user/user3");
    }

    @RequestMapping(
            params = {"regionalManagement"}
    )
    public String regionalManagement(HttpServletRequest request) {
        return "system/user/userList3";
    }

    @RequestMapping(
            params = {"addorupdate2"}
    )
    public ModelAndView addorupdate2(TSUser user, HttpServletRequest req) {
        new ArrayList();
        if(StringUtil.isNotEmpty(user.getId())) {
            user = (TSUser)this.systemService.getEntity(TSUser.class, user.getId());
            req.setAttribute("user", user);
            this.idandname(req, user);
        }

        return new ModelAndView("system/user/user2");
    }

    @RequestMapping(
            params = {"addorupdate"}
    )
    public ModelAndView addorupdate(TSUser user, HttpServletRequest req) {
        new ArrayList();
        if(StringUtil.isNotEmpty(user.getId())) {
            user = (TSUser)this.systemService.getEntity(TSUser.class, user.getId());
            req.setAttribute("user", user);
            req.setAttribute("territoryId", user.getTerritoryId());
            req.setAttribute("text", user.getCity());
            this.idandname(req, user);
        }

        return new ModelAndView("system/user/user");
    }

    @RequestMapping(
            params = {"userOrgSelect"}
    )
    public ModelAndView userOrgSelect(HttpServletRequest request) {
        String userId = oConvertUtils.getString(request.getParameter("userId"));
        List<Object[]> orgArrList = this.systemService.findHql("from TSDepart d,TSUserOrg uo where d.id=uo.tsDepart.id and uo.tsUser.id=?", new String[]{userId});
        TSUser user = (TSUser)this.systemService.getEntity(TSUser.class, userId);
        request.setAttribute("user", user);
        return new ModelAndView("system/user/userOrgSelect");
    }

    public void idandname(HttpServletRequest req, TSUser user) {
        List<TSRoleUser> roleUsers = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
        String roleId = "";
        String roleName = "";
        TSRoleUser tRoleUser;
        if(roleUsers.size() > 0) {
            for(Iterator var7 = roleUsers.iterator(); var7.hasNext(); roleName = roleName + tRoleUser.getTSRole().getRoleName() + ",") {
                tRoleUser = (TSRoleUser)var7.next();
                roleId = roleId + tRoleUser.getTSRole().getId() + ",";
            }
        }

        req.setAttribute("id", roleId);
        req.setAttribute("roleName", roleName);
    }

    public void getOrgInfos(HttpServletRequest req, TSUser user) {
        String orgIds = "";
        String departname = "";
        req.setAttribute("orgIds", orgIds);
    }

    @RequestMapping(
            params = {"choose"}
    )
    public String choose(HttpServletRequest request) {
        List<TSRole> roles = this.systemService.loadAll(TSRole.class);
        request.setAttribute("roleList", roles);
        return "system/membership/checkuser";
    }

    @RequestMapping(
            params = {"chooseUser"}
    )
    public String chooseUser(HttpServletRequest request) {
        String departid = request.getParameter("departid");
        String roleid = request.getParameter("roleid");
        request.setAttribute("roleid", roleid);
        request.setAttribute("departid", departid);
        return "system/membership/userlist";
    }

    @RequestMapping(
            params = {"datagridUser"}
    )
    public void datagridUser(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        String departid = request.getParameter("departid");
        String roleid = request.getParameter("roleid");
        CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
        if(departid.length() > 0) {
            cq.eq("TDepart.departid", Integer.valueOf(oConvertUtils.getInt(departid, 0)));
            cq.add();
        }

        String userid = "";
        if(roleid.length() > 0) {
            List<TSRoleUser> roleUsers = this.systemService.findByProperty(TSRoleUser.class, "TRole.roleid", Integer.valueOf(oConvertUtils.getInt(roleid, 0)));
            TSRoleUser tRoleUser;
            if(roleUsers.size() > 0) {
                for(Iterator var10 = roleUsers.iterator(); var10.hasNext(); userid = userid + tRoleUser.getTSUser().getId() + ",") {
                    tRoleUser = (TSRoleUser)var10.next();
                }
            }

            cq.in("userid", oConvertUtils.getInts(userid.split(",")));
            cq.add();
        }

        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"roleDepart"}
    )
    public String roleDepart(HttpServletRequest request) {
        List<TSRole> roles = this.systemService.loadAll(TSRole.class);
        request.setAttribute("roleList", roles);
        return "system/membership/roledepart";
    }

    @RequestMapping(
            params = {"chooseDepart"}
    )
    public ModelAndView chooseDepart(HttpServletRequest request) {
        String nodeid = request.getParameter("nodeid");
        ModelAndView modelAndView = null;
        if(nodeid.equals("role")) {
            modelAndView = new ModelAndView("system/membership/users");
        } else {
            modelAndView = new ModelAndView("system/membership/departList");
        }

        return modelAndView;
    }

    @RequestMapping(
            params = {"test"}
    )
    public void test(HttpServletRequest request, HttpServletResponse response) {
        String jString = request.getParameter("_dt_json");
        DataTables dataTables = new DataTables(request);
        CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataTables);
        String username = request.getParameter("userName");
        if(username != null) {
            cq.like("userName", username);
            cq.add();
        }

        DataTableReturn dataTableReturn = this.systemService.getDataTableReturn(cq, true);
        TagUtil.datatable(response, dataTableReturn, "id,userName,mobilePhone,TSDepart_departname");
    }

    @RequestMapping(
            params = {"index"}
    )
    public String index() {
        return "bootstrap/main";
    }

    @RequestMapping(
            params = {"main"}
    )
    public String main() {
        return "bootstrap/test";
    }

    @RequestMapping(
            params = {"testpage"}
    )
    public String testpage(HttpServletRequest request) {
        return "test/test";
    }

    @RequestMapping(
            params = {"addsign"}
    )
    public ModelAndView addsign(HttpServletRequest request) {
        String id = request.getParameter("id");
        request.setAttribute("id", id);
        return new ModelAndView("system/user/usersign");
    }

    @RequestMapping(
            params = {"savesign"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson savesign(HttpServletRequest req) {
        String message = null;
        UploadFile uploadFile = new UploadFile(req);
        String id = uploadFile.get("id");
        TSUser user = (TSUser)this.systemService.getEntity(TSUser.class, id);
        uploadFile.setRealPath("signatureFile");
        uploadFile.setCusPath("signature");
        uploadFile.setByteField("signature");
        uploadFile.setBasePath("resources");
        uploadFile.setRename(false);
        uploadFile.setObject(user);
        AjaxJson j = new AjaxJson();
        message = user.getUserName() + "设置签名成功";
        this.systemService.uploadFile(uploadFile);
        this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"testSearch"}
    )
    public void testSearch(TSUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
        if(user.getUserName() != null) {
            cq.like("userName", user.getUserName());
        }

        if(user.getRealName() != null) {
            cq.like("realName", user.getRealName());
        }

        cq.add();
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"changestyle"}
    )
    public String changeStyle(HttpServletRequest request) {
        TSUser user = ResourceUtil.getSessionUserName();
        if(user == null) {
            return "login/login";
        } else {
            SysThemesEnum sysThemesEnum = SysThemesUtil.getSysTheme(request);
            request.setAttribute("indexStyle", sysThemesEnum.getStyle());
            return "system/user/changestyle";
        }
    }

    @RequestMapping(
            params = {"savestyle"}
    )
    @ResponseBody
    public AjaxJson saveStyle(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        j.setSuccess(Boolean.FALSE.booleanValue());
        TSUser user = ResourceUtil.getSessionUserName();
        if(user != null) {
            String indexStyle = request.getParameter("indexStyle");
            if(StringUtils.isNotEmpty(indexStyle)) {
                Cookie cookie = new Cookie("JEECGINDEXSTYLE", indexStyle);
                cookie.setMaxAge(2592000);
                response.addCookie(cookie);
                logger.debug(" ----- 首页样式: indexStyle ----- " + indexStyle);
                j.setSuccess(Boolean.TRUE.booleanValue());
                j.setMsg("样式修改成功，请刷新页面");
            }

            try {
                ClientManager.getInstance().getClient().getFunctions().clear();
            } catch (Exception var7) {
                ;
            }
        } else {
            j.setMsg("请登录后再操作");
        }

        return j;
    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "userController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(TSUser tsUser, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
        HqlGenerateUtil.installHql(cq, tsUser, request.getParameterMap());
        List<TSUser> tsUsers = this.userService.getListByCriteriaQuery(cq, Boolean.valueOf(false));

        for(int i = 0; i < tsUsers.size(); ++i) {
            TSUser user = (TSUser)tsUsers.get(i);
            this.systemService.getSession().evict(user);
            String id = user.getId();
            List<TSRole> roles = this.systemService.getSession().createSQLQuery("select * from t_s_role where id in (select roleid from t_s_role_user where userid=:userid)").addEntity(TSRole.class).setString("userid", id).list();
            String roleCodes = "";

            TSRole role;
            for(Iterator var14 = roles.iterator(); var14.hasNext(); roleCodes = roleCodes + role.getRoleCode() + ",") {
                role = (TSRole)var14.next();
            }

            user.setUserKey(roleCodes.substring(0, roleCodes.length() - 1));
        }

        modelMap.put("fileName", "用户表");
        modelMap.put("entity", TSUser.class);
        modelMap.put("params", new ExportParams("用户表列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", tsUsers);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByT"}
    )
    public String exportXlsByT(TSUser tsUser, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "用户表");
        modelMap.put("entity", TSUser.class);
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

        label199:
        while(var7.hasNext()) {
            Entry<String, MultipartFile> entity = (Entry)var7.next();
            MultipartFile file = (MultipartFile)entity.getValue();
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);

            try {
                List<TSUser> tsUsers = ExcelImportUtil.importExcel(file.getInputStream(), TSUser.class, params);
                Iterator var12 = tsUsers.iterator();

                while(true) {
                    while(true) {
                        while(true) {
                            while(true) {
                                if(!var12.hasNext()) {
                                    continue label199;
                                }

                                TSUser tsUser = (TSUser)var12.next();
                                tsUser.setStatus(new Short("1"));
                                String username = tsUser.getUserName();
                                String roleCodes = tsUser.getUserKey();
                                if(username != null && !username.equals("")) {
                                    if(roleCodes != null && !roleCodes.equals("")) {
                                        String[] roles = roleCodes.split(",");
                                        boolean flag = true;
                                        String[] var20 = roles;
                                        int var19 = roles.length;

                                        for(int var18 = 0; var18 < var19; ++var18) {
                                            String roleCode = var20[var18];
                                            List<TSRole> roleList = this.systemService.findByProperty(TSRole.class, "roleCode", roleCode);
                                            if(roleList.size() == 0) {
                                                flag = false;
                                            }
                                        }

                                        if(flag) {
                                            List<TSUser> users = this.systemService.findByProperty(TSUser.class, "userName", username);
                                            if(users.size() != 0) {
                                                TSUser user = (TSUser)users.get(0);
                                                MyBeanUtils.copyBeanNotNull2Bean(tsUser, user);
                                                this.systemService.saveOrUpdate(user);
                                                String id = user.getId();
                                                this.systemService.executeSql("delete from t_s_role_user where userid='" + id + "'", new Object[0]);
                                                String[] var47 = roles;
                                                int var46 = roles.length;

                                                for(int var45 = 0; var45 < var46; ++var45) {
                                                    String roleCode = var47[var45];
                                                    List<TSRole> roleList = this.systemService.findByProperty(TSRole.class, "roleCode", roleCode);
                                                    TSRoleUser tsRoleUser = new TSRoleUser();
                                                    tsRoleUser.setTSUser(user);
                                                    tsRoleUser.setTSRole((TSRole)roleList.get(0));
                                                    this.systemService.save(tsRoleUser);
                                                }
                                            } else {
                                                this.systemService.save(tsUser);
                                                String[] var44 = roles;
                                                int var42 = roles.length;

                                                for(var19 = 0; var19 < var42; ++var19) {
                                                    String roleCode = var44[var19];
                                                    List<TSRole> roleList = this.systemService.findByProperty(TSRole.class, "roleCode", roleCode);
                                                    TSRoleUser tsRoleUser = new TSRoleUser();
                                                    tsRoleUser.setTSUser(tsUser);
                                                    tsRoleUser.setTSRole((TSRole)roleList.get(0));
                                                    this.systemService.save(tsRoleUser);
                                                }
                                            }

                                            j.setMsg("文件导入成功！");
                                        } else {
                                            j.setMsg("组织机构编码和角色编码不能匹配");
                                        }
                                    } else {
                                        List<TSUser> users = this.systemService.findByProperty(TSUser.class, "userName", username);
                                        if(users.size() != 0) {
                                            TSUser user = (TSUser)users.get(0);
                                            MyBeanUtils.copyBeanNotNull2Bean(tsUser, user);
                                            this.systemService.saveOrUpdate(user);
                                        } else {
                                            this.systemService.save(tsUser);
                                        }
                                    }
                                } else {
                                    j.setMsg("用户名为必填字段，导入失败");
                                }
                            }
                        }
                    }
                }
            } catch (Exception var34) {
                j.setMsg("文件导入失败！");
                logger.error(ExceptionUtil.getExceptionMessage(var34));
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException var33) {
                    var33.printStackTrace();
                }

            }
        }

        return j;
    }

    @RequestMapping(
            params = {"userSelect"}
    )
    public String userSelect() {
        return "system/user/userSelect";
    }

    @RequestMapping(
            params = {"uploadPic"}
    )
    @ResponseBody
    public AjaxJson uploadPic(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        String path_temp = request.getSession().getServletContext().getRealPath("");
        String path = path_temp.substring(0, path_temp.lastIndexOf(separator));
        String imgPath = separator + "upload" + separator + "head";
        String filePath = path + imgPath;
        File fileDir = new File(filePath);
        if(!fileDir.exists()) {
            fileDir.mkdirs();
        }

        File picTempFile = null;
        Iterator var13 = fileMap.entrySet().iterator();

        while(var13.hasNext()) {
            Entry<String, MultipartFile> entity = (Entry)var13.next();
            MultipartFile file = (MultipartFile)entity.getValue();
            String fileNametemp = FileUtils.getExtend(file.getOriginalFilename());
            if("jpg".equals(fileNametemp) || "jpeg".equals(fileNametemp) || "png".equals(fileNametemp) || "gif".equals(fileNametemp) || "bmp".equals(fileNametemp) || "ico".equals(fileNametemp) || "tif".equals(fileNametemp)) {
                String fileName = DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(8) + "." + FileUtils.getExtend(file.getOriginalFilename());
                picTempFile = new File(fileDir, fileName);

                try {
                    if(picTempFile.exists()) {
                        org.apache.commons.io.FileUtils.forceDelete(picTempFile);
                    }

                    FileCopyUtils.copy(file.getBytes(), picTempFile);
                } catch (Exception var21) {
                    var21.printStackTrace();
                    j.setMsg("缩略图上传失败！");
                    j.setSuccess(false);
                }

                String domain = request.getServerName() + ":";
                String pathUrlName = "http://" + domain + "80";
                String picname = pathUrlName + "/upload/head/" + picTempFile.getName();
                j.setObj(picname);
                Map nameMap = new HashMap();
                nameMap.put("name", picname);
                j.setAttributes(nameMap);
            }

            j.setMsg("缩略图上传成功！");
            j.setSuccess(true);
        }

        return j;
    }

    @RequestMapping(
            params = {"deleUser"}
    )
    @ResponseBody
    public AjaxJson deleUser(TSUser user, HttpServletRequest req) {
        String message = null;
        AjaxJson j = new AjaxJson();
        if("admin".equals(user.getUserName())) {
            message = "管理员[admin]不可删除";
            j.setMsg(message);
            return j;
        } else {
            user = (TSUser)this.systemService.getEntity(TSUser.class, user.getId());
            List<TSRoleUser> roleUser = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
            if(!user.getStatus().equals(Globals.User_ADMIN)) {
                List<WCashAccountEntity> cashAccountList = this.systemService.findByQueryString("from WCashAccountEntity where userId='" + user.getId() + "'");
                if(cashAccountList.size() > 0) {
                    for(int a = 0; a < cashAccountList.size(); ++a) {
                        this.systemService.delete((WCashAccountEntity)cashAccountList.get(a));
                    }
                }

                TSBaseUser tSBaseUser;
                if(roleUser.size() > 0) {
                    this.delRoleUser(user);
                    this.userService.delete(user);
                    tSBaseUser = (TSBaseUser)this.systemService.findUniqueByProperty(TSBaseUser.class, "id", user.getId());
                    if(tSBaseUser != null) {
                        this.tSBaseUserService.delete(tSBaseUser);
                    }

                    if(user.getPhone() != null && !"".equals(user.getPhone())) {
                        JMessageUserUtils.deleteUser(user.getPhone());
                    }

                    message = "用户：" + user.getUserName() + "删除成功";
                    this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
                } else {
                    this.userService.delete(user);
                    tSBaseUser = (TSBaseUser)this.systemService.findUniqueByProperty(TSBaseUser.class, "id", user.getId());
                    if(tSBaseUser != null) {
                        this.tSBaseUserService.delete(tSBaseUser);
                    }

                    message = "用户：" + user.getUserName() + "删除成功";
                }
            } else {
                message = "超级管理员不可删除";
            }

            message = "用户：" + user.getUserName() + "删除成功";
            j.setMsg(message);
            return j;
        }
    }
}
