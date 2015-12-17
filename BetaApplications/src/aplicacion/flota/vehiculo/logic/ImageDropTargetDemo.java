package aplicacion.flota.vehiculo.logic;
import java.awt.*;
import javax.swing.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.dnd.peer.*;
import java.awt.datatransfer.*;
import javax.swing.TransferHandler;
import java.util.*;
import java.io.*;
import java.net.*;

public class ImageDropTargetDemo extends JPanel 
implements DropTargetListener {
DropTarget dropTarget;
JLabel dropHereLabel;
static DataFlavor urlFlavor, uriListFlavor, macPictStreamFlavor;
static {

	try { 
		urlFlavor = 
		new DataFlavor ("application/x-java-url; class=java.net.URL"); 
		uriListFlavor = 
		new DataFlavor ("text/uri-list; class=java.lang.String");
	} catch (ClassNotFoundException cnfe) { 
		cnfe.printStackTrace( );
	}
}

public ImageDropTargetDemo( )  {
	super(new BorderLayout( ));
	dropHereLabel = new JLabel (" Drop here ",
	
					SwingConstants.CENTER);    
	dropHereLabel.setFont (getFont( ).deriveFont (Font.BOLD, 24.0f)); 
	add (dropHereLabel, BorderLayout.CENTER); 
	// set up drop target stuff
	dropTarget = new DropTarget (dropHereLabel, this);
}

public void drop (DropTargetDropEvent dtde) { 
	System.out.println ("drop");
	dtde.acceptDrop (DnDConstants.ACTION_COPY_OR_MOVE);   
	Transferable trans = dtde.getTransferable( );
	System.out.println ("Flavors:"); 
	dumpDataFlavors (trans); boolean gotData = false;
	try {
		// try to get an image
		if (trans.isDataFlavorSupported (DataFlavor.imageFlavor)) { 
			System.out.println ("image flavor is supported"); 
			Image img = (Image) trans.getTransferData (DataFlavor.imageFlavor); 
			showImageInNewFrame (img); 
			gotData = true;
		} else if (trans.isDataFlavorSupported (
			DataFlavor.javaFileListFlavor)) {
			System.out.println ("javaFileList is supported");
			java.util.List list = (java.util.List)
			trans.getTransferData (DataFlavor.javaFileListFlavor);
			ListIterator it = list.listIterator( );    
			while (it.hasNext( )) {
			File f = (File) it.next( );
			ImageIcon icon = new ImageIcon (f.getAbsolutePath( ));
			showImageInNewFrame (icon);
			}
			gotData = true;
		} else if (trans.isDataFlavorSupported (uriListFlavor)) {
			System.out.println ("uri-list flavor is supported"); 
			String uris = (String)
			trans.getTransferData (uriListFlavor);

			// url-lists are defined by rfc 2483 as crlf-delimited 
			StringTokenizer izer = new StringTokenizer (uris, "\r\n");   
			while (izer.hasMoreTokens ( )) {
			String uri = izer.nextToken( );
			System.out.println (uri);
			ImageIcon icon = new ImageIcon (uri);
			showImageInNewFrame (icon);
			}
			gotData = true;
		} else if (trans.isDataFlavorSupported (urlFlavor)) {
			System.out.println ("url flavor is supported");
			URL url = (URL) trans.getTransferData (urlFlavor);
			System.out.println (url.toString( ));
			ImageIcon icon = new ImageIcon (url);
			showImageInNewFrame (icon);
			gotData = true;
		}
	} catch (Exception e) {
		e.printStackTrace( );
	} finally {
		System.out.println ("gotData is " + gotData);
		dtde.dropComplete (gotData);
	}
}



// drop target listener events

public void dragEnter (DropTargetDragEvent dtde) {}

public void dragExit (DropTargetEvent dte) {}

public void dragOver (DropTargetDragEvent dtde) {}

// drop( ) method listed below

public void dropActionChanged (DropTargetDragEvent dtde) {}

public void showImageInNewFrame (ImageIcon icon) {
	JFrame frame = new JFrame( );
	frame.getContentPane( ).add (new JLabel (icon));
	frame.pack( );
	frame.setVisible(true);
}
public void showImageInNewFrame (Image image) {
	showImageInNewFrame (new ImageIcon (image));
}

private void dumpDataFlavors (Transferable trans) {
	System.out.println ("Flavors:");
	DataFlavor[] flavors = trans.getTransferDataFlavors( );
	for (int i=0; i<flavors.length; i++) {
		System.out.println ("*** " + i + ": " + flavors[i]);
	}
}

public static void main (String[] args) { 
	JFrame frame = new JFrame ("Image DropTarget Demo");
	ImageDropTargetDemo demoPanel = new ImageDropTargetDemo( ); 
	frame.getContentPane( ).add (demoPanel); 
	frame.pack( ); frame.setVisible(true);
}

}

