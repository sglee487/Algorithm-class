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
		// �������� �ҷ��� �迭�� ��������.
		// ��ķ� �����µ�, col�� ������ int[3]����, row�� ArrayList��.
		int[] item_col = new int[3];
		ArrayList<int[]> item_row = new ArrayList<int[]>();
		
		// ����ڿ��� ����ũ�� �Է¹ޱ�
		Scanner keyboard = new Scanner(System.in);
		int weight;
		
		// ���������� ǥ�� ���� �����.
		int[][] result;
		//result = {{1,2,3},{2,3,4}};
		//int[] temp = {1,2,3};
		//item_row.add(temp);
		try {
			FileReader fr = new FileReader(FILE);
			BufferedReader br = new BufferedReader(fr);
			
			// item ���� ���߱�����, item_row�� ù��°�� 1 index�� ���Բ� �ϱ� ����
			// item_row�� 0index���� ��� 0�� ���� �ִ´�.
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
			
			System.out.print("������ ����� �Է��ϼ���(0~50) : ");
			weight = Integer.valueOf(keyboard.nextLine());
			if (weight < 0 || weight > 50) {
				System.out.print("�ٽ� �Է��ϼ���(0~50) : ");
				weight = Integer.valueOf(keyboard.nextLine());
			}
			
			// ����� ��峪 �׽�Ʈ��
			for (int i=0;i < item_row.size();i++) {
				for (int j=0; j < item_row.get(i).length; j++) {
					System.out.print(item_row.get(i)[j] + ",");
							
				}
				System.out.println();
			}
			
			//int[] items = new int[item_row.size()];
			ArrayList items = new ArrayList<Integer>();
			// result[][] �� ũ��
			result = new int[item_row.size()][weight+1];
			
			// �ϴ� 1���� 0���� ��´�.
			for (int i=0;i < weight+1;i++ ) {
				result[0][i] = 0;
			}
			/*
			//item_row.get(item)[0]�� Item, [1]�� value, [2]�� weight
			/*
			//public static int OPT(int i, int w, int wi,int vi) 
			for (int item = 1; item < item_row.size();item++) {
				for (int inc_w=0;inc_w < weight+1;inc_w++) {
					if (item_row.get(item)[2] > inc_w) {
						result[item][inc_w] = OPT(item-1,inc_w,item_row.get(item)[2],item_row.get(item)[1]);
					} else {
						result[item][inc_w] = max(OPT(item-1,inc_w,item_row.get(item)[2],item_row.get(item)[1]),
								item_row.get(item)[1] + OPT(item-1, inc_w - item_row.get(item)[2],item_row.get(item)[2],item_row.get(item)[1]));
					}
				}
			}
*/ //public static int OPT(int i, int w, int wi,int vi)
			/*
			for (int item = 1; item < item_row.size();item++) {
				for (int item_r = 1; item_r <= item; item_r++) {
					for (int inc_w=0;inc_w < weight+1;inc_w++) {
						if (item_row.get(item_r)[2] > inc_w) {
							result[item][inc_w] = OPT(item-1,inc_w,item_row.get(item_r)[2],item_row.get(item_r)[1]);
						} else {
							result[item][inc_w] = max(OPT(item-1,inc_w,item_row.get(item_r)[2],item_row.get(item_r)[1]),
									item_row.get(item_r)[1] + OPT(item-1, inc_w - item_row.get(item_r)[2],item_row.get(item_r)[2],item_row.get(item_r)[1]));
						}
					}
				}
				
			}
			*/
			for (int i=0;i<weight+1;i++) {
				System.out.print(OPT(,,,));
			}
			/*
			for (int i=0;i < item_row.size();i++) {
				for (int j=0; j < weight+1; j++) {
					System.out.print(result[i][j] + ",");
							
				}
				System.out.println();
			}
			*/
	
			
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static int OPT(int i, int w, int wi,int vi) {
		if (i == 0) {
			return 0;
		} else if (wi > w) {
			return OPT(i-1,w,wi,vi);
		} else {
			return max(OPT(i-1,w,wi,vi),
					vi + OPT(i-1,w-wi,wi,vi));
		}
	}
	/*
	public static int OPT(int i, int w, int v, int W) {
		if (i==0) {
			return 0;
		} else if(w > W) {
			return OPT(i-1,w,v,W);
		} else {
			return max(OPT(i-1,w,v,W),v+OPT(i-1,w,v,W-w));
		}
	}
	*/

	public static int max(int a, int b) {
		if (a>b) return a;
		return b;
	}
	
	public static boolean bigger(int a, int b) {
		if (a>b) return true;
		return false;
	}

	public static boolean doesContain(int tmp,ArrayList<Integer> array) {
		for (int i=0;i<array.size();i++) {
			if (array.get(i)==tmp) return true;
		}
		return false;
	}
}
