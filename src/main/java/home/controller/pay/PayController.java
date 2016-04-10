package home.controller.pay;

import home.manager.pay.PayManager;
import home.util.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by qijie on 2016/4/8.
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayManager payManager;
    /**
     * 支付主页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "pay/index";
    }

    /**
     * 支付
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/pay")
    public String pay(Model model,HttpServletRequest request){

        String url = request.getRequestURL().toString();
        url = url.substring(0, url.indexOf("myhome")+4);//本地
//		url = "http://xfydhome.com";//线上
        String orderNo = UUIDGenerator.getUUID();
        String formHtml = payManager.pay(orderNo, "测试", 0.01D, url);

        model.addAttribute("formHtml",formHtml);
        return "pay/pay";
    }
}
