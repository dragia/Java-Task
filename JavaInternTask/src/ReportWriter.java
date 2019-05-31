import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ReportWriter {
	
	private JSONObject def;
	private JSONArray dat;
	
	private long tPT;
	private boolean uEM;
	private long pL;
	
	private ArrayList<Employee> report =new ArrayList<Employee>();
	
	public ReportWriter(JSONObject ob, JSONArray ar) throws IOException {
		super();
		this.def = ob;
		this.dat = ar;
		tPT=(long) def.get("topPerformersThreshold");
		uEM=(boolean) def.get("useExprienceMultiplier");
		pL=(long) def.get("periodLimit");
		double score;
		double treshold;

		for(int i=0;i<dat.size();i++) {
			JSONObject o=(JSONObject) dat.get(i);
			long sP=(long)o.get("salesPeriod");
			long tS=(long)o.get("totalSales");
			double eM=(double)o.get("experienceMultiplier");
			
			System.out.println(o);
			System.out.println(sP+"--"+tS+"--"+eM);
			
			
			if((long)o.get("salesPeriod")<=pL) {
				if(uEM==false) {
					report.add(new Employee((String) o.get("name"), score=(double)tS/(double)sP ));
				} else if(uEM==true) {
					report.add(new Employee((String) o.get("name"), score=(double)tS/(double)sP*eM ));
				}
			}
			
		}
		
		for(int i=0; i<report.size(); i++) {
			System.out.println("Name: "+report.get(i).name+"Score: "+report.get(i).score);
		}
		
		
		Collections.sort(report, new Comparator<Employee>()
		{
			public int compare(Employee s1, Employee s2) {
				return Double.compare(s2.score, s1.score);
			}
		});
		
		for(int i=0; i<report.size(); i++) {
			System.out.println("Name: "+report.get(i).name+"Score: "+report.get(i).score);
		}
		
		treshold=(double)report.size()/100*tPT;
		System.out.println(treshold);
		treshold=Math.ceil(treshold);
		System.out.println(treshold);
		
		FileWriter csvWriter = new FileWriter("result.csv");  
		csvWriter.append("Name");  
		csvWriter.append(",");  
		csvWriter.append("Score");  
		csvWriter.append("\n");

		for (int i=0; i<treshold; i++) {  
			
			String str1=String.format("%1$,.2f", report.get(i).score);
			String str2=str1.replaceAll(",", ".");
			
		    csvWriter.append(String.join(",", report.get(i).name, str2 ));
		    csvWriter.append("\n");
		}

		csvWriter.flush();  
		csvWriter.close();  
	}
	
	
}
