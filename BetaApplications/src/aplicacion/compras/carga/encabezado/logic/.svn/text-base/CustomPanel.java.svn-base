package aplicacion.compras.carga.encabezado.logic;
import javax.swing.JPanel;
import java.awt.Component;

public class CustomPanel extends JPanel{
	private _Logic logic=null;
	
	public void setLogic(_Logic logic){
		this.logic=logic;
	}
	public void remove(Component C){
		logic.sortComponents();
		super.remove(C);
	}
}
