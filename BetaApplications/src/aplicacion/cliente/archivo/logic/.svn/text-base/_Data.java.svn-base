package aplicacion.cliente.archivo.logic;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;

public class _Data extends Data{

	public Object[][] getSaldos(String idcliente){
		Object[][] results=null;
		String q="";
		q+="select CONVERT(VARCHAR(10), b.fecha  , 105),b.tc,b.idcomprobante,";
		q+="(case when b.debe_haber like 'd' then b.importe else 0 end) as debe,";
		q+="(case when b.debe_haber like 'h' then b.importe else 0 end) as haber ";
		q+=" from b_mv_asientos b ";
		q+="where  b.cuenta like '"+idcliente+"' ";
		q+="and b.anulado=0 ";
		q+="order by b.fecha desc,b.tc,b.idcomprobante  ";
		System.out.println("SALDOS> "+q);
		results=this.getResults(q);
		return results;
	}
	public String getCategoriaId(String descripcion){
		String id="";
		String q="select idcategoria from v_ta_categoria where descripcion like '"+descripcion+"'";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				id=(String)results[0][0];	
			}
			
		}
		return id;
	}
	public String getCategoriaNombre(String idcategoria){
		String id="";
		String q="select descripcion from v_ta_categoria where idcategoria like '"+idcategoria+"'";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				id=(String)results[0][0];	
			}
			
		}
		return id;
	}
	public String[] getCategorias(){
		String q="select descripcion from v_ta_categoria ";
		q+="group by descripcion ";
		q+="order by descripcion ";
		Object[][] results=this.getResults(q);
		String[] categorias=new String[results.length];
		for (int i=0;i<results.length;i++){
			categorias[i]=results[i][0].toString();
		}
		return categorias;
	}
	
	private String getClientQuery(String idcliente){
		String q="";
		q=q+" select M.codigo,M.descripcion,";
		q=q+" isnull(a.telefono,''),";
		q=q+" isnull(a.fax,''),";
		q=q+" isnull(a.mail,''),";
		q=q+" isnull(a.contacto,''),";
		q=q+" isnull(a.localidad,''),";
		q=q+" isnull(a.observaciones,''),";
		q=q+" a.iva, ";
		q=q+" a.numero_documento, ";
		q=q+" a.idcond_cpra_vta, ";
		q=q+" t.descripcion, ";
		q=q+" isnull(a.calle,''),isnull(ltrim(a.numero),''),isnull(a.piso,''),ltrim(a.cpostal), ";
		q=q+" a.provincia,st.descripcion,tip.descripcion,a.documento_tipo,isnull(td.descripcion,''), ";
		q=q+" isnull(a.idcategoria,''),isnull(a.limite_credito,0),isnull(a.descuento,0) ";
		q=q+" FROM MA_CUENTAS M LEFT OUTER JOIN ";
		q=q+" MA_CUENTASADIC  a  ";
		q=q+" ON M.CODIGO = a.CODIGO left outer join v_ta_cpra_vta t ";
		q=q+" on a.idcond_cpra_vta = t.idcond_cpra_vta ";
		q=q+" left outer join ta_condiva tip ";
		q=q+" on a.iva=tip.codigo ";
		q=q+" left outer join ta_estados st ";
		q=q+" on a.provincia=st.codigo ";
		q=q+" left outer join ta_tipodocumento td on a.documento_tipo=td.codigo ";
		q=q+" where M.codigo like '"+idcliente+"'";
		return q;
	}
	
	public Object[][] getCliente(String idcliente){
		String q=this.getClientQuery(idcliente);
		System.out.println(q);
		Object[][] results=this.getResults(q);
		return results;
	}
	
	public Object[][] getCptesImpagosAlfa(String idcliente){
		String q="";
		q+="select convert(varchar(10),fecha,105), ";
		q+="tc,(sucursal+numero+letra),";
		q+="(case when [debe-haber] like 'D' then IMPORTE ELSE 0 end),";
		q+="(case when [debe-haber] like 'H' then IMPORTE ELSE 0 end)";
		q+="from ve_cptes_IMPAGOS ";
		q+="where importe >0 and cuenta like '"+idcliente+"' ";
		return this.getResults(q);
	}
	
	public Object[][] getCreditosAlfa(String idcliente){
		String q="";
		q+="select convert(varchar(10),fecha,105), ";
		q+="tc,(sucursal+numero+letra),";
		q+="(case when [debe-haber] like 'D' then IMPORTE ELSE 0 end),";
		q+="(case when [debe-haber] like 'H' then IMPORTE ELSE 0 end)";
		q+="from ve_cptes_IMPAGOS ";
		q+="where importe >0 and cuenta like '"+idcliente+"' ";
		return this.getResults(q);
	}

	public Object[][] getCptesAlfa(String idcliente){
		
		
		String q="";
		
		q+="SELECT FECHA,TC,(SUCURSAL+NUMERO+LETRA),";
		q+="(case when SALDO*-1 <0 then 0 else SALDO*-1 end) as 'debe', ";
		q+="(case when SALDO*-1 <0 then SALDO else 0 end) as 'haber' ";
		q+="FROM VE_CPTES_CREDITOS_PENDIENTES_SINREMITOS  ";
		q+="where cuenta  >= '"+idcliente+"' and Cuenta <= '"+idcliente+"' ";
		q+="and saldo<>0 ";
		q+="union ";
		q+="SELECT FECHA,TC,(SUCURSAL+NUMERO+LETRA),";
		q+="(case when saldo <0 then 0 else saldo end) as 'debe', ";
		q+="(case when saldo <0 then saldo else 0 end) as 'haber' ";
		q+="FROM VE_CPTES_SALDOS  ";
		q+="where (saldo > 0)  and cuenta  >= '"+idcliente+"' "; 
		q+="and Cuenta <= '"+idcliente+"' ";
		q+="and saldo <>0 ";
/*
		q+="select top 100 fecha,";//convert(varchar(10),fecha,105)
		q+="TC,(SUCURSAL+NUMERO+LETRA),";
		q+="(case when [debe-haber] like 'D' then IMPORTE ELSE 0 end),";
		q+="(case when [debe-haber] like 'H' then IMPORTE ELSE 0 end) ";
		q+="from ve_cptes_IMPAGOS ";
		q+="where importe >0 and cuenta >= '"+idcliente+"' and cuenta <= '"+idcliente+"' ";
		q+="union ";
		q+="select fecha,";
		q+="TC,(SUCURSAL+NUMERO+LETRA),";
		q+="(case when [debe-haber] like 'D' then IMPORTE ELSE 0 end),";
		q+="(case when [debe-haber] like 'H' then IMPORTE ELSE 0 end) ";
		q+="from ve_cptes_creditos_pendientes_sinremitos ";
		q+="where importe >0 and cuenta >= '"+idcliente+"' and cuenta <= '"+idcliente+"' ";
		q+="order by fecha ";
		*/
		return this.getResults(q);
	}
	
	public String getUpdate(String idcuenta,String descripcion){
		String q="";
		q+="update ma_cuentas set descripcion ='"+descripcion +"' where codigo like '"+idcuenta+"'";
		return q;
	}
	
	public String getUpdateAdicional(String idcuenta,
			double limite,double descuento,String idcategoria,String email,
			String calle,String numero,String piso,String postal,String idprovincia,String localidad,String telefono,
			String fax,String contacto,String observaciones,String tipo_doc,String doc,String iva,
			String condicion){
		//calle,numero,piso,postal,localidad,idprovincia,telefono,fax,contacto,observaciones,
		//condicion_iva,tipo_doc,doc,condicion venta,
		String q="";
		q+="update ma_cuentasadic set limite_credito="+limite+",";
		q+="descuento="+descuento+",";
		q+="idcategoria='"+idcategoria+"', ";
		q+="mail='"+email+"', ";
		q+="calle='"+calle+"', ";
		q+="numero='"+numero+"', ";
		q+="piso='"+piso+"', ";
		q+="cpostal='"+postal+"', ";
		q+="provincia='"+idprovincia+"', ";
		q+="localidad='"+localidad+"', ";
		q+="telefono='"+telefono+"', ";
		q+="fax='"+fax+"', ";
		q+="contacto='"+contacto+"', ";
		q+="observaciones='"+observaciones+"', ";
		q+="documento_tipo='"+tipo_doc+"',";
		q+="numero_documento='"+doc+"', ";
		q+="iva='"+iva+"', ";
		q+="idcond_cpra_vta='"+condicion+"' ";
		q+="where codigo like '"+idcuenta+"' ";
		return q;
	}
}
