/*    */ package org.jeecgframework.web.cgform.entity.config;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class CgSubTableVO
/*    */ {
/* 17 */   CgFormHeadEntity head = new CgFormHeadEntity();
/*    */ 
/* 20 */   List<Map<String, Object>> fieldList = new ArrayList();
/*    */ 
/* 22 */   List<Map<String, Object>> hiddenFieldList = new ArrayList();
/*    */ 
/* 25 */   List<Map<String, Object>> fieldNoAreaList = new ArrayList();
/*    */ 
/* 27 */   List<Map<String, Object>> fieldAreaList = new ArrayList();
/*    */ 
/*    */   public CgFormHeadEntity getHead()
/*    */   {
/* 31 */     return this.head;
/*    */   }
/*    */ 
/*    */   public void setHead(CgFormHeadEntity head) {
/* 35 */     this.head = head;
/*    */   }
/*    */ 
/*    */   public List<Map<String, Object>> getFieldList() {
/* 39 */     return this.fieldList;
/*    */   }
/*    */ 
/*    */   public void setFieldList(List<Map<String, Object>> fieldList) {
/* 43 */     this.fieldList = fieldList;
/* 44 */     for (Map map : fieldList)
/* 45 */       if ("Y".equals((String)map.get("is_show")))
/* 46 */         if ("textarea".equals((String)map.get("show_type")))
/* 47 */           this.fieldAreaList.add(map);
/*    */         else
/* 49 */           this.fieldNoAreaList.add(map);
/*    */   }
/*    */ 
/*    */   public List<Map<String, Object>> getHiddenFieldList()
/*    */   {
/* 56 */     return this.hiddenFieldList;
/*    */   }
/*    */ 
/*    */   public void setHiddenFieldList(List<Map<String, Object>> hiddenFieldList) {
/* 60 */     this.hiddenFieldList = hiddenFieldList;
/*    */   }
/*    */ 
/*    */   public List<Map<String, Object>> getFieldNoAreaList() {
/* 64 */     return this.fieldNoAreaList;
/*    */   }
/*    */ 
/*    */   public void setFieldNoAreaList(List<Map<String, Object>> fieldNoAreaList) {
/* 68 */     this.fieldNoAreaList = fieldNoAreaList;
/*    */   }
/*    */ 
/*    */   public List<Map<String, Object>> getFieldAreaList() {
/* 72 */     return this.fieldAreaList;
/*    */   }
/*    */ 
/*    */   public void setFieldAreaList(List<Map<String, Object>> fieldAreaList) {
/* 76 */     this.fieldAreaList = fieldAreaList;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.config.CgSubTableVO
 * JD-Core Version:    0.6.2
 */