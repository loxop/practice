import java.util.LinkedList;

public class BusinessTasks {
	public String getTask(String[] list, int n){
		LinkedList<String> linkedList = new LinkedList<String>();
		for (String item: list){
			linkedList.add(item);
		}
		int index = 0;
		while (linkedList.size() > 1) {
			int i = (index + n - 1) % linkedList.size();
			linkedList.remove(i);
			index = i;
		}
		return linkedList.get(0);
	}
}

