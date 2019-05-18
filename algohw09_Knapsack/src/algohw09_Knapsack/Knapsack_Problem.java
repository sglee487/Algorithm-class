package algohw09_Knapsack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// 201300995
public class Knapsack_Problem {
	private static final String FILE = "data11.txt";

	
	public static void main(String[] args) {
				
		// 아이템을 불러와 배열에 저장하자.
		// 행렬로 나누는데, col은 고정된 int[3]으로, row는 ArrayList로.
		int[] item_col = new int[3];
		ArrayList<int[]> item_row = new ArrayList<int[]>();
		

		// 사용자에게 가방크기 입력받기
		Scanner keyboard = new Scanner(System.in);
		int weight;
		
		// 최종적으로 표에 나올 결과들.
		int[][] result;
		//result = {{1,2,3},{2,3,4}};
		//int[] temp = {1,2,3};
		//item_row.add(temp);
			
		try {
			FileReader fr = new FileReader(FILE);
			BufferedReader br = new BufferedReader(fr);
			
			// item 숫자 맞추기위해, item_row의 첫번째가 1 index에 오게끔 하기 위해
			// item_row의 0index에는 모두 0인 값을 넣는다.
			int[] zero = {0,0,0};
			item_row.add(zero);
			
			String line = "";
			String[] number_string;
			while((line = br.readLine()) != null) {
				number_string = line.split(",");
				for (int i=0; i<number_string.length;i++) {
					item_col[i] = Integer.valueOf(number_string[i]);
				}
				int[] temp = {item_col[0],item_col[1],item_col[2]};
				item_row.add((temp));
			}
			

			System.out.print("가방의 사이즈를 입력하세요(0~50) : ");
			weight = Integer.valueOf(keyboard.nextLine());
			if (weight < 0 || weight > 50) {
				System.out.print("다시 입력하세요(0~50) : ");
				weight = Integer.valueOf(keyboard.nextLine());
			}
			
			/*
			// 제대로 담겼나 테스트용
			for (int i=0;i < item_row.size();i++) {
				for (int j=0; j < item_row.get(i).length; j++) {
					System.out.print(item_row.get(i)[j] + ",");
							
				}
				System.out.println();
			}
			*/
			
			// 이제 파일 다 담았으니 result의 크기를 정해주자.
			result = new int[item_row.size()][weight+1];
			//item_row.get(item)[0]은 Item, [1]은 value, [2]는 weight
			for (int item_inc = 0; item_inc < item_row.size(); item_inc++) {
				for (int w_inc=0;w_inc<weight+1;w_inc++) {
				System.out.print(OPT(item_inc,w_inc,item_row) + "\t");
				result[item_inc][w_inc] = OPT(item_inc,w_inc,item_row);
				}
				System.out.println();
			}
			
			// result 든거 테스트
			// 여기서 제일 큰값을 꺼내고, 큰 값일때의 행과 열을 구하자.
			int max=0;
			int row=0; 
			int col = 0;
			for (int r=0;r < result.length;r++) {
				for (int c=0;c<result[0].length;c++) {
					//System.out.print(result[r][c] + "\t");
					if (result[r][c] > max) {
						max = result[r][c];
						row=r;
						col=c;
					}
				}
				//System.out.println();
			}
			System.out.println("max : " + max);
			
			// row은 아이템 갯수, col는 가방 크기.
			
			ArrayList<Integer> items = new ArrayList<Integer>();
			ArrayList<Integer> real_items = new ArrayList<Integer>();
			// 처음 System.out.println(OPT(row,col,item_row,items)); 를 돌리면,
			// 마지막에 나오는 아이템은 무조건 들어감.
			// 그럼 그 아이템의 weight값을 뺀 뒤에 다시 계산하면??
			// weight가 0이 나올때까지 돌리면 될듯.
			// 
			
			while (col>0) {
				//System.out.println(OPT(row,col,item_row,items) + " OPT");
				OPT(row,col,item_row,items);
				real_items.add(items.get(0));
				col -= item_row.get(items.get(0))[2];
			}
			/*
			for (int i=0;i < real_items.size();i++) {
				System.out.print(real_items.get(i) + " ");
			}
			*/
			//이제다 담겼으니 순서를 맞추자.
			//int[] sun_items = new int[real_items.size()];
			int sort_tmp = 0;
			for (int i=0;i < real_items.size();i++) {
				for (int j=i; j < real_items.size();j++) {
					if (real_items.get(i) > real_items.get(j)) {
						sort_tmp = real_items.get(i);
						real_items.set(i,real_items.get(j));
						real_items.set(j, sort_tmp);
					}
				}
			}
			
			System.out.print("item : ");
			for (int i=0;i < real_items.size();i++) {
				System.out.print(real_items.get(i) + " ");
			}
			
	
		} catch (IOException e) {
			
		}
	}
	
	
	public static int OPT(int i, int W, ArrayList<int[]> item_row) {
		if (i == 0) {
			return 0;
		} else if (item_row.get(i)[2] > W) {
			return OPT(i-1,W,item_row);
		} else {
			return max(OPT(i-1,W,item_row),
					item_row.get(i)[1] + OPT(i-1,W-item_row.get(i)[2],item_row));
		}
	}
	
	public static int OPT(int i, int W, ArrayList<int[]> item_row, ArrayList<Integer> items) {
		if (i == 0) {
			return 0;
		} else if (item_row.get(i)[2] > W) {
			return OPT(i-1,W,item_row,items);
		} else {
			int a = OPT(i-1,W,item_row,items);
			int b= item_row.get(i)[1] + OPT(i-1,W-item_row.get(i)[2],item_row,items);
			if (a > b) {
				//System.out.println(i);
				//System.out.print("not: " + i + " ");
				return a;
			} else {
				//System.out.println(i);
				// 마지막꺼 삭제
				//if (items.size() != 0) items.remove(items.size()-1);
				items.clear();
				//System.out.print("yes: " + i + " ");
				items.add(i);
				return b;
			}
		}
	}

	public static int max(int a, int b) {
		if (a>b) return a;
		return b;
	}
	
	

}
