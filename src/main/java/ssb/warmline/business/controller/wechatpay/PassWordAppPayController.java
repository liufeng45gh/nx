//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.controller.wechatpay;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ssb.warmline.business.commons.enums.Orderstatu;
import ssb.warmline.business.commons.enums.PaymentMode;
import ssb.warmline.business.commons.enums.capitalType;
import ssb.warmline.business.commons.utils.PayStatus;
import ssb.warmline.business.commons.utils.UUIDUtil;
import ssb.warmline.business.commons.utils.wechatpay.PayCommonUtil;
import ssb.warmline.business.commons.utils.wechatpay.XMLUtil;
import ssb.warmline.business.entity.wcapital.WCapitalEntity;
import ssb.warmline.business.entity.worder.WOrderEntity;
import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
import ssb.warmline.business.service.wcapital.WCapitalServiceI;
import ssb.warmline.business.service.worder.WOrderServiceI;
import ssb.warmline.business.service.worderrecord.WOrderRecordServiceI;

@Controller
@RequestMapping({"/passWordAppPay"})
public class PassWordAppPayController extends BaseController {
    private static final Logger log = Logger.getLogger(PassWordAppPayController.class);
    private ExecutorService executorService = null;
    @Autowired
    private WOrderServiceI wOrderService;
    @Autowired
    private WOrderRecordServiceI orderRecordService;
    @Autowired
    private TSBaseUserServiceI tSBaseUserService;
    @Autowired
    private WCapitalServiceI wCapitalService;

    public PassWordAppPayController() {
        this.executorService = Executors.newCachedThreadPool();
    }

    @RequestMapping(
            params = {"passWordAppWeixinNotify"}
    )
    public void passWordAppWeixinNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        StringBuffer sbu = new StringBuffer();
        InputStream inputStream = request.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

        String s;
        while((s = in.readLine()) != null) {
            sbu.append(s);
        }

        in.close();
        inputStream.close();
        new HashMap();
        Map<String, String> map = XMLUtil.doXMLParse(sbu.toString());
        SortedMap<Object, Object> packageParams = new TreeMap();

        String parameter;
        String resXml;
        String outTradeNo;
        for(Iterator it = map.keySet().iterator(); it.hasNext(); packageParams.put(parameter, outTradeNo)) {
            parameter = (String)it.next();
            resXml = (String)map.get(parameter);
            outTradeNo = "";
            if(resXml != null) {
                outTradeNo = resXml.trim();
            }
        }

