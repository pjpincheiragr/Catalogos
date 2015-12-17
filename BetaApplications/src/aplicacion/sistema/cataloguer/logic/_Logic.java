package aplicacion.sistema.cataloguer.logic;

import java.awt.Color;
import java.awt.Component;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.io.*;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.*;
import javax.swing.tree.DefaultTreeModel;
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


import aplicacion.sistema.cataloguer.gui._Frame;
import aplicacion.sistema.cataloguer.interfaces.*;
import aplicacion.sistema.cataloguer.logic._Data;
import aplicacion.sistema.menu.logic.ImageCompare;

import java.io.*;
import aplicacion.herramientas.java.xml.Element;
import aplicacion.herramientas.java.xml.Atributo;
import aplicacion.herramientas.java.xml.XML;
import java.util.*;
public class _Logic extends Logic {
	private _Frame frame;
	private _Data data;
	private List<String> lineas;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector = null;
	private aplicacion.sistema.autorizacion.constructor._Constructor aplicaciones = null;
	private String idproveedor="";
	private String odbc="";
	private String tabla="";
	private String eqv_idproveedor="";
	private String eqv_odbc="";
	private String eqv_tabla="";
	private String img_idproveedor="";
	private String img_odbc="";
	private String img_tabla="";
	//variables de timer
	private String estado="";
	private String destino="//192.168.4.150/windows storage/catalogo/";
	private int current;
	private int errors;
	private int lenght,max;
	private Timer Timer;  
	private boolean debug=false;
	private boolean done=false;
	private boolean canceled=false;
	private boolean override;
	private Crono crono;
	

	class _taskUpdate {
		_taskUpdate() {
			importarBase();
			}
		}
	
	public void fillODBC(){
		frame.get_lst_odbc().removeAllItems();
		String[] options=new String[]{
				"",
				_Interface._odbc_access,
				_Interface._odbc_dbase,
				_Interface._odbc_fox,
				_Interface._odbc_paradox,
				_Interface._odbc_sqlite};
		for (int i=0;i<options.length;i++){
			frame.get_lst_odbc().addItem(options[i]);	
		}
	}
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	
	
	private aplicacion.herramientas.java.buscadores.Dynamic bDynamic=null;
	public void BuscarDynamic(JTextField ext) {
		 if (bDynamic!=null){
			 bDynamic.getConstructor().dispose();
		 }
	 bDynamic=new aplicacion.herramientas.java.buscadores.Dynamic(this.getConstructor());
	 bDynamic.setTop(500);
	 columna c=new columna();
	 c.setNombre("");
	 c.setAlias("");
	 bDynamic.AddColumn(c);
	 bDynamic.Buscar(ext);
	} 
	
	
	
	public void delete(){
		String id=frame.get_txt_idquery().getText();
		if (preguntar("Confirmar","Desea eliminar la consulta "+id+"?")){
			data.delete(id);
			data.delete_aplicaciones(id);
			clean();
		}
	}
	
	
	
	public void clean(){
		canceled=true;
		if (Timer!=null){
			Timer.stop();	
		}
		this.endbar();
		canceled=false;
		idproveedor="";
		odbc="";
		tabla="";
		done=false;
		frame.get_txt_idquery().setText("");
		frame.get_btn_buscar_usuario().setEnabled(true);
		frame.setJTable(null);
		frame.setJTable1(null);
		frame.get_txt_idquery().setEditable(true);
		frame.get_txt_idquery().requestFocusInWindow();
		frame.get_txt_categoria().setText("");
		frame.get_txt_categoria2().setText("");
		frame.get_txt_linea().setText("");
		frame.get_txt_descripcion().setText("");
		frame.get_lst_categoria().removeAllItems();
		frame.get_lst_categoria2().removeAllItems();
		frame.get_lst_codigo().removeAllItems();
		frame.get_lst_desc_1().removeAllItems();
		frame.get_lst_desc_2().removeAllItems();
		frame.get_lst_desc_3().removeAllItems();
		frame.get_lst_desc_4().removeAllItems();
		frame.get_lst_desc_5().removeAllItems();
		frame.get_lst_desc_6().removeAllItems();
		frame.get_lst_odbc().setSelectedIndex(-1);
		frame.get_lst_equiv_1().removeAllItems();
		frame.get_lst_equiv_2().removeAllItems();
		frame.get_lst_equiv_3().removeAllItems();
		frame.get_lst_linea_original().removeAllItems();
		frame.get_lst_codigo_original().removeAllItems();
		frame.get_lst_linea_reemplazo().removeAllItems();
		frame.get_lst_codigo_reemplazo().removeAllItems();
		frame.get_txt_query_equivalencia().setText("");
		frame.get_lst_precio().removeAllItems();
		frame.get_lst_linea().removeAllItems();
		frame.get_lst_marca_vehiculo().removeAllItems();
		frame.get_lst_vehiculo().removeAllItems();
		frame.get_lst_imagen().removeAllItems();
		frame.get_lst_imagen_codigo().removeAllItems();
		frame.get_lst_imagen_linea().removeAllItems();
		frame.get_txt_imagen_directorio().setText("");
		frame.get_txt_odbc_imagen().setText("");
	}
	public void AgregarLinea(String linea){
		boolean found=false;
		int i=0;
		while (i<lineas.size() & !found){
			found=lineas.get(i).compareTo(linea)==0;
			i++;
		}
		if (!found){
			lineas.add(linea);
		}
		
	}
	
	public String cleanWord(String word){
		String tmp=word.toUpperCase();
		if (frame.getJTable1()!=null){
			for (int i=0;i<frame.getJTable1().getRowCount();i++){
				String clave=frame.getJTable1().getValueAt(i, 0).toString().toUpperCase();
				String reemplazo=frame.getJTable1().getValueAt(i, 1).toString().toUpperCase();
				if (tmp.indexOf(clave)>=0){
					tmp=tmp.replaceAll(clave, reemplazo);
				}
			}	
		}
		return tmp;
	}
	
	public String process_field(String field){
		
		String tipo_odbc=frame.get_lst_odbc().getSelectedItem().toString();
		if (tipo_odbc.compareTo(_Interface._odbc_access)==0){
			field="Iif(isnull("+field+"),'',"+field+")";
			}
		if (tipo_odbc.compareTo(_Interface._odbc_sqlite)==0){
			field="ifnull("+field+",'')";	
		}
		return field;
	}
	
	public int LimpiarLineas(Object[][] results){
		int errors=0;
		this.lenght=results.length;
		int i=0;
		frame.getJProgressBar().setIndeterminate(false);
		
		while(i<results.length & !canceled){
			this.current=i;
			
			String codigo=(String) results[i][1];
			String linea=(String) results[i][2];
			linea=linea.replaceAll("\"", "");
			linea=this.cleanWord(linea);
			aplicacion.herramientas.java.Convertidor C=
				new aplicacion.herramientas.java.Convertidor();
			linea=C.remove_last_spaces(linea);
			this.estado="Checkeando Lineas "+codigo+" "+linea;
			
			this.AgregarLinea(linea);
		
			i++;
		}
		this.lenght=lineas.size();
		i=0;
		while(i<lineas.size() & !canceled){
			this.current=i;
			
			String linea=lineas.get(i);
			estado="Limpiando Linea "+linea;
			data.clearBatch();
			data.addBatch(data.clearCatalogo(idproveedor, linea));
			boolean error=data.executeBatch();
			if (error)errors++;
			i++;
		}
		return errors;
	}
	
