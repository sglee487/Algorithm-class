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
		
		// �����̵��� table�� ������, ���� �� �տ� ���� �� ĭ ���°� ������
		// ���߿� charAt���� ���� �̾Ƴ��ǵ�, 0�� ����, 1�� ù��° ����, 2�� �ι�° ����..
		line1 = " " + line1;
		line2 = " " + line2;
		
		// line1�� ������, line2�� ������
		// ��, line2�� ��. line1�� ��.
		//int[][] MemoryTable = new int [line2.length()][line1.length()];
		// ����� ������ �����ؾߵɰ� ���Ƽ�, 3���� �迭�� ���� �����, ó�� �� [][]�� �� ���̺�� ����,
		// ���� [0]�� MemoryTable�� ����, [1]�� ���Ⱚ�� ������
		// [2]�� ����(��� ������ �� ��ô �κ�) ������ ���� �ٸ� ���� ������ ����.
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
		
		// ���� ���ڸ� �����ִ� �Լ��� �̿��� MemoryTable�� ä������.
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
		
		// ���ο� "-------" string�� ����� �ϳ��� ä������ ������� �Ϸ�������, string�� ������ �Ұ����ϹǷ�
		// char�� ��� list�� ����� �ٲٴ� ������� ����...
		
		// ���̴� ���� �� ���� +1�� ������ϴϱ�...
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
		System.out.println("&& �� ���ҵ� ���");
		int i = line2.length()-1;
		int j = line1.length()-1;
		int count = MaxLength-1;
		MemoryFromTable[i][j][2] = Follow;
		while ((i > 0 || j > 0) && count > 0) {
		//while (count > 0) {	
			
			// �ϴ� ��ĥ���� ����..
			// ���⿡ ���� ���ڸ� �ٲٴ°Ŵ�.
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
				// �࿡ ��ȭ ����. ���� ��ȭ ����.
				// ���⼭ ���� i, ���� j.
				// ��ȭ �ִ����� ���ڸ�, �������� '-'��.
				// ���� line2, ���� line1
				//line2char[i] = '-';
				//line1char[j] = line1.charAt(j);
				line2char[count] = '-';
				line1char[count] = line1.charAt(j);
				j--;
				break;
			case FromUpAndDiagonal:
				MemoryFromTable[i-1][j][2] = Follow;
				MemoryFromTable[i-1][j-1][2] = Follow;
				// ���� �밢�� �ΰ��� �� �������� ����� ����.
				//line2char[i] = line2.charAt(i);
				//line1char[j] = '-';
				line2char[count] = line2.charAt(i);
				line1char[count] = '-';
				i--;
				break;
			case FromDiagonalAndLeft:
				MemoryFromTable[i-1][j-1][2] = Follow;
				MemoryFromTable[i][j-1][2] = Follow;
				// �밢���� ���� �ΰ��� �� �������� ����� ����.
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
				// ��� �ɷ��� �� �������� ����� ����.
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
		
		System.out.println("&& ���� ���");

		showTable3D(MemoryFromTable, line1, line2);
		
		System.out.println("&& ������ ����");
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
		
		System.out.print("����\tX\t\t");
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
	
	// up, diagonal, left���� �г�Ƽ ���� ������� ������ ����.
	public static int maxFrom(int up, int diagonal, int left) {
		if ((up == diagonal) && (diagonal== left)) return FromAll;
		else if (up > diagonal) return FromUp;
		else if (up == diagonal) return FromUpAndDiagonal;
		else if (diagonal > left) return FromDiagonal;
		else if (diagonal == left) return FromDiagonalAndLeft;
		else return FromLeft;
	}
	
	// �� �� ���ڸ� ���� �����ִ� �Լ��� �ʿ��Ұ� ����.
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
