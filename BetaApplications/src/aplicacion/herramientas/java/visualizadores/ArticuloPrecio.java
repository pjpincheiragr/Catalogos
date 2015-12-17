package aplicacion.herramientas.java.visualizadores;

import aplicacion.herramientas.java.visualselector.constructor._Constructor;
import aplicacion.herramientas.java.visualselector.logic.Columna;
import aplicacion.herramientas.java.visualselector.logic.Filtro;
import aplicacion.herramientas.java.visualselector.logic._Logic;
import aplicacion.modelo.constructor.Constructor;
import javax.swing.JTextField;

public class ArticuloPrecio extends Generico
{
  private boolean suspendidov = false;
  private boolean suspendidoc = false;
  private String idarticulo = "";

  private boolean publico = false;

  private String idcliente = "";

  public boolean isSuspendidov()
  {
    return this.suspendidov;
  }

  public void setSuspendidov(boolean suspendidov) {
    this.suspendidov = suspendidov;
  }

  public boolean isSuspendidoc() {
    return this.suspendidoc;
  }

  public void setSuspendidoc(boolean suspendidoc) {
    this.suspendidoc = suspendidoc;
  }

  public boolean isPublico()
  {
    return this.publico;
  }

  public void setPublico(boolean publico) {
    this.publico = publico;
  }

  public String getIdCliente()
  {
    return this.idcliente;
  }

  public void setIdCliente(String idproveedor) {
    this.idcliente = idproveedor;
  }

  public ArticuloPrecio(Constructor C) {
    super(C);
  }

  public void initializeConstructor() {
    this.C = new _Constructor();
  }

  public int initializeLogic(JTextField tx)
  {
    _Logic logic = (_Logic)this.C
      .getLogic();
    logic.setCaller(tx);
    Columna c = new Columna();
    Filtro f = new Filtro();
    c = new Columna();
    c.setNombre("s.fecha");
    c.setAlias("fecha");
    c.setWidth(100);
    logic.addColumn(c);

    c = new Columna();
    c.setNombre("c.codigo");
    c.setAlias("codigo");
    c.setWidth(80);
    logic.addColumn(c);

    c = new Columna();
    c.setNombre("c.descripcion");
    c.setAlias("descripcion");
    c.setWidth(140);
    logic.addColumn(c);

    c = new Columna();
    c.setNombre("s.tc");
    c.setAlias("tc");
    c.setWidth(40);
    logic.addColumn(c);

    c = new Columna();
    c.setNombre("s.idcomprobante");
    c.setAlias("idcomprobante");
    c.setWidth(100);
    logic.addColumn(c);

    c = new Columna();
    c.setNombre("s.cantidadud");
    c.setAlias("cant");
    c.setWidth(60);
    logic.addColumn(c);

    c = new Columna();
    c.setNombre("round(s.importe_s_iva*1.21,2)");
    c.setAlias("$venta");
    c.setWidth(100);
    c.setColumnField(tx);
    logic.addColumn(c);

    c = new Columna();
    c.setNombre("v.nombre");
    c.setAlias("vendedor");
    c.setWidth(70);
    logic.addColumn(c);

    f = new Filtro();
    f.setNombre("s.idarticulo");
    f.setValor(this.idarticulo);
    logic.addFilter(f);

    if ((this.idcliente != null) && 
      (this.idcliente.compareTo("") != 0)) {
      f = new Filtro();
      f.setNombre("s.cuentaproveedor");
      f.setAlias("cuenta");
      f.setValor(this.idcliente);
      logic.addFilter(f);
    }

    logic
      .setFromTable(" v_mv_stock s left outer join ma_cuentas c on s.cuentaproveedor =c.codigo left outer join ma_cuentasadic d on c.codigo=d.codigo left outer join v_ta_vendedores v on s.idvendedor=v.idvendedor  ");

    logic.setTop(100);
    logic.setOrderby("s.fecha desc ");
    String restriction = "";
    restriction = restriction + " (s.tc like 'fvn'  or s.tc like 'tk' or s.tc like 'tkfc' or s.tc like 'fc' or s.tc like 'nc' or s.tc like 'fp' or s.tc like 'ncfp' ) and s.idarticulo like '" + 
      this.idarticulo + "' ";

    if (restriction.length() > 0) {
      logic.setRestriction(restriction);
    }
    logic.setTitle("Buscador de Precio de Venta");

    int n = logic._loadoptions();

    return n;
  }

  public String getIdarticulo()
  {
    return this.idarticulo;
  }

  public void setIdarticulo(String idarticulo) {
    this.idarticulo = idarticulo;
  }
}