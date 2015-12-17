package aplicacion.cliente.cobranza.logic.extensions;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import aplicacion.cliente.cobranza.logic._Logic;
import stock1.Convertidor;
import aplicacion.cliente.cobranza.logic._Data;
import aplicacion.cliente.cobranza.gui._Frame;
import aplicacion.modelo.logic.Logic_Extension;
import visual.CargaFVN;
public class _Asiento  extends Logic_Extension {
private String caja="   1";
	
public 	List<String> getBloqueDeInstrucciones(boolean credito){
	_Logic logic=(_Logic) this._logic;
	_Data data=(_Data)this._logic.getData();
	_Frame frame=(_Frame)this._logic.getFrame();
	
	
	List<String> batch=new ArrayList<String>();
	
	String fecha=frame.get_txt_fecha().getText();
	String cuenta=frame.get_txt_idcliente().getText();
	String descripcion=frame.get_txt_clientedescripcion().getText();
	String debe_haber="";
	if (credito){
		debe_haber="D";	
	}else{
		debe_haber="H";
	}
	//String tc="FVN";
	
	String cb_tc="CBCT";
	String cb_idcomp=data.getProximoCBCTCorrecto();
	
	String mes=data.getMes_Operativo();
	String asiento=""+(data.getNumeroAsientoProximo());
	String importe=frame.get_txt_total_pago().getText().replace(",","");
	
	String detalle="";
	String caja=this.caja;
	String ant=frame.get_txt_anticipo().getText().replace(",","");
	
	if (logic.getVentas()!=null){
		
		//incremento el numero de asiento porque el primero lo va a usar la venta.
		asiento=""+(data.getNumeroAsientoProximo()+1);
	}
	
	
	
	
	List<String> _instrucciones_asiento=this.getMediosDePago(fecha, debe_haber, mes, asiento, detalle, cb_tc, cb_idcomp, cuenta, descripcion, importe,caja);
	for (int i=0;i<_instrucciones_asiento.size();i++){
		batch.add(_instrucciones_asiento.get(i));
	}
	List<String> _instrucciones_conciliaciones=this.getConciliaciones(cuenta, cb_tc, cb_idcomp);
	for (int i=0;i<_instrucciones_conciliaciones.size();i++){
		batch.add(_instrucciones_conciliaciones.get(i));
	}
	String _instruccion_encabezado_cobranza=this.getInsertIntoCbs(cb_tc, cb_idcomp, cuenta,fecha, importe, ant);
	batch.add(_instruccion_encabezado_cobranza);
	
	String _instruccion_update_numero_cobranza=data.getUpdateCBCT();
	batch.add(_instruccion_update_numero_cobranza);
	return batch;
}

private List<String> getMediosDePago(String fecha,String debe_haber,String mes,String asiento,
			String detalle,String cb_tc,String cb_idcomp,String cuenta,String descripcion,String imp,String caja){
		LinkedList medios_de_pago =this.GrabarMedioss();
		_Data data=(_Data)this._logic.getData();
		_Frame frame=(_Frame)this._logic.getFrame();
		List<String> tmp=new ArrayList<String>();
		
		if (medios_de_pago.size()>0){
			int sec=0;
			
			for (int i=0;i<medios_de_pago.size();i++){
				String[] cht=(String[])medios_de_pago.get(i);
				String cuenta2=cht[0];
				String descripcion2=cht[1];
				String importe2=cht[2];
				double impx=0.0;
				try {
					impx=new Double(importe2.replace(",", ""));
				}catch(Exception e){
					
				}
				
				importe2=new Convertidor().getMoney(impx, 2);
				if (cuenta2.compareTo("111010002")==0){
					importe2=cht[6];
					String cht_idbanco=cht[2];
					String cht_serie=cht[3];
					String cht_numero=cht[4];
					String cht_vencimiento=cht[5];
					//String cht_importe=cht[6]; no es necesario cht_importe. uso importe
					String obs="";
					try {
						impx=new Double(importe2.replace(",", ""));
					}catch(Exception e){
						
					}
					importe2=new Convertidor().getMoney(impx, 2);
					importe2.replace(",", "");
					if (impx>0){
						tmp.add(this.getInsertACuentaCHT(mes, asiento, ""+sec,detalle, fecha, cuenta2, descripcion2, obs, cb_tc, cb_idcomp, debe_haber, importe2, caja, cht_idbanco, cht_serie, cht_numero, cht_vencimiento, importe2));						
					}
					
				}
				
				if (cuenta2.compareTo("42101")==0){
					if (impx>0){
						importe2.replace(",", "");
						tmp.add(this.getInsertACuenta(mes, asiento, ""+sec, detalle,fecha, cuenta2, descripcion2, "", cb_tc, cb_idcomp, debe_haber, importe2, caja));						
					}
				}
				if (cuenta2.compareTo("111010001")==0){
					importe2.replace(",", "");
					tmp.add(this.getInsertACuenta(mes, asiento, ""+sec, detalle,fecha, cuenta2, descripcion2, "", cb_tc, cb_idcomp, debe_haber, importe2, caja));
				}  
				sec++;
			}
			
			if (debe_haber.compareTo("H")==0){
				debe_haber="D";
			}else{
				debe_haber="H";
			}
			tmp.add(this.getInsertACuenta(mes, asiento, ""+sec, detalle,fecha, cuenta, descripcion, "", cb_tc, cb_idcomp, debe_haber, imp, caja));
			
		}
		return tmp;
	}
	
