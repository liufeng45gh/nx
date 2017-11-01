//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.commons.config;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ssb.warmline.business.commons.enums.Orderstatu;
import ssb.warmline.business.commons.enums.PaymentMode;
import ssb.warmline.business.commons.enums.capitalType;
import ssb.warmline.business.commons.shortMessage.PhoneCode;
import ssb.warmline.business.commons.utils.PayStatus;
import ssb.warmline.business.commons.utils.wechatpay.PayCommonUtil;
import ssb.warmline.business.commons.utils.wechatpay.XMLUtil;
import ssb.warmline.business.entity.wcapital.WCapitalEntity;
import ssb.warmline.business.entity.worder.WOrderEntity;
import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
import ssb.warmline.business.service.wcapital.WCapitalServiceI;
import ssb.warmline.business.service.worder.WOrderServiceI;
import ssb.warmline.business.service.worderrecord.WOrderRecordServiceI;
import ssb.warmline.business.service.wterritory.WTerritoryBusinessServiceI;

public class WeChatAppPayServlet extends HttpServlet {
    protected static final Logger log = LoggerFactory.getLogger(WeChatAppPayServlet.class);
    private ApplicationContext context = null;

    public WeChatAppPayServlet() {
    }

    public void init() throws ServletException {
        this.context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.passWordAppWeixinNotify(req, resp);
    }

    public void passWordAppWeixinNotify(HttpServletRequest request, HttpServletResponse response) {
        WOrderServiceI wOrderService = (WOrderServiceI)this.context.getBean(WOrderServiceI.class);
        WTerritoryBusinessServiceI wTerritoryBusinessService = (WTerritoryBusinessServiceI)this.context.getBean(WTerritoryBusinessServiceI.class);
        TSBaseUserServiceI tsBaseUserService = (TSBaseUserServiceI)this.context.getBean(TSBaseUserServiceI.class);
        WOrderRecordServiceI orderRecordService = (WOrderRecordServiceI)this.context.getBean(WOrderRecordServiceI.class);
        WCapitalServiceI wCapitalService = (WCapitalServiceI)this.context.getBean(WCapitalServiceI.class);
        StringBuffer sbu = new StringBuffer();

        try {
            InputStream inputStream = request.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String s;
            while((s = in.readLine()) != null) {
                sbu.append(s);
            }

            in.close();
            inputStream.close();
        } catch (IOException var28) {
            var28.printStackTrace();
        }

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

        parameter = "NUANXingapiKey6352ERyusiJanDaoNo";
        if(PayCommonUtil.isTenpaySign("UTF-8", packageParams, parameter)) {
            resXml = "";
            if("SUCCESS".equals((String)packageParams.get("result_code"))) {
                outTradeNo = (String)packageParams.get("out_trade_no");
                String var16 = (String)packageParams.get("attach");

                try {
                    WOrderEntity order = (WOrderEntity)wOrderService.findUniqueByProperty(WOrderEntity.class, "orderNumber", outTradeNo);
                    order.setOrderStatus(Orderstatu.ORDERSTATU_001.toString());
                    order.setBuyStatus(PayStatus.PAY_002.toString());
                    order.setPaymentMode(PaymentMode.weChat.toString());
                    order.setPaymentTime(new Date());
                    wOrderService.saveOrUpdate(order);
                    orderRecordService.updateBySqlString("update w_order_record set buy_status='" + PayStatus.PAY_002.toString() + "', payment_mode='" + PaymentMode.weChat.toString() + "',order_status='" + Orderstatu.ORDERSTATU_001.toString() + "',status_time=now() where order_Number='" + outTradeNo + "'");
                    WCapitalEntity capital = new WCapitalEntity();
                    capital.setUserId(order.getIssuerId());
                    capital.setUserName(order.getIssuer());
                    capital.setPhone(order.getPhone());
                    capital.setOrderId(order.getId());
                    capital.setOrderNumber(order.getOrderNumber());
                    capital.setAmout(order.getPrice());
                    capital.setPayMethod(PaymentMode.weChat.toString());
                    capital.setType(capitalType.expenditure.toString());
                    capital.setDescription("发布订单支出");
                    capital.setTradeTime(new Date());
                    wCapitalService.save(capital);
                    if("0".equals(order.getSeekStatus())) {
                        String city = order.getCity();
                        if(!"".equals(city) && city != null) {
                            String territoryId = order.getTerritoryId();
                            List<TSBaseUser> tsBaseUser = tsBaseUserService.findByProperty(TSBaseUser.class, "territoryId", territoryId);

                            for(int i = 0; i < tsBaseUser.size(); ++i) {
                                TSBaseUser tsBaseUser2 = (TSBaseUser)tsBaseUser.get(i);
                                if(!"".equals(tsBaseUser2.getPhone()) && tsBaseUser2.getPhone() != null && tsBaseUser2.getAreaCode() != null && !"".equals(tsBaseUser2.getAreaCode())) {
                                    String phones = tsBaseUser2.getAreaCode() + tsBaseUser2.getPhone();
                                    String content = "【暖行】 你所负责的" + city + "区有人发布紧急订单寻求帮助，订单号为： " + outTradeNo + ", 详情请登录系统进行查看。";
                                    PhoneCode.getLoginPhoneOrderCode(phones, content);
                                }
                            }
                        }
                    }
                } catch (Exception var27) {
                    log.info("支付失败,错误信息：" + packageParams.get("err_code"));
                    resXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml> ";
                    var27.getStackTrace();
                }

                resXml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml> ";
            } else {
                log.info("支付失败,错误信息：" + packageParams.get("err_code"));
                resXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml> ";
            }

            try {
                BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
                out.write(resXml.getBytes());
                out.flush();
                out.close();
            } catch (IOException var26) {
                var26.printStackTrace();
            }
        } else {
            log.info("通知签名验证失败");
        }

    }
}
