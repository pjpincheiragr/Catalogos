package aplicacion.asterisk.manager.logic;
import java.io.*;
import java.util.*;
public class TaskManager {
	
	public TaskManager(){
		
	}

	
	public List listRunningProcesses() {
	List<String> processes = new ArrayList<String>();
	try {
	String line;
	Process p = Runtime.getRuntime().exec("tasklist.exe /nh /v");
	BufferedReader input = new BufferedReader
	(new InputStreamReader(p.getInputStream()));
	while ((line = input.readLine()) != null) {
	if (!line.trim().equals("")) {
	// keep only the process name
		String process=line.substring(0, line.indexOf(" "));
		if (line.contains("Activo")){
			process+=" ACTIVO";
		}
		processes.add(process);
	}

	}
	input.close();
	}
	catch (Exception err) {
	err.printStackTrace();
	}
	return processes;
	}
	public int isRunnningAnotherBeta(){
		
		List<String> processes = listRunningProcesses();
		String result = "";
		String NEW_LINE = System.getProperty("line.separator");
		// display the result
		Iterator<String> it = processes.iterator();
		int i = 0;
		System.out.println("Running processes :");
		int running=0;
		while (it.hasNext() ) {
			
			String process=it.next();
			System.out.println(process);
			if (process.contains("Beta.exe") & process.contains("ACTIVO")){
				
				running++;
			}
		i++;
		if (i==10) {
		result += "\n ";
		i = 0;
		}
		}	
		
		return running;
	}
	
	public static void main(String[] args){
		TaskManager TM=new TaskManager();
		int running=TM.isRunnningAnotherBeta();
		System.out.println("Esta corriendo otro beta?"+running);
	}
}
