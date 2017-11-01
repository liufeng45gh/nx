//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.common.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.interceptors.DateConvertEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/baseController"})
public class BaseController {
    public BaseController() {
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateConvertEditor());
    }

    public List<?> pageBaseMethod(HttpServletRequest request, DetachedCriteria dc, CommonService commonService, int pageRow) {
        //int currentPage = true;
        //int totalRow = false;
        //int totalPage = false;
        String str_currentPage = request.getParameter("str_currentPage");
        int currentPage = str_currentPage != null && !"".equals(str_currentPage)?Integer.parseInt(str_currentPage):1;
        String str_pageRow = request.getParameter("str_pageRow");
        pageRow = str_pageRow != null && !"".equals(str_pageRow)?Integer.parseInt(str_pageRow):pageRow;
        dc.setProjection(Projections.rowCount());
        int totalRow = Integer.parseInt(commonService.findByDetached(dc).get(0).toString());
        int totalPage = (totalRow + pageRow - 1) / pageRow;
        currentPage = currentPage < 1?1:currentPage;
        currentPage = currentPage > totalPage?totalPage:currentPage;
        dc.setProjection((Projection)null);
        List<?> list = commonService.pageList(dc, (currentPage - 1) * pageRow, pageRow);
        request.setAttribute("currentPage", Integer.valueOf(currentPage));
        request.setAttribute("pageRow", Integer.valueOf(pageRow));
        request.setAttribute("totalRow", Integer.valueOf(totalRow));
        request.setAttribute("totalPage", Integer.valueOf(totalPage));
        return list;
    }

    protected List<String> extractIdListByComma(String ids) {
        List<String> result = new ArrayList();
        if(StringUtils.hasText(ids)) {
            String[] var6;
            int var5 = (var6 = ids.split(",")).length;

            for(int var4 = 0; var4 < var5; ++var4) {
                String id = var6[var4];
                if(StringUtils.hasLength(id)) {
                    result.add(id.trim());
                }
            }
        }

        return result;
    }
}
