//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.common.dao.jdbc;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.poi.ss.formula.functions.T;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

@Repository("jdbcDao")
public class JdbcDao extends SimpleJdbcTemplate {
    public static final String DATABSE_TYPE_MYSQL = "mysql";
    public static final String DATABSE_TYPE_POSTGRE = "postgresql";
    public static final String DATABSE_TYPE_ORACLE = "oracle";
    public static final String DATABSE_TYPE_SQLSERVER = "sqlserver";
    public static final String MYSQL_SQL = "select * from ( {0}) sel_tab00 limit {1},{2}";
    public static final String POSTGRE_SQL = "select * from ( {0}) sel_tab00 limit {2} offset {1}";
    public static final String ORACLE_SQL = "select * from (select row_.*,rownum rownum_ from ({0}) row_ where rownum <= {1}) where rownum_>{2}";
    public static final String SQLSERVER_SQL = "select * from ( select row_number() over(order by tempColumn) tempRowNumber, * from (select top {1} tempColumn = 0, {0}) t ) tt where tempRowNumber > {2}";

    @Autowired
    public JdbcDao(DataSource dataSource) {
        super(dataSource);
    }

    public List find(String sql, Class clazz, Map parameters) {
        return super.find(sql, clazz, parameters);
    }

    public Object findForObject(String sql, Class clazz, Map parameters) {
        return super.findForObject(sql, clazz, parameters);
    }

    public long findForLong(String sql, Map parameters) {
        return super.findForLong(sql, parameters);
    }

    public Map findForMap(String sql, Map parameters) {
        return super.findForMap(sql, parameters);
    }

    public List<Map<String, Object>> findForListMap(String sql, Map parameters) {
        return super.findForListMap(sql, parameters);
    }

    public int executeForObject(String sql, Object bean) {
        return super.executeForObject(sql, bean);
    }

    public int executeForMap(String sql, Map parameters) {
        return super.executeForMap(sql, parameters);
    }

    public int[] batchUpdate(String sql, List<Object[]> batch) {
        return super.batchUpdate(sql, batch);
    }

    public List<Map<String, Object>> findForJdbc(String sql, int page, int rows) {
        sql = jeecgCreatePageSql(sql, page, rows);
        return this.jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
        return this.jdbcTemplate.queryForList(sql, objs);
    }

    public List<T> findObjForJdbc(String sql, int page, int rows, Class<T> clazz) {
        List<T> rsList = new ArrayList();
        sql = jeecgCreatePageSql(sql, page, rows);
        List<Map<String, Object>> mapList = this.jdbcTemplate.queryForList(sql);
        T po = null;
        Iterator var9 = mapList.iterator();

        while(var9.hasNext()) {
            Map m = (Map)var9.next();

            try {
                po = (T)clazz.newInstance();
                MyBeanUtils.copyMap2Bean_Nobig(po, m);
                rsList.add(po);
            } catch (Exception var11) {
                var11.printStackTrace();
            }
        }

        return rsList;
    }

    public List<Map<String, Object>> findForJdbcParam(String sql, int page, int rows, Object... objs) {
        sql = jeecgCreatePageSql(sql, page, rows);
        return this.jdbcTemplate.queryForList(sql, objs);
    }

    public Map<String, Object> findOneForJdbc(String sql, Object... objs) {
        try {
            return this.jdbcTemplate.queryForMap(sql, objs);
        } catch (EmptyResultDataAccessException var4) {
            return null;
        }
    }

    public Long getCountForJdbc(String sql) {
        return (Long)this.jdbcTemplate.queryForObject(sql, Long.class);
    }

    public Long getCountForJdbcParam(String sql, Object... objs) {
        return (Long)this.jdbcTemplate.queryForObject(sql, objs, Long.class);
    }

    public Integer executeSql2(String sql, List<Object> param) {
        return Integer.valueOf(this.jdbcTemplate.update(sql, new Object[]{param}));
    }

    public Integer executeSql(String sql, Object... param) {
        return Integer.valueOf(this.jdbcTemplate.update(sql, param));
    }

    public Integer countByJdbc(String sql, Object... param) {
        return (Integer)this.jdbcTemplate.queryForObject(sql, param, Integer.class);
    }

    public static String jeecgCreatePageSql(String sql, int page, int rows) {
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

    private static int getAfterSelectInsertPoint(String sql) {
        int selectIndex = sql.toLowerCase().indexOf("select");
        int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");
        return selectIndex + (selectDistinctIndex == selectIndex?15:6);
    }
}