	public List<String> getConciliaciones(String cuenta,String cb_tc,String cb_idcomp){
	
		_Frame frame=(_Frame)this._logic.getFrame();
		List<String> tmp=new ArrayList<String>();
		LinkedList Cptes=new LinkedList();
		for (int i=0;i<frame.getJTableCpte().getRowCount();i++){
			boolean b=false;
			try {
				b=(Boolean)frame.getJTableCpte().getValueAt(i,0);
				
			}catch(Exception e){
				
			}
			if (b){
				String[] comp=new String[2];
				comp[0]=frame.getJTableCpte().getValueAt(i,2).toString();
				comp[1]=frame.getJTableCpte().getValueAt(i,3).toString();
				Cptes.addLast(comp);
			}
		}
		if (frame.getJTableOPG()!=null){
			for (int i=0;i<frame.getJTableOPG().getRowCount();i++){
				boolean b=false;
				try {
					b=(Boolean)frame.getJTableOPG().getValueAt(i,0);
					
				}catch(Exception e){
					
				}
				if (b){
					String[] comp=new String[2];
					comp[0]=frame.getJTableOPG().getValueAt(i,2).toString();
					comp[1]=frame.getJTableOPG().getValueAt(i,3).toString();
					Cptes.addLast(comp);
				}
			}	
		}
		for (int i=0;i<Cptes.size();i++){
			String[] comp =(String[]) Cptes.get(i);
			String _instruccion_conciliacion_cpte=this.getGenerarAplicacionCobranzaComprobante(cuenta, comp[0], comp[1], cb_tc, cb_idcomp);
			tmp.add(_instruccion_conciliacion_cpte);
		}
		return tmp;
	}
	
	public void GenerarAsientodePago(boolean credito){
		_Logic logic=(_Logic) this._logic;
		_Data data=(_Data)this._logic.getData();
		List<String> batch=this.getBloqueDeInstrucciones(credito);
		
		
			data.clearBatch();
			for (int i=0;i<batch.size();i++){
				String q=batch.get(i);
				System.out.println(q);
				data.addBatch(q);
			}
			boolean error=data.executeBatch();
			if (!error){
				logic.aviso("La Cobranza fue Grabada Correctamente");
				logic.imprimir();
				logic.clean();
			}else {
				logic.error("Error al Grabar la Cobranza");
			}	
		
		
	}
	

	
	
	public String getInsertACuenta(String mes,String asiento,String sec,String detalle,String fecha,String cuenta,String descripcion,String obs,String tc,String idcomp,String debe_haber,String importe,String caja){
		String q="";
		String px="";
		double price=0.0;
		try {
			importe=importe.replace(",", "");
			price=new Double(importe);
			px=new Convertidor().getMoney(price, 2);
			px=px.replace(",", "");
		}catch(Exception e){
			
		}
		q=q+"insert into b_mv_asientos ";
		q=q+" (mes_operativo,numero_asiento,secuencia,detalle,fecha,cuenta,cuenta_descripcion,observacion,tc,idcomprobante,debe_haber,importe,anulado,idcajas)";
		q=q+" values (";
		q=q+" '"+mes+"','"+asiento+"','"+sec+"','"+detalle+"','"+fecha+"','"+cuenta+"','"+descripcion+"','"+obs+"','"+tc+"','"+idcomp+"','"+debe_haber+"',"+px+",";
		q=q+"0,'"+caja+"')";
		return q;
	}
	
