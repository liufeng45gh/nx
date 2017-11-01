/*    */ package ssb.warmline.business.commons.support.spring.data.redis;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.IOException;
/*    */ import java.util.zip.GZIPInputStream;
/*    */ import org.springframework.core.convert.converter.Converter;
/*    */ import org.springframework.core.serializer.DefaultDeserializer;
/*    */ import org.springframework.core.serializer.Deserializer;
/*    */ import org.springframework.core.serializer.support.SerializationFailedException;
/*    */ import org.springframework.util.Assert;
/*    */ 
/*    */ public class DeserializingConverter
/*    */   implements Converter<byte[], Object>
/*    */ {
/*    */   private final Deserializer<Object> deserializer;
/*    */ 
/*    */   public DeserializingConverter()
/*    */   {
/* 24 */     this.deserializer = new DefaultDeserializer();
/*    */   }
/*    */ 
/*    */   public DeserializingConverter(ClassLoader classLoader)
/*    */   {
/* 35 */     this.deserializer = new DefaultDeserializer(classLoader);
/*    */   }
/*    */ 
/*    */   public DeserializingConverter(Deserializer<Object> deserializer)
/*    */   {
/* 43 */     Assert.notNull(deserializer, "Deserializer must not be null");
/* 44 */     this.deserializer = deserializer;
/*    */   }
/*    */ 
/*    */   public Object convert(byte[] source) {
/* 48 */     GZIPInputStream gzipStream = null;
/* 49 */     ByteArrayInputStream byteStream = new ByteArrayInputStream(source);
/*    */     try
/*    */     {
/* 52 */       gzipStream = new GZIPInputStream(byteStream);
/* 53 */       return this.deserializer.deserialize(gzipStream);
/*    */     } catch (Throwable ex) {
/* 55 */       throw new SerializationFailedException(
/* 56 */         "Failed to deserialize payload. Is the byte array a result of corresponding serialization for " + 
/* 57 */         this.deserializer.getClass().getSimpleName() + "?", 
/* 58 */         ex);
/*    */     } finally {
/* 60 */       if (gzipStream != null)
/*    */         try {
/* 62 */           gzipStream.close();
/*    */         }
/*    */         catch (IOException localIOException1)
/*    */         {
/*    */         }
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.support.spring.data.redis.DeserializingConverter
 * JD-Core Version:    0.6.2
 */