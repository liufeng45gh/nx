/*    */ package org.jeecgframework.web.sms.util.msg.domain;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.IOException;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class MsgActiveTestResp extends MsgHead
/*    */ {
/* 20 */   private static Logger logger = Logger.getLogger(MsgActiveTestResp.class);
/*    */   private byte reserved;
/*    */ 
/*    */   public MsgActiveTestResp(byte[] data)
/*    */   {
/* 30 */     if (data.length == 9) {
/* 31 */       ByteArrayInputStream bins = new ByteArrayInputStream(data);
/* 32 */       DataInputStream dins = new DataInputStream(bins);
/*    */       try {
/* 34 */         setTotalLength(data.length + 4);
/* 35 */         setCommandId(dins.readInt());
/* 36 */         setSequenceId(dins.readInt());
/* 37 */         this.reserved = dins.readByte();
/* 38 */         dins.close();
/* 39 */         bins.close();
/*    */       } catch (IOException e) {
/* 41 */         e.printStackTrace();
/*    */       }
/*    */     } else {
/* 44 */       logger.info("链路检查,解析数据包出错，包长度不一致。长度为:" + data.length);
/*    */     }
/*    */   }
/*    */ 
/*    */   public byte getReserved()
/*    */   {
/* 54 */     return this.reserved;
/*    */   }
/*    */ 
/*    */   public void setReserved(byte reserved)
/*    */   {
/* 64 */     this.reserved = reserved;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.util.msg.domain.MsgActiveTestResp
 * JD-Core Version:    0.6.2
 */