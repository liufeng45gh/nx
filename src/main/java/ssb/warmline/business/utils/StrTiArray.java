/*    */ package ssb.warmline.business.utils;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.StringTokenizer;
/*    */ 
/*    */ public class StrTiArray
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/*  7 */     String str = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
/*  8 */     String[] strArray = null;
/*  9 */     System.out.println("调用convertStrToArray结果：");
/* 10 */     strArray = convertStrToArray(str);
/* 11 */     printArray(strArray);
/*    */ 
/* 13 */     System.out.println("调用convertStrToArray2结果：");
/* 14 */     strArray = convertStrToArray2(str);
/* 15 */     printArray(strArray);
/*    */   }
/*    */ 
/*    */   public static String[] convertStrToArray(String str) {
/* 19 */     String[] strArray = null;
/* 20 */     strArray = str.split(",");
/* 21 */     return strArray;
/*    */   }
/*    */ 
/*    */   public static String[] convertStrToArray2(String str)
/*    */   {
/* 29 */     StringTokenizer st = new StringTokenizer(str, ",");
/* 30 */     String[] strArray = new String[st.countTokens()];
/* 31 */     int i = 0;
/* 32 */     while (st.hasMoreTokens()) {
/* 33 */       strArray[(i++)] = st.nextToken();
/*    */     }
/* 35 */     return strArray;
/*    */   }
/*    */ 
/*    */   public static void printArray(String[] array) {
/* 39 */     for (int i = 0; i < array.length; i++) {
/* 40 */       System.out.print(array[i]);
/* 41 */       if (i == array.length - 1)
/* 42 */         System.out.print("\n");
/*    */       else
/* 44 */         System.out.print(",");
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.utils.StrTiArray
 * JD-Core Version:    0.6.2
 */