import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;

class TestCase {
	String[] up;
	String[] down;
	String[] east;
	String[] west;
	int startLevel;
	int startEasting;
	int answer; 
	TestCase(String[] u, String[] d, String[] e, String[] w, int sl, int se, int a) {
		up = u;
		down = d;
		east = e;
		west = w;
		startLevel = sl;
		startEasting = se;
		answer = a;
	}
}

public class SystemTest {
	static String[] convertToStringArray(JSONArray jsonArray){
		String[] stringArray = new String[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++){
			stringArray[i] = jsonArray.get(i).toString();
		}
		return stringArray;
	}

	static ArrayList<TestCase> readDataFromJson(String filename) {
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(filename))
		ArrayList<TestCase> data = new ArrayList<TestCase>();
		String[] up;
		String[] down;
		String[] east;
		String[] west;
		int startLevel;
		int startEasting;
		int answer;

		for (Object item: jsonArray){
			JSONArray itemArray = (JSONArray)item;
			up = convertToStringArray((JSONArray)itemArray.get(0));
			down = convertToStringArray((JSONArray)itemArray.get(1));
			east = convertToStringArray((JSONArray)itemArray.get(2));
			west = convertToStringArray((JSONArray)itemArray.get(3));
			startLevel = Integer.parseInt(itemArray.get(4).toString());
			startEasting = Integer.parseInt(itemArray.get(5).toString());
			answer = Integer.parseInt(itemArray.get(6).toString());
			TestCase newCase = new TestCase(up, down, east, west, startLevel, startEasting, answer);
			data.add(newCase);
		}
		return data;
	}

	public static void main(String[] args) {
		DungeonEscape escape = new DungeonEscape();
		ArrayList<TestCase> data = readDataFromJson("case.json");
		int count = 0;
		for (TestCase item: data){
			int result = escape.exitTime(up, down, east, west, startLevel, startEasting);
			if (result != answer){
				System.out.print("\t>> Wrong: ");
				count++;
			}
			System.out.println("" + result + " / " + answer);
		}
		if (count > 0) {
			System.out.println("System test failed.");
		} else {
			System.out.println("System test passed.");
		}
	}
}

