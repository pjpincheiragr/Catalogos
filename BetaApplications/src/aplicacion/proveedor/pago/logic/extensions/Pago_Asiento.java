package aplicacion.proveedor.pago.logic.extensions;

import java.util.LinkedList;

import javax.swing.JOptionPane;
import aplicacion.herramientas.java.*;

import aplicacion.proveedor.pago.logic.*;
import aplicacion.proveedor.pago.gui.*;
import aplicacion.modelo.logic.Logic_Extension;
import visual.CargaFVN;
public class Pago_Asiento  extends Logic_Extension {
	private String caja="   2";
	private String detalle="";
	private CargaFVN FVN;
	
	public void setDetalle(String detalle){
		this.detalle=detalle;
	}
	public void setCaja(String idcaja){
		this.caja=idcaja;
	}
	
	public void GenerarAsientodePago(){
		_Data data=(_Data)this._logic.getData();
		_Frame frame=(_Frame)this._logic.getFrame();
		LinkedList batch=new LinkedList();
		LinkedList medios_de_pago =this.GrabarMedioss();
		String imp=frame.get_txt_total_pago().getText();
		String account=frame.get_txt_idproveedor().getText();
		String cuenta=account;
		String descripcion=frame.get_txt_proveedor_descripcion().getText();
		String importe=""+imp;
		String tc="FCN";
		if (FVN!=null){
			tc=FVN.getTipoTC();
		}
		String idcomp=frame.get_txt_idproveedor().getText();
		String cb_tc="PG";
		String cb_idcomp=data.getProximoPGCorrecto();
		String mes=data.getMes_Operativo();
		String asiento=""+(data.getNumeroAsientoProximo());
		
		String fecha=frame.get_txt_fecha().getText();
		
		String debe_haber="H";
		
		
		String caja=this.caja;
		
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
					String obs="";
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
				
				if (cuenta2.compareTo("43104")==0){
					if (impx>0){
						importe2.replace(",", "");
						qs[i]=this.getInsertACuenta(mes, asiento, ""+sec, detalle,fecha, cuenta2, descripcion2, "", cb_tc, cb_idcomp, debe_haber, importe2, caja);						
					}
				}
				if (cuenta2.compareTo("111010001")==0){
					importe2.replace(",", "");
					qs[i]=this.getInsertACuenta(mes, asiento, ""+sec, detalle,fecha, cuenta2, descripcion2, "", cb_tc, cb_idcomp, debe_haber, importe2, caja);
				}  
				sec++;
			}
			debe_haber="D";
			
			qs[medios_de_pago.size()]=this.getInsertACuenta(mes, asiento, ""+sec, detalle,fecha, cuenta, descripcion, "", cb_tc, cb_idcomp, debe_haber, imp, caja);
			
			
			for (int i=0;i<qs.length;i++){
				batch.addLast(qs[i]);
			}
			
			//String f=this.getUpdatepg();
			//System.out.println(f);
			//this.GX.addBatchSQL(f);
		}
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
			String qu=this.getGenerarAplicacionCobranzaComprobante(cuenta, comp[0], comp[1], cb_tc, cb_idcomp);
			batch.addLast(qu);
		}
		importe=importe.replace(",","");
		String ant=frame.get_txt_anticipo().getText();
		ant=ant.replace(",","");
		String t=this.getInsertIntoCbs(cb_tc, cb_idcomp, account,fecha, importe, ant);
		batch.addLast(t);
		String w=data.getUpdatePG();
		batch.addLast(w);
		//System.out.println("Asiento");
		if (FVN==null){
			
			data.clearBatch();
			for (int i=0;i<batch.size();i++){
				String q=(String) batch.get(i);
				System.out.println(q);
				data.addBatch(q);
			}
			//boolean error=false ;
			boolean error=data.executeBatch();
			if (!error){
				JOptionPane.showMessageDialog(frame, "El Pago fue Grabado Correctamente");
				((_Logic) this._logic).imprimir();
				((_Logic) this._logic).clean();
			}else {
				JOptionPane.showMessageDialog(frame, "Error al Grabar El Pago");
			}	
		}else {
			for (int i=0;i<batch.size();i++){
				String q=(String) batch.get(i);
				System.out.println(q);
				FVN.addInstruccion(q);
			}
			
			boolean error=FVN.GuardarCambios();
			if (!error){
				frame.setVisible(false);
				frame.dispose();
			}
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
							if (medio.compareTo("DOB")==0){
								String cuenta="43104";
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
