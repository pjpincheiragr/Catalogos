package aplicacion.gestion.canje.logic.extensions;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import aplicacion.herramientas.java.*;
import javax.swing.JTable;
import aplicacion.gestion.canje.logic.*;
import aplicacion.gestion.canje.gui.*;
import aplicacion.modelo.logic.Logic_Extension;

public class Canje_Asiento  extends Logic_Extension {
	//private String caja="   2";
	//private CargaFVN FVN;
	
	
	public void GenerarAsientodePago(){
		_Data data=(_Data)this._logic.getData();
		_Frame frame=(_Frame)this._logic.getFrame();
		List<String> batch=new ArrayList();
		
		
		String imp=frame.get_txt_total_egreso().getText();
		
		String obs="";
		System.out.println("OBS> "+obs);
		String cb_tc="CJE";
		String cb_idcomp=data.getProximoPGCorrecto();
		String mes=data.getMes_Operativo();
		String asiento=""+(data.getNumeroAsientoProximo());
		String fecha=frame.get_txt_fecha().getText();
		String debe_haber="H";
		String detalle=frame.get_txt_concepto().getText();
		String uscanje=frame.get_txt_cotizacion_dolar().getText();
		String caja=frame.get_list_cajas().getSelectedItem().toString();
		LinkedList medios_de_pago =this.GrabarMedioss(frame.getJTableMedios());
		int secuencia=0;
		List<String> instructions=this.getInsertInstructions(medios_de_pago, mes, asiento, fecha, detalle, obs, cb_tc, cb_idcomp, debe_haber, caja,secuencia,uscanje);
		LinkedList medios_de_pago2 =this.GrabarMedioss(frame.getJTableMedios2());
		for (int i=0;i<instructions.size();i++){
			batch.add(instructions.get(i));
		}
		secuencia=instructions.size();
		debe_haber="D";
		List<String> instructions2=this.getInsertInstructions(medios_de_pago2, mes, asiento, fecha, detalle, obs, cb_tc, cb_idcomp, debe_haber, caja,secuencia,uscanje);
		for (int i=0;i<instructions2.size();i++){
			batch.add(instructions2.get(i));
		}
		
		String w=data.getUpdatePG();
		batch.add(w);
		
			data.clearBatch();
			for (int i=0;i<batch.size();i++){
				System.out.println(batch.get(i));
				data.addBatch(batch.get(i));
			}

			boolean error=data.executeBatch();
			if (!error){
				JOptionPane.showMessageDialog(frame, "El Canje fue Grabado Correctamente");
				((_Logic) this._logic).clean();
			}else {
				JOptionPane.showMessageDialog(frame, "Error al Grabar Canje");
			}	
		
		
	}
	
	
	public String getInsertACuenta(String mes,String asiento,String sec,String detalle,String fecha,String cuenta,String descripcion,String obs,String tc,String idcomp,String debe_haber,String importe,String caja,String uscotizacion){
		String q="";
		String px="";
		double price=0.0;
		if (uscotizacion.compareTo("")==0){
			uscotizacion="0.0";
		}
		try {
			importe=importe.replace(",", "");
			price=new Double(importe);
			px=new Convertidor().getMoney(price, 2);
			px=px.replace(",", "");
		}catch(Exception e){
			
		}
		q=q+"insert into b_mv_asientos ";
		q=q+" (mes_operativo,numero_asiento,secuencia,detalle,fecha,cuenta,cuenta_descripcion,observacion,tc,idcomprobante,debe_haber,importe,anulado,idcajas,us)";
		q=q+" values (";
		q=q+" '"+mes+"','"+asiento+"','"+sec+"','"+detalle+"','"+fecha+"','"+cuenta+"','"+descripcion+"','"+obs+"','"+tc+"','"+idcomp+"','"+debe_haber+"',"+px+",";
		q=q+"0,'"+caja+"',"+uscotizacion+")";
		return q;
	}
	
	public List<String> getInsertInstructions(LinkedList medios_de_pago,String mes,
			String asiento,String fecha,String detalle,
			String obs, String cb_tc, String cb_idcomp,
			String debe_haber, String caja,
			int secuencia,
			String uscanje
			){
		List<String> instructions=new ArrayList<String>();
		if (medios_de_pago.size()>0){
			int sec=secuencia;
			
			for (int i=0;i<medios_de_pago.size();i++){
				String insert="";
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
					
					try {
						impx=new Double(importe2.replace(",", ""));
					}catch(Exception e){
						
					}
					importe2=new Convertidor().getMoney(impx, 2);
					importe2.replace(",", "");
					if (impx>0){
						insert=this.getInsertACuentaCHT(mes, asiento, ""+sec,detalle, fecha, cuenta2, descripcion2, obs, cb_tc, cb_idcomp, debe_haber, importe2, caja, cht_idbanco, cht_serie, cht_numero, cht_vencimiento, importe2,uscanje);						
					}
					
				}
				
				if (cuenta2.compareTo("42101")==0){
					if (impx>0){
						importe2.replace(",", "");
						insert=this.getInsertACuenta(mes, asiento, ""+sec, detalle,fecha, cuenta2, descripcion2, obs, cb_tc, cb_idcomp, debe_haber, importe2, caja,uscanje);						
					}
				}
				if (cuenta2.compareTo("111010001")==0){
					importe2.replace(",", "");
					insert=this.getInsertACuenta(mes, asiento, ""+sec, detalle,fecha, cuenta2, descripcion2, obs, cb_tc, cb_idcomp, debe_haber, importe2, caja,uscanje);
				}
				if (cuenta2.compareTo("111010099")==0){
					importe2.replace(",", "");
					insert=this.getInsertACuenta(mes, asiento, ""+sec, detalle,fecha, cuenta2, descripcion2, obs, cb_tc, cb_idcomp, debe_haber, importe2, caja,uscanje);
				} 
				sec++;
				if (insert.compareTo("")!=0)instructions.add(insert);
				insert="";
			}
			
		}
		return instructions;
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
	
