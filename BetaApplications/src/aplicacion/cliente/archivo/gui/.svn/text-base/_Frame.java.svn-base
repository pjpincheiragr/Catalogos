package aplicacion.cliente.archivo.gui;
import org.jdesktop.jxlayer.plaf.ext.*;
import org.jdesktop.jxlayer.plaf.*;
import org.jdesktop.jxlayer.JXLayer;
import org.jdesktop.jxlayer.plaf.effect.BufferedImageOpEffect;
import com.jhlabs.image.BoxBlurFilter;
import java.awt.image.ColorConvertOp;

import java.awt.color.ColorSpace;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;


import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;



import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;

import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.JComboBox;

public class _Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	
	private JPanel jPanel = null;

	private JLabel jLabel = null;

	 //  @jve:decl-index=0:
	private JPanel jPanel1 = null;

	private JScrollPane jScrollPane = null;

	private JTable jTable = null;
	private int[] sizes=null;
	private DefaultTableModel defaultTableModel;
	private String[] columns=new String[]{""};
	
	private int[] sizes1=null;
	private DefaultTableModel defaultTableModel1;
	private String[] columns1=new String[]{""};
	private String iduser="";
	private JButton _btn_rotulo = null;
	Font fuente=new Font("Arial", Font.PLAIN, 9);  //  @jve:decl-index=0:
	private JLabel calle = null;

	private JLabel jLabel1 = null;


	private JLabel jLabel2 = null;

	private JLabel jLabel3 = null;


	private JLabel jLabel4 = null;


	private JLabel jLabel5 = null;


	private JLabel jLabel6 = null;


	private JLabel jLabel7 = null;


	private JLabel jLabel8 = null;


	private JLabel jLabel9 = null;


	private JLabel jLabel10 = null;

	private JPanel jPanel3 = null;

	private JLabel jLabel11 = null;


	private JLabel jLabel12 = null;

	private JPanel jPanel4 = null;

	private JLabel jLabel14 = null;

	private JLabel jLabel15 = null;


	private JLabel jLabel17 = null;


	private JLabel jLabel18 = null;

	private JTextField _txt_tipo_doc = null;
	private JTextField _txt_cond_iva = null;
	private JTextField _txt_condicion_detalle = null;
	private JTextField _txt_tipo_doc_detalle = null;
	private JTextField _txt_cuit = null;
	private JTextField _txt_cond_venta = null;
	private JTextField _txt_condicion_venta_detalle = null;
	private JTextField _txt_idprovincia = null;
	private JTextArea  _txt_observaciones = null;
	private JTextField _txt_provincia = null;
	private JTextField _txt_piso = null;
	private JTextField _txt_contacto = null;
	private JTextField txt_email = null;
	private JTextField _txt_fax = null;
	private JTextField _txt_localidad = null;
	private JTextField _txt_postal = null;
	private JTextField _txt_numero = null;
	private JTextField _txt_idcliente = null;
	private JTextField _txt_proveedordescripcion = null;
	private JTextField _txt_domicilio = null;
	private JTextField _txt_telefono = null;
	private JTextField _txt_saldo = null;
	private JTextField _txt_debe = null;
	private JTextField _txt_haber = null;
	
	private JLabel jLabel19 = null;



	private JPanel General = null;  //  @jve:decl-index=0:visual-constraint="10,649"

	private JTabbedPane jTabbedPane = null;

	private JPanel Comprobantes = null;

	private JButton _btn_cobranza = null;
	private JButton _btn_nuevo = null;
	private JButton _btn_grabar = null;
	private JButton _btn_cancelar = null;
	private JLabel jLabel20 = null;

	private JLabel jLabel21 = null;

	private JLabel jLabel22 = null;

	private JButton _btn_buscar_cliente = null;

	private JButton _btn_actualizar_saldo = null;

	private JToolBar jToolBar = null;

	private JButton _btn_salir = null;

	private JButton _btn_reporte = null;
	private LockableUI lockableUI=null;

	private JButton _btn_analitico = null;

	private JButton _btn_error = null;

	private JTabbedPane jTabbedPane1 = null;

	private JPanel jPanel2 = null;

	private JToolBar jToolBar1 = null;

	private JScrollPane jScrollPane1 = null;

	private JTable jTable1 = null;

	private JTextField _txt_debe_alfa = null;

	private JTextField _txt_haber_alfa = null;

	private JTextField _txt_saldo_alfa = null;

	private JLabel jLabel13 = null;

	private JLabel jLabel16 = null;

	private JLabel jLabel23 = null;

	private JTextField _txt_saldo_total_beta = null;

	private JTextField _txt_saldo_total_alfa = null;

	private JTextField _txt_saldo_total = null;

	private JLabel jLabel24 = null;

	private JLabel jLabel25 = null;

	private JLabel jLabel26 = null;

	private JButton _btn_enviar_email = null;

	private JToolBar jToolBar2 = null;

	private JTextField _txt_limite_credito = null;

	private JLabel jLabel27 = null;

	private JLabel jLabel28 = null;

	private JComboBox _lst_categoria = null;

	private JLabel jLabel29 = null;

	private JTextField _txt_descuento = null;

	/**
	 * This is the default constructor
	 */
	public _Frame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(842, 541);
		this.setContentPane(getJContentPane());
		this.setResizable(false);
		this.setTitle("Maestro de Cliente");
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jLabel = new JLabel();
			jLabel.setText("idcliente");
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel.setBounds(new Rectangle(14, 24, 86, 14));
			jContentPane.add(this.get_txt_proveedordescripcion(), null);
			jContentPane.add(get_txt_idcliente(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJTabbedPane(), null);
			jContentPane.add(get_btn_buscar_cliente(), null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(get_btn_actualizar_saldo(), null);
			
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getJPanel() {
		if (jPanel == null) {
			jLabel12 = new JLabel();
			jLabel12.setBounds(new Rectangle(620, 2, 85, 14));
			jLabel12.setText("Ubicacion");
			jLabel12.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel12.setForeground(Color.DARK_GRAY);
			jLabel10 = new JLabel();
			jLabel10.setText("Observaciones");
			jLabel10.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel10.setBounds(new Rectangle(213, 43, 92, 14));
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(214, 39, 80, 14));
			jLabel9.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel9.setText("Provincia");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(276, 4, 41, 14));
			jLabel8.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel8.setText("Piso");
			jLabel7 = new JLabel();
			jLabel7.setText("Contacto");
			jLabel7.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel7.setBounds(new Rectangle(4, 42, 82, 14));
			jLabel6 = new JLabel();
			jLabel6.setText("e-mail");
			jLabel6.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel6.setBounds(new Rectangle(420, 5, 76, 14));
			jLabel5 = new JLabel();
			jLabel5.setText("Fax");
			jLabel5.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel5.setBounds(new Rectangle(213, 6, 60, 14));
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(61, 38, 117, 14));
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel4.setText("Localidad");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(4, 38, 49, 14));
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel3.setText("Postal");
			jLabel2 = new JLabel();
			jLabel2.setText("Telefono/Cel");
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel2.setBounds(new Rectangle(5, 6, 104, 14));
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(214, 4, 56, 14));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel1.setText("Numero");
			calle = new JLabel();
			calle.setBounds(new Rectangle(3, 4, 59, 14));
			calle.setFont(new Font("Dialog", Font.PLAIN, 10));
			calle.setText("Calle");
			
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBackground(new Color(200,200,210));
			jPanel.setBounds(new Rectangle(11, 4, 709, 78));
			jPanel.add(get_txt_domicilio(), null);
			jPanel.add(calle, null);
			jPanel.add(jLabel1, null);
			jPanel.add(get_txt_numero(), null);
			jPanel.add(jLabel3, null);
			jPanel.add(get_txt_postal(), null);
			jPanel.add(jLabel4, null);
			jPanel.add(get_txt_localidad(), null);
			jPanel.add(jLabel8, null);
			jPanel.add(get_txt_piso(), null);
			jPanel.add(jLabel9, null);
			jPanel.add(get_txt_provincia(), null);
			jPanel.add(jLabel12, null);
			jPanel.add(get_txt_idprovincia(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes idCliente	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idcliente() {
		if (_txt_idcliente == null) {
			_txt_idcliente = new JTextField();
			_txt_idcliente.setBounds(new Rectangle(10, 40, 139, 17));
				
		}
		return _txt_idcliente;
	}
	
		
	/**
	 * This method initializes ClienteDescripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_proveedordescripcion() {
		if (_txt_proveedordescripcion == null) {
			_txt_proveedordescripcion = new JTextField();
			_txt_proveedordescripcion.setBounds(new Rectangle(209, 39, 507, 18));
			
			_txt_proveedordescripcion.setFont(fuente);
		}
		return _txt_proveedordescripcion;
	}

	/**
	 * This method initializes _txt_domicilio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_domicilio() {
		if (_txt_domicilio == null) {
			_txt_domicilio = new JTextField();
			_txt_domicilio.setFont(fuente);
			_txt_domicilio.setBounds(new Rectangle(3, 19, 208, 18));
		}
		return _txt_domicilio;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel22 = new JLabel();
			jLabel22.setBounds(new Rectangle(653, 217, 71, 14));
			jLabel22.setText("Saldo");
			jLabel22.setBackground(Color.black);
			jLabel22.setFont(new Font("Arial", Font.PLAIN, 10));
			jLabel22.setForeground(Color.DARK_GRAY);
			Font fuente = new Font("Arial", Font.ITALIC, 13);
			jLabel22.setHorizontalAlignment(JTextField.RIGHT);
			jLabel21 = new JLabel();
			jLabel21.setBounds(new Rectangle(580, 218, 65, 12));
			jLabel21.setText("Haber");
			jLabel21.setBackground(Color.black);
			jLabel21.setForeground(Color.DARK_GRAY);
			jLabel21.setFont(new Font("Arial", Font.PLAIN, 10));
			
			jLabel21.setHorizontalAlignment(JTextField.RIGHT);
			jLabel20 = new JLabel();
			jLabel20.setBounds(new Rectangle(508, 217, 65, 12));
			jLabel20.setText("Debe");
			jLabel20.setBackground(Color.black);
			jLabel20.setForeground(Color.DARK_GRAY);
			jLabel20.setFont(new Font("Arial", Font.PLAIN, 10));
			jLabel20.setHorizontalAlignment(JTextField.RIGHT);
			
			
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setBackground(Color.lightGray);
			jPanel1.add(getJScrollPane(), null);
			jPanel1.add(get_txt_saldo(), null);
			jPanel1.add(get_txt_debe(), null);
			jPanel1.add(get_txt_haber(), null);
			jPanel1.add(jLabel20, null);
			jPanel1.add(jLabel21, null);
			jPanel1.add(jLabel22, null);
			jPanel1.add(getJToolBar1(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(7, 31, 722, 179));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	
	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
		}
		return jTable;
	}

	public void setJTable(JTable table){
		this.jTable=table;
		this.jScrollPane.setViewportView(jTable);
	}
	/**
	 * This method initializes _btn_rotulo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	
	public JButton get_btn_rotulo() {
		if (_btn_rotulo == null) {
			_btn_rotulo = new JButton();
			_btn_rotulo.setIcon(new ImageIcon(getClass().getResource("/icons/document-print.png")));
			_btn_rotulo.setToolTipText("Imprimir Rotulo");
		}
		return _btn_rotulo;
	}

	/**
	 * This method initializes _txt_numero	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_numero() {
		if (_txt_numero == null) {
			_txt_numero = new JTextField();
			_txt_numero.setFont(fuente);
			_txt_numero.setBounds(new Rectangle(214, 19, 58, 18));
		}
		return _txt_numero;
	}

	/**
	 * This method initializes _txt_telefono	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_telefono() {
		if (_txt_telefono == null) {
			_txt_telefono = new JTextField();
			_txt_telefono.setBounds(new Rectangle(3, 22, 206, 18));
			_txt_telefono.setFont(fuente);
		}
		return _txt_telefono;
	}

	/**
	 * This method initializes _txt_postal	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_postal() {
		if (_txt_postal == null) {
			_txt_postal = new JTextField();
			_txt_postal.setFont(fuente);
			_txt_postal.setBounds(new Rectangle(3, 53, 56, 18));
		}
		return _txt_postal;
	}

	/**
	 * This method initializes _txt_localidad	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_localidad() {
		if (_txt_localidad == null) {
			_txt_localidad = new JTextField();
			_txt_localidad.setFont(fuente);
			_txt_localidad.setBounds(new Rectangle(61, 53, 151, 18));
		}
		return _txt_localidad;
	}

	/**
	 * This method initializes _txt_fax	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_fax() {
		if (_txt_fax == null) {
			_txt_fax = new JTextField();
			_txt_fax.setBounds(new Rectangle(212, 22, 206, 18));
			_txt_fax.setFont(fuente);
		}
		return _txt_fax;
	}

	/**
	 * This method initializes txt_email	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_email() {
		if (txt_email == null) {
			txt_email = new JTextField();
			txt_email.setBounds(new Rectangle(420, 22, 264, 18));
		}
		return txt_email;
	}

	/**
	 * This method initializes _txt_contacto	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_contacto() {
		if (_txt_contacto == null) {
			_txt_contacto = new JTextField();
			_txt_contacto.setBounds(new Rectangle(4, 58, 205, 18));
		}
		return _txt_contacto;
	}

	/**
	 * This method initializes _txt_piso	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_piso() {
		if (_txt_piso == null) {
			_txt_piso = new JTextField();
			_txt_piso.setBounds(new Rectangle(275, 19, 49, 18));
		}
		return _txt_piso;
	}

	/**
	 * This method initializes _txt_provincia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_provincia() {
		if (_txt_provincia == null) {
			_txt_provincia = new JTextField();
			_txt_provincia.setBounds(new Rectangle(251, 54, 125, 18));
		}
		return _txt_provincia;
	}

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(606, 6, 92, 13));
			jLabel11.setText("Comunicacion");
			jLabel11.setFont(new Font("Dialog", Font.PLAIN, 10));
			
			jLabel11.setForeground(Color.DARK_GRAY);
			jPanel3 = new JPanel();
			jPanel3.setLayout(null);
			jPanel3.setBackground(new Color(200,200,210));
			jPanel3.setBounds(new Rectangle(11, 85, 708, 126));
			jPanel3.add(get_txt_contacto(), null);
			jPanel3.add(jLabel10, null);
			jPanel3.add(jLabel7, null);
			jPanel3.add(get_txt_telefono(), null);
			jPanel3.add(jLabel2, null);
			jPanel3.add(jLabel5, null);
			jPanel3.add(get_txt_fax(), null);
			jPanel3.add(jLabel6, null);
			jPanel3.add(get_txt_email(), null);
			jPanel3.add(jLabel11, null);
			jPanel3.add(get_txt_observaciones(), null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes _txt_observaciones	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea get_txt_observaciones() {
		if (_txt_observaciones == null) {
			_txt_observaciones = new JTextArea();
			_txt_observaciones.setWrapStyleWord(true);
			_txt_observaciones.setBounds(new Rectangle(213, 59, 471, 56));
		}
		return _txt_observaciones;
	}

	/**
	 * This method initializes jPanel4	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			
			jLabel29 = new JLabel();
			jLabel29.setBounds(new Rectangle(324, 87, 78, 16));
			jLabel29.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel29.setText("Descuento");
			jLabel28 = new JLabel();
			jLabel28.setBounds(new Rectangle(6, 86, 111, 16));
			jLabel28.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel28.setText("Categoria");
			jLabel27 = new JLabel();
			jLabel27.setBounds(new Rectangle(212, 87, 84, 17));
			jLabel27.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel27.setText("Limite Credito");
			jLabel19 = new JLabel();
			jLabel19.setBounds(new Rectangle(4, 43, 125, 16));
			jLabel19.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel19.setText("Condicion Venta");
			jLabel18 = new JLabel();
			jLabel18.setBounds(new Rectangle(378, 0, 57, 17));
			jLabel18.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel18.setText("Doc/Cuit");
			jLabel17 = new JLabel();
			jLabel17.setBounds(new Rectangle(213, 0, 69, 18));
			jLabel17.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel17.setText("Tipo Doc");
			jLabel15 = new JLabel();
			jLabel15.setBounds(new Rectangle(5, 2, 129, 17));
			jLabel15.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel15.setText("Condicion IVA");
			jLabel14 = new JLabel();
			jLabel14.setBounds(new Rectangle(619, 4, 85, 16));
			jLabel14.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel14.setText("Condiciones");
			jPanel4 = new JPanel();
			jPanel4.setLayout(null);
			jPanel4.setBounds(new Rectangle(13, 214, 708, 127));
			jPanel4.add(jLabel14, null);
			jPanel4.add(jLabel15, null);
			jPanel4.add(get_txt_cond_iva(), null);
			jPanel4.add(get_txt_condicion_detalle(), null);
			jPanel4.add(jLabel17, null);
			jPanel4.add(get_txt_tipo_doc(), null);
			jPanel4.add(jLabel18, null);
			jPanel4.add(get_txt_tipo_doc_detalle(), null);
			jPanel4.add(get_txt_cuit(), null);
			jPanel4.add(jLabel19, null);
			jPanel4.add(get_txt_cond_venta(), null);
			jPanel4.add(get_txt_condicion_venta_detalle(), null);
			jPanel4.setBackground(new Color(200,200,210));
			jPanel4.add(get_txt_limite_credito(), null);
			jPanel4.add(jLabel27, null);
			jPanel4.add(jLabel28, null);
			jPanel4.add(get_lst_categoria(), null);
			jPanel4.add(jLabel29, null);
			jPanel4.add(get_txt_descuento(), null);
		}
		return jPanel4;
	}

	/**
	 * This method initializes _txt_cond_iva	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_cond_iva() {
		if (_txt_cond_iva == null) {
			_txt_cond_iva = new JTextField();
			_txt_cond_iva.setBounds(new Rectangle(5, 20, 47, 18));
		}
		return _txt_cond_iva;
	}

	/**
	 * This method initializes _txt_condicion_detalle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_condicion_detalle() {
		if (_txt_condicion_detalle == null) {
			_txt_condicion_detalle = new JTextField();
			_txt_condicion_detalle.setEditable(false);
			_txt_condicion_detalle.setFont(fuente);
			_txt_condicion_detalle.setBounds(new Rectangle(55, 20, 155, 18));
		}
		return _txt_condicion_detalle;
	}

	/**
	 * This method initializes _txt_tipo_doc	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_tipo_doc() {
		if (_txt_tipo_doc == null) {
			_txt_tipo_doc = new JTextField();
			_txt_tipo_doc.setBounds(new Rectangle(213, 20, 49, 18));
		}
		return _txt_tipo_doc;
	}

	/**
	 * This method initializes _txt_tipo_doc_detalle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_tipo_doc_detalle() {
		if (_txt_tipo_doc_detalle == null) {
			_txt_tipo_doc_detalle = new JTextField();
			_txt_tipo_doc_detalle.setEditable(false);
			_txt_tipo_doc_detalle.setFont(fuente);
			_txt_tipo_doc_detalle.setBounds(new Rectangle(266, 20, 100, 18));
		}
		return _txt_tipo_doc_detalle;
	}

	/**
	 * This method initializes _txt_cuit	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_cuit() {
		if (_txt_cuit == null) {
			_txt_cuit = new JTextField();
			_txt_cuit.setBounds(new Rectangle(378, 20, 164, 18));
		}
		return _txt_cuit;
	}

	/**
	 * This method initializes _txt_cond_venta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_cond_venta() {
		if (_txt_cond_venta == null) {
			_txt_cond_venta = new JTextField();
			_txt_cond_venta.setBounds(new Rectangle(6, 60, 45, 18));
		}
		return _txt_cond_venta;
	}

	/**
	 * This method initializes _txt_condicion_venta_detalle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_condicion_venta_detalle() {
		if (_txt_condicion_venta_detalle == null) {
			_txt_condicion_venta_detalle = new JTextField();
			_txt_condicion_venta_detalle.setEditable(false);
			_txt_condicion_venta_detalle.setFont(fuente);
			_txt_condicion_venta_detalle.setBounds(new Rectangle(55, 61, 157, 18));
		}
		return _txt_condicion_venta_detalle;
	}

	/**
	 * This method initializes _txt_idprovincia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_idprovincia() {
		if (_txt_idprovincia == null) {
			_txt_idprovincia = new JTextField();
			_txt_idprovincia.setBounds(new Rectangle(214, 54, 36, 18));
		}
		return _txt_idprovincia;
	}

	/**
	 * This method initializes General	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getGeneral() {
		if (General == null) {
			General = new JPanel();
			General.setLayout(null);
			General.setSize(new Dimension(720, 346));
			
			General.add(getJPanel(), null);
			General.add(getJPanel3(), null);
			General.add(getJPanel4(), null);
			
		}
		return General;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(14, 59, 793, 435));
			jTabbedPane.setFont(new Font("Dialog", Font.PLAIN, 10));
			jTabbedPane.addTab("General", null, this.getJXLayerUI(), null);
			jTabbedPane.addTab("Saldo", null, getComprobantes(), null);
		}
		return jTabbedPane;
	}
	
	/**
	 * This method initializes Comprobantes	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getComprobantes() {
		if (Comprobantes == null) {
			jLabel26 = new JLabel();
			jLabel26.setBounds(new Rectangle(568, 355, 57, 19));
			jLabel26.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel26.setText("Total");
			jLabel25 = new JLabel();
			jLabel25.setBounds(new Rectangle(569, 330, 56, 18));
			jLabel25.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel25.setText("Alfa");
			jLabel24 = new JLabel();
			jLabel24.setBounds(new Rectangle(569, 307, 56, 17));
			jLabel24.setFont(new Font("Dialog", Font.BOLD, 10));
			jLabel24.setText("Beta");
			Comprobantes = new JPanel();
			Comprobantes.setLayout(null);
			Comprobantes.add(getJTabbedPane1(), null);
			Comprobantes.add(get_txt_saldo_total_beta(), null);
			Comprobantes.add(get_txt_saldo_total_alfa(), null);
			Comprobantes.add(get_txt_saldo_total(), null);
			Comprobantes.add(jLabel24, null);
			Comprobantes.add(jLabel25, null);
			Comprobantes.add(jLabel26, null);
		}
		return Comprobantes;
	}

	/**
	 * This method initializes _btn_cobranza	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cobranza() {
		if (_btn_cobranza == null) {
			_btn_cobranza = new JButton();
			_btn_cobranza.setIcon(new ImageIcon(getClass().getResource("/icons/cash-icon.png")));
			_btn_cobranza.setToolTipText("Cobranza");
		}
		return _btn_cobranza;
	}

	/**
	 * This method initializes _btn_nuevo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_nuevo() {
		if (_btn_nuevo == null) {
			_btn_nuevo = new JButton();
			_btn_nuevo.setToolTipText("Nuevo Cliente");
			_btn_nuevo.setIcon(new ImageIcon(getClass().getResource("/icons/stock_new-text.png")));
			
		}
		return _btn_nuevo;
	}

	/**
	 * This method initializes _btn_grabar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_grabar() {
		if (_btn_grabar == null) {
			_btn_grabar = new JButton();
			_btn_grabar.setText("");
			_btn_grabar.setIcon(new ImageIcon(getClass().getResource("/icons/document-save.png")));
			_btn_grabar.setToolTipText("Grabar");
		}
		return _btn_grabar;
	}

	/**
	 * This method initializes _txt_saldo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_saldo() {
		if (_txt_saldo == null) {
			_txt_saldo = new JTextField();
			_txt_saldo.setEditable(false);
			_txt_saldo.setBackground(Color.black);
			_txt_saldo.setText("0.00");
			_txt_saldo.setForeground(Color.white);
			Font fuente=new Font("Arial", Font.ITALIC, 11);
			_txt_saldo.setFont(fuente);
			_txt_saldo.setHorizontalAlignment(JTextField.RIGHT);
			_txt_saldo.setBounds(new Rectangle(648, 233, 80, 18));
		}
		return _txt_saldo;
	}

	/**
	 * This method initializes _txt_debe	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_debe() {
		if (_txt_debe == null) {
			_txt_debe = new JTextField();
			_txt_debe.setBackground(Color.black);
			_txt_debe.setText("0.00");
			_txt_debe.setForeground(Color.white);
			Font fuente=new Font("Arial", Font.ITALIC, 11);
			_txt_debe.setFont(fuente);
			_txt_debe.setHorizontalAlignment(JTextField.RIGHT);
			_txt_debe.setBounds(new Rectangle(506, 233, 70, 18));
		}
		return _txt_debe;
	}

	/**
	 * This method initializes _txt_haber	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_haber() {
		if (_txt_haber == null) {
			_txt_haber = new JTextField();
			_txt_haber.setBackground(Color.black);
			_txt_haber.setText("0.00");
			_txt_haber.setForeground(Color.white);
			Font fuente=new Font("Arial", Font.ITALIC, 11);
			_txt_haber.setFont(fuente);
			_txt_haber.setHorizontalAlignment(JTextField.RIGHT);
			_txt_haber.setBounds(new Rectangle(577, 233, 70, 18));
		}
		return _txt_haber;
	}

	/**
	 * This method initializes _btn_cancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_cancelar() {
		if (_btn_cancelar == null) {
			_btn_cancelar = new JButton();
			_btn_cancelar.setIcon(new ImageIcon(getClass().getResource("/icons/stock_calc-cancel.png")));
			_btn_cancelar.setToolTipText("Cancelar");
		}
		return _btn_cancelar;
	}
	
	/**
	 * This method initializes _btn_buscar_cliente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_buscar_cliente() {
		if (_btn_buscar_cliente == null) {
			_btn_buscar_cliente = new JButton();
			_btn_buscar_cliente.setBounds(new Rectangle(151, 39, 25, 18));
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/search.png");
			_btn_buscar_cliente.setIcon(new ImageIcon(resourceURL));
		}
		return _btn_buscar_cliente;
	}

	/**
	 * This method initializes _btn_actualizar_saldo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_actualizar_saldo() {
		if (_btn_actualizar_saldo == null) {
			_btn_actualizar_saldo = new JButton();
			URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/stock_refresh.png");
			_btn_actualizar_saldo.setIcon(new ImageIcon(resourceURL));
			_btn_actualizar_saldo.setBounds(new Rectangle(178, 38, 28, 19));
		}
		return _btn_actualizar_saldo;
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setSize(new Dimension(832, 24));
			jToolBar.setLocation(new Point(0, 0));
			jToolBar.setFloatable(false);
			jToolBar.add(get_btn_nuevo());
			jToolBar.add(get_btn_grabar());
			jToolBar.add(get_btn_cancelar());
			jToolBar.add(get_btn_reporte());
			jToolBar.add(get_btn_cobranza());
			jToolBar.add(get_btn_analitico());
			jToolBar.add(get_btn_rotulo());
			jToolBar.add(get_btn_enviar_email());
			jToolBar.add(get_btn_salir());
			jToolBar.addSeparator();
			jToolBar.add(get_btn_error());
		}
		return jToolBar;
	}

	/**
	 * This method initializes _btn_salir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_salir() {
		if (_btn_salir == null) {
			_btn_salir = new JButton();
			_btn_salir.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-logout.png")));
		}
		return _btn_salir;
	}

	/**
	 * This method initializes _btn_reporte	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_reporte() {
		if (_btn_reporte == null) {
			_btn_reporte = new JButton();
			_btn_reporte.setIcon(new ImageIcon(getClass().getResource("/icons/stock_text_right.png")));
			_btn_reporte.setToolTipText("Reporte De Cuenta");
		}
		return _btn_reporte;
	}

	public JXLayer<JComponent> getJXLayerUI(){
		JPanel panel = this.getGeneral();
		//panel.setBounds(new Rectangle(580, 83, 26, 32));
		JXLayer<JComponent> l = new JXLayer<JComponent>(panel, this.getLockableUI());
		return l;
		
	}
	
	public LockableUI getLockableUI(){
		if (lockableUI==null){
			lockableUI= new LockableUI();        
			BoxBlurFilter blur=new BoxBlurFilter(2,2,2);
			BufferedImageOpEffect effect = new BufferedImageOpEffect(blur);
			lockableUI.setLockedEffects(effect);
			
			
			lockableUI.setLocked(true);
		}
		return lockableUI; 

	}

	/**
	 * This method initializes _btn_analitico	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_analitico() {
		if (_btn_analitico == null) {
			_btn_analitico = new JButton();
			_btn_analitico.setIcon(new ImageIcon(getClass().getResource("/icons/stock_hyperlink-internet-search.png")));
		}
		return _btn_analitico;
	}

	/**
	 * This method initializes _btn_error	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_error() {
		if (_btn_error == null) {
			_btn_error = new JButton();
			_btn_error.setIcon(new ImageIcon(getClass().getResource("/icons/gtk-media-record.png")));
			_btn_error.setToolTipText("Envio de Informacion/Error a Sistemas");
		}
		return _btn_error;
	}

	/**
	 * This method initializes jTabbedPane1	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane1() {
		if (jTabbedPane1 == null) {
			jTabbedPane1 = new JTabbedPane();
			jTabbedPane1.setBounds(new Rectangle(6, 9, 768, 289));
			jTabbedPane1.addTab("Alfa", null, getJPanel2(), null);
			jTabbedPane1.addTab("Beta", null, getJPanel1(), null);
		}
		return jTabbedPane1;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jLabel23 = new JLabel();
			jLabel23.setBounds(new Rectangle(684, 214, 42, 17));
			jLabel23.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel23.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel23.setText("Saldo");
			jLabel16 = new JLabel();
			jLabel16.setBounds(new Rectangle(591, 214, 53, 17));
			jLabel16.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel16.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel16.setText("Haber");
			jLabel13 = new JLabel();
			jLabel13.setBounds(new Rectangle(508, 215, 66, 16));
			jLabel13.setFont(new Font("Dialog", Font.PLAIN, 10));
			jLabel13.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel13.setText("Debe");
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.add(getJScrollPane1(), null);
			jPanel2.add(get_txt_debe_alfa(), null);
			jPanel2.add(get_txt_haber_alfa(), null);
			jPanel2.add(get_txt_saldo_alfa(), null);
			jPanel2.add(jLabel13, null);
			jPanel2.add(jLabel16, null);
			jPanel2.add(jLabel23, null);
			jPanel2.add(getJToolBar2(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jToolBar1	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar1() {
		if (jToolBar1 == null) {
			jToolBar1 = new JToolBar();
			jToolBar1.setBounds(new Rectangle(1, 2, 734, 23));
			jToolBar1.setFloatable(false);
		}
		return jToolBar1;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(7, 31, 722, 179));
			jScrollPane1.setViewportView(getJTable1());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTable1	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTable1() {
		if (jTable1 == null) {
			jTable1 = new JTable();
		}
		return jTable1;
	}
	
	public void setJTable1(JTable table){
		this.jTable1=table;
		this.jScrollPane1.setViewportView(jTable1);
	}

	/**
	 * This method initializes _txt_debe_alfa	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_debe_alfa() {
		if (_txt_debe_alfa == null) {
			_txt_debe_alfa = new JTextField();
			_txt_debe_alfa.setBackground(Color.black);
			_txt_debe_alfa.setText("0.00");
			_txt_debe_alfa.setForeground(Color.white);
			Font fuente=new Font("Arial", Font.ITALIC, 11);
			_txt_debe_alfa.setFont(fuente);
			_txt_debe_alfa.setHorizontalAlignment(JTextField.RIGHT);
			_txt_debe_alfa.setBounds(new Rectangle(506, 233, 70, 18)); 
			 
		}
		return _txt_debe_alfa;
	}

	/**
	 * This method initializes _txt_haber_alfa	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_haber_alfa() {
		if (_txt_haber_alfa == null) {
			_txt_haber_alfa = new JTextField();
			 _txt_haber_alfa.setBackground(Color.black);
			_txt_haber_alfa.setText("0.00");
			_txt_haber_alfa.setForeground(Color.white);
			Font fuente=new Font("Arial", Font.ITALIC, 11);
			_txt_haber_alfa.setFont(fuente);
			_txt_haber_alfa.setHorizontalAlignment(JTextField.RIGHT);
			_txt_haber_alfa.setBounds(new Rectangle(577, 233, 70, 18));
			 
		}
		return _txt_haber_alfa;
	}

	/**
	 * This method initializes _txt_saldo_alfa	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_saldo_alfa() {
		if (_txt_saldo_alfa == null) {
			_txt_saldo_alfa = new JTextField();
			_txt_saldo_alfa.setEditable(false);
			_txt_saldo_alfa.setBackground(Color.black);
			_txt_saldo_alfa.setText("0.00");
			_txt_saldo_alfa.setForeground(Color.white);
			Font fuente=new Font("Arial", Font.ITALIC, 11);
			_txt_saldo_alfa.setFont(fuente);
			_txt_saldo_alfa.setHorizontalAlignment(JTextField.RIGHT);
			_txt_saldo_alfa.setBounds(new Rectangle(648, 233, 80, 18));
		}
		return _txt_saldo_alfa;
	}

	/**
	 * This method initializes _txt_saldo_total_beta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_saldo_total_beta() {
		if (_txt_saldo_total_beta == null) {
			_txt_saldo_total_beta = new JTextField();
			_txt_saldo_total_beta.setBounds(new Rectangle(629, 305, 106, 19));
			_txt_saldo_total_beta.setFont(new Font("Dialog", Font.BOLD, 10));
			_txt_saldo_total_beta.setEditable(false);
			_txt_saldo_total_beta.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_saldo_total_beta;
	}

	/**
	 * This method initializes _txt_saldo_total_alfa	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_saldo_total_alfa() {
		if (_txt_saldo_total_alfa == null) {
			_txt_saldo_total_alfa = new JTextField();
			_txt_saldo_total_alfa.setBounds(new Rectangle(629, 331, 106, 18));
			_txt_saldo_total_alfa.setFont(new Font("Dialog", Font.BOLD, 10));
			_txt_saldo_total_alfa.setEditable(false);
			_txt_saldo_total_alfa.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_saldo_total_alfa;
	}

	/**
	 * This method initializes _txt_saldo_total	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_saldo_total() {
		if (_txt_saldo_total == null) {
			_txt_saldo_total = new JTextField();
			_txt_saldo_total.setBounds(new Rectangle(629, 354, 105, 19));
			_txt_saldo_total.setFont(new Font("Dialog", Font.BOLD, 10));
			_txt_saldo_total.setEditable(false);
			_txt_saldo_total.setHorizontalAlignment(JTextField.RIGHT);
		}
		return _txt_saldo_total;
	}

	/**
	 * This method initializes _btn_enviar_email	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton get_btn_enviar_email() {
		if (_btn_enviar_email == null) {
			_btn_enviar_email = new JButton();
			_btn_enviar_email.setIcon(new ImageIcon(getClass().getResource("/icons/gnome-stock-mail-fwd.png")));
			_btn_enviar_email.setToolTipText("Enviar Email");
		}
		return _btn_enviar_email;
	}

	/**
	 * This method initializes jToolBar2	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar2() {
		if (jToolBar2 == null) {
			jToolBar2 = new JToolBar();
			jToolBar2.setBounds(new Rectangle(4, 4, 727, 21));
			jToolBar2.setFloatable(false);
		}
		return jToolBar2;
	}

	/**
	 * This method initializes _txt_limite_credito	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_limite_credito() {
		if (_txt_limite_credito == null) {
			_txt_limite_credito = new JTextField();
			_txt_limite_credito.setBounds(new Rectangle(211, 105, 100, 19));
			_txt_limite_credito.setHorizontalAlignment(JTextField.RIGHT);
			_txt_limite_credito.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_limite_credito;
	}

	/**
	 * This method initializes _lst_categoria	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox get_lst_categoria() {
		if (_lst_categoria == null) {
			_lst_categoria = new JComboBox();
			_lst_categoria.setBounds(new Rectangle(6, 106, 201, 18));
		}
		return _lst_categoria;
	}

	/**
	 * This method initializes _txt_descuento	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField get_txt_descuento() {
		if (_txt_descuento == null) {
			_txt_descuento = new JTextField();
			_txt_descuento.setBounds(new Rectangle(324, 105, 74, 19));
			_txt_descuento.setHorizontalAlignment(JTextField.RIGHT);
			_txt_descuento.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return _txt_descuento;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
