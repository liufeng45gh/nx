/*     */ package org.jeecgframework.web.cgform.controller.upload;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.core.common.controller.BaseController;
/*     */ import org.jeecgframework.core.common.model.common.UploadFile;
/*     */ import org.jeecgframework.core.common.model.json.AjaxJson;
/*     */ import org.jeecgframework.core.constant.Globals;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.core.util.oConvertUtils;
/*     */ import org.jeecgframework.web.cgform.entity.upload.CgUploadEntity;
/*     */ import org.jeecgframework.web.cgform.service.upload.CgUploadServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/cgUploadController"})
/*     */ public class CgUploadController extends BaseController
/*     */ {
/*  44 */   private static final Logger logger = Logger.getLogger(CgUploadController.class);
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @Autowired
/*     */   private CgUploadServiceI cgUploadService;
/*     */ 
/*     */   @RequestMapping(params={"saveFiles"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public AjaxJson saveFiles(HttpServletRequest request, HttpServletResponse response, CgUploadEntity cgUploadEntity)
/*     */   {
/*  60 */     AjaxJson j = new AjaxJson();
/*  61 */     Map attributes = new HashMap();
/*  62 */     String fileKey = oConvertUtils.getString(request.getParameter("fileKey"));
/*  63 */     String id = oConvertUtils.getString(request.getParameter("cgFormId"));
/*  64 */     String tableName = oConvertUtils.getString(request.getParameter("cgFormName"));
/*  65 */     String cgField = oConvertUtils.getString(request.getParameter("cgFormField"));
/*  66 */     if (!StringUtil.isEmpty(id)) {
/*  67 */       cgUploadEntity.setCgformId(id);
/*  68 */       cgUploadEntity.setCgformName(tableName);
/*  69 */       cgUploadEntity.setCgformField(cgField);
/*     */     }
/*  71 */     if (StringUtil.isNotEmpty(fileKey)) {
/*  72 */       cgUploadEntity.setId(fileKey);
/*  73 */       cgUploadEntity = (CgUploadEntity)this.systemService.getEntity(CgUploadEntity.class, fileKey);
/*     */     }
/*  75 */     UploadFile uploadFile = new UploadFile(request, cgUploadEntity);
/*  76 */     uploadFile.setCusPath("files");
/*  77 */     uploadFile.setSwfpath("swfpath");
/*  78 */     uploadFile.setByteField(null);
/*  79 */     cgUploadEntity = (CgUploadEntity)this.systemService.uploadFile(uploadFile);
/*  80 */     this.cgUploadService.writeBack(id, tableName, cgField, fileKey, cgUploadEntity.getRealpath());
/*  81 */     attributes.put("fileKey", cgUploadEntity.getId());
/*  82 */     attributes.put("viewhref", "commonController.do?objfileList&fileKey=" + cgUploadEntity.getId());
/*  83 */     attributes.put("delurl", "commonController.do?delObjFile&fileKey=" + cgUploadEntity.getId());
/*  84 */     j.setMsg("操作成功");
/*  85 */     j.setAttributes(attributes);
/*  86 */     return j;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"delFile"})
/*     */   @ResponseBody
/*     */   public AjaxJson delFile(HttpServletRequest request)
/*     */   {
/*  97 */     String message = null;
/*  98 */     AjaxJson j = new AjaxJson();
/*  99 */     String id = request.getParameter("id");
/* 100 */     CgUploadEntity file = (CgUploadEntity)this.systemService.getEntity(CgUploadEntity.class, id);
/* 101 */     message = file.getAttachmenttitle() + "被删除成功";
/* 102 */     this.cgUploadService.deleteFile(file);
/* 103 */     this.systemService.addLog(message, Globals.Log_Type_DEL, 
/* 104 */       Globals.Log_Leavel_INFO);
/* 105 */     j.setSuccess(true);
/* 106 */     j.setMsg(message);
/* 107 */     return j;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.controller.upload.CgUploadController
 * JD-Core Version:    0.6.2
 */