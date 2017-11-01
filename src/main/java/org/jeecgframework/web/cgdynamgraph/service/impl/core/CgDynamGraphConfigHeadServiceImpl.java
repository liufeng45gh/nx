//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgdynamgraph.service.impl.core;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigHeadEntity;
import org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigItemEntity;
import org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigParamEntity;
import org.jeecgframework.web.cgdynamgraph.service.core.CgDynamGraphConfigHeadServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("cgDynamGraphConfigHeadService")
@Transactional
public class CgDynamGraphConfigHeadServiceImpl extends CommonServiceImpl implements CgDynamGraphConfigHeadServiceI {
    public CgDynamGraphConfigHeadServiceImpl() {
    }

    public <T> void delete(T entity) {
        super.delete(entity);
        this.doDelSql((CgDynamGraphConfigHeadEntity)entity);
    }

    public void addMain(CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead, List<CgDynamGraphConfigItemEntity> cgDynamGraphConfigItemList, List<CgDynamGraphConfigParamEntity> cgDynamGraphConfigParamList) {
        this.save(cgDynamGraphConfigHead);
        Iterator var5 = cgDynamGraphConfigItemList.iterator();

        while(var5.hasNext()) {
            CgDynamGraphConfigItemEntity cgDynamGraphConfigItem = (CgDynamGraphConfigItemEntity)var5.next();
            cgDynamGraphConfigItem.setCgrheadId(cgDynamGraphConfigHead.getId());
            this.save(cgDynamGraphConfigItem);
        }

        var5 = cgDynamGraphConfigParamList.iterator();

        while(var5.hasNext()) {
            CgDynamGraphConfigParamEntity cgDynamGraphConfigParam = (CgDynamGraphConfigParamEntity)var5.next();
            cgDynamGraphConfigParam.setCgrheadId(cgDynamGraphConfigHead.getId());
            this.save(cgDynamGraphConfigParam);
        }

        this.doAddSql(cgDynamGraphConfigHead);
    }

    public void updateMain(CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead, List<CgDynamGraphConfigItemEntity> cgDynamGraphConfigItemList, List<CgDynamGraphConfigParamEntity> cgDynamGraphConfigParamList) {
        this.saveOrUpdate(cgDynamGraphConfigHead);
        Object id0 = cgDynamGraphConfigHead.getId();
        String hql0 = "from CgDynamGraphConfigItemEntity where 1 = 1 AND cgrheadId = ? ";
        List<CgDynamGraphConfigItemEntity> cgDynamGraphConfigItemOldList = this.findHql(hql0, new Object[]{id0});
        Iterator var8 = cgDynamGraphConfigItemOldList.iterator();
        Iterator var20;
        {
            CgDynamGraphConfigItemEntity oldE;

            while (var8.hasNext()) {
                oldE = (CgDynamGraphConfigItemEntity) var8.next();
                boolean isUpdate = false;
                Iterator var11 = cgDynamGraphConfigItemList.iterator();

                while (var11.hasNext()) {
                    CgDynamGraphConfigItemEntity sendE = (CgDynamGraphConfigItemEntity) var11.next();
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

            var8 = cgDynamGraphConfigItemList.iterator();

            while (var8.hasNext()) {
                oldE = (CgDynamGraphConfigItemEntity) var8.next();
                if (StringUtil.isEmpty(oldE.getId())) {
                    oldE.setCgrheadId(cgDynamGraphConfigHead.getId());
                    this.save(oldE);
                }
            }

            String hql1 = "from CgDynamGraphConfigParamEntity where 1 = 1 AND cgrheadId = ? ";
            List<CgDynamGraphConfigParamEntity> cgDynamGraphConfigParamOldList = this.findHql(hql1, new Object[]{id0});
            var20 = cgDynamGraphConfigParamOldList.iterator();
        }
        CgDynamGraphConfigParamEntity oldE;
        while(var20.hasNext()) {
            oldE = (CgDynamGraphConfigParamEntity)var20.next();
            boolean isUpdate = false;
            Iterator var13 = cgDynamGraphConfigParamList.iterator();

            while(var13.hasNext()) {
                CgDynamGraphConfigParamEntity sendE = (CgDynamGraphConfigParamEntity)var13.next();
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

        var20 = cgDynamGraphConfigParamList.iterator();

        while(var20.hasNext()) {
            oldE = (CgDynamGraphConfigParamEntity)var20.next();
            if(StringUtil.isEmpty(oldE.getId())) {
                oldE.setCgrheadId(cgDynamGraphConfigHead.getId());
                this.save(oldE);
            }
        }

        this.doUpdateSql(cgDynamGraphConfigHead);
    }

    public void delMain(CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead) {
        this.delete(cgDynamGraphConfigHead);
        Object id0 = cgDynamGraphConfigHead.getId();
        String hql0 = "from CgDynamGraphConfigItemEntity where 1 = 1 AND cgrheadId = ? ";
        List<CgDynamGraphConfigItemEntity> cgDynamGraphConfigItemOldList = this.findHql(hql0, new Object[]{id0});
        this.deleteAllEntitie(cgDynamGraphConfigItemOldList);
        String hql1 = "from CgDynamGraphConfigParamEntity where 1 = 1 AND cgrheadId = ? ";
        List<CgDynamGraphConfigParamEntity> cgDynamGraphConfigParamOldList = this.findHql(hql1, new Object[]{id0});
        this.deleteAllEntitie(cgDynamGraphConfigParamOldList);
    }

    public boolean doAddSql(CgDynamGraphConfigHeadEntity t) {
        return true;
    }

    public boolean doUpdateSql(CgDynamGraphConfigHeadEntity t) {
        return true;
    }

    public boolean doDelSql(CgDynamGraphConfigHeadEntity t) {
        return true;
    }

    public String replaceVal(String sql, CgDynamGraphConfigHeadEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{code}", String.valueOf(t.getCode()));
        sql = sql.replace("#{name}", String.valueOf(t.getName()));
        sql = sql.replace("#{cgr_sql}", String.valueOf(t.getCgrSql()));
        sql = sql.replace("#{content}", String.valueOf(t.getContent()));
        sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
        return sql;
    }
}
