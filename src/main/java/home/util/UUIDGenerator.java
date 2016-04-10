package home.util;

import java.util.UUID;

/**
 * 
 * @类功能说明：UUID随机数生成器
 * @创建时间：2016年1月14日上午10:35:25
 * @版本：V1.0
 *
 */
public class UUIDGenerator {
	
	 /** 
     * 获得一个UUID 
     * @return String UUID 
     */ 
    public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    } 

}
