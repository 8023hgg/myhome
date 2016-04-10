package home.util.des;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * Created by qijie on 2016/4/8.
 */
public class DesBUtils {

    //加密解密需要的密钥，长度要是8的倍数
    public static String CRYPT_KEY_DEFAULT = "9588028820109132570743325311898426347857298773549" +
            "468758875018579537757772163084478873699447306034466200616411960" +
            "574122434059469100235892702736860872901247123456";

    /**
     * 加密
     * @return byte[]
     */
    public static String encrypt(String datasourced,String password) {

        try{
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            BASE64Encoder decoder = new BASE64Encoder();
            return decoder.encode(cipher.doFinal(datasourced.getBytes()));
        }catch(Throwable e){
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 解密
     * @return byte[]
     * @throws Exception
     */
    public static String decrypt(String data,String password) throws Exception {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return new String(cipher.doFinal(buf));
    }

    public static void main(String args[]){

        String str = "5f647aedd039414fb5f25604a2553214";
        String result = DesBUtils.encrypt(str,CRYPT_KEY_DEFAULT);
        System.out.println("加密后："+new String(result));
        //直接将如上内容解密
        try {
            String decryResult = DesBUtils.decrypt(result,CRYPT_KEY_DEFAULT);
            System.out.println("解密后："+new String(decryResult));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
}
