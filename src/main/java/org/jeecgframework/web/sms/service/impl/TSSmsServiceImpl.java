//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.sms.service.impl;

import com.sun.mail.smtp.SMTPAddressFailedException;
import java.io.Serializable;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.mail.AuthenticationFailedException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DBTypeUtil;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.PropertiesUtil;
import org.jeecgframework.web.sms.entity.TSSmsEntity;
import org.jeecgframework.web.sms.service.TSSmsServiceI;
import org.jeecgframework.web.sms.util.CMPPSenderUtil;
import org.jeecgframework.web.sms.util.MailUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tSSmsService")
@Transactional
public class TSSmsServiceImpl extends CommonServiceImpl implements TSSmsServiceI {
    public TSSmsServiceImpl() {
    }

    public <T> void delete(T entity) {
        super.delete(entity);
        this.doDelSql((TSSmsEntity)entity);
    }

    public <T> Serializable save(T entity) {
        Serializable t = super.save(entity);
        this.doAddSql((TSSmsEntity)entity);
        return t;
    }

    public <T> void saveOrUpdate(T entity) {
        super.saveOrUpdate(entity);
        this.doUpdateSql((TSSmsEntity)entity);
    }

    public boolean doAddSql(TSSmsEntity t) {
        return true;
    }

    public boolean doUpdateSql(TSSmsEntity t) {
        return true;
    }

    public boolean doDelSql(TSSmsEntity t) {
        return true;
    }

    public String replaceVal(String sql, TSSmsEntity t) {
        sql = sql.replace("#{id}", String.valueOf(t.getId()));
        sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
        sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
        sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
        sql = sql.replace("#{update_name}", String.valueOf(t.getUpdateName()));
        sql = sql.replace("#{update_by}", String.valueOf(t.getUpdateBy()));
        sql = sql.replace("#{update_date}", String.valueOf(t.getUpdateDate()));
        sql = sql.replace("#{es_title}", String.valueOf(t.getEsTitle()));
        sql = sql.replace("#{es_type}", String.valueOf(t.getEsType()));
        sql = sql.replace("#{es_sender}", String.valueOf(t.getEsSender()));
        sql = sql.replace("#{es_receiver}", String.valueOf(t.getEsReceiver()));
        sql = sql.replace("#{es_content}", String.valueOf(t.getEsContent()));
        sql = sql.replace("#{es_sendtime}", String.valueOf(t.getEsSendtime()));
        sql = sql.replace("#{es_status}", String.valueOf(t.getEsStatus()));
        sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
        return sql;
    }

    @Transactional
    public void send() {
        LogUtil.info("===============消息发扫描开始=================");
        List<TSSmsEntity> smsSendList = this.findHql("from TSSmsEntity e where e.esStatus = ?", new Object[]{"1"});
        if(smsSendList != null && smsSendList.size() != 0) {
            PropertiesUtil util = new PropertiesUtil("sysConfig.properties");
            Iterator var4 = smsSendList.iterator();

            while(var4.hasNext()) {
                TSSmsEntity tsSmsEntity = (TSSmsEntity)var4.next();
                String remark = "";
                if("2".equals(tsSmsEntity.getEsType())) {
                    try {
                        MailUtil.sendEmail(util.readProperty("mail.smtpHost"), tsSmsEntity.getEsReceiver(), tsSmsEntity.getEsTitle(), tsSmsEntity.getEsContent(), util.readProperty("mail.sender"), util.readProperty("mail.user"), util.readProperty("mail.pwd"));
                        tsSmsEntity.setEsStatus("2");
                        tsSmsEntity.setEsSendtime(new Date());
                        remark = "发送成功";
                        tsSmsEntity.setRemark(remark);
                        this.updateEntitie(tsSmsEntity);
                    } catch (Exception var7) {
                        if(var7 instanceof AuthenticationFailedException) {
                            remark = "认证失败错误的用户名或者密码";
                        } else if(var7 instanceof SMTPAddressFailedException) {
                            remark = "接受邮箱格式不对";
                        } else if(var7 instanceof ConnectException) {
                            remark = "邮件服务器连接失败";
                        } else {
                            remark = var7.getMessage();
                        }

                        tsSmsEntity.setEsStatus("3");
                        tsSmsEntity.setEsSendtime(new Date());
                        tsSmsEntity.setRemark(remark);
                        this.updateEntitie(tsSmsEntity);
                    }
                }

                if("1".equals(tsSmsEntity.getEsType())) {
                    String r = CMPPSenderUtil.sendMsg(tsSmsEntity.getEsReceiver(), tsSmsEntity.getEsContent());
                    if("0".equals(r)) {
                        tsSmsEntity.setEsStatus("2");
                    } else {
                        tsSmsEntity.setEsStatus("3");
                    }
                }

                tsSmsEntity.setRemark(remark);
                tsSmsEntity.setEsSendtime(new Date());
                this.updateEntitie(tsSmsEntity);
            }

            LogUtil.info("===============消息发扫描结束=================");
        }
    }

    public List<TSSmsEntity> getMsgsList(String curUser, String curDate) {
        new ArrayList();
        String hql = null;
        if("sqlserver".equals(DBTypeUtil.getDBType())) {
            hql = "from TSSmsEntity t where t.esType='3' and t.esReceiver=? and CONVERT(varchar(20),t.esSendtime) like ?";
        } else {
            hql = "from TSSmsEntity t where t.esType='3' and t.esReceiver=? and str(t.esSendtime) like ?";
        }

        List<TSSmsEntity> list = this.findHql(hql, new Object[]{curUser, curDate + '%'});
        return list;
    }
}
