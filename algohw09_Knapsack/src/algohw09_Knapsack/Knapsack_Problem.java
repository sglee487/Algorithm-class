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
			
			/*
			// ����� ��峪 �׽�Ʈ��
			for (int i=0;i < item_row.size();i++) {
				for (int j=0; j < item_row.get(i).length; j++) {
					System.out.print(item_row.get(i)[j] + ",");
							
				}
				System.out.println();
			}
			*/
			
			// ���� ���� �� ������� result�� ũ�⸦ ��������.
			result = new int[item_row.size()][weight+1];
			//item_row.get(item)[0]�� Item, [1]�� value, [2]�� weight
			for (int item_inc = 0; item_inc < item_row.size(); item_inc++) {
				for (int w_inc=0;w_inc<weight+1;w_inc++) {
				System.out.print(OPT(item_inc,w_inc,item_row) + "\t");
				result[item_inc][w_inc] = OPT(item_inc,w_inc,item_row);
				}
				System.out.println();
			}
			
			// result ��� �׽�Ʈ
			// ���⼭ ���� ū���� ������, ū ���϶��� ��� ���� ������.
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
			
			// row�� ������ ����, col�� ���� ũ��.
			
			ArrayList<Integer> items = new ArrayList<Integer>();
			ArrayList<Integer> real_items = new ArrayList<Integer>();
			// ó�� System.out.println(OPT(row,col,item_row,items)); �� ������,
			// �������� ������ �������� ������ ��.
			// �׷� �� �������� weight���� �� �ڿ� �ٽ� ����ϸ�??
			// weight�� 0�� ���ö����� ������ �ɵ�.
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
			//������ ������� ������ ������.
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
				// �������� ����
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
