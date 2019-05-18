package alhw02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class 3way_merge {
	long start = System.currentTimeMillis();
	private static final String FILE = "data02.txt";
	
	public static void mergeSort(int arr[], int p, int r) {
		
		if ((r-p) > 1) {
			int q1 = p + (r-p)/3;
			int q2 = p + ((r-p)*2/3);
			
			mergeSort(arr, p, q1);

			mergeSort(arr, q1+1, q2);
			
			mergeSort(arr, q2+1, r);

			merge(arr,p,q1,q2,r);
		}
	}
	
	public static void merge(int arr[], int p, int q1, int q2, int r) {
		
		int count = p;
		//start p, start q;
		int s_p = p;
		int s_q1 = ++q1;
		int s_q2 = ++q2;
		//temp_array
		int[] t_arr = new int[arr.length];
		
		while(p < s_q1 && q1 < s_q2 && q2 < r+1) {
			if (arr[p] < arr[q1] && arr[p] < arr[q2]) {
				t_arr[count++] = arr[p++];
			}
			else if (arr[q1] < arr[q2] && arr[q1] < arr[p]) {
				t_arr[count++] = arr[q1++];
			}
			else
			{
				t_arr[count++] = arr[q2++];
			}
		}
		
		//q1과 q2끼리만 비교해야함
		
			while(q1 < s_q2 && q2 < r+1) {
				if (arr[q1] < arr[q2]) {
					t_arr[count++] = arr[q1++];
				}
				else {
					t_arr[count++] = arr[q2++];
				}
			}
		
		
		//p와 q1끼리 비교

			while(p < s_q1 && q1<s_q2) {
				if (arr[p] < arr[q1]) {
					t_arr[count++] = arr[p++];
				}
				else {
					t_arr[count++] = arr[q1++];
				}
			}
		
		//p와 q2끼리 비교


			while(p < s_q1 && q2 < r+1) {
				if (arr[p] < arr[q2]) {
					t_arr[count++] = arr[p++];
				}
				else {
					t_arr[count++] = arr[q2++];
				}
			}
		

		while(p < s_q1) {
			t_arr[count++] = arr[p++];
		}

		while(q1 < s_q2) {
			t_arr[count++] = arr[q1++];
		}
		
		while(q2 < r+1) {
			t_arr[count++] = arr[q2++];
		}
		
		for (int num = s_p; num < r+1;num++) {
			arr[num] = t_arr[num];
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		
		String[] S_array;
		
		try {
		FileReader fr = new FileReader(FILE);
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw = new FileWriter("hw_00_201300995_3way_merge.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		
		S_array = br.readLine().split(",");
		int[] I_array = new int[S_array.length];
		for (int i=0;i<S_array.length;i++) {
			I_array[i] = Integer.valueOf(S_array[i]);
		}
		
		/// 정렬하세요!
		mergeSort(I_array, 0, I_array.length-1);
		
		for (int i=0;i<I_array.length;i++) {
		//System.out.print(I_array[i] + " ");
			bw.write(String.valueOf(I_array[i]) + ",");
			if (i == I_array.length -1) {
				bw.write(String.valueOf(I_array[i]));
			}
		}
		
		br.close();
		bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("실행 시간 : " + (end - start)/1000.0+"초");
	}
}
