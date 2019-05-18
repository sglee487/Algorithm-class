package algohw04;

// 201300995
// 방법 2
public class Floor_2 {

	public static void main(String[] args) {
		
		long N = 1000000000000000000L;
		int e = 1;
		double new_k = 2;
		while (new_k <= N) {
			e = e*2;
			new_k = new_k*new_k;
		}
		//이게 끝나있으면, e는 N보다 큰쪽 지수, k도 N보다 큰 쪽의 완전 계산값(2^e 의 계산값).

		int mid = 0;
		int old_e = e/2;
		// e 와 mid 차이가 1 나면 다 수행한거임.
		while (e - mid != 1) { // mid 값이 나와야함.
			mid = ((old_e) + e)/2;
			
			if (N < new_k) {
				// mid보다 작을 경우
				for (int i = e - mid; i>0; i--) {
					new_k = new_k/2;
				}

			}
			else {
				// mid보다 큰 경우
				for (int i = e - mid; i>0; i--) {
					new_k = new_k*2;
				}
			}
			
			if (N < new_k) {
				// mid보다 작을 경우
				e = mid;

			}
			else {
				// mid보다 큰 경우
				old_e = mid;
			}
		}
		
		System.out.println("log(2)" + N +"의 floor는 " + mid);
	}

}
