package basic;

//20.02.12-04
/*
 1~20억까지의 합계를 구하는데 걸린시간을 체크해 보자 
 전체합계를 구하는 작업을 싱글 스레드가 처리할때와 
 멀티스레드로 분할해서 처리할 때의 시간을 비교해 보자
 */

public class ThreadTest04 {
	public static void main(String[] args) {

		SumThread smAll = new SumThread(1l, 2000000000l);

		SumThread[] sms = new SumThread[] {

		new SumThread(1l, 500000000l), new SumThread(500000000l, 1000000000l),
				new SumThread(1000000000l, 1500000000l),
				new SumThread(1500000000l, 2000000000l) };

		// 단독으로 처리하는 경우
		long startTime = System.currentTimeMillis();
		smAll.start();
		try {
			smAll.join();
		} catch (InterruptedException e) {
		}

		long endTime = System.currentTimeMillis();
		System.out.println("단독으로 처리할때의 시간 >> " + (endTime - startTime));

		System.out.println();

		System.out.println("=====================");

		// 여러개의 스레드가 협력해서 처리했을때
		startTime = System.currentTimeMillis();

		// 스레드 시작
		for (int i = 0; i < sms.length; i++) {
			sms[i].start();
		}

		for (SumThread sm : sms) {
			try {
				sm.join();
			} catch (Exception e) {
			}
		}

		endTime = System.currentTimeMillis();

		System.out.println("협력해서 처리할때의 시간 >> " + (endTime - startTime));

	}
}

class SumThread extends Thread {

	private long min, max;

	public SumThread(long min, long max) {
		this.min = min;
		this.max = max;
	}

	public void run() {
		long sum = 0l;
		for (long i = min; i <= max; i++) {
			sum += i;
		}
		System.out.println("합계 >> " + sum);
	}

}
