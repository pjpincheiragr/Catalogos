package aplicacion.asterisk.manager.test;

import org.asteriskjava.live.AsteriskServer;
import org.asteriskjava.live.AsteriskChannel;
import org.asteriskjava.live.AsteriskQueue;
import org.asteriskjava.live.AsteriskAgent;
import org.asteriskjava.live.MeetMeRoom;
import org.asteriskjava.live.DefaultAsteriskServer;

import org.asteriskjava.live.ManagerCommunicationException;

public class HelloLive
{
    private AsteriskServer asteriskServer;

    public HelloLive()
    {
        asteriskServer = new DefaultAsteriskServer( "192.168.4.114", "admin", "amp111");
    }

    public void run() throws ManagerCommunicationException
    {
    	
    	for (AsteriskAgent asteriskAgent: asteriskServer.getAgents()){
    		System.out.println("Agent"+asteriskAgent.getAgentId()+" name="+asteriskAgent.getName()+" state="+asteriskAgent.getState());
    	}
        for (AsteriskChannel asteriskChannel : asteriskServer.getChannels())
        {
            //System.out.println(asteriskChannel);
            System.out.println("Chanel:"+asteriskChannel.getName()+" state:"+asteriskChannel.getState());
        }

        for (AsteriskQueue asteriskQueue : asteriskServer.getQueues())
        {
            System.out.println("Queue:"+asteriskQueue.getName());
        }
        /*
        for (MeetMeRoom meetMeRoom : asteriskServer.getMeetMeRooms())
        {
            System.out.println(meetMeRoom);
        }*/
    }

    public static void main(String[] args) throws Exception
    {
        HelloLive helloLive = new HelloLive();
        helloLive.run();
    }
}