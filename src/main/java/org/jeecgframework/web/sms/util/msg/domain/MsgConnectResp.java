/*     */ package org.jeecgframework.web.sms.util.msg.domain;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.IOException;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class MsgConnectResp extends MsgHead
/*     */ {
/*  19 */   private static Logger logger = Logger.getLogger(MsgConnectResp.class);
/*     */   private int status;
/*     */   private String statusStr;
/*     */   private byte[] authenticatorISMG;
/*     */   private byte version;
/*     */ 
/*     */   public MsgConnectResp(byte[] data)
/*     */   {
/*  38 */     if (data.length == 29) {
/*  39 */       ByteArrayInputStream bins = new ByteArrayInputStream(data);
/*  40 */       DataInputStream dins = new DataInputStream(bins);
/*     */       try {
/*  42 */         setTotalLength(data.length + 4);
/*  43 */         setCommandId(dins.readInt());
/*  44 */         setSequenceId(dins.readInt());
/*  45 */         setStatus(dins.readInt());
/*  46 */         byte[] aiByte = new byte[16];
/*  47 */         dins.read(aiByte);
/*  48 */         this.authenticatorISMG = aiByte;
/*  49 */         this.version = dins.readByte();
/*  50 */         dins.close();
/*  51 */         bins.close();
/*     */       } catch (IOException e) {
/*  53 */         e.printStackTrace();
/*     */       }
/*     */     } else {
/*  56 */       logger.info("链接至IMSP,解析数据包出错，包长度不一致。长度为:" + data.length);
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getStatus()
/*     */   {
/*  66 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(int status)
/*     */   {
/*  76 */     this.status = status;
/*  77 */     switch (status) {
/*     */     case 0:
/*  79 */       this.statusStr = "正确";
/*  80 */       break;
/*     */     case 1:
/*  82 */       this.statusStr = "消息结构错";
/*  83 */       break;
/*     */     case 2:
/*  85 */       this.statusStr = "非法源地址";
/*  86 */       break;
/*     */     case 3:
/*  88 */       this.statusStr = "认证错";
/*  89 */       break;
/*     */     case 4:
/*  91 */       this.statusStr = "版本太高";
/*  92 */       break;
/*     */     case 5:
/*  94 */       this.statusStr = "其他错误";
/*  95 */       break;
/*     */     default:
/*  97 */       this.statusStr = (status + ":未知");
/*     */     }
/*     */   }
/*     */ 
/*     */   public byte[] getAuthenticatorISMG()
/*     */   {
/* 108 */     return this.authenticatorISMG;
/*     */   }
/*     */ 
/*     */   public void setAuthenticatorISMG(byte[] authenticatorISMG)
/*     */   {
/* 118 */     this.authenticatorISMG = authenticatorISMG;
/*     */   }
/*     */ 
/*     */   public byte getVersion()
/*     */   {
/* 127 */     return this.version;
/*     */   }
/*     */ 
/*     */   public void setVersion(byte version)
/*     */   {
/* 137 */     this.version = version;
/*     */   }
/*     */ 
/*     */   public String getStatusStr()
/*     */   {
/* 146 */     return this.statusStr;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.util.msg.domain.MsgConnectResp
 * JD-Core Version:    0.6.2
 */