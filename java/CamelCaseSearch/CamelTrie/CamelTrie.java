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
		int at = -1;
		boolean flag = false;
		CamelTrieNode p = root;
		for (int i = 0; i < word.length(); i++) {
			at = p.childIndex(word.charAt(i));
			if (at < 0) {
				p.dump(word, i);
				flag = true;
				break;
			}
			p = p.child(at);
		}
		System.out.format("%s\n", root);
		return flag;
	}

	public boolean contains(String prefix) {
		CamelTrieNode p = root;
		int at = -1;
		for (int i = 0; i < prefix.length(); i++){
			at = p.childIndex(prefix.charAt(i));
			if (at < 0) {
				return false;
			}
			p = p.child(at);
		}
		return true;
	}
}

class CamelTrieNode {
	char c;
	List<CamelTrieNode> children;

	CamelTrieNode(char c){
		// init children list on first use, to reduce memory usage
		this.c = c;
		children = null;
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

	int childIndex(char c) {
		if (children == null) {
			return -1;
		}
		return children.indexOf(c);
	}

	CamelTrieNode child(int index) {
		if (children != null && index >= 0 && children.size() > index ){
			return children.get(index);
		}
		return null;
	}

	// Used for 'dumping' a new string to a leaf node
	void dump(String s, int startsAt) {
		CamelTrieNode p = this;
		for (int i = startsAt; i < s.length(); i++) {
			p.addChild(s.charAt(i));
			p = p.child(p.children.size() - 1);
		}
	}
	
	public String toString() {
		return "Node(" + c + ")-" + children; 
	}

	public boolean equals(Object o) {
		if (o instanceof CamelTrieNode) {
			return ((CamelTrieNode)o).c == c;
		} else if (o instanceof Character) {
			return c == (Character)o;
		}
		return false;
	}
}

