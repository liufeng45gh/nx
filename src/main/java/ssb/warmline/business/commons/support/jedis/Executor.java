package ssb.warmline.business.commons.support.jedis;

import redis.clients.jedis.ShardedJedis;

public abstract interface Executor<K>
{
  public abstract K execute(ShardedJedis paramShardedJedis);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.support.jedis.Executor
 * JD-Core Version:    0.6.2
 */