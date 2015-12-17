package aplicacion.sistema.autorizar.logic;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.*;
import javax.swing.JTextField;




import aplicacion.herramientas.java.sortableselector.logic.Filtro;
import aplicacion.herramientas.java.sortableselector.logic.columna;
import aplicacion.herramientas.java.table.CellEditor;
import aplicacion.herramientas.java.table.CheckBoxCellEditor;
import aplicacion.herramientas.java.table.Column;
import aplicacion.herramientas.java.table.CustomTable;
import aplicacion.modelo.interfaces._parametros;
import aplicacion.modelo.logic.Data;
import aplicacion.modelo.logic.Logic;
import aplicacion.sistema.autorizar.gui._Frame;
import aplicacion.sistema.autorizar.logic._Data;
import javax.crypto.KeyGenerator;

public class _Logic extends Logic {
	private _Frame frame;
	private _Data data;
	private aplicacion.herramientas.java.visualselector.constructor._Constructor vSelector = null;
	private aplicacion.sistema.autorizacion.constructor._Constructor aplicaciones = null;
	
	
	public void setFrame(JFrame _frame){
		this.frame=(_Frame)_frame;
		super.setFrame(_frame);
	}
	
	public void setData(Data _data){
		this.data=(_Data)_data;
		super.setData(_data);
	}
	
	public void initialize_encrypter(){
	
	}
	
	public void clean(){
		frame.get_txt_codigo_de_autorizacion().setText("");
		frame.get_txt_codigo_requerido().setText("");
	}
	
	public void focus(){
		frame.get_txt_codigo_de_autorizacion().requestFocusInWindow();
	}
	
	public void evaluate_codigo_requerido(JTextField tx){
		if (tx.getText().compareTo("")!=0){
			String _solucion=this.getCodigoDeAutorizacion(tx.getText());
			frame.get_txt_codigo_de_autorizacion().setText(_solucion);
		}
	}
	
}
