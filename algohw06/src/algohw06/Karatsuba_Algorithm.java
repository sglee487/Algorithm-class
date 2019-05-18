package algohw06;

import java.util.Scanner;

// 201300995
public class Karatsuba_Algorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int threshold = 3;
		
		// x, y��. ���߿� �Է����� �ٲ���.
		//long x = 5324;
		//long y = 1233;
		Scanner keyboard = new Scanner(System.in);
		System.out.print("x�� �Է�: ");
		long x = keyboard.nextLong();
		System.out.print("y�� �Է�: ");
		long y = keyboard.nextLong();
		
		//long x = 1111;
		//long y = 1111;		
		long result;
		
		
		// �ϴ� ���� �����ϴµ�, thres������ ���� �ڸ��� ���� ������ �� �����ϴ� ����.
		result = kara_multi(x,y,threshold);
		
		
		System.out.println("�����: " + result);
	}
	
	public static long kara_multi(long a, long b, int thres) {
		long z2,z1,z0;
		int tem_thres_a = (int)(Math.log10(a) + 1);
		//System.out.println(tem_thres_a);
		long thres_pow = (long)Math.pow(10, thres-1);
		//System.out.println("thres_pow : " + thres_pow);
		long mid_ja = (long)Math.pow(10, tem_thres_a/2);
		//System.out.println("mid_ja : " + mid_ja);
		long result;
		

		// z2 = x1*y1, z0 = x0*y0, z1=x1y0 + x0y1 = (x1+x0)(y1+y0) - z2 - z0
		//z2 = kara_multi(a1,b1,thres)... �̷������� ���ߵɰ� ����.
		if(a < thres_pow && b < thres_pow) {
			return a*b;
		} else {
			z2 = kara_multi((a/mid_ja),(b/mid_ja),thres);
			z0 = kara_multi((a%mid_ja),(b%mid_ja),thres);
			z1 = kara_multi((a/mid_ja) + (a%mid_ja),(b/mid_ja) + (b%mid_ja),thres) -z2 -z0;
			//System.out.print("z2 : " + z2 + ", z0 : " + z0 + ", z1 : " + z1);
			//System.out.println();
			
			result = z2*mid_ja*mid_ja + z1*mid_ja + z0;
			return result;
		}
	}

}
