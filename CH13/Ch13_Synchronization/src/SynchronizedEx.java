// 동기화를 사용하지 않으면 결과값이 200이 아닐 수 있다. 컴퓨터가 어떤 CPU를 사용하는지 우린 알수 없다
public class SynchronizedEx {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SharedBoard board = new SharedBoard();
		Thread th1 = new StudentThread("Jeno", board);
		Thread th2 = new StudentThread("Mark", board);
		th1.start();
		th2.start();
	}

}

class SharedBoard {
	private int sum = 0; // 집계의 판
	synchronized public void add() { // 동기화 synchronized
		int n = sum;
		Thread.yield(); // 현재 실행 중인 스레드 양보
		n += 10;
		sum = n; // 증가한 값을 집계합에 기록
		System.out.println(Thread.currentThread().getName() + " : " + sum);
	}
	public int getSum( ) { return sum; }
}

class StudentThread extends Thread {
	private SharedBoard board; // 집계판의 주소
	public StudentThread(String name, SharedBoard board) {
		super(name);
		this.board = board;
	}
	
	public void run() {
		for (int i=0; i<10; i++) {
			board.add();
		}
	}
}