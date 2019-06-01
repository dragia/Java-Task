import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
			
			if((long)o.get("salesPeriod")<=pL) {
				if(uEM==false) {
					report.add(new Employee((String) o.get("name"), score=(double)tS/(double)sP ));
				} else if(uEM==true) {
					report.add(new Employee((String) o.get("name"), score=(double)tS/(double)sP*eM ));
				}
			}
			
		}
		
		Collections.sort(report, new Comparator<Employee>()
		{
			public int compare(Employee s1, Employee s2) {
				return Double.compare(s2.score, s1.score);
			}
		});
		
		treshold=(double)report.size()/100*tPT;
		treshold=Math.ceil(treshold);
		
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
		System.out.println("Result printed!");
	}
}
