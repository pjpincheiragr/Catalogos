package aplicacion.sistema.scantool.logic;


import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	public Object[][] getParametroSqlite(String iduser){
		Object[][] results=null;
		String q="";
		q+="select  iduser,nombre,pass,background,superusuario,monitor,internet,idvendedor from b_users ";
		q+="where iduser like '"+iduser+"' ";
		results=getResults(q);
		return results;
	}
	
	public boolean existePedidoCliente(String idpedido){
		boolean exist=false;
		String q="";
		q+="select idpedido from b_pdc where idpedido like '%"+idpedido+"' ";
		Object[][] results= getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
	}
	
	public boolean existePedidoProveedor(String idpedido){
		boolean exist=false;
		String q="";
		q+="select idpedido from b_pdp where idpedido like '%"+idpedido+"' ";
		Object[][] results= getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
	}
	public boolean existePedidoEspecialProveedor(String idpedido){
		boolean exist=false;
		String q="";
		q+="select idpedido from b_pep where idpedido like '%"+idpedido+"' ";
		Object[][] results= getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
	}
	
	public Object[][] getProveedorCuit(String cuit){
		String q="";
		q+="select codigo from ma_cuentasadic ";
		q+="where replace(replace(numero_documento,'-',''),' ','') like '"+cuit+"' ";
		q+="and codigo like '21101%'";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public Object[][] getArticulo(String idarticulo){
		String q="";
		q+="select m.descripcion,m.precio2,m.precio5,m.descripabrev,m.suspendidov,m.suspendidoc,sum(isnull(s.cantidadud,0)) ";
		q+="from v_ma_articulos m left outer join v_mv_stock s on m.idarticulo=s.idarticulo and s.anulado=0"; 
		q+="where m.idarticulo like '"+idarticulo+"' ";
		q+="group by m.descripcion,m.precio2,m.precio5,m.descripabrev,m.suspendidov,m.suspendidoc ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public boolean existeArticulo(String codigo){
		boolean existe=false;
		Object[][] results=this.getArticulo(codigo);
		if (results!=null){
			if (results.length>0){
				existe=true;
			}
		}
		return existe;
	}
	
	public boolean existeProveedor(String idproveedor){
		boolean exist=false;
		String q="";
		q+="select codigo from ma_cuentas where codigo like '"+idproveedor+"' and codigo like '21101%'";
		Object[][] results= getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
	}
	
	public boolean existeCliente(String cliente){
		boolean exist=false;
		String q="";
		q+="select codigo from ma_cuentas where codigo like '"+cliente+"' and codigo like '11201%'";
		Object[][] results= getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
	}
	
	public boolean update(String iduser,String idcaja,boolean origen,boolean destino){
		boolean error=false;
		int _origen=0;
		int _destino=0;
		if (origen) _origen=1;
		if (destino) _destino=1;
		String q="";
		q+="update b_users_caja set origen="+_origen+",destino="+_destino+" ";
		q+="where iduser like '"+iduser+"' and idcaja like '"+idcaja+"' ";
		this.clearBatch();
		this.addBatch(q);
		error=this.executeBatch();
		return error;
	}
	
	public boolean insert(String iduser,String idcaja,boolean origen,boolean destino){
		boolean error=false;
		int _origen=0;
		int _destino=0;
		if (origen) _origen=1;
		if (destino) _destino=1;
		String q="";
		q+="insert into b_users_caja (iduser,idcaja,origen,destino) ";
		q+="values ('"+iduser+"','"+idcaja+"',"+_origen+","+_destino+") ";
		this.clearBatch();
		this.addBatch(q);
		error=this.executeBatch();
		return error;
	}
	
	public boolean insert(String iduser, String nombre,String pass,String background,String superusuario,String monitor,String internet,String idvendedor){
		String q="";
		q+="insert into b_users (iduser,nombre,pass,background,superusuario,monitor,internet,idvendedor)";
		q+="values ('"+iduser+"','"+nombre+"','"+pass+"','"+background+"','"+superusuario+"','"+monitor+"',"+internet+",'"+idvendedor+"')";
		clearBatch();
		addBatch(q);
		return executeBatch();
	}
	
	public boolean update(String iduser, String nombre,String pass,String background,String superusuario,String monitor,String internet,String idvendedor){
		String q="";
		q+="update b_users set nombre='"+nombre+"',pass='"+pass+"',background='"+background+"',superusuario="+superusuario+",monitor="+monitor+",internet="+internet+",idvendedor='"+idvendedor+"' where iduser like '"+iduser+"' ";
		q+="";
		clearBatch();
		addBatch(q);
		return executeBatch();
	}
	
	public boolean check_user(String id){
		boolean existe=false;
		Object[][] results=this.getParametroSqlite(id);
		if (results!=null){
			existe=results.length>0;
		}
		return existe;
	}
	
	public void delete(String id){
		String q="";
		q+="delete from b_users where iduser like '"+id+"' ";
		q+="";
		clearBatch();
		addBatch(q);
		executeBatch();
	}
	
	public void delete_aplicaciones(String id){
		String q="";
		q+="delete from b_users_aplicaciones where iduser like '"+id+"' ";
		q+="";
		clearBatch();
		addBatch(q);
		executeBatch();
	}
	
}
