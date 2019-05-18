package algohw03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Random;

public class hw03_00_201300995_quick {

	public static void main(String[] args) {
		
		//파일 읽기
		try {
			FileReader fr = new FileReader("data04.txt");
			BufferedReader br = new BufferedReader(fr);
			
			//그냥 sort와 random 해서 sort한거 2개 파일을 만들기 위해
			//filewriter를 2개 만듬.
			FileWriter fw1 = new FileWriter("hw03_00_201300995_quick.txt");
			BufferedWriter bw1 = new BufferedWriter(fw1);
			FileWriter fw2 = new FileWriter("hw03_00_201300995_quickRandom.txt");
			BufferedWriter bw2 = new BufferedWriter(fw2);
			
			//읽은걸 배열에 가져오기
			String[] s = br.readLine().split(",");
			//읽어들인 string을 int로 변환할껀데, 그걸 받을 배열
			//quick.txt의 결과와 quickRandom.txt의 결과 2개를 나눠서 저장하기 위해
			//행렬을 2개 놓았다.
			int[] array1 = new int[s.length];
			int[] array2 = new int[s.length];
			for (int i=0; i<s.length;i++) {
				array1[i] = Integer.valueOf(s[i]);
				array2[i] = Integer.valueOf(s[i]);
			}
			
			//Sort 실행!
			quickSort(array1,0,array1.length -1);
			quickSort_withRandom(array2,0,array2.length-1);
			
			//결과를 txt로 반환.
			for (int i=0;i<array1.length;i++) {
				bw1.write(String.valueOf(array1[i]) + ",");
				if (i == array1.length -1) {
					bw1.write(String.valueOf(array1[i]));
				}
			}
			for (int i=0;i<array2.length;i++) {
				bw2.write(String.valueOf(array2[i]) + ",");
				if (i == array2.length -1) {
					bw2.write(String.valueOf(array2[i]));
				}
			}
			
			br.close();
			bw1.close();
			bw2.close();
		} catch (IOException e) {
			System.out.println("파일 입출력 관련 에러.");
		}

	}
	
	public static int partition(int[] A,int p,int r) {
		//j = p. p는 j의 출발지점.배열의 맨 처음. i는 p-1부터 시작.
		int x = A[r];
		int i = p-1;
		int im; //자리바꿈 위한 임시저장소
		for (int j = p;j<r;j++) {
			if (A[j] <= x) {
				i++;
				im = A[j];
				A[j] = A[i];
				A[i] = im;
			}
		}
		i++;
		im = A[r];
		A[r] = A[i];
		A[i] = im;
		//return i의 의미는, i까지는 x보다 작도록 만들었다.
		return i;

	}
	
	public static int randomizedPartition(int[] A, int p, int r) {
		int i = (int)((Math.random()) * (r-p))+p+1;
		//자리 바꿈을 위한 임시값
		int im;
		im = A[r];
		A[r] = A[i];
		A[i] = im;
		
		return partition(A,p,r);
	}
	
	public static void quickSort(int[] A, int p, int r) {
		if (p < r) {
			int q = partition(A,p,r);
					quickSort(A,p,q-1);
					quickSort(A,q+1,r);
		}
	}

	public static void quickSort_withRandom(int[] A, int p, int r) {
		if (p < r) {
			int q = randomizedPartition(A,p,r);
				quickSort_withRandom(A,p,q-1);
				quickSort_withRandom(A,q+1,r);
		}
	}
}
