/*    */ package ssb.warmline.business.commons.utils.wechatpay;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.dom4j.Document;
/*    */ import org.dom4j.DocumentException;
/*    */ import org.dom4j.DocumentHelper;
/*    */ import org.dom4j.Element;
/*    */ 
/*    */ public class XMLUtil
/*    */ {
/*    */   public static Map doXMLParse(String strxml)
/*    */   {
/* 24 */     Map map = new HashMap();
/* 25 */     strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
/*    */     try {
/* 27 */       if ((strxml == null) || ("".equals(strxml))) {
/* 28 */         return null;
/*    */       }
/*    */ 
/* 33 */       Document document = DocumentHelper.parseText(strxml);
/* 34 */       Element root = document.getRootElement();
/* 35 */       List elementList = root.elements();
/*    */ 
/* 38 */       Iterator it = elementList.iterator();
/* 39 */       while (it.hasNext()) {
/* 40 */         Element element = (Element)it.next();
/* 41 */         String k = element.getName();
/* 42 */         String v = "";
/* 43 */         List children = element.attributes();
/* 44 */         if (children.isEmpty())
/*    */         {
/* 46 */           v = element.getStringValue();
/*    */         }
/* 48 */         else v = getChildrenText(children);
/*    */ 
/* 51 */         map.put(k, v);
/*    */       }
/*    */     }
/*    */     catch (DocumentException e) {
/* 55 */       e.printStackTrace();
/*    */     }
/* 57 */     return map;
/*    */   }
/*    */ 
/*    */   public static String getChildrenText(List children)
/*    */   {
/* 66 */     StringBuffer sb = new StringBuffer();
/* 67 */     if (!children.isEmpty()) {
/* 68 */       Iterator it = children.iterator();
/* 69 */       while (it.hasNext()) {
/* 70 */         Element ele = (Element)it.next();
/* 71 */         String name = ele.getName();
/* 72 */         String value = ele.getStringValue();
/* 73 */         List list = ele.attributes();
/* 74 */         sb.append("<" + name + ">");
/* 75 */         if (!list.isEmpty()) {
/* 76 */           sb.append(getChildrenText(list));
/*    */         }
/* 78 */         sb.append(value);
/* 79 */         sb.append("</" + name + ">");
/*    */       }
/*    */     }
/*    */ 
/* 83 */     return sb.toString();
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.wechatpay.XMLUtil
 * JD-Core Version:    0.6.2
 */