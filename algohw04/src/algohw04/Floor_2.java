package algohw04;

// 201300995
// ��� 2
public class Floor_2 {

	public static void main(String[] args) {
		
		long N = 1000000000000000000L;
		int e = 1;
		double new_k = 2;
		while (new_k <= N) {
			e = e*2;
			new_k = new_k*new_k;
		}
		//�̰� ����������, e�� N���� ū�� ����, k�� N���� ū ���� ���� ��갪(2^e �� ��갪).

		int mid = 0;
		int old_e = e/2;
		// e �� mid ���̰� 1 ���� �� �����Ѱ���.
		while (e - mid != 1) { // mid ���� ���;���.
			mid = ((old_e) + e)/2;
			
			if (N < new_k) {
				// mid���� ���� ���
				for (int i = e - mid; i>0; i--) {
					new_k = new_k/2;
				}

			}
			else {
				// mid���� ū ���
				for (int i = e - mid; i>0; i--) {
					new_k = new_k*2;
				}
			}
			
			if (N < new_k) {
				// mid���� ���� ���
				e = mid;

			}
			else {
				// mid���� ū ���
				old_e = mid;
			}
		}
		
		System.out.println("log(2)" + N +"�� floor�� " + mid);
	}

}
