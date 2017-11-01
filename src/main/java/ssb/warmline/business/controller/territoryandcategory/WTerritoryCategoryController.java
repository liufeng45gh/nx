//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.controller.territoryandcategory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.CommonService;
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
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
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
import ssb.warmline.business.commons.enums.Orderstatu;
import ssb.warmline.business.commons.enums.PayStatus;
import ssb.warmline.business.commons.utils.DateUtils;
import ssb.warmline.business.commons.utils.OrderNumber;
import ssb.warmline.business.commons.utils.UUIDUtil;
import ssb.warmline.business.entity.territoryandcategory.WTerritoryCategoryEntity;
import ssb.warmline.business.entity.wcategory.WCategoryEntity;
import ssb.warmline.business.entity.worder.WOrderEntity;
import ssb.warmline.business.entity.wterritory.WTerritoryBusiness;
import ssb.warmline.business.service.territoryandcategory.WTerritoryCategoryServiceI;
import ssb.warmline.business.service.wcategory.WCategoryServiceI;
import ssb.warmline.business.service.worder.WOrderServiceI;
import ssb.warmline.business.service.wterritory.WTerritoryBusinessServiceI;

@Controller
@RequestMapping({"/wTerritoryCategoryController"})
public class WTerritoryCategoryController extends BaseController {
    private static final Logger logger = Logger.getLogger(WTerritoryCategoryController.class);
    static final String separator;
    @Autowired
    private WTerritoryCategoryServiceI wTerritoryCategoryService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;
    @Autowired
    private CommonService commonService;
    @Autowired
    private WTerritoryBusinessServiceI WTerritoryBusinessService;
    @Autowired
    private WCategoryServiceI WCategoryService;
    @Autowired
    private WOrderServiceI wOrderService;

    static {
        separator = File.separator;
    }

