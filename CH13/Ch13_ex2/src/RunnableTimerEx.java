import java.awt.*;
import javax.swing.*;

class TimerThread implements Runnable {
	private JLabel timerLabel;
	private int minutes = 0;
	
	public TimerThread (JLabel timerLabel) {
		this.timerLabel = timerLabel;  // Ÿ�̸� ī��Ʈ�� ����� ���̺�
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int n=0; // Ÿ�̸� ī��Ʈ ��
		while (true) { 
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

public class RunnableTimerEx extends JFrame {
	
	public RunnableTimerEx() {
		setTitle("Runnable Ÿ�̸� ������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		// Ÿ�̸� ��� ���̺�
		JLabel timerLabel = new JLabel();
		timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));
		c.add(timerLabel);
		
		// Ÿ�̸� ������ ��ü ����. Ÿ�̸� ���� ����� ���̺��� �����ڿ��� ����
		Thread rt = new Thread(new TimerThread(timerLabel));
		
		setSize(300, 170);
		setVisible(true);
		
		rt.start(); // ������ ���� ����
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RunnableTimerEx();
	}

	

}
