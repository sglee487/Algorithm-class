package algohw14;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

// 201300995
public class Ford_Fulkerson_Algorithm {
	final static int INF  = 987654231;
	static int V;
	
	// �����̵忡 �ִ°� �״�� �� �Ŵϱ� 6�� ������ �ɷ� ����.
	final static int MAX_V = 6;
	static int[][] capacity = new int[MAX_V][MAX_V];
	static int[][] flow = new int[MAX_V][MAX_V];
	
	public static void main(String[] args) {
		
		// ...
		// V��<Vertex ��> �Է¹���
		// capacity[][] �Է¹ްų� Ȥ�� �̸� �Էµ��ִ� ����
		// networkFlow �Լ� ȣ��
		// ...
		
		// �����̵� � �����ִ� �͵�� �Ȱ��� ������.
		// �׷���� Vector ���� 6��, capacity�� 6 by 6 ���.
		// MAX_V�� �̹� ���������ϱ� V���� �Է¹����� �ɵ�.
		V = 6;
		// capacity �Է�. �����̵��� �Ȱ��� �Ҳ��ϱ� �̸� �Է�����.
		final int S = 0; final int A = 1; final int B = 2; final int C = 3; final int D = 4; final int T = 5; 
		
		capacity[0][0] = 0; capacity[0][1] = 12; capacity[0][2] = 0; capacity[0][3] = 13; capacity[0][4] = 0; capacity[0][5] = 0;
		capacity[1][0] = 0; capacity[1][1] = 0; capacity[1][2] = 10; capacity[1][3] = 0; capacity[1][4] = 0; capacity[1][5] = 0; 
		capacity[2][0] = 0; capacity[2][1] = 0; capacity[2][2] = 0; capacity[2][3] = 13; capacity[2][4] = 3; capacity[2][5] = 15;
		capacity[3][0] = 0; capacity[3][1] = 0; capacity[3][2] = 7; capacity[3][3] = 0; capacity[3][4] = 15; capacity[3][5] = 0; 
		capacity[4][0] = 0; capacity[4][1] = 0; capacity[4][2] = 0; capacity[4][3] = 0; capacity[4][4] = 0; capacity[4][5] = 17;
		capacity[5][0] = 0; capacity[5][1] = 0; capacity[5][2] = 0; capacity[5][3] = 0; capacity[5][4] = 0; capacity[5][5] = 0;
		
		/*
		capacity[S][S] = 0; capacity[S][A] = 12; capacity[S][B] = 0; capacity[S][C] = 3; capacity[S][D] = 0; capacity[S][T] = 0;
		capacity[A][S] = 0; capacity[A][A] = 0; capacity[A][B] = 10; capacity[A][C] = 0; capacity[A][D] = 0; capacity[A][T] = 0; 
		capacity[B][S] = 0; capacity[B][A] = 0; capacity[B][B] = 0; capacity[B][C] = 13; capacity[B][D] = 3; capacity[B][T] = 15;
		capacity[C][S] = 0; capacity[C][A] = 11; capacity[C][B] = 0; capacity[C][C] = 0; capacity[C][D] = 5; capacity[C][T] = 0; 
		capacity[D][S] = 0; capacity[D][A] = 0; capacity[D][B] = 0; capacity[D][C] = 0; capacity[D][D] = 0; capacity[D][T] = 17;
		capacity[T][S] = 0; capacity[T][A] = 0; capacity[T][B] = 0; capacity[T][C] = 0; capacity[T][D] = 0; capacity[T][T] = 0;
		*/
		//printMatrix(capacity);
		
		//System.out.println(networkFlow(0,5));
		networkFlow(0,V-1);
		//printMatrix(capacity);
		
	}
	
	// Source�� �������. Sink�� ��������.
	public static int networkFlow(int source, int sink) {
		//memset(flow, 0, flow.length);
		int totalFlow = 0;
		while (true) {
			// vector<int> parent(MAX_V, -1)
			// c++�� �̷��� �ϸ� MAX_V��ŭ�� �迭 ������, ��� -1�� �ʱ�ȭ �ϴµ�
			// �ڹٴ� �迭 ũ�Ⱑ -1, ���� �ʰ��ϸ� -1�� �Ǵ°���.
			// �°� �ٲ�����..
			//Vector<Integer> parent = new Vector<Integer>(MAX_V, -1);
			Vector<Integer> parent = new Vector<Integer>(MAX_V);
			for (int i=0;i<MAX_V;i++)
				parent.add(-1);
			Queue<Integer> q = new PriorityQueue<Integer>();
			
			//parent[source] = source;
			parent.set(source, source);
			//q.push(source);
			q.add(source);
			while(!q.isEmpty() && parent.elementAt(sink) == -1) {
				int here = q.poll();
				//q.pop();
				for (int there = 0; there < V; ++there) {
					if (capacity[here][there] - flow[here][there] > 0 && parent.elementAt(there) == -1) {
						q.add(there);
						//parent[there] = here;
						parent.set(there, here);
					}
				}
			}
			if (parent.elementAt(sink) == -1) break;
			
			int amount = INF;
			for (int p = sink; p != source; p = parent.elementAt(p))
				amount = min(capacity[parent.elementAt(p)][p] - flow[parent.elementAt(p)][p], amount);
			
			// ����� ���� ��.
			Stack<Integer> stack = new Stack<Integer>();
			stack.add(V-1);
			
			for (int p = sink; p != source; p = parent.elementAt(p)) {
				//System.out.print(p+"-");
				//System.out.print(parent.elementAt(p)+"-");
				stack.add(parent.elementAt(p));
				flow[parent.elementAt(p)][p] += amount;
				flow[p][parent.elementAt(p)] -= amount;
			}
			

			totalFlow += amount;
			
			//System.out.println(totalFlow);
			//printMatrix(flow);	
			while(true) {
				System.out.print(stack.pop());
				if (stack.empty()) {
					System.out.print(" / ");
					break;
				} else {
					System.out.print("-");
				}
			}
						

			System.out.println("�ִ� �뷮 : " + amount);

		}
		//System.out.println();
		System.out.println("���� ��Ʈ��ũ ��ü�� �ִ� �뷮 : " + totalFlow);
		return totalFlow;
	}
	
	public static void memset(int[][] flow, int start, int end) {
		/*
		for (int i=0;i < end;i++) {
			for (int j=0;j<end;j++) {
				flow[i][j] = capacity[i][j];
			}
		}
		*/
		for (int j=0;j<end;j++) {
			flow[j][j] = 0;
		}
	}
	
	public static int min(int a, int b) {
		return a>b ? b : a;
	}
	
	public static void printMatrix(int[][] M) {
		for(int[] a : M) {
			for (int v : a) {
				System.out.print(v + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
