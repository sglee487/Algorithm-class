package algohw09;

import java.util.ArrayList;
import java.util.HashMap;

public class vertex {
	// �ڱ�� ����Ǿ��ִ� vertex�� �ް�, �� vertex �� way ���̸� ������ �� �־�� ��.
	// arraylist�� vertex�� �����ϰ�, hashmap���� �� vertex�� way�� �����ϵ��� ������.
	ArrayList<vertex> link_vertex = new ArrayList();
	HashMap<vertex, Integer> vertex_way = new HashMap();
	
	// �θ�� �ڽĵ� ���� �����ؾ߰ڴ�.
	vertex parent = new vertex();
	vertex child = new vertex();
	
	public void setWay(vertex _v, int _way) {
		link_vertex.add(_v);
		vertex_way.put(_v, _way);
	}
}
