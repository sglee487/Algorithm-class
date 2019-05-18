package algohw03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Random;

public class hw03_00_201300995_quick {

	public static void main(String[] args) {
		
		//���� �б�
		try {
			FileReader fr = new FileReader("data04.txt");
			BufferedReader br = new BufferedReader(fr);
			
			//�׳� sort�� random �ؼ� sort�Ѱ� 2�� ������ ����� ����
			//filewriter�� 2�� ����.
			FileWriter fw1 = new FileWriter("hw03_00_201300995_quick.txt");
			BufferedWriter bw1 = new BufferedWriter(fw1);
			FileWriter fw2 = new FileWriter("hw03_00_201300995_quickRandom.txt");
			BufferedWriter bw2 = new BufferedWriter(fw2);
			
			//������ �迭�� ��������
			String[] s = br.readLine().split(",");
			//�о���� string�� int�� ��ȯ�Ҳ���, �װ� ���� �迭
			//quick.txt�� ����� quickRandom.txt�� ��� 2���� ������ �����ϱ� ����
			//����� 2�� ���Ҵ�.
			int[] array1 = new int[s.length];
			int[] array2 = new int[s.length];
			for (int i=0; i<s.length;i++) {
				array1[i] = Integer.valueOf(s[i]);
				array2[i] = Integer.valueOf(s[i]);
			}
			
			//Sort ����!
			quickSort(array1,0,array1.length -1);
			quickSort_withRandom(array2,0,array2.length-1);
			
			//����� txt�� ��ȯ.
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
			System.out.println("���� ����� ���� ����.");
		}

	}
	
	public static int partition(int[] A,int p,int r) {
		//j = p. p�� j�� �������.�迭�� �� ó��. i�� p-1���� ����.
		int x = A[r];
		int i = p-1;
		int im; //�ڸ��ٲ� ���� �ӽ������
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
		//return i�� �ǹ̴�, i������ x���� �۵��� �������.
		return i;

	}
	
	public static int randomizedPartition(int[] A, int p, int r) {
		int i = (int)((Math.random()) * (r-p))+p+1;
		//�ڸ� �ٲ��� ���� �ӽð�
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
