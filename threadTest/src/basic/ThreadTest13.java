package basic;

//20.02.14--01

//yield()메소드 연습용예제(교재 760p)

public class ThreadTest13 {
	public static void main(String[] args) {
		ThreadYield th1 = new ThreadYield("1번 스레드");
		ThreadYield th2 = new ThreadYield("2번 스레드");

		th1.start();
		th2.start();

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out
				.println("=============================00000000000000000000000=");

		th1.work = false;

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}

		System.out
				.println("=============================22222222222222222222222");

		th1.work = true;

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}

		th1.stop = true;
		th2.stop = true;

	}
}

// yield()메소드 연습용스레드
class ThreadYield extends Thread {

	public boolean stop = false; // 스레드 전체의 실행 제어용
	public boolean work = true; // 스레드 작동중 작업의 실행 여부 제어용

	// 생성자
	public ThreadYield(String name) {
		super(name);// 스레드의 이름 설정(Thread 클래스에 name속성이 있는데 이 속성에는 스레드의 이름이 저장된다.)
	}

	@Override
	public void run() {
		while (!stop) {
			// stop이 true가 되면 반복문 종료
			if (work) {
				// getName() => 현재스레드의 name속성값을 반환한다.
				System.out.println(getName() + " 스레드 작업중.");
			} else {
				Thread.yield();
				// yield()메소드는 thread의 정적 메소드 이다.
				// else가 없어도 결과는 같다.
				// 그럼에도 불구하고 작성한 이유 :: cpu의 공회전(쓸데없이 작동되는 것) 시간이 감소된다. 효율성을 높이기 위해서!!
			}
		}

		System.out.println(getName() + " 스레드 종료.");
	}
}
