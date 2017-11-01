/*    */ package org.jeecgframework.core.util;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.jeecgframework.web.system.pojo.base.TSFunction;
/*    */ 
/*    */ public class ListtoJson
/*    */ {
/* 30 */   static int count = 0;
/*    */ 
/*    */   public static String getJsonData(List list)
/*    */   {
/* 12 */     StringBuffer buffer = new StringBuffer();
/*    */ 
/* 14 */     buffer.append("[");
/*    */ 
/* 16 */     iterGet(list, "0", buffer);
/*    */ 
/* 18 */     buffer.append("]");
/*    */ 
/* 22 */     String tmp = buffer.toString();
/*    */ 
/* 24 */     tmp = tmp.replaceAll(",\n]", "\n]");
/*    */ 
/* 26 */     return tmp;
/*    */   }
/*    */ 
/*    */   static void iterGet(List<TSFunction> list, String pid, StringBuffer buffer)
/*    */   {
/* 40 */     for (TSFunction node : list)
/*    */     {
/* 43 */       if (node.getTSFunction() != null)
/*    */       {
/* 45 */         if (pid.equals(oConvertUtils.getString(node.getTSFunction().getId())))
/*    */         {
/* 47 */           count += 1;
/* 48 */           buffer.append("{'id':" + node.getId() + ",'text':'" + 
/* 49 */             node.getFunctionName() + "','children':[");
/*    */ 
/* 51 */           iterGet(list, node.getId(), buffer);
/* 52 */           buffer.append("]},\n");
/* 53 */           count -= 1;
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.ListtoJson
 * JD-Core Version:    0.6.2
 */