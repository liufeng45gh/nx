/*     */ package org.jeecgframework.core.common.service.impl;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.jeecgframework.core.common.dao.ICommonDao;
/*     */ import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
/*     */ import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
/*     */ import org.jeecgframework.core.common.hibernate.qbc.PageList;
/*     */ import org.jeecgframework.core.common.model.common.DBTable;
/*     */ import org.jeecgframework.core.common.model.common.UploadFile;
/*     */ import org.jeecgframework.core.common.model.json.ComboTree;
/*     */ import org.jeecgframework.core.common.model.json.DataGridReturn;
/*     */ import org.jeecgframework.core.common.model.json.ImportFile;
/*     */ import org.jeecgframework.core.common.model.json.TreeGrid;
/*     */ import org.jeecgframework.core.common.service.CommonService;
/*     */ import org.jeecgframework.tag.vo.datatable.DataTableReturn;
/*     */ import org.jeecgframework.tag.vo.easyui.Autocomplete;
/*     */ import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
/*     */ import org.jeecgframework.tag.vo.easyui.TreeGridModel;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service("commonService")
/*     */ @Transactional
/*     */ public class CommonServiceImpl
/*     */   implements CommonService
/*     */ {
/*  34 */   public ICommonDao commonDao = null;
/*     */ 
/*     */   public List<DBTable> getAllDbTableName()
/*     */   {
/*  42 */     return this.commonDao.getAllDbTableName();
/*     */   }
/*     */ 
/*     */   public Integer getAllDbTableSize() {
/*  46 */     return this.commonDao.getAllDbTableSize();
/*     */   }
/*     */ 
/*     */   @Resource
/*     */   public void setCommonDao(ICommonDao commonDao) {
/*  51 */     this.commonDao = commonDao;
/*     */   }
/*     */ 
/*     */   public <T> Serializable save(T entity)
/*     */   {
/*  56 */     return this.commonDao.save(entity);
/*     */   }
/*     */ 
/*     */   public <T> void saveOrUpdate(T entity)
/*     */   {
/*  61 */     this.commonDao.saveOrUpdate(entity);
/*     */   }
/*     */ 
/*     */   public <T> void delete(T entity)
/*     */   {
/*  67 */     this.commonDao.delete(entity);
/*     */   }
/*     */ 
/*     */   public <T> void deleteAllEntitie(Collection<T> entities)
/*     */   {
/*  78 */     this.commonDao.deleteAllEntitie(entities);
/*     */   }
/*     */ 
/*     */   public <T> T get(Class<T> class1, Serializable id)
/*     */   {
/*  85 */     return this.commonDao.get(class1, id);
/*     */   }
/*     */ 
/*     */   public <T> List<T> getList(Class clas)
/*     */   {
/*  97 */     return this.commonDao.loadAll(clas);
/*     */   }
/*     */ 
/*     */   public <T> T getEntity(Class entityName, Serializable id)
/*     */   {
/* 104 */     return this.commonDao.getEntity(entityName, id);
/*     */   }
/*     */ 
/*     */   public <T> T findUniqueByProperty(Class<T> entityClass, String propertyName, Object value)
/*     */   {
/* 118 */     return this.commonDao.findUniqueByProperty(entityClass, propertyName, value);
/*     */   }
/*     */ 
/*     */   public <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value)
/*     */   {
/* 127 */     return this.commonDao.findByProperty(entityClass, propertyName, value);
/*     */   }
/*     */ 
/*     */   public <T> List<T> loadAll(Class<T> entityClass)
/*     */   {
/* 138 */     return this.commonDao.loadAll(entityClass);
/*     */   }
/*     */ 
/*     */   public <T> T singleResult(String hql) {
/* 142 */     return this.commonDao.singleResult(hql);
/*     */   }
/*     */ 
/*     */   public <T> void deleteEntityById(Class entityName, Serializable id)
/*     */   {
/* 152 */     this.commonDao.deleteEntityById(entityName, id);
/*     */   }
/*     */ 
/*     */   public <T> void updateEntitie(T pojo)
/*     */   {
/* 162 */     this.commonDao.updateEntitie(pojo);
/*     */   }
/*     */ 
/*     */   public <T> List<T> findByQueryString(String hql)
/*     */   {
/* 174 */     return this.commonDao.findByQueryString(hql);
/*     */   }
/*     */ 
/*     */   public int updateBySqlString(String sql)
/*     */   {
/* 184 */     return this.commonDao.updateBySqlString(sql);
/*     */   }
/*     */ 
/*     */   public <T> List<T> findListbySql(String query)
/*     */   {
/* 195 */     return this.commonDao.findListbySql(query);
/*     */   }
/*     */ 
/*     */   public <T> List<T> findByPropertyisOrder(Class<T> entityClass, String propertyName, Object value, boolean isAsc)
/*     */   {
/* 207 */     return this.commonDao.findByPropertyisOrder(entityClass, propertyName, 
/* 208 */       value, isAsc);
/*     */   }
/*     */ 
/*     */   public PageList getPageList(CriteriaQuery cq, boolean isOffset)
/*     */   {
/* 220 */     return this.commonDao.getPageList(cq, isOffset);
/*     */   }
/*     */ 
/*     */   public DataTableReturn getDataTableReturn(CriteriaQuery cq, boolean isOffset)
/*     */   {
/* 232 */     return this.commonDao.getDataTableReturn(cq, isOffset);
/*     */   }
/*     */ 
/*     */   public DataGridReturn getDataGridReturn(CriteriaQuery cq, boolean isOffset)
/*     */   {
/* 244 */     return this.commonDao.getDataGridReturn(cq, isOffset);
/*     */   }
/*     */ 
/*     */   public PageList getPageList(HqlQuery hqlQuery, boolean needParameter)
/*     */   {
/* 257 */     return this.commonDao.getPageList(hqlQuery, needParameter);
/*     */   }
/*     */ 
/*     */   public PageList getPageListBySql(HqlQuery hqlQuery, boolean isToEntity)
/*     */   {
/* 270 */     return this.commonDao.getPageListBySql(hqlQuery, isToEntity);
/*     */   }
/*     */ 
/*     */   public Session getSession()
/*     */   {
/* 276 */     return this.commonDao.getSession();
/*     */   }
/*     */ 
/*     */   public List findByExample(String entityName, Object exampleEntity)
/*     */   {
/* 281 */     return this.commonDao.findByExample(entityName, exampleEntity);
/*     */   }
/*     */ 
/*     */   public <T> List<T> getListByCriteriaQuery(CriteriaQuery cq, Boolean ispage)
/*     */   {
/* 293 */     return this.commonDao.getListByCriteriaQuery(cq, ispage);
/*     */   }
/*     */ 
/*     */   public <T> T uploadFile(UploadFile uploadFile)
/*     */   {
/* 302 */     return this.commonDao.uploadFile(uploadFile);
/*     */   }
/*     */ 
/*     */   public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile)
/*     */   {
/* 308 */     return this.commonDao.viewOrDownloadFile(uploadFile);
/*     */   }
/*     */ 
/*     */   public HttpServletResponse createXml(ImportFile importFile)
/*     */   {
/* 319 */     return this.commonDao.createXml(importFile);
/*     */   }
/*     */ 
/*     */   public void parserXml(String fileName)
/*     */   {
/* 329 */     this.commonDao.parserXml(fileName);
/*     */   }
/*     */ 
/*     */   public List<ComboTree> ComboTree(List all, ComboTreeModel comboTreeModel, List in, boolean recursive) {
/* 333 */     return this.commonDao.ComboTree(all, comboTreeModel, in, recursive);
/*     */   }
/*     */ 
/*     */   public List<TreeGrid> treegrid(List all, TreeGridModel treeGridModel)
/*     */   {
/* 340 */     return this.commonDao.treegrid(all, treeGridModel);
/*     */   }
/*     */ 
/*     */   public <T> List<T> getAutoList(Autocomplete autocomplete)
/*     */   {
/* 350 */     StringBuffer sb = new StringBuffer("");
/* 351 */     for (String searchField : autocomplete.getSearchField().split(",")) {
/* 352 */       sb.append("  or " + searchField + " like '%" + 
/* 353 */         autocomplete.getTrem() + "%' ");
/*     */     }
/* 355 */     String hql = "from " + autocomplete.getEntityName() + " where 1!=1 " + 
/* 356 */       sb.toString();
/* 357 */     return this.commonDao.getSession().createQuery(hql)
/* 358 */       .setFirstResult(autocomplete.getCurPage().intValue() - 1)
/* 359 */       .setMaxResults(autocomplete.getMaxRows().intValue()).list();
/*     */   }
/*     */ 
/*     */   public Integer executeSql(String sql, List<Object> param)
/*     */   {
/* 364 */     return this.commonDao.executeSql(sql, param);
/*     */   }
/*     */ 
/*     */   public Integer executeSql(String sql, Object[] param)
/*     */   {
/* 369 */     return this.commonDao.executeSql(sql, param);
/*     */   }
/*     */ 
/*     */   public Integer executeSql(String sql, Map<String, Object> param)
/*     */   {
/* 374 */     return this.commonDao.executeSql(sql, param);
/*     */   }
/*     */ 
/*     */   public Object executeSqlReturnKey(String sql, Map<String, Object> param) {
/* 378 */     return this.commonDao.executeSqlReturnKey(sql, param);
/*     */   }
/*     */ 
/*     */   public List<Map<String, Object>> findForJdbc(String sql, int page, int rows) {
/* 382 */     return this.commonDao.findForJdbc(sql, page, rows);
/*     */   }
/*     */ 
/*     */   public List<Map<String, Object>> findForJdbc(String sql, Object[] objs)
/*     */   {
/* 387 */     return this.commonDao.findForJdbc(sql, objs);
/*     */   }
/*     */ 
/*     */   public List<Map<String, Object>> findForJdbcParam(String sql, int page, int rows, Object[] objs)
/*     */   {
/* 393 */     return this.commonDao.findForJdbcParam(sql, page, rows, objs);
/*     */   }
/*     */ 
/*     */   public <T> List<T> findObjForJdbc(String sql, int page, int rows, Class<T> clazz)
/*     */   {
/* 399 */     return this.commonDao.findObjForJdbc(sql, page, rows, clazz);
/*     */   }
/*     */ 
/*     */   public Map<String, Object> findOneForJdbc(String sql, Object[] objs)
/*     */   {
/* 404 */     return this.commonDao.findOneForJdbc(sql, objs);
/*     */   }
/*     */ 
/*     */   public Long getCountForJdbc(String sql)
/*     */   {
/* 409 */     return this.commonDao.getCountForJdbc(sql);
/*     */   }
/*     */ 
/*     */   public Long getCountForJdbcParam(String sql, Object[] objs) {
/* 413 */     return this.commonDao.getCountForJdbcParam(sql, objs);
/*     */   }
/*     */ 
/*     */   public <T> void batchSave(List<T> entitys)
/*     */   {
/* 419 */     this.commonDao.batchSave(entitys);
/*     */   }
/*     */ 
/*     */   public <T> List<T> findHql(String hql, Object[] param)
/*     */   {
/* 430 */     return this.commonDao.findHql(hql, param);
/*     */   }
/*     */ 
/*     */   public <T> List<T> pageList(DetachedCriteria dc, int firstResult, int maxResult)
/*     */   {
/* 435 */     return this.commonDao.pageList(dc, firstResult, maxResult);
/*     */   }
/*     */ 
/*     */   public <T> List<T> findByDetached(DetachedCriteria dc) {
/* 439 */     return this.commonDao.findByDetached(dc);
/*     */   }
/*     */ 
/*     */   public <T> List<T> executeProcedure(String procedureSql, Object[] params)
/*     */   {
/* 446 */     return this.commonDao.executeProcedure(procedureSql, params);
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.common.service.impl.CommonServiceImpl
 * JD-Core Version:    0.6.2
 */