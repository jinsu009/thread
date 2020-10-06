package basic;

//20.02.14--02

//stop()메소드  교재 755p
/*
 Thread의 stop()메소드를 호출하면 스레드가 바로 멈춘다. 
 이 때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어 나중에 실행되는 프로그램에 영향을 줄수 있다. 
 그래서 stop()메소드는 비추천되어있다.

 */
public class ThreadTest14 {

	public static void main(String[] args) {
		/*
		 * ThreadStopEx1 th1 = new ThreadStopEx1();
		 * 
		 * th1.start();
		 * 
		 * try { Thread.sleep(1000); } catch (InterruptedException e) { // TODO:
		 * handle exception }
		 * 
		 * //th1.stop(); //stop밑줄 : stop를 사용할수는 있지만 이클립스에서 일어나는 문제는 자기들이 책임을 지지
		 * 않겠다는 표시 // 출력문 _00이 출력되지 않는다.
		 * 
		 * //더 안전한 방법! th1.setStop(true);
		 */

		// interrupt()메서드를 이용한 스레드 멈추기
		ThreadStopEx2 th2 = new ThreadStopEx2();

		th2.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		// *****
		th2.interrupt();
	}

}

class ThreadStopEx1 extends Thread {

	private boolean stop;

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	@Override
	public void run() {
		while (!stop) {
			System.out.println("** 스레드 실행중! **");
		}

		// 출력문_00
		System.out.println("** 자원 정리 **");
		System.out.println("** 실행 종료 **");

	}

}

// interrupt() 메소드를 이용하여 스레드를 멈추게 하는 방법 --교재 752p
class ThreadStopEx2 extends Thread {

	@Override
	public void run() {
		// 방법1
		/*
		 * while(true){ System.out.println("실행중");
		 * 
		 * try { Thread.sleep(1); //sleep()에 의해 일시정지 상태에서 interrupt()메서드가 실행되면
		 * //InterruptedException이 발생된다. } catch (InterruptedException e) {
		 * break; } }
		 */

		// 방법2
		while (true) {

			System.out.println("실행중");

			// interrupt()메서드가 호출되었는지를 검사

			/*
			 * // 검사방법1 // ==> 인스턴스 객체용 메소드 isInterrupted()를 이용하여 검사한다.
			 * if(this.isInterrupted()){ //this.isInterrupted() == true break; }
			 */

			// 검사방법2
			// ==> thread의 정적 메서드 interrupted()를 이용하여 검사한다.
			if (Thread.interrupted()) {
				break;
			}

		}
		System.out.println("** 자원 정리 **");
		System.out.println("** 실행 종료 **");

	}

}