	public String getGenerarAplicacionCobranzaComprobante(String cuenta,String tc,String idcomp,String cb_tc,String cb_idcomp){
		_Frame frame=(_Frame)this._logic.getFrame();
		String q="";
		String fecha=frame.get_txt_fecha().getText();
		q=q+"insert b_aplicacion (cuenta,tc,idcomprobante,origen_tc,origen_idcomprobante,fecha) ";
		q=q+" values ('"+cuenta+"',";
		q=q+"'"+cb_tc+"',";
		q=q+"'"+cb_idcomp+"',";
		q=q+"'"+tc+"',";
		q=q+"'"+idcomp+"',";
		q=q+"'"+fecha+"')";
		return q;
	}
	
	public String getInsertIntoCbs(String tc,String idcomp,String cuenta,String fecha,String importe,String anticipo){
		String q="insert into b_cbs (tc,idcomprobante,cuenta,fecha,importe,anticipo) values (";
		q=q+"'"+tc+"',";
		q=q+"'"+idcomp+"',";
		q=q+"'"+cuenta+"',";
		q=q+"'"+fecha+"',";
		q=q+""+importe+",";
		q=q+""+anticipo+")";
		return q;
	}
	
	public LinkedList GrabarMedioss(){
		_Frame frame=(_Frame)this._logic.getFrame();
		LinkedList medios_de_pago=new LinkedList();
		for (int i=0;i<frame.getJTableMedios().getRowCount();i++){
			Boolean ok=false;
			ok=frame.getJTableMedios().getValueAt(i, 0)!=null;
			if (ok){
				String medio="";
				String descripcion="";
				try {
					medio=frame.getJTableMedios().getValueAt(i, 0).toString();
					descripcion=frame.getJTableMedios().getValueAt(i, 1).toString();
					}catch(Exception e){
						
					}
				String importe="0.0";
				Double imp=0.0;
				try {
				importe=frame.getJTableMedios().getValueAt(i, 7).toString().replace(",", "");
				imp=new Double(importe);
				}catch(Exception e){
					
				}
				
				ok=medio.compareTo("")!=0;
				if (ok){
					if (medio.compareTo("CHT")==0){
						String cuenta="111010002";
						String cod_banc=frame.getJTableMedios().getValueAt(i, 2).toString();
						String serie=frame.getJTableMedios().getValueAt(i, 4).toString();
						String numero=frame.getJTableMedios().getValueAt(i, 5).toString();
						String venc=frame.getJTableMedios().getValueAt(i, 6).toString();
						importe=importe.replace(",", "");
						String[] row=new String[7];
						row[0]=cuenta;
						row[1]=descripcion;
						row[2]=cod_banc;
						row[3]=serie;
						row[4]=numero;
						row[5]=venc;
						row[6]=importe;
						medios_de_pago.add(row);
					}else {
						if (imp>0){
							if (medio.compareTo("DO")==0){
								String cuenta="42101";
								String[] row=new String[3];
								row[0]=cuenta;
								row[1]=descripcion;
								row[2]=importe;
								medios_de_pago.add(row);
							}
							if (medio.compareTo("EF")==0){
								String cuenta="111010001";
								String[] row=new String[3];
								row[0]=cuenta;
								row[1]=descripcion;
								row[2]=importe;
								medios_de_pago.add(row);
							}
								
						}
						
					}
					
				}
			}
		}
		return medios_de_pago;
}
	
	
	public String getInsertACuentaCHT(String mes,String asiento,String sec,String detalle,String fecha,String cuenta,String descripcion,String obs,String tc,String idcomp,String debe_haber,String importe,String caja,String cht_idbanco,String cht_serie,String cht_numero,String cht_venc,String cht_imp){
		String q="";
		double price=0.0;
		try {
			importe=importe.replace(",", "");
			price=new Double(importe);
			
		}catch(Exception e){
			
		}
		q=q+"insert into b_mv_asientos ";
		q=q+" (mes_operativo,numero_asiento,secuencia,detalle,fecha,cuenta,cuenta_descripcion,observacion,tc,idcomprobante,debe_haber,importe,anulado,idcajas,cht_idbanco,cht_serie,cht_numero,cht_vencimiento,cht_importe)";
		q=q+" values (";
		q=q+" '"+mes+"','"+asiento+"','"+sec+"','"+detalle+"','"+fecha+"','"+cuenta+"','"+descripcion+"','"+obs+"','"+tc+"','"+idcomp+"','"+debe_haber+"',"+price+",";
		q=q+"0,";
		q=q+"'"+caja+"',";
		q=q+"'"+cht_idbanco+"',";
		q=q+"'"+cht_serie+"',";
		q=q+"'"+cht_numero+"',";
		q=q+"'"+cht_venc+"',";
		q=q+"'"+price+"'";
		q=q+")";
		return q;
	}
	
	
}
