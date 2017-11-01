//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.service.businessprocessor.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssb.warmline.business.commons.config.ResponseObject;
import ssb.warmline.business.commons.enums.ReadingState;
import ssb.warmline.business.commons.utils.CommonUtils;
import ssb.warmline.business.entity.whelpmessage.WHelpMessageEntity;
import ssb.warmline.business.entity.wquestionanswer.WQuestionAnswerEntity;
import ssb.warmline.business.entity.wversionupdatemanagement.WVersionUpdateManagementEntity;
import ssb.warmline.business.service.businessprocessor.BaseInterface;
import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
import ssb.warmline.business.service.whelpmessage.WHelpMessageServiceI;
import ssb.warmline.business.service.wquestionanswer.WQuestionAnswerServiceI;
import ssb.warmline.business.service.wversionupdatemanagement.WVersionUpdateManagementServiceI;

@Service
public class MessageserviceProcessor extends BaseInterface {
    @Autowired
    private WHelpMessageServiceI helpMessageService;
    @Autowired
    private WVersionUpdateManagementServiceI WVersionUpdateManagementService;
    @Autowired
    private WQuestionAnswerServiceI wQuestionAnswerService;
    @Autowired
    private TSBaseUserServiceI tSBaseUserService;

    public MessageserviceProcessor() {
    }

    public ResponseObject seekHelpMessage(String uid, String token, Integer page, Integer rows) {
        Map<String, Object> jsonMap = new HashMap();
        String sql = "SELECT whm.id AS id, whm.help_people_id AS helpPeopleId, whm.create_date AS createDate, whm.seek_help_people_id AS seekHelpPeopleId,whm.seek_help_people AS seekHelpPeople,whm.reading_state AS readingState, whm.help_people AS helpPeople, whm.content AS content, whm.message_state AS messageState,whm.message_status AS messageStatus,whm.order_id AS orderid FROM w_help_message whm WHERE whm.seek_help_people_id = '" + uid + "' AND whm.message_status != '' AND whm.message_state = 'SEEKHELP' ORDER BY create_date desc";
        List<WHelpMessageEntity> helpMessageEntities = this.helpMessageService.findObjForJdbc(sql, page.intValue(), rows.intValue(), WHelpMessageEntity.class);
        List<Map> orderList = new ArrayList();
        if(helpMessageEntities.size() > 0) {
            Map map = new HashMap();
            WHelpMessageEntity hme = (WHelpMessageEntity)helpMessageEntities.get(0);
            if(hme.getHelpPeopleId() != null && !"".equals(hme.getHelpPeopleId())) {
                TSBaseUser helpPeopleId = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", hme.getHelpPeopleId());
                if(helpPeopleId == null) {
                    map.put("headPortrait", (Object)null);
                } else if(helpPeopleId.getHeadPortrait() != null && !"".equals(helpPeopleId.getHeadPortrait())) {
                    map.put("headPortrait", helpPeopleId.getHeadPortrait());
                } else {
                    map.put("headPortrait", (Object)null);
                }

                map.put("messageId", hme.getId());
                if(helpPeopleId.getUserName() != null && !"".equals(helpPeopleId.getUserName())) {
                    map.put("helpPeople", helpPeopleId.getUserName());
                } else {
                    map.put("helpPeople", helpPeopleId.getRealName());
                }

                map.put("createDate", hme.getCreateDate());
                map.put("readingState", hme.getReadingState());
                map.put("helpPeopleId", hme.getHelpPeopleId());
                map.put("messageCount", hme.getContent());
                map.put("messageStatus", hme.getMessageStatus());
                map.put("seekHelpPeopleId", hme.getSeekHelpPeopleId());
                if(hme.getSeekHelpPeople() != null && !"".equals(hme.getSeekHelpPeople())) {
                    map.put("seekHelpPeople", hme.getSeekHelpPeople());
                } else {
                    TSBaseUser seekHelpPeopleId = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", hme.getSeekHelpPeopleId());
                    if(seekHelpPeopleId != null) {
                        map.put("seekHelpPeople", seekHelpPeopleId.getRealName());
                    } else {
                        map.put("seekHelpPeople", hme.getSeekHelpPeople());
                    }
                }

                map.put("orderid", hme.getOrderid());
                orderList.add(map);
            } else {
                map.put("headPortrait", "");
                map.put("messageId", hme.getId());
                map.put("helpPeople", "");
                map.put("createDate", hme.getCreateDate());
                map.put("readingState", hme.getReadingState());
                map.put("helpPeopleId", "");
                map.put("messageCount", hme.getContent());
                map.put("messageStatus", hme.getMessageStatus());
                map.put("seekHelpPeopleId", hme.getSeekHelpPeopleId());
                map.put("seekHelpPeople", hme.getSeekHelpPeople());
                map.put("orderid", hme.getOrderid());
                orderList.add(map);
            }
        }

        jsonMap.put("orderList", orderList);
        return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
    }

