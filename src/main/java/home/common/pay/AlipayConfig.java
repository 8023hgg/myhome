package home.common.pay;

import org.springframework.stereotype.Component;

/**
 * 支付配置
 * Created by qijie on 2016/4/8.
 */
@Component
public class AlipayConfig {


    // ------------------ 固定配置 ------------------

    /**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    public static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";

    /**
     * 支付宝消息验证地址
     */
    public static final String HTTPS_VERIFY_URL = ALIPAY_GATEWAY_NEW + "service=notify_verify&";

    /**
     * 字符编码格式 目前支持 gbk 或 utf-8
     */
    public static final String INPUT_CHARSET = "utf-8";

    /**
     * 签名方式 不需修改
     */
    public static final String SIGN_TYPE = "MD5";

    // ------------------ 自定义配置 ------------------

    /**
     * 合作身份者ID，以2088开头由16位纯数字组成的字符串
     */
    private String partner = "2088611359544680";

    /**
     * 商户的私钥
     */
    private String key = "l53339pfhv07foem56hx2ayue427u3og";

    /**
     * 卖家支付宝帐户
     */
    private String sellerEmail = "ply365@ply365.com";

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }
}
