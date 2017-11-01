/*    */ package org.jeecgframework.core.util;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.jeecgframework.web.system.pojo.base.TSFunction;
/*    */ 
/*    */ public class NumberComparator
/*    */   implements Comparator<Object>
/*    */ {
/* 15 */   private boolean ignoreCase = true;
/*    */ 
/*    */   public NumberComparator() {
/*    */   }
/*    */ 
/*    */   public NumberComparator(boolean ignoreCase) {
/* 21 */     this.ignoreCase = ignoreCase;
/*    */   }
/*    */ 
/*    */   public int compare(Object obj1, Object obj2) {
/* 25 */     String o1 = "";
/* 26 */     String o2 = "";
/* 27 */     if (this.ignoreCase) {
/* 28 */       TSFunction c1 = (TSFunction)obj1;
/* 29 */       TSFunction c2 = (TSFunction)obj2;
/* 30 */       o1 = c1.getFunctionOrder();
/* 31 */       o2 = c2.getFunctionOrder();
/*    */     }
/* 33 */     if ((o1 != null) && (o2 != null)) {
/* 34 */       for (int i = 0; i < o1.length(); i++) {
/* 35 */         if ((i == o1.length()) && (i < o2.length()))
/* 36 */           return -1;
/* 37 */         if ((i == o2.length()) && (i < o1.length())) {
/* 38 */           return 1;
/*    */         }
/* 40 */         char ch1 = o1.charAt(i);
/* 41 */         char ch2 = o2.charAt(i);
/* 42 */         if ((ch1 >= '0') && (ch2 <= '9')) {
/* 43 */           int i1 = getNumber(o1.substring(i));
/* 44 */           int i2 = getNumber(o2.substring(i));
/* 45 */           if (i1 != i2)
/*    */           {
/* 48 */             return i1 - i2;
/*    */           }
/* 50 */         } else if (ch1 != ch2) {
/* 51 */           return ch1 - ch2;
/*    */         }
/*    */       }
/*    */     }
/* 55 */     return 0;
/*    */   }
/*    */ 
/*    */   private int getNumber(String str) {
/* 59 */     int num = 2147483647;
/* 60 */     int bits = 0;
/* 61 */     for (int i = 0; i < str.length(); i++) {
/* 62 */       if ((str.charAt(i) < '0') || (str.charAt(i) > '9')) break;
/* 63 */       bits++;
/*    */     }
/*    */ 
/* 68 */     if (bits > 0) {
/* 69 */       num = Integer.parseInt(str.substring(0, bits));
/*    */     }
/* 71 */     return num;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.NumberComparator
 * JD-Core Version:    0.6.2
 */