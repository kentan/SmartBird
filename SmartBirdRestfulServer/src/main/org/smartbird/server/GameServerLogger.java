package org.smartbird.server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * TODO
 * implemented simply.
 * need to implement in sbcommon.jar
 * @author kentan
 *
 */
public class GameServerLogger {

	private static FileWriter fw = null;
	private static String ls = System.getProperty("line.separator");
	static{
		try {
			fw = new FileWriter(new File("out"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void writeln(Object buf){
		writeln(buf.toString());
	}
	public static void write(Object buf){
		write(buf.toString());
	}
	public static void writeln(String buf){
		write(buf);
		try {
			fw.write(ls);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void write(String buf){
		try {
			fw.write(buf);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void close(){
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
