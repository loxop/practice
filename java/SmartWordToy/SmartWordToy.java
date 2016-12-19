import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class SmartWordToy {
	List<Integer> visitMap;
	String[][] forbidMap;

	private int convertWordToInt(String w) {
		int result = 0x00000000;
		for (int i = 0; i < 4; i++){
			result |= ((w.charAt(i) - 'a') << (i * 8));
		}
		return result;
	}

	private boolean isForbidden(String w) {
		for (String[] forbid: forbidMap) {
			boolean w_match = false;
			for (int i = 0; i < 4; i++) {
				boolean c_match = false;
				char c = w.charAt(i);
				for (int j = 0; j < forbid[i].length(); j++) {
					if (forbid[i].charAt(j) == c){
						c_match = true;
						break;
					}
				}
				if (!c_match){
					break;
				}
				if (i == 3){
					return true;
				}
			}
		}
		return false;
	}

	private String[] nextWords(String w){
		String[] next = new String[8];
		char[] charArray = w.toCharArray();
		for (int i = 0; i < 4; i++){
			char c = charArray[i];

			charArray[i] = (char)(((c - 'a') + 27) % 26 + 'a');
			next[2 * i] = new String(charArray);

			charArray[i] = (char)(((c - 'a') + 25) % 26 + 'a');
			next[2 * i + 1] = new String(charArray);

			charArray[i] = c;
		}
		return next;
	}

	public int minPresses(String start, String end, String[] forbid){
		visitMap = new ArrayList<Integer>();
		forbidMap = new String[forbid.length][];
		for (int i = 0; i < forbid.length; i++) {
			forbidMap[i] = forbid[i].split(" ");
		}

		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(start, 0));
		int e = convertWordToInt(end);

		while (!pq.isEmpty()) {
			Node n = pq.poll();
			System.out.println("Polled: " + n.word);
			int w = convertWordToInt(n.word);
			if (w == e){
				return n.cost;
			}

			if (visitMap.indexOf(w) >= 0){
				continue;
			}
			visitMap.add(w);

			String[] next = nextWords(n.word);
			for (String n_word: next){
				if (isForbidden(n_word)) {
					System.out.println("Forbidden: " + n_word);
					continue;
				}
				pq.add(new Node(n_word, n.cost + 1));
					System.out.println("Added: " + n_word);
			}
		}

		return -1;
	}

	public static void main(String[] args){
		String start = "aaaa";
		String end = "bbbb";
		//String[] forbid = {"a a a z", "a a z a", "a z a a", "z a a a", "a z z z", "z a z z", "z z a z", "z z z a"};
		String[] forbid = {"b b b b"};
		SmartWordToy toy = new SmartWordToy();
		System.out.println("" + toy.minPresses(start, end, forbid));
	}
}

class Node implements Comparable<Node> {
	public String word;
	public int cost;

	public Node(String w, int c){
		word = w;
		cost = c;
	}

	public int compareTo(Node n){
		return cost - n.cost;
	}
}
