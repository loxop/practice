import CamelTrie.*;

public class CamelCaseSearch {
	static CamelTrie t;

	public static void main(String args[]) {
		t = new CamelTrie();
		t.insert("Apple");
		t.insert("Banana");
		t.insert("Cooconut");

		if (t.contains("App")) {
			System.out.format("contains App\n");
		}
		if (t.contains("Apl")) {
			System.out.format("contains Apl\n");
		}
		if (t.contains("Cooc")) {
			System.out.format("contains Cooc\n");
		}
	}
}
