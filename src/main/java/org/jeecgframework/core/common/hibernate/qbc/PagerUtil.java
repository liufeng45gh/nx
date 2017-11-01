/*    */ package org.jeecgframework.core.common.hibernate.qbc;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.jeecgframework.core.util.LogUtil;
/*    */ 
/*    */ public class PagerUtil
/*    */ {
/*    */   public static String getBar(String action, String form, int allCounts, int curPageNO, int pageSize, Map<String, Object> map)
/*    */   {
/* 18 */     Pager pager = null;
/*    */     try {
/* 20 */       if (curPageNO > (int)Math.ceil(allCounts / pageSize))
/* 21 */         curPageNO = (int)Math.ceil(allCounts / pageSize);
/* 22 */       if (curPageNO <= 1) {
/* 23 */         curPageNO = 1;
/*    */       }
/* 25 */       pager = new Pager(allCounts, curPageNO, pageSize, map);
/*    */     } catch (Exception e) {
/* 27 */       LogUtil.info("生成工具条出错!");
/*    */     }
/* 29 */     return pager.getToolBar(action, form);
/*    */   }
/*    */ 
/*    */   public static String getBar(String url, int allCounts, int curPageNO, int pageSize, Map<String, Object> map) {
/* 33 */     Pager pager = null;
/*    */     try {
/* 35 */       if (curPageNO > (int)Math.ceil(allCounts / pageSize))
/* 36 */         curPageNO = (int)Math.ceil(allCounts / pageSize);
/* 37 */       if (curPageNO <= 1) {
/* 38 */         curPageNO = 1;
/*    */       }
/*    */ 
/* 41 */       pager = new Pager(allCounts, curPageNO, pageSize, map);
/*    */     } catch (Exception e) {
/* 43 */       LogUtil.info("生成工具条出错!");
/*    */     }
/* 45 */     return pager.getToolBar(url);
/*    */   }
/*    */ 
/*    */   public static int getOffset(int rowCounts, int curPageNO, int pageSize) {
/* 49 */     int offset = 0;
/*    */     try {
/* 51 */       if (curPageNO > (int)Math.ceil(rowCounts / pageSize)) {
/* 52 */         curPageNO = (int)Math.ceil(rowCounts / pageSize);
/*    */       }
/* 54 */       if (curPageNO <= 1) {
/* 55 */         curPageNO = 1;
/*    */       }
/* 57 */       offset = (curPageNO - 1) * pageSize;
/*    */     } catch (Exception e) {
/* 59 */       LogUtil.info("getOffset出错!");
/*    */     }
/* 61 */     return offset;
/*    */   }
/*    */ 
/*    */   public static int getcurPageNo(int rowCounts, int curPageNO, int pageSize)
/*    */   {
/*    */     try {
/* 67 */       if (curPageNO > (int)Math.ceil(rowCounts / pageSize))
/* 68 */         curPageNO = (int)Math.ceil(rowCounts / pageSize);
/* 69 */       if (curPageNO <= 1)
/* 70 */         curPageNO = 1;
/*    */     } catch (Exception e) {
/* 72 */       LogUtil.info("getOffset出错!");
/*    */     }
/* 74 */     return curPageNO;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.common.hibernate.qbc.PagerUtil
 * JD-Core Version:    0.6.2
 */