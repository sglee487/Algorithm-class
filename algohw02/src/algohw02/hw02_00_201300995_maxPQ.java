package algohw02;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class hw02_00_201300995_maxPQ {

	public static void main(String[] args) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		HashMap<Integer,String> map = new HashMap<Integer, String>();

		//ArrayList 는 동적배열로, HashMap은 key,value로(파이썬의 딕셔너리처럼) 쓰기
		try {
					//파일에서 읽어와서 분리해야함.
		FileReader fr = new FileReader("data03.txt");
		BufferedReader br = new BufferedReader(fr);

		//한줄 씩 읽기
		String s;
		String[] st = new String[2]; // 첫번째는 int key값, 두번째는 string을 빼오자.
		while ((s = br.readLine()) != null ) {
			st = s.split(", ");
			first_insert(array, Integer.valueOf(st[0]),st[1], map);
		}
		
		
		
		//test
		System.out.println(array.get(2));
		System.out.println(array.indexOf(5));
		
		//일단 처음에 다 입력받기
		//first_insert(array, key, String x);
		
	fr.close();
		} catch (IOException e) {
			System.out.println("파일을 못불러왔습니다.");
		}
}
	
	public static void insert(ArrayList<Integer> array,String x, HashMap<Integer,String> map) {
		//앞에 key값은 아무렇게나 하고, array에 추가하고 map에 추가해서 연결할 생각임.
		int randomkey = (int)((Math.random() * 250) + 1);
		array.add(randomkey);
		map.put(randomkey, x);
	}
	
	public static void first_insert(ArrayList<Integer> array, int key, String x, HashMap<Integer,String> map) {
		//맨 처음에 받을때는 key값이 정해져있으니까, 추가해서 받음.
		array.add(key);
		map.put(key, x);
	}
	
	public static void printAll(ArrayList<Integer> array, HashMap<Integer,String> map) {
		for(Integer tmp : array) {
			
		}
	}
	

}
