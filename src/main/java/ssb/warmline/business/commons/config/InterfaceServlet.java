//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.commons.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ssb.warmline.business.commons.enums.Code;
import ssb.warmline.business.commons.shortMessage.JedisUtil;
import ssb.warmline.business.commons.utils.CommonUtils;
import ssb.warmline.business.commons.utils.DateUtil;
import ssb.warmline.business.commons.utils.HttpUtils;
import ssb.warmline.business.commons.utils.InstanceUtil;
import ssb.warmline.business.commons.utils.JsonReturn;
import ssb.warmline.business.commons.utils.LogUtil;
import ssb.warmline.business.commons.utils.SerializeUtil;
import ssb.warmline.business.service.businessprocessor.BaseInterface;

public class InterfaceServlet extends HttpServlet {
    protected static final Logger logger = LoggerFactory.getLogger(InterfaceServlet.class);
    public static final Map<String, TSBaseUser> mobileUserLoginMap = new HashMap();
    private ApplicationContext context = null;

    public InterfaceServlet() {
    }

    public void init() throws ServletException {
        this.context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        this.printParameters(req, "get");
        this.action(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        this.printParameters(req, "post");
        this.action(req, resp);
    }

    private void action(HttpServletRequest req, HttpServletResponse response) throws IOException {
        String taskType = req.getParameter("taskType");
        String className = "";
        String methodName = "";
        Object instance = null;
        Class cls = null;
        InterfaceConfig config = null;
        if(StringUtils.isBlank(taskType)) {
            CommonUtils.repsonseToClientWithBody(response, "10012", (Object)null, new String[]{taskType});
        } else {
            try {
                Map map = LoadConfig.getInterfaceMap();
                config = (InterfaceConfig)map.get(taskType);
                boolean isPass = this.checkConfig(req, response, taskType, methodName, instance, config);
                if(isPass) {
                    className = config.getClassName();

                    try {
                        cls = Class.forName(className);
                        instance = cls.newInstance();
                    } catch (Exception var18) {
                        CommonUtils.repsonseToClientWithBody(response, "10015", (Object)null, new String[]{config.getTaskName()});
                        return;
                    }

                    methodName = config.getMethodName();
                    List paramList = config.getParamsList();
                    List params = new ArrayList();

                    for(int i = 0; i < paramList.size(); ++i) {
                        InterfaceParams interfaceParams = (InterfaceParams)paramList.get(i);
                        String parameter = req.getParameter(interfaceParams.getName());
                        params.add(this.castType(parameter, interfaceParams.getDataType()));
                    }

                    BaseInterface base = null;
                    if(!(instance instanceof BaseInterface)) {
                        CommonUtils.repsonseToClientWithBody(response, "10017", (Object)null, new String[]{config.getTaskName()});
                        return;
                    }

                    try {
                        Object instanceObj = this.context.getBean(instance.getClass());
                        base = (BaseInterface)instanceObj;
                        boolean b = base.ipInterceptor(taskType, req);
                        if(!b) {
                            CommonUtils.repsonseToClientWithBody(response, "10104", (Object)null, new String[]{config.getTaskName()});
                            return;
                        }

                        Object ret = InstanceUtil.invokeMethod(instanceObj, methodName, params);
                        base.updateCallCount();
                        this.printResultToClient(response, ret);
                    } catch (Exception var17) {
                        var17.printStackTrace();
                    }
                }
            } catch (Exception var19) {
                var19.printStackTrace();
                LogUtil.saveException(logger, var19);
                CommonUtils.repsonseToClientWithBody(response, "10001", (Object)null, new String[]{config.getTaskName(), var19.getMessage()});
            }

        }
    }

    private boolean checkConfig(HttpServletRequest req, HttpServletResponse response, String taskType, String methodName, Object instance, InterfaceConfig config) throws IOException {
        if(config == null) {
            CommonUtils.repsonseToClientWithBody(response, "10013", (Object)null, new String[]{config.getTaskName()});
            return false;
        } else {
            String requestPath = req.getServletPath();
            if("/ssbchannel.action".equals(requestPath)) {
                if(config.getCheckLogin() != null && "true".equals(config.getCheckLogin()) && !this.checkMobileLogin(req, response, taskType)) {
                    return false;
                }
            } else if(!StringUtils.isBlank(config.getDeviceType())) {
                CommonUtils.repsonseToClientWithBody(response, "10014", (Object)null, new String[]{config.getTaskName()});
                return false;
            }

            if(!"1".equals(config.getStatus())) {
                CommonUtils.repsonseToClientWithBody(response, "10016", (Object)null, new String[]{config.getTaskName()});
                return false;
            } else if(StringUtils.isBlank(config.getClassName())) {
                CommonUtils.repsonseToClientWithBody(response, "10010", (Object)null, new String[]{config.getTaskName()});
                return false;
            } else if(StringUtils.isBlank(config.getMethodName())) {
                CommonUtils.repsonseToClientWithBody(response, "10011", (Object)null, new String[]{config.getTaskName()});
                return false;
            } else {
                List paramList = config.getParamsList();
                StringBuffer buffer = new StringBuffer("#参数");
                boolean paramError = false;

                for(int i = 0; i < paramList.size(); ++i) {
                    InterfaceParams interfaceParams = (InterfaceParams)paramList.get(i);
                    String parameter = req.getParameter(interfaceParams.getName());
                    if("1".equals(interfaceParams.getRequired())) {
                        if(StringUtils.isBlank(parameter)) {
                            paramError = true;
                            buffer.append("[").append(interfaceParams.getName()).append("-").append(interfaceParams.getParamDisp()).append("]");
                        } else {
                            try {
                                this.castType(parameter, interfaceParams.getDataType());
                            } catch (Exception var17) {
                                buffer.setLength(0);
                                buffer.append("参数[").append(interfaceParams.getName()).append("-").append(interfaceParams.getParamDisp()).append("]类型不匹配:").append(interfaceParams.getDataType());
                                CommonUtils.repsonseToClientWithBody(response, "10018", (Object)null, new String[]{config.getTaskName() + "接口中" + buffer.toString()});
                                return false;
                            }
                        }
                    } else {
                        try {
                            this.castType(parameter, interfaceParams.getDataType());
                        } catch (Exception var16) {
                            buffer.setLength(0);
                            buffer.append("参数[").append(interfaceParams.getName()).append("-").append(interfaceParams.getParamDisp()).append("]类型不匹配:").append(interfaceParams.getDataType());
                            CommonUtils.repsonseToClientWithBody(response, "10018", config.getTaskName() + "接口中" + buffer.toString(), new String[0]);
                            return false;
                        }
                    }
                }

                if(paramError) {
                    buffer.append("不能为空");
                    CommonUtils.repsonseToClientWithBody(response, "10019", (Object)null, new String[]{config.getTaskName() + "接口中" + buffer.toString()});
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    private void printResultToClient(HttpServletResponse response, Object ret) {
        if(ret instanceof List) {
            if(((List)ret).size() > 0 && ((List)ret).get(0) instanceof ResponseStatus) {
                CommonUtils.repsonseToClientWithBody(response, "10000", ((List)ret).get(0), new String[0]);
            } else {
                CommonUtils.repsonseToClientWithBody(response, "10000", (List)ret, new String[0]);
            }
        }

        if(ret instanceof Pagination) {
            if(((Pagination)ret).getItems().size() > 0 && ((Pagination)ret).getItems().get(0) instanceof ResponseStatus) {
                CommonUtils.repsonseToClientWithBody(response, "10000", ((Pagination)ret).getItems().get(0), new String[0]);
            } else {
                CommonUtils.repsonseToClientWithBody(response, "10000", (Pagination)ret, new String[0]);
            }
        }

        if(ret instanceof JSONObject) {
            CommonUtils.repsonseToClientWithBody(response, "10000", (JSONObject)ret, new String[0]);
        }

        if(ret instanceof JsonReturn) {
            CommonUtils.repsonseToClientWithBody(response, "10000", ((JsonReturn)ret).getRetmsg(), new String[0]);
        }

        if(ret instanceof Code) {
            Code code = (Code)ret;
            CommonUtils.repsonseToClientWithBody(response, code.getCode(), code.getCodeMsg(), new String[0]);
        }

        if(ret instanceof ResponseObject) {
            ResponseObject ro = (ResponseObject)ret;
            CommonUtils.repsonseToClientWithBody(response, ro.getStatus().getRespCode(), ro.getBody(), new String[0]);
        }

        if(ret instanceof String) {
            CommonUtils.repsonseToClientWithBody(response, "10000", (String)ret, new String[0]);
        }

    }

    public boolean checkMobileLogin(HttpServletRequest req, HttpServletResponse response, String taskType) throws IOException {
        boolean flag = true;
        if(StringUtils.isBlank(req.getParameter("token"))) {
            CommonUtils.repsonseToClientWithBody(response, "10067", (Object)null, new String[0]);
            flag = false;
        }

        if(!"31".equals(taskType) && !"32".equals(taskType)) {
            if(StringUtils.isBlank(req.getParameter("uid"))) {
                CommonUtils.repsonseToClientWithBody(response, "10066", (Object)null, new String[0]);
                flag = false;
            }

            String uid = req.getParameter("uid");
            String loginToken = req.getParameter("token");
            TSBaseUser mobileLogin = (TSBaseUser)mobileUserLoginMap.get(uid);
            if(mobileLogin == null) {
                boolean istrue = JedisUtil.exists(("person" + loginToken).getBytes()).booleanValue();
                if(istrue) {
                    mobileLogin = (TSBaseUser)SerializeUtil.unserizlize(JedisUtil.get(("person" + loginToken).getBytes(), new Integer[0]));
                    if(mobileLogin == null) {
                        CommonUtils.repsonseToClientWithBody(response, "10068", (Object)null, new String[0]);
                        flag = false;
                    } else if(!loginToken.equals(mobileLogin.getToken())) {
                        String logintime = DateUtil.getFormateDate(mobileLogin.getLoginTime(), "yyyy-MM-dd HH:mm");
                        CommonUtils.repsonseToClientWithBody(response, "10069", (Object)null, new String[0]);
                        flag = false;
                    }
                }
            } else if(!loginToken.equals(mobileLogin.getToken())) {
                String logintime = DateUtil.getFormateDate(mobileLogin.getLoginTime(), "yyyy-MM-dd HH:mm");
                CommonUtils.repsonseToClientWithBody(response, "10069", (Object)null, new String[0]);
                flag = false;
            }
        }

        return flag;
    }

    private void printParameters(HttpServletRequest request, String reqType) throws IOException {
        String ipAddr = HttpUtils.getIpAddr(request);
        String taskType = request.getParameter("taskType");
        if("get".equals(reqType)) {
            logger.info(">>>>>>>>>>>>>>>>>>>IP地址:{}调用接口(get):{}--{}?{}", new Object[]{ipAddr, taskType, request.getRequestURL(), request.getQueryString()});
        } else if("post".equals(reqType)) {
            Enumeration<String> e = request.getParameterNames();
            StringBuffer buff = new StringBuffer();

            while(e.hasMoreElements()) {
                String parameterName = (String)e.nextElement();
                String parameterValue = request.getParameter(parameterName);
                buff.append(parameterName).append("=").append(parameterValue).append("&");
            }

            if(buff.length() > 0) {
                buff.deleteCharAt(buff.length() - 1);
            }

            logger.info(">>>>>>>>>>>>>>>>>>>IP地址:{}调用接口(post):{}--{}?{}", new Object[]{ipAddr, taskType, request.getRequestURL(), buff.toString()});
        }

    }

    private Object castType(String parameter, String dataType) {
        Object obj = null;
        if(StringUtils.isBlank(parameter)) {
            return null;
        } else if(StringUtils.isBlank(dataType)) {
            return parameter;
        } else {
            if(dataType.toUpperCase().equals("LONG")) {
                obj = new Long(parameter);
            } else {
                if(!dataType.toUpperCase().equals("INTEGER")) {
                    return parameter;
                }

                obj = new Integer(parameter);
            }

            return obj;
        }
    }
}
