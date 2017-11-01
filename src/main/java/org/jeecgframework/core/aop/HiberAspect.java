//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.aop;

import java.io.Serializable;
import java.util.Date;
import org.apache.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.stereotype.Component;

@Component
public class HiberAspect extends EmptyInterceptor {
    private static final Logger logger = Logger.getLogger(HiberAspect.class);
    private static final long serialVersionUID = 1L;

    public HiberAspect() {
    }

    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        TSUser currentUser = null;

        try {
            currentUser = ResourceUtil.getSessionUserName();
        } catch (RuntimeException var8) {
            ;
        }

        if(currentUser == null) {
            return true;
        } else {
            try {
                for(int index = 0; index < propertyNames.length; ++index) {
                    if(!"createDate".equals(propertyNames[index]) && !"createTime".equals(propertyNames[index])) {
                        if("createBy".equals(propertyNames[index])) {
                            if(oConvertUtils.isEmpty(state[index])) {
                                state[index] = ResourceUtil.getUserSystemData("sysUserCode");
                            }
                        } else if("createName".equals(propertyNames[index])) {
                            if(oConvertUtils.isEmpty(state[index])) {
                                state[index] = ResourceUtil.getUserSystemData("sysUserName");
                            }
                        } else if("sysUserCode".equals(propertyNames[index])) {
                            if(oConvertUtils.isEmpty(state[index])) {
                                state[index] = ResourceUtil.getUserSystemData("sysUserCode");
                            }
                        } else if("sysOrgCode".equals(propertyNames[index])) {
                            if(oConvertUtils.isEmpty(state[index])) {
                                state[index] = ResourceUtil.getUserSystemData("sysOrgCode");
                            }
                        } else if("sysCompanyCode".equals(propertyNames[index]) && oConvertUtils.isEmpty(state[index])) {
                            state[index] = ResourceUtil.getUserSystemData("sysCompanyCode");
                        }
                    } else if(oConvertUtils.isEmpty(state[index])) {
                        state[index] = new Date();
                    }
                }
            } catch (RuntimeException var9) {
                var9.printStackTrace();
            }

            return true;
        }
    }

    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        TSUser currentUser = null;

        try {
            currentUser = ResourceUtil.getSessionUserName();
        } catch (RuntimeException var9) {
            ;
        }

        if(currentUser == null) {
            return true;
        } else {
            for(int index = 0; index < propertyNames.length; ++index) {
                if(!"updateDate".equals(propertyNames[index]) && !"updateTime".equals(propertyNames[index])) {
                    if("updateBy".equals(propertyNames[index])) {
                        currentState[index] = ResourceUtil.getUserSystemData("sysUserCode");
                    } else if("updateName".equals(propertyNames[index])) {
                        currentState[index] = ResourceUtil.getUserSystemData("sysUserName");
                    }
                } else {
                    currentState[index] = new Date();
                }
            }

            return true;
        }
    }
}
