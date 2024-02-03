import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class TimerThread implements Runnable {
	private JLabel timerLabel;
	private static int n = 0;
	
	public TimerThread (JLabel timerLabel) {
		this.timerLabel = timerLabel;  // Ÿ�̸� ī��Ʈ�� ����� ���̺�
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//int n=0; // Ÿ�̸� ī��Ʈ ��
		while (true) { 
			timerLabel.setText(Integer.toString(n)); // ���̺� ī��Ʈ �� ���
			n++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return; //���� �߻� �� ������ ����
			}
		}
	}
}

public class TimerRunnable extends JFrame {
	private Thread th;
	private JButton killBtn, startBtn;
	
	public TimerRunnable() {
		setTitle("Runnable Ÿ�̸� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		// Ÿ�̸� ��� ���̺�
		JLabel timerLabel = new JLabel();
		timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));
		
		// Ÿ�̸� ������ ��ü ����. Ÿ�̸� ���� ����� ���̺��� �����ڿ��� ����
		Thread th = new Thread(new TimerThread(timerLabel)); // ������ ����
		c.add(timerLabel);
		
		killBtn = new JButton("Kill Timer");
		killBtn.setEnabled(false);
		killBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				th.interrupt(); // Ÿ�̸� ���� ���� 
				JButton killBtn = (JButton)e.getSource();
				killBtn.setEnabled(false); // ��ư ��Ȱ��ȭ
				startBtn.setEnabled(true);
			}
			
		});
		
		startBtn = new JButton("Start Timer");
		startBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TimerThread runnable = new TimerThread(timerLabel);
				th = new Thread(runnable); // ������ ����
				th.start(); // ������ ���� ����
				JButton startBtn = (JButton)e.getSource();
				startBtn.setEnabled(false); // ��ư ��Ȱ��ȭ
				killBtn.setEnabled(true);
			}
			
		});
		
		c.add(startBtn);
		c.add(killBtn);
		
		setSize(300, 170);
		setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TimerRunnable();
	}

}
