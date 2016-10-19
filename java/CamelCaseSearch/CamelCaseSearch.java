import Trie.*;

public class CamelCaseSearch {
	static Trie t;

	public static void main(String args[]) {
		t = new Trie();
		t.insert("Apple");
		t.insert("Application");
		t.insert("App");
		t.insert("Aptitude");
		t.insert("Bananan");
		t.insert("Banapple");
		t.insert("Banana");
		t.insert("Cooconut");

		System.out.println(t);

		/*
		if (t.contains("App")) {
			System.out.format("contains App\n");
		}
		if (t.contains("Apl")) {
			System.out.format("contains Apl\n");
		}
		if (t.contains("Cooc")) {
			System.out.format("contains Cooc\n");
		}
		*/
	}
}
