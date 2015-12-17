package aplicacion.alfa.comprobantes.mv_asientos.logic;
import aplicacion.alfa.comprobantes.mv_asientos.interfaces._Interface;
import aplicacion.alfa.comprobantes.mv_asientos.objetos.Asiento;
import aplicacion.alfa.comprobantes.mv_asientos.objetos.Renglon;
import aplicacion.modelo.logic.Data;
import aplicacion.herramientas.java.*;
import java.util.*;

public class _Data extends Data{

	public boolean existe(Asiento A){
		boolean existe=false;
		String[] parameters=new String[]{
		A.getPeriodo(),
		A.getMes_operativo(),
		A.getNumero_asiento()
		};
		String q=this.getQuery(_Interface.existe, parameters);
		Object[][] results=this.getResults(q);
		if (results!=null){
			existe=results.length>0;
		}
		return existe;
	}
	
	
	private boolean insertar(Asiento A){
		boolean ok=false;
		this.clearBatch();
		System.out.println("Renlgones? "+A.getRenglones());
		for (int secuencia=0;secuencia<A.getRenglones();secuencia++){
			Renglon R=A.getRenglon(secuencia);
			
			Object[] parameters=new Object[]{
					R.getCuenta(),
					secuencia,
					A.getMes_operativo(),
					A.getNumero_asiento(),
					A.getFecha(),
					A.getDetalle(),
					A.getTc(),
					A.getSucursal(),
					A.getNumero(),
					A.getLetra(),
					R.getDebe_haber(),
					R.getImporte(),
					A.getMoneda(),
					A.getCotizacion(),
					A.getId_cotiz(),
					A.getPeriodo(),
					A.getCabimporte(),
					R.getVencimiento(),
					A.getTipo_reg(),
					A.getFechahora_grabacion(),
					R.getCabcuenta(),
					R.getCabnombre(),
					R.getCabcuit(),
					R.getCabcondiva(),
					R.getLiva_tipo(),
					R.getLiva_impnetograv(),
					R.getLiva_impnetongrav(),
					R.getLiva_exento(),
					R.getLiva_aliciva(),
					R.getLiva_impiva(),
					R.getLiva_aliciva2(),
					R.getLiva_impiva2(),
					R.getLiva_aliciva3(),
					R.getLiva_impiva3(),
					R.getLiva_ret_perc(),
					R.getLiva_ret_ibtos(),
					R.getLiva_ret_ganancias(),
					R.getLiva_total(),
					A.getFechasubdiario(),
					A.getNrocai(),
					A.getFhvtocai(),
					R.getIdcajas(),
					A.getUnegocio(),
					A.getIdmotivo()
			};
			double impx=0.0;
			try {
				impx=new Double(R.getImporte());
			}catch(Exception e){
				
			}
			if (impx>0){
				String q=this.getQuery(_Interface.insertar, parameters);
				System.out.println(q);
				this.addBatch(q);
					
			}
			
		}
		ok=!this.executeBatch();
		
		
		return ok;
	}
	
	private List<String> getInstrucciones_Insertar(Asiento A){
		List<String> instrucciones=new ArrayList<String>();
		
		
		System.out.println("Renlgones? "+A.getRenglones());
		for (int secuencia=0;secuencia<A.getRenglones();secuencia++){
			Renglon R=A.getRenglon(secuencia);
			
			Object[] parameters=new Object[]{
					R.getCuenta(),
					secuencia,
					A.getMes_operativo(),
					A.getNumero_asiento(),
					A.getFecha(),
					A.getDetalle(),
					A.getTc(),
					A.getSucursal(),
					A.getNumero(),
					A.getLetra(),
					R.getDebe_haber(),
					R.getImporte(),
					A.getMoneda(),
					A.getCotizacion(),
					A.getId_cotiz(),
					A.getPeriodo(),
					A.getCabimporte(),
					R.getVencimiento(),
					A.getTipo_reg(),
					A.getFechahora_grabacion(),
					R.getCabcuenta(),
					R.getCabnombre(),
					R.getCabcuit(),
					R.getCabcondiva(),
					R.getLiva_tipo(),
					R.getLiva_impnetograv(),
					R.getLiva_impnetongrav(),
					R.getLiva_exento(),
					R.getLiva_aliciva(),
					R.getLiva_impiva(),
					R.getLiva_aliciva2(),
					R.getLiva_impiva2(),
					R.getLiva_aliciva3(),
					R.getLiva_impiva3(),
					R.getLiva_ret_perc(),
					R.getLiva_ret_ibtos(),
					R.getLiva_ret_ganancias(),
					R.getLiva_total(),
					A.getFechasubdiario(),
					A.getNrocai(),
					A.getFhvtocai(),
					R.getIdcajas(),
					A.getUnegocio(),
					A.getIdmotivo()
			};
			double impx=0.0;
			try {
				impx=new Double(R.getImporte());
			}catch(Exception e){
				
			}
			if (impx>0){
				String q=this.getQuery(_Interface.insertar, parameters);
				System.out.println(q);
				instrucciones.add(q);
					
			}
			
		}
		
		
		
		return instrucciones;
	}
	
