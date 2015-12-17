package aplicacion.sistema.error.logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import aplicacion.modelo.logic.Data;

public class _Data extends Data {

	public void log(Exception e){
		System.out.println("Log en Data");
		
			   
			   
	    final Writer result = new StringWriter();
	    final PrintWriter printWriter = new PrintWriter(result);
		final String NEW_LINE = System.getProperty("line.separator");
		   
		
	    try{
	    for (StackTraceElement element : e.getStackTrace() ){
	        result.append( element.toString() );
	        result.append( NEW_LINE );
	     }
	    }
	    catch(IOException io){
	    	
	    }
	    String exception=result.toString();
	    
	    String localizedmessage=e.getLocalizedMessage();
	    String message=e.getMessage();
	    this.setHost(getHost().replaceAll("'", "''"));
	    message=message.replaceAll("'", "''");
	    localizedmessage=localizedmessage.replaceAll("'", "''");
	    exception=exception.replaceAll("'", "''");
	    String[] parameters=new String[]{
	    		getHost(),
	    		getIp(),
	    		message,
	    		localizedmessage,
	    		exception
	    };
		String q=this.getPrimitiveQuery("log", parameters);
		clearBatch();
		addBatch(q);
		boolean error=executeBatch();
		
		
		if (error){
			
			
			System.out.println("No se pudo logear error!");
		}
	}
	
	public void logBatch(){
		String[] parameters=new String[]{
			getIp(),
			getHost(),
		};
		String q=this.getPrimitiveQuery("batch", parameters);
		//emergency.startSqlTransaction();
		
		setAutoCommit(true);
		clearBatch();
		addBatch(q);
		System.out.println(q);
		for (int i=0;i<this.get_batch().size();i++){
			String qi=(String) this.get_batch().get(i);
			addBatch(qi);	
			System.out.println(qi);
		}
		boolean error=executeBatch();
		if (!error){
			//emergency.commitSqlTransaction();
		}else {
			//emergency.rollbackSqlTransaction();
		}
		
	}
}
