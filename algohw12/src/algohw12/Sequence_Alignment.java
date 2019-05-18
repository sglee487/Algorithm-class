package algohw12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//201300995
public class Sequence_Alignment {
	
	static final int SameChar = 1;
	static final int DiffChar = -1;
	static final int OneSpace = -2;
	static final int TwoSpace = 0;
	
	static final int FromNot = 0;
	static final int FromUp = 1;
	static final int FromDiagonal = 2;
	static final int FromLeft = 3;
	static final int FromUpAndDiagonal = 4;
	static final int FromDiagonalAndLeft = 5;
	static final int FromAll = 6;
	
	static final int FollowNot = 0;
	static final int Follow = 1;
	
	public static void main(String[] args) {

		try {
			FileReader fr = new FileReader("data12.txt");
			BufferedReader br = new BufferedReader(fr);
			
			String line1 = br.readLine();
			String line2 = br.readLine();
			
			sequenceAligner(line1,line2);
			
			
		} catch (IOException e) {
			
		}
	}
	
	public static void sequenceAligner(String line1, String line2) {
		
		// 슬라이드의 table을 봤을때, 문자 맨 앞에 공백 한 칸 띄우는게 유리함
		// 나중에 charAt으로 글자 뽑아낼건데, 0은 공백, 1은 첫번째 글자, 2는 두번째 글자..
		line1 = " " + line1;
		line2 = " " + line2;
		
		// line1이 가로축, line2가 세로축
		// 즉, line2가 행. line1이 열.
		//int[][] MemoryTable = new int [line2.length()][line1.length()];
		// 방향과 색깔을 저장해야될것 같아서, 3차원 배열을 따로 만들어, 처음 두 [][]는 위 테이블과 같고,
		// 뒤의 [0]은 MemoryTable과 같고, [1]은 방향값을 가지고
		// [2]는 색깔(계산 끝난뒤 역 추척 부분) 영역에 따라 다른 값을 가지게 하자.
		int[][][] MemoryFromTable = new int [line2.length()][line1.length()][3];
		
		//MemoryTable[0][0] = 0;
		MemoryFromTable[0][0][0] = 0;
		for (int i=1;i < MemoryFromTable.length; i++) {
			//MemoryTable[i][0] = MemoryTable[i-1][0] + OneSpace;
			//MemoryFromTable[i][0][0] = MemoryTable[i-1][0] + OneSpace;
			MemoryFromTable[i][0][0] = MemoryFromTable[i-1][0][0] + OneSpace;
			MemoryFromTable[i][0][1] = FromUp;
			MemoryFromTable[i][0][2] = FollowNot;
		}
		for (int j=1;j < MemoryFromTable[0].length; j++) {
			//MemoryTable[0][j] = MemoryTable[0][j-1] + OneSpace;
			//MemoryFromTable[0][j][0] = MemoryTable[0][j-1] + OneSpace;
			MemoryFromTable[0][j][0] = MemoryFromTable[0][j-1][0] + OneSpace;
			MemoryFromTable[0][j][1] = FromLeft;
			MemoryFromTable[0][j][2] = FollowNot;
		}
		
		// 양쪽 글자를 비교해주는 함수를 이용해 MemoryTable을 채워넣자.
		int up,dia,left;
		for (int i=1; i< MemoryFromTable.length;i++) {
			for (int j=1; j < MemoryFromTable[0].length;j++) {
				up = MemoryFromTable[i-1][j][0] + compareChar(' ',line2.charAt(i));
				dia = MemoryFromTable[i-1][j-1][0] + compareChar(line1.charAt(j),line2.charAt(i));
				left = MemoryFromTable[i][j-1][0] + compareChar(line1.charAt(j),' ');
				
				MemoryFromTable[i][j][0] = max(up,dia,left);
				MemoryFromTable[i][j][1] = maxFrom(up,dia,left);
			}
		}
		
		// 새로운 "-------" string을 만들고 하나씩 채워가는 방식으로 하려햇으나, string은 수정이 불가능하므로
		// char을 담는 list를 만들고 바꾸는 방식으로 하자...
		
		// 길이는 가장 긴 쪽의 +1로 맞춰야하니까...
		int MaxLength = (line1.length()>line2.length()) ? line1.length() : line2.length();
		char[] line1char = new char[MaxLength];
		char[] line2char = new char[MaxLength];
		
		for (int i=0;i < MaxLength;i++) {
			line1char[i] = '-';
			line2char[i] = '-';
		}
		
		/*
		char[] line1char = new char[line1.length()];
		char[] line2char = new char[line2.length()];
		
		for (int i=0;i < line1.length();i++) line1char[i] = '-'; 
		for (int i=0;i < line2.length();i++) line2char[i] = '-'; 
		*/
		System.out.println("&& 각 원소들 계산");
		int i = line2.length()-1;
		int j = line1.length()-1;
		int count = MaxLength-1;
		MemoryFromTable[i][j][2] = Follow;
		while ((i > 0 || j > 0) && count > 0) {
		//while (count > 0) {	
			
			// 일단 색칠부터 하자..
			// 방향에 따라 문자를 바꾸는거다.
			switch (MemoryFromTable[i][j][1]) {
			case FromUp:
				MemoryFromTable[i-1][j][2] = Follow;
				//line2char[i] = line2.charAt(i);
				//line1char[j] = '-';
				line2char[count] = line2.charAt(i);
				line1char[count] = '-';
				i--;
				break;
			case FromDiagonal:
				MemoryFromTable[i-1][j-1][2] = Follow;
				//line2char[i] = line2.charAt(i);
				//System.out.println(line2char[i]);
				//line1char[j] = line1.charAt(j);
				line2char[count] = line2.charAt(i);
				//System.out.println(line2char[i]);
				line1char[count] = line1.charAt(j);
				i--;
				j--;
				break;
			case FromLeft:
				MemoryFromTable[i][j-1][2] = Follow;
				// 행에 변화 없음. 열에 변화 있음.
				// 여기서 행은 i, 열은 j.
				// 변화 있는쪽은 글자를, 없는쪽은 '-'를.
				// 행이 line2, 열이 line1
				//line2char[i] = '-';
				//line1char[j] = line1.charAt(j);
				line2char[count] = '-';
				line1char[count] = line1.charAt(j);
				j--;
				break;
			case FromUpAndDiagonal:
				MemoryFromTable[i-1][j][2] = Follow;
				MemoryFromTable[i-1][j-1][2] = Follow;
				// 위와 대각선 두개일 땐 위쪽으로 가기로 하자.
				//line2char[i] = line2.charAt(i);
				//line1char[j] = '-';
				line2char[count] = line2.charAt(i);
				line1char[count] = '-';
				i--;
				break;
			case FromDiagonalAndLeft:
				MemoryFromTable[i-1][j-1][2] = Follow;
				MemoryFromTable[i][j-1][2] = Follow;
				// 대각선과 왼쪽 두개일 땐 왼쪽으로 가기로 하자.
				//line1char[j] = line1.charAt(j);
				//line2char[i] = '-';
				line1char[count] = line1.charAt(j);
				line2char[count] = '-';
				j--;
				break;
			case FromAll:
				MemoryFromTable[i-1][j][2] = Follow;
				MemoryFromTable[i-1][j-1][2] = Follow;
				MemoryFromTable[i-1][j][2] = Follow;
				// 모두 걸렸을 땐 왼쪽으로 가기로 하자.
				//line1char[j] = line1.charAt(j);
				//line2char[i] = '-';
				line1char[count] = line1.charAt(j);
				line2char[count] = '-';
				j--;
				break;
			}
			count--;
			//System.out.println(count);
			printChars(line1char,line2char);
			System.out.println();

		}
		System.out.println();
		
		System.out.println("&& 최종 결과");

		showTable3D(MemoryFromTable, line1, line2);
		
		System.out.println("&& 최적의 조합");
		printChars(line1char,line2char);
		

		//showTable(MemoryTable);
		//showTable(MemoryFromTable);
	}
	
