package aplicacion.actualizacion.odbc.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.Timer;

import aplicacion.herramientas.java.Convertidor;
import aplicacion.herramientas.java.launcher.logic.SwingWorker;
import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Logic;
import aplicacion.modelo.logic.Data;
import aplicacion.herramientas.java.*;
import aplicacion.actualizacion.odbc.logic.*;
import aplicacion.actualizacion.odbc.gui.*;

public class _Logic extends Logic{
	private Object[][] archivo;
	String estado="";
	private aplicacion.herramientas.java.buscadores.Proveedor bProveedor=null;
	private aplicacion.herramientas.java.visualizadores.Proveedor vProveedor=null;
	private String tc="odbc";
	public int errors;
	private int current;
	private int lenght;
	private boolean debug,done,canceled,override;
	private Timer Timer;  //  @jve:decl-index=0:
	private Crono crono;
	int length=0;
	private _Data data=null;
	private _Frame frame=null;
	   
	   private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector=null;
	   
	   public void setData(Data data){
		   this.data=(_Data) data;
		   super.setData(data);
	   }
	   
	   public void setFrame(JFrame frame){
		   this.frame=(_Frame) frame;
		   super.setFrame(frame);
	   }
	   class _taskInfo {
			_taskInfo() {
				_taskworkInfo();
				}
			}
	   
	   class _taskExport {
			_taskExport() {
				_taskworkExport();
				}
			}
	   class _taskUpdate {
			_taskUpdate() {
				//_taskworkUpdate();
				}
			}
	   
