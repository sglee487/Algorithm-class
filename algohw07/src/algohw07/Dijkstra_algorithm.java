package algohw07;

import java.util.ArrayList;

public class Dijkstra_algorithm {

	public static void main(String[] args) {
	
		
		// 길이를 저장하는 w
		// A,B,C,D,E순서
		int[][] w = {
				{0,10,3,999,999},
				{999,0,1,2,999},
				{999,4,0,8,2},
				{999,999,999,0,7},
				{999,999,999,9,0}
		};
		
		// 이제 d를 정의할건데, 0은 A, 1은 B, 2는 C... 로 상징적으로 생각하자.
		int[] d = new int[w.length];
		// 출력표시 이유로 old_d를 만들었다.
		int[] old_d = new int[w.length];
		// S, Q도 만들어야 한다.
		// S, Q는 순서가 있어야 하므로 ArrayList로 만들고,
		// ArrayList로 node라는 클래스를 따로 만들어 담아내는게 맞는것같다.
		ArrayList<Node> S = new ArrayList<Node>();
		ArrayList<Node> Q = new ArrayList<Node>();
		
		// 모든 노드 만들기.
		Node A = new Node("A",0);
		Node B = new Node("B",999);
		Node C = new Node("C",999);
		Node D = new Node("D",999);
		Node E = new Node("E",999);
		int u=0,v=0;
		// Q 자리바꾸기용
		Node tem_Node;
		
		System.out.println("dijkstra's algorithm으로 계산한 결과는 다음과 같습니다.\n");
		// 여기부터 시작
		d[0]=0;
		for (int i=1;i<w.length;i++) {
			d[i] = 999;
			old_d[i] = 999;
		}
		// S는 공집합.
		Q.add(A);
		Q.add(B);
		Q.add(C);
		Q.add(D);
		Q.add(E);
		
		while(!Q.isEmpty()) {
		//while(v < w.length) {
			// Q의 value중 제일 작은 값을 S에 그대로 넣어야 함.
			// Extract-Min(Q)는 
			
			//public static int Extract_Min
			//(TreeMap<Integer,Integer> Q, int u, int v,int[][] w, int[] d)
			S.add(Extract_Min(Q));
			System.out.println("============================================");
			System.out.println("S["+u+"] : d["+S.get(u).getName()+"] = " + S.get(u).getWay());
			System.out.println("--------------------------------------------");
			Q.remove(0);
			
			// d의 인데스 자리수 순으로 A,B,C,D,E이다.
			// 각 Q들을 일일히 검사해서 넣는 방법 밖에 없는것같다.
			// 기존값들을 가지고잇는 old_d와, 새로운 값을 가지는 그냥 d를 만들어서,
			// 출력을 만드는게 좋을것 같다. 코드가 좀 꼬여있는듯.
			for (int tem_v=v;tem_v < w.length;tem_v++) {
				//System.out.print("Q["+i+"] : d[" + Q.get(i).getName()+"] = " + Q.get(i).getWay());

				if (d[tem_v] > d[u] + w[u][tem_v]) {
					d[tem_v] = d[u] + w[u][tem_v];
					// -> 변화된것 프린트.
					//System.out.print("->" + d[tem_v]);
					//System.out.println();
				}
				// 위에 것이 끝난다음엔, 열이 고정, 행이 0~4까지 검사.
				if (d[u] > d[tem_v] + w[tem_v][u]) {
					d[u] = d[tem_v] + w[tem_v][u];
					// -> 변화된것 프린트.
					//System.out.print("->" + d[u]);
					//System.out.println();
				}
				//System.out.println();
				
				}
			// 위에서 d 계산결과가 끝나면, 이것을 Q에 넣어야 함.
			
			for (int i=0;i<Q.size();i++) {
				System.out.print("Q["+i+"] : d[" + Q.get(i).getName()+"] = " + Q.get(i).getWay());
				// 만약 Q.get(i).getName() 이 "A"일 경우 d의 몇번 인덱스 값을 꺼내온다.. 는 식으로 하자.
				// old_d와 d가 다른경우도 확인해서 다를경우 둘 다 출력해야함.
				// old_d는 무조건 출력, d는 다를경우 추가해서 출력.
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
			// old_d를 최신화
			for (int i=0; i <5; i++) {
				old_d[i]=d[i];
			}
			
			// 이제 Q를 Q 안의 way에 따라 정렬해야 한다.
			// 그냥 이중중첩으로 정리하자.
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
			// -> 변화된것 프린트.
			System.out.print("->");
			//System.out.println();
		}
		*/
		// 위처럼 한 결과, 최단거리 D는 A->B->D 로 9가 나와야 하는데 계속 11이 나옴.
		// 이는 w행렬에서 2행4열을 고려 안하기때문에 생김.				
		/*
		for (int tem_u = u; tem_u < w.length; tem_u++) {
		if (w[tem_u][tem_v] == 0 || d[tem_v] == 0) {continue;}
			if (d[tem_v] > d[tem_u] + w[tem_u][tem_v]) {
				d[tem_v] = d[tem_u] + w[tem_u][tem_v];
				// -> 변화된것 프린트.
				System.out.print("->");
				//System.out.println();
			}
		}
		*/
		// 위에걸로 하면 제대로 작동되지만, tem_u로 미리 다 계산이 끝나서 출력을 제대로 못함.
		// v가 이미 넘어갔더라도, w는 앞의 행을 고려해줘야 한다.
		// w에서 행이 시작점이고, 열이 도착점인데, 시작점은 d[u]가 하므로
		// 시작점 d[u], 도착점은 모든 행에 대해 고정된 열로 고려해서 계산해보자.
		
	}

	
	// 추출하면, 추출된 값은 Q의 맨 앞에 오고, 위에서 검사할때 for 1부터시작, for 2부터시작...
	// 하는 방식으로 노드를 제외하자
	// 이 함수가 끝난 뒤 Q[u] 이런식으로.
	// w를 적극 활용해야 할듯하다.
	
	// 얘의 역할은 Q가 가지고 있는 노드 중에 제일 작은 way를 가지고 있는 노드를 불러내는것.
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
