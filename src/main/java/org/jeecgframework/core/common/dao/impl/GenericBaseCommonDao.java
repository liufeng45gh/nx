//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.common.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.jeecgframework.core.annotation.JeecgEntityTitle;
import org.jeecgframework.core.common.dao.IGenericBaseCommonDao;
import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.DetachedCriteriaUtil;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.hibernate.qbc.PagerUtil;
import org.jeecgframework.core.common.model.common.DBTable;
import org.jeecgframework.core.common.model.json.DataGridReturn;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.ToEntityUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.vo.datatable.DataTableReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.Assert;

public abstract class GenericBaseCommonDao<T, PK extends Serializable> implements IGenericBaseCommonDao {
    private static final Logger logger = Logger.getLogger(GenericBaseCommonDao.class);
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;
    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    @Autowired
    @Qualifier("namedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public GenericBaseCommonDao() {
    }

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    private <T> void getProperty(Class entityName) {
        ClassMetadata cm = this.sessionFactory.getClassMetadata(entityName);
        String[] str = cm.getPropertyNames();

        for(int i = 0; i < str.length; ++i) {
            String property = str[i];
            String type = cm.getPropertyType(property).getName();
            LogUtil.info(property + "---&gt;" + type);
        }

    }

    public List<DBTable> getAllDbTableName() {
        List<DBTable> resultList = new ArrayList();
        SessionFactory factory = this.getSession().getSessionFactory();
        Map<String, ClassMetadata> metaMap = factory.getAllClassMetadata();

        DBTable dbTable;
        for(Iterator var5 = metaMap.keySet().iterator(); var5.hasNext(); resultList.add(dbTable)) {
            String key = (String)var5.next();
            dbTable = new DBTable();
            AbstractEntityPersister classMetadata = (AbstractEntityPersister)metaMap.get(key);
            dbTable.setTableName(classMetadata.getTableName());
            dbTable.setEntityName(classMetadata.getEntityName());

            try {
                Class<?> c = Class.forName(key);
                JeecgEntityTitle t = (JeecgEntityTitle)c.getAnnotation(JeecgEntityTitle.class);
                dbTable.setTableTitle(t != null?t.name():"");
            } catch (ClassNotFoundException var10) {
                var10.printStackTrace();
            }
        }

        return resultList;
    }

    public Integer getAllDbTableSize() {
        SessionFactory factory = this.getSession().getSessionFactory();
        Map<String, ClassMetadata> metaMap = factory.getAllClassMetadata();
        return Integer.valueOf(metaMap.size());
    }

    public <T> T findUniqueByProperty(Class<T> entityClass, String propertyName, Object value) {
        Assert.hasText(propertyName);
        return (T) this.createCriteria(entityClass, new Criterion[]{Restrictions.eq(propertyName, value)}).uniqueResult();
    }

