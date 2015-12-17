package aplicacion.contabilidad.asiento.modelo.objetos;
import java.util.*;
import aplicacion.contabilidad.asiento.modelo.logic.*;
import aplicacion.herramientas.conexion.*;

public class Asiento {
	private String detalle="";
	private String fecha="";
	private String mes="";
	private String numero="";
	private String periodo="";
	private _Data  data=null;
	
	private LinkedList Renglones=null;

	
	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	
	public void addRenglon(Renglon renglon){
		Renglones.add(renglon);
	}
	
	public Renglon getRenglon(int i){
		Renglon renglon=null;
		if (i<Renglones.size() & i>=0){
			renglon=(Renglon) Renglones.get(i);
		}
		
		return renglon;
	}
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	public Asiento(ConnectionHandler sql){
		Renglones=new LinkedList();
		data=new _Data();
		data.setSql(sql);
	}
	
	public int getRenglones(){
		return Renglones.size();
	}
	
	public boolean balancea(){
		double sum_debe=0.0;
		double sum_haber=0.0;
		for (int i=0;i<Renglones.size();i++){
			Renglon renglon=(Renglon) Renglones.get(i);
			sum_debe+=renglon.getDebe();
			sum_haber+=renglon.getHaber();
		}
		return (sum_debe-sum_haber)==0;
	}
	
	public boolean existe(){
		boolean existe=false;
		if (this.mes.compareTo("")!=0
		& this.periodo.compareTo("")!=0
		& this.numero.compareTo("")!=0
		){
			existe=data.existe(this);	
		}
		
		return existe;
	}
	
	public boolean grabar(){
		boolean ok=false;
		if (this.mes.compareTo("")!=0
			& this.periodo.compareTo("")!=0
			& this.numero.compareTo("")!=0
			){
			ok=data.grabar(this);
		}else {
			ok=data.cargar_idnuevo(this);
			if (ok){
				ok=data.grabar(this);	
			}
		}
		return ok;
	}
	
	private void cargar_idnuevo(){
		
	}
	public boolean eliminar(){
		boolean ok=false;
		if (this.mes.compareTo("")!=0
				& this.periodo.compareTo("")!=0
				& this.numero.compareTo("")!=0
				){
			ok=data.eliminar(this);
		}
		return ok;
	}

	
}
