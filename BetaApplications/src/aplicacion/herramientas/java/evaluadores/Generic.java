package aplicacion.herramientas.java.evaluadores;

import javax.swing.JTextField;

import aplicacion.modelo.constructor.Constructor;

public class Generic {
	protected Constructor constructor;
	private boolean done=true;
	private boolean numeric_code=true;
	private String idconector="";
	
	
	public void dispose(){
		
	}
	
	public void setIdConnector(String id){
		this.idconector=id;
	}
	public boolean isNumeric_code() {
		return numeric_code;
	}
	public void setNumeric_code(boolean numeric_code) {
		this.numeric_code = numeric_code;
	}
	private boolean isDone(){
		return done;
	}
	public Constructor getConstructor() {
		return constructor;
	}
	public void setConstructor(Constructor c) {
		constructor = c;
	}

	public String getQuery(String id){
		String q="";
		return q;
	}
	
	public boolean existe(String codigo){
		boolean existe=false;
		String q=this.getQuery(codigo);
		Object[][] results=null;
		if (this.idconector.compareTo("")!=0){
			results=constructor.getConnectionHandler().getConnector(idconector).getResults(q);
		}else{
			
			results=constructor.getConnectionHandler().getDefaultConnector().getResults(q);	
		}
		
		
		if (results!=null){
			if (results.length>0){
				existe=true;
			}
		}
		return existe;
	}
	
	public void cargar(JTextField tx){
		this.cargar(tx.getText());
	}
	public boolean evaluate(JTextField tx){
		String codigo=tx.getText();
		String aux=tx.getText();
		boolean band=true;
		
			if (aux.compareTo("")!=0){
				if (numeric_code){
					if (Character.isLetter(aux.charAt(0))){
						this.buscar(tx);
					}else{
						if (this.existe(codigo)){
							cargar(tx);
						}else{
							constructor.getLogic().aviso(this.getAviso());	
							band=false;
						}
					}	
				}else{
					if (this.existe(codigo)){
						cargar(tx);
					}else{
						constructor.getLogic().aviso(this.getAviso());
						band=false;
					}
				}
				
			}else{ 
				this.buscar(tx);
			}
		return band;
	}
	
	public String getAviso(){
		String aviso="No hay Entidades con ese codigo";
		return aviso;
	}
	/**
	 * Aca tiene que ir el visualizador de la entidad
	 * @param tx
	 */
	public void buscar(JTextField tx) {
	
	}
	/**
	 * Este Metodo Carga datos segun sea necesario en el frame donde se utiliza esta herramienta
	 * @param codigo
	 */
	public void cargar(String codigo){
		
	}
	/**
	 * Este Metodo Carga datos segun sea necesario en el frame donde se utiliza esta herramienta
	 * @param codigo
	 */
	public void completar(String codigo){
		
	}
	/**
	 * Aca tiene que ir el Buscador de la Entidad
	 * @param ext
	 */
	public void Buscar(JTextField ext) {
	
	}
	
	
	public Object[][] getInfo(String codigo){
		String q=this.getQuery(codigo);
		Object[][] results=null;
		if (this.idconector.compareTo("")!=0){
			results=constructor.getConnectionHandler().getConnector(idconector).getResults(q);
		}else{
			results=constructor.getConnectionHandler().getDefaultConnector().getResults(q);	
		}
		
		return results;
	}
}
