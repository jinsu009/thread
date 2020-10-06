package basic;

//20.02.14--13일과제 

import java.util.Arrays;

import javax.xml.ws.Dispatch;

//20.02.14--01

/*
 10마리의 말들이 경주하는 경마 프로그램을 작성해봐 ..

 말은 horse라는 이름의 클래스로 구성
 이 클래스는 말이름 (String), 등수(int), 현재위치(int)를 멤버변수로 갖는다. 
 그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부정렬기준이 있다. 
 ( Comparable인터페이스 구현...)???

 경기구간은 1~50구간으로 되어있다. 
 (구간을 현재 출력하는 ...??? 1~50까지 반복문 .. )
 경기가 진행하는 동안 일정한 시간 마다 말들의 현재 위치를 출력 

 예) 
 말이름1 
 (1번말의 위치) ------>------------------------------------------- 

 말이름2 
 (2번말의 위치) ----------------------->-------------------------- 

 말이름3
 (3번말의 위치) ------------------------------>------------------- 

 ....
 말이름10
 (10번말의 위치) ------------------------------>------------------- 


 경기가 끝나면 등수순으로 출력


 */

public class ThreadTest12_T {

	public static void main(String[] args) {

		Horse00[] horses = new Horse00[] {

		new Horse00("1 번말"), new Horse00("2 번말"), new Horse00("3 번말"),
				new Horse00("4 번말"), new Horse00("5 번말")

		};

		PlayState display = new PlayState(horses);

		System.out.println("*** 경기 시작 ***");

		for (Horse00 h : horses) {
			h.start();
		}

		display.start();

		for (Horse00 h : horses) {
			// 경주가 끝날대까지 기다림
			try {
				h.join();
			} catch (InterruptedException e) {
			}
		}

		try {
			display.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}

		// 경기가 끝나면 배열을 등수순으로 정렬
		Arrays.sort(horses);

		System.out.println("경기 결과");
		for (Horse00 h : horses) {
			System.out.println(h);
		}

	}
}

// 경주마를 나타내는 클래스 --> 각 말들은 경주를 해야하기 때문에 이 클래스는 스레드로 만든다.
class Horse00 extends Thread implements Comparable<Horse00> {

	public static int currentRank = 0;
	// 경주가 끝난 말의 등수를 결정하기 위한 변수

	private String horseName; // 말이름
	private int rank; // 등수
	private int location; // 위치

	// 생성자 == 말이름
	public Horse00(String horseName) {
		super();
		this.horseName = horseName;
	}

	// getter setter
	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "경주마 " + horseName + " 는 " + rank + " 등 입니다.";
	}

	// 등수의 오름차순으로 정렬할수 있는 정렬기준 만들기
	@Override
	public int compareTo(Horse00 h) {
		return Integer.compare(rank, h.rank);
		// 내등수와 상대편의 등수를 비교 하여 바꿀지 안바꿀지를 결정
	}

	// comparable할려면 있어야한다.

	@Override
	public void run() {
		for (int i = 0; i <= 50; i++) {
			location = i; // 현재 달리고 있는 구간이 현재 말의 위치가 된다.

			try {
				Thread.sleep((int) (Math.random() * 400));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		++currentRank;
		// 등수를 결정하는 변수값 증가시키긱
		this.rank = currentRank;
	}
}

// 경기 중 현재 의 상황을 출력하는 스레드
class PlayState extends Thread {
	// 말들은 배열에 저장되어 있으니까
	private Horse00[] horses;

	// 경주에 참가한 말들을 저장할 배열

	public PlayState(Horse00[] horses) {
		this.horses = horses;
	}

	@Override
	public void run() {

		while (true) {

			if (Horse00.currentRank == horses.length)
				break;

			for (int i = 1; i <= 15; i++) {
				System.out.println();
			}

			// 각각의 말들의 위치를 출력해야하므로 말의 갯수만큼 반복해야한다.

			for (int i = 0; i < horses.length; i++) {
				System.out.print(horses[i].getHorseName() + " : ");

				// 1~50 구간을 나타내는 반복문
				for (int j = 1; j <= 50; j++) {
					// 현재말의 우치
					if (horses[i].getLocation() == j) {

						System.out.print(">");
					} else {
						System.out.print("-");
					}
				}// for j

				System.out.println();// 줄바꿈

			}// for i

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}// while
	}
}
