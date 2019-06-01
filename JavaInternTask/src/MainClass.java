import java.io.*;


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
		
		System.out.print("Path to JSON report definition file: ");
		try {
			definitionPath= br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DefinitionReader def=new DefinitionReader(definitionPath);
		try {
			ReportWriter writer=new ReportWriter(def.getOb(), data.getAr());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