	public boolean grabar(Asiento A){
		boolean ok=false;
		A.normalizeToAlfaDefaults();
		//this.setCABData(A);
		if (this.esNuevo(A)){
			this.cargar_idnuevo(A);
			while(this.existe(A)){
				System.out.println("El id "+A.getPeriodo()+"."+A.getMes_operativo()+"."+A.getNumero_asiento()+" nuevo ya fue utilizado ");
				this.cargar_idnuevo(A);	
				
			}
			if (this.esCorrecto(A)){
				
				ok=this.insertar(A);
				if(!ok){
					System.out.println("Error al insertar asiento");
				}

			}else{
				System.out.println("el asiento es incorrecto");
			}
			
		}else {
			
			if (this.esCorrecto(A)){		
				if (existe(A)){
					ok=this.actualizar(A);	
					
				}else {
					ok=this.insertar(A);		
				}
			}
			
		}
		System.out.println("GRABACION ASIENTO ["+A.getPeriodo()+"."+A.getMes_operativo()+"."+A.getNumero_asiento()+"] = "+ok);
		return ok;
	}
	
	public List<String> getInstrucciones(Asiento A){
		List<String> instrucciones=new ArrayList<String>();
			A.normalizeToAlfaDefaults();
			//this.setCABData(A);
			if (this.esNuevo(A)){
				this.cargar_idnuevo(A);
				while(this.existe(A)){
					System.out.println("El id "+A.getPeriodo()+"."+A.getMes_operativo()+"."+A.getNumero_asiento()+" nuevo ya fue utilizado ");
					this.cargar_idnuevo(A);	
					
				}
				if (this.esCorrecto(A)){
					
					List<String> instrucciones_insertar=this.getInstrucciones_Insertar(A);
					for (int i=0;i<instrucciones_insertar.size();i++){
						instrucciones.add(instrucciones_insertar.get(i));
					}

				}else{
					System.out.println("el asiento es incorrecto");
				}
				
			}else {
				
				if (this.esCorrecto(A)){		
					if (existe(A)){
						
						List<String> instrucciones_insertar=this.getInstrucciones_Actualizar(A);
						for (int i=0;i<instrucciones_insertar.size();i++){
							instrucciones.add(instrucciones.get(i));
						}	
					}else {
						List<String> instrucciones_insertar=this.getInstrucciones_Insertar(A);
						for (int i=0;i<instrucciones_insertar.size();i++){
							instrucciones.add(instrucciones.get(i));
						}		
					}
				}
				
			}
			System.out.println("GRABACION ASIENTO ["+A.getPeriodo()+"."+A.getMes_operativo()+"."+A.getNumero_asiento()+"]");
			return instrucciones;
			
		}

	
	private boolean actualizar(Asiento A){
		boolean ok=false;
		clearBatch();
		for (int secuencia=0;secuencia<A.getRenglones();secuencia++){
			Renglon R=A.getRenglon(secuencia);
			Object[] parameters=new Object[]{
					R.getCuenta(),
					secuencia,
					A.getMes_operativo(),
					A.getNumero_asiento(),
					A.getFecha(),
					A.getDetalle(),
					A.getTc(),
					A.getSucursal(),
					A.getNumero(),
					A.getLetra(),
					R.getDebe_haber(),
					R.getImporte(),
					A.getMoneda(),
					A.getCotizacion(),
					A.getId_cotiz(),
					A.getPeriodo(),
					A.getCabimporte(),
					R.getVencimiento(),
					A.getTipo_reg(),
					A.getFechahora_grabacion(),
					R.getCabcuenta(),
					R.getCabnombre(),
					R.getCabcuit(),
					R.getCabcondiva(),
					R.getLiva_tipo(),
					R.getLiva_impnetograv(),
					R.getLiva_impnetongrav(),
					R.getLiva_exento(),
					R.getLiva_aliciva(),
					R.getLiva_impiva(),
					R.getLiva_ret_perc(),
					R.getLiva_ret_ibtos(),
					R.getLiva_ret_ganancias(),
					R.getLiva_total(),
					A.getFechasubdiario(),
					R.getIdcajas(),
					A.getUnegocio(),
					A.getIdmotivo()
			};
			String q=this.getQuery(_Interface.actualizar, parameters);
			addBatch(q);
		}
		ok=!executeBatch();
		return ok;
	}
	

