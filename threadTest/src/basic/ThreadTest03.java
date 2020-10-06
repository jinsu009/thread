package basic;

//20.02.12-03

public class ThreadTest03 {

	public static void main(String[] args) {
		// 스레드의 수행시간을 체크해보자
		Thread th = new Thread(new MyRunner());
		// 생성자가 매개변수의 값으로 들어가도록
		// 1970년 1월1일 0시0분0초(표준시간)부터 현재시간 까지 경과한 시간을 밀리세컨드(1/1000초)단위로 반환
		long startTime = System.currentTimeMillis();
		th.start();
		try {
			th.join();
			// 현재 스레드에서 th 쓰레드가 종료될때까지 기다리라는 메소드
		} catch (InterruptedException e) {

		}
		long endTime = System.currentTimeMillis();

		System.out.println("경과시간 : " + (endTime - startTime));
	}

}

// 1부터 10억까지의 합계를 구하는 스레드

class MyRunner implements Runnable {

	public void run() {
		long sum = 0l;
		for (long i = 1l; i <= 1_000_000_000l; i++) {
			sum += i;
		}
		System.out.println(sum);
	}
}
