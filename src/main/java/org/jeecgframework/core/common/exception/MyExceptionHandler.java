//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.common.exception;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ExceptionUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MyExceptionHandler implements HandlerExceptionResolver {
    private static final Logger logger = Logger.getLogger(MyExceptionHandler.class);

    public MyExceptionHandler() {
    }

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String exceptionMessage = ExceptionUtil.getExceptionMessage(ex);
        logger.error(exceptionMessage);
        Map<String, Object> model = new HashMap();
        model.put("exceptionMessage", exceptionMessage);
        model.put("ex", ex);
        return new ModelAndView("common/error", model);
    }
}