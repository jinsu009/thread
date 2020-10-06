package basic;

//20.02.12-01

public class ThreadTest01 {

	public static void main(String[] args) {
		// 싱글스레드 프로그램
		// 순서대로 진행
		for (int i = 0; i <= 200; i++) {
			System.out.print("*");
		}
		System.out.println();

		for (int j = 0; j <= 200; j++) {
			System.out.print("$");
		}
		System.out.println();

	}

}
