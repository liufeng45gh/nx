//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.demo.service.impl.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.jeecgframework.core.common.dao.impl.CommonDao;
import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.web.demo.dao.test.JeecgMinidaoDao;
import org.jeecgframework.web.demo.entity.test.JeecgDemo;
import org.jeecgframework.web.demo.entity.test.JeecgMinidaoEntity;
import org.jeecgframework.web.demo.entity.test.JeecgOrderCustomEntity;
import org.jeecgframework.web.demo.service.test.TransactionTestServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("transactionTestService")
@Transactional(
        rollbackFor = {Exception.class}
)
public class TransactionTestServiceImpl implements TransactionTestServiceI {
    @Autowired
    private JeecgMinidaoDao jeecgMinidaoDao;
    @Autowired
    private CommonDao commonDao;
    @Autowired
    private JdbcDao jdbcDao;

    public TransactionTestServiceImpl() {
    }

    public Map<String, Integer> getCounts() {
        Map<String, Integer> counts = new HashMap();
        counts.put("minidao", this.jeecgMinidaoDao.getCount());
        counts.put("jdbc", Integer.valueOf((int)this.jdbcDao.findForLong("select count(*) from jeecg_demo", new HashMap())));
        Long count = this.commonDao.getCountForJdbc("select count(*) from jeecg_order_custom");
        if(count != null) {
            counts.put("hibernate", Integer.valueOf(count.intValue()));
        } else {
            counts.put("hibernate", Integer.valueOf(0));
        }

        return counts;
    }

    public Map<String, Integer> insertData(JeecgMinidaoEntity entity, JeecgDemo demo, JeecgOrderCustomEntity customEntity, boolean rollback) throws Exception {
        Map<String, Integer> counts = new HashMap();
        entity.setUserName("test");
        counts.put("minidao", Integer.valueOf(1));
        demo.setId(String.valueOf((new Date()).getTime()));
        counts.put("jdbc", Integer.valueOf(this.jdbcDao.executeForObject("insert into jeecg_demo (ID,MOBILE_PHONE,OFFICE_PHONE,EMAIL,AGE,USER_NAME) values(:id,:mobilePhone,:officePhone,:email,:age,'test')", demo)));
        counts.put("hibernate", Integer.valueOf(this.commonDao.save(customEntity) == null?0:1));
        if(rollback) {
            throw new RuntimeException();
        } else {
            return counts;
        }
    }
}
