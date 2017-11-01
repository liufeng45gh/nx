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
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import ssb.warmline.business.commons.enums.capitalType;
import ssb.warmline.business.commons.utils.UUIDUtil;
import ssb.warmline.business.commons.utils.wechatpay.PayCommonUtil;
import ssb.warmline.business.commons.utils.wechatpay.XMLUtil;
import ssb.warmline.business.entity.wcapital.WCapitalEntity;
import ssb.warmline.business.entity.worder.WOrderEntity;
import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
import ssb.warmline.business.service.wcapital.WCapitalServiceI;
import ssb.warmline.business.service.worder.WOrderServiceI;

public class GiveRewardWeChatAppPayServlet extends HttpServlet {
    protected static final Logger log = LoggerFactory.getLogger(GiveRewardWeChatAppPayServlet.class);
    private ApplicationContext context = null;

    public GiveRewardWeChatAppPayServlet() {
    }

    public void init() throws ServletException {
        this.context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.giveReward_AppWeixinNotify(req, resp);
    }

    public void giveReward_AppWeixinNotify(HttpServletRequest request, HttpServletResponse response) {
        WOrderServiceI wOrderService = (WOrderServiceI)this.context.getBean(WOrderServiceI.class);
        WCapitalServiceI wCapitalService = (WCapitalServiceI)this.context.getBean(WCapitalServiceI.class);
        TSBaseUserServiceI tSBaseUserService = (TSBaseUserServiceI)this.context.getBean(TSBaseUserServiceI.class);
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
        } catch (IOException var33) {
            var33.printStackTrace();
        }

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

        parameter = "NUANXingapiKey6352ERyusiJanDaoNo";
        if(PayCommonUtil.isTenpaySign("UTF-8", packageParams, parameter)) {
            resXml = "";
            if("SUCCESS".equals((String)packageParams.get("result_code"))) {
                orderId = (String)packageParams.get("attach");
                String total_fee_str = (String)packageParams.get("total_fee");

                try {
                    BigDecimal bigIntegerTotal = new BigDecimal(total_fee_str);
                    BigDecimal param = new BigDecimal(100);
                    double total_fee_temp = bigIntegerTotal.divide(param).doubleValue();
                    String total_fee = String.valueOf(total_fee_temp);
                    WOrderEntity orderEntity = (WOrderEntity)wOrderService.getEntity(WOrderEntity.class, orderId);
                    String orderPersonId = orderEntity.getOrderPersonId();
                    String issuerId = orderEntity.getIssuerId();
                    TSBaseUser tSBaseUser = (TSBaseUser)tSBaseUserService.getEntity(TSBaseUser.class, orderPersonId);
                    TSBaseUser tSBaseUser_issuer = (TSBaseUser)tSBaseUserService.getEntity(TSBaseUser.class, issuerId);
                    String balance_temp = tSBaseUser.getBalance();
                    if(balance_temp == null || "".equals(balance_temp)) {
                        balance_temp = "0";
                    }

                    BigDecimal toalFee = new BigDecimal(total_fee);
                    BigDecimal balance2 = new BigDecimal(balance_temp);
                    String balance = balance2.add(toalFee).toString();
                    tSBaseUser.setBalance(balance);
                    tSBaseUserService.saveOrUpdate(tSBaseUser);
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
                    wCapitalService.save(wCapital);
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
                    wCapitalService.save(wCapitalA);
                } catch (Exception var32) {
                    log.info("支付失败,错误信息：" + packageParams.get("err_code"));
                    resXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml> ";
                    var32.getStackTrace();
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
            } catch (IOException var31) {
                var31.printStackTrace();
            }
        } else {
            log.info("通知签名验证失败");
        }

    }
}