    public WTerritoryCategoryController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("ssb/warmline/business/territoryandcategory/wTerritoryCategoryList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(WTerritoryCategoryEntity wTerritoryCategory, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WTerritoryCategoryEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wTerritoryCategory, request.getParameterMap());
        cq.add();
        this.wTerritoryCategoryService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(WTerritoryCategoryEntity wTerritoryCategory, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        wTerritoryCategory = (WTerritoryCategoryEntity)this.systemService.getEntity(WTerritoryCategoryEntity.class, wTerritoryCategory.getId());
        message = "区域分类中间表删除成功";

        try {
            this.wTerritoryCategoryService.delete(wTerritoryCategory);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "区域分类中间表删除失败";
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
        message = "区域分类中间表删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                WTerritoryCategoryEntity wTerritoryCategory = (WTerritoryCategoryEntity)this.systemService.getEntity(WTerritoryCategoryEntity.class, id);
                this.wTerritoryCategoryService.delete(wTerritoryCategory);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "区域分类中间表删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(WTerritoryCategoryEntity wTerritoryCategory, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "区域分类中间表添加成功";

        try {
            List<WTerritoryCategoryEntity> list = this.wTerritoryCategoryService.findByQueryString(" from WTerritoryCategoryEntity where categoryId= '" + wTerritoryCategory.getCategoryId() + "' and territoryId= '" + wTerritoryCategory.getTerritoryId() + "'");
            if(list.size() > 0) {
                WTerritoryCategoryEntity wTerritoryCategoryTemp = (WTerritoryCategoryEntity)list.get(0);
                wTerritoryCategoryTemp.setPrice(wTerritoryCategory.getPrice());
                wTerritoryCategoryTemp.setCategoryImage(wTerritoryCategory.getCategoryImage());
                this.commonService.updateEntitie(wTerritoryCategoryTemp);
            } else {
                this.wTerritoryCategoryService.save(wTerritoryCategory);
            }

            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var7) {
            var7.printStackTrace();
            message = "区域分类中间表添加失败";
            throw new BusinessException(var7.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"categoryList"}
    )
    @ResponseBody
    public JSONArray categoryList(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        JSONArray childMenu = new JSONArray();
        if(id == null) {
            id = "0";
        }

        List<WCategoryEntity> listCategoryTemp = this.systemService.findByProperty(WCategoryEntity.class, "categoryParentid", id);
        Iterator var7 = listCategoryTemp.iterator();

        while(var7.hasNext()) {
            WCategoryEntity object = (WCategoryEntity)var7.next();
            JSONObject jsonMenu = JSONObject.fromObject(object);
            if(!"0".equals(jsonMenu.get("id"))) {
                String jsonStr = jsonMenu.toString();
                jsonStr = jsonStr.replaceAll("categoryName", "name");
                jsonStr = jsonStr.replaceAll("categoryParentid", "pId");
                jsonStr = jsonStr.replaceAll("isparent", "isParent");
                childMenu.add(jsonStr);
            }
        }

        return childMenu;
    }

    @RequestMapping(
            params = {"territoryList"}
    )
    @ResponseBody
    public JSONArray territoryList(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        JSONArray childMenu = new JSONArray();
        if(id == null) {
            id = "00";
        }

        String hql = "FROM WTerritoryBusiness where territoryparentid='" + id + "'  ORDER BY territorysort asc";
        List<WTerritoryBusiness> listTerritoryTemp = this.systemService.findByQueryString(hql);
        Iterator var8 = listTerritoryTemp.iterator();

        while(var8.hasNext()) {
            WTerritoryBusiness object = (WTerritoryBusiness)var8.next();
            if(object != null) {
                JSONObject jsonMenu = JSONObject.fromObject(object);
                if("10000".equals(id)) {
                    jsonMenu.remove("isparent");
                    jsonMenu.put("isparent", "false");
                } else if(!"00".equals(id) && !"0".equals(id) && !"1".equals(id) && !"19".equals(id) && !"2323".equals(id) && !"801".equals(id)) {
                    jsonMenu.remove("isparent");
                    jsonMenu.put("isparent", "false");
                }

                String jsonStr = jsonMenu.toString();
                jsonStr = jsonStr.replaceAll("territoryname", "name");
                jsonStr = jsonStr.replaceAll("territoryparentid", "pId");
                jsonStr = jsonStr.replaceAll("isparent", "isParent");
                childMenu.add(jsonStr);
            }
        }

        return childMenu;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(WTerritoryCategoryEntity wTerritoryCategory, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "区域分类中间表更新成功";
        WTerritoryCategoryEntity t = (WTerritoryCategoryEntity)this.wTerritoryCategoryService.get(WTerritoryCategoryEntity.class, wTerritoryCategory.getId());

        try {
            MyBeanUtils.copyBeanNotNull2Bean(wTerritoryCategory, t);
            this.wTerritoryCategoryService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var7) {
            var7.printStackTrace();
            message = "区域分类中间表更新失败";
            throw new BusinessException(var7.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(WTerritoryCategoryEntity wTerritoryCategory, HttpServletRequest req) {
        JSONArray categoryMenu = new JSONArray();
        List<WCategoryEntity> listCategoryTemp = this.systemService.loadAll(WCategoryEntity.class);
        Iterator var6 = listCategoryTemp.iterator();

        while(var6.hasNext()) {
            WCategoryEntity object = (WCategoryEntity)var6.next();
            JSONObject jsonMenu = JSONObject.fromObject(object);
            categoryMenu.add(jsonMenu);
        }

        req.setAttribute("categoryMenu", categoryMenu);
        return new ModelAndView("ssb/warmline/business/territoryandcategory/wTerritoryCategory-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(WTerritoryCategoryEntity wTerritoryCategory, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wTerritoryCategory.getId())) {
            wTerritoryCategory = (WTerritoryCategoryEntity)this.wTerritoryCategoryService.getEntity(WTerritoryCategoryEntity.class, wTerritoryCategory.getId());
            String domain = req.getServerName() + ":" + req.getServerPort();
            String picname = "http://" + domain + wTerritoryCategory.getCategoryImage();
            req.setAttribute("categoryImage", wTerritoryCategory.getCategoryImage());
            req.setAttribute("imgRootPath", picname);
            req.setAttribute("wTerritoryCategoryPage", wTerritoryCategory);
        }

        return new ModelAndView("ssb/warmline/business/territoryandcategory/wTerritoryCategory-update");
    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "wTerritoryCategoryController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(WTerritoryCategoryEntity wTerritoryCategory, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(WTerritoryCategoryEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wTerritoryCategory, request.getParameterMap());
        List<WTerritoryCategoryEntity> wTerritoryCategorys = this.wTerritoryCategoryService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "区域分类中间表");
        modelMap.put("entity", WTerritoryCategoryEntity.class);
        modelMap.put("params", new ExportParams("区域分类中间表列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", wTerritoryCategorys);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByT"}
    )
    public String exportXlsByT(WTerritoryCategoryEntity wTerritoryCategory, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "区域分类中间表");
        modelMap.put("entity", WTerritoryCategoryEntity.class);
        modelMap.put("params", new ExportParams("区域分类中间表列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
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
                List<WTerritoryCategoryEntity> listWTerritoryCategoryEntitys = ExcelImportUtil.importExcel(file.getInputStream(), WTerritoryCategoryEntity.class, params);
                Iterator var12 = listWTerritoryCategoryEntitys.iterator();

                while(var12.hasNext()) {
                    WTerritoryCategoryEntity wTerritoryCategory = (WTerritoryCategoryEntity)var12.next();
                    this.wTerritoryCategoryService.save(wTerritoryCategory);
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
    public List<WTerritoryCategoryEntity> list() {
        List<WTerritoryCategoryEntity> listWTerritoryCategorys = this.wTerritoryCategoryService.getList(WTerritoryCategoryEntity.class);
        return listWTerritoryCategorys;
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        WTerritoryCategoryEntity task = (WTerritoryCategoryEntity)this.wTerritoryCategoryService.get(WTerritoryCategoryEntity.class, id);
        return task == null?new ResponseEntity(HttpStatus.NOT_FOUND):new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(
            method = {RequestMethod.POST},
            consumes = {"application/json"}
    )
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody WTerritoryCategoryEntity wTerritoryCategory, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<WTerritoryCategoryEntity>> failures = this.validator.validate(wTerritoryCategory, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wTerritoryCategoryService.save(wTerritoryCategory);
            String id = wTerritoryCategory.getId();
            URI uri = uriBuilder.path("/rest/wTerritoryCategoryController/" + id).build().toUri();
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
    public ResponseEntity<?> update(@RequestBody WTerritoryCategoryEntity wTerritoryCategory) {
        Set<ConstraintViolation<WTerritoryCategoryEntity>> failures = this.validator.validate(wTerritoryCategory, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wTerritoryCategoryService.saveOrUpdate(wTerritoryCategory);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.DELETE}
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.wTerritoryCategoryService.deleteEntityById(WTerritoryCategoryEntity.class, id);
    }

    @RequestMapping(
            params = {"uploadPic"}
    )
    @ResponseBody
    public AjaxJson uploadPic(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        String path_temp = request.getSession().getServletContext().getRealPath("");
        String path = path_temp.substring(0, path_temp.lastIndexOf(separator));
        String imgPath = separator + "upload" + separator + "territory";
        String filePath = path + imgPath;
        File fileDir = new File(filePath);
        if(!fileDir.exists()) {
            fileDir.mkdirs();
        }

        File picTempFile = null;
        Iterator var13 = fileMap.entrySet().iterator();

        while(var13.hasNext()) {
            Entry<String, MultipartFile> entity = (Entry)var13.next();
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
                } catch (Exception var21) {
                    var21.printStackTrace();
                    j.setMsg("缩略图上传失败！");
                    j.setSuccess(false);
                }

                String domain = request.getServerName() + ":";
                String pathUrlName = "http://" + domain + "80";
                String picname = pathUrlName + "/upload/territory/" + picTempFile.getName();
                j.setObj(picname);
                Map nameMap = new HashMap();
                nameMap.put("name", "/upload/territory/" + picTempFile.getName());
                j.setAttributes(nameMap);
            }

            j.setMsg("缩略图上传成功！");
            j.setSuccess(true);
        }

        return j;
    }

    @RequestMapping(
            params = {"selectionClassIfication"}
    )
    public ModelAndView selectionClassIfication(HttpServletRequest request, String status) {
        HttpSession session = request.getSession();
        String territoryid = request.getParameter("territoryid");
        session.setAttribute("territoryid", territoryid);
        return new ModelAndView("ssb/warmline/business/territoryandcategory/selectionClassIfication");
    }

    @RequestMapping(
            params = {"datagrids"}
    )
    public void datagrids(WTerritoryCategoryEntity wTerritoryCategory, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WTerritoryCategoryEntity.class, dataGrid);
        HttpSession session = request.getSession();
        String territoryid = (String)session.getAttribute("territoryid");
        HqlGenerateUtil.installHql(cq, wTerritoryCategory, request.getParameterMap());

        try {
            cq.eq("territoryId", territoryid);
        } catch (Exception var9) {
            throw new BusinessException(var9.getMessage());
        }

        cq.add();
        this.wTerritoryCategoryService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"mysqlCategory"}
    )
    public void mysqlCategory() {
        String w_categorySql = " from WCategoryEntity where isParent='false'";
        String w_territorySql = "from WTerritoryBusiness where territorylevel='2'";
        List<WCategoryEntity> WcList = this.WCategoryService.findByQueryString(w_categorySql);
        List<WTerritoryBusiness> WbList = this.WTerritoryBusinessService.findByQueryString(w_territorySql);

        for(int b = 0; WbList.size() > b; ++b) {
            WTerritoryBusiness territoryBusiness = (WTerritoryBusiness)WbList.get(b);
            List<WTerritoryCategoryEntity> listTerritory = new ArrayList();
            if("2".equals(territoryBusiness.getId()) || "20".equals(territoryBusiness.getId())) {
                System.out.println("**************id*******" + territoryBusiness.getId());
            }

            if(!"2".equals(territoryBusiness.getId()) && !"20".equals(territoryBusiness.getId()) && !"2324".equals(territoryBusiness.getId()) && !"802".equals(territoryBusiness.getId())) {
                for(int a = 0; WcList.size() > a; ++a) {
                    WCategoryEntity category = (WCategoryEntity)WcList.get(a);
                    String uuid = UUIDUtil.getId();
                    WTerritoryCategoryEntity wc_entitys = new WTerritoryCategoryEntity();
                    wc_entitys.setCategoryId(category.getId());
                    wc_entitys.setCategoryName(category.getCategoryName());
                    wc_entitys.setId(uuid);
                    wc_entitys.setPrice(Double.valueOf(9999.0D));
                    wc_entitys.setTerritoryId(territoryBusiness.getId());
                    wc_entitys.setTerritoryName(territoryBusiness.getTerritoryname());
                    wc_entitys.setCategoryparentid(category.getCategoryParentid());
                    listTerritory.add(wc_entitys);
                }

                this.wTerritoryCategoryService.batchSave(listTerritory);
                listTerritory = null;
            } else {
                String w_territorySql_Son = "from WTerritoryBusiness where territoryparentid= '" + territoryBusiness.getId() + "'";
                List<WTerritoryBusiness> WbList_Son = this.WTerritoryBusinessService.findByQueryString(w_territorySql_Son);

                for(int k = 0; WbList_Son.size() > k; ++k) {
                    WTerritoryBusiness territory = (WTerritoryBusiness)WbList_Son.get(k);
                    listTerritory = null;
                    listTerritory = new ArrayList();

                    for(int i = 0; WcList.size() > i; ++i) {
                        WCategoryEntity category = (WCategoryEntity)WcList.get(i);
                        String uuid = UUIDUtil.getId();
                        WTerritoryCategoryEntity wc_entitys = new WTerritoryCategoryEntity();
                        wc_entitys.setCategoryId(category.getId());
                        wc_entitys.setCategoryName(category.getCategoryName());
                        wc_entitys.setId(uuid);
                        wc_entitys.setPrice(Double.valueOf(9999.0D));
                        wc_entitys.setTerritoryId(territory.getId());
                        wc_entitys.setTerritoryName(territory.getTerritoryname());
                        wc_entitys.setCategoryparentid(category.getCategoryParentid());
                        listTerritory.add(wc_entitys);
                    }

                    this.wTerritoryCategoryService.batchSave(listTerritory);
                }
            }
        }

    }

    @RequestMapping(
            params = {"mysqlOrder"}
    )
    public void mysqlOrder() {
        String w_territorySql = "from WTerritoryBusiness where territorylevel='2' and out_in='Thailand'";
        List<WTerritoryBusiness> WbList = this.WTerritoryBusinessService.findByQueryString(w_territorySql);
        List<WOrderEntity> listWorder = new ArrayList();

        for(int b = 0; WbList.size() > b; ++b) {
            WTerritoryBusiness territoryBusiness = (WTerritoryBusiness)WbList.get(b);
            if(!"2".equals(territoryBusiness.getId()) && !"20".equals(territoryBusiness.getId()) && !"2324".equals(territoryBusiness.getId()) && !"802".equals(territoryBusiness.getId())) {
                WOrderEntity wOrder = new WOrderEntity();
                wOrder.setId(UUID.randomUUID().toString());
                wOrder.setOrderNumber(OrderNumber.generateNumber3());
                wOrder.setTitle("普通订单说明—无论在生活中遇到任何需求，都可以在这里求助");
                wOrder.setDescription("在这里详细描述您的需求；然后根据实际情况，你可以将订单发到附近、同城或者异地、异国；选择相对应的分类，如果找不到相应分类可以选择其他；系统会给为您提供参考价格的最低价，根据这个参考，您可以适当提高价格；最后订单会发送到您选择的位置，基于LBS，在该地区的其他用户可以看到您的订单，并选择帮助您。");
                wOrder.setCategory("其他更多帮助");
                wOrder.setPhotos("2c9a517a5e03da12015e08931a3c0457");
                wOrder.setPrice("10.0");
                wOrder.setIssuer("小暖运营");
                wOrder.setPhone("18701403132");
                wOrder.setState("泰国");
                WTerritoryBusiness wt_lv2 = (WTerritoryBusiness)WbList.get(b);
                WTerritoryBusiness wt_lv1 = (WTerritoryBusiness)this.WTerritoryBusinessService.findUniqueByProperty(WTerritoryBusiness.class, "id", wt_lv2.getTerritoryparentid());
                wOrder.setCity(wt_lv1.getTerritoryname() + " " + wt_lv2.getTerritoryname());
                wOrder.setOrderStatus(Orderstatu.ORDERSTATU_001.toString());
                wOrder.setSeekStatus(Integer.toString(1));
                wOrder.setIssuerId("2c9a517a5e03da12015e084289db0438");
                wOrder.setOrderTime(new Date());
                wOrder.setStartTime(new Date());
                Date date = new Date();
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(2, 1);
                date = c.getTime();
                wOrder.setEndTime(date);
                wOrder.setBuyStatus(PayStatus.PAY_002.toString());
                wOrder.setPaymentMode("wallet");
                wOrder.setLatitude(wt_lv2.getXwgs84().toString());
                wOrder.setLongitude(wt_lv2.getYwgs84().toString());
                wOrder.setPaymentTime(new Date());
                wOrder.setTerritoryId(wt_lv2.getId());
                wOrder.setIsVirtualUser("yes");
                listWorder.add(wOrder);
            } else {
                String w_territorySql_Son = "from WTerritoryBusiness where territoryparentid= '" + territoryBusiness.getId() + "'";
                List<WTerritoryBusiness> WbList_Son = this.WTerritoryBusinessService.findByQueryString(w_territorySql_Son);

                for(int k = 0; WbList_Son.size() > k; ++k) {
                    WOrderEntity wOrder = new WOrderEntity();
                    wOrder.setId(UUID.randomUUID().toString());
                    wOrder.setOrderNumber(OrderNumber.generateNumber3());
                    wOrder.setTitle("普通订单说明—无论在生活中遇到任何需求，都可以在这里求助");
                    wOrder.setDescription("在这里详细描述您的需求；然后根据实际情况，你可以将订单发到附近、同城或者异地、异国；选择相对应的分类，如果找不到相应分类可以选择其他；系统会给为您提供参考价格的最低价，根据这个参考，您可以适当提高价格；最后订单会发送到您选择的位置，基于LBS，在该地区的其他用户可以看到您的订单，并选择帮助您。");
                    wOrder.setCategory("其他更多帮助");
                    wOrder.setPhotos("2c9a517a5e03da12015e08931a3c0457");
                    wOrder.setPrice("10.0");
                    wOrder.setIssuer("小暖运营");
                    wOrder.setPhone("18701403132");
                    wOrder.setState("泰国");
                    WTerritoryBusiness wt_lv2 = (WTerritoryBusiness)WbList_Son.get(k);
                    wOrder.setCity(territoryBusiness.getTerritoryname() + " " + wt_lv2.getTerritoryname());
                    wOrder.setOrderStatus(Orderstatu.ORDERSTATU_001.toString());
                    WTerritoryBusiness wt_lv1 = (WTerritoryBusiness)this.WTerritoryBusinessService.findUniqueByProperty(WTerritoryBusiness.class, "id", wt_lv2.getTerritoryparentid());
                    wOrder.setCity(wt_lv1.getTerritoryname() + "/t/t" + wt_lv2.getTerritoryname());
                    wOrder.setOrderStatus(Orderstatu.ORDERSTATU_001.toString());
                    wOrder.setSeekStatus(Integer.toString(0));
                    wOrder.setIssuerId("2c9a517a5e03da12015e084289db0438");
                    wOrder.setOrderTime(new Date());
                    wOrder.setStartTime(new Date());
                    Date date = new Date();
                    Calendar c = Calendar.getInstance();
                    c.setTime(date);
                    c.add(2, 1);
                    date = c.getTime();
                    wOrder.setEndTime(date);
                    wOrder.setBuyStatus(PayStatus.PAY_002.toString());
                    wOrder.setPaymentMode("wallet");
                    wOrder.setBuyStatus(PayStatus.PAY_002.toString());
                    wOrder.setLatitude(wt_lv2.getXwgs84().toString());
                    wOrder.setLongitude(wt_lv2.getYwgs84().toString());
                    wOrder.setPaymentTime(new Date());
                    wOrder.setTerritoryId(wt_lv2.getId());
                    wOrder.setIsVirtualUser("yes");
                    listWorder.add(wOrder);
                }
            }
        }

        this.wOrderService.batchSave(listWorder);
        listWorder.clear();
    }
}
