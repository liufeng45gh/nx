//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.controller.excel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.UUIDGenerator;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;
import org.jeecgframework.poi.handler.impl.ExcelDataHandlerDefaultImpl;
import org.jeecgframework.poi.util.PoiPublicUtil;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.jeecgframework.web.cgform.service.autolist.CgTableServiceI;
import org.jeecgframework.web.cgform.service.autolist.ConfigServiceI;
import org.jeecgframework.web.cgform.service.build.DataBaseService;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.jeecgframework.web.cgform.service.impl.config.util.FieldNumComparator;
import org.jeecgframework.web.cgform.util.QueryParamUtil;
import org.jeecgframework.web.system.pojo.base.DictEntity;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/excelTempletController"})
public class ExcelTempletController extends BaseController {
    private static final Logger logger = Logger.getLogger(ExcelTempletController.class);
    @Autowired
    private ConfigServiceI configService;
    @Autowired
    private CgFormFieldServiceI cgFormFieldService;
    @Autowired
    private DataBaseService dataBaseService;
    @Autowired
    private CgTableServiceI cgTableService;
    @Autowired
    private SystemService systemService;

    public ExcelTempletController() {
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(HttpServletRequest request, ModelMap modelMap, HttpServletResponse response, String field, DataGrid dataGrid) {
        String codedFileName = "文件";
        String sheetName = "导出信息";
        List<CgFormFieldEntity> lists = null;
        if(!StringUtil.isNotEmpty(request.getParameter("tableName"))) {
            throw new BusinessException("参数错误");
        } else {
            String configId = request.getParameter("tableName");
            String jversion = this.cgFormFieldService.getCgFormVersionByTableName(configId);
            Map<String, Object> configs = this.configService.queryConfigs(configId, jversion);
            Map params = new HashMap();
            List<CgFormFieldEntity> beans = (List)configs.get("fileds");
            Iterator var15 = beans.iterator();

            while(var15.hasNext()) {
                CgFormFieldEntity b = (CgFormFieldEntity)var15.next();
                QueryParamUtil.loadQueryParams(request, b, params);
            }

            List<Map<String, Object>> result = this.cgTableService.querySingle(configId, field.toString(), params, (String)null, (String)null, 1, 99999);
            lists = (List)configs.get("fileds");

            for(int i = lists.size() - 1; i >= 0; --i) {
                if(!field.contains(((CgFormFieldEntity)lists.get(i)).getFieldName())) {
                    lists.remove(i);
                }
            }

            this.handlePageDic(beans, result);
            this.dealDic(result, beans);
            sheetName = (String)configs.get("config_name");
            String tableName = (String)configs.get("tableName");
            codedFileName = sheetName + "_" + tableName + "-v" + (String)configs.get("jformVersion");
            List<ExcelExportEntity> entityList = this.convertToExportEntity(lists);
            if(2 == Integer.parseInt(configs.get("tableType").toString())) {
                String subTableStr = configs.get("subTables").toString();
                if(StringUtils.isNotEmpty(subTableStr)) {
                    String[] subTables = subTableStr.split(",");
                    String[] var22 = subTables;
                    int var21 = subTables.length;

                    for(int var20 = 0; var20 < var21; ++var20) {
                        String subTable = var22[var20];
                        this.addAllSubTableDate(subTable, configs, result, entityList);
                    }
                }
            }

            modelMap.put("data", entityList);
            modelMap.put("mapList", result);
            modelMap.put("fileName", codedFileName);
            modelMap.put("params", new ExportParams((String)null, sheetName));
            return "jeecgMapExcelView";
        }
    }

    private List<ExcelExportEntity> convertToExportEntity(List<CgFormFieldEntity> lists) {
        Collections.sort(lists, new FieldNumComparator());
        List<ExcelExportEntity> entityList = new ArrayList();

        for(int i = 0; i < lists.size(); ++i) {
            if(((CgFormFieldEntity)lists.get(i)).getIsShow().equals("Y")) {
                ExcelExportEntity entity = new ExcelExportEntity(((CgFormFieldEntity)lists.get(i)).getContent(), ((CgFormFieldEntity)lists.get(i)).getFieldName());
                int columnWidth = ((CgFormFieldEntity)lists.get(i)).getLength().intValue() == 0?12:(((CgFormFieldEntity)lists.get(i)).getLength().intValue() > 30?30:((CgFormFieldEntity)lists.get(i)).getLength().intValue());
                if(((CgFormFieldEntity)lists.get(i)).getShowType().equals("date")) {
                    entity.setFormat("yyyy-MM-dd");
                } else if(((CgFormFieldEntity)lists.get(i)).getShowType().equals("datetime")) {
                    entity.setFormat("yyyy-MM-dd HH:mm:ss");
                }

                entity.setWidth((double)columnWidth);
                entityList.add(entity);
            }
        }

        return entityList;
    }

    private void addAllSubTableDate(String subTable, Map<String, Object> configs, List<Map<String, Object>> result, List<ExcelExportEntity> entityList) {
        String jversion = this.cgFormFieldService.getCgFormVersionByTableName(subTable);
        Map<String, Object> subConfigs = this.configService.queryConfigs(subTable, jversion);
        ExcelExportEntity tableEntity = new ExcelExportEntity(subConfigs.get("config_name").toString(), subTable);
        List<CgFormFieldEntity> beans = (List)subConfigs.get("fileds");
        tableEntity.setList(this.convertToExportEntity(beans));
        entityList.add(tableEntity);

        for(int i = 0; i < result.size(); ++i) {
            List<Map<String, Object>> subResult = this.cgFormFieldService.getSubTableData(configs.get("config_id").toString(), subTable, ((Map)result.get(i)).get("id"));
            this.handlePageDic(beans, subResult);
            this.dealDic(subResult, beans);
            ((Map)result.get(i)).put(subTable, subResult);
        }

    }

    private void handlePageDic(List<CgFormFieldEntity> beans, List<Map<String, Object>> result) {
        Map<String, Object> dicMap = new HashMap();
        Iterator var5 = beans.iterator();

        label53:
        while(true) {
            CgFormFieldEntity b;
            List dicList;
            do {
                if(!var5.hasNext()) {
                    return;
                }

                b = (CgFormFieldEntity)var5.next();
                this.loadDic(dicMap, b);
                dicList = (List)dicMap.get("field_dictlist");
            } while(dicList.size() <= 0);

            Iterator var8 = result.iterator();

            while(true) {
                Map resultMap;
                StringBuffer sb;
                String[] arrayVal;
                do {
                    String value;
                    do {
                        if(!var8.hasNext()) {
                            continue label53;
                        }

                        resultMap = (Map)var8.next();
                        sb = new StringBuffer();
                        value = (String)resultMap.get(b.getFieldName());
                    } while(oConvertUtils.isEmpty(value));

                    arrayVal = value.split(",");
                } while(arrayVal.length <= 1);

                String[] var15 = arrayVal;
                int var14 = arrayVal.length;

                for(int var13 = 0; var13 < var14; ++var13) {
                    String val = var15[var13];
                    Iterator var17 = dicList.iterator();

                    while(var17.hasNext()) {
                        DictEntity dictEntity = (DictEntity)var17.next();
                        if(val.equals(dictEntity.getTypecode())) {
                            sb.append(dictEntity.getTypename());
                            sb.append(",");
                        }
                    }
                }

                resultMap.put(b.getFieldName(), sb.toString().substring(0, sb.toString().length() - 1));
            }
        }
    }

    @RequestMapping(
            params = {"goImplXls"},
            method = {RequestMethod.GET}
    )
    public ModelAndView goImplXls(HttpServletRequest request) {
        request.setAttribute("tableName", request.getParameter("tableName"));
        return new ModelAndView("jeecg/cgform/excel/upload");
    }

    @RequestMapping(
            params = {"importExcel"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
        String message = "上传成功";
        AjaxJson j = new AjaxJson();
        String configId = request.getParameter("tableName");
        String jversion = this.cgFormFieldService.getCgFormVersionByTableName(configId);
        Map<String, Object> configs = this.configService.queryConfigs(configId, jversion);
        String version = (String)configs.get("jformVersion");
        List<CgFormFieldEntity> lists = (List)configs.get("fileds");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        Iterator var13 = fileMap.entrySet().iterator();

        while(true) {
            while(var13.hasNext()) {
                Entry<String, MultipartFile> entity = (Entry)var13.next();
                MultipartFile file = (MultipartFile)entity.getValue();
                String docVersion = getDocVersion(file.getOriginalFilename());
                if(docVersion.equals(version)) {
                    try {
                        ImportParams params = new ImportParams();
                        params.setDataHanlder(new ExcelTempletController.CgFormExcelHandler(lists));
                        List<Map<String, Object>> listDate = ExcelImportUtil.importExcel(file.getInputStream(), Map.class, params);
                        if(listDate == null) {
                            message = "识别模版数据错误";
                            logger.error(message);
                        } else {
                            Iterator var19 = listDate.iterator();

                            while(var19.hasNext()) {
                                Map<String, Object> map = (Map)var19.next();
                                map.put("id", UUIDGenerator.generate());
                                this.dataBaseService.insertTable(configId, map);
                            }

                            message = "文件导入成功！";
                        }
                    } catch (Exception var20) {
                        message = "文件导入失败！";
                        logger.error(ExceptionUtil.getExceptionMessage(var20));
                    }
                } else {
                    message = "模版文件版本和表达不匹配，请重新下载模版";
                    logger.error(message);
                }
            }

            j.setMsg(message);
            return j;
        }
    }

    private static String getDocVersion(String docName) {
        return docName.indexOf("(") > 0?docName.substring(docName.indexOf("-v") + 2, docName.indexOf("(")).trim():docName.substring(docName.indexOf("-v") + 2, docName.indexOf(".")).trim();
    }

    private void loadDic(Map m, CgFormFieldEntity bean) {
        String dicT = bean.getDictTable();
        String dicF = bean.getDictField();
        String dicText = bean.getDictText();
        if(StringUtil.isEmpty(dicT) && StringUtil.isEmpty(dicF)) {
            m.put("field_dictlist", new ArrayList(0));
        } else {
            if(!bean.getShowType().equals("popup")) {
                List<DictEntity> dicDatas = this.queryDic(dicT, dicF, dicText);
                m.put("field_dictlist", dicDatas);
            } else {
                m.put("field_dictlist", new ArrayList(0));
            }

        }
    }

    private List<DictEntity> queryDic(String dicTable, String dicCode, String dicText) {
        return this.systemService.queryDict(dicTable, dicCode, dicText);
    }

    private void dealDic(List<Map<String, Object>> result, List<CgFormFieldEntity> beans) {
        Iterator var4 = beans.iterator();

        while(true) {
            CgFormFieldEntity bean;
            String dicTable;
            String dicCode;
            String dicText;
            do {
                do {
                    if(!var4.hasNext()) {
                        return;
                    }

                    bean = (CgFormFieldEntity)var4.next();
                    dicTable = bean.getDictTable();
                    dicCode = bean.getDictField();
                    dicText = bean.getDictText();
                } while(StringUtil.isEmpty(dicTable) && StringUtil.isEmpty(dicCode));
            } while(bean.getShowType().equals("popup"));

            List<DictEntity> dicDataList = this.queryDic(dicTable, dicCode, dicText);
            Iterator var10 = result.iterator();

            while(var10.hasNext()) {
                Map r = (Map)var10.next();
                String value = String.valueOf(r.get(bean.getFieldName()));
                Iterator var13 = dicDataList.iterator();

                while(var13.hasNext()) {
                    DictEntity dictEntity = (DictEntity)var13.next();
                    if(value.equalsIgnoreCase(dictEntity.getTypecode())) {
                        r.put(bean.getFieldName(), MutiLangUtil.getMutiLangInstance().getLang(dictEntity.getTypename()));
                    }
                }
            }
        }
    }

    private class CgFormExcelHandler extends ExcelDataHandlerDefaultImpl {
        Map<String, CgFormFieldEntity> fieldMap;

        public CgFormExcelHandler(List<CgFormFieldEntity> var1) {
            this.fieldMap = this.convertDate(var1);
        }

        private Map<String, CgFormFieldEntity> convertDate(List<CgFormFieldEntity> lists) {
            Map<String, CgFormFieldEntity> maps = new HashMap();
            Iterator var4 = lists.iterator();

            while(var4.hasNext()) {
                CgFormFieldEntity cgFormFieldEntity = (CgFormFieldEntity)var4.next();
                maps.put(cgFormFieldEntity.getContent(), cgFormFieldEntity);
            }

            return maps;
        }

        public void setMapValue(Map<String, Object> map, String originKey, Object value) {
            if(value instanceof Double) {
                map.put(this.getRealKey(originKey), PoiPublicUtil.doubleToString((Double)value));
            } else {
                map.put(this.getRealKey(originKey), value.toString());
            }

        }

        private String getRealKey(String originKey) {
            return this.fieldMap.containsKey(originKey)?((CgFormFieldEntity)this.fieldMap.get(originKey)).getFieldName():originKey;
        }
    }
}
