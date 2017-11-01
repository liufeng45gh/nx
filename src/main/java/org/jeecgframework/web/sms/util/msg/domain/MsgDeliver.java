/*     */ package org.jeecgframework.web.sms.util.msg.domain;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.IOException;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class MsgDeliver extends MsgHead
/*     */ {
/*  13 */   private static Logger logger = Logger.getLogger(MsgDeliver.class);
/*     */   private long msgId;
/*     */   private String destId;
/*     */   private String serviceId;
/*  17 */   private byte tPPid = 0;
/*  18 */   private byte tPUdhi = 0;
/*  19 */   private byte msgFmt = 15;
/*     */   private String srcTerminalId;
/*  21 */   private byte srcTerminalType = 0;
/*  22 */   private byte registeredDelivery = 0;
/*     */   private byte msgLength;
/*     */   private String msgContent;
/*     */   private String linkID;
/*     */   private long msgIdReport;
/*     */   private String stat;
/*     */   private String submitTime;
/*     */   private String doneTime;
/*     */   private String destTerminalId;
/*     */   private int sMSCSequence;
/*     */   private int result;
/*     */ 
/*     */   public MsgDeliver(byte[] data)
/*     */   {
/*  42 */     if (data.length > 105) {
/*  43 */       String fmtStr = "gb2312";
/*  44 */       ByteArrayInputStream bins = new ByteArrayInputStream(data);
/*  45 */       DataInputStream dins = new DataInputStream(bins);
/*     */       try {
/*  47 */         setTotalLength(data.length + 4);
/*  48 */         setCommandId(dins.readInt());
/*  49 */         setSequenceId(dins.readInt());
/*  50 */         this.msgId = dins.readLong();
/*  51 */         byte[] destIdByte = new byte[21];
/*  52 */         dins.read(destIdByte);
/*  53 */         this.destId = new String(destIdByte);
/*  54 */         byte[] service_IdByte = new byte[10];
/*  55 */         dins.read(service_IdByte);
/*  56 */         this.serviceId = new String(service_IdByte);
/*  57 */         this.tPPid = dins.readByte();
/*  58 */         this.tPUdhi = dins.readByte();
/*  59 */         this.msgFmt = dins.readByte();
/*  60 */         fmtStr = this.msgFmt == 8 ? "utf-8" : "gb2312";
/*  61 */         byte[] src_terminal_IdByte = new byte[32];
/*  62 */         dins.read(src_terminal_IdByte);
/*  63 */         this.srcTerminalId = new String(src_terminal_IdByte);
/*  64 */         this.srcTerminalType = dins.readByte();
/*  65 */         this.registeredDelivery = dins.readByte();
/*     */ 
/*  67 */         this.msgLength = dins.readByte();
/*  68 */         byte[] msg_ContentByte = new byte[this.msgLength];
/*  69 */         dins.read(msg_ContentByte);
/*  70 */         if (this.registeredDelivery == 1) {
/*  71 */           this.msgContent = new String(msg_ContentByte, fmtStr);
/*  72 */           if (this.msgLength == 60) {
/*  73 */             ByteArrayInputStream binsC = new ByteArrayInputStream(
/*  74 */               data);
/*  75 */             DataInputStream dinsC = new DataInputStream(binsC);
/*  76 */             this.msgIdReport = dinsC.readLong();
/*  77 */             byte[] startByte = new byte[7];
/*  78 */             dinsC.read(startByte);
/*  79 */             this.stat = new String(startByte, fmtStr);
/*  80 */             byte[] submit_timeByte = new byte[10];
/*  81 */             dinsC.read(submit_timeByte);
/*  82 */             this.submitTime = new String(submit_timeByte, fmtStr);
/*  83 */             byte[] done_timeByte = new byte[7];
/*  84 */             dinsC.read(done_timeByte);
/*  85 */             this.doneTime = new String(done_timeByte, fmtStr);
/*  86 */             byte[] dest_terminal_IdByte = new byte[21];
/*  87 */             dinsC.read(dest_terminal_IdByte);
/*  88 */             this.destTerminalId = new String(dest_terminal_IdByte, 
/*  89 */               fmtStr);
/*  90 */             this.sMSCSequence = dinsC.readInt();
/*  91 */             dinsC.close();
/*  92 */             binsC.close();
/*  93 */             this.result = 0;
/*     */           } else {
/*  95 */             this.result = 1;
/*     */           }
/*     */         } else {
/*  98 */           this.msgContent = new String(msg_ContentByte, fmtStr);
/*     */         }
/* 100 */         byte[] linkIDByte = new byte[20];
/* 101 */         this.linkID = new String(linkIDByte);
/* 102 */         this.result = 0;
/* 103 */         dins.close();
/* 104 */         bins.close();
/*     */       } catch (IOException e) {
/* 106 */         this.result = 8;
/*     */       }
/*     */     } else {
/* 109 */       this.result = 1;
/* 110 */       logger.info("短信网关CMPP_DELIVER,解析数据包出错，包长度不一致。长度为:" + data.length);
/*     */     }
/*     */   }
/*     */ 
/*     */   public long getMsgId()
/*     */   {
/* 120 */     return this.msgId;
/*     */   }
/*     */ 
/*     */   public void setMsgId(long msgId)
/*     */   {
/* 130 */     this.msgId = msgId;
/*     */   }
/*     */ 
/*     */   public String getDestId()
/*     */   {
/* 139 */     return this.destId;
/*     */   }
/*     */ 
/*     */   public void setDestId(String destId)
/*     */   {
/* 149 */     this.destId = destId;
/*     */   }
/*     */ 
/*     */   public String getServiceId()
/*     */   {
/* 158 */     return this.serviceId;
/*     */   }
/*     */ 
/*     */   public void setServiceId(String serviceId)
/*     */   {
/* 168 */     this.serviceId = serviceId;
/*     */   }
/*     */ 
/*     */   public byte getTPPid()
/*     */   {
/* 177 */     return this.tPPid;
/*     */   }
/*     */ 
/*     */   public void setTPPid(byte tpPid)
/*     */   {
/* 187 */     this.tPPid = tpPid;
/*     */   }
/*     */ 
/*     */   public byte getTPUdhi()
/*     */   {
/* 196 */     return this.tPUdhi;
/*     */   }
/*     */ 
/*     */   public void setTPUdhi(byte tpUdhi)
/*     */   {
/* 206 */     this.tPUdhi = tpUdhi;
/*     */   }
/*     */ 
/*     */   public byte getMsgFmt()
/*     */   {
/* 215 */     return this.msgFmt;
/*     */   }
/*     */ 
/*     */   public void setMsgFmt(byte msgFmt)
/*     */   {
/* 225 */     this.msgFmt = msgFmt;
/*     */   }
/*     */ 
/*     */   public String getSrcTerminalId()
/*     */   {
/* 234 */     return this.srcTerminalId;
/*     */   }
/*     */ 
/*     */   public void setSrcTerminalId(String srcTerminalId)
/*     */   {
/* 244 */     this.srcTerminalId = srcTerminalId;
/*     */   }
/*     */ 
/*     */   public byte getSrcTerminalType()
/*     */   {
/* 253 */     return this.srcTerminalType;
/*     */   }
/*     */ 
/*     */   public void setSrcTerminalType(byte srcTerminalType)
/*     */   {
/* 263 */     this.srcTerminalType = srcTerminalType;
/*     */   }
/*     */ 
/*     */   public byte getRegisteredDelivery()
/*     */   {
/* 272 */     return this.registeredDelivery;
/*     */   }
/*     */ 
/*     */   public void setRegisteredDelivery(byte registeredDelivery)
/*     */   {
/* 282 */     this.registeredDelivery = registeredDelivery;
/*     */   }
/*     */ 
/*     */   public byte getMsgLength()
/*     */   {
/* 291 */     return this.msgLength;
/*     */   }
/*     */ 
/*     */   public void setMsgLength(byte msgLength)
/*     */   {
/* 301 */     this.msgLength = msgLength;
/*     */   }
/*     */ 
/*     */   public String getMsgContent()
/*     */   {
/* 310 */     return this.msgContent;
/*     */   }
/*     */ 
/*     */   public void setMsgContent(String msgContent)
/*     */   {
/* 320 */     this.msgContent = msgContent;
/*     */   }
/*     */ 
/*     */   public String getLinkID()
/*     */   {
/* 329 */     return this.linkID;
/*     */   }
/*     */ 
/*     */   public void setLinkID(String linkID)
/*     */   {
/* 339 */     this.linkID = linkID;
/*     */   }
/*     */ 
/*     */   public long getMsgIdReport()
/*     */   {
/* 348 */     return this.msgIdReport;
/*     */   }
/*     */ 
/*     */   public void setMsgIdReport(long msgIdReport)
/*     */   {
/* 358 */     this.msgIdReport = msgIdReport;
/*     */   }
/*     */ 
/*     */   public String getStat()
/*     */   {
/* 367 */     return this.stat;
/*     */   }
/*     */ 
/*     */   public void setStat(String stat)
/*     */   {
/* 377 */     this.stat = stat;
/*     */   }
/*     */ 
/*     */   public String getSubmitTime()
/*     */   {
/* 386 */     return this.submitTime;
/*     */   }
/*     */ 
/*     */   public void setSubmitTime(String submitTime)
/*     */   {
/* 396 */     this.submitTime = submitTime;
/*     */   }
/*     */ 
/*     */   public String getDoneTime()
/*     */   {
/* 405 */     return this.doneTime;
/*     */   }
/*     */ 
/*     */   public void setDoneTime(String doneTime)
/*     */   {
/* 415 */     this.doneTime = doneTime;
/*     */   }
/*     */ 
/*     */   public String getDestTerminalId()
/*     */   {
/* 424 */     return this.destTerminalId;
/*     */   }
/*     */ 
/*     */   public void setDestTerminalId(String destTerminalId)
/*     */   {
/* 434 */     this.destTerminalId = destTerminalId;
/*     */   }
/*     */ 
/*     */   public int getSMSCSequence()
/*     */   {
/* 443 */     return this.sMSCSequence;
/*     */   }
/*     */ 
/*     */   public void setSMSCSequence(int sMSCSequence)
/*     */   {
/* 453 */     this.sMSCSequence = sMSCSequence;
/*     */   }
/*     */ 
/*     */   public int getResult()
/*     */   {
/* 462 */     return this.result;
/*     */   }
/*     */ 
/*     */   public void setResult(int result)
/*     */   {
/* 472 */     this.result = result;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.util.msg.domain.MsgDeliver
 * JD-Core Version:    0.6.2
 */