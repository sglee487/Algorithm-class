package algohw06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// 1��° �������� �ߴ� mergesort�� �����ͼ� �̿��Ѵ�.

// 201300995
public class Count_inversion {
	private static final String FILE = "data07_inversion.txt";
	
	public static int count_inversion(int arr[], int p, int r) {
		int inv_count = 0;
		if (p<r) {
			
			int q = (p+r)/2;
			//�ϴ� �� ������ ���� ��� ������ �ɰ�.
			inv_count += count_inversion(arr, p, q);
			//�� �ɰ����� ���� �� ������.
			inv_count += count_inversion(arr, q+1, r);
			//�� �ɰ����� ���� �����ϸ� ��ĥ����.
			inv_count += count(arr,p,q,r);
			//System.out.println(inv_count);
		}
		return inv_count;
	}
	
	public static int count(int arr[], int p, int q, int r) {
		
		int inv_count = 0;
		//int count = p;
		int s_q = ++q;
		//temp_array
		//int[] t_arr = new int[arr.length];
		
		// ���� index�� ������ �ΰ�, ������ index�� 1�� �ø��鼭 �˻�.
		// �������� ��� ũ��, count ���� ���� ���� index�� 1 �ø���, ������ index�� ó������ �ٽ� �˻�.
		// ���� ���� index�� ����ִ� ���� ������ index�� ����ִ� ������ ũ��, ���ʿ� �����ִ� ���Ҹ� ����ؼ� count�� ����.
		
		for (int i = p; i < s_q;i++) {
			for (int j = s_q; j < r+1;j++) {
				if (arr[i] > arr[j]) {
					System.out.print("(" + arr[i] + "," + arr[j] + ") ");
					inv_count++;
					
				}
			}
		}
		//System.out.println(inv_count);
		return inv_count;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[] S_array;
		int inv_count = 0;
		
		try {
		FileReader fr = new FileReader(FILE);
		BufferedReader br = new BufferedReader(fr);

		
		S_array = br.readLine().split(",");
		int[] I_array = new int[S_array.length];
		for (int i=0;i<S_array.length;i++) {
			I_array[i] = Integer.valueOf(S_array[i]);
		}
		System.out.print("inversion ������ : ");
		/// �����ϼ���!
		inv_count = count_inversion(I_array, 0, I_array.length-1);
		System.out.println("");
		System.out.println("�� inversion ����: " + inv_count);
		
		
		br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