    public ResponseObject helpMessage(String uid, String token, Integer page, Integer rows) {
        Map<String, Object> jsonMap = new HashMap();
        String sql = "SELECT whm.id AS id, whm.help_people_id AS helpPeopleId, whm.create_date AS createDate, whm.seek_help_people_id AS seekHelpPeopleId,whm.seek_help_people AS seekHelpPeople,whm.reading_state AS readingState, whm.help_people AS helpPeople, whm.content AS content, whm.message_state AS messageState,whm.message_status AS messageStatus,whm.order_id AS orderid FROM w_help_message whm WHERE whm.help_people_id = '" + uid + "' AND whm.message_status != '' AND whm.message_state = 'HELP' ORDER BY create_date desc";
        List<WHelpMessageEntity> helpMessageEntities = this.helpMessageService.findObjForJdbc(sql, page.intValue(), rows.intValue(), WHelpMessageEntity.class);
        List<Map> orderList = new ArrayList();
        if(helpMessageEntities.size() > 0) {
            Map map = new HashMap();
            WHelpMessageEntity hme = (WHelpMessageEntity)helpMessageEntities.get(0);
            TSBaseUser seekHelpPeopleId = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", hme.getSeekHelpPeopleId());
            if(seekHelpPeopleId == null) {
                map.put("headPortrait", (Object)null);
            } else if(seekHelpPeopleId.getHeadPortrait() != null && !"".equals(seekHelpPeopleId.getHeadPortrait())) {
                map.put("headPortrait", seekHelpPeopleId.getHeadPortrait());
            } else {
                map.put("headPortrait", (Object)null);
            }

            map.put("messageId", hme.getId());
            if(!"".equals(hme.getHelpPeople()) && hme.getHelpPeople() != null) {
                map.put("helpPeople", hme.getHelpPeople());
            } else {
                TSBaseUser helpPeopleId = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", hme.getHelpPeopleId());
                map.put("helpPeople", helpPeopleId.getRealName());
            }

            map.put("createDate", hme.getCreateDate());
            map.put("readingState", hme.getReadingState());
            map.put("helpPeopleId", hme.getHelpPeopleId());
            map.put("messageCount", hme.getContent());
            map.put("messageStatus", hme.getMessageStatus());
            map.put("seekHelpPeopleId", hme.getSeekHelpPeopleId());
            if(!"".equals(hme.getSeekHelpPeople()) && hme.getSeekHelpPeople() != null) {
                map.put("seekHelpPeople", seekHelpPeopleId.getUserName());
            } else {
                map.put("seekHelpPeople", seekHelpPeopleId.getRealName());
            }

            map.put("orderid", hme.getOrderid());
            orderList.add(map);
        }

        jsonMap.put("orderList", orderList);
        return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
    }

