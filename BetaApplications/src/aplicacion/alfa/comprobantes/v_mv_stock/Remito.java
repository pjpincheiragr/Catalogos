package aplicacion.alfa.comprobantes.v_mv_stock;
import java.util.*;

import javax.swing.JOptionPane;
import aplicacion.herramientas.java.*;

public class Remito {

private String tc; // RM
private String idcomprobante; //000100020212X
private String fecha;//fecha
private String cuenta;//112010001
private String nombre;//Consumidor Final
private String Domicilio; //.
private String Telefono ;//*
private String Localidad; //Neuquen
private String idprovincia; //'  14'
private String codigopostal; //8300
private String documentotipo; //'   3'
private String documentonumero; //*
private String condicioniva; //'   3'
private String idcond_cpra_vta; //'   1'
private String claseprecio; //2
private String observaciones; //''
private Double Importe;//380.19
private Double Importe_S_iva;//314.21
private int finalizada; //1;
private int anulada; //1;
private int aprobado; //1;
private String idmotivocpravta; // '   1'
private Double porcDescuento1;// 0.0
private String idvendedor;//'   3'
private Double ImporteInsumos;//314.21
private Double ImporteServicios;
private Double ImporteOtrosConceptos;
private Double ImporteIva;//65.98
private Double ImporteIvaRec;//0.0
private Double netoGravado;//314.21
private Double netonoGravado;//0.0
private int impreso;//0
private String iddeposito;//'   1'
private double aliciva; //21
private String moneda;//'   1'
private double Cotizacion;// 1.0
private String sucursal; //0001
private String numero; //00020212
private String letra;//X
private String unegocio;//'   1'
private String usuario;//'VENTAS1'
private String unegocio_destino;//'   1'

private String idpedido="";
String idunidad="   1";
String idunidadbase="   1";
double impuestos=0.0;
double porcdto=0.0;
double importedto=0.0;
double equiv_udbase=1.0;
int transmision=1;
int recepcion=1;
int exento=0;
String idcaja="   1";
String tipolista="V";

public Remito(){
	this.InitValues();
}

public void InitValues(){
	 tc="RM";
	 idcomprobante=""; //000100020212X
	 cuenta="112010001";
	 nombre="Consumidor Final";
	 Domicilio=".";
	 fecha="";
	 Telefono="*";
	 Localidad="Neuquen";
	 idprovincia="  14";
	 codigopostal="8300";
	 documentotipo="   3";
	 documentonumero="*";
	 condicioniva="   3";
	 idcond_cpra_vta="   1"; 
	 claseprecio="2";
	 observaciones="";
	 Importe=0.0;//380.19;
	 Importe_S_iva=0.0;//314.21
	 finalizada=0; //1;
	 anulada=0; //1;
	 aprobado=0; //1;
	 idmotivocpravta="   1"; // ''
	 porcDescuento1=0.0;// 0.0
	 idvendedor="   7";//''
	 ImporteInsumos=0.0;//314.21
	 ImporteServicios=0.0;
	 ImporteOtrosConceptos=0.0;
	 ImporteIva=0.0;//65.98
	 ImporteIvaRec=0.0;//0.0
	 netoGravado=0.0;//314.21
	 netonoGravado=0.0;//0.0
	 impreso=0;//0
	 iddeposito="   1";//''
	 aliciva=21; //21
	 moneda="   1";//'   1'
	 Cotizacion=1.0;// 1.0
	 sucursal="0001"; //0001
	 numero=""; //00020212
	 letra="X";//X
	 unegocio="   1";//'   1'
	 usuario="VENTAS1";//'VENTAS1'
	 unegocio_destino="   1";//'   1'
	 exento=0;
}

public void setIdVendedor(String idvendedor){
	this.idvendedor=idvendedor;
}
public void setCondicionCpraVta(String cond){
	idcond_cpra_vta=cond;
}
public double getImporte_S_IVA(){
	return this.Importe_S_iva;
}
public double getImporteIVA(){
	return this.ImporteIva;
}
public void setDocumentoTipo(String tipo_doc){
	this.documentotipo=tipo_doc;
}

public void setClase(String clase){
	this.claseprecio=clase;
}
public void setDocumento(String cuit){
	this.documentonumero=cuit;
}
public void setTelefono(String tel){
	this.Telefono=tel;
}

public void setPostal(String postal){
	this.codigopostal=postal;
}
public void setLocalidad(String loc){
	this.Localidad=loc;
}
public void setProvincia(String idprov){
	this.idprovincia=idprov;
}
public double getTotal(){
	return this.Importe;
}
public void setPedido(String idp){
	this.idpedido=idp;
}
public void setCuenta(String id){
	this.cuenta=id;
}
public void setExento(int i){
	this.exento=i;
}
public void setNombre(String nombre){
	this.nombre=nombre;
}

public void setCondicionIva(String condicion){
	this.condicioniva=condicion;
}

public void setNumero(String numero){
	this.numero=numero;
}

public void setDomicilio(String domicilio){
	this.Domicilio=domicilio;
}

public void setFecha(String fecha){
	this.fecha=fecha;
}



public void setTC(String tc){
	this.tc=tc;
}





private String getDetalleQuery(){
	String q="";
	q+="select idarticulo,descripcion,importe,importe_s_iva,total,cantidad ";
	q+="from v_mv_cpteinsumos ";
	q+="where tc like '"+this.tc+"' ";
	q+="and idcomprobante like '"+this.idcomprobante+"' ";
	
	return q;
}


public String getQueryDetallesV(String idprov,String tc,String idcomp){
	String q="";
	q=q+" select i.idarticulo,i.descripcion,i.cantidad,i.importe,a.descripabrev ";
	q=q+" from v_mv_cpte c left outer join ";
	q=q+" v_mv_cpteinsumos i on ";
	q=q+" c.tc=i.tc and c.idcomprobante=i.idcomprobante ";
	q=q+" left outer join v_ma_articulos a on i.idarticulo=a.idarticulo ";
	q=q+" where c.idcomprobante like '"+idcomp+"' ";
	q=q+" and c.cuenta like '"+idprov+"' ";
	q=q+" and c.tc like '"+tc+"' ";
	q=q+" order by c.idcomprobante ,i.idarticulo ";
	
	return q;
}


private String getQuery(String tc,String idcomprobante){
	String q="";
	q="select importe_s_iva,importeiva,importe from v_mv_cpte ";
	q+="where tc like '"+tc+"' ";
	q+="and idcomprobante like '"+idcomprobante+"' ";
	return q;
}



public String getItemInsert(String idarticulo,String descripcion,String cantidad,String costo,String importe,String importe_siva,String total,String totalfinal){
	
	String q="";
	q=q+"insert into v_mv_cpteinsumos (";
	q=q+"tc,idcomprobante,idarticulo,";
	q=q+"descripcion,idunidad,cantidadud,cantidad,";
	q=q+"costo,importe,importe_s_iva,";
	q=q+"total,exento,claseprecio,";
	q=q+"tipolista,porcdto,importedto,";
	q=q+"aliciva,totalfinal,idunidadbase,idcaja,equiv_udbase,";
	q=q+"transmision,recepcion,iddeposito) ";
	q=q+"values (";
	q=q+"'"+tc+"',";
	q=q+"'"+idcomprobante+"',";
	q=q+"'"+idarticulo+"',"; //idarticulo
	q=q+"'"+descripcion+"',"; //descripcion
	q=q+"'"+idunidad+"',"; //idunidad
	q=q+""+cantidad+","; //cantidad
	q=q+""+cantidad+","; //cantidadud=cantidad
	q=q+""+costo+","; //costo
	q=q+""+importe+","; //importe
	q=q+""+importe_siva+","; //importe_s_iva
	q=q+""+total+","; //total
	q=q+""+exento+",";//exento
	q=q+"'"+claseprecio+"',"; //claseprecio
	q=q+"'"+tipolista+"',"; //tipolista
	q=q+""+porcdto+","; //porcdto
	q=q+""+importedto+","; //importedto
	q=q+""+aliciva+","; //aliciva
	q=q+""+totalfinal+","; //totalfinal!=total
	q=q+"'"+idunidad+"',";
	q=q+"'"+idcaja+"',";
	q=q+""+this.equiv_udbase+",";
	q=q+""+transmision+","; //transmision
	q=q+""+recepcion+","; //recepcion
	q=q+"'"+this.iddeposito+"'"; //iddeposito
	q=q+")";
	
	return q;
}

private String getProximoRemito(){
	String q="select x_ultimo_nro from v_ta_cpte where codigo = 'RM'";
	
	return q;
}

public void setIDComprobante(String comp){
	this.idcomprobante=comp;
}


public String getInsertRemito(){
	String q="insert into v_mv_cpte (";
	q=q+"tc,idcomprobante,cuenta,nombre,Domicilio,fecha,fechaestinicio,fechaestfin,";
	q=q+"Telefono,Localidad,idprovincia,codigopostal,documentotipo,documentonumero,";
	q=q+"condicioniva,idcond_cpra_vta,claseprecio,observaciones,";
	q=q+"Importe,Importe_S_iva,";
	q=q+"finalizada,anulada,aprobado,";
	q=q+"idmotivocpravta,porcDescuento1,idvendedor,";
	q=q+"ImporteInsumos,ImporteServicios,ImporteOtrosConceptos,ImporteIva,ImporteIvaRec,";
	q=q+"netoGravado,netonoGravado,";
	q=q+"impreso,iddeposito,aliciva,";
	q=q+"moneda,Cotizacion,sucursal,numero,letra,";
	q=q+"unegocio,usuario,unegocio_destino) ";
	q=q+"values (";
	q=q+"'"+tc+"',";
	q=q+"'"+idcomprobante+"',";
	q=q+"'"+cuenta+"',";
	q=q+"'"+nombre+"',";
	q=q+"'"+Domicilio+"',";
	q=q+"'"+fecha+"',";
	q=q+"'"+fecha+"',";
	q=q+"'"+fecha+"',";
	q=q+"'"+Telefono+"',";
	q=q+"'"+Localidad+"',";
	q=q+"'"+idprovincia+"',";
	q=q+"'"+codigopostal+"',";
	q=q+"'"+documentotipo+"',";
	q=q+"'"+documentonumero+"',";
	q=q+"'"+condicioniva+"',";
	q=q+"'"+idcond_cpra_vta+"',";
	q=q+"'"+claseprecio+"',";
	q=q+"'"+observaciones+"',";
	q=q+""+Importe+",";
	q=q+""+Importe_S_iva+",";
	q=q+""+finalizada+",";
	q=q+""+anulada+",";
	q=q+""+aprobado+",";
	q=q+"'"+idmotivocpravta+"',";
	q=q+""+porcDescuento1+",";
	q=q+"'"+idvendedor+"',";
	q=q+""+ImporteInsumos+",";
	q=q+""+ImporteServicios+",";
	q=q+""+ImporteOtrosConceptos+",";
	q=q+""+ImporteIva+",";
	q=q+""+ImporteIvaRec+",";
	q=q+""+netoGravado+",";
	q=q+""+netonoGravado+",";
	q=q+""+impreso+",";
	q=q+"'"+iddeposito+"',";
	q=q+""+aliciva+",";
	q=q+"'"+moneda+"',";
	q=q+""+Cotizacion+",";
	q=q+"'"+sucursal+"',";
	q=q+"'"+numero+"',";
	q=q+"'"+letra+"',";
	q=q+"'"+unegocio+"',";
	q=q+"'"+usuario+"',";
	q=q+"'"+unegocio_destino+"')";
	return q;
}
public void setImporte(double importe){
	this.Importe=importe;
}

public void setImporte_S_iva(double importe){
	this.Importe_S_iva=importe;
	this.ImporteInsumos=importe;
	this.netoGravado=importe;
}
public void setIva(double iva){
	this.ImporteIva=iva;
}
public String getDeleteInsumos(String tc,String idcomprobante){
	String q="delete from v_mv_cpteinsumos where ";
	q=q+"tc like '"+tc+"'";
	q=q+" and idcomprobante like '"+idcomprobante+"'";
	return q;
}
public String getDelete(String tc,String idcomprobante){
	String q="delete from v_mv_cpte where ";
	q=q+"tc like '"+tc+"'";
	q=q+" and idcomprobante like '"+idcomprobante+"'";
	return q;
}

public String getDeleteRemitoQuery2(String rms){
	Convertidor Cv=new Convertidor();
	String q="delete from b_pdc_rms ";
	q=q+" where idpedido = '"+idpedido+"' and remito like '"+rms+"'";
	System.out.println(q);
	return q;
}
public String getDeleteRemitoQuery(){
	Convertidor Cv=new Convertidor();
	String q="update B_PDC set remito=''";
	q=q+" where idpedido = '"+idpedido+"'";
	
	System.out.println(q);
	return q;
}

public String getInsertRemitoQuery(){
	Convertidor Cv=new Convertidor();
	String fecha=Cv.getDateWithFormat("dd-MM-yyyy");
	
	String q="insert into b_pdc_rms (idpedido,remito,creacion) values (";
	q=q+"'"+idpedido+"','"+idcomprobante+"','"+fecha+"') ";
	
	return q;
}

public String getUpdateRemitoQuery(){
	Convertidor Cv=new Convertidor();
	String q="insert B_PDC set remito='"+idcomprobante+"'";
	q=q+" where idpedido = '"+idpedido+"'";
	return q;
}

public String getidcuenta(){
	return this.cuenta;
}

public String getCuentaDescripcion(){
	return this.nombre;
}

public String getIdComprobante(){
	return this.sucursal+"-"+this.numero+"-"+this.letra;
}


}
