//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.common.dao.jdbc;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.util.Assert;

public class SimpleJdbcTemplate {
    protected final Log logger = LogFactory.getLog(this.getClass());
    protected JdbcTemplate jdbcTemplate;
    protected NamedParameterJdbcTemplate namedJdbcTemplate;
    protected SimpleJdbcInsert simpleJdbcInsert;

    public SimpleJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
    }

    public List find(String sql, Class clazz, Map parameters) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            Assert.notNull(clazz, "集合中对象类型不能为空!");
            return parameters != null?this.jdbcTemplate.query(sql, this.resultBeanMapper(clazz), new Object[]{parameters}):this.jdbcTemplate.query(sql, this.resultBeanMapper(clazz));
        } catch (Exception var5) {
            return null;
        }
    }

    public Object findForObject(String sql, Class clazz, Map parameters) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            Assert.notNull(clazz, "集合中对象类型不能为空!");
            return parameters != null?this.jdbcTemplate.queryForObject(sql, this.resultBeanMapper(clazz), new Object[]{parameters}):this.jdbcTemplate.queryForObject(sql, this.resultBeanMapper(clazz), new Object[]{Long.class});
        } catch (Exception var5) {
            return null;
        }
    }

    public long findForLong(String sql, Map parameters) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            return parameters != null?((Long)this.namedJdbcTemplate.queryForObject(sql, parameters, Long.class)).longValue():((Long)this.jdbcTemplate.queryForObject(sql, Long.class)).longValue();
        } catch (Exception var4) {
            return 0L;
        }
    }

    public Map findForMap(String sql, Map parameters) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            return parameters != null?this.jdbcTemplate.queryForMap(sql, new Object[]{parameters}):this.jdbcTemplate.queryForMap(sql);
        } catch (Exception var4) {
            return null;
        }
    }

    public List<Map<String, Object>> findForListMap(String sql, Map parameters) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            return parameters != null?this.jdbcTemplate.queryForList(sql, new Object[]{parameters}):this.jdbcTemplate.queryForList(sql);
        } catch (Exception var4) {
            return null;
        }
    }

    public int executeForObject(String sql, Object bean) {
        Assert.hasText(sql, "sql语句不正确!");
        return bean != null?this.jdbcTemplate.update(sql, new Object[]{this.paramBeanMapper(bean)}):this.jdbcTemplate.update(sql);
    }

    public int executeForMap(String sql, Map parameters) {
        Assert.hasText(sql, "sql语句不正确!");
        return parameters != null?this.jdbcTemplate.update(sql, new Object[]{parameters}):this.jdbcTemplate.update(sql);
    }

    public int[] batchUpdate(String sql, List<Object[]> batch) {
        int[] updateCounts = this.jdbcTemplate.batchUpdate(sql, batch);
        return updateCounts;
    }

    protected BeanPropertyRowMapper resultBeanMapper(Class clazz) {
        return BeanPropertyRowMapper.newInstance(clazz);
    }

    protected BeanPropertySqlParameterSource paramBeanMapper(Object object) {
        return new BeanPropertySqlParameterSource(object);
    }
}
