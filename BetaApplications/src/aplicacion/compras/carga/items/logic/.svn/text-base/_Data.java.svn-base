package aplicacion.compras.carga.items.logic;
import aplicacion.modelo.logic.Data;
public class _Data extends Data{
	
	public String getEliminarAsientoBeta(String cuenta,String tc,String idcomprobante){
		
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("eliminar_asiento_beta", parameters);
		System.out.println(">"+q);
		return q;
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
	
	public String getVendedorValidacion(String password){
		String q="select idvendedor from b_users where pass like '"+password+"' ";
		String idvendedor="";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				idvendedor=(String) results[0][0];
			}
		}
		return idvendedor;
	}

	public Object[][] getVendedor(String iduser){
		String q="";
		q+="select b.idvendedor,v.nombre ";
		q+="from b_users b left outer join v_ta_vendedores v on ltrim(b.idvendedor)=ltrim(v.idvendedor) ";
		q+="where b.iduser like '"+iduser+"'";
		return this.getResults(q);
	}
	public String getEliminarEnlace(String tc,String idcomprobante,String cuenta){
		String q="delete from b_compras_pp where ";
		q+="tc like '"+tc+"' ";
		q+="and idcomprobante like '"+idcomprobante+"' ";
		q+=" and cuenta like '"+cuenta+"' ";
		q+="";
		System.out.println(">"+q);
		return q;
	}
	
	public String getEliminarCpte(String cuenta,String tc,String idcomprobante){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("eliminar_cpte", parameters);
		System.out.println(">"+q);
		return q;
	}
	
	public String getCuentaImpuesto(String impuesto){
		String cuenta=this.getParametroServer(impuesto);
		if (cuenta==null)cuenta="";
		return cuenta;
	}
	
	public String getEliminarCpteBeta(String cuenta,String tc,String idcomprobante){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("b_eliminar_cpte", parameters);
		System.out.println(">"+q);
		return q;
	}
	
	public String getEliminarDigital(String cuenta,String tc,String idcomprobante){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("eliminar_digital", parameters);
		System.out.println(">"+q);
		
		return q;
	}
	
	public String getEliminarDigitalBeta(String cuenta,String tc,String idcomprobante){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("eliminar_digital", parameters);
		System.out.println(">"+q);
		
		return q;
	}
	
	public String getEliminarAsiento(String cuenta,String tc,String sucursal,String numero,String letra){
		String[] parameters=new String[]{
				cuenta,
				tc,
				sucursal,
				numero,
				letra
				};
		String q=this.getQuery("eliminar_asiento", parameters);
		System.out.println(">"+q);
		return q;
	}	

	public String getDeleteFotosQuery(String idproveedor,String tc,String idcomprobante){
		String q="delete from facturas where idproveedor like '"+idproveedor+"' and tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"'";
		return q;
	}
	public boolean deleteFotos(String idproveedor,String tc,String idcomprobante){
	String q=this.getDeleteFotosQuery(idproveedor, tc, idcomprobante);
		this.getConnector("MySQL").clearBatch();
		this.getConnector("MySQL").addBatch(q);
		return this.getConnector("MySQL").executeBatch();
		}
	public Object[][] getAcountInfo(String cuenta){
		String[] parameters=new String[]{
				cuenta
			};
			String q=this.getQuery("account_info", parameters);
			Object[][] results=this.getResults(q);
			return results;
	}
	
	private Object[][] getAsientoBeta(String mes_operativo,String numero_asiento){
		String[] parameters=new String[]{
				mes_operativo,
				numero_asiento
				};
		String q=this.getQuery("b_asiento", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	private Object[][] getAsiento(String mes_operativo,String numero_asiento){
		String[] parameters=new String[]{
				mes_operativo,
				numero_asiento
				};
		String q=this.getQuery("asiento", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public Object[][] getImpuestos(String idproveedor,String tc,String idcomprobante){
		String sucursal=idcomprobante.substring(0,4);
		String numero=idcomprobante.substring(4,12);
		String letra=idcomprobante.substring(12, 13);
		String[] parameters=new String[]{
				idproveedor,
				tc,
				sucursal,
				numero,
				letra
				};
		String q=this.getQuery("asiento_numero", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		int mes_operativo=-1;
		int numero_asiento=-1;
		
		if (results!=null){
			if (results.length>0){
				try {
					mes_operativo=new Integer((String)results[0][0]);
					numero_asiento=new Integer((String)results[0][1]);
				}catch(Exception e){
					
				}
			}
		}
		if (mes_operativo >0 & numero_asiento>0){
			results=this.getAsiento(""+mes_operativo, ""+numero_asiento);
		}else{
			results=null;
		}
		return results;
	}
	
	public Object[][] getImpuestosBeta(String idproveedor,String tc,String idcomprobante){
		String[] parameters=new String[]{
				idproveedor,
				tc,
				idcomprobante
				};
		String q=this.getQuery("asiento_numero_beta", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		int mes_operativo=-1;
		int numero_asiento=-1;
		
		if (results!=null){
			if (results.length>0){
				try {
					mes_operativo=new Integer((String)results[0][0]);
					numero_asiento=new Integer((String)results[0][1]);
				}catch(Exception e){
					
				}
			}
		}
		if (mes_operativo >0 & numero_asiento>0){
			results=this.getAsientoBeta(""+mes_operativo, ""+numero_asiento);
		}else{
			results=null;
		}
		return results;
	}
	public Object[][] getProveedor(String idproveedor){
		String[] parameters=new String[]{
				idproveedor
				};
		String q=this.getQuery("proveedor", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
		
		
	}
	
	public Object[][] getImputacionCodigos(String idproveedor){
		String[] parameters=new String[]{
				idproveedor,
				
				};
		String q=this.getQuery("imputacion", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	public Object[][] existe_cpte(String idproveedor,String tc,String idcomprobante){
		String[] parameters=new String[]{
				idproveedor,
				tc,
				idcomprobante
				};
		String q=this.getQuery("existe_cpte", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
		
	}
	
	public boolean existCpte(String cuenta,String tc,String idcomprobante){
		boolean exist=false;
		String q="select tc from c_mv_cpte where tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"' and cuenta like '"+cuenta +"'";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
	}
	
	public boolean existCpteBeta(String cuenta,String tc,String idcomprobante){
		boolean exist=false;
		String q="select tc from b_fcn where tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"' and cuenta like '"+cuenta +"'";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
	}
	public boolean existDigital(String cuenta,String tc,String idcomprobante){
		boolean exist=false;
		String q="select tc from b_cpte_digital where tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"' and cuenta like '"+cuenta +"'";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
	}
	
	public String getInsertDigital(String cuenta,String tc,String idcomprobante,int fotos,int carga_articulos,String fecha_carga_articulos,String idusuario,String ip){
		String[] parameters=new String[]{
			cuenta,tc,idcomprobante,""+fotos,""+carga_articulos,fecha_carga_articulos,idusuario,ip	
		};
		String q=this.getQuery("insert_digital", parameters);
		return q;
	}

	public boolean existeDigital(String cuenta,String tc,String idcomprobante,int fotos,int carga_articulos){
		boolean exist=false;
		String q="";
		q+="select * from b_cpte_digital where tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"' and cuenta like '"+cuenta+"'";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				exist=true;
			}
		}
		return exist;
	}
	public String getInsertDigitalBeta(String cuenta,String tc,String idcomprobante,int fotos,int carga_articulos){
		String[] parameters=new String[]{
			cuenta,tc,idcomprobante,""+fotos,""+carga_articulos	
		};
		String q=this.getQuery("insert_digital_beta", parameters);
		return q;
	}
	public Object[][] getImputaciones(String codigo){
		String[] parameters=new String[]{
			codigo	
		};
		String q=this.getQuery("imputaciones", parameters);
		System.out.println(q);
		Object[][] results=this.getResults(q);
		return results;
	}
	
	public String getInsert(String cuenta,
			String tc,
			String idcomprobante,
			String fecha,
			String fecha_contable,
			String vencimiento,
			String importe,
			String importe_s_iva,
			String importe_insumos,
			String importe_iva,
			String neto_gravado,
			String sucursal,
			String numero,
			String letra,
			String idvendedor
			){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante,
				fecha,
				fecha_contable,
				vencimiento,
				importe,
				importe_s_iva,
				importe_insumos,
				importe_iva,
				neto_gravado,
				sucursal,
				numero,
				letra,
				idvendedor
				};
		String q=this.getQuery("insert", parameters);
		System.out.println(">insert query"+q);
		return q;
	}
	
	public String getInsertBeta(String cuenta,
			String tc,
			String idcomprobante,
			String fecha,
			String fecha_contable,
			String vencimiento,
			String importe,
			String importe_s_iva,
			String importe_insumos,
			String importe_iva,
			String neto_gravado,
			String sucursal,
			String numero,
			String letra,
			String idvendedor,
			String idcomprobante_asociado
			){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante,
				fecha,
				fecha_contable,
				vencimiento,
				importe,
				importe_s_iva,
				importe_insumos,
				importe_iva,
				neto_gravado,
				sucursal,
				numero,
				letra,
				idvendedor,
				idcomprobante_asociado
				};
		String q=this.getQuery("insertBeta", parameters);
		System.out.println(">insert query"+q);
		return q;
	}
	public String getEliminarCpteInsumos(String cuenta,String tc,String idcomprobante){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("eliminar_cpteinsumos", parameters);
		System.out.println(">"+q);
		
		return q;
	}
	
	
	public String getEliminarCpteInsumosBeta(String cuenta,String tc,String idcomprobante){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("eliminar_cpteinsumosBeta", parameters);
		System.out.println(">"+q);
		
		return q;
	}
	public String getEliminarStock(String cuenta,String tc,String idcomprobante){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("eliminar_stock", parameters);
		System.out.println(">"+q);
		
		return q;
	}
	
	public String getBlock(){
		String q=this.getMessage("block");
		System.out.println(">"+q);
		
		return q;
	}
	public String getunBlock(){
		String q=this.getMessage("unblock");
		System.out.println(">"+q);
		
		return q;
	}
	public Object[][] getCpteArticulos(String cuenta,String tc,String idcomprobante){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("cpte_articulos", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	public Object[][] getCpteArticulosBeta(String cuenta,String tc,String idcomprobante){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("cpte_articulosBeta", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	public Object[][] getStock(String idarticulo){
		String q="";
		q+="select a.idarticulo,a.descripcion,sum(isnull(s.cantidadud,0)) ";
		q+="from v_ma_articulos a ";
		q+="left outer join v_mv_stock s ";
		q+="on a.idarticulo=s.idarticulo ";
		q+="where a.idarticulo like '"+idarticulo+"' ";
		q+="group by a.idarticulo,a.descripcion ";
		
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
		
		
	}
	
	public Object[][] getArticulos(String cuenta,String tc,String idcomprobante){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("cpte_articulos", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
		
		
	}
	public Object[][] getArticulosBeta(String cuenta,String tc,String idcomprobante){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("cpte_articulosBeta", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
		
		
	}
	public String getUpdateArticulo(String idarticulo,String precio,String iduser){

		String q="update v set precio5="+precio+",costo="+precio+"*isnull(p.mcosto,1),precio2="+precio+"*isnull(p.mcosto,1)*isnull(p.mclase2,1.6), ";
		q+="actualizacion=getdate(),iduser_actualizador='"+iduser+"' ";
		q+="from v_ma_articulos v left outer join v_ta_politicaprecios p ";
		q+="on v.politicaprecios=p.codigo ";
		q+="where v.idarticulo like '"+idarticulo+"' ";
		return q;
		
	}
	
	public Object[][] getCurrentValues(String idarticulo){
		String q="";
		q+="select descripcion,precio2,costo,precio5,politicaprecios,";
		q+="suspendidov,suspendidoc,puntopedido from v_ma_articulos where idarticulo like '"+idarticulo+"' ";
		q+="";
		Object[][] results=this.getResults(q);
		return results;
	}
	
	
	public String getRegistrarCambio(String iduser,
			String ip,
			String idarticulo,
			String precio5,
			String validacion,
			String idoperacion
		){
		String q="";
		q+="insert into b_auditor_articulos ";
		q+="(iduser,ip,fecha,idarticulo,precio,precio_old,";
		q+="costo,costo_old,precio5,precio5_old,politicaprecios,validacion,";
		q+="idoperacion) ";
		q+="select '"+iduser+"',";
		q+="'"+ip+"',";
		q+="getdate(),";
		q+="v.idarticulo,";
		q+=""+precio5+"*p.mcosto*p.mclase2,";
		q+="v.precio2,";
		q+=""+precio5+"*p.mcosto,";
		q+="v.costo,";
		q+=""+precio5+"*,";
		q+="v.precio5,";
		q+="v.politicaprecios,";
		q+="'"+validacion+"' ";
		q+="from v_ma_articulos v ";
		q+="left outer join v_ta_politicaprecios p ";
		q+="on v.politicaprecios=p.codigo ";
		q+="where idarticulo like '"+idarticulo+"' ";
		
		return q;
	}

	
	public Object[][] getArticulosPrecios(String cuenta,String tc,String idcomprobante){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("cpte_articulos_precios", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
		
		
	}
	
	public Object[][] getArticulosPreciosBeta(String cuenta,String tc,String idcomprobante){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("cpte_articulos_preciosBeta", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
		
		
	}
	public Object[][] getLineas(String idproveedor){
		String[] parameters=new String[]{
				idproveedor
				};
		String q=this.getQuery("lineas", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
		
		
	}
	
	public Object[][] getPrefijo(String idproveedor,String linea){
		String[] parameters=new String[]{
				idproveedor,
				linea
				};
		String q=this.getQuery("prefijo", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
		
		
	}
	/*
	set @secuencia=? 
set @idarticulo='?' 
set @descripcion='?' 
set @cantidad=? 
set @costo=? 
set @precioventa=?   
set @descuento=? 
set @importe=@costo
set @importe_s_iva=@costo
set @precio_lista=@costo
set @total=?  
	 */
	public String getInsertSets(
		String secuencia,
		String idarticulo,
		String descripcion,
		String cantidad,
		String costo,
		String precioventa,
		String descuento,
		String total,
		String tc_origen,
		String idcomprobante_origen
		){
		if (tc_origen.compareTo("RMC")!=0){
			tc_origen="";
			idcomprobante_origen="";
		}
	String[] parameters=new String[]{
			secuencia,
			idarticulo,
			descripcion,
			cantidad,
			costo,
			precioventa,
			descuento,
			total,
			tc_origen,
			idcomprobante_origen
			};
	String q=this.getQuery("insert_sets", parameters);
	
	return q;
	}
	
	public String getInsertStock(){
		String q=this.getQuery("insert_stock",null);
		System.out.println(q);
		return q;
	}
	
	public String getInsertCpte(){
		String q=this.getQuery("insert_cpte",null);
		System.out.println(q);
		return q;
	}
	public String getInsertCpteBeta(){
		String q=this.getQuery("insert_cpteBeta",null);
		System.out.println(q);
		return q;
	}
	public int getProximoPEDIDO() {
		int c = 0;
		String q = "select x_ultimo_nro from b_ta_cpte where codigo = 'FCN'";
		Object[][] aux = getResults(q);
		try {
			c = new Integer(aux[0][0].toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return c;
	}
	public void genera_nuevo_pedido() {
		int next = getProximoPEDIDO() + 1;
		String q = "update b_ta_cpte set x_ultimo_nro=" + next
				+ " where codigo = 'FCN'";
		clearBatch();
		addBatch(q);
		boolean b = executeBatch();
	}
	
	public String getUpdateTc(String tc){
		String q="";
		q+="update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 ";
		q+="where codigo = '"+tc+"' ";
		return q;
	}
	public Object[][] getFotos(String cuenta,String tc,String idcomprobante){
		String q="";
		q+="";
		q+="select fotos ";
		q+="from b_cpte_digital "; 
		q+="where  ";
		q+="cuenta like '"+cuenta+"' ";
		q+="and tc like '"+tc+"' ";
		q+="and idcomprobante like '"+idcomprobante+"' ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
		
		
	}
	
	public String getInsertEncabezado(String cuenta,String tc,String idcomprobante,String fecha){
		String[] parameters=new String[]{
				fecha,
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("insert_encabezado", parameters);
		return q;
	}
	
	public String getUpdateDigital(String cuenta,String tc,String idcomprobante,String carga_articulos,String fotos,String fecha_carga_articulos,String idusuario,String ip){
		String q="";
		q+=" update b_cpte_digital ";
		q+=" set fecha_carga=getdate(),fotos='"+fotos+"',carga_articulos='"+carga_articulos+"',fecha_carga_articulos='"+fecha_carga_articulos+"',idusuario='"+idusuario+"',ip='"+ip+"' ";
		q+=" where cuenta = '"+cuenta+"' ";
		q+=" and tc = '"+tc+"' "; 
		q+=" and idcomprobante ='"+idcomprobante+"' ";
		return q;
	}
	
	public String getDeleteDigital(String tc,String idcomprobante,String cuenta){
		String q="";
		q+="delete from b_cpte_digital ";
		q+=" where cuenta = '"+cuenta+"' ";
		q+=" and tc = '"+tc+"' "; 
		q+=" and idcomprobante ='"+idcomprobante+"' ";
		return q;
	}
	
	public Object[][] getCpte(String cuenta,String tc,String idcomprobante){
		
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("cpte", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
		
		
	}
	
	public Object[][] getCpteBeta(String cuenta,String tc,String idcomprobante){
		String[] parameters=new String[]{
				cuenta,
				tc,
				idcomprobante
				};
		String q=this.getQuery("cpteBeta", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	/*
	public Object[][] getArticulo(String idarticulo){
		String[] parameters=new String[]{
				idarticulo
				};
		String q=this.getQuery("articulo", parameters);
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}*/
	
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
	
	public boolean bloqueado(String articulo){
		boolean suspendidov=false;
		Object[][] results=getArticulo(articulo);
		if (results!=null){
			if (results.length>0){
				System.out.println("Valido?");
				suspendidov=results[0][5].toString().compareTo("1")==0;		
			}
		}
		return suspendidov;
	}
	
	
	public Object[][] getData(String idarticulo){
		String q="";
		q+="select m.idarticulo,m.descripcion,m.descripabrev,sum(isnull(s.cantidadud,0)),isnull(m.suspendidoc,0),convert(varchar(10),m.actualizacion,105),precio5 ";
		q+="from v_ma_articulos m ";
		q+="left outer join v_mv_stock s on m.idarticulo=s.idarticulo and s.anulado=0 "; 
		q+="where m.idarticulo like '"+idarticulo+"' ";
		q+="group by m.idarticulo,m.descripcion,m.descripabrev,isnull(m.suspendidoc,0),convert(varchar(10),m.actualizacion,105),precio5 ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}

	public Object[][] getPedidosEspeciales(String idcuenta){
		String q="";
		q+="SELECT CONVERT(VARCHAR(10), P.FECHA_CREACION,105) AS FECHA,'PEP',P.IDPEDIDO,P.DESCRIPCION,P.ESTADO,V.NOMBRE,T.NOMBRE AS TRANSPORTE ";
		q+="FROM B_PEP P LEFT OUTER JOIN V_TA_VENDEDORES V ON P.IDVENDEDOR=V.IDVENDEDOR LEFT OUTER JOIN TRANSPORTES T ON P.IDTRANSPORTE=T.IDTRANSPORTE ";
		q+="WHERE P.CLIENTE LIKE '"+idcuenta+"' AND ESTADO LIKE 'ENVIADO' ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public Object[][] getRemitos(String idcuenta){
		String q="";
		q+="select c.fecha,c.tc,c.idcomprobante,c.importe ";
		q+="from c_mv_cpte c ";
		q+="left outer join mv_aplicacion s ";
		q+="on c.tc=s.tco_origen and c.idcomprobante=s.idcomprobante_origen ";
		q+="where c.tc like 'rmc' ";
		q+="and c.cuenta like '"+idcuenta+"' ";
		q+="and s.tc is null ";
		q+="group by c.fecha,c.tc,c.idcomprobante,c.importe,s.tc ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	public Object[][] getPedidosProyeccion(String idcuenta){
		String q="";
		q+="SELECT CONVERT(VARCHAR(10), P.FECHA_creacion,105) AS FECHA,'PDP',P.IDPEDIDO,P.DESCRIPCION,P.ESTADO,V.NOMBRE,T.NOMBRE AS TRANSPORTE ";
		q+="FROM B_PDP P LEFT OUTER JOIN V_TA_VENDEDORES V ON P.IDVENDEDOR=V.IDVENDEDOR LEFT OUTER JOIN TRANSPORTES T ON P.IDTRANSPORTE=T.IDTRANSPORTE ";
		q+="WHERE P.CUENTA LIKE '"+idcuenta+"' AND ESTADO LIKE 'ENVIADO' ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public String getInsert_enlace_compras_pedido(String tc,String idcomprobante,
			String cuenta,String pp_tc,String pp_idcomprobante,String idarticulo){
		String q="";
		q+="insert into b_compras_pp ";
		q+="(cuenta,tc,idcomprobante,pp_tc,pp_idcomprobante,idarticulo) values (";
		q+="'"+cuenta+"',";
		q+="'"+tc+"',";
		q+="'"+idcomprobante+"',";
		q+="'"+pp_tc+"',";
		q+="'"+pp_idcomprobante+"',";
		q+="'"+idarticulo+"')";
		q+="";
		return q;
	}
	
	public String getDeleteEnlace(String tc,String idcomprobante,String cuenta){
		String q="delete from b_compras_pp where ";
		q+="tc like '"+tc+"' ";
		q+="and idcomprobante like '"+tc+"' ";
		q+=" and cuenta like '"+cuenta+"' ";
		q+="";
		return q;
	}
	public Object[][] getPedidosEspecialesItems(String idpedido){
		String q="";
		q+="SELECT I.IDARTICULO,I.DESCRIPCION,I.CANTIDAD,M.PRECIO5,ISNULL(M.DESCRIPABREV,'') ";
		q+="FROM B_PEP_ITEM I LEFT OUTER JOIN V_MA_ARTICULOS M ON I.IDARTICULO=M.IDARTICULO ";
		q+="WHERE I.IDPEDIDO LIKE '"+idpedido+"' ORDER BY I.IDARTICULO ";
		q+="";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	public Object[][] getRemitosItems(String cuenta,String idcomprobante){
		String q="";
		q+="SELECT I.IDARTICULO,I.DESCRIPCION,I.CANTIDAD,M.PRECIO5,ISNULL(M.DESCRIPABREV,'') ";
		q+="FROM c_mv_cpteinsumos I LEFT OUTER JOIN V_MA_ARTICULOS M ON I.IDARTICULO=M.IDARTICULO ";
		q+="WHERE I.cuenta LIKE '"+cuenta+"' and i.idcomprobante like '"+idcomprobante+"' ORDER BY I.IDARTICULO ";
		q+="";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public boolean tiene_remitos_aplicados(String cuenta,String tc,String idcomprobante){
		boolean ok=false;
		Object[][] results=this.getRemitos_aplicados(cuenta,tc, idcomprobante);
		if (results!=null){
			if (results.length>0){
				String tcorigen=(String) results[0][0];
				if (tcorigen.compareTo("")!=0){
					ok=true;	
				}
				
				
			}
		}
		return ok;
	}
	public Object[][] getRemitos_aplicados(String cuenta,String tc,String idcomprobante){
		String q="select a.tco_origen,a.idcomprobante_origen,convert(varchar(10),c.fecha,105) ";
		q+="from mv_aplicacion a left outer join c_mv_Cpte c on a.tco_origen=c.tc and a.idcomprobante_origen=c.idcomprobante ";
		q+="where a.tc like '"+tc+"' and a.idcomprobante like '"+idcomprobante+"' and a.cuenta like '"+cuenta+"' ";
		q+=" and a.tc_print not like 'PG' and a.tc_print not like 'OPG' ";  
		System.out.println("remitos aplicados?"+tc+"-"+idcomprobante+">"+q);
		Object[][] results=this.getResults(q);
		return results;
		
	}
	
	public String getDelete_remitos_aplicados(String cuenta,String tc,String idcomprobante){
		String q="delete from mv_aplicacion where tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"' and cuenta like '"+cuenta+"' and tco_origen like 'rmc' ";
		return q;
		
	}
	
	public boolean remito_incluido_en_factura(String cuenta,String tc, String idcomprobante){
		boolean ok=false;
		String q="";
		q+="select * from mv_aplicacion ";
		q+="where tco_origen like '"+tc+"' and idcomprobante_origen like '"+idcomprobante+"' and cuenta like '"+cuenta+"' ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				ok=true;
			}
		}
		return ok;
	}
	
	public Object[][] getPedidosProyectadosItems(String idpedido){
		String q="";
		q+="SELECT items.IDARTICULO,items.DESCRIPCION,items.CANTIDAD,articulo.PRECIO5,ISNULL(articulo.DESCRIPABREV,'') ";
		q+="from b_pdp_lineas lineas left outer join v_ma_articulos articulo ";
		q+="on lineas.linea=articulo.descripabrev ";
		q+="left outer join b_pdp_items items ";
		q+="on items.idpedido=lineas.idpedido and items.idarticulo=articulo.idarticulo and items.usar=1 ";
		q+="where lineas.idpedido like '"+idpedido+"' and lineas.seleccionada=1	and items.cantidad>0 ";
		q+="order by items.idarticulo ";
		System.out.println(">"+q);
		Object[][] results=getResults(q);
		return results;
	}
	
	public String getRemoveRemitoCompraStock(String tc,String idcomprobante){
		String q="delete from v_mv_stock where tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"'";
		return q;
	}
	
	public boolean existe_imagen(){
		boolean ok=false;
		return ok;
	}
	public String getRemitoAplicacion(String cuenta,String tc, 
			String sucursal,String numero,String letra,
			String _otc, String _osucursal,String _onumero,String _oletra,
			String _oimporte,
			String idcomprobante,
			String _oidcomprobante){
		String q="";
		q+="insert into mv_aplicacion ";
		q+="(cuenta,tc,sucursal,numero,letra,tco_origen,sucursal_origen,numero_origen,letra_origen,importe,idcomprobante,idcomprobante_origen)";
		q+="values ";
		q+="('"+cuenta+"','"+tc+"','"+sucursal+"','"+numero+"','"+letra+"','";
		q+=_otc+"','"+_osucursal+"','"+_onumero+"','"+_oletra+"',"+_oimporte+",";
		q+="'"+idcomprobante+"','"+_oidcomprobante+"')";
		return q;
	}
	
	public boolean tieneMovimientos(String idarticulo){
		boolean mov=false;
		String q="";
		q+="select top 1 idarticulo ";
		q+="from v_mv_stock ";
		q+="where idarticulo like '"+idarticulo+"' ";
		q+="";
		mov=this.hasResults(q);
		return mov;
	}
	
	public int ultimaVenta(String idarticulo){
		int id=-1;
		String q="";
		q+="select top 1 id ";
		q+="from v_mv_stock ";
		q+="where idarticulo like '"+idarticulo+"' ";
		q+=" and ( tc like 'fc' or tc like 'fvn' or tc like 'fp' or tc like 'fp' or tc like 'tk' or tc like 'tkfc' ) ";
		q+=" order by id desc ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				String idmovimiento=(String) results[0][0];
				try {
					id=new Integer(idmovimiento);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return id;
	}
	
	public int ultimaCompra(String idarticulo){
		int id=-1;
		String q="";
		q+="select top 1 id ";
		q+="from v_mv_stock ";
		q+="where idarticulo like '"+idarticulo+"' ";
		q+=" and ( tc like 'fcc' or tc like 'fcn' or tc like 'fpc' ) ";
		q+=" order by id desc ";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				String idmovimiento=(String) results[0][0];
				try {
					id=new Integer(idmovimiento);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return id;
	}
	
	public double getPedido(String idarticulo){
		double pedido=0;
		String q="";
		q+="select  ";
		q+="isnull(sum(di.cantidad),0) ";
		q+="from v_ma_articulos a ";
		q+="left outer join b_pdp_items di ";
		q+="on a.idarticulo=di.idarticulo ";
		q+="left outer join b_pdp d ";
		q+="on di.idpedido=d.idpedido ";
		q+="where a.idarticulo like '"+idarticulo+"' ";
		q+="and datediff(day,d.fecha,getdate())<=31 ";
		System.out.println(q);
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				String cantidad=(String) results[0][0];
				try {
					pedido=new Double(cantidad);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return pedido;
	}
	
	public double getPedidoEspecial(String idarticulo){
		double pedido=0;
		String q="";
		q+="select  ";
		q+="isnull(sum(di.cantidad),0) ";
		q+="from v_ma_articulos a ";
		q+="left outer join b_pep_item di ";
		q+="on a.idarticulo=di.idarticulo ";
		q+="left outer join b_pep d ";
		q+="on di.idpedido=d.idpedido ";
		q+="where a.idarticulo like '"+idarticulo+"' ";
		q+="and datediff(day,d.fecha_creacion,getdate())<=31 ";
		System.out.println(q);
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				String cantidad=(String) results[0][0];
				try {
					pedido=new Double(cantidad);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return pedido;
	}
	
	
	public Object[][] getComposicionPedido(String idarticulo){
		String q="";
		q+="select convert (varchar(10),f.fecha,105),f.tc,f.idcomprobante ,v.nombre,isnull(i.cantidad,f.cantidad) ";
		q+="from b_articulos_faltantes f ";
		q+="left outer join b_pdc_item i ";
		q+="on f.idarticulo=i.idarticulo and f.idcomprobante=i.idpedido ";
		q+="left outer join v_ta_vendedores v ";
		q+="on f.idusuario=v.idvendedor ";
		q+="where f.idarticulo like '"+idarticulo+"' ";
		q+=" and datediff(day,f.fecha,getdate())<=31  ";
		q+="order by f.id desc ";
		System.out.println(q);
		Object[][] results=this.getResults(q);
		return results;
	}
}
