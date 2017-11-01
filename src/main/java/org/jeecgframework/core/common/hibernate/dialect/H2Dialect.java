//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.common.hibernate.dialect;

public class H2Dialect extends Dialect {
    public H2Dialect() {
    }

    public boolean supportsLimit() {
        return true;
    }

    public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
        return (new StringBuffer(sql.length() + 40)).append(sql).append(" limit " + limitPlaceholder).toString();
    }

    public boolean supportsLimitOffset() {
        return true;
    }
}
