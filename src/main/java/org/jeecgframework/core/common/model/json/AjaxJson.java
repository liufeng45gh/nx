/*    */ package org.jeecgframework.core.common.model.json;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class AjaxJson
/*    */ {
/* 15 */   private boolean success = true;
/* 16 */   private String msg = "操作成功";
/* 17 */   private Object obj = null;
/*    */   private Map<String, Object> attributes;
/*    */ 
/*    */   public Map<String, Object> getAttributes()
/*    */   {
/* 20 */     return this.attributes;
/*    */   }
/*    */ 
/*    */   public void setAttributes(Map<String, Object> attributes) {
/* 24 */     this.attributes = attributes;
/*    */   }
/*    */ 
/*    */   public String getMsg() {
/* 28 */     return this.msg;
/*    */   }
/*    */ 
/*    */   public void setMsg(String msg) {
/* 32 */     this.msg = msg;
/*    */   }
/*    */ 
/*    */   public Object getObj() {
/* 36 */     return this.obj;
/*    */   }
/*    */ 
/*    */   public void setObj(Object obj) {
/* 40 */     this.obj = obj;
/*    */   }
/*    */ 
/*    */   public boolean isSuccess() {
/* 44 */     return this.success;
/*    */   }
/*    */ 
/*    */   public void setSuccess(boolean success) {
/* 48 */     this.success = success;
/*    */   }
/*    */   public String getJsonStr() {
/* 51 */     JSONObject obj = new JSONObject();
/* 52 */     obj.put("success", Boolean.valueOf(isSuccess()));
/* 53 */     obj.put("msg", getMsg());
/* 54 */     obj.put("obj", this.obj);
/* 55 */     obj.put("attributes", this.attributes);
/* 56 */     return obj.toJSONString();
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.common.model.json.AjaxJson
 * JD-Core Version:    0.6.2
 */