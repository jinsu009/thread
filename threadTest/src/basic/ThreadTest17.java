package basic;

//20.02.17--01
/*	
 쓰레드의 동기화 처리 예제 => 은행의 입출금을 쓰레드로 처리하는 예제
 (synchronized를 이용한 동기화)
 */
public class ThreadTest17 {

	private int balance; // 입출금 잔액이 저장될 변수

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	// 입금하는 메서드
	public synchronized void deposit(int money) {
		balance += money;
		// 잔액을 누적
	}

	// 출금하는 메소드
	// ( 반환값 ==> 출금이 성공하면 true, 출금이 실패 하면 false)

	// 동기화 영역에서 호출하는 메소드도 동기화 처리를 해주어야 안전하다.
	// 메서드 자체에 동기화 처리하기

	// 처음으로 메소드를 처리하는 부분을 헤더라고 한다.
	// 헤더부분에 싱크로나이즈드를 붙여준다.
	public boolean withdraw(int money) {
		synchronized (this) {
			// 동기화 블럭으로 동기화 처리하기
			// this : 현재객체가 동기화 처리가 되어야한다.
			if (balance >= money)
			// 잔액과 출금할 금액의 액수를 비교
			{
				for (int i = 1; i <= 100000000; i++) // 시간때우기용
				{
				}

				balance -= money;

				System.out.println("메서드 안에서 balance = " + getBalance());
				return true;
			} else {
				return false;
			}
		}
	}

	public static void main(String[] args) {
		final ThreadTest17 acount = new ThreadTest17();
		acount.setBalance(10000); // 통장 잔액 세팅

		// 익명 구현체를 이용한 쓰레드 구현
		Runnable acountTest = new Runnable() {

			@Override
			public void run() {
				boolean result = acount.withdraw(6000); // 6000원을 출금
				System.out.println("스레드에서 result = " + result + ", balance =  "
						+ acount.getBalance());
			}
		};
		// -----------------

		Thread th1 = new Thread(acountTest);
		Thread th2 = new Thread(acountTest);

		th1.start();
		th2.start();

	}
}