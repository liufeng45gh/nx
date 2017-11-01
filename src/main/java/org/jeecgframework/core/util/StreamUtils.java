/*     */ package org.jeecgframework.core.util;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import jodd.io.StringInputStream;
/*     */ 
/*     */ public class StreamUtils
/*     */ {
/*     */   static final int BUFFER_SIZE = 4096;
/*     */ 
/*     */   public static String InputStreamTOString(InputStream in)
/*     */   {
/*  37 */     ByteArrayOutputStream outStream = new ByteArrayOutputStream();
/*  38 */     byte[] data = new byte[4096];
/*  39 */     String string = null;
/*  40 */     int count = 0;
/*     */     try {
/*  42 */       while ((count = in.read(data, 0, 4096)) != -1)
/*  43 */         outStream.write(data, 0, count);
/*     */     } catch (IOException e) {
/*  45 */       e.printStackTrace();
/*     */     }
/*     */ 
/*  48 */     data = null;
/*     */     try {
/*  50 */       string = new String(outStream.toByteArray(), "UTF-8");
/*     */     } catch (UnsupportedEncodingException e) {
/*  52 */       e.printStackTrace();
/*     */     }
/*  54 */     return string;
/*     */   }
/*     */ 
/*     */   public static String InputStreamTOString(InputStream in, String encoding)
/*     */   {
/*  66 */     String string = null;
/*  67 */     ByteArrayOutputStream outStream = new ByteArrayOutputStream();
/*  68 */     byte[] data = new byte[4096];
/*  69 */     int count = -1;
/*     */     try {
/*  71 */       while ((count = in.read(data, 0, 4096)) != -1)
/*  72 */         outStream.write(data, 0, count);
/*     */     } catch (IOException e) {
/*  74 */       e.printStackTrace();
/*     */     }
/*     */ 
/*  77 */     data = null;
/*     */     try {
/*  79 */       string = new String(outStream.toByteArray(), encoding);
/*     */     } catch (UnsupportedEncodingException e) {
/*  81 */       e.printStackTrace();
/*     */     }
/*  83 */     return string;
/*     */   }
/*     */ 
/*     */   public static InputStream StringTOInputStream(String in)
/*     */     throws Exception
/*     */   {
/*  95 */     ByteArrayInputStream is = new ByteArrayInputStream(in.getBytes("UTF-8"));
/*  96 */     return is;
/*     */   }
/*     */ 
/*     */   public static byte[] StringTObyte(String in)
/*     */   {
/* 107 */     byte[] bytes = null;
/*     */     try {
/* 109 */       bytes = InputStreamTOByte(StringTOInputStream(in));
/*     */     } catch (IOException localIOException) {
/*     */     } catch (Exception e) {
/* 112 */       e.printStackTrace();
/*     */     }
/* 114 */     return bytes;
/*     */   }
/*     */ 
/*     */   public static byte[] InputStreamTOByte(InputStream in)
/*     */     throws IOException
/*     */   {
/* 127 */     ByteArrayOutputStream outStream = new ByteArrayOutputStream();
/* 128 */     byte[] data = new byte[4096];
/* 129 */     int count = -1;
/* 130 */     while ((count = in.read(data, 0, 4096)) != -1) {
/* 131 */       outStream.write(data, 0, count);
/*     */     }
/* 133 */     data = null;
/* 134 */     return outStream.toByteArray();
/*     */   }
/*     */ 
/*     */   public static InputStream byteTOFInputStream(byte[] in)
/*     */     throws Exception
/*     */   {
/* 146 */     InputStream is = new StringInputStream(InputStreamTOString(byteTOInputStream(in)));
/* 147 */     return is;
/*     */   }
/*     */ 
/*     */   public static InputStream byteTOInputStream(byte[] in)
/*     */     throws Exception
/*     */   {
/* 158 */     ByteArrayInputStream is = new ByteArrayInputStream(in);
/* 159 */     return is;
/*     */   }
/*     */ 
/*     */   public static String byteTOString(byte[] in)
/*     */   {
/* 171 */     InputStream is = null;
/*     */     try {
/* 173 */       is = byteTOInputStream(in);
/*     */     } catch (Exception e) {
/* 175 */       e.printStackTrace();
/*     */     }
/* 177 */     return InputStreamTOString(is, "UTF-8");
/*     */   }
/*     */ 
/*     */   public static String getString(String in)
/*     */   {
/* 188 */     String is = null;
/*     */     try {
/* 190 */       is = byteTOString(StringTObyte(in));
/*     */     } catch (Exception e) {
/* 192 */       e.printStackTrace();
/*     */     }
/* 194 */     return is;
/*     */   }
/*     */ 
/*     */   public byte[] getBytes(InputStream is)
/*     */     throws IOException
/*     */   {
/* 200 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 201 */     byte[] b = new byte[4096];
/* 202 */     int len = 0;
/*     */ 
/* 204 */     while ((len = is.read(b, 0, 4096)) != -1) {
/* 205 */       baos.write(b, 0, len);
/*     */     }
/*     */ 
/* 208 */     baos.flush();
/*     */ 
/* 210 */     byte[] bytes = baos.toByteArray();
/*     */ 
/* 212 */     LogUtil.info(new String(bytes));
/*     */ 
/* 214 */     return bytes;
/*     */   }
/*     */ 
/*     */   public static FileInputStream getFileInputStream(String filepath)
/*     */   {
/* 223 */     FileInputStream fileInputStream = null;
/*     */     try {
/* 225 */       fileInputStream = new FileInputStream(filepath);
/*     */     } catch (FileNotFoundException e) {
/* 227 */       System.out.print("错误信息:文件不存在");
/* 228 */       e.printStackTrace();
/*     */     }
/* 230 */     return fileInputStream;
/*     */   }
/*     */ 
/*     */   public static FileInputStream getFileInputStream(File file)
/*     */   {
/* 239 */     FileInputStream fileInputStream = null;
/*     */     try {
/* 241 */       fileInputStream = new FileInputStream(file);
/*     */     } catch (FileNotFoundException e) {
/* 243 */       System.out.print("错误信息:文件不存在");
/* 244 */       e.printStackTrace();
/*     */     }
/* 246 */     return fileInputStream;
/*     */   }
/*     */ 
/*     */   public static FileOutputStream getFileOutputStream(File file, boolean append)
/*     */   {
/* 256 */     FileOutputStream fileOutputStream = null;
/*     */     try {
/* 258 */       fileOutputStream = new FileOutputStream(file, append);
/*     */     } catch (FileNotFoundException e) {
/* 260 */       System.out.print("错误信息:文件不存在");
/* 261 */       e.printStackTrace();
/*     */     }
/* 263 */     return fileOutputStream;
/*     */   }
/*     */ 
/*     */   public static FileOutputStream getFileOutputStream(String filepath, boolean append)
/*     */   {
/* 273 */     FileOutputStream fileOutputStream = null;
/*     */     try {
/* 275 */       fileOutputStream = new FileOutputStream(filepath, append);
/*     */     } catch (FileNotFoundException e) {
/* 277 */       System.out.print("错误信息:文件不存在");
/* 278 */       e.printStackTrace();
/*     */     }
/* 280 */     return fileOutputStream;
/*     */   }
/*     */ 
/*     */   public static File getFile(String filepath) {
/* 284 */     return new File(filepath);
/*     */   }
/*     */   public static ByteArrayOutputStream getByteArrayOutputStream() {
/* 287 */     return new ByteArrayOutputStream();
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.StreamUtils
 * JD-Core Version:    0.6.2
 */