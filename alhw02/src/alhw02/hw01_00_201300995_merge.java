package alhw02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class hw01_00_201300995_merge {
	long start = System.currentTimeMillis();
	private static final String FILE = "data02.txt";
	
	public static void mergeSort(int arr[], int p, int r) {
		
		if (p<r) {
			int q = (p+r)/2;
			//일단 맨 왼쪽을 놓고 계속 반으로 쪼갬.
			mergeSort(arr, p, q);
			//다 쪼개지면 이제 그 다음꺼.
			mergeSort(arr, q+1, r);
			//다 쪼갰으면 이제 정렬하며 합칠차례.
			merge(arr,p,q,r);
		}
	}
	
	public static void merge(int arr[], int p, int q, int r) {
		
		int count = p;
		//start p, start q;
		int s_p = p;
		int s_q = ++q;
		//temp_array
		int[] t_arr = new int[arr.length];
		
		while(p < s_q && q < r+1) {
			if (arr[p] < arr[q]) {
				t_arr[count++] = arr[p++];
			}
			else {
				t_arr[count++] = arr[q++];
			}
		}
		
		while(p < s_q) {
			t_arr[count++] = arr[p++];
		}
		
		while(q < r+1) {
			t_arr[count++] = arr[q++];
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
		FileWriter fw = new FileWriter("hw_00_201300995_merge.txt");
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
		try {
		Thread.sleep(1000);
		} catch(InterruptedException e) {
			
		}
		long end = System.currentTimeMillis();
		System.out.println("실행 시간 : " + (end - start)/1000.0+"초");
	}
}
