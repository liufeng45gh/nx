//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.service.impl.config;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.hibernate.HibernateException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormIndexEntity;
import org.jeecgframework.web.cgform.service.config.CgFormIndexServiceI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("cgFormIndexService")
@Transactional
public class CgFormIndexServiceImpl extends CommonServiceImpl implements CgFormIndexServiceI {
    public CgFormIndexServiceImpl() {
    }

    public <T> void delete(T entity) {
        super.delete(entity);
        this.doDelSql((CgFormIndexEntity)entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        this.doAddSql((CgFormIndexEntity)entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        this.doUpdateSql((CgFormIndexEntity)entity);
    }

    public boolean doAddSql(CgFormIndexEntity t) {
        return true;
    }

    public boolean doUpdateSql(CgFormIndexEntity t) {
        return true;
    }

    public boolean doDelSql(CgFormIndexEntity t) {
        return true;
    }

    public String replaceVal(String sql, CgFormIndexEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{update_name}", String.valueOf(t.getUpdateName()));
        sql = sql.replace("#{update_by}", String.valueOf(t.getUpdateBy()));
        sql = sql.replace("#{update_date}", String.valueOf(t.getUpdateDate()));
        sql = sql.replace("#{index_name}", String.valueOf(t.getIndexName()));
        sql = sql.replace("#{index_field}", String.valueOf(t.getIndexField()));
        sql = sql.replace("#{index_type}", String.valueOf(t.getIndexType()));
        sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
        return sql;
    }

    public boolean updateIndexes(CgFormHeadEntity cgFormHead) {
        boolean isChange = false;
        List<CgFormIndexEntity> indexes = cgFormHead.getIndexes();
        List<CgFormIndexEntity> oldindexes = this.getSession().createSQLQuery("select * from cgform_index where table_id = '" + cgFormHead.getId() + "'").addEntity(CgFormIndexEntity.class).list();
        CgFormIndexEntity cgform;
        if(oldindexes.size() != 0 && indexes != null) {
            if(oldindexes.size() != indexes.size()) {
                isChange = true;
            } else {
                for(int i = 0; i < indexes.size(); ++i) {
                    CgFormIndexEntity oldindex = (CgFormIndexEntity)oldindexes.get(i);
                    cgform = (CgFormIndexEntity)indexes.get(i);
                    if(!oldindex.getIndexField().equals(cgform.getIndexField()) || !oldindex.getIndexName().equals(cgform.getIndexName()) || !oldindex.getIndexType().equals(cgform.getIndexType())) {
                        isChange = true;
                    }
                }
            }
        } else if(oldindexes.size() == 0 && indexes == null) {
            isChange = false;
        } else {
            isChange = true;
        }

        cgFormHead.setIsDbSynch(isChange?"N":cgFormHead.getIsDbSynch());
        String id = cgFormHead.getId();
        CgFormHeadEntity formhead = (CgFormHeadEntity)this.getEntity(CgFormHeadEntity.class, cgFormHead.getId());

        Iterator var8;
        try {
            if(oldindexes != null) {
                var8 = oldindexes.iterator();

                while(var8.hasNext()) {
                    cgform = (CgFormIndexEntity)var8.next();
                    String sql = "drop index " + cgform.getIndexName() + " on " + formhead.getTableName();
                    this.getSession().createSQLQuery(sql).executeUpdate();
                }
            }
        } catch (HibernateException var10) {
            ;
        }

        this.getSession().createSQLQuery("delete from cgform_index where table_id = '" + id + "'").executeUpdate();
        if(indexes != null) {
            var8 = indexes.iterator();

            while(var8.hasNext()) {
                cgform = (CgFormIndexEntity)var8.next();
                cgform.setTable(cgFormHead);
                this.save(cgform);
            }
        }

        return isChange;
    }

    public void createIndexes(CgFormHeadEntity cgFormHead) {
        CgFormHeadEntity formhead = (CgFormHeadEntity)this.getEntity(CgFormHeadEntity.class, cgFormHead.getId());
        List<CgFormIndexEntity> indexes = this.getSession().createSQLQuery("select * from cgform_index where table_id = '" + cgFormHead.getId() + "'").addEntity(CgFormIndexEntity.class).list();
        String sql;
        if(indexes.size() != 0) {
            for(Iterator var5 = indexes.iterator(); var5.hasNext(); this.getSession().createSQLQuery(sql).executeUpdate()) {
                CgFormIndexEntity cgform = (CgFormIndexEntity)var5.next();
                sql = "";
                if(cgform.getIndexType().equals("normal")) {
                    sql = "create index " + cgform.getIndexName() + " on " + formhead.getTableName() + "(" + cgform.getIndexField() + ")";
                } else {
                    sql = "create " + cgform.getIndexType() + " index " + cgform.getIndexName() + " on " + formhead.getTableName() + "(" + cgform.getIndexField() + ")";
                }
            }
        }

    }
}
