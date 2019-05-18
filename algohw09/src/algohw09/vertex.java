package algohw09;

import java.util.ArrayList;
import java.util.HashMap;

public class vertex {
	// 자기와 연결되어있는 vertex를 받고, 그 vertex 의 way 길이를 저장할 수 있어야 함.
	// arraylist로 vertex를 저장하고, hashmap으로 그 vertex의 way를 저장하도록 만들어보자.
	ArrayList<vertex> link_vertex = new ArrayList();
	HashMap<vertex, Integer> vertex_way = new HashMap();
	
	// 부모와 자식도 따로 저장해야겠다.
	vertex parent = new vertex();
	vertex child = new vertex();
	
	public void setWay(vertex _v, int _way) {
		link_vertex.add(_v);
		vertex_way.put(_v, _way);
	}
}
