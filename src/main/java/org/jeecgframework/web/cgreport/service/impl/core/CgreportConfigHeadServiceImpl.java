//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgreport.service.impl.core;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgreport.entity.core.CgreportConfigHeadEntity;
import org.jeecgframework.web.cgreport.entity.core.CgreportConfigItemEntity;
import org.jeecgframework.web.cgreport.entity.core.CgreportConfigParamEntity;
import org.jeecgframework.web.cgreport.service.core.CgreportConfigHeadServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("cgreportConfigHeadService")
@Transactional
public class CgreportConfigHeadServiceImpl extends CommonServiceImpl implements CgreportConfigHeadServiceI {
    public CgreportConfigHeadServiceImpl() {
    }

    public <T> void delete(T entity) {
        super.delete(entity);
        this.doDelSql((CgreportConfigHeadEntity)entity);
    }

    public void addMain(CgreportConfigHeadEntity cgreportConfigHead, List<CgreportConfigItemEntity> cgreportConfigItemList, List<CgreportConfigParamEntity> cgreportConfigParamList) {
        this.save(cgreportConfigHead);
        Iterator var5 = cgreportConfigItemList.iterator();

        while(var5.hasNext()) {
            CgreportConfigItemEntity cgreportConfigItem = (CgreportConfigItemEntity)var5.next();
            cgreportConfigItem.setCgrheadId(cgreportConfigHead.getId());
            this.save(cgreportConfigItem);
        }

        var5 = cgreportConfigParamList.iterator();

        while(var5.hasNext()) {
            CgreportConfigParamEntity cgreportConfigParam = (CgreportConfigParamEntity)var5.next();
            cgreportConfigParam.setCgrheadId(cgreportConfigHead.getId());
            this.save(cgreportConfigParam);
        }

        this.doAddSql(cgreportConfigHead);
    }

    public void updateMain(CgreportConfigHeadEntity cgreportConfigHead, List<CgreportConfigItemEntity> cgreportConfigItemList, List<CgreportConfigParamEntity> cgreportConfigParamList) {
        this.saveOrUpdate(cgreportConfigHead);
        Object id0 = cgreportConfigHead.getId();
        String hql0 = "from CgreportConfigItemEntity where 1 = 1 AND cgrheadId = ? ";
        List<CgreportConfigItemEntity> cgreportConfigItemOldList = this.findHql(hql0, new Object[]{id0});
        Iterator var8 = cgreportConfigItemOldList.iterator();
        Iterator var20;
        {
            CgreportConfigItemEntity oldE;
            while (var8.hasNext()) {
                oldE = (CgreportConfigItemEntity) var8.next();
                boolean isUpdate = false;
                Iterator var11 = cgreportConfigItemList.iterator();

                while (var11.hasNext()) {
                    CgreportConfigItemEntity sendE = (CgreportConfigItemEntity) var11.next();
                    if (oldE.getId().equals(sendE.getId())) {
                        try {
                            MyBeanUtils.copyBeanNotNull2Bean(sendE, oldE);
                            this.saveOrUpdate(oldE);
                        } catch (Exception var16) {
                            var16.printStackTrace();
                            throw new BusinessException(var16.getMessage());
                        }

                        isUpdate = true;
                        break;
                    }
                }

                if (!isUpdate) {
                    super.delete(oldE);
                }
            }

            var8 = cgreportConfigItemList.iterator();

            while (var8.hasNext()) {
                oldE = (CgreportConfigItemEntity) var8.next();
                if (StringUtil.isEmpty(oldE.getId())) {
                    oldE.setCgrheadId(cgreportConfigHead.getId());
                    this.save(oldE);
                }
            }

            String hql1 = "from CgreportConfigParamEntity where 1 = 1 AND cgrheadId = ? ";
            List<CgreportConfigParamEntity> cgreportConfigParamOldList = this.findHql(hql1, new Object[]{id0});
            var20 = cgreportConfigParamOldList.iterator();
        }
        CgreportConfigParamEntity oldE;
        while(var20.hasNext()) {
            oldE = (CgreportConfigParamEntity)var20.next();
            boolean isUpdate = false;
            Iterator var13 = cgreportConfigParamList.iterator();

            while(var13.hasNext()) {
                CgreportConfigParamEntity sendE = (CgreportConfigParamEntity)var13.next();
                if(oldE.getId().equals(sendE.getId())) {
                    try {
                        MyBeanUtils.copyBeanNotNull2Bean(sendE, oldE);
                        this.saveOrUpdate(oldE);
                    } catch (Exception var15) {
                        var15.printStackTrace();
                        throw new BusinessException(var15.getMessage());
                    }

                    isUpdate = true;
                    break;
                }
            }

            if(!isUpdate) {
                super.delete(oldE);
            }
        }

        var20 = cgreportConfigParamList.iterator();

        while(var20.hasNext()) {
            oldE = (CgreportConfigParamEntity)var20.next();
            if(StringUtil.isEmpty(oldE.getId())) {
                oldE.setCgrheadId(cgreportConfigHead.getId());
                this.save(oldE);
            }
        }

        this.doUpdateSql(cgreportConfigHead);
    }

    public void delMain(CgreportConfigHeadEntity cgreportConfigHead) {
        this.delete(cgreportConfigHead);
        Object id0 = cgreportConfigHead.getId();
        String hql0 = "from CgreportConfigItemEntity where 1 = 1 AND cgrheadId = ? ";
        List<CgreportConfigItemEntity> cgreportConfigItemOldList = this.findHql(hql0, new Object[]{id0});
        this.deleteAllEntitie(cgreportConfigItemOldList);
        String hql1 = "from CgreportConfigParamEntity where 1 = 1 AND cgrheadId = ? ";
        List<CgreportConfigParamEntity> cgreportConfigParamOldList = this.findHql(hql1, new Object[]{id0});
        this.deleteAllEntitie(cgreportConfigParamOldList);
    }

    public boolean doAddSql(CgreportConfigHeadEntity t) {
        return true;
    }

    public boolean doUpdateSql(CgreportConfigHeadEntity t) {
        return true;
    }

    public boolean doDelSql(CgreportConfigHeadEntity t) {
        return true;
    }

    public String replaceVal(String sql, CgreportConfigHeadEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{code}", String.valueOf(t.getCode()));
        sql = sql.replace("#{name}", String.valueOf(t.getName()));
        sql = sql.replace("#{cgr_sql}", String.valueOf(t.getCgrSql()));
        sql = sql.replace("#{content}", String.valueOf(t.getContent()));
        sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
        return sql;
    }
}
