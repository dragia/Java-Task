import java.io.*;

import javax.xml.crypto.Data;


public class MainClass {
	public static void main(String[] args){
		String dataPath = null;
		String definitionPath= null;
		
		
		
		InputStreamReader r = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(r);
		
		System.out.print("Path to JSON data file: ");
		try {
			dataPath= br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DataReader data=new DataReader(dataPath);
		data.printAr();
		
		System.out.print("Path to JSON report definition file: ");
		try {
			definitionPath= br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DefinitionReader def=new DefinitionReader(definitionPath);
		def.printOb();
		
		try {
			ReportWriter writer=new ReportWriter(def.getOb(), data.getAr());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//testing
		System.out.println("----------------");
		System.out.println(dataPath);
		System.out.println(definitionPath);
	}
}
