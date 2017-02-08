import java.util.*;

public class MapListTest {
	public static void main(String[] args){
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		List<Integer> a = new LinkedList<Integer>();
		a.add(1);
		map.put(1,a);
		List<Integer> c = map.get(1);
		System.out.println("" + (c == a));
		System.out.println(c.size());
		System.out.println(map.get(1).size());
		c.add(2);
		System.out.println(map.get(1).size());
	}
}
