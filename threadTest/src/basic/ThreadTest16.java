package basic;

//20.02.14--04

public class ThreadTest16 {
	public static void main(String[] args) {
		// 공통으로 사용할 객체 생성
		ShareObject sObj = new ShareObject();

		WorkerThread work = new WorkerThread("test1", sObj);
		WorkerThread work2 = new WorkerThread("test2", sObj);

		work.start();
		work2.start();
	}
}

// 작업용 스레드
class WorkerThread extends Thread {

	private ShareObject sObj;

	public WorkerThread(String name, ShareObject sObj) {
		super(name); // 스레드 이름 설정
		this.sObj = sObj;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			sObj.add();
			// ShareObject에서 add라는 메소드를 불러들임
		}
	}
}

class ShareObject {

	private int sum = 0;

	// 동기화 처리 (교재 767p)
	// 방법1(메소드 자체에 동기화 설정학하기 ) public synchronized void add()
	// synchronized를 붙여주면 순서대로 나온다.

	public void add() {

		// 방법2(동기화 블럭으로 설정하기)
		synchronized (this) {
			int n = sum;
			n += 10;
			sum = n;
			// 명령어를 잘게 분할한것은 권한이 다른곳으로 넘어가는것을 확인하기 우ㅣ해???
			System.out.println(Thread.currentThread().getName() + " 합계  >> "
					+ sum);
		}
	}

	public int getSum() {

		return sum;
	}

}