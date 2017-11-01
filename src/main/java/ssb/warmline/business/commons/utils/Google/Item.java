/*    */ package ssb.warmline.business.commons.utils.Google;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class Item
/*    */ {
/*    */   List<AddressComponents> address_components;
/*    */   String formatted_address;
/*    */   Geometry geometry;
/*    */   String place_id;
/*    */   String[] types;
/*    */ 
/*    */   public List<AddressComponents> getAddress_components()
/*    */   {
/* 12 */     return this.address_components;
/*    */   }
/*    */   public void setAddress_components(List<AddressComponents> address_components) {
/* 15 */     this.address_components = address_components;
/*    */   }
/*    */   public String getFormatted_address() {
/* 18 */     return this.formatted_address;
/*    */   }
/*    */   public void setFormatted_address(String formatted_address) {
/* 21 */     this.formatted_address = formatted_address;
/*    */   }
/*    */   public Geometry getGeometry() {
/* 24 */     return this.geometry;
/*    */   }
/*    */   public void setGeometry(Geometry geometry) {
/* 27 */     this.geometry = geometry;
/*    */   }
/*    */   public String getPlace_id() {
/* 30 */     return this.place_id;
/*    */   }
/*    */   public void setPlace_id(String place_id) {
/* 33 */     this.place_id = place_id;
/*    */   }
/*    */   public String[] getTypes() {
/* 36 */     return this.types;
/*    */   }
/*    */   public void setTypes(String[] types) {
/* 39 */     this.types = types;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.Google.Item
 * JD-Core Version:    0.6.2
 */