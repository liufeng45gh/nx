/*    */ package org.jeecgframework.web.sms.util.msg.domain;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.IOException;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class MsgSubmitResp extends MsgHead
/*    */ {
/* 20 */   private static Logger logger = Logger.getLogger(MsgSubmitResp.class);
/*    */   private long msgId;
/*    */   private int result;
/*    */ 
/*    */   public MsgSubmitResp(byte[] data)
/*    */   {
/* 34 */     if (data.length == 20) {
/* 35 */       ByteArrayInputStream bins = new ByteArrayInputStream(data);
/* 36 */       DataInputStream dins = new DataInputStream(bins);
/*    */       try {
/* 38 */         setTotalLength(data.length + 4);
/* 39 */         setCommandId(dins.readInt());
/* 40 */         setSequenceId(dins.readInt());
/* 41 */         this.msgId = dins.readLong();
/* 42 */         this.result = dins.readInt();
/* 43 */         dins.close();
/* 44 */         bins.close();
/*    */       } catch (IOException e) {
/* 46 */         e.printStackTrace();
/*    */       }
/*    */     } else {
/* 49 */       logger.info("发送短信IMSP回复,解析数据包出错，包长度不一致。长度为:" + data.length);
/*    */     }
/*    */   }
/*    */ 
/*    */   public long getMsgId()
/*    */   {
/* 59 */     return this.msgId;
/*    */   }
/*    */ 
/*    */   public void setMsgId(long msgId)
/*    */   {
/* 69 */     this.msgId = msgId;
/*    */   }
/*    */ 
/*    */   public int getResult()
/*    */   {
/* 78 */     return this.result;
/*    */   }
/*    */ 
/*    */   public void setResult(int result)
/*    */   {
/* 88 */     this.result = result;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.util.msg.domain.MsgSubmitResp
 * JD-Core Version:    0.6.2
 */