package aplicacion.herramientas.java.visualselector.logic;

public class Filtro {
	private String nombre = "";
	private String alias = "";
	private String valor = "";
	private String initial_value = "";
	private int width = 120;
	private boolean focus=false;
	private boolean quecontenga=false;
	private boolean quecomience=false;
	private boolean quetermine=false;
	
	private Class tipo = String.class;

	public void setQueContenga(boolean b){
		this.quecontenga=b;
	}
	
	public void setFocus(boolean focus){
		this.focus=focus;
	}
	
	public boolean hasFocus(){
		return this.focus;
	}
	public boolean getQueContenga(){
		return this.quecontenga;
	}
	
	public void setQueEmpiece(boolean b){
		this.quecomience=b;
	}
	
	public boolean getQueEmpiece(){
		return this.quecomience;
	}
	public void setQueTermine(boolean b){
		this.quetermine=b;
	}
	
	public boolean getQueTermine(){
		return this.quetermine;
	}
	public void setTipo(Class tipo) {
		this.tipo = tipo;
	}

	public Class getTipo() {
		return this.tipo;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setInitialValue(String ini) {
		this.initial_value = ini;
	}

	public String getInitialValue() {
		return this.initial_value;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
		if (alias.compareTo("") == 0) {
			this.alias = nombre;
		}
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setWidth(int w) {
		this.width = w;
	}

	public int getWidth() {
		return this.width;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return this.valor;
	}
}
