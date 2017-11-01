//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.service.businessprocessor.app;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssb.warmline.business.commons.utils.JsonReturn;
import ssb.warmline.business.commons.utils.UUIDUtil;
import ssb.warmline.business.commons.utils.wechatpay.HttpUtil;
import ssb.warmline.business.commons.utils.wechatpay.PayCommonUtil;
import ssb.warmline.business.commons.utils.wechatpay.XMLUtil;
import ssb.warmline.business.entity.worder.WOrderEntity;
import ssb.warmline.business.service.businessprocessor.BaseInterface;
import ssb.warmline.business.service.worder.WOrderServiceI;

@Service
public class WeChatPayServiceProcessor extends BaseInterface {
    private static final Logger log = Logger.getLogger(WeChatPayServiceProcessor.class);
    @Autowired
    private WOrderServiceI wOrderService;

    public WeChatPayServiceProcessor() {
    }

    public JsonReturn weChatAppPay(String spbill_create_ip, String orderId, String uid, String token) throws Exception {
        WOrderEntity orderEntity = (WOrderEntity)this.wOrderService.findUniqueByProperty(WOrderEntity.class, "orderNumber", orderId);
        Map<String, String> weiXinMap = new HashMap();
        String body_name = orderEntity.getTitle();
        String out_trade_no = orderEntity.getOrderNumber();
        String order_price = orderEntity.getPrice();
        String appid = "wx93f526ae469ba2f0";
        String mch_id = "1455164902";
        String apiKey = "NUANXingapiKey6352ERyusiJanDaoNo";
        String weixin_pay_unified_place_order_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String uuid = UUIDUtil.getId();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("commission");
        String serviceIp = resourceBundle.getString("serviceIp");
        String noticeUrl = "http://" + serviceIp + "/passWordAppWeixinNotify.action";
        String currTime = PayCommonUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = String.valueOf(PayCommonUtil.buildRandom(4));
        String nonce_str = strTime + strRandom;
        String trade_type = "APP";
        SortedMap<Object, Object> packageParams = new TreeMap();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", body_name);
        packageParams.put("out_trade_no", out_trade_no);
        BigDecimal bigIntegerTotal = new BigDecimal(order_price);
        BigDecimal param = new BigDecimal(100);
        int total_fee_temp = param.multiply(bigIntegerTotal).intValue();
        String total_fee = String.valueOf(total_fee_temp);
        packageParams.put("total_fee", total_fee);
        packageParams.put("spbill_create_ip", spbill_create_ip);
        packageParams.put("notify_url", noticeUrl);
        packageParams.put("trade_type", trade_type);
        packageParams.put("attach", uid);
        String sign = PayCommonUtil.createSign("UTF-8", packageParams, apiKey);
        packageParams.put("sign", sign);
        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        System.out.println(requestXML);
        String resXml = HttpUtil.postData(weixin_pay_unified_place_order_url, requestXML);
        Map map = XMLUtil.doXMLParse(resXml);
        String return_code = (String)map.get("return_code");
        String prepay_id = null;
        if(return_code.contains("SUCCESS")) {
            prepay_id = (String)map.get("prepay_id");
            long var34 = System.currentTimeMillis();
            long var36 = var34 / 1000L;
            String seconds = String.valueOf(var36).substring(0, 10);
            TreeMap signParam = new TreeMap();
            signParam.put("appid", appid);
            signParam.put("partnerid", mch_id);
            signParam.put("prepayid", prepay_id);
            signParam.put("noncestr", uuid);
            signParam.put("timestamp", seconds);
            signParam.put("package", "Sign=WXPay");
            String signAgain = PayCommonUtil.createSign("UTF-8", signParam, apiKey);
            packageParams.put("sign", signAgain);
            weiXinMap.put("appid", appid);
            weiXinMap.put("partnerid", mch_id);
            weiXinMap.put("prepayid", prepay_id);
            weiXinMap.put("noncestr", uuid);
            weiXinMap.put("timestamp", seconds);
            weiXinMap.put("package", "Sign=WXPay");
            weiXinMap.put("sign", signAgain);
            return JsonReturn.toSucResult(weiXinMap);
        } else {
            return JsonReturn.toSucResult(resXml);
        }
    }

    public JsonReturn giveReward_weChatAppPay(String spbill_create_ip, String orderId, String userId, String totalFee) throws Exception {
        WOrderEntity orderEntity = (WOrderEntity)this.wOrderService.getEntity(WOrderEntity.class, orderId);
        String body_name = orderEntity.getTitle();
        Map<String, String> weiXinMap = new HashMap();
        String appid = null;
        String mch_id = null;
        String apiKey = null;
        appid = "wx93f526ae469ba2f0";
        mch_id = "1455164902";
        apiKey = "NUANXingapiKey6352ERyusiJanDaoNo";
        String weixin_pay_unified_place_order_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String uuid = UUIDUtil.getId();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("commission");
        String serviceIp = resourceBundle.getString("serviceIp");
        String noticeUrl = "http://" + serviceIp + "/giveReward_AppWeixinNotify.action";
        String currTime = PayCommonUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = String.valueOf(PayCommonUtil.buildRandom(4));
        String nonce_str = strTime + strRandom;
        String trade_type = "APP";
        SortedMap<Object, Object> packageParams = new TreeMap();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", body_name);
        packageParams.put("out_trade_no", UUIDUtil.getId());
        BigDecimal bigIntegerTotal = new BigDecimal(totalFee);
        BigDecimal param = new BigDecimal(100);
        int total_fee_temp = param.multiply(bigIntegerTotal).intValue();
        String total_fee = String.valueOf(total_fee_temp);
        packageParams.put("total_fee", total_fee);
        packageParams.put("spbill_create_ip", spbill_create_ip);
        packageParams.put("notify_url", noticeUrl);
        packageParams.put("trade_type", trade_type);
        packageParams.put("attach", orderId);
        String sign = PayCommonUtil.createSign("UTF-8", packageParams, apiKey);
        packageParams.put("sign", sign);
        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        System.out.println(requestXML);
        String resXml = HttpUtil.postData(weixin_pay_unified_place_order_url, requestXML);
        Map map = XMLUtil.doXMLParse(resXml);
        String return_code = (String)map.get("return_code");
        String prepay_id = null;
        if(return_code.contains("SUCCESS")) {
            prepay_id = (String)map.get("prepay_id");
        }

        long currentTimeMillis = System.currentTimeMillis();
        long second = currentTimeMillis / 1000L;
        String seconds = String.valueOf(second).substring(0, 10);
        SortedMap<Object, Object> signParam = new TreeMap();
        signParam.put("appid", appid);
        signParam.put("partnerid", mch_id);
        signParam.put("prepayid", prepay_id);
        signParam.put("noncestr", uuid);
        signParam.put("timestamp", seconds);
        signParam.put("package", "Sign=WXPay");
        String signAgain = PayCommonUtil.createSign("UTF-8", signParam, apiKey);
        packageParams.put("sign", signAgain);
        weiXinMap.put("appid", appid);
        weiXinMap.put("partnerid", mch_id);
        weiXinMap.put("prepayid", prepay_id);
        weiXinMap.put("noncestr", uuid);
        weiXinMap.put("timestamp", seconds);
        weiXinMap.put("package", "Sign=WXPay");
        weiXinMap.put("sign", signAgain);
        return JsonReturn.toSucResult(weiXinMap);
    }
}
