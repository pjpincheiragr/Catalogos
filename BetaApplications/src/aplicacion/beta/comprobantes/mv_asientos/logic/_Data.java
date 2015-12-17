package aplicacion.beta.comprobantes.mv_asientos.logic;
import aplicacion.beta.comprobantes.mv_asientos.interfaces._Interface;
import aplicacion.beta.comprobantes.mv_asientos.objetos.Asiento;
import aplicacion.beta.comprobantes.mv_asientos.objetos.Renglon;
import aplicacion.modelo.logic.Data;
import aplicacion.herramientas.java.*;
import java.util.*;

public class _Data extends Data{

	public boolean existe(Asiento A){
		boolean existe=false;
		String[] parameters=new String[]{
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
					A.getMes_operativo(),
					A.getNumero_asiento(),
					secuencia,
					A.getDetalle(),
					A.getFecha(),
					R.getCuenta(),
					R.getCuenta_nombre(),
					A.getObservacion(),
					A.getTc(),
					A.getIdcomprobante(),
					R.getDebe_haber(),
					R.getImporte(),
					R.getIdcajas(),
					R.getCotizacion()
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
					A.getMes_operativo(),
					A.getNumero_asiento(),
					secuencia,
					A.getDetalle(),
					A.getFecha(),
					R.getCuenta(),
					R.getCuenta_nombre(),
					A.getObservacion(),
					A.getTc(),
					A.getIdcomprobante(),
					R.getDebe_haber(),
					R.getImporte(),
					R.getIdcajas(),
					R.getCotizacion()
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
		A.normalizeToBetaDefaults();
		this.beginTransaction();
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
					this.rollbackTransaction();
				}else{
					this.commitTransaction();
				}

			}else{
				System.out.println("el asiento es incorrecto");
				this.rollbackTransaction();
			}
			
		}else {
			
			if (this.esCorrecto(A)){		
				if (existe(A)){
					ok=this.actualizar(A);	
					if (ok){
						this.commitTransaction();
					}else{
						this.rollbackTransaction();
					}
				}else {
					ok=this.insertar(A);
					if (ok){
						this.commitTransaction();
					}else{
						this.rollbackTransaction();
					}
				}
			}
			
		}
		System.out.println("GRABACION ASIENTO ["+A.getPeriodo()+"."+A.getMes_operativo()+"."+A.getNumero_asiento()+"] = "+ok);
		return ok;
	}
	
	public List<String> getInstrucciones(Asiento A){
		List<String> instrucciones=new ArrayList<String>();
			A.normalizeToBetaDefaults();
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
					A.getMes_operativo(),
					A.getNumero_asiento(),
					secuencia,
					A.getDetalle(),
					A.getFecha(),
					R.getCuenta(),
					R.getCuenta_nombre(),
					A.getObservacion(),
					A.getTc(),
					A.getIdcomprobante(),
					R.getDebe_haber(),
					R.getImporte(),
					R.getIdcajas(),
					R.getCotizacion()
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
					A.getMes_operativo(),
					A.getNumero_asiento(),
					secuencia,
					A.getDetalle(),
					A.getFecha(),
					R.getCuenta(),
					R.getCuenta_nombre(),
					A.getObservacion(),
					A.getTc(),
					A.getIdcomprobante(),
					R.getDebe_haber(),
					R.getImporte(),
					R.getIdcajas(),
					R.getCotizacion()
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
				
				String mes=(String)results[0][0];
				String numero=(String)results[0][1];
				Integer _numero=new Integer(numero);
				System.out.println("asiento ultimo:"+_numero);
				_numero++;
				numero=""+_numero;
				A.setPeriodo("");
				A.setMes_operativo(mes);
				System.out.println("asiento actual:"+_numero);
				A.setNumero_asiento(numero);
			}
		}
		if (!ok){
			String mes=new Convertidor().ConvertDate("M", "dd-MM-yyyy", A.getFecha());
			A.setMes_operativo(mes);
			int numero=1;
			A.setNumero_asiento(""+numero);
			while (A.existe()){
				numero++;
				A.setNumero_asiento(""+numero);	
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
	
	
	
	public Renglon get_gastos_tarjeta(Asiento A){
	return this.getRenglon(A, "42301");	
	}
	
	
	
	private boolean esNotadeCredito(Asiento A){
		boolean nc=false;
		nc=(A.getTc().compareTo("NCC")==0 |
				A.getTc().compareTo("NC")==0);
		return nc;
	}
	
}