    public <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value) {
        Assert.hasText(propertyName);
        return this.createCriteria(entityClass, new Criterion[]{Restrictions.eq(propertyName, value)}).list();
    }

    public <T> Serializable save(T entity) {
        try {
            Serializable id = this.getSession().save(entity);
            if(logger.isDebugEnabled()) {
                logger.debug("保存实体成功," + entity.getClass().getName());
            }

            return id;
        } catch (RuntimeException var3) {
            logger.error("保存实体异常", var3);
            throw var3;
        }
    }

    public <T> void batchSave(List<T> entitys) {
        for(int i = 0; i < entitys.size(); ++i) {
            this.getSession().save(entitys.get(i));
            if(i % 1000 == 0) {
                this.getSession().flush();
                this.getSession().clear();
            }
        }

        this.getSession().flush();
        this.getSession().clear();
    }

    public <T> void saveOrUpdate(T entity) {
        try {
            this.getSession().saveOrUpdate(entity);
            if(logger.isDebugEnabled()) {
                logger.debug("添加或更新成功," + entity.getClass().getName());
            }

        } catch (RuntimeException var3) {
            logger.error("添加或更新异常", var3);
            throw var3;
        }
    }

    public <T> void delete(T entity) {
        try {
            this.getSession().delete(entity);
            if(logger.isDebugEnabled()) {
                logger.debug("删除成功," + entity.getClass().getName());
            }

        } catch (RuntimeException var3) {
            logger.error("删除异常", var3);
            throw var3;
        }
    }

    public <T> void deleteEntityById(Class entityName, Serializable id) {
        this.delete(this.get(entityName, id));
    }

    public <T> void deleteAllEntitie(Collection<T> entitys) {
        Iterator var3 = entitys.iterator();

        while(var3.hasNext()) {
            Object entity = var3.next();
            this.getSession().delete(entity);
        }

    }

    public <T> T get(Class<T> entityClass, Serializable id) {
        return (T) this.getSession().get(entityClass, id);
    }

    public <T> T getEntity(Class entityName, Serializable id) {
        T t = (T) this.getSession().get(entityName, id);
        return t;
    }

    public <T> void updateEntitie(T pojo) {
        this.getSession().update(pojo);
    }

    public <T> void updateEntitie(String className, Object id) {
        this.getSession().update(className, id);
    }

    public <T> void updateEntityById(Class entityName, Serializable id) {
        this.updateEntitie(this.get(entityName, id));
    }

    public List<T> findByQueryString(String query) {
        Query queryObject = this.getSession().createQuery(query);
        List<T> list = queryObject.list();
        return list;
    }

    public <T> T singleResult(String hql) {
        T t = null;
        Query queryObject = this.getSession().createQuery(hql);
        List<T> list = queryObject.list();
        if(list.size() == 1) {
            t = list.get(0);
        } else if(list.size() > 0) {
            throw new BusinessException("查询结果数:" + list.size() + "大于1");
        }

        return t;
    }

    public Map<Object, Object> getHashMapbyQuery(String hql) {
        Query query = this.getSession().createQuery(hql);
        List list = query.list();
        Map<Object, Object> map = new HashMap();
        Iterator iterator = list.iterator();

        while(iterator.hasNext()) {
            Object[] tm = (Object[])iterator.next();
            map.put(tm[0].toString(), tm[1].toString());
        }

        return map;
    }

    public int updateBySqlString(String query) {
        Query querys = this.getSession().createSQLQuery(query);
        return querys.executeUpdate();
    }

    public List<T> findListbySql(String sql) {
        Query querys = this.getSession().createSQLQuery(sql);
        return querys.list();
    }

    private <T> Criteria createCriteria(Class<T> entityClass, boolean isAsc, Criterion... criterions) {
        Criteria criteria = this.createCriteria(entityClass, criterions);
        if(isAsc) {
            criteria.addOrder(Order.asc("asc"));
        } else {
            criteria.addOrder(Order.desc("desc"));
        }

        return criteria;
    }

    private <T> Criteria createCriteria(Class<T> entityClass, Criterion... criterions) {
        Criteria criteria = this.getSession().createCriteria(entityClass);
        Criterion[] var7 = criterions;
        int var6 = criterions.length;

        for(int var5 = 0; var5 < var6; ++var5) {
            Criterion c = var7[var5];
            criteria.add(c);
        }

        return criteria;
    }

    public <T> List<T> loadAll(Class<T> entityClass) {
        Criteria criteria = this.createCriteria(entityClass);
        return criteria.list();
    }

    private <T> Criteria createCriteria(Class<T> entityClass) {
        Criteria criteria = this.getSession().createCriteria(entityClass);
        return criteria;
    }

    public <T> List<T> findByPropertyisOrder(Class<T> entityClass, String propertyName, Object value, boolean isAsc) {
        Assert.hasText(propertyName);
        return this.createCriteria(entityClass, isAsc, new Criterion[]{Restrictions.eq(propertyName, value)}).list();
    }

    public <T> T findUniqueBy(Class<T> entityClass, String propertyName, Object value) {
        Assert.hasText(propertyName);
        return (T) this.createCriteria(entityClass, new Criterion[]{Restrictions.eq(propertyName, value)}).uniqueResult();
    }

    public Query createQuery(Session session, String hql, Object... objects) {
        Query query = session.createQuery(hql);
        if(objects != null) {
            for(int i = 0; i < objects.length; ++i) {
                query.setParameter(i, objects[i]);
            }
        }

        return query;
    }

    public <T> int batchInsertsEntitie(List<T> entityList) {
        int num = 0;

        for(int i = 0; i < entityList.size(); ++i) {
            this.save(entityList.get(i));
            ++num;
        }

        return num;
    }

    public List<T> executeQuery(String hql, Object[] values) {
        Query query = this.getSession().createQuery(hql);

        for(int i = 0; values != null && i < values.length; ++i) {
            query.setParameter(i, values[i]);
        }

        return query.list();
    }

    public List findByExample(String entityName, Object exampleEntity) {
        Assert.notNull(exampleEntity, "Example entity must not be null");
        Criteria executableCriteria = entityName != null?this.getSession().createCriteria(entityName):this.getSession().createCriteria(exampleEntity.getClass());
        executableCriteria.add(Example.create(exampleEntity));
        return executableCriteria.list();
    }

    public Integer getRowCount(DetachedCriteria criteria) {
        return Integer.valueOf(oConvertUtils.getInt(((Criteria)criteria.setProjection(Projections.rowCount())).uniqueResult(), 0));
    }

    public void callableStatementByName(String proc) {
    }

    public int getCount(Class<T> clazz) {
        int count = DataAccessUtils.intResult(this.getSession().createQuery("select count(*) from " + clazz.getName()).list());
        return count;
    }

    public PageList getPageList(CriteriaQuery cq, boolean isOffset) {
        Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(this.getSession());
        CriteriaImpl impl = (CriteriaImpl)criteria;
        Projection projection = impl.getProjection();
        int allCounts = ((Long)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
        criteria.setProjection(projection);
        if(projection == null) {
            criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }

        if(CriteriaQuery.getOrdermap() != null) {
            cq.setOrder(CriteriaQuery.getOrdermap());
        }

        int pageSize = cq.getPageSize();
        int curPageNO = PagerUtil.getcurPageNo(allCounts, cq.getCurPage().intValue(), pageSize);
        int offset = PagerUtil.getOffset(allCounts, curPageNO, pageSize);
        String toolBar = "";
        if(isOffset) {
            criteria.setFirstResult(offset);
            criteria.setMaxResults(cq.getPageSize());
            if(cq.getIsUseimage() == 1) {
                toolBar = PagerUtil.getBar(cq.getMyAction(), cq.getMyForm(), allCounts, curPageNO, pageSize, cq.getMap());
            } else {
                toolBar = PagerUtil.getBar(cq.getMyAction(), allCounts, curPageNO, pageSize, cq.getMap());
            }
        }

        return new PageList(criteria.list(), toolBar, offset, curPageNO, allCounts);
    }

    public DataTableReturn getDataTableReturn(CriteriaQuery cq, boolean isOffset) {
        Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(this.getSession());
        CriteriaImpl impl = (CriteriaImpl)criteria;
        Projection projection = impl.getProjection();
        int allCounts = ((Long)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
        criteria.setProjection(projection);
        if(projection == null) {
            criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }

        if(CriteriaQuery.getOrdermap() != null) {
            cq.setOrder(CriteriaQuery.getOrdermap());
        }

        int pageSize = cq.getPageSize();
        int curPageNO = PagerUtil.getcurPageNo(allCounts, cq.getCurPage().intValue(), pageSize);
        int offset = PagerUtil.getOffset(allCounts, curPageNO, pageSize);
        if(isOffset) {
            criteria.setFirstResult(offset);
            criteria.setMaxResults(cq.getPageSize());
        }

        DetachedCriteriaUtil.selectColumn(cq.getDetachedCriteria(), cq.getField().split(","), cq.getEntityClass(), false);
        return new DataTableReturn(Integer.valueOf(allCounts), Integer.valueOf(allCounts), cq.getDataTables().getEcho(), criteria.list());
    }

    public DataGridReturn getDataGridReturn(CriteriaQuery cq, boolean isOffset) {
        Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(this.getSession());
        CriteriaImpl impl = (CriteriaImpl)criteria;
        Projection projection = impl.getProjection();
        int allCounts = ((Long)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
        criteria.setProjection(projection);
        if(projection == null) {
            criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }

        if(StringUtils.isNotBlank(cq.getDataGrid().getSort())) {
            cq.addOrder(cq.getDataGrid().getSort(), cq.getDataGrid().getOrder());
        }

        if(!CriteriaQuery.getOrdermap().isEmpty()) {
            cq.setOrder(CriteriaQuery.getOrdermap());
        }

        int pageSize = cq.getPageSize();
        int curPageNO = PagerUtil.getcurPageNo(allCounts, cq.getCurPage().intValue(), pageSize);
        int offset = PagerUtil.getOffset(allCounts, curPageNO, pageSize);
        if(isOffset) {
            criteria.setFirstResult(offset);
            criteria.setMaxResults(cq.getPageSize());
        }

        List list = criteria.list();
        cq.getDataGrid().setResults(list);
        cq.getDataGrid().setTotal(allCounts);
        return new DataGridReturn(Integer.valueOf(allCounts), list);
    }

    public PageList getPageListBySql(HqlQuery hqlQuery, boolean isToEntity) {
        Query query = this.getSession().createSQLQuery(hqlQuery.getQueryString());
        int allCounts = query.list().size();
        int curPageNO = hqlQuery.getCurPage();
        int offset = PagerUtil.getOffset(allCounts, curPageNO, hqlQuery.getPageSize());
        query.setFirstResult(offset);
        query.setMaxResults(hqlQuery.getPageSize());
        List list = null;
        if(isToEntity) {
            list = (List)ToEntityUtil.toEntityList(query.list(), hqlQuery.getClass1(), hqlQuery.getDataGrid().getField().split(","));
        } else {
            list = query.list();
        }

        return new PageList(hqlQuery, list, offset, curPageNO, allCounts);
    }

    public PageList getPageList(HqlQuery hqlQuery, boolean needParameter) {
        Query query = this.getSession().createQuery(hqlQuery.getQueryString());
        if(needParameter) {
            query.setParameters(hqlQuery.getParam(), hqlQuery.getTypes());
        }

        int allCounts = query.list().size();
        int curPageNO = hqlQuery.getCurPage();
        int offset = PagerUtil.getOffset(allCounts, curPageNO, hqlQuery.getPageSize());
        String toolBar = PagerUtil.getBar(hqlQuery.getMyaction(), allCounts, curPageNO, hqlQuery.getPageSize(), hqlQuery.getMap());
        query.setFirstResult(offset);
        query.setMaxResults(hqlQuery.getPageSize());
        return new PageList(query.list(), toolBar, offset, curPageNO, allCounts);
    }

    public List<T> getListByCriteriaQuery(CriteriaQuery cq, Boolean ispage) {
        Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(this.getSession());
        if(CriteriaQuery.getOrdermap() != null) {
            cq.setOrder(CriteriaQuery.getOrdermap());
        }

        if(ispage.booleanValue()) {
            criteria.setFirstResult((cq.getCurPage().intValue() - 1) * cq.getPageSize());
            criteria.setMaxResults(cq.getPageSize());
        }

        return criteria.list();
    }

    public List<Map<String, Object>> findForJdbc(String sql, int page, int rows) {
        sql = JdbcDao.jeecgCreatePageSql(sql, page, rows);
        return this.jdbcTemplate.queryForList(sql);
    }

    public <T> List<T> findObjForJdbc(String sql, int page, int rows, Class<T> clazz) {
        List<T> rsList = new ArrayList();
        sql = JdbcDao.jeecgCreatePageSql(sql, page, rows);
        List<Map<String, Object>> mapList = this.jdbcTemplate.queryForList(sql);
        T po = null;
        Iterator var9 = mapList.iterator();

        while(var9.hasNext()) {
            Map m = (Map)var9.next();

            try {
                po = clazz.newInstance();
                MyBeanUtils.copyMap2Bean_Nobig(po, m);
                rsList.add(po);
            } catch (Exception var11) {
                var11.printStackTrace();
            }
        }

        return rsList;
    }

    public List<Map<String, Object>> findForJdbcParam(String sql, int page, int rows, Object... objs) {
        sql = JdbcDao.jeecgCreatePageSql(sql, page, rows);
        return this.jdbcTemplate.queryForList(sql, objs);
    }

    public Long getCountForJdbc(String sql) {
        return (Long)this.jdbcTemplate.queryForObject(sql, Long.class);
    }

    public Long getCountForJdbcParam(String sql, Object[] objs) {
        return (Long)this.jdbcTemplate.queryForObject(sql, objs, Long.class);
    }

    public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
        return this.jdbcTemplate.queryForList(sql, objs);
    }

    public Integer executeSql(String sql, List<Object> param) {
        return Integer.valueOf(this.jdbcTemplate.update(sql, new Object[]{param}));
    }

    public Integer executeSql(String sql, Object... param) {
        return Integer.valueOf(this.jdbcTemplate.update(sql, param));
    }

    public Integer executeSql(String sql, Map<String, Object> param) {
        return Integer.valueOf(this.namedParameterJdbcTemplate.update(sql, param));
    }

    public Object executeSqlReturnKey(String sql, Map<String, Object> param) {
        Object keyValue = null;
        KeyHolder keyHolder = null;
        SqlParameterSource sqlp = new MapSqlParameterSource(param);
        if(StringUtil.isNotEmpty(param.get("id"))) {
            this.namedParameterJdbcTemplate.update(sql, sqlp);
        } else {
            keyHolder = new GeneratedKeyHolder();
            this.namedParameterJdbcTemplate.update(sql, sqlp, keyHolder, new String[]{"id"});
            Number number = keyHolder.getKey();
            if(oConvertUtils.isNotEmpty(number)) {
                keyValue = Long.valueOf(keyHolder.getKey().longValue());
            }
        }

        return keyValue;
    }

    public Integer countByJdbc(String sql, Object... param) {
        return (Integer)this.jdbcTemplate.queryForObject(sql, param, Integer.class);
    }

    public Map<String, Object> findOneForJdbc(String sql, Object... objs) {
        try {
            return this.jdbcTemplate.queryForMap(sql, objs);
        } catch (EmptyResultDataAccessException var4) {
            return null;
        }
    }

    public <T> List<T> findHql(String hql, Object... param) {
        Query q = this.getSession().createQuery(hql);
        if(param != null && param.length > 0) {
            for(int i = 0; i < param.length; ++i) {
                q.setParameter(i, param[i]);
            }
        }

        return q.list();
    }

    public Integer executeHql(String hql) {
        Query q = this.getSession().createQuery(hql);
        return Integer.valueOf(q.executeUpdate());
    }

    public <T> List<T> pageList(DetachedCriteria dc, int firstResult, int maxResult) {
        Criteria criteria = dc.getExecutableCriteria(this.getSession());
        criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResult);
        return criteria.list();
    }

    public <T> List<T> findByDetached(DetachedCriteria dc) {
        return dc.getExecutableCriteria(this.getSession()).list();
    }

    public <T> List<T> executeProcedure(String executeSql, Object... params) {
        SQLQuery sqlQuery = this.getSession().createSQLQuery(executeSql);

        for(int i = 0; i < params.length; ++i) {
            sqlQuery.setParameter(i, params[i]);
        }

        return sqlQuery.list();
    }
}
