/*     */ package org.jeecgframework.web.rest.controller;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.validation.Validator;
/*     */ import org.jeecgframework.core.beanvalidator.BeanValidators;
/*     */ import org.jeecgframework.web.system.pojo.base.TSUser;
/*     */ import org.jeecgframework.web.system.service.UserService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.http.HttpHeaders;
/*     */ import org.springframework.http.HttpStatus;
/*     */ import org.springframework.http.ResponseEntity;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.PathVariable;
/*     */ import org.springframework.web.bind.annotation.RequestBody;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.bind.annotation.ResponseStatus;
/*     */ import org.springframework.web.util.UriComponents;
/*     */ import org.springframework.web.util.UriComponentsBuilder;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/user"})
/*     */ public class UserRestController
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private UserService userService;
/*     */ 
/*     */   @Autowired
/*     */   private Validator validator;
/*     */ 
/*     */   @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   @ResponseBody
/*     */   public List<TSUser> list()
/*     */   {
/*  50 */     List listUsers = this.userService.getList(TSUser.class);
/*  51 */     return listUsers;
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   @ResponseBody
/*     */   public ResponseEntity<?> get(@PathVariable("id") String id)
/*     */   {
/*  62 */     TSUser task = (TSUser)this.userService.get(TSUser.class, id);
/*  63 */     if (task == null) {
/*  64 */       return new ResponseEntity(HttpStatus.NOT_FOUND);
/*     */     }
/*  66 */     return new ResponseEntity(task, HttpStatus.OK);
/*     */   }
/*     */ 
/*     */   @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
/*     */   @ResponseBody
/*     */   public ResponseEntity<?> create(@RequestBody TSUser user, UriComponentsBuilder uriBuilder) {
/*  73 */     Set failures = this.validator.validate(user, new Class[0]);
/*  74 */     if (!failures.isEmpty()) {
/*  75 */       return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
/*     */     }
/*     */ 
/*  79 */     this.userService.save(user);
/*     */ 
/*  82 */     String id = user.getId();
/*  83 */     URI uri = uriBuilder.path("/rest/user/" + id).build().toUri();
/*  84 */     HttpHeaders headers = new HttpHeaders();
/*  85 */     headers.setLocation(uri);
/*     */ 
/*  87 */     return new ResponseEntity(headers, HttpStatus.CREATED);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes={"application/json"})
/*     */   public ResponseEntity<?> update(@RequestBody TSUser user)
/*     */   {
/*  93 */     Set failures = this.validator.validate(user, new Class[0]);
/*  94 */     if (!failures.isEmpty()) {
/*  95 */       return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
/*     */     }
/*     */ 
/*  99 */     this.userService.saveOrUpdate(user);
/*     */ 
/* 102 */     return new ResponseEntity(HttpStatus.NO_CONTENT);
/*     */   }
/*     */   @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.DELETE})
/*     */   @ResponseStatus(HttpStatus.NO_CONTENT)
/*     */   public void delete(@PathVariable("id") String id) {
/* 108 */     this.userService.deleteEntityById(TSUser.class, id);
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.rest.controller.UserRestController
 * JD-Core Version:    0.6.2
 */