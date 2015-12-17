package aplicacion.herramientas.java.pdf.logic;

import aplicacion.herramientas.java.pdf.gui.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.*;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import aplicacion.modelo.logic.Logic;


public class _Logic extends Logic {

	private SwingController controller =null;
	private SwingViewBuilder factory =null;
	private JPanel viewerComponentPanel =null;
	private _Frame frame=null;
	
	
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	public void initSwing(){
		controller = new SwingController();
		factory = new SwingViewBuilder(controller);
		viewerComponentPanel = factory.buildViewerPanel();

        // add interactive mouse link annotation support via callback
        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));
		}
	
	public void exit_command(){
		controller.dispose();
		controller=null;
		factory=null;
		viewerComponentPanel=null;
		super.exit_command();
	}
	public void load(InputStream input,int _page){
		
		
		
		frame.getContentPane().add(viewerComponentPanel);
		this.maximizar();
        // Now that the GUI is all in place, we can try openning a PDF
        controller.openDocument(input, "", "");
        
        controller.goToDeltaPage(_page);
        controller.setZoom(new Float(1.5));
        
        // show the component
        
        //frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setSize(frame.getHeight()-200,frame.getWidth()-100);
        frame.setVisible(true);	
	}
}
