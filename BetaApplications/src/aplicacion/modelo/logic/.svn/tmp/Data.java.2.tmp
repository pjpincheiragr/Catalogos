package aplicacion.modelo.logic;

import java.net.URL;
import java.util.LinkedList;

import aplicacion.herramientas.java.xml.Atributo;
import aplicacion.herramientas.java.xml.Element;
import aplicacion.herramientas.java.xml.XML;
import aplicacion.modelo.interfaces._parametros;

import aplicacion.sistema.error.logic._Logic;
//import beta.tools.connector.GTransfer;
import aplicacion.herramientas.conexion.*;
import aplicacion.herramientas.conexion.conectores.*;
import aplicacion.herramientas.conexion.creator.interfaces._Interface;

import java.io.*;
import java.sql.*;


public class Data {

protected ConnectionHandler connection_handler=null;

private int log=1; 

public void setLog(int log){
	this.log=log;
}
protected Element _data=null;
protected Element _primitive=null;
private LinkedList _batch=null;
private String host="";
private String os="";
private String nombre="";


	public ConnectionHandler getConnectionHandler() {
		return connection_handler;
	}

	public void setSql(ConnectionHandler sql) {
		this.connection_handler = sql;
		
	}
	
	public Object[][] getQueryResult(String query){
		Object[][] results=this.getResults(query);
		return results;
	}
	
