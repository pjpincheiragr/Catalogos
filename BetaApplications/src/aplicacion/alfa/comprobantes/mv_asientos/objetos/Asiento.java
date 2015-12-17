package aplicacion.alfa.comprobantes.mv_asientos.objetos;
import java.util.*;

import aplicacion.alfa.comprobantes.mv_asientos.logic._Data;
import java.text.*;
import aplicacion.herramientas.conexion.*;
public class Asiento {
	private _Data  data=null;
	
	
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
		this.moneda="   1";
		this.cotizacion="1.0";
		this.id_cotiz="0";
		this.tipo_reg="0";
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
	private String sucursal=null; 
	private String numero=null; 
	private String letra=null; 
	private String moneda=null; 
	private String cotizacion=null; 
	private String id_cotiz=null; 
	private String cabimporte=null; 
	private String tipo_reg=null; 
	private String contabilizado=null; 
	private String fechahora_grabacion=null; 
	private String fechahora_modificacion=null; 
	private String esresumen=null; 
	private String es_saldo_apertura=null; 
	private String esajuste=null; 
	private String ajuste_hasta=null; 
	private String nro_transmision=null; 
	private String ordendecompra=null; 
	private String idvendedor=null; 
	private String fechasubdiario=null; 
	private String escierre=null; 
	private String cod_destino=null; 
	private String nroliquneg=null; 
	private String idmotivo=null; 
	private String codcptedgi=null; 
	private String nrocai=null; 
	private String fhvtocai=null; 
	private String estado=null; 
	private String tc_origen=null; 
	private String cpte_origen=null; 
	private String complemento_origen=null; 
	private String fechapresentacion=null; 
	private String fechavtopresentacion=null; 
	private String transmision=null; 
	private String recepcion=null; 
	private String sucursalcuenta=null; 
	private String conciliado=null; 
	private String unegocio=null; 
	private String liva_aliciva1terceros=null; 
	private String liva_impiva1terceros=null; 
	private String liva_aliciva2terceros=null; 
	private String liva_impiva2terceros=null; 
	private String liva_imptotal1terceros=null; 
	private String liva_imptotal2terceros=null; 
	
	private String liva_tc=null; 
	private String liva_suc=null; 
	private String liva_nro=null; 
	private String liva_let=null; 
	private String liva_itc=null;

	private String liva_exento="";
	private double subtotal=0.0;
	
	public void setSubtotal(double subtotal){
		this.subtotal=subtotal;
	}
	
