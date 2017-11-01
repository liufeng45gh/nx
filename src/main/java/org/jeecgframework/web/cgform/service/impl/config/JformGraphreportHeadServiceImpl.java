//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.service.impl.config;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.cgform.entity.config.JformGraphreportHeadEntity;
import org.jeecgframework.web.cgform.entity.config.JformGraphreportItemEntity;
import org.jeecgframework.web.cgform.service.config.JformGraphreportHeadServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jformGraphreportHeadService")
@Transactional
public class JformGraphreportHeadServiceImpl extends CommonServiceImpl implements JformGraphreportHeadServiceI {
    public JformGraphreportHeadServiceImpl() {
    }

    public <T> void delete(T entity) {
        super.delete(entity);
        this.doDelSql((JformGraphreportHeadEntity)entity);
    }

    public void addMain(JformGraphreportHeadEntity jformGraphreportHead, List<JformGraphreportItemEntity> jformGraphreportItemList) {
        this.save(jformGraphreportHead);
        Iterator var4 = jformGraphreportItemList.iterator();

        while(var4.hasNext()) {
            JformGraphreportItemEntity jformGraphreportItem = (JformGraphreportItemEntity)var4.next();
            jformGraphreportItem.setCgreportHeadId(jformGraphreportHead.getId());
            this.save(jformGraphreportItem);
        }

        this.doAddSql(jformGraphreportHead);
    }

    public void updateMain(JformGraphreportHeadEntity jformGraphreportHead, List<JformGraphreportItemEntity> jformGraphreportItemList) {
        this.saveOrUpdate(jformGraphreportHead);
        Object id0 = jformGraphreportHead.getId();
        String hql0 = "from JformGraphreportItemEntity where 1 = 1 AND cGREPORT_HEAD_ID = ? ";
        List<JformGraphreportItemEntity> jformGraphreportItemOldList = this.findHql(hql0, new Object[]{id0});
        Iterator var7 = jformGraphreportItemOldList.iterator();

        JformGraphreportItemEntity oldE;
        while(var7.hasNext()) {
            oldE = (JformGraphreportItemEntity)var7.next();
            boolean isUpdate = false;
            Iterator var10 = jformGraphreportItemList.iterator();

            while(var10.hasNext()) {
                JformGraphreportItemEntity sendE = (JformGraphreportItemEntity)var10.next();
                if(oldE.getId().equals(sendE.getId())) {
                    try {
                        MyBeanUtils.copyBeanNotNull2Bean(sendE, oldE);
                        this.saveOrUpdate(oldE);
                    } catch (Exception var12) {
                        var12.printStackTrace();
                        throw new BusinessException(var12.getMessage());
                    }

                    isUpdate = true;
                    break;
                }
            }

            if(!isUpdate) {
                super.delete(oldE);
            }
        }

        var7 = jformGraphreportItemList.iterator();

        while(var7.hasNext()) {
            oldE = (JformGraphreportItemEntity)var7.next();
            if(oConvertUtils.isEmpty(oldE.getId())) {
                oldE.setCgreportHeadId(jformGraphreportHead.getId());
                this.save(oldE);
            }
        }

        this.doUpdateSql(jformGraphreportHead);
    }

    public void delMain(JformGraphreportHeadEntity jformGraphreportHead) {
        this.delete(jformGraphreportHead);
        Object id0 = jformGraphreportHead.getId();
        String hql0 = "from JformGraphreportItemEntity where 1 = 1 AND cGREPORT_HEAD_ID = ? ";
        List<JformGraphreportItemEntity> jformGraphreportItemOldList = this.findHql(hql0, new Object[]{id0});
        this.deleteAllEntitie(jformGraphreportItemOldList);
    }

    public boolean doAddSql(JformGraphreportHeadEntity t) {
        return true;
    }

    public boolean doUpdateSql(JformGraphreportHeadEntity t) {
        return true;
    }

    public boolean doDelSql(JformGraphreportHeadEntity t) {
        return true;
    }

    public String replaceVal(String sql, JformGraphreportHeadEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{name}", String.valueOf(t.getName()));
        sql = sql.replace("#{code}", String.valueOf(t.getCode()));
        sql = sql.replace("#{cgr_sql}", String.valueOf(t.getCgrSql()));
        sql = sql.replace("#{content}", String.valueOf(t.getContent()));
        sql = sql.replace("#{ytext}", String.valueOf(t.getYtext()));
        sql = sql.replace("#{categories}", String.valueOf(t.getCategories()));
        sql = sql.replace("#{is_show_list}", String.valueOf(t.getIsShowList()));
        sql = sql.replace("#{x_page_js}", String.valueOf(t.getXpageJs()));
        sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
        return sql;
    }
}
