package basic;

//20.02.13 -06 -- 과제 

import java.util.*;

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
public class ThreadTest12 {
	public static void main(String[] args) {
		Horse[] ho = new Horse[] {

		new Horse("구렁이"), new Horse("보아뱀"), new Horse("방울뱀")

		};

		for (Horse i : ho) {
			i.start();
		}

		PrintHorse zz = new PrintHorse(ho);
		zz.start();

		for (int i = 0; i < ho.length; i++) {
			try {
				ho[i].join();
			} catch (InterruptedException e) {
			}
		}

		// 경기가 끝나면
		System.out.println();
		System.out.println("=== 경기결과 ===");
		System.out.println("순위 >> " + PrintHorse.strRank);
	}

}

class PrintHorse extends Thread {

	Horse[] aa = new Horse[] {};

	public static String strRank = ""; // 경기를 마친 스레드의 name값이 누적될 변수

	private String name; // 경기참여자 이름이 저장될 변수

	// 생성자
	public PrintHorse(Horse[] ho) {
	}

	@Override
	public void run() {

		while (true) {
			ArrayList<String> race = new ArrayList<>();

			for (int z = 0; z < aa.length; z++) {
				for (int i = 0; i < 50; i++) {
					race.add("-");
					try {
						sleep((int) (Math.random() * 300));
					} catch (InterruptedException e) {
					}
				}
				// 구렁이불러오기

				race.set(aa[z].getLocate() - 1, ">");

				System.out.println(aa[z].name + " : " + race.toString());
				strRank += name + "  ";
			}
		}
	}
}

class Horse extends Thread implements Comparable<Horse> {
	// 등수를 오름차순으로 처리 할 수 있는 내부정렬 기준

	String name;
	private int rank;
	private int locate;

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocate() {
		return locate;
	}

	public void setLocate(int locate) {
		this.locate = locate;
	}

	public Horse(String name) {
		this.name = name;
		// 새로운 값을 새롭게 준다.
	}

	@Override
	public void run() {
		int cnt = 1;
		while (true) {
			locate += cnt;

			try {
				sleep(1000 * (int) (Math.random() * 4 + 1));
			} catch (InterruptedException e) {
			}

			if (locate == 50) {
				break;
			}
		}

	}

	@Override
	public int compareTo(Horse o) {
		return 0;
	}

}
