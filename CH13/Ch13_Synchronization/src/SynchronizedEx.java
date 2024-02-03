// ����ȭ�� ������� ������ ������� 200�� �ƴ� �� �ִ�. ��ǻ�Ͱ� � CPU�� ����ϴ��� �츰 �˼� ����
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
	private int sum = 0; // ������ ��
	synchronized public void add() { // ����ȭ synchronized
		int n = sum;
		Thread.yield(); // ���� ���� ���� ������ �纸
		n += 10;
		sum = n; // ������ ���� �����տ� ���
		System.out.println(Thread.currentThread().getName() + " : " + sum);
	}
	public int getSum( ) { return sum; }
}

class StudentThread extends Thread {
	private SharedBoard board; // �������� �ּ�
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