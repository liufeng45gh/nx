/*     */ package org.jeecgframework.web.sms.util.msg.util;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.web.sms.util.msg.domain.MsgActiveTestResp;
/*     */ import org.jeecgframework.web.sms.util.msg.domain.MsgConnectResp;
/*     */ import org.jeecgframework.web.sms.util.msg.domain.MsgDeliver;
/*     */ import org.jeecgframework.web.sms.util.msg.domain.MsgDeliverResp;
/*     */ import org.jeecgframework.web.sms.util.msg.domain.MsgHead;
/*     */ import org.jeecgframework.web.sms.util.msg.domain.MsgSubmitResp;
/*     */ 
/*     */ public class CmppSender
/*     */ {
/*  31 */   private static Logger logger = Logger.getLogger(CmppSender.class);
/*  32 */   private List<byte[]> sendData = new ArrayList();
/*  33 */   private List<byte[]> getData = new ArrayList();
/*     */   private DataOutputStream out;
/*     */   private DataInputStream in;
/*     */ 
/*     */   public CmppSender(DataOutputStream out, DataInputStream in, List<byte[]> sendData)
/*     */   {
/*  50 */     this.sendData = sendData;
/*  51 */     this.out = out;
/*  52 */     this.in = in;
/*     */   }
/*     */ 
/*     */   public void start()
/*     */     throws Exception
/*     */   {
/*  62 */     if ((this.out != null) && (this.sendData != null)) {
/*  63 */       for (byte[] data : this.sendData) {
/*  64 */         logger.info("发送的二进制队列里data长度====" + data.length);
/*  65 */         sendMsg(data);
/*  66 */         byte[] returnData = getInData();
/*  67 */         logger.info("发送的二进制队列里响应值的长度====" + returnData.length);
/*  68 */         if (returnData != null) {
/*  69 */           this.getData.add(returnData);
/*     */         }
/*     */       }
/*     */     }
/*  73 */     if ((this.in != null) && (this.getData != null))
/*  74 */       for (byte[] data : this.getData) {
/*  75 */         logger.info("接收的二进制队列里data长度====" + data.length);
/*  76 */         if (data.length >= 8) {
/*  77 */           MsgHead head = new MsgHead(data);
/*  78 */           switch (head.getCommandId()) {
/*     */           case -2147483647:
/*  80 */             logger.info("链接至短信网关之前data长度====" + data.length);
/*  81 */             MsgConnectResp connectResp = new MsgConnectResp(data);
/*  82 */             logger.info("快消平台" + 
/*  83 */               new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
/*  84 */               .format(new Date()) + "链接短信网关,状态:" + 
/*  85 */               connectResp.getStatusStr() + " 序列号：" + 
/*  86 */               connectResp.getSequenceId());
/*  87 */             break;
/*     */           case -2147483640:
/*  89 */             MsgActiveTestResp activeResp = new MsgActiveTestResp(
/*  90 */               data);
/*  91 */             logger.info("快消平台" + 
/*  92 */               new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
/*  93 */               .format(new Date()) + "短信网关与短信网关进行连接检查" + 
/*  94 */               " 序列号：" + activeResp.getSequenceId());
/*  95 */             break;
/*     */           case -2147483644:
/*  97 */             MsgSubmitResp submitResp = new MsgSubmitResp(data);
/*  98 */             logger.info("快消平台" + 
/*  99 */               new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
/* 100 */               .format(new Date()) + "向用户下发短信，状态码:" + 
/* 101 */               submitResp.getResult() + " 序列号：" + 
/* 102 */               submitResp.getSequenceId());
/* 103 */             break;
/*     */           case -2147483646:
/* 105 */             logger.info("快消平台" + 
/* 106 */               new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
/* 107 */               .format(new Date()) + "拆除与ISMG的链接" + 
/* 108 */               " 序列号：" + head.getSequenceId());
/* 109 */             break;
/*     */           case -2147483641:
/* 111 */             logger.info("CMPP_CANCEL_RESP 序列号：" + 
/* 112 */               head.getSequenceId());
/* 113 */             break;
/*     */           case 7:
/* 115 */             logger.info("CMPP_CANCEL 序列号：" + head.getSequenceId());
/* 116 */             break;
/*     */           case 5:
/* 118 */             MsgDeliver msgDeliver = new MsgDeliver(data);
/* 119 */             if (msgDeliver.getResult() == 0) {
/* 120 */               logger.info("CMPP_DELIVER 序列号：" + 
/* 121 */                 head.getSequenceId() + 
/* 122 */                 "，是否消息回复" + (
/* 123 */                 msgDeliver.getRegisteredDelivery() == 0 ? "不是,消息内容：" + 
/* 124 */                 msgDeliver.getMsgContent() : 
/* 125 */                 new StringBuilder("是，目的手机号：")
/* 126 */                 .append(msgDeliver
/* 127 */                 .getDestTerminalId()).toString()));
/*     */             }
/*     */             else
/*     */             {
/* 129 */               logger.info("CMPP_DELIVER 序列号：" + 
/* 130 */                 head.getSequenceId());
/*     */             }
/* 132 */             MsgDeliverResp msgDeliverResp = new MsgDeliverResp();
/* 133 */             msgDeliverResp.setTotalLength(24);
/* 134 */             msgDeliverResp
/* 135 */               .setCommandId(-2147483643);
/* 136 */             msgDeliverResp.setSequenceId(MsgUtils.getSequence());
/* 137 */             msgDeliverResp.setMsgId(msgDeliver.getMsgId());
/* 138 */             msgDeliverResp.setResult(msgDeliver.getResult());
/* 139 */             sendMsg(msgDeliverResp.toByteArry());
/* 140 */             break;
/*     */           case -2147483643:
/* 142 */             logger.info("CMPP_DELIVER_RESP 序列号：" + 
/* 143 */               head.getSequenceId());
/* 144 */             break;
/*     */           case 6:
/* 146 */             logger.info("CMPP_QUERY 序列号：" + head.getSequenceId());
/* 147 */             break;
/*     */           case -2147483642:
/* 149 */             logger.info("CMPP_QUERY_RESP 序列号：" + 
/* 150 */               head.getSequenceId());
/* 151 */             break;
/*     */           case 2:
/* 153 */             logger.info("CMPP_TERMINATE 序列号：" + 
/* 154 */               head.getSequenceId());
/* 155 */             break;
/*     */           case 1:
/* 157 */             logger.info("CMPP_CONNECT 序列号：" + head.getSequenceId());
/* 158 */             break;
/*     */           case 8:
/* 160 */             logger.info("CMPP_ACTIVE_TEST 序列号：" + 
/* 161 */               head.getSequenceId());
/* 162 */             break;
/*     */           case 4:
/* 164 */             logger.info("CMPP_SUBMIT 序列号：" + head.getSequenceId());
/* 165 */             break;
/*     */           default:
/* 167 */             logger.error("无法解析IMSP返回的包结构：包长度为" + 
/* 168 */               head.getTotalLength());
/*     */           }
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   public List<byte[]> getGetData()
/*     */   {
/* 182 */     return this.getData;
/*     */   }
/*     */ 
/*     */   private boolean sendMsg(byte[] data)
/*     */     throws Exception
/*     */   {
/*     */     try
/*     */     {
/* 196 */       this.out.write(data);
/* 197 */       this.out.flush();
/* 198 */       return true;
/*     */     } catch (NullPointerException ef) {
/* 200 */       logger.error("在本连结上发送已打包后的消息的字节:无字节输入");
/*     */     }
/* 202 */     return false;
/*     */   }
/*     */ 
/*     */   private byte[] getInData()
/*     */     throws IOException
/*     */   {
/*     */     try
/*     */     {
/* 212 */       int len = this.in.readInt();
/* 213 */       logger.info("输入的流里读取的len==" + len);
/* 214 */       if ((this.in != null) && (len != 0)) {
/* 215 */         byte[] data = new byte[len - 4];
/* 216 */         this.in.read(data);
/* 217 */         return data;
/*     */       }
/* 219 */       return null;
/*     */     }
/*     */     catch (NullPointerException ef) {
/* 222 */       logger.error("在本连结上接受字节消息:无流输入");
/* 223 */       return null;
/*     */     } catch (EOFException eof) {
/* 225 */       logger.error("在本连结上接受字节消息:" + eof.getMessage());
/* 226 */     }return null;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.util.msg.util.CmppSender
 * JD-Core Version:    0.6.2
 */