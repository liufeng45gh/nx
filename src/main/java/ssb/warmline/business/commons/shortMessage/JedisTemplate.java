//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.commons.shortMessage;

import java.util.ResourceBundle;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import ssb.warmline.business.commons.support.jedis.Executor;

@PropertySource({"classpath:redis.properties"})
public class JedisTemplate {
    private static final Logger logger = LogManager.getLogger(JedisTemplate.class);
    private static ShardedJedisPool shardedJedisPool = null;
    private static Integer EXPIRE = Integer.valueOf(ResourceBundle.getBundle("redis").getString("redis.expiration"));

    public JedisTemplate() {
    }

    private static ShardedJedis getJedis() {
        if(shardedJedisPool == null) {
            Integer var0 = EXPIRE;
            synchronized(EXPIRE) {
                if(shardedJedisPool == null) {
                    WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
                    shardedJedisPool = (ShardedJedisPool)wac.getBean(ShardedJedisPool.class);
                }
            }
        }

        return shardedJedisPool.getResource();
    }

    public static <K> K run(String key, Executor<K> executor, Integer... expire) {
        ShardedJedis jedis = getJedis();
        if(jedis == null) {
            return null;
        } else {
            try {
                K result = executor.execute(jedis);
                if(jedis.exists(key).booleanValue() && expire != null && expire.length != 0 && expire.length == 1) {
                    jedis.expire(key, expire[0].intValue());
                }

                Object var6 = result;
                return (K) var6;
            } catch (Exception var9) {
                logger.error(var9.getMessage(), var9);
            } finally {
                if(jedis != null) {
                    jedis.close();
                }

            }

            return null;
        }
    }

    public static <K> K run(byte[] key, Executor<K> executor, Integer... expire) {
        ShardedJedis jedis = getJedis();
        if(jedis == null) {
            return null;
        } else {
            try {
                K result = executor.execute(jedis);
                if(jedis.exists(key).booleanValue() && expire != null && expire.length != 0 && expire.length == 1) {
                    jedis.expire(key, expire[0].intValue());
                }

                Object var6 = result;
                return (K) var6;
            } catch (Exception var9) {
                logger.error(var9.getMessage(), var9);
            } finally {
                if(jedis != null) {
                    jedis.close();
                }

            }

            return null;
        }
    }
}
