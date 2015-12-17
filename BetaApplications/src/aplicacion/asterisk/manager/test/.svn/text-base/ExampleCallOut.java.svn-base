package aplicacion.asterisk.manager.test;

/* Cloudvox - place an outgoing call with Java
Place and receive phone calls via open API: http://cloudvox.com/
Learn about call scripting, Asterisk/AGI, voice apps: http://help.cloudvox.com/ */
import java.io.IOException;
 
import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.OriginateAction;
import org.asteriskjava.manager.response.ManagerResponse;
 
// Sample outgoing call
public class ExampleCallOut {
  /* Cloudvox outgoing settings. If these aren't defined, create a free account
and add an app at cloudvox.com. */
  public static final String HOSTNAME = "192.168.6.254";
  public static final int PORT = 5043;
  public static final String USERNAME = "6028";
  public static final String PASSWORD = "6028";
  public static final String CONTEXT = "OUTGOING_CONTEXT";
 
  private ManagerConnection managerConnection;
  // Number to call (10 digits, no leading 1 or 9)
  public String call = "2065551234";
 
  public ExampleCallOut() throws IOException {
    // Cloudvox outgoing settings
    ManagerConnectionFactory factory = new ManagerConnectionFactory (
      HOSTNAME, PORT, USERNAME, PASSWORD);
 
    this.managerConnection = factory.createManagerConnection();
  }
 
  public void run() throws IOException, AuthenticationFailedException, TimeoutException {
    OriginateAction originateAction;
    ManagerResponse originateResponse;
 
    originateAction = new OriginateAction();
    /* Format the call for dialing
       Channel example: Local/12065551234@outgoing-42 */
    originateAction.setChannel("Local/" + call + "@" + CONTEXT);
 
    /* What should happen when the phone is answered?
       Send it to an Asterisk application that can reside anywhere (AGI):
//originateAction.setApplication("AGI");
//originateAction.setData("agi://my.server.com/myscript.agi");
 
/* .. or, instead, to a specific single pre-made application (Swift text-to-speech): */
    originateAction.setApplication("Swift");
    originateAction.setData("\"I'm calling from Cloudvox to wish you a very happy birthday. This message could say anything, interact with the caller, play sounds, MP3s, voices, connect them to another call, or do pretty much anything your heart desires.\"");
 
    // Caller ID as a Cloudvox number
    originateAction.setCallerId("2063576220");
    originateAction.setTimeout(new Long(30000));
 
    /* Set variables that a script can access when the phone call is answered, such as
a user or message ID, tracking code, or other state */
    //originateAction.setVariables(new Hashtable(...))
 
    // connect to Asterisk and log in
    managerConnection.login();
 
    /* send the originate action and wait for a maximum of 30 seconds for Asterisk
to send a reply */
    originateResponse = managerConnection.sendAction(originateAction, 30000);
 
    // print out whether the originate succeeded or not
    System.out.println(originateResponse.getResponse());
 
    // and finally log off and disconnect
    managerConnection.logoff();
  }
 
  public static void main(String[] args) throws Exception {
    ExampleCallOut callout;
 
    callout = new ExampleCallOut();
    callout.run();
  }
}
