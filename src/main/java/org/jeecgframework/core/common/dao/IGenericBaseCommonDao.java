package org.jeecgframework.core.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.common.DBTable;
import org.jeecgframework.core.common.model.json.DataGridReturn;
import org.jeecgframework.tag.vo.datatable.DataTableReturn;

public abstract interface IGenericBaseCommonDao
{
  public abstract List<DBTable> getAllDbTableName();

  public abstract Integer getAllDbTableSize();

  public abstract <T> Serializable save(T paramT);

  public abstract <T> void batchSave(List<T> paramList);

  public abstract <T> void saveOrUpdate(T paramT);

  public abstract <T> void delete(T paramT);

  public abstract <T> T get(Class<T> paramClass, Serializable paramSerializable);

  public abstract <T> T findUniqueByProperty(Class<T> paramClass, String paramString, Object paramObject);

  public abstract <T> List<T> findByProperty(Class<T> paramClass, String paramString, Object paramObject);

  public abstract <T> List<T> loadAll(Class<T> paramClass);

  public abstract <T> T getEntity(Class paramClass, Serializable paramSerializable);

  public abstract <T> void deleteEntityById(Class paramClass, Serializable paramSerializable);

  public abstract <T> void deleteAllEntitie(Collection<T> paramCollection);

  public abstract <T> void updateEntitie(T paramT);

  public abstract <T> void updateEntityById(Class paramClass, Serializable paramSerializable);

  public abstract <T> List<T> findByQueryString(String paramString);

  public abstract <T> T singleResult(String paramString);

  public abstract int updateBySqlString(String paramString);

  public abstract <T> List<T> findListbySql(String paramString);

  public abstract <T> List<T> findByPropertyisOrder(Class<T> paramClass, String paramString, Object paramObject, boolean paramBoolean);

  public abstract PageList getPageList(CriteriaQuery paramCriteriaQuery, boolean paramBoolean);

  public abstract <T> List<T> getListByCriteriaQuery(CriteriaQuery paramCriteriaQuery, Boolean paramBoolean);

  public abstract PageList getPageList(HqlQuery paramHqlQuery, boolean paramBoolean);

  public abstract PageList getPageListBySql(HqlQuery paramHqlQuery, boolean paramBoolean);

  public abstract Session getSession();

  public abstract List findByExample(String paramString, Object paramObject);

  public abstract Map<Object, Object> getHashMapbyQuery(String paramString);

  public abstract DataTableReturn getDataTableReturn(CriteriaQuery paramCriteriaQuery, boolean paramBoolean);

  public abstract DataGridReturn getDataGridReturn(CriteriaQuery paramCriteriaQuery, boolean paramBoolean);

  public abstract Integer executeSql(String paramString, List<Object> paramList);

  public abstract Integer executeSql(String paramString, Object[] paramArrayOfObject);

  public abstract Integer executeSql(String paramString, Map<String, Object> paramMap);

  public abstract Object executeSqlReturnKey(String paramString, Map<String, Object> paramMap);

  public abstract List<Map<String, Object>> findForJdbc(String paramString, Object[] paramArrayOfObject);

  public abstract Map<String, Object> findOneForJdbc(String paramString, Object[] paramArrayOfObject);

  public abstract List<Map<String, Object>> findForJdbc(String paramString, int paramInt1, int paramInt2);

  public abstract <T> List<T> findObjForJdbc(String paramString, int paramInt1, int paramInt2, Class<T> paramClass);

  public abstract List<Map<String, Object>> findForJdbcParam(String paramString, int paramInt1, int paramInt2, Object[] paramArrayOfObject);

  public abstract Long getCountForJdbc(String paramString);

  public abstract Long getCountForJdbcParam(String paramString, Object[] paramArrayOfObject);

  public abstract <T> List<T> findHql(String paramString, Object[] paramArrayOfObject);

  public abstract Integer executeHql(String paramString);

  public abstract <T> List<T> pageList(DetachedCriteria paramDetachedCriteria, int paramInt1, int paramInt2);

  public abstract <T> List<T> findByDetached(DetachedCriteria paramDetachedCriteria);

  public abstract <T> List<T> executeProcedure(String paramString, Object[] paramArrayOfObject);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.common.dao.IGenericBaseCommonDao
 * JD-Core Version:    0.6.2
 */