	public double getSubtotal(){
		return this.subtotal;
	}
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

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
	}

	public String getId_cotiz() {
		return id_cotiz;
	}

	public void setId_cotiz(String id_cotiz) {
		this.id_cotiz = id_cotiz;
	}

	public String getCabimporte() {
		return cabimporte;
	}

	public void setCabimporte(String cabimporte) {
		this.cabimporte = cabimporte;
	}

	public String getTipo_reg() {
		return tipo_reg;
	}

	public void setTipo_reg(String tipo_reg) {
		this.tipo_reg = tipo_reg;
	}

	public String getContabilizado() {
		return contabilizado;
	}

	public void setContabilizado(String contabilizado) {
		this.contabilizado = contabilizado;
	}

	public String getFechahora_grabacion() {
		return fechahora_grabacion;
	}

	public void setFechahora_grabacion(String fechahora_grabacion) {
		this.fechahora_grabacion = fechahora_grabacion;
	}

	public String getFechahora_modificacion() {
		return fechahora_modificacion;
	}

	public void setFechahora_modificacion(String fechahora_modificacion) {
		this.fechahora_modificacion = fechahora_modificacion;
	}

	public String getEsresumen() {
		return esresumen;
	}

	public void setEsresumen(String esresumen) {
		this.esresumen = esresumen;
	}

	public String getEs_saldo_apertura() {
		return es_saldo_apertura;
	}

	public void setEs_saldo_apertura(String es_saldo_apertura) {
		this.es_saldo_apertura = es_saldo_apertura;
	}

	public String getEsajuste() {
		return esajuste;
	}

	public void setEsajuste(String esajuste) {
		this.esajuste = esajuste;
	}

	public String getAjuste_hasta() {
		return ajuste_hasta;
	}

	public void setAjuste_hasta(String ajuste_hasta) {
		this.ajuste_hasta = ajuste_hasta;
	}

	public String getNro_transmision() {
		return nro_transmision;
	}

	public void setNro_transmision(String nro_transmision) {
		this.nro_transmision = nro_transmision;
	}

	public String getOrdendecompra() {
		return ordendecompra;
	}

	public void setOrdendecompra(String ordendecompra) {
		this.ordendecompra = ordendecompra;
	}

	public String getIdvendedor() {
		return idvendedor;
	}

	public void setIdvendedor(String idvendedor) {
		this.idvendedor = idvendedor;
	}

	public String getFechasubdiario() {
		return fechasubdiario;
	}

	public void setFechasubdiario(String fechasubdiario) {
		this.fechasubdiario = fechasubdiario;
	}

	public String getEscierre() {
		return escierre;
	}

	public void setEscierre(String escierre) {
		this.escierre = escierre;
	}

	public String getCod_destino() {
		return cod_destino;
	}

	public void setCod_destino(String cod_destino) {
		this.cod_destino = cod_destino;
	}

	public String getNroliquneg() {
		return nroliquneg;
	}

	public void setNroliquneg(String nroliquneg) {
		this.nroliquneg = nroliquneg;
	}

	public String getIdmotivo() {
		return idmotivo;
	}

	public void setIdmotivo(String idmotivo) {
		this.idmotivo = idmotivo;
	}

	public String getCodcptedgi() {
		return codcptedgi;
	}

	public void setCodcptedgi(String codcptedgi) {
		this.codcptedgi = codcptedgi;
	}

	public String getNrocai() {
		return nrocai;
	}

	public void setNrocai(String nrocai) {
		this.nrocai = nrocai;
	}

	public String getFhvtocai() {
		return fhvtocai;
	}

	public void setFhvtocai(String fhvtocai) {
		this.fhvtocai = fhvtocai;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTc_origen() {
		return tc_origen;
	}

	public void setTc_origen(String tc_origen) {
		this.tc_origen = tc_origen;
	}

	public String getCpte_origen() {
		return cpte_origen;
	}

	public void setCpte_origen(String cpte_origen) {
		this.cpte_origen = cpte_origen;
	}

	public String getComplemento_origen() {
		return complemento_origen;
	}

	public void setComplemento_origen(String complemento_origen) {
		this.complemento_origen = complemento_origen;
	}

	public String getFechapresentacion() {
		return fechapresentacion;
	}

	public void setFechapresentacion(String fechapresentacion) {
		this.fechapresentacion = fechapresentacion;
	}

	public String getFechavtopresentacion() {
		return fechavtopresentacion;
	}

	public void setFechavtopresentacion(String fechavtopresentacion) {
		this.fechavtopresentacion = fechavtopresentacion;
	}

	public String getTransmision() {
		return transmision;
	}

	public void setTransmision(String transmision) {
		this.transmision = transmision;
	}

	public String getRecepcion() {
		return recepcion;
	}

	public void setRecepcion(String recepcion) {
		this.recepcion = recepcion;
	}

	public String getSucursalcuenta() {
		return sucursalcuenta;
	}

	public void setSucursalcuenta(String sucursalcuenta) {
		this.sucursalcuenta = sucursalcuenta;
	}

	public String getConciliado() {
		return conciliado;
	}

	public void setConciliado(String conciliado) {
		this.conciliado = conciliado;
	}

	public String getUnegocio() {
		return unegocio;
	}

	public void setUnegocio(String unegocio) {
		this.unegocio = unegocio;
	}

	public String getLiva_aliciva1terceros() {
		return liva_aliciva1terceros;
	}

	public void setLiva_aliciva1terceros(String liva_aliciva1terceros) {
		this.liva_aliciva1terceros = liva_aliciva1terceros;
	}

	public String getLiva_impiva1terceros() {
		return liva_impiva1terceros;
	}

	public void setLiva_impiva1terceros(String liva_impiva1terceros) {
		this.liva_impiva1terceros = liva_impiva1terceros;
	}

	public String getLiva_aliciva2terceros() {
		return liva_aliciva2terceros;
	}

	public void setLiva_aliciva2terceros(String liva_aliciva2terceros) {
		this.liva_aliciva2terceros = liva_aliciva2terceros;
	}

	public String getLiva_impiva2terceros() {
		return liva_impiva2terceros;
	}

	public void setLiva_impiva2terceros(String liva_impiva2terceros) {
		this.liva_impiva2terceros = liva_impiva2terceros;
	}

	public String getLiva_imptotal1terceros() {
		return liva_imptotal1terceros;
	}

	public void setLiva_imptotal1terceros(String liva_imptotal1terceros) {
		this.liva_imptotal1terceros = liva_imptotal1terceros;
	}

	public String getLiva_imptotal2terceros() {
		return liva_imptotal2terceros;
	}

	public void setLiva_imptotal2terceros(String liva_imptotal2terceros) {
		this.liva_imptotal2terceros = liva_imptotal2terceros;
	}

	public String getLiva_tc() {
		return liva_tc;
	}

	public void setLiva_tc(String liva_tc) {
		this.liva_tc = liva_tc;
	}

	public String getLiva_suc() {
		return liva_suc;
	}

	public void setLiva_suc(String liva_suc) {
		this.liva_suc = liva_suc;
	}

	public String getLiva_nro() {
		return liva_nro;
	}

	public void setLiva_nro(String liva_nro) {
		this.liva_nro = liva_nro;
	}

	public String getLiva_let() {
		return liva_let;
	}

	public void setLiva_let(String liva_let) {
		this.liva_let = liva_let;
	}

	public String getLiva_itc() {
		return liva_itc;
	}

	public void setLiva_itc(String liva_itc) {
		this.liva_itc = liva_itc;
	}

	public void setRenglones(LinkedList renglones) {
		Renglones = renglones;
	}
	
	public void normalizeToAlfaDefaults(){
		double cabimporte=this.calculateCabImporte();
		this.setCabimporte(""+cabimporte);
	    this.setMoneda("   1");
	    this.setTipo_reg("    ");
	    
	}

	
	
}
