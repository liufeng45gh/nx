//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class ToEntityUtil {
    public ToEntityUtil() {
    }

    public static <T> T toEntityList(List<?> datas, Class<?> clas, String... labels) {
        List<T> entitys = new ArrayList();

        for(int i = 0; i < datas.size(); ++i) {
            Object[] data = (Object[])datas.get(i);

            try {
                T entity = toEntity(clas, data, labels);
                entitys.add(entity);
            } catch (Exception var8) {
                var8.printStackTrace();
            }
        }

        return (T) entitys;
    }

    public static <T> T toEntity(Class<?> clas, Object[] data, String... labels) throws Exception {
        T entity = (T) clas.newInstance();

        for(int j = 0; j < labels.length; ++j) {
            if(data[j] != null && data[j].toString().trim().length() != 0) {
                String methodName = "set" + String.valueOf(labels[j].charAt(0)).toUpperCase() + labels[j].substring(1);
                Field field = entity.getClass().getDeclaredField(labels[j]);
                String parameterType = field.getType().getSimpleName();
                Method method = entity.getClass().getDeclaredMethod(methodName, new Class[]{field.getType()});
                String val = data[j].toString();
                if(parameterType.equals("String")) {
                    method.invoke(entity, new Object[]{val});
                } else if(parameterType.equals("Character")) {
                    method.invoke(entity, new Object[]{Character.valueOf(val.charAt(0))});
                } else if(!parameterType.equals("Boolean")) {
                    if(parameterType.equals("Short")) {
                        method.invoke(entity, new Object[]{Short.valueOf(Short.parseShort(val))});
                    } else if(parameterType.equals("Integer")) {
                        method.invoke(entity, new Object[]{Integer.valueOf(Integer.parseInt(val))});
                    } else if(parameterType.equals("Float")) {
                        method.invoke(entity, new Object[]{Float.valueOf(Float.parseFloat(val))});
                    } else if(parameterType.equals("Long")) {
                        method.invoke(entity, new Object[]{Long.valueOf(Long.parseLong(val))});
                    } else if(parameterType.equals("Double")) {
                        method.invoke(entity, new Object[]{Double.valueOf(Double.parseDouble(val))});
                    }
                } else {
                    method.invoke(entity, new Object[]{Boolean.valueOf(val.equals("true") || val.equals("1"))});
                }
            }
        }

        return entity;
    }
}
