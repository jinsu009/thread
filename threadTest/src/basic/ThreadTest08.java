package basic;

//20.02.13 -02
public class ThreadTest08 {

	public static void main(String[] args) {
		Thread th1 = new UpperThread();
		Thread th2 = new LowerThread();

		// 우선순위 설정 ==> start()메소드 호출 전에 해야한다.
		th1.setPriority(9);
		th2.setPriority(3);
		// th1 의 우선순위가 9일때 th2의 우선순위가 8이든 3이든 상관은 없다.
		// 운영체제 혹은 cpu의 성능에 따라 차이가 나타난다.
		// 성능이 좋을수록 우선순위는 별 소용이 없다.

		// 우선순위출력하기
		System.out.println("th1 의 우선순위 >> " + th1.getPriority());
		System.out.println("th2 의 우선순위 >> " + th2.getPriority());

		th1.start();
		th2.start();
	}
}

// 대문자를 출력하는 메소드
class UpperThread extends Thread {

	@Override
	public void run() {
		for (char ch = 'A'; ch <= 'Z'; ch++) {

			System.out.println(ch);

			// 시간 때우기용 반복문
			for (long i = 1l; i <= 1000000000l; i++) {
			}

		}

	}
}

// 소문자를 출력하는 메소드
class LowerThread extends Thread {

	@Override
	public void run() {

		for (char ch = 'a'; ch <= 'z'; ch++) {

			System.out.println(ch);

			// 시간 때우기용 반복문
			for (long i = 1l; i <= 1000000000l; i++) {
			}

		}
	}

}
