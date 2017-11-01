/*     */ package org.jeecgframework.core.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.jeecgframework.core.common.model.json.ComboTree;
/*     */ import org.jeecgframework.web.system.pojo.base.MutiLangEntity;
/*     */ import org.jeecgframework.web.system.service.MutiLangServiceI;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ 
/*     */ public class MutiLangUtil
/*     */ {
/*  18 */   private static Log logger = LogFactory.getLog(StringUtil.class);
/*     */   private static MutiLangServiceI mutiLangService;
/*     */ 
/*     */   public static String paramDelSuccess(String param_lang_key)
/*     */   {
/*  29 */     String message = getMutiLangInstance().getLang("common.delete.success.param", param_lang_key);
/*  30 */     return message;
/*     */   }
/*     */ 
/*     */   public static String paramDelFail(String param_lang_key)
/*     */   {
/*  41 */     String message = getMutiLangInstance().getLang("common.delete.fail.param", param_lang_key);
/*  42 */     return message;
/*     */   }
/*     */ 
/*     */   public static String paramUpdSuccess(String param_lang_key)
/*     */   {
/*  53 */     String message = getMutiLangInstance().getLang("common.edit.success.param", param_lang_key);
/*  54 */     return message;
/*     */   }
/*     */ 
/*     */   public static String paramUpdFail(String param_lang_key)
/*     */   {
/*  64 */     String message = getMutiLangInstance().getLang("common.edit.fail.param", param_lang_key);
/*  65 */     return message;
/*     */   }
/*     */ 
/*     */   public static String paramAddSuccess(String param_lang_key)
/*     */   {
/*  75 */     String message = getMutiLangInstance().getLang("common.add.success.param", param_lang_key);
/*  76 */     return message;
/*     */   }
/*     */ 
/*     */   public static void setMutiTree(List<?> treeList)
/*     */   {
/*  86 */     if (ListUtils.isNullOrEmpty(treeList)) return;
/*     */ 
/*  88 */     for (Iterator localIterator = treeList.iterator(); localIterator.hasNext(); ) { Object treeItem = localIterator.next();
/*     */ 
/*  90 */       ReflectHelper reflectHelper = new ReflectHelper(treeItem);
/*  91 */       String lang_key = (String)reflectHelper.getMethodValue("text");
/*  92 */       String lang_context = getMutiLangInstance().getLang(lang_key);
/*  93 */       reflectHelper.setMethodValue("text", lang_context);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void setMutiComboTree(List<ComboTree> treeList)
/*     */   {
/* 102 */     for (ComboTree index : treeList) {
/* 103 */       index.setText(getMutiLangInstance().getLang(index.getText()));
/* 104 */       if ((index.getChildren() != null) && (index.getChildren().size() > 0))
/*     */       {
/* 106 */         setMutiComboTree(index.getChildren());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean existLangKey(String lang_key)
/*     */   {
/* 119 */     List langKeyList = getMutiLangInstance().findByProperty(MutiLangEntity.class, "langKey", lang_key);
/* 120 */     if (!langKeyList.isEmpty())
/*     */     {
/* 122 */       return true;
/*     */     }
/*     */ 
/* 125 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean existLangKey(String lang_key, String langCode)
/*     */   {
/* 136 */     String hql = "from MutiLangEntity where langKey = '" + lang_key + "' and langCode = '" + langCode + "'";
/* 137 */     List langKeyList = getMutiLangInstance().findByQueryString(hql);
/* 138 */     if (!langKeyList.isEmpty())
/*     */     {
/* 140 */       return true;
/*     */     }
/*     */ 
/* 143 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean existLangContext(String lang_context)
/*     */   {
/* 154 */     List langContextList = getMutiLangInstance().findByProperty(MutiLangEntity.class, "langContext", lang_context);
/* 155 */     if (!langContextList.isEmpty())
/*     */     {
/* 157 */       return true;
/*     */     }
/*     */ 
/* 160 */     return false;
/*     */   }
/*     */ 
/*     */   public static MutiLangServiceI getMutiLangInstance()
/*     */   {
/* 170 */     if (mutiLangService == null)
/*     */     {
/* 172 */       mutiLangService = (MutiLangServiceI)ApplicationContextUtil.getContext().getBean(MutiLangServiceI.class);
/*     */     }
/* 174 */     return mutiLangService;
/*     */   }
/*     */ 
/*     */   public static String doMutiLang(String title, String langArg) {
/* 178 */     String context = getMutiLangInstance().getLang(title, langArg);
/* 179 */     return context;
/*     */   }
/*     */ 
/*     */   public static void setMutiLangValueForList(List<Object> list, String[] attributes)
/*     */   {
/* 189 */     if (ListUtils.isNullOrEmpty(list)) {
/* 190 */       return;
/*     */     }
/* 192 */     if ((attributes == null) || (attributes.length == 0)) {
/* 193 */       return;
/*     */     }
/* 195 */     List newList = new ArrayList();
/* 196 */     for (Iterator localIterator = list.iterator(); localIterator.hasNext(); ) { Object obj = localIterator.next();
/*     */ 
/* 198 */       Object cloneObj = null;
/*     */       try {
/* 200 */         cloneObj = Class.forName(obj.getClass().getName()).newInstance();
/* 201 */         MyBeanUtils.copyBean2Bean(cloneObj, obj);
/*     */       } catch (Exception e) {
/* 203 */         e.printStackTrace();
/* 204 */         continue;
/*     */       }
/* 206 */       ReflectHelper reflectHelper = new ReflectHelper(cloneObj);
/* 207 */       for (String attribute : attributes) {
/* 208 */         String lang_key = (String)reflectHelper.getMethodValue(attribute);
/* 209 */         String lang_context = getMutiLangInstance().getLang(lang_key);
/* 210 */         reflectHelper.setMethodValue(attribute, lang_context);
/*     */       }
/* 212 */       newList.add(cloneObj);
/*     */     }
/* 214 */     list.clear();
/* 215 */     list.addAll(newList);
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.MutiLangUtil
 * JD-Core Version:    0.6.2
 */