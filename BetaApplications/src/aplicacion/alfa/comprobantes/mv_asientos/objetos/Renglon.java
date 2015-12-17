package aplicacion.alfa.comprobantes.mv_asientos.objetos;

public class Renglon {
	private String cuenta =null; 
	private String secuecia=null;
	private String debe_haber=null; 
	private String vencimiento=null; 
	private String importe=null; 
	private String nrocomprobantebancario=null; 
	private String cabcuenta=null; 
	private String cabnombre=null; 
	private String cabcuit=null; 
	private String cabcondiva=null; 
	private String liva_tipo=null; 
	private String liva_impnetograv=null; 
	private String liva_impnetongrav=null; 
	private String liva_exento=null;
	private String liva_aliciva=null; 
	private String liva_alicivarec=null; 
	private String liva_impiva=null; 
	private String liva_impivarec=null; 
	private String liva_ret_perc=null; 
	private String liva_ret_ibtos=null; 
	private String liva_ret_ganancias=null; 
	private String liva_total=null; 
	private String clearing=null; 
	private String idbanco=null; 
	private String chpropio=null; 
	private String tj_nro_socio=null; 
	private String tj_nrocupon=null; 
	private String tj_cuotas=null; 
	private String tj_autorizo=null; 
	private String tj_observaciones=null; 
	private String cuentaorigen=null; 
	private String cuentadestino=null; 
	private String liva_aliciva2=null; 
	private String liva_impiva2=null; 
	private String liva_aliciva3=null; 
	private String liva_impiva3=null; 
	private String liva_aliciva4=null; 
	private String liva_impiva4=null; 
	private String tj_presentacion=null;
	private String tj_liquidacion=null;
	private String idcajas=null;
	
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getSecuecia() {
		return secuecia;
	}
	public void setSecuecia(String secuecia) {
		this.secuecia = secuecia;
	}
	public String getDebe_haber() {
		return debe_haber;
	}
	public void setDebe_haber(String debe_haber) {
		this.debe_haber = debe_haber;
	}
	public String getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getNrocomprobantebancario() {
		return nrocomprobantebancario;
	}
	public void setNrocomprobantebancario(String nrocomprobantebancario) {
		this.nrocomprobantebancario = nrocomprobantebancario;
	}
	public String getCabcuenta() {
		return cabcuenta;
	}
	public void setCabcuenta(String cabcuenta) {
		this.cabcuenta = cabcuenta;
	}
	public String getCabnombre() {
		return cabnombre;
	}
	public void setCabnombre(String cabnombre) {
		this.cabnombre = cabnombre;
	}
	public String getCabcuit() {
		return cabcuit;
	}
	public void setCabcuit(String cabcuit) {
		this.cabcuit = cabcuit;
	}
	public String getCabcondiva() {
		return cabcondiva;
	}
	public void setCabcondiva(String cabcondiva) {
		this.cabcondiva = cabcondiva;
	}
	public String getLiva_tipo() {
		return liva_tipo;
	}
	public void setLiva_tipo(String liva_tipo) {
		this.liva_tipo = liva_tipo;
	}
	public String getLiva_impnetograv() {
		return liva_impnetograv;
	}
	public void setLiva_impnetograv(String liva_impnetograv) {
		this.liva_impnetograv = liva_impnetograv;
	}
	public String getLiva_impnetongrav() {
		return liva_impnetongrav;
	}
	public void setLiva_impnetongrav(String liva_impnetongrav) {
		this.liva_impnetongrav = liva_impnetongrav;
	}
	public String getLiva_exento() {
		return liva_exento;
	}
	public void setLiva_exento(String liva_exento) {
		this.liva_exento = liva_exento;
	}
	public String getLiva_aliciva() {
		return liva_aliciva;
	}
	public void setLiva_aliciva(String liva_aliciva) {
		System.out.println("RENGLON SET ALICIVA>"+liva_aliciva);
		this.liva_aliciva = liva_aliciva;
	}
	public String getLiva_alicivarec() {
		return liva_alicivarec;
	}
	public void setLiva_alicivarec(String liva_alicivarec) {
		this.liva_alicivarec = liva_alicivarec;
	}
	public String getLiva_impiva() {
		return liva_impiva;
	}
	public void setLiva_impiva(String liva_impiva) {
		this.liva_impiva = liva_impiva;
	}
	public String getLiva_impivarec() {
		return liva_impivarec;
	}
	public void setLiva_impivarec(String liva_impivarec) {
		this.liva_impivarec = liva_impivarec;
	}
	public String getLiva_ret_perc() {
		return liva_ret_perc;
	}
	public void setLiva_ret_perc(String liva_ret_perc) {
		this.liva_ret_perc = liva_ret_perc;
	}
	public String getLiva_ret_ibtos() {
		return liva_ret_ibtos;
	}
	public void setLiva_ret_ibtos(String liva_ret_ibtos) {
		this.liva_ret_ibtos = liva_ret_ibtos;
	}
	public String getLiva_ret_ganancias() {
		return liva_ret_ganancias;
	}
	public void setLiva_ret_ganancias(String liva_ret_ganancias) {
		this.liva_ret_ganancias = liva_ret_ganancias;
	}
	public String getLiva_total() {
		return liva_total;
	}
	public void setLiva_total(String liva_total) {
		this.liva_total = liva_total;
	}
	public String getClearing() {
		return clearing;
	}
	public void setClearing(String clearing) {
		this.clearing = clearing;
	}
	public String getIdbanco() {
		return idbanco;
	}
	public void setIdbanco(String idbanco) {
		this.idbanco = idbanco;
	}
	public String getChpropio() {
		return chpropio;
	}
	public void setChpropio(String chpropio) {
		this.chpropio = chpropio;
	}
	public String getTj_nro_socio() {
		return tj_nro_socio;
	}
	public void setTj_nro_socio(String tj_nro_socio) {
		this.tj_nro_socio = tj_nro_socio;
	}
	public String getTj_nrocupon() {
		return tj_nrocupon;
	}
	public void setTj_nrocupon(String tj_nrocupon) {
		this.tj_nrocupon = tj_nrocupon;
	}
	public String getTj_cuotas() {
		return tj_cuotas;
	}
	public void setTj_cuotas(String tj_cuotas) {
		this.tj_cuotas = tj_cuotas;
	}
	public String getTj_autorizo() {
		return tj_autorizo;
	}
	public void setTj_autorizo(String tj_autorizo) {
		this.tj_autorizo = tj_autorizo;
	}
	public String getTj_observaciones() {
		return tj_observaciones;
	}
	public void setTj_observaciones(String tj_observaciones) {
		this.tj_observaciones = tj_observaciones;
	}
	public String getCuentaorigen() {
		return cuentaorigen;
	}
	public void setCuentaorigen(String cuentaorigen) {
		this.cuentaorigen = cuentaorigen;
	}
	public String getCuentadestino() {
		return cuentadestino;
	}
	public void setCuentadestino(String cuentadestino) {
		this.cuentadestino = cuentadestino;
	}
	public String getLiva_aliciva2() {
		return liva_aliciva2;
	}
	public void setLiva_aliciva2(String liva_aliciva2) {
		this.liva_aliciva2 = liva_aliciva2;
	}
	public String getLiva_impiva2() {
		return liva_impiva2;
	}
	public void setLiva_impiva2(String liva_impiva2) {
		this.liva_impiva2 = liva_impiva2;
	}
	public String getLiva_aliciva3() {
		return liva_aliciva3;
	}
	public void setLiva_aliciva3(String liva_aliciva3) {
		this.liva_aliciva3 = liva_aliciva3;
	}
	public String getLiva_impiva3() {
		return liva_impiva3;
	}
	public void setLiva_impiva3(String liva_impiva3) {
		this.liva_impiva3 = liva_impiva3;
	}
	public String getLiva_aliciva4() {
		return liva_aliciva4;
	}
	public void setLiva_aliciva4(String liva_aliciva4) {
		this.liva_aliciva4 = liva_aliciva4;
	}
	public String getLiva_impiva4() {
		return liva_impiva4;
	}
	public void setLiva_impiva4(String liva_impiva4) {
		this.liva_impiva4 = liva_impiva4;
	}
	public String getTj_presentacion() {
		return tj_presentacion;
	}
	public void setTj_presentacion(String tj_presentacion) {
		this.tj_presentacion = tj_presentacion;
	}
	public String getTj_liquidacion() {
		return tj_liquidacion;
	}
	public void setTj_liquidacion(String tj_liquidacion) {
		this.tj_liquidacion = tj_liquidacion;
	}
	public String getIdcajas() {
		return idcajas;
	}
	public void setIdcajas(String idcajas) {
		this.idcajas = idcajas;
	}
	
}
