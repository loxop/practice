package Trie;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class Trie {
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

	public boolean contains(String prefix) {
		TrieNode p = root;
		int index = -1;
		for (int i = 0; i < prefix.length(); i++){
			index = p.indexOfChild(prefix.charAt(i));
			if (index < 0) {
				return false;
			}
			p = p.childAt(index);
		}
		return true;
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

class CamelSearchResult {
	String prefix;
	TrieNode branch;
	
	CamelSearchResult(String p, TrieNode b){
		prefix = p;
		branch = b;
	}

	public void printOut(){	
		String str = prefix + branch.c;
		if (branch.children != null) {
			if (branch.hasEndPoint()) {
				System.out.println(str);
			}
			for (TrieNode child: branch.children){
				new CamelSearchResult(str, child).printOut();
			}
		}
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

