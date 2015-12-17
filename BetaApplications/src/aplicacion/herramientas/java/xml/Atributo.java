package aplicacion.herramientas.java.xml;

public class Atributo {
	private String nombre;
	private Class tipo;
	private String modo;
	private String modo_metodo="public";
	private String valor="";
	private boolean primitive=false;
	private boolean correcto =true;
	
	public Atributo(String nombre){
		
		if (nombre.indexOf(" ")>=0|nombre.indexOf("-")>=0){
			nombre=nombre.replace(" ", "_");
			nombre=nombre.replace("-", "_");
			correcto=false;
		}
		this.nombre=nombre;
	}

	public void setTipo(Class tipo){
		this.tipo=tipo;
		if (tipo==boolean.class | tipo==byte.class | tipo==char.class | tipo==short.class 
			| tipo==int.class | tipo==long.class | tipo==float.class | tipo==double.class ){
			primitive=true;
		}
	}
	
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	
	public void setModo(String modo){
		this.modo=modo;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	public Class getTipo(){
		return tipo;
	}
	public String getModo(){
		return modo;
	}
	
	public String getJavaVariableDeclaration(){
		String declaration="";
		declaration+=this.modo+" "+this.tipo.getSimpleName().toString()+" ";
		declaration+=this.nombre+";\n";
		return declaration;
	}
	
	public String getJavaGetMetohd(){
		String method="";
		String method_name=this.nombre.substring(0, 1).toUpperCase()+this.nombre.substring(1, this.nombre.length());
		method+=this.modo_metodo+" "+this.tipo.getSimpleName().toString();
		method+=" get"+method_name+"(){\n";
		method+=" return this."+this.nombre+";\n";
		method+="}";
		return method;
	}
	
	public boolean isPrimitive(){
		return this.primitive;
	}
	public boolean esCorrecto(){
		return this.correcto;
	}
	
	public String getJavaSetMetohd(){
		String method="";
		String method_name=this.nombre.substring(0, 1).toUpperCase()+this.nombre.substring(1, this.nombre.length());
		method+=this.modo_metodo+" void";
		method+=" set"+method_name+"("+this.tipo.getSimpleName().toString()+" "+this.nombre+"){\n";
		method+=" this."+this.nombre+"="+this.nombre+";\n";
		method+="}";
		return method;
	}
	
	public void setValor(String valor){
		if (valor==null){
			valor="";
		}
		valor=valor.replaceAll("\"","&quot;");
		valor=valor.replaceAll("<","&lt;");
		valor=valor.replaceAll(">","&gt;");
		//System.out.println(this.getNombre()+"="+valor);
		this.valor=valor;
	}
	public String getValor(){
		return this.getValor(false);
	}
	public String getValor(boolean transform){
		String tmp=valor;
		if (tmp!=null){
			if (transform){
				tmp=tmp.replaceAll("&quot;","\"");
				tmp=tmp.replaceAll("&lt;","<");
				tmp=tmp.replaceAll("&gt;",">");	
			}
				
		}
		
		return tmp;
	}
}