	public static void showTable(int[][] table) {
		/*
		for (int i=0;i < table.length;i++) {
			for (int j=0;j<table[0].length;j++) {
				System.out.print(table[i][j] + "\t");
			}
			System.out.println();
		}
		*/
		for (int[] row : table) {
			for (int value : row) {
				System.out.print(value + "\t");
			}
			System.out.println();
		}
	}
	
	public static void showTable3D(int [][][] table, String line1, String line2) {
		
		System.out.print("서열\tX\t\t");
		for (int i=1;i<line1.length();i++) {
			System.out.print(line1.charAt(i) + "\t");
		}
		System.out.println();
		System.out.print("Y\tIndex\t");
		for (int i=0;i < table[0].length;i++) {
			System.out.print(i+"\t");
		}
		System.out.println();
		int rowcount=0;
		//System.out.println(line2.length());
		
		for (int i=0;i < line2.length();i++) {
			System.out.print(line2.charAt(i) + "\t" + i + "\t");
			for (int j=0; j < line1.length();j++) {
				if (table[i][j][2] == Follow) {
					System.out.print("x " +table[i][j][0]+"\t");
				} else {
					System.out.print(" " + table[i][j][0]+"\t");
				}
			}
			System.out.println();
		}
		System.out.println();
		
		/*
		line1char[0] = ' ';
		for (char ch : line1char) {
			System.out.print(ch + "\t");
		}
		System.out.println();
		System.out.print("Y\tIndex\t");
		for (int i=0;i < line1char.length;i++) {
			System.out.print(i + "\t");
		}
		System.out.println();
		line2char[0] = ' ';
		int rowcount = 0;
		for (int[][] row : table) {
			System.out.print(line2char[rowcount] + "\t" + rowcount + "\t");
			rowcount++;
			for (int[] value : row) {
				if (value[2] == Follow)
					System.out.print("x "+value[0] + "\t");
				else
					System.out.print(" "+value[0] + "\t");
			}
			System.out.println();
		}
		System.out.println();
		*/
		/*
		for (int[][] row : table) {
			for (int[] value : row) {
				if (value[2] == Follow)
					System.out.print("x "+value[1] + "\t");
				else
					System.out.print("  "+value[1] + "\t");
			}
			System.out.println();
		}
		
		for (int[][] row : table) {
			for (int[] value : row) {
				if (value[2] == Follow)
					System.out.print("x "+value[2] + "\t");
				else
					System.out.print("  "+value[2] + "\t");
			}
			System.out.println();
		}
		*/
	}
	public static int max(int a, int b, int c) {
		return (a > b) ? a : (b > c) ? b : c;
	}
	
