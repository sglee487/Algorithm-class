package alhw02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class InsertArray {
	long start = System.currentTimeMillis();
	private static final String FILE = "data02.txt";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		
		String[] S_array;
		int key;
		
		try {
		FileReader fr = new FileReader(FILE);
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw = new FileWriter("hw_00_201300995_insertion.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		
		S_array = br.readLine().split(",");
		int[] I_array = new int[S_array.length];
		for (int i=0;i<S_array.length;i++) {
			I_array[i] = Integer.valueOf(S_array[i]);
		}
		
		for (int i=1; i < I_array.length;i++) {
			key = I_array[i];
			
			for (int j=i; j > 0; j--)
				if (j==0) {
					I_array[j] = key;
					break;
				}
				else if (I_array[j-1] > key) {
					I_array[j] = I_array[j-1];
				}
				else {
					I_array[j] = key;
					break;
				}
		}
		for (int i=0;i<I_array.length;i++) {
		//System.out.print(I_array[i] + " ");
			bw.write(String.valueOf(I_array[i]) + ",");
			if (i == I_array.length -1)
				bw.write(String.valueOf(I_array[i]));
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
