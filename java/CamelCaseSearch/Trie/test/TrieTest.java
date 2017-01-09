import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.instrument.Instrumentation;

public class TrieTest{
	public static void main(String args[]){
		ArrayTrie at = new ArrayTrie();
		Trie t = new Trie();

		at.insert("FooBarTest");
		at.insert("FooBar");
		at.insert("ElephantTiger");
		at.insert("HelloWorld");
		at.insert("FooBanThor");

		t.insert("FooBarTest");
		t.insert("FooBar");
		t.insert("ElephantTiger");
		t.insert("HelloWorld");
		t.insert("FooBanThor");
		
		System.out.println("ArrayTrie: " + ObjectSizeFetcher.getObjectSize(at));
		System.out.println("ListTrie: " + ObjectSizeFetcher.getObjectSize(t));
	}
}

class ArrayTrie {
	ArrayTrieNode root;

	public ArrayTrie() {
		root = new ArrayTrieNode(Character.MIN_VALUE);
	}

	// insert a word to this ArrayTrie, returns false if already exist
	public boolean insert(String word) {
		int at = -1;
		boolean flag = false;
		ArrayTrieNode p = root;
		for (int i = 0; i < word.length(); i++) {
			at = p.indexOfChild(word.charAt(i));
			if (at < 0) {
				dump(p, word, i);
				flag = true;
				break;
			}
			p = p.childAt(at);
		}

		// case of inserting word="App" when "Apple" is in ArrayTrie
		if (!flag && !p.hasEndPoint()) {
			p.addEndPoint();
			flag = true;
		}

		return flag;
	}

	// Used for 'dumping' a new string to a leaf node
	void dump(ArrayTrieNode p, String s, int startsAt) {
		for (int i = startsAt; i < s.length(); i++) {
			p.addChild(s.charAt(i));
			p = p.childAt(p.children.length - 1);
		}
		p.addEndPoint();
	}

	public List<String> camelSearch(String key){
		return camelDFS("", root, key, 0);
	}

	public List<String> camelDFS(String prefix, ArrayTrieNode node, String key, int key_index) {
		List<String> result = new LinkedList<String>();
		for (ArrayTrieNode child: node.children){
			if (child.isEndPoint()) {
				result.add(prefix);
				continue;
			}
			if (key.length() <= key_index || (Character.isUpperCase(key.charAt(key_index)) && Character.isLowerCase(child.c))) {
				result.addAll(camelDFS(prefix + child.c, child, key, key_index));
			} else if(key.charAt(key_index) == child.c) {
				result.addAll(camelDFS(prefix + child.c, child, key, key_index + 1));
			}
		}
		return result;
	}

	public String toString() {
		return root.toString();
	}
}

class ArrayTrieNode {
	char c;
	int index;
	ArrayTrieNode[] children;

	ArrayTrieNode(char c){
		// init children list on first use, to reduce memory usage
		this.c = c;
		children = null;
	}

	void addChild(char c) {
		if (children == null) {
			children = new ArrayTrieNode[4];
			index = 0;
		}
		if (index >= children.length){
			ArrayTrieNode[] newChildren = new ArrayTrieNode[children.length * 2];
			for (int i = 0; i < children.length; i++){
				newChildren[i] = children[i];
			}
			children = newChildren;
		}
		children[index++] = new ArrayTrieNode(c);
		System.gc();
	}

	int indexOfChild(char c) {
		if (children != null) {
			for (int i = 0; i < children.length; i++){
				if (children[i].c == c) {
					return i;
				}
			}
		}
		return -1;
	}

	ArrayTrieNode childAt(int index) {
		if (children != null && index >= 0 && children.length > index ){
			return children[index];
		}
		return null;
	}

	void addEndPoint() {
		if (!hasEndPoint()) {
			addChild(Character.MIN_VALUE);
		}
	}

	boolean hasEndPoint() {
		return indexOfChild(Character.MIN_VALUE) >= 0;
	}

	boolean isEndPoint() {
		return c == Character.MIN_VALUE;
	}
	
	public String toString() {
		return "(" + c + ")-" + children; 
	}
}


class Trie {
	TrieNode root;

	public Trie() {
		root = new TrieNode(Character.MIN_VALUE);
	}

	// insert a word to this Trie, returns false if already exist
	public boolean insert(String word) {
		int at = -1;
		boolean flag = false;
		TrieNode p = root;
		for (int i = 0; i < word.length(); i++) {
			at = p.indexOfChild(word.charAt(i));
			if (at < 0) {
				dump(p, word, i);
				flag = true;
				break;
			}
			p = p.childAt(at);
		}

		// case of inserting word="App" when "Apple" is in Trie
		if (!flag && !p.hasEndPoint()) {
			p.addEndPoint();
			flag = true;
		}

		return flag;
	}

	// Used for 'dumping' a new string to a leaf node
	void dump(TrieNode p, String s, int startsAt) {
		for (int i = startsAt; i < s.length(); i++) {
			p.addChild(s.charAt(i));
			p = p.childAt(p.children.size() - 1);
		}
		p.addEndPoint();
	}

	public List<String> camelSearch(String key){
		return camelDFS("", root, key, 0);
	}

	public List<String> camelDFS(String prefix, TrieNode node, String key, int key_index) {
		List<String> result = new LinkedList<String>();
		for (TrieNode child: node.children){
			if (child.isEndPoint()) {
				result.add(prefix);
				continue;
			}
			if (key.length() <= key_index || (Character.isUpperCase(key.charAt(key_index)) && Character.isLowerCase(child.c))) {
				result.addAll(camelDFS(prefix + child.c, child, key, key_index));
			} else if(key.charAt(key_index) == child.c) {
				result.addAll(camelDFS(prefix + child.c, child, key, key_index + 1));
			}
		}
		return result;
	}

	public String toString() {
		return root.toString();
	}
}

class TrieNode {
	char c;
	List<TrieNode> children;

	TrieNode(char c){
		// init children list on first use, to reduce memory usage
		this.c = c;
		children = null;
	}

	void addChild(char c) {
		if (children == null) {
			children = new ArrayList<TrieNode>();
		}
		children.add(new TrieNode(c));
	}

	int indexOfChild(char c) {
		if (children != null) {
			for (int i = 0; i < children.size(); i++){
				if (children.get(i).c == c) {
					return i;
				}
			}
		}
		return -1;
	}

	TrieNode childAt(int index) {
		if (children != null && index >= 0 && children.size() > index ){
			return children.get(index);
		}
		return null;
	}

	void addEndPoint() {
		if (!hasEndPoint()) {
			addChild(Character.MIN_VALUE);
		}
	}

	boolean hasEndPoint() {
		return indexOfChild(Character.MIN_VALUE) >= 0;
	}

	boolean isEndPoint() {
		return c == Character.MIN_VALUE;
	}
	
	public String toString() {
		return "(" + c + ")-" + children; 
	}
}

