/*     */ package org.jeecgframework.web.sms.util.msg.domain;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.web.sms.util.msg.util.MsgUtils;
/*     */ 
/*     */ public class MsgConnect extends MsgHead
/*     */ {
/*  25 */   private static Logger logger = Logger.getLogger(MsgConnect.class);
/*     */   private String sourceAddr;
/*     */   private byte[] authenticatorSource;
/*     */   private byte version;
/*     */   private int timestamp;
/*     */ 
/*     */   public byte[] toByteArry()
/*     */   {
/*  41 */     ByteArrayOutputStream bous = new ByteArrayOutputStream();
/*  42 */     DataOutputStream dous = new DataOutputStream(bous);
/*     */     try {
/*  44 */       dous.writeInt(getTotalLength());
/*  45 */       dous.writeInt(getCommandId());
/*  46 */       dous.writeInt(getSequenceId());
/*  47 */       MsgUtils.writeString(dous, this.sourceAddr, 6);
/*  48 */       dous.write(this.authenticatorSource);
/*  49 */       dous.writeByte(48);
/*  50 */       dous.writeInt(Integer.parseInt(MsgUtils.getTimestamp()));
/*  51 */       dous.close();
/*     */     } catch (IOException e) {
/*  53 */       logger.error("封装链接二进制数组失败。");
/*     */     }
/*  55 */     return bous.toByteArray();
/*     */   }
/*     */ 
/*     */   public String getSourceAddr()
/*     */   {
/*  64 */     return this.sourceAddr;
/*     */   }
/*     */ 
/*     */   public void setSourceAddr(String sourceAddr)
/*     */   {
/*  74 */     this.sourceAddr = sourceAddr;
/*     */   }
/*     */ 
/*     */   public byte[] getAuthenticatorSource()
/*     */   {
/*  83 */     return this.authenticatorSource;
/*     */   }
/*     */ 
/*     */   public void setAuthenticatorSource(byte[] authenticatorSource)
/*     */   {
/*  93 */     this.authenticatorSource = authenticatorSource;
/*     */   }
/*     */ 
/*     */   public byte getVersion()
/*     */   {
/* 102 */     return this.version;
/*     */   }
/*     */ 
/*     */   public void setVersion(byte version)
/*     */   {
/* 112 */     this.version = version;
/*     */   }
/*     */ 
/*     */   public int getTimestamp()
/*     */   {
/* 121 */     return this.timestamp;
/*     */   }
/*     */ 
/*     */   public void setTimestamp(int timestamp)
/*     */   {
/* 131 */     this.timestamp = timestamp;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.util.msg.domain.MsgConnect
 * JD-Core Version:    0.6.2
 */