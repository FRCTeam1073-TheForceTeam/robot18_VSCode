package org.usfirst.frc1073.robot18;


import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {
	
	//Constructor
	// open file
	public Logger(){
		
	}
	
// just output user string with time and function name
	public static void setLog(String content) {

		String timeStamp = new SimpleDateFormat("HH:mm:ss:ms").format(Calendar.getInstance().getTime());
		String nameOfClass = Thread.currentThread().getStackTrace()[2].getClassName();
		String nameOfFunction = Thread.currentThread().getStackTrace()[2].getMethodName();
		
		try(FileWriter fw = new FileWriter("/home/lvuser/Logger.txt", true)){
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw);
			    fw.write(timeStamp + "::" + nameOfClass + "." + nameOfFunction + ": " + content + "\n");
			    fw.close();
			} catch (IOException e) {}
		
	}
	
	public static void clear(){
		try{
		    PrintWriter writer = new PrintWriter("/home/lvuser/Logger.txt", "UTF-8");
		    writer.println("");
		    writer.close();
		} catch (IOException e) {
		   // do something
		}
	}
	
}