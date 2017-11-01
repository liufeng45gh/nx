//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;

public class MutiLangSqlCriteriaUtil {
    public MutiLangSqlCriteriaUtil() {
    }

    public static void assembleCondition(List<String> fieldLangKeyList, CriteriaQuery cq, String fieldName, String fieldValue) {
        Map<String, String> fieldLangMap = new HashMap();
        Iterator var6 = fieldLangKeyList.iterator();

        while(var6.hasNext()) {
            String nameKey = (String)var6.next();
            String name = MutiLangUtil.getMutiLangInstance().getLang(nameKey);
            fieldLangMap.put(nameKey, name);
        }

        if("*".equals(fieldValue)) {
            fieldValue = "**";
        }

        List<String> paramValueList = new ArrayList();
        Iterator var12 = fieldLangMap.entrySet().iterator();

        while(true) {
            while(var12.hasNext()) {
                Entry<String, String> entry = (Entry)var12.next();
                String fieldLangKey = (String)entry.getKey();
                String fieldLangValue = (String)entry.getValue();
                if(fieldValue.startsWith("*") && fieldValue.endsWith("*")) {
                    if(fieldLangValue.contains(fieldValue.substring(1, fieldValue.length() - 1))) {
                        paramValueList.add(fieldLangKey);
                    }
                } else if(fieldValue.startsWith("*")) {
                    if(fieldLangValue.endsWith(fieldValue.substring(1))) {
                        paramValueList.add(fieldLangKey);
                    }
                } else if(fieldValue.endsWith("*")) {
                    if(fieldLangValue.startsWith(fieldValue.substring(0, fieldValue.length() - 1))) {
                        paramValueList.add(fieldLangKey);
                    }
                } else if(fieldLangValue.equals(fieldValue)) {
                    paramValueList.add(fieldLangKey);
                }
            }

            if(paramValueList.size() == 0) {
                paramValueList.add("~!@#$%_()*&^");
            }

            cq.in(fieldName, paramValueList.toArray());
            cq.add();
            return;
        }
    }
}
