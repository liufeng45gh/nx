/*    */ package org.jeecgframework.core.util;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.apache.commons.beanutils.BeanUtils;
/*    */ 
/*    */ public class ListUtils
/*    */ {
/*    */   public static <E> List<E> copyTo(List<?> source, Class<E> destinationClass)
/*    */     throws Exception
/*    */   {
/* 18 */     if (source.size() == 0)
/* 19 */       return new ArrayList();
/* 20 */     List res = new ArrayList(source.size());
/* 21 */     for (Iterator localIterator = source.iterator(); localIterator.hasNext(); ) { Object o = localIterator.next();
/* 22 */       Object e = destinationClass.newInstance();
/* 23 */       BeanUtils.copyProperties(e, o);
/* 24 */       res.add(e);
/*    */     }
/* 26 */     return res;
/*    */   }
/*    */ 
/*    */   public static boolean isNullOrEmpty(List list)
/*    */   {
/* 31 */     return (list == null) || (list.isEmpty());
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.ListUtils
 * JD-Core Version:    0.6.2
 */