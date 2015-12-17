package aplicacion.sistema.menu.logic;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import aplicacion.herramientas.conexion.conectores.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;



import aplicacion.herramientas.conexion.creator.interfaces._Interface;
import aplicacion.modelo.logic.Data;

public class _Data extends Data{
	
	public String getVersion(){
		Object[][] results=this.getParametroSqlite("version");
		String _valor="";
		if (results!=null){
			if (results.length>0){
				try {
					_valor=results[0][1].toString();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
				
		}
		
		return _valor;
	}
	
	private String  _system_loadAplicationsQuery(String area,String iduser){
		String q="";
		q=q+" select a.area,a.menu_nombre,a.lanzador from b_users_aplicaciones u ";
		q=q+" left outer join b_aplicaciones a ";
		q=q+" on u.idaplicacion = a.idaplicacion ";
		q=q+" where u.iduser like '"+iduser+"' ";
		q=q+" and a.area like '"+area+"' ";
		q=q+" and a.visible=1 ";
		q=q+" order by a.area,a.menu_nombre ";
		return q;
	}
	
	public String getParametroServer(String id){
		String q="select valor from b_parametros where idparametro like '"+id+"'";
		String valor="";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				valor=(String) results[0][0];
			}
		}
		return valor;
	}
	
	public boolean insertParametroServer(String id,String valor){
		String q="insert into b_parametros (idparametro,valor) values('"+id+"','"+valor+"') ";
		this.clearBatch();
		this.addBatch(q);
		return !this.executeBatch();
	}
	
	
	public int getImages(String iduser){
		int i=0;
		String q="select count(iduser) from wallpapers where iduser like '"+iduser+"'";
		Object[][] results=this.getConnector("MySQL").getResults(q);
		if (results!=null){
			if (results.length>0){
				try {
					i=new Integer((String)results[0][0]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return i;
	}
	
	public void eliminar_fotos(String iduser){
		String q="delete from wallpapers where iduser like '"+iduser+"'";
		this.getConnector("MySQL").clearBatch();
		this.getConnector("MySQL").addBatch(q);
		this.getConnector("MySQL").executeBatch();
	}
	private String  _system_loadUserAreasQuery(String iduser){
		String q="";
		q=q+" select a.area from b_users_aplicaciones u ";
		q=q+" left outer join b_aplicaciones a ";
		q=q+" on u.idaplicacion = a.idaplicacion ";
		q=q+" where u.iduser like '"+iduser+"' ";
		q=q+" group by a.area ";
		q=q+" order by a.area ";
		return q;
	}
	
	public Object[][] _system_loadUserAreas(String iduser){
		Object[][] results=null;
		if (connection_handler.getDefaultConnector()!=null){
			results=connection_handler.getDefaultConnector().getResults(this._system_loadUserAreasQuery(iduser));
		}
		
		return results;
	}
	
	public Object[][] _system_loadAplications(String area,String iduser){
		Object[][] results=connection_handler.getDefaultConnector().getResults(this._system_loadAplicationsQuery(area,iduser));
		return results;
	}
	
	private String  loadAplicationsQuery(String area,String iduser){
		String q="";
		q=q+" select area,label,id from Aplicaciones ";
		q=q+" where area like '"+area+"'";
		q=q+" order by area,label ";
		return q;
	}
	
	private String  loadUserAreasQuery(String iduser){
		String q="";
		q=q+" select area from Aplicaciones ";
		q=q+" group by area ";
		q=q+" order by area ";
		return q;
	}
	
	public Object[][] loadUserAreas(String iduser){
		Object[][] results=getConnector(_Interface._beta_database).getResults(this.loadUserAreasQuery(iduser));
		return results;
	}
	
	public Object[][] loadAplications(String area,String iduser){
		Object[][] results=getConnector(_Interface._beta_database).getResults(this.loadAplicationsQuery(area,iduser));
		return results;
	}
	
	public Object[][] getBackground(String iduser){
		String q="";
		q+="select isnull(background,'') from b_users where iduser like '"+iduser+"' ";
		q+="";
		Object[][] results=getResults(q);
		return results;
	}
	public Object[][] getParametroSqlite(String id){
		Object[][] results=null;
		String q="";
		q+="select  id,value from parameters ";
		q+="where id like '%"+id+"%' ";
		q+="order by id";
		try {
			SQLite Connector=(SQLite)getConnector(_Interface._beta_database);
			if (Connector!=null){
				results=Connector.getResults(q);	
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	
	public String getPrinted(String id){
		String q="";
		q+="upbdate b_etiquetas set impresa=1 where id ="+id+" ";
		return q;
	}
	
}
