/*    */ package ssb.warmline.business.service.businessprocessor;
/*    */ 
/*    */ import java.lang.reflect.Constructor;
/*    */ import java.lang.reflect.Method;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.Set;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import ssb.warmline.business.commons.config.InterfaceConfig;
/*    */ import ssb.warmline.business.commons.config.LoadConfig;
/*    */ 
/*    */ public class InterfaceFactory
/*    */ {
/* 20 */   private static Map interfacesMap = new HashMap();
/* 21 */   protected static final Logger logger = LoggerFactory.getLogger(InterfaceFactory.class);
/*    */ 
/*    */   public static BaseInterface getInstance(String type) throws Exception {
/* 24 */     if (interfacesMap.containsKey(type)) {
/* 25 */       return (BaseInterface)interfacesMap.get(type);
/*    */     }
/* 27 */     Map map = LoadConfig.getInterfaceMap();
/* 28 */     InterfaceConfig config = (InterfaceConfig)map.get(type);
/* 29 */     BaseInterface task = initInterface(config);
/* 30 */     interfacesMap.put(type, task);
/* 31 */     return task;
/*    */   }
/*    */ 
/*    */   public static BaseInterface initInterface(InterfaceConfig config)
/*    */     throws Exception
/*    */   {
/* 42 */     String pkg = InterfaceFactory.class.getPackage().getName();
/* 43 */     String claName = pkg + "." + config.getClassName();
/* 44 */     Class taskClass = Class.forName(claName);
/*    */ 
/* 46 */     Constructor cc = taskClass.getDeclaredConstructor(new Class[0]);
/* 47 */     BaseInterface task = (BaseInterface)cc.newInstance(new Object[0]);
/* 48 */     initInterfaceField(task, config);
/* 49 */     return task;
/*    */   }
/*    */ 
/*    */   private static void initInterfaceField(BaseInterface task, InterfaceConfig config) throws Exception {
/* 53 */     task.setTaskType(config.getTaskType());
/*    */ 
/* 55 */     Map fieldsMap = config.getFieldsMap();
/* 56 */     for (Iterator itor = fieldsMap.entrySet().iterator(); itor.hasNext(); ) {
/* 57 */       Map.Entry entry = (Map.Entry)itor.next();
/* 58 */       String fieldName = (String)entry.getKey();
/* 59 */       String fieldValue = (String)entry.getValue();
/*    */ 
/* 61 */       String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
/* 62 */       Method setFeildMethod = task.getClass().getMethod(setMethodName, new Class[] { String.class });
/* 63 */       setFeildMethod.invoke(task, new Object[] { fieldValue });
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void removeInterface(String taskType) {
/* 68 */     BaseInterface task = (BaseInterface)interfacesMap.get(taskType);
/* 69 */     if (task != null) {
/* 70 */       interfacesMap.remove(taskType);
/* 71 */       logger.info(">>>>接口:{}停止", taskType);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void flushInterface(InterfaceConfig interfaceConfig) throws Exception {
/* 76 */     String taskType = interfaceConfig.getTaskType();
/* 77 */     BaseInterface task = (BaseInterface)interfacesMap.get(taskType);
/* 78 */     if (task == null) {
/* 79 */       return;
/*    */     }
/* 81 */     if (!"1".equals(interfaceConfig.getStatus()))
/* 82 */       removeInterface(taskType);
/*    */     else
/* 84 */       initInterfaceField(task, interfaceConfig);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.businessprocessor.InterfaceFactory
 * JD-Core Version:    0.6.2
 */