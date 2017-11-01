/*     */ package org.jeecgframework.web.system.servlet;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.util.Random;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.jeecgframework.core.util.ResourceUtil;
/*     */ 
/*     */ public class RandCodeImageServlet extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = -1257947018545327308L;
/*     */   private static final String SESSION_KEY_OF_RAND_CODE = "randCode";
/*     */   private static final int count = 200;
/*     */   private static final int width = 105;
/*     */   private static final int height = 35;
/*     */   private static final int lineWidth = 2;
/*     */ 
/*     */   public void doGet(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/*  56 */     response.setHeader("Pragma", "No-cache");
/*  57 */     response.setHeader("Cache-Control", "no-cache");
/*  58 */     response.setDateHeader("Expires", 0L);
/*     */ 
/*  62 */     BufferedImage image = new BufferedImage(105, 35, 1);
/*     */ 
/*  64 */     Graphics2D graphics = (Graphics2D)image.getGraphics();
/*     */ 
/*  67 */     graphics.setColor(Color.WHITE);
/*  68 */     graphics.fillRect(0, 0, 105, 35);
/*     */ 
/*  71 */     graphics.drawRect(0, 0, 104, 34);
/*     */ 
/*  73 */     Random random = new Random();
/*     */ 
/*  75 */     for (int i = 0; i < 200; i++) {
/*  76 */       graphics.setColor(getRandColor(150, 200));
/*     */ 
/*  78 */       int x = random.nextInt(102) + 1;
/*  79 */       int y = random.nextInt(32) + 1;
/*  80 */       int xl = random.nextInt(2);
/*  81 */       int yl = random.nextInt(2);
/*  82 */       graphics.drawLine(x, y, x + xl, y + yl);
/*     */     }
/*     */ 
/*  86 */     String resultCode = exctractRandCode();
/*  87 */     for (int i = 0; i < resultCode.length(); i++)
/*     */     {
/*  93 */       graphics.setColor(Color.BLACK);
/*     */ 
/*  96 */       graphics.setFont(new Font("Times New Roman", 1, 24));
/*     */ 
/*  98 */       graphics.drawString(String.valueOf(resultCode.charAt(i)), 23 * i + 8, 26);
/*     */     }
/*     */ 
/* 102 */     request.getSession().setAttribute("randCode", resultCode);
/*     */ 
/* 104 */     graphics.dispose();
/*     */ 
/* 107 */     ImageIO.write(image, "JPEG", response.getOutputStream());
/*     */   }
/*     */ 
/*     */   public void doPost(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/* 114 */     doGet(request, response);
/*     */   }
/*     */ 
/*     */   private String exctractRandCode()
/*     */   {
/* 121 */     String randCodeType = ResourceUtil.getRandCodeType();
/* 122 */     int randCodeLength = Integer.parseInt(ResourceUtil.getRandCodeLength());
/* 123 */     if (randCodeType == null) {
/* 124 */       return RandCodeImageEnum.NUMBER_CHAR.generateStr(randCodeLength);
/*     */     }
/* 126 */     switch (randCodeType.charAt(0)) {
/*     */     case '1':
/* 128 */       return RandCodeImageEnum.NUMBER_CHAR.generateStr(randCodeLength);
/*     */     case '2':
/* 130 */       return RandCodeImageEnum.LOWER_CHAR.generateStr(randCodeLength);
/*     */     case '3':
/* 132 */       return RandCodeImageEnum.UPPER_CHAR.generateStr(randCodeLength);
/*     */     case '4':
/* 134 */       return RandCodeImageEnum.LETTER_CHAR.generateStr(randCodeLength);
/*     */     case '5':
/* 136 */       return RandCodeImageEnum.ALL_CHAR.generateStr(randCodeLength);
/*     */     }
/*     */ 
/* 139 */     return RandCodeImageEnum.NUMBER_CHAR.generateStr(randCodeLength);
/*     */   }
/*     */ 
/*     */   private Color getRandColor(int fc, int bc)
/*     */   {
/* 155 */     Random random = new Random();
/* 156 */     if (fc > 255) {
/* 157 */       fc = 255;
/*     */     }
/* 159 */     if (bc > 255) {
/* 160 */       bc = 255;
/*     */     }
/*     */ 
/* 163 */     int r = fc + random.nextInt(bc - fc);
/* 164 */     int g = fc + random.nextInt(bc - fc);
/* 165 */     int b = fc + random.nextInt(bc - fc);
/*     */ 
/* 167 */     return new Color(r, g, b);
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.servlet.RandCodeImageServlet
 * JD-Core Version:    0.6.2
 */