    public ResponseObject systemMessage(String uid, String token, Integer page, Integer rows, String phoneType) {
        Map<String, Object> jsonMap = new HashMap();
        TSBaseUser uids = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", uid);
        String sql;
        String sqls;
        if("Android".equals(phoneType)) {
            sql = " SELECT whm.id AS id, whm.content AS content, whm.create_date AS createDate, whm.reading_state AS readingState, whm.message_state AS messageState, whm.message_status AS messageStatus, whm.version_path AS versionPath , whm.title AS title  FROM w_help_message whm WHERE whm.message_state = 'SYSTEM' AND whm.message_status != '' and whm.push_category = 'WHOLE'  ORDER BY whm.create_date DESC";
            sqls = " SELECT whm.id AS id, whm.content AS content, whm.create_date AS createDate, whm.reading_state AS readingState, whm.message_state AS messageState, whm.message_status AS messageStatus, whm.version_path AS versionPath , whm.title AS title  FROM w_help_message whm WHERE whm.message_state = 'SYSTEM' AND whm.message_status != '' AND whm.push_category ='PERSONAL' AND find_in_set('" + uids.getPhone() + "',whm.personal_information ) ORDER BY whm.create_date DESC";
        } else if("ios".equals(phoneType)) {
            sql = " SELECT whm.id AS id, whm.content AS content, whm.create_date AS createDate, whm.reading_state AS readingState,  whm.message_state AS messageState, whm.message_status AS messageStatus, whm.version_path AS versionPath , whm.title AS title  FROM w_help_message whm WHERE whm.message_state = 'SYSTEM' AND whm.message_status = 'TEXT' AND whm.message_status != '' and whm.push_category = 'WHOLE' ORDER BY whm.create_date DESC";
            sqls = " SELECT whm.id AS id, whm.content AS content, whm.create_date AS createDate, whm.reading_state AS readingState, whm.message_state AS messageState, whm.message_status AS messageStatus, whm.version_path AS versionPath , whm.title AS title  FROM w_help_message whm WHERE whm.message_state = 'SYSTEM' AND whm.message_status = 'TEXT' and whm.push_category ='PERSONAL' AND find_in_set('" + uids.getPhone() + "',whm.personal_information ) ORDER BY whm.create_date DESC";
        } else {
            sql = " SELECT whm.id AS id, whm.content AS content, whm.create_date AS createDate, whm.reading_state AS readingState, whm.message_state AS messageState, whm.message_status AS messageStatus, whm.version_path AS versionPath , whm.title AS title FROM w_help_message whm WHERE whm.message_state = 'SYSTEM' AND whm.message_status != '' and whm.push_category = 'WHOLE' ORDER BY whm.create_date DESC";
            sqls = " SELECT whm.id AS id, whm.content AS content, whm.create_date AS createDate, whm.reading_state AS readingState, whm.message_state AS messageState, whm.message_status AS messageStatus, whm.version_path AS versionPath , whm.title AS title  FROM w_help_message whm WHERE whm.message_state = 'SYSTEM' AND whm.message_status != '' AND whm.push_category ='PERSONAL' AND whm.personal_information LIKE '%" + uids.getPhone() + "%' ORDER BY whm.create_date DESC";
        }

        List<WHelpMessageEntity> helpMessageEntities = this.helpMessageService.findObjForJdbc(sql, page.intValue(), rows.intValue(), WHelpMessageEntity.class);
        List<WHelpMessageEntity> helpMessageEntities2 = this.helpMessageService.findObjForJdbc(sqls, page.intValue(), rows.intValue(), WHelpMessageEntity.class);
        List<Map> orderList = new ArrayList();
        if(helpMessageEntities.size() > 0) {
            for(int a = 0; a < helpMessageEntities.size(); ++a) {
                Map map = new HashMap();
                WHelpMessageEntity hme = (WHelpMessageEntity)helpMessageEntities.get(a);
                map.put("messageId", hme.getId());
                map.put("content", hme.getContent());
                map.put("title", hme.getTitle());
                map.put("createDate", hme.getCreateDate());
                map.put("readingState", hme.getReadingState());
                map.put("messageStatus", hme.getMessageStatus());
                map.put("versionPath", hme.getVersionPath());
                orderList.add(map);
            }
        }

        List<Map> orderList2 = new ArrayList();
        if(helpMessageEntities2.size() > 0) {
            for(int a = 0; a < helpMessageEntities2.size(); ++a) {
                Map map = new HashMap();
                WHelpMessageEntity hme = (WHelpMessageEntity)helpMessageEntities2.get(a);
                map.put("messageId", hme.getId());
                map.put("content", hme.getContent());
                map.put("title", hme.getTitle());
                map.put("createDate", hme.getCreateDate());
                map.put("readingState", hme.getReadingState());
                map.put("messageStatus", hme.getMessageStatus());
                map.put("versionPath", hme.getVersionPath());
                orderList2.add(map);
            }
        }

        orderList.addAll(orderList2);
        jsonMap.put("orderList", orderList);
        return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
    }

