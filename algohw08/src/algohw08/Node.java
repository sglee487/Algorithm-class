package algohw08;

public class Node {
	private String name;
	private int way;
	public Node(String _name,int _way) {
		name = _name;
		way = _way;
	}
	public void setName(String _name) {
		name = _name;
	}
	public void setWay(int _way) {
		way = _way;
	}
	public String getName() {
		return name;
	}
	public int getWay() {
		return way;
	}
}
