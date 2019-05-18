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
			

			//읽은걸 배열에 가져오기
			String l;
			//좌표로 받기위해 2가지 값만 저장하도록 공간을 만듬.
			String[] s = new String[1];
			
			// x좌표, y좌표를 저장할 배열.
			// 각각의 배열에 int값으로 저장.
			ArrayList<Double> x_arr = new ArrayList<Double>();
			ArrayList<Double> y_arr = new ArrayList<Double>();
			System.out.println("Input Data");
			while ((l = br.readLine())!= null) {
				System.out.println(l);
				s = l.split(",");
				x_arr.add(Double.valueOf(s[0]));
				y_arr.add(Double.valueOf(s[1]));
			}

			// x좌표로 각 배열들을 정렬
			//자리 바꾸기 위한 임시값
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
			
			// Line의 index값.
			int Line = x_arr.size()/2;
			
			// Line의 x값 지정. 모든 x좌표 값들의 평균값으로 했다.
			double x_line = 0;
			for (int i = 0; i < x_arr.size();i++) {
				x_line += x_arr.get(i);
			}
			x_line = x_line / x_arr.size();
			
			// Line을 기준으로 좌, 우 나눠서 계산
			double leftlow = 10000, rightlow = 10000, lowest;
			
			// 비교할때 잠시 저장을 위한 임시값
			double tmpdouble;
			
			// 왼쪽
			leftlow = btwdot(leftlow, Line, x_arr, y_arr);
			
			// 오른쪽
			rightlow = btwdot(leftlow, Line, x_arr, y_arr);
			
			// leftlow와 rightlow중 작은값을 저장한다.
			lowest = min(leftlow, rightlow);
			
			// 이제 Line부근을 비교한다.
			// Line 부근에 있는 index값을 따로 배열에 저장한 다음, 이 배열에 들어있는 점끼리만 비교할 예정.
			ArrayList<Integer> mid = new ArrayList<Integer>();
			for (int i=0; i<x_arr.size(); i++) {
				// Line에서 lowest길이보다 작은지 큰지 비교. 크면 배열에 저장안함.
				if (Math.abs(x_line - x_arr.get(i)) < lowest) {
					mid.add(i);
				}
			}
			
			// 이제 mid끼리 비교.
			lowest = btwdot2(lowest, mid.size(), x_arr, y_arr, mid);

			System.out.println("Output Data\n" + lowest);
			
			
			br.close();
			
			} catch (IOException e) {
				System.out.println("파일 입출력 관련 에러.");
			}

	}

	// 두 점끼리 길이 비교해서 가장 작은 길이를 반환하는 함수
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
	
	// Line 부근의 점들에서 segment를 하나 더 받아야 해서 생긴 함수. 위 함수와 거의 동일함.
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
	
	// 두 점의 길이 반환 함수
	static double length (double ax, double ay, double bx, double by) {
		return Math.sqrt(Math.pow(Math.abs(ax-bx), 2) + Math.pow(Math.abs(ay-by), 2));
	}
	
	// 둘중에 작은 값 반환 함수
	static double min(double a, double b) {
		if (a < b) {
			return a;
		}
		else {
			return b;
		}
	}

}
