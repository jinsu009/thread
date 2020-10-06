package basic;

//20.02.13 -04

//스레드의 상태를 출력하는 예제
public class ThreadTest10 {

	public static void main(String[] args) {
		TargetThread target = new TargetThread(); //타겟용 스레드 객체 생성
		
		//타겟용 스레드를 생성자의 매개값으로 주어서 스레드의 상태를 출력하는 스레드 객체를 생성한다. 
		StatePrintThread prn = new StatePrintThread(target);
		
		prn.start(); //스레드의 상태를 출력하는 스레드를 작동시킨다. ..????
	}

}

// TargetThread의 상태를 출력하는 스레드 클래스
class StatePrintThread extends Thread {
	private TargetThread target; // 상태를 출력할 대상이되는 스레드가 저장될 변수

	// 생성자
	public StatePrintThread(TargetThread target) {
		this.target = target;
	}

	@Override
	public void run() {
		while (true)// 무한 반복문
		{
			// target용 스레드의 상태값 구하기
			Thread.State state = target.getState();
			System.out.println("타겟스레드의 현재 상태값 >> " + state);

			if (state == Thread.State.NEW) {
				// 스레드의 상태가 new (새롭게 시작된)상태인지 검사
				target.start(); // 스레드작동
			}

			if (state == Thread.State.TERMINATED) {
				// 스레드가 종료(TERMINATED)된 상태인지 검사
				break;// 스레드를 출력하는 스레드로 끝나도록 한다.
			}
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				//InterruptedException e
			}
			
		}
	}

}

// target용 스레드 클래스 작성
class TargetThread extends Thread {

	@Override
	public void run() {
		for (long i = 1l; i <= 2000000000l; i++) {
		}// 시간지연용

		try {
			Thread.sleep(1500);// 1.5초
		} catch (InterruptedException e) {
		}

		for (long i = 1l; i <= 2000000000l; i++) {
		}// 시간지연용
	}
}