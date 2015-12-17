package aplicacion.sistema.version.test;

import java.io.*;
import java.util.*;
public class Main {
 
	public static void main(String[] args)
    {
        System.out.println("Java version = "+ System.getProperty("java.version"));

        Calendar now = Calendar.getInstance();

        TimeZone timeZone = now.getTimeZone();
        timeZone.setID("America/Argentina/Buenos_Aires");
        

        System.out.println("Current System Time is: " + new Date(System.currentTimeMillis()));
        System.out.println("Current Calendar Time is: " + new Date(now.getTimeInMillis()));
        System.out.println("Current TimeZone is: \"" + timeZone.getDisplayName() + "\": ID = " + timeZone.getID());

        int offset = timeZone.getRawOffset();
        System.out.println("Current TimeZone.getRawOffset is: " + offset + ": offset in hours = "+ offset / 3600000);

        offset = timeZone.getOffset(now.getTimeInMillis());
        System.out.println("Current TimeZone.getOffset is: " + offset + ": offset in hours = "+ offset / 3600000);
        System.out.println("Current TimeZone.useDaylightTime is: " + timeZone.useDaylightTime());

        String id = "America/Argentina/Buenos_Aires";
        System.out.println("\ntimeZone.getTimeZone(ID) using ID = \""+ id + "\" returns\n" + timeZone.getTimeZone(id));
    }


}