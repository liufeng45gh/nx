/*    */ package ssb.warmline.business.commons.support.spring.data.redis;
/*    */ 
/*    */ import org.springframework.core.convert.converter.Converter;
/*    */ import org.springframework.data.redis.serializer.RedisSerializer;
/*    */ import org.springframework.data.redis.serializer.SerializationException;
/*    */ import org.springframework.util.Assert;
/*    */ 
/*    */ public class ObjectRedisSerializer
/*    */   implements RedisSerializer<Object>
/*    */ {
/*    */   private final Converter<Object, byte[]> serializer;
/*    */   private final Converter<byte[], Object> deserializer;
/*    */ 
/*    */   public ObjectRedisSerializer()
/*    */   {
/* 18 */     this(new SerializingConverter(), new DeserializingConverter());
/*    */   }
/*    */ 
/*    */   public ObjectRedisSerializer(ClassLoader classLoader)
/*    */   {
/* 29 */     this(new SerializingConverter(), new DeserializingConverter(classLoader));
/*    */   }
/*    */ 
/*    */   public ObjectRedisSerializer(Converter<Object, byte[]> serializer, Converter<byte[], Object> deserializer)
/*    */   {
/* 41 */     Assert.notNull("Serializer must not be null!");
/* 42 */     Assert.notNull("Deserializer must not be null!");
/*    */ 
/* 44 */     this.serializer = serializer;
/* 45 */     this.deserializer = deserializer;
/*    */   }
/*    */ 
/*    */   public Object deserialize(byte[] bytes) {
/* 49 */     if ((bytes == null) || (bytes.length == 0)) {
/* 50 */       return null;
/*    */     }
/*    */     try
/*    */     {
/* 54 */       return this.deserializer.convert(bytes);
/*    */     } catch (Exception ex) {
/* 56 */       throw new SerializationException("Cannot deserialize", ex);
/*    */     }
/*    */   }
/*    */ 
/*    */   public byte[] serialize(Object object) {
/* 61 */     if (object == null)
/* 62 */       return new byte[0];
/*    */     try
/*    */     {
/* 65 */       return (byte[])this.serializer.convert(object);
/*    */     } catch (Exception ex) {
/* 67 */       throw new SerializationException("Cannot serialize", ex);
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.support.spring.data.redis.ObjectRedisSerializer
 * JD-Core Version:    0.6.2
 */