package basic;

import java.util.*;

//20.02.17--03

public class ThreadTest19 {

	public static void main(String[] args) {
		/*
		 * vector, hashtable 등의 예전부터 존재하던 collection들은 내부에 동기화 처리가 되어있다. 그런데 새롭게
		 * 구성된 collection들은 기본적으로 동기화 처리가 되어 있지 않다. 그래서, 동기화가 필요한 프로그램에서
		 * 이런collection들을 사용하려면 동기화 처리를 한 후에 사용해야 한다.
		 */
		final Vector<Integer> vec = new Vector<>();
		// vector : 내부에 동기화처리가 자동으로 된다.
		final ArrayList<Integer> list = new ArrayList<>();
		// arralist : 동기화 처리가 되지 않는다.

		// 동기화 하는 경우
		// synchronized 는 상황에따라 set , list , hashmap 을 선택해서 사용할수 잇따.
		final List<Integer> list2 = Collections
				.synchronizedList(new ArrayList<Integer>());

		Runnable r = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					// vec.add(i);
					// list.add(i);
					list2.add(i);
				}
			}
		};

		Thread[] ths = new Thread[] { new Thread(r), new Thread(r),
				new Thread(r), new Thread(r), new Thread(r) };

		for (Thread th : ths) {
			th.start();
		}
		for (Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}

		System.out.println("vector의 갯수  >> " + vec.size());
		// System.out.println("list의 갯수  >> " +list.size());
		// list는 error가 발생..
		System.out.println("list2의 갯수  >> " + list2.size());

	}

}
