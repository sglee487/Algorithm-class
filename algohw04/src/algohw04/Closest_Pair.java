package algohw04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// 201300995
public class Closest_Pair {

	public static void main(String[] args) {
		
		try {
			FileReader fr = new FileReader("data05_closest.txt");
			BufferedReader br = new BufferedReader(fr);
			

			//������ �迭�� ��������
			String l;
			//��ǥ�� �ޱ����� 2���� ���� �����ϵ��� ������ ����.
			String[] s = new String[1];
			
			// x��ǥ, y��ǥ�� ������ �迭.
			// ������ �迭�� int������ ����.
			ArrayList<Double> x_arr = new ArrayList<Double>();
			ArrayList<Double> y_arr = new ArrayList<Double>();
			System.out.println("Input Data");
			while ((l = br.readLine())!= null) {
				System.out.println(l);
				s = l.split(",");
				x_arr.add(Double.valueOf(s[0]));
				y_arr.add(Double.valueOf(s[1]));
			}

			// x��ǥ�� �� �迭���� ����
			//�ڸ� �ٲٱ� ���� �ӽð�
			double tmp_x;
			double tmp_y;
			for (int i= 0; i < x_arr.size(); i++) {
				for (int j=i; j < x_arr.size(); j++) {
					if (x_arr.get(i) > x_arr.get(j)) {
						tmp_x = x_arr.get(i);
						tmp_y = y_arr.get(i);
						x_arr.set(i, x_arr.get(j));
						y_arr.set(i, y_arr.get(j));
						x_arr.set(j, tmp_x);
						y_arr.set(j, tmp_y);
					}
				}
			}
			
			// Line�� index��.
			int Line = x_arr.size()/2;
			
			// Line�� x�� ����. ��� x��ǥ ������ ��հ����� �ߴ�.
			double x_line = 0;
			for (int i = 0; i < x_arr.size();i++) {
				x_line += x_arr.get(i);
			}
			x_line = x_line / x_arr.size();
			
			// Line�� �������� ��, �� ������ ���
			double leftlow = 10000, rightlow = 10000, lowest;
			
			// ���Ҷ� ��� ������ ���� �ӽð�
			double tmpdouble;
			
			// ����
			leftlow = btwdot(leftlow, Line, x_arr, y_arr);
			
			// ������
			rightlow = btwdot(leftlow, Line, x_arr, y_arr);
			
			// leftlow�� rightlow�� �������� �����Ѵ�.
			lowest = min(leftlow, rightlow);
			
			// ���� Line�α��� ���Ѵ�.
			// Line �αٿ� �ִ� index���� ���� �迭�� ������ ����, �� �迭�� ����ִ� �������� ���� ����.
			ArrayList<Integer> mid = new ArrayList<Integer>();
			for (int i=0; i<x_arr.size(); i++) {
				// Line���� lowest���̺��� ������ ū�� ��. ũ�� �迭�� �������.
				if (Math.abs(x_line - x_arr.get(i)) < lowest) {
					mid.add(i);
				}
			}
			
			// ���� mid���� ��.
			lowest = btwdot2(lowest, mid.size(), x_arr, y_arr, mid);

			System.out.println("Output Data\n" + lowest);
			
			
			br.close();
			
			} catch (IOException e) {
				System.out.println("���� ����� ���� ����.");
			}

	}

	// �� ������ ���� ���ؼ� ���� ���� ���̸� ��ȯ�ϴ� �Լ�
	static double btwdot (double _lowest, int end, ArrayList<Double> _x_arr, ArrayList<Double> _y_arr) {
		double tmpdouble;
		for (int i=0; i<end; i++) {
			for (int j=i+1; j<end; j++) {
				tmpdouble = length(_x_arr.get(i),_y_arr.get(i),_x_arr.get(j),_y_arr.get(j));
				if (tmpdouble < _lowest) {
					_lowest = tmpdouble;
				}
			}
		}
		return _lowest;
	}
	
	// Line �α��� ���鿡�� segment�� �ϳ� �� �޾ƾ� �ؼ� ���� �Լ�. �� �Լ��� ���� ������.
	static double btwdot2 (double _lowest, int end, ArrayList<Double> _x_arr, ArrayList<Double> _y_arr, ArrayList<Integer> _mid) {
		double tmpdouble;
		for (int i=0; i<end; i++) {
			for (int j=i+1; j<end; j++) {
				tmpdouble = length(_x_arr.get(_mid.get(i)),_y_arr.get(_mid.get(i)),_x_arr.get(_mid.get(j)),_y_arr.get(_mid.get(j)));
				if (tmpdouble < _lowest) {
					_lowest = tmpdouble;
				}
			}
		}
		return _lowest;
	}
	
	// �� ���� ���� ��ȯ �Լ�
	static double length (double ax, double ay, double bx, double by) {
		return Math.sqrt(Math.pow(Math.abs(ax-bx), 2) + Math.pow(Math.abs(ay-by), 2));
	}
	
	// ���߿� ���� �� ��ȯ �Լ�
	static double min(double a, double b) {
		if (a < b) {
			return a;
		}
		else {
			return b;
		}
	}

}
