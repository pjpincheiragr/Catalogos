package aplicacion.herramientas.java.importadores;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import aplicacion.herramientas.java.image.logic.ImageComponent;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.herramientas.conexion.*;
/**
 * Aplicacion para evitar tener q crear a cada rato unas 100 lineas de codigo al pedo. :-)
 * @author Agustin
 *
 */
/* Como utilizarlo? pegar este codigo en la aplicacion
private aplicacion.herramientas.java.buscadores.Articulo bArticulo=null;
public void BuscarArticulo(JTextField ext) {
	 if (bArticulo!=null){
		 bArticulo.close();
	 }
 bArticulo=new aplicacion.herramientas.java.buscadores.Articulo(this.getConstructor().getConnectionHandler());
 bArticulo.Buscar(ext);
} 
 */
import aplicacion.modelo.constructor.*;
public class Articulo extends Generico{
	private JFrame frame=null;
	private boolean suspendidov=false;
	private boolean suspendidoc=false;
	public boolean isSuspendidov() {
		return suspendidov;
	}

	public void setSuspendidov(boolean suspendidov) {
		this.suspendidov = suspendidov;
	}

	public boolean isSuspendidoc() {
		return suspendidoc;
	}

	public void setSuspendidoc(boolean suspendidoc) {
		this.suspendidoc = suspendidoc;
	}
	public Articulo(Constructor C){
		super(C);
	}

	public void cargar(Object[] selection){
		System.out.println("Cargar Seleccion de Articulo");
	}

	public void showImageInNewFrame (String idarticulo,ImageIcon icon) {
		if (frame==null){
			frame = new JFrame( );
			
		}
		Dimension d=this.getConstructor().getFrame().getSize();
		Dimension s=Toolkit.getDefaultToolkit().getScreenSize();
		double w=s.getWidth()*0.5;
		double h=s.getHeight()*0.5;
		double wf=(d.getWidth())*0.5;
		double hf=d.getHeight()*0.5;
		int x=new Double(w-wf).intValue();
		int y=new Double(h-hf).intValue();
		 
		this.getConstructor().getFrame().setLocation(x-100, y);
		
		
		x=this.getConstructor().getFrame().getLocation().x+this.getConstructor().getFrame().getWidth();
		frame.setLocation(x, y);
		frame.setTitle(idarticulo);
		frame.getContentPane( ).removeAll();
		frame.getContentPane( ).add (new JLabel (icon));
		frame.pack( );
		frame.setVisible(true);
		
		this.getConstructor().getFrame().setVisible(true);
		
		
	}
	
	public void initializeConstructor() {
		C = new aplicacion.herramientas.java.msortableselector.constructor._Constructor() {
			@Override
			protected void initialize_logic() {
				_logic = new aplicacion.herramientas.java.msortableselector.logic._Logic() {
					
					@Override
					public void add(Object[] selection) {
						cargar(selection);
					}
					public void exit_command(){
						
						if (frame!=null){
							frame.setVisible(false);	
						}
						
						super.exit_command();
					}
					
					public void DoSomethingSeleccion(JTextField tx,int row,int col,JTable table){
						System.out.println(">"+table.getValueAt(row, col).toString()+"<");
						String idarticulo=table.getValueAt(row, 1).toString();
						
						
						
						super.focus(col);
					}
					
					public Color getColor(int row,int col,JTable table){
						Color color=Color.white;
						double stock=0.0;
						String _stock=table.getValueAt(row, 4).toString();
						boolean _suspendidoc=false;
						boolean _suspendidov=false;
							if (suspendidov){
								_suspendidov=table.getValueAt(row, 6).toString().compareTo("1")==0;
								if (suspendidoc){
									_suspendidoc=table.getValueAt(row, 7).toString().compareTo("1")==0;
								}else{
									
								}
							}else{
								if (suspendidoc){
									_suspendidov=table.getValueAt(row, 6).toString().compareTo("1")==0;
								}
							}
						
						if (_stock!=null){
							if (_stock.compareTo("")!=0){
								stock=new Double(_stock.replaceAll(",", ""));
							}
						}
						//System.out.println("Stock>"+_stock);
						if (stock>0){
							//System.out.println("Stock>"+stock);
							color=Color.GREEN;
						}
						if (_suspendidoc|_suspendidov){
							color=Color.RED;
						}
						return color;
					}
					
					public void editar(int row,int col,JTable table){
						String idarticulo=table.getValueAt(row, 1).toString();
						if (idarticulo!=null){
							if (idarticulo.compareTo("")!=0){
								goMa_Articulos(idarticulo);
							}
						}
					}
				};
			}
		};
		
		this.getConstructor().addChild(C);
	}
	
