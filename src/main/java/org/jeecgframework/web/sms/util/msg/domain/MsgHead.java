/*     */ package org.jeecgframework.web.sms.util.msg.domain;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class MsgHead
/*     */ {
/*  25 */   private Logger logger = Logger.getLogger(MsgHead.class);
/*     */   private int totalLength;
/*     */   private int commandId;
/*     */   private int sequenceId;
/*     */ 
/*     */   public byte[] toByteArry()
/*     */   {
/*  37 */     ByteArrayOutputStream bous = new ByteArrayOutputStream();
/*  38 */     DataOutputStream dous = new DataOutputStream(bous);
/*     */     try {
/*  40 */       dous.writeInt(getTotalLength());
/*  41 */       dous.writeInt(getCommandId());
/*  42 */       dous.writeInt(getSequenceId());
/*  43 */       dous.close();
/*     */     } catch (IOException e) {
/*  45 */       this.logger.error("封装CMPP消息头二进制数组失败。");
/*     */     }
/*  47 */     return bous.toByteArray();
/*     */   }
/*     */ 
/*     */   public MsgHead(byte[] data)
/*     */   {
/*  57 */     ByteArrayInputStream bins = new ByteArrayInputStream(data);
/*  58 */     DataInputStream dins = new DataInputStream(bins);
/*     */     try {
/*  60 */       setTotalLength(data.length + 4);
/*  61 */       setCommandId(dins.readInt());
/*  62 */       setSequenceId(dins.readInt());
/*  63 */       dins.close();
/*  64 */       bins.close();
/*     */     } catch (IOException e) {
/*  66 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public MsgHead()
/*     */   {
/*     */   }
/*     */ 
/*     */   public int getTotalLength()
/*     */   {
/*  83 */     return this.totalLength;
/*     */   }
/*     */ 
/*     */   public void setTotalLength(int totalLength)
/*     */   {
/*  93 */     this.totalLength = totalLength;
/*     */   }
/*     */ 
/*     */   public int getCommandId()
/*     */   {
/* 102 */     return this.commandId;
/*     */   }
/*     */ 
/*     */   public void setCommandId(int commandId)
/*     */   {
/* 112 */     this.commandId = commandId;
/*     */   }
/*     */ 
/*     */   public int getSequenceId()
/*     */   {
/* 121 */     return this.sequenceId;
/*     */   }
/*     */ 
/*     */   public void setSequenceId(int sequenceId)
/*     */   {
/* 131 */     this.sequenceId = sequenceId;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.util.msg.domain.MsgHead
 * JD-Core Version:    0.6.2
 */