//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.common.hibernate.dialect;

import org.hibernate.dialect.PostgreSQLDialect;

public class MyPostgreSQLDialect extends PostgreSQLDialect {
    public MyPostgreSQLDialect() {
    }

    public boolean useInputStreamToInsertBlob() {
        return true;
    }
}
