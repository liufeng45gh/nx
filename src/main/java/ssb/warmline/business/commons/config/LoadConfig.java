/*     */ package ssb.warmline.business.commons.config;
/*     */ 
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentException;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.io.SAXReader;
/*     */ import ssb.warmline.business.service.businessprocessor.InterfaceFactory;
/*     */ 
/*     */ public class LoadConfig
/*     */ {
/*     */   private static Map<String, InterfaceConfig> interfaceMap;
/*     */ 
/*     */   static
/*     */   {
/*  23 */     load();
/*     */   }
/*     */ 
/*     */   private static void load()
/*     */   {
/*  30 */     Element root = null;
/*  31 */     SAXReader reader = new SAXReader();
/*     */     try
/*     */     {
/*  34 */       URL url = LoadConfig.class.getClassLoader().getResource("interfaceConfig.xml");
/*  35 */       Document interfaceDoc = reader.read(url);
/*  36 */       root = interfaceDoc.getRootElement();
/*  37 */       interfaceMap = initInterface(root);
/*     */     } catch (DocumentException e) {
/*  39 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reload() throws Exception {
/*  44 */     SAXReader reader = new SAXReader();
/*  45 */     Element root = null;
/*     */     try
/*     */     {
/*  49 */       Document interfaceDoc = reader.read(LoadConfig.class.getClassLoader().getResource("interfaceConfig.xml"));
/*  50 */       root = interfaceDoc.getRootElement();
/*  51 */       Map tempInterfaceMap = initInterface(root);
/*  52 */       for (Iterator itor = interfaceMap.entrySet().iterator(); itor.hasNext(); ) {
/*  53 */         Map.Entry entry = (Map.Entry)itor.next();
/*  54 */         InterfaceConfig interfaceConfig = (InterfaceConfig)entry.getValue();
/*  55 */         if (!tempInterfaceMap.containsKey(entry.getKey())) {
/*  56 */           InterfaceFactory.removeInterface(interfaceConfig.getTaskType());
/*     */         }
/*     */       }
/*  59 */       for (Iterator itor = tempInterfaceMap.entrySet().iterator(); itor.hasNext(); ) {
/*  60 */         Map.Entry entry = (Map.Entry)itor.next();
/*  61 */         String taskType = (String)entry.getKey();
/*  62 */         InterfaceConfig interfaceConfig = (InterfaceConfig)entry.getValue();
/*  63 */         InterfaceConfig oldConfig = (InterfaceConfig)interfaceMap.get(taskType);
/*  64 */         if (oldConfig != null)
/*     */         {
/*  67 */           if (!interfaceConfig.equals(oldConfig))
/*     */           {
/*  70 */             InterfaceFactory.flushInterface(interfaceConfig);
/*  71 */             interfaceMap.put(taskType, interfaceConfig);
/*     */           }
/*     */         }
/*     */       }
/*  74 */       interfaceMap = tempInterfaceMap;
/*     */     } catch (Exception e) {
/*  76 */       throw e;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static Map<String, InterfaceConfig> initInterface(Element rootElement)
/*     */   {
/*  86 */     Element interfaces = rootElement.element("interfaces");
/*  87 */     Map rsMap = new HashMap();
/*  88 */     for (Iterator i = interfaces.elementIterator("interface"); i.hasNext(); ) {
/*  89 */       InterfaceConfig config = new InterfaceConfig();
/*  90 */       Element inter = (Element)i.next();
/*  91 */       String className = inter.elementText("className");
/*  92 */       String taskType = inter.elementText("taskType");
/*  93 */       String module = inter.elementText("module");
/*  94 */       config.setModule(module);
/*  95 */       config.setTaskName(inter.elementText("taskName"));
/*  96 */       config.setTaskType(taskType);
/*  97 */       config.setClassName(className);
/*  98 */       config.setMethodName(inter.elementText("methodName"));
/*  99 */       config.setStatus(inter.elementText("status"));
/*     */ 
/* 101 */       Element deviceType = inter.element("deviceType");
/* 102 */       if (deviceType != null) {
/* 103 */         config.setDeviceType(inter.elementText("deviceType"));
/*     */       }
/*     */ 
/* 106 */       Element checkLogin = inter.element("checkLogin");
/* 107 */       if (checkLogin != null) {
/* 108 */         config.setCheckLogin(inter.elementText("checkLogin"));
/*     */       }
/* 110 */       List paramsList = new ArrayList();
/* 111 */       Element params = inter.element("params");
/* 112 */       if (params != null) {
/* 113 */         for (Iterator e = params.elementIterator("param"); e.hasNext(); ) {
/* 114 */           Element param = (Element)e.next();
/* 115 */           InterfaceParams interfaceParams = new InterfaceParams();
/* 116 */           interfaceParams.setName(param.attributeValue("name"));
/* 117 */           interfaceParams.setRequired(param.attributeValue("required"));
/* 118 */           interfaceParams.setDataType(param.attributeValue("dataType"));
/* 119 */           interfaceParams.setParamDisp(param.getText());
/* 120 */           paramsList.add(interfaceParams);
/*     */         }
/*     */       }
/* 123 */       Map fieldsMap = new HashMap();
/* 124 */       Element fields = inter.element("fields");
/* 125 */       if (fields != null) {
/* 126 */         for (Iterator e = fields.elementIterator("field"); e.hasNext(); ) {
/* 127 */           Element param = (Element)e.next();
/* 128 */           fieldsMap.put(param.attributeValue("name"), param.getText());
/*     */         }
/*     */       }
/* 131 */       config.setParamsList(paramsList);
/* 132 */       config.setFieldsMap(fieldsMap);
/* 133 */       rsMap.put(taskType, config);
/*     */     }
/* 135 */     return rsMap;
/*     */   }
/*     */ 
/*     */   public static Map<String, InterfaceConfig> getInterfaceMap() {
/* 139 */     return interfaceMap;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.config.LoadConfig
 * JD-Core Version:    0.6.2
 */