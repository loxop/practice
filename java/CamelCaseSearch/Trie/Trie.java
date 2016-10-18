package Trie;

import java.util.List;
import java.util.ArrayList;

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
			at = p.childIndex(word.charAt(i));
			if (at < 0) {
				p.dump(word, i);
				flag = true;
				break;
			}
			p = p.child(at);
		}
		return flag;
	}

	public boolean contains(String prefix) {
		TrieNode p = root;
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

	public CamelSearchResult camelSearch(String key) {
	}
}

class CamelSearchResult {
	String prefix;
	TrieNode branch;
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

	boolean hasChildren() {
		return children != null;
	}

	boolean hasChild(char c) {
		return hasChildren() && children.contains(c);
	}

	int childIndex(char c) {
		if (children != null) {
			for (int i = 0; i < children.size(); i++){
				if (children.get(i).c == c) {
					return i;
				}
			}
		}
		return -1;
	}

	TrieNode child(int index) {
		if (children != null && index >= 0 && children.size() > index ){
			return children.get(index);
		}
		return null;
	}

	// Used for 'dumping' a new string to a leaf node
	void dump(String s, int startsAt) {
		TrieNode p = this;
		for (int i = startsAt; i < s.length(); i++) {
			p.addChild(s.charAt(i));
			p = p.child(p.children.size() - 1);
		}
	}
	
	public String toString() {
		return "Node(" + c + ")-" + children; 
	}
}

