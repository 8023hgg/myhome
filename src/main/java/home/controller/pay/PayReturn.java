package home.controller.pay;

import home.common.pay.AlipayConfig;
import home.common.pay.PayNotify;
import home.util.pay.AliPayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by qijie on 2016/4/8.
 */
@Controller
@RequestMapping("/pay/return")
public class PayReturn {
    /**
     *
     * @方法功能说明：接收支付宝异步通知
     */
    @RequestMapping(value="/text-notify")
    @ResponseBody
    public String notify(Model model,HttpServletRequest request){

        try {
            // 检查并封装支付宝请求
//			Map<String, String> alipayParams = AliPayUtils.alipayNotify(request, new AlipayConfig(), true);
//			PayNotify payNotify = new PayNotify(alipayParams);
            //后台数据处理，比如生成支付订单处理
            // 成功返回
            return "success";
        } catch (Exception e) {
            // 处理异常
            return "fail";
        }
    }

    /**
     *
     * @方法功能说明：接收支付宝同步通知
     */
    @RequestMapping(value="/text-return")
    public String returns(Model model,HttpServletRequest request){

        // 检查并封装支付宝返回的结果信息
        Map<String, String> alipayParams = AliPayUtils.alipayNotify(request, new AlipayConfig(), false);
        PayNotify payNotify = new PayNotify(alipayParams);
        //跳转到支付成功的页面
        model.addAttribute("orderNo", payNotify.getTradeNo());
        model.addAttribute("message", "支付成功");

        return "pay/success";
    }
}
