//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.common.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.JSONHelper;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Autowired
    private SystemService systemService;
    private static final Logger log = Logger.getLogger(GlobalExceptionResolver.class);
    private static final int WIRTE_DB_MAX_LENGTH = 1500;
    private static final short LOG_LEVEL = 6;
    private static final short LOG_OPT = 3;

    public GlobalExceptionResolver() {
    }

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        boolean isajax = this.isAjax(request, response);
        Throwable deepestException = this.deepestException(ex);
        return this.processException(request, response, handler, deepestException, isajax);
    }

    private boolean isAjax(HttpServletRequest request, HttpServletResponse response) {
        return oConvertUtils.isNotEmpty(request.getHeader("X-Requested-With"));
    }

    private Throwable deepestException(Throwable e) {
        Throwable tmp = e;
        int breakPoint = 0;

        while(tmp.getCause() != null && !tmp.equals(tmp.getCause())) {
            tmp = tmp.getCause();
            ++breakPoint;
            if(breakPoint > 1000) {
                break;
            }
        }

        return tmp;
    }

    private ModelAndView processException(HttpServletRequest request, HttpServletResponse response, Object handler, Throwable ex, boolean isajax) {
        log.error("全局处理异常捕获:", ex);
        this.logDb(ex);
        return isajax?this.processAjax(request, response, handler, ex):this.processNotAjax(request, response, handler, ex);
    }

    private void logDb(Throwable ex) {
        String exceptionMessage = "错误异常: " + ex.getClass().getSimpleName() + ",错误描述：" + ex.getMessage();
        if(oConvertUtils.isNotEmpty(exceptionMessage) && exceptionMessage.length() > 1500) {
            exceptionMessage = exceptionMessage.substring(0, 1500);
        }

        this.systemService.addLog(exceptionMessage, Short.valueOf((short) 6), Short.valueOf((short) 3));
    }

    private ModelAndView processAjax(HttpServletRequest request, HttpServletResponse response, Object handler, Throwable deepestException) {
        ModelAndView empty = new ModelAndView();
        response.setHeader("Cache-Control", "no-store");
        AjaxJson json = new AjaxJson();
        json.setSuccess(true);
        json.setMsg(deepestException.getMessage());
        PrintWriter pw = null;

        try {
            pw = response.getWriter();
            pw.write(JSONHelper.bean2json(json));
            pw.flush();
        } catch (IOException var12) {
            var12.printStackTrace();
        } finally {
            pw.close();
        }

        empty.clear();
        return empty;
    }

    private ModelAndView processNotAjax(HttpServletRequest request, HttpServletResponse response, Object handler, Throwable ex) {
        String exceptionMessage = this.getThrowableMessage(ex);
        Map<String, Object> model = new HashMap();
        model.put("exceptionMessage", exceptionMessage);
        model.put("ex", ex);
        return new ModelAndView("common/error", model);
    }

    public String getThrowableMessage(Throwable ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }
}
