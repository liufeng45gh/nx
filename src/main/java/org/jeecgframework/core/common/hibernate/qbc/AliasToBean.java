//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.common.hibernate.qbc;

import com.opensymphony.xwork2.ognl.OgnlUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ognl.Ognl;
import org.hibernate.HibernateException;
import org.hibernate.transform.ResultTransformer;

public class AliasToBean implements ResultTransformer {
    private static final long serialVersionUID = 1L;
    private static final OgnlUtil ognlUntil = new OgnlUtil();
    private static final Map<String, Boolean> context = new HashMap(1);
    private final Class<?> resultClass;

    static {
        context.put("xwork.NullHandler.createNullObjects", Boolean.valueOf(true));
    }

    public AliasToBean(Class<?> pojoClass) {
        if(pojoClass == null) {
            throw new IllegalArgumentException("resultClass cannot be null");
        } else {
            this.resultClass = pojoClass;
        }
    }

    public List transformList(List collection) {
        return collection;
    }

    public Object transformTuple(Object[] tuple, String[] aliases) {
        try {
            Object root = this.resultClass.newInstance();

            for(int i = 0; i < aliases.length; ++i) {
                if(aliases[i] != null && !aliases[i].equals("")) {
                    Ognl.setValue(ognlUntil.compile(aliases[i]), context, root, tuple[i]);
                }
            }

            return root;
        } catch (Exception var5) {
            throw new HibernateException(var5.getMessage(), var5);
        }
    }
}
