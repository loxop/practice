import java.util.*;

public class BinaryTreeCheck {
	public static void main(String[] args) {
		/*
		Input format:
		3		// Number of lines
		A B C 	// A->B A->C
		B D		// B->D
		C E		// C->E
		*/
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		in.nextLine();
		Map<String, Node> nodeMap = new HashMap<String, Node>();
		for (int i = 0; i < n; i++) {
			String line = in.nextLine();
			String[] tokens = line.split(" ");
			Node parent = nodeMap.get(tokens[0]);
			if (parent == null) {
				parent = new Node(tokens[0]);
				nodeMap.put(tokens[0], parent);
			}
			for (int j = 1; j < tokens.length; j++) {
				Node child = nodeMap.get(tokens[j]);
				if (child == null) {
					child = new Node(tokens[j]);
					nodeMap.put(tokens[j], child);
				}
				parent.children.add(child);
				child.parents.add(parent);
			}
		}
		List<Node> nodes = new ArrayList<Node>();
		nodes.addAll(nodeMap.values());

		BinaryTreeCheck check = new BinaryTreeCheck();
		System.out.println(check.isBinaryTree(nodes));
	}

	public boolean isBinaryTree(List<Node> nodes) {
		Node root = null;
		for (Node node: nodes) {
			// 1. Check number of parents (to check cycle)
			if (node.parents.size() > 1) {
				// a node has more than one parent
				return false;
			}
			
			// 2. Check the number of roots -- if more than one, false
			if (node.parents.size() == 0) {
				// A node has no parent, that means its a root
				if (root != null) {
					// there is another root already found
					// 		that means, the nodes are not a tree (separated graphs)
					return false;
				}
				root = node;
			}
		}
		// 3. Check the number of roots again -- if zero, false
		if (root == null) {
			// there is no root, which means it isn't a tree
			return false;
		}

		// If gets here, the graph of nodes is a tree.
		// 4. check if the root is a binary root.
		return isBinaryRoot(root);
	}

	public boolean isBinaryRoot(Node node) {
		// if the node has more than two children, the tree isn't a binary tree
		if (node.children.size() > 2 ){
			return false;
		}
		for (Node child: node.children) {
			// if the node has any decendant which is not a binary root, the tree isn't a binary tree
			if (!isBinaryRoot(child)){
				return false;
			}
		}
		return true;
	}
}

class Node {
	List<Node> children;
	List<Node> parents;
	String data;

	Node(String d){
		data = d;
		children = new ArrayList<Node>();
		parents = new ArrayList<Node>();
	}
}
