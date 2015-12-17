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
public class Cataloguer extends Generico{
	private JFrame frame=null;
	public Cataloguer(Constructor C){
		super(C);
	}

	public void cargar(Object[] selection){
		System.out.println("Cargar Seleccion de Articulo");
	}
	public BufferedImage loadImage(String filename){
		BufferedImage img=null;
		
    	try {
      	   img = ImageIO.read(new File(filename));
         } catch (IOException e) {
         }
         return img;
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
	public void showImageInNewFrame (String idarticulo,Image image) {
		showImageInNewFrame (idarticulo,new ImageIcon (image));
	}
	public void showImageInNewFrame (JTextField tx,String idarticulo,int x, int y) {
		String q="select foto from v_ma_articulos_foto ";
		q+="where idarticulo like '"+idarticulo+"' ";
		Object[][] results=this.getConstructor().getLogic().getData().getResults(q);
		if (results!=null){
			if (results.length>0){
				String path=results[0][0].toString();
				System.out.println("image>"+path);
				BufferedImage bi=this.loadImage(path);
				this.showImageInNewFrame(idarticulo,bi);
				
			}else{
				if (frame!=null){
					frame.setVisible(false);
				}
			}
		}else{
			if (frame!=null){
				frame.setVisible(false);
			}
		}
		tx.requestFocusInWindow();
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
						
						showImageInNewFrame(tx,idarticulo,0,0);
						
						super.focus(col);
					}
					
					public Color getColor(int row,int col,JTable table){
						Color color=Color.white;
						/*
						double stock=0.0;
						String _stock=table.getValueAt(row, 4).toString();
						if (_stock!=null){
							if (_stock.compareTo("")!=0){
								stock=new Double(_stock.replaceAll(",", ""));
							}
						}
						//System.out.println("Stock>"+_stock);
						if (stock>0){
							//System.out.println("Stock>"+stock);
							color=Color.GREEN;
						}*/
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
		

		c = new columna();
		c.setNombre("c.codigo");
		c.setAlias("codigo");
		c.setWidth(100);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("c.descripcion");
		c.setAlias("descripcion");
		c.setWidth(200);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("c.categoria1");
		c.setAlias("Categoria");
		c.setWidth(200);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("c.categoria2");
		c.setAlias("SubCategoria");
		c.setWidth(200);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("c.linea");
		c.setAlias("linea");
		c.setWidth(140);
		c.setMaster(false);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("c.idproveedor");
		c.setAlias("idproveedor");
		c.setWidth(140);
		c.setMaster(false);
		logic.addColumn(c);

		c = new columna();
		c.setNombre("c.marca_vehiculo");
		c.setAlias("marca vehiculo");
		c.setWidth(140);
		c.setMaster(false);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("c.vehiculo");
		c.setAlias("vehiculo");
		c.setWidth(140);
		c.setMaster(false);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("c.equivalencia");
		c.setAlias("equivalencia");
		c.setWidth(140);
		c.setMaster(false);
		logic.addColumn(c);
		
		c = new columna();
		c.setNombre("c.precio");
		c.setAlias("precio");
		c.setWidth(140);
		c.setMaster(false);
		logic.addColumn(c);
		
		
		
		
		
		c = new columna();
		c.setNombre("convert(varchar(10),c.fecha,105)");
		c.setAlias("actualizado");
		c.setWidth(120);
		c.setMaster(false);
		logic.addColumn(c);
		
		logic.addFromTable("b_catalogo c ");
		f = new Filtro();
		f.setNombre("c.codigo");
		f.setAlias("codigo");
		f.setWidth(120);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("c.descripcion+c.categoria1+c.categoria2+c.marca_vehiculo+c.vehiculo+c.equivalencia");
		f.setAlias("descripcion");
		f.setWidth(350);
		f.setFocus(true);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("c.linea");
		f.setAlias("linea");
		f.setWidth(150);
		logic.addFilter(f);
		f = new Filtro();
		f.setNombre("c.idproveedor");
		f.setAlias("idproveedor");
		f.setWidth(150);
		logic.addFilter(f);
		logic.setTop(200);
		logic.addOrder("c.linea,c.codigo");
		
		logic.setTitle("Buscador por Catalogos Vs Sistema");
		//logic.addGroup("ar.idarticulo,ar.descripcion,c.codigo,c.descripcion,c.linea,c.idproveedor,ar.precio2,c.categoria1,c.categoria2,c.marca_vehiculo,c.vehiculo");
				
		logic.setColors(true);
		
		logic.init();
		
	}
	private aplicacion.inventario.articulo.constructor._Constructor articulo = null;
	public void goMa_Articulos(String idarticulo) {
		if (articulo != null) {
			articulo.dispose();
		}
		articulo = new aplicacion.inventario.articulo.constructor._Constructor();
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
