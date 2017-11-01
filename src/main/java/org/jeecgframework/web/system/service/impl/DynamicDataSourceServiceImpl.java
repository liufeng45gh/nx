//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.system.service.impl;

import java.util.Iterator;
import java.util.List;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.DynamicDataSourceEntity;
import org.jeecgframework.web.system.service.DynamicDataSourceServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("dynamicDataSourceService")
@Transactional
public class DynamicDataSourceServiceImpl extends CommonServiceImpl implements DynamicDataSourceServiceI {
    public DynamicDataSourceServiceImpl() {
    }

    public List<DynamicDataSourceEntity> initDynamicDataSource() {
        ResourceUtil.dynamicDataSourceMap.clear();
        List<DynamicDataSourceEntity> dynamicSourceEntityList = this.commonDao.loadAll(DynamicDataSourceEntity.class);
        Iterator var3 = dynamicSourceEntityList.iterator();

        while(var3.hasNext()) {
            DynamicDataSourceEntity dynamicSourceEntity = (DynamicDataSourceEntity)var3.next();
            ResourceUtil.dynamicDataSourceMap.put(dynamicSourceEntity.getDbKey(), dynamicSourceEntity);
        }

        return dynamicSourceEntityList;
    }

    public static DynamicDataSourceEntity getDbSourceEntityByKey(String dbKey) {
        DynamicDataSourceEntity dynamicDataSourceEntity = (DynamicDataSourceEntity)ResourceUtil.dynamicDataSourceMap.get(dbKey);
        return dynamicDataSourceEntity;
    }

    public void refleshCache() {
        this.initDynamicDataSource();
    }

    public DynamicDataSourceEntity getDynamicDataSourceEntityForDbKey(String dbKey) {
        List<DynamicDataSourceEntity> dynamicDataSourceEntitys = this.commonDao.findHql("from DynamicDataSourceEntity where dbKey = ?", new Object[]{dbKey});
        return dynamicDataSourceEntitys.size() > 0?(DynamicDataSourceEntity)dynamicDataSourceEntitys.get(0):null;
    }
}
