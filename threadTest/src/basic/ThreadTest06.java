package basic;

import javax.swing.JOptionPane;

//20.02.12-06

public class ThreadTest06 {

	public static void main(String[] args) {
		Thread th1 = new DataInput();
		Thread th2 = new CountDown();

		th1.start();
		th2.start();

	}
}

// 데이터 입력을 담당하는 스레드
class DataInput extends Thread {

	// 입력이 완료되면 true값이 저장되어 입력이 완료되었는지 여부를 나타내는 변수
	public static boolean inputChk;

	@Override
	public void run() {
		String str;

		do {
			str = JOptionPane.showInputDialog("아무거나 입력");
		} while (str == null);

		inputChk = true;// 입력이 완료되어 값을 true로 변경한다.
		System.out.println("입력값 >> " + str);

	}

}

// 카운트 다운을 담당하는 스레드
class CountDown extends Thread {

	@Override
	public void run() {
		for (int i = 10; i >= 1; i--) {

			// 입력완료여부검사
			if (DataInput.inputChk == true) {
				return;
			}

			System.out.println(i);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}

		System.out.println("10초가 지났습니다.!! 프로그램 종료");

		// 프로그램 종료하는 메소드
		System.exit(0);

	}

}
