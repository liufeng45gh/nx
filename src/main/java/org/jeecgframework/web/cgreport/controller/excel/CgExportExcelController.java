//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgreport.controller.excel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.DynamicDBUtil;
import org.jeecgframework.core.util.SqlUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;
import org.jeecgframework.web.cgreport.exception.CgReportNotFoundException;
import org.jeecgframework.web.cgreport.service.core.CgReportServiceI;
import org.jeecgframework.web.cgreport.util.CgReportQueryParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/cgExportExcelController"})
public class CgExportExcelController extends BaseController {
    @Autowired
    private CgReportServiceI cgReportService;

    public CgExportExcelController() {
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String codedFileName = "报表";
        String sheetName = "导出信息";
        if(!StringUtil.isNotEmpty(request.getParameter("configId"))) {
            throw new BusinessException("参数错误");
        } else {
            String configId = request.getParameter("configId");
            Map cgReportMap = null;

            try {
                cgReportMap = this.cgReportService.queryCgReportConfig(configId);
            } catch (Exception var17) {
                throw new CgReportNotFoundException("动态报表配置不存在!");
            }

            List<Map<String, Object>> fieldList = (List)cgReportMap.get("items");
            Map configM = (Map)cgReportMap.get("main");
            codedFileName = configM.get("name") + codedFileName;
            String querySql = (String)configM.get("cgreport_sql");
            List<Map<String, Object>> items = (List)cgReportMap.get("items");
            Map queryparams = new LinkedHashMap();
            Iterator result = items.iterator();

            while(result.hasNext()) {
                Map<String, Object> item = (Map)result.next();
                String isQuery = (String)item.get("search_flag");
                if("Y".equalsIgnoreCase(isQuery)) {
                    CgReportQueryParamUtil.loadQueryParams(request, item, queryparams);
                }
            }

            String dbKey = (String)configM.get("db_source");
            result = null;
            List resultList;
            if(StringUtils.isNotBlank(dbKey)) {
                resultList = DynamicDBUtil.findList(dbKey, SqlUtil.getFullSql(querySql, queryparams), new Object[0]);
            } else {
                resultList = this.cgReportService.queryByCgReportSql(querySql, queryparams, -1, -1);
            }

            List<ExcelExportEntity> entityList = new ArrayList();

            for(int i = 0; i < fieldList.size(); ++i) {
                entityList.add(new ExcelExportEntity(((Map)fieldList.get(i)).get("field_txt").toString(), ((Map)fieldList.get(i)).get("field_name")));
            }

            modelMap.put("data", entityList);
            modelMap.put("mapList", result);
            modelMap.put("fileName", codedFileName);
            modelMap.put("params", new ExportParams((String)null, sheetName));
            return "jeecgMapExcelView";
        }
    }
}
