//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.system.controller.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.enums.SysThemesEnum;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.IpUtil;
import org.jeecgframework.core.util.ListtoMenu;
import org.jeecgframework.core.util.NumberComparator;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.SysThemesUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.Client;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSConfig;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleFunction;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.MutiLangServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping({"/loginController"})
public class LoginController extends BaseController {
    private Logger log = Logger.getLogger(LoginController.class);
    private SystemService systemService;
    private UserService userService;
    @Autowired
    private MutiLangServiceI mutiLangService;

    public LoginController() {
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
            params = {"goPwdInit"}
    )
    public String goPwdInit() {
        return "login/pwd_init";
    }

    @RequestMapping(
            params = {"checkuser"}
    )
    @ResponseBody
    public AjaxJson checkuser(TSUser user, HttpServletRequest req) {
        HttpSession session = req.getSession();
        AjaxJson j = new AjaxJson();
        TSBaseUser baseUser = (TSBaseUser)this.systemService.findUniqueByProperty(TSBaseUser.class, "userName", user.getUserName());
        if(baseUser == null) {
            j.setMsg(this.mutiLangService.getLang("您的用户名或者密码错误！"));
            j.setSuccess(false);
            return j;
        } else {
            if(req.getParameter("langCode") != null) {
                req.getSession().setAttribute("lang", req.getParameter("langCode"));
            }

            String randCode = req.getParameter("randCode");
            if(StringUtils.isEmpty(randCode)) {
                j.setMsg(this.mutiLangService.getLang("common.enter.verifycode"));
                j.setSuccess(false);
            } else if(!randCode.equalsIgnoreCase(String.valueOf(session.getAttribute("randCode")))) {
                j.setMsg(this.mutiLangService.getLang("common.verifycode.error"));
                j.setSuccess(false);
            } else {
                String phone = baseUser.getPhone();
                user.setPhone(phone);
                TSUser u = this.userService.checkUserExits(user);
                if(u == null) {
                    j.setMsg(this.mutiLangService.getLang("用户名或者密码错误！"));
                    j.setSuccess(false);
                    return j;
                }

                if(u != null && u.getStatus().shortValue() != 0) {
                    Map<String, Object> attrMap = new HashMap();
                    j.setAttributes(attrMap);
                    String orgId = req.getParameter("orgId");
                    this.saveLoginSuccessInfo(req, u, orgId);
                } else {
                    j.setMsg(this.mutiLangService.getLang("common.username.or.password.error"));
                    j.setSuccess(false);
                }
            }

            return j;
        }
    }

    @RequestMapping(
            params = {"changeDefaultOrg"}
    )
    @ResponseBody
    public AjaxJson changeDefaultOrg(TSUser user, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        Map<String, Object> attrMap = new HashMap();
        String orgId = req.getParameter("orgId");
        TSUser u = this.userService.checkUserExits(user);
        if(oConvertUtils.isNotEmpty(orgId)) {
            attrMap.put("orgNum", Integer.valueOf(1));
            this.saveLoginSuccessInfo(req, u, orgId);
        }

        return j;
    }

    private void saveLoginSuccessInfo(HttpServletRequest req, TSUser user, String orgId) {
        String message = null;
        HttpSession session = ContextHolderUtils.getSession();
        session.setAttribute("LOCAL_CLINET_USER", user);
        message = this.mutiLangService.getLang("common.user") + ": " + user.getUserName() + this.mutiLangService.getLang("common.login.success");
        Client clientOld = ClientManager.getInstance().getClient(session.getId());
        if(clientOld != null && clientOld.getUser() != null && !user.getUserName().equals(clientOld.getUser().getUserName())) {
            ClientManager.getInstance().removeClinet(session.getId());
            session = req.getSession(true);
            session.setAttribute("LOCAL_CLINET_USER", user);
            session.setAttribute("randCode", req.getParameter("randCode"));
            this.checkuser(user, req);
        } else {
            Client client = new Client();
            client.setIp(IpUtil.getIpAddr(req));
            client.setLogindatetime(new Date());
            client.setUser(user);
            ClientManager.getInstance().addClinet(session.getId(), client);
        }

        this.systemService.addLog(message, Globals.Log_Type_LOGIN, Globals.Log_Leavel_INFO);
    }

