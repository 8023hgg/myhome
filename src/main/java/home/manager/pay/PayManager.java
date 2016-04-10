package home.manager.pay;

import home.Interface.pay.PayHttpRequest;
import home.common.pay.AlipayConfig;
import home.util.pay.AliPayUtils;
import org.springframework.stereotype.Component;

/**
 * 支付宝支付Manager
 * Created by qijie on 2016/4/8.
 */
@Component
public class PayManager {

    public String pay(String orderNo,String orderName,Double totalMoney,String url){
        String notifyUrl = url + "/pay/return/text-notify";
        String returnUrl = url + "/pay/return/text-return";
        PayHttpRequest payHttpRequest = new PayHttpRequest(notifyUrl, returnUrl, orderNo, orderName, totalMoney, null, null);
        return AliPayUtils.buildPayRequestFormHtml(payHttpRequest, new AlipayConfig());
    }
}
