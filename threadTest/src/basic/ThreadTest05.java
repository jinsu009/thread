package basic;

import javax.swing.JOptionPane;

//20.02.12-05
public class ThreadTest05 {

	public static void main(String[] args) {

		String str = JOptionPane.showInputDialog("아무거나 입력하세요");
		System.out.println("입력값>> " + str);
		// 취소버튼 눌렀을땐 null값이 나온다.

		// 카운트 다운하는 반복문
		for (int i = 10; i >= 1; i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}

	}

}