        log.info(packageParams);
        parameter = "NUANXingapiKey6352ERyusiJanDaoNo";
        if(PayCommonUtil.isTenpaySign("UTF-8", packageParams, parameter)) {
            resXml = "";
            if("SUCCESS".equals((String)packageParams.get("result_code"))) {
                outTradeNo = (String)packageParams.get("out_trade_no");
                String var13 = (String)packageParams.get("attach");

                try {
                    WOrderEntity order = (WOrderEntity)this.wOrderService.findUniqueByProperty(WOrderEntity.class, "orderNumber", outTradeNo);
                    order.setOrderStatus(Orderstatu.ORDERSTATU_001.toString());
                    order.setBuyStatus(PayStatus.PAY_002.toString());
                    order.setPaymentMode(PaymentMode.weChat.toString());
                    this.wOrderService.saveOrUpdate(order);
                    this.orderRecordService.updateBySqlString("update WOrderRecordEntity set buyStatus='" + PayStatus.PAY_002.toString() + "', paymentMode='" + PaymentMode.weChat.toString() + "' where orderId='" + outTradeNo + "'");
                } catch (Exception var15) {
                    log.info("支付失败,错误信息：" + packageParams.get("err_code"));
                    resXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml> ";
                    var15.getStackTrace();
                }

                resXml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml> ";
            } else {
                log.info("支付失败,错误信息：" + packageParams.get("err_code"));
                resXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml> ";
            }

            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
        } else {
            log.info("通知签名验证失败");
        }

    }

    @RequestMapping(
            params = {"giveReward_AppWeixinNotify"}
    )
    public void giveReward_AppWeixinNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        StringBuffer sbu = new StringBuffer();
        InputStream inputStream = request.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

        String s;
        while((s = in.readLine()) != null) {
            sbu.append(s);
        }

        in.close();
        inputStream.close();
        new HashMap();
        Map<String, String> map = XMLUtil.doXMLParse(sbu.toString());
        SortedMap<Object, Object> packageParams = new TreeMap();

        String parameter;
        String resXml;
        String orderId;
        for(Iterator it = map.keySet().iterator(); it.hasNext(); packageParams.put(parameter, orderId)) {
            parameter = (String)it.next();
            resXml = (String)map.get(parameter);
            orderId = "";
            if(resXml != null) {
                orderId = resXml.trim();
            }
        }

        log.info(packageParams);
        parameter = "NUANXingapiKey6352ERyusiJanDaoNo";
        if(PayCommonUtil.isTenpaySign("UTF-8", packageParams, parameter)) {
            resXml = "";
            if("SUCCESS".equals((String)packageParams.get("result_code"))) {
                orderId = (String)packageParams.get("attach");
                String total_fee_str = request.getParameter("total_fee");
                BigDecimal bigIntegerTotal = new BigDecimal(total_fee_str);
                BigDecimal param = new BigDecimal(100);
                double total_fee_temp = bigIntegerTotal.divide(param).doubleValue();
                String total_fee = String.valueOf(total_fee_temp);

                try {
                    WOrderEntity orderEntity = (WOrderEntity)this.wOrderService.getEntity(WOrderEntity.class, orderId);
                    String orderPersonId = orderEntity.getOrderPersonId();
                    String issuerId = orderEntity.getIssuerId();
                    TSBaseUser tSBaseUser = (TSBaseUser)this.tSBaseUserService.getEntity(TSBaseUser.class, orderPersonId);
                    TSBaseUser tSBaseUser_issuer = (TSBaseUser)this.tSBaseUserService.getEntity(TSBaseUser.class, issuerId);
                    String balance_temp = tSBaseUser.getBalance();
                    if(balance_temp == null || "".equals(balance_temp)) {
                        balance_temp = "0";
                    }

                    BigDecimal toalFee = new BigDecimal(total_fee);
                    BigDecimal balance2 = new BigDecimal(balance_temp);
                    String balance = balance2.add(toalFee).toString();
                    tSBaseUser.setBalance(balance);
                    this.tSBaseUserService.saveOrUpdate(tSBaseUser);
                    WCapitalEntity wCapital = new WCapitalEntity();
                    wCapital.setId(UUIDUtil.getId());
                    wCapital.setAmout(toalFee.toString());
                    wCapital.setUserId(orderPersonId);
                    wCapital.setUserName(tSBaseUser.getUserName());
                    wCapital.setPhone(tSBaseUser.getPhone());
                    wCapital.setTradeTime(new Date());
                    wCapital.setDescription("打赏收入");
                    wCapital.setOrderId(orderId);
                    wCapital.setType(capitalType.income.toString());
                    this.wCapitalService.save(wCapital);
                    WCapitalEntity wCapitalA = new WCapitalEntity();
                    wCapitalA.setId(UUIDUtil.getId());
                    wCapitalA.setAmout(toalFee.toString());
                    wCapitalA.setUserId(issuerId);
                    wCapitalA.setUserName(tSBaseUser_issuer.getUserName());
                    wCapitalA.setPhone(tSBaseUser_issuer.getPhone());
                    wCapitalA.setTradeTime(new Date());
                    wCapitalA.setDescription("打赏支出");
                    wCapitalA.setOrderId(orderId);
                    wCapitalA.setType(capitalType.expenditure.toString());
                    this.wCapitalService.save(wCapitalA);
                } catch (Exception var30) {
                    log.info("支付失败,错误信息：" + packageParams.get("err_code"));
                    resXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml> ";
                    var30.getStackTrace();
                }

                resXml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml> ";
            } else {
                log.info("支付失败,错误信息：" + packageParams.get("err_code"));
                resXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml> ";
            }

            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
        } else {
            log.info("通知签名验证失败");
        }

    }
}
