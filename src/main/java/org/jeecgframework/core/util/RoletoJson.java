/*    */ package org.jeecgframework.core.util;
/*    */ 
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.jeecgframework.core.common.model.json.ComboBox;
/*    */ import org.jeecgframework.web.system.pojo.base.TSRole;
/*    */ 
/*    */ public class RoletoJson
/*    */ {
/*    */   public static String getComboBoxJson(List<TSRole> list, List<TSRole> roles)
/*    */   {
/* 24 */     StringBuffer buffer = new StringBuffer();
/* 25 */     buffer.append("[");
/* 26 */     for (TSRole node : list) {
/* 27 */       if (roles.size() > 0) {
/* 28 */         buffer.append("{\"id\":" + node.getId() + ",\"text\":\"" + node.getRoleName() + "\"");
/* 29 */         for (TSRole node1 : roles) {
/* 30 */           if (node.getId() == node1.getId()) {
/* 31 */             buffer.append(",\"selected\":true");
/*    */           }
/*    */         }
/* 34 */         buffer.append("},");
/*    */       } else {
/* 36 */         buffer.append("{\"id\":" + node.getId() + ",\"text\":\"" + node.getRoleName() + "\"},");
/*    */       }
/*    */     }
/*    */ 
/* 40 */     buffer.append("]");
/*    */ 
/* 44 */     String tmp = buffer.toString();
/* 45 */     tmp = tmp.replaceAll(",]", "]");
/* 46 */     return tmp;
/*    */   }
/*    */ 
/*    */   public static List<ComboBox> getComboBox(List<TSRole> list, List<TSRole> roles)
/*    */   {
/* 53 */     StringBuffer buffer = new StringBuffer();
/* 54 */     List comboxBoxs = new ArrayList();
/* 55 */     buffer.append("[");
/* 56 */     for (TSRole node : list) {
/* 57 */       ComboBox box = new ComboBox();
/* 58 */       box.setId(node.getId().toString());
/* 59 */       box.setText(node.getRoleName());
/* 60 */       if (roles.size() > 0) {
/* 61 */         for (TSRole node1 : roles) {
/* 62 */           if (node.getId() == node1.getId()) {
/* 63 */             box.setSelected(true);
/*    */           }
/*    */         }
/*    */       }
/* 67 */       comboxBoxs.add(box);
/*    */     }
/* 69 */     return comboxBoxs;
/*    */   }
/*    */ 
/*    */   public static String listToReplaceStr(List<?> objList, String perFieldName, String sufFieldName)
/*    */   {
/* 83 */     List strList = new ArrayList();
/* 84 */     for (Iterator localIterator = objList.iterator(); localIterator.hasNext(); ) { Object object = localIterator.next();
/* 85 */       String perStr = null;
/* 86 */       String sufStr = null;
/*    */       try {
/* 88 */         perStr = (String)PropertyUtils.getProperty(object, perFieldName);
/* 89 */         sufStr = (String)PropertyUtils.getProperty(object, sufFieldName);
/*    */       } catch (InvocationTargetException e) {
/* 91 */         e.printStackTrace();
/*    */       } catch (NoSuchMethodException e) {
/* 93 */         e.printStackTrace();
/*    */       } catch (IllegalAccessException e) {
/* 95 */         e.printStackTrace();
/*    */       }
/* 97 */       strList.add(perStr + "_" + sufStr);
/*    */     }
/* 99 */     return StringUtils.join(strList, ",");
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.RoletoJson
 * JD-Core Version:    0.6.2
 */