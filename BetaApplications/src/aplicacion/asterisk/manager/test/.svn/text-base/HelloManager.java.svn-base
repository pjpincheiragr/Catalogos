package aplicacion.asterisk.manager.test;

import java.io.IOException;

import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.OriginateAction;
import org.asteriskjava.manager.response.ManagerResponse;
import org.asteriskjava.manager.*;
import org.asteriskjava.fastagi.*;
import org.asteriskjava.manager.action.*;
import org.asteriskjava.fastagi.command.ExecCommand;
import org.asteriskjava.manager.event.*;
import java.io.IOException; 

 
public class HelloManager
{
    private ManagerConnection managerConnection;

    public HelloManager() throws IOException
    {
        ManagerConnectionFactory factory = new ManagerConnectionFactory(
                "192.168.4.114", "admin", "amp111");

        this.managerConnection = factory.createManagerConnection();
    }

    public void run() throws IOException, AuthenticationFailedException,
            TimeoutException
    {
        OriginateAction originateAction;
        ManagerResponse originateResponse;

        originateAction = new OriginateAction();
        String user="6028";
        
        originateAction.setChannel("SIP/" + user);
        originateAction.setContext("default");
        originateAction.setExten("5358237");
        originateAction.setPriority(new Integer(1));
        
        
        String command=" Dial(\"SIP/6028-09a46b00\", \"DAHDI/2/2995358237|300|\") ";
       
      	
    	String DialStr ="SIP/100|40|tr"; 
    	ExecCommand OBJ_EXEC = new ExecCommand("DIAL",DialStr); 
    	
        // connect to Asterisk and log in
        managerConnection.login();
        
        // send the originate action and wait for a maximum of 30 seconds for Asterisk
        // to send a reply
        
        originateResponse = managerConnection.sendAction(originateAction, 30000);

        // print out whether the originate succeeded or not
        System.out.println(originateResponse.getResponse());

        // and finally log off and disconnect
        managerConnection.logoff();
    }

    public static void main(String[] args) throws Exception
    {
        HelloManager helloManager;

        helloManager = new HelloManager();
        helloManager.run();
    }
}