//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.demo.service.impl.test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.web.demo.entity.test.TFinanceEntity;
import org.jeecgframework.web.demo.entity.test.TFinanceFilesEntity;
import org.jeecgframework.web.demo.service.test.TFinanceServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tFinanceService")
@Transactional
public class TFinanceServiceImpl extends CommonServiceImpl implements TFinanceServiceI {
    public TFinanceServiceImpl() {
    }

    public void deleteFile(TFinanceFilesEntity file) {
        String sql = "select * from t_s_attachment where id = ?";
        Map<String, Object> attachmentMap = this.commonDao.findOneForJdbc(sql, new Object[]{file.getId()});
        String realpath = (String)attachmentMap.get("realpath");
        String fileName = FileUtils.getFilePrefix2(realpath);
        String realPath = ContextHolderUtils.getSession().getServletContext().getRealPath("/");
        FileUtils.delete(realPath + realpath);
        FileUtils.delete(realPath + fileName + ".pdf");
        FileUtils.delete(realPath + fileName + ".swf");
        this.commonDao.delete(file);
    }

    public void deleteFinance(TFinanceEntity finance) {
        String attach_sql = "select * from t_s_attachment where id in (select id from t_finance_files where financeId = ?)";
        List<Map<String, Object>> attachmentList = this.commonDao.findForJdbc(attach_sql, new Object[]{finance.getId()});
        Iterator var5 = attachmentList.iterator();

        while(var5.hasNext()) {
            Map<String, Object> map = (Map)var5.next();
            String realpath = (String)map.get("realpath");
            String fileName = FileUtils.getFilePrefix2(realpath);
            String realPath = ContextHolderUtils.getSession().getServletContext().getRealPath("/");
            FileUtils.delete(realPath + realpath);
            FileUtils.delete(realPath + fileName + ".pdf");
            FileUtils.delete(realPath + fileName + ".swf");
        }

        this.commonDao.delete(finance);
    }
}
