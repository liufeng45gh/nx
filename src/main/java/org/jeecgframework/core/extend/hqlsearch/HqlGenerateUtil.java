//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.extend.hqlsearch;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.annotation.query.QueryTimeFormat;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.extend.hqlsearch.parse.ObjectParseUtil;
import org.jeecgframework.core.extend.hqlsearch.parse.PageValueConvertRuleEnum;
import org.jeecgframework.core.extend.hqlsearch.parse.vo.HqlRuleEnum;
import org.jeecgframework.core.util.JSONHelper;
import org.jeecgframework.core.util.JeecgDataAutorUtils;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.demo.entity.test.QueryCondition;
import org.jeecgframework.web.system.pojo.base.TSDataRule;
import org.springframework.util.NumberUtils;

public class HqlGenerateUtil {
    private static final String END = "_end";
    private static final String BEGIN = "_begin";
    private static final SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public HqlGenerateUtil() {
    }

    public static void installHql(CriteriaQuery cq, Object searchObj) {
        installHql(cq, searchObj, (Map)null);
    }

    public static void installHql(CriteriaQuery cq, Object searchObj, Map<String, String[]> parameterMap) {
        installHqlJoinAlias(cq, searchObj, getRuleMap(), parameterMap, "");

        try {
            String json = null;
            if(StringUtil.isNotEmpty(cq.getDataGrid().getSqlbuilder())) {
                json = cq.getDataGrid().getSqlbuilder();
            } else if(parameterMap != null && StringUtil.isNotEmpty(parameterMap.get("sqlbuilder"))) {
                json = ((String[])parameterMap.get("sqlbuilder"))[0];
            }

            if(StringUtil.isNotEmpty(json)) {
                List<QueryCondition> list = JSONHelper.toList(json, QueryCondition.class);
                String sql = getSql(list, "", searchObj.getClass());
                LogUtil.debug("DEBUG sqlbuilder:" + sql);
                cq.add(Restrictions.sqlRestriction(sql));
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        cq.add();
    }

    private static void installHqlJoinAlias(CriteriaQuery cq, Object searchObj, Map<String, TSDataRule> ruleMap, Map<String, String[]> parameterMap, String alias) {
        PropertyDescriptor[] origDescriptors = PropertyUtils.getPropertyDescriptors(searchObj);

        for(int i = 0; i < origDescriptors.length; ++i) {
            String aliasName = (alias.equals("")?"":alias + ".") + origDescriptors[i].getName();
            String name = origDescriptors[i].getName();
            String type = origDescriptors[i].getPropertyType().toString();

            try {
                if(!judgedIsUselessField(name) && PropertyUtils.isReadable(searchObj, name)) {
                    if(ruleMap.containsKey(aliasName)) {
                        addRuleToCriteria((TSDataRule)ruleMap.get(aliasName), aliasName, origDescriptors[i].getPropertyType(), cq);
                    }

                    String beginValue = null;
                    String endValue = null;
                    if(parameterMap != null && parameterMap.containsKey(name + "_begin")) {
                        beginValue = ((String[])parameterMap.get(name + "_begin"))[0].trim();
                    }

                    if(parameterMap != null && parameterMap.containsKey(name + "_end")) {
                        endValue = ((String[])parameterMap.get(name + "_end"))[0].trim();
                    }

                    Object value = PropertyUtils.getSimpleProperty(searchObj, name);
                    Object beginValue_;
                    if(!type.contains("class java.lang") && !type.contains("class java.math")) {
                        if("class java.util.Date".equals(type)) {
                            QueryTimeFormat format = (QueryTimeFormat)origDescriptors[i].getReadMethod().getAnnotation(QueryTimeFormat.class);
                            SimpleDateFormat userDefined = null;
                            if(format != null) {
                                userDefined = new SimpleDateFormat(format.format());
                            }

                            if(StringUtils.isNotBlank(beginValue)) {
                                if(userDefined != null) {
                                    cq.ge(aliasName, userDefined.parse(beginValue));
                                } else if(beginValue.length() == 19) {
                                    cq.ge(aliasName, time.parse(beginValue));
                                } else if(beginValue.length() == 10) {
                                    cq.ge(aliasName, time.parse(beginValue + " 00:00:00"));
                                }
                            }

                            if(StringUtils.isNotBlank(endValue)) {
                                if(userDefined != null) {
                                    cq.ge(aliasName, userDefined.parse(beginValue));
                                } else if(endValue.length() == 19) {
                                    cq.le(aliasName, time.parse(endValue));
                                } else if(endValue.length() == 10) {
                                    cq.le(aliasName, time.parse(endValue + " 23:23:59"));
                                }
                            }

                            if(isNotEmpty(value)) {
                                cq.eq(aliasName, value);
                            }
                        } else if(!StringUtil.isJavaClass(origDescriptors[i].getPropertyType())) {
                            beginValue_ = PropertyUtils.getSimpleProperty(searchObj, name);
                            if(isHaveRuleData(ruleMap, aliasName) || isNotEmpty(beginValue_) && itIsNotAllEmpty(beginValue_)) {
                                cq.createAlias(aliasName, aliasName.replaceAll("\\.", "_"));
                                installHqlJoinAlias(cq, beginValue_, ruleMap, parameterMap, aliasName);
                            }
                        }
                    } else if(value != null && !value.equals("")) {
                        HqlRuleEnum rule = PageValueConvertRuleEnum.convert(value);
                        value = PageValueConvertRuleEnum.replaceValue(rule, value);
                        ObjectParseUtil.addCriteria(cq, aliasName, rule, value);
                    } else if(parameterMap != null) {
                        beginValue_ = null;
                        Object endValue_ = null;
                        if("class java.lang.Integer".equals(type)) {
                            if(!"".equals(beginValue) && beginValue != null) {
                                beginValue_ = Integer.valueOf(Integer.parseInt(beginValue));
                            }

                            if(!"".equals(endValue) && endValue != null) {
                                endValue_ = Integer.valueOf(Integer.parseInt(endValue));
                            }
                        } else if("class java.math.BigDecimal".equals(type)) {
                            if(!"".equals(beginValue) && beginValue != null) {
                                beginValue_ = new BigDecimal(beginValue);
                            }

                            if(!"".equals(endValue) && endValue != null) {
                                endValue_ = new BigDecimal(endValue);
                            }
                        } else if("class java.lang.Short".equals(type)) {
                            if(!"".equals(beginValue) && beginValue != null) {
                                beginValue_ = Short.valueOf(Short.parseShort(beginValue));
                            }

                            if(!"".equals(endValue) && endValue != null) {
                                endValue_ = Short.valueOf(Short.parseShort(endValue));
                            }
                        } else if("class java.lang.Long".equals(type)) {
                            if(!"".equals(beginValue) && beginValue != null) {
                                beginValue_ = Long.valueOf(Long.parseLong(beginValue));
                            }

                            if(!"".equals(endValue) && endValue != null) {
                                endValue_ = Long.valueOf(Long.parseLong(endValue));
                            }
                        } else if("class java.lang.Float".equals(type)) {
                            if(!"".equals(beginValue) && beginValue != null) {
                                beginValue_ = Float.valueOf(Float.parseFloat(beginValue));
                            }

                            if(!"".equals(endValue) && endValue != null) {
                                endValue_ = Float.valueOf(Float.parseFloat(endValue));
                            }
                        } else {
                            beginValue_ = beginValue;
                            endValue_ = endValue;
                        }

                        ObjectParseUtil.addCriteria(cq, aliasName, HqlRuleEnum.GE, beginValue_);
                        ObjectParseUtil.addCriteria(cq, aliasName, HqlRuleEnum.LE, endValue_);
                    }
                }
            } catch (Exception var15) {
                var15.printStackTrace();
            }
        }

    }

    private static boolean isHaveRuleData(Map<String, TSDataRule> ruleMap, String aliasName) {
        Iterator var3 = ruleMap.keySet().iterator();

        while(var3.hasNext()) {
            String key = (String)var3.next();
            if(key.contains(aliasName)) {
                return true;
            }
        }

        return false;
    }

    private static void addRuleToCriteria(TSDataRule tsDataRule, String aliasName, Class propertyType, CriteriaQuery cq) {
        HqlRuleEnum rule = HqlRuleEnum.getByValue(tsDataRule.getRuleConditions());
        if(rule.equals(HqlRuleEnum.IN)) {
            String[] values = tsDataRule.getRuleValue().split(",");
            Object[] objs = new Object[values.length];
            if(!propertyType.equals(String.class)) {
                for(int i = 0; i < values.length; ++i) {
                    ((Object[])objs)[i] = NumberUtils.parseNumber(values[i], propertyType);
                }
            } else {
                objs = values;
            }

            ObjectParseUtil.addCriteria(cq, aliasName, rule, objs);
        } else if(propertyType.equals(String.class)) {
            ObjectParseUtil.addCriteria(cq, aliasName, rule, converRuleValue(tsDataRule.getRuleValue()));
        } else {
            ObjectParseUtil.addCriteria(cq, aliasName, rule, NumberUtils.parseNumber(tsDataRule.getRuleValue(), propertyType));
        }

    }

    private static String converRuleValue(String ruleValue) {
        String value = ResourceUtil.converRuleValue(ruleValue);
        return value != null?value:ruleValue;
    }

    private static boolean judgedIsUselessField(String name) {
        return "class".equals(name) || "ids".equals(name) || "page".equals(name) || "rows".equals(name) || "sort".equals(name) || "order".equals(name);
    }

    public static boolean isNotEmpty(Object value) {
        return value != null && !"".equals(value);
    }

    private static boolean itIsNotAllEmpty(Object param) {
        boolean isNotEmpty = false;

        try {
            PropertyDescriptor[] origDescriptors = PropertyUtils.getPropertyDescriptors(param);

            for(int i = 0; i < origDescriptors.length; ++i) {
                String name = origDescriptors[i].getName();
                if(!"class".equals(name) && PropertyUtils.isReadable(param, name)) {
                    if(Map.class.isAssignableFrom(origDescriptors[i].getPropertyType())) {
                        Map<?, ?> map = (Map)PropertyUtils.getSimpleProperty(param, name);
                        if(map != null && map.size() > 0) {
                            isNotEmpty = true;
                            break;
                        }
                    } else if(Collection.class.isAssignableFrom(origDescriptors[i].getPropertyType())) {
                        Collection<?> c = (Collection)PropertyUtils.getSimpleProperty(param, name);
                        if(c != null && c.size() > 0) {
                            isNotEmpty = true;
                            break;
                        }
                    } else if(StringUtil.isNotEmpty(PropertyUtils.getSimpleProperty(param, name))) {
                        isNotEmpty = true;
                        break;
                    }
                }
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return isNotEmpty;
    }

    private static Map<String, TSDataRule> getRuleMap() {
        Map<String, TSDataRule> ruleMap = new HashMap();
        List<TSDataRule> list = JeecgDataAutorUtils.loadDataSearchConditonSQL();
        if(list != null && list.size() > 0) {
            if(list.get(0) == null) {
                return ruleMap;
            }

            Iterator var3 = list.iterator();

            while(var3.hasNext()) {
                TSDataRule rule = (TSDataRule)var3.next();
                ruleMap.put(rule.getRuleColumn(), rule);
            }
        }

        return ruleMap;
    }

    public static String getSql(List<QueryCondition> list, String tab, Class claszz) {
        StringBuffer sb = new StringBuffer();
        sb.append(" 1=1 ");
        Iterator var5 = list.iterator();

        while(var5.hasNext()) {
            QueryCondition c = (QueryCondition)var5.next();
            String column = invokeFindColumn(claszz, c.getField());
            String type = invokeFindType(claszz, c.getField());
            c.setType(type);
            c.setField(column);
            sb.append(tab + c);
            sb.append("\r\n");
            if(c.getChildren() != null) {
                List list1 = JSONHelper.toList(c.getChildren(), QueryCondition.class);
                sb.append(tab);
                sb.append(c.getRelation() + "( ");
                sb.append(getSql(list1, tab + "\t", claszz));
                sb.append(tab + ")\r\n");
            }
        }

        return sb.toString();
    }

    public static String invokeFindType(Class clazz, String field_name) {
        String type = null;

        try {
            Field field = clazz.getDeclaredField(field_name);
            if(field != null) {
                type = field.getType().getSimpleName();
            }

            return type;
        } catch (Exception var5) {
            return type;
        }
    }

    public static String invokeFindColumn(Class clazz, String field_name) {
        String column = null;

        try {
            Field field = clazz.getDeclaredField(field_name);
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
            Method getMethod = pd.getReadMethod();
            Column col = (Column)getMethod.getAnnotation(Column.class);
            if(col != null) {
                column = col.name();
            }

            return column;
        } catch (Exception var7) {
            return column;
        }
    }

    public static CriteriaQuery getDataAuthorConditionHql(CriteriaQuery cq, Object searchObj) {
        Map<String, TSDataRule> ruleMap = getRuleMap();
        PropertyDescriptor[] origDescriptors = PropertyUtils.getPropertyDescriptors(searchObj);

        for(int i = 0; i < origDescriptors.length; ++i) {
            String aliasName = origDescriptors[i].getName();
            String name = origDescriptors[i].getName();
            String type = origDescriptors[i].getPropertyType().toString();

            try {
                if(!judgedIsUselessField(name) && PropertyUtils.isReadable(searchObj, name)) {
                    if(ruleMap.containsKey(aliasName)) {
                        addRuleToCriteria((TSDataRule)ruleMap.get(aliasName), aliasName, origDescriptors[i].getPropertyType(), cq);
                    }

                    Object value = PropertyUtils.getSimpleProperty(searchObj, name);
                    if(!type.contains("class java.lang") && !type.contains("class java.math")) {
                        if("class java.util.Date".equals(type)) {
                            QueryTimeFormat format = (QueryTimeFormat)origDescriptors[i].getReadMethod().getAnnotation(QueryTimeFormat.class);
                            SimpleDateFormat userDefined = null;
                            if(format != null) {
                                new SimpleDateFormat(format.format());
                            }

                            if(isNotEmpty(value)) {
                                cq.eq(aliasName, value);
                            }
                        } else if(!StringUtil.isJavaClass(origDescriptors[i].getPropertyType())) {
                            Object param = PropertyUtils.getSimpleProperty(searchObj, name);
                            if(isHaveRuleData(ruleMap, aliasName) || isNotEmpty(param) && itIsNotAllEmpty(param)) {
                                cq.createAlias(aliasName, aliasName.replaceAll("\\.", "_"));
                                getDataAuthorConditionHql(cq, param);
                            }
                        }
                    } else if(value != null && !value.equals("")) {
                        HqlRuleEnum rule = PageValueConvertRuleEnum.convert(value);
                        value = PageValueConvertRuleEnum.replaceValue(rule, value);
                        ObjectParseUtil.addCriteria(cq, aliasName, rule, value);
                    }
                }
            } catch (Exception var11) {
                var11.printStackTrace();
            }
        }

        return cq;
    }
}
