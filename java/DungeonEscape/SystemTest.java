import org.json.simple.*;

class TestCase {
	String[] up;
	String[] down;
	String[] east;
	String[] west;
	Integer startLevel;
	Integer startEasting;
	TestCase( String[] u, String[] d, String[] e, String[] w, Integer l, Integer e) {

	}
}

public class SystemTest {
	static void readDataFromJson(String filename, String[] up, String[] down, String[] east, String[] west, Integer startLevel, Integer startEasting) {
	}


	public static void main(String[] args) {
		DungeonEscape escape = new DungeonEscape();
		String[] up;
		String[] down;
		String[] east;
		String[] west;
		Integer startLevel;
		Integer startEasting;
		int result = escape.exitTime(up, down, east, west, startLevel, startEasting);
		System.out.println("Result : " + result);
	}
}

