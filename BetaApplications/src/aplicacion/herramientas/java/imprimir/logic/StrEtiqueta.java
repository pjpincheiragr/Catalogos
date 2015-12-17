/*    */ package aplicacion.herramientas.java.imprimir.logic;
/*    */ 
/*    */ public class StrEtiqueta
/*    */ {
/*  4 */   private String codigo = "";
/*  5 */   private String descripcion = "";
/*  6 */   private int cantidad = 0;
/*    */ 
/*    */   public void StrEtiqueta() {
/*  9 */     this.codigo = "";
/* 10 */     this.descripcion = "";
/* 11 */     this.cantidad = 0;
/*    */   }
/*    */ 
/*    */   public void setCodigo(String codigo) {
/* 15 */     this.codigo = codigo;
/*    */   }
/*    */ 
/*    */   public void setDescripcion(String descripcion) {
/* 19 */     this.descripcion = descripcion;
/*    */   }
/*    */ 
/*    */   public void setCantidad(int cantidad) {
/* 23 */     this.cantidad = cantidad;
/*    */   }
/*    */ 
/*    */   public String getCodigo() {
/* 27 */     return this.codigo;
/*    */   }
/*    */ 
/*    */   public String getDescripcion() {
/* 31 */     return this.descripcion;
/*    */   }
/*    */ 
/*    */   public int getCantidad() {
/* 35 */     return this.cantidad;
/*    */   }
/*    */   public boolean esVacia() {
/* 38 */     return this.codigo.compareTo("") == 0;
/*    */   }
/*    */ }

/* Location:           C:\Beta\lib\beta.alfa.0.jar
 * Qualified Name:     beta.estructuras.datos.StrEtiqueta
 * JD-Core Version:    0.6.0
 */