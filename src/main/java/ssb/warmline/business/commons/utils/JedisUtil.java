//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.commons.utils;

import com.alibaba.fastjson.JSON;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.BinaryClient.LIST_POSITION;
import ssb.warmline.business.commons.support.jedis.Executor;
import ssb.warmline.business.commons.support.jedis.JedisTemplate;
import ssb.warmline.business.commons.support.spring.data.redis.ObjectRedisSerializer;

public final class JedisUtil {
    private JedisUtil() {
    }

    public static final String get(final String key, Integer... expire) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.get(key);
            }
        }, expire);
    }

    public static final String set(final String key, final Integer seconds, final Object value) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.setex(key, seconds.intValue(), JSON.toJSONString(value));
            }
        }, new Integer[]{seconds, seconds});
    }

    public static final String set(final String key, final Object value) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.set(key, JSON.toJSONString(value));
            }
        }, new Integer[0]);
    }

    public static final Boolean exists(final String key) {
        return (Boolean)JedisTemplate.run(key, new Executor<Boolean>() {
            public Boolean execute(ShardedJedis jedis) {
                return jedis.exists(key);
            }
        }, new Integer[0]);
    }

    public static final Long del(final String key) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.del(key);
            }
        }, new Integer[0]);
    }

    public static final String type(final String key) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.type(key);
            }
        }, new Integer[0]);
    }

    public static final Long expire(final String key, final int seconds) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.expire(key, seconds);
            }
        }, new Integer[]{Integer.valueOf(seconds), Integer.valueOf(seconds)});
    }

    public static final Long expireAt(final String key, final long unixTime) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.expireAt(key, unixTime);
            }
        }, new Integer[0]);
    }

    public static final Long ttl(final String key) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.ttl(key);
            }
        }, new Integer[0]);
    }

    public static final Boolean setbit(final String key, final long offset, final boolean value) {
        return (Boolean)JedisTemplate.run(key, new Executor<Boolean>() {
            public Boolean execute(ShardedJedis jedis) {
                return jedis.setbit(key, offset, value);
            }
        }, new Integer[0]);
    }

    public static final Boolean getbit(final String key, final long offset) {
        return (Boolean)JedisTemplate.run(key, new Executor<Boolean>() {
            public Boolean execute(ShardedJedis jedis) {
                return jedis.getbit(key, offset);
            }
        }, new Integer[0]);
    }

    public static final Long setrange(final String key, final long offset, final String value) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.setrange(key, offset, value);
            }
        }, new Integer[0]);
    }

    public static final String getrange(final String key, final long startOffset, final long endOffset) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.getrange(key, startOffset, endOffset);
            }
        }, new Integer[0]);
    }

    public static final String getSet(final String key, final String value) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.getSet(key, value);
            }
        }, new Integer[0]);
    }

    public static final Long setnx(final String key, final String value) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.setnx(key, value);
            }
        }, new Integer[0]);
    }

    public static final String setex(final String key, final int seconds, final String value) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.setex(key, seconds, value);
            }
        }, new Integer[]{Integer.valueOf(seconds), Integer.valueOf(seconds)});
    }

    public static final Long decrBy(final String key, final long integer) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.decrBy(key, integer);
            }
        }, new Integer[0]);
    }

    public static final Long decr(final String key) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.decr(key);
            }
        }, new Integer[0]);
    }

    public static final Long incrBy(final String key, final long integer) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.incrBy(key, integer);
            }
        }, new Integer[0]);
    }

    public static final Long incr(final String key) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.incr(key);
            }
        }, new Integer[0]);
    }

    public static final Long append(final String key, final String value) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.append(key, value);
            }
        }, new Integer[0]);
    }

    public static final String substr(final String key, final int start, final int end) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.substr(key, start, end);
            }
        }, new Integer[0]);
    }

    public static final Long hset(final String key, final String field, final String value) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.hset(key, field, value);
            }
        }, new Integer[0]);
    }

    public static final String hget(final String key, final String field) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.hget(key, field);
            }
        }, new Integer[0]);
    }

    public static final Long hsetnx(final String key, final String field, final String value) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.hsetnx(key, field, value);
            }
        }, new Integer[0]);
    }

    public static final String hmset(final String key, final Map<String, String> hash) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.hmset(key, hash);
            }
        }, new Integer[0]);
    }

    public static final List<String> hmget(final String key, final String... fields) {
        return (List)JedisTemplate.run(key, new Executor<List<String>>() {
            public List<String> execute(ShardedJedis jedis) {
                return jedis.hmget(key, fields);
            }
        }, new Integer[0]);
    }

    public static final Long hincrBy(final String key, final String field, final long value) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.hincrBy(key, field, value);
            }
        }, new Integer[0]);
    }

    public static final Boolean hexists(final String key, final String field) {
        return (Boolean)JedisTemplate.run(key, new Executor<Boolean>() {
            public Boolean execute(ShardedJedis jedis) {
                return jedis.hexists(key, field);
            }
        }, new Integer[0]);
    }

    public static final Long hdel(final String key, final String field) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.hdel(key, new String[]{field});
            }
        }, new Integer[0]);
    }

    public static final Long hlen(final String key) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.hlen(key);
            }
        }, new Integer[0]);
    }

    public static final Set<String> hkeys(final String key) {
        return (Set)JedisTemplate.run(key, new Executor<Set<String>>() {
            public Set<String> execute(ShardedJedis jedis) {
                return jedis.hkeys(key);
            }
        }, new Integer[0]);
    }

    public static final List<String> hvals(final String key) {
        return (List)JedisTemplate.run(key, new Executor<List<String>>() {
            public List<String> execute(ShardedJedis jedis) {
                return jedis.hvals(key);
            }
        }, new Integer[0]);
    }

    public static final Map<String, String> hgetAll(final String key) {
        return (Map)JedisTemplate.run(key, new Executor<Map<String, String>>() {
            public Map<String, String> execute(ShardedJedis jedis) {
                return jedis.hgetAll(key);
            }
        }, new Integer[0]);
    }

    public static final Long rpush(final String key, final String string) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.rpush(key, new String[]{string});
            }
        }, new Integer[0]);
    }

    public static final Long lpush(final String key, final String string) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.lpush(key, new String[]{string});
            }
        }, new Integer[0]);
    }

    public static final Long llen(final String key) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.llen(key);
            }
        }, new Integer[0]);
    }

    public static final List<String> lrange(final String key, final long start, final long end) {
        return (List)JedisTemplate.run(key, new Executor<List<String>>() {
            public List<String> execute(ShardedJedis jedis) {
                return jedis.lrange(key, start, end);
            }
        }, new Integer[0]);
    }

    public static final String ltrim(final String key, final long start, final long end) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.ltrim(key, start, end);
            }
        }, new Integer[0]);
    }

    public static final String lindex(final String key, final long index) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.lindex(key, index);
            }
        }, new Integer[0]);
    }

    public static final String lset(final String key, final long index, final String value) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.lset(key, index, value);
            }
        }, new Integer[0]);
    }

    public static final Long lrem(final String key, final long count, final String value) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.lrem(key, count, value);
            }
        }, new Integer[0]);
    }

    public static final String lpop(final String key) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.lpop(key);
            }
        }, new Integer[0]);
    }

    public static final String rpop(final String key) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.rpop(key);
            }
        }, new Integer[0]);
    }

    public static final Long sadd(final String key, final String member) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.sadd(key, new String[]{member});
            }
        }, new Integer[0]);
    }

    public static final Set<String> smembers(final String key) {
        return (Set)JedisTemplate.run(key, new Executor<Set<String>>() {
            public Set<String> execute(ShardedJedis jedis) {
                return jedis.smembers(key);
            }
        }, new Integer[0]);
    }

    public static final Long srem(final String key, final String member) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.srem(key, new String[]{member});
            }
        }, new Integer[0]);
    }

    public static final String spop(final String key) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.spop(key);
            }
        }, new Integer[0]);
    }

    public static final Long scard(final String key) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.scard(key);
            }
        }, new Integer[0]);
    }

    public static final Boolean sismember(final String key, final String member) {
        return (Boolean)JedisTemplate.run(key, new Executor<Boolean>() {
            public Boolean execute(ShardedJedis jedis) {
                return jedis.sismember(key, member);
            }
        }, new Integer[0]);
    }

    public static final String srandmember(final String key) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.srandmember(key);
            }
        }, new Integer[0]);
    }

    public static final Long zadd(final String key, final double score, final String member) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.zadd(key, score, member);
            }
        }, new Integer[0]);
    }

    public static final Set<String> zrange(final String key, final int start, final int end) {
        return (Set)JedisTemplate.run(key, new Executor<Set<String>>() {
            public Set<String> execute(ShardedJedis jedis) {
                return jedis.zrange(key, (long)start, (long)end);
            }
        }, new Integer[0]);
    }

    public static final Long zrem(final String key, final String member) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.zrem(key, new String[]{member});
            }
        }, new Integer[0]);
    }

    public static final Double zincrby(final String key, final double score, final String member) {
        return (Double)JedisTemplate.run(key, new Executor<Double>() {
            public Double execute(ShardedJedis jedis) {
                return jedis.zincrby(key, score, member);
            }
        }, new Integer[0]);
    }

    public static final Long zrank(final String key, final String member) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.zrank(key, member);
            }
        }, new Integer[0]);
    }

    public static final Long zrevrank(final String key, final String member) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.zrevrank(key, member);
            }
        }, new Integer[0]);
    }

    public static final Set<String> zrevrange(final String key, final int start, final int end) {
        return (Set)JedisTemplate.run(key, new Executor<Set<String>>() {
            public Set<String> execute(ShardedJedis jedis) {
                return jedis.zrevrange(key, (long)start, (long)end);
            }
        }, new Integer[0]);
    }

    public static final Set<Tuple> zrangeWithScores(final String key, final int start, final int end) {
        return (Set)JedisTemplate.run(key, new Executor<Set<Tuple>>() {
            public Set<Tuple> execute(ShardedJedis jedis) {
                return jedis.zrangeWithScores(key, (long)start, (long)end);
            }
        }, new Integer[0]);
    }

    public static final Set<Tuple> zrevrangeWithScores(final String key, final int start, final int end) {
        return (Set)JedisTemplate.run(key, new Executor<Set<Tuple>>() {
            public Set<Tuple> execute(ShardedJedis jedis) {
                return jedis.zrevrangeWithScores(key, (long)start, (long)end);
            }
        }, new Integer[0]);
    }

    public static final Long zcard(final String key) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.zcard(key);
            }
        }, new Integer[0]);
    }

    public static final Double zscore(final String key, final String member) {
        return (Double)JedisTemplate.run(key, new Executor<Double>() {
            public Double execute(ShardedJedis jedis) {
                return jedis.zscore(key, member);
            }
        }, new Integer[0]);
    }

    public static final List<String> sort(final String key) {
        return (List)JedisTemplate.run(key, new Executor<List<String>>() {
            public List<String> execute(ShardedJedis jedis) {
                return jedis.sort(key);
            }
        }, new Integer[0]);
    }

    public static final List<String> sort(final String key, final SortingParams sortingParameters) {
        return (List)JedisTemplate.run(key, new Executor<List<String>>() {
            public List<String> execute(ShardedJedis jedis) {
                return jedis.sort(key, sortingParameters);
            }
        }, new Integer[0]);
    }

    public static final Long zcount(final String key, final double min, final double max) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.zcount(key, min, max);
            }
        }, new Integer[0]);
    }

    public static final Set<String> zrangeByScore(final String key, final double min, final double max) {
        return (Set)JedisTemplate.run(key, new Executor<Set<String>>() {
            public Set<String> execute(ShardedJedis jedis) {
                return jedis.zrangeByScore(key, min, max);
            }
        }, new Integer[0]);
    }

    public static final Set<String> zrevrangeByScore(final String key, final double max, final double min) {
        return (Set)JedisTemplate.run(key, new Executor<Set<String>>() {
            public Set<String> execute(ShardedJedis jedis) {
                return jedis.zrevrangeByScore(key, max, min);
            }
        }, new Integer[0]);
    }

    public static final Set<String> zrangeByScore(final String key, final double min, final double max, final int offset, final int count) {
        return (Set)JedisTemplate.run(key, new Executor<Set<String>>() {
            public Set<String> execute(ShardedJedis jedis) {
                return jedis.zrangeByScore(key, min, max, offset, count);
            }
        }, new Integer[0]);
    }

    public static final Set<String> zrevrangeByScore(final String key, final double max, final double min, final int offset, final int count) {
        return (Set)JedisTemplate.run(key, new Executor<Set<String>>() {
            public Set<String> execute(ShardedJedis jedis) {
                return jedis.zrevrangeByScore(key, max, min, offset, count);
            }
        }, new Integer[0]);
    }

    public static final Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max) {
        return (Set)JedisTemplate.run(key, new Executor<Set<Tuple>>() {
            public Set<Tuple> execute(ShardedJedis jedis) {
                return jedis.zrangeByScoreWithScores(key, min, max);
            }
        }, new Integer[0]);
    }

    public static final Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min) {
        return (Set)JedisTemplate.run(key, new Executor<Set<Tuple>>() {
            public Set<Tuple> execute(ShardedJedis jedis) {
                return jedis.zrevrangeByScoreWithScores(key, max, min);
            }
        }, new Integer[0]);
    }

    public static final Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max, final int offset, final int count) {
        return (Set)JedisTemplate.run(key, new Executor<Set<Tuple>>() {
            public Set<Tuple> execute(ShardedJedis jedis) {
                return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
            }
        }, new Integer[0]);
    }

    public static final Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min, final int offset, final int count) {
        return (Set)JedisTemplate.run(key, new Executor<Set<Tuple>>() {
            public Set<Tuple> execute(ShardedJedis jedis) {
                return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
            }
        }, new Integer[0]);
    }

    public static final Long zremrangeByRank(final String key, final int start, final int end) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.zremrangeByRank(key, (long)start, (long)end);
            }
        }, new Integer[0]);
    }

    public static final Long zremrangeByScore(final String key, final double start, final double end) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.zremrangeByScore(key, start, end);
            }
        }, new Integer[0]);
    }

    public static final Long linsert(final String key, final LIST_POSITION where, final String pivot, final String value) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.linsert(key, where, pivot, value);
            }
        }, new Integer[0]);
    }

    public static final String set(final byte[] key, final byte[] value, Integer... expire) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.set(key, value);
            }
        }, expire);
    }

    public static final byte[] get(final byte[] key, Integer... expire) {
        return (byte[])JedisTemplate.run(key, new Executor<byte[]>() {
            public byte[] execute(ShardedJedis jedis) {
                return jedis.get(key);
            }
        }, expire);
    }

    public static final Boolean exists(final byte[] key) {
        return (Boolean)JedisTemplate.run(key, new Executor<Boolean>() {
            public Boolean execute(ShardedJedis jedis) {
                return jedis.exists(key);
            }
        }, new Integer[0]);
    }

    public static final String type(final byte[] key) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.type(key);
            }
        }, new Integer[0]);
    }

    public static final Long expire(final byte[] key, final int seconds) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.expire(key, seconds);
            }
        }, new Integer[]{Integer.valueOf(seconds), Integer.valueOf(seconds)});
    }

    public static final Long expireAt(final byte[] key, final long unixTime) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.expireAt(key, unixTime);
            }
        }, new Integer[0]);
    }

    public static final Long ttl(final byte[] key) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.ttl(key);
            }
        }, new Integer[0]);
    }

    public static final Long del(final byte[] key) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.del(key);
            }
        }, new Integer[0]);
    }

    public byte[] getSet(final byte[] key, final byte[] value) {
        return (byte[])JedisTemplate.run(key, new Executor<byte[]>() {
            public byte[] execute(ShardedJedis jedis) {
                return jedis.getSet(key, value);
            }
        }, new Integer[0]);
    }

    public static Long setnx(final byte[] key, final byte[] value) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.setnx(key, value);
            }
        }, new Integer[0]);
    }

    public static String setex(final byte[] key, final int seconds, final byte[] value) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.setex(key, seconds, value);
            }
        }, new Integer[]{Integer.valueOf(seconds), Integer.valueOf(seconds)});
    }

    public static Long decrBy(final byte[] key, final long integer) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.decrBy(key, integer);
            }
        }, new Integer[0]);
    }

    public static Long decr(final byte[] key) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.decr(key);
            }
        }, new Integer[0]);
    }

    public static Long incrBy(final byte[] key, final long integer) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.incrBy(key, integer);
            }
        }, new Integer[0]);
    }

    public static Long incr(final byte[] key) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.incr(key);
            }
        }, new Integer[0]);
    }

    public static Long append(final byte[] key, final byte[] value) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.append(key, value);
            }
        }, new Integer[0]);
    }

    public static byte[] substr(final byte[] key, final int start, final int end) {
        return (byte[])JedisTemplate.run(key, new Executor<byte[]>() {
            public byte[] execute(ShardedJedis jedis) {
                return jedis.substr(key, start, end);
            }
        }, new Integer[0]);
    }

    public static Long hset(final byte[] key, final byte[] field, final byte[] value) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.hset(key, field, value);
            }
        }, new Integer[0]);
    }

    public static byte[] hget(final byte[] key, final byte[] field) {
        return (byte[])JedisTemplate.run(key, new Executor<byte[]>() {
            public byte[] execute(ShardedJedis jedis) {
                return jedis.hget(key, field);
            }
        }, new Integer[0]);
    }

    public static Long hsetnx(final byte[] key, final byte[] field, final byte[] value) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.hsetnx(key, field, value);
            }
        }, new Integer[0]);
    }

    public static String hmset(final byte[] key, final Map<byte[], byte[]> hash) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.hmset(key, hash);
            }
        }, new Integer[0]);
    }

    public static List<byte[]> hmget(final byte[] key, final byte[]... fields) {
        return (List)JedisTemplate.run(key, new Executor<List<byte[]>>() {
            public List<byte[]> execute(ShardedJedis jedis) {
                return jedis.hmget(key, fields);
            }
        }, new Integer[0]);
    }

    public static Long hincrBy(final byte[] key, final byte[] field, final long value) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.hincrBy(key, field, value);
            }
        }, new Integer[0]);
    }

    public static Boolean hexists(final byte[] key, final byte[] field) {
        return (Boolean)JedisTemplate.run(key, new Executor<Boolean>() {
            public Boolean execute(ShardedJedis jedis) {
                return jedis.hexists(key, field);
            }
        }, new Integer[0]);
    }

    public static Long hdel(final byte[] key, final byte[] field) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.hdel(key, new byte[][]{field});
            }
        }, new Integer[0]);
    }

    public static Long hlen(final byte[] key) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.hlen(key);
            }
        }, new Integer[0]);
    }

    public static final Set<byte[]> hkeys(final byte[] key) {
        return (Set)JedisTemplate.run(key, new Executor<Set<byte[]>>() {
            public Set<byte[]> execute(ShardedJedis jedis) {
                return jedis.hkeys(key);
            }
        }, new Integer[0]);
    }

    public static final Collection<byte[]> hvals(final byte[] key) {
        return (Collection)JedisTemplate.run(key, new Executor<Collection<byte[]>>() {
            public Collection<byte[]> execute(ShardedJedis jedis) {
                return jedis.hvals(key);
            }
        }, new Integer[0]);
    }

    public static final Map<byte[], byte[]> hgetAll(final byte[] key) {
        return (Map)JedisTemplate.run(key, new Executor<Map<byte[], byte[]>>() {
            public Map<byte[], byte[]> execute(ShardedJedis jedis) {
                return jedis.hgetAll(key);
            }
        }, new Integer[0]);
    }

    public static final Long rpush(final byte[] key, final byte[] string) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.rpush(key, new byte[][]{string});
            }
        }, new Integer[0]);
    }

    public static final Long lpush(final byte[] key, final byte[] string) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.lpush(key, new byte[][]{string});
            }
        }, new Integer[0]);
    }

    public static final Long llen(final byte[] key) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.llen(key);
            }
        }, new Integer[0]);
    }

    public static final List<byte[]> lrange(final byte[] key, final int start, final int end) {
        return (List)JedisTemplate.run(key, new Executor<List<byte[]>>() {
            public List<byte[]> execute(ShardedJedis jedis) {
                return jedis.lrange(key, (long)start, (long)end);
            }
        }, new Integer[0]);
    }

    public static final String ltrim(final byte[] key, final int start, final int end) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.ltrim(key, (long)start, (long)end);
            }
        }, new Integer[0]);
    }

    public static final byte[] lindex(final byte[] key, final int index) {
        return (byte[])JedisTemplate.run(key, new Executor<byte[]>() {
            public byte[] execute(ShardedJedis jedis) {
                return jedis.lindex(key, (long)index);
            }
        }, new Integer[0]);
    }

    public static final String lset(final byte[] key, final int index, final byte[] value) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.lset(key, (long)index, value);
            }
        }, new Integer[0]);
    }

    public static final Long lrem(final byte[] key, final int count, final byte[] value) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.lrem(key, (long)count, value);
            }
        }, new Integer[0]);
    }

    public static final byte[] lpop(final byte[] key) {
        return (byte[])JedisTemplate.run(key, new Executor<byte[]>() {
            public byte[] execute(ShardedJedis jedis) {
                return jedis.lpop(key);
            }
        }, new Integer[0]);
    }

    public static final byte[] rpop(final byte[] key) {
        return (byte[])JedisTemplate.run(key, new Executor<byte[]>() {
            public byte[] execute(ShardedJedis jedis) {
                return jedis.rpop(key);
            }
        }, new Integer[0]);
    }

    public static final Long sadd(final byte[] key, final byte[] member) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.sadd(key, new byte[][]{member});
            }
        }, new Integer[0]);
    }

    public static final Set<byte[]> smembers(final byte[] key) {
        return (Set)JedisTemplate.run(key, new Executor<Set<byte[]>>() {
            public Set<byte[]> execute(ShardedJedis jedis) {
                return jedis.smembers(key);
            }
        }, new Integer[0]);
    }

    public static final Long srem(final byte[] key, final byte[] member) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.srem(key, new byte[][]{member});
            }
        }, new Integer[0]);
    }

    public static final byte[] spop(final byte[] key) {
        return (byte[])JedisTemplate.run(key, new Executor<byte[]>() {
            public byte[] execute(ShardedJedis jedis) {
                return jedis.spop(key);
            }
        }, new Integer[0]);
    }

    public static final Long scard(final byte[] key) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.scard(key);
            }
        }, new Integer[0]);
    }

    public static final Boolean sismember(final byte[] key, final byte[] member) {
        return (Boolean)JedisTemplate.run(key, new Executor<Boolean>() {
            public Boolean execute(ShardedJedis jedis) {
                return jedis.sismember(key, member);
            }
        }, new Integer[0]);
    }

    public static final byte[] srandmember(final byte[] key) {
        return (byte[])JedisTemplate.run(key, new Executor<byte[]>() {
            public byte[] execute(ShardedJedis jedis) {
                return jedis.srandmember(key);
            }
        }, new Integer[0]);
    }

    public static final Long zadd(final byte[] key, final double score, final byte[] member) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.zadd(key, score, member);
            }
        }, new Integer[0]);
    }

    public static final Set<byte[]> zrange(final byte[] key, final int start, final int end) {
        return (Set)JedisTemplate.run(key, new Executor<Set<byte[]>>() {
            public Set<byte[]> execute(ShardedJedis jedis) {
                return jedis.zrange(key, (long)start, (long)end);
            }
        }, new Integer[0]);
    }

    public static final Long zrem(final byte[] key, final byte[] member) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.zrem(key, new byte[][]{member});
            }
        }, new Integer[0]);
    }

    public static final Double zincrby(final byte[] key, final double score, final byte[] member) {
        return (Double)JedisTemplate.run(key, new Executor<Double>() {
            public Double execute(ShardedJedis jedis) {
                return jedis.zincrby(key, score, member);
            }
        }, new Integer[0]);
    }

    public static final Long zrank(final byte[] key, final byte[] member) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.zrank(key, member);
            }
        }, new Integer[0]);
    }

    public static final Long zrevrank(final byte[] key, final byte[] member) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.zrevrank(key, member);
            }
        }, new Integer[0]);
    }

    public static final Set<byte[]> zrevrange(final byte[] key, final int start, final int end) {
        return (Set)JedisTemplate.run(key, new Executor<Set<byte[]>>() {
            public Set<byte[]> execute(ShardedJedis jedis) {
                return jedis.zrevrange(key, (long)start, (long)end);
            }
        }, new Integer[0]);
    }

    public static final Set<Tuple> zrangeWithScores(final byte[] key, final int start, final int end) {
        return (Set)JedisTemplate.run(key, new Executor<Set<Tuple>>() {
            public Set<Tuple> execute(ShardedJedis jedis) {
                return jedis.zrangeWithScores(key, (long)start, (long)end);
            }
        }, new Integer[0]);
    }

    public static final Set<Tuple> zrevrangeWithScores(final byte[] key, final int start, final int end) {
        return (Set)JedisTemplate.run(key, new Executor<Set<Tuple>>() {
            public Set<Tuple> execute(ShardedJedis jedis) {
                return jedis.zrevrangeWithScores(key, (long)start, (long)end);
            }
        }, new Integer[0]);
    }

    public static final Long zcard(final byte[] key) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.zcard(key);
            }
        }, new Integer[0]);
    }

    public static final Double zscore(final byte[] key, final byte[] member) {
        return (Double)JedisTemplate.run(key, new Executor<Double>() {
            public Double execute(ShardedJedis jedis) {
                return jedis.zscore(key, member);
            }
        }, new Integer[0]);
    }

    public static final List<byte[]> sort(final byte[] key) {
        return (List)JedisTemplate.run(key, new Executor<List<byte[]>>() {
            public List<byte[]> execute(ShardedJedis jedis) {
                return jedis.sort(key);
            }
        }, new Integer[0]);
    }

    public static final List<byte[]> sort(final byte[] key, final SortingParams sortingParameters) {
        return (List)JedisTemplate.run(key, new Executor<List<byte[]>>() {
            public List<byte[]> execute(ShardedJedis jedis) {
                return jedis.sort(key, sortingParameters);
            }
        }, new Integer[0]);
    }

    public static final Long zcount(final byte[] key, final double min, final double max) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.zcount(key, min, max);
            }
        }, new Integer[0]);
    }

    public static final Set<byte[]> zrangeByScore(final byte[] key, final double min, final double max) {
        return (Set)JedisTemplate.run(key, new Executor<Set<byte[]>>() {
            public Set<byte[]> execute(ShardedJedis jedis) {
                return jedis.zrangeByScore(key, min, max);
            }
        }, new Integer[0]);
    }

    public static final Set<byte[]> zrangeByScore(final byte[] key, final double min, final double max, final int offset, final int count) {
        return (Set)JedisTemplate.run(key, new Executor<Set<byte[]>>() {
            public Set<byte[]> execute(ShardedJedis jedis) {
                return jedis.zrangeByScore(key, min, max, offset, count);
            }
        }, new Integer[0]);
    }

    public static final Set<Tuple> zrangeByScoreWithScores(final byte[] key, final double min, final double max) {
        return (Set)JedisTemplate.run(key, new Executor<Set<Tuple>>() {
            public Set<Tuple> execute(ShardedJedis jedis) {
                return jedis.zrangeByScoreWithScores(key, min, max);
            }
        }, new Integer[0]);
    }

    public static final Set<Tuple> zrangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset, final int count) {
        return (Set)JedisTemplate.run(key, new Executor<Set<Tuple>>() {
            public Set<Tuple> execute(ShardedJedis jedis) {
                return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
            }
        }, new Integer[0]);
    }

    public static final Set<byte[]> zrevrangeByScore(final byte[] key, final double max, final double min) {
        return (Set)JedisTemplate.run(key, new Executor<Set<byte[]>>() {
            public Set<byte[]> execute(ShardedJedis jedis) {
                return jedis.zrevrangeByScore(key, max, min);
            }
        }, new Integer[0]);
    }

    public static final Set<byte[]> zrevrangeByScore(final byte[] key, final double max, final double min, final int offset, final int count) {
        return (Set)JedisTemplate.run(key, new Executor<Set<byte[]>>() {
            public Set<byte[]> execute(ShardedJedis jedis) {
                return jedis.zrevrangeByScore(key, max, min, offset, count);
            }
        }, new Integer[0]);
    }

    public static final Set<Tuple> zrevrangeByScoreWithScores(final byte[] key, final double max, final double min) {
        return (Set)JedisTemplate.run(key, new Executor<Set<Tuple>>() {
            public Set<Tuple> execute(ShardedJedis jedis) {
                return jedis.zrevrangeByScoreWithScores(key, max, min);
            }
        }, new Integer[0]);
    }

    public static final Set<Tuple> zrevrangeByScoreWithScores(final byte[] key, final double max, final double min, final int offset, final int count) {
        return (Set)JedisTemplate.run(key, new Executor<Set<Tuple>>() {
            public Set<Tuple> execute(ShardedJedis jedis) {
                return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
            }
        }, new Integer[0]);
    }

    public static final Long zremrangeByRank(final byte[] key, final int start, final int end) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.zremrangeByRank(key, (long)start, (long)end);
            }
        }, new Integer[0]);
    }

    public static final Long zremrangeByScore(final byte[] key, final double start, final double end) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.zremrangeByScore(key, start, end);
            }
        }, new Integer[0]);
    }

    public static final Long linsert(final byte[] key, final LIST_POSITION where, final byte[] pivot, final byte[] value) {
        return (Long)JedisTemplate.run(key, new Executor<Long>() {
            public Long execute(ShardedJedis jedis) {
                return jedis.linsert(key, where, pivot, value);
            }
        }, new Integer[0]);
    }

    public static final String getKeyTag(final String key) {
        return (String)JedisTemplate.run(key, new Executor<String>() {
            public String execute(ShardedJedis jedis) {
                return jedis.getKeyTag(key);
            }
        }, new Integer[0]);
    }

    public static void main(String[] args) {
        TSBaseUser order = new TSBaseUser();
        order.setUserName("demo");
        String uuid = UUIDUtil.getId();
        ObjectRedisSerializer objectRedisSerializer = new ObjectRedisSerializer();
        set(uuid.getBytes(), (new ObjectRedisSerializer()).serialize(order), new Integer[]{Integer.valueOf(86400)});
        TSBaseUser result = (TSBaseUser)objectRedisSerializer.deserialize(get(uuid.getBytes(), new Integer[0]));
        System.out.println(result.getUserName());
    }
}
