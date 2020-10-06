package basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//20.02.17--02

/*
 쓰레드의 동기화 처리 예제 => 은행의 입출금을 쓰레드로 처리하는 예제
 (lock을 이용한 동기화)
 */
public class ThreadTest18 {
	private int balance; // 입출금 잔액이 저장될 변수
	private final Lock lock = new ReentrantLock();

	// lock객체 생성 되도록이면 private final로 만든다.

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	// 입금하는 메서드
	public void deposit(int money) {
		lock.lock(); // 락 설정 >> lock 메소드 호출

		balance += money;
		// 잔액을 누적

		lock.unlock();
		// 락 해제 (lock메소드로 락을 설정한 곳에서는 반드시 unlock메서드로 락을 해제해주어야한다.)
	}

	public boolean withdraw(int money) {
		lock.lock();

		boolean chk = false;

		try {
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
				chk = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// try catch가 실행이 되고 반드시 거쳐가야하는곳
			lock.unlock();
		}
		return chk;

	}

	public static void main(String[] args) {
		final ThreadTest18 acount = new ThreadTest18();
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
