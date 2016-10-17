package CamelTrie;

import java.util.List;
import java.util.LinkedList;

public class CamelTrie {
	CamelTrieNode root;

	public CamelTrie() {
		root = new CamelTrieNode(Character.MIN_VALUE);
	}

	// insert a word to this Trie, returns false if already exist
	public boolean insert(String word) {
		int at;
		boolean flag = false;
		CamelTrieNode p = root;
		for (int i = 0; i < word.length; i++) {
			at = p.children.indexOf(word.charAt(i));
			if (at < 0) {
				p.dump(word, i);
				flag = true;
			} else {
				p = children.get(at);
			}
		}
		return flag;
	}

	public boolean contains(Sring prefix) {
		TrieNode p = root;
		int at;
		for (int i = 0; i < prefix.length; i++){
			at = p.children.indexOf(prefix.charAt(i));
			if (at >= 0) {
			}
		}
	}
}

class CamelTrieNode {
	char c;
	List<CamelTrieNode> children;

	CamelTrieNode(char c){
		// init children list on first use, to reduce memory usage
		children = null;
		this.c = c;
	}

	void addChild(char c) {
		if (children == null) {
			children = new LinkedList<CamelTrieNode>();
		}
		children.add(new CamelTrieNode(c));
	}

	boolean hasChildren() {
		return children != null;
	}

	boolean hasChild(char c) {
		return hasChildren() && children.contains(c);
	}

	// Used for 'dumping' a new string to a leaf node
	void dump(String s, int startsAt) {
		TrieNode p = this;
		if (p.hasChildren()){
			System.out.format("Error: %c already has children\n", p.c);
			return;
		}
		for (int i = startsAt; i < s.length(), i++) {
			p.addChild(s.charAt[i]);
			p = p.children.get(0);
		}
	}
}

