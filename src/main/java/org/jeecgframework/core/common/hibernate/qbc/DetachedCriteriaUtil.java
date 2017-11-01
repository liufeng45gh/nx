//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.common.hibernate.qbc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.StringUtil;

public class DetachedCriteriaUtil {
    public static final String MIDDLE_SEPRATOR_CHAR = "x";
    public static final String SEPARATOR_TWO_SPACE = "  ";
    public static ProjectionList projectionList;
    private static final String ALIAS_KEY_IN_REQUEST = "ALIAS_KEY_IN_REQUEST";
    private static final String HAS_JOIN_TABLE_KEY_IN_REQUEST = "HAS_JOIN_TABLE_KEY_IN_REQUEST";
    private static final String POINT = ".";

    public static ProjectionList getProjectionList() {
        return projectionList;
    }

    private DetachedCriteriaUtil() {
    }

    public static DetachedCriteria createDetachedCriteria(Class<?> pojoClazz, String startChar, String alias) {
        return createDetachedCriteria(pojoClazz, startChar, alias, (String[])null);
    }

    public static DetachedCriteria createDetachedCriteria(Class<?> pojoClazz, String startChar, String alias, String[] columnNames) {
        return createDetachedCriteria(pojoClazz, startChar, alias, columnNames, (String[])null);
    }

    public static DetachedCriteria createDetachedCriteria(Class<?> pojoClazz, String startChar, String alias, String[] columnNames, String[] excludeParameters) {
        DetachedCriteria criteria = DetachedCriteria.forClass(pojoClazz, alias);
        if(columnNames != null) {
            int var10000 = columnNames.length;
        }

        return criteria;
    }

    private static void setAliasToRequest(HttpServletRequest request, Set<String> aliases) {
        request.setAttribute("ALIAS_KEY_IN_REQUEST", aliases);
    }

    private static Set<String> getAliasesFromRequest() {
        Set<String> aliases = (Set)ContextHolderUtils.getRequest().getAttribute("ALIAS_KEY_IN_REQUEST");
        if(aliases == null) {
            aliases = new HashSet(5);
            setAliasToRequest(ContextHolderUtils.getRequest(), (Set)aliases);
        }

        return (Set)aliases;
    }

    private static boolean getHasJoinTatleFromRequest() {
        Boolean hasJoin = (Boolean)ContextHolderUtils.getRequest().getAttribute("HAS_JOIN_TABLE_KEY_IN_REQUEST");
        return hasJoin == null?false:hasJoin.booleanValue();
    }

    public static void selectColumn(DetachedCriteria criteria, String[] columnNames, Class<?> pojoClass, boolean forJoinTable) {
        if(columnNames != null) {
            List<Projection> tempProjectionList = new ArrayList();
            Set<String> aliases = getAliasesFromRequest();
            boolean hasJoniTable = false;
            String rootAlias = criteria.getAlias();
            String[] var11 = columnNames;
            int var10 = columnNames.length;

            for(int var9 = 0; var9 < var10; ++var9) {
                String property = var11[var9];
                if(property.contains("_")) {
                    String[] propertyChain = property.split("_");
                    createAlias(criteria, rootAlias, aliases, propertyChain, 0);
                    tempProjectionList.add(Projections.property(StringUtil.getProperty(property)).as(StringUtil.getProperty(property)));
                    hasJoniTable = true;
                } else {
                    tempProjectionList.add(Projections.property(rootAlias + "." + property).as(property));
                }
            }

            projectionList = Projections.projectionList();
            if(hasJoniTable || forJoinTable || getHasJoinTatleFromRequest()) {
                projectionList.add(Projections.distinct(Projections.id()));
            }

            Iterator var14 = tempProjectionList.iterator();

            while(var14.hasNext()) {
                Projection proj = (Projection)var14.next();
                projectionList.add(proj);
            }

            criteria.setProjection(projectionList);
            if(!hasJoniTable) {
                criteria.setResultTransformer(Transformers.aliasToBean(pojoClass));
            } else {
                criteria.setResultTransformer(new AliasToBean(pojoClass));
            }

        }
    }

    private static void createAlias(DetachedCriteria criteria, String rootAlais, Set<String> aliases, String[] columns, int currentStep) {
        if(currentStep < columns.length - 1) {
            if(!aliases.contains(converArrayToAlias(columns, currentStep))) {
                if(currentStep > 0) {
                    criteria.createAlias(converArrayToAlias(columns, currentStep - 1) + "." + columns[currentStep], converArrayToAlias(columns, currentStep)).setFetchMode(columns[currentStep], FetchMode.JOIN);
                } else {
                    criteria.createAlias(rootAlais + "." + columns[currentStep], converArrayToAlias(columns, currentStep)).setFetchMode(columns[currentStep], FetchMode.JOIN);
                }

                aliases.add(converArrayToAlias(columns, currentStep));
            }

            ++currentStep;
            createAlias(criteria, rootAlais, aliases, columns, currentStep);
        }

    }

    public static String getAliasFromPropertyChainString(String property) {
        return property.contains(".")?property.substring(0, property.lastIndexOf(".")).replaceAll("\\.", "_") + property.substring(property.lastIndexOf(".")):property;
    }

    private static String converArrayToAlias(String[] columns, int currentStep) {
        StringBuilder alias = new StringBuilder();

        for(int i = 0; i <= currentStep; ++i) {
            if(alias.length() > 0) {
                alias.append("_");
            }

            alias.append(columns[i]);
        }

        return alias.toString();
    }
}