	public void initializeLogic(JTextField tx){
		aplicacion.herramientas.java.msortableselector.logic._Logic logic = (aplicacion.herramientas.java.msortableselector.logic._Logic) C
				.getLogic();
		logic.setCaller(tx);
		columna c = new columna();
		Filtro f = new Filtro();
		c = new columna();
		c.setNombre("v.idarticulo");
		c.setAlias("idarticulo");
		c.setColumnField(tx);
		c.setWidth(120);
		c.setMaster(true);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("v.descripcion");
		c.setAlias("descripcion");
		c.setWidth(300);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("v.descripabrev");
		c.setAlias("linea");
		c.setWidth(140);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("sum(isnull(s.cantidadud,0))");
		c.setAlias("stock");
		c.setWidth(140);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("v.precio2");
		c.setAlias("Publico");
		c.setWidth(120);
		c.setMaster(false);
		logic.addColumn(c);
		if (this.suspendidov){
			c = new columna();
			c.setNombre("v.suspendidov");
			c.setAlias("sv");
			c.setWidth(120);
			c.setMaster(false);
			logic.addColumn(c);
			
		}
		if (this.suspendidoc){
			c = new columna();
			c.setNombre("v.suspendidoc");
			c.setAlias("sc");
			c.setWidth(120);
			c.setMaster(false);
			logic.addColumn(c);
			
		}
		
		logic
				.addFromTable("V_MA_ARTICULOS v LEFT OUTER JOIN  Stk_Stock_UDKG s ON  v.IDARTICULO = s.IdArticulo");
		f = new Filtro();
		f.setNombre("v.idarticulo");
		f.setAlias("idarticulo");
		f.setWidth(120);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("v.descripcion");
		f.setAlias("descripcion");
		f.setWidth(350);
		f.setFocus(true);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("v.descripabrev");
		f.setAlias("linea");
		f.setWidth(150);
		logic.addFilter(f);
		logic.setTop(200);
		logic.addOrder("v.idarticulo");
		String restriction="";
		
		if (restriction.length()>0){
			logic.addRestriction(restriction);	
		}
		
		logic.setTitle("Buscador de Articulo");
		String group="v.idarticulo,v.descripcion,v.descripabrev,v.precio2";
		if (this.suspendidoc){
			group+=",v.suspendidoc";
		}
		if (this.suspendidov){
			group+=",v.suspendidov";
		}
		logic.addGroup(group);
		
		logic.setColors(true);
		logic.init();
		
	}
	
	private aplicacion.inventario.articulo.constructor._Constructor articulo = null;
	public void goMa_Articulos(String idarticulo) {
		if (articulo != null) {
			articulo.dispose();
		}
		articulo = new aplicacion.inventario.articulo.constructor._Constructor();
		articulo.setParameter(_parametros.iduser, C.getIduser());
		articulo.setParameter(_parametros.LookAndFeel, C.getLookAndFeelTheme()
				);
		articulo.setParameter(_parametros.connector, C.getConnectionHandler());
		articulo
				.setParameter(
						aplicacion.inventario.articulo.interfaces._parametros.idarticulo,
						idarticulo);
		articulo.build(this.getConstructor());
		articulo.init();
	}
}
