import CamelTrie.*;

public class CamelCaseSearch {
	static CamelTrie t;
	static {
		t = new CamelTrie();
	}

	public static void main() {
		t.insert("Apple");
		t.insert("Banana");
		t.insert("Cooconut");
	}
}
