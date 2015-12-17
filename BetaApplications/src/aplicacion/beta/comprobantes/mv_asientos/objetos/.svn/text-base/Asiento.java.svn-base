package aplicacion.beta.comprobantes.mv_asientos.objetos;
import java.util.*;

import aplicacion.beta.comprobantes.mv_asientos.logic._Data;
import java.text.*;
import aplicacion.herramientas.conexion.*;
public class Asiento {
	private _Data  data=null;
	private String observacion="";
	
	private LinkedList Renglones=null;

	public void addRenglon(Renglon renglon){
		Renglones.add(renglon);
	}
	
	public Renglon getRenglon(int i)
	{
		Renglon renglon=null;
		if (i<Renglones.size() & i>=0){
			renglon=(Renglon) Renglones.get(i);
		}
		return renglon;
	}
	
	public Asiento(ConnectionHandler sql){
		Renglones=new LinkedList();
		data=new _Data();
		data.setSql(sql);
	}
	
	private void initialize(){
		
		this.cotizacion="1.0";
		
	}
	
	public int getRenglones(){
		return Renglones.size();
	}
	
	public boolean balancea(){
		double sum_debe=0.0;
		double sum_haber=0.0;
		for (int i=0;i<Renglones.size();i++){
			Renglon renglon=(Renglon) Renglones.get(i);
			String debe_haber=renglon.getDebe_haber();
			String _importe=renglon.getImporte();
			_importe=_importe.replace(",", "");
			double importe=new Double(_importe).doubleValue();
			
			

			if (debe_haber.toLowerCase().compareTo("d")==0){
				sum_debe+=importe;	
			}else {
				sum_haber+=importe;
			}
		}
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		sum_debe= Double.valueOf((twoDForm.format(sum_debe)).replaceAll(",", ""));
		sum_haber= Double.valueOf((twoDForm.format(sum_haber)).replaceAll(",", ""));
		boolean ok=(sum_debe-sum_haber)==0;
		System.out.println("balancea ?"+sum_debe+"<>"+sum_haber+"="+ok);
		return ok;
	}
	
	public boolean existe(){
		boolean existe=false;
		existe=data.existe(this);	
		return existe;
	}
	
	public boolean grabar(){
		boolean ok=false;
		ok=data.grabar(this);	
		return ok;
	}
	
	public List<String> getInstrucciones(){
		return data.getInstrucciones(this);
	}
	
	public boolean eliminar(){
		boolean ok=false;
		ok=data.eliminar(this);
		return ok;
	}

	private double calculateCabImporte(){
		double sum_debe=0.0;
		double sum_haber=0.0;
		for (int i=0;i<Renglones.size();i++){
			Renglon renglon=(Renglon) Renglones.get(i);
			String debe_haber=renglon.getDebe_haber();
			String _importe=renglon.getImporte();
			double importe=new Double(_importe);
			if (debe_haber.toLowerCase().compareTo("d")==0){
				sum_debe+=importe;	
			}else {
				sum_haber+=importe;
			}
		}
		return sum_debe;
		
	}
	
	
	private String fecha=null;
	private String periodo=null;
	private String mes_operativo=null;
	private String numero_asiento=null;
	private String detalle=null; 
	private String tc=null; 
	 
	private String idcomprobante="";
	 
	public String getIdcomprobante() {
		return idcomprobante;
	}

	public void setIdcomprobante(String idcomprobante) {
		this.idcomprobante = idcomprobante;
	}


	private String cotizacion=null; 
		
	public _Data getData() {
		return data;
	}

	public void setData(_Data data) {
		this.data = data;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getMes_operativo() {
		return mes_operativo;
	}

	public void setMes_operativo(String mes_operativo) {
		this.mes_operativo = mes_operativo;
	}

	public String getNumero_asiento() {
		return numero_asiento;
	}

	public void setNumero_asiento(String numero_asiento) {
		this.numero_asiento = numero_asiento;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getTc() {
		return tc;
	}

	public void setTc(String tc) {
		this.tc = tc;
	}

	
	public String getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
	}

	
	public void setRenglones(LinkedList renglones) {
		Renglones = renglones;
	}
	
	public void normalizeToBetaDefaults(){
		
			    
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	
	
}
