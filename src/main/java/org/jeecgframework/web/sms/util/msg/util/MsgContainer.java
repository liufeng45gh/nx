/*     */ package org.jeecgframework.web.sms.util.msg.util;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.Socket;
/*     */ import java.net.UnknownHostException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.web.sms.util.msg.domain.MsgConnect;
/*     */ import org.jeecgframework.web.sms.util.msg.domain.MsgHead;
/*     */ import org.jeecgframework.web.sms.util.msg.domain.MsgSubmit;
/*     */ 
/*     */ public class MsgContainer
/*     */ {
/*  28 */   private static Logger logger = Logger.getLogger(MsgContainer.class);
/*     */   private static Socket msgSocket;
/*     */   private static DataInputStream in;
/*     */   private static DataOutputStream out;
/*     */ 
/*     */   public static DataInputStream getSocketDIS()
/*     */   {
/*  39 */     if ((in == null) || (msgSocket == null) || (msgSocket.isClosed()) || 
/*  40 */       (!msgSocket.isConnected())) {
/*     */       try {
/*  42 */         in = new DataInputStream(getSocketInstance()
/*  43 */           .getInputStream());
/*     */       } catch (IOException e) {
/*  45 */         in = null;
/*     */       }
/*     */     }
/*  48 */     return in;
/*     */   }
/*     */ 
/*     */   public static DataOutputStream getSocketDOS()
/*     */   {
/*  57 */     if ((out == null) || (msgSocket == null) || (msgSocket.isClosed()) || 
/*  58 */       (!msgSocket.isConnected())) {
/*     */       try {
/*  60 */         out = new DataOutputStream(getSocketInstance()
/*  61 */           .getOutputStream());
/*     */       } catch (IOException e) {
/*  63 */         out = null;
/*     */       }
/*     */     }
/*  66 */     return out;
/*     */   }
/*     */ 
/*     */   public static Socket getSocketInstance()
/*     */   {
/*  75 */     if ((msgSocket == null) || (msgSocket.isClosed()) || 
/*  76 */       (!msgSocket.isConnected())) {
/*     */       try {
/*  78 */         in = null;
/*  79 */         out = null;
/*  80 */         msgSocket = new Socket(MsgConfig.getIsmgIp(), 
/*  81 */           MsgConfig.getIsmgPort());
/*  82 */         msgSocket.setKeepAlive(true);
/*  83 */         in = getSocketDIS();
/*  84 */         out = getSocketDOS();
/*  85 */         int count = 0;
/*  86 */         boolean result = connectISMG();
/*  87 */         logger.info("result" + result);
/*  88 */         while (!result) {
/*  89 */           count++;
/*  90 */           result = connectISMG();
/*  91 */           if (count >= MsgConfig.getConnectCount() - 1)
/*     */             break;
/*     */         }
/*     */       }
/*     */       catch (UnknownHostException e) {
/*  96 */         logger.error("Socket链接短信网关端口号不正确：" + e.getMessage());
/*     */       }
/*     */       catch (IOException e) {
/*  99 */         logger.error("Socket链接短信网关失败：" + e.getMessage());
/*     */       }
/*     */     }
/* 102 */     return msgSocket;
/*     */   }
/*     */ 
/*     */   private static boolean connectISMG()
/*     */   {
/* 111 */     MsgConnect connect = new MsgConnect();
/* 112 */     connect.setTotalLength(39);
/* 113 */     connect.setCommandId(1);
/* 114 */     connect.setSequenceId(MsgUtils.getSequence());
/* 115 */     connect.setSourceAddr(MsgConfig.getSpId());
/* 116 */     connect.setAuthenticatorSource(MsgUtils.getAuthenticatorSource(
/* 117 */       MsgConfig.getSpId(), MsgConfig.getSpSharedSecret()));
/* 118 */     connect.setTimestamp(Integer.parseInt(MsgUtils.getTimestamp()));
/*     */ 
/* 120 */     connect.setVersion((byte)48);
/* 121 */     logger.error("消息长度:" + connect.getTotalLength());
/* 122 */     logger.error("标识:" + connect.getCommandId());
/* 123 */     logger.error("序列:" + connect.getSequenceId());
/* 124 */     logger.error("企业id:" + connect.getSourceAddr());
/* 125 */     logger.error("md5:" + connect.getAuthenticatorSource().length);
/* 126 */     logger.error("时间戳:" + connect.getTimestamp());
/* 127 */     logger.error("版本号:" + connect.getVersion());
/* 128 */     List dataList = new ArrayList();
/* 129 */     dataList.add(connect.toByteArry());
/* 130 */     CmppSender sender = new CmppSender(getSocketDOS(), getSocketDIS(), 
/* 131 */       dataList);
/*     */     try {
/* 133 */       sender.start();
/* 134 */       return true;
/*     */     } catch (Exception e) {
/*     */       try {
/* 137 */         out.close();
/*     */       } catch (IOException e1) {
/* 139 */         out = null;
/*     */       }
/*     */     }
/* 141 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sendMsg(String msg, String cusMsisdn)
/*     */   {
/*     */     try
/*     */     {
/* 156 */       if (msg.getBytes("utf-8").length < 140) {
/* 157 */         boolean result = sendShortMsg(msg, cusMsisdn);
/* 158 */         int count = 0;
/* 159 */         while (!result) {
/* 160 */           count++;
/* 161 */           result = sendShortMsg(msg, cusMsisdn);
/* 162 */           if (count >= MsgConfig.getConnectCount() - 1) {
/*     */             break;
/*     */           }
/*     */         }
/* 166 */         return result;
/*     */       }
/* 168 */       boolean result = sendLongMsg(msg, cusMsisdn);
/* 169 */       int count = 0;
/* 170 */       while (!result) {
/* 171 */         count++;
/* 172 */         result = sendLongMsg(msg, cusMsisdn);
/* 173 */         if (count >= MsgConfig.getConnectCount() - 1) {
/*     */           break;
/*     */         }
/*     */       }
/* 177 */       return result;
/*     */     }
/*     */     catch (Exception e) {
/*     */       try {
/* 181 */         out.close();
/*     */       } catch (IOException e1) {
/* 183 */         out = null;
/*     */       }
/*     */     }
/* 185 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sendWapPushMsg(String url, String desc, String cusMsisdn)
/*     */   {
/*     */     try
/*     */     {
/* 203 */       int msgContent = 30 + url.getBytes("utf-8").length + 3 + 
/* 204 */         desc.getBytes("utf-8").length + 3;
/* 205 */       if (msgContent < 140) {
/* 206 */         boolean result = sendShortWapPushMsg(url, desc, cusMsisdn);
/* 207 */         int count = 0;
/* 208 */         while (!result) {
/* 209 */           count++;
/* 210 */           result = sendShortWapPushMsg(url, desc, cusMsisdn);
/* 211 */           if (count >= MsgConfig.getConnectCount() - 1) {
/*     */             break;
/*     */           }
/*     */         }
/* 215 */         return result;
/*     */       }
/* 217 */       boolean result = sendLongWapPushMsg(url, desc, cusMsisdn);
/* 218 */       int count = 0;
/* 219 */       while (!result) {
/* 220 */         count++;
/* 221 */         result = sendLongWapPushMsg(url, desc, cusMsisdn);
/* 222 */         if (count >= MsgConfig.getConnectCount() - 1) {
/*     */           break;
/*     */         }
/*     */       }
/* 226 */       return result;
/*     */     }
/*     */     catch (Exception e) {
/*     */       try {
/* 230 */         out.close();
/*     */       } catch (IOException e1) {
/* 232 */         out = null;
/*     */       }
/* 234 */       logger.error("发送web push短信:" + e.getMessage());
/* 235 */     }return false;
/*     */   }
/*     */ 
/*     */   private static boolean sendShortMsg(String msg, String cusMsisdn)
/*     */   {
/*     */     try
/*     */     {
/* 250 */       int seq = MsgUtils.getSequence();
/* 251 */       MsgSubmit submit = new MsgSubmit();
/* 252 */       submit.setTotalLength(175 + 
/* 254 */         msg.length() * 2 + 20);
/* 255 */       submit.setCommandId(4);
/* 256 */       submit.setSequenceId(seq);
/* 257 */       submit.setPkTotal((byte)1);
/* 258 */       submit.setPkNumber((byte)1);
/* 259 */       submit.setRegisteredDelivery((byte)0);
/* 260 */       submit.setMsgLevel((byte)1);
/* 261 */       submit.setFeeUserType((byte)0);
/* 262 */       submit.setFeeTerminalId("");
/* 263 */       submit.setFeeTerminalType((byte)0);
/* 264 */       submit.setTpPId((byte)0);
/* 265 */       submit.setTpUdhi((byte)0);
/* 266 */       submit.setMsgFmt((byte)15);
/* 267 */       submit.setMsgSrc(MsgConfig.getSpId());
/* 268 */       submit.setSrcId(MsgConfig.getSpCode());
/* 269 */       submit.setDestTerminalId(cusMsisdn);
/* 270 */       submit.setMsgLength((byte)(msg.length() * 2));
/* 271 */       submit.setMsgContent(msg.getBytes("gb2312"));
/*     */ 
/* 273 */       List dataList = new ArrayList();
/* 274 */       dataList.add(submit.toByteArry());
/* 275 */       CmppSender sender = new CmppSender(getSocketDOS(), getSocketDIS(), 
/* 276 */         dataList);
/* 277 */       sender.start();
/* 278 */       logger.info("数据乐园于" + 
/* 279 */         new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
/* 280 */         .format(new Date()) + "向" + cusMsisdn + 
/* 281 */         "下发短短信，序列号为:" + seq);
/* 282 */       return true;
/*     */     } catch (Exception e) {
/*     */       try {
/* 285 */         out.close();
/*     */       } catch (IOException e1) {
/* 287 */         out = null;
/*     */       }
/* 289 */       logger.error("发送短短信" + e.getMessage());
/* 290 */     }return false;
/*     */   }
/*     */ 
/*     */   private static boolean sendLongMsg(String msg, String cusMsisdn)
/*     */   {
/*     */     try
/*     */     {
/* 305 */       byte[] allByte = msg.getBytes("UTF-16BE");
/* 306 */       List dataList = new ArrayList();
/* 307 */       int msgLength = allByte.length;
/* 308 */       int maxLength = 140;
/* 309 */       int msgSendCount = msgLength % (maxLength - 6) == 0 ? msgLength / (
/* 310 */         maxLength - 6) : msgLength / (maxLength - 6) + 1;
/*     */ 
/* 312 */       byte[] msgHead = new byte[6];
/* 313 */       msgHead[0] = 5;
/* 314 */       msgHead[1] = 0;
/* 315 */       msgHead[2] = 3;
/* 316 */       msgHead[3] = ((byte)MsgUtils.getSequence());
/* 317 */       msgHead[4] = ((byte)msgSendCount);
/* 318 */       msgHead[5] = 1;
/* 319 */       int seqId = MsgUtils.getSequence();
/* 320 */       for (int i = 0; i < msgSendCount; i++)
/*     */       {
/* 322 */         msgHead[5] = ((byte)(i + 1));
/* 323 */         byte[] needMsg = null;
/*     */ 
/* 325 */         if (i != msgSendCount - 1) {
/* 326 */           int start = (maxLength - 6) * i;
/* 327 */           int end = (maxLength - 6) * (i + 1);
/* 328 */           needMsg = MsgUtils.getMsgBytes(allByte, start, end);
/*     */         } else {
/* 330 */           int start = (maxLength - 6) * i;
/* 331 */           int end = allByte.length;
/* 332 */           needMsg = MsgUtils.getMsgBytes(allByte, start, end);
/*     */         }
/* 334 */         int subLength = needMsg.length + msgHead.length;
/* 335 */         byte[] sendMsg = new byte[needMsg.length + msgHead.length];
/* 336 */         System.arraycopy(msgHead, 0, sendMsg, 0, 6);
/* 337 */         System.arraycopy(needMsg, 0, sendMsg, 6, needMsg.length);
/* 338 */         MsgSubmit submit = new MsgSubmit();
/* 339 */         submit.setTotalLength(175 + 
/* 341 */           subLength + 20);
/* 342 */         submit.setCommandId(4);
/* 343 */         submit.setSequenceId(seqId);
/* 344 */         submit.setPkTotal((byte)msgSendCount);
/* 345 */         submit.setPkNumber((byte)(i + 1));
/* 346 */         submit.setRegisteredDelivery((byte)0);
/* 347 */         submit.setMsgLevel((byte)1);
/* 348 */         submit.setFeeUserType((byte)0);
/* 349 */         submit.setFeeTerminalId("");
/* 350 */         submit.setFeeTerminalType((byte)0);
/* 351 */         submit.setTpPId((byte)0);
/* 352 */         submit.setTpUdhi((byte)1);
/* 353 */         submit.setMsgFmt((byte)8);
/* 354 */         submit.setMsgSrc(MsgConfig.getSpId());
/* 355 */         submit.setSrcId(MsgConfig.getSpCode());
/* 356 */         submit.setDestTerminalId(cusMsisdn);
/* 357 */         submit.setMsgLength((byte)subLength);
/* 358 */         submit.setMsgContent(sendMsg);
/* 359 */         dataList.add(submit.toByteArry());
/*     */       }
/* 361 */       CmppSender sender = new CmppSender(getSocketDOS(), getSocketDIS(), 
/* 362 */         dataList);
/* 363 */       sender.start();
/* 364 */       logger.info("数据乐园于" + 
/* 365 */         new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
/* 366 */         .format(new Date()) + "向" + cusMsisdn + 
/* 367 */         "下发长短信，序列号为:" + seqId);
/* 368 */       return true;
/*     */     } catch (Exception e) {
/*     */       try {
/* 371 */         out.close();
/*     */       } catch (IOException e1) {
/* 373 */         out = null;
/*     */       }
/* 375 */       logger.error("发送长短信" + e.getMessage());
/* 376 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean cancelISMG()
/*     */   {
/*     */     try
/*     */     {
/* 387 */       MsgHead head = new MsgHead();
/* 388 */       head.setTotalLength(12);
/* 389 */       head.setCommandId(2);
/* 390 */       head.setSequenceId(MsgUtils.getSequence());
/*     */ 
/* 392 */       List dataList = new ArrayList();
/* 393 */       dataList.add(head.toByteArry());
/* 394 */       CmppSender sender = new CmppSender(getSocketDOS(), getSocketDIS(), 
/* 395 */         dataList);
/* 396 */       sender.start();
/* 397 */       getSocketInstance().close();
/* 398 */       out.close();
/* 399 */       in.close();
/* 400 */       return true;
/*     */     } catch (Exception e) {
/*     */       try {
/* 403 */         out.close();
/* 404 */         in.close();
/*     */       } catch (IOException e1) {
/* 406 */         in = null;
/* 407 */         out = null;
/*     */       }
/* 409 */       logger.error("拆除与ISMG的链接" + e.getMessage());
/* 410 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean activityTestISMG()
/*     */   {
/*     */     try
/*     */     {
/* 421 */       logger.info("activityTestISMG================start");
/* 422 */       MsgHead head = new MsgHead();
/* 423 */       head.setTotalLength(12);
/* 424 */       head.setCommandId(8);
/* 425 */       head.setSequenceId(MsgUtils.getSequence());
/* 426 */       List dataList = new ArrayList();
/* 427 */       dataList.add(head.toByteArry());
/* 428 */       CmppSender sender = new CmppSender(getSocketDOS(), getSocketDIS(), 
/* 429 */         dataList);
/* 430 */       sender.start();
/* 431 */       logger.info("activityTestISMG================end");
/* 432 */       return true;
/*     */     } catch (Exception e) {
/*     */       try {
/* 435 */         out.close();
/* 436 */         logger.info("activityTestISMG================end");
/*     */       } catch (IOException e1) {
/* 438 */         out = null;
/*     */       }
/* 440 */       logger.error("链路检查" + e.getMessage());
/* 441 */     }return false;
/*     */   }
/*     */ 
/*     */   private static boolean sendShortWapPushMsg(String url, String desc, String cusMsisdn)
/*     */   {
/*     */     try
/*     */     {
/* 460 */       byte[] szWapPushHeader1 = { 11, 5, 4, 11, -124, 
/* 461 */         35, -16, 0, 3, 3, 1, 1 };
/*     */ 
/* 463 */       byte[] szWapPushHeader2 = { 41, 6, 6, 3, -82, 
/* 464 */         -127, -22, -115, -54 };
/*     */ 
/* 466 */       byte[] szWapPushIndicator = { 2, 5, 106, 0, 69, 
/* 467 */         -58, 8, 12, 3 };
/*     */ 
/* 469 */       byte[] szWapPushUrl = url.getBytes("utf-8");
/*     */ 
/* 471 */       byte[] szWapPushDisplayTextHeader = { 0, 1, 3 };
/*     */ 
/* 473 */       byte[] szMsg = desc.getBytes("utf-8");
/*     */ 
/* 475 */       byte[] szEndOfWapPush = { 0, 1, 1 };
/* 476 */       int msgLength = 30 + szWapPushUrl.length + 3 + szMsg.length + 
/* 477 */         3;
/* 478 */       int seq = MsgUtils.getSequence();
/* 479 */       MsgSubmit submit = new MsgSubmit();
/* 480 */       submit.setTotalLength(175 + 
/* 482 */         msgLength + 20);
/* 483 */       submit.setCommandId(4);
/* 484 */       submit.setSequenceId(seq);
/* 485 */       submit.setPkTotal((byte)1);
/* 486 */       submit.setPkNumber((byte)1);
/* 487 */       submit.setRegisteredDelivery((byte)0);
/* 488 */       submit.setMsgLevel((byte)1);
/* 489 */       submit.setFeeUserType((byte)0);
/* 490 */       submit.setFeeTerminalId("");
/* 491 */       submit.setFeeTerminalType((byte)0);
/* 492 */       submit.setTpPId((byte)0);
/* 493 */       submit.setTpUdhi((byte)1);
/* 494 */       submit.setMsgFmt((byte)4);
/* 495 */       submit.setMsgSrc(MsgConfig.getSpId());
/* 496 */       submit.setSrcId(MsgConfig.getSpCode());
/* 497 */       submit.setDestTerminalId(cusMsisdn);
/* 498 */       submit.setMsgLength((byte)msgLength);
/* 499 */       byte[] sendMsg = new byte[30 + szWapPushUrl.length + 3 + 
/* 500 */         szMsg.length + 3];
/* 501 */       System.arraycopy(szWapPushHeader1, 0, sendMsg, 0, 12);
/* 502 */       System.arraycopy(szWapPushHeader2, 0, sendMsg, 12, 9);
/* 503 */       System.arraycopy(szWapPushIndicator, 0, sendMsg, 21, 9);
/* 504 */       System.arraycopy(szWapPushUrl, 0, sendMsg, 30, 
/* 505 */         szWapPushUrl.length);
/* 506 */       System.arraycopy(szWapPushDisplayTextHeader, 0, sendMsg, 
/* 507 */         30 + szWapPushUrl.length, 3);
/* 508 */       System.arraycopy(szMsg, 0, sendMsg, 30 + 
/* 509 */         szWapPushUrl.length + 3, szMsg.length);
/* 510 */       System.arraycopy(szEndOfWapPush, 0, sendMsg, 30 + 
/* 511 */         szWapPushUrl.length + 3 + szMsg.length, 3);
/* 512 */       submit.setMsgContent(sendMsg);
/* 513 */       List dataList = new ArrayList();
/* 514 */       dataList.add(submit.toByteArry());
/* 515 */       CmppSender sender = new CmppSender(getSocketDOS(), getSocketDIS(), 
/* 516 */         dataList);
/* 517 */       sender.start();
/* 518 */       logger.info("数据乐园于" + 
/* 519 */         new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
/* 520 */         .format(new Date()) + "向" + cusMsisdn + 
/* 521 */         "下发web push短短信，序列号为:" + seq);
/* 522 */       return true;
/*     */     } catch (Exception e) {
/*     */       try {
/* 525 */         out.close();
/*     */       } catch (IOException e1) {
/* 527 */         out = null;
/*     */       }
/* 529 */       logger.error("发送web push短短信" + e.getMessage());
/* 530 */     }return false;
/*     */   }
/*     */ 
/*     */   private static boolean sendLongWapPushMsg(String url, String desc, String cusMsisdn)
/*     */   {
/*     */     try
/*     */     {
/* 548 */       List dataList = new ArrayList();
/*     */ 
/* 550 */       byte[] wdp = { 11, 5, 4, 11, -124, 35, 
/* 551 */         -16, 0, 3, 3, 1, 1 };
/*     */ 
/* 554 */       byte[] wsp = { 41, 6, 6, 3, -82, -127, 
/* 555 */         -22, -115, -54 };
/*     */ 
/* 557 */       byte[] szWapPushIndicator = { 2, 5, 106, 0, 69, 
/* 558 */         -58, 8, 12, 3 };
/*     */ 
/* 560 */       byte[] szWapPushUrl = url.getBytes("utf-8");
/*     */ 
/* 562 */       byte[] szWapPushDisplayTextHeader = { 0, 1, 3 };
/*     */ 
/* 564 */       byte[] szMsg = desc.getBytes("utf-8");
/*     */ 
/* 566 */       byte[] szEndOfWapPush = { 0, 1, 1 };
/* 567 */       byte[] allByte = new byte[18 + szWapPushUrl.length + 3 + 
/* 568 */         szMsg.length + 3];
/*     */ 
/* 570 */       System.arraycopy(wsp, 0, allByte, 0, 9);
/* 571 */       System.arraycopy(szWapPushIndicator, 0, allByte, 9, 9);
/* 572 */       System.arraycopy(szWapPushUrl, 0, allByte, 18, szWapPushUrl.length);
/* 573 */       System.arraycopy(szWapPushDisplayTextHeader, 0, allByte, 
/* 574 */         18 + szWapPushUrl.length, 3);
/* 575 */       System.arraycopy(szMsg, 0, allByte, 18 + szWapPushUrl.length + 3, 
/* 576 */         szMsg.length);
/* 577 */       System.arraycopy(szEndOfWapPush, 0, allByte, 18 + 
/* 578 */         szWapPushUrl.length + 3 + szMsg.length, 3);
/* 579 */       int msgMax = 140;
/* 580 */       int msgCount = allByte.length % (msgMax - wdp.length) == 0 ? allByte.length / (
/* 581 */         msgMax - wdp.length) : 
/* 582 */         allByte.length / (msgMax - wdp.length) + 1;
/* 583 */       wdp[10] = ((byte)msgCount);
/* 584 */       int seqId = MsgUtils.getSequence();
/* 585 */       for (int i = 0; i < msgCount; i++) {
/* 586 */         wdp[11] = ((byte)(i + 1));
/* 587 */         byte[] needMsg = null;
/*     */ 
/* 589 */         if (i != msgCount - 1) {
/* 590 */           int start = (msgMax - wdp.length) * i;
/* 591 */           int end = (msgMax - wdp.length) * (i + 1);
/* 592 */           needMsg = MsgUtils.getMsgBytes(allByte, start, end);
/*     */         } else {
/* 594 */           int start = (msgMax - wdp.length) * i;
/* 595 */           int end = allByte.length;
/* 596 */           needMsg = MsgUtils.getMsgBytes(allByte, start, end);
/*     */         }
/* 598 */         int msgLength = needMsg.length + wdp.length;
/* 599 */         MsgSubmit submit = new MsgSubmit();
/* 600 */         submit.setTotalLength(175 + 
/* 602 */           msgLength + 20);
/* 603 */         submit.setCommandId(4);
/* 604 */         submit.setSequenceId(seqId);
/* 605 */         submit.setPkTotal((byte)msgCount);
/* 606 */         submit.setPkNumber((byte)(i + 1));
/* 607 */         submit.setRegisteredDelivery((byte)0);
/* 608 */         submit.setMsgLevel((byte)1);
/* 609 */         submit.setFeeUserType((byte)0);
/* 610 */         submit.setFeeTerminalId("");
/* 611 */         submit.setFeeTerminalType((byte)0);
/* 612 */         submit.setTpPId((byte)0);
/* 613 */         submit.setTpUdhi((byte)1);
/* 614 */         submit.setMsgFmt((byte)4);
/* 615 */         submit.setMsgSrc(MsgConfig.getSpId());
/* 616 */         submit.setSrcId(MsgConfig.getSpCode());
/* 617 */         submit.setDestTerminalId(cusMsisdn);
/* 618 */         submit.setMsgLength((byte)msgLength);
/* 619 */         byte[] sendMsg = new byte[wdp.length + needMsg.length];
/* 620 */         System.arraycopy(wdp, 0, sendMsg, 0, wdp.length);
/* 621 */         System.arraycopy(needMsg, 0, sendMsg, wdp.length, 
/* 622 */           needMsg.length);
/* 623 */         submit.setMsgContent(sendMsg);
/* 624 */         dataList.add(submit.toByteArry());
/*     */       }
/* 626 */       CmppSender sender = new CmppSender(getSocketDOS(), getSocketDIS(), 
/* 627 */         dataList);
/* 628 */       sender.start();
/* 629 */       logger.info("数据乐园于" + 
/* 630 */         new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
/* 631 */         .format(new Date()) + "向" + cusMsisdn + 
/* 632 */         "下发web pus长短信，序列号为:" + seqId);
/* 633 */       return true;
/*     */     } catch (Exception e) {
/*     */       try {
/* 636 */         out.close();
/*     */       } catch (IOException e1) {
/* 638 */         out = null;
/*     */       }
/* 640 */       logger.error("发送web push长短信" + e.getMessage());
/* 641 */     }return false;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.util.msg.util.MsgContainer
 * JD-Core Version:    0.6.2
 */