	   public boolean _taskworkUpdate(String idcomprobante,String idproveedor,String linea,String pol,Object[][] results){
			//System.out.println("Actualizando UPDW "+idcomprobante);
		   	boolean ok=true;
		   	//idproveedor=this.data.getSupplierID(idproveedor);
		   	this.lenght=results.length;
			
			
		    	int u=0;
		    	Convertidor Cv=new Convertidor();
		    	//Object[] test=PF.getSettings();
		    	Object[] test=new Integer[]{0,1,3,2};
	    		int i_art=-1;
	    		int i_pre=-1;
	    		int i_des=-1;
	    		int i_lin=-1;
	    		try {
	    			i_art=(Integer)test[0];	
	    		}catch(Exception e){
	    			System.out.println(e.getMessage());
	    		}
	    		
	    		try {
	    			i_des=(Integer)test[1];	
	    		}catch(Exception e){
	    			System.out.println(e.getMessage());
	    		} 
	    		if (test[2].getClass()==Integer.class){
	    			i_pre=(Integer)test[2];
	    		}
	    		if (test[3].getClass()==Integer.class){
	    			i_lin=(Integer)test[3];
	    			
	    		}
	    		String filter=linea;
	    		if (filter.compareTo("")!=0){
	    			int qtys=0;
	    			while (u<results.length & !canceled){
			    		current=u;
			    		linea=(String)results[u][i_lin];		
			    		if (linea.compareTo(filter)==0){
			    			qtys++;
			    		}
			    		u++;
		    		}
	    			this.lenght=qtys;
	    		}
	    		u=0;
	    		current=0;
	        	while (u<results.length & !canceled){
		    		
		    		String codigo=(String)results[u][i_art];
		    		
		        	codigo=Cv.LimpiarString(codigo, " ");
		        	
		        	if (idproveedor.compareTo("211010044")==0){
		        		String codigox="";
		        		for (int e=0;e<codigo.length();e++){
		        			int _char=codigo.charAt(e);
		        			codigox=codigox+""+(char)(_char-6);
		        			
		        		}
		        		
		    		codigo=codigox;
		    		}
		        	
		        	String descripcion="";
		        	try {
	    				descripcion=(String)results[u][i_des];		
	    			}catch(Exception e){
	    				
	    			}
		        	//System.out.println("Do Update "+u);
		    		try {
		    				linea=(String)results[u][i_lin];		
		    		}catch(Exception e){
		    				
		    		}
		    				    		
		    		linea=Cv.remove_last_spaces(linea);
//		    		linea=data.getBrandID(linea);
		    		estado=codigo+" "+linea+" ";
		    		String pre=(String)results[u][i_pre];
//		    		System.out.println("Actualizando> "+estado+" "+pre);
		    		//pre=Cv.normalize(pre);
		    		if (pre.contains(".") & pre.contains(",")){
		    			pre=pre.replace(",", "");
		    		}else{
		    			if (pre.contains(".")){
			    			pre=pre.replace(",", "");	
			    		}else {
			    			pre=pre.replace(",", ".");
			    		}	
		    		}
		    		
		    		
		    		double precio=0;
		    		try {
						precio=new Double(pre);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					boolean doit=true;
					if (filter.compareTo("")!=0){
						if (linea.compareTo(filter)!=0){
							doit=false;
						}
					}
					boolean error=false;
					if (precio>0 & doit){
//						data.clearBatch();
						data.setAutoCommit();
						double old=data.getPrecioViejo(idproveedor, codigo, linea);
						String q="";
			    		if ((Math.abs(precio-old)>0.01)|override){
			    			
			    			if (!data.exist(codigo,linea,idproveedor)){
			    				q=data.getInsert(idproveedor, codigo, linea, precio, old,descripcion,idcomprobante,pol);
			    				error=data.executeUpdate(q);
			    				System.out.println(q);
			    			}else{
			    				q=data.getUpdate(precio,old, codigo, linea, idproveedor,idcomprobante);
			    				System.out.println(q);
			    				error=data.executeUpdate(q);
			    			}
			    			
			    			
			    		} else {
			    			q=data.getUpdateFecha(codigo, linea, idproveedor,idcomprobante);
			    			System.out.println(q);
			    			error=data.executeUpdate(q);
			    			
			    		}
			    		
			    		current++;
					}
		    		
		    		u++;
		    	}
		    	
		    	ok=this.Actualizar(idcomprobante,idproveedor, pol);
		    	
		    	done=true;
		    	
		    	if (ok){
		    		
		    		this.exportar(idproveedor);
		    	}
		    	
		    	return ok;
		}

	   
		
		public void evaluar_idcomprobante(JTextField tx){
			String _nuevo=data.getProximoPGCorrecto("odbc");
			String idcomprobante=tx.getText();
			if (_nuevo.compareTo(idcomprobante)==0){
				frame.get_txt_idcomprobante().setEditable(false);
				frame.get_txt_idproveedor().requestFocusInWindow();	
			}else{
				aviso("debe cargarse uno existente o error");
				tx.requestFocusInWindow();
			}
			
		}
		
		 public boolean Actualizar(String idcomprobante,String idproveedor,String pol){
			 	boolean error=false;
			 	System.out.println("Actualizacion de fechas y aplicacion de precios nuevos UPDF:"+idcomprobante);
			 	if (!canceled){
			 		estado="Aplicando Precios Nuevos";
			    	frame.getJProgressBar().setIndeterminate(true);
			    	String iduser=this.getConstructor().getIduser();
			    	
			    	
			    	String q="";
			    	data.beginTransaction();
			    	data.clearBatch();
			    	q="alter table v_ma_articulos disable trigger all ";
			    	data.addBatch(q);
			    	q=data.getUpdatePrices(idproveedor, pol, tc, idcomprobante, iduser);
			    	data.addBatch(q);
			    	q="alter table v_ma_articulos enable trigger all ";
			    	data.addBatch(q);
			    	q+="update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 where codigo like '"+tc+"'";
			    	System.out.println(q);
			    	data.addBatch(q);
			    	error=data.executeBatch();
			    	if (!error){
			    		data.commitTransaction();
			    		aviso("Se aplicaron los calculos de precios con politicas y fechas de actualizacion");
			    	}else{
			    	
			    		data.rollbackTransaction();
			    		error("Error Aplicando Precios");
			    	}	
			 	}
		    	
		    	
		    	return !error;
		    }
	   
	      private aplicacion.actualizacion.exportar.constructor._Constructor exportar=null;
			public void exportar(String idproveedor){
				
				
						boolean error=false;
						try {
							if (exportar!=null){
								exportar.dispose();
							}
							exportar=new aplicacion.actualizacion.exportar.constructor._Constructor();
							exportar.setParameter(_parametros.connector, this.getConstructor().getConnectionHandler().Clone());
							exportar.setParameter(_parametros.iduser, getConstructor().getIduser());
							exportar.setParameter(_parametros.LookAndFeel,getConstructor().getLookAndFeelTheme());
							exportar.setParameter(aplicacion.actualizacion.exportar.interfaces._parametros._idproveedor, idproveedor);
							exportar.build(this.getConstructor());
							exportar.init();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							error=true;
						}
						if (error){
							error("Error Al Iniciar Esportar. Inicie manualmente-> Actualizacion->Esportar Actualizacion Web");
						}
					
				
				
			}
	   
	   public void ActualizarFechas(String pol,String idcomprobante,String iduser){
		   frame.getJProgressBar().setIndeterminate(true);
	    	estado="Aplicando Cambio de Precios a Articulos";
	    	current=0;
	    	lenght=0;
	    	data.beginTransaction();
	    	String q=data.update_politica(idcomprobante, pol,iduser);
	    	data.clearBatch();
	    	data.addBatch(q);
	    	q=data.getUpdateTC("odbc");
	    	data.addBatch(q);
	    	boolean error=data.executeBatch();
	    	if (!error){
	    		data.commitTransaction();
	    	}else{
	    		data.rollbackTransaction();
	    	}
	    	
	    	estado="Finalizado";
	    	done=true;
	    }
	   
	   aplicacion.herramientas.java.exportar.constructor._Constructor _exportar;
	   public void _exportar() {
			if (_exportar != null) {
				_exportar.dispose();
			}
			_exportar = new aplicacion.herramientas.java.exportar.constructor._Constructor();
			_exportar.setParameter(_parametros.connector, this.getConstructor()
					.getConnectionHandler());
			_exportar.setParameter(_parametros.LookAndFeel, this.getConstructor()
					.getLookAndFeelTheme());
			_exportar
					.setParameter(
							aplicacion.herramientas.java.exportar.interfaces._parametros._data,
							archivo);
			_exportar.setShowOnStartup(true);
			_exportar.build(this.getConstructor());
    
			_exportar.init();

			aplicacion.herramientas.java.exportar.logic._Logic _logic = (aplicacion.herramientas.java.exportar.logic._Logic) _exportar
					.getLogic();

			columna col = null;

			col = new columna();
			col.setNombre("codigo");
			col.setColumn(0);
			col.setMaster(true);
			_logic.addColumn(col);

			col = new columna();
			col.setNombre("descripcion");
			col.setColumn(1);
			col.setMaster(false);
			_logic.addColumn(col);

			col = new columna();
			col.setNombre("linea");
			col.setColumn(2);
			col.setMaster(false);
			_logic.addColumn(col);

			col = new columna();
			col.setNombre("precio");
			col.setColumn(3);
			col.setMaster(false);
			_logic.addColumn(col);

			
			_logic.crear_tabla();

		}

	   public void _taskworkExport(){
	    	frame.getJProgressBar().setIndeterminate(true);
	    	archivo=null;
	    	String pol=frame.get_txt_idpolitica().getText();
	    	if (this.Politica.existe(pol)){
	    		estado="Cargando Informacion desde Catalogo ";
	        	String idproveedor=frame.get_txt_idproveedor().getText();
	        	boolean ok=data.createODBCConnection(idproveedor);
	        	
	        	if (ok){
	        		String query=frame.get_txt_consulta().getText();
	                try{
	                 archivo=data.getODBCResult(idproveedor, query);
	                }catch(Exception e){
	                    	
	                 }
	                String linea=frame.get_txt_linea().getText();
	                String idcomprobante=frame.get_txt_idcomprobante().getText();
	                
	                frame.getJProgressBar().setIndeterminate(false);
	                
	                 estado="Finalizando ";
	        	}
	        	
	    	}else{
	    		error("Ingrese una Politica de Precios");
	    	}
	    	done=true;
	    }  
	   public void _taskworkInfo(){
    	frame.getJProgressBar().setIndeterminate(true);
    	archivo=null;
    	String pol=frame.get_txt_idpolitica().getText();
    	boolean ok=true;
//    	data.createPostgresConnection(this.getConstructor(),"192.168.4.42", 5432, "wismi_production", "postgres", "ipsilon");
    	if (ok){
    		estado="Cargando Informacion desde Catalogo ";
        	String idproveedor=frame.get_txt_idproveedor().getText();
        	ok=data.createODBCConnection(idproveedor);
        	
        	if (ok){
        		String query=frame.get_txt_consulta().getText();
                try{
                 archivo=data.getODBCResult(idproveedor, query);
                }catch(Exception e){
                    	
                 }
                String linea=frame.get_txt_linea().getText();
                String idcomprobante=frame.get_txt_idcomprobante().getText();
                
                frame.getJProgressBar().setIndeterminate(false);
                 boolean oku=this._taskworkUpdate(idcomprobante, idproveedor, linea,pol, archivo);
                 estado="Finalizando ";
        	}
        
    	}
    			
    	
    	done=true;
    }
    
    public void clean(){
    	frame.get_txt_consulta().setText("");
    	frame.get_txt_idpolitica().setText("");
    	frame.get_txt_idproveedor().setText("");
    	frame.get_txt_odbc().setText("");
    	frame.get_txt_politica_detalle().setText("");
    	frame.get_txt_proveedor_descripcion().setText("");
    	frame.get_btn_test().setEnabled(false);
    	frame.get_txt_idproveedor().setEnabled(true);
    	frame.get_txt_idpolitica().setEnabled(true);
    	frame.getJProgressBar().setString("");
    	frame.getJProgressBar().setIndeterminate(false);
    	frame.getJProgressBar().setValue(0);
    	frame.get_txt_class().setText("");
    	frame.get_txt_idcomprobante().setText("");
    	frame.get_txt_idcomprobante().setEditable(true);
    }
    
    public void nuevo(){
    	this.clean();
    	String idcomprobante=data.getProximoPGCorrecto("odbc");
    	frame.get_txt_idcomprobante().setText(idcomprobante);
    	this.evaluar_idcomprobante(frame.get_txt_idcomprobante());
    }
    
    
    
    public void cargarProveedor(String idproveedor){
		Object[][] results=data.getProveedor(idproveedor);
		if (results!=null){
			if (results.length>0){
				frame.get_txt_idproveedor().setEnabled(false);
				String codigo=(String) results[0][0];
				String descripcion=(String) results[0][1];
				String consulta=(String) results[0][2];
				String linea=(String) results[0][3];
				String odbc=(String) results[0][4];
				String politica=(String) results[0][5];
				String clase=(String) results[0][7];
				frame.get_txt_proveedor_descripcion().setText(descripcion);
				frame.get_txt_odbc().setText(odbc);
				frame.get_txt_class().setText(clase);
				frame.get_txt_consulta().setText(consulta);
				frame.get_txt_idpolitica().setText(politica);
				frame.get_txt_idpolitica().requestFocusInWindow();
			}
		}
	}
    
 	private aplicacion.herramientas.java.evaluadores.Politica Politica=null;
	public void initialize_Politica(){
		Politica=new aplicacion.herramientas.java.evaluadores.Politica(){
			public void cargar(String codigo){
				Object[][] results=this.getInfo(codigo);
				String descripcion=(String) results[0][1];
				frame.get_txt_politica_detalle().setText(descripcion);
			}
		};
		Politica.setConstructor(this.getConstructor());
	}
	public void BuscarPolitica(JTextField tx){
		Politica.Buscar(tx);
	}
	public void BuscarPolitica(){
		Politica.Buscar(frame.get_txt_idpolitica());
	}
	public void buscarPolitica(JTextField tx){
		Politica.buscar(tx);
	}
	
	public void evaluarPolitica(JTextField tx){
		Politica.evaluate(tx);
	}

    private aplicacion.herramientas.java.evaluadores.Proveedor proveedor=null;
    public void initialize_proveedor(){
    	proveedor=new aplicacion.herramientas.java.evaluadores.Proveedor(){
    		public void cargar(String codigo){
    			frame.get_btn_test().setEnabled(true);
				cargarProveedor(codigo);
    		}
    	};
    	proveedor.setConstructor(this.getConstructor());
    }
    public void BuscarProveedor(JTextField tx){
    	proveedor.Buscar(tx);
    }
    public void buscarProveedor(JTextField tx){
    	proveedor.buscar(tx);
    }
    public void BuscarProveedor(){
    	proveedor.Buscar(frame.get_txt_idproveedor());
    }
    public void evaluarProveedor(JTextField tx){
    	frame.get_btn_test().setEnabled(false);
    	proveedor.evaluate(tx);
    }

    public void focus(){
    	frame.get_txt_idproveedor().requestFocusInWindow();
    }
    
    
    public void goUpdate() {
    	
    	this.createTimer();
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
    
public void goExport() {
    	
    	
    	SwingWorker worker=null;
    		worker = new SwingWorker() {
    			@Override
    			public Object construct() {
    				return new _taskExport();
    			}
    		};
    		if (Timer!=null) {
    			Timer.start();
    		}
    		worker.start();
    	}
    public void goInfo() {
    	this.createTimer();
    	SwingWorker worker=null;
    		worker = new SwingWorker() {
    			@Override
    			public Object construct() {
    				return new _taskInfo();
    			}
    		};
   		if (Timer!=null) {
    			Timer.start();
   		}
   		worker.start();
    }
    
    public void Info(){
    	aviso("Este proceso demorara unos minutos. Si se extiende por mas de 2 minutos, probablemente tenga un  error de configuracion");
		done=false;
		canceled=false;
		this.createTimer();
		goInfo();
	}
    public void Update(){
    	override=frame.get_chk_forzar().isSelected();
    	
//		done=false;
//		canceled=false;
//		this.createTimer();
		goUpdate();
	}
    
  //metodos basicos de tareas swing
	public void createTimer(){
		crono=new Crono();
		crono.start();
		lenght=0;
		current=0;
		errors=0;
		done = false;
		canceled=false;
		Timer=new Timer(300, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (done |canceled){
					endbar();
					Timer.stop();
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
		frame.get_btn_guardar().setEnabled(true);
	}
	
	public void doCancel(){
		if (preguntar("confirmar","Cancela Tarea?")){
			this.canceled=true;
			frame.get_btn_cancelar().setEnabled(true);
		}
	}	
	
	public void guardar(){
		String idproveedor=frame.get_txt_idproveedor().getText();
		String consulta=frame.get_txt_consulta().getText();
		String politica=frame.get_txt_idpolitica().getText();
		String odbc=frame.get_txt_odbc().getText();
		String linea=frame.get_txt_linea().getText();
		String clase=frame.get_txt_class().getText();
		if (clase.compareTo("")==0){
			
		}
		boolean error=false;
		if (data.check_catalog(idproveedor)){
			error=data.UpdateCatalog(idproveedor, politica, consulta,linea,odbc,clase);	
		}else{
			error=data.InsertCatalog(idproveedor, politica, consulta,linea,odbc,clase);
		}
		if (!error){
			aviso("Se Grabo la configuracion Correctamente");
		}else{
			error("Error Grabando Configuracion");
		}
	}
}