	public LinkedList GrabarMedioss(JTable table){
		_Frame frame=(_Frame)this._logic.getFrame();
		LinkedList medios_de_pago=new LinkedList();
		for (int i=0;i<table.getRowCount();i++){
			Boolean ok=false;
			ok=table.getValueAt(i, 0)!=null;
			if (ok){
				String medio="";
				String descripcion="";
				try {
					medio=table.getValueAt(i, 0).toString();
					descripcion=table.getValueAt(i, 1).toString();
					}catch(Exception e){
						
					}
				String importe="0.0";
				Double imp=0.0;
				try {
				importe=table.getValueAt(i, 7).toString().replace(",", "");
				imp=new Double(importe);
				}catch(Exception e){
					
				}
				
				ok=medio.compareTo("")!=0;
				if (ok){
					if (medio.compareTo("CHT")==0){
						String cuenta="111010002";
						String cod_banc=table.getValueAt(i, 2).toString();
						String serie=table.getValueAt(i, 4).toString();
						String numero=table.getValueAt(i, 5).toString();
						String venc=table.getValueAt(i, 6).toString();
						importe=importe.replace(",", "");
						String[] cht=new String[7];
						cht[0]=cuenta;
						cht[1]=descripcion;
						cht[2]=cod_banc;
						cht[3]=serie;
						cht[4]=numero;
						cht[5]=venc;
						cht[6]=importe;
						medios_de_pago.add(cht);
					}else {
						if (imp>0){
							if (medio.compareTo("DO")==0){
								String cuenta="42101";
								String[] cht=new String[3];
								cht[0]=cuenta;
								cht[1]=descripcion;
								cht[2]=importe;
								medios_de_pago.add(cht);
							}
							if (medio.compareTo("EF")==0){
								String cuenta="111010001";
								String[] cht=new String[3];
								cht[0]=cuenta;
								cht[1]=descripcion;
								cht[2]=importe;
								medios_de_pago.add(cht);
							}
							if (medio.compareTo("US")==0){
								String cuenta="111010099";
								String[] cht=new String[3];
								cht[0]=cuenta;
								cht[1]=descripcion;
								cht[2]=importe;
								medios_de_pago.add(cht);
							}	
						}
						
					}
					
				}
			}
		}
		return medios_de_pago;
}
	
	public LinkedList GrabarMedioss(){
		_Frame frame=(_Frame)this._logic.getFrame();
		LinkedList medios_de_pago=new LinkedList();
		medios_de_pago=this.GrabarMedioss(frame.getJTableMedios());
		return medios_de_pago;
	}
	
	
	
	public String getInsertACuentaCHT(String mes,String asiento,String sec,String detalle,String fecha,String cuenta,String descripcion,String obs,String tc,String idcomp,String debe_haber,String importe,String caja,String cht_idbanco,String cht_serie,String cht_numero,String cht_venc,String cht_imp,String uscotizacion){
		String q="";
		double price=0.0;
		if (uscotizacion.compareTo("")==0){
			uscotizacion="0.0";
		}
		try {
			importe=importe.replace(",", "");
			price=new Double(importe);
			
		}catch(Exception e){
			
		}
		q=q+"insert into b_mv_asientos ";
		q=q+" (mes_operativo,numero_asiento,secuencia,detalle,fecha,cuenta,cuenta_descripcion,observacion,tc,idcomprobante,debe_haber,importe,anulado,idcajas,cht_idbanco,cht_serie,cht_numero,cht_vencimiento,cht_importe,us)";
		q=q+" values (";
		q=q+" '"+mes+"','"+asiento+"','"+sec+"','"+detalle+"','"+fecha+"','"+cuenta+"','"+descripcion+"','"+obs+"','"+tc+"','"+idcomp+"','"+debe_haber+"',"+price+",";
		q=q+"0,";
		q=q+"'"+caja+"',";
		q=q+"'"+cht_idbanco+"',";
		q=q+"'"+cht_serie+"',";
		q=q+"'"+cht_numero+"',";
		q=q+"'"+cht_venc+"',";
		q=q+"'"+price+"',";
		q=q+""+uscotizacion+"";
		q=q+")";
		return q;
	}
	
	
}
