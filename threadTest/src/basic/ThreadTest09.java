package basic;

//20.02.13 -03


//데몬스레드 연습 --> 자동저장기능 구현하기 
public class ThreadTest09 {
	
	public static void main(String[] args){
		
		AutoSave auto = new AutoSave();
			
		//데몬스레드로 설정( start()메소드를 호출하기전에 설정해야한다...)
		auto.setDaemon(true);
		auto.start();
		
		try {
			for(int i = 0; i<=20; i++)
			{
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {}
		
		System.out.println("메인스레드 종료!");
	}
	

}


//자동저장하는 스레드 ==> 3초에 한번씩 자동저장하는 스레드 

class AutoSave extends Thread{
	
	public void save(){
		//저장기능 수행하는 메소드 
		System.out.println("작업 내용을 저장합니다 @^@");
		
	}
	
	@Override
	public void run() {
		while(true)
		{
			try {
				Thread.sleep(3*1000);
			} catch (InterruptedException e) {
				break;
			}
			
			save();
			//저장하는 메소드 호출
			//3초쉬엇다가 호출을 반복 
			
		}
		
	
	}
	
}
