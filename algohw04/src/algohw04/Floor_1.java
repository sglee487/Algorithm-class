package algohw04;


// 201300995
// 방법 1
public class Floor_1 {
	
	public static void main(String[] args) {
		
		long N = 1000000000000000000L;
		int e = -1;
		long k = 1L;
		while (k <= N) {
			e++;
			k = k*2;
		}
		System.out.println("log(2)" + N +"의 floor는 " + e);
	}
}
