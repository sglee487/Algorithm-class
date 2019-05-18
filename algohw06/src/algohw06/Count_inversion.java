package algohw06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// 1번째 과제에서 했던 mergesort를 가져와서 이용한다.

// 201300995
public class Count_inversion {
	private static final String FILE = "data07_inversion.txt";
	
	public static int count_inversion(int arr[], int p, int r) {
		int inv_count = 0;
		if (p<r) {
			
			int q = (p+r)/2;
			//일단 맨 왼쪽을 놓고 계속 반으로 쪼갬.
			inv_count += count_inversion(arr, p, q);
			//다 쪼개지면 이제 그 다음꺼.
			inv_count += count_inversion(arr, q+1, r);
			//다 쪼갰으면 이제 정렬하며 합칠차례.
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
		
		// 왼쪽 index는 가만히 두고, 오른쪽 index를 1씩 올리면서 검사.
		// 오른쪽이 모두 크면, count 증가 없이 왼쪽 index를 1 올리고, 오른쪽 index는 처음부터 다시 검사.
		// 만약 왼쪽 index에 들어있는 값이 오른쪽 index에 들어있는 값보다 크면, 왼쪽에 남아있는 원소를 고려해서 count에 더함.
		
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
		System.out.print("inversion 순서쌍 : ");
		/// 정렬하세요!
		inv_count = count_inversion(I_array, 0, I_array.length-1);
		System.out.println("");
		System.out.println("총 inversion 갯수: " + inv_count);
		
		
		br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
