//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.util;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Map;
import org.jeecgframework.web.system.pojo.base.DynamicDataSourceEntity;

public class SqlUtil {
    public static final String DATABSE_TYPE_MYSQL = "mysql";
    public static final String DATABSE_TYPE_POSTGRE = "postgresql";
    public static final String DATABSE_TYPE_ORACLE = "oracle";
    public static final String DATABSE_TYPE_SQLSERVER = "sqlserver";
    public static final String MYSQL_SQL = "select * from ( {0}) sel_tab00 limit {1},{2}";
    public static final String POSTGRE_SQL = "select * from ( {0}) sel_tab00 limit {2} offset {1}";
    public static final String ORACLE_SQL = "select * from (select row_.*,rownum rownum_ from ({0}) row_ where rownum <= {1}) where rownum_>{2}";
    public static final String SQLSERVER_SQL = "select * from ( select row_number() over(order by tempColumn) tempRowNumber, * from (select top {1} tempColumn = 0, {0}) t ) tt where tempRowNumber > {2}";
    public static final String MYSQL_ALLTABLES_SQL = "select distinct table_name from information_schema.columns where table_schema = {0}";
    public static final String POSTGRE__ALLTABLES_SQL = "SELECT distinct c.relname AS  table_name FROM pg_class c";
    public static final String ORACLE__ALLTABLES_SQL = "select distinct colstable.table_name as  table_name from user_tab_cols colstable";
    public static final String SQLSERVER__ALLTABLES_SQL = "select distinct c.name as  table_name from sys.objects c";
    public static final String MYSQL_ALLCOLUMNS_SQL = "select column_name from information_schema.columns where table_name = {0} and table_schema = {1}";
    public static final String POSTGRE_ALLCOLUMNS_SQL = "select table_name from information_schema.columns where table_name = {0}";
    public static final String ORACLE_ALLCOLUMNS_SQL = "select column_name from all_tab_columns where table_name ={0}";
    public static final String SQLSERVER_ALLCOLUMNS_SQL = "select name from syscolumns where id={0}";

    public SqlUtil() {
    }

    public static String getFullSql(String sql, Map params) {
        StringBuilder sqlB = new StringBuilder();
        sqlB.append("SELECT t.* FROM ( ");
        sqlB.append(sql + " ");
        sqlB.append(") t ");
        if(params != null && params.size() >= 1) {
            sqlB.append("WHERE 1=1  ");
            Iterator it = params.keySet().iterator();

            while(it.hasNext()) {
                String key = String.valueOf(it.next());
                String value = String.valueOf(params.get(key));
                if(!StringUtil.isEmpty(value) && !"null".equals(value)) {
                    sqlB.append(" AND ");
                    sqlB.append(" " + key + value);
                }
            }
        }

        return sqlB.toString();
    }

    public static String getCountSql(String sql, Map params) {
        String querySql = getFullSql(sql, params);
        querySql = "SELECT COUNT(*) FROM (" + querySql + ") t2";
        return querySql;
    }

    public static String jeecgCreatePageSql(String sql, Map params, int page, int rows) {
        int beginNum = (page - 1) * rows;
        String[] sqlParam = new String[]{sql, String.valueOf(beginNum), String.valueOf(rows)};
        if(ResourceUtil.getJdbcUrl().indexOf("mysql") != -1) {
            sql = MessageFormat.format("select * from ( {0}) sel_tab00 limit {1},{2}", sqlParam);
        } else if(ResourceUtil.getJdbcUrl().indexOf("postgresql") != -1) {
            sql = MessageFormat.format("select * from ( {0}) sel_tab00 limit {2} offset {1}", sqlParam);
        } else {
            int beginIndex = (page - 1) * rows;
            int endIndex = beginIndex + rows;
            sqlParam[2] = Integer.toString(beginIndex);
            sqlParam[1] = Integer.toString(endIndex);
            if(ResourceUtil.getJdbcUrl().indexOf("oracle") != -1) {
                sql = MessageFormat.format("select * from (select row_.*,rownum rownum_ from ({0}) row_ where rownum <= {1}) where rownum_>{2}", sqlParam);
            } else if(ResourceUtil.getJdbcUrl().indexOf("sqlserver") != -1) {
                sqlParam[0] = sql.substring(getAfterSelectInsertPoint(sql));
                sql = MessageFormat.format("select * from ( select row_number() over(order by tempColumn) tempRowNumber, * from (select top {1} tempColumn = 0, {0}) t ) tt where tempRowNumber > {2}", sqlParam);
            }
        }

        return sql;
    }