	// up, diagonal, left에는 패널티 적용 결과값이 들어가도록 하자.
	public static int maxFrom(int up, int diagonal, int left) {
		if ((up == diagonal) && (diagonal== left)) return FromAll;
		else if (up > diagonal) return FromUp;
		else if (up == diagonal) return FromUpAndDiagonal;
		else if (diagonal > left) return FromDiagonal;
		else if (diagonal == left) return FromDiagonalAndLeft;
		else return FromLeft;
	}
	
	// 양 쪽 글자를 서로 비교해주는 함수가 필요할것 같다.
	public static int compareChar(char a, char b) {
		if ((a == ' ') && (b == ' ')) return TwoSpace;	
		if ((a == ' ')) return OneSpace;
		if ((b == ' ')) return OneSpace;
		
		if (a == b) return SameChar;
		else return DiffChar;
	}

	public static void printChars(char[] char1, char[] char2) {
		/*
		for (char ch : char1)
			System.out.print(ch);
		System.out.println();
		for (char ch : char2)
			System.out.print(ch);
		System.out.println();
		*/
		
		for (int i=1;i < char1.length;i++) {
			System.out.print(char1[i]);
		}
		System.out.println();
		for (int i=1;i < char2.length;i++) {
			System.out.print(char2[i]);
		}
		System.out.println();
	}
}
