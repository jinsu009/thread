package basic;

//20.02.14--03

/*
 원주율을 계산하는 쓰레드와 계산된 원주율을 출력하는 스레드가 있다.

 원주율을 관리하는 객체를 생성해서 두 쓰레드에서 공통으로 사용한다.   

 */
public class ThreadTest15 {

	public static void main(String[] args) {
		ShareData sd = new ShareData();
		// 공통으로 사용할 객체 생성

		CalcPIThread cal = new CalcPIThread(sd);

		PrintThread prn = new PrintThread(sd);

		cal.start();
		prn.start();
	}
}

// 원주율을 게산하는 스레드
class CalcPIThread extends Thread {
	// 공유하는 부분설정
	private ShareData sd;

	public CalcPIThread(ShareData sd) {
		this.sd = sd;
	}

	@Override
	public void run() {
		/*
		 * 원주율 = (1/1 - 1/3 + 1/5 - 1/7 +1/9 ...) *4
		 */
		double sum = 0.0;

		for (int i = 1; i <= 1000000000; i += 2) {
			// 방법1
			/*
			 * //i = 1-1, (-)3-2, 5-3, (-)7-4, 9-5, (-)11-6 int j = 1;
			 * if(j%2==1) { sum = 1.0/i; } else { i*=(-1); sum = 1.0/i; } j++;
			 */

			// 방법2
			/*
			 * // 1 3 5 7 9 11 .. //2로나눈 몫 0 1 2 3 4 5 .. //2로나눈몫을 2로나눈나머지 0 1 0
			 * 1 0 1 .. if((i/2)%2==0){ sum += 1.0/i; } else{ sum -= 1.0/i; }
			 */
			// 방법3 (4로 나눈나머지 )
			if (i % 4 == 1) {
				sum += 1.0 / i;
			} else {
				sum -= 1.0 / i;
			}

		}

		sd.result = sum * 4;

		sd.isOk = true;// 계산이 완료됨을 표시
	}

}

// 계산이 완료되면 계산된 원주율을 출력하는 스레드
class PrintThread extends Thread {
	// 공유하는 부분설정
	private ShareData sd;

	public PrintThread(ShareData sd) {
		this.sd = sd;
	}

	@Override
	public void run() {
		while (!sd.isOk) {
			Thread.yield();
			// continue;
			// continue를 할경우 하단에서 프린트된 값이 변경되서 못가져오니까
		}
		System.out.println();
		System.out.println("결과 : " + sd.result);
		System.out.println("PI : " + Math.PI);
	}

}

// 원주율을 관리하는 클래스 (공유될 클래스)
class ShareData {

	public double result; // 계산된 결과가 저장될 변수
	public volatile boolean isOk = false; // 계산이 완료되었는지를 나타내는 변수
	// volatile 을 기재해주면 continue를 붙여도 나온다.
	// volatile (교재 786p)
	// >> 해당변수가 있는 영역에 데이터를 직접 입출력 하기 때문에 변경된 내용을 바로 적용받을 수 있다.
}
