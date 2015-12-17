package aplicacion.asterisk.manager.test;
import java.io.IOException;

import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.ManagerEventListener;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.StatusAction;
import org.asteriskjava.manager.event.ManagerEvent;
import org.asteriskjava.manager.event.NewExtenEvent;
import org.asteriskjava.manager.event.StatusCompleteEvent;
public class Controler implements ManagerEventListener {
	private ManagerConnection managerConnection;
	private String callerid="";
    public Controler() throws IOException
    {
        ManagerConnectionFactory factory = new ManagerConnectionFactory(
        		 "192.168.4.114", "admin", "amp111");


        this.managerConnection = factory.createManagerConnection();
    }

    public void run() throws IOException, AuthenticationFailedException,
            TimeoutException, InterruptedException
    {
        // register for events
        managerConnection.addEventListener(this);
        
        // connect to Asterisk and log in
        managerConnection.login();

        // request channel state
        managerConnection.sendAction(new StatusAction());
        
        // wait 10 seconds for events to come in
        Thread.sleep(100000);

        // and finally log off and disconnect
        managerConnection.logoff();
    }

    public void onManagerEvent(ManagerEvent event)
    {
        // just print received events
    	if (event instanceof NewExtenEvent){
    		
    		NewExtenEvent custom=(NewExtenEvent) event;
    		evalCall(custom);
    	}else{
    		if (event instanceof StatusCompleteEvent){
    			StatusCompleteEvent custom=(StatusCompleteEvent) event;
    			System.out.print("[StatusCompleteEvent]");
    			System.out.println(custom);
    		}
    	}
    	
    	
    	
        
    }
    
    public void evalCall(NewExtenEvent custom){
    	
    	System.out.println("Extension:"+custom.getExtension()+" Channel:"+custom.getChannel()+" Context:"+custom.getContext()+" AppData:"+custom.getAppData()+" Application:"+custom.getApplication()+" Source:"+custom.getSource());
    	
		if (custom.getChannel().contains("DAHDI")){
			if (custom.getExtension().compareTo("6028")==0){
				System.out.println("You Are Recieving a Call from "+callerid);
			}
			if (custom.getContext().contains("macro-user-callerid")){
				String data=custom.getAppData();
				if (data.contains("AMPUSER")){
					int index=data.indexOf("AMPUSER=")+"AMPUSER=".length();
					data=data.substring(index);
					//int end=data.indexOf(" ");
					System.out.println("Calling number:"+callerid);
				}
					
			}
			if (custom.getContext().contains("from-pstn")){
				//Context:from-pstn AppData:1 |Set|CALLERID(name)=5358237
				String data=custom.getAppData();
				if (data.contains("1 |Set|CALLERID(name)=")){
					int index=data.indexOf("1 |Set|CALLERID(name)=")+"1 |Set|CALLERID(name)=".length();
					data=data.substring(index,data.length());
					System.out.println("Calling number:"+data);
					callerid=data;
				}
			}
		}
    }

    public static void main(String[] args) throws Exception
    {
        Controler controler;
        controler = new Controler();
        controler.run();
    }
}
