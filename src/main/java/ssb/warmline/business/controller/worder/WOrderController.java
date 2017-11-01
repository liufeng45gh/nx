//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.controller.worder;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import ssb.warmline.business.commons.enums.HelpMessageState;
import ssb.warmline.business.commons.enums.HelpMessageType;
import ssb.warmline.business.commons.enums.OrderType;
import ssb.warmline.business.commons.enums.Orderstatu;
import ssb.warmline.business.commons.enums.PayStatus;
import ssb.warmline.business.commons.enums.PaymentMode;
import ssb.warmline.business.commons.enums.PhotoType;
import ssb.warmline.business.commons.enums.ReadingState;
import ssb.warmline.business.commons.shortMessage.PhoneCode;
import ssb.warmline.business.commons.utils.DateUtils;
import ssb.warmline.business.commons.utils.OrderNumber;
import ssb.warmline.business.entity.whelpmessage.WHelpMessageEntity;
import ssb.warmline.business.entity.worder.WOrderEntity;
import ssb.warmline.business.entity.worderphoto.WOrderPhotoEntity;
import ssb.warmline.business.entity.worderphotomain.WOrderPhotoMainEntity;
import ssb.warmline.business.entity.worderrecord.WOrderRecordEntity;
import ssb.warmline.business.entity.wterritory.WTerritoryBusiness;
import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
import ssb.warmline.business.service.whelpmessage.WHelpMessageServiceI;
import ssb.warmline.business.service.worder.WOrderServiceI;
import ssb.warmline.business.service.worderphoto.WOrderPhotoServiceI;
import ssb.warmline.business.service.worderphotomain.WOrderPhotoMainServiceI;
import ssb.warmline.business.service.worderrecord.WOrderRecordServiceI;
import ssb.warmline.business.service.wterritory.WTerritoryServiceI;
import ssb.warmline.business.utils.PropertiesUtil;

@Controller
@RequestMapping({"/wOrderController"})
public class WOrderController extends BaseController {
    private static final Logger logger = Logger.getLogger(WOrderController.class);
    static final String separator;
    private ApplicationContext context = null;
    @Autowired
    private WOrderServiceI wOrderService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private TSBaseUserServiceI tSBaseUserService;
    @Autowired
    private WHelpMessageServiceI wHelpMessageService;
    @Autowired
    private WOrderPhotoServiceI wOrderPhotoService;
    @Autowired
    private Validator validator;
    @Autowired
    private WOrderPhotoMainServiceI wOrderPhotoMainService;
    @Autowired
    private WOrderRecordServiceI orderRecordService;
    @Autowired
    private WTerritoryServiceI wTerritoryService;

    static {
        separator = File.separator;
    }

