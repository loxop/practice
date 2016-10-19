import Trie.*;
import java.io.*;
import java.util.List;

public class CamelCaseSearch {
	static Trie t = new Trie();

	public static String humanReadableSeconds(long nano){
		String result = "" + nano + "ns";
		double micro = nano / 100 / 10.0;
		if (micro > 0.1) {
			result = "" + micro + "us";
			double milli = micro / 100 / 10.0;
			if ( milli > 0.1) {
				result = "" + milli + "ms";
				double second = milli / 100 / 10.0;
				if (second > 0.1) {
					result = "" + second + "s";
					int minute = (int)second / 60;
					if (minute > 1) {
						result = "" + minute + "m" + ((int)second % 60) + "s";
					}
				}
			}
		}
		return result;
	}

	public static void readInputFile(String fileName, int report){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			String line;
			int reps = 0;
			int count = 0;
			long start = System.nanoTime();
			while((line = br.readLine()) != null) {
				t.insert(line);
				count++;
				if (count >= report) {
					reps++;
					System.out.println("" + (report * reps) + " words has been loaded.");
					count = 0;
				}
			}
			long end = System.nanoTime();
			System.out.println("Loading " + fileName + " completed in " + humanReadableSeconds(end - start) + ".");
		} catch (Exception e){}
	}

	public static void waitForKeyword(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		while(true){
			System.out.print("Search Keyword('q' for quit): ");
			try {
				line = br.readLine();
			} catch(Exception e){}

			if (line.equals("q")){
				break;
			}
			long start = System.nanoTime();
			List<String> result = t.camelSearch(line);
			long end = System.nanoTime();
			for (String str: result){
				System.out.println(str);
			}
			System.out.println("----Search completed in " + humanReadableSeconds(end - start) + ".\n");
		}
	}

	public static void main(String args[]) {
		if (args.length < 1) {
			System.out.println("Usage: java CamelCaseSearch FILE_NAME [REPORT_COUNT]");
			return;
		}

		int report = 1000000;
		if (args.length >= 2) {
			report = Integer.parseInt(args[1]);
		}

		readInputFile(args[0], report);
		waitForKeyword();

		
		/*
		t = new Trie();
		t.insert("HelloWorld");
		t.insert("FooBarTest");
		t.insert("FooBack");
		t.insert("FoolishBorder");
		t.insert("FopSong");
		t.insert("FoopBar");
		t.insert("ToopBar");
		t.insert("FallBan");
		t.insert("FallApple");
		t.insert("FBT");
		t.insert("FB");
		t.insert("HelloFooBar");

		for (String result: t.camelSearch("FB")){
			System.out.println(result);
		}
		*/
	}
}
