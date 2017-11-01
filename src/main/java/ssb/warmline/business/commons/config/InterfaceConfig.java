/*     */ package ssb.warmline.business.commons.config;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class InterfaceConfig
/*     */ {
/*     */   private String taskType;
/*     */   private String taskName;
/*     */   private String className;
/*     */   private String methodName;
/*     */   private String status;
/*     */   private String module;
/*     */   private String deviceType;
/*     */   private String checkLogin;
/*     */   private List paramsList;
/*     */   private Map fieldsMap;
/*     */ 
/*     */   public String getTaskType()
/*     */   {
/*  49 */     return this.taskType;
/*     */   }
/*     */ 
/*     */   public void setTaskType(String taskType) {
/*  53 */     this.taskType = taskType;
/*     */   }
/*     */ 
/*     */   public String getTaskName() {
/*  57 */     return this.taskName;
/*     */   }
/*     */ 
/*     */   public void setTaskName(String taskName) {
/*  61 */     this.taskName = taskName;
/*     */   }
/*     */ 
/*     */   public String getClassName() {
/*  65 */     return this.className;
/*     */   }
/*     */ 
/*     */   public void setClassName(String className) {
/*  69 */     this.className = className;
/*     */   }
/*     */ 
/*     */   public String getMethodName() {
/*  73 */     return this.methodName;
/*     */   }
/*     */ 
/*     */   public void setMethodName(String methodName) {
/*  77 */     this.methodName = methodName;
/*     */   }
/*     */ 
/*     */   public String getStatus() {
/*  81 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(String status) {
/*  85 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public String getDeviceType() {
/*  89 */     return this.deviceType;
/*     */   }
/*     */ 
/*     */   public void setDeviceType(String deviceType) {
/*  93 */     this.deviceType = deviceType;
/*     */   }
/*     */ 
/*     */   public String getCheckLogin() {
/*  97 */     return this.checkLogin;
/*     */   }
/*     */ 
/*     */   public void setCheckLogin(String checkLogin) {
/* 101 */     this.checkLogin = checkLogin;
/*     */   }
/*     */ 
/*     */   public List getParamsList() {
/* 105 */     return this.paramsList;
/*     */   }
/*     */ 
/*     */   public void setParamsList(List paramsList) {
/* 109 */     this.paramsList = paramsList;
/*     */   }
/*     */ 
/*     */   public Map getFieldsMap() {
/* 113 */     return this.fieldsMap;
/*     */   }
/*     */ 
/*     */   public void setFieldsMap(Map fieldsMap) {
/* 117 */     this.fieldsMap = fieldsMap;
/*     */   }
/*     */ 
/*     */   public String getModule() {
/* 121 */     return this.module;
/*     */   }
/*     */ 
/*     */   public void setModule(String module) {
/* 125 */     this.module = module;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 130 */     int prime = 31;
/* 131 */     int result = 1;
/* 132 */     result = 31 * result + (this.className == null ? 0 : this.className.hashCode());
/* 133 */     result = 31 * result + (this.fieldsMap == null ? 0 : this.fieldsMap.hashCode());
/* 134 */     result = 31 * result + (this.methodName == null ? 0 : this.methodName.hashCode());
/* 135 */     result = 31 * result + (this.paramsList == null ? 0 : this.paramsList.hashCode());
/* 136 */     result = 31 * result + (this.status == null ? 0 : this.status.hashCode());
/* 137 */     result = 31 * result + (this.taskName == null ? 0 : this.taskName.hashCode());
/* 138 */     result = 31 * result + (this.taskType == null ? 0 : this.taskType.hashCode());
/* 139 */     return result;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 144 */     if (this == obj) {
/* 145 */       return true;
/*     */     }
/* 147 */     if (obj == null) {
/* 148 */       return false;
/*     */     }
/* 150 */     if (getClass() != obj.getClass()) {
/* 151 */       return false;
/*     */     }
/* 153 */     InterfaceConfig other = (InterfaceConfig)obj;
/* 154 */     if (this.className == null) {
/* 155 */       if (other.className != null)
/* 156 */         return false;
/*     */     }
/* 158 */     else if (!this.className.equals(other.className)) {
/* 159 */       return false;
/*     */     }
/* 161 */     if (this.fieldsMap == null) {
/* 162 */       if (other.fieldsMap != null)
/* 163 */         return false;
/*     */     }
/* 165 */     else if (!this.fieldsMap.equals(other.fieldsMap)) {
/* 166 */       return false;
/*     */     }
/* 168 */     if (this.methodName == null) {
/* 169 */       if (other.methodName != null)
/* 170 */         return false;
/*     */     }
/* 172 */     else if (!this.methodName.equals(other.methodName)) {
/* 173 */       return false;
/*     */     }
/* 175 */     if (this.paramsList == null) {
/* 176 */       if (other.paramsList != null)
/* 177 */         return false;
/*     */     }
/* 179 */     else if (!this.paramsList.equals(other.paramsList)) {
/* 180 */       return false;
/*     */     }
/* 182 */     if (this.status == null) {
/* 183 */       if (other.status != null)
/* 184 */         return false;
/*     */     }
/* 186 */     else if (!this.status.equals(other.status)) {
/* 187 */       return false;
/*     */     }
/* 189 */     if (this.taskName == null) {
/* 190 */       if (other.taskName != null)
/* 191 */         return false;
/*     */     }
/* 193 */     else if (!this.taskName.equals(other.taskName)) {
/* 194 */       return false;
/*     */     }
/* 196 */     if (this.taskType == null) {
/* 197 */       if (other.taskType != null)
/* 198 */         return false;
/*     */     }
/* 200 */     else if (!this.taskType.equals(other.taskType)) {
/* 201 */       return false;
/*     */     }
/* 203 */     return true;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.config.InterfaceConfig
 * JD-Core Version:    0.6.2
 */