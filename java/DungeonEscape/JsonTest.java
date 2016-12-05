import org.json.simple.*;
import org.json.simple.parser.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class JsonTest{
	public static void main(String[] args){
 
        try {
			String caseJson = new String(Files.readAllBytes(Paths.get("case.json")));

            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(caseJson);
			for (Object item: jsonArray){
				JSONArray itemArray = (JSONArray)item;
				for (Object subItem: itemArray){
					System.out.println(subItem);
				}
			}

			if (caseJson.length() > 1){
				return;
			}
             
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
 
	}
}
