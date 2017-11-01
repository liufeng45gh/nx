//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.system.service.impl;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jeecgframework.core.annotation.config.AutoMenu;
import org.jeecgframework.core.annotation.config.AutoMenuOperation;
import org.jeecgframework.core.annotation.config.MenuCodeType;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSIcon;
import org.jeecgframework.web.system.pojo.base.TSOperation;
import org.jeecgframework.web.system.service.MenuInitService;
import org.jeecgframework.web.system.util.PackagesToScanUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("menuInitService")
@Transactional
public class MenuInitServiceImpl extends CommonServiceImpl implements MenuInitService {
    private static final String KEY_SPLIT = "-";
    private static final String MENU_TYPE_ID = "#";
    private static final String MENU_TYPE_CSS = ".";

    public MenuInitServiceImpl() {
    }

    public void initMenu() {
        List<TSFunction> functionList = this.loadAll(TSFunction.class);
        List<TSOperation> operationList = this.loadAll(TSOperation.class);
        Map<String, TSFunction> functionMap = new HashMap();
        Map<String, TSOperation> operationMap = new HashMap();
        Iterator var6 = functionList.iterator();

        StringBuffer key;
        while(var6.hasNext()) {
            TSFunction function = (TSFunction)var6.next();
            key = new StringBuffer();
            key.append(function.getFunctionName() == null?"":function.getFunctionName());
            key.append("-");
            key.append(function.getFunctionLevel() == null?"":function.getFunctionLevel());
            key.append("-");
            key.append(function.getFunctionUrl() == null?"":function.getFunctionUrl());
            functionMap.put(key.toString(), function);
        }

        var6 = operationList.iterator();

        while(var6.hasNext()) {
            TSOperation operation = (TSOperation)var6.next();
            key = new StringBuffer();
            key.append(operation.getTSFunction() == null?"":operation.getTSFunction().getId());
            key.append("-");
            key.append(operation.getOperationcode() == null?"":operation.getOperationcode());
            operationMap.put(key.toString(), operation);
        }

        Set<Class<?>> classSet = PackagesToScanUtil.getClasses(".*");
        Iterator var25 = classSet.iterator();

        while(true) {
            AutoMenu autoMenu;
            Class clazz;
            do {
                do {
                    if(!var25.hasNext()) {
                        return;
                    }

                    clazz = (Class)var25.next();
                } while(!clazz.isAnnotationPresent(AutoMenu.class));

                autoMenu = (AutoMenu)clazz.getAnnotation(AutoMenu.class);
            } while(!StringUtil.isNotEmpty(autoMenu.name()));

            StringBuffer menuKey = new StringBuffer();
            menuKey.append(autoMenu.name());
            menuKey.append("-");
            menuKey.append(autoMenu.level() == null?"":autoMenu.level());
            menuKey.append("-");
            menuKey.append(autoMenu.url() == null?"":autoMenu.url());
            TSFunction function = null;
            if(!functionMap.containsKey(menuKey.toString())) {
                function = new TSFunction();
                function.setFunctionName(autoMenu.name());
                function.setFunctionIframe((Short)null);
                function.setFunctionLevel(Short.valueOf(autoMenu.level()));
                function.setFunctionOrder(Integer.toString(autoMenu.order()));
                function.setFunctionUrl(autoMenu.url());
                function.setTSFunction((TSFunction)null);
                String iconId = autoMenu.icon();
                if(StringUtil.isNotEmpty(iconId)) {
                    Object obj = this.get(TSIcon.class, iconId);
                    if(obj != null) {
                        function.setTSIcon((TSIcon)obj);
                    } else {
                        function.setTSIcon((TSIcon)null);
                    }
                } else {
                    function.setTSIcon((TSIcon)null);
                }

                Serializable id = this.save(function);
                function.setId(id.toString());
            } else {
                function = (TSFunction)functionMap.get(menuKey.toString());
            }

            Method[] methods = clazz.getDeclaredMethods();
            Method[] var15 = methods;
            int var14 = methods.length;

            for(int var13 = 0; var13 < var14; ++var13) {
                Method method = var15[var13];
                if(method.isAnnotationPresent(AutoMenuOperation.class)) {
                    AutoMenuOperation autoMenuOperation = (AutoMenuOperation)method.getAnnotation(AutoMenuOperation.class);
                    if(StringUtil.isNotEmpty(autoMenuOperation.code())) {
                        StringBuffer menuOperationKey = new StringBuffer();
                        menuOperationKey.append(function == null?"":function.getId());
                        menuOperationKey.append("-");
                        String code = "";
                        if(autoMenuOperation.codeType() == MenuCodeType.TAG) {
                            code = autoMenuOperation.code();
                        } else if(autoMenuOperation.codeType() == MenuCodeType.ID) {
                            code = "#" + autoMenuOperation.code();
                        } else if(autoMenuOperation.codeType() == MenuCodeType.CSS) {
                            code = "." + autoMenuOperation.code();
                        }

                        menuOperationKey.append(code);
                        if(!operationMap.containsKey(menuOperationKey.toString())) {
                            TSOperation operation = new TSOperation();
                            operation.setOperationname(autoMenuOperation.name());
                            operation.setOperationcode(code);
                            operation.setOperationicon((String)null);
                            operation.setStatus(Short.valueOf(Short.parseShort(Integer.toString(autoMenuOperation.status()))));
                            operation.setTSFunction(function);
                            String iconId = autoMenuOperation.icon();
                            if(StringUtil.isNotEmpty(iconId)) {
                                TSIcon icon = new TSIcon();
                                icon.setId(iconId);
                                operation.setTSIcon(icon);
                            } else {
                                operation.setTSIcon((TSIcon)null);
                            }

                            this.save(operation);
                        }
                    }
                }
            }
        }
    }
}