    public static String jeecgCreatePageSql(String dbKey, String sql, Map params, int page, int rows) {
        int beginNum = (page - 1) * rows;
        String[] sqlParam = new String[]{sql, String.valueOf(beginNum), String.valueOf(rows)};
        DynamicDataSourceEntity dynamicSourceEntity = (DynamicDataSourceEntity)ResourceUtil.dynamicDataSourceMap.get(dbKey);
        String databaseType = dynamicSourceEntity.getDbType();
        if("mysql".equalsIgnoreCase(databaseType)) {
            sql = MessageFormat.format("select * from ( {0}) sel_tab00 limit {1},{2}", sqlParam);
        } else if("postgresql".equalsIgnoreCase(databaseType)) {
            sql = MessageFormat.format("select * from ( {0}) sel_tab00 limit {2} offset {1}", sqlParam);
        } else {
            int beginIndex = (page - 1) * rows;
            int endIndex = beginIndex + rows;
            sqlParam[2] = Integer.toString(beginIndex);
            sqlParam[1] = Integer.toString(endIndex);
            if("oracle".equalsIgnoreCase(databaseType)) {
                sql = MessageFormat.format("select * from (select row_.*,rownum rownum_ from ({0}) row_ where rownum <= {1}) where rownum_>{2}", sqlParam);
            } else if("sqlserver".equalsIgnoreCase(databaseType)) {
                sqlParam[0] = sql.substring(getAfterSelectInsertPoint(sql));
                sql = MessageFormat.format("select * from ( select row_number() over(order by tempColumn) tempRowNumber, * from (select top {1} tempColumn = 0, {0}) t ) tt where tempRowNumber > {2}", sqlParam);
            }
        }

        return sql;
    }

    private static int getAfterSelectInsertPoint(String sql) {
        int selectIndex = sql.toLowerCase().indexOf("select");
        int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");
        return selectIndex + (selectDistinctIndex == selectIndex?15:6);
    }

    public static String getAllTableSql(String dbType, String... param) {
        if(StringUtil.isNotEmpty(dbType)) {
            if(dbType.equals("mysql")) {
                return MessageFormat.format("select distinct table_name from information_schema.columns where table_schema = {0}", param);
            }

            if(dbType.equals("oracle")) {
                return "select distinct colstable.table_name as  table_name from user_tab_cols colstable";
            }

            if(dbType.equals("postgresql")) {
                return "SELECT distinct c.relname AS  table_name FROM pg_class c";
            }

            if(dbType.equals("sqlserver")) {
                return "select distinct c.name as  table_name from sys.objects c";
            }
        }

        return null;
    }

    public static String getAllCloumnSql(String dbType, String... param) {
        if(StringUtil.isNotEmpty(dbType)) {
            if(dbType.equals("mysql")) {
                return MessageFormat.format("select column_name from information_schema.columns where table_name = {0} and table_schema = {1}", param);
            }

            if(dbType.equals("oracle")) {
                return MessageFormat.format("select column_name from all_tab_columns where table_name ={0}", param);
            }

            if(dbType.equals("postgresql")) {
                return MessageFormat.format("select table_name from information_schema.columns where table_name = {0}", param);
            }

            if(dbType.equals("sqlserver")) {
                return MessageFormat.format("select name from syscolumns where id={0}", param);
            }
        }

        return null;
    }
}
