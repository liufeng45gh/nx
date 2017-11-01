/*    */ package ssb.warmline.business.commons.support.spring.data.redis;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.util.zip.GZIPOutputStream;
/*    */ import org.springframework.core.convert.converter.Converter;
/*    */ import org.springframework.core.serializer.DefaultSerializer;
/*    */ import org.springframework.core.serializer.Serializer;
/*    */ import org.springframework.core.serializer.support.SerializationFailedException;
/*    */ import org.springframework.util.Assert;
/*    */ 
/*    */ public class SerializingConverter
/*    */   implements Converter<Object, byte[]>
/*    */ {
/*    */   private final Serializer<Object> serializer;
/*    */ 
/*    */   public SerializingConverter()
/*    */   {
/* 20 */     this.serializer = new DefaultSerializer();
/*    */   }
/*    */ 
/*    */   public SerializingConverter(Serializer<Object> serializer)
/*    */   {
/* 28 */     Assert.notNull(serializer, "Serializer must not be null");
/* 29 */     this.serializer = serializer;
/*    */   }
/*    */ 
/*    */   public byte[] convert(Object source)
/*    */   {
/* 36 */     ByteArrayOutputStream byteStream = new ByteArrayOutputStream(1024);
/*    */     try
/*    */     {
/* 39 */       GZIPOutputStream gzipStream = new GZIPOutputStream(byteStream);
/* 40 */       this.serializer.serialize(source, gzipStream);
/* 41 */       gzipStream.close();
/* 42 */       return byteStream.toByteArray();
/*    */     } catch (Throwable ex) {
/* 44 */       throw new SerializationFailedException(
/* 45 */         "Failed to serialize object using " + this.serializer.getClass().getSimpleName(), ex);
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.support.spring.data.redis.SerializingConverter
 * JD-Core Version:    0.6.2
 */