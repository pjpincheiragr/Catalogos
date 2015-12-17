package aplicacion.compras.pedidoe.logic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import stock1.Convertidor;

import aplicacion.modelo.logic.Data;

public class _Data extends Data {
	private String TC = "PEP";

	private int getProximoTC(String tc) {
		int c = 0;
		String q = "";
		q += "select x_ultimo_nro from b_ta_cpte ";
		q += "where codigo = '" + tc + "' ";
		Object[][] aux = connection_handler.getDefaultConnector().getResults(q);
		try {
			c = new Integer(aux[0][0].toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return c;
	}

	private String getLineQuery(String linea) {
		String q = "";
		q += "select top 1 descripabrev ";
		q += "FROM v_ma_articulos ";
		q += "where  descripabrev like '" + linea + "'";
		return q;
	}

	public String getVendedorValidacion(String password) {
		String q = "select idvendedor from b_users where pass like '"
				+ password + "' ";
		String idvendedor = "";
		Object[][] results = this.getResults(q);
		if (results != null) {
			if (results.length > 0) {
				idvendedor = (String) results[0][0];
			}
		}
		return idvendedor;
	}

	public boolean update_seguimiento(String idpedido, boolean seguimiento,
			String estado) {
		String q = "";
		String seg = "0";
		if (seguimiento)
			seg = "1";
		q += "update b_pep set seguimiento=" + seg + ",estado='" + estado
				+ "' where idpedido like '" + idpedido + "' ";
		this.clearBatch();
		System.out.println(q);
		this.addBatch(q);
		return !this.executeBatch();
	}

	public boolean check_linea(String linea) {
		boolean exist = false;
		Object[][] results = getResults(this.getLineQuery(linea));
		if (results != null) {
			if (results.length > 0) {
				exist = true;
			}
		}
		return exist;
	}

	public double getPedidoCantidad(String idarticulo, String idpedido) {
		double cant = 0;
		String q = "";
		q += "SELECT I.Cantidad ";
		q += "FROM B_PEP P LEFT OUTER JOIN B_PEP_ITEM I ON P.IDPEDIDO=I.IDPEDIDO  WHERE I.IDARTICULO LIKE '"
				+ idarticulo + "' ";
		q += "AND P.ELIMINAR =0  and P.estado like 'Enviado' and P.idpedido not like '"
				+ idpedido + "' ";
		q += "union ";
		q += "SELECT I.Cantidad ";
		q += "FROM B_PDP P LEFT OUTER JOIN B_PDP_ITEMs I ON P.IDPEDIDO=I.IDPEDIDO  WHERE I.IDARTICULO LIKE '"
				+ idarticulo + "' ";
		q += "AND P.ELIMINAR =0  and P.estado like 'Enviado' ";
		Object[][] results = this.getResults(q);

		if (results != null) {
			if (results.length > 0) {
				double qi = 0;
				for (int i = 0; i < results.length; i++) {
					String qty = (String) results[i][0];
					try {
						qi = new Double(qty);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					cant += qi;
				}

			}
		}
		return cant;
	}

	public Object[][] getData(String idarticulo) {
		String q = "";
		q += "select m.idarticulo,m.descripcion,m.descripabrev,isnull(sum(s.cantidadud),0),isnull(m.suspendidov,0),convert(varchar(10),m.actualizacion,105),m.cuentaproveedor,c.descripcion,m.precio2 ";
		q += "from v_ma_articulos m ";
		q += "left outer join v_mv_stock s on m.idarticulo=s.idarticulo and s.anulado =0 ";
		q += "left outer join ma_cuentas c on m.cuentaproveedor=c.codigo ";
		q += "where m.idarticulo like '" + idarticulo + "' ";
		q += "group by m.idarticulo,m.descripcion,m.descripabrev,isnull(m.suspendidov,0),convert(varchar(10),m.actualizacion,105),m.cuentaproveedor,c.descripcion,m.precio2 ";
		System.out.println(">" + q);
		Object[][] results = getResults(q);
		return results;
	}

	public String getUpdateTc(String tc) {
		String q = "";
		q += "update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 ";
		q += "where codigo = '" + tc + "' ";
		return q;
	}

	public int updateTC(String tc) {
		int c = 0;
		String q = "";
		q += "update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 ";

		Object[][] aux = connection_handler.getDefaultConnector().getResults(q);
		try {
			c = new Integer(aux[0][0].toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return c;
	}

	public String getUpdateRM() {
		String q = "";
		q += "update v_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 ";

		return q;
	}

	public String getQueryRemitoGenerado(String idpedido) {
		String q = "";
		q += "select remito ";
		q += "from b_pep_rms ";
		q += "where idpedido like '" + idpedido + "'  ";
		q += "and eliminado=0 ";
		return q;
	}

	public String getRemitoGenerado(String idpedido) {
		Object[][] results = this.getResults(this
				.getQueryRemitoGenerado(idpedido));
		String remito = "";
		if (results != null) {
			if (results.length > 0) {
				remito = (String) results[0][0];
			}
		}
		return remito;
	}

	public Object[][] getPedidoItems(String idpedido) {
		String q = "";
		q += "";
		q += "select idarticulo,rtrim(descripcion),cantidad,costo,cotiza,isnull(seleccionado,0),isnull(idpedido_pdc,'') ";
		q += "from b_pep_item ";
		q += "where idpedido like '" + idpedido + "' ";
		q += "order by item ";
		System.out.println(q);
		Object[][] results = getResults(q);
		return results;
	}

	public Object[][] getPDCItems(String idpedido) {
		String q = "";
		q += "";
		q += "select idarticulo,rtrim(descripcion),cantidad ";
		q += "from b_pdc_item ";
		q += "where idpedido like '" + idpedido + "' ";
		q += "order by item ";
		System.out.println(q);
		Object[][] results = getResults(q);
		return results;
	}

	public Object[][] getPedidosPDC(String idpedido) {
		String q = "";
		q += "";
		q += "select c.idpedido,c.descripcion,c.cliente,c.cliente_descripcion,v.nombre,c.fecha_creacion ";
		q += "from b_pep_pdc p left outer join b_pdc c on p.idpedido_pdc=c.idpedido ";
		q += "left outer join v_ta_vendedores v on c.idvendedor=v.idvendedor ";
		q += "where p.idpedido like '" + idpedido + "' ";
		q += "order by c.idpedido ";
		System.out.println(q);
		Object[][] results = getResults(q);
		return results;
	}

	public boolean existePDC(String numero) {
		boolean existe = false;
		String q = "select * from b_pdc where idpedido like '" + numero + "' ";
		Object[][] results = this.getResults(q);
		if (results != null) {
			if (results.length > 0) {
				existe = true;
			}
		}
		return existe;
	}

	public String getInstruccionAsociacionRemitoPedido(String idpedido,
			String iduser) {
		String q = "insert into b_pep_rms (idpedido,remito,creacion,idusuario) values ";
		q += "(";
		q += "'" + idpedido + "',";
		q += "@idremito,";
		q += "getdate(),";
		q += "'" + iduser + "'";
		q += ")";
		return q;
	}

	public Object[][] getPedido(String idpedido) {
		String q = "";
		q += "select pedido.descripcion,";
		q += "pedido.fecha_creacion,";
		q += "pedido.ultima_modificacion,";
		q += "(case when isnull(pedido.cliente,'*') like '*' then '112010001' else pedido.cliente end),";
		q += "pedido.cliente_descripcion,";
		q += "pedido.datos_extra,";
		q += "pedido.idvendedor,";
		q += "vendedores.nombre, ";
		q += "pedido.idtransporte,";
		q += "isnull(transporte.nombre,''),";
		q += "convert(varchar(10),pedido.fecha_envio,105),";
		q += "pedido.guia, ";
		q += "convert(varchar(10),pedido.fecha_agenda,105),";
		q += "pedido.seguimiento, ";
		q += "isnull(pedido.domicilio,''),";
		q += "isnull(pedido.cliente_localidad,''),";
		q += "isnull(pedido.cliente_idprovincia,''),";
		q += "isnull(pedido.cliente_cpostal,''),";
		q += "isnull(pedido.cliente_telefono,''),";
		q += "isnull(pedido.estado,''),";
		q += "isnull(pedido.observaciones,''),";
		q += "isnull(pedido.idcreador,''),";
		q += "isnull(pedido.email,'')";
		q += "from b_pep pedido left outer join v_ta_vendedores vendedores ";
		q += "on ltrim(pedido.idvendedor)=ltrim(vendedores.idvendedor) ";
		q += " left outer join transportes transporte ";
		q += "on ltrim(pedido.idtransporte)=ltrim(transporte.idtransporte) ";
		q += "where pedido.idpedido like '" + idpedido + "' ";
		System.out.println(">" + q);
		Object[][] results = getResults(q);
		return results;
	}

	public String getDeletePEP_PDC(String idpedido) {
		String q = "";
		q += "delete from b_PEP_pdc ";
		q += " where idpedido  like '" + idpedido + "' ";
		q += "";
		return q;
	}

	public String getInsertPEP_PDC(String idpedido, String idpedido_pdc) {
		String q = "";
		q += "insert into b_PEP_pdc (idpedido,idpedido_pdc) values (";
		q += "'" + idpedido + "','" + idpedido_pdc + "'";
		q += ")";
		return q;
	}

	public Object[][] getPedidoPDC(String idpedido) {
		String q = "";
		q += "select pedido.descripcion,pedido.fecha_creacion,";
		q += "pedido.ultima_modificacion,(case when isnull(pedido.cliente,'*') like '*' then '112010001' else pedido.cliente end),pedido.cliente_descripcion,";
		q += "pedido.datos_extra,pedido.idvendedor,vendedores.nombre, ";
		q += "pedido.idtransporte,isnull(transporte.nombre,''),";
		q += "convert(varchar(10),pedido.fecha_envio,105),";
		q += "pedido.guia, ";
		q += "convert(varchar(10),pedido.fecha_agenda,105),";
		q += "pedido.seguimiento, ";
		q += "isnull(pedido.domicilio,''),";
		q += "isnull(pedido.cliente_localidad,''),";
		q += "isnull(pedido.cliente_idprovincia,''),";
		q += "isnull(pedido.cliente_cpostal,''),";
		q += "isnull(pedido.cliente_telefono,'')";
		q += "from b_pdc pedido left outer join v_ta_vendedores vendedores ";
		q += "on ltrim(pedido.idvendedor)=ltrim(vendedores.idvendedor) ";
		q += " left outer join transportes transporte ";
		q += "on ltrim(pedido.idtransporte)=ltrim(transporte.idtransporte) ";
		q += "where pedido.idpedido like '" + idpedido + "' ";
		System.out.println(">" + q);
		Object[][] results = getResults(q);
		return results;
	}

	public Object[][] getStock(String idarticulo) {
		String q = "";
		q += "select a.idarticulo,a.descripcion,sum(isnull(s.cantidadud,0)) ";
		q += "from v_ma_articulos a ";
		q += "left outer join v_mv_stock s ";
		q += "on a.idarticulo=s.idarticulo ";
		q += "where a.idarticulo like '" + idarticulo + "' ";
		q += "group by a.idarticulo,a.descripcion ";
		System.out.println(">" + q);
		Object[][] results = getResults(q);
		return results;
	}

	public boolean existeArticulo(String idarticulo) {
		boolean existe = false;
		Object[][] results = this.getArticulo(idarticulo);
		if (results != null) {
			if (results.length > 0) {
				existe = true;
			}
		}
		return existe;
	}

	public Object[][] getArticulo(String idarticulo) {
		String q = "";
		q += "select m.descripcion,m.precio2,m.costo,m.descripabrev,m.suspendidov,m.suspendidoc,isnull(s.cantidadud,0) ";
		q += "from v_ma_articulos m left outer join Stk_Stock_UDKG s on m.idarticulo=s.idarticulo ";
		q += "where m.idarticulo like '" + idarticulo + "' ";
		System.out.println(">" + q);
		Object[][] results = getResults(q);
		return results;
	}

	public Object[][] getVendedor(String iduser) {
		String q = "";
		q += "select b.idvendedor,v.nombre ";
		q += "from b_users b left outer join v_ta_vendedores v on ltrim(b.idvendedor)=ltrim(v.idvendedor) ";
		q += "where b.iduser like '" + iduser + "'";
		return this.getResults(q);
	}

	public Object[][] getVendedorPorNombre(String nombre) {
		String q = "select idvendedor from v_ta_vendedores ";
		q += "where nombre like '" + nombre + "' ";
		return this.getResults(q);
	}

	public boolean eliminado(String numero) {
		boolean existe = false;
		String q = "select * from b_pep where idpedido like '" + numero
				+ "' and eliminar=1 ";
		Object[][] results = this.getResults(q);
		if (results != null) {
			if (results.length > 0) {
				existe = true;
			}
		}
		return existe;
	}

	public boolean existe(String numero) {
		boolean existe = false;
		String q = "select * from b_pep where idpedido like '" + numero + "' ";
		Object[][] results = this.getResults(q);
		if (results != null) {
			if (results.length > 0) {
				existe = true;
			}
		}
		return existe;
	}

	public boolean esResponsableInscripto(String idcliente) {
		boolean existe = false;
		String q = "select * from ma_cuentasadic where codigo like '"
				+ idcliente + "' and ltrim(iva) like '1' ";
		Object[][] results = this.getResults(q);
		if (results != null) {
			if (results.length > 0) {
				existe = true;
			}
		}
		return existe;
	}

	public boolean utilizaPrecioPublico(String idcliente) {
		boolean existe = false;
		String q = "select isnull(clase,2) from ma_cuentasadic where codigo like '"
				+ idcliente + "' and isnull(clase,2)=2 ";
		Object[][] results = this.getResults(q);
		if (results != null) {
			if (results.length > 0) {
				existe = true;
			}
		}
		return existe;
	}

	public Object[][] getClientInformation(String idcliente) {

		String q = "";
		q = q + "select M.codigo,M.descripcion ";
		q = q + "FROM MA_CUENTAS M ";
		q = q + " where M.codigo like '" + idcliente + "' and M.titulo = 0";
		Object[][] results = connection_handler.getDefaultConnector()
				.getResults(q);
		return results;
	}

	public String getProximoPGCorrecto(String tc) {
		String prox = "";
		prox = this.getProximoPG_Ceros(tc);
		return prox;
	}

	private int getProximoPG(String tc) {
		int c = 0;
		c = this.getProximoTC("" + tc + "");
		return c;
	}

	private String getProximoPG_Ceros(String tc) {
		String c = "";
		int i = this.getProximoPG(tc);
		String aux = "" + i;
		while (aux.length() < 8) {
			aux = "0" + aux;
		}
		c = aux;
		return c;
	}

	private String getClientQuery(String idcliente) {
		String q = "";
		q = q + " select M.codigo,M.descripcion,";
		q = q + " isnull(a.telefono,''),";
		q = q + " isnull(a.fax,''),";
		q = q + " isnull(a.mail,''),";
		q = q + " isnull(a.contacto,''),";
		q = q + " isnull(a.localidad,''),";
		q = q + " isnull(a.observaciones,''),";
		q = q + " a.iva, ";
		q = q + " a.numero_documento, ";
		q = q + " a.idcond_cpra_vta, ";
		q = q + " t.descripcion, ";
		q = q + " isnull(a.calle,''),";
		q += "  isnull(ltrim(a.numero),'')";
		q += "  ,isnull(a.piso,''), ltrim(a.cpostal), ";
		q = q + " a.provincia,";
		q += "  st.descripcion,tip.descripcion,a.documento_tipo,isnull(td.descripcion,''), ";
		q += "  isnull(a.idtransporte,''),isnull(tt.nombre,'')";
		q = q + " FROM MA_CUENTAS M LEFT OUTER JOIN ";
		q = q + " MA_CUENTASADIC  a  ";
		q = q + " ON M.CODIGO = a.CODIGO left outer join v_ta_cpra_vta t ";
		q = q + " on a.idcond_cpra_vta = t.idcond_cpra_vta ";
		q = q + " left outer join ta_condiva tip ";
		q = q + " on a.iva=tip.codigo ";
		q = q + " left outer join ta_estados st ";
		q = q + " on a.provincia=st.codigo ";
		q = q
				+ " left outer join ta_tipodocumento td on a.documento_tipo=td.codigo ";
		q = q
				+ " left outer join transportes tt on a.idtransporte=tt.idtransporte ";
		q = q + " where M.codigo like '" + idcliente + "'";
		return q;
	}

	public Object[][] getCliente(String idcliente) {
		String q = this.getClientQuery(idcliente);
		System.out.println(q);
		Object[][] results = this.getResults(q);
		return results;
	}

	public Object[][] getRemitos(String idpedido) {
		String q = "";
		q += "select  remitos.creacion,remitos.remito,isnull(remitos.idusuario,''),(case when count(remitos.remito)=count(alfa.tc) and count(remitos.remito)>0 then 'Alfa' else ";
		q += "( (case when count(remitos.remito)=count(fvn.tc) and count(remitos.remito)>0  and fvn.anulada=0 then 'Beta' else  ";
		q += "( (case when count(remitos.remito)>0 and isnull(rmx.anulada,0)=0 then 'RM' else '' end) )  end )) end) as estado ";
		q += "from b_pep pedido  ";
		q += "left outer join b_pep_rms remitos on pedido.idpedido=remitos.idpedido and remitos.eliminado=0 ";
		q += "left outer join b_fvn fvn on remitos.remito=fvn.idcomprobante_origen  ";
		q += "left outer join v_mv_cpte alfa on remitos.remito=alfa.comprobanteorigen  ";
		q += "left outer join v_mv_cpte rmx on remitos.remito=rmx.idcomprobante and rmx.tc like 'RM' ";
		q += "where pedido.idpedido like '" + idpedido + "' ";
		q += "group by pedido.idpedido,remitos.creacion,remitos.remito,remitos.idusuario,rmx.anulada,fvn.anulada ";
		System.out.println("Remitos>" + q);
		return this.getResults(q);
	}

	public int getRemitosValidos(String idpedido) {
		String q = "";
		q += "select count(*) ";
		q += "from b_pep_rms ";
		q += "where idpedido like '" + idpedido + "' and eliminado=0 ";
		q += "group by idpedido ";
		int validos = 0;
		Object[][] results = this.getResults(q);
		if (results != null) {
			if (results.length > 0) {
				String _validos = (String) results[0][0];
				try {
					validos = new Integer(_validos);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return validos;
	}

	public String getEliminar(String idpedido) {
		String q = "";
		q += "update b_pep set eliminar=1 where idpedido like '" + idpedido
				+ "'";
		return q;
	}

	public String getUpdateQuery(String idpedido, String descripcion,
			String cliente, String cliente_descripcion, String vendedor,
			String informacion, String idtransporte, String fecha_envio,
			String guia, String fecha_agenda, String total, String seguimiento,
			String domicilio, String ciudad, String idprovincia,
			String cpostal, String telefono, String estado,
			String observaciones, String email) {
		String q = "update b_pep set ";
		q = q + " Descripcion='" + descripcion + "',";
		q = q + " Enviar = '',";
		q = q + " Cliente='" + cliente + "',";
		q = q + " Cliente_Descripcion='" + cliente_descripcion + "',";
		q = q + " datos_extra='" + informacion + "',";
		q = q + " Plazo='',";
		q = q + " estado='" + estado + "',";
		q = q + " cond_iva='',";
		q = q + " cuit='',";
		q = q + " clase='',";
		q = q + " tipo_doc='',";
		q = q + " vence=null,";
		q = q + " idvendedor='" + vendedor + "', ";
		q = q + " ordinario=0, ";
		q = q + " ultima_modificacion=getdate(), ";
		q = q + " idtransporte='" + idtransporte + "',";
		q = q + " fecha_envio='" + fecha_envio + "',";
		q = q + " guia='" + guia + "', ";
		q = q + " fecha_agenda='" + fecha_agenda + "',";
		q = q + " total=" + total + ", ";
		q = q + " seguimiento=" + seguimiento + ", ";
		q += " domicilio='" + domicilio + "',";
		q += " cliente_localidad='" + ciudad + "',";
		q += " cliente_idprovincia='" + idprovincia + "',";
		q += " cliente_cpostal='" + cpostal + "', ";
		q += " cliente_telefono='" + telefono + "', ";
		q += " observaciones='" + observaciones + "', ";
		q += " email='" + email + "' ";
		q = q + " where idpedido = '" + idpedido + "'";

		return q;
	}

	public String getUltimaModificacion(String idpedido) {
		String q = "";
		q += " select ultima_modificacion ";
		q += " from ";
		q += " b_pep ";
		q += " where idpedido like '" + idpedido + "' ";
		Object[][] results = this.getResults(q);
		String modificado = "";
		if (results != null) {
			if (results.length > 0) {
				modificado = (String) results[0][0];
			}
		}
		return modificado;
	}

	public String getDeleteItemQuery(String idpedido, int item) {
		String q = "";
		q += "delete from b_pep_item ";
		q += "where item>" + item + " ";
		q += "and idpedido like '" + idpedido + "' ";
		return q;
	}

	public String getGuardarItemQuery(String idpedido, String item,
			String idarticulo, String descripcion, String cantidad,
			String costo, String cotiza, String total, String seleccionado,
			String idpedido_pdc) {
		String q = "";
		q += "insert into b_pep_item ";
		q += "(idpedido,item,idarticulo,descripcion,cantidad,costo,cotiza,total,";
		q += "observaciones,ave,ped,listo,seleccionado,idpedido_pdc) ";
		q += "values (";
		q += "'" + idpedido + "',";
		q += "" + item + ",";
		q += "'" + idarticulo + "',";
		q += "'" + descripcion + "',";
		q += "" + cantidad + ",";
		q += "" + costo + ",";
		q += "" + cotiza + ",";
		q += "" + total + ",";
		q += "'',";// obs
		q += "'',";// ave
		q += "'',";// ped
		q += "'',";// listo
		q += seleccionado + ",";
		q += "'" + idpedido_pdc + "'";
		q += ")";
		return q;
	}

	public String getUpdateItems(String idpedido, String item, String art,
			String desc, String qty, String cost, String price, String total,
			String seleccionado, String idpedido_pdc) {
		String q = "";
		Convertidor Cv = new Convertidor();
		q += " update b_pep_item ";
		desc = desc.replaceAll("'", "");
		q += " set idarticulo='" + art + "',descripcion='" + desc
				+ "',cantidad=" + qty + ",cotiza=" + price + ",costo=" + cost
				+ ",total=" + total + ",seleccionado=" + seleccionado
				+ ",idpedido_pdc='" + idpedido_pdc + "' ";
		q += " where idpedido = '" + idpedido + "' and item = '" + item + "'";
		return q;
	}

	public String getGuardarQuery(String idpedido, String descripcion,
			String cliente, String cliente_descripcion, String idvendedor,
			String info, String idtransporte, String fecha_envio, String guia,
			String fecha_agenda, String total, String seguimiento,
			String domicilio, String ciudad, String idprovincia,
			String cpostal, String telefono, String estado,
			String observaciones, String email) {
		String q = "insert into b_pep ";
		q += "(idPedido,Descripcion,";
		q += "Fecha_Creacion,Cliente,";
		q += "Cliente_Descripcion,";
		q += "Plazo,estado,cond_iva,idvendedor,cuit,";
		q += "vence,ordinario,datos_extra,enviar,";
		q += "tipo_doc,clase,ultima_modificacion,idtransporte,fecha_envio,guia,fecha_agenda,";
		q += "total,seguimiento,";
		q += "domicilio,cliente_localidad,cliente_idprovincia,cliente_cpostal,cliente_telefono,observaciones,";
		q += "idcreador,email)";
		q = q + " values (";
		q += "'" + idpedido + "',";
		q += "'" + descripcion + "',";
		q += "getdate(),";
		q += "'" + cliente + "',";
		q += "'" + cliente_descripcion + "',";
		q += "'',";// plazo
		q += "'" + estado + "',";// estado
		q += "'',";// iva
		q += "'" + idvendedor + "',";
		q += "'',";// cuit
		q += "'',";// vence
		q += "'',";// ordinario
		q += "'" + info + "',";
		q += "'',";// enviar
		q += "'',";// tipo_doc
		q += "'',";// clase
		q += "getdate(),";
		q += "'" + idtransporte + "',";
		q += "'" + fecha_envio + "',";
		q += "'" + guia + "',";
		q += "'" + fecha_agenda + "',";
		q += "" + total + ",";
		q += "" + seguimiento + ",";
		q += "'" + domicilio + "',";
		q += "'" + ciudad + "',";
		q += "'" + idprovincia + "',";
		q += "'" + cpostal + "',";
		q += "'" + telefono + "',";
		q += "'" + observaciones + "',";
		q += "'" + idvendedor + "',";
		q += "'" + email + "'";
		q = q + ")";

		return q;
	}

	public boolean existItemPedido(String idpedido, int item) {
		boolean existe = false;
		String q = "select item from b_pep_item ";
		q += "where idpedido like '" + idpedido + "' and item =" + item + "";
		Object[][] results = this.getResults(q);
		if (results != null) {
			if (results.length > 0) {
				existe = true;
			}
		}
		return existe;
	}

	public String getRemitoVariables() {
		String q = "";
		q += "declare @sucursal varchar(4) ";
		q += "declare @numero varchar(8) ";
		q += "declare @letra varchar(1) ";
		q += "declare @idremito varchar(13) ";
		q += "set @sucursal='0000' ";
		q += "set @numero='0000000' ";
		q += "select @sucursal=substring(@sucursal,1,4-len(x_suc_default))+convert(varchar(1),x_suc_default), ";
		q += "@numero=substring(@numero,1,8-len(x_ultimo_nro))+convert(varchar,x_ultimo_nro), ";
		q += "@letra='X' ";
		q += "from v_ta_cpte ";
		q += "where codigo like 'RM' ";
		q += "set @idremito=@sucursal+@numero+@letra ";
		// q+="select @numero,@sucursal,@letra,@idremito ";
		return q;
	}

	public String getInsertRemito(String idcliente, String fecha,
			String idvendedor, String importe, String importe_s_iva,
			String iva, String condicion) {

		String iddeposito = "   1";
		String aliciva = "21";
		String q = "";
		q += this.getRemitoVariables();
		q += "declare @codigo varchar(9) ";
		q += "declare @nombre varchar(50) ";
		q += "declare @domicilio varchar(100) ";
		q += "declare @telefono varchar(100) ";
		q += "declare @localidad varchar(50) ";
		q += "declare @provincia varchar(4) ";
		q += "declare @cpostal varchar(10) ";
		q += "declare @tipo_documento varchar(4) ";
		q += "declare @numero_documento varchar(13) ";
		q += "declare @iva varchar(4) ";
		q += "declare @condicion varchar(4) ";
		q += "declare @clase varchar(4) ";
		q += "select @codigo=m.codigo, ";
		q += "@nombre=m.descripcion, ";
		q += "@domicilio=RTRIM(LTRIM(CASE WHEN ISNULL(calle,'.') like '' then '.' else ISNULL(calle,'.') end))+' '+RTRIM(LTRIM(isnull(numero,''))), ";
		// 
		q += "@telefono=ma.Telefono, ";
		q += "@localidad=ma.Localidad, ";
		q += "@provincia=ma.provincia, ";
		q += "@cpostal=ma.cpostal, ";
		q += "@tipo_documento=ma.documento_tipo, ";
		q += "@numero_documento=RTRIM(LTRIM(ma.numero_documento)), ";
		q += "@iva=ma.iva, ";
		q += "@condicion=ma.idcond_cpra_vta, ";
		q += "@clase=ma.clase ";
		q += "from ma_cuentas m left outer join ma_cuentasadic ma ";
		q += "on m.codigo=ma.codigo ";
		q += "where m.codigo like '" + idcliente + "' ";

		q += "";
		q += "insert into v_mv_cpte ( ";
		q += "tc,idcomprobante,cuenta,nombre,Domicilio,fecha,fechaestinicio,fechaestfin, ";
		q += "Telefono,Localidad,idprovincia,codigopostal,documentotipo,documentonumero, ";
		q += "condicioniva,idcond_cpra_vta,claseprecio,observaciones, ";
		q += "Importe,Importe_S_iva, ";
		q += "finalizada,anulada,aprobado, ";
		q += "idmotivocpravta,porcDescuento1,idvendedor, ";
		q += "ImporteInsumos,ImporteServicios,ImporteOtrosConceptos,ImporteIva,ImporteIvaRec, ";
		q += "netoGravado,netonoGravado, ";
		q += "impreso,iddeposito,aliciva, ";
		q += "moneda,Cotizacion,sucursal,numero,letra, ";
		q += "unegocio,usuario,unegocio_destino)  ";
		q += "values ('RM',@idremito,@codigo,@nombre,@domicilio,getdate(),getdate(),getdate(),";
		q += "@telefono,@localidad,@provincia,@cpostal,@tipo_documento,@numero_documento,@iva,@condicion,@clase, ";
		q += "''," + importe + "," + importe_s_iva + ",0,0,0, ";
		q += "'   1', ";
		q += "0, ";
		q += "'" + idvendedor + "', ";
		q += "" + importe + ",0,0,";
		q += "" + iva + ",0," + importe_s_iva + ",0,0,'" + iddeposito + "',"
				+ aliciva
				+ ",'   1',1,@sucursal,@numero,@letra,'   1','VENTAS1','   1' ";
		q += ")";
		return q;
	}

	public String getInsertRemitoItem(String idarticulo, String descripcion,
			double cantidad, double costo, double importe,
			double importe_s_iva, double total, double totalf) {
		String idunidad = "   1";
		String aliciva = "21.0";
		String idcaja = "   1";
		double equiv_udbase = 1.0;
		double porcdto = 0.0;
		double importedto = 0.0;
		int exento = 0;
		int transmision = 1;
		int recepcion = 1;
		String tipolista = "V";
		String iddeposito = "   1";
		String unegocio = "   1";
		String q = "";
		q = q + "insert into v_mv_cpteinsumos (";
		q = q + "tc,idcomprobante,idarticulo,";
		q = q + "descripcion,idunidad,cantidadud,cantidad,";
		q = q + "costo,importe,importe_s_iva,";
		q = q + "total,exento,claseprecio,";
		q = q + "tipolista,porcdto,importedto,";
		q = q + "aliciva,totalfinal,idunidadbase,idcaja,equiv_udbase,";
		q = q + "transmision,recepcion,iddeposito) ";
		q = q + "values (";
		q = q + "'RM',";
		q = q + "@idremito,";
		q = q + "'" + idarticulo + "',"; // idarticulo
		q = q + "'" + descripcion + "',"; // descripcion
		q = q + "'" + idunidad + "',"; // idunidad
		q = q + "" + cantidad + ","; // cantidad
		q = q + "" + cantidad + ","; // cantidadud=cantidad
		q = q + "" + costo + ","; // costo
		q = q + "" + importe + ","; // importe
		q = q + "" + importe_s_iva + ","; // importe_s_iva
		q = q + "" + total + ","; // total
		q = q + "" + exento + ",";// exento
		q = q + "@clase,"; // claseprecio
		q = q + "'" + tipolista + "',"; // tipolista
		q = q + "" + porcdto + ","; // porcdto
		q = q + "" + importedto + ","; // importedto
		q = q + "" + aliciva + ","; // aliciva
		q = q + "" + totalf + ","; // totalfinal!=total
		q = q + "'" + idunidad + "',";
		q = q + "'" + idcaja + "',";
		q = q + "" + equiv_udbase + ",";
		q = q + "" + transmision + ","; // transmision
		q = q + "" + recepcion + ","; // recepcion
		q = q + "'" + iddeposito + "'"; // iddeposito
		q = q + ")";

		return q;
	}

	public String getComprobantesAlfaConRemito(String idremito) {
		String q = "";
		q += "select tc,idcomprobante from v_mv_cpte ";
		q += "where  ";
		q += "tcorigen like 'RM' and comprobanteorigen like '" + idremito
				+ "' ";
		return q;
	}

	public String getComprobantesBetaConRemito(String idremito) {
		String q = "";
		q += "select tc,idcomprobante from b_fvn ";
		q += "where tc_origen like 'RM' ";
		q += "and idcomprobante_origen like '" + idremito + "' ";
		q += "and anulada=0 ";
		return q;
	}

	public Object[][] tieneComprobanteBetaAsociado(String idremito) {
		String q = this.getComprobantesBetaConRemito(idremito);

		Object[][] results = this.getResults(q);

		return results;
	}

	public String getMarcarAnuladoRemito(String remito) {
		String q = "";
		q = q
				+ "update v_mv_cpte set anulada=1,finalizada=1 where tc like 'rm' and idcomprobante like '"
				+ remito + "'";
		q = q + "";
		q = q + "";
		return q;
	}

	public String getDeleteRemitoPedidoQuery(String rmx) {
		String q = "update b_pep_rms set eliminado=1";
		q = q + " where remito = '" + rmx + "' ";
		return q;
	}

	public Object[][] tieneComprobanteAlfaAsociado(String idremito) {
		String q = this.getComprobantesAlfaConRemito(idremito);

		Object[][] results = this.getResults(q);

		return results;
	}

	public String getNombreEmpresa() {
		String nombre = "";
		String q = "select valor ";
		q += "from ta_configuracion ";
		q += "where grupo like 'datos' ";
		q += "and clave like 'Nombre' ";
		Object[][] results = this.getResults(q);
		if (results != null) {
			if (results.length > 0) {
				nombre = (String) results[0][0];
			}
		}
		return nombre;
	}

	public String getEmail() {
		String nombre = "";
		String q = "select valor ";
		q += "from ta_configuracion ";
		q += "where grupo like 'datos' ";
		q += "and clave like 'web_email' ";
		Object[][] results = this.getResults(q);
		if (results != null) {
			if (results.length > 0) {
				nombre = (String) results[0][0];
			}
		}
		return nombre;
	}

	public String getTelefonoEmpresa() {
		String telefono = "";
		String q = "select valor ";
		q += "from ta_configuracion ";
		q += "where grupo like 'datos' ";
		q += "and clave like 'telefono' ";
		Object[][] results = this.getResults(q);
		if (results != null) {
			if (results.length > 0) {
				telefono = (String) results[0][0];
			}
		}
		return telefono;
	}

	public String getLineaItems(String idproveedor, String desde, String hasta,
			String linea, int stock_cero, String categoria,int tipo,boolean full) {
		String q = "";
		q += " select m.idarticulo,";
		q += " m.descripcion, ";
		q += " CAST(m.costo AS decimal(10,2)),";
		if (tipo<=0){
			q += " isnull(e.categoria_rotacion,'D'),";
			q += " isnull(e.rotacion,0),";	
		}
		if (tipo==1){
			q += " isnull(e.categoria_volumen,'D'),";
			q += " isnull(e.volumen,0),";	
		}
		if (tipo==2){
			q += " isnull(e.categoria_pickups,'D'),";
			q += " isnull(e.pickups,0),";	
		}
		q += " ISNULL(s.STOCK, 0) AS stk, ";
		q += " sum(case when (v.tc like 'FC' or v.tc like 'TK' or v.tc like 'TKFC' or v.tc like 'FP' or v.tc like 'FVN' ) then -v.cantidadud else 0 end)as Ventas,";
		q += " m.descripabrev ";
		q += " from v_mv_stock v left outer join  v_ma_articulos m ";
		q += " on v.IDARTICULO = m.IdArticulo and isnull(v.anulado,0)=0 ";
		q += " and v.fecha between '" + desde + "' and '" + hasta + "' ";
		q += " left outer join view_stock s ON ";
		q += " m.IDARTICULO = s.IdArticulo ";
		q += " left outer join b_articulos_abc_evolution e ON ";
		q += " m.idarticulo=e.idarticulo ";
		q += " where m.suspendido=0 ";
		if (!full){
			if (idproveedor.compareTo("") != 0) {
				q += " and isnull(m.cuentaproveedor,'') like '" + idproveedor
						+ "' ";
			}	
		}
		

		if (linea.compareTo("") != 0) {
			q += " and m.descripabrev like '" + linea + "' ";
		}
		if (stock_cero >= 0) {
			q += " and ISNULL(s.stock, 0)<= " + stock_cero + " ";
		}
		if (categoria.compareTo("") != 0) {
			if (tipo<=0){
				q += " and e.categoria_rotacion like '" + categoria + "' ";	
			}
			if (tipo==1){
				q += " and e.categoria_volumen like '" + categoria + "' ";	
			}
			if (tipo==2){
				q += " and e.categoria_pickups like '" + categoria + "' ";	
			}
		}
		q += " group by m.idarticulo,m.descripcion,m.costo,s.stock,m.descripabrev,";
		if (tipo<=0){
			q +=" e.categoria_rotacion,e.rotacion ";	
		}
		if (tipo==1){
			q +=" e.categoria_volumen,e.volumen ";	
		}
		if (tipo==2){
			q +=" e.categoria_pickups,e.pickups ";	
		}
		q += " having sum(case when (v.tc like 'FC' or v.tc like 'TK' or v.tc like 'TKFC' or v.tc like 'FP' or v.tc like 'FVN' ) then -v.cantidadud else 0 end) > 0";
		q += " order by m.descripabrev,m.idarticulo ";
		return q;
	}

	/**
	 * Consulta
	 * idarticulo|descripcion|costo|puntopedido|stock|ventas|linea|fecha
	 * |vendedor|tc|idcomprobante|id
	 * 
	 * @param idproveedor
	 * @param desde
	 * @param hasta
	 * @param ventas_desde
	 * @param ventas_hasta
	 * @param mostrar_eliminados
	 * @param linea
	 * @return
	 */
	public String getFaltantes(String idproveedor, String desde, String hasta,
			String ventas_desde, String ventas_hasta,
			boolean mostrar_eliminados, String linea, String categoria, int pdc,int tipo) {
		String q = "";
		q += "select f.idarticulo,isnull(f.descripcion,m.descripcion), ";
		q += " CAST(m.costo AS decimal(10,2)), ";
		if (tipo<=0){
			q += "isnull(e.categoria_rotacion,'D'), ";
			q += "isnull(e.rotacion,0), ";	
		}
		if (tipo==1){
			q += "isnull(e.categoria_volumen,'D'), ";
			q += "isnull(e.volumen,0), ";	
		}
		if (tipo==2){
			q += "isnull(e.categoria_pickups,'D'), ";
			q += "isnull(e.pcikups,0), ";	
		}
		q += "sum(ISNULL(s.cantidadud, 0)) AS stk, ";
		q += "sum(case when (s.fecha between '"
				+ ventas_desde
				+ "' and '"
				+ ventas_hasta
				+ "') and (s.tc like 'FC' or s.tc like 'TK' or s.tc like 'TKFC' or s.tc like 'FP' or s.tc like 'FVN' ) then -s.cantidadud else 0 end)as Ventas, ";
		
		q += "isnull(m.descripabrev,''), ";

		q += "f.fecha, ";
		q += "d.nombre, ";
		q += "isnull(f.tc,''), ";
		q += "isnull(f.idcomprobante,''), ";
		q += "isnull(f.id,'') ";
		q += "from   b_articulos_faltantes f ";
		q += "left outer join v_ta_vendedores d on f.idusuario=d.idvendedor ";
		q += "left outer join   v_ma_articulos m ";
		q += "on f.IDARTICULO = m.IdArticulo  ";
		q += "left outer join b_articulos_abc_evolution e ";
		q += "on m.idarticulo = e.idarticulo ";
		q += "left outer join v_mv_stock s on m.IDARTICULO = s.IdArticulo and isnull(s.anulado,0)=0 ";
		// q+="left outer join v_mv_stock v on m.IDARTICULO = v.IdArticulo and isnull(v.anulado,0)=0 ";

		
		q += "where  ";
		q += "isnull(m.cuentaproveedor,'') like '" + idproveedor + "' ";
		q += "and f.fecha between '" + desde + "' and '" + hasta + "' ";
		if (linea.compareTo("") != 0) {
			q += " and m.descripabrev like '" + linea + "' ";
		}
		q += "and isnull(m.suspendido,0)=0 ";
		if (!mostrar_eliminados) {
			q += " and isnull(f.eliminar,0)=0 ";
		}
		if (categoria.compareTo("") != 0) {
			if (tipo<=0){
				q += " and isnull(e.categoria_rotacion,'D') like '" + categoria
				+ "' ";	
			}
			if (tipo==1){
				q += " and isnull(e.categoria_volumen,'D') like '" + categoria
				+ "' ";	
			}
			if (tipo==2){
				q += " and isnull(e.categoria_pickups,'D') like '" + categoria
				+ "' ";	
			}
		}
		if (pdc == 1) {
			q += " and isnull(f.tc,'') like 'PDC' ";
		} else {
			if (pdc == 2) {
				q += " and isnull(f.tc,'') like '' ";
			}
		}
		q += "group by f.idarticulo,f.descripcion,m.descripcion,m.costo,m.descripabrev,f.fecha,d.nombre ,f.tc,  f.idcomprobante,f.id, ";
		if (tipo<=0){
			q += "isnull(e.categoria_rotacion,'D'), ";
			q += "isnull(e.rotacion,0) ";	
		}
		if (tipo==1){
			q += "isnull(e.categoria_volumen,'D'), ";
			q += "isnull(e.volumen,0) ";	
		}
		if (tipo==2){
			q += "isnull(e.categoria_pickups,'D'), ";
			q += "isnull(e.pcikups,0) ";	
		}
		q += "order by f.fecha desc,f.idarticulo ";
		return q;
	}

	public String getFaltantesViejo(String idproveedor, String desde,
			String hasta, String ventas_desde, String ventas_hasta) {
		String q = "";
		q += "select m.idarticulo,m.descripcion,  CAST(m.costo AS decimal(10,2)),m.puntopedido, ";
		q += "ISNULL(s.cantidadud, 0) AS stk,  ";
		q += "sum(case when (v.tc like 'FC' or v.tc like 'TK' or v.tc like 'TKFC' or v.tc like 'FP' or v.tc like 'FVN' ) ";
		q += "then -v.cantidadud else 0 end)as Ventas,m.descripabrev,f.fecha,d.nombre ";
		q += "from   v_ma_articulos m left outer join  ";
		q += "b_articulos_faltantes f on m.IDARTICULO = f.IdArticulo ";
		q += "left outer join v_mv_stock v on m.IDARTICULO = v.IdArticulo ";
		q += "and isnull(v.anulado,0)=0  and v.fecha between '" + ventas_desde
				+ "' and '" + ventas_hasta + "' ";
		q += "left outer join Stk_Stock_UDKG s ON  m.IDARTICULO = s.IdArticulo   ";
		q += "left outer join v_ta_vendedores d on f.idusuario=d.idvendedor ";
		q += "where  ";
		q += "m.cuentaproveedor like '" + idproveedor + "' and ";
		q += "f.fecha between '" + desde + "' and '" + hasta + "' ";
		q += "and m.suspendido=0   ";
		q += "group by m.idarticulo,m.descripcion,m.costo,s.cantidadud,m.puntopedido,m.descripabrev,f.fecha,d.nombre ";
		q += "order by f.fecha desc,m.idarticulo ";
		return q;
	}

	public String getEliminar_faltante_utilizado(String id) {
		String q = "";
		q += "update b_articulos_faltantes set eliminar=1 where id ='" + id
				+ "' ";
		return q;
	}

	public Object[][] getEquivalencias(String idarticulo) {
		String q = "";
		q += "select m.idarticulo,m.descripcion,m.descripabrev,m.precio2,sum(isnull(s.cantidadud,0)) ";
		q += "from b_articulos_equivalencias e left outer join v_ma_articulos m ";
		q += " on (e.idarticulo1=m.idarticulo or e.idarticulo2=m.idarticulo) ";
		q += " left outer join v_mv_stock s on m.idarticulo=s.idarticulo and s.anulado=0 ";
		q += " where (e.idarticulo1 like '" + idarticulo
				+ "' or e.idarticulo2 like '" + idarticulo + "')";
		q += " and m.idarticulo not like '" + idarticulo + "' ";
		q += " group by m.idarticulo,m.descripcion,m.descripabrev,m.precio2 ";
		return this.getResults(q);
	}

}