    public WOrderController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("ssb/warmline/business/worder/wOrderList");
    }

    @RequestMapping(
            params = {"wOrderVirtualUserList"}
    )
    public ModelAndView wOrderVirtualUserList(HttpServletRequest request) {
        return new ModelAndView("ssb/warmline/business/worder/wOrderVirtualUserList");
    }

    @RequestMapping(
            params = {"wOrderOrdinaryList"}
    )
    public ModelAndView wOrderOrdinaryList(HttpServletRequest request) {
        return new ModelAndView("ssb/warmline/business/worder/wOrderOrdinaryList");
    }

    @RequestMapping(
            params = {"historyOrder"}
    )
    public String historyOrder(HttpServletRequest request, String status) {
        HttpSession session = request.getSession();
        String issuerId = request.getParameter("issuerId");
        session.setAttribute("issuerId", issuerId);
        return "ssb/warmline/business/worder/historyList";
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(WOrderEntity wOrder, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WOrderEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wOrder, request.getParameterMap());

        try {
            cq.eq("seekStatus", "0");
        } catch (Exception var7) {
            throw new BusinessException(var7.getMessage());
        }

        cq.add();
        this.wOrderService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"orderDatagrid"}
    )
    public void orderDatagrid(WOrderEntity wOrder, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WOrderEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wOrder, request.getParameterMap());
        String issuerId = (String)request.getSession().getAttribute("issuerId");

        try {
            cq.eq("issuerId", issuerId);
        } catch (Exception var8) {
            throw new BusinessException(var8.getMessage());
        }

        cq.add();
        this.wOrderService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"virtualUserDatagrid"}
    )
    public void virtualUserDatagrid(WOrderEntity wOrder, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WOrderEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wOrder, request.getParameterMap());

        try {
            cq.eq("isVirtualUser", "Yes");
            TSUser currentUser = ResourceUtil.getSessionUserName();
            new ArrayList();
            String id = PropertiesUtil.getProperties();
            List<TSRoleUser> roleUsers = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", currentUser.getId());
            TSRoleUser tsRU = (TSRoleUser)roleUsers.get(0);
            TSRole role = tsRU.getTSRole();
            if("sysWorker".equals(role.getRoleCode())) {
                cq.eq("subordinateAdmin", currentUser.getId());
            }
        } catch (Exception var11) {
            throw new BusinessException(var11.getMessage());
        }

        cq.add();
        this.wOrderService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"ordinaryDatagrid"}
    )
    public void ordinaryDatagrid(WOrderEntity wOrder, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WOrderEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wOrder, request.getParameterMap());

        try {
            cq.eq("seekStatus", "1");
        } catch (Exception var7) {
            throw new BusinessException(var7.getMessage());
        }

        cq.add();
        this.wOrderService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(WOrderEntity wOrder, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        wOrder = (WOrderEntity)this.systemService.getEntity(WOrderEntity.class, wOrder.getId());
        message = "订单删除成功";

        try {
            this.wOrderService.delete(wOrder);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "订单删除失败";
            throw new BusinessException(var6.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doBatchDel"}
    )
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "订单删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                WOrderEntity wOrder = (WOrderEntity)this.systemService.getEntity(WOrderEntity.class, id);
                this.wOrderService.delete(wOrder);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "订单删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(WOrderEntity wOrder, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "订单添加成功";

        try {
            this.wOrderService.save(wOrder);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "订单添加失败";
            throw new BusinessException(var6.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(WOrderEntity wOrder, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "订单更新成功";
        WOrderEntity t = (WOrderEntity)this.wOrderService.get(WOrderEntity.class, wOrder.getId());

        try {
            MyBeanUtils.copyBeanNotNull2Bean(wOrder, t);
            this.wOrderService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var7) {
            var7.printStackTrace();
            message = "订单更新失败";
            throw new BusinessException(var7.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(WOrderEntity wOrder, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wOrder.getId())) {
            wOrder = (WOrderEntity)this.wOrderService.getEntity(WOrderEntity.class, wOrder.getId());
            req.setAttribute("wOrderPage", wOrder);
        }

        return new ModelAndView("ssb/warmline/business/worder/wOrder-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(WOrderEntity wOrder, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wOrder.getId())) {
            wOrder = (WOrderEntity)this.wOrderService.getEntity(WOrderEntity.class, wOrder.getId());
            req.setAttribute("wOrderPage", wOrder);
        }

        return new ModelAndView("ssb/warmline/business/worder/wOrder-update");
    }

    @RequestMapping(
            params = {"choiceTerritory"}
    )
    @ResponseBody
    public AjaxJson choiceTerritory(HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String territoryid = request.getParameter("territoryid");
        WTerritoryBusiness territory = (WTerritoryBusiness)this.wTerritoryService.findUniqueByProperty(WTerritoryBusiness.class, "id", territoryid);
        int territorylevel;
        if("China".equals(territory.getOut_in())) {
            territorylevel = territory.getTerritorylevel().intValue();
            if(2 == territorylevel) {
                if(!"1".equals(territory.getTerritoryparentid()) && !"19".equals(territory.getTerritoryparentid()) && !"2323".equals(territory.getTerritoryparentid()) && !"801".equals(territory.getTerritoryparentid())) {
                    j.setSuccess(true);
                } else {
                    j.setSuccess(false);
                }
            } else if(3 != territorylevel) {
                j.setSuccess(false);
            }
        } else if("Thailand".equals(territory.getOut_in())) {
            territorylevel = territory.getTerritorylevel().intValue();
            if(2 == territorylevel) {
                j.setMsg("true");
            } else {
                j.setMsg("false");
            }
        }

        return j;
    }

    @RequestMapping(
            params = {"doOrderAdd"}
    )
    @ResponseBody
    public AjaxJson doOrderAdd(WOrderEntity wOrder, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        TSUser currentUser = ResourceUtil.getSessionUserName();
        new ArrayList();
        String id = PropertiesUtil.getProperties();
        List<TSRoleUser> roleUsers = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", currentUser.getId());
        TSRoleUser tsRU = (TSRoleUser)roleUsers.get(0);
        TSRole role = tsRU.getTSRole();
        String message = null;
        if(!"sysWorker".equals(role.getRoleCode())) {
            message = "订单录入只能为系统运营员！！";
            j.setMsg(message);
            return j;
        } else {
            String territoryid = request.getParameter("territoryid");
            String photoss = request.getParameter("photoss");
            WTerritoryBusiness wTerritory = (WTerritoryBusiness)this.wTerritoryService.findUniqueByProperty(WTerritoryBusiness.class, "id", territoryid);
            WTerritoryBusiness cityTerritory;
            if("China".equals(wTerritory.getOut_in())) {
                cityTerritory = (WTerritoryBusiness)this.wTerritoryService.findUniqueByProperty(WTerritoryBusiness.class, "id", wTerritory.getTerritoryparentid());
                WTerritoryBusiness stateTerritory = (WTerritoryBusiness)this.wTerritoryService.findUniqueByProperty(WTerritoryBusiness.class, "id", cityTerritory.getTerritoryparentid());
                if(stateTerritory == null || "00".equals(stateTerritory.getId())) {
                    message = "订单添加失败,不能选择父级区域";
                    j.setMsg(message);
                    j.setSuccess(false);
                    return j;
                }

                if(2 != wTerritory.getTerritorylevel().intValue() && "1".equals(wTerritory.getTerritoryparentid()) && "19".equals(wTerritory.getTerritoryparentid()) && "2323".equals(wTerritory.getTerritoryparentid()) && "801".equals(wTerritory.getTerritoryparentid())) {
                    message = "订单添加失败,不能选择父级区域";
                    j.setMsg(message);
                    j.setSuccess(false);
                    return j;
                }

                wOrder.setState(stateTerritory.getTerritoryname());
                wOrder.setCity(cityTerritory.getTerritoryname() + "  " + wTerritory.getTerritoryname());
                wOrder.setLatitude(wTerritory.getXwgs84().toString());
                wOrder.setLongitude(wTerritory.getYwgs84().toString());
                wOrder.setTerritoryId(wTerritory.getTerritoryparentid());
            } else if("Thailand".equals(wTerritory.getOut_in())) {
                cityTerritory = (WTerritoryBusiness)this.wTerritoryService.findUniqueByProperty(WTerritoryBusiness.class, "id", wTerritory.getTerritoryparentid());
                if("00".equals(cityTerritory.getId())) {
                    message = "订单添加失败,不能选择父级区域";
                    j.setMsg(message);
                    j.setSuccess(false);
                    return j;
                }

                wOrder.setState(cityTerritory.getTerritoryname());
                wOrder.setCity(cityTerritory.getTerritoryname() + "  " + wTerritory.getTerritoryname());
                wOrder.setLatitude(wTerritory.getXwgs84().toString());
                wOrder.setLongitude(wTerritory.getYwgs84().toString());
                wOrder.setTerritoryId(wTerritory.getId());
            }

            TSUser user = ResourceUtil.getSessionUserName();
            message = "订单添加成功";

            try {
                wOrder.setOrderNumber(OrderNumber.generateNumber3());
                wOrder.setCategory(wOrder.getCategory());
                wOrder.setIssuerId(wOrder.getIssuerId());
                wOrder.setIssuer(wOrder.getIssuer());
                wOrder.setPhone(wOrder.getPhone());
                wOrder.setOrderPhone(wOrder.getPhone());
                wOrder.setOrderTime(new Date());
                wOrder.setPaymentTime(new Date());
                wOrder.setOrderStatus(Orderstatu.ORDERSTATU_001.toString());
                wOrder.setIsVirtualUser("Yes");
                wOrder.setBuyStatus(PayStatus.PAY_002.toString());
                wOrder.setPaymentMode(PaymentMode.wallet.toString());
                wOrder.setLocation(wTerritory.getTerritoryname());
                if("sysWorker".equals(role.getRoleCode())) {
                    wOrder.setSubordinateAdmin(user.getId());
                }

                this.wOrderService.saveOrUpdate(wOrder);
                List<WOrderPhotoEntity> wOrderPhoto = this.wOrderPhotoService.findByProperty(WOrderPhotoEntity.class, "id", photoss);
                List<WOrderPhotoMainEntity> orderPhotoMainList = this.wOrderPhotoMainService.findByProperty(WOrderPhotoMainEntity.class, "orderId", wOrder.getId());
                WOrderPhotoMainEntity wOrderPhotoMain;
                if(wOrderPhoto.size() > 0) {
                    for(int i = 0; i < wOrderPhoto.size(); ++i) {
                        WOrderPhotoEntity wOrderPhotoEntity = (WOrderPhotoEntity)wOrderPhoto.get(i);
                        wOrderPhotoMain = new WOrderPhotoMainEntity();
                        wOrderPhotoMain.setOrderId(wOrder.getId());
                        wOrderPhotoMain.setUid(wOrder.getIssuerId());
                        wOrderPhotoMain.setCreateTime(new Date());
                        wOrderPhotoMain.setPhotoType(wOrderPhotoEntity.getPhotoType());
                        wOrderPhotoMain.setPhotoUrl(wOrderPhotoEntity.getPhotoUrl());
                        wOrderPhotoMain.setPhotoName(wOrderPhotoEntity.getPhotoName());
                        this.wOrderPhotoMainService.saveOrUpdate(wOrderPhotoMain);
                        wOrder.setPhotos(wOrderPhotoMain.getId());
                    }
                } else if(orderPhotoMainList.size() > 0) {
                    wOrderPhotoMain = (WOrderPhotoMainEntity)orderPhotoMainList.get(0);
                    wOrder.setPhotos(wOrderPhotoMain.getId());
                }

                this.wOrderService.saveOrUpdate(wOrder);
                WOrderRecordEntity wJournal = new WOrderRecordEntity();
                wJournal.setId(UUID.randomUUID().toString());
                wJournal.setOrderId(wOrder.getId());
                wJournal.setOrderNumber(wOrder.getOrderNumber());
                wJournal.setAmount(wOrder.getPrice());
                wJournal.setOrderType(OrderType.ORDERTYPE_001.toString());
                wJournal.setCreateTime(new Date());
                wJournal.setDescription("普通订单购买");
                wJournal.setPhone(wOrder.getPhone());
                wJournal.setIssuerId(wOrder.getIssuerId());
                wJournal.setIssuer(wOrder.getIssuer());
                wJournal.setBuyStatus(PayStatus.PAY_002.toString());
                wJournal.setOrderStatus(Orderstatu.ORDERSTATU_001.toString());
                this.orderRecordService.save(wJournal);
                this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
            } catch (Exception var19) {
                var19.printStackTrace();
                message = "订单添加失败";
                throw new BusinessException(var19.getMessage());
            }

            j.setMsg(message);
            return j;
        }
    }

    @RequestMapping(
            params = {"choiceVirtualUser"}
    )
    public ModelAndView choiceVirtualUser(HttpServletRequest request, String status) {
        return new ModelAndView("ssb/warmline/business/worder/choiceVirtualUser");
    }

    @RequestMapping(
            params = {"goVirtualUserAdd"}
    )
    public ModelAndView goVirtualUserAdd(WOrderEntity wOrder, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wOrder.getId())) {
            wOrder = (WOrderEntity)this.wOrderService.getEntity(WOrderEntity.class, wOrder.getId());
            req.setAttribute("wOrderPage", wOrder);
        }

        return new ModelAndView("ssb/warmline/business/worder/wOrder-virtualUserAdd");
    }

    @RequestMapping(
            params = {"goVirtualUserUpdate"}
    )
    public ModelAndView goVirtualUserUpdate(WOrderEntity wOrder, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wOrder.getId())) {
            wOrder = (WOrderEntity)this.wOrderService.getEntity(WOrderEntity.class, wOrder.getId());
            req.setAttribute("wOrderPage", wOrder);
            req.setAttribute("text", wOrder.getCity());
            req.setAttribute("id", wOrder.getTerritoryId());
            List<WOrderPhotoMainEntity> wOrderPhotoList = this.wOrderPhotoMainService.findByProperty(WOrderPhotoMainEntity.class, "id", wOrder.getPhotos());
            if(wOrderPhotoList.size() > 0) {
                WOrderPhotoMainEntity wOrderPhoto = (WOrderPhotoMainEntity)wOrderPhotoList.get(0);
                String domain = req.getServerName() + ":";
                String pathUrlName = "http://" + domain + "80";
                String picname = pathUrlName + wOrderPhoto.getPhotoUrl();
                req.setAttribute("categoryImage", wOrderPhoto.getPhotoUrl());
                req.setAttribute("imgRootPath", picname);
                req.setAttribute("imgRootPath", picname);
            }
        }

        return new ModelAndView("ssb/warmline/business/worder/wOrder-virtualUserUpdate");
    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "wOrderController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(WOrderEntity wOrder, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(WOrderEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wOrder, request.getParameterMap());
        List<WOrderEntity> wOrders = this.wOrderService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "订单");
        modelMap.put("entity", WOrderEntity.class);
        modelMap.put("params", new ExportParams("订单列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", wOrders);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByT"}
    )
    public String exportXlsByT(WOrderEntity wOrder, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "订单");
        modelMap.put("entity", WOrderEntity.class);
        modelMap.put("params", new ExportParams("订单列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"importExcel"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        Iterator var7 = fileMap.entrySet().iterator();

        while(var7.hasNext()) {
            Entry<String, MultipartFile> entity = (Entry)var7.next();
            MultipartFile file = (MultipartFile)entity.getValue();
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);

            try {
                List<WOrderEntity> listWOrderEntitys = ExcelImportUtil.importExcel(file.getInputStream(), WOrderEntity.class, params);
                Iterator var12 = listWOrderEntitys.iterator();

                while(var12.hasNext()) {
                    WOrderEntity wOrder = (WOrderEntity)var12.next();
                    this.wOrderService.save(wOrder);
                }

                j.setMsg("文件导入成功！");
            } catch (Exception var21) {
                j.setMsg("文件导入失败！");
                logger.error(ExceptionUtil.getExceptionMessage(var21));
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException var20) {
                    var20.printStackTrace();
                }

            }
        }

        return j;
    }

    @RequestMapping(
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public List<WOrderEntity> list() {
        List<WOrderEntity> listWOrders = this.wOrderService.getList(WOrderEntity.class);
        return listWOrders;
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        WOrderEntity task = (WOrderEntity)this.wOrderService.get(WOrderEntity.class, id);
        return task == null?new ResponseEntity(HttpStatus.NOT_FOUND):new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(
            method = {RequestMethod.POST},
            consumes = {"application/json"}
    )
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody WOrderEntity wOrder, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<WOrderEntity>> failures = this.validator.validate(wOrder, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wOrderService.save(wOrder);
            String id = wOrder.getId();
            URI uri = uriBuilder.path("/rest/wOrderController/" + id).build().toUri();
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uri);
            return new ResponseEntity(headers, HttpStatus.CREATED);
        }
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.PUT},
            consumes = {"application/json"}
    )
    public ResponseEntity<?> update(@RequestBody WOrderEntity wOrder) {
        Set<ConstraintViolation<WOrderEntity>> failures = this.validator.validate(wOrder, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wOrderService.saveOrUpdate(wOrder);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.DELETE}
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.wOrderService.deleteEntityById(WOrderEntity.class, id);
    }

    @RequestMapping(
            params = {"choicePickOne"}
    )
    public ModelAndView choicePickOne(HttpServletRequest request, String status) {
        HttpSession session = request.getSession();
        String orderId = request.getParameter("orderId");
        if(orderId != null && !"".equals(orderId)) {
            WOrderEntity wOrder = (WOrderEntity)this.wOrderService.findUniqueByProperty(WOrderEntity.class, "id", orderId);
            request.setAttribute("territoryId", wOrder.getTerritoryId());
        }

        session.setAttribute("orderId", orderId);
        return new ModelAndView("ssb/warmline/business/tsbaseuser/tSBaseUserChoiceSingle");
    }

    @RequestMapping(
            params = {"choiceSingle"}
    )
    @ResponseBody
    public AjaxJson choiceSingle(HttpServletRequest request, String riskplannerId) {
        String message = null;
        AjaxJson j = new AjaxJson();
        HttpSession session = request.getSession();
        String userId = request.getParameter("userId");
        String orderid = (String)session.getAttribute("orderId");
        WOrderEntity wOrder = (WOrderEntity)this.wOrderService.findUniqueByProperty(WOrderEntity.class, "id", orderid);
        TSBaseUser tsBaseUser = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", userId);
        if(!"ORDERSTATU_001".equals(wOrder.getOrderStatus())) {
            message = "给订单已经选择了接单人或者未支付！";
            j.setMsg(message);
            j.setSuccess(false);
            return j;
        } else if(tsBaseUser.getId().equals(wOrder.getIssuerId())) {
            message = "用户自己不能接自己发的单！";
            j.setMsg(message);
            j.setSuccess(false);
            return j;
        } else {
            wOrder.setOrderPersonId(userId);
            wOrder.setOrderPersonName(tsBaseUser.getRealName());
            wOrder.setOrderStatus(Orderstatu.ORDERSTATU_002.toString());
            wOrder.setOrderPersonPhone(tsBaseUser.getPhone());
            wOrder.setDetermineServiceTime(new Date());
            List<WHelpMessageEntity> wHelpMessage = this.wHelpMessageService.findByProperty(WHelpMessageEntity.class, "orderid", orderid);
            WHelpMessageEntity wHelpMessageEntity;
            if(wHelpMessage.size() > 0) {
                for(int a = 0; a < wHelpMessage.size(); ++a) {
                    wHelpMessageEntity = (WHelpMessageEntity)wHelpMessage.get(a);
                    if(!tsBaseUser.getId().equals(wHelpMessageEntity.getHelpPeopleId())) {
                        wHelpMessageEntity.setReadingState("No");
                        wHelpMessageEntity.setContent(HelpMessageType.REFUSE.toString());
                        wHelpMessageEntity.setMessageType(HelpMessageType.REFUSE.toString());
                        this.wHelpMessageService.saveOrUpdate(wHelpMessageEntity);
                    }
                }
            }

            String content = "恭喜您，" + wOrder.getIssuerId() + "选择了您帮助他。";
            wHelpMessageEntity = new WHelpMessageEntity();
            wHelpMessageEntity.setId(UUID.randomUUID().toString());
            wHelpMessageEntity.setSeekHelpPeopleId(wOrder.getIssuerId());
            wHelpMessageEntity.setSeekHelpPeople(wOrder.getIssuer());
            wHelpMessageEntity.setHelpPeople(tsBaseUser.getUserName());
            wHelpMessageEntity.setHelpPeopleId(tsBaseUser.getId());
            wHelpMessageEntity.setContent(content);
            wHelpMessageEntity.setCreateDate(new Date());
            wHelpMessageEntity.setReadingState(ReadingState.No.toString());
            wHelpMessageEntity.setMessageType(HelpMessageType.AGREE.toString());
            wHelpMessageEntity.setMessageStatus(HelpMessageType.AGREE.toString());
            wHelpMessageEntity.setOrderid(wOrder.getId());
            wHelpMessageEntity.setMessageState(HelpMessageState.HELP.toString());
            this.wHelpMessageService.save(wHelpMessageEntity);
            this.wOrderService.saveOrUpdate(wOrder);
            TSBaseUser issuerBaseUser = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", wOrder.getIssuerId());
            PhoneCode.getLoginPhoneOrderCode(issuerBaseUser.getAreaCode() + issuerBaseUser.getPhone(), "【暖行】恭喜您，您的紧急订单：" + wOrder.getOrderNumber() + "已被手机号为：" + tsBaseUser.getAreaCode() + "-" + tsBaseUser.getPhone() + "接单！");
            PhoneCode.getLoginPhoneOrderCode(tsBaseUser.getAreaCode() + tsBaseUser.getPhone(), "【暖行】恭喜您，" + wOrder.getIssuer() + "的紧急订单选择了您帮助他！可联系发单人电话：" + issuerBaseUser.getAreaCode() + "-" + issuerBaseUser.getPhone());
            return j;
        }
    }

    @RequestMapping(
            params = {"doCheck"}
    )
    public ModelAndView doCheck(HttpServletRequest request) {
        String id = request.getParameter("id");
        request.setAttribute("id", id);
        if(StringUtil.isNotEmpty(id)) {
            WOrderEntity fOrderAddress = (WOrderEntity)this.wOrderService.getEntity(WOrderEntity.class, id);
            request.setAttribute("fOrderAddress", fOrderAddress);
        }

        return new ModelAndView("ssb/warmline/business/worder/remarks");
    }

    @RequestMapping(
            params = {"saveAuthor"}
    )
    @ResponseBody
    public AjaxJson saveAuthor(WOrderEntity WOrderentity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        if(StringUtil.isNotEmpty(WOrderentity.getId())) {
            message = "审核成功";

            try {
                WOrderEntity fOrderAddress = (WOrderEntity)this.wOrderService.getEntity(WOrderEntity.class, WOrderentity.getId());
                fOrderAddress.setRemarks(WOrderentity.getRemarks());
                this.wOrderService.saveOrUpdate(fOrderAddress);
                this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
            } catch (Exception var6) {
                var6.printStackTrace();
            }
        }

        return j;
    }

    @RequestMapping(
            params = {"uploadPic"}
    )
    @ResponseBody
    public AjaxJson uploadPic(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        TSUser currentUser = ResourceUtil.getSessionUserName();
        String id = currentUser.getId();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        String path_temp = request.getSession().getServletContext().getRealPath("");
        String path = path_temp.substring(0, path_temp.lastIndexOf(separator));
        String imgPath = separator + "upload" + separator + "orderImage";
        String uploadPath = path + separator + "upload";
        String filePath = path + imgPath;
        File fileDir = new File(filePath);
        if(!fileDir.exists()) {
            fileDir.mkdirs();
        }

        File picTempFile = null;
        Iterator var16 = fileMap.entrySet().iterator();

        while(var16.hasNext()) {
            Entry<String, MultipartFile> entity = (Entry)var16.next();
            MultipartFile file = (MultipartFile)entity.getValue();
            String fileNametemp = FileUtils.getExtend(file.getOriginalFilename());
            if("jpg".equals(fileNametemp) || "jpeg".equals(fileNametemp) || "png".equals(fileNametemp) || "gif".equals(fileNametemp) || "bmp".equals(fileNametemp) || "ico".equals(fileNametemp) || "tif".equals(fileNametemp)) {
                String fileName = DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(8) + "." + FileUtils.getExtend(file.getOriginalFilename());
                picTempFile = new File(fileDir, fileName);

                try {
                    if(picTempFile.exists()) {
                        org.apache.commons.io.FileUtils.forceDelete(picTempFile);
                    }

                    FileCopyUtils.copy(file.getBytes(), picTempFile);
                } catch (Exception var25) {
                    var25.printStackTrace();
                    j.setMsg("缩略图上传失败！");
                    j.setSuccess(false);
                }

                String domain = request.getServerName() + ":";
                String pathUrlName = "http://" + domain + "80";
                String picname = pathUrlName + "/upload/orderImage/" + fileName;
                WOrderPhotoEntity wOrderPhotoEntity = new WOrderPhotoEntity();
                wOrderPhotoEntity.setId(UUID.randomUUID().toString());
                wOrderPhotoEntity.setCreateTime(new Date());
                wOrderPhotoEntity.setPhotoUrl(picname);
                wOrderPhotoEntity.setPhotoName(fileName);
                wOrderPhotoEntity.setUid(id);
                wOrderPhotoEntity.setPhotoType(PhotoType.PHOTOTYPE_001.toString());
                this.wOrderPhotoService.save(wOrderPhotoEntity);
                j.setObj(picname);
                Map nameMap = new HashMap();
                nameMap.put("name", "/upload/orderImage/" + fileName);
                nameMap.put("wOrderPhotoId", wOrderPhotoEntity.getId());
                j.setAttributes(nameMap);
            }

            j.setMsg("缩略图上传成功！");
            j.setSuccess(true);
        }

        return j;
    }
}
