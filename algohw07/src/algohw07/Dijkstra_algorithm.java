package algohw07;

import java.util.ArrayList;

public class Dijkstra_algorithm {

	public static void main(String[] args) {
	
		
		// ���̸� �����ϴ� w
		// A,B,C,D,E����
		int[][] w = {
				{0,10,3,999,999},
				{999,0,1,2,999},
				{999,4,0,8,2},
				{999,999,999,0,7},
				{999,999,999,9,0}
		};
		
		// ���� d�� �����Ұǵ�, 0�� A, 1�� B, 2�� C... �� ��¡������ ��������.
		int[] d = new int[w.length];
		// ���ǥ�� ������ old_d�� �������.
		int[] old_d = new int[w.length];
		// S, Q�� ������ �Ѵ�.
		// S, Q�� ������ �־�� �ϹǷ� ArrayList�� �����,
		// ArrayList�� node��� Ŭ������ ���� ����� ��Ƴ��°� �´°Ͱ���.
		ArrayList<Node> S = new ArrayList<Node>();
		ArrayList<Node> Q = new ArrayList<Node>();
		
		// ��� ��� �����.
		Node A = new Node("A",0);
		Node B = new Node("B",999);
		Node C = new Node("C",999);
		Node D = new Node("D",999);
		Node E = new Node("E",999);
		int u=0,v=0;
		// Q �ڸ��ٲٱ��
		Node tem_Node;
		
		System.out.println("dijkstra's algorithm���� ����� ����� ������ �����ϴ�.\n");
		// ������� ����
		d[0]=0;
		for (int i=1;i<w.length;i++) {
			d[i] = 999;
			old_d[i] = 999;
		}
		// S�� ������.
		Q.add(A);
		Q.add(B);
		Q.add(C);
		Q.add(D);
		Q.add(E);
		
		while(!Q.isEmpty()) {
		//while(v < w.length) {
			// Q�� value�� ���� ���� ���� S�� �״�� �־�� ��.
			// Extract-Min(Q)�� 
			
			//public static int Extract_Min
			//(TreeMap<Integer,Integer> Q, int u, int v,int[][] w, int[] d)
			S.add(Extract_Min(Q));
			System.out.println("============================================");
			System.out.println("S["+u+"] : d["+S.get(u).getName()+"] = " + S.get(u).getWay());
			System.out.println("--------------------------------------------");
			Q.remove(0);
			
			// d�� �ε��� �ڸ��� ������ A,B,C,D,E�̴�.
			// �� Q���� ������ �˻��ؼ� �ִ� ��� �ۿ� ���°Ͱ���.
			// ���������� �������մ� old_d��, ���ο� ���� ������ �׳� d�� ����,
			// ����� ����°� ������ ����. �ڵ尡 �� �����ִµ�.
			for (int tem_v=v;tem_v < w.length;tem_v++) {
				//System.out.print("Q["+i+"] : d[" + Q.get(i).getName()+"] = " + Q.get(i).getWay());

				if (d[tem_v] > d[u] + w[u][tem_v]) {
					d[tem_v] = d[u] + w[u][tem_v];
					// -> ��ȭ�Ȱ� ����Ʈ.
					//System.out.print("->" + d[tem_v]);
					//System.out.println();
				}
				// ���� ���� ����������, ���� ����, ���� 0~4���� �˻�.
				if (d[u] > d[tem_v] + w[tem_v][u]) {
					d[u] = d[tem_v] + w[tem_v][u];
					// -> ��ȭ�Ȱ� ����Ʈ.
					//System.out.print("->" + d[u]);
					//System.out.println();
				}
				//System.out.println();
				
				}
			// ������ d ������� ������, �̰��� Q�� �־�� ��.
			
			for (int i=0;i<Q.size();i++) {
				System.out.print("Q["+i+"] : d[" + Q.get(i).getName()+"] = " + Q.get(i).getWay());
				// ���� Q.get(i).getName() �� "A"�� ��� d�� ��� �ε��� ���� �����´�.. �� ������ ����.
				// old_d�� d�� �ٸ���쵵 Ȯ���ؼ� �ٸ���� �� �� ����ؾ���.
				// old_d�� ������ ���, d�� �ٸ���� �߰��ؼ� ���.
				switch (Q.get(i).getName()) {
				case "A": //System.out.print(old_d[0]);
				if (old_d[0] != d[0]) {
					System.out.print(" -> d["+Q.get(i).getName()+"] = "+d[0]);
					Q.get(i).setWay(d[0]);
				}
					break;
				case "B": //System.out.print(old_d[1]);
				if (old_d[1] != d[1]) {
					System.out.print(" -> d["+Q.get(i).getName()+"] = "+d[1]);
					Q.get(i).setWay(d[1]);
				}
					break;
				case "C": //System.out.print(old_d[2]);
				if (old_d[2] != d[2]) {
					System.out.print(" -> d["+Q.get(i).getName()+"] = "+d[2]);
					Q.get(i).setWay(d[2]);
				}
					break;
				case "D": //System.out.print(old_d[3]);
				if (old_d[3] != d[3]) {
					System.out.print(" -> d["+Q.get(i).getName()+"] = "+d[3]);
					Q.get(i).setWay(d[3]);
				}
					break;
				case "E": //System.out.print(old_d[4]);
				if (old_d[4] != d[4]) {
					System.out.print(" -> d["+Q.get(i).getName()+"] = "+d[4]);
					Q.get(i).setWay(d[4]);
				}
					break;
				}
				System.out.println();
			}
			// old_d�� �ֽ�ȭ
			for (int i=0; i <5; i++) {
				old_d[i]=d[i];
			}
			
			// ���� Q�� Q ���� way�� ���� �����ؾ� �Ѵ�.
			// �׳� ������ø���� ��������.
			for (int i=0;i<Q.size();i++) {
				for (int j=i;j<Q.size();j++) {
					if (Q.get(j).getWay()<Q.get(i).getWay()) {
						tem_Node = Q.get(j);
						Q.set(j, Q.get(i));
						Q.set(i, tem_Node);
					}
				}
			}
			
			System.out.println();
			v++;
			u++;
			
		}
		
		/*
		for (int i=u; i <5; i++) {
			System.out.print(i + ": " + d[i] + ". ");
		}
		System.out.println();
		*/
		/*
		if (d[tem_v] > d[u] + w[u][tem_v]) {
			d[tem_v] = d[u] + w[u][tem_v];
			// -> ��ȭ�Ȱ� ����Ʈ.
			System.out.print("->");
			//System.out.println();
		}
		*/
		// ��ó�� �� ���, �ִܰŸ� D�� A->B->D �� 9�� ���;� �ϴµ� ��� 11�� ����.
		// �̴� w��Ŀ��� 2��4���� ��� ���ϱ⶧���� ����.				
		/*
		for (int tem_u = u; tem_u < w.length; tem_u++) {
		if (w[tem_u][tem_v] == 0 || d[tem_v] == 0) {continue;}
			if (d[tem_v] > d[tem_u] + w[tem_u][tem_v]) {
				d[tem_v] = d[tem_u] + w[tem_u][tem_v];
				// -> ��ȭ�Ȱ� ����Ʈ.
				System.out.print("->");
				//System.out.println();
			}
		}
		*/
		// �����ɷ� �ϸ� ����� �۵�������, tem_u�� �̸� �� ����� ������ ����� ����� ����.
		// v�� �̹� �Ѿ����, w�� ���� ���� �������� �Ѵ�.
		// w���� ���� �������̰�, ���� �������ε�, �������� d[u]�� �ϹǷ�
		// ������ d[u], �������� ��� �࿡ ���� ������ ���� ����ؼ� ����غ���.
		
	}

	
	// �����ϸ�, ����� ���� Q�� �� �տ� ����, ������ �˻��Ҷ� for 1���ͽ���, for 2���ͽ���...
	// �ϴ� ������� ��带 ��������
	// �� �Լ��� ���� �� Q[u] �̷�������.
	// w�� ���� Ȱ���ؾ� �ҵ��ϴ�.
	
	// ���� ������ Q�� ������ �ִ� ��� �߿� ���� ���� way�� ������ �ִ� ��带 �ҷ����°�.
	public static Node Extract_Min(ArrayList<Node> Q) {		
		int tem_num = 0;
		for (int i=0; i<Q.size();i++) {
			if (Q.get(i).getWay() < Q.get(tem_num).getWay()) {
				tem_num = i;
			}
		}	
		return Q.get(tem_num);
	}
	
}