	private List<String> getInstrucciones_Actualizar(Asiento A){
		
		List<String> instrucciones=new ArrayList<String>();
		clearBatch();
		for (int secuencia=0;secuencia<A.getRenglones();secuencia++){
			Renglon R=A.getRenglon(secuencia);
			Object[] parameters=new Object[]{
					R.getCuenta(),
					secuencia,
					A.getMes_operativo(),
					A.getNumero_asiento(),
					A.getFecha(),
					A.getDetalle(),
					A.getTc(),
					A.getSucursal(),
					A.getNumero(),
					A.getLetra(),
					R.getDebe_haber(),
					R.getImporte(),
					A.getMoneda(),
					A.getCotizacion(),
					A.getId_cotiz(),
					A.getPeriodo(),
					A.getCabimporte(),
					R.getVencimiento(),
					A.getTipo_reg(),
					A.getFechahora_grabacion(),
					R.getCabcuenta(),
					R.getCabnombre(),
					R.getCabcuit(),
					R.getCabcondiva(),
					R.getLiva_tipo(),
					R.getLiva_impnetograv(),
					R.getLiva_impnetongrav(),
					R.getLiva_exento(),
					R.getLiva_aliciva(),
					R.getLiva_impiva(),
					R.getLiva_ret_perc(),
					R.getLiva_ret_ibtos(),
					R.getLiva_ret_ganancias(),
					R.getLiva_total(),
					A.getFechasubdiario(),
					R.getIdcajas(),
					A.getUnegocio(),
					A.getIdmotivo()
			};
			String q=this.getQuery(_Interface.actualizar, parameters);
			instrucciones.add(q);
			
		}
		
		return instrucciones;
	}
	public boolean cargar_idnuevo(Asiento A){
		boolean ok=false;
		Object[] parameters=new Object[]{
				A.getFecha()
			};
		String q=this.getQuery(_Interface.nuevo, parameters);
		
		Object[][] results=getResults(q);
		if (results!=null){
			if (results.length>0){
				ok=true;
				String periodo=(String)results[0][0];
				String mes=(String)results[0][1];
				String numero=(String)results[0][2];
				Integer _numero=new Integer(numero);
				System.out.println("asiento ultimo:"+_numero);
				_numero++;
				numero=""+_numero;
				A.setPeriodo(periodo);
				A.setMes_operativo(mes);
				System.out.println("asiento actual:"+_numero);
				A.setNumero_asiento(numero);
			}
		}
		return ok;
	}
	
	private boolean esNuevo(Asiento A){
		
		boolean nuevo=(A.getMes_operativo()==null
				& A.getPeriodo()==null
				& A.getNumero_asiento()==null
				);
			
		return nuevo;
	}
	
	private boolean esCorrecto(Asiento A){
		boolean correcto=false;
		if (A.getMes_operativo()!=null){
			correcto=(A.getMes_operativo().compareTo("")!=0
					& A.getPeriodo().compareTo("")!=0
					& A.getNumero_asiento().compareTo("")!=0);
			if (correcto){
				correcto=A.balancea();
			}	
		}
		
		return correcto;
	}
	
	public boolean eliminar(Asiento A){
		boolean eliminado=false;
		if (this.esCorrecto(A)){
			if (this.existe(A)){
				Object[] parameters=new Object[]{
						A.getPeriodo(),
						A.getMes_operativo(),
						A.getNumero_asiento()
					};
				String q=this.getQuery(_Interface.eliminar, parameters);
				clearBatch();
				addBatch(q);
				executeBatch();
				
				
			}	
		}
		
		
		eliminado=!this.existe(A);
		System.out.println("ELIMINACION ASIENTO ["+A.getPeriodo()+"."+A.getMes_operativo()+"."+A.getNumero_asiento()+"] = "+eliminado);
		return eliminado;
	}
	
