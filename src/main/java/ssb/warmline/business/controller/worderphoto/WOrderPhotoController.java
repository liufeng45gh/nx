//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.controller.worderphoto;

import java.io.File;
import java.io.FileOutputStream;
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
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import ssb.warmline.business.commons.config.ResponseObject;
import ssb.warmline.business.commons.enums.PhotoType;
import ssb.warmline.business.commons.utils.CommonUtils;
import ssb.warmline.business.commons.utils.DateUtils;
import ssb.warmline.business.entity.worderphoto.WOrderPhotoEntity;
import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
import ssb.warmline.business.service.worder.WOrderServiceI;
import ssb.warmline.business.service.worderphoto.WOrderPhotoServiceI;
import ssb.warmline.business.service.worderphotomain.WOrderPhotoMainServiceI;
import ssb.warmline.business.service.worderrecord.WOrderRecordServiceI;

@Controller
@RequestMapping({"/wOrderPhotoController"})
public class WOrderPhotoController extends BaseController {
    private static final Logger logger = Logger.getLogger(WOrderPhotoController.class);
    static final String separator;
    @Autowired
    private WOrderPhotoServiceI wOrderPhotoService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;
    @Autowired
    private WOrderPhotoMainServiceI wOrderPhotoMainService;
    @Autowired
    private TSBaseUserServiceI tsBaseUserServiceI;
    @Autowired
    private WOrderRecordServiceI orderRecordService;
    @Autowired
    private WOrderServiceI wOrderService;

    static {
        separator = File.separator;
    }

