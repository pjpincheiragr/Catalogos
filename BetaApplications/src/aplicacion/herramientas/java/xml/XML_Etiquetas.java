package aplicacion.herramientas.java.xml;

public class XML_Etiquetas extends XML{
	
	public XML_Etiquetas(){
		super();
		this.setStartElement("etiquetas");
		
		setAtributos(new String[]{
				"hoja_margen_izquierdo",
				"hoja_margen_superior",
				"hoja_anchura",
				"hoja_longitud",
				"celda_anchura",
				"celda_longitud",
				"hoja_columnas",
				"hoja_filas",
				"hoja_espacio_columnas",
				"hoja_espacio_filas",
				"hoja_borde",
				"celda_borde",
				"print_longitud",
				"print_anchura",
				"print_margen_superior",
				"print_margen_izquierdo",
				"print_escala_x",
				"print_escala_y",
				"codigo_font_size",
				"descripcion_font_size"});
		
		
		
		
	}
}