	public String getUserValidacion(String password){
		String q="select iduser from b_users where pass like '"+password+"' ";
		String idvendedor="";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				idvendedor=(String) results[0][0];
			}
		}
		return idvendedor;
	}
	
	public boolean registrar_operacion(String tc,String idcomprobante,String cuenta,String iduser,String ip,String operacion){
		String q="";
		q+="insert into b_control_operaciones (fecha,tc,idcomprobante,cuenta,iduser,ip,operacion) values (";
		q+="getdate(),";
		q+="'"+tc+"',";
		q+="'"+idcomprobante+"',";
		q+="'"+cuenta+"',";
		q+="'"+iduser+"',";
		q+="'"+ip+"',";
		q+="'"+operacion+"')";
		this.clearBatch();
		this.addBatch(q);
		boolean error=this.executeBatch();
		return error;
	}
	
	public Data(){
		
		try {
		  java.net.InetAddress i = java.net.InetAddress.getLocalHost();
			   
			   host=i.getHostName();    // name
			   nombre=i.getHostAddress(); // IP address only
			   }
		catch(Exception ex){
		
		}
		try {
			this._read_primitive();	
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			this._read_data();	
		}catch(Exception e){
			//e.printStackTrace();
		}
		
	}
	
	
	public Object[][] getParametroSqlite(String id){
		Object[][] results=null;
		String q="";
		q+="select  id,value from parameters ";
		q+="where id like '%"+id+"%' ";
		q+="order by id";
		try {
			results=getConnector(_Interface._beta_database).getResults(q);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	
	public void setParameteroSqlite(String id, String value){
		String q="";
		q+="update parameters set value='"+value+"' where id like '"+id+"' ";
		q+="";
		getConnector(_Interface._beta_database).clearBatch();
		getConnector(_Interface._beta_database).addBatch(q);
		getConnector(_Interface._beta_database).executeBatch();
	}
	
	public String[] getColumns(){
		String[] columns=null;
		if (connection_handler.getDefaultConnector()!=null){
			columns=connection_handler.getDefaultConnector().getColumns();
		}
		return columns;
	}
	public String getSystemDateTime(){
		String q="select CONVERT(VARCHAR(10), getdate()  , 105)+' '+CONVERT(VARCHAR(10), getdate()  , 8)";
		//Object[][] aux=sql.getSQLResult(q);
		
		Object[][] aux=null;
		if (connection_handler.getDefaultConnector()!=null){
			aux=connection_handler.getDefaultConnector().getResults(q);	
		}
		
		String ax="";
		if (aux!=null){
			ax=aux[0][0].toString();
		}
		return ax;
	}
	
	public String getSystemTime(){
		String q="select CONVERT(VARCHAR(10), getdate()  , 8)";
		//Object[][] aux=sql.getSQLResult(q);
		
		Object[][] aux=null;
		if (connection_handler.getDefaultConnector()!=null){
			aux=connection_handler.getDefaultConnector().getResults(q);	
		}
		
		String ax="";
		if (aux!=null){
			ax=aux[0][0].toString();
		}
		return ax;
	}
	
	public String getSystemDate(){
		String q="select CONVERT(VARCHAR(10), getdate()  , 105)";
		//Object[][] aux=sql.getSQLResult(q);
		
		Object[][] aux=null;
		if (connection_handler.getDefaultConnector()!=null){
			aux=connection_handler.getDefaultConnector().getResults(q);	
		}
		
		String ax="";
		if (aux!=null){
			ax=aux[0][0].toString();
		}
		return ax;
	}
	
	public boolean getIsSuperUser(String iduser){
		boolean su=false;
		iduser.replaceAll("%", "");
		String q="select isnull(superusuario,0) ";
		q+="from b_users ";
		q+="where iduser like '"+iduser+"' ";
		Object[][] aux=null;
		if (connection_handler.getDefaultConnector()!=null){
			aux=connection_handler.getDefaultConnector().getResults(q);	
		}
		
		if (aux!=null){
			if (aux.length>0){
				String ax=aux[0][0].toString();
				su=ax.compareTo("1")==0;	
			}
			
		}
		return su;
	}
	
	public boolean _Log(String iduser,String operacion,String cuenta,String tc,String idcomprobante){
		
		String[] parameters=new String[]{
				iduser,
				operacion,
				nombre,
				host,
				cuenta,
				tc,
				idcomprobante
		};
		String q=this.getPrimitiveQuery("operacion", parameters);
		
		clearBatch();
		addBatch(q);
		boolean ok=!executeBatch();
		return ok;
	}
	
	public Generic getConnector(String id){
		
		return this.connection_handler.getConnector(id);
	}
	
	public String _getLog(String iduser,String operacion,String cuenta,String tc,String idcomprobante){
		
		String[] parameters=new String[]{
				iduser,
				operacion,
				nombre,
				host,
				cuenta,
				tc,
				idcomprobante
		};
		String q=this.getPrimitiveQuery("operacion", parameters);
		return q;
	}
	private void _read_data(){
		XML xml=new XML();
		Object _content=null;
		URL resourceURL =null;
		InputStream in =null;
		try {
			String _package = this.getClass().getName();
		    int lastDot = _package.lastIndexOf ('.');
		    if (lastDot==-1){}else {
		    	_package=_package.substring (0, lastDot);
		    	_package=_package.replace(".", "/");
		    	
		    	in = getClass().getClassLoader().getResourceAsStream(_package+"/xml/data.xml");
		        }
		    
		    
		    xml.setInputStream(in);
		    
			xml.readAll();
			_data=xml.getRoot();
	
		}catch(Exception e){
			//e.printStackTrace();
		}
	}
	
	private void _read_primitive(){
		XML xml=new XML();
		Object _content=null;
		URL resourceURL =null;
		InputStream in =null;
		try {
			String _package = this.getClass().getName();
		    int lastDot = _package.lastIndexOf ('.');
		    if (lastDot==-1){}else {
		    	_package=_package.substring (0, lastDot);
		    	_package=_package.replace(".", "/");
		    	
		    	//in = getClass().getClassLoader().getResourceAsStream(_package+"/xml/primitive.xml");
		    	in = getClass().getClassLoader().getResourceAsStream("aplicacion/modelo/logic/xml/primitive.xml");
		        }
		    
		    
		    xml.setInputStream(in);
		    
			xml.readAll();
			_primitive=xml.getRoot();
	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String getMessage(String idmessage){
		String msg="";
		java.util.List<Element> _elements=_data.getElements();
		boolean found=false;
		int i=0;
		while (i<_elements.size() & !found){
			Element e=(Element)_elements.get(i);
			Atributo a=e.getAtributo("id");
			//System.out.println("id>"+a.getValor());
			
			if (a.getValor().compareTo(idmessage)==0){
				a=e.getAtributo("text");
				//System.out.println("text>"+a.getValor());
				msg=a.getValor();
				found=true;
			}
			i++;
		}
		
		return msg;
	}
	
	public String getPrimitiveMessage(String idmessage){
		String msg="";
		java.util.List<Element> _elements=_primitive.getElements();
		boolean found=false;
		int i=0;
		while (i<_elements.size() & !found){
			Element e=(Element)_elements.get(i);
			Atributo a=e.getAtributo("id");
			//System.out.println("id>"+a.getValor());
			
			if (a.getValor().compareTo(idmessage)==0){
				a=e.getAtributo("text");
				//System.out.println("text>"+a.getValor());
				msg=a.getValor();
				found=true;
			}
			i++;
		}
		
		return msg;
	}
	
	
	public String getQuery(String id,Object[] parameters){
		String q=this.getMessage(id);
		int _parameters=0;
		int idx=0;
		
		String aux=q;
		while (idx<q.length() & idx>=0){
			
			idx=aux.indexOf("?");
			if (idx>=0){
				aux=aux.substring(idx+1,aux.length());
				_parameters++;
			}
			//System.out.println("params> ("+parameters+")> "+aux);
		}
		if (parameters!=null){
		if (_parameters==parameters.length){
			for (int i=0;i<_parameters;i++){
				int idz=q.indexOf("?");
				if (idz+2<q.length()){
					if (q.substring(idz-1, idz+2).compareTo("'?'")==0){
						if (parameters[i]==null){
							q=q.substring(0, idz-1)+parameters[i]+q.substring(idz+2);	
						}else {
							q=q.substring(0, idz)+parameters[i]+q.substring(idz+1);	
						}
						
					}else {
						q=q.substring(0, idz)+parameters[i]+q.substring(idz+1);	
					}	
				}else {
					q=q.substring(0, idz)+parameters[i]+q.substring(idz+1);
				}
				
				
				
			}
		}else {
			//System.out.println(id+" Data> Error definicion de parametros "+_parameters+"<>"+parameters.length);
		}
		}
		return q;
	}
	
	public String getPrimitiveQuery(String id,Object[] parameters){
		String q=this.getPrimitiveMessage(id);
		int _parameters=0;
		int idx=0;
		
		String aux=q;
		while (idx<q.length() & idx>=0){
			
			idx=aux.indexOf("?");
			if (idx>=0){
				aux=aux.substring(idx+1,aux.length());
				_parameters++;
			}
			//System.out.println("params> ("+parameters+")> "+aux);
		}
		if (parameters!=null){
		if (_parameters==parameters.length){
			for (int i=0;i<_parameters;i++){
				int idz=q.indexOf("?");
				if (idz+2<q.length()){
					if (q.substring(idz-1, idz+2).compareTo("'?'")==0){
						if (parameters[i]==null){
							q=q.substring(0, idz-1)+parameters[i]+q.substring(idz+2);	
						}else {
							q=q.substring(0, idz)+parameters[i]+q.substring(idz+1);	
						}
						
					}else {
						q=q.substring(0, idz)+parameters[i]+q.substring(idz+1);	
					}	
				}else {
					q=q.substring(0, idz)+parameters[i]+q.substring(idz+1);
				}
				
				
				
			}
		}else {
			//System.out.println(id+" Data> Error definicion de parametros "+_parameters+"<>"+parameters.length);
		}
		}
		return q;
	}
	/*
	public Object[][] getSQLResult(String query){
		boolean error=false;
		Object[][] results=null;
		try{
		results=sql.getSQLResultCustom(query);
		}catch(SQLException e){
			this.aviso(e);
			
		}
		return results;
	}*/
	
	public Object[][] getResults(String query){
		
		Object[][] results=null;
		results=connection_handler.getDefaultConnector().getResults(query);	
		return results;
	}
	
	public boolean hasResults(String query){
		boolean ok=false;
		ok=connection_handler.getDefaultConnector().hasResults(query);	
		return ok;
	}
	
	
	public void aviso(Exception e){
		if (log==1){
			
		}else{
			
		}
	}
	
	
	
	public String getIntelligentInsertion(String id,Object[] params){
		String q=this.getMessage(id);
		int parameters=0;
		int idx=0;
		
		String aux=q;
		while (idx<q.length() & idx>=0){
			
			idx=aux.indexOf("?");
			if (idx>=0){
				aux=aux.substring(idx+1,aux.length());
				parameters++;
			}
			//System.out.println("params> ("+parameters+")> "+aux);
		}
		
		int _index_start=q.indexOf("into");
		int _index_end  =q.indexOf("values");
		if (_index_start>=0){
			String columns=q.substring(_index_start, _index_end);
			
			_index_start=columns.indexOf("(");
			_index_end  =columns.indexOf(")");
			//System.out.println(columns+"indexes("+_index_start+1+":"+_index_end+1+")");
			columns=columns.substring(_index_start+1, _index_end+1);
			//System.out.println(columns);
			idx=0;
			int idz=idx;
			LinkedList _columns=new LinkedList();
			aux=columns;
			idz=aux.indexOf(",");
			while (idz>=0){
				String column="";
				if (idz<0){
					idz=aux.length()-1;;
					column=aux.substring(0, idz);
					idz=-1;
				}else {
					column=aux.substring(0, idz);
					aux=aux.substring(idz+1, aux.length());

				}
				_columns.add(column);
				//System.out.println(_columns.getLast());
				idz=aux.indexOf(",");
			}
			_columns.add(aux.substring(0, aux.length()-1));
			//System.out.println(_columns.getLast());
			
			for (int i=0;i<_columns.size();i++){
				//System.out.println("Columna "+i+"> "+((String)_columns.get(i)));
			}
		}
		
		if (parameters==params.length){
			for (int i=0;i<parameters;i++){
				int idy=q.indexOf("?");
				q=q.substring(0, idy)+params[i]+q.substring(idy+1);	
			}
		}else {
			//System.out.println("Data> Error definicion de parametros "+parameters+"<>"+params.length);
		}
		
		return q;
	}
	public void setAutoCommit(){
		//sql.startSqlTransaction();
		connection_handler.getDefaultConnector().setAutoComit(true);
	}
	public void beginTransaction(){
		//sql.startSqlTransaction();
		connection_handler.getDefaultConnector().startTransaction();
	}
	
	public boolean commitTransaction(){
		//		boolean error=sql.commitSqlTransaction();
		boolean error=connection_handler.getDefaultConnector().commitTransaction();
		return error;
	}
	public boolean rollbackTransaction(){
		//boolean error=sql.rollbackSqlTransaction();
		boolean error=connection_handler.getDefaultConnector().rollbackTransaction();
		return error;
	}
	
	public void clearBatch(){
		//sql.clearBatchSQL();
		connection_handler.getDefaultConnector().clearBatch();
		_batch=new LinkedList();
	}
	
	public void addBatch(String instruction){
		//sql.addBatchSQL(instruction);
		connection_handler.getDefaultConnector().addBatch(instruction);
		instruction=instruction.replaceAll("'", "''");
		String[] parameters=new String[]{
				instruction
		};
		String _instruction=this.getPrimitiveQuery("batch_instruction", parameters);
		_batch.add(_instruction);
	}
	
	
	private void clearConnection(){
		//sql.clearConnectionSQL();
	}
	
	protected void setAutoCommit(boolean b){
		//sql.setAutoCommitSQL(b);
		connection_handler.getDefaultConnector().setAutoComit(b);
	}
	
	public boolean executeUpdate(String update){
		boolean error=false;
		try {
			error=connection_handler.getDefaultConnector().executeUpdate(update);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	return error;
	}
	/**
	 * Retorna true si existe algun error en la ejecucion del Batch SQL
	 * @return
	 */
	public boolean executeBatch(){
		boolean error=false;
			try {
				error=connection_handler.getDefaultConnector().executeBatch();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return error;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String pc) {
		this.host = pc;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getIp() {
		return nombre;
	}

	public void setIp(String ip) {
		this.nombre = ip;
	}

	public LinkedList get_batch() {
		return _batch;
	}
	
	public boolean createODBCConnection(String odbc) {
		
     	boolean b=true;
         try {
			ODBC c=new ODBC(null);
			 c.setDebugMode(true);
			 c.setDatabase(odbc);
			 c.setId(odbc);
			 c.connect();
			 connection_handler.addConector(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			b=false;
		}
         return b;
     }
	public String getAnularPedidoAutorizacion(String tc,String idcomprobante){
		String q="update b_autorizaciones set anulada=1 where tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"'";
		return q;
		
	}
	public String getConfirmarAutorizacion(String tc,String idcomprobante,String validacion,String ip){
		String q="update b_autorizaciones set finalizada=1,idusuario_autoriza='"+validacion+"',ip='"+ip+"',fecha_finalizada=getdate() where tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"'";
		return q;
		
	}
	
	public String getPedirAutorizacion(String tc,String idcomprobante,String iduser,String ip){
		String q="insert into b_autorizaciones (tc,idcomprobante,fecha,idusuario_requiere,ip,anulada,finalizada) values ('"+tc+"','"+idcomprobante+"',getdate(),'"+iduser+"','"+ip+"',0,0) ";
		return q;
	}

	public String getAutorizacionesPendientes(String tc,String iduser){
		String q="select tc,idcomprobante from b_autorizaciones where tc like '"+tc+"' and idusuario_requiere like '"+iduser+"' and anulada=0 and finalizada=0 ";
		return q;
	}
	
	public String getAutorizacionesPendienteQuery(String tc,String idcomprobante){
		String q="select tc,idcomprobante from b_autorizaciones where tc like '"+tc+"' and idcomprobante like '"+idcomprobante+"' and anulada=0 and finalizada=0";
		return q;
	}
	
	public boolean existeAutorizacionesPendientes(String tc,String iduser){
		boolean existe=false;
		String q=this.getAutorizacionesPendientes(tc,iduser);
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				existe=true;
			}
		}
		return existe;
	}
	
	public boolean getAutorizacionesPendiente(String tc,String idcomprobante){
		boolean existe=false;
		String q=this.getAutorizacionesPendienteQuery(tc, idcomprobante);
		//System.out.println(q);
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				existe=true;
			}
		}
		return existe;
	}
	
	public int  getProximoOperacion(){
		int c=0;
		String q="";
		q+="select x_ultimo_nro from b_ta_cpte ";
		q+="where codigo = 'PDCH' ";
		Object[][] aux=connection_handler.getDefaultConnector().getResults(q);
		try{
			c=new Integer(aux[0][0].toString());
		}catch(Exception e){
			//System.out.println(e.getMessage());
		}
		return c;
	}
	
	
	
	public String getUpdateOperacion(){
		String q="";
		q+="update b_ta_cpte set x_ultimo_nro = x_ultimo_nro + 1 where codigo like 'PDCH'";
		return q;
	}
	
	public String getOperacion(String idoperacion,String iduser,String descripcion){
		String ip=this.getIp();
		String q="insert into b_auditor_operacion (idoperacion,iduser,ip,descripcion,fecha) values ";
		q+="('"+idoperacion+"','"+iduser+"','"+ip+"','"+descripcion+"',getdate()) ";
		return q;
	}
	
	
	public Object[][] getParametro(String id){
		Object[][] results=null;
		String q="";
		q+="select  idparametro,valor from b_parametros ";
		q+="where idparametro like '%"+id+"%' ";
		q+="order by idparametro";
		results=getResults(q);
		return results;
	}
	
	public void insertParametro(String id, String value){
		String q="";
		q+="insert into b_parametros (idparametro,valor)";
		q+="values ('"+id+"','"+value+"')";
		clearBatch();
		addBatch(q);
		executeBatch();
	}
	public void updateParametro(String id, String value){
		String q="";
		q+="update b_parametros set valor='"+value+"' where idparametro like '"+id+"' ";
		q+="";
		clearBatch();
		addBatch(q);
		executeBatch();
	}
	
	public boolean existeParametro(String id){
		boolean existe=false;
		Object[][] results=this.getParametroSqlite(id);
		if (results!=null){
			existe=results.length>0;
		}
		return existe;
	}
	public boolean existeParametroServer(String id){
		boolean existe=false;
		Object[][] results=this.getParametro(id);
		if (results!=null){
			existe=results.length>0;
		}
		return existe;
	}
	public void deleteParametro(String id){
		String q="";
		q+="delete from b_parametros where idparametro like '"+id+"' ";
		clearBatch();
		addBatch(q);
		executeBatch();
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
	

	public String getDepositoUser(String iduser){
		String deposito="   1";
		String q="select deposito from b_users where iduser like '"+iduser+"'";
		Object[][] results=this.getResults(q);
		if (results!=null){
			if (results.length>0){
				deposito=results[0][0].toString();
				
					
			}
			
		}
		return deposito;
	}
}
