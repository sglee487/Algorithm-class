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

		//ArrayList �� �����迭��, HashMap�� key,value��(���̽��� ��ųʸ�ó��) ����
		try {
					//���Ͽ��� �о�ͼ� �и��ؾ���.
		FileReader fr = new FileReader("data03.txt");
		BufferedReader br = new BufferedReader(fr);

		//���� �� �б�
		String s;
		String[] st = new String[2]; // ù��°�� int key��, �ι�°�� string�� ������.
		while ((s = br.readLine()) != null ) {
			st = s.split(", ");
			first_insert(array, Integer.valueOf(st[0]),st[1], map);
		}
		
		
		
		//test
		System.out.println(array.get(2));
		System.out.println(array.indexOf(5));
		
		//�ϴ� ó���� �� �Է¹ޱ�
		//first_insert(array, key, String x);
		
	fr.close();
		} catch (IOException e) {
			System.out.println("������ ���ҷ��Խ��ϴ�.");
		}
}
	
	public static void insert(ArrayList<Integer> array,String x, HashMap<Integer,String> map) {
		//�տ� key���� �ƹ����Գ� �ϰ�, array�� �߰��ϰ� map�� �߰��ؼ� ������ ������.
		int randomkey = (int)((Math.random() * 250) + 1);
		array.add(randomkey);
		map.put(randomkey, x);
	}
	
	public static void first_insert(ArrayList<Integer> array, int key, String x, HashMap<Integer,String> map) {
		//�� ó���� �������� key���� �����������ϱ�, �߰��ؼ� ����.
		array.add(key);
		map.put(key, x);
	}
	
	public static void printAll(ArrayList<Integer> array, HashMap<Integer,String> map) {
		for(Integer tmp : array) {
			
		}
	}
	

}
