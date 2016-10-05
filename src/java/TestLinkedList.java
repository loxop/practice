import LinkedList.LinkedList;

public class TestLinkedList {
	public static void main(String[] args){
		if (args.length < 1) {
			System.out.println("No parameter!");
			return;
		}


		LinkedList l = new LinkedList();
		for (int i = 0; i < args[0].length(); i++){
			l.append(args[0].charAt(i));
		}
		System.out.println(l);
	}
}
