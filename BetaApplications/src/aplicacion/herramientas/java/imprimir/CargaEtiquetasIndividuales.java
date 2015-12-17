/*      */ package aplicacion.herramientas.java.imprimir;
/*      */ 
/*      */ import aplicacion.herramientas.java.imprimir.logic.StrEtiqueta;
/*      */ import beta.gui.codigobarra.Formato;
/*      */ import beta.tools.connector.GTransfer;
/*      */ import beta.tools.utils.FileCreator;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.KeyboardFocusManager;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.FocusEvent;
/*      */ import java.awt.event.ItemEvent;
/*      */ import java.awt.event.ItemListener;
/*      */ import java.awt.event.KeyAdapter;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.awt.event.MouseAdapter;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.WindowAdapter;
/*      */ import java.awt.event.WindowEvent;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.File;
/*      */ import java.io.FileReader;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.net.URL;
/*      */ import java.util.LinkedList;
/*      */ import java.util.Random;
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
/*   77 */   private boolean cantidadud = false;
/*      */   private static final long serialVersionUID = 1L;
/*   79 */   private String[] columns = { "" };
/*   80 */   private String[] columns_cptes = { "" };
/*   81 */   private String[] columns_detalles = { "" };
/*   82 */   private JPanel jContentPane = null;
/*      */   private SortButtonRenderer renderer;
/*   85 */   private LinkedList Seleccion = null;
/*   86 */   private JButton Generar = null;
/*   87 */   private Formato formato = null;
/*   88 */   private JButton Orden = null;
/*   89 */   private JScrollPane jScrollPane = null;
/*   90 */   private GTransfer GX = null;
/*   91 */   private JTable jTable = null;
/*   92 */   private int[] sizes = { 40, 140, 380, 70 };
/*   93 */   private int[] sizes_cptes = { 40, 140, 60, 120 };
/*   94 */   private int[] sizes_detalles = { 40, 140, 380, 70 };
/*      */   private HeaderListener hl;
/*      */   private Crono crono;
/*   97 */   private Etiquetas etiquetas = null;
/*      */   private SortableTableModel defaultTableModel;
/*      */   private DefaultTableModel defaultTableModel_cptes;
/*      */   private DefaultTableModel defaultTableModel_detalles;
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
/*  119 */   private double unidades = 0.0D;
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
/*  142 */   private String iduser = "";
/*  143 */   private JButton Pendientes = null;
/*      */   private Timer TimerP;
/*  145 */   private JTextField archivo = null;
/*      */   private ProgressDialog PD;
/*      */   private Object[][] auxObj;
/*      */   private int current;
/*      */   private int lenght;
/*      */   private int max;
/*      */   private JProgressBar Pbar;
/*      */   private boolean debug;
/*      */   private boolean done;
/*      */   private SwingWorker worker;
/*  153 */   private JTextField _txt_e_width = null;
/*  154 */   private JCheckBox _chk_quitar_prefijo = null;
/*      */ 
/*      */   public CargaEtiquetasIndividuales()
/*      */   {
/*  161 */     setDefaultCloseOperation(0);
/*  162 */     addWindowListener(new WindowAdapter() {
/*      */       public void windowClosing(WindowEvent e) {
/*  164 */         int n = JOptionPane.showConfirmDialog(
/*  165 */           new JFrame("Salir"), 
/*  166 */           "Desea Salir de Impresion de Etiquetas?", 
/*  167 */           "Salir", 
/*  168 */           0);
/*  169 */         if (n == 0) {
/*  170 */           CargaEtiquetasIndividuales.this.hide();
/*  171 */           CargaEtiquetasIndividuales.this.dispose();
/*      */         }
/*      */       }
/*      */     });
/*  176 */     initialize();
/*      */   }
/*      */ 
/*      */   public void setUser(String iduser) {
/*  180 */     this.iduser = iduser;
/*      */   }
/*      */ 
/*      */   private void initialize()
/*      */   {
/*  189 */     setSize(1024, 758);
/*  190 */     setContentPane(getJContentPane());
/*  191 */     setTitle("Etiquetas Manuales");
/*  192 */     create_table_cptes(1);
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
/*  257 */       URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/stock_print.png");
/*  258 */       this.Generar.setIcon(new ImageIcon(resourceURL));
/*  259 */       this.Generar.setSize(new Dimension(181, 20));
/*  260 */       this.Generar.setLocation(new Point(227, 11));
/*  261 */       this.Generar.setFont(new Font("Dialog", 1, 10));
/*  262 */       this.Generar.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/*  265 */           CargaEtiquetasIndividuales.this.cargar();
/*      */         } } );
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
/*      */   public void create_table(int rows) {
/*  299 */     this.defaultTableModel = new SortableTableModel() {
/*      */       public boolean isCellEditable(int row, int col) {
/*  301 */         switch (col) {
/*      */         case 2:
/*  303 */           return CargaEtiquetasIndividuales.this.codigos_no_existentes.isSelected();
/*      */         }
/*  305 */         return true;
/*      */       }
/*      */     };
/*  311 */     this.renderer = new SortButtonRenderer();
/*  312 */     this.defaultTableModel.setRowCount(rows);
/*  313 */     this.defaultTableModel.setColumnCount(this.columns.length);
/*  314 */     this.jTable = new JTable(this.defaultTableModel) {
/*      */       public Class getColumnClass(int columnIndex) {
/*  316 */         switch (columnIndex) {
/*      */         case 0:
/*  318 */           return Boolean.class;
/*      */         }
/*      */ 
/*  321 */         return super.getColumnClass(columnIndex);
/*      */       }
/*      */ 
/*      */       public TableCellEditor getCellEditor(int row, int col) {
/*  325 */         switch (col) {
/*      */         case 4:
/*  327 */           return CargaEtiquetasIndividuales.this.getCell(row, col);
/*      */         case 3:
/*  329 */           return CargaEtiquetasIndividuales.this.getCell(row, col);
/*      */         case 2:
/*  331 */           return CargaEtiquetasIndividuales.this.getCell(row, col);
/*      */         case 1:
/*  333 */           return CargaEtiquetasIndividuales.this.getCell(row, col);
/*      */         }
/*  335 */         return super.getCellEditor(row, col);
/*      */       }
/*      */     };
/*  341 */     this.columns = new String[] { "", "idArticulo", "Descripcion", "Etiq" };
/*  342 */     this.defaultTableModel.setColumnIdentifiers(this.columns);
/*      */ 
/*  344 */     JTableHeader header = this.jTable.getTableHeader();
/*  345 */     header.addMouseListener(new ColumnHeaderListener());
/*      */ 
/*  347 */     this.jTable.setShowVerticalLines(true);
/*  348 */     this.jTable.addMouseListener(new MouseAdapter() {
/*      */       public void mouseClicked(MouseEvent e) {
/*  350 */         JTable et = (JTable)e.getSource();
/*  351 */         Point p = e.getPoint();
/*  352 */         int row = et.rowAtPoint(p);
/*  353 */         int col = et.columnAtPoint(p);
/*  354 */         if (e.getClickCount() == 2);
/*      */       }
/*      */     });
/*  365 */     this.jTable.setShowHorizontalLines(true);
/*  366 */     this.jTable.setAutoResizeMode(0);
/*  367 */     this.jTable.setModel(this.defaultTableModel);
/*  368 */     TableColumnModel model = this.jTable.getColumnModel();
/*      */ 
/*  370 */     for (int u = 0; u < this.sizes.length; u++) {
/*  371 */       model.getColumn(u).setWidth(this.sizes[u]);
/*  372 */       model.getColumn(u).setMinWidth(this.sizes[u]);
/*  373 */       model.getColumn(u).setPreferredWidth(this.sizes[u]);
/*  374 */       model.getColumn(u).setHeaderRenderer(this.renderer);
/*      */     }
/*      */ 
/*  377 */     header = this.jTable.getTableHeader();
/*  378 */     this.hl = new HeaderListener(header, this.renderer);
/*  379 */     header.addMouseListener(this.hl);
/*  380 */     this.jScrollPane.setViewportView(null);
/*  381 */     this.jScrollPane.setViewportView(this.jTable);
/*      */   }
/*      */ 
/*      */   public void agregar_items_desde_cptes() {
/*  385 */     if (this.jTable_cptes != null)
/*  386 */       for (int i = 0; i < this.jTable_cptes.getRowCount(); i++)
/*  387 */         if (((Boolean)this.jTable_cptes.getValueAt(i, 0)).booleanValue()) {
/*  388 */           String idprov = this.jTable_cptes.getValueAt(i, 1).toString();
/*  389 */           String tc = this.jTable_cptes.getValueAt(i, 2).toString();
/*  390 */           String idcomp = this.jTable_cptes.getValueAt(i, 3).toString();
/*  391 */           agregar_items_desde_cpte(idprov, tc, idcomp);
/*      */         }
/*      */   }
/*      */ 
/*      */   public void Buscar(JTable jTable, int row, int col)
/*      */   {
/*  415 */     BuscadorArt BA = new BuscadorArt();
/*  416 */     System.out.println("BuscadorArt Creado pasando coenxion");
/*  417 */     BA.setGTransfer(this.GX);
/*  418 */     BA.setIndex(1);
/*  419 */     BA.setReturnTable(jTable, row, col);
/*  420 */     BA.show();
/*      */   }
/*      */ 
/*      */   public void Buscar(JTextField cod, int row, int col) {
/*  424 */     if (col == 1) {
/*  425 */       System.out.println("BuscadorArt Creado pasando coenxion");
/*  426 */       BuscadorArt BA = new BuscadorArt();
/*  427 */       BA.setGTransfer(this.GX);
/*  428 */       BA.setIndex(1);
/*  429 */       BA.setRetunrField(cod);
/*  430 */       BA.show();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void agregar_items_desde_cpte(String idprov, String tc, String idcomp)
/*      */   {
/*  437 */     String q = "";
/*  438 */     if (esCompra(tc))
/*  439 */       q = getQueryDetallesC(idprov, tc, idcomp);
/*      */     else {
/*  441 */       q = getQueryDetallesV(idprov, tc, idcomp);
/*      */     }
/*  443 */     System.out.println(q);
/*  444 */     Object[][] results = this.GX.getSQLResult(q);
/*  445 */     int ix = this.jTable.getRowCount() - 1;
/*  446 */     if ((results != null) && 
/*  447 */       (results.length > 0))
/*  448 */       for (int i = 0; i < results.length; i++) {
/*  449 */         String code = results[i][0].toString();
/*  450 */         int j = -1;
/*  451 */         j = existe(code);
/*  452 */         if (j >= 0) {
/*  453 */           System.out.println("ya esta " + code + " en " + j + " opcion si, suma, incrementa la cantidad en la fila " + i);
/*  454 */           double current = 0.0D;
/*  455 */           String val = this.jTable.getValueAt(j, 3).toString();
/*      */           try {
/*  457 */             current = new Double(val).doubleValue();
/*      */           } catch (Exception e) {
/*  459 */             System.out.println(e.getMessage());
/*      */           }
/*      */ 
/*  462 */           double qx = 0.0D;
/*      */           try {
/*  464 */             qx = new Double(results[i][2].toString()).doubleValue();
/*      */           } catch (Exception e) {
/*  466 */             System.out.println("error transformando cantidad " + q);
/*      */           }
/*  468 */           System.out.println("Cantidad total:" + qx + current);
/*  469 */           Double qr = Double.valueOf(qx + current);
/*  470 */           this.jTable.setValueAt(qr.intValue(), j, 3);
/*      */         }
/*      */         else
/*      */         {
/*  474 */           this.jTable.setValueAt(Boolean.valueOf(true), ix, 0);
/*  475 */           this.jTable.setValueAt(results[i][0].toString(), ix, 1);
/*  476 */           this.jTable.setValueAt(results[i][1].toString().toString(), ix, 2);
/*  477 */           this.jTable.setValueAt(results[i][2].toString(), ix, 3);
/*  478 */           double qx = 0.0D;
/*      */           try {
/*  480 */             qx = new Double(results[i][2].toString()).doubleValue();
/*      */           } catch (Exception e) {
/*  482 */             System.out.println("error transformando cantidad " + q);
/*      */           }
/*  484 */           this.unidades += qx;
/*  485 */           this.Unidades.setText(this.unidades);
/*  486 */           ix++;
/*  487 */           newRow();
/*      */         }
/*      */       }
/*      */   }
/*      */ 
/*      */   public void agregar_items_desde_pendientes()
/*      */   {
/*  498 */     String q = " select e.idarticulo,a.descripcion,e.cantidad ";
/*  499 */     q = q + " from b_etiquetas e left outer join v_ma_articulos a ";
/*  500 */     q = q + " on e.idarticulo=a.idarticulo ";
/*  501 */     q = q + " order by e.fecha,e.tc,e.idcomprobante ";
/*  502 */     Object[][] results = this.GX.getSQLResult(q);
/*  503 */     if ((results != null) && 
/*  504 */       (results.length > 0))
/*  505 */       for (int i = 0; i < results.length; i++) {
/*  506 */         String code = (String)results[i][0];
/*  507 */         String descripcion = (String)results[i][1];
/*  508 */         String cantidad = (String)results[i][2];
/*      */ 
/*  510 */         int r = getEmptyRow();
/*  511 */         if (r < 0) {
/*  512 */           r = this.jTable.getRowCount() - 1;
/*  513 */           newSEmptyRow(r);
/*  514 */           this.jTable.setValueAt(Boolean.valueOf(true), r + 1, 0);
/*  515 */           this.jTable.setValueAt(code, r + 1, 1);
/*  516 */           this.jTable.setValueAt(descripcion, r + 1, 2);
/*  517 */           this.jTable.setValueAt(cantidad, r + 1, 3);
/*      */         }
/*      */         else
/*      */         {
/*  521 */           this.jTable.setValueAt(Boolean.valueOf(true), r, 0);
/*  522 */           this.jTable.setValueAt(code, r, 1);
/*  523 */           this.jTable.setValueAt(descripcion, r, 2);
/*  524 */           this.jTable.setValueAt(cantidad, r, 3);
/*      */         }
/*      */       }
/*      */   }
/*      */ 
/*      */   public void newSEmptyRow(int r)
/*      */   {
/*  534 */     System.out.println("agregamos otra?" + r);
/*      */ 
/*  536 */     if (r == this.defaultTableModel.getRowCount() - 1) {
/*  537 */       this.defaultTableModel.setRowCount(this.defaultTableModel.getRowCount() + 1);
/*      */     }
/*  539 */     int rows = this.defaultTableModel.getRowCount() - 1;
/*      */   }
/*      */ 
/*      */   public int getEmptyRow()
/*      */   {
/*  544 */     int e = -1;
/*  545 */     int i = 0;
/*  546 */     while (((e < 0 ? 1 : 0) & (i < this.jTable.getRowCount() ? 1 : 0)) != 0) {
/*  547 */       String empty = "";
/*      */       try {
/*  549 */         empty = this.jTable.getValueAt(i, 2).toString();
/*      */       }
/*      */       catch (Exception localException) {
/*      */       }
/*  553 */       if (empty.compareTo("") == 0) {
/*  554 */         e = i;
/*      */       }
/*  556 */       i++;
/*      */     }
/*  558 */     return e;
/*      */   }
/*      */ 
/*      */   public void readFile3(String path) {
/*  562 */     int chrx = 9;
/*  563 */     String record = null;
/*  564 */     File file = new File(path);
/*      */ 
/*  566 */     int recCount = 0;
/*  567 */     this.current = recCount;
/*      */     try
/*      */     {
/*  570 */       FileReader fr = new FileReader(file);
/*  571 */       BufferedReader br = new BufferedReader(fr);
/*  572 */       if (this.TimerP != null) {
/*  573 */         this.TimerP.start();
/*      */       }
/*  575 */       if (this.PD != null) {
/*  576 */         this.PD.setComment(file.getName());
/*      */       }
/*  578 */       if (this.PD != null) {
/*  579 */         this.PD.show();
/*      */       }
/*  581 */       record = new String();
/*  582 */       int cols = 0;
/*      */ 
/*  586 */       this.auxObj = new Object[this.lenght][this.max];
/*      */ 
/*  588 */       fr = new FileReader(file);
/*  589 */       br = new BufferedReader(fr);
/*  590 */       record = new String();
/*  591 */       recCount = 0;
/*      */ 
/*  593 */       while ((record = br.readLine()) != null) {
/*  594 */         this.current = recCount;
/*  595 */         String auxs = "";
/*  596 */         int col = 0;
/*  597 */         for (int i = 0; i < record.length(); i++)
/*      */         {
/*  599 */           if (record.charAt(i) == chrx)
/*      */           {
/*      */             try {
/*  602 */               this.auxObj[recCount][col] = new String(auxs);
/*      */             } catch (Exception e) {
/*  604 */               if (this.debug) {
/*  605 */                 System.out.println(e.getMessage());
/*      */               }
/*      */             }
/*  608 */             auxs = "";
/*  609 */             col++;
/*      */           }
/*      */           else
/*      */           {
/*  613 */             auxs = auxs + record.charAt(i);
/*      */           }
/*      */ 
/*  616 */           this.current = (i + 1);
/*      */         }
/*      */         try {
/*  619 */           this.auxObj[recCount][col] = new String(auxs);
/*      */         } catch (Exception e) {
/*  621 */           if (this.debug) {
/*  622 */             System.out.println(e.getMessage());
/*      */           }
/*      */         }
/*  625 */         for (int v = col + 1; v < this.auxObj.length; v++) {
/*  626 */           for (int u = col + 1; u < cols; u++) {
/*  627 */             if (this.auxObj[v][u] != null)
/*      */               continue;
/*  629 */             this.auxObj[v][u] = new String("");
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*  634 */         if (this.debug) {
/*  635 */           System.out.println(record);
/*      */         }
/*  637 */         recCount++;
/*  638 */         for (int u = 0; u < cols; u++) {
/*      */           try {
/*  640 */             if (this.auxObj[recCount][u] == null)
/*  641 */               this.auxObj[recCount][u] = new String("");
/*      */           }
/*      */           catch (Exception e) {
/*  644 */             if (this.debug) {
/*  645 */               System.out.println(e.getMessage());
/*      */             }
/*      */ 
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */     catch (IOException e)
/*      */     {
/*  657 */       e.printStackTrace();
/*      */     }
/*  659 */     fillTable();
/*      */   }
/*      */ 
/*      */   public void fillTable()
/*      */   {
/*  664 */     int rows = this.auxObj.length;
/*  665 */     int cols = this.auxObj[0].length;
/*      */ 
/*  667 */     int ix = 0;
/*  668 */     for (int i = 0; i < rows; i++) {
/*  669 */       this.jTable.setValueAt(Boolean.valueOf(true), i, 0);
/*  670 */       for (int j = 0; j < cols; j++) {
/*  671 */         this.jTable.setValueAt((String)this.auxObj[i][j], i, j + 1);
/*      */       }
/*      */ 
/*  674 */       if (i == this.defaultTableModel.getRowCount() - 1) {
/*  675 */         this.defaultTableModel.setRowCount(this.defaultTableModel.getRowCount() + 1);
/*      */       }
/*      */     }
/*      */ 
/*  679 */     this.done = true;
/*      */   }
/*      */   private JTextField getArchivo() {
/*  682 */     if (this.archivo == null) {
/*  683 */       this.archivo = new JTextField();
/*  684 */       this.archivo.setBounds(new Rectangle(230, 80, 120, 19));
/*      */     }
/*  686 */     return this.archivo;
/*      */   }
/*      */ 
/*      */   private JButton getBuscar() {
/*  690 */     if (this.Buscar == null) {
/*  691 */       this.Buscar = new JButton();
/*  692 */       this.Buscar.setBounds(new Rectangle(350, 80, 29, 19));
/*      */ 
/*  695 */       URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/document-open.png");
/*  696 */       this.Buscar.setIcon(new ImageIcon(resourceURL));
/*  697 */       this.Buscar.setToolTipText("importar etiquetas desde archivo");
/*  698 */       this.Buscar.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/*  701 */           CargaEtiquetasIndividuales.this.buscar();
/*      */         } } );
/*      */     }
/*  705 */     return this.Buscar;
/*      */   }
/*      */ 
/*      */   private JButton getGuardar() {
/*  709 */     if (this.guardar == null) {
/*  710 */       this.guardar = new JButton();
/*  711 */       this.guardar.setBounds(new Rectangle(380, 80, 50, 19));
/*      */ 
/*  713 */       URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/document-save.png");
/*  714 */       this.guardar.setIcon(new ImageIcon(resourceURL));
/*  715 */       this.guardar.setToolTipText("Exportar etiquetas a archivo");
/*  716 */       this.guardar.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/*  719 */           CargaEtiquetasIndividuales.this.guardar();
/*      */         } } );
/*      */     }
/*  723 */     return this.guardar;
/*      */   }
/*      */ 
/*      */   public void buscar() {
/*  727 */     JFileChooser JF = new JFileChooser();
/*  728 */     int rx = JF.showOpenDialog(this);
/*  729 */     if (rx == 0) {
/*  730 */       File file = JF.getSelectedFile();
/*      */ 
/*  732 */       this.archivo.setText(file.getAbsolutePath());
/*  733 */       this.crono = new Crono();
/*  734 */       this.crono.start();
/*  735 */       goRead();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void goRead()
/*      */   {
/*  742 */     if (this.worker == null)
/*  743 */       this.worker = new SwingWorker() {
/*      */         public Object construct() {
/*  745 */           CargaEtiquetasIndividuales.this.current = 0;
/*  746 */           CargaEtiquetasIndividuales.this.done = false;
/*  747 */           return new CargaEtiquetasIndividuales.readTask(CargaEtiquetasIndividuales.this);
/*      */         }
/*      */       };
/*  751 */     this.done = false;
/*  752 */     this.worker.start();
/*      */   }
/*      */ 
/*      */   public void guardar() {
/*  756 */     String output = "c:/temp/test.txt";
/*      */ 
/*  758 */     JFileChooser JF = new JFileChooser();
/*  759 */     int fx = JF.showSaveDialog(this);
/*  760 */     if (fx == 0) {
/*  761 */       File file = JF.getSelectedFile();
/*  762 */       FileCreator Logs = new FileCreator(file.getAbsolutePath());
/*  763 */       Logs.Open();
/*      */ 
/*  765 */       for (int i = 0; i < this.jTable.getRowCount(); i++)
/*      */       {
/*  767 */         String rx = "";
/*  768 */         String codigo = "";
/*      */         try {
/*  770 */           codigo = this.jTable.getValueAt(i, 1).toString();
/*      */         }
/*      */         catch (Exception localException) {
/*      */         }
/*  774 */         if (codigo.compareTo("") != 0) {
/*  775 */           String desc = this.jTable.getValueAt(i, 2).toString();
/*  776 */           String cant = this.jTable.getValueAt(i, 3).toString();
/*  777 */           rx = codigo + "\t" + desc + "\t" + cant;
/*  778 */           Logs.println(rx);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  783 */       Logs.Close();
/*  784 */       JOptionPane.showMessageDialog(this, "Se Grabo Correctamente");
/*      */     }
/*      */   }
/*      */ 
/*      */   public void endReadbar()
/*      */   {
/*  790 */     this.Pbar.setValue(this.lenght);
/*  791 */     this.PD.hide();
/*      */   }
/*      */ 
/*      */   public void updateReadbar() {
/*  795 */     this.Pbar.setMaximum(this.lenght);
/*  796 */     this.Pbar.setValue(this.current);
/*  797 */     this.Pbar.setString(this.current + "/" + this.lenght);
/*  798 */     this.Pbar.setStringPainted(true);
/*  799 */     this.PD.setElapsed(this.crono.elapsed());
/*  800 */     this.PD.setRemanning(this.crono.Left(this.current, this.lenght));
/*      */   }
/*      */   public void create_Readtimer() {
/*  803 */     this.crono = new Crono();
/*  804 */     this.crono.start();
/*  805 */     this.TimerP = new Timer(1000, new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  807 */         if (CargaEtiquetasIndividuales.this.done) {
/*  808 */           CargaEtiquetasIndividuales.this.endReadbar();
/*  809 */           CargaEtiquetasIndividuales.this.TimerP.stop();
/*      */         } else {
/*  811 */           CargaEtiquetasIndividuales.this.updateReadbar();
/*      */         }
/*      */       }
/*      */     });
/*  815 */     this.PD = new ProgressDialog("Cargando Archivo");
/*  816 */     this.PD.makegui();
/*  817 */     this.Pbar = this.PD.getProgressBar();
/*      */   }
/*      */ 
/*      */   public void readFile()
/*      */   {
/*  824 */     create_Readtimer();
/*  825 */     String path = this.archivo.getText();
/*      */ 
/*  827 */     iniLength(path);
/*  828 */     readFile3(path);
/*      */   }
/*      */ 
/*      */   public void iniLength(String path) {
/*  832 */     int chrx = 9;
/*  833 */     String record = null;
/*  834 */     File file = new File(path);
/*  835 */     int recCount = 0;
/*      */     try
/*      */     {
/*  839 */       FileReader fr = new FileReader(file);
/*  840 */       BufferedReader br = new BufferedReader(fr);
/*  841 */       if (this.TimerP != null) {
/*  842 */         this.TimerP.start();
/*      */       }
/*  844 */       if (this.PD != null) {
/*  845 */         this.PD.setComment(file.getName());
/*      */       }
/*  847 */       if (this.PD != null) {
/*  848 */         this.PD.show();
/*      */       }
/*  850 */       record = new String();
/*  851 */       int cols = 0; this.max = 0;
/*  852 */       this.lenght = 1;
/*  853 */       while ((record = br.readLine()) != null) {
/*  854 */         recCount++;
/*  855 */         cols = 1;
/*  856 */         this.current = recCount;
/*  857 */         this.lenght += 1;
/*  858 */         for (int i = 0; i < record.length(); i++)
/*      */         {
/*  860 */           if (record.charAt(i) != chrx) continue; cols++;
/*      */         }
/*      */ 
/*  863 */         if (cols <= this.max) continue; this.max = cols;
/*      */       }
/*      */ 
/*  866 */       cols++;
/*      */ 
/*  868 */       System.out.println("Object to create:" + recCount + "/" + (this.max + 1));
/*      */ 
/*  870 */       this.lenght = recCount;
/*      */     }
/*      */     catch (IOException e)
/*      */     {
/*  874 */       if (this.debug) {
/*  875 */         System.out.println("Uh oh, got an IOException error!");
/*      */       }
/*  877 */       e.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void agregar_items_desde_items()
/*      */   {
/*  905 */     int ix = this.jTable.getRowCount() - 1;
/*  906 */     System.out.println("ultima fila " + ix);
/*  907 */     if ((this.jTable_detalles != null) && 
/*  908 */       (this.jTable_detalles.getRowCount() > 0))
/*      */     {
/*  910 */       for (int i = 0; i < this.jTable_detalles.getRowCount(); i++)
/*  911 */         if (((Boolean)this.jTable_detalles.getValueAt(i, 0)).booleanValue()) {
/*  912 */           String code = this.jTable_detalles.getValueAt(i, 1).toString();
/*  913 */           int j = -1;
/*  914 */           j = existe(code);
/*  915 */           if (j >= 0) {
/*  916 */             System.out.println("ya esta " + code + " en " + j + " opcion si, suma, incrementa la cantidad en la fila " + i);
/*  917 */             double current = 0.0D;
/*  918 */             String val = this.jTable.getValueAt(j, 3).toString();
/*      */             try {
/*  920 */               current = new Double(val).doubleValue();
/*      */             } catch (Exception e) {
/*  922 */               System.out.println(e.getMessage());
/*      */             }
/*      */ 
/*  925 */             double qx = 0.0D;
/*      */             try {
/*  927 */               qx = new Double(this.jTable_detalles.getValueAt(i, 3).toString()).doubleValue();
/*      */             } catch (Exception e) {
/*  929 */               System.out.println("error transformando cantidad " + qx);
/*      */             }
/*  931 */             System.out.println("Cantidad total:" + qx + current);
/*  932 */             Double qr = Double.valueOf(qx + current);
/*  933 */             this.jTable.setValueAt(qr.intValue(), j, 3);
/*      */           }
/*      */           else {
/*  936 */             System.out.println("se agrega " + code);
/*      */ 
/*  938 */             this.jTable.setValueAt(Boolean.valueOf(true), ix, 0);
/*  939 */             this.jTable.setValueAt(this.jTable_detalles.getValueAt(i, 1).toString(), ix, 1);
/*  940 */             this.jTable.setValueAt(this.jTable_detalles.getValueAt(i, 2).toString(), ix, 2);
/*  941 */             this.jTable.setValueAt(this.jTable_detalles.getValueAt(i, 3).toString(), ix, 3);
/*  942 */             double qx = 0.0D;
/*      */             try {
/*  944 */               qx = new Double(this.jTable_detalles.getValueAt(i, 3).toString()).doubleValue();
/*      */             } catch (Exception e) {
/*  946 */               System.out.println("error transformando cantidad " + qx);
/*      */             }
/*  948 */             this.unidades += qx;
/*  949 */             this.Unidades.setText(""+this.unidades);
/*  950 */             ix++;
/*  951 */             newRow();
/*      */           }
/*      */         }
/*      */     }
/*      */   }
/*      */ 
/*      */   public int existe(String code)
/*      */   {
/*  962 */     boolean found = false;
/*  963 */     int i = 0;
/*  964 */     while (((found ? 0 : 1) & (i < this.jTable.getRowCount() ? 1 : 0)) != 0) {
/*  965 */       Object obj = this.jTable.getValueAt(i, 1);
/*  966 */       if (obj != null) {
/*  967 */         found = this.jTable.getValueAt(i, 1).toString().toUpperCase().compareTo(code.toUpperCase()) == 0;
/*      */       }
/*      */ 
/*  970 */       if (found) continue; i++;
/*      */     }
/*  972 */     if (!found) {
/*  973 */       i = -1;
/*      */     }
/*  975 */     return i;
/*      */   }
/*      */   public void Eval_prov(String value, int row, int col) {
/*  978 */     if (check_prooveedor(value)) {
/*  979 */       this.jTable_cptes.changeSelection(row, col + 1, false, false);
/*  980 */       this.jTable_cptes.editCellAt(row, col + 1);
/*  981 */       this.jTable_cptes.transferFocus();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void Eval_Tc(String value, int row, int col) {
/*  986 */     if (check_tc(value)) {
/*  987 */       this.jTable_cptes.changeSelection(row, col + 1, false, false);
/*  988 */       this.jTable_cptes.editCellAt(row, col + 1);
/*  989 */       this.jTable_cptes.transferFocus();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void Eval_row(String value, int row, int col)
/*      */   {
/*  995 */     String prov = this.jTable_cptes.getValueAt(row, 1).toString();
/*  996 */     String tc = this.jTable_cptes.getValueAt(row, 2).toString();
/*  997 */     String id = check_cptes_row(prov, tc, value);
/*  998 */     if (id.compareTo("") != 0) {
/*  999 */       if (this.jTable_cptes.getRowCount() - 1 == row) {
/* 1000 */         newEmptyRowcPTES(row);
/*      */       }
/* 1002 */       this.jTable_cptes.clearSelection();
/*      */ 
/* 1004 */       this.jTable_cptes.changeSelection(row + 1, 1, false, false);
/* 1005 */       System.out.println("Seting value==" + id);
/* 1006 */       this.jTable_cptes.setValueAt(id, row, 3);
/* 1007 */       this.jTable_cptes.editCellAt(row + 1, 1);
/* 1008 */       this.jTable_cptes.transferFocus();
/*      */     } else {
/* 1010 */       JOptionPane.showMessageDialog(this, "El comprobante " + prov + " " + tc + "-" + value + " no existe ");
/*      */     }
/*      */   }
/*      */ 
/*      */   public String check_cptes_row(String prov, String tc, String idcomp) {
/* 1015 */     String aux = "";
/* 1016 */     String q = "";
/* 1017 */     q = q + "select cuenta,tc,idcomprobante  ";
/* 1018 */     q = q + " from c_mv_cpte ";
/* 1019 */     q = q + " where cuenta ='" + prov + "'";
/* 1020 */     q = q + " and tc like '" + tc + "' ";
/* 1021 */     q = q + " and idcomprobante like '%" + idcomp + "%'";
/* 1022 */     q = q + " order by cuenta,tc,idcomprobante desc ";
/* 1023 */     Object[][] results = this.GX.getSQLResult(q);
/* 1024 */     if ((results != null) && 
/* 1025 */       (results.length > 0)) {
/* 1026 */       if (results.length == 1)
/* 1027 */         aux = results[0][2].toString();
/*      */       else {
/* 1029 */         JOptionPane.showMessageDialog(this, "Mas de un comprbante concuerda con sus datos. Eso no esta Permitido");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1034 */     System.out.println("encontro?=" + aux);
/* 1035 */     return aux;
/*      */   }
/*      */ 
/*      */   public void create_table_cptes(int rows)
/*      */   {
/* 1111 */     this.defaultTableModel_cptes = new DefaultTableModel() {
/*      */       public boolean isCellEditable(int row, int col) {
/* 1113 */         switch (col) {
/*      */         case 0:
/* 1115 */           return true;
/*      */         }
/* 1117 */         return true;
/*      */       }
/*      */     };
/* 1124 */     this.defaultTableModel_cptes.setRowCount(rows);
/*      */ 
/* 1127 */     this.defaultTableModel_cptes.setColumnCount(this.columns_cptes.length);
/*      */ 
/* 1129 */     this.jTable_cptes = new JTable(this.defaultTableModel_cptes) {
/*      */       public Class getColumnClass(int columnIndex) {
/* 1131 */         switch (columnIndex) {
/*      */         case 0:
/* 1133 */           return Boolean.class;
/*      */         }
/*      */ 
/* 1136 */         return super.getColumnClass(columnIndex);
/*      */       }
/*      */ 
/*      */       public TableCellEditor getCellEditor(int row, int col) {
/* 1140 */         switch (col) {
/*      */         case 1:
/* 1142 */           return CargaEtiquetasIndividuales.this.getCellCptes(row, col);
/*      */         case 2:
/* 1144 */           return CargaEtiquetasIndividuales.this.getCellCptes(row, col);
/*      */         case 3:
/* 1146 */           return CargaEtiquetasIndividuales.this.getCellCptes(row, col);
/*      */         }
/* 1148 */         return super.getCellEditor(row, col);
/*      */       }
/*      */     };
/* 1154 */     this.columns_cptes = new String[] { "", "idproveedor", "Tc", "idComprobante" };
/* 1155 */     this.defaultTableModel_cptes.setColumnIdentifiers(this.columns_cptes);
/*      */ 
/* 1157 */     JTableHeader header = this.jTable_cptes.getTableHeader();
/* 1158 */     header.addMouseListener(new ColumnHeaderListener());
/*      */ 
/* 1160 */     this.jTable_cptes.setShowVerticalLines(true);
/* 1161 */     this.jTable_cptes.addMouseListener(new MouseAdapter() {
/*      */       public void mouseClicked(MouseEvent e) {
/* 1163 */         JTable et = (JTable)e.getSource();
/* 1164 */         Point p = e.getPoint();
/* 1165 */         int row = et.rowAtPoint(p);
/* 1166 */         int col = et.columnAtPoint(p);
/* 1167 */         if (e.getClickCount() == 1)
/*      */         {
/*      */           try
/*      */           {
/* 1174 */             String idprov = CargaEtiquetasIndividuales.this.jTable_cptes.getValueAt(row, 1).toString();
/* 1175 */             String tc = CargaEtiquetasIndividuales.this.jTable_cptes.getValueAt(row, 2).toString();
/* 1176 */             String idcomp = CargaEtiquetasIndividuales.this.jTable_cptes.getValueAt(row, 3).toString();
/* 1177 */             CargaEtiquetasIndividuales.this.fill_detalles(idprov, tc, idcomp);
/*      */           }
/*      */           catch (Exception localException)
/*      */           {
/*      */           }
/*      */         }
/*      */       }
/*      */     });
/* 1188 */     this.jTable_cptes.setShowHorizontalLines(true);
/* 1189 */     this.jTable_cptes.setAutoResizeMode(0);
/* 1190 */     this.jTable_cptes.setModel(this.defaultTableModel_cptes);
/* 1191 */     TableColumnModel model = this.jTable_cptes.getColumnModel();
/*      */ 
/* 1193 */     for (int u = 0; u < this.sizes_cptes.length; u++) {
/* 1194 */       model.getColumn(u).setWidth(this.sizes_cptes[u]);
/* 1195 */       model.getColumn(u).setMinWidth(this.sizes_cptes[u]);
/* 1196 */       model.getColumn(u).setPreferredWidth(this.sizes_cptes[u]);
/*      */     }
/*      */ 
/* 1202 */     this.jScrollPane_cptes.setViewportView(null);
/* 1203 */     this.jScrollPane_cptes.setViewportView(this.jTable_cptes);
/*      */   }
/*      */ 
/*      */   public String getQueryDetallesC(String idprov, String tc, String idcomp) {
/* 1207 */     String q = "";
/* 1208 */     q = q + " select i.idarticulo,i.descripcion,";
/* 1209 */     if (this.cantidadud)
/* 1210 */       q = q + "i.cantidadud ";
/*      */     else {
/* 1212 */       q = q + "i.cantidad ";
/*      */     }
/*      */ 
/* 1215 */     q = q + " from c_mv_cpte c left outer join ";
/* 1216 */     q = q + " c_mv_cpteinsumos i on ";
/* 1217 */     q = q + " c.tc=i.tc and c.idcomprobante=i.idcomprobante ";
/* 1218 */     q = q + " where c.idcomprobante like '" + idcomp + "' ";
/* 1219 */     q = q + " and c.cuenta like '" + idprov + "' ";
/* 1220 */     q = q + " and c.tc like '" + tc + "' ";
/* 1221 */     q = q + " order by c.idcomprobante,i.idarticulo ";
/* 1222 */     return q;
/*      */   }
/*      */ 
/*      */   public String getQueryDetallesV(String idprov, String tc, String idcomp)
/*      */   {
/* 1227 */     String q = "";
/* 1228 */     q = q + " select i.idarticulo,i.descripcion, ";
/* 1229 */     if (this.cantidadud)
/* 1230 */       q = q + "i.cantidadud ";
/*      */     else {
/* 1232 */       q = q + "i.cantidad ";
/*      */     }
/* 1234 */     q = q + " from v_mv_cpte c left outer join ";
/* 1235 */     q = q + " v_mv_cpteinsumos i on ";
/* 1236 */     q = q + " c.tc=i.tc and c.idcomprobante=i.idcomprobante ";
/* 1237 */     q = q + " where c.idcomprobante like '" + idcomp + "' ";
/* 1238 */     q = q + " and c.cuenta like '" + idprov + "' ";
/* 1239 */     q = q + " and c.tc like '" + tc + "' ";
/* 1240 */     q = q + " order by c.idcomprobante ,i.idarticulo ";
/* 1241 */     return q;
/*      */   }
/*      */ 
/*      */   public String getQueryDetallesFCN(String idprov, String tc, String idcomp) {
/* 1245 */     String q = "";
/* 1246 */     q = q + " select i.idarticulo,i.descripcion, ";
/* 1247 */     if (this.cantidadud)
/* 1248 */       q = q + "i.cantidadud ";
/*      */     else {
/* 1250 */       q = q + "i.cantidad ";
/*      */     }
/* 1252 */     q = q + " from B_FCN c left outer join ";
/* 1253 */     q = q + " B_FCN_ITEM i on ";
/* 1254 */     q = q + " c.tc=i.tc and c.idcomprobante=i.idcomprobante ";
/* 1255 */     q = q + " where c.idcomprobante like '" + idcomp + "' ";
/* 1256 */     q = q + " and c.cuenta like '" + idprov + "' ";
/* 1257 */     q = q + " and c.tc like '" + tc + "' ";
/* 1258 */     q = q + " order by c.idcomprobante ,i.idarticulo ";
/* 1259 */     return q;
/*      */   }
/*      */ 
/*      */   public String getQueryDetallesCTRL(String tc, String idcomp) {
/* 1263 */     String q = "";
/* 1264 */     q = q + " select s.idarticulo,s.descripcion,s.stock ";
/* 1265 */     q = q + " from v_mv_ctrol_cpte v join ";
/* 1266 */     q = q + " v_mv_stock s ";
/* 1267 */     q = q + " on v.tc=s.tc ";
/* 1268 */     q = q + " and v.idcomprobante=s.idcomprobante ";
/* 1269 */     q = q + " where v.tc like '" + tc + "' ";
/* 1270 */     q = q + " and v.idcomprobante like '" + idcomp + "' ";
/* 1271 */     q = q + " order by v.idcomprobante,s.idarticulo ";
/* 1272 */     return q;
/*      */   }
/*      */ 
/*      */   public String getQueryDetallesETQ(String tc, String idcomp) {
/* 1276 */     String q = "";
/* 1277 */     q = q + " select s.idarticulo,s.descripcion,s.cantidad ";
/* 1278 */     q = q + " from v_etiquetas v join ";
/* 1279 */     q = q + " v_etiquetas_articulos s ";
/* 1280 */     q = q + " on v.idtrabajo=s.idtrabajo ";
/* 1281 */     q = q + " where v.idtrabajo like '" + idcomp + "' ";
/* 1282 */     q = q + " order by v.idtrabajo,s.idarticulo ";
/* 1283 */     return q;
/*      */   }
/*      */ 
/*      */   public boolean esCompra(String tc) {
/* 1287 */     boolean b = true;
/* 1288 */     if (tc.toUpperCase().compareTo("FC") == 0) {
/* 1289 */       b = false;
/*      */     }
/* 1291 */     if (tc.toUpperCase().compareTo("FCN") == 0) {
/* 1292 */       b = false;
/*      */     }
/* 1294 */     if (tc.toUpperCase().compareTo("FP") == 0) {
/* 1295 */       b = false;
/*      */     }
/* 1297 */     if (tc.toUpperCase().compareTo("RM") == 0) {
/* 1298 */       b = false;
/*      */     }
/* 1300 */     if (tc.toUpperCase().compareTo("TK") == 0) {
/* 1301 */       b = false;
/*      */     }
/* 1303 */     if (tc.toUpperCase().compareTo("TKFC") == 0) {
/* 1304 */       b = false;
/*      */     }
/* 1306 */     if (tc.toUpperCase().compareTo("CTRL") == 0) {
/* 1307 */       b = false;
/*      */     }
/* 1309 */     if (tc.toUpperCase().compareTo("ETQ") == 0) {
/* 1310 */       b = false;
/*      */     }
/* 1312 */     return b;
/*      */   }
/*      */   public void fill_detalles(String idprov, String tc, String idcomp) {
/* 1315 */     String q = "";
/* 1316 */     if (esCompra(tc)) {
/* 1317 */       q = getQueryDetallesC(idprov, tc, idcomp);
/*      */     }
/* 1319 */     else if (tc.toUpperCase().compareTo("CTRL") == 0) {
/* 1320 */       q = getQueryDetallesCTRL(tc, idcomp);
/*      */     }
/* 1322 */     else if (tc.toUpperCase().compareTo("ETQ") == 0) {
/* 1323 */       q = getQueryDetallesETQ(tc, idcomp);
/*      */     }
/* 1325 */     else if (tc.toUpperCase().compareTo("FCN") == 0)
/* 1326 */       q = getQueryDetallesFCN(idprov, tc, idcomp);
/*      */     else {
/* 1328 */       q = getQueryDetallesV(idprov, tc, idcomp);
/*      */     }
/*      */ 
/* 1337 */     System.out.println(q);
/* 1338 */     Object[][] results = this.GX.getSQLResult(q);
/* 1339 */     if (results != null)
/* 1340 */       if (results.length > 0) {
/* 1341 */         create_table_detalles(results.length);
/* 1342 */         for (int i = 0; i < results.length; i++) {
/* 1343 */           this.jTable_detalles.setValueAt(Boolean.valueOf(true), i, 0);
/* 1344 */           this.jTable_detalles.setValueAt(results[i][0].toString(), i, 1);
/* 1345 */           this.jTable_detalles.setValueAt(results[i][1].toString(), i, 2);
/* 1346 */           this.jTable_detalles.setValueAt(results[i][2].toString(), i, 3);
/*      */         }
/*      */       } else {
/* 1349 */         this.jScrollPane_detalles.setViewportView(null);
/*      */       }
/*      */   }
/*      */ 
/*      */   public void create_table_detalles(int rows)
/*      */   {
/* 1355 */     this.defaultTableModel_detalles = new DefaultTableModel() {
/*      */       public boolean isCellEditable(int row, int col) {
/* 1357 */         switch (col) {
/*      */         case 0:
/* 1359 */           return true;
/*      */         }
/* 1361 */         return false;
/*      */       }
/*      */     };
/* 1365 */     this.defaultTableModel_detalles.setRowCount(rows);
/* 1366 */     this.defaultTableModel_detalles.setColumnCount(this.columns_detalles.length);
/*      */ 
/* 1368 */     this.jTable_detalles = new JTable(this.defaultTableModel_detalles) {
/*      */       public Class getColumnClass(int columnIndex) {
/* 1370 */         switch (columnIndex) {
/*      */         case 0:
/* 1372 */           return Boolean.class;
/*      */         }
/* 1374 */         return super.getColumnClass(columnIndex);
/*      */       }
/*      */ 
/*      */       public TableCellEditor getCellEditor(int row, int col)
/*      */       {
/* 1383 */         return super.getCellEditor(row, col);
/*      */       }
/*      */     };
/* 1389 */     this.columns_detalles = new String[] { "", "idarticulo", "descripcion", "cant" };
/* 1390 */     this.defaultTableModel_detalles.setColumnIdentifiers(this.columns_detalles);
/*      */ 
/* 1392 */     JTableHeader header = this.jTable_detalles.getTableHeader();
/* 1393 */     header.addMouseListener(new ColumnHeaderListener());
/* 1394 */     this.jTable_detalles.setShowVerticalLines(true);
/* 1395 */     this.jTable_detalles.addMouseListener(new MouseAdapter() {
/*      */       public void mouseClicked(MouseEvent e) {
/* 1397 */         JTable et = (JTable)e.getSource();
/* 1398 */         Point p = e.getPoint();
/* 1399 */         int row = et.rowAtPoint(p);
/* 1400 */         int col = et.columnAtPoint(p);
/* 1401 */         if (e.getClickCount() == 2);
/*      */       }
/*      */     });
/* 1406 */     this.jTable_detalles.setShowHorizontalLines(true);
/* 1407 */     this.jTable_detalles.setAutoResizeMode(0);
/* 1408 */     this.jTable_detalles.setModel(this.defaultTableModel_detalles);
/* 1409 */     TableColumnModel model = this.jTable_detalles.getColumnModel();
/* 1410 */     for (int u = 0; u < this.sizes_detalles.length; u++) {
/* 1411 */       model.getColumn(u).setWidth(this.sizes_detalles[u]);
/* 1412 */       model.getColumn(u).setMinWidth(this.sizes_detalles[u]);
/* 1413 */       model.getColumn(u).setPreferredWidth(this.sizes_detalles[u]);
/*      */     }
/* 1415 */     this.jScrollPane_detalles.setViewportView(null);
/* 1416 */     this.jScrollPane_detalles.setViewportView(this.jTable_detalles);
/*      */   }
/*      */ 
/*      */   public DefaultCellEditor getCell(int r, int col)
/*      */   {
/* 1421 */     int rx = r;
/* 1422 */     int ry = col;
/*      */ 
/* 1424 */     JTextField currentCell = new JTextField() {
/*      */       public void processFocusEvent(FocusEvent e) {
/* 1426 */         JTextField tx = (JTextField)e.getSource();
/* 1427 */         System.out.println("foco textfield  " + tx.getText());
/* 1428 */         Component focusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
/* 1429 */         if ((isDisplayable()) && (e.getID() == 1004)) {
/* 1430 */           if (focusOwner == this) {
/* 1431 */             setBackground(Color.green);
/* 1432 */             setSelectionStart(0);
/* 1433 */             setSelectionEnd(tx.getText().length());
/*      */           }
/*      */ 
/*      */         }
/* 1437 */         else if (e.getID() == 1005) {
/* 1438 */           System.out.println("loosing focuss on textfield");
/* 1439 */           setBackground(Color.white);
/*      */         }
/*      */ 
/* 1442 */         super.processFocusEvent(e);
/*      */       }
/*      */     };
/* 1445 */     if (((col == 1 ? 1 : 0) | (col == 3 ? 1 : 0)) != 0)
/* 1446 */       currentCell.setHorizontalAlignment(4);
/*      */     else {
/* 1448 */       currentCell.setHorizontalAlignment(2);
/*      */     }
/*      */ 
/* 1451 */     DefaultCellEditor dce5 = new DefaultCellEditor(currentCell);
/* 1452 */     currentCell.addKeyListener(new KeyAdapter(rx, ry) {
/*      */       public void keyPressed(KeyEvent event) {
/* 1454 */         switch (event.getKeyCode())
/*      */         {
/*      */         case 116:
/* 1457 */           JTextField one = (JTextField)event.getSource();
/* 1458 */           CargaEtiquetasIndividuales.this.Buscar(one, this.val$rx, this.val$ry);
/*      */ 
/* 1462 */           break;
/*      */         case 113:
/* 1466 */           break;
/*      */         case 10:
/* 1468 */           System.out.println("textfield enter pressed " + this.val$rx + " " + this.val$ry);
/* 1469 */           JTextField one = (JTextField)event.getSource();
/*      */ 
/* 1471 */           CargaEtiquetasIndividuales.this.evaluatecell(one.getText(), this.val$rx, this.val$ry);
/*      */           try {
/* 1473 */             System.out.println("geting value from enter len:" + one.getText().length());
/*      */           }
/*      */           catch (Exception localException)
/*      */           {
/*      */           }
/*      */ 
/*      */         case 9:
/* 1480 */           break;
/*      */         case 127:
/* 1482 */           if (this.val$rx == 0)
/*      */             break;
/*      */         case 27:
/*      */         }
/*      */       }
/*      */     });
/* 1491 */     return dce5;
/*      */   }
/*      */ 
/*      */   public DefaultCellEditor getCellCptes(int r, int col)
/*      */   {
/* 1496 */     int rx = r;
/* 1497 */     int ry = col;
/*      */ 
/* 1499 */     JTextField currentCell = new JTextField();
/* 1500 */     if (((col == 1 ? 1 : 0) | (col == 3 ? 1 : 0)) != 0)
/* 1501 */       currentCell.setHorizontalAlignment(4);
/*      */     else {
/* 1503 */       currentCell.setHorizontalAlignment(2);
/*      */     }
/*      */ 
/* 1506 */     DefaultCellEditor dce5 = new DefaultCellEditor(currentCell);
/* 1507 */     currentCell.addKeyListener(new KeyAdapter(rx, ry) {
/*      */       public void keyPressed(KeyEvent event) {
/* 1509 */         switch (event.getKeyCode())
/*      */         {
/*      */         case 116:
/* 1515 */           break;
/*      */         case 113:
/* 1519 */           break;
/*      */         case 10:
/* 1521 */           System.out.println("textfield enter pressed " + this.val$rx + " " + this.val$ry);
/* 1522 */           JTextField one = (JTextField)event.getSource();
/*      */ 
/* 1524 */           CargaEtiquetasIndividuales.this.evaluatecellCptes(one.getText(), this.val$rx, this.val$ry);
/*      */           try {
/* 1526 */             System.out.println("geting value from enter len:" + one.getText().length());
/*      */           }
/*      */           catch (Exception localException)
/*      */           {
/*      */           }
/*      */ 
/*      */         case 9:
/* 1533 */           break;
/*      */         case 127:
/* 1535 */           if (this.val$rx == 0)
/*      */             break;
/*      */         case 27:
/*      */         }
/*      */       }
/*      */     });
/* 1544 */     return dce5;
/*      */   }
/*      */   public String getCodeQuery(String code) {
/* 1547 */     String q = "";
/* 1548 */     q = q + " select idarticulo,descripcion from v_ma_articulos where idarticulo like '" + code + "'";
/* 1549 */     return q;
/*      */   }
/*      */ 
/*      */   public String checkcode(String code) {
/* 1553 */     String aux = "";
/* 1554 */     String q = getCodeQuery(code);
/* 1555 */     System.out.println(q);
/* 1556 */     Object[][] results = this.GX.getSQLResult(q);
/* 1557 */     if ((results != null) && 
/* 1558 */       (results.length > 0) && 
/* 1559 */       (results[0][0].toString().toUpperCase().compareTo(code.toUpperCase()) == 0)) {
/* 1560 */       aux = results[0][1].toString();
/*      */     }
/*      */ 
/* 1564 */     return aux;
/*      */   }
/*      */ 
/*      */   public void evaluatecell(String value, int row, int col) {
/* 1568 */     if (col == 1) {
/* 1569 */       System.out.println("evaluate code..." + value);
/* 1570 */       if (this.codigos_no_existentes.isSelected()) {
/* 1571 */         this.jTable.changeSelection(row, col + 1, false, false);
/* 1572 */         this.jTable.editCellAt(row, col + 1);
/* 1573 */         this.jTable.transferFocus();
/*      */       } else {
/* 1575 */         eval_code(value, row, col);
/*      */       }
/*      */     }
/*      */ 
/* 1579 */     if (col == 2) {
/* 1580 */       this.jTable.changeSelection(row, col + 1, false, false);
/* 1581 */       this.jTable.editCellAt(row, col + 1);
/* 1582 */       this.jTable.transferFocus();
/*      */     }
/* 1584 */     if (col == 3) {
/* 1585 */       System.out.println("evaluate cantidad.." + value);
/* 1586 */       if (this.codigos_no_existentes.isSelected()) {
/* 1587 */         if (this.jTable.getRowCount() - 1 == row) {
/* 1588 */           newEmptyRow(row);
/*      */         }
/* 1590 */         this.jTable.changeSelection(row + 1, 1, false, false);
/* 1591 */         this.jTable.editCellAt(row + 1, 1);
/* 1592 */         this.jTable.transferFocus();
/*      */       } else {
/* 1594 */         eval_row(value, row, col);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void evaluatecellCptes(String value, int row, int col)
/*      */   {
/* 1601 */     if (col == 1) {
/* 1602 */       System.out.println("evaluate prov..." + value);
/* 1603 */       Eval_prov(value, row, col);
/*      */     }
/* 1605 */     if (col == 2) {
/* 1606 */       System.out.println("evaluate tc.." + value);
/* 1607 */       Eval_Tc(value, row, col);
/*      */     }
/* 1609 */     if (col == 3) {
/* 1610 */       System.out.println("evaluate row.." + value);
/* 1611 */       Eval_row(value, row, col);
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean check_row(int row) {
/* 1616 */     String aux = "";
/* 1617 */     boolean b = false;
/*      */     try {
/* 1619 */       String code = this.jTable.getValueAt(row, 1).toString();
/* 1620 */       aux = checkcode(code);
/* 1621 */       System.out.println("para " + code + " se obtuvo " + aux);
/*      */     } catch (Exception e) {
/* 1623 */       System.out.println("error leyendo codigo en " + row + e.getMessage());
/*      */     }
/* 1625 */     String qty = "";
/*      */     try {
/* 1627 */       qty = this.jTable.getValueAt(row, 3).toString();
/*      */     } catch (Exception e) {
/* 1629 */       System.out.println("error leyendo cantidad " + e.getMessage());
/*      */     }
/*      */ 
/* 1632 */     double q = 0.0D;
/*      */     try {
/* 1634 */       q = new Double(qty).intValue();
/*      */     } catch (Exception e) {
/* 1636 */       System.out.println("error transformando cantidad " + q);
/*      */     }
/* 1638 */     this.unidades += q;
/* 1639 */     this.Unidades.setText(this.unidades);
/* 1640 */     if (((aux.compareTo("") != 0 ? 1 : 0) & (q > 0.0D ? 1 : 0)) != 0)
/* 1641 */       b = true;
/*      */     else {
/* 1643 */       b = false;
/*      */     }
/* 1645 */     return b;
/*      */   }
/*      */ 
/*      */   public void eval_des(String qty, int row, int col) {
/* 1649 */     String aux = "";
/*      */     try {
/* 1651 */       aux = checkcode(this.jTable.getValueAt(row, 1).toString());
/* 1652 */       System.out.println(aux);
/*      */     } catch (Exception e) {
/* 1654 */       System.out.println(e.getMessage());
/*      */     }
/*      */ 
/* 1658 */     if (aux.compareTo("") != 0) {
/* 1659 */       this.jTable.changeSelection(row, col + 1, false, false);
/* 1660 */       this.jTable.editCellAt(row, col + 1);
/* 1661 */       this.jTable.transferFocus();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void eval_row(String qty, int row, int col) {
/* 1666 */     String aux = "";
/*      */     try {
/* 1668 */       aux = checkcode(this.jTable.getValueAt(row, 1).toString());
/* 1669 */       System.out.println(aux);
/*      */     } catch (Exception e) {
/* 1671 */       System.out.println(e.getMessage());
/*      */     }
/* 1673 */     int q = 0;
/*      */     try {
/* 1675 */       q = new Integer(qty).intValue();
/*      */     } catch (Exception e) {
/* 1677 */       System.out.println(q);
/*      */     }
/* 1679 */     this.unidades += q;
/* 1680 */     this.Unidades.setText(this.unidades);
/* 1681 */     if (((aux.compareTo("") != 0 ? 1 : 0) & (q > 0 ? 1 : 0)) != 0) {
/* 1682 */       if (this.jTable.getRowCount() - 1 == row) {
/* 1683 */         newEmptyRow(row);
/*      */       }
/* 1685 */       this.jTable.changeSelection(row + 1, 1, false, false);
/* 1686 */       this.jTable.editCellAt(row + 1, 1);
/* 1687 */       this.jTable.transferFocus();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void eval_code(String code, int row, int col) {
/* 1692 */     String aux = "";
/*      */     try {
/* 1694 */       aux = checkcode(code);
/* 1695 */       System.out.println(aux);
/*      */     } catch (Exception e) {
/* 1697 */       System.out.println(e.getMessage());
/*      */     }
/*      */ 
/* 1700 */     if (aux.compareTo("") != 0) {
/* 1701 */       this.jTable.setValueAt(aux, row, col + 1);
/* 1702 */       this.jTable.changeSelection(row, col + 2, false, false);
/* 1703 */       this.jTable.editCellAt(row, col + 2);
/* 1704 */       this.jTable.transferFocus();
/*      */     } else {
/* 1706 */       JOptionPane.showMessageDialog(this, "El Codigo " + code + " es incorrecto!");
/* 1707 */       this.jTable.setValueAt(aux, row, col);
/* 1708 */       this.jTable.changeSelection(row, col, false, false);
/* 1709 */       this.jTable.editCellAt(row, col);
/* 1710 */       this.jTable.transferFocus();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void newRow() {
/* 1714 */     this.defaultTableModel.setRowCount(this.defaultTableModel.getRowCount() + 1);
/*      */   }
/*      */   public void newEmptyRow(int r) {
/* 1717 */     System.out.println("agregamos otra?");
/* 1718 */     if (r == this.defaultTableModel.getRowCount() - 1) {
/* 1719 */       this.defaultTableModel.setRowCount(this.defaultTableModel.getRowCount() + 1);
/*      */     }
/* 1721 */     int rows = this.defaultTableModel.getRowCount() - 1;
/* 1722 */     this.jTable.requestFocusInWindow();
/* 1723 */     this.jTable.changeSelection(rows, 0, false, false);
/* 1724 */     this.jTable.editCellAt(rows, 0);
/* 1725 */     this.jTable.transferFocus();
/*      */   }
/*      */ 
/*      */   public void newEmptyRowcPTES(int r) {
/* 1729 */     System.out.println("agregamos otra?");
/* 1730 */     if (r == this.defaultTableModel_cptes.getRowCount() - 1) {
/* 1731 */       this.defaultTableModel_cptes.setRowCount(this.defaultTableModel_cptes.getRowCount() + 1);
/*      */     }
/* 1733 */     int rows = this.defaultTableModel_cptes.getRowCount() - 1;
/* 1734 */     this.jTable_cptes.requestFocusInWindow();
/* 1735 */     this.jTable_cptes.changeSelection(rows, 0, false, false);
/* 1736 */     this.jTable_cptes.editCellAt(rows, 0);
/* 1737 */     this.jTable_cptes.transferFocus();
/*      */   }
/*      */ 
/*      */   public void setGTransfer(GTransfer GX) {
/* 1741 */     this.GX = GX;
/*      */   }
/*      */ 
/*      */   public void importar_stock()
/*      */   {
/* 1746 */     String q = get_importar_desde_stockString();
/* 1747 */     Object[][] results = this.GX.getSQLResult(q);
/* 1748 */     if ((results != null) && 
/* 1749 */       (results.length > 0)) {
/* 1750 */       int ix = this.jTable.getRowCount() - 1;
/* 1751 */       for (int i = 0; i < results.length; i++) {
/* 1752 */         String code = (String)results[i][0];
/* 1753 */         String desc = (String)results[i][1];
/* 1754 */         String qty = "1";
/* 1755 */         if (this.solo_una_etiqueta.isSelected())
/* 1756 */           qty = "1";
/*      */         else {
/* 1758 */           qty = (String)results[i][2];
/*      */         }
/*      */ 
/* 1761 */         this.jTable.setValueAt(Boolean.valueOf(true), i + ix, 0);
/* 1762 */         this.jTable.setValueAt(code, i + ix, 1);
/* 1763 */         this.jTable.setValueAt(desc, i + ix, 2);
/* 1764 */         this.jTable.setValueAt(qty, i + ix, 3);
/* 1765 */         newEmptyRow(i + ix);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public String get_importar_desde_stockString() {
/* 1771 */     String q = "";
/* 1772 */     q = q + "select V_MA_ARTICULOS.idarticulo,V_MA_ARTICULOS.descripcion, ";
/* 1773 */     q = q + "ISNULL(Stk_Stock_UDKG.cantidadud, 0) AS cantidad ";
/* 1774 */     q = q + "FROM V_MA_ARTICULOS LEFT OUTER JOIN ";
/* 1775 */     q = q + "Stk_Stock_UDKG  ON ";
/* 1776 */     q = q + "V_MA_ARTICULOS.IDARTICULO = Stk_Stock_UDKG.IdArticulo ";
/* 1777 */     q = q + "where V_MA_ARTICULOS.idarticulo between ";
/* 1778 */     q = q + "'" + this.Desde.getText() + "' ";
/* 1779 */     q = q + "and ";
/* 1780 */     q = q + "'" + this.Hasta.getText() + "'";
/* 1781 */     if (this.Stock_positivo.isSelected()) {
/* 1782 */       q = q + " and ISNULL(Stk_Stock_UDKG.cantidadud, 0) >0";
/*      */     }
/* 1784 */     q = q + "order by V_MA_ARTICULOS.IDARTICULO";
/* 1785 */     return q;
/*      */   }
/*      */ 
/*      */   public void cargar()
/*      */   {
/* 1790 */     LinkedList etqs = new LinkedList();
/* 1791 */     int emptys = 0;
/* 1792 */     if (this.Vacias.getText().compareTo("") != 0)
/*      */       try {
/* 1794 */         emptys = new Integer(this.Vacias.getText()).intValue();
/*      */       }
/*      */       catch (Exception localException)
/*      */       {
/*      */       }
/* 1799 */     int qtys = 0;
/* 1800 */     for (int i = 0; i < this.jTable.getRowCount(); i++) {
/* 1801 */       Object obj = this.jTable.getValueAt(i, 0);
/* 1802 */       if ((obj == null) || 
/* 1803 */         (!((Boolean)this.jTable.getValueAt(i, 0)).booleanValue())) continue;
/* 1804 */       if (this.codigos_no_existentes.isSelected()) {
/* 1805 */         qtys++;
/*      */       }
/* 1807 */       else if (check_row(i)) {
/* 1808 */         qtys++;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1814 */     if (qtys > 0) {
/* 1815 */       if (confirmar("Confirme Para imprimir etiquetas ", 2)) {
/* 1816 */         this.GX.startSqlTransaction();
/* 1817 */         this.GX.clearBatchSQL();
/* 1818 */         for (int i = 0; i < this.jTable.getRowCount(); i++) {
/* 1819 */           Object obj = this.jTable.getValueAt(i, 0);
/* 1820 */           if ((obj == null) || 
/* 1821 */             (!((Boolean)this.jTable.getValueAt(i, 0)).booleanValue())) continue;
/* 1822 */           String Subcode = (String)this.jTable.getValueAt(i, 1);
/* 1823 */           String desc = (String)this.jTable.getValueAt(i, 2);
/* 1824 */           String cant = (String)this.jTable.getValueAt(i, 3);
/* 1825 */           int qty = 0;
/*      */           try {
/* 1827 */             qty = new Double(cant.replace(",", "")).intValue();
/*      */           }
/*      */           catch (Exception localException1) {
/*      */           }
/* 1831 */           String spc = "0";
/* 1832 */           String height = this._txt_e_width.getText();
/* 1833 */           String width = this._txt_e_width.getText();
/* 1834 */           String prefijo = "0";
/* 1835 */           if (this.especiales.isSelected()) {
/* 1836 */             spc = "1";
/* 1837 */             if (height.compareTo("") == 0) {
/* 1838 */               height = "40";
/*      */             }
/* 1840 */             if (width.compareTo("") == 0) {
/* 1841 */               width = "40";
/*      */             }
/* 1843 */             if (this._chk_quitar_prefijo.isSelected()) {
/* 1844 */               prefijo = "1";
/*      */             }
/*      */           }
/*      */ 
/* 1848 */           String q = "insert into b_etiquetas (fecha,idarticulo,descripcion,cantidad,especial,especial_width,especial_height,quitarprefijo) ";
/* 1849 */           q = q + "values (getdate(),'" + Subcode + "','" + desc + "'," + qty + "," + spc + "," + width + "," + height + "," + prefijo + ")";
/*      */ 
/* 1851 */           if (this.codigos_no_existentes.isSelected()) {
/* 1852 */             this.GX.addBatchSQL(q);
/*      */           }
/* 1854 */           else if (check_row(i)) {
/* 1855 */             this.GX.addBatchSQL(q);
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/* 1864 */         this.GX.executeBatchSQL();
/* 1865 */         this.GX.commitSqlTransaction();
/*      */       }
/*      */     }
/*      */     else
/* 1869 */       error("No hay etiquetas validas seleccionadas ");
/*      */   }
/*      */ 
/*      */   public int getRandomNumber(int digitos)
/*      */   {
/* 1874 */     Random randomGenerator = new Random();
/* 1875 */     String _str = "";
/* 1876 */     for (int idx = 1; idx <= digitos; idx++) {
/* 1877 */       int randomInt = randomGenerator.nextInt(10);
/* 1878 */       _str = _str + randomInt;
/*      */     }
/*      */ 
/* 1881 */     int number = new Integer(_str).intValue();
/* 1882 */     return number;
/*      */   }
/*      */   public void error(String mensaje) {
/* 1885 */     if (SwingUtilities.isEventDispatchThread())
/* 1886 */       _error(mensaje);
/*      */     else
/* 1888 */       errorSwing(mensaje);
/*      */   }
/*      */ 
/*      */   private void errorSwing(String error) {
/* 1892 */     String _error = error;
/* 1893 */     Runnable _execute = new Runnable(_error) {
/*      */       public void run() {
/* 1895 */         CargaEtiquetasIndividuales.this.error(this.val$_error);
/*      */       }
/*      */     };
/* 1898 */     invokeAndWait(_execute);
/*      */   }
/*      */ 
/*      */   public void invokeAndWait(Runnable _execute) {
/*      */     try {
/* 1903 */       SwingUtilities.invokeAndWait(_execute);
/*      */     }
/*      */     catch (InterruptedException e) {
/* 1906 */       e.printStackTrace();
/*      */     }
/*      */     catch (InvocationTargetException e) {
/* 1909 */       e.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void _error(String mensaje) {
/* 1914 */     JOptionPane.showMessageDialog(this, mensaje, "Error en " + getTitle(), 
/* 1915 */       2);
/*      */   }
/*      */ 
/*      */   public boolean confirmar(String mensaje, int digitos) {
/* 1919 */     int generated = getRandomNumber(digitos);
/* 1920 */     String message = mensaje + generated;
/* 1921 */     String input = JOptionPane.showInputDialog(message);
/* 1922 */     if (input != null) {
/* 1923 */       boolean ok = input.compareTo(generated) == 0;
/* 1924 */       if (!ok) {
/* 1925 */         error("La Confirmacion Ingresada no es Correcta " + generated + "<>" + input);
/*      */       }
/* 1927 */       return ok;
/*      */     }
/* 1929 */     return false;
/*      */   }
/*      */ 
/*      */   public void clean_etiquetas()
/*      */   {
/* 1939 */     int n = JOptionPane.showConfirmDialog(
/* 1940 */       this, 
/* 1941 */       "Limpia Etiquetas?", 
/* 1942 */       "Limpiar", 
/* 1943 */       0);
/* 1944 */     if (n == 0) {
/* 1945 */       create_table(1);
/* 1946 */       create_table_cptes(1);
/* 1947 */       this.unidades = 0.0D;
/* 1948 */       this.Seleccion = new LinkedList();
/* 1949 */       this.jScrollPane_detalles.setViewportView(null);
/* 1950 */       this.Vacias.setText("");
/*      */     }
/*      */   }
/*      */ 
/*      */   private boolean check_prooveedor(String idproveedor)
/*      */   {
/* 1956 */     boolean aux = false;
/* 1957 */     String q = "select codigo from ma_cuentas where codigo like '21101%' ";
/* 1958 */     q = q + " and codigo like '" + idproveedor + "' ";
/* 1959 */     Object[][] results = this.GX.getSQLResult(q);
/* 1960 */     if ((results != null) && 
/* 1961 */       (results.length > 0)) {
/* 1962 */       aux = results[0][0].toString().compareTo("") != 0;
/*      */     }
/*      */ 
/* 1965 */     return aux;
/*      */   }
/*      */   private boolean check_tc(String tc) {
/* 1968 */     boolean aux = false;
/* 1969 */     String q = "select codigo from ta_comprobantes where ";
/* 1970 */     q = q + " codigo like '" + tc + "' ";
/* 1971 */     Object[][] results = this.GX.getSQLResult(q);
/* 1972 */     if ((results != null) && 
/* 1973 */       (results.length > 0)) {
/* 1974 */       aux = results[0][0].toString().compareTo("") != 0;
/*      */     }
/*      */ 
/* 1977 */     return aux;
/*      */   }
/*      */ 
/*      */   private void seleccionar(boolean b) {
/* 1981 */     if (b)
/* 1982 */       this.seleccionar.setText("deseleccionar");
/*      */     else {
/* 1984 */       this.seleccionar.setText("seleccionar");
/*      */     }
/* 1986 */     for (int i = 0; i < this.jTable.getRowCount(); i++)
/*      */     {
/* 1988 */       if (b) {
/* 1989 */         if (!this.codigos_no_existentes.isSelected()) {
/* 1990 */           if (check_row(i))
/* 1991 */             this.jTable.setValueAt(Boolean.valueOf(b), i, 0);
/*      */         }
/*      */         else {
/* 1994 */           this.jTable.setValueAt(Boolean.valueOf(b), i, 0);
/*      */         }
/*      */       }
/*      */       else
/* 1998 */         this.jTable.setValueAt(Boolean.valueOf(b), i, 0);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void seleccionar_cptes(boolean b)
/*      */   {
/* 2004 */     if (b)
/* 2005 */       this.seleccionar_cptes.setText("deseleccionar");
/*      */     else {
/* 2007 */       this.seleccionar_cptes.setText("seleccionar");
/*      */     }
/* 2009 */     for (int i = 0; i < this.jTable_cptes.getRowCount(); i++)
/*      */     {
/* 2011 */       if (b)
/* 2012 */         this.jTable_cptes.setValueAt(Boolean.valueOf(b), i, 0);
/*      */       else
/* 2014 */         this.jTable_cptes.setValueAt(Boolean.valueOf(b), i, 0);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void seleccionar_detalles(boolean b)
/*      */   {
/* 2020 */     if (b)
/* 2021 */       this.Seleccionar_items.setText("deseleccionar");
/*      */     else {
/* 2023 */       this.Seleccionar_items.setText("seleccionar");
/*      */     }
/* 2025 */     for (int i = 0; i < this.jTable_detalles.getRowCount(); i++)
/*      */     {
/* 2027 */       if (b)
/* 2028 */         this.jTable_detalles.setValueAt(Boolean.valueOf(b), i, 0);
/*      */       else
/* 2030 */         this.jTable_detalles.setValueAt(Boolean.valueOf(b), i, 0);
/*      */     }
/*      */   }
/*      */ 
/*      */   private JCheckBox getSeleccionar()
/*      */   {
/* 2042 */     if (this.seleccionar == null) {
/* 2043 */       this.seleccionar = new JCheckBox();
/* 2044 */       this.seleccionar.setText("Seleccionar");
/* 2045 */       this.seleccionar.setBounds(new Rectangle(12, 12, 130, 15));
/* 2046 */       this.seleccionar.addItemListener(new ItemListener()
/*      */       {
/*      */         public void itemStateChanged(ItemEvent e) {
/* 2049 */           JCheckBox chk = (JCheckBox)e.getSource();
/* 2050 */           CargaEtiquetasIndividuales.this.seleccionar(chk.isSelected());
/*      */         } } );
/*      */     }
/* 2054 */     return this.seleccionar;
/*      */   }
/*      */ 
/*      */   public void importarCompras()
/*      */   {
/* 2063 */     BuscadorComprobantes stk = new BuscadorComprobantes();
/* 2064 */     stk.setGTransfer(this.GX);
/* 2065 */     stk.setCompra();
/* 2066 */     stk.setCargaEtqiqueta(this);
/* 2067 */     stk.show();
/*      */   }
/*      */ 
/*      */   public void importarVentas() {
/* 2071 */     BuscadorComprobantes stk = new BuscadorComprobantes();
/* 2072 */     stk.setGTransfer(this.GX);
/* 2073 */     stk.setCargaEtqiqueta(this);
/* 2074 */     stk.setVenta();
/* 2075 */     stk.show();
/*      */   }
/*      */ 
/*      */   public void importarControl() {
/* 2079 */     BuscadorComprobantes stk = new BuscadorComprobantes();
/* 2080 */     stk.setGTransfer(this.GX);
/* 2081 */     stk.setCargaEtqiqueta(this);
/* 2082 */     stk.setControl(true);
/* 2083 */     stk.show();
/*      */   }
/*      */ 
/*      */   public void importarTrabajo() {
/* 2087 */     BuscadorComprobantes stk = new BuscadorComprobantes();
/* 2088 */     stk.setGTransfer(this.GX);
/* 2089 */     stk.setTrabajo(true);
/* 2090 */     stk.setCargaEtqiqueta(this);
/* 2091 */     stk.show();
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
/* 2103 */       URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/document-open.png");
/* 2104 */       this.Importar.setIcon(new ImageIcon(resourceURL));
/* 2105 */       this.Importar.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/* 2108 */           CargaEtiquetasIndividuales.this.importarCompras();
/*      */         } } );
/*      */     }
/* 2112 */     return this.Importar;
/*      */   }
/*      */   public void print_seleccion() {
/* 2115 */     if (this.Seleccion.size() > 0)
/* 2116 */       for (int i = 0; i < this.Seleccion.size(); i++) {
/* 2117 */         String[] aux = (String[])this.Seleccion.get(i);
/* 2118 */         System.out.println(aux[0] + " " + aux[1] + " " + aux[2] + " ");
/*      */       }
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
/* 2356 */       this.Agregar_etq.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/* 2359 */           CargaEtiquetasIndividuales.this.agregar_items_desde_cptes();
/*      */         } } );
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
/* 2377 */       this.agregar_items.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/* 2380 */           CargaEtiquetasIndividuales.this.agregar_items_desde_items();
/*      */         } } );
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
/* 2414 */       this.Limpiar.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/* 2417 */           CargaEtiquetasIndividuales.this.clean_etiquetas();
/*      */         } } );
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
/* 2437 */       URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/document-open.png");
/* 2438 */       this.ImportarV.setIcon(new ImageIcon(resourceURL));
/* 2439 */       this.ImportarV.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/* 2442 */           CargaEtiquetasIndividuales.this.importarVentas();
/*      */         } } );
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
/* 2511 */       URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/document-open.png");
/* 2512 */       this.importar_stock.setIcon(new ImageIcon(resourceURL));
/* 2513 */       this.importar_stock.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/* 2516 */           CargaEtiquetasIndividuales.this.importar_stock();
/*      */         }
/*      */       });
/*      */     }
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
/* 2552 */       URL resourceURL = getClass().getClassLoader().getResource("beta/tools/icons/document-open.png");
/* 2553 */       this.Control.setIcon(new ImageIcon(resourceURL));
/* 2554 */       this.Control.addActionListener(new ActionListener() {
/*      */         public void actionPerformed(ActionEvent e) {
/* 2556 */           System.out.println("actionPerformed()");
/* 2557 */           CargaEtiquetasIndividuales.this.importarControl();
/*      */         } } );
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
/* 2641 */       this.Trabajo.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/* 2644 */           CargaEtiquetasIndividuales.this.importarTrabajo();
/*      */         }
/*      */       });
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
/* 2664 */       this.Pendientes.addActionListener(new ActionListener()
/*      */       {
/*      */         public void actionPerformed(ActionEvent e) {
/* 2667 */           CargaEtiquetasIndividuales.this.agregar_items_desde_pendientes();
/*      */         } } );
/*      */     }
/* 2671 */     return this.Pendientes;
/*      */   }
/*      */ 
/*      */   public void load() {
/* 2675 */     String path = "c:/temp/etiquetas.xml";
/* 2676 */     File file = new File(path);
/*      */ 
/* 2678 */     XML_Etiquetas read = new XML_Etiquetas();
/* 2679 */     if (file.exists()) {
/* 2680 */       System.out.println("File exist. Reading Config Etiquetas.xml");
/*      */       try {
/* 2682 */         read.setFile(file);
/* 2683 */         read.readConfig();
/*      */       }
/*      */       catch (Exception localException) {
/*      */       }
/* 2687 */       this.formato = new Formato();
/* 2688 */       this.formato.hoja_margen_izquierdo = new Integer(read.getValorAtributo("hoja_margen_izquierdo")).intValue();
/* 2689 */       this.formato.hoja_margen_superior = new Integer(read.getValorAtributo("hoja_margen_superior")).intValue();
/* 2690 */       this.formato.hoja_anchura = new Integer(read.getValorAtributo("hoja_anchura")).intValue();
/* 2691 */       this.formato.hoja_longitud = new Integer(read.getValorAtributo("hoja_longitud")).intValue();
/* 2692 */       this.formato.hoja_columnas = new Integer(read.getValorAtributo("hoja_columnas")).intValue();
/* 2693 */       this.formato.hoja_filas = new Integer(read.getValorAtributo("hoja_filas")).intValue();
/* 2694 */       this.formato.celda_anchura = new Integer(read.getValorAtributo("celda_anchura")).intValue();
/* 2695 */       this.formato.celda_longitud = new Integer(read.getValorAtributo("celda_longitud")).intValue();
/* 2696 */       this.formato.celda_margen_izquierdo = new Integer(read.getValorAtributo("celda_margen_izquierdo")).intValue();
/* 2697 */       this.formato.celda_margen_superior = new Integer(read.getValorAtributo("celda_margen_superior")).intValue();
/* 2698 */       this.formato.hoja_espacio_columnas = new Integer(read.getValorAtributo("hoja_espacio_columnas")).intValue();
/* 2699 */       this.formato.hoja_espacion_filas = new Integer(read.getValorAtributo("hoja_espacio_filas")).intValue();
/* 2700 */       this.formato.celda_borde = new Integer(read.getValorAtributo("celda_borde")).intValue();
/* 2701 */       this.formato.hoja_borde = new Integer(read.getValorAtributo("hoja_borde")).intValue();
/* 2702 */       this.formato.print_longitud = new Integer(read.getValorAtributo("print_longitud")).intValue();
/* 2703 */       this.formato.print_anchura = new Integer(read.getValorAtributo("print_anchura")).intValue();
/* 2704 */       this.formato.print_margen_superior = new Integer(read.getValorAtributo("print_margen_superior")).intValue();
/* 2705 */       this.formato.print_margen_izquierdo = new Integer(read.getValorAtributo("print_margen_izquierdo")).intValue();
/* 2706 */       this.formato.codigo_font_size = new Integer(read.getValorAtributo("codigo_font_size")).intValue();
/* 2707 */       this.formato.descripcion_font_size = new Integer(read.getValorAtributo("descripcion_font_size")).intValue();
/* 2708 */       this.formato.print_escala_x = new Double(read.getValorAtributo("print_escala_x")).doubleValue();
/* 2709 */       this.formato.print_escala_y = new Double(read.getValorAtributo("print_escala_y")).doubleValue();
/*      */ 
/* 2711 */       this.formato.print();
/*      */     }
/*      */   }
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
/*      */ 
/* 2772 */     stk.load();
/* 2773 */     stk.create_table(1);
/* 2774 */     stk.show();
/*      */   }
/*      */ 
/*      */   public void addSeleccionados(LinkedList sel) {
/* 2778 */     if (this.Seleccion == null) {
/* 2779 */       this.Seleccion = new LinkedList();
/*      */     }
/* 2781 */     for (int i = 0; i < sel.size(); i++) {
/* 2782 */       String[] aux = (String[])sel.get(i);
/* 2783 */       boolean found = false;
/* 2784 */       int j = 0;
/* 2785 */       while (((found ? 0 : 1) & (j < this.Seleccion.size() ? 1 : 0)) != 0) {
/* 2786 */         String[] aux2 = (String[])this.Seleccion.get(j);
/* 2787 */         int v = 0;
/* 2788 */         boolean ok = true;
/* 2789 */         while ((ok & v < 3)) {
/* 2790 */           ok = aux[v].compareTo(aux2[v]) == 0;
/* 2791 */           v++;
/*      */         }
/* 2793 */         found = ok;
/* 2794 */         if (!found) {
/* 2795 */           j++;
/*      */         }
/*      */       }
/*      */ 
/* 2799 */       if (!found) {
/* 2800 */         this.Seleccion.add((String[])sel.get(i));
/* 2801 */         String[] auxx = (String[])sel.get(i);
/* 2802 */         System.out.println("Se agrego El comprobante " + auxx[0] + " " + auxx[1] + " " + auxx[2]);
/*      */       } else {
/* 2804 */         String[] auxx = (String[])sel.get(i);
/* 2805 */         System.out.println("El comprobante " + auxx[0] + " " + auxx[1] + " " + auxx[2] + " ya esta seleccionado!!");
/*      */       }
/*      */     }
/* 2808 */     refreash_cptes();
/*      */   }
/*      */ 
/*      */   public void refreash_cptes() {
/* 2812 */     if ((this.Seleccion != null) && 
/* 2813 */       (this.Seleccion.size() > 0)) {
/* 2814 */       create_table_cptes(this.Seleccion.size());
/* 2815 */       for (int i = 0; i < this.Seleccion.size(); i++) {
/* 2816 */         String[] auxx = (String[])this.Seleccion.get(i);
/* 2817 */         this.jTable_cptes.setValueAt(Boolean.valueOf(true), i, 0);
/* 2818 */         this.jTable_cptes.setValueAt(auxx[0], i, 1);
/* 2819 */         this.jTable_cptes.setValueAt(auxx[1], i, 2);
/* 2820 */         this.jTable_cptes.setValueAt(auxx[2], i, 3);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void imprimir2()
/*      */   {
/* 2828 */     LinkedList etqs = new LinkedList();
/* 2829 */     for (int i = 0; i < this.jTable.getRowCount(); i++) {
/* 2830 */       Object obj = this.jTable.getValueAt(i, 0);
/* 2831 */       if ((obj == null) || 
/* 2832 */         (!((Boolean)this.jTable.getValueAt(i, 0)).booleanValue()) || 
/* 2833 */         (!check_row(i)))
/*      */         continue;
/* 2835 */       String Subcode = (String)this.jTable.getValueAt(i, 1);
/*      */ 
/* 2837 */       String qtyx = (String)this.jTable.getValueAt(i, 3);
/* 2838 */       int qty = 0;
/*      */       try {
/* 2840 */         qty = new Integer(qtyx).intValue();
/*      */       }
/*      */       catch (Exception localException) {
/*      */       }
/* 2844 */       StrEtiqueta str_etiqueta = new StrEtiqueta();
/* 2845 */       str_etiqueta.setCodigo(Subcode);
/* 2846 */       str_etiqueta.setDescripcion((String)this.jTable.getValueAt(i, 2));
/* 2847 */       str_etiqueta.setCantidad(qty);
/* 2848 */       etqs.add(str_etiqueta);
/*      */     }
/*      */ 
/* 2857 */     if (etqs.size() > 0)
/*      */     {
/* 2859 */       ImpresionCodigoDeBarras PI = new ImpresionCodigoDeBarras();
/*      */ 
/* 2862 */       PI.setEtiquetas(etqs);
/* 2863 */       PI.show();
/*      */     }
/*      */     else
/*      */     {
/* 2868 */       JOptionPane.showMessageDialog(this, "Noy Hay Etiquetas Seleccionadas!!!");
/*      */     }
/*      */   }
/*      */ 
/*      */   private int idx_num(String code) {
/* 2873 */     int i = 0;
/* 2874 */     boolean num = true;
/* 2875 */     while (num) {
/* 2876 */       int xx = -1;
/*      */       try {
/* 2878 */         xx = new Integer(code.substring(i, i + 1)).intValue();
/*      */       } catch (Exception e) {
/* 2880 */         System.out.println("error en " + i + " >" + e.getMessage());
/*      */       }
/* 2882 */       num = xx >= 0;
/* 2883 */       if (!num) continue; i++;
/*      */     }
/* 2885 */     return i;
/*      */   }
/*      */ 
/*      */   private int order_strings(String code1, String code2) {
/* 2889 */     int mayor = -1;
/* 2890 */     int u = idx_num(code1);
/* 2891 */     int v = idx_num(code2);
/* 2892 */     int num1 = 0;
/*      */     try {
/* 2894 */       num1 = new Integer(code1.substring(0, u - 1)).intValue();
/*      */     } catch (Exception e) {
/* 2896 */       System.out.println("Error tratando de obtener el numero de " + code1);
/*      */     }
/* 2898 */     int num2 = 0;
/*      */     try {
/* 2900 */       num2 = new Integer(code2.substring(0, v - 1)).intValue();
/*      */     } catch (Exception e) {
/* 2902 */       System.out.println("Error tratando de obtener el numero de " + code2);
/*      */     }
/* 2904 */     if (num1 > num2) {
/* 2905 */       mayor = 1;
/*      */     }
/* 2907 */     else if (num1 == num2) {
/* 2908 */       if (u < 0) u = 0;
/* 2909 */       if (v < 0) v = 0;
/* 2910 */       String cx = "";
/*      */       try {
/* 2912 */         code1.substring(u, code1.length() - 1);
/*      */       }
/*      */       catch (Exception localException1) {
/*      */       }
/* 2916 */       String cy = "";
/*      */       try {
/* 2918 */         code2.substring(v, code2.length() - 1);
/*      */       }
/*      */       catch (Exception localException2) {
/*      */       }
/* 2922 */       if (cx.compareTo(cy) > 0)
/* 2923 */         mayor = 1;
/*      */       else
/* 2925 */         mayor = -1;
/*      */     }
/*      */     else {
/* 2928 */       mayor = -1;
/*      */     }
/*      */ 
/* 2931 */     return mayor;
/*      */   }
/*      */ 
/*      */   class EventKeyHandler extends KeyAdapter
/*      */   {
/*      */     EventKeyHandler()
/*      */     {
/*      */     }
/*      */ 
/*      */     public void keyPressed(KeyEvent e)
/*      */     {
/*  400 */       switch (e.getKeyCode()) {
/*      */       case 116:
/*  402 */         JTable jt = (JTable)e.getSource();
/*  403 */         int col = jt.getSelectedColumn();
/*  404 */         int row = jt.getSelectedRow();
/*  405 */         if (col != 1) break;
/*  406 */         CargaEtiquetasIndividuales.this.Buscar(jt, row, col);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   class HeaderListener extends MouseAdapter
/*      */   {
/*      */     JTableHeader header;
/*      */     SortButtonRenderer renderer;
/*      */ 
/*      */     HeaderListener(JTableHeader header, SortButtonRenderer renderer)
/*      */     {
/* 1044 */       this.header = header;
/* 1045 */       this.renderer = renderer;
/*      */     }
/*      */ 
/*      */     public void ordenarpor(int Col, String Asc) {
/* 1049 */       int sortCol = this.header.getTable().convertColumnIndexToModel(Col);
/* 1050 */       this.renderer.setPressedColumn(Col);
/* 1051 */       this.renderer.setSelectedColumn(Col);
/* 1052 */       this.header.repaint();
/*      */ 
/* 1054 */       if (this.header.getTable().isEditing())
/* 1055 */         this.header.getTable().getCellEditor().stopCellEditing();
/*      */       boolean isAscent;
/*      */       boolean isAscent;
/* 1059 */       if (Asc.compareTo("A") == 0) {
/* 1060 */         isAscent = true;
/*      */       }
/*      */       else {
/* 1063 */         isAscent = false;
/*      */       }
/*      */ 
/* 1066 */       ((SortableTableModel)this.header.getTable().getModel()).sortByColumn(
/* 1067 */         sortCol, isAscent);
/*      */ 
/* 1069 */       if (1 == this.renderer.getState(Col))
/* 1070 */         isAscent = true;
/*      */       else {
/* 1072 */         isAscent = false;
/*      */       }
/* 1074 */       ((SortableTableModel)this.header.getTable().getModel()).sortByColumn(
/* 1075 */         sortCol, isAscent);
/*      */     }
/*      */ 
/*      */     public void mousePressed(MouseEvent e)
/*      */     {
/* 1082 */       int col = this.header.columnAtPoint(e.getPoint());
/* 1083 */       int sortCol = this.header.getTable().convertColumnIndexToModel(col);
/* 1084 */       this.renderer.setPressedColumn(col);
/* 1085 */       this.renderer.setSelectedColumn(col);
/* 1086 */       this.header.repaint();
/*      */ 
/* 1088 */       if (this.header.getTable().isEditing())
/* 1089 */         this.header.getTable().getCellEditor().stopCellEditing();
/*      */       boolean isAscent;
/*      */       boolean isAscent;
/* 1093 */       if (1 == this.renderer.getState(col))
/* 1094 */         isAscent = true;
/*      */       else {
/* 1096 */         isAscent = false;
/*      */       }
/* 1098 */       ((SortableTableModel)this.header.getTable().getModel()).sortByColumn(
/* 1099 */         sortCol, isAscent);
/*      */     }
/*      */ 
/*      */     public void mouseReleased(MouseEvent e) {
/* 1103 */       int col = this.header.columnAtPoint(e.getPoint());
/* 1104 */       this.renderer.setPressedColumn(-1);
/* 1105 */       this.header.repaint();
/*      */     }
/*      */   }
/*      */ 
/*      */   class readTask
/*      */   {
/*      */     readTask()
/*      */     {
/*  820 */       CargaEtiquetasIndividuales.this.readFile();
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Beta\lib\beta.alfa.0.jar
 * Qualified Name:     visual.CargaEtiquetasIndividuales
 * JD-Core Version:    0.6.0
 */