    @RequestMapping(
            params = {"login"}
    )
    public String login(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        TSUser user = ResourceUtil.getSessionUserName();
        String roles = "";
        if(user == null) {
            return "login/login";
        } else {
            List<TSRoleUser> rUsers = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());

            TSRole role;
            for(Iterator var8 = rUsers.iterator(); var8.hasNext(); roles = roles + role.getRoleName() + ",") {
                TSRoleUser ru = (TSRoleUser)var8.next();
                role = ru.getTSRole();
            }

            if(roles.length() > 0) {
                roles = roles.substring(0, roles.length() - 1);
            }

            modelMap.put("roleName", roles);
            modelMap.put("userName", user.getUserName());
            SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
            if("ace".equals(sysTheme.getStyle()) || "diy".equals(sysTheme.getStyle()) || "acele".equals(sysTheme.getStyle()) || "hplus".equals(sysTheme.getStyle())) {
                request.setAttribute("menuMap", this.getFunctionMap(user));
            }

            Cookie cookie = new Cookie("JEECGINDEXSTYLE", sysTheme.getStyle());
            cookie.setMaxAge(2592000);
            response.addCookie(cookie);
            Cookie[] cookies = request.getCookies();
            Cookie zIndexCookie = new Cookie("ZINDEXNUMBER", "1990");
            zIndexCookie.setMaxAge(86400);
            response.addCookie(zIndexCookie);
            return sysTheme.getIndexPath();
        }
    }

    @RequestMapping(
            params = {"logout"}
    )
    public ModelAndView logout(HttpServletRequest request) {
        HttpSession session = ContextHolderUtils.getSession();
        TSUser user = ResourceUtil.getSessionUserName();
        this.systemService.addLog("用户" + user.getUserName() + "已退出", Globals.Log_Type_EXIT, Globals.Log_Leavel_INFO);
        ClientManager.getInstance().removeClinet(session.getId());
        ModelAndView modelAndView = new ModelAndView(new RedirectView("loginController.do?login"));
        return modelAndView;
    }

    @RequestMapping(
            params = {"left"}
    )
    public ModelAndView left(HttpServletRequest request) {
        TSUser user = ResourceUtil.getSessionUserName();
        HttpSession session = ContextHolderUtils.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if(user.getId() == null) {
            session.removeAttribute("USER_SESSION");
            modelAndView.setView(new RedirectView("loginController.do?login"));
        } else {
            List<TSConfig> configs = this.userService.loadAll(TSConfig.class);
            Iterator var7 = configs.iterator();

            while(var7.hasNext()) {
                TSConfig tsConfig = (TSConfig)var7.next();
                request.setAttribute(tsConfig.getCode(), tsConfig.getContents());
            }

            modelAndView.setViewName("main/left");
            request.setAttribute("menuMap", this.getFunctionMap(user));
        }

        return modelAndView;
    }

    private Map<Integer, List<TSFunction>> getFunctionMap(TSUser user) {
        HttpSession session = ContextHolderUtils.getSession();
        Client client = ClientManager.getInstance().getClient(session.getId());
        if(client.getFunctionMap() != null && client.getFunctionMap().size() != 0) {
            return client.getFunctionMap();
        } else {
            Map<Integer, List<TSFunction>> functionMap = new HashMap();
            Map<String, TSFunction> loginActionlist = this.getUserFunction(user);
            if(loginActionlist.size() > 0) {
                Collection<TSFunction> allFunctions = loginActionlist.values();
                Iterator var8 = allFunctions.iterator();

                while(var8.hasNext()) {
                    TSFunction function = (TSFunction)var8.next();
                    if(function.getFunctionType().intValue() != Globals.Function_TYPE_FROM.intValue()) {
                        if(!functionMap.containsKey(Integer.valueOf(function.getFunctionLevel().shortValue() + 0))) {
                            functionMap.put(Integer.valueOf(function.getFunctionLevel().shortValue() + 0), new ArrayList());
                        }

                        ((List)functionMap.get(Integer.valueOf(function.getFunctionLevel().shortValue() + 0))).add(function);
                    }
                }

                Collection<List<TSFunction>> c = functionMap.values();
                Iterator var9 = c.iterator();

                while(var9.hasNext()) {
                    List<TSFunction> list = (List)var9.next();
                    Collections.sort(list, new NumberComparator());
                }
            }

            client.setFunctionMap(functionMap);
            loginActionlist.clear();
            return functionMap;
        }
    }

    private Map<String, TSFunction> getUserFunction(TSUser user) {
        HttpSession session = ContextHolderUtils.getSession();
        Client client = ClientManager.getInstance().getClient(session.getId());
        if(client.getFunctions() == null || client.getFunctions().size() == 0) {
            Map<String, TSFunction> loginActionlist = new HashMap();
            StringBuilder hqlsb1 = (new StringBuilder("select distinct f from TSFunction f,TSRoleFunction rf,TSRoleUser ru  ")).append("where ru.TSRole.id=rf.TSRole.id and rf.TSFunction.id=f.id and ru.TSUser.id=? ");
            List<TSFunction> list1 = this.systemService.findHql(hqlsb1.toString(), new Object[]{user.getId()});
            Iterator var8 = list1.iterator();

            while(var8.hasNext()) {
                TSFunction function = (TSFunction)var8.next();
                loginActionlist.put(function.getId(), function);
            }

            client.setFunctions(loginActionlist);
            list1.clear();
        }

        return client.getFunctions();
    }

    /** @deprecated */
    private void assembleFunctionsByRole(Map<String, TSFunction> loginActionlist, TSRole role) {
        List<TSRoleFunction> roleFunctionList = this.systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
        Iterator var5 = roleFunctionList.iterator();

        while(var5.hasNext()) {
            TSRoleFunction roleFunction = (TSRoleFunction)var5.next();
            TSFunction function = roleFunction.getTSFunction();
            if(function.getFunctionType().intValue() != Globals.Function_TYPE_FROM.intValue()) {
                loginActionlist.put(function.getId(), function);
            }
        }

    }

    @RequestMapping(
            params = {"home"}
    )
    public ModelAndView home(HttpServletRequest request) {
        SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
        if(!"ace".equals(sysTheme.getStyle()) && !"diy".equals(sysTheme.getStyle()) && !"acele".equals(sysTheme.getStyle())) {
            request.setAttribute("show", "0");
        } else {
            request.setAttribute("show", "1");
        }

        return new ModelAndView("main/home");
    }

    @RequestMapping(
            params = {"acehome"}
    )
    public ModelAndView acehome(HttpServletRequest request) {
        SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
        if(!"ace".equals(sysTheme.getStyle()) && !"diy".equals(sysTheme.getStyle()) && !"acele".equals(sysTheme.getStyle())) {
            request.setAttribute("show", "0");
        } else {
            request.setAttribute("show", "1");
        }

        return new ModelAndView("main/acehome");
    }

    @RequestMapping(
            params = {"hplushome"}
    )
    public ModelAndView hplushome(HttpServletRequest request) {
        SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
        return new ModelAndView("main/hplushome");
    }

    @RequestMapping(
            params = {"noAuth"}
    )
    public ModelAndView noAuth(HttpServletRequest request) {
        return new ModelAndView("common/noAuth");
    }

    @RequestMapping(
            params = {"top"}
    )
    public ModelAndView top(HttpServletRequest request) {
        TSUser user = ResourceUtil.getSessionUserName();
        HttpSession session = ContextHolderUtils.getSession();
        if(user.getId() == null) {
            session.removeAttribute("USER_SESSION");
            return new ModelAndView(new RedirectView("loginController.do?login"));
        } else {
            request.setAttribute("menuMap", this.getFunctionMap(user));
            List<TSConfig> configs = this.userService.loadAll(TSConfig.class);
            Iterator var6 = configs.iterator();

            while(var6.hasNext()) {
                TSConfig tsConfig = (TSConfig)var6.next();
                request.setAttribute(tsConfig.getCode(), tsConfig.getContents());
            }

            return new ModelAndView("main/bootstrap_top");
        }
    }

    @RequestMapping(
            params = {"shortcut_top"}
    )
    public ModelAndView shortcut_top(HttpServletRequest request) {
        TSUser user = ResourceUtil.getSessionUserName();
        HttpSession session = ContextHolderUtils.getSession();
        if(user.getId() == null) {
            session.removeAttribute("USER_SESSION");
            return new ModelAndView(new RedirectView("loginController.do?login"));
        } else {
            request.setAttribute("menuMap", this.getFunctionMap(user));
            List<TSConfig> configs = this.userService.loadAll(TSConfig.class);
            Iterator var6 = configs.iterator();

            while(var6.hasNext()) {
                TSConfig tsConfig = (TSConfig)var6.next();
                request.setAttribute(tsConfig.getCode(), tsConfig.getContents());
            }

            return new ModelAndView("main/shortcut_top");
        }
    }

    @RequestMapping(
            params = {"primaryMenu"}
    )
    @ResponseBody
    public String getPrimaryMenu() {
        List<TSFunction> primaryMenu = (List)this.getFunctionMap(ResourceUtil.getSessionUserName()).get(Integer.valueOf(0));
        String floor = "";
        if(primaryMenu == null) {
            return floor;
        } else {
            Iterator var4 = primaryMenu.iterator();

            while(true) {
                while(true) {
                    TSFunction function;
                    do {
                        if(!var4.hasNext()) {
                            return floor;
                        }

                        function = (TSFunction)var4.next();
                    } while(function.getFunctionLevel().shortValue() != 0);

                    String lang_key = function.getFunctionName();
                    String lang_context = this.mutiLangService.getLang(lang_key);
                    lang_context = lang_context.trim();
                    String s;
                    if("业务申请".equals(lang_context)) {
                        s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>" + lang_context + "</span></div>";
                        floor = floor + " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/ywsq.png' />  <img class='imag2' src='plug-in/login/images/ywsq-up.png' style='display: none;' />" + s + " </li> ";
                    } else if("个人办公".equals(lang_context)) {
                        s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>" + lang_context + "</span></div>";
                        floor = floor + " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/grbg.png' />  <img class='imag2' src='plug-in/login/images/grbg-up.png' style='display: none;' />" + s + " </li> ";
                    } else if("流程管理".equals(lang_context)) {
                        s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>" + lang_context + "</span></div>";
                        floor = floor + " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/lcsj.png' />  <img class='imag2' src='plug-in/login/images/lcsj-up.png' style='display: none;' />" + s + " </li> ";
                    } else if("Online 开发".equals(lang_context)) {
                        floor = floor + " <li><img class='imag1' src='plug-in/login/images/online.png' />  <img class='imag2' src='plug-in/login/images/online_up.png' style='display: none;' /> </li> ";
                    } else if("自定义表单".equals(lang_context)) {
                        s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>" + lang_context + "</span></div>";
                        floor = floor + " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/zdybd.png' />  <img class='imag2' src='plug-in/login/images/zdybd-up.png' style='display: none;' />" + s + " </li> ";
                    } else if("系统监控".equals(lang_context)) {
                        floor = floor + " <li><img class='imag1' src='plug-in/login/images/xtjk.png' />  <img class='imag2' src='plug-in/login/images/xtjk_up.png' style='display: none;' /> </li> ";
                    } else if("统计报表".equals(lang_context)) {
                        floor = floor + " <li><img class='imag1' src='plug-in/login/images/tjbb.png' />  <img class='imag2' src='plug-in/login/images/tjbb_up.png' style='display: none;' /> </li> ";
                    } else if("消息中间件".equals(lang_context)) {
                        s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>" + lang_context + "</span></div>";
                        floor = floor + " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/msg.png' />  <img class='imag2' src='plug-in/login/images/msg_up.png' style='display: none;' />" + s + " </li> ";
                    } else if("系统管理".equals(lang_context)) {
                        floor = floor + " <li><img class='imag1' src='plug-in/login/images/xtgl.png' />  <img class='imag2' src='plug-in/login/images/xtgl_up.png' style='display: none;' /> </li> ";
                    } else if("常用示例".equals(lang_context)) {
                        floor = floor + " <li><img class='imag1' src='plug-in/login/images/cysl.png' />  <img class='imag2' src='plug-in/login/images/cysl_up.png' style='display: none;' /> </li> ";
                    } else if(lang_context.contains("消息推送")) {
                        s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'>消息推送</div>";
                        floor = floor + " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/msg.png' />  <img class='imag2' src='plug-in/login/images/msg_up.png' style='display: none;' />" + s + "</li> ";
                    } else {
                        s = "";
                        if(lang_context.length() >= 5 && lang_context.length() < 7) {
                            s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>" + lang_context + "</span></div>";
                        } else if(lang_context.length() < 5) {
                            s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'>" + lang_context + "</div>";
                        } else if(lang_context.length() >= 7) {
                            s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>" + lang_context.substring(0, 6) + "</span></div>";
                        }

                        floor = floor + " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/default.png' />  <img class='imag2' src='plug-in/login/images/default_up.png' style='display: none;' />" + s + "</li> ";
                    }
                }
            }
        }
    }

    @RequestMapping(
            params = {"primaryMenuDiy"}
    )
    @ResponseBody
    public String getPrimaryMenuDiy() {
        List<TSFunction> primaryMenu = (List)this.getFunctionMap(ResourceUtil.getSessionUserName()).get(Integer.valueOf(1));
        String floor = "";
        if(primaryMenu == null) {
            return floor;
        } else {
            String menuString = "user.manage role.manage department.manage menu.manage";
            Iterator var5 = primaryMenu.iterator();

            while(true) {
                while(true) {
                    TSFunction function;
                    do {
                        do {
                            if(!var5.hasNext()) {
                                return floor;
                            }

                            function = (TSFunction)var5.next();
                        } while(!menuString.contains(function.getFunctionName()));
                    } while(function.getFunctionLevel().shortValue() != 1);

                    String lang_key = function.getFunctionName();
                    String lang_context = this.mutiLangService.getLang(lang_key);
                    String s;
                    if("申请".equals(lang_key)) {
                        lang_context = "申请";
                        s = "";
                        s = "<div style='width:67px;position: absolute;top:47px;text-align:center;color:#000000;font-size:12px;'>" + lang_context + "</div>";
                        floor = floor + " <li><img class='imag1' src='plug-in/login/images/head_icon1.png' />  <img class='imag2' src='plug-in/login/images/head_icon1.png' style='display: none;' />" + s + " </li> ";
                    } else if("Online 开发".equals(lang_context)) {
                        floor = floor + " <li><img class='imag1' src='plug-in/login/images/online.png' />  <img class='imag2' src='plug-in/login/images/online_up.png' style='display: none;' /> </li> ";
                    } else if("统计查询".equals(lang_context)) {
                        floor = floor + " <li><img class='imag1' src='plug-in/login/images/guanli.png' />  <img class='imag2' src='plug-in/login/images/guanli_up.png' style='display: none;' /> </li> ";
                    } else if("系统管理".equals(lang_context)) {
                        floor = floor + " <li><img class='imag1' src='plug-in/login/images/xtgl.png' />  <img class='imag2' src='plug-in/login/images/xtgl_up.png' style='display: none;' /> </li> ";
                    } else if("常用示例".equals(lang_context)) {
                        floor = floor + " <li><img class='imag1' src='plug-in/login/images/cysl.png' />  <img class='imag2' src='plug-in/login/images/cysl_up.png' style='display: none;' /> </li> ";
                    } else if("系统监控".equals(lang_context)) {
                        floor = floor + " <li><img class='imag1' src='plug-in/login/images/xtjk.png' />  <img class='imag2' src='plug-in/login/images/xtjk_up.png' style='display: none;' /> </li> ";
                    } else if(lang_context.contains("消息推送")) {
                        s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'>消息推送</div>";
                        floor = floor + " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/msg.png' />  <img class='imag2' src='plug-in/login/images/msg_up.png' style='display: none;' />" + s + "</li> ";
                    } else {
                        s = "";
                        if(lang_context.length() >= 5 && lang_context.length() < 7) {
                            s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#000000;font-size:12px;'><span style='letter-spacing:-1px;'>" + lang_context + "</span></div>";
                        } else if(lang_context.length() < 5) {
                            s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#000000;font-size:12px;'>" + lang_context + "</div>";
                        } else if(lang_context.length() >= 7) {
                            s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#000000;font-size:12px;'><span style='letter-spacing:-1px;'>" + lang_context.substring(0, 6) + "</span></div>";
                        }

                        floor = floor + " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/head_icon2.png' />  <img class='imag2' src='plug-in/login/images/default_up.png' style='display: none;' />" + s + "</li> ";
                    }
                }
            }
        }
    }

    @RequestMapping(
            params = {"getPrimaryMenuForWebos"}
    )
    @ResponseBody
    public AjaxJson getPrimaryMenuForWebos() {
        AjaxJson j = new AjaxJson();
        Object getPrimaryMenuForWebos = ContextHolderUtils.getSession().getAttribute("getPrimaryMenuForWebos");
        if(oConvertUtils.isNotEmpty(getPrimaryMenuForWebos)) {
            j.setMsg(getPrimaryMenuForWebos.toString());
        } else {
            String PMenu = ListtoMenu.getWebosMenu(this.getFunctionMap(ResourceUtil.getSessionUserName()));
            ContextHolderUtils.getSession().setAttribute("getPrimaryMenuForWebos", PMenu);
            j.setMsg(PMenu);
        }

        return j;
    }

    @RequestMapping(
            params = {"login2"}
    )
    public String login2() {
        return "login/login2";
    }

    @RequestMapping(
            params = {"login3"}
    )
    public String login3() {
        return "login/login3";
    }
}
