//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.interceptors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.jeecgframework.core.extend.hqlsearch.SysContextSqlConvert;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.JeecgDataAutorUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.Client;
import org.jeecgframework.web.system.pojo.base.TSDataRule;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSOperation;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class AuthInterceptor implements HandlerInterceptor {
    private static final Logger logger = Logger.getLogger(AuthInterceptor.class);
    private SystemService systemService;
    private List<String> excludeUrls;
    private static List<TSFunction> functionList;

    public AuthInterceptor() {
    }

    public List<String> getExcludeUrls() {
        return this.excludeUrls;
    }

    public void setExcludeUrls(List<String> excludeUrls) {
        this.excludeUrls = excludeUrls;
    }

    public SystemService getSystemService() {
        return this.systemService;
    }

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String requestPath = ResourceUtil.getRequestPath(request);
        HttpSession session = ContextHolderUtils.getSession();
        Client client = ClientManager.getInstance().getClient(session.getId());
        if(client == null) {
            client = ClientManager.getInstance().getClient(request.getParameter("sessionId"));
        }

        if(this.excludeUrls.contains(requestPath)) {
            return true;
        } else if(client != null && client.getUser() != null) {
            if(!this.hasMenuAuth(request) && !client.getUser().getUserName().equals("admin")) {
                response.sendRedirect("loginController.do?noAuth");
                return false;
            } else {
                String functionId = "";
                if(requestPath.equals("cgAutoListController.do?datagrid")) {
                    requestPath = requestPath + "&configId=" + request.getParameter("configId");
                }

                if(requestPath.equals("cgAutoListController.do?list")) {
                    requestPath = requestPath + "&id=" + request.getParameter("id");
                }

                if(requestPath.equals("cgFormBuildController.do?ftlForm")) {
                    requestPath = requestPath + "&tableName=" + request.getParameter("tableName");
                }

                if(requestPath.equals("cgFormBuildController.do?goAddFtlForm")) {
                    requestPath = requestPath + "&tableName=" + request.getParameter("tableName");
                }

                if(requestPath.equals("cgFormBuildController.do?goUpdateFtlForm")) {
                    requestPath = requestPath + "&tableName=" + request.getParameter("tableName");
                }

                if(requestPath.equals("cgFormBuildController.do?goDatilFtlForm")) {
                    requestPath = requestPath + "&tableName=" + request.getParameter("tableName");
                }

                String uri = request.getRequestURI().substring(request.getContextPath().length() + 1);
                String realRequestPath = null;
                if(!uri.endsWith(".do") && !uri.endsWith(".action")) {
                    realRequestPath = uri;
                } else {
                    realRequestPath = requestPath;
                }

                List<TSFunction> functions = this.systemService.findByProperty(TSFunction.class, "functionUrl", realRequestPath);
                if(functions.size() > 0) {
                    functionId = ((TSFunction)functions.get(0)).getId();
                }

                if(!oConvertUtils.isEmpty(functionId)) {
                    Set<String> operationCodes = this.systemService.getOperationCodesByUserIdAndFunctionId(client.getUser().getId(), functionId);
                    request.setAttribute("operationCodes", operationCodes);
                }

                if(!oConvertUtils.isEmpty(functionId)) {
                    List<TSOperation> allOperation = this.systemService.findByProperty(TSOperation.class, "TSFunction.id", functionId);
                    List<TSOperation> newall = new ArrayList();
                    if(allOperation.size() > 0) {
                        Iterator var14 = allOperation.iterator();

                        while(var14.hasNext()) {
                            TSOperation s = (TSOperation)var14.next();
                            newall.add(s);
                        }

                        String hasOperSql = "SELECT operation FROM t_s_role_function fun, t_s_role_user role WHERE  fun.functionid='" + functionId + "' AND fun.operation is not null  AND fun.roleid=role.roleid AND role.userid='" + client.getUser().getId() + "' ";
                        List<String> hasOperList = this.systemService.findListbySql(hasOperSql);
                        Iterator var16 = hasOperList.iterator();

                        while(var16.hasNext()) {
                            String operationIds = (String)var16.next();
                            String[] var20;
                            int var19 = (var20 = operationIds.split(",")).length;

                            for(int var18 = 0; var18 < var19; ++var18) {
                                String operationId = var20[var18];
                                operationId = operationId.replaceAll(" ", "");
                                TSOperation operation = new TSOperation();
                                operation.setId(operationId);
                                newall.remove(operation);
                            }
                        }
                    }

                    request.setAttribute("noauto_operationCodes", newall);
                    List<TSDataRule> MENU_DATA_AUTHOR_RULES = new ArrayList();
                    String MENU_DATA_AUTHOR_RULE_SQL = "";
                    Set<String> dataruleCodes = this.systemService.getOperationCodesByUserIdAndDataId(client.getUser().getId(), functionId);
                    request.setAttribute("dataRulecodes", dataruleCodes);

                    TSDataRule dataRule;
                    for(Iterator var29 = dataruleCodes.iterator(); var29.hasNext(); MENU_DATA_AUTHOR_RULE_SQL = MENU_DATA_AUTHOR_RULE_SQL + SysContextSqlConvert.setSqlModel(dataRule)) {
                        String dataRuleId = (String)var29.next();
                        dataRule = (TSDataRule)this.systemService.getEntity(TSDataRule.class, dataRuleId);
                        MENU_DATA_AUTHOR_RULES.add(dataRule);
                    }

                    JeecgDataAutorUtils.installDataSearchConditon(request, MENU_DATA_AUTHOR_RULES);
                    JeecgDataAutorUtils.installDataSearchConditon(request, MENU_DATA_AUTHOR_RULE_SQL);
                }

                return true;
            }
        } else {
            this.forward(request, response);
            return false;
        }
    }

    private boolean hasMenuAuth(HttpServletRequest request) {
        String requestPath = ResourceUtil.getRequestPath(request);
        boolean bMgrUrl = false;
        if(functionList == null) {
            functionList = this.systemService.findHql("from TSFunction where functionType = ? ", new Object[]{Short.valueOf((short) 0)});
        }

        Iterator var5 = functionList.iterator();

        while(var5.hasNext()) {
            TSFunction function = (TSFunction)var5.next();
            if(function.getFunctionUrl() != null && function.getFunctionUrl().startsWith(requestPath)) {
                bMgrUrl = true;
                break;
            }
        }

        if(!bMgrUrl) {
            return true;
        } else {
            String funcid = oConvertUtils.getString(request.getParameter("clickFunctionId"));
            if(!bMgrUrl && (requestPath.indexOf("loginController.do") != -1 || funcid.length() == 0)) {
                return true;
            } else {
                TSUser currLoginUser = ClientManager.getInstance().getClient(ContextHolderUtils.getSession().getId()).getUser();
                String userid = currLoginUser.getId();
                String sql = "SELECT DISTINCT f.id FROM t_s_function f,t_s_role_function  rf,t_s_role_user ru  WHERE f.id=rf.functionid AND rf.roleid=ru.roleid AND ru.userid='" + userid + "' AND f.functionurl like '" + requestPath + "%'";
                List list = this.systemService.findListbySql(sql);
                if(list.size() == 0) {
                    String functionOfOrgSql = "SELECT DISTINCT f.id from t_s_function f, t_s_role_function rf, t_s_role_org ro  WHERE f.ID=rf.functionid AND rf.roleid=ro.role_id  AND f.functionurl like '" + requestPath + "%'";
                    List functionOfOrgList = this.systemService.findListbySql(functionOfOrgSql);
                    return functionOfOrgList.size() > 0;
                } else {
                    return true;
                }
            }
        }
    }

    @RequestMapping(
            params = {"forword"}
    )
    public ModelAndView forword(HttpServletRequest request) {
        return new ModelAndView(new RedirectView("loginController.do?login"));
    }

    private void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("webpage/login/timeout.jsp").forward(request, response);
    }
}
