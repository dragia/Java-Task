import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DefinitionReader {
	
	private String address;
	private JSONObject ob;
	private JSONParser parser=new JSONParser();
	
	public DefinitionReader(String address) {
		super();
		this.address = address;
		try {
			ob = (JSONObject) parser.parse(new FileReader(address));
			
			} catch (FileNotFoundException e) {
			e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public JSONObject getOb() {
		return ob;
	}
	
    //testing
	public void printOb() {

	    
	      System.out.println(ob+"");
	}
}
