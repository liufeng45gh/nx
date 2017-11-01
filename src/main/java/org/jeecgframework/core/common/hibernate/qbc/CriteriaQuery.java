/*     */ package org.jeecgframework.core.common.hibernate.qbc;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import org.hibernate.criterion.Criterion;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Property;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.hibernate.transform.Transformers;
/*     */ import org.hibernate.type.Type;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.tag.vo.datatable.DataTables;
/*     */ import org.jeecgframework.tag.vo.datatable.SortDirection;
/*     */ import org.jeecgframework.tag.vo.datatable.SortInfo;
/*     */ 
/*     */ public class CriteriaQuery
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  36 */   private int curPage = 1;
/*  37 */   private int pageSize = 10;
/*     */   private String myAction;
/*     */   private String myForm;
/*  40 */   private CriterionList criterionList = new CriterionList();
/*  41 */   private CriterionList jqcriterionList = new CriterionList();
/*  42 */   private int isUseimage = 0;
/*     */   private DetachedCriteria detachedCriteria;
/*     */   private static Map<String, Object> map;
/*     */   private static Map<String, Object> ordermap;
/*  46 */   private boolean flag = true;
/*  47 */   private String field = "";
/*     */   private Class entityClass;
/*     */   private List results;
/*     */   private int total;
/*  51 */   private List<String> alias = new ArrayList();
/*     */   private DataGrid dataGrid;
/*     */   private DataTables dataTables;
/*     */ 
/*     */   public CriteriaQuery()
/*     */   {
/*     */   }
/*     */ 
/*     */   public List getResults()
/*     */   {
/*  53 */     return this.results;
/*     */   }
/*     */ 
/*     */   public void setResults(List results) {
/*  57 */     this.results = results;
/*     */   }
/*     */ 
/*     */   public int getTotal() {
/*  61 */     return this.total;
/*     */   }
/*     */ 
/*     */   public void setTotal(int total) {
/*  65 */     this.total = total;
/*     */   }
/*     */ 
/*     */   public DataTables getDataTables()
/*     */   {
/*  72 */     return this.dataTables;
/*     */   }
/*     */ 
/*     */   public void setDataTables(DataTables dataTables) {
/*  76 */     this.dataTables = dataTables;
/*     */   }
/*     */ 
/*     */   public DataGrid getDataGrid() {
/*  80 */     return this.dataGrid;
/*     */   }
/*     */ 
/*     */   public void setDataGrid(DataGrid dataGrid) {
/*  84 */     this.dataGrid = dataGrid;
/*     */   }
/*     */ 
/*     */   public Class getEntityClass() {
/*  88 */     return this.entityClass;
/*     */   }
/*     */ 
/*     */   public void setEntityClass(Class entityClass) {
/*  92 */     this.entityClass = entityClass;
/*     */   }
/*     */   public CriterionList getJqcriterionList() {
/*  95 */     return this.jqcriterionList;
/*     */   }
/*     */ 
/*     */   public void setJqcriterionList(CriterionList jqcriterionList) {
/*  99 */     this.jqcriterionList = jqcriterionList;
/*     */   }
/*     */ 
/*     */   public CriteriaQuery(Class c) {
/* 103 */     this.detachedCriteria = DetachedCriteria.forClass(c);
/* 104 */     map = new HashMap();
/* 105 */     ordermap = new HashMap();
/*     */   }
/*     */ 
/*     */   public CriteriaQuery(Class c, int curPage, String myAction, String myForm) {
/* 109 */     this.curPage = curPage;
/* 110 */     this.myAction = myAction;
/* 111 */     this.myForm = myForm;
/* 112 */     this.detachedCriteria = DetachedCriteria.forClass(c);
/*     */   }
/*     */ 
/*     */   public CriteriaQuery(Class c, int curPage, String myAction) {
/* 116 */     this.myAction = myAction;
/* 117 */     this.curPage = curPage;
/* 118 */     this.detachedCriteria = DetachedCriteria.forClass(c);
/* 119 */     map = new HashMap();
/* 120 */     ordermap = new HashMap();
/*     */   }
/*     */ 
/*     */   public CriteriaQuery(Class entityClass, int curPage) {
/* 124 */     this.curPage = curPage;
/* 125 */     this.detachedCriteria = DetachedCriteria.forClass(entityClass);
/* 126 */     map = new HashMap();
/*     */   }
/*     */   public CriteriaQuery(Class entityClass, DataGrid dg) {
/* 129 */     this.curPage = dg.getPage();
/*     */ 
/* 133 */     this.detachedCriteria = DetachedCriteria.forClass(entityClass);
/*     */ 
/* 136 */     this.field = dg.getField();
/* 137 */     this.entityClass = entityClass;
/* 138 */     this.dataGrid = dg;
/* 139 */     this.pageSize = dg.getRows();
/* 140 */     map = new HashMap();
/* 141 */     ordermap = new HashMap();
/*     */   }
/*     */   public CriteriaQuery(Class entityClass, DataTables dataTables) {
/* 144 */     this.curPage = dataTables.getDisplayStart();
/* 145 */     String[] fieldstring = dataTables.getsColumns().split(",");
/* 146 */     this.detachedCriteria = 
/* 147 */       DetachedCriteriaUtil.createDetachedCriteria(entityClass, "start", "_table", fieldstring);
/*     */ 
/* 149 */     this.field = dataTables.getsColumns();
/* 150 */     this.entityClass = entityClass;
/* 151 */     this.dataTables = dataTables;
/* 152 */     this.pageSize = dataTables.getDisplayLength();
/* 153 */     map = new HashMap();
/* 154 */     ordermap = new HashMap();
/* 155 */     addJqCriteria(dataTables);
/*     */   }
/*     */ 
/*     */   public CriteriaQuery(Class c, int pageSize, int curPage, String myAction, String myForm)
/*     */   {
/* 160 */     this.pageSize = pageSize;
/* 161 */     this.curPage = curPage;
/* 162 */     this.myAction = myAction;
/* 163 */     this.myForm = myForm;
/* 164 */     this.detachedCriteria = DetachedCriteria.forClass(c);
/*     */   }
/*     */ 
/*     */   public void add(Criterion c)
/*     */   {
/* 177 */     this.detachedCriteria.add(c);
/*     */   }
/*     */ 
/*     */   public void add()
/*     */   {
/* 184 */     for (int i = 0; i < getCriterionList().size(); i++) {
/* 185 */       add(getCriterionList().getParas(i));
/*     */     }
/* 187 */     getCriterionList().removeAll(getCriterionList());
/*     */   }
/*     */ 
/*     */   public void addJqCriteria(DataTables dataTables)
/*     */   {
/* 194 */     String search = dataTables.getSearch();
/* 195 */     SortInfo[] sortInfo = dataTables.getSortColumns();
/* 196 */     String[] sColumns = dataTables.getsColumns().split(",");
/* 197 */     if (StringUtil.isNotEmpty(search))
/*     */     {
/* 199 */       for (String string : sColumns) {
/* 200 */         if (string.indexOf("_") == -1)
/*     */         {
/* 202 */           this.jqcriterionList.addPara(Restrictions.like(string, "%" + search + 
/* 203 */             "%"));
/*     */         }
/*     */       }
/* 206 */       add(getOrCriterion(this.jqcriterionList));
/*     */     }
/*     */ 
/* 209 */     if (sortInfo.length > 0)
/*     */     {
/* 211 */       for (SortInfo sortInfo2 : sortInfo)
/* 212 */         addOrder(sColumns[sortInfo2.getColumnId().intValue()], sortInfo2.getSortOrder());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void createCriteria(String name)
/*     */   {
/* 218 */     this.detachedCriteria.createCriteria(name);
/*     */   }
/*     */ 
/*     */   public void createCriteria(String name, String value) {
/* 222 */     this.detachedCriteria.createCriteria(name, value);
/*     */   }
/*     */ 
/*     */   public void createAlias(String name, String value)
/*     */   {
/* 232 */     if (!this.alias.contains(name)) {
/* 233 */       this.detachedCriteria.createAlias(name, value);
/* 234 */       this.alias.add(name);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setResultTransformer(Class class1) {
/* 239 */     this.detachedCriteria.setResultTransformer(Transformers.aliasToBean(class1));
/*     */   }
/*     */ 
/*     */   public void setProjection(Property property) {
/* 243 */     this.detachedCriteria.setProjection(property);
/*     */   }
/*     */ 
/*     */   public Criterion and(CriteriaQuery query, int source, int dest)
/*     */   {
/* 257 */     return Restrictions.and(query.getCriterionList().getParas(source), 
/* 258 */       query.getCriterionList().getParas(dest));
/*     */   }
/*     */ 
/*     */   public Criterion and(Criterion c, CriteriaQuery query, int souce)
/*     */   {
/* 272 */     return Restrictions.and(c, query.getCriterionList().getParas(souce));
/*     */   }
/*     */ 
/*     */   public Criterion getOrCriterion(CriterionList list)
/*     */   {
/* 279 */     Criterion c1 = null;
/* 280 */     Criterion c2 = null;
/* 281 */     Criterion c3 = null;
/* 282 */     c1 = list.getParas(0);
/* 283 */     for (int i = 1; i < list.size(); i++) {
/* 284 */       c2 = list.getParas(i);
/* 285 */       c3 = getor(c1, c2);
/* 286 */       c1 = c3;
/*     */     }
/* 288 */     return c3;
/*     */   }
/*     */ 
/*     */   public Criterion getor(Criterion c1, Criterion c2)
/*     */   {
/* 299 */     return Restrictions.or(c1, c2);
/*     */   }
/*     */ 
/*     */   public Criterion and(Criterion c1, Criterion c2)
/*     */   {
/* 314 */     return Restrictions.and(c1, c2);
/*     */   }
/*     */ 
/*     */   public Criterion or(CriteriaQuery query, int source, int dest)
/*     */   {
/* 326 */     return Restrictions.or(query.getCriterionList().getParas(source), query
/* 327 */       .getCriterionList().getParas(dest));
/*     */   }
/*     */ 
/*     */   public Criterion or(Criterion c, CriteriaQuery query, int source)
/*     */   {
/* 338 */     return Restrictions.or(c, query.getCriterionList().getParas(source));
/*     */   }
/*     */ 
/*     */   public void or(Criterion c1, Criterion c2)
/*     */   {
/* 354 */     this.detachedCriteria.add(Restrictions.or(c1, c2));
/*     */   }
/*     */ 
/*     */   public void addOrder(String ordername, SortDirection ordervalue)
/*     */   {
/* 366 */     ordermap.put(ordername, ordervalue);
/*     */   }
/*     */ 
/*     */   public void setOrder(Map<String, Object> map)
/*     */   {
/* 378 */     for (Map.Entry entry : map.entrySet()) {
/* 379 */       judgecreateAlias((String)entry.getKey());
/* 380 */       if (SortDirection.asc.equals(entry.getValue()))
/* 381 */         this.detachedCriteria.addOrder(Order.asc((String)entry.getKey()));
/*     */       else
/* 383 */         this.detachedCriteria.addOrder(Order.desc((String)entry.getKey()));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void judgecreateAlias(String entitys)
/*     */   {
/* 395 */     String[] aliass = entitys.split("\\.");
/* 396 */     for (int i = 0; i < aliass.length - 1; i++)
/* 397 */       createAlias(aliass[i], aliass[i]);
/*     */   }
/*     */ 
/*     */   public static Map<String, Object> getOrdermap()
/*     */   {
/* 402 */     return ordermap;
/*     */   }
/*     */ 
/*     */   public static void setOrdermap(Map<String, Object> ordermap) {
/* 406 */     ordermap = ordermap;
/*     */   }
/*     */ 
/*     */   public void eq(String keyname, Object keyvalue)
/*     */   {
/* 418 */     if ((keyvalue != null) && (keyvalue != "")) {
/* 419 */       this.criterionList.addPara(Restrictions.eq(keyname, keyvalue));
/* 420 */       if (this.flag) {
/* 421 */         put(keyname, keyvalue);
/*     */       }
/* 423 */       this.flag = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void notEq(String keyname, Object keyvalue)
/*     */   {
/* 435 */     if ((keyvalue != null) && (keyvalue != "")) {
/* 436 */       this.criterionList.addPara(Restrictions.ne(keyname, keyvalue));
/* 437 */       if (this.flag) {
/* 438 */         put(keyname, keyvalue);
/*     */       }
/* 440 */       this.flag = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void like(String keyname, Object keyvalue)
/*     */   {
/* 452 */     if ((keyvalue != null) && (keyvalue != ""))
/*     */     {
/* 454 */       this.criterionList.addPara(Restrictions.like(keyname, keyvalue));
/* 455 */       if (this.flag) {
/* 456 */         put(keyname, keyvalue);
/*     */       }
/* 458 */       this.flag = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void gt(String keyname, Object keyvalue)
/*     */   {
/* 470 */     if ((keyvalue != null) && (keyvalue != "")) {
/* 471 */       this.criterionList.addPara(Restrictions.gt(keyname, keyvalue));
/* 472 */       if (this.flag) {
/* 473 */         put(keyname, keyvalue);
/*     */       }
/* 475 */       this.flag = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void lt(String keyname, Object keyvalue)
/*     */   {
/* 487 */     if ((keyvalue != null) && (keyvalue != "")) {
/* 488 */       this.criterionList.addPara(Restrictions.lt(keyname, keyvalue));
/* 489 */       if (this.flag) {
/* 490 */         put(keyname, keyvalue);
/*     */       }
/* 492 */       this.flag = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void le(String keyname, Object keyvalue)
/*     */   {
/* 504 */     if ((keyvalue != null) && (keyvalue != "")) {
/* 505 */       this.criterionList.addPara(Restrictions.le(keyname, keyvalue));
/* 506 */       if (this.flag) {
/* 507 */         put(keyname, keyvalue);
/*     */       }
/* 509 */       this.flag = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void ge(String keyname, Object keyvalue)
/*     */   {
/* 521 */     if ((keyvalue != null) && (keyvalue != "")) {
/* 522 */       this.criterionList.addPara(Restrictions.ge(keyname, keyvalue));
/* 523 */       if (this.flag) {
/* 524 */         put(keyname, keyvalue);
/*     */       }
/* 526 */       this.flag = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void in(String keyname, Object[] keyvalue)
/*     */   {
/* 538 */     if ((keyvalue != null) && (keyvalue.length > 0) && (keyvalue[0] != ""))
/* 539 */       this.criterionList.addPara(Restrictions.in(keyname, keyvalue));
/*     */   }
/*     */ 
/*     */   public void isNull(String keyname)
/*     */   {
/* 551 */     this.criterionList.addPara(Restrictions.isNull(keyname));
/*     */   }
/*     */ 
/*     */   public void isNotNull(String keyname)
/*     */   {
/* 562 */     this.criterionList.addPara(Restrictions.isNotNull(keyname));
/*     */   }
/*     */ 
/*     */   public void put(String keyname, Object keyvalue)
/*     */   {
/* 573 */     if ((keyvalue != null) && (keyvalue != ""))
/* 574 */       map.put(keyname, keyvalue);
/*     */   }
/*     */ 
/*     */   public void between(String keyname, Object keyvalue1, Object keyvalue2)
/*     */   {
/* 586 */     Criterion c = null;
/*     */ 
/* 588 */     if ((!keyvalue1.equals(null)) && (!keyvalue2.equals(null)))
/* 589 */       c = Restrictions.between(keyname, keyvalue1, keyvalue2);
/* 590 */     else if (!keyvalue1.equals(null))
/* 591 */       c = Restrictions.ge(keyname, keyvalue1);
/* 592 */     else if (!keyvalue2.equals(null)) {
/* 593 */       c = Restrictions.le(keyname, keyvalue2);
/*     */     }
/* 595 */     this.criterionList.add(c);
/*     */   }
/*     */ 
/*     */   public void sql(String sql) {
/* 599 */     Restrictions.sqlRestriction(sql);
/*     */   }
/*     */ 
/*     */   public void sql(String sql, Object[] objects, Type[] type) {
/* 603 */     Restrictions.sqlRestriction(sql, objects, type);
/*     */   }
/*     */ 
/*     */   public void sql(String sql, Object objects, Type type) {
/* 607 */     Restrictions.sqlRestriction(sql, objects, type);
/*     */   }
/*     */ 
/*     */   public Integer getCurPage() {
/* 611 */     return Integer.valueOf(this.curPage);
/*     */   }
/*     */ 
/*     */   public void setCurPage(Integer curPage) {
/* 615 */     this.curPage = curPage.intValue();
/*     */   }
/*     */ 
/*     */   public int getPageSize() {
/* 619 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public void setPageSize(int pageSize)
/*     */   {
/* 628 */     this.pageSize = pageSize;
/*     */   }
/*     */ 
/*     */   public String getMyAction() {
/* 632 */     return this.myAction;
/*     */   }
/*     */ 
/*     */   public void setMyAction(String myAction) {
/* 636 */     this.myAction = myAction;
/*     */   }
/*     */ 
/*     */   public String getMyForm() {
/* 640 */     return this.myForm;
/*     */   }
/*     */ 
/*     */   public void setMyForm(String myForm) {
/* 644 */     this.myForm = myForm;
/*     */   }
/*     */ 
/*     */   public CriterionList getCriterionList() {
/* 648 */     return this.criterionList;
/*     */   }
/*     */ 
/*     */   public void setCriterionList(CriterionList criterionList) {
/* 652 */     this.criterionList = criterionList;
/*     */   }
/*     */ 
/*     */   public DetachedCriteria getDetachedCriteria() {
/* 656 */     return this.detachedCriteria;
/*     */   }
/*     */ 
/*     */   public String getField() {
/* 660 */     return this.field;
/*     */   }
/*     */ 
/*     */   public void setField(String field) {
/* 664 */     this.field = field;
/*     */   }
/*     */ 
/*     */   public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
/* 668 */     this.detachedCriteria = detachedCriteria;
/*     */   }
/*     */ 
/*     */   public int getIsUseimage() {
/* 672 */     return this.isUseimage;
/*     */   }
/*     */ 
/*     */   public void setIsUseimage(int isUseimage)
/*     */   {
/* 681 */     this.isUseimage = isUseimage;
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getMap() {
/* 685 */     return map;
/*     */   }
/*     */ 
/*     */   public void setMap(Map<String, Object> map) {
/* 689 */     map = map;
/*     */   }
/*     */ 
/*     */   public boolean isFlag() {
/* 693 */     return this.flag;
/*     */   }
/*     */ 
/*     */   public void setFlag(boolean flag)
/*     */   {
/* 702 */     this.flag = flag;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery
 * JD-Core Version:    0.6.2
 */