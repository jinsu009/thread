package basic;

import javax.swing.JOptionPane;

//20.02.13-01
//가위바위보 게임 선생님

//여기도 다른 하나의 스레드로 본다
public class Game_T {

	public static boolean inputChk;

	public static void main(String[] args) {
		GameTimer gt = new GameTimer();

		// 난수를 이용해서 컴퓨터의 가위, 바위, 보를 정한다.
		String[] data = { "가위", "바위", "보" };
		// 배열을 생성해준다. 가위부터 0,1,2

		int index = (int) (Math.random() * 3);
		// 0부터 2사이의 난수 만들기

		String com = data[index];

		// 사용자의 가위,바위,보 정하기
		String user = null;

		// 가위 바위 보를 입력 받기 전에 카운트 다운을 시작한다.
		gt.start();
		do {
			user = JOptionPane.showInputDialog("가위 바위 보 를 입력하세요");
		} while (user == null || !user.equals("가위") && !user.equals("바위")
				&& !user.equals("보"));
		inputChk = true; // 입력이 완료되었다는 표시를 저장

		// 승패 판정하기
		String res = "";
		if (user.equals(com)) {
			res = "비겼습니다.";
		} else if ((user.equals("가위") && com.equals("보"))
				|| (user.equals("바위") && com.equals("가위"))
				|| (user.equals("보") && com.equals("바위"))) {
			res = "이겼습니다.";
		} else {
			res = "졌습니다.";
		}

		// 결과 출력하기
		System.out.println(" === 결과 ===");
		System.out.println(" 컴퓨터 : " + com);
		System.out.println(" 사용자 : " + user);
		System.out.println(" 결   과 : " + res);
	}
}

// 카운트다운하는 스레드
class GameTimer extends Thread {
	@Override
	public void run() {
		for (int i = 5; i >= 1; i--) {
			if (Game_T.inputChk == true) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

		}

		System.out.println("입력시간이 초과되어 당신이 졌습니다.");
		System.exit(0);
	}
}