	public int incorporar(Object[][] results){
		int errors=0;
		int i=0;
		this.lenght=results.length;
		while(i<results.length & !canceled){
			this.current=i;
			
			String idproveedor=(String) results[i][0];
			String codigo=(String) results[i][1];
			codigo=codigo.replaceAll(" ", "");
			String linea=(String) results[i][2];
			linea=linea.replaceAll("\"", "");
			linea=this.cleanWord(linea);
			String descripcion=(String) results[i][3];
			descripcion=descripcion.replaceAll("'", "");
			descripcion=this.cleanWord(descripcion);
			String marca_vehiculo=(String) results[i][4];
			marca_vehiculo=marca_vehiculo.replaceAll("'", "");
			marca_vehiculo=this.cleanWord(marca_vehiculo);
			String vehiculo=(String) results[i][5];
			vehiculo=vehiculo.replaceAll("'", "");
			vehiculo=this.cleanWord(vehiculo);
			String categoria1="";
			try {
				categoria1 = (String) results[i][6];
				categoria1=categoria1.replaceAll("'", "");
				categoria1=this.cleanWord(categoria1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			String categoria2="";
			try {
				categoria2 = (String) results[i][7];
				categoria2=categoria2.replaceAll("'", "");
				categoria2=this.cleanWord(categoria2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String precio= (String) results[i][8];
			String equivalencia= (String) results[i][9];
			
			aplicacion.herramientas.java.Convertidor C=
				new aplicacion.herramientas.java.Convertidor();
			linea=C.remove_last_spaces(linea);
			codigo=C.remove_last_spaces(codigo);
			this.estado="Incorporando "+codigo+" "+linea;
			if (precio.compareTo("")==0){
				precio="0.0";
			}
			String qc=data.insertCatalogo(idproveedor, codigo, linea, descripcion, marca_vehiculo, vehiculo, categoria1, categoria2,precio,equivalencia);
			data.clearBatch();
			data.addBatch(qc);
			boolean error=data.executeBatch();
			if (error){
				errors++;
			}
			//System.out.println(qc);
			i++;
		}
		return errors;
	}
	
	public int incorporarEquivalencias(){
		int errors=0;
		frame.getJProgressBar().setIndeterminate(true);
		estado="Buscando Equivalencias para Incorporar";
		this.current=1;
		String q=this.getequivalenciaquery();
		Object[][] results2=data.getConnector(odbc).getResults(q);
		this.lenght=results2.length;
		int i=0;
		frame.getJProgressBar().setIndeterminate(false);
		String old_line="";
		String old_codigo="";
		String equivalencia="";
		while(i<results2.length & !canceled){
			String linea_original=(String)results2[i][0];
			String codigo_original=(String)results2[i][1];
			String linea_reemplazo=(String)results2[i][2];
			String codigo_reemplazo=(String)results2[i][3];
			
			aplicacion.herramientas.java.Convertidor C=
				new aplicacion.herramientas.java.Convertidor();
			linea_original=C.remove_last_spaces(linea_original);
			codigo_original=C.remove_last_spaces(codigo_original);
			linea_reemplazo=C.remove_last_spaces(linea_reemplazo);
			codigo_reemplazo=C.remove_last_spaces(codigo_reemplazo);
			
			
			this.estado="Incorporando Equivalencia de "+codigo_original+" "+linea_original;
			if (old_line.compareTo(linea_original)==0 & old_codigo.compareTo(codigo_original)==0){
				if (linea_reemplazo.compareTo(linea_original)!=0 | codigo_reemplazo.compareTo(codigo_original)!=0){
					equivalencia+=linea_reemplazo+"="+codigo_reemplazo+" ";		
				}
			}else{
					if (equivalencia.compareTo("")!=0){
						String qc=this.insertarEquivalencia(idproveedor, linea_original, codigo_original, equivalencia);
						data.clearBatch();
						data.addBatch(qc);
						boolean error=data.executeBatch();
						if (error){
							errors++;
						}	
					}
					
					equivalencia="";
					old_line=linea_original;
					old_codigo=codigo_original;
					if (linea_reemplazo.compareTo(linea_original)!=0 | codigo_reemplazo.compareTo(codigo_original)!=0){
						equivalencia=linea_reemplazo+"="+codigo_reemplazo+" ";	
					}
					
			}
			
			
			
			
			
			current=i;
			
		i++;
		if (i>=results2.length){
			
			if (equivalencia.compareTo("")!=0){
				String qc=this.insertarEquivalencia(idproveedor, linea_original, codigo_original, equivalencia);
				data.clearBatch();
				data.addBatch(qc);
				boolean error=data.executeBatch();
				if (error){
					errors++;
				}
			}
		}
		}
		return errors;
	}
	
	public int enlazarEquivalencias(){
		int errors=0;
		frame.getJProgressBar().setIndeterminate(true);
		estado="Buscando Equivalencias para Enlazar";
		this.current=1;
		String q=this.getequivalenciaquery();
		System.out.println(q);
		Object[][] results2=data.getConnector(odbc).getResults(q);
		this.lenght=results2.length;
		int i=0;
		frame.getJProgressBar().setIndeterminate(false);
		String old_line="";
		String old_codigo="";
		String equivalencia="";
		while(i<results2.length & !canceled){
			String linea_original=(String)results2[i][0];
			String codigo_original=(String)results2[i][1];
			String linea_reemplazo=(String)results2[i][2];
			String codigo_reemplazo=(String)results2[i][3];
			
			aplicacion.herramientas.java.Convertidor C=
				new aplicacion.herramientas.java.Convertidor();
			linea_original=C.remove_last_spaces(linea_original);
			codigo_original=C.remove_last_spaces(codigo_original);
			linea_reemplazo=C.remove_last_spaces(linea_reemplazo);
			codigo_reemplazo=C.remove_last_spaces(codigo_reemplazo);
			if (!data.existe_enlace(codigo_original, linea_original, codigo_reemplazo, linea_reemplazo)){
					String qx=data.insertar_enlace(codigo_original, linea_original, codigo_reemplazo, linea_reemplazo);
					data.clearBatch();
					data.addBatch(qx);
					data.executeBatch();
			}	
			this.estado="Enlazando Equivalencia de "+codigo_original;
			current=i;
		i++;
		}
		return errors;
	}
	
	public void storeImage(String codigo,String linea,String idproveedor,int secuencia,String filename){
    	FileInputStream fis = null;
        PreparedStatement ps = null;
        if (linea.compareTo("")!=0 & idproveedor.compareTo("")!=0 & idproveedor.compareTo("")!=0){
        	String INSERT_PICTURE = "insert into imagen_catalogo(codigo, linea, idproveedor, secuencia,imagen) values (?, ?, ?, ?,?)";
            File file = new File(filename);
              if (file.exists()){
            	  if (file.isFile()){
            	  try {
        	          
        	          
        	          try {
        				fis = new FileInputStream(file);
        	          } catch (FileNotFoundException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        	          }
        	          
        	          try {
        	        	  
        				ps = data.getConnector("MySQL").prepareStatement(INSERT_PICTURE);
        				  ps.setString(1, codigo);
        				  ps.setString(2, linea);
        				  ps.setString(3, idproveedor);
        				  ps.setInt(4,secuencia);
        				  ps.setBinaryStream(5, fis, (int) file.length());
        				  
        				  ps.executeUpdate();
        				  
        				  System.out.println("Insercion de imagen:"+file.getName()+">?");
        			} catch (SQLException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        	          
        	        } finally {
        	          try {
        				ps.close();
        			} catch (SQLException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        	          try {
        				fis.close();
        			} catch (IOException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        	        }
              }else{
            	  System.out.println(filename+" no existe");
              }
          	
        }
        }
          
    }
	public int incorporarImagenes(){
		frame.getJProgressBar().setIndeterminate(true);
		estado="Buscando Fotos para Incorporar";
		int errors=0;
		this.current=1;
		String q=this.getimagenquery();
		Object[][] results3=data.getConnector(odbc).getResults(q);
		this.lenght=results3.length;
		int i=0;
		current=0;
		frame.getJProgressBar().setIndeterminate(false);
		String old_line="";
		String old_codigo="";
		
		String origen=frame.get_txt_imagen_directorio().getText();
		aplicacion.herramientas.java.Convertidor C=
			new aplicacion.herramientas.java.Convertidor();
		
		while(i<results3.length & !canceled){
			String imagen_codigo=(String)results3[i][0];
			imagen_codigo=C.remove_last_spaces(imagen_codigo);
			String imagen_linea=(String)results3[i][1];
			imagen_linea=this.cleanWord(imagen_linea);
			imagen_linea=C.remove_last_spaces(imagen_linea);
			String imagen=(String)results3[i][2];
			
			imagen=imagen.replaceAll("/", "#");
			System.out.println("Incorporar imagen de "+imagen_codigo+" > "+imagen);
			this.estado="Incorporando Foto de "+imagen_codigo+ " "+imagen_linea;
			if (old_line.compareTo(imagen_linea)==0 & old_codigo.compareTo(imagen_codigo)==0){
				
			}else{
				old_line=imagen_linea;
				old_codigo=imagen_codigo;
					if (imagen.compareTo("")!=0){
						String source=origen+"/"+imagen;
						
						
						File _sf=new File(source);
						
						if (_sf.exists()){
							
							
							int number=0;
							boolean copy=true;
							String[] results=data.getImagenes(imagen_codigo, imagen_linea,idproveedor);
							if (results!=null){
								if (results.length>0){
									copy=false;
								}
							}
							if (copy){
								this.storeImage(imagen_codigo, imagen_linea, idproveedor, 0, source);
							}
							
							
							
							
							
						}
						
						
						boolean error=false;
						if (error){
							errors++;
						}	
					}
							
			}
			
			
			
			
			
			current=i;
			
		i++;
		if (i>=results3.length){
			
			if (imagen.compareTo("")!=0){
				String source=origen+"/"+imagen;
				String destino2=destino+"/"+imagen_linea;
				boolean copy=true;
				String[] results=data.getImagenes(imagen_codigo, imagen_linea,idproveedor);
				if (results!=null){
					if (results.length>0){
						copy=false;
					}
				}
				if (copy){
					this.storeImage(imagen_codigo, imagen_linea, idproveedor, 0, source);
				}
				boolean error=false;
				if (error){
					errors++;
				}
			}
		}
		}
		
		return errors;
	}
	
	
	public boolean Similar(String image1,String image2){
		boolean match=false;
		File f1=new File(image1);
		File f2=new File(image2);
		if (f1.exists() & f2.exists()){
			try {
				ImageCompare ic = new ImageCompare(image2, image1);
				// Set the comparison parameters. 
				//   (num vertical regions, num horizontal regions, sensitivity, stabilizer)
				ic.setParameters(1, 1, 1, 1);
				// Display some indication of the differences in the image.
				ic.setDebugMode(-2);
				// Compare.
				ic.compare();
				match=ic.match();	
				ic=null;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			this.freeMemory();
		}
		System.out.println(image1+" vs "+image2+" = "+match);
		return match;
	}
	
	public void freeMemory(){
		try {
			this.getConstructor().freeResources();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void check_dir(String destino_folder){
		File fd=new File(destino_folder);
		if (!fd.exists()){
			boolean success = (fd).mkdir();	
		}
		
	}
	
	public boolean copy(String source,String destiny){
		boolean ok=true;
		System.out.println("Copiando foto "+source+" en "+destiny);
		File _sf=new File(source);
		File _df=new File(destiny);
		if (_sf.exists()){
			
			boolean similar=_df.exists();
			
			
			if (!similar){
				try {
					InputStream in = new FileInputStream(_sf);
					  
					  //For Append the file.
//			      OutputStream out = new FileOutputStream(f2,true);

					  //For Overwrite the file.
					  OutputStream out = new FileOutputStream(_df);

					  byte[] buf = new byte[1024];
					  int len;
					  while ((len = in.read(buf)) > 0){
					    out.write(buf, 0, len);
					  }
					  in.close();
					  out.close();
					  System.out.println("File copied.");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ok=false;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ok=false;
				}
		
			}else{
				System.out.println("Lo iva a copiar. pero ya existe");
			}
		}
		return ok;
	}
	
	public void test_rodamet(){
		if (data.getConnector(odbc)==null){
			boolean ok=data.createODBCConnection(odbc);
			ok=data.getConnector(odbc).connect();
		}
			String q="select FIGURA FROM FIGURAS where codigo like '0058' and marca like 'KOBLA'";
			java.sql.ResultSet
			rs1=data.getConnector(odbc).getResulSet(q);
			String imgLen="";
			try {
				imgLen = rs1.getString(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (imgLen.length()>0){
				System.out.println(imgLen.length());
		        int len = imgLen.length();
		        byte [] rb = new byte[len];
		        int index;
				try {
					InputStream readImg = rs1.getBinaryStream(1);
					index = readImg.read(rb, 0, len);
					System.out.println("index----------------"+index);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
		        	
			}
	        
		
	}
	public void importarBase(){
		lineas=new ArrayList<String>();
		String q=this.getquery(0);
		System.out.println(q);
		if (data.getConnector(odbc)==null){
			boolean ok=data.createODBCConnection(odbc);
			ok=data.getConnector(odbc).connect();
		}
		estado="Consultando Catalogo. Por Favor Espere....";
		this.lenght=100;
		frame.getJProgressBar().setIndeterminate(true);
		this.current=0;
		
		this.errors=0;
		Object[][] results=null;
		if (frame.get_chk_limpia_linea().isSelected()){
			results=data.getConnector(odbc).getResults(q);
			errors+=this.LimpiarLineas(results);
		}
		if (frame.get_chk_incorpora_articulos().isSelected()){
			if (results==null){
				results=data.getConnector(odbc).getResults(q);	
			}
			errors+=this.incorporar(results);	
		}
		
		
		//si incorpora
		/*
		
		*/
		if (frame.get_chk_equivalencias().isSelected()){
			if (frame.get_txt_query_equivalencia().getText().compareTo("")!=0){
				errors+=this.incorporarEquivalencias();	
			}	
		}
		if (frame.get_chk_incorporar_enlaces().isSelected()){
			if (frame.get_txt_query_equivalencia().getText().compareTo("")!=0){
				errors+=this.enlazarEquivalencias();	
			}	
		}
		if (frame.get_chk_incorpora_imagenes().isSelected()){
			if (frame.get_txt_odbc_imagen().getText().compareTo("")!=0){
				errors+=this.incorporarImagenes();
			}	
		}
		
		aviso("Operacion Importar Completa con "+this.lenght+" operaciones y "+errors+" errores ");
		done=true;
	}
	
	
	public void cargarFiltros(){
		String idquery=frame.get_txt_idquery().getText();
		Object[][] results=data.getFiltros(idquery);
		if (results!=null){
			if (results.length<=0){
				results=new Object[][]{{"",""}};
			}
		}else{
			results=new Object[][]{{"",""}};
		}
		this.create_table_filtros(results);
	}
	
	public void show_preview(){
		boolean ok=data.createODBCConnection(odbc);
		ok=data.getConnector(odbc).connect();
		if (ok){
			String q=this.getquery(0);
			System.out.println(q);
			Object[][] results=data.getConnector(odbc).getResults(q);
			this.create_table(results);	
		}
		
	}
	
	public void borrarCatalogo(){
		if (preguntar("Confirmar","Elimina Articulos del Catalogo "+idproveedor+" ?")){
			String q=data.clearCatalogo(idproveedor);
			data.clearBatch();
			data.addBatch(q);
			boolean error=data.executeBatch();
			if (!error){
				aviso("Se elimino correctamente");
			}else{
				error("Error Al Eliminar");
			}
			
		}
	}
	public void borrarRenglon(int row){
		if (preguntar("Confirmar","Elimina Renglon "+row+" de la tabla?")){
			String idquery=frame.get_txt_idquery().getText();
			String clave="";
			try {
				clave = frame.getJTable1().getValueAt(row, 0).toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if (data.existe(idquery, clave)){
					data.delete(idquery, clave);
			}
			DefaultTableModel model=(DefaultTableModel) frame.getJTable1().getModel();
			model.removeRow(row);
			if (model.getRowCount()<=0){
				model.setRowCount(1);
				model.setValueAt(true, 0, 0);
				
			}
			
		}
	}
	public String concatenate(String source,String chunk){
		String glue="+";
		String tipo_odbc=frame.get_lst_odbc().getSelectedItem().toString();
		if (tipo_odbc.compareTo(_Interface._odbc_sqlite)==0){
			glue="||";
		}
		if (source.compareTo("")!=0){
			source+=glue+"' '"+glue+chunk;	
		}else{
			source+=chunk;
		}
		return source;
			
	}
	
	public String getequivalenciaquery(){
		String q="";
		String linea_original="";
		try {
			linea_original = frame.get_lst_linea_original().getSelectedItem().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String codigo_original="";
		try {
			codigo_original = frame.get_lst_codigo_original().getSelectedItem().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String linea_reemplazo="";
		try {
			linea_reemplazo = frame.get_lst_linea_reemplazo().getSelectedItem().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String codigo_reemplazo="";
		try {
			codigo_reemplazo = frame.get_lst_codigo_reemplazo().getSelectedItem().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		q+="select ";
		q+=""+linea_original+",";
		q+=""+codigo_original+",";
		q+=""+linea_reemplazo+",";
		q+=""+codigo_reemplazo+" ";
		q+=" from "+this.eqv_tabla+" ";
		if (frame.get_lst_odbc().getSelectedItem()==_Interface._odbc_access){
			q+=" group by "+linea_original+",";
			q+=""+codigo_original+",";
			q+=""+linea_reemplazo+",";
			q+=""+codigo_reemplazo+" ";
			q+="order by "+linea_original+",";
			q+=""+codigo_original+",";
			q+=""+linea_reemplazo+",";
			q+=""+codigo_reemplazo+" ";
			
		}
		System.out.println(q);	

		return q;
	}
	
	public String getimagenquery(){
		String q="";
		q+="select ";
		String imagen_codigo=frame.get_lst_imagen_codigo().getSelectedItem().toString();
		String imagen_linea=frame.get_lst_imagen_linea().getSelectedItem().toString();
		String imagen=frame.get_lst_imagen().getSelectedItem().toString();
		q+=""+imagen_codigo+",";
		q+=""+imagen_linea+",";
		q+=""+imagen+" ";
		q+=" from "+this.img_tabla+" ";
		
		if (frame.get_lst_odbc().getSelectedItem()==_Interface._odbc_access){
			q+=" group by ";
			q+=""+imagen_codigo+",";
			q+=""+imagen_linea+",";
			q+=""+imagen+" ";
			q+="order by ";
			q+=""+imagen_codigo+",";
			q+=""+imagen_linea+",";
			q+=""+imagen+" ";
		}
		System.out.println(q);

		return q;
	}
	
	public String insertarEquivalencia(String idproveedor,String linea_original,String codigo_original,String equivalencia){
		String q="update b_catalogo set equivalencia=equivalencia+'"+equivalencia+"' ";
		q+="where idproveedor like '"+idproveedor+"' and linea like '"+linea_original+"' and codigo like '"+codigo_original+"' ";
		//System.out.println(q);
		return q;
	}
	
	public String getquery(int top){
		String q="";
		String codigo=frame.get_lst_codigo().getSelectedItem().toString();
		String descripcion=frame.get_txt_descripcion().getText();
	
		if (descripcion.compareTo("")!=0){
			descripcion="'"+descripcion+"\'";
		}
		
		String descripcion1=frame.get_lst_desc_1().getSelectedItem().toString();
		
		if (descripcion1.compareTo("")!=0){
			descripcion1=this.process_field(descripcion1);
			descripcion=this.concatenate(descripcion, descripcion1);
		}
		String descripcion2=frame.get_lst_desc_2().getSelectedItem().toString();
		
		if (descripcion2.compareTo("")!=0){
			descripcion2=this.process_field(descripcion2);
			descripcion=this.concatenate(descripcion, descripcion2);
		}
		String descripcion3=frame.get_lst_desc_3().getSelectedItem().toString();
		if (descripcion3.compareTo("")!=0){
			descripcion3=this.process_field(descripcion3);
			descripcion=this.concatenate(descripcion, descripcion3);
		}
		
		String descripcion4=frame.get_lst_desc_4().getSelectedItem().toString();
		if (descripcion4.compareTo("")!=0){
			descripcion4=this.process_field(descripcion4);
			descripcion=this.concatenate(descripcion, descripcion4);
		}
		String descripcion5=frame.get_lst_desc_5().getSelectedItem().toString();
		if (descripcion5.compareTo("")!=0){
			descripcion5=this.process_field(descripcion5);
			descripcion=this.concatenate(descripcion, descripcion5);
		}
		String descripcion6=frame.get_lst_desc_6().getSelectedItem().toString();
		if (descripcion6.compareTo("")!=0){
			descripcion6=this.process_field(descripcion6);
			descripcion=this.concatenate(descripcion, descripcion6);
		}
		String linea=frame.get_txt_linea().getText();
		if (linea.compareTo("")!=0){
			linea="'"+linea+"'";
		}
		if (linea.compareTo("")==0){
			linea=frame.get_lst_linea().getSelectedItem().toString();
		}
		
		String marca_vehiculo=frame.get_lst_marca_vehiculo().getSelectedItem().toString();
		if (marca_vehiculo.compareTo("")==0){
			marca_vehiculo="''";
		}
		String vehiculo=frame.get_lst_vehiculo().getSelectedItem().toString();
		if (vehiculo.compareTo("")==0){
			vehiculo="''";
		}
		
		
		String categoria=frame.get_txt_categoria().getText();
		String categoria2=frame.get_txt_categoria2().getText();
		if (categoria.compareTo("")!=0){
			categoria="'"+categoria+"'";
		}
		if (categoria2.compareTo("")!=0){
			categoria2="'"+categoria2+"'";
		}
		
		if (categoria.compareTo("")==0){
			categoria=frame.get_lst_categoria().getSelectedItem().toString();
		}
		if (categoria2.compareTo("")==0){
			categoria2=frame.get_lst_categoria2().getSelectedItem().toString();
		}
		String precio=frame.get_lst_precio().getSelectedItem().toString();
		String equivalencia="";
		String equivalencia1=frame.get_lst_equiv_1().getSelectedItem().toString();
		if (equivalencia1.compareTo("")!=0){
			equivalencia1=this.process_field(equivalencia1);
			equivalencia=this.concatenate(equivalencia, equivalencia1);
		}
		String equivalencia2=frame.get_lst_equiv_2().getSelectedItem().toString();
		if (equivalencia2.compareTo("")!=0){
			equivalencia2=this.process_field(equivalencia2);
			equivalencia=this.concatenate(equivalencia, equivalencia2);
		}
		String equivalencia3=frame.get_lst_equiv_3().getSelectedItem().toString();
		if (equivalencia3.compareTo("")!=0){
			equivalencia3=this.process_field(equivalencia3);
			equivalencia=this.concatenate(equivalencia, equivalencia3);
		}
		
		q+="select ";
		if (top>0){
			q+="top "+top;	
		}
		
		q+=" '"+idproveedor+"',"+codigo+","+linea+","+descripcion+","+marca_vehiculo+","+vehiculo+" ";
		if (categoria.compareTo("")==0){
			categoria="''";
		}
		if (categoria2.compareTo("")==0){
			categoria2="''";
		}
		
		
		if (precio.compareTo("")==0){
			precio="0.0";
		}
		if (equivalencia.compareTo("")==0){
			equivalencia="''";
		}
		q+=","+categoria;
		q+=","+categoria2;
		q+=","+precio;
		q+=","+equivalencia;
		q+=" from "+this.tabla;
		return q;
	}
	
	public boolean existe(String idquery){
		boolean aux=false;
		String q="";
		q+="select idquery from b_query_cataloguer where idquery like '"+idquery+"' ";
		Object[][] results=data.getResults(q);
		if (results!=null){
			if (results.length>0){
				aux=true;
			}
		}
		return aux;
	}
	
	public void guardar(){
		boolean error=false;
		String idquery=frame.get_txt_idquery().getText();
		String codigo=frame.get_lst_codigo().getSelectedItem().toString();
		String descripcion=frame.get_txt_descripcion().getText();
		descripcion=descripcion.replaceAll("'","''");
		String descripcion1=frame.get_lst_desc_1().getSelectedItem().toString();
		descripcion1=descripcion1.replaceAll("'","''");
		String descripcion2=frame.get_lst_desc_2().getSelectedItem().toString();
		descripcion2=descripcion2.replaceAll("'","''");
		String descripcion3=frame.get_lst_desc_3().getSelectedItem().toString();
		descripcion3=descripcion3.replaceAll("'","''");
		String descripcion4=frame.get_lst_desc_4().getSelectedItem().toString();
		descripcion4=descripcion4.replaceAll("'","''");
		String descripcion5=frame.get_lst_desc_5().getSelectedItem().toString();
		descripcion5=descripcion5.replaceAll("'","''");
		String descripcion6=frame.get_lst_desc_6().getSelectedItem().toString();
		descripcion6=descripcion6.replaceAll("'","''");
		String categoria1=frame.get_txt_categoria().getText();
		categoria1=categoria1.replaceAll("'","''");
		String categoria2=frame.get_txt_categoria2().getText();
		categoria2=categoria2.replaceAll("'","''");
		String categoria_1=frame.get_lst_categoria().getSelectedItem().toString();
		categoria_1=categoria_1.replaceAll("'","''");
		String categoria_2=frame.get_lst_categoria2().getSelectedItem().toString();
		categoria_2=categoria_2.replaceAll("'","''");
		String linea=frame.get_txt_linea().getText();
		linea=linea.replaceAll("'","''");
		String linea1=frame.get_lst_linea().getSelectedItem().toString();
		linea1=linea1.replaceAll("'","''");
		String marca_vehiculo=frame.get_lst_marca_vehiculo().getSelectedItem().toString();
		marca_vehiculo=marca_vehiculo.replaceAll("'","''");
		String vehiculo=frame.get_lst_vehiculo().getSelectedItem().toString();
		vehiculo=vehiculo.replaceAll("'","''");
		String precio=frame.get_lst_precio().getSelectedItem().toString();
		String equivalencia1=frame.get_lst_equiv_1().getSelectedItem().toString();
		equivalencia1=equivalencia1.replaceAll("'","''");
		String equivalencia2=frame.get_lst_equiv_2().getSelectedItem().toString();
		equivalencia2=equivalencia2.replaceAll("'","''");
		String equivalencia3=frame.get_lst_equiv_3().getSelectedItem().toString();
		equivalencia3=equivalencia3.replaceAll("'","''");
		String linea_original="";
		try {
			linea_original = frame.get_lst_linea_original().getSelectedItem().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		linea_original=linea_original.replaceAll("'","''");
		String codigo_original="";
		try {
			codigo_original = frame.get_lst_codigo_original().getSelectedItem().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		codigo_original=codigo_original.replaceAll("'","''");
		String linea_reemplazo="";
		try {
			linea_reemplazo = frame.get_lst_linea_reemplazo().getSelectedItem().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		linea_reemplazo=linea_reemplazo.replaceAll("'","''");
		String codigo_reemplazo="";
		try {
			codigo_reemplazo = frame.get_lst_codigo_reemplazo().getSelectedItem().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		codigo_reemplazo=codigo_reemplazo.replaceAll("'","''");
		String query_equivalencia=frame.get_txt_query_equivalencia().getText();
		query_equivalencia=query_equivalencia.replaceAll("'","''");
		String tipo_odbc=frame.get_lst_odbc().getSelectedItem().toString();
		String imagen="";
		try {
			imagen = frame.get_lst_imagen().getSelectedItem().toString();
		} catch (Exception e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		String imagen_codigo="";
		try {
			imagen_codigo = frame.get_lst_imagen_codigo().getSelectedItem().toString();
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		String imagen_linea="";
		try {
			imagen_linea = frame.get_lst_imagen_linea().getSelectedItem().toString();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String imagen_directorio="";
		try {
			imagen_directorio = frame.get_txt_imagen_directorio().getText();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String query_imagen="";
		try {
			query_imagen = frame.get_txt_odbc_imagen().getText();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imagen=imagen.replaceAll("'","''");
		imagen_codigo=imagen_codigo.replaceAll("'","''");
		imagen_linea=imagen_linea.replaceAll("'","''");
		data.clearBatch();
		String q="";
		if (!existe(idquery)){
			q=data.insert(idquery, codigo, descripcion, descripcion1, descripcion2, descripcion3, descripcion4, descripcion5, descripcion6, linea, linea1, marca_vehiculo, vehiculo, categoria1, categoria2, categoria_1, categoria_2, precio, equivalencia1, equivalencia2, equivalencia3, linea_original, codigo_original, linea_reemplazo, codigo_reemplazo, query_equivalencia, tipo_odbc, query_imagen, imagen_codigo, imagen_linea, imagen, imagen_directorio);
			
		}else{
			q=data.update(idquery, codigo, descripcion, descripcion1, descripcion2, descripcion3, descripcion4, descripcion5, descripcion6, linea, linea1, marca_vehiculo, vehiculo, categoria1, categoria2, categoria_1, categoria_2, precio, equivalencia1, equivalencia2, equivalencia3, linea_original, codigo_original, linea_reemplazo, codigo_reemplazo, query_equivalencia, tipo_odbc, query_imagen, imagen_codigo, imagen_linea, imagen, imagen_directorio);
		}
		data.addBatch(q);
	
		error=data.executeBatch();
		if (!error){
			aviso("Se Grabo Correctamente");
		}else{
			error("Error Grabando Cataloguer");
		}
		
	}
	
	public String getConfiguration(String idquery){
		String q="";
		q+="select codigo,descripcion,descripcion1,descripcion2,descripcion3,descripcion4,descripcion5,descripcion6,";
		q+="linea,linea1,";
		q+="marca_vehiculo,vehiculo, ";
		q+="categoria1,categoria2,categoria_1,categoria_2,precio,equivalencia1,equivalencia2,equivalencia3,linea_original,codigo_original,linea_reemplazo,codigo_reemplazo,query_equivalencia,tipo_odbc, ";
		q+="query_imagen,imagen_codigo,imagen_linea,imagen,imagen_directorio ";
		q+="from b_query_cataloguer ";
		q+="where idquery like '"+idquery+"'";
		return q;
	}
	
	
	public void setOption(JComboBox cb,String option){
		boolean found=false;
		int i=0;
		while (i<cb.getItemCount() & !found){
			found=cb.getItemAt(i).toString().compareTo(option)==0;
			if (found){
				cb.setSelectedIndex(i);
			}
			i++;
		}
	}
	
	public void loadConfiguration(String idquery){
		Object[][] results=data.getResults(this.getConfiguration(idquery));
		if (results!=null){
			if (results.length>0){
			String codigo=(String) results[0][0];
			String descripcion=(String) results[0][1];
			String descripcion1=(String) results[0][2];
			String descripcion2=(String) results[0][3];
			String descripcion3=(String) results[0][4];
			String descripcion4=(String) results[0][5];
			String descripcion5=(String) results[0][6];
			String descripcion6=(String) results[0][7];
			String linea=(String) results[0][8];
			String linea1=(String) results[0][9];
			String marca_vehiculo=(String) results[0][10];
			String vehiculo=(String) results[0][11];
			String categoria1=(String) results[0][12];
			String categoria2=(String) results[0][13];
			String categoria_1=(String) results[0][14];
			String categoria_2=(String) results[0][15];
			String precio=(String) results[0][16];
			String equivalencia1=(String) results[0][17];
			String equivalencia2=(String) results[0][18];
			String equivalencia3=(String) results[0][19];
			String query_equivalencia=(String) results[0][24];
			if (query_equivalencia.compareTo("")!=0){
				frame.get_txt_query_equivalencia().setText(query_equivalencia);
				this.cargar_equivalencia(query_equivalencia);
				this.loadConfigurationEquivalencia(idquery);
			}
			String tipo_odbc=(String) results[0][25];
			String imagen_odbc=(String) results[0][26];
			if (imagen_odbc.compareTo("")!=0){
				frame.get_txt_odbc_imagen().setText(imagen_odbc);
				this.cargar_imagen(imagen_odbc);
				this.loadConfigurationImagen(idquery);
			}
			
			this.setOption(frame.get_lst_codigo(), codigo);
			this.setOption(frame.get_lst_desc_1(), descripcion1);
			this.setOption(frame.get_lst_desc_2(), descripcion2);
			this.setOption(frame.get_lst_desc_3(), descripcion3);
			this.setOption(frame.get_lst_desc_4(), descripcion4);
			this.setOption(frame.get_lst_desc_5(), descripcion5);
			this.setOption(frame.get_lst_desc_6(), descripcion6);
			this.setOption(frame.get_lst_linea(), linea1);
			this.setOption(frame.get_lst_marca_vehiculo(), marca_vehiculo);
			this.setOption(frame.get_lst_vehiculo(), vehiculo);
			this.setOption(frame.get_lst_categoria(), categoria_1);
			this.setOption(frame.get_lst_categoria2(), categoria_2);
			this.setOption(frame.get_lst_precio(),precio);
			this.setOption(frame.get_lst_equiv_1(), equivalencia1);
			this.setOption(frame.get_lst_equiv_2(), equivalencia2);
			this.setOption(frame.get_lst_equiv_3(), equivalencia3);
			this.setOption(frame.get_lst_odbc(), tipo_odbc);
			
			frame.get_txt_categoria().setText(categoria1);
			frame.get_txt_categoria2().setText(categoria2);
			this.frame.get_txt_descripcion().setText(descripcion);
			this.frame.get_txt_linea().setText(linea);
			}
			
		}
		
	}
	
	public void loadConfigurationEquivalencia(String idquery){
		Object[][] results=data.getResults(this.getConfiguration(idquery));
		if (results!=null){
			if (results.length>0){
			
			String linea_original=(String) results[0][20];
			String codigo_original=(String) results[0][21];
			String linea_reemplazo=(String) results[0][22];
			String codigo_reemplazo=(String) results[0][23];
			
			this.setOption(frame.get_lst_linea_original(), linea_original);
			this.setOption(frame.get_lst_codigo_original(), codigo_original);
			this.setOption(frame.get_lst_linea_reemplazo(), linea_reemplazo);
			this.setOption(frame.get_lst_codigo_reemplazo(), codigo_reemplazo);
			}
			
		}
	}
	
	public void loadConfigurationImagen(String idquery){
		Object[][] results=data.getResults(this.getConfiguration(idquery));
		if (results!=null){
			if (results.length>0){
				String imagen_codigo=(String) results[0][27];
				String imagen_linea=(String) results[0][28];
				String imagen=(String) results[0][29];
				String imagen_directorio=(String) results[0][30];
			
			
			this.setOption(frame.get_lst_imagen_codigo(), imagen_codigo);
			this.setOption(frame.get_lst_imagen_linea(), imagen_linea);
			this.setOption(frame.get_lst_imagen(), imagen);
			frame.get_txt_imagen_directorio().setText(imagen_directorio);
			}
			
		}
	}
	
	public void setOptionsEquivalencia(Object[][] columns){
		String[] options=new String[columns.length+1];
		options[0]="";
		for (int i=0;i<columns.length;i++){
			options[i+1]=(String)columns[i][0];
		}
		frame.get_lst_linea_original().removeAllItems();
		frame.get_lst_codigo_original().removeAllItems();
		frame.get_lst_linea_reemplazo().removeAllItems();
		frame.get_lst_codigo_reemplazo().removeAllItems();
		
		for (int i=0;i<options.length;i++){
			frame.get_lst_linea_original().addItem(options[i]);
			frame.get_lst_codigo_original().addItem(options[i]);
			frame.get_lst_linea_reemplazo().addItem(options[i]);
			frame.get_lst_codigo_reemplazo().addItem(options[i]);
		}
	}
	
	public void setOptionsImagen(Object[][] columns){
		String[] options=new String[columns.length+1];
		options[0]="";
		for (int i=0;i<columns.length;i++){
			options[i+1]=(String)columns[i][0];
		}
		frame.get_lst_imagen_codigo().removeAllItems();
		frame.get_lst_imagen_linea().removeAllItems();
		frame.get_lst_imagen().removeAllItems();
		
		
		for (int i=0;i<options.length;i++){
			frame.get_lst_imagen_codigo().addItem(options[i]);
			frame.get_lst_imagen_linea().addItem(options[i]);
			frame.get_lst_imagen().addItem(options[i]);
			
		}
	}
	public void setOptions(Object[][] columns){
		String[] options=new String[columns.length+1];
		options[0]="";
		for (int i=0;i<columns.length;i++){
			options[i+1]=(String)columns[i][0];
		}
		frame.get_lst_codigo().removeAllItems();
		for (int i=0;i<options.length;i++){
			frame.get_lst_codigo().addItem(options[i]);	
		}
		frame.get_lst_desc_1().removeAllItems();
		for (int i=0;i<options.length;i++){
			frame.get_lst_desc_1().addItem(options[i]);	
		}
		frame.get_lst_desc_2().removeAllItems();
		for (int i=0;i<options.length;i++){
			frame.get_lst_desc_2().addItem(options[i]);	
		}
		frame.get_lst_desc_3().removeAllItems();
		for (int i=0;i<options.length;i++){
			frame.get_lst_desc_3().addItem(options[i]);	
		}
		frame.get_lst_desc_4().removeAllItems();
		for (int i=0;i<options.length;i++){
			frame.get_lst_desc_4().addItem(options[i]);	
		}
		frame.get_lst_desc_6().removeAllItems();
		for (int i=0;i<options.length;i++){
			frame.get_lst_desc_6().addItem(options[i]);	
		}
		frame.get_lst_desc_5().removeAllItems();
		for (int i=0;i<options.length;i++){
			frame.get_lst_desc_5().addItem(options[i]);	
		}
		frame.get_lst_linea().removeAllItems();
		for (int i=0;i<options.length;i++){
			frame.get_lst_linea().addItem(options[i]);	
		}
		frame.get_lst_marca_vehiculo().removeAllItems();
		for (int i=0;i<options.length;i++){
			frame.get_lst_marca_vehiculo().addItem(options[i]);	
		}
		frame.get_lst_vehiculo().removeAllItems();
		for (int i=0;i<options.length;i++){
			frame.get_lst_vehiculo().addItem(options[i]);	
		}
		frame.get_lst_categoria().removeAllItems();
		for (int i=0;i<options.length;i++){
			frame.get_lst_categoria().addItem(options[i]);	
		}
		frame.get_lst_categoria2().removeAllItems();
		for (int i=0;i<options.length;i++){
			frame.get_lst_categoria2().addItem(options[i]);	
		}
		frame.get_lst_precio().removeAllItems();
		for (int i=0;i<options.length;i++){
			frame.get_lst_precio().addItem(options[i]);	
		}
		frame.get_lst_equiv_1().removeAllItems();
		for (int i=0;i<options.length;i++){
			frame.get_lst_equiv_1().addItem(options[i]);	
		}
		frame.get_lst_equiv_2().removeAllItems();
		for (int i=0;i<options.length;i++){
			frame.get_lst_equiv_2().addItem(options[i]);	
		}
		frame.get_lst_equiv_3().removeAllItems();
		for (int i=0;i<options.length;i++){
			frame.get_lst_equiv_3().addItem(options[i]);	
		}
		
	}
		
	public void cargar(String codigo){
		Object[][] results=data.getQuery(codigo);
		if (results!=null){
			if (results.length>0){
				idproveedor=(String)results[0][1];
				odbc=(String)results[0][2];
				tabla=(String)results[0][3];
				
				Object[][] columns=data.getQueryColumns(codigo);
				this.setOptions(columns);
				this.fillODBC();
				this.loadConfiguration(codigo);
				this.cargarFiltros();
			}
		}
	}
	
	public void cargar_equivalencia(String codigo){
		Object[][] results=data.getQuery(codigo);
		if (results!=null){
			if (results.length>0){
				eqv_idproveedor=(String)results[0][1];
				eqv_odbc=(String)results[0][2];
				eqv_tabla=(String)results[0][3];
				
				Object[][] columns=data.getQueryColumns(codigo);
				this.setOptionsEquivalencia(columns);
				this.loadConfiguration(codigo);
				
			}
		}
	}
	
	public void cargar_imagen(String codigo){
		Object[][] results=data.getQuery(codigo);
		if (results!=null){
			if (results.length>0){
				img_idproveedor=(String)results[0][1];
				img_odbc=(String)results[0][2];
				img_tabla=(String)results[0][3];
				
				Object[][] columns=data.getQueryColumns(codigo);
				this.setOptionsImagen(columns);
				this.loadConfiguration(codigo);
				
			}
		}
	}
	
	private aplicacion.herramientas.java.evaluadores.Query Query=null;
	public void initialize_Query(){
		Query=new aplicacion.herramientas.java.evaluadores.Query(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				frame.get_lst_codigo().requestFocusInWindow();
			}
		};
		Query.setConstructor(this.getConstructor());
	}
	public void BuscarQuery(JTextField tx){
		Query.Buscar(tx);
	}
	public void BuscarQuery(){
		Query.Buscar(frame.get_txt_idquery());
	}
	public void buscarQuery(JTextField tx){
		Query.buscar(tx);
	}
	
	public void evaluarQuery(JTextField tx){
		String codigo=tx.getText();
		if (codigo.compareTo("")!=0){
			if (Query.existe(codigo)){
				this.cargar(codigo);	
			}else{
				error("La consulta no existe");
			}	
		}else{
			Query.evaluate(tx);
		}
		
		
	}
	
	
	
	public void focus(){
		frame.get_txt_idquery().requestFocusInWindow();
	}
	
	public void cargarCajas(String iduser){
		Object[][] results=data.getCajas(iduser);
		this.create_table(results);
	}
	public Object[][] processData(Object[][] results){
		Object[][] tmp=new Object[results.length][8];
		for (int i=0;i<results.length;i++){
			for (int j=0;j<results[0].length;j++){
				tmp[i][j]=results[i][j];
			}
			
			
		}
		return tmp;
	}
	
	private void create_table_filtros(Object[][] results) {
		CustomTable table = new CustomTable();
		Column col = new Column();
		
		
		col = new Column();
		col.setName("clave");
		col.setWidth(100);
		col.setEditable(true);
		CellEditor pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_filtros_clave);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);
		
		col = new Column();
		col.setName("reemplazo");
		col.setWidth(120);
		col.setEditable(true);
		pce = new CellEditor();
		pce.addKeyListener(this.getConstructor().getKeyListener());
		pce.setSelectedBackgroundColor(Color.lightGray);
		pce.setName(_Interface._table_filtros_reemplazo);
		pce.setTipo(String.class);
		col.setCellEditor(pce.getCellEditor());
		table.addColumn(col);
		
		table.setData(results);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente=new Font("Arial", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.setName(_Interface._table);
		table.build();
		table.fillData();
		
		frame.setJTable1(table.getTable());
	}
	
	private void create_table(Object[][] results) {
		CustomTable table = new CustomTable();
		Column col = new Column();
		
		
		col = new Column();
		col.setName("idproveedor");
		col.setWidth(100);
		col.setEditable(false);
		
		table.addColumn(col);
		
		col = new Column();
		col.setName("codigo");
		col.setWidth(120);
		col.setEditable(false);
		
		table.addColumn(col);
		
		col = new Column();
		col.setName("linea");
		col.setWidth(180);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("descripcion");
		col.setWidth(280);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("Marca Vehiculo");
		col.setWidth(120);
		col.setEditable(false);
		table.addColumn(col);
		
		
		col = new Column();
		col.setName("Vehiculo");
		col.setWidth(180);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("Categoria1");
		col.setWidth(180);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("Categoria2");
		col.setWidth(180);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("precio");
		col.setWidth(100);
		col.setEditable(false);
		table.addColumn(col);
		
		col = new Column();
		col.setName("equivalencia");
		col.setWidth(140);
		col.setEditable(false);
		table.addColumn(col);
		
		
		table.setData(results);
		table.addKeyListener(this._constructor.getKeyListener());
		Font fuente=new Font("Arial", Font.PLAIN, 10);
		table.setHeaderFont(fuente);
		table.build();
		table.fillData();
		
		frame.setJTable(table.getTable());
	}
	
	public void evaluate_descripcion(JTextField tx){
		String valor=tx.getText();
		if (valor.compareTo("")!=0){
			frame.get_lst_desc_1().requestFocusInWindow();
		}
	}
	
	public void evaluate_linea_productos(JTextField tx){
		String valor=tx.getText();
		if (valor.compareTo("")!=0){
			frame.get_lst_marca_vehiculo().requestFocusInWindow();
		}
	}
	
	public void evaluar_tabla_clave(JTextField tx,int row){
		String id=tx.getText();
		if (id.compareTo("")!=0){
			frame.getJTable1().changeSelection(row, 1, false, false);
			frame.getJTable1().editCellAt(row, 1);
			frame.getJTable1().transferFocus();
		}else{
			tx.requestFocusInWindow();
		}
	}
	
public void evaluar_tabla_reemplazo(JTextField tx,int row){
		String idquery=frame.get_txt_idquery().getText();
		String clave="";
		try {
			clave = frame.getJTable1().getValueAt(row, 0).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String reemplazo=tx.getText();
		
		
		
		
		if (data.existe(idquery, clave)){
				data.delete(idquery, clave);
		}
		data.insert(idquery, clave, reemplazo);
		
		DefaultTableModel model=(DefaultTableModel) frame.getJTable1().getModel();
		if (row>=model.getRowCount()-1){
				model.setRowCount(model.getRowCount()+1);
		}
		row++;
		frame.getJTable1().changeSelection(row, 0, false, false);
		frame.getJTable1().editCellAt(row, 0);
		frame.getJTable1().transferFocus();
		
	}
	public void evaluate_tabla(JTextField tx){
		String valor=tx.getText();
		if (valor.compareTo("")!=0){
			Object[][] results=new Object[][]{{"","",false,false}};
			this.create_table(results);
		}
	}
	
	public void evaluate_tabla_nombre(JTextField tx,int col,int row){
		String valor=tx.getText();
		if (valor.compareTo("")!=0){
			frame.getJTable().changeSelection(row, col+1, false, false);
			frame.getJTable().editCellAt(row, col+1);
			frame.getJTable().transferFocus();
		}else{
			aviso("Ingrese una descripcion de Columna");
		}
	}
	
	public void evaluate_tabla_alias(JTextField tx,int col,int row){
		String valor=tx.getText();
		if (valor.compareTo("")!=0){
			frame.getJTable().setValueAt(true, row, col+1);
			DefaultTableModel model=(DefaultTableModel)frame.getJTable().getModel();
			if (row==model.getRowCount()-1){
				model.setRowCount(model.getRowCount()+1);
			}
			frame.getJTable().changeSelection(row+1, 0, false, false);
			frame.getJTable().editCellAt(row+1, 0);
			frame.getJTable().transferFocus();	
		}else{
			aviso("Ingrese un alias de Columna");
		}
	}
	
	//metodos basicos de tareas swing
	public void createTimer(){
		current=0;
		errors=0;
		canceled = false;
		done = false;
		crono=new Crono();
		crono.start();
		Timer=new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done |canceled){
					endbar();
					Timer.stop();
					canceled=false;
					done=false;
					}else {
					updateBar();
				}
			}
		}); 
		
	}
	
	
	
	public void updateBar(){
		frame.getJProgressBar().setMaximum(lenght);
		frame.getJProgressBar().setValue(current);
		frame.getJProgressBar().setString(estado+" "+current+"/"+lenght+" "+crono.elapsed());
		frame.getJProgressBar().setStringPainted(true);
	}
	
	public void endbar(){
		estado="";
		frame.getJProgressBar().setString("");
		frame.getJProgressBar().setIndeterminate(false);
		frame.getJProgressBar().setValue(0);
		frame.get_btn_importar().setEnabled(true);
	}
	
	
	public void _goUpdate() {
		this.createTimer();
		frame.get_btn_importar().setEnabled(false);
		SwingWorker worker=null;
			worker = new SwingWorker() {
				@Override
				public Object construct() {
					return new _taskUpdate();
				}
			};
			if (Timer!=null) {
				Timer.start();
			}
			worker.start();
		}
	}

