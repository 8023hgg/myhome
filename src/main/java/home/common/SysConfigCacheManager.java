package home.common;

import com.alibaba.fastjson.JSON;
import home.common.system.SysConfig;
import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by qijie on 2016/4/5.
 */
public class SysConfigCacheManager {

    private JedisPool jedisPool;

    public SysConfigCacheManager() {
    }

    public Map<String, SysConfig> getAllSysConfig() {
        Jedis jedis = (Jedis)this.jedisPool.getResource();
        if(!jedis.exists("_SYS_CONFIG_").booleanValue()) {
            return new HashMap();
        } else {
            Map jsonMap = jedis.hgetAll("_SYS_CONFIG_");
            HashMap allSysConfig = new HashMap();
            Iterator i$ = jsonMap.keySet().iterator();

            while(i$.hasNext()) {
                String key = (String)i$.next();
                SysConfig sc = (SysConfig) JSON.parseObject((String) jsonMap.get(key), SysConfig.class);
                allSysConfig.put(key, sc);
            }

            return allSysConfig;
        }
    }

    public void updateAllSysConfig(Map<String, SysConfig> allSysConfig) {
        if(allSysConfig != null) {
            Jedis jedis = (Jedis)this.jedisPool.getResource();
            HashMap jsonMap = new HashMap();
            Iterator i$ = allSysConfig.keySet().iterator();

            while(i$.hasNext()) {
                String key = (String)i$.next();
                jsonMap.put(key, JSON.toJSONString(allSysConfig.get(key)));
            }

            jedis.del(new String[]{"_SYS_CONFIG_"});
            jedis.hmset("_SYS_CONFIG_", jsonMap);
            jedis.bgsave();
        }

    }

    public void putSysConfig(SysConfig sysConfig) {
        if(sysConfig != null) {
            Jedis jedis = (Jedis)this.jedisPool.getResource();
            jedis.hset("_SYS_CONFIG_", sysConfig.getConfName(), JSON.toJSONString(sysConfig));
            jedis.bgsave();
        }

    }

    public void removeSysConfigByName(String confName) {
        if(StringUtils.isNotBlank(confName)) {
            Jedis jedis = (Jedis)this.jedisPool.getResource();
            jedis.hdel("_SYS_CONFIG_", new String[]{confName});
            jedis.bgsave();
        }

    }

    public void removeSysConfigById(String id) {
        if(id != null) {
            Jedis jedis = (Jedis)this.jedisPool.getResource();
            List jsonList = jedis.hvals("_SYS_CONFIG_");
            Iterator i$ = jsonList.iterator();

            while(i$.hasNext()) {
                String json = (String)i$.next();
                SysConfig sysConfig = (SysConfig)JSON.parseObject(json, SysConfig.class);
                if(id.equals(sysConfig.getId())) {
                    jedis.hdel("_SYS_CONFIG_", new String[]{sysConfig.getConfName()});
                    jedis.bgsave();
                    break;
                }
            }
        }

    }

    public SysConfig getConfig(String confName) {
        if(StringUtils.isNotBlank(confName)) {
            Jedis jedis = (Jedis)this.jedisPool.getResource();
            String json = jedis.hget("_SYS_CONFIG_", confName);
            if(StringUtils.isNotBlank(json)) {
                return (SysConfig)JSON.parseObject(json, SysConfig.class);
            }
        }

        return null;
    }

    public JedisPool getJedisPool() {
        return this.jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
