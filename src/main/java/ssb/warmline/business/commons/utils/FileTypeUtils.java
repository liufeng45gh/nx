/*     */ package ssb.warmline.business.commons.utils;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class FileTypeUtils
/*     */ {
/*  21 */   private static Logger log = Logger.getLogger(FileTypeUtils.class);
/*     */ 
/*  23 */   public static final Map<String, String> FILE_TYPE_MAP = new HashMap();
/*     */ 
/*     */   static
/*     */   {
/*  28 */     getAllFileType();
/*     */   }
/*     */ 
/*     */   private static void getAllFileType()
/*     */   {
/*  40 */     FILE_TYPE_MAP.put("jpg", "FFD8FF");
/*  41 */     FILE_TYPE_MAP.put("png", "89504E47");
/*  42 */     FILE_TYPE_MAP.put("gif", "47494638");
/*  43 */     FILE_TYPE_MAP.put("tif", "49492A00");
/*  44 */     FILE_TYPE_MAP.put("bmp", "424D");
/*  45 */     FILE_TYPE_MAP.put("dwg", "41433130");
/*  46 */     FILE_TYPE_MAP.put("html", "68746D6C3E");
/*  47 */     FILE_TYPE_MAP.put("rtf", "7B5C727466");
/*  48 */     FILE_TYPE_MAP.put("xml", "3C3F786D6C");
/*  49 */     FILE_TYPE_MAP.put("zip", "504B0304");
/*  50 */     FILE_TYPE_MAP.put("rar", "52617221");
/*  51 */     FILE_TYPE_MAP.put("psd", "38425053");
/*  52 */     FILE_TYPE_MAP.put("eml", "44656C69766572792D646174653A");
/*     */ 
/*  56 */     FILE_TYPE_MAP.put("dbx", "CFAD12FEC5FD746F");
/*  57 */     FILE_TYPE_MAP.put("pst", "2142444E");
/*  58 */     FILE_TYPE_MAP.put("xls", "D0CF11E0");
/*  59 */     FILE_TYPE_MAP.put("doc", "D0CF11E0");
/*  60 */     FILE_TYPE_MAP.put("mdb", "5374616E64617264204A");
/*  61 */     FILE_TYPE_MAP.put("wpd", "FF575043");
/*  62 */     FILE_TYPE_MAP.put("eps", "252150532D41646F6265");
/*  63 */     FILE_TYPE_MAP.put("ps", "252150532D41646F6265");
/*  64 */     FILE_TYPE_MAP.put("pdf", "255044462D312E");
/*  65 */     FILE_TYPE_MAP.put("qdf", "AC9EBD8F");
/*  66 */     FILE_TYPE_MAP.put("pwl", "E3828596");
/*  67 */     FILE_TYPE_MAP.put("wav", "57415645");
/*  68 */     FILE_TYPE_MAP.put("avi", "41564920");
/*  69 */     FILE_TYPE_MAP.put("ram", "2E7261FD");
/*  70 */     FILE_TYPE_MAP.put("rm", "2E524D46");
/*  71 */     FILE_TYPE_MAP.put("mpg", "000001BA");
/*  72 */     FILE_TYPE_MAP.put("mov", "6D6F6F76");
/*  73 */     FILE_TYPE_MAP.put("asf", "3026B2758E66CF11");
/*  74 */     FILE_TYPE_MAP.put("mid", "4D546864");
/*     */   }
/*     */ 
/*     */   public static final String getImageFileType(File f)
/*     */   {
/*  90 */     if (isImage(f)) {
/*     */       try {
/*  92 */         ImageInputStream iis = ImageIO.createImageInputStream(f);
/*  93 */         Iterator iter = ImageIO.getImageReaders(iis);
/*  94 */         if (!iter.hasNext()) {
/*  95 */           return null;
/*     */         }
/*  97 */         ImageReader reader = (ImageReader)iter.next();
/*  98 */         iis.close();
/*  99 */         return reader.getFormatName();
/*     */       } catch (IOException e) {
/* 101 */         return null;
/*     */       } catch (Exception e) {
/* 103 */         return null;
/*     */       }
/*     */     }
/* 106 */     return null;
/*     */   }
/*     */ 
/*     */   public static final String getFileByFile(File file)
/*     */   {
/* 120 */     String filetype = null;
/* 121 */     byte[] b = new byte[50];
/*     */     try {
/* 123 */       InputStream is = new FileInputStream(file);
/* 124 */       is.read(b);
/* 125 */       filetype = getFileTypeByStream(b);
/* 126 */       is.close();
/*     */     } catch (FileNotFoundException e) {
/* 128 */       e.printStackTrace();
/*     */     } catch (IOException e) {
/* 130 */       e.printStackTrace();
/*     */     }
/* 132 */     return filetype;
/*     */   }
/*     */ 
/*     */   public static final String getFileTypeByStream(byte[] b)
/*     */   {
/* 146 */     String filetypeHex = String.valueOf(getFileHexString(b));
/* 147 */     Iterator entryiterator = FILE_TYPE_MAP.entrySet().iterator();
/* 148 */     while (entryiterator.hasNext()) {
/* 149 */       Map.Entry entry = (Map.Entry)entryiterator.next();
/* 150 */       String fileTypeHexValue = (String)entry.getValue();
/* 151 */       if (filetypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
/* 152 */         return (String)entry.getKey();
/*     */       }
/*     */     }
/* 155 */     return null;
/*     */   }
/*     */ 
/*     */   public static final boolean isImage(File file)
/*     */   {
/* 169 */     boolean flag = false;
/*     */     try {
/* 171 */       BufferedImage bufreader = ImageIO.read(file);
/* 172 */       int width = bufreader.getWidth();
/* 173 */       int height = bufreader.getHeight();
/* 174 */       if ((width == 0) || (height == 0))
/* 175 */         flag = false;
/*     */       else
/* 177 */         flag = true;
/*     */     }
/*     */     catch (IOException e) {
/* 180 */       flag = false;
/*     */     } catch (Exception e) {
/* 182 */       flag = false;
/*     */     }
/* 184 */     return flag;
/*     */   }
/*     */ 
/*     */   public static final String getFileHexString(byte[] b)
/*     */   {
/* 198 */     StringBuilder stringBuilder = new StringBuilder();
/* 199 */     if ((b == null) || (b.length <= 0)) {
/* 200 */       return null;
/*     */     }
/* 202 */     for (int i = 0; i < b.length; i++) {
/* 203 */       int v = b[i] & 0xFF;
/* 204 */       String hv = Integer.toHexString(v);
/* 205 */       if (hv.length() < 2) {
/* 206 */         stringBuilder.append(0);
/*     */       }
/* 208 */       stringBuilder.append(hv);
/*     */     }
/* 210 */     return stringBuilder.toString();
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) throws Exception {
/* 214 */     File f = new File("D://zpic.gif");
/* 215 */     if (f.exists()) {
/* 216 */       String filetype1 = getImageFileType(f);
/* 217 */       System.out.println(filetype1);
/* 218 */       String filetype2 = getFileByFile(f);
/* 219 */       System.out.println(filetype2);
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.FileTypeUtils
 * JD-Core Version:    0.6.2
 */