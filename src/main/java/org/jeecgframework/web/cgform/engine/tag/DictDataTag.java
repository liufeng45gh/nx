//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.engine.tag;

import freemarker.core.Environment;
import freemarker.template.SimpleCollection;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dictDataTag")
public class DictDataTag implements TemplateDirectiveModel {
    private static final Logger LOG = LoggerFactory.getLogger(DictDataTag.class);
    @Autowired
    private SystemService systemService;

    public DictDataTag() {
    }

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String name = this.getAttribute(params, "name");
        if(name == null) {
            throw new TemplateException("Can not find attribute 'name' in data tag", env);
        } else {
            String text = this.getAttribute(params, "text");
            String tablename = this.getAttribute(params, "tablename");
            String var = this.getAttribute(params, "var");
            var = var != null?var:name;
            if(tablename != null && tablename.trim().length() > 0) {
                StringBuilder sql = new StringBuilder("");
                sql.append("select distinct ").append(name).append(" as typecode, ");
                if(text != null && text.trim().length() > 0) {
                    sql.append(text).append(" as typename ");
                } else {
                    sql.append(name).append(" as typename ");
                }

                sql.append(" from ").append(tablename);
                sql.append(" order by ").append(name);
                List<Map<String, Object>> dataList = this.systemService.findForJdbc(sql.toString(), new Object[0]);
                env.setGlobalVariable(var, new SimpleCollection(dataList));
            } else {
                List<TSType> dataList = (List)ResourceUtil.allTypes.get(name.toLowerCase());
                if(dataList == null) {
                    dataList = new ArrayList();
                }

                Iterator var11 = ((List)dataList).iterator();

                while(var11.hasNext()) {
                    TSType s = (TSType)var11.next();
                    String names = s.getTypename();
                    s.setTypename(MutiLangUtil.getMutiLangInstance().getLang(names));
                }

                env.setGlobalVariable(var, new SimpleCollection((Collection)dataList));
            }

            body.render(env.getOut());
        }
    }

    private String getAttribute(Map params, String name) {
        String value = null;
        if(params.containsKey(name)) {
            TemplateModel paramValue = (TemplateModel)params.get(name);

            try {
                value = ((TemplateScalarModel)paramValue).getAsString();
            } catch (TemplateModelException var6) {
                LOG.error("get attribute '{}' error", name, var6);
            }
        }

        return value;
    }
}