	private Renglon getRenglon(Asiento A,String idcuenta){
	    
	    Renglon R=null;
	    for (int i=0;i<A.getRenglones();i++){
	    	Renglon aux=A.getRenglon(i);
	    	if (aux.getCuenta().compareTo(idcuenta)==0){
	    		R=aux;
	    	}
	    }
	    return R;
	}
	
	public boolean tiene_iva_debito(Asiento A){
		boolean tiene=this.get_iva_debito(A)!=null;
		return tiene;
	}
	public boolean tiene_iva10(Asiento A){
		boolean tiene=this.get_iva10(A)!=null;
		return tiene;
	}
	public boolean tiene_iva27(Asiento A){
		boolean tiene=this.get_iva27(A)!=null;
		return tiene;
	}
	public boolean tiene_iva_credito(Asiento A){
		boolean tiene=this.get_iva_credito(A)!=null;
		return tiene;
	}
	
	private Renglon get_perc_iibb(Asiento A){
		return this.getRenglon(A, "11209");
		
	}
	private boolean tiene_perc_iibb(Asiento A){
		boolean tiene=this.get_perc_iibb(A)!=null;
		return tiene;
	}
	
	private boolean tiene_iva_perc(Asiento A){
		boolean tiene= this.get_iva_perc(A)!=null;
		return tiene;
	}
	
	private Renglon get_iva_credito(Asiento A){
		return this.getRenglon(A, "11203");	
	}
	private Renglon get_iva_debito(Asiento A){
		return this.getRenglon(A, "11201");	
	}
	
	private Renglon get_iva_perc(Asiento A){
		return this.getRenglon(A, "11208");	
	}
	private Renglon get_iva10(Asiento A){
		return this.getRenglon(A, "11261");	
	}
	private Renglon get_iva27(Asiento A){
		return this.getRenglon(A, "11262");	
	}
	private Renglon get_retencion_ganancias(Asiento A){
		return this.getRenglon(A, "11210");	
	}
	
	private boolean tiene_retencion_ganancias(Asiento A){
		boolean tiene=this.get_retencion_ganancias(A)!=null;
		return tiene;
	}
	
	public Renglon get_gastos_tarjeta(Asiento A){
	return this.getRenglon(A, "42301");	
	}
	
	private boolean tiene_gastos_tarjeta(Asiento A){
		boolean tiene=this.get_gastos_tarjeta(A)!=null;
		return tiene;
	}
	
