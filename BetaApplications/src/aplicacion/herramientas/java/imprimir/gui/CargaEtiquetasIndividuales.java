/*      */ package aplicacion.herramientas.java.imprimir.gui;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.KeyboardFocusManager;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import javax.swing.DefaultCellEditor;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JCheckBox;
/*      */ import javax.swing.JFileChooser;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JProgressBar;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JTabbedPane;
/*      */ import javax.swing.JTable;
/*      */ import javax.swing.JTextField;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.Timer;
/*      */ import javax.swing.table.DefaultTableModel;
/*      */ import javax.swing.table.JTableHeader;
/*      */ import javax.swing.table.TableCellEditor;
/*      */ import javax.swing.table.TableColumn;
/*      */ import javax.swing.table.TableColumnModel;
/*      */ import javax.swing.table.TableModel;
/*      */ import stock1.ColumnHeaderListener;
/*      */ import stock1.Crono;
/*      */ import stock1.Etiquetas;
/*      */ import stock1.ProgressDialog;
/*      */ import stock1.SortButtonRenderer;
/*      */ import stock1.SortableTableModel;
/*      */ import stock1.SwingWorker;
import xml.XML_Etiquetas;
/*      */ 
/*      */ public class CargaEtiquetasIndividuales extends JFrame
/*      */ {
/*      */   private static final long serialVersionUID = 1L;

/*   82 */   private JPanel jContentPane = null;
/*   86 */   private JButton Generar = null;
/*   88 */   private JButton Orden = null;
/*   89 */   private JScrollPane jScrollPane = null;
/*   91 */   private JTable jTable = null;
/*  101 */   private JCheckBox seleccionar = null;
/*  102 */   private JButton Importar = null;
/*  103 */   private JPanel Imports = null;
/*  104 */   private JScrollPane jScrollPane_cptes = null;
/*  105 */   private JTable jTable_cptes = null;
/*  106 */   private JScrollPane jScrollPane_detalles = null;
/*  107 */   private JTable jTable_detalles = null;
/*  108 */   private JButton Agregar = null;
/*  109 */   private JButton Quitar = null;
/*  110 */   private JTabbedPane jTabbedPane_Gui = null;
/*  111 */   private JPanel jPanel_etiq = null;
/*  112 */   private JButton Quitar_Cpte = null;
/*  113 */   private JButton Agregar_items = null;
/*  114 */   private JButton Quitar_Items = null;
/*  115 */   private JCheckBox Seleccionar_items = null;
/*  116 */   private JCheckBox seleccionar_cptes = null;
/*  117 */   private JButton Agregar_etq = null;
/*      */ 
/*  120 */   private JButton agregar_items = null;
/*  121 */   private JButton guardar = null;
/*  122 */   private JLabel jLabel = null;
/*  123 */   private JTextField Vacias = null;
/*  124 */   private JButton Limpiar = null;
/*  125 */   private JButton ImportarV = null;
/*  126 */   private JCheckBox especiales = null;
/*  127 */   private JLabel jLabel1 = null;
/*  128 */   private JTextField Desde = null;
/*  129 */   private JTextField Unidades = null;
/*  130 */   private JLabel jLabel2 = null;
/*  131 */   private JLabel jLabel42 = null;
/*  132 */   private JTextField Hasta = null;
/*  133 */   private JButton importar_stock = null;
/*  134 */   private JCheckBox Stock_positivo = null;
/*  135 */   private JButton Control = null;
/*  136 */   private JButton Buscar = null;
/*  137 */   private JCheckBox solo_una_etiqueta = null;
/*  138 */   private JCheckBox permite_repeticiones = null;
/*  139 */   private JCheckBox codigos_no_existentes = null;
/*  140 */   private JCheckBox codigo_barra = null;
/*  141 */   private JButton Trabajo = null;
/*  143 */   private JButton Pendientes = null;
/*  145 */   private JTextField archivo = null;
/*      */   private ProgressDialog PD;
/*      */   private JProgressBar Pbar;
/*  153 */   private JTextField _txt_e_width = null;
/*  154 */   private JCheckBox _chk_quitar_prefijo = null;
/*      */ 
/*      */   public CargaEtiquetasIndividuales()
/*      */   {

/*  176 */     initialize();
/*      */   }
/*      */ 

/*      */ 
/*      */   private void initialize()
/*      */   {
/*  189 */     setSize(1024, 758);
/*  190 */     setContentPane(getJContentPane());
/*  191 */     setTitle("Etiquetas Manuales");
/*      */   }
/*      */ 
/*      */   private JPanel getJContentPane()
/*      */   {
/*  201 */     if (this.jContentPane == null) {
/*  202 */       this.jLabel2 = new JLabel();
/*  203 */       this.jLabel2.setBounds(new Rectangle(240, 118, 73, 19));
/*  204 */       this.jLabel2.setText("Hasta:");
/*  205 */       this.jLabel1 = new JLabel();
/*  206 */       this.jLabel1.setBounds(new Rectangle(15, 117, 101, 21));
/*  207 */       this.jLabel1.setText("idArticulo Desde:");
/*  208 */       this.jLabel = new JLabel();
/*  209 */       this.jLabel.setText("Etiquetas Vacias");
/*  210 */       this.jLabel.setSize(new Dimension(140, 20));
/*  211 */       this.jLabel.setLocation(new Point(510, 46));
/*      */ 
/*  213 */       this.jContentPane = new JPanel();
/*  214 */       this.jContentPane.setLayout(null);
/*  215 */       this.jContentPane.add(getGenerar(), null);
/*      */ 
/*  217 */       this.jContentPane.add(getImportar(), null);
/*  218 */       this.jContentPane.add(getJTabbedPane_Gui(), null);
/*  219 */       this.jContentPane.add(this.jLabel, null);
/*  220 */       this.jContentPane.add(getVacias(), null);
/*  221 */       this.jContentPane.add(getLimpiar(), null);
/*  222 */       this.jContentPane.add(getImportarV(), null);
/*  223 */       this.jContentPane.add(getEspeciales(), null);
/*  224 */       this.jContentPane.add(this.jLabel1, null);
/*  225 */       this.jContentPane.add(getDesde(), null);
/*  226 */       this.jContentPane.add(getArchivo(), null);
/*  227 */       this.jContentPane.add(getBuscar(), null);
/*  228 */       this.jContentPane.add(getGuardar(), null);
/*      */ 
/*  231 */       this.jContentPane.add(this.jLabel2, null);
/*  232 */       this.jContentPane.add(getHasta(), null);
/*  233 */       this.jContentPane.add(getImportar_stock(), null);
/*  234 */       this.jContentPane.add(getStock_positivo(), null);
/*  235 */       this.jContentPane.add(getControl(), null);
/*  236 */       this.jContentPane.add(getSolo_una_etiqueta(), null);
/*  237 */       this.jContentPane.add(getPermiteRepeticiones(), null);
/*  238 */       this.jContentPane.add(getCodigos_no_existentes(), null);
/*  239 */       this.jContentPane.add(getCodigo_barra(), null);
/*      */ 
/*  241 */       this.jContentPane.add(get_txt_e_width(), null);
/*  242 */       this.jContentPane.add(get_chk_quitar_prefijo(), null);
/*      */     }
/*  244 */     return this.jContentPane;
/*      */   }
/*      */ 
/*      */   private JButton getGenerar()
/*      */   {
/*  254 */     if (this.Generar == null) {
/*  255 */       this.Generar = new JButton();
/*  256 */       this.Generar.setToolTipText("Imprimir Etiquetas");
/*  259 */       this.Generar.setSize(new Dimension(181, 20));
/*  260 */       this.Generar.setLocation(new Point(227, 11));
/*  261 */       this.Generar.setFont(new Font("Dialog", 1, 10));
/*      */     }
/*  269 */     return this.Generar;
/*      */   }
/*      */ 
/*      */   private JScrollPane getJScrollPane()
/*      */   {
/*  278 */     if (this.jScrollPane == null) {
/*  279 */       this.jScrollPane = new JScrollPane();
/*  280 */       this.jScrollPane.setBounds(new Rectangle(5, 30, 750, 400));
/*  281 */       this.jScrollPane.setViewportView(getJTable());
/*      */     }
/*  283 */     return this.jScrollPane;
/*      */   }
/*      */ 
/*      */   private JTable getJTable()
/*      */   {
/*  292 */     if (this.jTable == null) {
/*  293 */       this.jTable = new JTable();
/*      */     }
/*  295 */     return this.jTable;
/*      */   }
/*      */ 
/*      */ 
/*      */   private JTextField getArchivo() {
	/*  682 */     if (this.archivo == null) {
	/*  683 */       this.archivo = new JTextField();
	/*  684 */       this.archivo.setBounds(new Rectangle(230, 80, 120, 19));
	/*      */     }
	/*  686 */     return this.archivo;
	/*      */   }

/*      */   private JButton getBuscar() {
/*  690 */     if (this.Buscar == null) {
/*  691 */       this.Buscar = new JButton();
/*  692 */       this.Buscar.setBounds(new Rectangle(350, 80, 29, 19));
/*  697 */       this.Buscar.setToolTipText("importar etiquetas desde archivo");
				}
 /*  705 */     return this.Buscar;
/*      */   }
/*      */ 
/*      */   private JButton getGuardar() {
/*  709 */     if (this.guardar == null) {
/*  710 */       this.guardar = new JButton();
/*  711 */       this.guardar.setBounds(new Rectangle(380, 80, 50, 19));
/*  715 */       this.guardar.setToolTipText("Exportar etiquetas a archivo");
/*      */     }
/*  723 */     return this.guardar;
/*      */   }
/*      */ 
/*      */ 
/*      */   private JCheckBox getSeleccionar()
/*      */   {
/* 2042 */     if (this.seleccionar == null) {
/* 2043 */       this.seleccionar = new JCheckBox();
/* 2044 */       this.seleccionar.setText("Seleccionar");
/* 2045 */       this.seleccionar.setBounds(new Rectangle(12, 12, 130, 15));
/*      */     }
/* 2054 */     return this.seleccionar;
/*      */   }
/*      */ 
/*      */   private JButton getImportar() {
/* 2095 */     if (this.Importar == null) {
/* 2096 */       this.Importar = new JButton();
/* 2097 */       this.Importar.setPreferredSize(new Dimension(153, 10));
/* 2098 */       this.Importar.setLocation(new Point(6, 10));
/* 2099 */       this.Importar.setSize(new Dimension(205, 20));
/* 2100 */       this.Importar.setFont(new Font("Dialog", 1, 10));
/* 2101 */       this.Importar.setText("Compras");
/* 2102 */       this.Importar.setToolTipText("Importar desde Compras");
/*      */     }
/* 2112 */     return this.Importar;
/*      */   }
/*      */ 
/*      */   private JPanel getImports()
/*      */   {
/* 2129 */     if (this.Imports == null) {
/* 2130 */       this.Imports = new JPanel();
/* 2131 */       this.Imports.setLayout(null);
/* 2132 */       this.Imports.add(getJScrollPane_cptes(), null);
/* 2133 */       this.Imports.add(getJScrollPane_detalles(), null);
/* 2134 */       this.Imports.add(getAgregar(), null);
/* 2135 */       this.Imports.add(getQuitar(), null);
/* 2136 */       this.Imports.add(getQuitar_Cpte(), null);
/* 2137 */       this.Imports.add(getAgregar_items(), null);
/* 2138 */       this.Imports.add(getQuitar_Items(), null);
/* 2139 */       this.Imports.add(getSeleccionar_items(), null);
/* 2140 */       this.Imports.add(getSeleccionar_cptes(), null);
/* 2141 */       this.Imports.add(getAgregar_etq(), null);
/* 2142 */       this.Imports.add(getAgregar_items2(), null);
/*      */     }
/* 2144 */     return this.Imports;
/*      */   }
/*      */ 
/*      */   private JScrollPane getJScrollPane_cptes()
/*      */   {
/* 2154 */     if (this.jScrollPane_cptes == null) {
/* 2155 */       this.jScrollPane_cptes = new JScrollPane();
/* 2156 */       this.jScrollPane_cptes.setBounds(new Rectangle(15, 20, 600, 140));
/* 2157 */       this.jScrollPane_cptes.setViewportView(getJTable_cptes());
/*      */     }
/* 2159 */     return this.jScrollPane_cptes;
/*      */   }
/*      */ 
/*      */   private JTable getJTable_cptes()
/*      */   {
/* 2169 */     if (this.jTable_cptes == null) {
/* 2170 */       this.jTable_cptes = new JTable();
/*      */     }
/* 2172 */     return this.jTable_cptes;
/*      */   }
/*      */ 
/*      */   private JScrollPane getJScrollPane_detalles()
/*      */   {
/* 2182 */     if (this.jScrollPane_detalles == null) {
/* 2183 */       this.jScrollPane_detalles = new JScrollPane();
/* 2184 */       this.jScrollPane_detalles.setBounds(new Rectangle(16, 183, 600, 300));
/* 2185 */       this.jScrollPane_detalles.setViewportView(getJTable_detalles());
/*      */     }
/* 2187 */     return this.jScrollPane_detalles;
/*      */   }
/*      */ 
/*      */   private JTable getJTable_detalles()
/*      */   {
/* 2197 */     if (this.jTable_detalles == null) {
/* 2198 */       this.jTable_detalles = new JTable();
/*      */     }
/* 2200 */     return this.jTable_detalles;
/*      */   }
/*      */ 
/*      */   private JButton getAgregar()
/*      */   {
/* 2210 */     if (this.Agregar == null) {
/* 2211 */       this.Agregar = new JButton();
/* 2212 */       this.Agregar.setBounds(new Rectangle(269, 266, 68, 15));
/* 2213 */       this.Agregar.setText("Agregar");
/*      */     }
/* 2215 */     return this.Agregar;
/*      */   }
/*      */ 
/*      */   private JButton getQuitar()
/*      */   {
/* 2225 */     if (this.Quitar == null) {
/* 2226 */       this.Quitar = new JButton();
/* 2227 */       this.Quitar.setBounds(new Rectangle(347, 263, 74, 19));
/* 2228 */       this.Quitar.setText("Quitar");
/*      */     }
/* 2230 */     return this.Quitar;
/*      */   }
/*      */ 
/*      */   private JTabbedPane getJTabbedPane_Gui()
/*      */   {
/* 2240 */     if (this.jTabbedPane_Gui == null) {
/* 2241 */       this.jTabbedPane_Gui = new JTabbedPane();
/* 2242 */       this.jTabbedPane_Gui.setBounds(new Rectangle(24, 145, 893, 526));
/* 2243 */       this.jTabbedPane_Gui.addTab("Etiquetas", null, getJPanel_etiq(), null);
/* 2244 */       this.jTabbedPane_Gui.addTab("Importaciones", null, getImports(), null);
/*      */     }
/* 2246 */     return this.jTabbedPane_Gui;
/*      */   }
/*      */ 
/*      */   private JPanel getJPanel_etiq()
/*      */   {
/* 2256 */     if (this.jPanel_etiq == null) {
/* 2257 */       this.jPanel_etiq = new JPanel();
/* 2258 */       this.jLabel42 = new JLabel();
/* 2259 */       this.jLabel42.setBounds(new Rectangle(10, 450, 73, 19));
/* 2260 */       this.jLabel42.setText("Unidades:");
/* 2261 */       this.jPanel_etiq.setLayout(null);
/* 2262 */       this.jPanel_etiq.add(getJScrollPane(), null);
/* 2263 */       this.jPanel_etiq.add(getSeleccionar(), null);
/* 2264 */       this.jPanel_etiq.add(this.jLabel42, null);
/* 2265 */       this.jPanel_etiq.add(getUnidades(), null);
/*      */     }
/* 2267 */     return this.jPanel_etiq;
/*      */   }
/*      */ 
/*      */   private JButton getQuitar_Cpte()
/*      */   {
/* 2277 */     if (this.Quitar_Cpte == null) {
/* 2278 */       this.Quitar_Cpte = new JButton();
/* 2279 */       this.Quitar_Cpte.setBounds(new Rectangle(634, 15, 91, 18));
/* 2280 */       this.Quitar_Cpte.setText("Quitar");
/*      */     }
/* 2282 */     return this.Quitar_Cpte;
/*      */   }
/*      */ 
/*      */   private JButton getAgregar_items()
/*      */   {
/* 2292 */     if (this.Agregar_items == null) {
/* 2293 */       this.Agregar_items = new JButton();
/* 2294 */       this.Agregar_items.setBounds(new Rectangle(633, 185, 98, 19));
/* 2295 */       this.Agregar_items.setText("Agregar");
/*      */     }
/* 2297 */     return this.Agregar_items;
/*      */   }
/*      */ 
/*      */   private JButton getQuitar_Items()
/*      */   {
/* 2307 */     if (this.Quitar_Items == null) {
/* 2308 */       this.Quitar_Items = new JButton();
/* 2309 */       this.Quitar_Items.setBounds(new Rectangle(631, 212, 104, 24));
/* 2310 */       this.Quitar_Items.setText("Quitar");
/*      */     }
/* 2312 */     return this.Quitar_Items;
/*      */   }
/*      */ 
/*      */   private JCheckBox getSeleccionar_items()
/*      */   {
/* 2322 */     if (this.Seleccionar_items == null) {
/* 2323 */       this.Seleccionar_items = new JCheckBox();
/* 2324 */       this.Seleccionar_items.setBounds(new Rectangle(21, 164, 112, 15));
/* 2325 */       this.Seleccionar_items.setText("Seleccionar");
/*      */     }
/* 2327 */     return this.Seleccionar_items;
/*      */   }
/*      */ 
/*      */   private JCheckBox getSeleccionar_cptes()
/*      */   {
/* 2337 */     if (this.seleccionar_cptes == null) {
/* 2338 */       this.seleccionar_cptes = new JCheckBox();
/* 2339 */       this.seleccionar_cptes.setBounds(new Rectangle(20, 4, 119, 13));
/* 2340 */       this.seleccionar_cptes.setText("Seleccionar");
/*      */     }
/* 2342 */     return this.seleccionar_cptes;
/*      */   }
/*      */ 
/*      */   private JButton getAgregar_etq()
/*      */   {
/* 2352 */     if (this.Agregar_etq == null) {
/* 2353 */       this.Agregar_etq = new JButton();
/* 2354 */       this.Agregar_etq.setBounds(new Rectangle(233, 4, 283, 13));
/* 2355 */       this.Agregar_etq.setText("Agregar estos comprobantes a etiquetas");
/*      */     }
/* 2363 */     return this.Agregar_etq;
/*      */   }
/*      */ 
/*      */   private JButton getAgregar_items2()
/*      */   {
/* 2373 */     if (this.agregar_items == null) {
/* 2374 */       this.agregar_items = new JButton();
/* 2375 */       this.agregar_items.setBounds(new Rectangle(268, 166, 270, 13));
/* 2376 */       this.agregar_items.setText("Agregar estos items a Etiquetas");
/*      */     }
/* 2384 */     return this.agregar_items;
/*      */   }
/*      */ 
/*      */   private JTextField getVacias()
/*      */   {
/* 2394 */     if (this.Vacias == null) {
/* 2395 */       this.Vacias = new JTextField();
/* 2396 */       this.Vacias.setLocation(new Point(654, 46));
/* 2397 */       this.Vacias.setSize(new Dimension(64, 20));
/*      */     }
/* 2399 */     return this.Vacias;
/*      */   }
/*      */ 
/*      */   private JButton getLimpiar()
/*      */   {
/* 2409 */     if (this.Limpiar == null) {
/* 2410 */       this.Limpiar = new JButton();
/* 2411 */       this.Limpiar.setText("Limpiar Todo");
/* 2412 */       this.Limpiar.setSize(new Dimension(166, 20));
/* 2413 */       this.Limpiar.setLocation(new Point(550, 11));
/*      */     }
/* 2421 */     return this.Limpiar;
/*      */   }
/*      */ 
/*      */   private JButton getImportarV()
/*      */   {
/* 2431 */     if (this.ImportarV == null) {
/* 2432 */       this.ImportarV = new JButton();
/* 2433 */       this.ImportarV.setBounds(new Rectangle(6, 32, 205, 20));
/* 2434 */       this.ImportarV.setText("Ventas");
/* 2435 */       this.ImportarV.setToolTipText("Importar desde Ventas");
/* 2436 */       this.ImportarV.setFont(new Font("Dialog", 1, 10));
/*      */     }
/* 2446 */     return this.ImportarV;
/*      */   }
/*      */ 
/*      */   private JCheckBox getEspeciales()
/*      */   {
/* 2456 */     if (this.especiales == null) {
/* 2457 */       this.especiales = new JCheckBox();
/* 2458 */       this.especiales.setBounds(new Rectangle(737, 74, 145, 20));
/* 2459 */       this.especiales.setText("Etiquetas Especiales");
/*      */     }
/* 2461 */     return this.especiales;
/*      */   }
/*      */ 
/*      */   private JTextField getDesde()
/*      */   {
/* 2471 */     if (this.Desde == null) {
/* 2472 */       this.Desde = new JTextField();
/* 2473 */       this.Desde.setBounds(new Rectangle(125, 117, 103, 19));
/*      */     }
/* 2475 */     return this.Desde;
/*      */   }
/*      */   private JTextField getUnidades() {
/* 2478 */     if (this.Unidades == null) {
/* 2479 */       this.Unidades = new JTextField();
/* 2480 */       this.Unidades.setEditable(false);
/* 2481 */       this.Unidades.setBounds(new Rectangle(150, 451, 103, 19));
/*      */     }
/* 2483 */     return this.Unidades;
/*      */   }
/*      */ 
/*      */   private JTextField getHasta()
/*      */   {
/* 2492 */     if (this.Hasta == null) {
/* 2493 */       this.Hasta = new JTextField();
/* 2494 */       this.Hasta.setBounds(new Rectangle(325, 117, 119, 21));
/*      */     }
/* 2496 */     return this.Hasta;
/*      */   }
/*      */ 
/*      */   private JButton getImportar_stock()
/*      */   {
/* 2506 */     if (this.importar_stock == null) {
/* 2507 */       this.importar_stock = new JButton();
/* 2508 */       this.importar_stock.setBounds(new Rectangle(460, 115, 137, 23));
/* 2509 */       this.importar_stock.setText("Stock");
/* 2510 */       this.importar_stock.setToolTipText("Importar desde Stock");
/* 2511 */     }
/* 2521 */     return this.importar_stock;
/*      */   }
/*      */ 
/*      */   private JCheckBox getStock_positivo()
/*      */   {
/* 2531 */     if (this.Stock_positivo == null) {
/* 2532 */       this.Stock_positivo = new JCheckBox();
/* 2533 */       this.Stock_positivo.setBounds(new Rectangle(619, 118, 90, 16));
/* 2534 */       this.Stock_positivo.setText("Stock > 0");
/*      */     }
/* 2536 */     return this.Stock_positivo;
/*      */   }
/*      */ 
/*      */   private JButton getControl()
/*      */   {
/* 2546 */     if (this.Control == null) {
/* 2547 */       this.Control = new JButton();
/* 2548 */       this.Control.setBounds(new Rectangle(6, 54, 205, 20));
/* 2549 */       this.Control.setToolTipText("Importar desde Control");
/* 2550 */       this.Control.setText("Control");
/* 2551 */       this.Control.setFont(new Font("Dialog", 1, 10));
/* 2552 */       
/*      */     }
/* 2561 */     return this.Control;
/*      */   }
/*      */ 
/*      */   private JCheckBox getSolo_una_etiqueta()
/*      */   {
/* 2571 */     if (this.solo_una_etiqueta == null) {
/* 2572 */       this.solo_una_etiqueta = new JCheckBox();
/* 2573 */       this.solo_una_etiqueta.setBounds(new Rectangle(718, 105, 150, 18));
/* 2574 */       this.solo_una_etiqueta.setText("Solo una Etiqueta");
/*      */     }
/* 2576 */     return this.solo_una_etiqueta;
/*      */   }
/*      */ 
/*      */   private JCheckBox getPermiteRepeticiones() {
/* 2580 */     if (this.permite_repeticiones == null) {
/* 2581 */       this.permite_repeticiones = new JCheckBox();
/* 2582 */       this.permite_repeticiones.setBounds(new Rectangle(718, 125, 150, 18));
/* 2583 */       this.permite_repeticiones.setText("Permite Repeticiones");
/*      */     }
/* 2585 */     return this.permite_repeticiones;
/*      */   }
/*      */ 
/*      */   private JCheckBox getCodigos_no_existentes()
/*      */   {
/* 2594 */     if (this.codigos_no_existentes == null) {
/* 2595 */       this.codigos_no_existentes = new JCheckBox();
/* 2596 */       this.codigos_no_existentes.setBounds(new Rectangle(739, 14, 225, 18));
/* 2597 */       this.codigos_no_existentes.setText("Permite Codigos no Existentes");
/*      */     }
/* 2599 */     return this.codigos_no_existentes;
/*      */   }
/*      */ 
/*      */   private JCheckBox getCodigo_barra()
/*      */   {
/* 2609 */     if (this.codigo_barra == null) {
/* 2610 */       this.codigo_barra = new JCheckBox();
/* 2611 */       this.codigo_barra.setBounds(new Rectangle(740, 41, 204, 20));
/* 2612 */       this.codigo_barra.setSelected(true);
/* 2613 */       this.codigo_barra.setText("Imprime Codigo de Barras");
/*      */     }
/* 2615 */     return this.codigo_barra;
/*      */   }
/*      */ 
/*      */   private String getLoadQuery(String formato) {
/* 2619 */     String q = "";
/* 2620 */     q = q + "select margen_izq,margen_sup, ";
/* 2621 */     q = q + "hoja_ancho,hoja_altura, ";
/* 2622 */     q = q + "columnas, ";
/* 2623 */     q = q + "filas, ";
/* 2624 */     q = q + "celda_ancho,celda_altura,celda_izq,celda_sup, ";
/* 2625 */     q = q + "margen_col,margen_row ";
/* 2626 */     q = q + "from formato ";
/* 2627 */     q = q + "where nombre like '" + formato + "' ";
/* 2628 */     return q;
/*      */   }
/*      */ 
/*      */   private JButton getTrabajo()
/*      */   {
/* 2636 */     if (this.Trabajo == null) {
/* 2637 */       this.Trabajo = new JButton();
/* 2638 */       this.Trabajo.setBounds(new Rectangle(227, 55, 180, 20));
/* 2639 */       this.Trabajo.setText("Importar de Trabajos");
/* 2640 */       this.Trabajo.setFont(new Font("Dialog", 1, 10));

/*      */     }
/* 2649 */     return this.Trabajo;
/*      */   }
/*      */ 
/*      */   private JButton getPendientes()
/*      */   {
/* 2659 */     if (this.Pendientes == null) {
/* 2660 */       this.Pendientes = new JButton();
/* 2661 */       this.Pendientes.setBounds(new Rectangle(8, 78, 202, 20));
/* 2662 */       this.Pendientes.setText("Importar Pendientes");
/* 2663 */       this.Pendientes.setFont(new Font("Dialog", 1, 10));

/*      */     }
/* 2671 */     return this.Pendientes;
/*      */   }
/*      */ 

/*      */ 
/*      */   private JTextField get_txt_e_width()
/*      */   {
/* 2743 */     if (this._txt_e_width == null) {
/* 2744 */       this._txt_e_width = new JTextField();
/* 2745 */       this._txt_e_width.setText("60");
/* 2746 */       this._txt_e_width.setBounds(new Rectangle(891, 78, 34, 16));
/*      */     }
/* 2748 */     return this._txt_e_width;
/*      */   }
/*      */ 
/*      */   private JCheckBox get_chk_quitar_prefijo()
/*      */   {
/* 2757 */     if (this._chk_quitar_prefijo == null) {
/* 2758 */       this._chk_quitar_prefijo = new JCheckBox();
/* 2759 */       this._chk_quitar_prefijo.setBounds(new Rectangle(930, 79, 81, 17));
/* 2760 */       this._chk_quitar_prefijo.setSelected(true);
/* 2761 */       this._chk_quitar_prefijo.setText("sin prefijo");
/*      */     }
/* 2763 */     return this._chk_quitar_prefijo;
/*      */   }
/*      */ 
/*      */   public static void main(String[] args) {
/* 2767 */     CargaEtiquetasIndividuales stk = new CargaEtiquetasIndividuales();

/* 2774 */     stk.show();
/*      */   }
/*      */ 
/*      */ 

/*      */ }

/* Location:           C:\Beta\lib\beta.alfa.0.jar
 * Qualified Name:     visual.CargaEtiquetasIndividuales
 * JD-Core Version:    0.6.0
 */