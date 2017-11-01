/*    */ package org.jeecgframework.web.sms.util.msg.domain;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class MsgDeliverResp extends MsgHead
/*    */ {
/* 21 */   private static Logger logger = Logger.getLogger(MsgDeliverResp.class);
/*    */   private long msgId;
/*    */   private int result;
/*    */ 
/*    */   public byte[] toByteArry()
/*    */   {
/* 32 */     ByteArrayOutputStream bous = new ByteArrayOutputStream();
/* 33 */     DataOutputStream dous = new DataOutputStream(bous);
/*    */     try {
/* 35 */       dous.writeInt(getTotalLength());
/* 36 */       dous.writeInt(getCommandId());
/* 37 */       dous.writeInt(getSequenceId());
/* 38 */       dous.writeLong(this.msgId);
/* 39 */       dous.writeInt(this.result);
/* 40 */       dous.close();
/*    */     } catch (IOException e) {
/* 42 */       logger.error("封装链接二进制数组失败。");
/*    */     }
/* 44 */     return bous.toByteArray();
/*    */   }
/*    */ 
/*    */   public long getMsgId()
/*    */   {
/* 53 */     return this.msgId;
/*    */   }
/*    */ 
/*    */   public void setMsgId(long msgId)
/*    */   {
/* 63 */     this.msgId = msgId;
/*    */   }
/*    */ 
/*    */   public int getResult()
/*    */   {
/* 72 */     return this.result;
/*    */   }
/*    */ 
/*    */   public void setResult(int result)
/*    */   {
/* 82 */     this.result = result;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.util.msg.domain.MsgDeliverResp
 * JD-Core Version:    0.6.2
 */