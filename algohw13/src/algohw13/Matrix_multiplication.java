package algohw13;

import java.util.ArrayList;
import java.util.Scanner;

// 201300995
public class Matrix_multiplication {
	static final int Infinity = 999999;

	public static void main(String[] args) {
		// 일단 이렇게 입력된다고 가정하자. 형식은 나중에 만들고...
		// A(1) = 30 x 35
		// A(2) = 35 x 15
		// A(3) = 15 x 5
		// A(4) = 5 x 10
		// A(5) = 10 x 20
		// A(6) = 20 x 25
		
		// 입력받기
		Scanner keyboard = new Scanner(System.in);
		ArrayList<Integer> Input_Matrix = new ArrayList<Integer>();
		int count = 0;
		int tmp;
		
		while (true) {
			System.out.print((count+1) + " 번째 행렬의 행 길이: ");
			tmp = keyboard.nextInt();
			if (tmp == 0) break;
			Input_Matrix.add(tmp);
			System.out.print((count+1) + " 번째 행렬의 열 길이: ");
			Input_Matrix.add(keyboard.nextInt());
			System.out.println();
			
			count++;
		}
		
		//final int Matrix_Count = 6;
		final int Matrix_Count = Input_Matrix.size()/2;
		final int Matrix_Properties = 2;
		final int Matrix_row = 0;
		final int Matrix_col = 1;
		
		int[][] Matrix = new int[Matrix_Count][Matrix_Properties];
		
		// [(n-1)번째 매트릭스][0이면 행, 1이면 열]. 이런식으로 만들자.
		// 여기 밑에는 나중에 사용자의 입력으로 받도록 바꿔야 한다.
		/*
		Matrix[0][0] = 30; Matrix[0][1] = 35;
		Matrix[1][0] = 35; Matrix[1][1] = 15;
		Matrix[2][0] = 15; Matrix[2][1] = 5;
		Matrix[3][0] = 5; Matrix[3][1] = 10;
		Matrix[4][0] = 10; Matrix[4][1] = 20;
		Matrix[5][0] = 20; Matrix[5][1] = 25;
		*/
		System.out.println();
		for (int i=0; i < Input_Matrix.size();i++) {
			Matrix[i/2][0] = Input_Matrix.get(i);
			System.out.print("A(" + (i/2+1) + ") = " + Input_Matrix.get(i));
			i++;
			Matrix[i/2][1] = Input_Matrix.get(i);
			System.out.println(" x " + Input_Matrix.get(i));
		}
		//
		
		// m과 s 행렬.
		int[][] m = new int[Matrix_Count][Matrix_Count];
		int[][] s = new int[Matrix_Count][Matrix_Count];

		int[] p = new int[Matrix_Count+1];
		p[0] = Matrix[0][Matrix_row];
		for (int i=1;i<Matrix_Count+1;i++) {
			p[i] = Matrix[i-1][Matrix_col];
		}
		
		matrix_Chain_Order(p, m, s);

		
		//시험용
		//print_Array(p);
		System.out.println("----------------------------------------------");
		print_Matrix(m);
		System.out.println("----------------------------------------------");
		print_Matrix(s);
		System.out.println("----------------------------------------------");
		System.out.println("Optimal solution : " + m[0][Matrix_Count-1]);
		System.out.print("Optimal parens : ");
		print_Optimal_Parens(s,0,Matrix_Count-1);
		System.out.println();
	}
	
	static void print_Array(int[] a) {
		for (int value: a) System.out.print(value + "\t");
		System.out.println();
	}
	
	static void print_Matrix(int[][] a) {
		for(int row[] : a) {
			for (int value: row) {
				System.out.print(value + "\t");
			}
			System.out.println();
		}
	}
	
	static void matrix_Chain_Order(int[] p,int[][] m, int[][] s) {
		// 일단 m과 s 초기화.
		int n = p.length-1;
		int j = 0;
		int q=0;
		for (int i=0; i < n; i++) {
			for (int k=i; k >= 0; k--) {
				s[i][k] = -1;
				if (i==k) {
					m[i][k] = 0;
				} else {
					m[i][k] = -1;
				}
			}
		}
		
		for (int l=1; l < n; l++) {
			for (int i=0; i < n-l;i++) {
				j = i+l;
				m[i][j] = Infinity; // Infinity = 999999
				for (int k=i;k < j;k++) {
					q = m[i][k] + m[k+1][j] + (p[i]*p[k+1]*p[j+1]); // i번째 행 길이, i번째 열 길이, j번째 열 길이
					if (q < m[i][j]) {
						m[i][j] = q;
						s[i][j] = k+1;
					}
				}
			}
		}
	}
	
	static void print_Optimal_Parens(int[][] s, int i, int j) {
		if (i == j) {
			System.out.print("A"+(i+1));
		} else {
			System.out.print("(");
			print_Optimal_Parens(s, i, s[i][j]-1);
			print_Optimal_Parens(s, s[i][j], j);
			System.out.print(")");
		}
	}

}
