import java.awt.*;
import javax.swing.*;

// ����, �̺�Ʈ �й�, Ÿ�̸� �� �� ���� �����尡 ������

class TimerThread extends Thread { // Ÿ�̸� ���� ��µǴ� ���̺�
	private JLabel timerLabel;
	private int minutes = 0;
	/*
	 * private int sceonds = 0; private int period = 0;
	 */
	
	public TimerThread (JLabel timerLabel) {
		this.timerLabel = timerLabel;  // Ÿ�̸� ī��Ʈ�� ����� ���̺�
		//this.period = period; // �ֱ⸦ �޾ƿ�
	}
	
	// ������ �ڵ�. run()�� �����ϸ� �����嵵 ����
	public void run() {
		int n=0; // Ÿ�̸� ī��Ʈ ��
		while (true) { 
			/*
			 * minutes = n / 60; sceonds = n % 60;
			 * 
			 * String min = "", sce = ""; if (this.minutes < 10 ) min = "0"; if
			 * (this.sceonds < 10 ) sce = "0"; String time = min + Integer.toString(minutes)
			 * + ":" + sce + Integer.toString(sceonds);
			 */
			
			timerLabel.setText(Integer.toString(n)); // ���̺� ī��Ʈ �� ���
			n++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}

public class ThreadTimerEx extends JFrame {
	public ThreadTimerEx() {
		setTitle("Thread ��� ���� Ÿ�̸� ������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		// Ÿ�̸� ��� ���̺�
		JLabel timerLabel = new JLabel();
		timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));
		c.add(timerLabel);
		
		// Ÿ�̸� ������ ��ü ����. Ÿ�̸� ���� ����� ���̺��� �����ڿ��� ����
		TimerThread th = new TimerThread(timerLabel);
		
		setSize(300, 170);
		setVisible(true);
		
		th.start(); // ������ ���� ����
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ThreadTimerEx();
	}

}
