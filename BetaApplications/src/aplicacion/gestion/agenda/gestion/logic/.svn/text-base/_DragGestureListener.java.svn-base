package aplicacion.gestion.agenda.gestion.logic;

import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;

import javax.swing.JTable;

public class _DragGestureListener implements DragGestureListener {
	private int x,y;
	private JTable table;
	private _Logic logic;
	private String tableName="";
	@Override
	public void dragGestureRecognized(DragGestureEvent dge) {
		// TODO Auto-generated method stub
		
		logic.addAvisos(y, x, table.getSelectedRows(),tableName);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public JTable getTable() {
		return table;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public _Logic getLogic() {
		return logic;
	}
	public void setLogic(_Logic logic) {
		this.logic = logic;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
