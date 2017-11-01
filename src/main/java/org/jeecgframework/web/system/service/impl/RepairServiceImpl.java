/*      */ package org.jeecgframework.web.system.service.impl;
/*      */ 
/*      */ import java.math.BigDecimal;
/*      */ import java.text.ParseException;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.List;
/*      */ import org.jeecgframework.core.common.dao.ICommonDao;
/*      */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*      */ import org.jeecgframework.core.util.LogUtil;
/*      */ import org.jeecgframework.web.cgform.engine.FreemarkerHelper;
/*      */ import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
/*      */ import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
/*      */ import org.jeecgframework.web.demo.entity.test.CKEditorEntity;
/*      */ import org.jeecgframework.web.demo.entity.test.CourseEntity;
/*      */ import org.jeecgframework.web.demo.entity.test.JeecgDemoCkfinderEntity;
/*      */ import org.jeecgframework.web.demo.entity.test.JeecgJdbcEntity;
/*      */ import org.jeecgframework.web.demo.entity.test.JeecgMatterBom;
/*      */ import org.jeecgframework.web.demo.entity.test.JeecgNoteEntity;
/*      */ import org.jeecgframework.web.demo.entity.test.JeecgOrderCustomEntity;
/*      */ import org.jeecgframework.web.demo.entity.test.JeecgOrderMainEntity;
/*      */ import org.jeecgframework.web.demo.entity.test.JeecgOrderProductEntity;
/*      */ import org.jeecgframework.web.demo.entity.test.StudentEntity;
/*      */ import org.jeecgframework.web.demo.entity.test.TSStudent;
/*      */ import org.jeecgframework.web.demo.entity.test.TeacherEntity;
/*      */ import org.jeecgframework.web.system.dao.repair.RepairDao;
/*      */ import org.jeecgframework.web.system.pojo.base.TSAttachment;
/*      */ import org.jeecgframework.web.system.pojo.base.TSDemo;
/*      */ import org.jeecgframework.web.system.pojo.base.TSFunction;
/*      */ import org.jeecgframework.web.system.pojo.base.TSIcon;
/*      */ import org.jeecgframework.web.system.pojo.base.TSLog;
/*      */ import org.jeecgframework.web.system.pojo.base.TSOperation;
/*      */ import org.jeecgframework.web.system.pojo.base.TSRole;
/*      */ import org.jeecgframework.web.system.pojo.base.TSRoleFunction;
/*      */ import org.jeecgframework.web.system.pojo.base.TSRoleUser;
/*      */ import org.jeecgframework.web.system.pojo.base.TSTimeTaskEntity;
/*      */ import org.jeecgframework.web.system.pojo.base.TSType;
/*      */ import org.jeecgframework.web.system.pojo.base.TSTypegroup;
/*      */ import org.jeecgframework.web.system.pojo.base.TSUser;
/*      */ import org.jeecgframework.web.system.service.RepairService;
/*      */ import org.springframework.beans.factory.annotation.Autowired;
/*      */ import org.springframework.stereotype.Service;
/*      */ import org.springframework.transaction.annotation.Transactional;
/*      */ import ssb.warmline.business.commons.utils.DateUtils;
/*      */ 
/*      */ @Service("repairService")
/*      */ @Transactional
/*      */ public class RepairServiceImpl extends CommonServiceImpl
/*      */   implements RepairService
/*      */ {
/*      */ 
/*      */   @Autowired
/*      */   private RepairDao repairDao;
/*      */ 
/*      */   public void deleteAndRepair()
/*      */   {
/*   59 */     this.commonDao.executeHql("delete TSLog");
/*   60 */     this.commonDao.executeHql("delete CKEditorEntity");
/*   61 */     this.commonDao.executeHql("delete CgformEnhanceJsEntity");
/*   62 */     this.commonDao.executeHql("delete CgFormFieldEntity");
/*   63 */     this.commonDao.executeHql("delete CgFormHeadEntity");
/*   64 */     this.commonDao.executeHql("delete TSAttachment");
/*   65 */     this.commonDao.executeHql("delete TSOperation");
/*   66 */     this.commonDao.executeHql("delete TSRoleFunction");
/*   67 */     this.commonDao.executeHql("delete TSRoleUser");
/*   68 */     this.commonDao.executeHql("delete TSUser");
/*   69 */     this.commonDao.executeHql("delete TSBaseUser");
/*   70 */     this.commonDao.executeHql("update TSFunction ts set ts.TSFunction = null");
/*   71 */     this.commonDao.executeHql("delete TSFunction");
/*   72 */     this.commonDao.executeHql("update TSDepart t set t.TSPDepart = null");
/*   73 */     this.commonDao.executeHql("delete TSDepart");
/*   74 */     this.commonDao.executeHql("delete TSIcon");
/*   75 */     this.commonDao.executeHql("delete TSRole");
/*   76 */     this.commonDao.executeHql("delete TSType");
/*   77 */     this.commonDao.executeHql("delete TSTypegroup");
/*   78 */     this.commonDao.executeHql("update TSDemo t set t.TSDemo = null");
/*   79 */     this.commonDao.executeHql("delete TSDemo");
/*   80 */     this.commonDao.executeHql("delete JeecgDemoCkfinderEntity");
/*   81 */     this.commonDao.executeHql("delete TSTimeTaskEntity");
/*   82 */     this.commonDao.executeHql("update TSTerritory t set t.TSTerritory = null");
/*   83 */     this.commonDao.executeHql("delete TSTerritory");
/*   84 */     this.commonDao.executeHql("delete StudentEntity");
/*   85 */     this.commonDao.executeHql("delete CourseEntity");
/*   86 */     this.commonDao.executeHql("delete TeacherEntity");
/*   87 */     this.commonDao.executeHql("delete JeecgJdbcEntity ");
/*   88 */     this.commonDao.executeHql("delete JeecgOrderMainEntity ");
/*   89 */     this.commonDao.executeHql("delete JeecgOrderProductEntity ");
/*   90 */     this.commonDao.executeHql("delete JeecgOrderCustomEntity ");
/*   91 */     this.commonDao.executeHql("delete JeecgNoteEntity ");
/*   92 */     this.commonDao.executeHql("update JeecgMatterBom mb set mb.parent = null");
/*   93 */     this.commonDao.executeHql("delete JeecgMatterBom ");
/*   94 */     repair();
/*      */   }
/*      */ 
/*      */   public synchronized void repair()
/*      */   {
/*  104 */     repaireIcon();
/*  105 */     repairAttachment();
/*  106 */     repairMenu();
/*  107 */     repairRole();
/*  108 */     repairUser();
/*  109 */     repairTypeAndGroup();
/*  110 */     repairType();
/*  111 */     repairOperation();
/*  112 */     repairRoleFunction();
/*  113 */     repairUserRole();
/*  114 */     repairDemo();
/*  115 */     repairLog();
/*  116 */     repairCkEditor();
/*  117 */     repairCgFormHead();
/*  118 */     repairCgFormField();
/*  119 */     repairTask();
/*  120 */     repairExcel();
/*  121 */     this.repairDao.batchRepairTerritory();
/*  122 */     repairJdbcEntity();
/*  123 */     repairJeecgNoteEntity();
/*  124 */     repairOrder();
/*  125 */     repairMatterBom();
/*  126 */     repairReportEntity();
/*      */   }
/*      */ 
/*      */   private void repairMatterBom()
/*      */   {
/*  135 */     JeecgMatterBom entity = new JeecgMatterBom();
/*  136 */     entity.setCode("001");
/*  137 */     entity.setName("电脑");
/*  138 */     entity.setUnit("台");
/*  139 */     entity.setWeight("100");
/*  140 */     entity.setPrice(new BigDecimal(5000));
/*  141 */     entity.setStock(Integer.valueOf(10));
/*  142 */     entity.setAddress("广东深圳");
/*  143 */     entity.setProductionDate(new Date());
/*  144 */     entity.setQuantity(Integer.valueOf(5));
/*  145 */     this.commonDao.save(entity);
/*      */ 
/*  147 */     JeecgMatterBom entity2 = new JeecgMatterBom();
/*  148 */     entity2.setCode("001001");
/*  149 */     entity2.setName("主板");
/*  150 */     entity2.setUnit("个");
/*  151 */     entity2.setWeight("60");
/*  152 */     entity2.setPrice(new BigDecimal(800));
/*  153 */     entity2.setStock(Integer.valueOf(18));
/*  154 */     entity2.setAddress("上海");
/*  155 */     entity2.setProductionDate(new Date());
/*  156 */     entity2.setQuantity(Integer.valueOf(6));
/*  157 */     entity2.setParent(entity);
/*  158 */     this.commonDao.save(entity2);
/*      */   }
/*      */ 
/*      */   private void repairOrder()
/*      */   {
/*  167 */     JeecgOrderMainEntity main = new JeecgOrderMainEntity();
/*  168 */     main.setGoAllPrice(new BigDecimal(1111111));
/*  169 */     main.setGoContactName("alex");
/*  170 */     main.setGoContent("别放辣椒");
/*  171 */     main.setGoderType("1");
/*  172 */     main.setGoOrderCode("11111AAA");
/*  173 */     main.setGoOrderCount(Integer.valueOf(1));
/*  174 */     main.setGoReturnPrice(new BigDecimal(100));
/*  175 */     main.setUsertype("1");
/*  176 */     this.commonDao.save(main);
/*  177 */     JeecgOrderProductEntity product = new JeecgOrderProductEntity();
/*  178 */     product.setGoOrderCode(main.getGoOrderCode());
/*  179 */     product.setGopCount(Integer.valueOf(1));
/*  180 */     product.setGopOnePrice(new BigDecimal(100));
/*  181 */     product.setGopProductName("最最美味的地锅鸡");
/*  182 */     product.setGopProductType("1");
/*  183 */     product.setGopSumPrice(new BigDecimal(100));
/*  184 */     this.commonDao.save(product);
/*  185 */     JeecgOrderCustomEntity coustom = new JeecgOrderCustomEntity();
/*  186 */     coustom.setGoOrderCode(main.getGoOrderCode());
/*  187 */     coustom.setGocCusName("小明");
/*  188 */     coustom.setGocSex("1");
/*  189 */     this.commonDao.save(coustom);
/*      */   }
/*      */ 
/*      */   private void repairJeecgNoteEntity()
/*      */   {
/*  198 */     JeecgNoteEntity entity = new JeecgNoteEntity();
/*  199 */     entity.setAge(Integer.valueOf(10));
/*  200 */     entity.setBirthday(new Date());
/*  201 */     entity.setCreatedt(new Date());
/*  202 */     entity.setName("小红");
/*  203 */     entity.setSalary(new BigDecimal(1000));
/*  204 */     this.commonDao.save(entity);
/*      */   }
/*      */ 
/*      */   private void repairJdbcEntity()
/*      */   {
/*  214 */     JeecgJdbcEntity entity = new JeecgJdbcEntity();
/*  215 */     entity.setAge(Integer.valueOf(12));
/*      */ 
/*  217 */     entity.setBirthday(DateUtils.str2Date("2014-02-14", new SimpleDateFormat("yyyy-MM-dd")));
/*      */ 
/*  219 */     entity.setDepId("123");
/*  220 */     entity.setEmail("demo@jeecg.com");
/*  221 */     entity.setMobilePhone("13111111111");
/*  222 */     entity.setOfficePhone("66666666");
/*  223 */     entity.setSalary(new BigDecimal(111111));
/*  224 */     entity.setSex("1");
/*  225 */     entity.setUserName("小明");
/*  226 */     this.commonDao.save(entity);
/*      */   }
/*      */ 
/*      */   private void repairExcel()
/*      */   {
/*  235 */     CourseEntity course = new CourseEntity();
/*  236 */     course.setName("海贼王");
/*  237 */     TeacherEntity teacher = new TeacherEntity();
/*  238 */     teacher.setName("路飞");
/*  239 */     teacher.setPic("upload/Teacher/pic3345280233.PNG");
/*  240 */     course.setTeacher(teacher);
/*  241 */     List list = new ArrayList();
/*  242 */     StudentEntity student = new StudentEntity();
/*  243 */     student.setName("卓洛");
/*  244 */     student.setSex("0");
/*  245 */     list.add(student);
/*  246 */     student = new StudentEntity();
/*  247 */     student.setName("山治 ");
/*  248 */     student.setSex("0");
/*  249 */     list.add(student);
/*  250 */     course.setStudents(list);
/*  251 */     this.commonDao.save(course.getTeacher());
/*  252 */     this.commonDao.save(course);
/*  253 */     this.commonDao.save(course);
/*  254 */     for (StudentEntity s : course.getStudents()) {
/*  255 */       s.setCourse(course);
/*      */     }
/*  257 */     this.commonDao.batchSave(course.getStudents());
/*      */   }
/*      */ 
/*      */   private void repairTask()
/*      */   {
/*  266 */     TSTimeTaskEntity task = new TSTimeTaskEntity();
/*  267 */     task.setTaskId("taskDemoServiceTaskCronTrigger");
/*  268 */     task.setTaskDescribe("测试Demo");
/*  269 */     task.setCronExpression("0 0/1 * * * ?");
/*  270 */     task.setIsEffect("0");
/*  271 */     task.setIsStart("0");
/*  272 */     this.commonDao.saveOrUpdate(task);
/*      */   }
/*      */ 
/*      */   private void repairCkFinder()
/*      */   {
/*  280 */     JeecgDemoCkfinderEntity ckfinder = new JeecgDemoCkfinderEntity();
/*  281 */     ckfinder.setImage("/jeecg/userfiles/images/%E6%9C%AA%E5%91%BD%E5%90%8D.jpg");
/*  282 */     ckfinder.setAttachment("/jeecg/userfiles/files/JEECG%20UI%E6%A0%87%E7%AD%BE%E5%BA%93%E5%B8%AE%E5%8A%A9%E6%96%87%E6%A1%A3v3_2.pdf");
/*  283 */     String str = "<img alt=\"\" src=\"/jeecg/userfiles/images/%E6%9C%AA%E5%91%BD%E5%90%8D.jpg\" style=\"height:434px; width:439px\" /><br />\r\n可爱的小猫<br />\r\n<br />\r\n<strong><span style=\"font-size:14.0pt\">1</span></strong><strong><span style=\"font-family:宋体; font-size:14.0pt; line-height:150%\">．</span></strong><strong><span style=\"font-size:14.0pt\">CRM</span></strong><strong><span style=\"font-family:宋体; font-size:14.0pt; line-height:150%\">概述</span></strong><br />\r\n<strong><span style=\"font-size:12.0pt\">1</span></strong><strong><span style=\"font-family:宋体; font-size:12.0pt; line-height:150%\">．</span></strong><strong><span style=\"font-size:12.0pt\">1</span></strong><strong><span style=\"font-family:宋体; font-size:12.0pt; line-height:150%\">概念</span></strong>\r\n\r\n<p style=\"line-height:150%; text-indent:24.0pt\"><span style=\"color:black; font-family:宋体; font-size:12.0pt; line-height:150%\">CRM</span><span style=\"color:black; font-family:宋体; font-size:12.0pt; line-height:150%\">是一项商业战略，它是按照客户细分原则有效的组织企业资源，来培养以客户为中心的</span><span style=\"color:red; font-family:宋体; font-size:12.0pt; line-height:150%\">经营行为以及实施以</span><span style=\"color:black; font-family:宋体; font-size:12.0pt; line-height:150%\">客户为中心的业务流程，以此为手段来提高企业的获利能力、收入及客户满意度。</span></p>\r\n\r\n<p style=\"line-height:150%; text-indent:24.0pt\"><span style=\"color:black; font-family:宋体; font-size:12.0pt; line-height:150%\">U8CRM</span><span style=\"color:black; font-family:宋体; font-size:12.0pt; line-height:150%\">同样是基于客户为中心应用原则，</span><span style=\"font-family:宋体; font-size:12.0pt; line-height:150%\">把客户作为企业最重要的资源，围绕客户的生命周期，从客户接触开始，到客户交易、客户服务的全程进行跟踪管理、过程监控，并通过对客户多角度分析，识别客户满足度和价值度，从而不断改进产品和服务，使客户价值最大化、终身化。</span></p>\r\n<strong><span style=\"font-family:宋体; font-size:12.0pt; line-height:150%\">1</span></strong><strong><span style=\"font-family:宋体; font-size:12.0pt; line-height:\t150%\">．2应用价值</span></strong>\r\n\r\n<p style=\"line-height:150%; text-indent:24.0pt\"><span style=\"font-family:宋体; font-size:12.0pt; line-height:150%\">统一企业的客户资源：系统帮助企业建立完整的客户、联系人资源档案，不同的组织、部门可以根据资源权限访问相关的客户资料。</span></p>\r\n<span style=\"font-family:宋体; font-size:12.0pt; line-height:150%\">深入挖掘价值客户：系统帮助用户建立价值评估体系，基于客户交易数据，多角度、全方位评估客户价值。通过客户价值的评估，挖掘价值客户，进而更好的服务价值客户。例如，帮助企业发现带来80%销售收入的20%价值客户。</span>\r\n\r\n<p style=\"line-height:150%; text-indent:24.0pt\"><span style=\"font-family:宋体; font-size:12.0pt; line-height:150%\">跟踪和监控销售机会：系统侧重售前业务管理，从客户向企业表达意向开始，围绕销售线索不同阶段，提供对商机客户购买意向、接触过程、竞争对手、阶段评估等信息的追踪记录，并通过销售漏斗有效监督整个销售过程是否正常。</span></p>\r\n\r\n<p style=\"line-height:150%; text-indent:24.0pt\"><span style=\"font-family:宋体; font-size:12.0pt; line-height:150%\">科学预测未来销售情况：提供了一种预测模式，系统可以按照销售商机的预期销售收入和预计成交时间，科学的预测未来可能实现的销售收入。</span></p>\r\n\r\n<p style=\"line-height:150%; text-indent:24.0pt\"><span style=\"font-family:宋体; font-size:12.0pt; line-height:150%\">提供科学理性的分析决策：系统提供基于客户完整业务（客户关系管理、供应链和财务）的决策分析，并通过报表的形式展现给用户，辅助企业做出数字化决策。</span></p>\r\n<span style=\"font-family:宋体; font-size:12.0pt; line-height:150%\">资源共享：CRM系统与企业销售系统、财务系统等集成应用，实现企业信息化的整合和共享。</span><br />\r\n&nbsp;";
/*  284 */     ckfinder.setRemark(str);
/*  285 */     this.commonDao.saveOrUpdate(ckfinder);
/*      */   }
/*      */ 
/*      */   private void repairCgFormHead()
/*      */   {
/*  293 */     CgFormHeadEntity order_main = new CgFormHeadEntity();
/*  294 */     order_main.setTableName("jform_order_main");
/*  295 */     order_main.setIsTree("N");
/*  296 */     order_main.setIsPagination("Y");
/*  297 */     order_main.setIsCheckbox("N");
/*  298 */     order_main.setQuerymode("group");
/*  299 */     order_main.setIsDbSynch("N");
/*  300 */     order_main.setContent("订单主信息");
/*  301 */     order_main.setCreateBy("admin");
/*  302 */     order_main.setCreateDate(new Date());
/*  303 */     order_main.setJformPkType("UUID");
/*      */ 
/*  306 */     order_main.setCreateName("管理员");
/*  307 */     order_main.setJformVersion("57");
/*  308 */     order_main.setJformType(Integer.valueOf(2));
/*  309 */     order_main.setRelationType(Integer.valueOf(0));
/*  310 */     order_main.setSubTableStr("jform_order_ticket,jform_order_customer");
/*  311 */     this.commonDao.saveOrUpdate(order_main);
/*      */ 
/*  313 */     CgFormHeadEntity leave = new CgFormHeadEntity();
/*  314 */     leave.setTableName("jform_leave");
/*  315 */     leave.setIsTree("N");
/*  316 */     leave.setIsPagination("Y");
/*  317 */     leave.setIsCheckbox("N");
/*  318 */     leave.setJformPkType("UUID");
/*  319 */     leave.setQuerymode("group");
/*  320 */     leave.setIsDbSynch("N");
/*  321 */     leave.setContent("请假单");
/*  322 */     leave.setCreateBy("admin");
/*  323 */     leave.setCreateDate(new Date());
/*      */ 
/*  326 */     leave.setCreateName("管理员");
/*  327 */     leave.setJformVersion("51");
/*  328 */     leave.setJformType(Integer.valueOf(1));
/*  329 */     leave.setRelationType(Integer.valueOf(0));
/*  330 */     this.commonDao.saveOrUpdate(leave);
/*      */ 
/*  393 */     CgFormHeadEntity customer = new CgFormHeadEntity();
/*  394 */     customer.setTableName("jform_order_customer");
/*  395 */     customer.setIsTree("N");
/*  396 */     customer.setIsPagination("Y");
/*  397 */     customer.setIsCheckbox("Y");
/*  398 */     customer.setQuerymode("single");
/*  399 */     customer.setIsDbSynch("N");
/*  400 */     customer.setContent("订单客户信息");
/*  401 */     customer.setCreateBy("admin");
/*  402 */     customer.setJformPkType("UUID");
/*  403 */     customer.setCreateDate(new Date());
/*      */ 
/*  408 */     customer.setCreateName("管理员");
/*  409 */     customer.setJformVersion("16");
/*  410 */     customer.setJformType(Integer.valueOf(3));
/*  411 */     customer.setRelationType(Integer.valueOf(0));
/*  412 */     this.commonDao.saveOrUpdate(customer);
/*      */ 
/*  414 */     CgFormHeadEntity ticket = new CgFormHeadEntity();
/*  415 */     ticket.setTableName("jform_order_ticket");
/*  416 */     ticket.setIsTree("N");
/*  417 */     ticket.setIsPagination("Y");
/*  418 */     ticket.setIsCheckbox("N");
/*  419 */     ticket.setQuerymode("single");
/*  420 */     ticket.setIsDbSynch("N");
/*  421 */     ticket.setJformPkType("UUID");
/*  422 */     ticket.setContent("订单机票信息");
/*  423 */     ticket.setCreateBy("admin");
/*  424 */     ticket.setCreateDate(new Date());
/*      */ 
/*  430 */     ticket.setCreateName("管理员");
/*  431 */     ticket.setJformVersion("20");
/*  432 */     ticket.setJformType(Integer.valueOf(3));
/*  433 */     ticket.setRelationType(Integer.valueOf(0));
/*  434 */     this.commonDao.saveOrUpdate(ticket);
/*      */ 
/*  436 */     CgFormHeadEntity price1 = new CgFormHeadEntity();
/*  437 */     price1.setTableName("jform_price1");
/*  438 */     price1.setIsTree("N");
/*  439 */     price1.setIsPagination("Y");
/*  440 */     price1.setIsCheckbox("N");
/*  441 */     price1.setQuerymode("group");
/*  442 */     price1.setIsDbSynch("N");
/*  443 */     price1.setJformPkType("UUID");
/*  444 */     price1.setContent("价格认证机构统计表");
/*  445 */     price1.setCreateBy("admin");
/*  446 */     price1.setCreateDate(new Date());
/*      */ 
/*  450 */     price1.setCreateName("管理员");
/*  451 */     price1.setJformVersion("3");
/*  452 */     price1.setJformType(Integer.valueOf(1));
/*  453 */     price1.setRelationType(Integer.valueOf(0));
/*  454 */     this.commonDao.saveOrUpdate(price1);
/*      */   }
/*      */ 
/*      */   private void repairCgFormField()
/*      */   {
/*  464 */     CgFormHeadEntity jform_order_main = 
/*  465 */       (CgFormHeadEntity)this.commonDao.findByProperty(
/*  465 */       CgFormHeadEntity.class, "tableName", "jform_order_main").get(0);
/*  466 */     CgFormFieldEntity jform_order_main_id = new CgFormFieldEntity();
/*  467 */     jform_order_main_id.setFieldName("id");
/*  468 */     jform_order_main_id.setTable(jform_order_main);
/*  469 */     jform_order_main_id.setFieldLength(Integer.valueOf(0));
/*  470 */     jform_order_main_id.setIsKey("Y");
/*  471 */     jform_order_main_id.setIsNull("N");
/*  472 */     jform_order_main_id.setIsQuery("N");
/*  473 */     jform_order_main_id.setIsShow("N");
/*  474 */     jform_order_main_id.setIsShowList("N");
/*  475 */     jform_order_main_id.setShowType("text");
/*  476 */     jform_order_main_id.setLength(Integer.valueOf(36));
/*  477 */     jform_order_main_id.setType("string");
/*  478 */     jform_order_main_id.setOrderNum(Integer.valueOf(0));
/*  479 */     jform_order_main_id.setPointLength(Integer.valueOf(0));
/*  480 */     jform_order_main_id.setQueryMode("single");
/*  481 */     jform_order_main_id.setContent("主键");
/*  482 */     jform_order_main_id.setCreateBy("admin");
/*  483 */     jform_order_main_id.setCreateDate(new Date());
/*  484 */     jform_order_main_id.setCreateName("管理员");
/*  485 */     jform_order_main_id.setDictField("");
/*  486 */     jform_order_main_id.setDictTable("");
/*  487 */     jform_order_main_id.setMainTable("");
/*  488 */     jform_order_main_id.setMainField("");
/*  489 */     this.commonDao.saveOrUpdate(jform_order_main_id);
/*      */ 
/*  491 */     CgFormFieldEntity jform_order_main_order_code = new CgFormFieldEntity();
/*  492 */     jform_order_main_order_code.setFieldName("order_code");
/*  493 */     jform_order_main_order_code.setTable(jform_order_main);
/*  494 */     jform_order_main_order_code.setFieldLength(Integer.valueOf(0));
/*  495 */     jform_order_main_order_code.setIsKey("N");
/*  496 */     jform_order_main_order_code.setIsNull("Y");
/*  497 */     jform_order_main_order_code.setIsQuery("Y");
/*  498 */     jform_order_main_order_code.setIsShow("Y");
/*  499 */     jform_order_main_order_code.setIsShowList("Y");
/*  500 */     jform_order_main_order_code.setShowType("text");
/*  501 */     jform_order_main_order_code.setLength(Integer.valueOf(50));
/*  502 */     jform_order_main_order_code.setType("string");
/*  503 */     jform_order_main_order_code.setOrderNum(Integer.valueOf(1));
/*  504 */     jform_order_main_order_code.setPointLength(Integer.valueOf(0));
/*  505 */     jform_order_main_order_code.setQueryMode("single");
/*  506 */     jform_order_main_order_code.setContent("订单号");
/*  507 */     jform_order_main_order_code.setCreateBy("admin");
/*  508 */     jform_order_main_order_code.setCreateDate(new Date());
/*  509 */     jform_order_main_order_code.setCreateName("管理员");
/*  510 */     jform_order_main_order_code.setDictField("");
/*  511 */     jform_order_main_order_code.setDictTable("");
/*  512 */     jform_order_main_order_code.setMainTable("");
/*  513 */     jform_order_main_order_code.setMainField("");
/*  514 */     this.commonDao.saveOrUpdate(jform_order_main_order_code);
/*      */ 
/*  516 */     CgFormFieldEntity jform_order_main_order_date = new CgFormFieldEntity();
/*  517 */     jform_order_main_order_date.setFieldName("order_date");
/*  518 */     jform_order_main_order_date.setTable(jform_order_main);
/*  519 */     jform_order_main_order_date.setFieldLength(Integer.valueOf(0));
/*  520 */     jform_order_main_order_date.setIsKey("N");
/*  521 */     jform_order_main_order_date.setIsNull("Y");
/*  522 */     jform_order_main_order_date.setIsQuery("Y");
/*  523 */     jform_order_main_order_date.setIsShow("Y");
/*  524 */     jform_order_main_order_date.setIsShowList("Y");
/*  525 */     jform_order_main_order_date.setShowType("date");
/*  526 */     jform_order_main_order_date.setLength(Integer.valueOf(20));
/*  527 */     jform_order_main_order_date.setType("Date");
/*  528 */     jform_order_main_order_date.setOrderNum(Integer.valueOf(2));
/*  529 */     jform_order_main_order_date.setPointLength(Integer.valueOf(0));
/*  530 */     jform_order_main_order_date.setQueryMode("single");
/*  531 */     jform_order_main_order_date.setContent("订单日期");
/*  532 */     jform_order_main_order_date.setCreateBy("admin");
/*  533 */     jform_order_main_order_date.setCreateDate(new Date());
/*  534 */     jform_order_main_order_date.setCreateName("管理员");
/*  535 */     jform_order_main_order_date.setDictField("");
/*  536 */     jform_order_main_order_date.setDictTable("");
/*  537 */     jform_order_main_order_date.setMainTable("");
/*  538 */     jform_order_main_order_date.setMainField("");
/*  539 */     this.commonDao.saveOrUpdate(jform_order_main_order_date);
/*      */ 
/*  541 */     CgFormFieldEntity jform_order_main_order_money = new CgFormFieldEntity();
/*  542 */     jform_order_main_order_money.setFieldName("order_money");
/*  543 */     jform_order_main_order_money.setTable(jform_order_main);
/*  544 */     jform_order_main_order_money.setFieldLength(Integer.valueOf(0));
/*  545 */     jform_order_main_order_money.setIsKey("N");
/*  546 */     jform_order_main_order_money.setIsNull("Y");
/*  547 */     jform_order_main_order_money.setIsQuery("Y");
/*  548 */     jform_order_main_order_money.setIsShow("Y");
/*  549 */     jform_order_main_order_money.setIsShowList("Y");
/*  550 */     jform_order_main_order_money.setShowType("text");
/*  551 */     jform_order_main_order_money.setLength(Integer.valueOf(10));
/*  552 */     jform_order_main_order_money.setType("double");
/*  553 */     jform_order_main_order_money.setOrderNum(Integer.valueOf(3));
/*  554 */     jform_order_main_order_money.setPointLength(Integer.valueOf(3));
/*  555 */     jform_order_main_order_money.setQueryMode("single");
/*  556 */     jform_order_main_order_money.setContent("订单金额");
/*  557 */     jform_order_main_order_money.setCreateBy("admin");
/*  558 */     jform_order_main_order_money.setCreateDate(new Date());
/*  559 */     jform_order_main_order_money.setCreateName("管理员");
/*  560 */     jform_order_main_order_money.setDictField("");
/*  561 */     jform_order_main_order_money.setDictTable("");
/*  562 */     jform_order_main_order_money.setMainTable("");
/*  563 */     jform_order_main_order_money.setMainField("");
/*  564 */     this.commonDao.saveOrUpdate(jform_order_main_order_money);
/*      */ 
/*  566 */     CgFormFieldEntity jform_order_main_content = new CgFormFieldEntity();
/*  567 */     jform_order_main_content.setFieldName("content");
/*  568 */     jform_order_main_content.setTable(jform_order_main);
/*  569 */     jform_order_main_content.setFieldLength(Integer.valueOf(0));
/*  570 */     jform_order_main_content.setIsKey("N");
/*  571 */     jform_order_main_content.setIsNull("Y");
/*  572 */     jform_order_main_content.setIsQuery("Y");
/*  573 */     jform_order_main_content.setIsShow("Y");
/*  574 */     jform_order_main_content.setIsShowList("Y");
/*  575 */     jform_order_main_content.setShowType("text");
/*  576 */     jform_order_main_content.setLength(Integer.valueOf(255));
/*  577 */     jform_order_main_content.setType("string");
/*  578 */     jform_order_main_content.setOrderNum(Integer.valueOf(4));
/*  579 */     jform_order_main_content.setPointLength(Integer.valueOf(0));
/*  580 */     jform_order_main_content.setQueryMode("single");
/*  581 */     jform_order_main_content.setContent("备注");
/*  582 */     jform_order_main_content.setCreateBy("admin");
/*  583 */     jform_order_main_content.setCreateDate(new Date());
/*  584 */     jform_order_main_content.setCreateName("管理员");
/*  585 */     jform_order_main_content.setDictField("");
/*  586 */     jform_order_main_content.setDictTable("");
/*  587 */     jform_order_main_content.setMainTable("");
/*  588 */     jform_order_main_content.setMainField("");
/*  589 */     this.commonDao.saveOrUpdate(jform_order_main_content);
/*      */ 
/*  592 */     CgFormHeadEntity jform_leave = 
/*  593 */       (CgFormHeadEntity)this.commonDao.findByProperty(
/*  593 */       CgFormHeadEntity.class, "tableName", "jform_leave").get(0);
/*  594 */     CgFormFieldEntity jform_leave_id = new CgFormFieldEntity();
/*  595 */     jform_leave_id.setFieldName("id");
/*  596 */     jform_leave_id.setTable(jform_leave);
/*  597 */     jform_leave_id.setFieldLength(Integer.valueOf(0));
/*  598 */     jform_leave_id.setIsKey("Y");
/*  599 */     jform_leave_id.setIsNull("N");
/*  600 */     jform_leave_id.setIsQuery("N");
/*  601 */     jform_leave_id.setIsShow("N");
/*  602 */     jform_leave_id.setIsShowList("N");
/*  603 */     jform_leave_id.setShowType("text");
/*  604 */     jform_leave_id.setLength(Integer.valueOf(36));
/*  605 */     jform_leave_id.setType("string");
/*  606 */     jform_leave_id.setOrderNum(Integer.valueOf(0));
/*  607 */     jform_leave_id.setPointLength(Integer.valueOf(0));
/*  608 */     jform_leave_id.setQueryMode("single");
/*  609 */     jform_leave_id.setContent("主键");
/*  610 */     jform_leave_id.setCreateBy("admin");
/*  611 */     jform_leave_id.setCreateDate(new Date());
/*  612 */     jform_leave_id.setCreateName("管理员");
/*  613 */     jform_leave_id.setDictField("");
/*  614 */     jform_leave_id.setDictTable("");
/*  615 */     jform_leave_id.setMainTable("");
/*  616 */     jform_leave_id.setMainField("");
/*  617 */     this.commonDao.saveOrUpdate(jform_leave_id);
/*      */ 
/*  619 */     CgFormFieldEntity jform_leave_title = new CgFormFieldEntity();
/*  620 */     jform_leave_title.setFieldName("title");
/*  621 */     jform_leave_title.setTable(jform_leave);
/*  622 */     jform_leave_title.setFieldLength(Integer.valueOf(0));
/*  623 */     jform_leave_title.setIsKey("N");
/*  624 */     jform_leave_title.setIsNull("N");
/*  625 */     jform_leave_title.setIsQuery("N");
/*  626 */     jform_leave_title.setIsShow("Y");
/*  627 */     jform_leave_title.setIsShowList("Y");
/*  628 */     jform_leave_title.setShowType("text");
/*  629 */     jform_leave_title.setLength(Integer.valueOf(50));
/*  630 */     jform_leave_title.setType("string");
/*  631 */     jform_leave_title.setOrderNum(Integer.valueOf(1));
/*  632 */     jform_leave_title.setPointLength(Integer.valueOf(0));
/*  633 */     jform_leave_title.setQueryMode("single");
/*  634 */     jform_leave_title.setContent("请假标题");
/*  635 */     jform_leave_title.setCreateBy("admin");
/*  636 */     jform_leave_title.setCreateDate(new Date());
/*  637 */     jform_leave_title.setCreateName("管理员");
/*  638 */     jform_leave_title.setDictField("");
/*  639 */     jform_leave_title.setDictTable("");
/*  640 */     jform_leave_title.setMainTable("");
/*  641 */     jform_leave_title.setMainField("");
/*  642 */     this.commonDao.saveOrUpdate(jform_leave_title);
/*      */ 
/*  644 */     CgFormFieldEntity jform_leave_people = new CgFormFieldEntity();
/*  645 */     jform_leave_people.setFieldName("people");
/*  646 */     jform_leave_people.setTable(jform_leave);
/*  647 */     jform_leave_people.setFieldLength(Integer.valueOf(0));
/*  648 */     jform_leave_people.setIsKey("N");
/*  649 */     jform_leave_people.setIsNull("N");
/*  650 */     jform_leave_people.setIsQuery("Y");
/*  651 */     jform_leave_people.setIsShow("Y");
/*  652 */     jform_leave_people.setIsShowList("Y");
/*  653 */     jform_leave_people.setShowType("text");
/*  654 */     jform_leave_people.setLength(Integer.valueOf(20));
/*  655 */     jform_leave_people.setType("string");
/*  656 */     jform_leave_people.setOrderNum(Integer.valueOf(2));
/*  657 */     jform_leave_people.setPointLength(Integer.valueOf(0));
/*  658 */     jform_leave_people.setQueryMode("single");
/*  659 */     jform_leave_people.setContent("请假人");
/*  660 */     jform_leave_people.setCreateBy("admin");
/*  661 */     jform_leave_people.setCreateDate(new Date());
/*  662 */     jform_leave_people.setCreateName("管理员");
/*  663 */     jform_leave_people.setDictField("");
/*  664 */     jform_leave_people.setDictTable("");
/*  665 */     jform_leave_people.setMainTable("");
/*  666 */     jform_leave_people.setMainField("");
/*  667 */     this.commonDao.saveOrUpdate(jform_leave_people);
/*      */ 
/*  669 */     CgFormFieldEntity jform_leave_sex = new CgFormFieldEntity();
/*  670 */     jform_leave_sex.setFieldName("sex");
/*  671 */     jform_leave_sex.setTable(jform_leave);
/*  672 */     jform_leave_sex.setFieldLength(Integer.valueOf(0));
/*  673 */     jform_leave_sex.setIsKey("N");
/*  674 */     jform_leave_sex.setIsNull("N");
/*  675 */     jform_leave_sex.setIsQuery("Y");
/*  676 */     jform_leave_sex.setIsShow("Y");
/*  677 */     jform_leave_sex.setIsShowList("Y");
/*  678 */     jform_leave_sex.setShowType("list");
/*  679 */     jform_leave_sex.setLength(Integer.valueOf(10));
/*  680 */     jform_leave_sex.setType("string");
/*  681 */     jform_leave_sex.setOrderNum(Integer.valueOf(3));
/*  682 */     jform_leave_sex.setPointLength(Integer.valueOf(0));
/*  683 */     jform_leave_sex.setQueryMode("single");
/*  684 */     jform_leave_sex.setContent("性别");
/*  685 */     jform_leave_sex.setCreateBy("admin");
/*  686 */     jform_leave_sex.setCreateDate(new Date());
/*  687 */     jform_leave_sex.setCreateName("管理员");
/*  688 */     jform_leave_sex.setDictField("sex");
/*  689 */     jform_leave_sex.setDictTable("");
/*  690 */     jform_leave_sex.setMainTable("");
/*  691 */     jform_leave_sex.setMainField("");
/*  692 */     this.commonDao.saveOrUpdate(jform_leave_sex);
/*      */ 
/*  694 */     CgFormFieldEntity jform_leave_begindate = new CgFormFieldEntity();
/*  695 */     jform_leave_begindate.setFieldName("begindate");
/*  696 */     jform_leave_begindate.setTable(jform_leave);
/*  697 */     jform_leave_begindate.setFieldLength(Integer.valueOf(0));
/*  698 */     jform_leave_begindate.setIsKey("N");
/*  699 */     jform_leave_begindate.setIsNull("N");
/*  700 */     jform_leave_begindate.setIsQuery("N");
/*  701 */     jform_leave_begindate.setIsShow("Y");
/*  702 */     jform_leave_begindate.setIsShowList("Y");
/*  703 */     jform_leave_begindate.setShowType("date");
/*  704 */     jform_leave_begindate.setLength(Integer.valueOf(0));
/*  705 */     jform_leave_begindate.setType("Date");
/*  706 */     jform_leave_begindate.setOrderNum(Integer.valueOf(4));
/*  707 */     jform_leave_begindate.setPointLength(Integer.valueOf(0));
/*  708 */     jform_leave_begindate.setQueryMode("group");
/*  709 */     jform_leave_begindate.setContent("请假开始时间");
/*  710 */     jform_leave_begindate.setCreateBy("admin");
/*  711 */     jform_leave_begindate.setCreateDate(new Date());
/*  712 */     jform_leave_begindate.setCreateName("管理员");
/*  713 */     jform_leave_begindate.setDictField("");
/*  714 */     jform_leave_begindate.setDictTable("");
/*  715 */     jform_leave_begindate.setMainTable("");
/*  716 */     jform_leave_begindate.setMainField("");
/*  717 */     this.commonDao.saveOrUpdate(jform_leave_begindate);
/*      */ 
/*  719 */     CgFormFieldEntity jform_leave_enddate = new CgFormFieldEntity();
/*  720 */     jform_leave_enddate.setFieldName("enddate");
/*  721 */     jform_leave_enddate.setTable(jform_leave);
/*  722 */     jform_leave_enddate.setFieldLength(Integer.valueOf(0));
/*  723 */     jform_leave_enddate.setIsKey("N");
/*  724 */     jform_leave_enddate.setIsNull("N");
/*  725 */     jform_leave_enddate.setIsQuery("N");
/*  726 */     jform_leave_enddate.setIsShow("Y");
/*  727 */     jform_leave_enddate.setIsShowList("Y");
/*  728 */     jform_leave_enddate.setShowType("datetime");
/*  729 */     jform_leave_enddate.setLength(Integer.valueOf(0));
/*  730 */     jform_leave_enddate.setType("Date");
/*  731 */     jform_leave_enddate.setOrderNum(Integer.valueOf(5));
/*  732 */     jform_leave_enddate.setPointLength(Integer.valueOf(0));
/*  733 */     jform_leave_enddate.setQueryMode("group");
/*  734 */     jform_leave_enddate.setContent("请假结束时间");
/*  735 */     jform_leave_enddate.setCreateBy("admin");
/*  736 */     jform_leave_enddate.setCreateDate(new Date());
/*  737 */     jform_leave_enddate.setCreateName("管理员");
/*  738 */     jform_leave_enddate.setDictField("");
/*  739 */     jform_leave_enddate.setDictTable("");
/*  740 */     jform_leave_enddate.setMainTable("");
/*  741 */     jform_leave_enddate.setMainField("");
/*  742 */     this.commonDao.saveOrUpdate(jform_leave_enddate);
/*      */ 
/*  744 */     CgFormFieldEntity jform_leave_hol_dept = new CgFormFieldEntity();
/*  745 */     jform_leave_hol_dept.setFieldName("hol_dept");
/*  746 */     jform_leave_hol_dept.setTable(jform_leave);
/*  747 */     jform_leave_hol_dept.setFieldLength(Integer.valueOf(0));
/*  748 */     jform_leave_hol_dept.setIsKey("N");
/*  749 */     jform_leave_hol_dept.setIsNull("N");
/*  750 */     jform_leave_hol_dept.setIsQuery("N");
/*  751 */     jform_leave_hol_dept.setIsShow("Y");
/*  752 */     jform_leave_hol_dept.setIsShowList("Y");
/*  753 */     jform_leave_hol_dept.setShowType("list");
/*  754 */     jform_leave_hol_dept.setLength(Integer.valueOf(32));
/*  755 */     jform_leave_hol_dept.setType("string");
/*  756 */     jform_leave_hol_dept.setOrderNum(Integer.valueOf(7));
/*  757 */     jform_leave_hol_dept.setPointLength(Integer.valueOf(0));
/*  758 */     jform_leave_hol_dept.setQueryMode("single");
/*  759 */     jform_leave_hol_dept.setContent("所属部门");
/*  760 */     jform_leave_hol_dept.setCreateBy("admin");
/*  761 */     jform_leave_hol_dept.setCreateDate(new Date());
/*  762 */     jform_leave_hol_dept.setCreateName("管理员");
/*  763 */     jform_leave_hol_dept.setDictField("id");
/*  764 */     jform_leave_hol_dept.setDictTable("t_s_depart");
/*  765 */     jform_leave_hol_dept.setDictText("departname");
/*  766 */     jform_leave_hol_dept.setMainTable("");
/*  767 */     jform_leave_hol_dept.setMainField("");
/*  768 */     this.commonDao.saveOrUpdate(jform_leave_hol_dept);
/*      */ 
/*  770 */     CgFormFieldEntity jform_leave_hol_reson = new CgFormFieldEntity();
/*  771 */     jform_leave_hol_reson.setFieldName("hol_reson");
/*  772 */     jform_leave_hol_reson.setTable(jform_leave);
/*  773 */     jform_leave_hol_reson.setFieldLength(Integer.valueOf(0));
/*  774 */     jform_leave_hol_reson.setIsKey("N");
/*  775 */     jform_leave_hol_reson.setIsNull("N");
/*  776 */     jform_leave_hol_reson.setIsQuery("N");
/*  777 */     jform_leave_hol_reson.setIsShow("Y");
/*  778 */     jform_leave_hol_reson.setIsShowList("Y");
/*  779 */     jform_leave_hol_reson.setShowType("text");
/*  780 */     jform_leave_hol_reson.setLength(Integer.valueOf(255));
/*  781 */     jform_leave_hol_reson.setType("string");
/*  782 */     jform_leave_hol_reson.setOrderNum(Integer.valueOf(8));
/*  783 */     jform_leave_hol_reson.setPointLength(Integer.valueOf(0));
/*  784 */     jform_leave_hol_reson.setQueryMode("single");
/*  785 */     jform_leave_hol_reson.setContent("请假原因");
/*  786 */     jform_leave_hol_reson.setCreateBy("admin");
/*  787 */     jform_leave_hol_reson.setCreateDate(new Date());
/*  788 */     jform_leave_hol_reson.setCreateName("管理员");
/*  789 */     jform_leave_hol_reson.setDictField("");
/*  790 */     jform_leave_hol_reson.setDictTable("");
/*  791 */     jform_leave_hol_reson.setMainTable("");
/*  792 */     jform_leave_hol_reson.setMainField("");
/*  793 */     this.commonDao.saveOrUpdate(jform_leave_hol_reson);
/*      */ 
/*  795 */     CgFormFieldEntity jform_leave_dep_leader = new CgFormFieldEntity();
/*  796 */     jform_leave_dep_leader.setFieldName("dep_leader");
/*  797 */     jform_leave_dep_leader.setTable(jform_leave);
/*  798 */     jform_leave_dep_leader.setFieldLength(Integer.valueOf(0));
/*  799 */     jform_leave_dep_leader.setIsKey("N");
/*  800 */     jform_leave_dep_leader.setIsNull("N");
/*  801 */     jform_leave_dep_leader.setIsQuery("N");
/*  802 */     jform_leave_dep_leader.setIsShow("Y");
/*  803 */     jform_leave_dep_leader.setIsShowList("Y");
/*  804 */     jform_leave_dep_leader.setShowType("text");
/*  805 */     jform_leave_dep_leader.setLength(Integer.valueOf(20));
/*  806 */     jform_leave_dep_leader.setType("string");
/*  807 */     jform_leave_dep_leader.setOrderNum(Integer.valueOf(9));
/*  808 */     jform_leave_dep_leader.setPointLength(Integer.valueOf(0));
/*  809 */     jform_leave_dep_leader.setQueryMode("single");
/*  810 */     jform_leave_dep_leader.setContent("部门审批人");
/*  811 */     jform_leave_dep_leader.setCreateBy("admin");
/*  812 */     jform_leave_dep_leader.setCreateDate(new Date());
/*  813 */     jform_leave_dep_leader.setCreateName("管理员");
/*  814 */     jform_leave_dep_leader.setDictField("");
/*  815 */     jform_leave_dep_leader.setDictTable("");
/*  816 */     jform_leave_dep_leader.setMainTable("");
/*  817 */     jform_leave_dep_leader.setMainField("");
/*  818 */     this.commonDao.saveOrUpdate(jform_leave_dep_leader);
/*      */ 
/*  820 */     CgFormFieldEntity jform_leave_content = new CgFormFieldEntity();
/*  821 */     jform_leave_content.setFieldName("content");
/*  822 */     jform_leave_content.setTable(jform_leave);
/*  823 */     jform_leave_content.setFieldLength(Integer.valueOf(0));
/*  824 */     jform_leave_content.setIsKey("N");
/*  825 */     jform_leave_content.setIsNull("N");
/*  826 */     jform_leave_content.setIsQuery("N");
/*  827 */     jform_leave_content.setIsShow("Y");
/*  828 */     jform_leave_content.setIsShowList("Y");
/*  829 */     jform_leave_content.setShowType("text");
/*  830 */     jform_leave_content.setLength(Integer.valueOf(255));
/*  831 */     jform_leave_content.setType("string");
/*  832 */     jform_leave_content.setOrderNum(Integer.valueOf(10));
/*  833 */     jform_leave_content.setPointLength(Integer.valueOf(0));
/*  834 */     jform_leave_content.setQueryMode("single");
/*  835 */     jform_leave_content.setContent("部门审批意见");
/*  836 */     jform_leave_content.setCreateBy("admin");
/*  837 */     jform_leave_content.setCreateDate(new Date());
/*  838 */     jform_leave_content.setCreateName("管理员");
/*  839 */     jform_leave_content.setDictField("");
/*  840 */     jform_leave_content.setDictTable("");
/*  841 */     jform_leave_content.setMainTable("");
/*  842 */     jform_leave_content.setMainField("");
/*  843 */     this.commonDao.saveOrUpdate(jform_leave_content);
/*      */ 
/*  845 */     CgFormFieldEntity jform_leave_day_num = new CgFormFieldEntity();
/*  846 */     jform_leave_day_num.setFieldName("day_num");
/*  847 */     jform_leave_day_num.setTable(jform_leave);
/*  848 */     jform_leave_day_num.setFieldLength(Integer.valueOf(120));
/*  849 */     jform_leave_day_num.setIsKey("N");
/*  850 */     jform_leave_day_num.setIsNull("Y");
/*  851 */     jform_leave_day_num.setIsQuery("N");
/*  852 */     jform_leave_day_num.setIsShow("Y");
/*  853 */     jform_leave_day_num.setIsShowList("Y");
/*  854 */     jform_leave_day_num.setShowType("text");
/*  855 */     jform_leave_day_num.setLength(Integer.valueOf(10));
/*  856 */     jform_leave_day_num.setType("int");
/*  857 */     jform_leave_day_num.setOrderNum(Integer.valueOf(6));
/*  858 */     jform_leave_day_num.setPointLength(Integer.valueOf(0));
/*  859 */     jform_leave_day_num.setQueryMode("single");
/*  860 */     jform_leave_day_num.setContent("请假天数");
/*  861 */     jform_leave_day_num.setCreateBy("admin");
/*  862 */     jform_leave_day_num.setCreateDate(new Date());
/*  863 */     jform_leave_day_num.setCreateName("管理员");
/*  864 */     jform_leave_day_num.setDictField("");
/*  865 */     jform_leave_day_num.setDictTable("");
/*  866 */     jform_leave_day_num.setMainTable("");
/*  867 */     jform_leave_day_num.setMainField("");
/*  868 */     this.commonDao.saveOrUpdate(jform_leave_day_num);
/*      */ 
/* 1264 */     CgFormHeadEntity jform_order_customer = 
/* 1266 */       (CgFormHeadEntity)this.commonDao.findByProperty(
/* 1265 */       CgFormHeadEntity.class, "tableName", "jform_order_customer")
/* 1266 */       .get(0);
/* 1267 */     CgFormFieldEntity jform_order_customer_id = new CgFormFieldEntity();
/* 1268 */     jform_order_customer_id.setFieldName("id");
/* 1269 */     jform_order_customer_id.setTable(jform_order_customer);
/* 1270 */     jform_order_customer_id.setFieldLength(Integer.valueOf(0));
/* 1271 */     jform_order_customer_id.setIsKey("Y");
/* 1272 */     jform_order_customer_id.setIsNull("N");
/* 1273 */     jform_order_customer_id.setIsQuery("N");
/* 1274 */     jform_order_customer_id.setIsShow("N");
/* 1275 */     jform_order_customer_id.setIsShowList("N");
/* 1276 */     jform_order_customer_id.setShowType("text");
/* 1277 */     jform_order_customer_id.setLength(Integer.valueOf(36));
/* 1278 */     jform_order_customer_id.setType("string");
/* 1279 */     jform_order_customer_id.setOrderNum(Integer.valueOf(0));
/* 1280 */     jform_order_customer_id.setPointLength(Integer.valueOf(0));
/* 1281 */     jform_order_customer_id.setQueryMode("single");
/* 1282 */     jform_order_customer_id.setContent("主键");
/* 1283 */     jform_order_customer_id.setCreateBy("admin");
/* 1284 */     jform_order_customer_id.setCreateDate(new Date());
/* 1285 */     jform_order_customer_id.setCreateName("管理员");
/* 1286 */     jform_order_customer_id.setDictField("");
/* 1287 */     jform_order_customer_id.setDictTable("");
/* 1288 */     jform_order_customer_id.setMainTable("");
/* 1289 */     jform_order_customer_id.setMainField("");
/* 1290 */     this.commonDao.saveOrUpdate(jform_order_customer_id);
/*      */ 
/* 1292 */     CgFormFieldEntity jform_order_customer_name = new CgFormFieldEntity();
/* 1293 */     jform_order_customer_name.setFieldName("name");
/* 1294 */     jform_order_customer_name.setTable(jform_order_customer);
/* 1295 */     jform_order_customer_name.setFieldLength(Integer.valueOf(0));
/* 1296 */     jform_order_customer_name.setIsKey("N");
/* 1297 */     jform_order_customer_name.setIsNull("Y");
/* 1298 */     jform_order_customer_name.setIsQuery("Y");
/* 1299 */     jform_order_customer_name.setIsShow("Y");
/* 1300 */     jform_order_customer_name.setIsShowList("Y");
/* 1301 */     jform_order_customer_name.setShowType("text");
/* 1302 */     jform_order_customer_name.setLength(Integer.valueOf(32));
/* 1303 */     jform_order_customer_name.setType("string");
/* 1304 */     jform_order_customer_name.setOrderNum(Integer.valueOf(1));
/* 1305 */     jform_order_customer_name.setPointLength(Integer.valueOf(0));
/* 1306 */     jform_order_customer_name.setQueryMode("single");
/* 1307 */     jform_order_customer_name.setContent("客户名");
/* 1308 */     jform_order_customer_name.setCreateBy("admin");
/* 1309 */     jform_order_customer_name.setCreateDate(new Date());
/* 1310 */     jform_order_customer_name.setCreateName("管理员");
/* 1311 */     jform_order_customer_name.setDictField("");
/* 1312 */     jform_order_customer_name.setDictTable("");
/* 1313 */     jform_order_customer_name.setMainTable("");
/* 1314 */     jform_order_customer_name.setMainField("");
/* 1315 */     this.commonDao.saveOrUpdate(jform_order_customer_name);
/*      */ 
/* 1317 */     CgFormFieldEntity jform_order_customer_money = new CgFormFieldEntity();
/* 1318 */     jform_order_customer_money.setFieldName("money");
/* 1319 */     jform_order_customer_money.setTable(jform_order_customer);
/* 1320 */     jform_order_customer_money.setFieldLength(Integer.valueOf(0));
/* 1321 */     jform_order_customer_money.setIsKey("N");
/* 1322 */     jform_order_customer_money.setIsNull("Y");
/* 1323 */     jform_order_customer_money.setIsQuery("Y");
/* 1324 */     jform_order_customer_money.setIsShow("Y");
/* 1325 */     jform_order_customer_money.setIsShowList("Y");
/* 1326 */     jform_order_customer_money.setShowType("text");
/* 1327 */     jform_order_customer_money.setLength(Integer.valueOf(10));
/* 1328 */     jform_order_customer_money.setType("double");
/* 1329 */     jform_order_customer_money.setOrderNum(Integer.valueOf(2));
/* 1330 */     jform_order_customer_money.setPointLength(Integer.valueOf(2));
/* 1331 */     jform_order_customer_money.setQueryMode("group");
/* 1332 */     jform_order_customer_money.setContent("单价");
/* 1333 */     jform_order_customer_money.setCreateBy("admin");
/* 1334 */     jform_order_customer_money.setCreateDate(new Date());
/* 1335 */     jform_order_customer_money.setCreateName("管理员");
/* 1336 */     jform_order_customer_money.setDictField("");
/* 1337 */     jform_order_customer_money.setDictTable("");
/* 1338 */     jform_order_customer_money.setMainTable("");
/* 1339 */     jform_order_customer_money.setMainField("");
/* 1340 */     this.commonDao.saveOrUpdate(jform_order_customer_money);
/*      */ 
/* 1342 */     CgFormFieldEntity jform_order_customer_fk_id = new CgFormFieldEntity();
/* 1343 */     jform_order_customer_fk_id.setFieldName("fk_id");
/* 1344 */     jform_order_customer_fk_id.setTable(jform_order_customer);
/* 1345 */     jform_order_customer_fk_id.setFieldLength(Integer.valueOf(120));
/* 1346 */     jform_order_customer_fk_id.setIsKey("N");
/* 1347 */     jform_order_customer_fk_id.setIsNull("N");
/* 1348 */     jform_order_customer_fk_id.setIsQuery("Y");
/* 1349 */     jform_order_customer_fk_id.setIsShow("N");
/* 1350 */     jform_order_customer_fk_id.setIsShowList("N");
/* 1351 */     jform_order_customer_fk_id.setShowType("text");
/* 1352 */     jform_order_customer_fk_id.setLength(Integer.valueOf(36));
/* 1353 */     jform_order_customer_fk_id.setType("string");
/* 1354 */     jform_order_customer_fk_id.setOrderNum(Integer.valueOf(5));
/* 1355 */     jform_order_customer_fk_id.setPointLength(Integer.valueOf(0));
/* 1356 */     jform_order_customer_fk_id.setQueryMode("single");
/* 1357 */     jform_order_customer_fk_id.setContent("外键");
/* 1358 */     jform_order_customer_fk_id.setCreateBy("admin");
/* 1359 */     jform_order_customer_fk_id.setCreateDate(new Date());
/* 1360 */     jform_order_customer_fk_id.setCreateName("管理员");
/* 1361 */     jform_order_customer_fk_id.setDictField("");
/* 1362 */     jform_order_customer_fk_id.setDictTable("");
/* 1363 */     jform_order_customer_fk_id.setMainTable("jform_order_main");
/* 1364 */     jform_order_customer_fk_id.setMainField("id");
/* 1365 */     this.commonDao.saveOrUpdate(jform_order_customer_fk_id);
/*      */ 
/* 1367 */     CgFormFieldEntity jform_order_customer_telphone = new CgFormFieldEntity();
/* 1368 */     jform_order_customer_telphone.setFieldName("telphone");
/* 1369 */     jform_order_customer_telphone.setTable(jform_order_customer);
/* 1370 */     jform_order_customer_telphone.setFieldLength(Integer.valueOf(120));
/* 1371 */     jform_order_customer_telphone.setIsKey("N");
/* 1372 */     jform_order_customer_telphone.setIsNull("Y");
/* 1373 */     jform_order_customer_telphone.setIsQuery("Y");
/* 1374 */     jform_order_customer_telphone.setIsShow("Y");
/* 1375 */     jform_order_customer_telphone.setIsShowList("Y");
/* 1376 */     jform_order_customer_telphone.setShowType("text");
/* 1377 */     jform_order_customer_telphone.setLength(Integer.valueOf(32));
/* 1378 */     jform_order_customer_telphone.setType("string");
/* 1379 */     jform_order_customer_telphone.setOrderNum(Integer.valueOf(4));
/* 1380 */     jform_order_customer_telphone.setPointLength(Integer.valueOf(0));
/* 1381 */     jform_order_customer_telphone.setQueryMode("single");
/* 1382 */     jform_order_customer_telphone.setContent("电话");
/* 1383 */     jform_order_customer_telphone.setCreateBy("admin");
/* 1384 */     jform_order_customer_telphone.setCreateDate(new Date());
/* 1385 */     jform_order_customer_telphone.setCreateName("管理员");
/* 1386 */     jform_order_customer_telphone.setDictField("");
/* 1387 */     jform_order_customer_telphone.setDictTable("");
/* 1388 */     jform_order_customer_telphone.setMainTable("");
/* 1389 */     jform_order_customer_telphone.setMainField("");
/* 1390 */     this.commonDao.saveOrUpdate(jform_order_customer_telphone);
/*      */ 
/* 1392 */     CgFormFieldEntity jform_order_customer_sex = new CgFormFieldEntity();
/* 1393 */     jform_order_customer_sex.setFieldName("sex");
/* 1394 */     jform_order_customer_sex.setTable(jform_order_customer);
/* 1395 */     jform_order_customer_sex.setFieldLength(Integer.valueOf(120));
/* 1396 */     jform_order_customer_sex.setIsKey("N");
/* 1397 */     jform_order_customer_sex.setIsNull("Y");
/* 1398 */     jform_order_customer_sex.setIsQuery("Y");
/* 1399 */     jform_order_customer_sex.setIsShow("Y");
/* 1400 */     jform_order_customer_sex.setIsShowList("Y");
/* 1401 */     jform_order_customer_sex.setShowType("select");
/* 1402 */     jform_order_customer_sex.setLength(Integer.valueOf(4));
/* 1403 */     jform_order_customer_sex.setType("string");
/* 1404 */     jform_order_customer_sex.setOrderNum(Integer.valueOf(3));
/* 1405 */     jform_order_customer_sex.setPointLength(Integer.valueOf(0));
/* 1406 */     jform_order_customer_sex.setQueryMode("single");
/* 1407 */     jform_order_customer_sex.setContent("性别");
/* 1408 */     jform_order_customer_sex.setCreateBy("admin");
/* 1409 */     jform_order_customer_sex.setCreateDate(new Date());
/* 1410 */     jform_order_customer_sex.setCreateName("管理员");
/* 1411 */     jform_order_customer_sex.setDictField("sex");
/* 1412 */     jform_order_customer_sex.setDictTable("");
/* 1413 */     jform_order_customer_sex.setMainTable("");
/* 1414 */     jform_order_customer_sex.setMainField("");
/* 1415 */     this.commonDao.saveOrUpdate(jform_order_customer_sex);
/*      */ 
/* 1418 */     CgFormHeadEntity jform_order_ticket = 
/* 1419 */       (CgFormHeadEntity)this.commonDao.findByProperty(
/* 1419 */       CgFormHeadEntity.class, "tableName", "jform_order_ticket").get(
/* 1420 */       0);
/* 1421 */     CgFormFieldEntity jform_order_ticket_id = new CgFormFieldEntity();
/* 1422 */     jform_order_ticket_id.setFieldName("id");
/* 1423 */     jform_order_ticket_id.setTable(jform_order_ticket);
/* 1424 */     jform_order_ticket_id.setFieldLength(Integer.valueOf(120));
/* 1425 */     jform_order_ticket_id.setIsKey("Y");
/* 1426 */     jform_order_ticket_id.setIsNull("N");
/* 1427 */     jform_order_ticket_id.setIsQuery("N");
/* 1428 */     jform_order_ticket_id.setIsShow("N");
/* 1429 */     jform_order_ticket_id.setIsShowList("N");
/* 1430 */     jform_order_ticket_id.setShowType("checkbox");
/* 1431 */     jform_order_ticket_id.setLength(Integer.valueOf(36));
/* 1432 */     jform_order_ticket_id.setType("string");
/* 1433 */     jform_order_ticket_id.setOrderNum(Integer.valueOf(0));
/* 1434 */     jform_order_ticket_id.setPointLength(Integer.valueOf(0));
/* 1435 */     jform_order_ticket_id.setQueryMode("single");
/* 1436 */     jform_order_ticket_id.setContent("主键");
/* 1437 */     jform_order_ticket_id.setCreateBy("admin");
/* 1438 */     jform_order_ticket_id.setCreateDate(new Date());
/* 1439 */     jform_order_ticket_id.setCreateName("管理员");
/* 1440 */     jform_order_ticket_id.setDictField("");
/* 1441 */     jform_order_ticket_id.setDictTable("");
/* 1442 */     jform_order_ticket_id.setMainTable("");
/* 1443 */     jform_order_ticket_id.setMainField("");
/* 1444 */     this.commonDao.saveOrUpdate(jform_order_ticket_id);
/*      */ 
/* 1446 */     CgFormFieldEntity jform_order_ticket_ticket_code = new CgFormFieldEntity();
/* 1447 */     jform_order_ticket_ticket_code.setFieldName("ticket_code");
/* 1448 */     jform_order_ticket_ticket_code.setTable(jform_order_ticket);
/* 1449 */     jform_order_ticket_ticket_code.setFieldLength(Integer.valueOf(120));
/* 1450 */     jform_order_ticket_ticket_code.setIsKey("N");
/* 1451 */     jform_order_ticket_ticket_code.setIsNull("N");
/* 1452 */     jform_order_ticket_ticket_code.setIsQuery("Y");
/* 1453 */     jform_order_ticket_ticket_code.setIsShow("Y");
/* 1454 */     jform_order_ticket_ticket_code.setIsShowList("Y");
/* 1455 */     jform_order_ticket_ticket_code.setShowType("text");
/* 1456 */     jform_order_ticket_ticket_code.setLength(Integer.valueOf(100));
/* 1457 */     jform_order_ticket_ticket_code.setType("string");
/* 1458 */     jform_order_ticket_ticket_code.setOrderNum(Integer.valueOf(1));
/* 1459 */     jform_order_ticket_ticket_code.setPointLength(Integer.valueOf(0));
/* 1460 */     jform_order_ticket_ticket_code.setQueryMode("single");
/* 1461 */     jform_order_ticket_ticket_code.setContent("航班号");
/* 1462 */     jform_order_ticket_ticket_code.setCreateBy("admin");
/* 1463 */     jform_order_ticket_ticket_code.setCreateDate(new Date());
/* 1464 */     jform_order_ticket_ticket_code.setCreateName("管理员");
/* 1465 */     jform_order_ticket_ticket_code.setDictField("");
/* 1466 */     jform_order_ticket_ticket_code.setDictTable("");
/* 1467 */     jform_order_ticket_ticket_code.setMainTable("");
/* 1468 */     jform_order_ticket_ticket_code.setMainField("");
/* 1469 */     this.commonDao.saveOrUpdate(jform_order_ticket_ticket_code);
/*      */ 
/* 1471 */     CgFormFieldEntity jform_order_ticket_tickect_date = new CgFormFieldEntity();
/* 1472 */     jform_order_ticket_tickect_date.setFieldName("tickect_date");
/* 1473 */     jform_order_ticket_tickect_date.setTable(jform_order_ticket);
/* 1474 */     jform_order_ticket_tickect_date.setFieldLength(Integer.valueOf(120));
/* 1475 */     jform_order_ticket_tickect_date.setIsKey("N");
/* 1476 */     jform_order_ticket_tickect_date.setIsNull("N");
/* 1477 */     jform_order_ticket_tickect_date.setIsQuery("Y");
/* 1478 */     jform_order_ticket_tickect_date.setIsShow("Y");
/* 1479 */     jform_order_ticket_tickect_date.setIsShowList("Y");
/* 1480 */     jform_order_ticket_tickect_date.setShowType("datetime");
/* 1481 */     jform_order_ticket_tickect_date.setLength(Integer.valueOf(10));
/* 1482 */     jform_order_ticket_tickect_date.setType("Date");
/* 1483 */     jform_order_ticket_tickect_date.setOrderNum(Integer.valueOf(2));
/* 1484 */     jform_order_ticket_tickect_date.setPointLength(Integer.valueOf(0));
/* 1485 */     jform_order_ticket_tickect_date.setQueryMode("single");
/* 1486 */     jform_order_ticket_tickect_date.setContent("航班时间");
/* 1487 */     jform_order_ticket_tickect_date.setCreateBy("admin");
/* 1488 */     jform_order_ticket_tickect_date.setCreateDate(new Date());
/* 1489 */     jform_order_ticket_tickect_date.setCreateName("管理员");
/* 1490 */     jform_order_ticket_tickect_date.setDictField("");
/* 1491 */     jform_order_ticket_tickect_date.setDictTable("");
/* 1492 */     jform_order_ticket_tickect_date.setMainTable("");
/* 1493 */     jform_order_ticket_tickect_date.setMainField("");
/* 1494 */     this.commonDao.saveOrUpdate(jform_order_ticket_tickect_date);
/*      */ 
/* 1496 */     CgFormFieldEntity jform_order_ticket_fck_id = new CgFormFieldEntity();
/* 1497 */     jform_order_ticket_fck_id.setFieldName("fck_id");
/* 1498 */     jform_order_ticket_fck_id.setTable(jform_order_ticket);
/* 1499 */     jform_order_ticket_fck_id.setFieldLength(Integer.valueOf(120));
/* 1500 */     jform_order_ticket_fck_id.setIsKey("N");
/* 1501 */     jform_order_ticket_fck_id.setIsNull("N");
/* 1502 */     jform_order_ticket_fck_id.setIsQuery("N");
/* 1503 */     jform_order_ticket_fck_id.setIsShow("N");
/* 1504 */     jform_order_ticket_fck_id.setIsShowList("N");
/* 1505 */     jform_order_ticket_fck_id.setShowType("text");
/* 1506 */     jform_order_ticket_fck_id.setLength(Integer.valueOf(36));
/* 1507 */     jform_order_ticket_fck_id.setType("string");
/* 1508 */     jform_order_ticket_fck_id.setOrderNum(Integer.valueOf(3));
/* 1509 */     jform_order_ticket_fck_id.setPointLength(Integer.valueOf(0));
/* 1510 */     jform_order_ticket_fck_id.setQueryMode("single");
/* 1511 */     jform_order_ticket_fck_id.setContent("外键");
/* 1512 */     jform_order_ticket_fck_id.setCreateBy("admin");
/* 1513 */     jform_order_ticket_fck_id.setCreateDate(new Date());
/* 1514 */     jform_order_ticket_fck_id.setCreateName("管理员");
/* 1515 */     jform_order_ticket_fck_id.setDictField("");
/* 1516 */     jform_order_ticket_fck_id.setDictTable("");
/* 1517 */     jform_order_ticket_fck_id.setMainTable("jform_order_main");
/* 1518 */     jform_order_ticket_fck_id.setMainField("id");
/* 1519 */     this.commonDao.saveOrUpdate(jform_order_ticket_fck_id);
/*      */ 
/* 1522 */     CgFormHeadEntity jform_price1 = 
/* 1523 */       (CgFormHeadEntity)this.commonDao.findByProperty(
/* 1523 */       CgFormHeadEntity.class, "tableName", "jform_price1").get(0);
/* 1524 */     CgFormFieldEntity jform_price1_id = new CgFormFieldEntity();
/* 1525 */     jform_price1_id.setFieldName("id");
/* 1526 */     jform_price1_id.setTable(jform_price1);
/* 1527 */     jform_price1_id.setFieldLength(Integer.valueOf(0));
/* 1528 */     jform_price1_id.setIsKey("Y");
/* 1529 */     jform_price1_id.setIsNull("N");
/* 1530 */     jform_price1_id.setIsQuery("N");
/* 1531 */     jform_price1_id.setIsShow("N");
/* 1532 */     jform_price1_id.setIsShowList("N");
/* 1533 */     jform_price1_id.setShowType("text");
/* 1534 */     jform_price1_id.setLength(Integer.valueOf(36));
/* 1535 */     jform_price1_id.setType("string");
/* 1536 */     jform_price1_id.setOrderNum(Integer.valueOf(0));
/* 1537 */     jform_price1_id.setPointLength(Integer.valueOf(0));
/* 1538 */     jform_price1_id.setQueryMode("single");
/* 1539 */     jform_price1_id.setContent("主键");
/* 1540 */     jform_price1_id.setCreateBy("admin");
/* 1541 */     jform_price1_id.setCreateDate(new Date());
/* 1542 */     jform_price1_id.setCreateName("管理员");
/* 1543 */     jform_price1_id.setDictField("");
/* 1544 */     jform_price1_id.setDictTable("");
/* 1545 */     jform_price1_id.setMainTable("");
/* 1546 */     jform_price1_id.setMainField("");
/* 1547 */     this.commonDao.saveOrUpdate(jform_price1_id);
/*      */ 
/* 1549 */     CgFormFieldEntity jform_price1_a = new CgFormFieldEntity();
/* 1550 */     jform_price1_a.setFieldName("a");
/* 1551 */     jform_price1_a.setTable(jform_price1);
/* 1552 */     jform_price1_a.setFieldLength(Integer.valueOf(0));
/* 1553 */     jform_price1_a.setIsKey("N");
/* 1554 */     jform_price1_a.setIsNull("N");
/* 1555 */     jform_price1_a.setIsQuery("Y");
/* 1556 */     jform_price1_a.setIsShow("Y");
/* 1557 */     jform_price1_a.setIsShowList("Y");
/* 1558 */     jform_price1_a.setShowType("text");
/* 1559 */     jform_price1_a.setLength(Integer.valueOf(10));
/* 1560 */     jform_price1_a.setType("double");
/* 1561 */     jform_price1_a.setOrderNum(Integer.valueOf(1));
/* 1562 */     jform_price1_a.setPointLength(Integer.valueOf(2));
/* 1563 */     jform_price1_a.setQueryMode("group");
/* 1564 */     jform_price1_a.setContent("机构合计");
/* 1565 */     jform_price1_a.setCreateBy("admin");
/* 1566 */     jform_price1_a.setCreateDate(new Date());
/* 1567 */     jform_price1_a.setCreateName("管理员");
/* 1568 */     jform_price1_a.setDictField("");
/* 1569 */     jform_price1_a.setDictTable("");
/* 1570 */     jform_price1_a.setMainTable("");
/* 1571 */     jform_price1_a.setMainField("");
/* 1572 */     this.commonDao.saveOrUpdate(jform_price1_a);
/*      */ 
/* 1574 */     CgFormFieldEntity jform_price1_b1 = new CgFormFieldEntity();
/* 1575 */     jform_price1_b1.setFieldName("b1");
/* 1576 */     jform_price1_b1.setTable(jform_price1);
/* 1577 */     jform_price1_b1.setFieldLength(Integer.valueOf(0));
/* 1578 */     jform_price1_b1.setIsKey("N");
/* 1579 */     jform_price1_b1.setIsNull("N");
/* 1580 */     jform_price1_b1.setIsQuery("N");
/* 1581 */     jform_price1_b1.setIsShow("Y");
/* 1582 */     jform_price1_b1.setIsShowList("Y");
/* 1583 */     jform_price1_b1.setShowType("text");
/* 1584 */     jform_price1_b1.setLength(Integer.valueOf(10));
/* 1585 */     jform_price1_b1.setType("double");
/* 1586 */     jform_price1_b1.setOrderNum(Integer.valueOf(2));
/* 1587 */     jform_price1_b1.setPointLength(Integer.valueOf(2));
/* 1588 */     jform_price1_b1.setQueryMode("group");
/* 1589 */     jform_price1_b1.setContent("行政小计");
/* 1590 */     jform_price1_b1.setCreateBy("admin");
/* 1591 */     jform_price1_b1.setCreateDate(new Date());
/* 1592 */     jform_price1_b1.setCreateName("管理员");
/* 1593 */     jform_price1_b1.setDictField("");
/* 1594 */     jform_price1_b1.setDictTable("");
/* 1595 */     jform_price1_b1.setMainTable("");
/* 1596 */     jform_price1_b1.setMainField("");
/* 1597 */     this.commonDao.saveOrUpdate(jform_price1_b1);
/*      */ 
/* 1599 */     CgFormFieldEntity jform_price1_b11 = new CgFormFieldEntity();
/* 1600 */     jform_price1_b11.setFieldName("b11");
/* 1601 */     jform_price1_b11.setTable(jform_price1);
/* 1602 */     jform_price1_b11.setFieldLength(Integer.valueOf(0));
/* 1603 */     jform_price1_b11.setIsKey("N");
/* 1604 */     jform_price1_b11.setIsNull("N");
/* 1605 */     jform_price1_b11.setIsQuery("N");
/* 1606 */     jform_price1_b11.setIsShow("Y");
/* 1607 */     jform_price1_b11.setIsShowList("Y");
/* 1608 */     jform_price1_b11.setShowType("text");
/* 1609 */     jform_price1_b11.setLength(Integer.valueOf(100));
/* 1610 */     jform_price1_b11.setType("string");
/* 1611 */     jform_price1_b11.setOrderNum(Integer.valueOf(3));
/* 1612 */     jform_price1_b11.setPointLength(Integer.valueOf(0));
/* 1613 */     jform_price1_b11.setQueryMode("group");
/* 1614 */     jform_price1_b11.setContent("行政省");
/* 1615 */     jform_price1_b11.setCreateBy("admin");
/* 1616 */     jform_price1_b11.setCreateDate(new Date());
/* 1617 */     jform_price1_b11.setCreateName("管理员");
/* 1618 */     jform_price1_b11.setDictField("");
/* 1619 */     jform_price1_b11.setDictTable("");
/* 1620 */     jform_price1_b11.setMainTable("");
/* 1621 */     jform_price1_b11.setMainField("");
/* 1622 */     this.commonDao.saveOrUpdate(jform_price1_b11);
/*      */ 
/* 1624 */     CgFormFieldEntity jform_price1_b12 = new CgFormFieldEntity();
/* 1625 */     jform_price1_b12.setFieldName("b12");
/* 1626 */     jform_price1_b12.setTable(jform_price1);
/* 1627 */     jform_price1_b12.setFieldLength(Integer.valueOf(0));
/* 1628 */     jform_price1_b12.setIsKey("N");
/* 1629 */     jform_price1_b12.setIsNull("N");
/* 1630 */     jform_price1_b12.setIsQuery("N");
/* 1631 */     jform_price1_b12.setIsShow("Y");
/* 1632 */     jform_price1_b12.setIsShowList("Y");
/* 1633 */     jform_price1_b12.setShowType("text");
/* 1634 */     jform_price1_b12.setLength(Integer.valueOf(100));
/* 1635 */     jform_price1_b12.setType("string");
/* 1636 */     jform_price1_b12.setOrderNum(Integer.valueOf(4));
/* 1637 */     jform_price1_b12.setPointLength(Integer.valueOf(0));
/* 1638 */     jform_price1_b12.setQueryMode("group");
/* 1639 */     jform_price1_b12.setContent("行政市");
/* 1640 */     jform_price1_b12.setCreateBy("admin");
/* 1641 */     jform_price1_b12.setCreateDate(new Date());
/* 1642 */     jform_price1_b12.setCreateName("管理员");
/* 1643 */     jform_price1_b12.setDictField("");
/* 1644 */     jform_price1_b12.setDictTable("");
/* 1645 */     jform_price1_b12.setMainTable("");
/* 1646 */     jform_price1_b12.setMainField("");
/* 1647 */     this.commonDao.saveOrUpdate(jform_price1_b12);
/*      */ 
/* 1649 */     CgFormFieldEntity jform_price1_b13 = new CgFormFieldEntity();
/* 1650 */     jform_price1_b13.setFieldName("b13");
/* 1651 */     jform_price1_b13.setTable(jform_price1);
/* 1652 */     jform_price1_b13.setFieldLength(Integer.valueOf(0));
/* 1653 */     jform_price1_b13.setIsKey("N");
/* 1654 */     jform_price1_b13.setIsNull("N");
/* 1655 */     jform_price1_b13.setIsQuery("N");
/* 1656 */     jform_price1_b13.setIsShow("Y");
/* 1657 */     jform_price1_b13.setIsShowList("Y");
/* 1658 */     jform_price1_b13.setShowType("text");
/* 1659 */     jform_price1_b13.setLength(Integer.valueOf(100));
/* 1660 */     jform_price1_b13.setType("string");
/* 1661 */     jform_price1_b13.setOrderNum(Integer.valueOf(5));
/* 1662 */     jform_price1_b13.setPointLength(Integer.valueOf(0));
/* 1663 */     jform_price1_b13.setQueryMode("single");
/* 1664 */     jform_price1_b13.setContent("行政县");
/* 1665 */     jform_price1_b13.setCreateBy("admin");
/* 1666 */     jform_price1_b13.setCreateDate(new Date());
/* 1667 */     jform_price1_b13.setCreateName("管理员");
/* 1668 */     jform_price1_b13.setDictField("");
/* 1669 */     jform_price1_b13.setDictTable("");
/* 1670 */     jform_price1_b13.setMainTable("");
/* 1671 */     jform_price1_b13.setMainField("");
/* 1672 */     this.commonDao.saveOrUpdate(jform_price1_b13);
/*      */ 
/* 1674 */     CgFormFieldEntity jform_price1_b2 = new CgFormFieldEntity();
/* 1675 */     jform_price1_b2.setFieldName("b2");
/* 1676 */     jform_price1_b2.setTable(jform_price1);
/* 1677 */     jform_price1_b2.setFieldLength(Integer.valueOf(0));
/* 1678 */     jform_price1_b2.setIsKey("N");
/* 1679 */     jform_price1_b2.setIsNull("N");
/* 1680 */     jform_price1_b2.setIsQuery("N");
/* 1681 */     jform_price1_b2.setIsShow("Y");
/* 1682 */     jform_price1_b2.setIsShowList("Y");
/* 1683 */     jform_price1_b2.setShowType("text");
/* 1684 */     jform_price1_b2.setLength(Integer.valueOf(10));
/* 1685 */     jform_price1_b2.setType("double");
/* 1686 */     jform_price1_b2.setOrderNum(Integer.valueOf(6));
/* 1687 */     jform_price1_b2.setPointLength(Integer.valueOf(2));
/* 1688 */     jform_price1_b2.setQueryMode("single");
/* 1689 */     jform_price1_b2.setContent("事业合计");
/* 1690 */     jform_price1_b2.setCreateBy("admin");
/* 1691 */     jform_price1_b2.setCreateDate(new Date());
/* 1692 */     jform_price1_b2.setCreateName("管理员");
/* 1693 */     jform_price1_b2.setDictField("");
/* 1694 */     jform_price1_b2.setDictTable("");
/* 1695 */     jform_price1_b2.setMainTable("");
/* 1696 */     jform_price1_b2.setMainField("");
/* 1697 */     this.commonDao.saveOrUpdate(jform_price1_b2);
/*      */ 
/* 1699 */     CgFormFieldEntity jform_price1_b3 = new CgFormFieldEntity();
/* 1700 */     jform_price1_b3.setFieldName("b3");
/* 1701 */     jform_price1_b3.setTable(jform_price1);
/* 1702 */     jform_price1_b3.setFieldLength(Integer.valueOf(0));
/* 1703 */     jform_price1_b3.setIsKey("N");
/* 1704 */     jform_price1_b3.setIsNull("N");
/* 1705 */     jform_price1_b3.setIsQuery("N");
/* 1706 */     jform_price1_b3.setIsShow("Y");
/* 1707 */     jform_price1_b3.setIsShowList("Y");
/* 1708 */     jform_price1_b3.setShowType("text");
/* 1709 */     jform_price1_b3.setLength(Integer.valueOf(10));
/* 1710 */     jform_price1_b3.setType("double");
/* 1711 */     jform_price1_b3.setOrderNum(Integer.valueOf(7));
/* 1712 */     jform_price1_b3.setPointLength(Integer.valueOf(2));
/* 1713 */     jform_price1_b3.setQueryMode("single");
/* 1714 */     jform_price1_b3.setContent("参公小计");
/* 1715 */     jform_price1_b3.setCreateBy("admin");
/* 1716 */     jform_price1_b3.setCreateDate(new Date());
/* 1717 */     jform_price1_b3.setCreateName("管理员");
/* 1718 */     jform_price1_b3.setDictField("");
/* 1719 */     jform_price1_b3.setDictTable("");
/* 1720 */     jform_price1_b3.setMainTable("");
/* 1721 */     jform_price1_b3.setMainField("");
/* 1722 */     this.commonDao.saveOrUpdate(jform_price1_b3);
/*      */ 
/* 1724 */     CgFormFieldEntity jform_price1_b31 = new CgFormFieldEntity();
/* 1725 */     jform_price1_b31.setFieldName("b31");
/* 1726 */     jform_price1_b31.setTable(jform_price1);
/* 1727 */     jform_price1_b31.setFieldLength(Integer.valueOf(0));
/* 1728 */     jform_price1_b31.setIsKey("N");
/* 1729 */     jform_price1_b31.setIsNull("N");
/* 1730 */     jform_price1_b31.setIsQuery("N");
/* 1731 */     jform_price1_b31.setIsShow("Y");
/* 1732 */     jform_price1_b31.setIsShowList("Y");
/* 1733 */     jform_price1_b31.setShowType("text");
/* 1734 */     jform_price1_b31.setLength(Integer.valueOf(100));
/* 1735 */     jform_price1_b31.setType("string");
/* 1736 */     jform_price1_b31.setOrderNum(Integer.valueOf(8));
/* 1737 */     jform_price1_b31.setPointLength(Integer.valueOf(0));
/* 1738 */     jform_price1_b31.setQueryMode("single");
/* 1739 */     jform_price1_b31.setContent("参公省");
/* 1740 */     jform_price1_b31.setCreateBy("admin");
/* 1741 */     jform_price1_b31.setCreateDate(new Date());
/* 1742 */     jform_price1_b31.setCreateName("管理员");
/* 1743 */     jform_price1_b31.setDictField("");
/* 1744 */     jform_price1_b31.setDictTable("");
/* 1745 */     jform_price1_b31.setMainTable("");
/* 1746 */     jform_price1_b31.setMainField("");
/* 1747 */     this.commonDao.saveOrUpdate(jform_price1_b31);
/*      */ 
/* 1749 */     CgFormFieldEntity jform_price1_b32 = new CgFormFieldEntity();
/* 1750 */     jform_price1_b32.setFieldName("b32");
/* 1751 */     jform_price1_b32.setTable(jform_price1);
/* 1752 */     jform_price1_b32.setFieldLength(Integer.valueOf(0));
/* 1753 */     jform_price1_b32.setIsKey("N");
/* 1754 */     jform_price1_b32.setIsNull("N");
/* 1755 */     jform_price1_b32.setIsQuery("N");
/* 1756 */     jform_price1_b32.setIsShow("Y");
/* 1757 */     jform_price1_b32.setIsShowList("Y");
/* 1758 */     jform_price1_b32.setShowType("text");
/* 1759 */     jform_price1_b32.setLength(Integer.valueOf(100));
/* 1760 */     jform_price1_b32.setType("string");
/* 1761 */     jform_price1_b32.setOrderNum(Integer.valueOf(9));
/* 1762 */     jform_price1_b32.setPointLength(Integer.valueOf(0));
/* 1763 */     jform_price1_b32.setQueryMode("single");
/* 1764 */     jform_price1_b32.setContent("参公市");
/* 1765 */     jform_price1_b32.setCreateBy("admin");
/* 1766 */     jform_price1_b32.setCreateDate(new Date());
/* 1767 */     jform_price1_b32.setCreateName("管理员");
/* 1768 */     jform_price1_b32.setDictField("");
/* 1769 */     jform_price1_b32.setDictTable("");
/* 1770 */     jform_price1_b32.setMainTable("");
/* 1771 */     jform_price1_b32.setMainField("");
/* 1772 */     this.commonDao.saveOrUpdate(jform_price1_b32);
/*      */ 
/* 1774 */     CgFormFieldEntity jform_price1_b33 = new CgFormFieldEntity();
/* 1775 */     jform_price1_b33.setFieldName("b33");
/* 1776 */     jform_price1_b33.setTable(jform_price1);
/* 1777 */     jform_price1_b33.setFieldLength(Integer.valueOf(0));
/* 1778 */     jform_price1_b33.setIsKey("N");
/* 1779 */     jform_price1_b33.setIsNull("N");
/* 1780 */     jform_price1_b33.setIsQuery("N");
/* 1781 */     jform_price1_b33.setIsShow("Y");
/* 1782 */     jform_price1_b33.setIsShowList("Y");
/* 1783 */     jform_price1_b33.setShowType("text");
/* 1784 */     jform_price1_b33.setLength(Integer.valueOf(100));
/* 1785 */     jform_price1_b33.setType("string");
/* 1786 */     jform_price1_b33.setOrderNum(Integer.valueOf(10));
/* 1787 */     jform_price1_b33.setPointLength(Integer.valueOf(0));
/* 1788 */     jform_price1_b33.setQueryMode("single");
/* 1789 */     jform_price1_b33.setContent("参公县");
/* 1790 */     jform_price1_b33.setCreateBy("admin");
/* 1791 */     jform_price1_b33.setCreateDate(new Date());
/* 1792 */     jform_price1_b33.setCreateName("管理员");
/* 1793 */     jform_price1_b33.setDictField("");
/* 1794 */     jform_price1_b33.setDictTable("");
/* 1795 */     jform_price1_b33.setMainTable("");
/* 1796 */     jform_price1_b33.setMainField("");
/* 1797 */     this.commonDao.saveOrUpdate(jform_price1_b33);
/*      */ 
/* 1799 */     CgFormFieldEntity jform_price1_c1 = new CgFormFieldEntity();
/* 1800 */     jform_price1_c1.setFieldName("c1");
/* 1801 */     jform_price1_c1.setTable(jform_price1);
/* 1802 */     jform_price1_c1.setFieldLength(Integer.valueOf(0));
/* 1803 */     jform_price1_c1.setIsKey("N");
/* 1804 */     jform_price1_c1.setIsNull("N");
/* 1805 */     jform_price1_c1.setIsQuery("N");
/* 1806 */     jform_price1_c1.setIsShow("Y");
/* 1807 */     jform_price1_c1.setIsShowList("Y");
/* 1808 */     jform_price1_c1.setShowType("text");
/* 1809 */     jform_price1_c1.setLength(Integer.valueOf(10));
/* 1810 */     jform_price1_c1.setType("double");
/* 1811 */     jform_price1_c1.setOrderNum(Integer.valueOf(11));
/* 1812 */     jform_price1_c1.setPointLength(Integer.valueOf(2));
/* 1813 */     jform_price1_c1.setQueryMode("single");
/* 1814 */     jform_price1_c1.setContent("全额拨款");
/* 1815 */     jform_price1_c1.setCreateBy("admin");
/* 1816 */     jform_price1_c1.setCreateDate(new Date());
/* 1817 */     jform_price1_c1.setCreateName("管理员");
/* 1818 */     jform_price1_c1.setDictField("");
/* 1819 */     jform_price1_c1.setDictTable("");
/* 1820 */     jform_price1_c1.setMainTable("");
/* 1821 */     jform_price1_c1.setMainField("");
/* 1822 */     this.commonDao.saveOrUpdate(jform_price1_c1);
/*      */ 
/* 1824 */     CgFormFieldEntity jform_price1_c2 = new CgFormFieldEntity();
/* 1825 */     jform_price1_c2.setFieldName("c2");
/* 1826 */     jform_price1_c2.setTable(jform_price1);
/* 1827 */     jform_price1_c2.setFieldLength(Integer.valueOf(0));
/* 1828 */     jform_price1_c2.setIsKey("N");
/* 1829 */     jform_price1_c2.setIsNull("N");
/* 1830 */     jform_price1_c2.setIsQuery("N");
/* 1831 */     jform_price1_c2.setIsShow("Y");
/* 1832 */     jform_price1_c2.setIsShowList("Y");
/* 1833 */     jform_price1_c2.setShowType("text");
/* 1834 */     jform_price1_c2.setLength(Integer.valueOf(10));
/* 1835 */     jform_price1_c2.setType("double");
/* 1836 */     jform_price1_c2.setOrderNum(Integer.valueOf(12));
/* 1837 */     jform_price1_c2.setPointLength(Integer.valueOf(2));
/* 1838 */     jform_price1_c2.setQueryMode("single");
/* 1839 */     jform_price1_c2.setContent("差额拨款");
/* 1840 */     jform_price1_c2.setCreateBy("admin");
/* 1841 */     jform_price1_c2.setCreateDate(new Date());
/* 1842 */     jform_price1_c2.setCreateName("管理员");
/* 1843 */     jform_price1_c2.setDictField("");
/* 1844 */     jform_price1_c2.setDictTable("");
/* 1845 */     jform_price1_c2.setMainTable("");
/* 1846 */     jform_price1_c2.setMainField("");
/* 1847 */     this.commonDao.saveOrUpdate(jform_price1_c2);
/*      */ 
/* 1849 */     CgFormFieldEntity jform_price1_c3 = new CgFormFieldEntity();
/* 1850 */     jform_price1_c3.setFieldName("c3");
/* 1851 */     jform_price1_c3.setTable(jform_price1);
/* 1852 */     jform_price1_c3.setFieldLength(Integer.valueOf(0));
/* 1853 */     jform_price1_c3.setIsKey("N");
/* 1854 */     jform_price1_c3.setIsNull("N");
/* 1855 */     jform_price1_c3.setIsQuery("N");
/* 1856 */     jform_price1_c3.setIsShow("Y");
/* 1857 */     jform_price1_c3.setIsShowList("Y");
/* 1858 */     jform_price1_c3.setShowType("text");
/* 1859 */     jform_price1_c3.setLength(Integer.valueOf(10));
/* 1860 */     jform_price1_c3.setType("double");
/* 1861 */     jform_price1_c3.setOrderNum(Integer.valueOf(13));
/* 1862 */     jform_price1_c3.setPointLength(Integer.valueOf(2));
/* 1863 */     jform_price1_c3.setQueryMode("single");
/* 1864 */     jform_price1_c3.setContent("自收自支");
/* 1865 */     jform_price1_c3.setCreateBy("admin");
/* 1866 */     jform_price1_c3.setCreateDate(new Date());
/* 1867 */     jform_price1_c3.setCreateName("管理员");
/* 1868 */     jform_price1_c3.setDictField("");
/* 1869 */     jform_price1_c3.setDictTable("");
/* 1870 */     jform_price1_c3.setMainTable("");
/* 1871 */     jform_price1_c3.setMainField("");
/* 1872 */     this.commonDao.saveOrUpdate(jform_price1_c3);
/*      */ 
/* 1874 */     CgFormFieldEntity jform_price1_d = new CgFormFieldEntity();
/* 1875 */     jform_price1_d.setFieldName("d");
/* 1876 */     jform_price1_d.setTable(jform_price1);
/* 1877 */     jform_price1_d.setFieldLength(Integer.valueOf(0));
/* 1878 */     jform_price1_d.setIsKey("N");
/* 1879 */     jform_price1_d.setIsNull("N");
/* 1880 */     jform_price1_d.setIsQuery("Y");
/* 1881 */     jform_price1_d.setIsShow("Y");
/* 1882 */     jform_price1_d.setIsShowList("Y");
/* 1883 */     jform_price1_d.setShowType("text");
/* 1884 */     jform_price1_d.setLength(Integer.valueOf(10));
/* 1885 */     jform_price1_d.setType("int");
/* 1886 */     jform_price1_d.setOrderNum(Integer.valueOf(14));
/* 1887 */     jform_price1_d.setPointLength(Integer.valueOf(2));
/* 1888 */     jform_price1_d.setQueryMode("single");
/* 1889 */     jform_price1_d.setContent("经费合计");
/* 1890 */     jform_price1_d.setCreateBy("admin");
/* 1891 */     jform_price1_d.setCreateDate(new Date());
/* 1892 */     jform_price1_d.setCreateName("管理员");
/* 1893 */     jform_price1_d.setDictField("");
/* 1894 */     jform_price1_d.setDictTable("");
/* 1895 */     jform_price1_d.setMainTable("");
/* 1896 */     jform_price1_d.setMainField("");
/* 1897 */     this.commonDao.saveOrUpdate(jform_price1_d);
/*      */ 
/* 1899 */     CgFormFieldEntity jform_price1_d1 = new CgFormFieldEntity();
/* 1900 */     jform_price1_d1.setFieldName("d1");
/* 1901 */     jform_price1_d1.setTable(jform_price1);
/* 1902 */     jform_price1_d1.setFieldLength(Integer.valueOf(0));
/* 1903 */     jform_price1_d1.setIsKey("N");
/* 1904 */     jform_price1_d1.setIsNull("N");
/* 1905 */     jform_price1_d1.setIsQuery("N");
/* 1906 */     jform_price1_d1.setIsShow("Y");
/* 1907 */     jform_price1_d1.setIsShowList("Y");
/* 1908 */     jform_price1_d1.setShowType("text");
/* 1909 */     jform_price1_d1.setLength(Integer.valueOf(1000));
/* 1910 */     jform_price1_d1.setType("string");
/* 1911 */     jform_price1_d1.setOrderNum(Integer.valueOf(15));
/* 1912 */     jform_price1_d1.setPointLength(Integer.valueOf(0));
/* 1913 */     jform_price1_d1.setQueryMode("single");
/* 1914 */     jform_price1_d1.setContent("机构资质");
/* 1915 */     jform_price1_d1.setCreateBy("admin");
/* 1916 */     jform_price1_d1.setCreateDate(new Date());
/* 1917 */     jform_price1_d1.setCreateName("管理员");
/* 1918 */     jform_price1_d1.setDictField("");
/* 1919 */     jform_price1_d1.setDictTable("");
/* 1920 */     jform_price1_d1.setMainTable("");
/* 1921 */     jform_price1_d1.setMainField("");
/* 1922 */     this.commonDao.saveOrUpdate(jform_price1_d1);
/*      */   }
/*      */ 
/*      */   private void repairCkEditor()
/*      */   {
/* 1931 */     CKEditorEntity ckEditor = new CKEditorEntity();
/* 1932 */     String str = "<html><head><title></title><link href='plug-in/easyui/themes/default/easyui.css' id='easyuiTheme' rel='stylesheet' type='text/css' /><link href='plug-in/easyui/themes/icon.css' rel='stylesheet' type='text/css' /><link href='plug-in/accordion/css/accordion.css' rel='stylesheet' type='text/css' /><link href='plug-in/Validform/css/style.css' rel='stylesheet' type='text/css' /><link href='plug-in/Validform/css/tablefrom.css' rel='stylesheet' type='text/css' /><style type='text/css'>body{font-size:12px;}table {border:1px solid #000000;border-collapse: collapse;}td {border:1px solid #000000;background:white;font-size:12px;font-family: 新宋体;color: #333;</style></head><body><div><p>附件2：</p><h1 style='text-align:center'><span style='font-size:24px'><strong>价格认证人员统计表</strong></span></h1><p>填报单位（盖章）：<input name='org_name' type='text' value='${org_name?if_exists?html}' /></p><p>单位代码号：<input name='num' type='text' value='${num?if_exists?html}' /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;单位：人填&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 报日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年&nbsp;&nbsp; 月&nbsp;&nbsp; 日</p><form action='cgFormBuildController.do?saveOrUpdate' id='formobj' method='post' name='formobj'><input name='tableName' type='hidden' value='${tableName?if_exists?html}' /> <input name='id' type='hidden' value='${id?if_exists?html}' />#{jform_hidden_field}<input type='hidden' /><p>&nbsp;</p><table border='1' cellpadding='0' cellspacing='0' style='width:1016px'><tbody><tr><td rowspan='4'><p>&nbsp;</p><p>&nbsp;</p><p>合计</p><p>&nbsp;</p></td><td colspan='6' rowspan='2'><p>&nbsp;</p><p>人数</p></td><td colspan='5' rowspan='2'><p>&nbsp;</p><p>学历</p></td><td colspan='4' rowspan='2'><p>&nbsp;</p><p>取得的（上岗）执业资格</p></td><td colspan='4'><p>专业技术职称</p></td></tr><tr><td colspan='4'><p>（经济系列、工程系列）</p></td></tr><tr><td colspan='3'><p>在编人员</p></td><td colspan='2'><p>聘用人员</p></td><td rowspan='2'><p>临时(借用)人员</p></td><td rowspan='2'><p>高中</p></td><td rowspan='2'><p>大专</p></td><td rowspan='2'><p>本科</p></td><td rowspan='2'><p>研究生</p></td><td rowspan='2'><p>其它</p></td><td rowspan='2'><p>价格</p><p>鉴证员</p></td><td rowspan='2'><p>价格</p><p>鉴证师</p></td><td rowspan='2'><p>复核</p><p>裁定员</p></td><td rowspan='2'><p>其它</p></td><td rowspan='2'><p>初级</p></td><td rowspan='2'><p>中级</p></td><td rowspan='2'><p>高级</p></td><td rowspan='2'><p>其它</p></td></tr><tr><td><p>本单位</p></td><td colspan='2'><p>其它</p></td><td><p>长期</p></td><td><p>短期</p></td></tr><tr><td><p>A1</p></td><td><p>B1</p></td><td><p>B2</p></td><td colspan='2'><p>B3</p></td><td><p>B4</p></td><td><p>B5</p></td><td><p>C1</p></td><td><p>C2</p></td><td><p>C3</p></td><td><p>C4</p></td><td><p>C5</p></td><td><p>D1</p></td><td><p>D2</p></td><td><p>D3</p></td><td><p>D4</p></td><td><p>E1</p></td><td><p>E2</p></td><td><p>E3</p></td><td><p>E4</p></td></tr><tr><td><p><input name='a1' size='4' type='text' value='${a1?if_exists?html}' /></p></td><td><p><input name='b1' size='4' type='text' value='${b1?if_exists?html}' /></p></td><td><p><input name='b2' size='4' type='text' value='${b2?if_exists?html}' /></p></td><td colspan='2'><p><input name='b3' size='4' type='text' value='${b3?if_exists?html}' /></p></td><td><p><input name='b4' size='4' type='text' value='${b4?if_exists?html}' /></p></td><td><p><input name='b5' size='4' type='text' value='${b5?if_exists?html}' /></p></td><td><p><input name='c1' size='4' type='text' value='${c1?if_exists?html}' /></p></td><td><p><input name='c2' size='4' type='text' value='${c2?if_exists?html}' /></p></td><td><p><input name='c3' size='4' type='text' value='${c3?if_exists?html}' /></p></td><td><p><input name='c4' size='4' type='text' value='${c4?if_exists?html}' /></p></td><td><p><input name='c5' size='4' type='text' value='${c5?if_exists?html}' /></p></td><td><p><input name='d1' size='4' type='text' value='${d1?if_exists?html}' /></p></td><td><p><input name='d2' size='4' type='text' value='${d2?if_exists?html}' /></p></td><td><p><input name='d3' size='4' type='text' value='${d3?if_exists?html}' /></p></td><td><p><input name='d4' size='4' type='text' value='${d4?if_exists?html}' /></p></td><td><p><input name='e1' size='4' type='text' value='${e1?if_exists?html}' /></p></td><td><p><input name='e2' size='4' type='text' value='${e2?if_exists?html}' /></p></td><td><p><input name='e3' size='4' type='text' value='${e3?if_exists?html}' /></p></td><td><p><input name='e4' size='4' type='text' value='${e4?if_exists?html}' /></p></td></tr><tr><td colspan='20'><p>&nbsp;</p><p>填报说明：</p><p>一、合计（A）：填报至统计截止期的本机构的人员总数。</p><p>二、人数：</p><p>在编人员：分别按照价格认证机构编制内及其它具有价格主管部门编制的实有人数填报在B1、B2栏内。</p><p>聘用人员：按照经价格主管部门或价格认证机构人事部门认可的并签订三年以上的工作合同的人员（B3）；以及没有经过价格主管部门或价格认证机构人事部门认可的签订合同少于三年的人员（B4）分别来进行统计。</p><p>临时（借用）人员（B5）：特指外聘的临时工，或者工作关系不在本单位且无长期聘用合同的借调人员等。</p><p>三、表内各栏目关系</p><p>A=B1+B2+B3+B4+B5=C1+C2+C3+C4+C5=D1+D2+D3+D4=E1+E2+E3+E4</p></td></tr></tbody></table></form></div></body></html>";
/*      */ 
/* 1991 */     ckEditor.setContents(str.getBytes());
/* 1992 */     this.commonDao.saveOrUpdate(ckEditor);
/*      */   }
/*      */ 
/*      */   private void repairLog()
/*      */   {
/* 2000 */     TSUser admin = 
/* 2001 */       (TSUser)this.commonDao.findByProperty(TSUser.class, "signatureFile", 
/* 2001 */       "images/renfang/qm/licf.gif").get(0);
/*      */     try {
/* 2003 */       TSLog log1 = new TSLog();
/* 2004 */       log1.setLogcontent("用户: admin登录成功");
/* 2005 */       log1.setBroswer("Chrome");
/* 2006 */       log1.setNote("169.254.200.136");
/* 2007 */       log1.setTSUser(admin);
/* 2008 */       log1.setOperatetime(DateUtils.parseTimestamp("2013-4-24 16:22:40", 
/* 2009 */         "yyyy-MM-dd HH:mm"));
/* 2010 */       log1.setOperatetype(Short.valueOf((short)1));
/* 2011 */       log1.setLoglevel(Short.valueOf((short)1));
/* 2012 */       this.commonDao.saveOrUpdate(log1);
/*      */ 
/* 2014 */       TSLog log2 = new TSLog();
/* 2015 */       log2.setLogcontent("用户: admin登录成功");
/* 2016 */       log2.setBroswer("Chrome");
/* 2017 */       log2.setNote("10.10.10.1");
/* 2018 */       log2.setTSUser(admin);
/* 2019 */       log2.setOperatetime(DateUtils.parseTimestamp("2013-4-24 17:12:22", 
/* 2020 */         "yyyy-MM-dd HH:mm"));
/* 2021 */       log2.setOperatetype(Short.valueOf((short)1));
/* 2022 */       log2.setLoglevel(Short.valueOf((short)1));
/* 2023 */       this.commonDao.saveOrUpdate(log2);
/*      */ 
/* 2025 */       TSLog log3 = new TSLog();
/* 2026 */       log3.setLogcontent("用户: admin登录成功");
/* 2027 */       log3.setBroswer("Chrome");
/* 2028 */       log3.setNote("169.254.218.201");
/* 2029 */       log3.setTSUser(admin);
/* 2030 */       log3.setOperatetime(DateUtils.parseTimestamp("2013-3-10 15:37:11", 
/* 2031 */         "yyyy-MM-dd HH:mm"));
/* 2032 */       log3.setOperatetype(Short.valueOf((short)1));
/* 2033 */       log3.setLoglevel(Short.valueOf((short)1));
/* 2034 */       this.commonDao.saveOrUpdate(log3);
/*      */ 
/* 2036 */       TSLog log4 = new TSLog();
/* 2037 */       log4.setLogcontent("用户admin已退出");
/* 2038 */       log4.setBroswer("Chrome");
/* 2039 */       log4.setNote("169.254.218.201");
/* 2040 */       log4.setTSUser(admin);
/* 2041 */       log4.setOperatetime(DateUtils.parseTimestamp("2013-3-10 15:38:33", 
/* 2042 */         "yyyy-MM-dd HH:mm"));
/* 2043 */       log4.setOperatetype(Short.valueOf((short)1));
/* 2044 */       log4.setLoglevel(Short.valueOf((short)2));
/* 2045 */       this.commonDao.saveOrUpdate(log4);
/*      */ 
/* 2047 */       TSLog log5 = new TSLog();
/* 2048 */       log5.setLogcontent("用户: admin登录成功");
/* 2049 */       log5.setBroswer("MSIE 9.0");
/* 2050 */       log5.setNote("169.254.218.201");
/* 2051 */       log5.setTSUser(admin);
/* 2052 */       log5.setOperatetime(DateUtils.parseTimestamp("2013-3-10 15:38:42", 
/* 2053 */         "yyyy-MM-dd HH:mm"));
/* 2054 */       log5.setOperatetype(Short.valueOf((short)1));
/* 2055 */       log5.setLoglevel(Short.valueOf((short)1));
/* 2056 */       this.commonDao.saveOrUpdate(log5);
/*      */ 
/* 2058 */       TSLog log6 = new TSLog();
/* 2059 */       log6.setLogcontent("JeecgDemo例子: 12被删除 成功");
/* 2060 */       log6.setBroswer("MSIE 9.0");
/* 2061 */       log6.setNote("169.254.218.201");
/* 2062 */       log6.setTSUser(admin);
/* 2063 */       log6.setOperatetime(DateUtils.parseTimestamp("2013-3-10 15:39:00", 
/* 2064 */         "yyyy-MM-dd HH:mm"));
/* 2065 */       log6.setOperatetype(Short.valueOf((short)1));
/* 2066 */       log6.setLoglevel(Short.valueOf((short)4));
/* 2067 */       this.commonDao.saveOrUpdate(log6);
/*      */ 
/* 2069 */       TSLog log7 = new TSLog();
/* 2070 */       log7.setLogcontent("JeecgDemo例子: 12被删除 成功");
/* 2071 */       log7.setBroswer("MSIE 9.0");
/* 2072 */       log7.setNote("169.254.218.201");
/* 2073 */       log7.setTSUser(admin);
/* 2074 */       log7.setOperatetime(DateUtils.parseTimestamp("2013-3-10 15:39:02", 
/* 2075 */         "yyyy-MM-dd HH:mm"));
/* 2076 */       log7.setOperatetype(Short.valueOf((short)1));
/* 2077 */       log7.setLoglevel(Short.valueOf((short)4));
/* 2078 */       this.commonDao.saveOrUpdate(log7);
/*      */ 
/* 2080 */       TSLog log8 = new TSLog();
/* 2081 */       log8.setLogcontent("JeecgDemo例子: 12被删除 成功");
/* 2082 */       log8.setBroswer("Chrome");
/* 2083 */       log8.setNote("169.254.218.201");
/* 2084 */       log8.setTSUser(admin);
/* 2085 */       log8.setOperatetime(DateUtils.parseTimestamp("2013-3-10 15:39:04", 
/* 2086 */         "yyyy-MM-dd HH:mm"));
/* 2087 */       log8.setOperatetype(Short.valueOf((short)1));
/* 2088 */       log8.setLoglevel(Short.valueOf((short)4));
/* 2089 */       this.commonDao.saveOrUpdate(log8);
/*      */ 
/* 2091 */       TSLog log9 = new TSLog();
/* 2092 */       log9.setLogcontent("权限: 单表模型被更新成功");
/* 2093 */       log9.setBroswer("MSIE 9.0");
/* 2094 */       log9.setNote("169.254.218.201");
/* 2095 */       log9.setTSUser(admin);
/* 2096 */       log9.setOperatetime(DateUtils.parseTimestamp("2013-3-10 15:39:30", 
/* 2097 */         "yyyy-MM-dd HH:mm"));
/* 2098 */       log9.setOperatetype(Short.valueOf((short)1));
/* 2099 */       log9.setLoglevel(Short.valueOf((short)5));
/* 2100 */       this.commonDao.saveOrUpdate(log9);
/*      */ 
/* 2102 */       TSLog log10 = new TSLog();
/* 2103 */       log10.setLogcontent("删除成功");
/* 2104 */       log10.setBroswer("Chrome");
/* 2105 */       log10.setNote("169.254.218.201");
/* 2106 */       log10.setTSUser(admin);
/* 2107 */       log10.setOperatetime(DateUtils.parseTimestamp("2013-3-10 15:39:38", 
/* 2108 */         "yyyy-MM-dd HH:mm"));
/* 2109 */       log10.setOperatetype(Short.valueOf((short)1));
/* 2110 */       log10.setLoglevel(Short.valueOf((short)4));
/* 2111 */       this.commonDao.saveOrUpdate(log10);
/*      */ 
/* 2113 */       TSLog log11 = new TSLog();
/* 2114 */       log11.setLogcontent("删除成功");
/* 2115 */       log11.setBroswer("MSIE 9.0");
/* 2116 */       log11.setNote("169.254.218.201");
/* 2117 */       log11.setTSUser(admin);
/* 2118 */       log11.setOperatetime(DateUtils.parseTimestamp("2013-3-10 15:39:40", 
/* 2119 */         "yyyy-MM-dd HH:mm"));
/* 2120 */       log11.setOperatetype(Short.valueOf((short)1));
/* 2121 */       log11.setLoglevel(Short.valueOf((short)4));
/* 2122 */       this.commonDao.saveOrUpdate(log11);
/*      */ 
/* 2124 */       TSLog log12 = new TSLog();
/* 2125 */       log12.setLogcontent("删除成功");
/* 2126 */       log12.setBroswer("Chrome");
/* 2127 */       log12.setNote("169.254.218.201");
/* 2128 */       log12.setTSUser(admin);
/* 2129 */       log12.setOperatetime(DateUtils.parseTimestamp("2013-3-10 15:39:41", 
/* 2130 */         "yyyy-MM-dd HH:mm"));
/* 2131 */       log12.setOperatetype(Short.valueOf((short)1));
/* 2132 */       log12.setLoglevel(Short.valueOf((short)4));
/* 2133 */       this.commonDao.saveOrUpdate(log12);
/*      */ 
/* 2135 */       TSLog log13 = new TSLog();
/* 2136 */       log13.setLogcontent("删除成功");
/* 2137 */       log13.setBroswer("Firefox");
/* 2138 */       log13.setNote("169.254.218.201");
/* 2139 */       log13.setTSUser(admin);
/* 2140 */       log13.setOperatetime(DateUtils.parseTimestamp("2013-3-10 15:39:42", 
/* 2141 */         "yyyy-MM-dd HH:mm"));
/* 2142 */       log13.setOperatetype(Short.valueOf((short)1));
/* 2143 */       log13.setLoglevel(Short.valueOf((short)4));
/* 2144 */       this.commonDao.saveOrUpdate(log13);
/*      */ 
/* 2146 */       TSLog log14 = new TSLog();
/* 2147 */       log14.setLogcontent("添加成功");
/* 2148 */       log14.setBroswer("Chrome");
/* 2149 */       log14.setNote("169.254.218.201");
/* 2150 */       log14.setTSUser(admin);
/* 2151 */       log14.setOperatetime(DateUtils.parseTimestamp("2013-3-10 15:40:00", 
/* 2152 */         "yyyy-MM-dd HH:mm"));
/* 2153 */       log14.setOperatetype(Short.valueOf((short)1));
/* 2154 */       log14.setLoglevel(Short.valueOf((short)3));
/* 2155 */       this.commonDao.saveOrUpdate(log14);
/*      */ 
/* 2157 */       TSLog log15 = new TSLog();
/* 2158 */       log15.setLogcontent("更新成功");
/* 2159 */       log15.setBroswer("Chrome");
/* 2160 */       log15.setNote("169.254.218.201");
/* 2161 */       log15.setTSUser(admin);
/* 2162 */       log15.setOperatetime(DateUtils.parseTimestamp("2013-3-10 15:40:04", 
/* 2163 */         "yyyy-MM-dd HH:mm"));
/* 2164 */       log15.setOperatetype(Short.valueOf((short)1));
/* 2165 */       log15.setLoglevel(Short.valueOf((short)5));
/* 2166 */       this.commonDao.saveOrUpdate(log15);
/*      */ 
/* 2168 */       TSLog log16 = new TSLog();
/* 2169 */       log16.setLogcontent("JeecgDemo例子: 12被添加成功");
/* 2170 */       log16.setBroswer("Chrome");
/* 2171 */       log16.setNote("169.254.218.201");
/* 2172 */       log16.setTSUser(admin);
/* 2173 */       log16.setOperatetime(DateUtils.parseTimestamp("2013-3-10 15:40:44", 
/* 2174 */         "yyyy-MM-dd HH:mm"));
/* 2175 */       log16.setOperatetype(Short.valueOf((short)1));
/* 2176 */       log16.setLoglevel(Short.valueOf((short)3));
/* 2177 */       this.commonDao.saveOrUpdate(log16);
/*      */ 
/* 2179 */       TSLog log17 = new TSLog();
/* 2180 */       log17.setLogcontent("部门: 信息部被更新成功");
/* 2181 */       log17.setBroswer("Chrome");
/* 2182 */       log17.setNote("169.254.218.201");
/* 2183 */       log17.setTSUser(admin);
/* 2184 */       log17.setOperatetime(DateUtils.parseTimestamp("2013-3-10 15:41:26", 
/* 2185 */         "yyyy-MM-dd HH:mm"));
/* 2186 */       log17.setOperatetype(Short.valueOf((short)1));
/* 2187 */       log17.setLoglevel(Short.valueOf((short)5));
/* 2188 */       this.commonDao.saveOrUpdate(log17);
/*      */ 
/* 2190 */       TSLog log18 = new TSLog();
/* 2191 */       log18.setLogcontent("部门: 设计部被更新成功");
/* 2192 */       log18.setBroswer("Chrome");
/* 2193 */       log18.setNote("169.254.218.201");
/* 2194 */       log18.setTSUser(admin);
/* 2195 */       log18.setOperatetime(DateUtils.parseTimestamp("2013-3-10 15:41:38", 
/* 2196 */         "yyyy-MM-dd HH:mm"));
/* 2197 */       log18.setOperatetype(Short.valueOf((short)1));
/* 2198 */       log18.setLoglevel(Short.valueOf((short)5));
/* 2199 */       this.commonDao.saveOrUpdate(log18);
/*      */ 
/* 2201 */       TSLog log19 = new TSLog();
/* 2202 */       log19.setLogcontent("类型: 信息部流程被更新成功");
/* 2203 */       log19.setBroswer("Chrome");
/* 2204 */       log19.setNote("169.254.218.201");
/* 2205 */       log19.setTSUser(admin);
/* 2206 */       log19.setOperatetime(DateUtils.parseTimestamp("2013-3-10 15:46:55", 
/* 2207 */         "yyyy-MM-dd HH:mm"));
/* 2208 */       log19.setOperatetype(Short.valueOf((short)1));
/* 2209 */       log19.setLoglevel(Short.valueOf((short)5));
/* 2210 */       this.commonDao.saveOrUpdate(log19);
/*      */ 
/* 2212 */       TSLog log20 = new TSLog();
/* 2213 */       log20.setLogcontent("用户: admin登录成功");
/* 2214 */       log20.setBroswer("Chrome");
/* 2215 */       log20.setNote("169.254.218.201");
/* 2216 */       log20.setTSUser(admin);
/* 2217 */       log20.setOperatetime(DateUtils.parseTimestamp("2013-3-10 15:48:47", 
/* 2218 */         "yyyy-MM-dd HH:mm"));
/* 2219 */       log20.setOperatetype(Short.valueOf((short)1));
/* 2220 */       log20.setLoglevel(Short.valueOf((short)1));
/* 2221 */       this.commonDao.saveOrUpdate(log20);
/*      */ 
/* 2223 */       TSLog log21 = new TSLog();
/* 2224 */       log21.setLogcontent("用户: admin登录成功");
/* 2225 */       log21.setBroswer("Firefox");
/* 2226 */       log21.setNote("169.254.218.201");
/* 2227 */       log21.setTSUser(admin);
/* 2228 */       log21.setOperatetime(DateUtils.parseTimestamp("2013-3-21 23:23:52", 
/* 2229 */         "yyyy-MM-dd HH:mm"));
/* 2230 */       log21.setOperatetype(Short.valueOf((short)1));
/* 2231 */       log21.setLoglevel(Short.valueOf((short)1));
/* 2232 */       this.commonDao.saveOrUpdate(log21);
/*      */ 
/* 2234 */       TSLog log22 = new TSLog();
/* 2235 */       log22.setLogcontent("用户: admin登录成功");
/* 2236 */       log22.setBroswer("Chrome");
/* 2237 */       log22.setNote("169.254.218.201");
/* 2238 */       log22.setTSUser(admin);
/* 2239 */       log22.setOperatetime(DateUtils.parseTimestamp("2013-3-21 23:26:22", 
/* 2240 */         "yyyy-MM-dd HH:mm"));
/* 2241 */       log22.setOperatetype(Short.valueOf((short)1));
/* 2242 */       log22.setLoglevel(Short.valueOf((short)1));
/* 2243 */       this.commonDao.saveOrUpdate(log22);
/*      */ 
/* 2245 */       TSLog log23 = new TSLog();
/* 2246 */       log23.setLogcontent("权限: 一对多实例被添加成功");
/* 2247 */       log23.setBroswer("Chrome");
/* 2248 */       log23.setNote("169.254.218.201");
/* 2249 */       log23.setTSUser(admin);
/* 2250 */       log23.setOperatetime(DateUtils.parseTimestamp("2013-3-21 23:28:34", 
/* 2251 */         "yyyy-MM-dd HH:mm"));
/* 2252 */       log23.setOperatetype(Short.valueOf((short)1));
/* 2253 */       log23.setLoglevel(Short.valueOf((short)3));
/* 2254 */       this.commonDao.saveOrUpdate(log23);
/*      */ 
/* 2256 */       TSLog log24 = new TSLog();
/* 2257 */       log24.setLogcontent("用户: admin登录成功");
/* 2258 */       log24.setBroswer("Chrome");
/* 2259 */       log24.setNote("169.254.218.201");
/* 2260 */       log24.setTSUser(admin);
/* 2261 */       log24.setOperatetime(DateUtils.parseTimestamp("2013-3-22 8:25:07", 
/* 2262 */         "yyyy-MM-dd HH:mm"));
/* 2263 */       log24.setOperatetype(Short.valueOf((short)1));
/* 2264 */       log24.setLoglevel(Short.valueOf((short)1));
/* 2265 */       this.commonDao.saveOrUpdate(log24);
/*      */ 
/* 2267 */       TSLog log25 = new TSLog();
/* 2268 */       log25.setLogcontent("用户: admin登录成功");
/* 2269 */       log25.setBroswer("Firefox");
/* 2270 */       log25.setNote("169.254.218.201");
/* 2271 */       log25.setTSUser(admin);
/* 2272 */       log25.setOperatetime(DateUtils.parseTimestamp("2013-3-22 9:05:25", 
/* 2273 */         "yyyy-MM-dd HH:mm"));
/* 2274 */       log25.setOperatetype(Short.valueOf((short)1));
/* 2275 */       log25.setLoglevel(Short.valueOf((short)1));
/* 2276 */       this.commonDao.saveOrUpdate(log25);
/*      */ 
/* 2278 */       TSLog log26 = new TSLog();
/* 2279 */       log26.setLogcontent("用户: admin登录成功");
/* 2280 */       log26.setBroswer("Chrome");
/* 2281 */       log26.setNote("169.254.218.201");
/* 2282 */       log26.setTSUser(admin);
/* 2283 */       log26.setOperatetime(DateUtils.parseTimestamp("2013-3-22 9:09:05", 
/* 2284 */         "yyyy-MM-dd HH:mm"));
/* 2285 */       log26.setOperatetype(Short.valueOf((short)1));
/* 2286 */       log26.setLoglevel(Short.valueOf((short)1));
/* 2287 */       this.commonDao.saveOrUpdate(log26);
/*      */ 
/* 2289 */       TSLog log27 = new TSLog();
/* 2290 */       log27.setLogcontent("用户: admin登录成功");
/* 2291 */       log27.setBroswer("MSIE 8.0");
/* 2292 */       log27.setNote("169.254.218.201");
/* 2293 */       log27.setTSUser(admin);
/* 2294 */       log27.setOperatetime(DateUtils.parseTimestamp("2013-3-22 9:28:50", 
/* 2295 */         "yyyy-MM-dd HH:mm"));
/* 2296 */       log27.setOperatetype(Short.valueOf((short)1));
/* 2297 */       log27.setLoglevel(Short.valueOf((short)1));
/* 2298 */       this.commonDao.saveOrUpdate(log27);
/*      */ 
/* 2300 */       TSLog log28 = new TSLog();
/* 2301 */       log28.setLogcontent("用户: admin登录成功");
/* 2302 */       log28.setBroswer("Firefox");
/* 2303 */       log28.setNote("169.254.218.201");
/* 2304 */       log28.setTSUser(admin);
/* 2305 */       log28.setOperatetime(DateUtils.parseTimestamp("2013-3-22 10:32:59", 
/* 2306 */         "yyyy-MM-dd HH:mm"));
/* 2307 */       log28.setOperatetype(Short.valueOf((short)1));
/* 2308 */       log28.setLoglevel(Short.valueOf((short)1));
/* 2309 */       this.commonDao.saveOrUpdate(log28);
/*      */ 
/* 2311 */       TSLog log29 = new TSLog();
/* 2312 */       log29.setLogcontent("物品: 笔记本添加成功");
/* 2313 */       log29.setBroswer("Chrome");
/* 2314 */       log29.setNote("169.254.218.201");
/* 2315 */       log29.setTSUser(admin);
/* 2316 */       log29.setOperatetime(DateUtils.parseTimestamp("2013-3-22 10:35:44", 
/* 2317 */         "yyyy-MM-dd HH:mm"));
/* 2318 */       log29.setOperatetype(Short.valueOf((short)1));
/* 2319 */       log29.setLoglevel(Short.valueOf((short)3));
/* 2320 */       this.commonDao.saveOrUpdate(log29);
/*      */ 
/* 2322 */       TSLog log30 = new TSLog();
/* 2323 */       log30.setLogcontent("用户: admin登录成功");
/* 2324 */       log30.setBroswer("Firefox");
/* 2325 */       log30.setNote("169.254.218.201");
/* 2326 */       log30.setTSUser(admin);
/* 2327 */       log30.setOperatetime(DateUtils.parseTimestamp("2013-3-22 10:41:46", 
/* 2328 */         "yyyy-MM-dd HH:mm"));
/* 2329 */       log30.setOperatetype(Short.valueOf((short)1));
/* 2330 */       log30.setLoglevel(Short.valueOf((short)1));
/* 2331 */       this.commonDao.saveOrUpdate(log30);
/*      */ 
/* 2333 */       TSLog log31 = new TSLog();
/* 2334 */       log31.setLogcontent("用户: admin登录成功");
/* 2335 */       log31.setBroswer("Firefox");
/* 2336 */       log31.setNote("169.254.218.201");
/* 2337 */       log31.setTSUser(admin);
/* 2338 */       log31.setOperatetime(DateUtils.parseTimestamp("2013-3-22 16:11:14", 
/* 2339 */         "yyyy-MM-dd HH:mm"));
/* 2340 */       log31.setOperatetype(Short.valueOf((short)1));
/* 2341 */       log31.setLoglevel(Short.valueOf((short)1));
/* 2342 */       this.commonDao.saveOrUpdate(log31);
/*      */ 
/* 2344 */       TSLog log32 = new TSLog();
/* 2345 */       log32.setLogcontent("用户: admin登录成功");
/* 2346 */       log32.setBroswer("Chrome");
/* 2347 */       log32.setNote("169.254.218.201");
/* 2348 */       log32.setTSUser(admin);
/* 2349 */       log32.setOperatetime(DateUtils.parseTimestamp("2013-3-22 21:49:43", 
/* 2350 */         "yyyy-MM-dd HH:mm"));
/* 2351 */       log32.setOperatetype(Short.valueOf((short)1));
/* 2352 */       log32.setLoglevel(Short.valueOf((short)1));
/* 2353 */       this.commonDao.saveOrUpdate(log32);
/*      */ 
/* 2355 */       TSLog log33 = new TSLog();
/* 2356 */       log33.setLogcontent("用户: admin登录成功");
/* 2357 */       log33.setBroswer("Chrome");
/* 2358 */       log33.setNote("169.254.218.201");
/* 2359 */       log33.setTSUser(admin);
/* 2360 */       log33.setOperatetime(DateUtils.parseTimestamp("2013-3-22 23:17:12", 
/* 2361 */         "yyyy-MM-dd HH:mm"));
/* 2362 */       log33.setOperatetype(Short.valueOf((short)1));
/* 2363 */       log33.setLoglevel(Short.valueOf((short)1));
/* 2364 */       this.commonDao.saveOrUpdate(log33);
/*      */ 
/* 2366 */       TSLog log34 = new TSLog();
/* 2367 */       log34.setLogcontent("用户: admin登录成功");
/* 2368 */       log34.setBroswer("Chrome");
/* 2369 */       log34.setNote("169.254.218.201");
/* 2370 */       log34.setTSUser(admin);
/* 2371 */       log34.setOperatetime(DateUtils.parseTimestamp("2013-3-22 23:27:22", 
/* 2372 */         "yyyy-MM-dd HH:mm"));
/* 2373 */       log34.setOperatetype(Short.valueOf((short)1));
/* 2374 */       log34.setLoglevel(Short.valueOf((short)1));
/* 2375 */       this.commonDao.saveOrUpdate(log34);
/*      */ 
/* 2377 */       TSLog log35 = new TSLog();
/* 2378 */       log35.setLogcontent("用户: admin登录成功");
/* 2379 */       log35.setBroswer("Chrome");
/* 2380 */       log35.setNote("169.254.218.201");
/* 2381 */       log35.setTSUser(admin);
/* 2382 */       log35.setOperatetime(DateUtils.parseTimestamp("2013-3-23 0:16:10", 
/* 2383 */         "yyyy-MM-dd HH:mm"));
/* 2384 */       log35.setOperatetype(Short.valueOf((short)1));
/* 2385 */       log35.setLoglevel(Short.valueOf((short)1));
/* 2386 */       this.commonDao.saveOrUpdate(log35);
/*      */ 
/* 2388 */       TSLog log36 = new TSLog();
/* 2389 */       log36.setLogcontent("用户: admin登录成功");
/* 2390 */       log36.setBroswer("Chrome");
/* 2391 */       log36.setNote("169.254.218.201");
/* 2392 */       log36.setTSUser(admin);
/* 2393 */       log36.setOperatetime(DateUtils.parseTimestamp("2013-3-23 0:22:46", 
/* 2394 */         "yyyy-MM-dd HH:mm"));
/* 2395 */       log36.setOperatetype(Short.valueOf((short)1));
/* 2396 */       log36.setLoglevel(Short.valueOf((short)1));
/* 2397 */       this.commonDao.saveOrUpdate(log36);
/*      */ 
/* 2399 */       TSLog log37 = new TSLog();
/* 2400 */       log37.setLogcontent("用户: admin登录成功");
/* 2401 */       log37.setBroswer("Firefox");
/* 2402 */       log37.setNote("169.254.218.201");
/* 2403 */       log37.setTSUser(admin);
/* 2404 */       log37.setOperatetime(DateUtils.parseTimestamp("2013-3-23 0:31:11", 
/* 2405 */         "yyyy-MM-dd HH:mm"));
/* 2406 */       log37.setOperatetype(Short.valueOf((short)1));
/* 2407 */       log37.setLoglevel(Short.valueOf((short)1));
/* 2408 */       this.commonDao.saveOrUpdate(log37);
/*      */ 
/* 2410 */       TSLog log38 = new TSLog();
/* 2411 */       log38.setLogcontent("用户: admin登录成功");
/* 2412 */       log38.setBroswer("Chrome");
/* 2413 */       log38.setNote("169.254.218.201");
/* 2414 */       log38.setTSUser(admin);
/* 2415 */       log38.setOperatetime(DateUtils.parseTimestamp("2013-3-23 14:23:36", 
/* 2416 */         "yyyy-MM-dd HH:mm"));
/* 2417 */       log38.setOperatetype(Short.valueOf((short)1));
/* 2418 */       log38.setLoglevel(Short.valueOf((short)1));
/* 2419 */       this.commonDao.saveOrUpdate(log38);
/*      */ 
/* 2421 */       TSLog log39 = new TSLog();
/* 2422 */       log39.setLogcontent("流程参数: 主任审批被添加成功");
/* 2423 */       log39.setBroswer("Chrome");
/* 2424 */       log39.setNote("169.254.218.201");
/* 2425 */       log39.setTSUser(admin);
/* 2426 */       log39.setOperatetime(DateUtils.parseTimestamp("2013-3-23 15:05:30", 
/* 2427 */         "yyyy-MM-dd HH:mm"));
/* 2428 */       log39.setOperatetype(Short.valueOf((short)1));
/* 2429 */       log39.setLoglevel(Short.valueOf((short)3));
/* 2430 */       this.commonDao.saveOrUpdate(log39);
/*      */ 
/* 2432 */       TSLog log40 = new TSLog();
/* 2433 */       log40.setLogcontent("业务参数: 入职申请被添加成功");
/* 2434 */       log40.setBroswer("Firefox");
/* 2435 */       log40.setNote("169.254.218.201");
/* 2436 */       log40.setTSUser(admin);
/* 2437 */       log40.setOperatetime(DateUtils.parseTimestamp("2013-3-23 15:05:42", 
/* 2438 */         "yyyy-MM-dd HH:mm"));
/* 2439 */       log40.setOperatetype(Short.valueOf((short)1));
/* 2440 */       log40.setLoglevel(Short.valueOf((short)3));
/* 2441 */       this.commonDao.saveOrUpdate(log40);
/*      */ 
/* 2443 */       TSLog log41 = new TSLog();
/* 2444 */       log41.setLogcontent("权限: 入职申请被添加成功");
/* 2445 */       log41.setBroswer("Chrome");
/* 2446 */       log41.setNote("169.254.218.201");
/* 2447 */       log41.setTSUser(admin);
/* 2448 */       log41.setOperatetime(DateUtils.parseTimestamp("2013-3-23 15:12:56", 
/* 2449 */         "yyyy-MM-dd HH:mm"));
/* 2450 */       log41.setOperatetype(Short.valueOf((short)1));
/* 2451 */       log41.setLoglevel(Short.valueOf((short)3));
/* 2452 */       this.commonDao.saveOrUpdate(log41);
/*      */ 
/* 2454 */       TSLog log42 = new TSLog();
/* 2455 */       log42.setLogcontent("权限: 入职办理被添加成功");
/* 2456 */       log42.setBroswer("Firefox");
/* 2457 */       log42.setNote("169.254.218.201");
/* 2458 */       log42.setTSUser(admin);
/* 2459 */       log42.setOperatetime(DateUtils.parseTimestamp("2013-3-23 15:13:23", 
/* 2460 */         "yyyy-MM-dd HH:mm"));
/* 2461 */       log42.setOperatetype(Short.valueOf((short)1));
/* 2462 */       log42.setLoglevel(Short.valueOf((short)3));
/* 2463 */       this.commonDao.saveOrUpdate(log42);
/*      */ 
/* 2465 */       TSLog log43 = new TSLog();
/* 2466 */       log43.setLogcontent("用户: admin登录成功");
/* 2467 */       log43.setBroswer("Chrome");
/* 2468 */       log43.setNote("10.10.10.1");
/* 2469 */       log43.setTSUser(admin);
/* 2470 */       log43.setOperatetime(DateUtils.parseTimestamp("2013-5-6 15:27:19", 
/* 2471 */         "yyyy-MM-dd HH:mm"));
/* 2472 */       log43.setOperatetype(Short.valueOf((short)1));
/* 2473 */       log43.setLoglevel(Short.valueOf((short)1));
/* 2474 */       this.commonDao.saveOrUpdate(log43);
/*      */ 
/* 2476 */       TSLog log44 = new TSLog();
/* 2477 */       log44.setLogcontent("用户: admin登录成功");
/* 2478 */       log44.setBroswer("MSIE 8.0");
/* 2479 */       log44.setNote("192.168.197.1");
/* 2480 */       log44.setTSUser(admin);
/* 2481 */       log44.setOperatetime(DateUtils.parseTimestamp("2013-7-7 15:16:05", 
/* 2482 */         "yyyy-MM-dd HH:mm"));
/* 2483 */       log44.setOperatetype(Short.valueOf((short)1));
/* 2484 */       log44.setLoglevel(Short.valueOf((short)1));
/* 2485 */       this.commonDao.saveOrUpdate(log44);
/*      */ 
/* 2487 */       TSLog log45 = new TSLog();
/* 2488 */       log45.setLogcontent("用户: admin登录成功");
/* 2489 */       log45.setBroswer("MSIE 8.0");
/* 2490 */       log45.setNote("192.168.197.1");
/* 2491 */       log45.setTSUser(admin);
/* 2492 */       log45.setOperatetime(DateUtils.parseTimestamp("2013-7-7 16:02:38", 
/* 2493 */         "yyyy-MM-dd HH:mm"));
/* 2494 */       log45.setOperatetype(Short.valueOf((short)1));
/* 2495 */       log45.setLoglevel(Short.valueOf((short)1));
/* 2496 */       this.commonDao.saveOrUpdate(log45);
/*      */ 
/* 2498 */       TSLog log46 = new TSLog();
/* 2499 */       log46.setLogcontent("用户: admin登录成功");
/* 2500 */       log46.setBroswer("MSIE 8.0");
/* 2501 */       log46.setNote("192.168.197.1");
/* 2502 */       log46.setTSUser(admin);
/* 2503 */       log46.setOperatetime(DateUtils.parseTimestamp("2013-7-7 16:07:49", 
/* 2504 */         "yyyy-MM-dd HH:mm"));
/* 2505 */       log46.setOperatetype(Short.valueOf((short)1));
/* 2506 */       log46.setLoglevel(Short.valueOf((short)1));
/* 2507 */       this.commonDao.saveOrUpdate(log46);
/*      */ 
/* 2509 */       TSLog log47 = new TSLog();
/* 2510 */       log47.setLogcontent("用户: admin登录成功");
/* 2511 */       log47.setBroswer("MSIE 8.0");
/* 2512 */       log47.setNote("192.168.197.1");
/* 2513 */       log47.setTSUser(admin);
/* 2514 */       log47.setOperatetime(DateUtils.parseTimestamp("2013-7-7 16:09:10", 
/* 2515 */         "yyyy-MM-dd HH:mm"));
/* 2516 */       log47.setOperatetype(Short.valueOf((short)1));
/* 2517 */       log47.setLoglevel(Short.valueOf((short)1));
/* 2518 */       this.commonDao.saveOrUpdate(log47);
/*      */ 
/* 2520 */       TSLog log48 = new TSLog();
/* 2521 */       log48.setLogcontent("用户: admin登录成功");
/* 2522 */       log48.setBroswer("MSIE 8.0");
/* 2523 */       log48.setNote("192.168.197.1");
/* 2524 */       log48.setTSUser(admin);
/* 2525 */       log48.setOperatetime(DateUtils.parseTimestamp("2013-7-7 16:11:49", 
/* 2526 */         "yyyy-MM-dd HH:mm"));
/* 2527 */       log48.setOperatetype(Short.valueOf((short)1));
/* 2528 */       log48.setLoglevel(Short.valueOf((short)1));
/* 2529 */       this.commonDao.saveOrUpdate(log48);
/*      */ 
/* 2531 */       TSLog log49 = new TSLog();
/* 2532 */       log49.setLogcontent("用户: admin登录成功");
/* 2533 */       log49.setBroswer("MSIE 8.0");
/* 2534 */       log49.setNote("192.168.197.1");
/* 2535 */       log49.setTSUser(admin);
/* 2536 */       log49.setOperatetime(DateUtils.parseTimestamp("2013-7-7 16:13:44", 
/* 2537 */         "yyyy-MM-dd HH:mm"));
/* 2538 */       log49.setOperatetype(Short.valueOf((short)1));
/* 2539 */       log49.setLoglevel(Short.valueOf((short)1));
/* 2540 */       this.commonDao.saveOrUpdate(log49);
/*      */ 
/* 2542 */       TSLog log50 = new TSLog();
/* 2543 */       log50.setLogcontent("用户: admin登录成功");
/* 2544 */       log50.setBroswer("MSIE 8.0");
/* 2545 */       log50.setNote("192.168.197.1");
/* 2546 */       log50.setTSUser(admin);
/* 2547 */       log50.setOperatetime(DateUtils.parseTimestamp("2013-7-7 16:16:52", 
/* 2548 */         "yyyy-MM-dd HH:mm"));
/* 2549 */       log50.setOperatetype(Short.valueOf((short)1));
/* 2550 */       log50.setLoglevel(Short.valueOf((short)1));
/* 2551 */       this.commonDao.saveOrUpdate(log50);
/*      */ 
/* 2553 */       TSLog log51 = new TSLog();
/* 2554 */       log51.setLogcontent("用户: admin登录成功");
/* 2555 */       log51.setBroswer("MSIE 8.0");
/* 2556 */       log51.setNote("192.168.197.1");
/* 2557 */       log51.setTSUser(admin);
/* 2558 */       log51.setOperatetime(DateUtils.parseTimestamp("2013-7-7 16:19:18", 
/* 2559 */         "yyyy-MM-dd HH:mm"));
/* 2560 */       log51.setOperatetype(Short.valueOf((short)1));
/* 2561 */       log51.setLoglevel(Short.valueOf((short)1));
/* 2562 */       this.commonDao.saveOrUpdate(log51);
/*      */ 
/* 2564 */       TSLog log52 = new TSLog();
/* 2565 */       log52.setLogcontent("用户: admin登录成功");
/* 2566 */       log52.setBroswer("MSIE 8.0");
/* 2567 */       log52.setNote("192.168.197.1");
/* 2568 */       log52.setTSUser(admin);
/* 2569 */       log52.setOperatetime(DateUtils.parseTimestamp("2013-7-7 16:27:05", 
/* 2570 */         "yyyy-MM-dd HH:mm"));
/* 2571 */       log52.setOperatetype(Short.valueOf((short)1));
/* 2572 */       log52.setLoglevel(Short.valueOf((short)1));
/* 2573 */       this.commonDao.saveOrUpdate(log52);
/*      */ 
/* 2575 */       TSLog log53 = new TSLog();
/* 2576 */       log53.setLogcontent("用户: admin登录成功");
/* 2577 */       log53.setBroswer("MSIE 8.0");
/* 2578 */       log53.setNote("192.168.197.1");
/* 2579 */       log53.setTSUser(admin);
/* 2580 */       log53.setOperatetime(DateUtils.parseTimestamp("2013-7-7 16:42:32", 
/* 2581 */         "yyyy-MM-dd HH:mm"));
/* 2582 */       log53.setOperatetype(Short.valueOf((short)1));
/* 2583 */       log53.setLoglevel(Short.valueOf((short)1));
/* 2584 */       this.commonDao.saveOrUpdate(log53);
/*      */ 
/* 2586 */       TSLog log54 = new TSLog();
/* 2587 */       log54.setLogcontent("用户: admin登录成功");
/* 2588 */       log54.setBroswer("MSIE 8.0");
/* 2589 */       log54.setNote("192.168.197.1");
/* 2590 */       log54.setTSUser(admin);
/* 2591 */       log54.setOperatetime(DateUtils.parseTimestamp("2013-7-7 16:44:38", 
/* 2592 */         "yyyy-MM-dd HH:mm"));
/* 2593 */       log54.setOperatetype(Short.valueOf((short)1));
/* 2594 */       log54.setLoglevel(Short.valueOf((short)1));
/* 2595 */       this.commonDao.saveOrUpdate(log54);
/*      */ 
/* 2597 */       TSLog log55 = new TSLog();
/* 2598 */       log55.setLogcontent("用户: admin登录成功");
/* 2599 */       log55.setBroswer("MSIE 8.0");
/* 2600 */       log55.setNote("192.168.197.1");
/* 2601 */       log55.setTSUser(admin);
/* 2602 */       log55.setOperatetime(DateUtils.parseTimestamp("2013-7-7 16:49:06", 
/* 2603 */         "yyyy-MM-dd HH:mm"));
/* 2604 */       log55.setOperatetype(Short.valueOf((short)1));
/* 2605 */       log55.setLoglevel(Short.valueOf((short)1));
/* 2606 */       this.commonDao.saveOrUpdate(log55);
/*      */ 
/* 2608 */       TSLog log56 = new TSLog();
/* 2609 */       log56.setLogcontent("用户: admin登录成功");
/* 2610 */       log56.setBroswer("MSIE 8.0");
/* 2611 */       log56.setNote("192.168.197.1");
/* 2612 */       log56.setTSUser(admin);
/* 2613 */       log56.setOperatetime(DateUtils.parseTimestamp("2013-7-7 16:50:51", 
/* 2614 */         "yyyy-MM-dd HH:mm"));
/* 2615 */       log56.setOperatetype(Short.valueOf((short)1));
/* 2616 */       log56.setLoglevel(Short.valueOf((short)1));
/* 2617 */       this.commonDao.saveOrUpdate(log56);
/*      */ 
/* 2619 */       TSLog log57 = new TSLog();
/* 2620 */       log57.setLogcontent("用户: admin登录成功");
/* 2621 */       log57.setBroswer("MSIE 8.0");
/* 2622 */       log57.setNote("192.168.197.1");
/* 2623 */       log57.setTSUser(admin);
/* 2624 */       log57.setOperatetime(DateUtils.parseTimestamp("2013-7-7 16:53:48", 
/* 2625 */         "yyyy-MM-dd HH:mm"));
/* 2626 */       log57.setOperatetype(Short.valueOf((short)1));
/* 2627 */       log57.setLoglevel(Short.valueOf((short)1));
/* 2628 */       this.commonDao.saveOrUpdate(log57);
/*      */ 
/* 2630 */       TSLog log58 = new TSLog();
/* 2631 */       log58.setLogcontent("修改成功");
/* 2632 */       log58.setBroswer("MSIE 8.0");
/* 2633 */       log58.setNote("192.168.197.1");
/* 2634 */       log58.setTSUser(admin);
/* 2635 */       log58.setOperatetime(DateUtils.parseTimestamp("2013-7-7 16:56:45", 
/* 2636 */         "yyyy-MM-dd HH:mm"));
/* 2637 */       log58.setOperatetype(Short.valueOf((short)1));
/* 2638 */       log58.setLoglevel(Short.valueOf((short)5));
/* 2639 */       this.commonDao.saveOrUpdate(log58);
/*      */ 
/* 2641 */       TSLog log59 = new TSLog();
/* 2642 */       log59.setLogcontent("用户: admin登录成功");
/* 2643 */       log59.setBroswer("MSIE 8.0");
/* 2644 */       log59.setNote("192.168.197.1");
/* 2645 */       log59.setTSUser(admin);
/* 2646 */       log59.setOperatetime(DateUtils.parseTimestamp("2013-7-7 16:59:22", 
/* 2647 */         "yyyy-MM-dd HH:mm"));
/* 2648 */       log59.setOperatetype(Short.valueOf((short)1));
/* 2649 */       log59.setLoglevel(Short.valueOf((short)1));
/* 2650 */       this.commonDao.saveOrUpdate(log59);
/*      */ 
/* 2652 */       TSLog log60 = new TSLog();
/* 2653 */       log60.setLogcontent("创建成功");
/* 2654 */       log60.setBroswer("MSIE 8.0");
/* 2655 */       log60.setNote("192.168.197.1");
/* 2656 */       log60.setTSUser(admin);
/* 2657 */       log60.setOperatetime(DateUtils.parseTimestamp("2013-7-7 17:22:42", 
/* 2658 */         "yyyy-MM-dd HH:mm"));
/* 2659 */       log60.setOperatetype(Short.valueOf((short)1));
/* 2660 */       log60.setLoglevel(Short.valueOf((short)3));
/* 2661 */       this.commonDao.saveOrUpdate(log60);
/*      */ 
/* 2663 */       TSLog log61 = new TSLog();
/* 2664 */       log61.setLogcontent("修改成功");
/* 2665 */       log61.setBroswer("MSIE 8.0");
/* 2666 */       log61.setNote("192.168.197.1");
/* 2667 */       log61.setTSUser(admin);
/* 2668 */       log61.setOperatetime(DateUtils.parseTimestamp("2013-7-7 17:26:03", 
/* 2669 */         "yyyy-MM-dd HH:mm"));
/* 2670 */       log61.setOperatetype(Short.valueOf((short)1));
/* 2671 */       log61.setLoglevel(Short.valueOf((short)5));
/* 2672 */       this.commonDao.saveOrUpdate(log61);
/*      */ 
/* 2674 */       TSLog log62 = new TSLog();
/* 2675 */       log62.setLogcontent("删除成功");
/* 2676 */       log62.setBroswer("MSIE 8.0");
/* 2677 */       log62.setNote("192.168.197.1");
/* 2678 */       log62.setTSUser(admin);
/* 2679 */       log62.setOperatetime(DateUtils.parseTimestamp("2013-7-7 17:31:00", 
/* 2680 */         "yyyy-MM-dd HH:mm"));
/* 2681 */       log62.setOperatetype(Short.valueOf((short)1));
/* 2682 */       log62.setLoglevel(Short.valueOf((short)4));
/* 2683 */       this.commonDao.saveOrUpdate(log62);
/*      */ 
/* 2685 */       TSLog log63 = new TSLog();
/* 2686 */       log63.setLogcontent("修改成功");
/* 2687 */       log63.setBroswer("MSIE 8.0");
/* 2688 */       log63.setNote("192.168.197.1");
/* 2689 */       log63.setTSUser(admin);
/* 2690 */       log63.setOperatetime(DateUtils.parseTimestamp("2013-7-7 17:35:02", 
/* 2691 */         "yyyy-MM-dd HH:mm"));
/* 2692 */       log63.setOperatetype(Short.valueOf((short)1));
/* 2693 */       log63.setLoglevel(Short.valueOf((short)5));
/* 2694 */       this.commonDao.saveOrUpdate(log63);
/*      */ 
/* 2696 */       TSLog log64 = new TSLog();
/* 2697 */       log64.setLogcontent("用户: admin登录成功");
/* 2698 */       log64.setBroswer("MSIE 8.0");
/* 2699 */       log64.setNote("192.168.197.1");
/* 2700 */       log64.setTSUser(admin);
/* 2701 */       log64.setOperatetime(DateUtils.parseTimestamp("2013-7-7 17:46:39", 
/* 2702 */         "yyyy-MM-dd HH:mm"));
/* 2703 */       log64.setOperatetype(Short.valueOf((short)1));
/* 2704 */       log64.setLoglevel(Short.valueOf((short)1));
/* 2705 */       this.commonDao.saveOrUpdate(log64);
/*      */ 
/* 2707 */       TSLog log65 = new TSLog();
/* 2708 */       log65.setLogcontent("用户: admin登录成功");
/* 2709 */       log65.setBroswer("MSIE 8.0");
/* 2710 */       log65.setNote("192.168.197.1");
/* 2711 */       log65.setTSUser(admin);
/* 2712 */       log65.setOperatetime(DateUtils.parseTimestamp("2013-7-7 17:55:01", 
/* 2713 */         "yyyy-MM-dd HH:mm"));
/* 2714 */       log65.setOperatetype(Short.valueOf((short)1));
/* 2715 */       log65.setLoglevel(Short.valueOf((short)1));
/* 2716 */       this.commonDao.saveOrUpdate(log65);
/*      */ 
/* 2718 */       TSLog log66 = new TSLog();
/* 2719 */       log66.setLogcontent("用户: admin登录成功");
/* 2720 */       log66.setBroswer("MSIE 8.0");
/* 2721 */       log66.setNote("192.168.197.1");
/* 2722 */       log66.setTSUser(admin);
/* 2723 */       log66.setOperatetime(DateUtils.parseTimestamp("2013-7-7 18:08:56", 
/* 2724 */         "yyyy-MM-dd HH:mm"));
/* 2725 */       log66.setOperatetype(Short.valueOf((short)1));
/* 2726 */       log66.setLoglevel(Short.valueOf((short)1));
/* 2727 */       this.commonDao.saveOrUpdate(log66);
/*      */ 
/* 2729 */       TSLog log67 = new TSLog();
/* 2730 */       log67.setLogcontent("用户: admin登录成功");
/* 2731 */       log67.setBroswer("MSIE 8.0");
/* 2732 */       log67.setNote("192.168.197.1");
/* 2733 */       log67.setTSUser(admin);
/* 2734 */       log67.setOperatetime(DateUtils.parseTimestamp("2013-7-7 18:13:02", 
/* 2735 */         "yyyy-MM-dd HH:mm"));
/* 2736 */       log67.setOperatetype(Short.valueOf((short)1));
/* 2737 */       log67.setLoglevel(Short.valueOf((short)1));
/* 2738 */       this.commonDao.saveOrUpdate(log67);
/*      */ 
/* 2740 */       TSLog log68 = new TSLog();
/* 2741 */       log68.setLogcontent("用户: admin登录成功");
/* 2742 */       log68.setBroswer("MSIE 8.0");
/* 2743 */       log68.setNote("192.168.197.1");
/* 2744 */       log68.setTSUser(admin);
/* 2745 */       log68.setOperatetime(DateUtils.parseTimestamp("2013-7-7 18:15:50", 
/* 2746 */         "yyyy-MM-dd HH:mm"));
/* 2747 */       log68.setOperatetype(Short.valueOf((short)1));
/* 2748 */       log68.setLoglevel(Short.valueOf((short)1));
/* 2749 */       this.commonDao.saveOrUpdate(log68);
/*      */ 
/* 2751 */       TSLog log69 = new TSLog();
/* 2752 */       log69.setLogcontent("修改成功");
/* 2753 */       log69.setBroswer("MSIE 8.0");
/* 2754 */       log69.setNote("192.168.197.1");
/* 2755 */       log69.setTSUser(admin);
/* 2756 */       log69.setOperatetime(DateUtils.parseTimestamp("2013-7-7 18:28:42", 
/* 2757 */         "yyyy-MM-dd HH:mm"));
/* 2758 */       log69.setOperatetype(Short.valueOf((short)1));
/* 2759 */       log69.setLoglevel(Short.valueOf((short)5));
/* 2760 */       this.commonDao.saveOrUpdate(log69);
/*      */ 
/* 2762 */       TSLog log70 = new TSLog();
/* 2763 */       log70.setLogcontent("修改成功");
/* 2764 */       log70.setBroswer("MSIE 8.0");
/* 2765 */       log70.setNote("192.168.197.1");
/* 2766 */       log70.setTSUser(admin);
/* 2767 */       log70.setOperatetime(DateUtils.parseTimestamp("2013-7-7 18:29:12", 
/* 2768 */         "yyyy-MM-dd HH:mm"));
/* 2769 */       log70.setOperatetype(Short.valueOf((short)1));
/* 2770 */       log70.setLoglevel(Short.valueOf((short)5));
/* 2771 */       this.commonDao.saveOrUpdate(log70);
/*      */ 
/* 2773 */       TSLog log71 = new TSLog();
/* 2774 */       log71.setLogcontent("修改成功");
/* 2775 */       log71.setBroswer("MSIE 8.0");
/* 2776 */       log71.setNote("192.168.197.1");
/* 2777 */       log71.setTSUser(admin);
/* 2778 */       log71.setOperatetime(DateUtils.parseTimestamp("2013-7-7 18:30:12", 
/* 2779 */         "yyyy-MM-dd HH:mm"));
/* 2780 */       log71.setOperatetype(Short.valueOf((short)1));
/* 2781 */       log71.setLoglevel(Short.valueOf((short)5));
/* 2782 */       this.commonDao.saveOrUpdate(log71);
/*      */ 
/* 2784 */       TSLog log72 = new TSLog();
/* 2785 */       log72.setLogcontent("修改成功");
/* 2786 */       log72.setBroswer("MSIE 8.0");
/* 2787 */       log72.setNote("192.168.197.1");
/* 2788 */       log72.setTSUser(admin);
/* 2789 */       log72.setOperatetime(DateUtils.parseTimestamp("2013-7-7 18:31:00", 
/* 2790 */         "yyyy-MM-dd HH:mm"));
/* 2791 */       log72.setOperatetype(Short.valueOf((short)1));
/* 2792 */       log72.setLoglevel(Short.valueOf((short)5));
/* 2793 */       this.commonDao.saveOrUpdate(log72);
/*      */ 
/* 2795 */       TSLog log73 = new TSLog();
/* 2796 */       log73.setLogcontent("修改成功");
/* 2797 */       log73.setBroswer("MSIE 8.0");
/* 2798 */       log73.setNote("192.168.197.1");
/* 2799 */       log73.setTSUser(admin);
/* 2800 */       log73.setOperatetime(DateUtils.parseTimestamp("2013-7-7 18:31:26", 
/* 2801 */         "yyyy-MM-dd HH:mm"));
/* 2802 */       log73.setOperatetype(Short.valueOf((short)1));
/* 2803 */       log73.setLoglevel(Short.valueOf((short)5));
/* 2804 */       this.commonDao.saveOrUpdate(log73);
/*      */ 
/* 2806 */       TSLog log74 = new TSLog();
/* 2807 */       log74.setLogcontent("物品: 555添加成功");
/* 2808 */       log74.setBroswer("MSIE 9.0");
/* 2809 */       log74.setNote("192.168.1.103");
/* 2810 */       log74.setTSUser(admin);
/* 2811 */       log74.setOperatetime(DateUtils.parseTimestamp("2013-3-20 23:03:06", 
/* 2812 */         "yyyy-MM-dd HH:mm"));
/* 2813 */       log74.setOperatetype(Short.valueOf((short)1));
/* 2814 */       log74.setLoglevel(Short.valueOf((short)3));
/* 2815 */       this.commonDao.saveOrUpdate(log74);
/*      */ 
/* 2817 */       TSLog log75 = new TSLog();
/* 2818 */       log75.setLogcontent("用户: admin登录成功");
/* 2819 */       log75.setBroswer("MSIE 9.0");
/* 2820 */       log75.setNote("192.168.1.103");
/* 2821 */       log75.setTSUser(admin);
/* 2822 */       log75.setOperatetime(DateUtils.parseTimestamp("2013-3-20 23:19:25", 
/* 2823 */         "yyyy-MM-dd HH:mm"));
/* 2824 */       log75.setOperatetype(Short.valueOf((short)1));
/* 2825 */       log75.setLoglevel(Short.valueOf((short)1));
/* 2826 */       this.commonDao.saveOrUpdate(log75);
/*      */ 
/* 2828 */       TSLog log76 = new TSLog();
/* 2829 */       log76.setLogcontent("用户: admin登录成功");
/* 2830 */       log76.setBroswer("MSIE 9.0");
/* 2831 */       log76.setNote("192.168.1.103");
/* 2832 */       log76.setTSUser(admin);
/* 2833 */       log76.setOperatetime(DateUtils.parseTimestamp("2013-3-21 20:09:02", 
/* 2834 */         "yyyy-MM-dd HH:mm"));
/* 2835 */       log76.setOperatetype(Short.valueOf((short)1));
/* 2836 */       log76.setLoglevel(Short.valueOf((short)1));
/* 2837 */       this.commonDao.saveOrUpdate(log76);
/*      */ 
/* 2839 */       TSLog log77 = new TSLog();
/* 2840 */       log77.setLogcontent("用户: admin登录成功");
/* 2841 */       log77.setBroswer("MSIE 9.0");
/* 2842 */       log77.setNote("192.168.1.103");
/* 2843 */       log77.setTSUser(admin);
/* 2844 */       log77.setOperatetime(DateUtils.parseTimestamp("2013-3-21 20:27:25", 
/* 2845 */         "yyyy-MM-dd HH:mm"));
/* 2846 */       log77.setOperatetype(Short.valueOf((short)1));
/* 2847 */       log77.setLoglevel(Short.valueOf((short)1));
/* 2848 */       this.commonDao.saveOrUpdate(log77);
/*      */ 
/* 2850 */       TSLog log78 = new TSLog();
/* 2851 */       log78.setLogcontent("用户: admin登录成功");
/* 2852 */       log78.setBroswer("MSIE 9.0");
/* 2853 */       log78.setNote("192.168.1.103");
/* 2854 */       log78.setTSUser(admin);
/* 2855 */       log78.setOperatetime(DateUtils.parseTimestamp("2013-3-21 20:44:40", 
/* 2856 */         "yyyy-MM-dd HH:mm"));
/* 2857 */       log78.setOperatetype(Short.valueOf((short)1));
/* 2858 */       log78.setLoglevel(Short.valueOf((short)1));
/* 2859 */       this.commonDao.saveOrUpdate(log78);
/*      */ 
/* 2861 */       TSLog log79 = new TSLog();
/* 2862 */       log79.setLogcontent("用户: admin登录成功");
/* 2863 */       log79.setBroswer("MSIE 9.0");
/* 2864 */       log79.setNote("192.168.1.103");
/* 2865 */       log79.setTSUser(admin);
/* 2866 */       log79.setOperatetime(DateUtils.parseTimestamp("2013-3-21 20:54:13", 
/* 2867 */         "yyyy-MM-dd HH:mm"));
/* 2868 */       log79.setOperatetype(Short.valueOf((short)1));
/* 2869 */       log79.setLoglevel(Short.valueOf((short)1));
/* 2870 */       this.commonDao.saveOrUpdate(log79);
/*      */ 
/* 2872 */       TSLog log80 = new TSLog();
/* 2873 */       log80.setLogcontent("用户: admin登录成功");
/* 2874 */       log80.setBroswer("MSIE 9.0");
/* 2875 */       log80.setNote("192.168.1.103");
/* 2876 */       log80.setTSUser(admin);
/* 2877 */       log80.setOperatetime(DateUtils.parseTimestamp("2013-3-21 21:01:54", 
/* 2878 */         "yyyy-MM-dd HH:mm"));
/* 2879 */       log80.setOperatetype(Short.valueOf((short)1));
/* 2880 */       log80.setLoglevel(Short.valueOf((short)1));
/* 2881 */       this.commonDao.saveOrUpdate(log80);
/*      */ 
/* 2883 */       TSLog log81 = new TSLog();
/* 2884 */       log81.setLogcontent("用户: admin登录成功");
/* 2885 */       log81.setBroswer("MSIE 9.0");
/* 2886 */       log81.setNote("192.168.1.103");
/* 2887 */       log81.setTSUser(admin);
/* 2888 */       log81.setOperatetime(DateUtils.parseTimestamp("2013-3-21 21:13:05", 
/* 2889 */         "yyyy-MM-dd HH:mm"));
/* 2890 */       log81.setOperatetype(Short.valueOf((short)1));
/* 2891 */       log81.setLoglevel(Short.valueOf((short)1));
/* 2892 */       this.commonDao.saveOrUpdate(log81);
/*      */ 
/* 2894 */       TSLog log82 = new TSLog();
/* 2895 */       log82.setLogcontent("物品: 55添加成功");
/* 2896 */       log82.setBroswer("MSIE 9.0");
/* 2897 */       log82.setNote("192.168.1.103");
/* 2898 */       log82.setTSUser(admin);
/* 2899 */       log82.setOperatetime(DateUtils.parseTimestamp("2013-3-21 21:15:07", 
/* 2900 */         "yyyy-MM-dd HH:mm"));
/* 2901 */       log82.setOperatetype(Short.valueOf((short)1));
/* 2902 */       log82.setLoglevel(Short.valueOf((short)3));
/* 2903 */       this.commonDao.saveOrUpdate(log82);
/*      */ 
/* 2905 */       TSLog log83 = new TSLog();
/* 2906 */       log83.setLogcontent("用户: admin登录成功");
/* 2907 */       log83.setBroswer("MSIE 9.0");
/* 2908 */       log83.setNote("192.168.1.103");
/* 2909 */       log83.setTSUser(admin);
/* 2910 */       log83.setOperatetime(DateUtils.parseTimestamp("2013-3-21 21:22:57", 
/* 2911 */         "yyyy-MM-dd HH:mm"));
/* 2912 */       log83.setOperatetype(Short.valueOf((short)1));
/* 2913 */       log83.setLoglevel(Short.valueOf((short)1));
/* 2914 */       this.commonDao.saveOrUpdate(log83);
/*      */ 
/* 2916 */       TSLog log84 = new TSLog();
/* 2917 */       log84.setLogcontent("物品: 55添加成功");
/* 2918 */       log84.setBroswer("MSIE 9.0");
/* 2919 */       log84.setNote("192.168.1.103");
/* 2920 */       log84.setTSUser(admin);
/* 2921 */       log84.setOperatetime(DateUtils.parseTimestamp("2013-3-21 21:23:12", 
/* 2922 */         "yyyy-MM-dd HH:mm"));
/* 2923 */       log84.setOperatetype(Short.valueOf((short)1));
/* 2924 */       log84.setLoglevel(Short.valueOf((short)3));
/* 2925 */       this.commonDao.saveOrUpdate(log84);
/*      */ 
/* 2927 */       TSLog log85 = new TSLog();
/* 2928 */       log85.setLogcontent("物品: 33添加成功");
/* 2929 */       log85.setBroswer("MSIE 9.0");
/* 2930 */       log85.setNote("192.168.1.103");
/* 2931 */       log85.setTSUser(admin);
/* 2932 */       log85.setOperatetime(DateUtils.parseTimestamp("2013-3-21 21:23:47", 
/* 2933 */         "yyyy-MM-dd HH:mm"));
/* 2934 */       log85.setOperatetype(Short.valueOf((short)1));
/* 2935 */       log85.setLoglevel(Short.valueOf((short)3));
/* 2936 */       this.commonDao.saveOrUpdate(log85);
/*      */ 
/* 2938 */       TSLog log86 = new TSLog();
/* 2939 */       log86.setLogcontent("用户: admin登录成功");
/* 2940 */       log86.setBroswer("MSIE 9.0");
/* 2941 */       log86.setNote("192.168.1.103");
/* 2942 */       log86.setTSUser(admin);
/* 2943 */       log86.setOperatetime(DateUtils.parseTimestamp("2013-3-21 21:25:09", 
/* 2944 */         "yyyy-MM-dd HH:mm"));
/* 2945 */       log86.setOperatetype(Short.valueOf((short)1));
/* 2946 */       log86.setLoglevel(Short.valueOf((short)1));
/* 2947 */       this.commonDao.saveOrUpdate(log86);
/*      */ 
/* 2949 */       TSLog log87 = new TSLog();
/* 2950 */       log87.setLogcontent("用户: admin登录成功");
/* 2951 */       log87.setBroswer("MSIE 9.0");
/* 2952 */       log87.setNote("192.168.1.103");
/* 2953 */       log87.setTSUser(admin);
/* 2954 */       log87.setOperatetime(DateUtils.parseTimestamp("2013-3-21 21:27:58", 
/* 2955 */         "yyyy-MM-dd HH:mm"));
/* 2956 */       log87.setOperatetype(Short.valueOf((short)1));
/* 2957 */       log87.setLoglevel(Short.valueOf((short)1));
/* 2958 */       this.commonDao.saveOrUpdate(log87);
/*      */ 
/* 2960 */       TSLog log88 = new TSLog();
/* 2961 */       log88.setLogcontent("权限: 采购审批被添加成功");
/* 2962 */       log88.setBroswer("MSIE 9.0");
/* 2963 */       log88.setNote("192.168.1.103");
/* 2964 */       log88.setTSUser(admin);
/* 2965 */       log88.setOperatetime(DateUtils.parseTimestamp("2013-3-21 21:29:04", 
/* 2966 */         "yyyy-MM-dd HH:mm"));
/* 2967 */       log88.setOperatetype(Short.valueOf((short)1));
/* 2968 */       log88.setLoglevel(Short.valueOf((short)3));
/* 2969 */       this.commonDao.saveOrUpdate(log88);
/*      */ 
/* 2971 */       TSLog log89 = new TSLog();
/* 2972 */       log89.setLogcontent("权限: 采购审批被更新成功");
/* 2973 */       log89.setBroswer("MSIE 9.0");
/* 2974 */       log89.setNote("192.168.1.103");
/* 2975 */       log89.setTSUser(admin);
/* 2976 */       log89.setOperatetime(DateUtils.parseTimestamp("2013-3-21 21:29:56", 
/* 2977 */         "yyyy-MM-dd HH:mm"));
/* 2978 */       log89.setOperatetype(Short.valueOf((short)1));
/* 2979 */       log89.setLoglevel(Short.valueOf((short)5));
/* 2980 */       this.commonDao.saveOrUpdate(log89);
/*      */ 
/* 2982 */       TSLog log90 = new TSLog();
/* 2983 */       log90.setLogcontent("权限: 采购审批被更新成功");
/* 2984 */       log90.setBroswer("MSIE 9.0");
/* 2985 */       log90.setNote("192.168.1.103");
/* 2986 */       log90.setTSUser(admin);
/* 2987 */       log90.setOperatetime(DateUtils.parseTimestamp("2013-3-21 21:30:08", 
/* 2988 */         "yyyy-MM-dd HH:mm"));
/* 2989 */       log90.setOperatetype(Short.valueOf((short)1));
/* 2990 */       log90.setLoglevel(Short.valueOf((short)5));
/* 2991 */       this.commonDao.saveOrUpdate(log90);
/*      */ 
/* 2993 */       TSLog log91 = new TSLog();
/* 2994 */       log91.setLogcontent("用户: admin更新成功");
/* 2995 */       log91.setBroswer("MSIE 9.0");
/* 2996 */       log91.setNote("192.168.1.103");
/* 2997 */       log91.setTSUser(admin);
/* 2998 */       log91.setOperatetime(DateUtils.parseTimestamp("2013-3-21 21:31:21", 
/* 2999 */         "yyyy-MM-dd HH:mm"));
/* 3000 */       log91.setOperatetype(Short.valueOf((short)1));
/* 3001 */       log91.setLoglevel(Short.valueOf((short)5));
/* 3002 */       this.commonDao.saveOrUpdate(log91);
/*      */ 
/* 3004 */       TSLog log92 = new TSLog();
/* 3005 */       log92.setLogcontent("流程参数: 采购审批员审批被添加成功");
/* 3006 */       log92.setBroswer("MSIE 9.0");
/* 3007 */       log92.setNote("192.168.1.103");
/* 3008 */       log92.setTSUser(admin);
/* 3009 */       log92.setOperatetime(DateUtils.parseTimestamp("2013-3-21 21:36:03", 
/* 3010 */         "yyyy-MM-dd HH:mm"));
/* 3011 */       log92.setOperatetype(Short.valueOf((short)1));
/* 3012 */       log92.setLoglevel(Short.valueOf((short)3));
/* 3013 */       this.commonDao.saveOrUpdate(log92);
/*      */ 
/* 3015 */       TSLog log93 = new TSLog();
/* 3016 */       log93.setLogcontent("流程参数: 采购审批员审批被更新成功");
/* 3017 */       log93.setBroswer("MSIE 9.0");
/* 3018 */       log93.setNote("192.168.1.103");
/* 3019 */       log93.setTSUser(admin);
/* 3020 */       log93.setOperatetime(DateUtils.parseTimestamp("2013-3-21 21:36:11", 
/* 3021 */         "yyyy-MM-dd HH:mm"));
/* 3022 */       log93.setOperatetype(Short.valueOf((short)1));
/* 3023 */       log93.setLoglevel(Short.valueOf((short)5));
/* 3024 */       this.commonDao.saveOrUpdate(log93);
/*      */ 
/* 3026 */       TSLog log94 = new TSLog();
/* 3027 */       log94.setLogcontent("流程参数: 采购审批员审批被更新成功");
/* 3028 */       log94.setBroswer("MSIE 9.0");
/* 3029 */       log94.setNote("192.168.1.103");
/* 3030 */       log94.setTSUser(admin);
/* 3031 */       log94.setOperatetime(DateUtils.parseTimestamp("2013-3-21 21:37:16", 
/* 3032 */         "yyyy-MM-dd HH:mm"));
/* 3033 */       log94.setOperatetype(Short.valueOf((short)1));
/* 3034 */       log94.setLoglevel(Short.valueOf((short)5));
/* 3035 */       this.commonDao.saveOrUpdate(log94);
/*      */ 
/* 3037 */       TSLog log95 = new TSLog();
/* 3038 */       log95.setLogcontent("流程类别: 采购审批员审批被删除 成功");
/* 3039 */       log95.setBroswer("MSIE 9.0");
/* 3040 */       log95.setNote("192.168.1.103");
/* 3041 */       log95.setTSUser(admin);
/* 3042 */       log95.setOperatetime(DateUtils.parseTimestamp("2013-3-21 21:38:20", 
/* 3043 */         "yyyy-MM-dd HH:mm"));
/* 3044 */       log95.setOperatetype(Short.valueOf((short)1));
/* 3045 */       log95.setLoglevel(Short.valueOf((short)4));
/* 3046 */       this.commonDao.saveOrUpdate(log95);
/*      */ 
/* 3048 */       TSLog log96 = new TSLog();
/* 3049 */       log96.setLogcontent("物品: 44添加成功");
/* 3050 */       log96.setBroswer("MSIE 9.0");
/* 3051 */       log96.setNote("192.168.1.103");
/* 3052 */       log96.setTSUser(admin);
/* 3053 */       log96.setOperatetime(DateUtils.parseTimestamp("2013-3-21 21:43:51", 
/* 3054 */         "yyyy-MM-dd HH:mm"));
/* 3055 */       log96.setOperatetype(Short.valueOf((short)1));
/* 3056 */       log96.setLoglevel(Short.valueOf((short)3));
/* 3057 */       this.commonDao.saveOrUpdate(log96);
/*      */ 
/* 3059 */       TSLog log97 = new TSLog();
/* 3060 */       log97.setLogcontent("用户: admin登录成功");
/* 3061 */       log97.setBroswer("MSIE 9.0");
/* 3062 */       log97.setNote("192.168.1.105");
/* 3063 */       log97.setTSUser(admin);
/* 3064 */       log97.setOperatetime(DateUtils.parseTimestamp("2013-2-7 10:10:29", 
/* 3065 */         "yyyy-MM-dd HH:mm"));
/* 3066 */       log97.setOperatetype(Short.valueOf((short)1));
/* 3067 */       log97.setLoglevel(Short.valueOf((short)1));
/* 3068 */       this.commonDao.saveOrUpdate(log97);
/*      */ 
/* 3070 */       TSLog log98 = new TSLog();
/* 3071 */       log98.setLogcontent("权限: 上传下载被添加成功");
/* 3072 */       log98.setBroswer("MSIE 9.0");
/* 3073 */       log98.setNote("192.168.1.105");
/* 3074 */       log98.setTSUser(admin);
/* 3075 */       log98.setOperatetime(DateUtils.parseTimestamp("2013-2-7 11:07:26", 
/* 3076 */         "yyyy-MM-dd HH:mm"));
/* 3077 */       log98.setOperatetype(Short.valueOf((short)1));
/* 3078 */       log98.setLoglevel(Short.valueOf((short)3));
/* 3079 */       this.commonDao.saveOrUpdate(log98);
/*      */ 
/* 3081 */       TSLog log99 = new TSLog();
/* 3082 */       log99.setLogcontent("权限: 插件演示被删除成功");
/* 3083 */       log99.setBroswer("MSIE 9.0");
/* 3084 */       log99.setNote("192.168.1.105");
/* 3085 */       log99.setTSUser(admin);
/* 3086 */       log99.setOperatetime(DateUtils.parseTimestamp("2013-2-7 11:07:39", 
/* 3087 */         "yyyy-MM-dd HH:mm"));
/* 3088 */       log99.setOperatetype(Short.valueOf((short)1));
/* 3089 */       log99.setLoglevel(Short.valueOf((short)4));
/* 3090 */       this.commonDao.saveOrUpdate(log99);
/*      */ 
/* 3092 */       TSLog log100 = new TSLog();
/* 3093 */       log100.setLogcontent("用户: admin登录成功");
/* 3094 */       log100.setBroswer("MSIE 9.0");
/* 3095 */       log100.setNote("192.168.1.105");
/* 3096 */       log100.setTSUser(admin);
/* 3097 */       log100.setOperatetime(DateUtils.parseTimestamp("2013-2-7 11:07:54", 
/* 3098 */         "yyyy-MM-dd HH:mm"));
/* 3099 */       log100.setOperatetype(Short.valueOf((short)1));
/* 3100 */       log100.setLoglevel(Short.valueOf((short)1));
/* 3101 */       this.commonDao.saveOrUpdate(log100);
/*      */     } catch (ParseException e) {
/* 3103 */       e.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void repairDemo()
/*      */   {
/* 3112 */     TSDemo demo = new TSDemo();
/*      */ 
/* 3114 */     String html = new FreemarkerHelper().parseTemplate("/org/jeecgframework/web/system/txt/valid-code-demo.ftl", null);
/* 3115 */     demo.setDemocode(html);
/* 3116 */     demo.setDemotitle("表单验证");
/* 3117 */     this.commonDao.saveOrUpdate(demo);
/*      */   }
/*      */ 
/*      */   private void repairUser()
/*      */   {
/* 3126 */     TSUser admin = new TSUser();
/* 3127 */     admin.setSignatureFile("images/renfang/qm/licf.gif");
/* 3128 */     admin.setStatus(Short.valueOf((short)1));
/* 3129 */     admin.setRealName("管理员");
/* 3130 */     admin.setUserName("admin");
/* 3131 */     admin.setPassword("c44b01947c9e6e3f");
/*      */ 
/* 3133 */     admin.setActivitiSync(Short.valueOf((short)1));
/* 3134 */     this.commonDao.saveOrUpdate(admin);
/*      */ 
/* 3136 */     TSUser scott = new TSUser();
/* 3137 */     scott.setMobilePhone("13426432910");
/* 3138 */     scott.setOfficePhone("7496661");
/* 3139 */     scott.setEmail("zhangdaiscott@163.com");
/* 3140 */     scott.setStatus(Short.valueOf((short)1));
/* 3141 */     scott.setRealName("张代浩");
/* 3142 */     scott.setUserName("scott");
/* 3143 */     scott.setPassword("97c07a884bf272b5");
/*      */ 
/* 3145 */     scott.setActivitiSync(Short.valueOf((short)0));
/* 3146 */     this.commonDao.saveOrUpdate(scott);
/*      */ 
/* 3148 */     TSUser buyer = new TSUser();
/* 3149 */     buyer.setStatus(Short.valueOf((short)1));
/* 3150 */     buyer.setRealName("采购员");
/* 3151 */     buyer.setUserName("cgy");
/* 3152 */     buyer.setPassword("f2322ec2fb9f40d1");
/*      */ 
/* 3154 */     buyer.setActivitiSync(Short.valueOf((short)0));
/* 3155 */     this.commonDao.saveOrUpdate(buyer);
/* 3156 */     TSUser approver = new TSUser();
/* 3157 */     approver.setStatus(Short.valueOf((short)1));
/* 3158 */     approver.setRealName("采购审批员");
/* 3159 */     approver.setUserName("cgspy");
/* 3160 */     approver.setPassword("a324509dc1a3089a");
/*      */ 
/* 3162 */     approver.setActivitiSync(Short.valueOf((short)1));
/* 3163 */     this.commonDao.saveOrUpdate(approver);
/*      */   }
/*      */ 
/*      */   private void repairUserRole()
/*      */   {
/* 3172 */     TSRole admin = 
/* 3173 */       (TSRole)this.commonDao.findByProperty(TSRole.class, "roleCode", 
/* 3173 */       "admin").get(0);
/* 3174 */     TSRole manager = 
/* 3175 */       (TSRole)this.commonDao.findByProperty(TSRole.class, "roleCode", 
/* 3175 */       "manager").get(0);
/* 3176 */     List user = this.commonDao.loadAll(TSUser.class);
/* 3177 */     for (int i = 0; i < user.size(); i++) {
/* 3178 */       if (((TSUser)user.get(i)).getEmail() != null) {
/* 3179 */         TSRoleUser roleuser = new TSRoleUser();
/* 3180 */         roleuser.setTSUser((TSUser)user.get(i));
/* 3181 */         roleuser.setTSRole(manager);
/* 3182 */         this.commonDao.saveOrUpdate(roleuser);
/*      */       } else {
/* 3184 */         TSRoleUser roleuser = new TSRoleUser();
/* 3185 */         roleuser.setTSUser((TSUser)user.get(i));
/* 3186 */         roleuser.setTSRole(admin);
/* 3187 */         this.commonDao.saveOrUpdate(roleuser);
/*      */       }
/* 3189 */       if (((TSUser)user.get(i)).getSignatureFile() != null) {
/* 3190 */         TSRoleUser roleuser = new TSRoleUser();
/* 3191 */         roleuser.setTSUser((TSUser)user.get(i));
/* 3192 */         roleuser.setTSRole(admin);
/* 3193 */         this.commonDao.saveOrUpdate(roleuser);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void repairRoleFunction()
/*      */   {
/* 3204 */     TSRole admin = 
/* 3205 */       (TSRole)this.commonDao.findByProperty(TSRole.class, "roleCode", 
/* 3205 */       "admin").get(0);
/* 3206 */     TSRole manager = 
/* 3207 */       (TSRole)this.commonDao.findByProperty(TSRole.class, "roleCode", 
/* 3207 */       "manager").get(0);
/* 3208 */     List list = this.commonDao.loadAll(TSFunction.class);
/* 3209 */     for (int i = 0; i < list.size(); i++) {
/* 3210 */       TSRoleFunction adminroleFunction = new TSRoleFunction();
/* 3211 */       TSRoleFunction managerFunction = new TSRoleFunction();
/* 3212 */       adminroleFunction.setTSFunction((TSFunction)list.get(i));
/* 3213 */       managerFunction.setTSFunction((TSFunction)list.get(i));
/* 3214 */       adminroleFunction.setTSRole(admin);
/* 3215 */       managerFunction.setTSRole(manager);
/* 3216 */       if (((TSFunction)list.get(i)).getFunctionName().equals("Demo示例")) {
/* 3217 */         adminroleFunction.setOperation("add,szqm,");
/*      */       }
/* 3219 */       this.commonDao.saveOrUpdate(adminroleFunction);
/* 3220 */       this.commonDao.saveOrUpdate(managerFunction);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void repairOperation()
/*      */   {
/* 3229 */     TSIcon back = 
/* 3230 */       (TSIcon)this.commonDao.findByProperty(TSIcon.class, "iconName", "返回")
/* 3230 */       .get(0);
/* 3231 */     TSFunction function = 
/* 3232 */       (TSFunction)this.commonDao.findByProperty(TSFunction.class, 
/* 3232 */       "functionName", "Demo示例").get(0);
/*      */ 
/* 3234 */     TSOperation add = new TSOperation();
/* 3235 */     add.setOperationname("录入");
/* 3236 */     add.setOperationcode("add");
/* 3237 */     add.setTSIcon(back);
/* 3238 */     add.setTSFunction(function);
/* 3239 */     this.commonDao.saveOrUpdate(add);
/*      */ 
/* 3241 */     TSOperation edit = new TSOperation();
/* 3242 */     edit.setOperationname("编辑");
/* 3243 */     edit.setOperationcode("edit");
/* 3244 */     edit.setTSIcon(back);
/* 3245 */     edit.setTSFunction(function);
/* 3246 */     this.commonDao.saveOrUpdate(edit);
/*      */ 
/* 3248 */     TSOperation del = new TSOperation();
/* 3249 */     del.setOperationname("删除");
/* 3250 */     del.setOperationcode("del");
/* 3251 */     del.setTSIcon(back);
/* 3252 */     del.setTSFunction(function);
/* 3253 */     this.commonDao.saveOrUpdate(del);
/*      */ 
/* 3255 */     TSOperation szqm = new TSOperation();
/* 3256 */     szqm.setOperationname("审核");
/* 3257 */     szqm.setOperationcode("szqm");
/* 3258 */     szqm.setTSIcon(back);
/* 3259 */     szqm.setTSFunction(function);
/* 3260 */     this.commonDao.saveOrUpdate(szqm);
/*      */   }
/*      */ 
/*      */   private void repairTypeAndGroup()
/*      */   {
/* 3268 */     TSTypegroup icontype = new TSTypegroup();
/* 3269 */     icontype.setTypegroupname("图标类型");
/* 3270 */     icontype.setTypegroupcode("icontype");
/* 3271 */     this.commonDao.saveOrUpdate(icontype);
/*      */ 
/* 3273 */     TSTypegroup ordertype = new TSTypegroup();
/* 3274 */     ordertype.setTypegroupname("订单类型");
/* 3275 */     ordertype.setTypegroupcode("order");
/* 3276 */     this.commonDao.saveOrUpdate(ordertype);
/*      */ 
/* 3278 */     TSTypegroup custom = new TSTypegroup();
/* 3279 */     custom.setTypegroupname("客户类型");
/* 3280 */     custom.setTypegroupcode("custom");
/* 3281 */     this.commonDao.saveOrUpdate(custom);
/*      */ 
/* 3283 */     TSTypegroup servicetype = new TSTypegroup();
/* 3284 */     servicetype.setTypegroupname("服务项目类型");
/* 3285 */     servicetype.setTypegroupcode("service");
/* 3286 */     this.commonDao.saveOrUpdate(servicetype);
/*      */ 
/* 3288 */     TSTypegroup searchMode = new TSTypegroup();
/* 3289 */     searchMode.setTypegroupname("查询模式");
/* 3290 */     searchMode.setTypegroupcode("searchmode");
/* 3291 */     this.commonDao.saveOrUpdate(searchMode);
/*      */ 
/* 3293 */     TSTypegroup yesOrno = new TSTypegroup();
/* 3294 */     yesOrno.setTypegroupname("逻辑条件");
/* 3295 */     yesOrno.setTypegroupcode("yesorno");
/* 3296 */     this.commonDao.saveOrUpdate(yesOrno);
/*      */ 
/* 3298 */     TSTypegroup fieldtype = new TSTypegroup();
/* 3299 */     fieldtype.setTypegroupname("字段类型");
/* 3300 */     fieldtype.setTypegroupcode("fieldtype");
/* 3301 */     this.commonDao.saveOrUpdate(fieldtype);
/*      */ 
/* 3303 */     TSTypegroup datatable = new TSTypegroup();
/* 3304 */     datatable.setTypegroupname("数据表");
/* 3305 */     datatable.setTypegroupcode("database");
/* 3306 */     this.commonDao.saveOrUpdate(datatable);
/*      */ 
/* 3308 */     TSTypegroup filetype = new TSTypegroup();
/* 3309 */     filetype.setTypegroupname("文档分类");
/* 3310 */     filetype.setTypegroupcode("fieltype");
/* 3311 */     this.commonDao.saveOrUpdate(filetype);
/*      */ 
/* 3313 */     TSTypegroup sex = new TSTypegroup();
/* 3314 */     sex.setTypegroupname("性别类");
/* 3315 */     sex.setTypegroupcode("sex");
/* 3316 */     this.commonDao.saveOrUpdate(sex);
/*      */   }
/*      */ 
/*      */   private void repairType()
/*      */   {
/* 3324 */     TSTypegroup icontype = 
/* 3325 */       (TSTypegroup)this.commonDao.findByProperty(TSTypegroup.class, 
/* 3325 */       "typegroupname", "图标类型").get(0);
/* 3326 */     TSTypegroup ordertype = 
/* 3327 */       (TSTypegroup)this.commonDao.findByProperty(TSTypegroup.class, 
/* 3327 */       "typegroupname", "订单类型").get(0);
/* 3328 */     TSTypegroup custom = 
/* 3329 */       (TSTypegroup)this.commonDao.findByProperty(TSTypegroup.class, 
/* 3329 */       "typegroupname", "客户类型").get(0);
/* 3330 */     TSTypegroup servicetype = 
/* 3331 */       (TSTypegroup)this.commonDao.findByProperty(TSTypegroup.class, 
/* 3331 */       "typegroupname", "服务项目类型").get(0);
/* 3332 */     TSTypegroup datatable = 
/* 3333 */       (TSTypegroup)this.commonDao.findByProperty(TSTypegroup.class, 
/* 3333 */       "typegroupname", "数据表").get(0);
/* 3334 */     TSTypegroup filetype = 
/* 3335 */       (TSTypegroup)this.commonDao.findByProperty(TSTypegroup.class, 
/* 3335 */       "typegroupname", "文档分类").get(0);
/* 3336 */     TSTypegroup sex = 
/* 3337 */       (TSTypegroup)this.commonDao.findByProperty(TSTypegroup.class, 
/* 3337 */       "typegroupname", "性别类").get(0);
/* 3338 */     TSTypegroup searchmode = 
/* 3339 */       (TSTypegroup)this.commonDao.findByProperty(TSTypegroup.class, 
/* 3339 */       "typegroupname", "查询模式").get(0);
/* 3340 */     TSTypegroup yesorno = 
/* 3341 */       (TSTypegroup)this.commonDao.findByProperty(TSTypegroup.class, 
/* 3341 */       "typegroupname", "逻辑条件").get(0);
/* 3342 */     TSTypegroup fieldtype = 
/* 3343 */       (TSTypegroup)this.commonDao.findByProperty(TSTypegroup.class, 
/* 3343 */       "typegroupname", "字段类型").get(0);
/*      */ 
/* 3345 */     TSType menu = new TSType();
/* 3346 */     menu.setTypename("菜单图标");
/* 3347 */     menu.setTypecode("2");
/* 3348 */     menu.setTSTypegroup(icontype);
/* 3349 */     this.commonDao.saveOrUpdate(menu);
/*      */ 
/* 3351 */     TSType systemicon = new TSType();
/* 3352 */     systemicon.setTypename("系统图标");
/* 3353 */     systemicon.setTypecode("1");
/* 3354 */     systemicon.setTSTypegroup(icontype);
/* 3355 */     this.commonDao.saveOrUpdate(systemicon);
/*      */ 
/* 3357 */     TSType file = new TSType();
/* 3358 */     file.setTypename("附件");
/* 3359 */     file.setTypecode("files");
/* 3360 */     file.setTSTypegroup(filetype);
/* 3361 */     this.commonDao.saveOrUpdate(file);
/*      */ 
/* 3363 */     TSType goodorder = new TSType();
/* 3364 */     goodorder.setTypename("优质订单");
/* 3365 */     goodorder.setTypecode("1");
/* 3366 */     goodorder.setTSTypegroup(ordertype);
/* 3367 */     this.commonDao.saveOrUpdate(goodorder);
/*      */ 
/* 3369 */     TSType general = new TSType();
/* 3370 */     general.setTypename("普通订单");
/* 3371 */     general.setTypecode("2");
/* 3372 */     general.setTSTypegroup(ordertype);
/* 3373 */     this.commonDao.saveOrUpdate(general);
/*      */ 
/* 3375 */     TSType sign = new TSType();
/* 3376 */     sign.setTypename("签约客户");
/* 3377 */     sign.setTypecode("1");
/* 3378 */     sign.setTSTypegroup(custom);
/* 3379 */     this.commonDao.saveOrUpdate(sign);
/*      */ 
/* 3381 */     TSType commoncustom = new TSType();
/* 3382 */     commoncustom.setTypename("普通客户");
/* 3383 */     commoncustom.setTypecode("2");
/* 3384 */     commoncustom.setTSTypegroup(custom);
/* 3385 */     this.commonDao.saveOrUpdate(commoncustom);
/*      */ 
/* 3387 */     TSType vipservice = new TSType();
/* 3388 */     vipservice.setTypename("特殊服务");
/* 3389 */     vipservice.setTypecode("1");
/* 3390 */     vipservice.setTSTypegroup(servicetype);
/* 3391 */     this.commonDao.saveOrUpdate(vipservice);
/*      */ 
/* 3393 */     TSType commonservice = new TSType();
/* 3394 */     commonservice.setTypename("普通服务");
/* 3395 */     commonservice.setTypecode("2");
/* 3396 */     commonservice.setTSTypegroup(servicetype);
/* 3397 */     this.commonDao.saveOrUpdate(commonservice);
/*      */ 
/* 3404 */     TSType single = new TSType();
/* 3405 */     single.setTypename("单条件查询");
/* 3406 */     single.setTypecode("single");
/* 3407 */     single.setTSTypegroup(searchmode);
/* 3408 */     this.commonDao.saveOrUpdate(single);
/*      */ 
/* 3410 */     TSType group = new TSType();
/* 3411 */     group.setTypename("范围查询");
/* 3412 */     group.setTypecode("group");
/* 3413 */     group.setTSTypegroup(searchmode);
/* 3414 */     this.commonDao.saveOrUpdate(group);
/*      */ 
/* 3416 */     TSType yes = new TSType();
/* 3417 */     yes.setTypename("是");
/* 3418 */     yes.setTypecode("Y");
/* 3419 */     yes.setTSTypegroup(yesorno);
/* 3420 */     this.commonDao.saveOrUpdate(yes);
/*      */ 
/* 3422 */     TSType no = new TSType();
/* 3423 */     no.setTypename("否");
/* 3424 */     no.setTypecode("N");
/* 3425 */     no.setTSTypegroup(yesorno);
/* 3426 */     this.commonDao.saveOrUpdate(no);
/*      */ 
/* 3428 */     TSType type_integer = new TSType();
/* 3429 */     type_integer.setTypename("Integer");
/* 3430 */     type_integer.setTypecode("Integer");
/* 3431 */     type_integer.setTSTypegroup(fieldtype);
/* 3432 */     this.commonDao.saveOrUpdate(type_integer);
/*      */ 
/* 3434 */     TSType type_date = new TSType();
/* 3435 */     type_date.setTypename("Date");
/* 3436 */     type_date.setTypecode("Date");
/* 3437 */     type_date.setTSTypegroup(fieldtype);
/* 3438 */     this.commonDao.saveOrUpdate(type_date);
/*      */ 
/* 3440 */     TSType type_string = new TSType();
/* 3441 */     type_string.setTypename("String");
/* 3442 */     type_string.setTypecode("String");
/* 3443 */     type_string.setTSTypegroup(fieldtype);
/* 3444 */     this.commonDao.saveOrUpdate(type_string);
/*      */ 
/* 3446 */     TSType type_long = new TSType();
/* 3447 */     type_long.setTypename("Long");
/* 3448 */     type_long.setTypecode("Long");
/* 3449 */     type_long.setTSTypegroup(fieldtype);
/* 3450 */     this.commonDao.saveOrUpdate(type_long);
/*      */ 
/* 3452 */     TSType workflow = new TSType();
/* 3453 */     workflow.setTypename("工作流引擎表");
/* 3454 */     workflow.setTypecode("act");
/* 3455 */     workflow.setTSTypegroup(datatable);
/* 3456 */     this.commonDao.saveOrUpdate(workflow);
/*      */ 
/* 3458 */     TSType systable = new TSType();
/* 3459 */     systable.setTypename("系统基础表");
/* 3460 */     systable.setTypecode("t_s");
/* 3461 */     systable.setTSTypegroup(datatable);
/* 3462 */     this.commonDao.saveOrUpdate(systable);
/*      */ 
/* 3464 */     TSType business = new TSType();
/* 3465 */     business.setTypename("业务表");
/* 3466 */     business.setTypecode("t_b");
/* 3467 */     business.setTSTypegroup(datatable);
/* 3468 */     this.commonDao.saveOrUpdate(business);
/*      */ 
/* 3470 */     TSType customwork = new TSType();
/* 3471 */     customwork.setTypename("自定义引擎表");
/* 3472 */     customwork.setTypecode("t_p");
/* 3473 */     customwork.setTSTypegroup(datatable);
/* 3474 */     this.commonDao.saveOrUpdate(customwork);
/*      */ 
/* 3476 */     TSType news = new TSType();
/* 3477 */     news.setTypename("新闻");
/* 3478 */     news.setTypecode("news");
/* 3479 */     news.setTSTypegroup(filetype);
/* 3480 */     this.commonDao.saveOrUpdate(news);
/*      */ 
/* 3482 */     TSType man = new TSType();
/* 3483 */     man.setTypename("男性");
/* 3484 */     man.setTypecode("0");
/* 3485 */     man.setTSTypegroup(sex);
/* 3486 */     this.commonDao.saveOrUpdate(man);
/*      */ 
/* 3488 */     TSType woman = new TSType();
/* 3489 */     woman.setTypename("女性");
/* 3490 */     woman.setTypecode("1");
/* 3491 */     woman.setTSTypegroup(sex);
/* 3492 */     this.commonDao.saveOrUpdate(woman);
/*      */   }
/*      */ 
/*      */   private void repairRole()
/*      */   {
/* 3500 */     TSRole admin = new TSRole();
/* 3501 */     admin.setRoleName("管理员");
/* 3502 */     admin.setRoleCode("admin");
/* 3503 */     this.commonDao.saveOrUpdate(admin);
/*      */ 
/* 3505 */     TSRole manager = new TSRole();
/* 3506 */     manager.setRoleName("普通用户");
/* 3507 */     manager.setRoleCode("manager");
/* 3508 */     this.commonDao.saveOrUpdate(manager);
/*      */   }
/*      */ 
/*      */   private void repairAttachment()
/*      */   {
/* 3518 */     TSAttachment jro = new TSAttachment();
/* 3519 */     jro.setAttachmenttitle("JR079839867R90000001000");
/* 3520 */     jro.setRealpath("JR079839867R90000001000");
/* 3521 */     jro.setSwfpath("upload/files/20130719201109hDr31jP1.swf");
/* 3522 */     jro.setExtend("doc");
/* 3523 */     this.commonDao.saveOrUpdate(jro);
/*      */ 
/* 3525 */     TSAttachment treaty = new TSAttachment();
/* 3526 */     treaty.setAttachmenttitle("JEECG平台协议");
/* 3527 */     treaty.setRealpath("JEECG平台协议");
/* 3528 */     treaty.setSwfpath("upload/files/20130719201156sYHjSFJj.swf");
/* 3529 */     treaty.setExtend("docx");
/* 3530 */     this.commonDao.saveOrUpdate(treaty);
/*      */ 
/* 3532 */     TSAttachment analyse = new TSAttachment();
/* 3533 */     analyse.setAttachmenttitle("分析JEECG与其他的开源项目的不足和优势");
/* 3534 */     analyse.setRealpath("分析JEECG与其他的开源项目的不足和优势");
/* 3535 */     analyse.setSwfpath("upload/files/20130719201727ZLEX1OSf.swf");
/* 3536 */     analyse.setExtend("docx");
/* 3537 */     this.commonDao.saveOrUpdate(analyse);
/*      */ 
/* 3539 */     TSAttachment DMS = new TSAttachment();
/* 3540 */     DMS.setAttachmenttitle("DMS-T3第三方租赁业务接口开发说明");
/* 3541 */     DMS.setRealpath("DMS-T3第三方租赁业务接口开发说明");
/* 3542 */     DMS.setSwfpath("upload/files/20130719201841LzcgqUek.swf");
/* 3543 */     DMS.setExtend("docx");
/* 3544 */     this.commonDao.saveOrUpdate(DMS);
/*      */ 
/* 3546 */     TSAttachment sap = new TSAttachment();
/* 3547 */     sap.setAttachmenttitle("SAP-需求说明书-金融服务公司-第三方租赁业务需求V1.7-研发");
/* 3548 */     sap.setRealpath("SAP-需求说明书-金融服务公司-第三方租赁业务需求V1.7-研发");
/* 3549 */     sap.setSwfpath("upload/files/20130719201925mkCrU47P.swf");
/* 3550 */     sap.setExtend("doc");
/* 3551 */     this.commonDao.saveOrUpdate(sap);
/*      */ 
/* 3553 */     TSAttachment standard = new TSAttachment();
/* 3554 */     standard.setAttachmenttitle("JEECG团队开发规范");
/* 3555 */     standard.setRealpath("JEECG团队开发规范");
/* 3556 */     standard.setSwfpath("upload/files/20130724103633fvOTwNSV.swf");
/* 3557 */     standard.setExtend("txt");
/* 3558 */     this.commonDao.saveOrUpdate(standard);
/*      */ 
/* 3560 */     TSAttachment temple = new TSAttachment();
/* 3561 */     temple.setAttachmenttitle("第一模板");
/* 3562 */     temple.setRealpath("第一模板");
/* 3563 */     temple.setSwfpath("upload/files/20130724104603pHDw4QUT.swf");
/* 3564 */     temple.setExtend("doc");
/* 3565 */     this.commonDao.saveOrUpdate(temple);
/*      */ 
/* 3567 */     TSAttachment githubhelp = new TSAttachment();
/* 3568 */     githubhelp.setAttachmenttitle("github入门使用教程");
/* 3569 */     githubhelp.setRealpath("github入门使用教程");
/* 3570 */     githubhelp.setSwfpath("upload/files/20130704200345EakUH3WB.swf");
/* 3571 */     githubhelp.setExtend("doc");
/* 3572 */     this.commonDao.saveOrUpdate(githubhelp);
/*      */ 
/* 3574 */     TSAttachment githelp = new TSAttachment();
/* 3575 */     githelp.setAttachmenttitle("github入门使用教程");
/* 3576 */     githelp.setRealpath("github入门使用教程");
/* 3577 */     githelp.setSwfpath("upload/files/20130704200651IE8wPdZ4.swf");
/* 3578 */     githelp.setExtend("doc");
/* 3579 */     this.commonDao.saveOrUpdate(githelp);
/*      */ 
/* 3581 */     TSAttachment settable = new TSAttachment();
/* 3582 */     settable.setAttachmenttitle("（张代浩）-金融服务公司机构岗位职责与任职资格设置表(根据模板填写）");
/* 3583 */     settable.setRealpath("（张代浩）-金融服务公司机构岗位职责与任职资格设置表(根据模板填写）");
/* 3584 */     settable.setSwfpath("upload/files/20130704201022KhdRW1Gd.swf");
/* 3585 */     settable.setExtend("xlsx");
/* 3586 */     this.commonDao.saveOrUpdate(settable);
/*      */ 
/* 3588 */     TSAttachment eim = new TSAttachment();
/* 3589 */     eim.setAttachmenttitle("EIM201_CN");
/* 3590 */     eim.setRealpath("EIM201_CN");
/* 3591 */     eim.setSwfpath("upload/files/20130704201046JVAkvvOt.swf");
/* 3592 */     eim.setExtend("pdf");
/* 3593 */     this.commonDao.saveOrUpdate(eim);
/*      */ 
/* 3595 */     TSAttachment github = new TSAttachment();
/* 3596 */     github.setAttachmenttitle("github入门使用教程");
/* 3597 */     github.setRealpath("github入门使用教程");
/* 3598 */     github.setSwfpath("upload/files/20130704201116Z8NhEK57.swf");
/* 3599 */     github.setExtend("doc");
/* 3600 */     this.commonDao.saveOrUpdate(github);
/*      */ 
/* 3602 */     TSAttachment taghelp = new TSAttachment();
/* 3603 */     taghelp.setAttachmenttitle("JEECGUI标签库帮助文档v3.2");
/* 3604 */     taghelp.setRealpath("JEECGUI标签库帮助文档v3.2");
/* 3605 */     taghelp.setSwfpath("upload/files/20130704201125DQg8hi2x.swf");
/* 3606 */     taghelp.setExtend("pdf");
/* 3607 */     this.commonDao.saveOrUpdate(taghelp);
/*      */   }
/*      */ 
/*      */   private void repaireIcon()
/*      */   {
/* 3615 */     LogUtil.info("修复图标中");
/*      */ 
/* 3617 */     TSIcon defaultIcon = new TSIcon();
/* 3618 */     defaultIcon.setIconName("默认图");
/* 3619 */     defaultIcon.setIconType(Short.valueOf((short)1));
/* 3620 */     defaultIcon.setIconPath("plug-in/accordion/images/default.png");
/* 3621 */     defaultIcon.setIconClas("default");
/* 3622 */     defaultIcon.setExtend("png");
/* 3623 */     this.commonDao.saveOrUpdate(defaultIcon);
/*      */ 
/* 3625 */     TSIcon back = new TSIcon();
/* 3626 */     back.setIconName("返回");
/* 3627 */     back.setIconType(Short.valueOf((short)1));
/* 3628 */     back.setIconPath("plug-in/accordion/images/back.png");
/* 3629 */     back.setIconClas("back");
/* 3630 */     back.setExtend("png");
/* 3631 */     this.commonDao.saveOrUpdate(back);
/*      */ 
/* 3633 */     TSIcon pie = new TSIcon();
/*      */ 
/* 3635 */     pie.setIconName("饼图");
/* 3636 */     pie.setIconType(Short.valueOf((short)1));
/* 3637 */     pie.setIconPath("plug-in/accordion/images/pie.png");
/* 3638 */     pie.setIconClas("pie");
/* 3639 */     pie.setExtend("png");
/* 3640 */     this.commonDao.saveOrUpdate(pie);
/*      */ 
/* 3642 */     TSIcon pictures = new TSIcon();
/* 3643 */     pictures.setIconName("图片");
/* 3644 */     pictures.setIconType(Short.valueOf((short)1));
/* 3645 */     pictures.setIconPath("plug-in/accordion/images/pictures.png");
/* 3646 */     pictures.setIconClas("pictures");
/* 3647 */     pictures.setExtend("png");
/* 3648 */     this.commonDao.saveOrUpdate(pictures);
/*      */ 
/* 3650 */     TSIcon pencil = new TSIcon();
/* 3651 */     pencil.setIconName("笔");
/* 3652 */     pencil.setIconType(Short.valueOf((short)1));
/* 3653 */     pencil.setIconPath("plug-in/accordion/images/pencil.png");
/* 3654 */     pencil.setIconClas("pencil");
/* 3655 */     pencil.setExtend("png");
/* 3656 */     this.commonDao.saveOrUpdate(pencil);
/*      */ 
/* 3658 */     TSIcon map = new TSIcon();
/* 3659 */     map.setIconName("地图");
/* 3660 */     map.setIconType(Short.valueOf((short)1));
/* 3661 */     map.setIconPath("plug-in/accordion/images/map.png");
/* 3662 */     map.setIconClas("map");
/* 3663 */     map.setExtend("png");
/* 3664 */     this.commonDao.saveOrUpdate(map);
/*      */ 
/* 3666 */     TSIcon group_add = new TSIcon();
/* 3667 */     group_add.setIconName("组");
/* 3668 */     group_add.setIconType(Short.valueOf((short)1));
/* 3669 */     group_add.setIconPath("plug-in/accordion/images/group_add.png");
/* 3670 */     group_add.setIconClas("group_add");
/* 3671 */     group_add.setExtend("png");
/* 3672 */     this.commonDao.saveOrUpdate(group_add);
/*      */ 
/* 3674 */     TSIcon calculator = new TSIcon();
/* 3675 */     calculator.setIconName("计算器");
/* 3676 */     calculator.setIconType(Short.valueOf((short)1));
/* 3677 */     calculator.setIconPath("plug-in/accordion/images/calculator.png");
/* 3678 */     calculator.setIconClas("calculator");
/* 3679 */     calculator.setExtend("png");
/* 3680 */     this.commonDao.saveOrUpdate(calculator);
/*      */ 
/* 3682 */     TSIcon folder = new TSIcon();
/* 3683 */     folder.setIconName("文件夹");
/* 3684 */     folder.setIconType(Short.valueOf((short)1));
/* 3685 */     folder.setIconPath("plug-in/accordion/images/folder.png");
/* 3686 */     folder.setIconClas("folder");
/* 3687 */     folder.setExtend("png");
/* 3688 */     this.commonDao.saveOrUpdate(folder);
/*      */   }
/*      */ 
/*      */   private TSIcon repairInconForDesk(String iconName, String iconLabelName)
/*      */   {
/* 3698 */     String iconPath = "plug-in/sliding/icon/" + iconName + ".png";
/* 3699 */     TSIcon deskIncon = new TSIcon();
/* 3700 */     deskIncon.setIconName(iconLabelName);
/* 3701 */     deskIncon.setIconType(Short.valueOf((short)3));
/* 3702 */     deskIncon.setIconPath(iconPath);
/* 3703 */     deskIncon.setIconClas("deskIcon");
/* 3704 */     deskIncon.setExtend("png");
/* 3705 */     this.commonDao.saveOrUpdate(deskIncon);
/*      */ 
/* 3707 */     return deskIncon;
/*      */   }
/*      */ 
/*      */   private void repairMenu()
/*      */   {
/* 3716 */     TSIcon defaultIcon = 
/* 3717 */       (TSIcon)this.commonDao.findByProperty(TSIcon.class, "iconName", "默认图")
/* 3717 */       .get(0);
/* 3718 */     TSIcon group_add = 
/* 3719 */       (TSIcon)this.commonDao.findByProperty(TSIcon.class, "iconName", 
/* 3719 */       "组").get(0);
/* 3720 */     TSIcon pie = 
/* 3721 */       (TSIcon)this.commonDao.findByProperty(TSIcon.class, "iconName", "饼图")
/* 3721 */       .get(0);
/* 3722 */     TSIcon folder = 
/* 3723 */       (TSIcon)this.commonDao.findByProperty(TSIcon.class, "iconName", 
/* 3723 */       "文件夹").get(0);
/* 3724 */     LogUtil.info(defaultIcon.getIconPath());
/* 3725 */     TSFunction autoinput = new TSFunction();
/* 3726 */     autoinput.setFunctionName("Online 开发");
/* 3727 */     autoinput.setFunctionUrl("");
/* 3728 */     autoinput.setFunctionLevel(Short.valueOf((short)0));
/* 3729 */     autoinput.setFunctionOrder("1");
/* 3730 */     autoinput.setTSIcon(folder);
/* 3731 */     this.commonDao.saveOrUpdate(autoinput);
/*      */ 
/* 3733 */     TSFunction sys = new TSFunction();
/* 3734 */     sys.setFunctionName("系统管理");
/* 3735 */     sys.setFunctionUrl("");
/* 3736 */     sys.setFunctionLevel(Short.valueOf((short)0));
/* 3737 */     sys.setFunctionOrder("5");
/* 3738 */     sys.setTSIcon(group_add);
/* 3739 */     this.commonDao.saveOrUpdate(sys);
/*      */ 
/* 3741 */     TSFunction state = new TSFunction();
/* 3742 */     state.setFunctionName("统计查询");
/* 3743 */     state.setFunctionUrl("");
/* 3744 */     state.setFunctionLevel(Short.valueOf((short)0));
/* 3745 */     state.setFunctionOrder("3");
/* 3746 */     state.setTSIcon(folder);
/* 3747 */     this.commonDao.saveOrUpdate(state);
/*      */ 
/* 3749 */     TSFunction commondemo = new TSFunction();
/* 3750 */     commondemo.setFunctionName("常用示例");
/* 3751 */     commondemo.setFunctionUrl("");
/* 3752 */     commondemo.setFunctionLevel(Short.valueOf((short)0));
/* 3753 */     commondemo.setFunctionOrder("8");
/* 3754 */     commondemo.setTSIcon(defaultIcon);
/* 3755 */     this.commonDao.saveOrUpdate(commondemo);
/*      */ 
/* 3757 */     TSFunction syscontrol = new TSFunction();
/* 3758 */     syscontrol.setFunctionName("系统监控");
/* 3759 */     syscontrol.setFunctionUrl("");
/* 3760 */     syscontrol.setFunctionLevel(Short.valueOf((short)0));
/* 3761 */     syscontrol.setFunctionOrder("11");
/* 3762 */     syscontrol.setTSIcon(defaultIcon);
/* 3763 */     this.commonDao.saveOrUpdate(syscontrol);
/* 3764 */     TSFunction user = new TSFunction();
/* 3765 */     user.setFunctionName("用户管理");
/* 3766 */     user.setFunctionUrl("userController.do?user");
/* 3767 */     user.setFunctionLevel(Short.valueOf((short)1));
/* 3768 */     user.setFunctionOrder("5");
/* 3769 */     user.setTSFunction(sys);
/* 3770 */     user.setTSIcon(defaultIcon);
/* 3771 */     user.setTSIconDesk(repairInconForDesk("Finder", "用户管理"));
/* 3772 */     this.commonDao.saveOrUpdate(user);
/*      */ 
/* 3774 */     TSFunction role = new TSFunction();
/* 3775 */     role.setFunctionName("角色管理");
/* 3776 */     role.setFunctionUrl("roleController.do?role");
/* 3777 */     role.setFunctionLevel(Short.valueOf((short)1));
/* 3778 */     role.setFunctionOrder("6");
/* 3779 */     role.setTSFunction(sys);
/* 3780 */     role.setTSIcon(defaultIcon);
/* 3781 */     role.setTSIconDesk(repairInconForDesk("friendgroup", "角色管理"));
/* 3782 */     this.commonDao.saveOrUpdate(role);
/*      */ 
/* 3784 */     TSFunction menu = new TSFunction();
/* 3785 */     menu.setFunctionName("菜单管理");
/* 3786 */     menu.setFunctionUrl("functionController.do?function");
/* 3787 */     menu.setFunctionLevel(Short.valueOf((short)1));
/* 3788 */     menu.setFunctionOrder("7");
/* 3789 */     menu.setTSFunction(sys);
/* 3790 */     menu.setTSIcon(defaultIcon);
/* 3791 */     menu.setTSIconDesk(repairInconForDesk("kaikai", "菜单管理"));
/* 3792 */     this.commonDao.saveOrUpdate(menu);
/*      */ 
/* 3794 */     TSFunction typegroup = new TSFunction();
/* 3795 */     typegroup.setFunctionName("数据字典");
/* 3796 */     typegroup.setFunctionUrl("systemController.do?typeGroupList");
/* 3797 */     typegroup.setFunctionLevel(Short.valueOf((short)1));
/* 3798 */     typegroup.setFunctionOrder("6");
/* 3799 */     typegroup.setTSFunction(sys);
/* 3800 */     typegroup.setTSIcon(defaultIcon);
/* 3801 */     typegroup.setTSIconDesk(repairInconForDesk("friendnear", "数据字典"));
/* 3802 */     this.commonDao.saveOrUpdate(typegroup);
/*      */ 
/* 3804 */     TSFunction icon = new TSFunction();
/* 3805 */     icon.setFunctionName("图标管理");
/* 3806 */     icon.setFunctionUrl("iconController.do?icon");
/* 3807 */     icon.setFunctionLevel(Short.valueOf((short)1));
/* 3808 */     icon.setFunctionOrder("18");
/* 3809 */     icon.setTSFunction(sys);
/* 3810 */     icon.setTSIcon(defaultIcon);
/* 3811 */     icon.setTSIconDesk(repairInconForDesk("kxjy", "图标管理"));
/* 3812 */     this.commonDao.saveOrUpdate(icon);
/*      */ 
/* 3814 */     TSFunction depart = new TSFunction();
/* 3815 */     depart.setFunctionName("部门管理");
/* 3816 */     depart.setFunctionUrl("departController.do?depart");
/* 3817 */     depart.setFunctionLevel(Short.valueOf((short)1));
/* 3818 */     depart.setFunctionOrder("22");
/* 3819 */     depart.setTSFunction(sys);
/* 3820 */     depart.setTSIcon(defaultIcon);
/* 3821 */     this.commonDao.saveOrUpdate(depart);
/*      */ 
/* 3823 */     TSFunction territory = new TSFunction();
/* 3824 */     territory.setFunctionName("地域管理");
/* 3825 */     territory.setFunctionUrl("territoryController.do?territory");
/* 3826 */     territory.setFunctionLevel(Short.valueOf((short)1));
/* 3827 */     territory.setFunctionOrder("22");
/* 3828 */     territory.setTSFunction(sys);
/* 3829 */     territory.setTSIcon(pie);
/* 3830 */     this.commonDao.saveOrUpdate(territory);
/* 3831 */     TSFunction useranalyse = new TSFunction();
/* 3832 */     useranalyse.setFunctionName("用户分析");
/* 3833 */     useranalyse.setFunctionUrl("logController.do?statisticTabs&isIframe");
/* 3834 */     useranalyse.setFunctionLevel(Short.valueOf((short)1));
/* 3835 */     useranalyse.setFunctionOrder("17");
/* 3836 */     useranalyse.setTSFunction(state);
/* 3837 */     useranalyse.setTSIcon(pie);
/* 3838 */     useranalyse.setTSIconDesk(repairInconForDesk("User", "用户分析"));
/* 3839 */     this.commonDao.saveOrUpdate(useranalyse);
/* 3840 */     TSFunction formconfig = new TSFunction();
/* 3841 */     formconfig.setFunctionName("表单配置");
/* 3842 */     formconfig.setFunctionUrl("cgFormHeadController.do?cgFormHeadList");
/* 3843 */     formconfig.setFunctionLevel(Short.valueOf((short)1));
/* 3844 */     formconfig.setFunctionOrder("1");
/* 3845 */     formconfig.setTSFunction(autoinput);
/* 3846 */     formconfig.setTSIcon(defaultIcon);
/* 3847 */     formconfig.setTSIconDesk(repairInconForDesk("Applications Folder", "表单配置"));
/* 3848 */     this.commonDao.saveOrUpdate(formconfig);
/* 3849 */     TSFunction formconfig1 = new TSFunction();
/* 3850 */     formconfig1.setFunctionName("动态报表配置");
/* 3851 */     formconfig1.setFunctionUrl("cgreportConfigHeadController.do?cgreportConfigHead");
/* 3852 */     formconfig1.setFunctionLevel(Short.valueOf((short)1));
/* 3853 */     formconfig1.setFunctionOrder("2");
/* 3854 */     formconfig1.setTSFunction(autoinput);
/* 3855 */     formconfig1.setTSIcon(defaultIcon);
/* 3856 */     this.commonDao.saveOrUpdate(formconfig1);
/* 3857 */     TSFunction druid = new TSFunction();
/* 3858 */     druid.setFunctionName("数据监控");
/* 3859 */     druid.setFunctionUrl("dataSourceController.do?goDruid&isIframe");
/* 3860 */     druid.setFunctionLevel(Short.valueOf((short)1));
/* 3861 */     druid.setFunctionOrder("11");
/* 3862 */     druid.setTSFunction(syscontrol);
/* 3863 */     druid.setTSIcon(defaultIcon);
/* 3864 */     druid.setTSIconDesk(repairInconForDesk("Super Disk", "数据监控"));
/* 3865 */     this.commonDao.saveOrUpdate(druid);
/*      */ 
/* 3867 */     TSFunction log = new TSFunction();
/* 3868 */     log.setFunctionName("系统日志");
/* 3869 */     log.setFunctionUrl("logController.do?log");
/* 3870 */     log.setFunctionLevel(Short.valueOf((short)1));
/* 3871 */     log.setFunctionOrder("21");
/* 3872 */     log.setTSFunction(syscontrol);
/* 3873 */     log.setTSIcon(defaultIcon);
/* 3874 */     log.setTSIconDesk(repairInconForDesk("fastsearch", "系统日志"));
/* 3875 */     this.commonDao.saveOrUpdate(log);
/*      */ 
/* 3877 */     TSFunction timeTask = new TSFunction();
/* 3878 */     timeTask.setFunctionName("定时任务");
/* 3879 */     timeTask.setFunctionUrl("timeTaskController.do?timeTask");
/* 3880 */     timeTask.setFunctionLevel(Short.valueOf((short)1));
/* 3881 */     timeTask.setFunctionOrder("21");
/* 3882 */     timeTask.setTSFunction(syscontrol);
/* 3883 */     timeTask.setTSIcon(defaultIcon);
/* 3884 */     timeTask.setTSIconDesk(repairInconForDesk("Utilities", "定时任务"));
/* 3885 */     this.commonDao.saveOrUpdate(timeTask);
/* 3886 */     TSFunction formcheck = new TSFunction();
/* 3887 */     formcheck.setFunctionName("表单验证");
/* 3888 */     formcheck.setFunctionUrl("demoController.do?formTabs");
/* 3889 */     formcheck.setFunctionLevel(Short.valueOf((short)1));
/* 3890 */     formcheck.setFunctionOrder("1");
/* 3891 */     formcheck.setTSFunction(commondemo);
/* 3892 */     formcheck.setTSIcon(defaultIcon);
/* 3893 */     formcheck.setTSIconDesk(repairInconForDesk("qidianzhongwen", "表单验证"));
/* 3894 */     this.commonDao.saveOrUpdate(formcheck);
/*      */ 
/* 3896 */     TSFunction demo = new TSFunction();
/* 3897 */     demo.setFunctionName("Demo示例");
/* 3898 */     demo.setFunctionUrl("jeecgDemoController.do?jeecgDemo");
/* 3899 */     demo.setFunctionLevel(Short.valueOf((short)1));
/* 3900 */     demo.setFunctionOrder("2");
/* 3901 */     demo.setTSFunction(commondemo);
/* 3902 */     demo.setTSIcon(defaultIcon);
/* 3903 */     this.commonDao.saveOrUpdate(demo);
/*      */ 
/* 3905 */     TSFunction minidao = new TSFunction();
/* 3906 */     minidao.setFunctionName("Minidao例子");
/* 3907 */     minidao.setFunctionUrl("jeecgMinidaoController.do?jeecgMinidao");
/* 3908 */     minidao.setFunctionLevel(Short.valueOf((short)1));
/* 3909 */     minidao.setFunctionOrder("2");
/* 3910 */     minidao.setTSFunction(commondemo);
/* 3911 */     minidao.setTSIcon(defaultIcon);
/* 3912 */     this.commonDao.saveOrUpdate(minidao);
/*      */ 
/* 3914 */     TSFunction onetable = new TSFunction();
/* 3915 */     onetable.setFunctionName("单表模型");
/* 3916 */     onetable.setFunctionUrl("jeecgNoteController.do?jeecgNote");
/* 3917 */     onetable.setFunctionLevel(Short.valueOf((short)1));
/* 3918 */     onetable.setFunctionOrder("3");
/* 3919 */     onetable.setTSFunction(commondemo);
/* 3920 */     onetable.setTSIcon(defaultIcon);
/* 3921 */     this.commonDao.saveOrUpdate(onetable);
/*      */ 
/* 3923 */     TSFunction onetoMany = new TSFunction();
/* 3924 */     onetoMany.setFunctionName("一对多模型");
/* 3925 */     onetoMany.setFunctionUrl("jeecgOrderMainController.do?jeecgOrderMain");
/* 3926 */     onetoMany.setFunctionLevel(Short.valueOf((short)1));
/* 3927 */     onetoMany.setFunctionOrder("4");
/* 3928 */     onetoMany.setTSFunction(commondemo);
/* 3929 */     onetoMany.setTSIcon(defaultIcon);
/* 3930 */     this.commonDao.saveOrUpdate(onetoMany);
/*      */ 
/* 3932 */     TSFunction excel = new TSFunction();
/* 3933 */     excel.setFunctionName("Excel导入导出");
/* 3934 */     excel.setFunctionUrl("courseController.do?course");
/* 3935 */     excel.setFunctionLevel(Short.valueOf((short)1));
/* 3936 */     excel.setFunctionOrder("5");
/* 3937 */     excel.setTSFunction(commondemo);
/* 3938 */     excel.setTSIcon(defaultIcon);
/* 3939 */     this.commonDao.saveOrUpdate(excel);
/*      */ 
/* 3941 */     TSFunction uploadownload = new TSFunction();
/* 3942 */     uploadownload.setFunctionName("上传下载");
/* 3943 */     uploadownload
/* 3944 */       .setFunctionUrl("commonController.do?listTurn&turn=system/document/filesList");
/* 3945 */     uploadownload.setFunctionLevel(Short.valueOf((short)1));
/* 3946 */     uploadownload.setFunctionOrder("6");
/* 3947 */     uploadownload.setTSFunction(commondemo);
/* 3948 */     uploadownload.setTSIcon(defaultIcon);
/* 3949 */     this.commonDao.saveOrUpdate(uploadownload);
/*      */ 
/* 3951 */     TSFunction jqueryFileUpload = new TSFunction();
/* 3952 */     jqueryFileUpload.setFunctionName("JqueryFileUpload示例");
/* 3953 */     jqueryFileUpload.setFunctionUrl("fileUploadController.do?fileUploadSample&isIframe");
/* 3954 */     jqueryFileUpload.setFunctionLevel(Short.valueOf((short)1));
/* 3955 */     jqueryFileUpload.setFunctionOrder("6");
/* 3956 */     jqueryFileUpload.setTSFunction(commondemo);
/* 3957 */     jqueryFileUpload.setTSIcon(defaultIcon);
/* 3958 */     this.commonDao.saveOrUpdate(jqueryFileUpload);
/*      */ 
/* 3960 */     TSFunction nopaging = new TSFunction();
/* 3961 */     nopaging.setFunctionName("无分页列表");
/* 3962 */     nopaging.setFunctionUrl("userNoPageController.do?user");
/* 3963 */     nopaging.setFunctionLevel(Short.valueOf((short)1));
/* 3964 */     nopaging.setFunctionOrder("7");
/* 3965 */     nopaging.setTSIcon(defaultIcon);
/* 3966 */     nopaging.setTSFunction(commondemo);
/* 3967 */     this.commonDao.saveOrUpdate(nopaging);
/*      */ 
/* 3969 */     TSFunction jdbcdemo = new TSFunction();
/* 3970 */     jdbcdemo.setFunctionName("jdbc示例");
/* 3971 */     jdbcdemo.setFunctionUrl("jeecgJdbcController.do?jeecgJdbc");
/* 3972 */     jdbcdemo.setFunctionLevel(Short.valueOf((short)1));
/* 3973 */     jdbcdemo.setFunctionOrder("8");
/* 3974 */     jdbcdemo.setTSFunction(commondemo);
/* 3975 */     jdbcdemo.setTSIcon(defaultIcon);
/* 3976 */     this.commonDao.saveOrUpdate(jdbcdemo);
/*      */ 
/* 3978 */     TSFunction sqlsep = new TSFunction();
/* 3979 */     sqlsep.setFunctionName("SQL分离");
/* 3980 */     sqlsep.setFunctionUrl("jeecgJdbcController.do?dictParameter");
/* 3981 */     sqlsep.setFunctionLevel(Short.valueOf((short)1));
/* 3982 */     sqlsep.setTSIcon(defaultIcon);
/* 3983 */     sqlsep.setFunctionOrder("9");
/* 3984 */     sqlsep.setTSFunction(commondemo);
/* 3985 */     this.commonDao.saveOrUpdate(sqlsep);
/*      */ 
/* 3987 */     TSFunction dicttag = new TSFunction();
/* 3988 */     dicttag.setFunctionName("字典标签");
/* 3989 */     dicttag.setFunctionUrl("demoController.do?dictSelect");
/* 3990 */     dicttag.setFunctionLevel(Short.valueOf((short)1));
/* 3991 */     dicttag.setFunctionOrder("10");
/* 3992 */     dicttag.setTSFunction(commondemo);
/* 3993 */     dicttag.setTSIcon(defaultIcon);
/* 3994 */     this.commonDao.saveOrUpdate(dicttag);
/*      */ 
/* 3996 */     TSFunction demomaintain = new TSFunction();
/* 3997 */     demomaintain.setFunctionName("表单弹出风格");
/* 3998 */     demomaintain.setFunctionUrl("demoController.do?demoList");
/* 3999 */     demomaintain.setFunctionLevel(Short.valueOf((short)1));
/* 4000 */     demomaintain.setFunctionOrder("11");
/* 4001 */     demomaintain.setTSFunction(commondemo);
/* 4002 */     demomaintain.setTSIcon(defaultIcon);
/* 4003 */     this.commonDao.saveOrUpdate(demomaintain);
/*      */ 
/* 4005 */     TSFunction democlassify = new TSFunction();
/* 4006 */     democlassify.setFunctionName("特殊布局");
/* 4007 */     democlassify.setFunctionUrl("demoController.do?demoIframe");
/* 4008 */     democlassify.setFunctionLevel(Short.valueOf((short)1));
/* 4009 */     democlassify.setFunctionOrder("12");
/* 4010 */     democlassify.setTSFunction(commondemo);
/* 4011 */     democlassify.setTSIcon(defaultIcon);
/* 4012 */     democlassify.setTSIconDesk(repairInconForDesk("xiami", "特殊布局"));
/* 4013 */     this.commonDao.saveOrUpdate(democlassify);
/*      */ 
/* 4015 */     TSFunction notag1 = new TSFunction();
/* 4016 */     notag1.setFunctionName("单表例子(无Tag)");
/* 4017 */     notag1.setFunctionUrl("jeecgEasyUIController.do?jeecgEasyUI");
/* 4018 */     notag1.setFunctionLevel(Short.valueOf((short)1));
/* 4019 */     notag1.setFunctionOrder("13");
/* 4020 */     notag1.setTSFunction(commondemo);
/* 4021 */     notag1.setTSIcon(defaultIcon);
/* 4022 */     this.commonDao.saveOrUpdate(notag1);
/*      */ 
/* 4024 */     TSFunction notag2 = new TSFunction();
/* 4025 */     notag2.setFunctionName("一对多例子(无Tag)");
/* 4026 */     notag2.setFunctionUrl("jeecgOrderMainNoTagController.do?jeecgOrderMainNoTag");
/* 4027 */     notag2.setFunctionLevel(Short.valueOf((short)1));
/* 4028 */     notag2.setFunctionOrder("14");
/* 4029 */     notag2.setTSFunction(commondemo);
/* 4030 */     notag2.setTSIcon(defaultIcon);
/* 4031 */     this.commonDao.saveOrUpdate(notag2);
/*      */ 
/* 4033 */     TSFunction htmledit = new TSFunction();
/* 4034 */     htmledit.setFunctionName("HTML编辑器");
/* 4035 */     htmledit.setFunctionUrl("jeecgDemoController.do?ckeditor&isIframe");
/* 4036 */     htmledit.setFunctionLevel(Short.valueOf((short)1));
/* 4037 */     htmledit.setFunctionOrder("15");
/* 4038 */     htmledit.setTSFunction(commondemo);
/* 4039 */     htmledit.setTSIcon(defaultIcon);
/* 4040 */     this.commonDao.saveOrUpdate(htmledit);
/*      */ 
/* 4042 */     TSFunction weboffice = new TSFunction();
/* 4043 */     weboffice.setFunctionName("在线word(IE)");
/* 4044 */     weboffice.setFunctionUrl("webOfficeController.do?webOffice");
/* 4045 */     weboffice.setFunctionLevel(Short.valueOf((short)1));
/* 4046 */     weboffice.setFunctionOrder("16");
/* 4047 */     weboffice.setTSFunction(commondemo);
/* 4048 */     weboffice.setTSIcon(defaultIcon);
/* 4049 */     this.commonDao.saveOrUpdate(weboffice);
/*      */ 
/* 4051 */     TSFunction Office = new TSFunction();
/* 4052 */     Office.setFunctionName("WebOffice官方例子");
/* 4053 */     Office.setFunctionUrl("webOfficeController.do?webOfficeSample&isIframe");
/* 4054 */     Office.setFunctionLevel(Short.valueOf((short)1));
/* 4055 */     Office.setFunctionOrder("17");
/* 4056 */     Office.setTSIcon(defaultIcon);
/* 4057 */     Office.setTSFunction(commondemo);
/* 4058 */     this.commonDao.saveOrUpdate(Office);
/*      */ 
/* 4060 */     TSFunction finance = new TSFunction();
/* 4061 */     finance.setFunctionName("多附件管理");
/* 4062 */     finance.setFunctionUrl("tFinanceController.do?tFinance");
/* 4063 */     finance.setFunctionLevel(Short.valueOf((short)1));
/* 4064 */     finance.setFunctionOrder("18");
/* 4065 */     finance.setTSFunction(commondemo);
/* 4066 */     finance.setTSIcon(defaultIcon);
/* 4067 */     finance.setTSIconDesk(repairInconForDesk("vadio", "多附件管理"));
/* 4068 */     this.commonDao.saveOrUpdate(finance);
/*      */ 
/* 4070 */     TSFunction userdemo = new TSFunction();
/* 4071 */     userdemo.setFunctionName("Datagrid手工Html");
/* 4072 */     userdemo.setFunctionUrl("userController.do?userDemo");
/* 4073 */     userdemo.setFunctionLevel(Short.valueOf((short)1));
/* 4074 */     userdemo.setFunctionOrder("19");
/* 4075 */     userdemo.setTSFunction(commondemo);
/* 4076 */     userdemo.setTSIcon(defaultIcon);
/* 4077 */     this.commonDao.saveOrUpdate(userdemo);
/* 4078 */     TSFunction matterBom = new TSFunction();
/* 4079 */     matterBom.setFunctionName("物料Bom");
/* 4080 */     matterBom.setFunctionUrl("jeecgMatterBomController.do?goList");
/* 4081 */     matterBom.setFunctionLevel(Short.valueOf((short)1));
/* 4082 */     matterBom.setFunctionOrder("20");
/* 4083 */     matterBom.setTSFunction(commondemo);
/* 4084 */     matterBom.setTSIcon(defaultIcon);
/* 4085 */     this.commonDao.saveOrUpdate(matterBom);
/* 4086 */     TSFunction reportdemo = new TSFunction();
/* 4087 */     reportdemo.setFunctionName("报表示例");
/* 4088 */     reportdemo.setFunctionUrl("reportDemoController.do?studentStatisticTabs&isIframe");
/* 4089 */     reportdemo.setFunctionLevel(Short.valueOf((short)1));
/* 4090 */     reportdemo.setFunctionOrder("21");
/* 4091 */     reportdemo.setTSFunction(state);
/* 4092 */     reportdemo.setTSIcon(pie);
/* 4093 */     this.commonDao.saveOrUpdate(reportdemo);
/*      */ 
/* 4095 */     TSFunction ckfinder = new TSFunction();
/* 4096 */     ckfinder.setFunctionName("ckfinder例子");
/* 4097 */     ckfinder.setFunctionUrl("jeecgDemoCkfinderController.do?jeecgDemoCkfinder");
/* 4098 */     ckfinder.setFunctionLevel(Short.valueOf((short)1));
/* 4099 */     ckfinder.setFunctionOrder("100");
/* 4100 */     ckfinder.setTSFunction(commondemo);
/* 4101 */     ckfinder.setTSIcon(defaultIcon);
/* 4102 */     this.commonDao.saveOrUpdate(ckfinder);
/*      */   }
/*      */ 
/*      */   private void repairReportEntity()
/*      */   {
/* 4111 */     TSStudent entity = null;
/* 4112 */     entity = new TSStudent();
/* 4113 */     entity.setName("张三");
/* 4114 */     entity.setSex("f");
/* 4115 */     entity.setClassName("1班");
/* 4116 */     this.commonDao.save(entity);
/*      */ 
/* 4118 */     entity = new TSStudent();
/* 4119 */     entity.setName("李四");
/* 4120 */     entity.setSex("f");
/* 4121 */     entity.setClassName("1班");
/* 4122 */     this.commonDao.save(entity);
/*      */ 
/* 4124 */     entity = new TSStudent();
/* 4125 */     entity.setName("王五");
/* 4126 */     entity.setSex("m");
/* 4127 */     entity.setClassName("1班");
/* 4128 */     this.commonDao.save(entity);
/*      */ 
/* 4130 */     entity = new TSStudent();
/* 4131 */     entity.setName("赵六");
/* 4132 */     entity.setSex("f");
/* 4133 */     entity.setClassName("1班");
/* 4134 */     this.commonDao.save(entity);
/*      */ 
/* 4136 */     entity = new TSStudent();
/* 4137 */     entity.setName("张三");
/* 4138 */     entity.setSex("f");
/* 4139 */     entity.setClassName("2班");
/* 4140 */     this.commonDao.save(entity);
/*      */ 
/* 4142 */     entity = new TSStudent();
/* 4143 */     entity.setName("李四");
/* 4144 */     entity.setSex("f");
/* 4145 */     entity.setClassName("2班");
/* 4146 */     this.commonDao.save(entity);
/*      */ 
/* 4148 */     entity = new TSStudent();
/* 4149 */     entity.setName("王五");
/* 4150 */     entity.setSex("m");
/* 4151 */     entity.setClassName("2班");
/* 4152 */     this.commonDao.save(entity);
/*      */ 
/* 4154 */     entity = new TSStudent();
/* 4155 */     entity.setName("赵六");
/* 4156 */     entity.setSex("f");
/* 4157 */     entity.setClassName("2班");
/* 4158 */     this.commonDao.save(entity);
/*      */ 
/* 4160 */     entity = new TSStudent();
/* 4161 */     entity.setName("张三");
/* 4162 */     entity.setSex("f");
/* 4163 */     entity.setClassName("3班");
/* 4164 */     this.commonDao.save(entity);
/*      */ 
/* 4166 */     entity = new TSStudent();
/* 4167 */     entity.setName("李四");
/* 4168 */     entity.setSex("f");
/* 4169 */     entity.setClassName("3班");
/* 4170 */     this.commonDao.save(entity);
/*      */ 
/* 4172 */     entity = new TSStudent();
/* 4173 */     entity.setName("王五");
/* 4174 */     entity.setSex("m");
/* 4175 */     entity.setClassName("3班");
/* 4176 */     this.commonDao.save(entity);
/*      */ 
/* 4178 */     entity = new TSStudent();
/* 4179 */     entity.setName("李四");
/* 4180 */     entity.setSex("f");
/* 4181 */     entity.setClassName("3班");
/* 4182 */     this.commonDao.save(entity);
/*      */ 
/* 4184 */     entity = new TSStudent();
/* 4185 */     entity.setName("王五");
/* 4186 */     entity.setSex("m");
/* 4187 */     entity.setClassName("3班");
/* 4188 */     this.commonDao.save(entity);
/*      */ 
/* 4190 */     entity = new TSStudent();
/* 4191 */     entity.setName("赵六");
/* 4192 */     entity.setSex("f");
/* 4193 */     entity.setClassName("3班");
/* 4194 */     this.commonDao.save(entity);
/*      */ 
/* 4196 */     entity = new TSStudent();
/* 4197 */     entity.setName("张三");
/* 4198 */     entity.setSex("f");
/* 4199 */     entity.setClassName("4班");
/* 4200 */     this.commonDao.save(entity);
/*      */ 
/* 4202 */     entity = new TSStudent();
/* 4203 */     entity.setName("李四");
/* 4204 */     entity.setSex("f");
/* 4205 */     entity.setClassName("4班");
/* 4206 */     this.commonDao.save(entity);
/*      */ 
/* 4208 */     entity = new TSStudent();
/* 4209 */     entity.setName("王五");
/* 4210 */     entity.setSex("m");
/* 4211 */     entity.setClassName("4班");
/* 4212 */     this.commonDao.save(entity);
/*      */ 
/* 4214 */     entity = new TSStudent();
/* 4215 */     entity.setName("赵六");
/* 4216 */     entity.setSex("f");
/* 4217 */     entity.setClassName("4班");
/* 4218 */     this.commonDao.save(entity);
/*      */ 
/* 4220 */     entity = new TSStudent();
/* 4221 */     entity.setName("张三");
/* 4222 */     entity.setSex("m");
/* 4223 */     entity.setClassName("5班");
/* 4224 */     this.commonDao.save(entity);
/*      */ 
/* 4226 */     entity = new TSStudent();
/* 4227 */     entity.setName("李四");
/* 4228 */     entity.setSex("f");
/* 4229 */     entity.setClassName("5班");
/* 4230 */     this.commonDao.save(entity);
/*      */ 
/* 4232 */     entity = new TSStudent();
/* 4233 */     entity.setName("王五");
/* 4234 */     entity.setSex("m");
/* 4235 */     entity.setClassName("5班");
/* 4236 */     this.commonDao.save(entity);
/*      */ 
/* 4238 */     entity = new TSStudent();
/* 4239 */     entity.setName("赵六");
/* 4240 */     entity.setSex("m");
/* 4241 */     entity.setClassName("5班");
/* 4242 */     this.commonDao.save(entity);
/*      */ 
/* 4244 */     entity = new TSStudent();
/* 4245 */     entity.setName("赵六");
/* 4246 */     entity.setSex("m");
/* 4247 */     entity.setClassName("5班");
/* 4248 */     this.commonDao.save(entity);
/*      */ 
/* 4250 */     entity = new TSStudent();
/* 4251 */     entity.setName("李四");
/* 4252 */     entity.setSex("f");
/* 4253 */     entity.setClassName("5班");
/* 4254 */     this.commonDao.save(entity);
/*      */ 
/* 4256 */     entity = new TSStudent();
/* 4257 */     entity.setName("王五");
/* 4258 */     entity.setSex("m");
/* 4259 */     entity.setClassName("5班");
/* 4260 */     this.commonDao.save(entity);
/*      */ 
/* 4262 */     entity = new TSStudent();
/* 4263 */     entity.setName("赵六");
/* 4264 */     entity.setSex("m");
/* 4265 */     entity.setClassName("5班");
/* 4266 */     this.commonDao.save(entity);
/*      */ 
/* 4268 */     entity = new TSStudent();
/* 4269 */     entity.setName("赵六");
/* 4270 */     entity.setSex("m");
/* 4271 */     entity.setClassName("5班");
/* 4272 */     this.commonDao.save(entity);
/*      */   }
/*      */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.service.impl.RepairServiceImpl
 * JD-Core Version:    0.6.2
 */