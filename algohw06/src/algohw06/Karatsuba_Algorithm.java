package algohw06;

import java.util.Scanner;

// 201300995
public class Karatsuba_Algorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int threshold = 3;
		
		// x, y값. 나중에 입력으로 바꾸자.
		//long x = 5324;
		//long y = 1233;
		Scanner keyboard = new Scanner(System.in);
		System.out.print("x값 입력: ");
		long x = keyboard.nextLong();
		System.out.print("y값 입력: ");
		long y = keyboard.nextLong();
		
		//long x = 1111;
		//long y = 1111;		
		long result;
		
		
		// 일단 곱을 수행하는데, thres값보다 많은 자릿수 값이 나오면 또 곱셉하는 형식.
		result = kara_multi(x,y,threshold);
		
		
		System.out.println("결과값: " + result);
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
		//z2 = kara_multi(a1,b1,thres)... 이런식으로 가야될것 같다.
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