	private boolean esNotadeCredito(Asiento A){
		boolean nc=false;
		nc=(A.getTc().compareTo("NCC")==0 |
				A.getTc().compareTo("NC")==0);
		return nc;
	}
	private void setCABData(Asiento A){
		Renglon R=A.getRenglon(0);
		Object[] parameters=new Object[]{
				R.getCuenta()
			};
		
		String q=this.getQuery(_Interface.cab, parameters);
		Object[][] results=getResults(q);
		if (results!=null){
			if (results.length>0){
				String descripcion=results[0][0].toString();
				String iva=results[0][1].toString();
				String cuit=results[0][2].toString();;
				R.setCabnombre(descripcion);
				R.setCabcuit(cuit);
				R.setCabcondiva(iva);
				if (this.tiene_iva_credito(A)){
					R.setLiva_tipo("COMPRAS");	
				}
				if (this.tiene_iva_debito(A)){
					R.setLiva_tipo("VENTAS");		
				}
				
				
				Double importetotal=0.0;
				Double importe_percepcion_iibb=0.0;
				Double importe_percepcion_iva=0.0;
				Double importe_netonogravado=0.0;
				Double importe_retencion_ganancias=0.0;
				double importe_iva=0.0;
				double importe_iva10=0.0;
				double importe_iva27=0.0;
				
				if (this.tiene_iva_credito(A)){
					
					Renglon r=this.get_iva_credito(A);
					System.out.println("tiene "+r.getCuenta());
					
					Double impx=new Double(r.getImporte());
					importe_iva+=impx;
					
				}
				if (this.tiene_iva_debito(A)){
					Renglon r=this.get_iva_debito(A);
					System.out.println("tiene "+r.getCuenta());
					Double impx=new Double(r.getImporte());
					importe_iva+=impx;
					
				}
				if (this.tiene_iva10(A)){
					Renglon r=this.get_iva10(A);
					System.out.println("tiene "+r.getCuenta());
					Double impx=new Double(r.getImporte());
					importe_iva10+=impx;
					
				}
				if (this.tiene_iva27(A)){
					Renglon r=this.get_iva27(A);
					System.out.println("tiene "+r.getCuenta());
					Double impx=new Double(r.getImporte());
					importe_iva27+=impx;
					
				}
				Double alic_iva=21.0;
				try {
					importetotal=new Double(R.getImporte());
				}catch(Exception e){
					
				}
				
				if (this.tiene_iva_perc(A)){
					Renglon aux=this.get_iva_perc(A);
					double importe=0.0;
					try {
						importe=new Double(aux.getImporte());
					}catch(Exception e){
						
					}
					importe_percepcion_iva+=importe;
					
				}
				if (this.tiene_perc_iibb(A)){
					Renglon aux=this.get_perc_iibb(A);
					double importe=0.0;
					try {
						importe=new Double(aux.getImporte());
					}catch(Exception e){
						
					}
					importe_percepcion_iibb+=importe;
					
				}
				
				if (this.tiene_retencion_ganancias(A)){
					Renglon aux=this.get_retencion_ganancias(A);
					double importe=0.0;
					try {
						importe=new Double(aux.getImporte());
					}catch(Exception e){
						
					}
					importe_retencion_ganancias+=importe;
					
				}
				if (this.tiene_gastos_tarjeta(A)){
					Renglon aux=this.get_gastos_tarjeta(A);
					double importe=0.0;
					try {
						importe=new Double(aux.getImporte());
					}catch(Exception e){
						
					}
					importe_netonogravado+=importe;
					
				}
				double importenetogravado=0.0;
				try{
					importenetogravado=A.getSubtotal();
				}catch(Exception e){
					
				}
				
				
				double liva_total=0.0;
				try{
					liva_total=new Double(A.getCabimporte().replaceAll(",", ""));
				}catch(Exception e){
					
				}
				
				if (this.esNotadeCredito(A)){
					importenetogravado=-importenetogravado;
					importe_netonogravado=-importe_netonogravado;
					liva_total=-liva_total;
					importe_retencion_ganancias=-importe_retencion_ganancias;
					importe_percepcion_iibb=-importe_percepcion_iibb;
					importe_percepcion_iva=-importe_percepcion_iva;
					importe_iva27=-importe_iva27;
					importe_iva10=-importe_iva10;
					importe_iva=-importe_iva;
					
				}
				
				R.setLiva_aliciva(""+alic_iva);
				Convertidor C=new Convertidor();
				System.out.println("alic iva?"+alic_iva);
				System.out.println("iva?"+importe_iva);
				System.out.println("iva10?"+importe_iva10);
				System.out.println("iva27?"+importe_iva27);
				
				R.setLiva_impiva(""+C.getMoney((importe_iva),2).replaceAll(",", ""));
				R.setLiva_total(""+C.getMoney((liva_total),2).replaceAll(",", ""));
				R.setLiva_exento("0.0");
				
				R.setLiva_impnetograv(""+C.getMoney(importenetogravado,2).replaceAll(",", ""));
				R.setLiva_impnetongrav(""+C.getMoney(importe_netonogravado,2).replaceAll(",", ""));
				R.setLiva_ret_ganancias(""+C.getMoney(importe_retencion_ganancias,2).replaceAll(",", ""));
				R.setLiva_ret_ibtos(""+C.getMoney(importe_percepcion_iibb,2).replaceAll(",", ""));
				R.setLiva_ret_perc(""+C.getMoney(importe_percepcion_iva,2).replaceAll(",", ""));
				R.setLiva_aliciva2("0.105");
				R.setLiva_impiva2(""+C.getMoney(importe_iva10,2).replaceAll(",", ""));
				R.setLiva_aliciva3("0.27");
				R.setLiva_impiva3(""+C.getMoney(importe_iva27,2).replaceAll(",", ""));
			}
		}
	}
}
