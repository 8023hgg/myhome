package home.util.md5;

import java.security.MessageDigest;

/**
 * Created by qijie on 2016/3/30.
 */
public class Md5Utils {

    /**
     * MD5 加密
     * @param str
     * @return
     */
    public String encryptionString(String str){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5 = md.digest(str.getBytes());
            return new String(md5,"UTF-8");
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
