package aplicacion.herramientas.java.ireport.logic;

import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JFrame;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.view.JRViewer;
import aplicacion.herramientas.java.ireport.gui._Frame;
import aplicacion.herramientas.java.ireport.logic._Data;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;

public class _Logic extends Logic {
	private _Data data;
	private _Frame frame;
	private JRViewer viewer;
	public void setFrame(JFrame _frame){
		this.frame=(_Frame) _frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	public void exit_command(){
		
		try {
			frame.setVisible(false);
			viewer=null;
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			frame.dispose();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.exit_command();
	}
	
	public void excelReport(String fileName,HashMap parameter,String output)
	{
		 File dir=new File (".");
			String directory="";
			try {
				directory=dir.getCanonicalPath();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			directory+="\\reports\\"+fileName;
			System.out.println("Abriendo "+directory);
			boolean ok=this.getConstructor().getConnectionHandler().getDefaultConnector().connect();
			Connection con = this.getConstructor().getConnectionHandler().getDefaultConnector().getConnection();
			
			
			//Way 1
			/*JasperDesign jasperDesign = JasperManager.loadXmlDesign(fileName);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint print = JasperFillManager.fillReport(jasperReport, parameter, con);*/

			//Way 2
			/*JasperReport jasperReport = JasperCompileManager.compileReport(fileName);
			JasperPrint print = JasperFillManager.fillReport(jasperReport, parameter, con);*/

			/*Way 3 (Here the parameter file should be in .jasper extension i.e., the compiled report)*/
			File file=new File(directory);
			if (file.exists()){
				JasperPrint print=null;
				try {
					  JasperReport masterReport = null;
			            try
			            {
			                masterReport = (JasperReport) JRLoader.loadObject(file);
			            }
			            catch (Exception e)
			            {
			            	this.displayError("Error Iniciando Reporte "+fileName, "No se Pudo iniciar el reporte", e.getMessage(), e);
			            
			            }    
			            if (masterReport!=null){
			            	print=JasperFillManager.fillReport(masterReport, parameter, con);
			      

			            }
					
					//print = JasperFillManager.fillReport(directory, parameter, con);
				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					this.displayError("Error Iniciando Reporte "+fileName, "No se Pudo iniciar el reporte", e.getMessage(), e);
				}
				if (print!=null){
					
			      	JRXlsExporter exporter = new JRXlsExporter();
	            	exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
	            	                exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME,output);
	            	                //exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
	            	try {
						exporter.exportReport();
					} catch (JRException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	
					}
				
				
			}else{
				error("No se Encontro el reporte "+fileName);
				this.exit_command();
				
			}
			
	}
		
	public void pdfReport(String fileName,HashMap parameter,String output)
	{
		 File dir=new File (".");
			String directory="";
			try {
				directory=dir.getCanonicalPath();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			directory+="\\reports\\"+fileName;
			System.out.println("Abriendo "+directory);
			this.getConstructor().getConnectionHandler().getDefaultConnector().connect();
			Connection con = this.getConstructor().getConnectionHandler().getDefaultConnector().getConnection();
			
			
			//Way 1
			/*JasperDesign jasperDesign = JasperManager.loadXmlDesign(fileName);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint print = JasperFillManager.fillReport(jasperReport, parameter, con);*/

			//Way 2
			/*JasperReport jasperReport = JasperCompileManager.compileReport(fileName);
			JasperPrint print = JasperFillManager.fillReport(jasperReport, parameter, con);*/

			/*Way 3 (Here the parameter file should be in .jasper extension i.e., the compiled report)*/
			File file=new File(directory);
			if (file.exists()){
				JasperPrint print=null;
				try {
					  JasperReport masterReport = null;
			            try
			            {
			                masterReport = (JasperReport) JRLoader.loadObject(file);
			            }
			            catch (Exception e)
			            {
			            	this.displayError("Error Iniciando Reporte "+fileName, "No se Pudo iniciar el reporte", e.getMessage(), e);
			            
			            }    
			            if (masterReport!=null){
			            	print=JasperFillManager.fillReport(masterReport, parameter, con);
			      

			            }
					
					//print = JasperFillManager.fillReport(directory, parameter, con);
				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					this.displayError("Error Iniciando Reporte "+fileName, "No se Pudo iniciar el reporte", e.getMessage(), e);
				}
				if (print!=null){
					
			      	JRPdfExporter exporter = new JRPdfExporter();
	            	exporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, print);
	            	                exporter.setParameter(JRPdfExporterParameter.OUTPUT_FILE_NAME,output);
	            	                
	            	try {
						exporter.exportReport();
					} catch (JRException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	
					}
				
				
			}else{
				error("No se Encontro el reporte "+fileName);
				this.exit_command();
				
			}
			
	}

	
	
	public void viewReport(String fileName,HashMap parameter)
	{
	
	 File dir=new File (".");
	String directory="";
	try {
		directory=dir.getCanonicalPath();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	if(this.getOS().toLowerCase().contains("linux")){
		directory+="/reports/"+fileName;	
	}else{
		directory+="\\reports\\"+fileName;	
	}
	
	System.out.println("Abriendo "+directory);
	
	Connection con = this.getConstructor().getConnectionHandler().getDefaultConnector().getConnection();
	
	
	//Way 1
	/*JasperDesign jasperDesign = JasperManager.loadXmlDesign(fileName);
	JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
	JasperPrint print = JasperFillManager.fillReport(jasperReport, parameter, con);*/

	//Way 2
	/*JasperReport jasperReport = JasperCompileManager.compileReport(fileName);
	JasperPrint print = JasperFillManager.fillReport(jasperReport, parameter, con);*/

	/*Way 3 (Here the parameter file should be in .jasper extension i.e., the compiled report)*/
	File file=new File(directory);
	if (file.exists()){
		JasperPrint print=null;
		try {
			  JasperReport masterReport = null;
	            try
	            {
	                masterReport = (JasperReport) JRLoader.loadObject(file);
	            }
	            catch (Exception e)
	            {
	            	this.displayError("Error Iniciando Reporte "+fileName, "No se Pudo iniciar el reporte", e.getMessage(), e);
	            
	            }    
	            if (masterReport!=null){
	            	print=JasperFillManager.fillReport(masterReport, parameter, con);    	
	            }
			
			//print = JasperFillManager.fillReport(directory, parameter, con);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.displayError("Error Iniciando Reporte "+fileName, "No se Pudo iniciar el reporte", e.getMessage(), e);
		}
		if (print!=null){
			final JasperPrint _print=print;
			if (javax.swing.SwingUtilities.isEventDispatchThread()){
				
				
				viewer=new JRViewer(print);
				Container c=frame.getContentPane();
				viewer.repaint();
				c.add(viewer);
				c.repaint();
				
				frame.repaint();
			}else{
				Runnable _execute=new Runnable(){
					public void run(){
				
						viewer=new JRViewer(_print);
						Container c=frame.getContentPane();
						c.add(viewer);
						c.repaint();
						viewer.repaint();
						frame.repaint();
					}
				};
				javax.swing.SwingUtilities.invokeLater(_execute);
			}	
		}
		
		
	}else{
		error("No se Encontro el reporte "+fileName);
		this.exit_command();
		
	}
	
	}

	

	
}
