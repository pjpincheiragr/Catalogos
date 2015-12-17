package aplicacion.sistema.indexersearch.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.*;
import javax.swing.JTextField;

import aplicacion.herramientas.java.Crono;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.sistema.indexersearch.interfaces.*;
import aplicacion.sistema.indexersearch.gui._Frame;
import aplicacion.sistema.indexersearch.logic._Data;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

public class _Logic extends Logic {
	private _Frame frame;
	private _Data data;
	private PDFParser parser;
	private String parsedText;

	private PDDocument pdDoc;
	private COSDocument cosDoc;
	private PDDocumentInformation pdDocInfo;
	private String estado = "";
	private int current;
	private int errors;
	private int lenght, max;
	private Timer Timer;
	private boolean debug = false;
	private boolean done = false;
	private boolean canceled = false;
	private boolean override;
	private Crono crono;
	private String carpeta_destino = "e:/indexados/";
	//private String carpeta_destino="file://192.168.4.150/windows share/indexados/";
	//private String carpeta_local="indexados/";
	private String acumulator="";
	class _taskUpdate {
		_taskUpdate(String busqueda) {

		}
	}

	public void setFrame(JFrame _frame) {
		this.frame = (_Frame) _frame;
		super.setFrame(_frame);
	}

	public void setData(Data _data) {
		this.data = (_Data) _data;
		super.setData(_data);
	}

	public void clean() {
		frame.get_txt_busqueda().setText("");
		frame.setJTable(null);
		this.acumulator="";
	}

	public void focus() {
		frame.get_txt_busqueda().requestFocusInWindow();
	}

	public void _goUpdate(String busqueda) {
		frame.getJProgressBar().setIndeterminate(false);
		this.createTimer();
		final String _busqueda = busqueda;
		frame.get_btn_buscar().setEnabled(false);
		SwingWorker worker = null;
		worker = new SwingWorker() {
			@Override
			public Object construct() {
				return new _taskUpdate(_busqueda);
			}
		};
		if (Timer != null) {
			Timer.start();
		}
		worker.start();
	}

	public void updateBar() {
		frame.getJProgressBar().setMaximum(lenght);
		frame.getJProgressBar().setValue(current);
		frame.getJProgressBar().setString(
				estado + " " + current + "/" + lenght + " " + crono.elapsed());
		frame.getJProgressBar().setStringPainted(true);
	}

	public void endbar() {
		estado = "";
		frame.getJProgressBar().setString("");
		frame.getJProgressBar().setIndeterminate(false);
		frame.getJProgressBar().setValue(0);
		frame.get_btn_buscar().setEnabled(true);

	}

	public void createTimer() {
		current = 0;
		errors = 0;
		canceled = false;
		done = false;
		crono = new Crono();
		crono.start();
		Timer = new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done | canceled) {
					endbar();
					Timer.stop();
					canceled = false;
					done = false;
				} else {
					updateBar();
				}
			}
		});

	}

	

	private void create_table_documentos(Object[][] results) {
		CustomTable table = new CustomTable();
		Column col = new Column();

		col = new Column();
		col.setName("idproveedor");
		col.setWidth(100);
		col.setEditable(false);

		table.addColumn(col);

		col = new Column();
		col.setName("linea");
		col.setWidth(120);
		col.setEditable(false);

		table.addColumn(col);

		col = new Column();
		col.setName("archivo");
		col.setWidth(180);
		col.setEditable(false);
		table.addColumn(col);

		col = new Column();
		col.setName("pagina");
		col.setWidth(80);
		col.setEditable(false);
		table.addColumn(col);

		table.setData(results);
		table.setName(_Interface._table);
		table.addKeyListener(this._constructor.getKeyListener());
		table.addMouseListener(this._constructor.getMouseListener());
		Font fuente = new Font("Arial", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.build();
		table.fillData();
		frame.setJTable(table.getTable());
	}


	public void buscar(){
		String busqueda=frame.get_txt_busqueda().getText();
		if (this.acumulator.length()>0){
			this.acumulator+=" ";
		}
		this.acumulator+=busqueda;
		if (acumulator.compareTo("")!=0){
			this.buscar(this.acumulator);	
		}else{
			error("Ingrese el texto que desea buscar");
		}
		
	}
	
	
	 
	 
	
	public void cargar(int row){
		String file=frame.getJTable().getValueAt(row,2).toString();
	
		String page=frame.getJTable().getValueAt(row,3).toString();
		//this.cargar(file, page);
		this.cargarCatalogo(file, page);
	}
	public void cargarCatalogo(String filename, String page){
    	BufferedImage _image=null;
    	boolean ok=false;
    	try {
			Statement stmt = data.getConnector("MySQL").createStatement();
				String q="SELECT data FROM pdf_archivo  where filename like '"+filename+"' ";
				System.out.println(q);
				ResultSet resultSet = stmt.executeQuery(q);
				
					 if (resultSet.next()){
						 ok=true;
						 Blob image = resultSet.getBlob(1);
						  InputStream input = image.getBinaryStream();
						  int _page=new Integer(page);
							_page--;
							
							//String filePath=this.carpeta_local+file;
							
							
					        SwingController controller = new SwingController();

					        SwingViewBuilder factory = new SwingViewBuilder(controller);

					        JPanel viewerComponentPanel = factory.buildViewerPanel();

					        // add interactive mouse link annotation support via callback
					        controller.getDocumentViewController().setAnnotationCallback(
					                new org.icepdf.ri.common.MyAnnotationCallback(
					                        controller.getDocumentViewController()));

					        JFrame applicationFrame = new JFrame();
					        applicationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					        applicationFrame.getContentPane().add(viewerComponentPanel);

					        // Now that the GUI is all in place, we can try openning a PDF
					        controller.openDocument(input, filename, "");
					        controller.goToDeltaPage(_page);
					        controller.setZoom(new Float(1.5));
					        
					        // show the component
					        applicationFrame.pack();
					        
					        applicationFrame.setVisible(true);	
				           
					 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
		
    }
	public void cargar(String file,String page){
		int _page=new Integer(page);
		_page--;
		
		//String filePath=this.carpeta_local+file;
		String filePath=this.carpeta_destino+file;
		
        SwingController controller = new SwingController();

        SwingViewBuilder factory = new SwingViewBuilder(controller);

        JPanel viewerComponentPanel = factory.buildViewerPanel();

        // add interactive mouse link annotation support via callback
        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));

        JFrame applicationFrame = new JFrame();
        applicationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        applicationFrame.getContentPane().add(viewerComponentPanel);

        // Now that the GUI is all in place, we can try openning a PDF
        controller.openDocument(filePath);
        controller.goToDeltaPage(_page);
        controller.setZoom(new Float(1.5));
        
        // show the component
        applicationFrame.pack();
        
        applicationFrame.setVisible(true);	
	}
	public void buscar(JTextField tx) {
		String text=tx.getText();
		this.buscar(text);
	}
	public void buscar(String text) {
		frame.get_txt_busqueda().setText("");
		Object[][] results=data.buscar(text);
		if (results!=null){
			if (results.length>0){
				this.create_table_documentos(results);		
			}else{
				aviso("resultado nulo");
				this.clean();
				
			}
		}else{
			aviso("resultado nulo");
			this.clean();
		}
		
	}
}