/*     */ package org.jeecgframework.web.cgform.service.excel;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.text.DateFormat;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.poi.hssf.usermodel.HSSFCellStyle;
/*     */ import org.apache.poi.hssf.usermodel.HSSFRichTextString;
/*     */ import org.apache.poi.hssf.usermodel.HSSFSheet;
/*     */ import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*     */ import org.apache.poi.ss.usermodel.Cell;
/*     */ import org.apache.poi.ss.usermodel.CellStyle;
/*     */ import org.apache.poi.ss.usermodel.RichTextString;
/*     */ import org.apache.poi.ss.usermodel.Row;
/*     */ import org.apache.poi.ss.usermodel.Sheet;
/*     */ import org.jeecgframework.core.common.exception.BusinessException;
/*     */ import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
/*     */ 
/*     */ public class ExcelTempletService
/*     */ {
/*  36 */   private static int maxColumnWidth = 30;
/*  37 */   private static int minColumnWidth = 12;
/*     */ 
/*  39 */   private static int defaultBlankRow = 1;
/*     */ 
/* 242 */   public static final DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
/*     */ 
/*     */   public static HSSFWorkbook exportExcel(String title, Collection<?> dataSet, List<Map<String, Object>> datalist)
/*     */   {
/*  49 */     return exportExcelInUserModel2File(title, dataSet, datalist);
/*     */   }
/*     */ 
/*     */   private static HSSFWorkbook exportExcelInUserModel2File(String title, Collection<?> dataSet, List<Map<String, Object>> datalist)
/*     */   {
/*  56 */     HSSFWorkbook workbook = null;
/*     */     try
/*     */     {
/*  59 */       if ((dataSet == null) || (dataSet.size() == 0)) {
/*  60 */         throw new Exception("导出数据为空！");
/*     */       }
/*  62 */       if (title == null) {
/*  63 */         throw new Exception("传入参数不能为空！");
/*     */       }
/*     */ 
/*  66 */       workbook = new HSSFWorkbook();
/*     */ 
/*  68 */       Sheet sheet = workbook.createSheet(title);
/*  69 */       int index = 0;
/*     */ 
/*  71 */       Row row = sheet.createRow(index);
/*  72 */       row.setHeight((short)450);
/*  73 */       CellStyle titleStyle = getTitleStyle(workbook);
/*  74 */       Iterator it = dataSet.iterator();
/*  75 */       while (it.hasNext()) {
/*  76 */         CgFormFieldEntity type = (CgFormFieldEntity)it.next();
/*     */ 
/*  78 */         if (type.getIsShow().equals("Y")) {
/*  79 */           Cell cell = row.createCell(index);
/*  80 */           RichTextString text = new HSSFRichTextString(type
/*  81 */             .getContent());
/*  82 */           cell.setCellValue(text);
/*     */ 
/*  84 */           int columnWidth = type.getLength().intValue() > maxColumnWidth ? maxColumnWidth : type.getLength().intValue() == 0 ? minColumnWidth : type.getLength().intValue();
/*  85 */           sheet.setColumnWidth(index, 256 * columnWidth);
/*  86 */           cell.setCellStyle(titleStyle);
/*  87 */           index++;
/*     */         }
/*     */ 
/*  90 */         setBlankRows(defaultBlankRow, index, workbook);
/*     */       }
/*  92 */       for (int i = 0; i < datalist.size(); i++) {
/*  93 */         it = dataSet.iterator();
/*  94 */         row = sheet.createRow(i + 1);
/*  95 */         index = 0;
/*  96 */         while (it.hasNext()) {
/*  97 */           CgFormFieldEntity type = (CgFormFieldEntity)it.next();
/*     */ 
/*  99 */           if (type.getIsShow().equals("Y")) {
/* 100 */             Cell cell = row.createCell(index);
/* 101 */             if (((Map)datalist.get(i)).get(type.getFieldName()) != null) {
/* 102 */               RichTextString text = new HSSFRichTextString(((Map)datalist.get(i)).get(type.getFieldName()).toString());
/* 103 */               cell.setCellValue(text);
/*     */             }
/*     */ 
/* 106 */             index++;
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (Exception e) {
/* 111 */       e.printStackTrace();
/*     */     }
/* 113 */     return workbook;
/*     */   }
/*     */ 
/*     */   public static HSSFCellStyle getTitleStyle(HSSFWorkbook workbook)
/*     */   {
/* 122 */     HSSFCellStyle titleStyle = workbook.createCellStyle();
/* 123 */     titleStyle.setBorderBottom((short)6);
/* 124 */     titleStyle.setBorderLeft((short)2);
/* 125 */     titleStyle.setBorderRight((short)2);
/* 126 */     titleStyle.setBorderTop((short)2);
/* 127 */     titleStyle.setBorderBottom((short)2);
/* 128 */     titleStyle.setBorderTop((short)6);
/* 129 */     titleStyle.setFillForegroundColor((short)40);
/* 130 */     titleStyle.setAlignment((short)2);
/*     */ 
/* 132 */     titleStyle.setFillPattern((short)1);
/*     */ 
/* 134 */     return titleStyle;
/*     */   }
/*     */ 
/*     */   public static void setBlankRows(int rows, int columns, HSSFWorkbook workbook)
/*     */   {
/* 140 */     Sheet sheet = workbook.getSheetAt(0);
/*     */ 
/* 142 */     CellStyle cellStyle = getOneStyle(workbook);
/* 143 */     for (int i = 1; i <= rows; i++) {
/* 144 */       Row row = sheet.createRow(i);
/* 145 */       for (int j = 0; j < columns; j++)
/* 146 */         row.createCell(j).setCellStyle(cellStyle);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static HSSFCellStyle getTwoStyle(HSSFWorkbook workbook)
/*     */   {
/* 153 */     HSSFCellStyle style = workbook.createCellStyle();
/* 154 */     style.setBorderLeft((short)1);
/* 155 */     style.setBorderRight((short)1);
/* 156 */     style.setBorderBottom((short)1);
/* 157 */     style.setBorderTop((short)1);
/* 158 */     style.setFillForegroundColor((short)41);
/* 159 */     style.setFillPattern((short)1);
/* 160 */     return style;
/*     */   }
/*     */ 
/*     */   public static HSSFCellStyle getOneStyle(HSSFWorkbook workbook)
/*     */   {
/* 165 */     HSSFCellStyle style = workbook.createCellStyle();
/* 166 */     style.setBorderLeft((short)1);
/* 167 */     style.setBorderRight((short)1);
/* 168 */     style.setBorderBottom((short)1);
/* 169 */     style.setBorderTop((short)1);
/* 170 */     return style;
/*     */   }
/*     */ 
/*     */   public static Collection importExcelByIs(InputStream inputstream, List<CgFormFieldEntity> lists)
/*     */   {
/* 184 */     Map fieldMap = ConvertDate(lists);
/*     */ 
/* 186 */     List tObject = new ArrayList();
/*     */     try
/*     */     {
/* 190 */       HSSFWorkbook book = new HSSFWorkbook(inputstream);
/*     */ 
/* 192 */       HSSFSheet sheet = book.getSheetAt(0);
/*     */ 
/* 194 */       Iterator row = sheet.rowIterator();
/*     */ 
/* 196 */       Row title = (Row)row.next();
/*     */ 
/* 198 */       Iterator cellTitle = title.cellIterator();
/*     */ 
/* 200 */       Map titlemap = new HashMap();
/*     */ 
/* 202 */       int i = 0;
/*     */ 
/* 204 */       while (cellTitle.hasNext()) {
/* 205 */         Cell cell = (Cell)cellTitle.next();
/* 206 */         String value = cell.getStringCellValue();
/* 207 */         if (fieldMap.get(value) == null) {
/* 208 */           throw new BusinessException("导入数据excel列名有不能识别的列");
/*     */         }
/* 210 */         titlemap.put(Integer.valueOf(i), value);
/* 211 */         i++;
/*     */       }
/*     */ 
/* 214 */       Map retMap = null;
/* 215 */       while (row.hasNext()) {
/* 216 */         retMap = new HashMap();
/*     */ 
/* 218 */         Row rown = (Row)row.next();
/*     */ 
/* 220 */         Iterator cellbody = rown.cellIterator();
/* 221 */         int k = 0;
/*     */ 
/* 223 */         while (cellbody.hasNext()) {
/* 224 */           Cell cell = (Cell)cellbody.next();
/*     */ 
/* 226 */           String titleString = (String)titlemap.get(Integer.valueOf(k));
/* 227 */           if (fieldMap.containsKey(titleString)) {
/* 228 */             retMap.put(((CgFormFieldEntity)fieldMap.get(titleString)).getFieldName(), getCellValueString(cell));
/*     */           }
/*     */ 
/* 231 */           k++;
/*     */         }
/* 233 */         tObject.add(retMap);
/*     */       }
/*     */     } catch (Exception e) {
/* 236 */       e.printStackTrace();
/* 237 */       return null;
/*     */     }
/* 239 */     return tObject;
/*     */   }
/*     */ 
/*     */   public static String getCellValueString(Cell cell)
/*     */   {
/* 251 */     if (cell == null) {
/* 252 */       return null;
/*     */     }
/*     */ 
/* 255 */     int dataFormat = cell.getCellStyle().getDataFormat();
/*     */ 
/* 257 */     if ((dataFormat == 14) || (dataFormat == 178) || (dataFormat == 180) || (dataFormat == 181) || 
/* 258 */       (dataFormat == 182)) {
/* 259 */       return getDateValue(cell);
/*     */     }
/* 261 */     String value = null;
/* 262 */     switch (cell.getCellType()) {
/*     */     case 0:
/* 264 */       value = new DecimalFormat("0.##########").format(cell.getNumericCellValue());
/* 265 */       break;
/*     */     case 1:
/* 268 */       value = cell.getRichStringCellValue().toString();
/* 269 */       break;
/*     */     case 2:
/* 271 */       value = String.valueOf(cell.getCellFormula());
/* 272 */       break;
/*     */     case 3:
/* 275 */       value = String.valueOf(cell.getRichStringCellValue().toString());
/* 276 */       break;
/*     */     case 4:
/* 278 */       value = String.valueOf(cell.getBooleanCellValue());
/* 279 */       break;
/*     */     case 5:
/* 281 */       value = String.valueOf(cell.getErrorCellValue());
/*     */     }
/*     */ 
/* 284 */     return value;
/*     */   }
/*     */ 
/*     */   private static String getDateValue(Cell cell)
/*     */   {
/* 292 */     return DEFAULT_DATE_FORMAT.format(cell.getDateCellValue());
/*     */   }
/*     */ 
/*     */   private static Map<String, CgFormFieldEntity> ConvertDate(List<CgFormFieldEntity> lists)
/*     */   {
/* 298 */     Map maps = new HashMap();
/*     */ 
/* 300 */     for (CgFormFieldEntity cgFormFieldEntity : lists) {
/* 301 */       maps.put(cgFormFieldEntity.getContent(), cgFormFieldEntity);
/*     */     }
/* 303 */     return maps;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.excel.ExcelTempletService
 * JD-Core Version:    0.6.2
 */