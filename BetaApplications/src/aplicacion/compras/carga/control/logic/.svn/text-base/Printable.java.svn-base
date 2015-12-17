package aplicacion.compras.carga.control.logic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
public class Printable extends JLabel implements java.awt.print.Printable {
	private ImageIcon i =null;
		
	public Printable(ImageIcon i){
		super(i);
		this.i=i;
		
	}
	
	public void print(){
    	PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable(this);
        if (printJob.printDialog())
          try { 
            printJob.print();
          } catch(PrinterException pe) {
            System.out.println("Error printing: " + pe);
       }
    }
	
	 public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
	        if (pageIndex > 0) {
	          return(NO_SUCH_PAGE);
	        } else {
	          Graphics2D g2d = (Graphics2D)g;
	          g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
	          
	          g2d.scale(640, 480);
	          // Turn off double buffering
	          paint(g2d);
	          // Turn double buffering back on
	          return(PAGE_EXISTS);
	        }
	      }
	 

}
