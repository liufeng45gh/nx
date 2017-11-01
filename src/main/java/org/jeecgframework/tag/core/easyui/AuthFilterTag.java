//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.tag.core.easyui;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.pojo.base.TSOperation;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthFilterTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    protected String name;
    @Autowired
    private SystemService systemService;

    public AuthFilterTag() {
    }

    public int doStartTag() throws JspException {
        return super.doStartTag();
    }

    public int doEndTag() throws JspException {
        JspWriter out = null;

        try {
            out = this.pageContext.getOut();
            out.print(this.end().toString());
            out.flush();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return 6;
    }

    protected Object end() {
        StringBuilder out = new StringBuilder();
        this.getAuthFilter(out);
        return out;
    }

    protected void getAuthFilter(StringBuilder out) {
        out.append("<script type=\"text/javascript\">");
        out.append("$(document).ready(function(){");
        if(!ResourceUtil.getSessionUserName().getUserName().equals("admin") && Globals.BUTTON_AUTHORITY_CHECK) {
            Set<String> operationCodes = (Set)super.pageContext.getRequest().getAttribute("operationCodes");
            if(operationCodes != null) {
                Iterator var4 = operationCodes.iterator();

                label29:
                while(true) {
                    TSOperation operation;
                    do {
                        if(!var4.hasNext()) {
                            break label29;
                        }

                        String MyoperationCode = (String)var4.next();
                        if(oConvertUtils.isEmpty(MyoperationCode)) {
                            break label29;
                        }

                        this.systemService = (SystemService)ApplicationContextUtil.getContext().getBean(SystemService.class);
                        operation = (TSOperation)this.systemService.getEntity(TSOperation.class, MyoperationCode);
                    } while(!operation.getOperationcode().startsWith(".") && !operation.getOperationcode().startsWith("#"));

                    if(operation.getOperationType().intValue() == Globals.OPERATION_TYPE_HIDE.shortValue()) {
                        out.append("$(\"" + operation.getOperationcode().replaceAll(" ", "") + "\").hide();");
                    } else {
                        out.append("$(\"" + operation.getOperationcode().replaceAll(" ", "") + "\").attr(\"disabled\",\"disabled\");");
                        out.append("$(\"" + operation.getOperationcode().replaceAll(" ", "") + "\").find(\":input\").attr(\"disabled\",\"disabled\");");
                    }
                }
            }
        }

        out.append("});");
        out.append("</script>");
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