    public WOrderPhotoController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("ssb/warmline/business/worderphoto/wOrderPhotoList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(WOrderPhotoEntity wOrderPhoto, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WOrderPhotoEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wOrderPhoto, request.getParameterMap());
        cq.add();
        this.wOrderPhotoService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(WOrderPhotoEntity wOrderPhoto, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        wOrderPhoto = (WOrderPhotoEntity)this.systemService.getEntity(WOrderPhotoEntity.class, wOrderPhoto.getId());
        message = "w_order_photo删除成功";

        try {
            this.wOrderPhotoService.delete(wOrderPhoto);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "w_order_photo删除失败";
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
        message = "w_order_photo删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                WOrderPhotoEntity wOrderPhoto = (WOrderPhotoEntity)this.systemService.getEntity(WOrderPhotoEntity.class, id);
                this.wOrderPhotoService.delete(wOrderPhoto);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "w_order_photo删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(WOrderPhotoEntity wOrderPhoto, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "w_order_photo添加成功";

        try {
            this.wOrderPhotoService.save(wOrderPhoto);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "w_order_photo添加失败";
            throw new BusinessException(var6.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(WOrderPhotoEntity wOrderPhoto, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "w_order_photo更新成功";
        WOrderPhotoEntity t = (WOrderPhotoEntity)this.wOrderPhotoService.get(WOrderPhotoEntity.class, wOrderPhoto.getId());

        try {
            MyBeanUtils.copyBeanNotNull2Bean(wOrderPhoto, t);
            this.wOrderPhotoService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var7) {
            var7.printStackTrace();
            message = "w_order_photo更新失败";
            throw new BusinessException(var7.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(WOrderPhotoEntity wOrderPhoto, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wOrderPhoto.getId())) {
            wOrderPhoto = (WOrderPhotoEntity)this.wOrderPhotoService.getEntity(WOrderPhotoEntity.class, wOrderPhoto.getId());
            req.setAttribute("wOrderPhotoPage", wOrderPhoto);
        }

        return new ModelAndView("ssb/warmline/business/worderphoto/wOrderPhoto-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(WOrderPhotoEntity wOrderPhoto, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(wOrderPhoto.getId())) {
            wOrderPhoto = (WOrderPhotoEntity)this.wOrderPhotoService.getEntity(WOrderPhotoEntity.class, wOrderPhoto.getId());
            req.setAttribute("wOrderPhotoPage", wOrderPhoto);
        }

        return new ModelAndView("ssb/warmline/business/worderphoto/wOrderPhoto-update");
    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "wOrderPhotoController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(WOrderPhotoEntity wOrderPhoto, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(WOrderPhotoEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, wOrderPhoto, request.getParameterMap());
        List<WOrderPhotoEntity> wOrderPhotos = this.wOrderPhotoService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "w_order_photo");
        modelMap.put("entity", WOrderPhotoEntity.class);
        modelMap.put("params", new ExportParams("w_order_photo列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", wOrderPhotos);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByT"}
    )
    public String exportXlsByT(WOrderPhotoEntity wOrderPhoto, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "w_order_photo");
        modelMap.put("entity", WOrderPhotoEntity.class);
        modelMap.put("params", new ExportParams("w_order_photo列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
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
                List<WOrderPhotoEntity> listWOrderPhotoEntitys = ExcelImportUtil.importExcel(file.getInputStream(), WOrderPhotoEntity.class, params);
                Iterator var12 = listWOrderPhotoEntitys.iterator();

                while(var12.hasNext()) {
                    WOrderPhotoEntity wOrderPhoto = (WOrderPhotoEntity)var12.next();
                    this.wOrderPhotoService.save(wOrderPhoto);
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
    public List<WOrderPhotoEntity> list() {
        List<WOrderPhotoEntity> listWOrderPhotos = this.wOrderPhotoService.getList(WOrderPhotoEntity.class);
        return listWOrderPhotos;
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        WOrderPhotoEntity task = (WOrderPhotoEntity)this.wOrderPhotoService.get(WOrderPhotoEntity.class, id);
        return task == null?new ResponseEntity(HttpStatus.NOT_FOUND):new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(
            method = {RequestMethod.POST},
            consumes = {"application/json"}
    )
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody WOrderPhotoEntity wOrderPhoto, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<WOrderPhotoEntity>> failures = this.validator.validate(wOrderPhoto, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wOrderPhotoService.save(wOrderPhoto);
            String id = wOrderPhoto.getId();
            URI uri = uriBuilder.path("/rest/wOrderPhotoController/" + id).build().toUri();
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
    public ResponseEntity<?> update(@RequestBody WOrderPhotoEntity wOrderPhoto) {
        Set<ConstraintViolation<WOrderPhotoEntity>> failures = this.validator.validate(wOrderPhoto, new Class[0]);
        if(!failures.isEmpty()) {
            return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
        } else {
            this.wOrderPhotoService.saveOrUpdate(wOrderPhoto);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.DELETE}
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        this.wOrderPhotoService.deleteEntityById(WOrderPhotoEntity.class, id);
    }

    @RequestMapping({"/urgentOrderPhoto"})
    public ResponseObject urgentOrderPhoto(String uid, String token, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HashMap jsonMap = new HashMap();

        try {
            if(uid != null && !"".equals(uid)) {
                String fileName_temp = request.getParameter("fileName");
                int start = fileName_temp.indexOf(".");
                String fileFormat = fileName_temp.substring(start - 1);
                ServletInputStream bufferIn = request.getInputStream();
                String path_temp = request.getSession().getServletContext().getRealPath("");
                String path = path_temp.substring(0, path_temp.lastIndexOf("\\"));
                String imgPath = separator + "upload" + separator + "orderPhoto";
                String filePath = path + imgPath;
                String fileName = DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(8) + "." + fileFormat;
                File file = new File(filePath, fileName);
                byte[] buffer = new byte[1024];
                FileOutputStream out = new FileOutputStream(file);

                for(int len = bufferIn.read(buffer, 0, 1024); len != -1; len = bufferIn.read(buffer, 0, 1024)) {
                    out.write(buffer, 0, len);
                }

                out.close();
                bufferIn.close();
                WOrderPhotoEntity wOrderPhotoEntity = new WOrderPhotoEntity();
                wOrderPhotoEntity.setId(UUID.randomUUID().toString());
                wOrderPhotoEntity.setCreateTime(new Date());
                wOrderPhotoEntity.setPhotoUrl("/upload/orderPhoto/" + fileName);
                wOrderPhotoEntity.setPhotoName(fileName);
                wOrderPhotoEntity.setUid(uid);
                wOrderPhotoEntity.setPhotoType(PhotoType.PHOTOTYPE_001.getOrderStatu());
                jsonMap.put("paths", "/upload/orderPhoto/" + fileName);
                this.wOrderPhotoService.save(wOrderPhotoEntity);
                jsonMap.put("photoId", wOrderPhotoEntity.getId());
                return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
            } else {
                return CommonUtils.repsonseOjbectToClientWithBody("20027", (Object)null, new String[0]);
            }
        } catch (Exception var20) {
            var20.printStackTrace();
            return CommonUtils.repsonseOjbectToClientWithBody("20022", (Object)null, new String[0]);
        }
    }
}
