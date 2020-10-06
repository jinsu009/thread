package basic;

//20.02.12-07

import java.util.*;

import javax.swing.JOptionPane;

/*
 컴퓨터와 가위바위보를 진행하는 프로그램을 작성하시오 

 컴퓨터의 가위 바위 보는 난수를 이용해서 구하고, 
 사용자의 입력 inputDialog() 메서드를 이요해서 입력받는다.(가위 바위 보)

 입력시간은 5초로 제한하고 카운트 다운을 한다. 
 5초안에 입력이 없으면 게임에서 진것으로 처리 

 5초안에 입력이 있을경우 승패를 구해서 다음과 같이 출력 

 결과 )) 
 -- 결과 --
 컴퓨터 : 가위(바위/보)
 사용자 : 바위(가위/보)
 결   과 : 사용자가 이겼습니다.(사용자가 졌습니다./비겼습니다.)

 */
public class ThreadTest07 {

	public static void main(String[] args) {

		Thread th1 = new DataInput2();
		Thread th2 = new CountDown2();

		th1.start();
		th2.start();

	}

}

// 데이터 입력을 담당하는 스레드
class DataInput2 extends Thread {

	// 입력이 완료되면 true값이 저장되어 입력이 완료되었는지 여부를 나타내는 변수
	public static boolean inputChk;

	@Override
	public void run() {
		String str;
		int com;

		com = (int) (Math.random() * 3) + 1;
		/*
		 * 가위 = 1 바위=2 보=3
		 */

		do {
			str = JOptionPane.showInputDialog("'가위','바위','보' 를 입력하세요");
		} while (!(str.equals("가위") || str.equals("바위") || str.equals("보")));

		inputChk = true;// 입력이 완료되어 값을 true로 변경한다.

		System.out.println("--- 결과 ---");

		System.out.println("사용자 >>" + str);
		if (com == 1) {
			System.out.println("컴퓨터 >> 가위 ");
		}
		if (com == 2) {
			System.out.println("컴퓨터 >> 바위");
		}
		if (com == 3) {
			System.out.println("컴퓨터 >> 보");
		}

		if ((com == 1 && str.equals("가위")) || (com == 2 && str.equals("바위"))
				|| (com == 3 && str.equals("보"))) {
			System.out.println("결과 : 무승부입니다.");

		}
		if ((com == 1 && str.equals("바위")) || (com == 2 && str.equals("보"))
				|| (com == 3 && str.equals("가위"))) {
			System.out.println("결과 : 사용자가 이겼습니다");
		}
		if ((com == 1 && str.equals("보")) || (com == 2 && str.equals("가위"))
				|| (com == 3 && str.equals("바위"))) {
			System.out.println("결과 : 사용자가 졌습니다.");
		}

	}

}

// 카운트 다운을 담당하는 스레드
class CountDown2 extends Thread {

	@Override
	public void run() {
		for (int i = 5; i >= 1; i--) {

			// 입력완료여부검사
			if (DataInput2.inputChk == true) {
				return;
			}

			System.out.println(i);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}

		System.out.println("입력시간이 초과되었습니다.");

		// 프로그램 종료하는 메소드
		System.exit(0);

	}

}
