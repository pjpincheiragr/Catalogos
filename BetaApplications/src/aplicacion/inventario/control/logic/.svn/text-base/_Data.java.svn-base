package aplicacion.inventario.control.logic;

import aplicacion.herramientas.conexion.creator.interfaces._Interface;
import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	private String getProvQuery(String code) {
		String q = "";
		q = q + "select M.codigo,M.descripcion,";
		q = q + "isnull(a.telefono,''),";
		q = q + "isnull(a.localidad,''),";
		q = q + "isnull(a.observaciones,'')";
		q = q + "FROM MA_CUENTAS M LEFT OUTER JOIN ";
		q = q + "MA_CUENTASADIC a ON ";
		q = q + "M.CODIGO = a.CODIGO ";
		q = q + "where M.codigo like '" + code + "'";
		return q;
	}
	
	public int getIdMovimiento(){
		String q = "";
		q+="select top 1 id from v_mv_stock ";
		q+="order by id desc";
		int id=-1;
		Object[][] results=this.getResults(q);
		
		if (results!=null){
			if (results.length>0){
				try {
					id=new Integer((String) results[0][0]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return id;
	}
	
	public double getPedidoCantidad(String idarticulo){
		double cant=0;
		String q="";
		q+="SELECT I.Cantidad ";
		q+="FROM B_PEP P LEFT OUTER JOIN B_PEP_ITEM I ON P.IDPEDIDO=I.IDPEDIDO  WHERE I.IDARTICULO LIKE '"+idarticulo+"' ";
		q+="AND P.ELIMINAR =0  and P.estado like 'Enviado' ";
		q+="union ";
		q+="SELECT I.Cantidad ";
		q+="FROM B_PDP P LEFT OUTER JOIN B_PDP_ITEMs I ON P.IDPEDIDO=I.IDPEDIDO  WHERE I.IDARTICULO LIKE '"+idarticulo+"' ";
		q+="AND P.ELIMINAR =0  and P.estado like 'Enviado' ";
		Object[][] results=this.getResults(q);
		
		if (results!=null){
			if (results.length>0){
				double qi=0;
				for (int i=0;i<results.length;i++){
					String qty=(String) results[i][0];
					try {
						qi=new Double(qty);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					cant+=qi;
				}
				
				
			}
		}
		return cant;
	}
	
	public Object[][] getData(String idarticulo){
		String q="";
		q+="select m.idarticulo,m.descripcion,m.descripabrev,isnull(sum(s.cantidadud),0),isnull(m.suspendidov,0),convert(varchar(10),m.actualizacion,105),m.cuentaproveedor,c.descripcion ";
		q+="from v_ma_articulos m ";
		q+="left outer join v_mv_stock s on m.idarticulo=s.idarticulo and s.anulado =0 "; 
		q+="left outer join ma_cuentas c on m.cuentaproveedor=c.codigo ";
		q+="where m.idarticulo like '"+idarticulo+"' ";
		q+="group by m.idarticulo,m.descripcion,m.descripabrev,isnull(m.suspendidov,0),convert(varchar(10),m.actualizacion,105),m.cuentaproveedor,c.descripcion ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public String getProximoPGCorrecto(String tc){
		String prox="";
		prox=this.getProximoPG_Ceros(tc);
		return prox;
	}
	
	
	
	private int getProximoPG(String tc){
		int c=0;
		c=this.getProximoTC(""+tc+"");
		return c;
	}
	private int getProximoTC(String tc){
		int c=0;
		String q="";
		q+="select x_ultimo_nro from b_ta_cpte ";
		q+="where codigo = '"+tc+"' ";
		Object[][] aux=connection_handler.getDefaultConnector().getResults(q);
		try{
			c=new Integer(aux[0][0].toString());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return c;
	}
	
	
	private String getProximoPG_Ceros(String tc){
		String c="";
		int i=this.getProximoPG(tc);
		String aux=""+i;
		while (aux.length()<8){
			aux="0"+aux;
		}
		c=aux;
		return c;
	}
	
	public boolean Update(
			String idarticulo,
			String descripcion,
			String linea,
			String idproveedor,
			String politica,
			String lista,
			String costo,
			String publico,
			String minimo) {
		clearBatch();
		String q=this.getUpdate(idarticulo, descripcion, linea, idproveedor, politica, lista, costo, publico,minimo);
		System.out.println(q);
		addBatch(q);
		return executeBatch();
	}
	
	public boolean Insert(
			String idarticulo,
			String descripcion,
			String linea,
			String idproveedor,
			String politica,
			String lista,
			String costo,
			String publico,
			String minimo) {
		clearBatch();
		addBatch(this.getInsert(idarticulo, descripcion, linea, idproveedor, politica, lista, costo, publico,minimo));
		return executeBatch();
	}
	/*
	public String getInsert(
			String idarticulo,
			String descripcion,
			String linea,
			String idproveedor,
			String politica,
			String lista,
			String costo,
			String publico,
			String minimo) {
		String q="";
		q+="insert into v_ma_articulos (idarticulo,descripcion,DESCRIPABREV,cuentaproveedor,politicaPRECIOS,precio5,costo,precio2,PUNTOPEDIDO) values (";
		q+="'"+idarticulo+"',";
		q+="'"+descripcion+"',";
		q+="'"+linea+"',";
		q+="'"+idproveedor+"',";
		q+="'"+politica+"',";
		q+=""+lista+",";
		q+=""+costo+",";
		q+=""+publico+",";
		q+=""+minimo+")";
		return q;
	}*/
	
	public String getUpdate(
			String idarticulo,
			String descripcion,
			String linea,
			String idproveedor,
			String politica,
			String lista,
			String costo,
			String publico,
			String minimo) {
		 String q="update v_ma_articulos ";
		 q+=" set descripcion='"+descripcion+"', ";
		 q+=" descripabrev='"+linea+"', ";
		 q+=" cuentaproveedor='"+idproveedor+"', ";
		 q+=" politicaprecios='"+politica+"', ";
		 q+=" precio2="+publico+", ";
		 q+=" precio5="+lista+", ";
		 q+=" costo="+costo+", ";
		 q+=" puntopedido="+minimo+" ";
		 q+=" where idarticulo like '"+idarticulo+"' ";
		 return q;
	}
	


	public boolean check_proveedor(String idproveedor){
		boolean exist=false;
		Object[][] results = getResults(this.getProvQuery(idproveedor));
		if (results != null) {
			if (results.length > 0) {
			exist=true;
			}
		}
		return exist;
	}

	public Object[][] getControl(String idcontrol){
		Object[][] results=getResults(this.getControlQuery(idcontrol));
		return results;
	}
	
	private String getControlQuery(String idcontrol) {
		String q = "";
		q+="select idcontrol,";
		q+="linea,";
		q+="idusuario, ";
		q+="iddeposito ";
		q+="FROM b_control ";
		q+="where idcontrol like '"+idcontrol+"' ";
		q+="order by idcontrol desc";
		return q;
	}

	
	public String getUpdateQuery(String idcontrol,String fecha,String iddeposito,String iduser,String idmovimiento,String punto){
		String q="";
		q+="update b_control set ultima_modificacion=getdate(),fecha='"+fecha+"',iddeposito='"+iddeposito+"',idusuario='"+iduser+"',punto="+punto+" ";
		if (idmovimiento.compareTo("")!=0){
			q+=",movimiento="+idmovimiento+" ";
		}
		q+="where idcontrol like '"+idcontrol+"' ";
		return q;
	}
	
	public String disable_triggers(){
		String q="";
		q+="alter table v_mv_stock disable trigger all ";
		return q;
	}
	public String getDeleteStock(String idcontrol){
		String q="";
		q+="delete from v_mv_stock  ";
		q+="where tc like 'CTRL' and idcomprobante like '"+idcontrol+"' ";
		return q;
	}
	public String getEliminarStock(String idcontrol){
		String q="";
		q+="update v_mv_stock  set anulado=0 ";
		q+="where tc like 'CTRL' and idcomprobante like '"+idcontrol+"' ";
		return q;
	}
	public String getDeleteControl(String idcontrol){
		String q="";
		q+="delete from b_control where idcontrol like '"+idcontrol+"'";
		return q;
	}
	
	public String getEliminarControl(String idcontrol){
		String q="";
		q+="update b_control set eliminar=1 where idcontrol like '"+idcontrol+"'";
		return q;
	}
	public String getDeleteControlItems(String idcontrol){
		String q="";
		q+="delete from b_control_items where idcontrol like '"+idcontrol+"'";
		return q;
	}
	public boolean exist(String idcontrol){
		boolean exist=false;
		Object[][] results = getResults(this.getControlQuery(idcontrol));
		if (results != null) {
			if (results.length > 0) {
			exist=true;
			}
		}
		return exist;
	}
	
	
	public String getQueryArticulos(int top,String idproveedor,String linea,String desde,String hasta){
	String q="";
	q+="select top "+top+" 0,idarticulo,descripcion,";
	q+="descripabrev,cuentaproveedor,precio5,";
	q+="politicaprecios,costo,precio2 ";
	q+="from v_ma_articulos   ";
	String where="";
	if (idproveedor.compareTo("")!=0){
		where+=" cuentaproveedor like '"+idproveedor+"' ";
	}
	if (linea.compareTo("")!=0){
		if (where.length()>0) where+=" and ";
		where+=" descripabrev like '"+linea+"' ";
	}
	if (where.length()>0) where+=" and ";
	where+="idarticulo between '"+desde+"' and '"+hasta+"'";
	q+="where "+where+" ";
	q+="order by idarticulo";
	q+="";
	return q;
	}
	
	public Object[][] getArticulos(int top,String idproveedor,String linea,String desde,String hasta){
		Object[][] results=this.getResults(this.getQueryArticulos(top, idproveedor, linea, desde, hasta));
		return results;
	}
	
	private String getPoliticaQuery(String codigo){
		String q="";
		q+="select "; 
		q+="codigo,descripcion,fcosto,fclase2 "; 
		q+="from v_ta_politicaprecios ";
		q+="where codigo like '"+codigo+"'";
		return q;	
	}
	
	public Object[][] getPolitica(String codigo){
		String q=this.getPoliticaQuery(codigo);
		return getResults(q);
	}

	public boolean check_politica(String codigo){
	Object[][] results=this.getPolitica(codigo);
	boolean exist=false;
	if (results!=null){
		if (results.length>0){
			exist=true;
		}
	}
	return exist;
	}
	
	public boolean check_alias(String idarticulo,String idproveedor){
		Object[][] results=getResults(this.getAliasQuery(idarticulo, idproveedor));
		boolean exist=false;
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
		}
		
	private String getQueryLinePrefix(String linea){
		String q = "";
		q+="select top 1 substring(idarticulo,0,4) ";
		q+="FROM v_ma_articulos ";
		q+="where  descripabrev like '"+linea+"' ";
		q+="group by substring(idarticulo,0,4) ";
		q+="order by count(*) desc";
		return q;
	}
	
	private String getLineQuery(String linea) {
		String q = "";
		q = q + "select top 1 descripabrev ";
		q = q + "FROM v_ma_articulos ";
		q = q + "where  descripabrev like '"+linea+"'";
		return q;
	}
	
	public boolean check_linea(String linea){
		boolean exist=false;
		Object[][] results = getResults(this.getLineQuery(linea));
		if (results != null) {
			if (results.length > 0) {
			exist=true;
			}
		}
		return exist;
	}
	
	public Object[][] getLinePrefix(String linea){
		return getResults(this.getQueryLinePrefix(linea));
	}
	
	public Object[][] getProveedor(String codigo){
		String q="";
		q=q+" select m.codigo,m.descripcion,isnull(c.politica_default,''),isnull(p.descripcion,''),isnull(c.odbc_name,''),isnull(c.consulta,''),isnull(c.linea,'') ";
		q=q+" from ma_cuentas m left outer join b_configuracion_catalogos c ";
		q=q+" left outer join v_ta_politicaprecios p ";
		q=q+" on c.politica_default=p.codigo ";
		q=q+" on m.codigo=c.idproveedor ";
		q=q+" where m.codigo like '"+codigo+"' ";
		Object[][] results=getResults(q);
		return results;
	}
	
	public String getAliasQuery(String idarticulo,String idproveedor){
		String q="";
		q+=" select id from b_alias ";
		q+=" where idarticulo like '"+idarticulo+"' ";
		q+=" and idproveedor like '"+idproveedor+"' ";
		return q;
	}
	public Object[][] getPrecioProveedor(String idarticulo,String idproveedor){
		String q="";
		q=q+" select c.precio5 from b_alias b ";
		q=q+" left outer join b_codigos c ";
		q=q+" on b.idcodigo=c.idcodigo ";
		q=q+" and b.lineaproveedor=c.lineaproveedor ";
		q=q+" and b.idproveedor =c.idproveedor ";
		q=q+" where b.idarticulo like '"+idarticulo+"' "; 
		q=q+" and b.idproveedor like '"+idproveedor+"' ";
		return getResults(q);
	}
	
	public String getQueryAlias(String idarticulo){
		String q="select a.idcodigo,a.lineaproveedor,a.idproveedor,m.descripcion,c.precio5,convert(varchar(10),c.ultimo_upd,105) ";
		q=q+" from b_alias a left outer join ma_cuentas m on a.idproveedor = m.codigo ";
		q=q+" left outer join b_codigos c on a.idproveedor=c.idproveedor and a.idcodigo = c.idcodigo and a.lineaproveedor=c.lineaproveedor ";
		q=q+" where a.idarticulo like '"+idarticulo+"'  ";
		return q;
	}
	
	public Object[][] getAlias(String idarticulo){
		return getResults(this.getQueryAlias(idarticulo));
	}
	
	public String getInsert(String idcontrol,String fecha,String iddeposito,String idusuario,String linea,String desde,String hasta,String punto,String free){
		String q="";
		q+="insert into b_control (idcontrol,fecha,iddeposito,idusuario,linea,desde,hasta,punto,free) ";
		q+="values (";
		q+="'"+idcontrol+"',";
		q+="'"+fecha+"',";
		q+="'"+iddeposito+"',";
		q+="'"+idusuario+"',";
		q+="'"+linea+"',";
		q+="'"+desde+"',";
		q+="'"+hasta+"',";
		q+=""+punto+",";
		q+=""+free+")";
		System.out.println(q);
		return q;
	}
	
	public String testStock(String linea,String desde,String hasta){
		String q="";
		q+="select count(idarticulo) from v_ma_Articulos ";
		q+="where descripabrev like '"+linea+"' ";
		q+="and idarticulo between '"+desde+"' and '"+hasta+"' ";
		q+="";
		return q;
	}
	
	public boolean getStock(String linea,String desde,String hasta){
		boolean ok=false;
		int count=0;
		String q=this.testStock(linea, desde, hasta);
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				try {
					count=new Integer(results[0][0].toString());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		ok=count>0;
		return ok;
	}
	public String getQueryMovimientos(String idmovimiento,String linea,String iddeposito){
		String q="";
		q+="select stock.id,stock.tiempo,stock.tc,stock.idcomprobante,stock.idarticulo,articulos.descripcion,stock.cantidadud ";
		q+="FROM v_mv_stock stock  ";
		q+="LEFT OUTER  JOIN  V_MA_ARTICULOS articulos "; 
		q+="ON stock.IdArticulo=articulos.IDARTICULO  and stock.anulado=0 "; 
		q+="and stock.iddeposito like '"+iddeposito+"'  ";
		if (idmovimiento.compareTo("")==0){
			idmovimiento="0";
		}
		q+="where stock.id > "+idmovimiento+"  and articulos.descripabrev like '"+linea+"' ";
		q+="order by stock.tiempo ";
		System.out.println(""+q);
		return q;
	}
	
	public String getQueryControlItemsFree(String idcontrol,String iddeposito,String fecha,String idmovimiento){
		String q="";
		q+="select articulos.idarticulo as idarticulo, ";
		q+="articulos.descripcion as descripcion, ";
		q+="articulos.costo, ";
		q+="sum(isnull(stock.cantidadud,0)) AS stk, ";
		q+="isnull(Control.cantidad, 0) as cant, ";
		q+="isnull(Control.etiqueta, 0) as etiqueta ";
		q+="FROM b_control ctr ";
		q+="left outer join b_control_items control on ctr.idcontrol=control.idcontrol ";
		q+="left outer join  ";
		q+="V_MA_ARTICULOS articulos ";
		q+="on control.idarticulo=articulos.idarticulo and ctr.idcontrol=control.idcontrol ";
		q+="LEFT OUTER  JOIN  ";
		q+="v_mv_stock stock ON "; 
		q+="articulos.IDARTICULO = stock.IdArticulo and stock.anulado=0 and stock.iddeposito like '"+iddeposito+"' ";
		if (idmovimiento.compareTo("")!=0){
			q+="and stock.id <= "+idmovimiento+" " ;
		}else{
			if (fecha.compareTo("")!=0){
				q+="and stock.fecha between '01-01-1900' and '"+fecha+"' ";	
			}
		}
		
		 
		q+="where ctr.idcontrol like '"+idcontrol+"'  ";
		q+="group by articulos.idarticulo,articulos.descripcion,articulos.costo,Control.cantidad,Control.etiqueta ";
		q+="order by articulos.IDARTICULO  ";
		System.out.println(""+q);
		
		return q;
	}
	
	public String getQueryControlItems(String idcontrol,String iddeposito,String fecha,String idmovimiento){
		String q="";
		q+="select articulos.idarticulo as idarticulo, ";
		q+="articulos.descripcion as descripcion, ";
		q+="articulos.costo, ";
		q+="sum(isnull(stock.cantidadud,0)) AS stk, ";
		q+="isnull(Control.cantidad, 0) as cant, ";
		q+="isnull(Control.etiqueta, 0) as etiqueta ";
		q+="FROM b_control ctr ";
		q+="left outer join  ";
		q+="V_MA_ARTICULOS articulos on ctr.linea=articulos.descripabrev ";
		q+="and articulos.idarticulo between isnull((case when ctr.desde like '' then '001-000' else ctr.desde end),'001-000') and isnull((case when ctr.hasta like '' then '999-zzz' else ctr.hasta end),'999-zzz') ";
		q+="LEFT OUTER  JOIN  ";
		q+="v_mv_stock stock ON "; 
		q+="articulos.IDARTICULO = stock.IdArticulo and stock.anulado=0 and stock.iddeposito like '"+iddeposito+"' ";
		if (idmovimiento.compareTo("")!=0){
			q+="and stock.id <= "+idmovimiento+" " ;
		}else{
			if (fecha.compareTo("")!=0){
				q+="and stock.fecha between '01-01-1900' and '"+fecha+"' ";	
			}
		}
		
		q+="left outer join b_control_items control ";
		q+="on articulos.idarticulo=control.idarticulo and control.idcontrol=ctr.idcontrol "; 
		q+="where ctr.idcontrol like '"+idcontrol+"'  ";
		q+="group by articulos.idarticulo,articulos.descripcion,articulos.costo,Control.cantidad,Control.etiqueta ";
		q+="order by articulos.IDARTICULO  ";
		System.out.println(""+q);
		
		return q;
	}
	
	public boolean aplicado(String idcontrol){
		boolean aplicado=false;
		String q="select idcomprobante from v_mv_stock where tc like 'CTRL' and idcomprobante like '"+idcontrol+"' and anulado=0 ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				aplicado=true;	
			}
		}
		return aplicado;
		
	}
	public String updateControlItem(String idcontrol,String idarticulo,double cantidad,double etiqueta){
		String q="";
		q+="update b_control_items set cantidad="+cantidad+",etiqueta="+etiqueta+" ";
		q+="where idcontrol like '"+idcontrol+"' and idarticulo like '"+idarticulo+"'";
		q+="";
		return q;
	}
	public String deleteControlItem(String idcontrol,String idarticulo,double cantidad){
		String q="";
		q+="delete from  b_control_items ";
		q+="where idcontrol like '"+idcontrol+"' and idarticulo like '"+idarticulo+"'";
		q+="";
		return q;
	}
	
	public String insertControlItem(String idcontrol,String idarticulo,double cantidad,double etiqueta){
		String q="";
		q+="insert into b_control_items (idcontrol,idarticulo,cantidad,etiqueta) ";
		q+="values ('"+idcontrol+"','"+idarticulo+"',"+cantidad+","+etiqueta+") ";
		q+="";
		return q;
	}
	
	public String insertControlHistory(String idcontrol,String idarticulo,double cantidad,double etiqueta,String idusuario,double stock,double ajuste){
		String q="";
		q+="insert into b_control_history (idcontrol,idarticulo,cantidad,etiqueta,idusuario,stock,ajuste) ";
		q+="values ('"+idcontrol+"','"+idarticulo+"',"+cantidad+","+etiqueta+",'"+idusuario+"',"+stock+","+ajuste+") ";
		q+="";
		return q;
	}
	
	public Object[][] getControlItems(String idcontrol,String iddeposito,String fecha,String idmovimiento){
		return this.getResults(this.getQueryControlItems(idcontrol,iddeposito,fecha,idmovimiento));
	}
	
	public Object[][] getControlItemsFree(String idcontrol,String iddeposito,String fecha,String idmovimiento){
		return this.getResults(this.getQueryControlItemsFree(idcontrol,iddeposito,fecha,idmovimiento));
	}
	public Object[][] getMovimientos(String idmovimiento,String linea,String iddeposito){
		return this.getResults(this.getQueryMovimientos(idmovimiento, linea, iddeposito));
	}
	
	public boolean existeItem(String idcontrol,String idarticulo){
		boolean exist=false;
		String q="";
		q+="select idarticulo from b_control_items ";
		q+="where idcontrol like '"+idcontrol+"' and idarticulo like '"+idarticulo+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=true;	
			}
		}
		return exist;
	}
	
	public String getInsertQueryVMVStock(String tc,String idcomprobante,String idarticulo,String descripcion,String cantidad,int sec,String fecha,String precio,String iddeposito){
		String q="";
		
		double price=0.0;
		try {
			precio=precio.replace(",", "");
			price=new Double(precio);
		}catch(Exception e){
			
		}
		double qty=0.0;
		try {
			cantidad=cantidad.replace(",", "");
			qty=new Double(cantidad);
		}catch(Exception e){
			
		}
		q="insert into v_mv_stock ";
		q=q+"(tc,idcomprobante,secuencia,fecha,idarticulo,descripcion,idunidad,cantidadud,cantidad,costo,precioventa,stock,iddeposito,UNEGOCIO)";
		q=q+"values ";
		q=q+"('"+tc+"','"+idcomprobante+"','"+sec+"',getdate(),'"+idarticulo+"','"+descripcion+"','   1',"+qty+","+qty+","+price+",0.0,0,'"+iddeposito+"','   1')";
		return q;
		}
	
	public Object[][] getVendedor(String iduser){
		String q="";
		q+="select b.idvendedor,v.nombre ";
		q+="from b_users b left outer join v_ta_vendedores v on ltrim(b.idvendedor)=ltrim(v.idvendedor) ";
		q+="where b.iduser like '"+iduser+"'";
		return this.getResults(q);
	}
	public Object[][] getArticulo(String idarticulo){
		return this.getArticulo(idarticulo,"");
	}
	
	public Object[][] getArticulo(String idarticulo,String iddeposito){
		String q="";
		q+="select m.descripcion,m.precio2,m.precio5,m.descripabrev,m.suspendidov,m.suspendidoc,sum(isnull(s.cantidadud,0)) ";
		q+="from v_ma_articulos m left outer join v_mv_stock s on m.idarticulo=s.idarticulo ";
		if (iddeposito.compareTo("")!=0){
			q+=" and s.iddeposito like '"+iddeposito+"' ";	
		}
		q+="where m.idarticulo like '"+idarticulo+"' ";
		
		q+="group by m.descripcion,m.precio2,m.precio5,m.descripabrev,m.suspendidov,m.suspendidoc ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
}