    public ResponseObject systemUpdate() {
        Map<String, Object> jsonMap = new HashMap();
        new ArrayList();
        new HashMap();
        WVersionUpdateManagementEntity findByProperty = (WVersionUpdateManagementEntity)this.WVersionUpdateManagementService.findUniqueByProperty(WVersionUpdateManagementEntity.class, "currentField", "true");
        if(findByProperty != null && !"".equals(findByProperty)) {
            jsonMap.put("versionName", findByProperty.getVersionName());
            jsonMap.put("versionNumber", findByProperty.getVersionNumber().toString());
            jsonMap.put("versionDescription", findByProperty.getVersionDescription());
            jsonMap.put("uploadUrl", findByProperty.getUploadUrl());
            return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
        } else {
            return CommonUtils.repsonseOjbectToClientWithBody("20044", (Object)null, new String[0]);
        }
    }

    public ResponseObject intelligence() {
        Map<String, Object> jsonMap = new HashMap();
        List<Map> QAList = new ArrayList();
        String hql = " from  WQuestionAnswerEntity  ORDER BY serialNumber ASC ";
        List<WQuestionAnswerEntity> findByQueryString = this.wQuestionAnswerService.findByQueryString(hql);
        if(findByQueryString.size() > 0) {
            for(int i = 0; i < findByQueryString.size(); ++i) {
                List<Map> QAList1 = new ArrayList();
                Map map = new HashMap();
                Map map1 = new HashMap();
                WQuestionAnswerEntity wQuestionAnswerEntity = (WQuestionAnswerEntity)findByQueryString.get(i);
                map.put("qAId", wQuestionAnswerEntity.getId());
                map.put("problem", wQuestionAnswerEntity.getProblem());
                map1.put("answer", wQuestionAnswerEntity.getAnswer());
                QAList1.add(map1);
                map.put("answerList", QAList1);
                map.put("serialNumber", wQuestionAnswerEntity.getSerialNumber());
                QAList.add(map);
            }
        }

        jsonMap.put("QAList", QAList);
        return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
    }

    public ResponseObject changeStatus(String uid, String token, String messageId) {
        Map<String, Object> jsonMap = new HashMap();
        WHelpMessageEntity wHelpMessage = (WHelpMessageEntity)this.helpMessageService.findUniqueByProperty(WHelpMessageEntity.class, "id", messageId);
        String orderId = wHelpMessage.getOrderid();
        String messageState = wHelpMessage.getMessageState();
        String sqlB;
        if("SEEKHELP".equals(messageState)) {
            if(orderId != null && !"".equals(orderId)) {
                sqlB = "update w_help_message set reading_state='Yes' where seek_help_people_id='" + uid + "' and order_id= '" + orderId + "'";
                this.helpMessageService.updateBySqlString(sqlB);
            }
        } else if("HELP".equals(messageState)) {
            if(orderId != null && !"".equals(orderId)) {
                sqlB = "update w_help_message set reading_state='Yes' where help_people_id='" + uid + "' and order_id= '" + orderId + "'";
                this.helpMessageService.updateBySqlString(sqlB);
            }
        } else if("SYSTEM".equals(messageState)) {
            wHelpMessage.setReadingState(ReadingState.Yes.toString());
            this.helpMessageService.saveOrUpdate(wHelpMessage);
        }

        return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
    }

    public ResponseObject readChangeStatus(String uid, String token) {
        Map<String, Object> jsonMap = new HashMap();
        TSBaseUser tSBaseUser = (TSBaseUser)this.tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", uid);
        String sql1 = "SELECT * FROM w_help_message WHERE seek_help_people_id ='" + uid + "' AND reading_state ='No' AND message_state = 'SEEKHELP' UNION " + " SELECT * FROM w_help_message WHERE  help_people_id='" + uid + "' AND reading_state ='No' AND message_state = 'HELP' UNION " + "SELECT * FROM w_help_message WHERE message_state = 'SYSTEM' AND message_status is not null " + " AND push_category ='PERSONAL' AND find_in_set('" + tSBaseUser.getPhone() + "',personal_information ) AND reading_state ='No'" + " UNION SELECT * FROM w_help_message WHERE message_state = 'SYSTEM' AND message_status is not null AND push_category ='WHOLE' " + " AND reading_state ='No'";
        List<WHelpMessageEntity> wHelpMessageList = this.helpMessageService.findObjForJdbc(sql1, 1, 10000, WHelpMessageEntity.class);
        if(wHelpMessageList != null && wHelpMessageList.size() > 0) {
            jsonMap.put("messageDisplay", "Yes");
        } else {
            jsonMap.put("messageDisplay", "No");
        }

        return CommonUtils.repsonseOjbectToClientWithBody("10000", jsonMap, new String[0]);
    }
}
