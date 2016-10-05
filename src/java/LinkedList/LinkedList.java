package LinkedList;

import java.lang.StringBuffer;

class Node {
	char data;
	Node next;

	public Node(char data){
		this.data = data;
		next = null;
	}
}

public class LinkedList {
	Node head;

	public LinkedList(){
		head = null;
	}
	
	public void append(char data){
		if (head == null){
			head = new Node(data);
			return;
		}

		Node tail = head;
		while (tail.next != null){
			tail = tail.next;
		}
		tail.next = new Node(data);
	}

	public String toString(){
		if (head == null){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		Node pointer = head;
		while(pointer.next != null){
			sb.append(pointer.data);
			sb.append("->");
			pointer = pointer.next;
		}
		sb.append(pointer.data);
		return sb.toString();
	}
}
