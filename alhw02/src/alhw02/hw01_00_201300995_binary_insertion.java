package alhw02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class hw01_00_201300995_binary_insertion {
	long start = System.currentTimeMillis();
	private static final String FILE = "data02.txt";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		
		String[] S_array;
		
		try {
		FileReader fr = new FileReader(FILE);
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw = new FileWriter("hw_00_201300995_binary_insertion.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		
		S_array = br.readLine().split(",");
		int[] I_array = new int[S_array.length];
		for (int i=0;i<S_array.length;i++) {
			I_array[i] = Integer.valueOf(S_array[i]);
		}
		
		//구현부분
		//binary start, binary end
		int bs,be;
		int key;
		int mid;
		for (int i=1; i < I_array.length;i++) {
			key = I_array[i];
			
			bs=0;
			be=i;
			while (true) {
				mid = (be+bs)/2;
				if ((be-bs)==1) {
					//be와 bs가 1밖에 차이가 안나므로, bs의 앞에삽입해야되는지, 뒤에 삽입해야 되는지
					//if문으로 나눠서
					if (I_array[bs] > key) {
						for (int j=i; j > bs;j--) {
							I_array[j] = I_array[j-1];
						}
						I_array[bs] = key;
						break;	
					}
					else {
						for (int j=i; j > be;j--) {
							I_array[j] = I_array[j-1];
						}
						I_array[be] = key;
						break;	
					}
				}
				else if (I_array[mid] > key) {
					be = mid;
				}
				else {
					bs = mid;
				}
			}
				
		}
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
