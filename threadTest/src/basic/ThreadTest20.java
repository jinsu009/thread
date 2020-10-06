package basic;

import java.io.*;

//20.02.17--04

/*
 교재 771p
 wait(), notify()를 이용해서 두 쓰레드가 번갈아 한번씩 실행되는 예제
 */
public class ThreadTest20 {

	public static void main(String[] args) throws Exception {

		WorkObject workObj = new WorkObject();
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);

		thA.start();
		thB.start();
		// 두가지 메소드가 번갈아가면서 수행하는 것을 확인할 수 잇따.
	}

}

class WorkObject {

	public synchronized void methodA() throws Exception {
		System.out.println("methodA() 메소드 작업 중....");

		notify();

		wait();
	}

	public synchronized void methodB() throws Exception {
		System.out.println("methodB() 메소드 작업 중....");

		notify();

		wait();
	}

}

// WorkObject 의 methodA()만 호출하는 쓰레드
class ThreadA extends Thread {

	private WorkObject workObj;

	public ThreadA(WorkObject workObj) throws Exception {
		this.workObj = workObj;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				workObj.methodA();
			} catch (Exception e) {
			}
		}
	}
}

class ThreadB extends Thread {

	private WorkObject workObj;

	public ThreadB(WorkObject workObj) throws Exception {
		this.workObj = workObj;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				workObj.methodB();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

/*
 * package basic;
 * 
 * ​
 * 
 * public class ThreadTest20 {
 * 
 * ​
 * 
 * public static void main(String[] args) {
 * 
 * ​
 * 
 * WorkObject workObj = new WorkObject();
 * 
 * ThreadA thA = new ThreadA(workObj);
 * 
 * ThreadB thB = new ThreadB(workObj);
 * 
 * ​
 * 
 * thA.start();
 * 
 * thB.start();
 * 
 * // 두가지 메소드가 번갈아가면서 수행하는 것을 확인할 수 잇따.
 * 
 * }
 * 
 * ​
 * 
 * }
 * 
 * ​
 * 
 * class WorkObject {
 * 
 * ​
 * 
 * public synchronized void methodA() {
 * 
 * System.out.println("methodA() 메소드 작업 중....");
 * 
 * ​
 * 
 * notify();
 * 
 * ​
 * 
 * try {
 * 
 * wait();
 * 
 * } catch (InterruptedException e) {
 * 
 * }
 * 
 * }
 * 
 * ​
 * 
 * public synchronized void methodB() {
 * 
 * System.out.println("methodB() 메소드 작업 중....");
 * 
 * ​
 * 
 * notify();
 * 
 * ​
 * 
 * try {
 * 
 * wait();
 * 
 * } catch (InterruptedException e) {
 * 
 * }
 * 
 * }
 * 
 * ​
 * 
 * }
 * 
 * ​
 * 
 * // WorkObject 의 methodA()만 호출하는 쓰레드
 * 
 * class ThreadA extends Thread {
 * 
 * ​
 * 
 * private WorkObject workObj;
 * 
 * ​
 * 
 * public ThreadA(WorkObject workObj) {
 * 
 * this.workObj = workObj;
 * 
 * }
 * 
 * ​
 * 
 * @Override
 * 
 * public void run() {
 * 
 * for (int i = 0; i < 10; i++) {
 * 
 * workObj.methodA();
 * 
 * }
 * 
 * }
 * 
 * }
 * 
 * ​
 * 
 * class ThreadB extends Thread {
 * 
 * ​
 * 
 * private WorkObject workObj;
 * 
 * ​
 * 
 * public ThreadB(WorkObject workObj) {
 * 
 * this.workObj = workObj;
 * 
 * }
 * 
 * ​
 * 
 * @Override
 * 
 * public void run() {
 * 
 * for (int i = 0; i < 10; i++) {
 * 
 * workObj.methodB();
 * 
 * }
 * 
 * }
 * 
 * }
 * 
 * ​
 */
