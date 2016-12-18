import DungeonEscape.DungeonEscape;
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

	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (int i = 0; i < up.length - 1; i++){
			sb.append(up[i]);
			sb.append(", ");
		}
		sb.append(up[up.length - 1]);
		sb.append("], ");

		sb.append("[");
		for (int i = 0; i < down.length - 1; i++){
			sb.append(down[i]);
			sb.append(", ");
		}
		sb.append(down[down.length - 1]);
		sb.append("], ");
		
		sb.append("[");
		for (int i = 0; i < east.length - 1; i++){
			sb.append(east[i]);
			sb.append(", ");
		}
		sb.append(east[east.length - 1]);
		sb.append("], ");

		sb.append("[");
		for (int i = 0; i < west.length - 1; i++){
			sb.append(west[i]);
			sb.append(", ");
		}
		sb.append(west[west.length - 1]);
		sb.append("], ");
		sb.append(startLevel + ", ");
		sb.append(startEasting);
		return sb.toString();
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
		try {
			JSONParser jsonParser = new JSONParser();
			JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(filename));
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
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		DungeonEscape escape = new DungeonEscape();
		ArrayList<TestCase> data = readDataFromJson("case.json");
		int count = 0;
		int fail_count = 0;
		for (TestCase item: data){
			count++;
			int result = escape.exitTime(item.up, item.down, item.east, item.west, item.startLevel, item.startEasting);
			System.out.format("%2d. ", count);
			if (result != item.answer){
				System.out.println("\t>> Wrong:");
				System.out.println(item);
				fail_count++;
			}
			System.out.println("" + result + " / " + item.answer);
		}
		if (fail_count > 0) {
			System.out.println("System test failed.");
		} else {
			System.out.println("System test passed.");
		}
	}
}

