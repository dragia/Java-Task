import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataReader {
	
	private String address;
	private JSONArray ar;
	private JSONParser parser=new JSONParser();
	
	public DataReader(String address) {
		super();
		this.address = address;
		try {
			ar = (JSONArray) parser.parse(new FileReader(address));
			
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

	public JSONArray getAr() {
		return ar;
	}
	
    //testing
	public void printAr() {

	    for (Object c : ar)
	    {
	      System.out.println(c+"");
	    }
	}

}

