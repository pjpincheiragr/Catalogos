package aplicacion.gestion.egreso.logic.extensions;

import java.util.LinkedList;

import javax.swing.JOptionPane;
import aplicacion.herramientas.java.*;

import aplicacion.gestion.egreso.logic.*;
import aplicacion.gestion.egreso.gui.*;
import aplicacion.modelo.logic.Logic_Extension;

public class Egreso_Asiento  extends Logic_Extension {
	//private String caja="   2";
	//private CargaFVN FVN;
	
	
	public void GenerarAsientodePago(){
		_Data data=(_Data)this._logic.getData();
		_Frame frame=(_Frame)this._logic.getFrame();
		LinkedList batch=new LinkedList();
		LinkedList medios_de_pago =this.GrabarMedioss();
		String imp=frame.get_txt_total_pago().getText();
		String account=frame.get_txt_idcuenta().getText();
		String cuenta=account;
		String descripcion=frame.get_txt_clientedescripcion().getText();
		String obs="";
		System.out.println("OBS> "+obs);
		String importe=""+imp;
		
		/*String tc="FVN";
		if (FVN!=null){
			tc=FVN.getTipoTC();
		}*/
		//String idcomp=frame.get_txt_idproveedor().getText();
		String cb_tc="EGR";
		String cb_idcomp=data.getProximoPGCorrecto();
		String mes=data.getMes_Operativo();
		String asiento=""+(data.getNumeroAsientoProximo());
		
		String fecha=frame.get_txt_fecha().getText();
		
		String debe_haber="H";
		
		String detalle=frame.get_txt_concepto().getText();//"Cob. "+this.getTipoTC()+" "+idcomp;
			//en detalle podria colocar todos los comprobantes
		String caja=frame.get_list_cajas().getSelectedItem().toString();
		
		if (medios_de_pago.size()>0){
			int sec=0;
			String[] qs=new String[medios_de_pago.size()+1];
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
					
					try {
						impx=new Double(importe2.replace(",", ""));
					}catch(Exception e){
						
					}
					importe2=new Convertidor().getMoney(impx, 2);
					importe2.replace(",", "");
					if (impx>0){
						qs[i]=this.getInsertACuentaCHT(mes, asiento, ""+sec,detalle, fecha, cuenta2, descripcion2, obs, cb_tc, cb_idcomp, debe_haber, importe2, caja, cht_idbanco, cht_serie, cht_numero, cht_vencimiento, importe2);						
					}
					
				}
				
				if (cuenta2.compareTo("42101")==0){
					if (impx>0){
						importe2.replace(",", "");
						qs[i]=this.getInsertACuenta(mes, asiento, ""+sec, detalle,fecha, cuenta2, descripcion2, obs, cb_tc, cb_idcomp, debe_haber, importe2, caja);						
					}
				}
				if (cuenta2.compareTo("111010001")==0){
					importe2.replace(",", "");
					qs[i]=this.getInsertACuenta(mes, asiento, ""+sec, detalle,fecha, cuenta2, descripcion2, obs, cb_tc, cb_idcomp, debe_haber, importe2, caja);
				}  
				if (cuenta2.compareTo("111010099")==0){
					importe2.replace(",", "");
					qs[i]=this.getInsertACuenta(mes, asiento, ""+sec, detalle,fecha, cuenta2, descripcion2, obs, cb_tc, cb_idcomp, debe_haber, importe2, caja);
				}  
				sec++;
			}
			debe_haber="D";
			
			qs[medios_de_pago.size()]=this.getInsertACuenta(mes, asiento, ""+sec, detalle,fecha, cuenta, descripcion, obs, cb_tc, cb_idcomp, debe_haber, imp, caja);
			
			
			for (int i=0;i<qs.length;i++){
				batch.addLast(qs[i]);
			}
			
			//String f=this.getUpdatepg();
			//System.out.println(f);
			//this.GX.addBatchSQL(f);
		}
		
		importe=importe.replace(",","");
		String w=data.getUpdatePG();
		batch.addLast(w);
		//System.out.println("Asiento");
		
			data.getConnectionHandler().getDefaultConnector().clearBatch();
			for (int i=0;i<batch.size();i++){
				String q=(String) batch.get(i);
				System.out.println(q);
				data.getConnectionHandler().getDefaultConnector().addBatch(q);
			}
			//boolean error=false ;
			boolean error=data.getConnectionHandler().getDefaultConnector().executeBatch();
			if (!error){
				JOptionPane.showMessageDialog(frame, "El Egreso fue Grabado Correctamente");
				((_Logic) this._logic).clean();
			}else {
				JOptionPane.showMessageDialog(frame, "Error al Grabar Egreso");
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
