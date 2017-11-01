/*     */ package org.jeecgframework.web.sms.util.msg.domain;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.web.sms.util.msg.util.MsgUtils;
/*     */ 
/*     */ public class MsgSubmit extends MsgHead
/*     */ {
/*  24 */   private static Logger logger = Logger.getLogger(MsgSubmit.class);
/*  25 */   private long msgId = 0L;
/*  26 */   private byte pkTotal = 1;
/*  27 */   private byte pkNumber = 1;
/*  28 */   private byte registeredDelivery = 0;
/*  29 */   private byte msgLevel = 1;
/*  30 */   private String serviceId = "";
/*  31 */   private byte feeUserType = 0;
/*  32 */   private String feeTerminalId = "";
/*  33 */   private byte feeTerminalType = 0;
/*  34 */   private byte tpPId = 0;
/*  35 */   private byte tpUdhi = 0;
/*  36 */   private byte msgFmt = 15;
/*     */   private String msgSrc;
/*  42 */   private String feeType = "01";
/*  43 */   private String feeCode = "000000";
/*  44 */   private String valIdTime = "";
/*  45 */   private String atTime = "";
/*     */   private String srcId;
/*  49 */   private byte destUsrTl = 1;
/*     */   private String destTerminalId;
/*  51 */   private byte destTerminalType = 0;
/*     */   private byte msgLength;
/*     */   private byte[] msgContent;
/*  55 */   private String linkID = "";
/*     */ 
/*     */   public byte[] toByteArry()
/*     */   {
/*  63 */     ByteArrayOutputStream bous = new ByteArrayOutputStream();
/*  64 */     DataOutputStream dous = new DataOutputStream(bous);
/*     */     try {
/*  66 */       dous.writeInt(getTotalLength());
/*  67 */       dous.writeInt(getCommandId());
/*  68 */       dous.writeInt(getSequenceId());
/*  69 */       dous.writeLong(this.msgId);
/*  70 */       dous.writeByte(this.pkTotal);
/*  71 */       dous.writeByte(this.pkNumber);
/*  72 */       dous.writeByte(this.registeredDelivery);
/*     */ 
/*  74 */       dous.writeByte(this.msgLevel);
/*  75 */       MsgUtils.writeString(dous, this.serviceId, 10);
/*     */ 
/*  77 */       dous.writeByte(this.feeUserType);
/*     */ 
/*  82 */       MsgUtils.writeString(dous, this.feeTerminalId, 32);
/*     */ 
/*  84 */       dous.writeByte(this.feeTerminalType);
/*     */ 
/*  86 */       dous.writeByte(this.tpPId);
/*  87 */       dous.writeByte(this.tpUdhi);
/*  88 */       dous.writeByte(this.msgFmt);
/*  89 */       MsgUtils.writeString(dous, this.msgSrc, 6);
/*  90 */       MsgUtils.writeString(dous, this.feeType, 2);
/*  91 */       MsgUtils.writeString(dous, this.feeCode, 6);
/*  92 */       MsgUtils.writeString(dous, this.valIdTime, 17);
/*  93 */       MsgUtils.writeString(dous, this.atTime, 17);
/*  94 */       MsgUtils.writeString(dous, this.srcId, 21);
/*  95 */       dous.writeByte(this.destUsrTl);
/*  96 */       MsgUtils.writeString(dous, this.destTerminalId, 32);
/*  97 */       dous.writeByte(this.destTerminalType);
/*     */ 
/*  99 */       dous.writeByte(this.msgLength);
/* 100 */       dous.write(this.msgContent);
/* 101 */       MsgUtils.writeString(dous, this.linkID, 20);
/* 102 */       dous.close();
/*     */     } catch (IOException e) {
/* 104 */       logger.error("封装短信发送二进制数组失败。");
/*     */     }
/* 106 */     return bous.toByteArray();
/*     */   }
/*     */ 
/*     */   public long getMsgId()
/*     */   {
/* 115 */     return this.msgId;
/*     */   }
/*     */ 
/*     */   public void setMsgId(long msgId)
/*     */   {
/* 125 */     this.msgId = msgId;
/*     */   }
/*     */ 
/*     */   public long getPkTotal()
/*     */   {
/* 134 */     return this.pkTotal;
/*     */   }
/*     */ 
/*     */   public void setPkTotal(byte pkTotal)
/*     */   {
/* 144 */     this.pkTotal = pkTotal;
/*     */   }
/*     */ 
/*     */   public byte getPkNumber()
/*     */   {
/* 153 */     return this.pkNumber;
/*     */   }
/*     */ 
/*     */   public void setPkNumber(byte pkNumber)
/*     */   {
/* 163 */     this.pkNumber = pkNumber;
/*     */   }
/*     */ 
/*     */   public byte getRegisteredDelivery()
/*     */   {
/* 172 */     return this.registeredDelivery;
/*     */   }
/*     */ 
/*     */   public void setRegisteredDelivery(byte registeredDelivery)
/*     */   {
/* 182 */     this.registeredDelivery = registeredDelivery;
/*     */   }
/*     */ 
/*     */   public byte getMsgLevel()
/*     */   {
/* 191 */     return this.msgLevel;
/*     */   }
/*     */ 
/*     */   public void setMsgLevel(byte msgLevel)
/*     */   {
/* 201 */     this.msgLevel = msgLevel;
/*     */   }
/*     */ 
/*     */   public String getServiceId()
/*     */   {
/* 210 */     return this.serviceId;
/*     */   }
/*     */ 
/*     */   public void setServiceId(String serviceId)
/*     */   {
/* 220 */     this.serviceId = serviceId;
/*     */   }
/*     */ 
/*     */   public byte getFeeUserType()
/*     */   {
/* 229 */     return this.feeUserType;
/*     */   }
/*     */ 
/*     */   public void setFeeUserType(byte feeUserType)
/*     */   {
/* 239 */     this.feeUserType = feeUserType;
/*     */   }
/*     */ 
/*     */   public String getFeeTerminalId()
/*     */   {
/* 248 */     return this.feeTerminalId;
/*     */   }
/*     */ 
/*     */   public void setFeeTerminalId(String feeTerminalId)
/*     */   {
/* 258 */     this.feeTerminalId = feeTerminalId;
/*     */   }
/*     */ 
/*     */   public byte getFeeTerminalType()
/*     */   {
/* 267 */     return this.feeTerminalType;
/*     */   }
/*     */ 
/*     */   public void setFeeTerminalType(byte feeTerminalType)
/*     */   {
/* 277 */     this.feeTerminalType = feeTerminalType;
/*     */   }
/*     */ 
/*     */   public byte getTpPId()
/*     */   {
/* 286 */     return this.tpPId;
/*     */   }
/*     */ 
/*     */   public void setTpPId(byte tpPId)
/*     */   {
/* 296 */     this.tpPId = tpPId;
/*     */   }
/*     */ 
/*     */   public byte getTpUdhi()
/*     */   {
/* 305 */     return this.tpUdhi;
/*     */   }
/*     */ 
/*     */   public void setTpUdhi(byte tpUdhi)
/*     */   {
/* 315 */     this.tpUdhi = tpUdhi;
/*     */   }
/*     */ 
/*     */   public byte getMsgFmt()
/*     */   {
/* 324 */     return this.msgFmt;
/*     */   }
/*     */ 
/*     */   public void setMsgFmt(byte msgFmt)
/*     */   {
/* 334 */     this.msgFmt = msgFmt;
/*     */   }
/*     */ 
/*     */   public String getMsgSrc()
/*     */   {
/* 343 */     return this.msgSrc;
/*     */   }
/*     */ 
/*     */   public void setMsgSrc(String msgSrc)
/*     */   {
/* 353 */     this.msgSrc = msgSrc;
/*     */   }
/*     */ 
/*     */   public String getFeeType()
/*     */   {
/* 362 */     return this.feeType;
/*     */   }
/*     */ 
/*     */   public void setFeeType(String feeType)
/*     */   {
/* 372 */     this.feeType = feeType;
/*     */   }
/*     */ 
/*     */   public String getFeeCode()
/*     */   {
/* 381 */     return this.feeCode;
/*     */   }
/*     */ 
/*     */   public void setFeeCode(String feeCode)
/*     */   {
/* 391 */     this.feeCode = feeCode;
/*     */   }
/*     */ 
/*     */   public String getValIdTime()
/*     */   {
/* 400 */     return this.valIdTime;
/*     */   }
/*     */ 
/*     */   public void setValIdTime(String valIdTime)
/*     */   {
/* 410 */     this.valIdTime = valIdTime;
/*     */   }
/*     */ 
/*     */   public String getAtTime()
/*     */   {
/* 419 */     return this.atTime;
/*     */   }
/*     */ 
/*     */   public void setAtTime(String atTime)
/*     */   {
/* 429 */     this.atTime = atTime;
/*     */   }
/*     */ 
/*     */   public String getSrcId()
/*     */   {
/* 438 */     return this.srcId;
/*     */   }
/*     */ 
/*     */   public void setSrcId(String srcId)
/*     */   {
/* 448 */     this.srcId = srcId;
/*     */   }
/*     */ 
/*     */   public byte getDestUsrTl()
/*     */   {
/* 457 */     return this.destUsrTl;
/*     */   }
/*     */ 
/*     */   public void setDestUsrTl(byte destUsrTl)
/*     */   {
/* 467 */     this.destUsrTl = destUsrTl;
/*     */   }
/*     */ 
/*     */   public String getDestTerminalId()
/*     */   {
/* 476 */     return this.destTerminalId;
/*     */   }
/*     */ 
/*     */   public void setDestTerminalId(String destTerminalId)
/*     */   {
/* 486 */     this.destTerminalId = destTerminalId;
/*     */   }
/*     */ 
/*     */   public byte getDestTerminalType()
/*     */   {
/* 495 */     return this.destTerminalType;
/*     */   }
/*     */ 
/*     */   public void setDestTerminalType(byte destTerminalType)
/*     */   {
/* 505 */     this.destTerminalType = destTerminalType;
/*     */   }
/*     */ 
/*     */   public byte getMsgLength()
/*     */   {
/* 514 */     return this.msgLength;
/*     */   }
/*     */ 
/*     */   public void setMsgLength(byte msgLength)
/*     */   {
/* 524 */     this.msgLength = msgLength;
/*     */   }
/*     */ 
/*     */   public byte[] getMsgContent()
/*     */   {
/* 533 */     return this.msgContent;
/*     */   }
/*     */ 
/*     */   public void setMsgContent(byte[] msgContent)
/*     */   {
/* 543 */     this.msgContent = msgContent;
/*     */   }
/*     */ 
/*     */   public String getLinkID()
/*     */   {
/* 552 */     return this.linkID;
/*     */   }
/*     */ 
/*     */   public void setLinkID(String linkID)
/*     */   {
/* 562 */     this.linkID = linkID;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.util.msg.domain.MsgSubmit
 * JD-Core Version:    0.6.2
 */