package basic;

//20.02.13 -05

/*
 3개의 스레드가 각각 알파벳 A~Z까지를 출력하는데 출력을 끝낸 순서대로 결과 를 나태는 프로그램 작성 

 */
public class ThreadTest11 {

	public static void main(String[] args) {
		DisplayChar[] dsp = new DisplayChar[] {

		new DisplayChar("구렁이"), new DisplayChar("보아뱀"), new DisplayChar("방울뱀") };

		for (DisplayChar dis : dsp) {
			dis.start();
		}

		for (int i = 0; i < dsp.length; i++) {
			try {
				dsp[i].join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		// 경기가 끝나면
		System.out.println();
		System.out.println("=== 경기결과 ===");
		System.out.println("순위 >> " + DisplayChar.strRank);
	}
}

// 알파벳 A~Z까지 출력하는 스레드 클래스

class DisplayChar extends Thread {

	public static String strRank = ""; // 경기를 마친 스레드의 name값이 누적될 변수

	private String name; // 경기참여자 이름이 저장될 변수

	// 생성자
	public DisplayChar(String name) {
		this.name = name;
	}

	@Override
	public void run() {

		for (char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.println(name + " 의 출력문자 : " + ch);

			// 난수값을 사용해서 속도를 조절한다.
			try {
				// sleep의 값을 201에서부터 500사이의 난수로 한다.
				Thread.sleep((int) (Math.random() * 300 + 201));
			} catch (InterruptedException e) {
			}
		}
		System.out.println(name + " 의 출력이 끝났습니다.");

		strRank += name + "  ";
	}

}
