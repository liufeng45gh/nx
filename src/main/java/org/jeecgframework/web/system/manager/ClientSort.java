/*    */ package org.jeecgframework.web.system.manager;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.Date;
/*    */ import org.jeecgframework.web.system.pojo.base.Client;
/*    */ 
/*    */ public class ClientSort
/*    */   implements Comparator<Client>
/*    */ {
/*    */   public int compare(Client prev, Client now)
/*    */   {
/* 11 */     return (int)(now.getLogindatetime().getTime() - prev.getLogindatetime().getTime());
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.manager.ClientSort
 * JD-Core Version:    0.6.2
 */