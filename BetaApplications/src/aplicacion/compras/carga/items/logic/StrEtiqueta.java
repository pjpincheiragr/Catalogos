package aplicacion.compras.carga.items.logic;

public class StrEtiqueta {
	private String codigo="";
	private String descripcion="";
	private int cantidad=0;
	private boolean especial=false;
	public int width=40;
	public boolean quitar_prefijo=false;
	public boolean isQuitar_prefijo() {
		return quitar_prefijo;
	}

	public void setQuitar_prefijo(boolean quitar_prefijo) {
		this.quitar_prefijo = quitar_prefijo;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	public int height=40;
	
		public boolean isEspecial() {
		return especial;
	}

	public void setEspecial(boolean especial) {
		this.especial = especial;
	}

		public void StrEtiqueta(){
			codigo="";
			descripcion="";
			cantidad=0;
			especial=false;
		}
		
		public void setCodigo(String codigo){
			this.codigo=codigo;
		}
		
		public void setDescripcion(String descripcion){
			this.descripcion=descripcion;
		}
		
		public void setCantidad(int cantidad){
			this.cantidad=cantidad;
		}
		
		public String getCodigo(){
			return this.codigo;
		}
		
		public String getDescripcion(){
			return this.descripcion;
		}
		
		public int getCantidad(){
			return this.cantidad;
		}
		public boolean esVacia(){
			return this.codigo.compareTo("")==0;
		}
	}