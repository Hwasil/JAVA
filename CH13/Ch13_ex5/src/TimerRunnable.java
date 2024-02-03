import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class TimerThread implements Runnable {
	private JLabel timerLabel;
	private static int n = 0;
	
	public TimerThread (JLabel timerLabel) {
		this.timerLabel = timerLabel;  // 타이머 카운트를 출력할 레이블
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//int n=0; // 타이머 카운트 값
		while (true) { 
			timerLabel.setText(Integer.toString(n)); // 레이블에 카운트 값 출력
			n++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return; //예외 발생 시 스레드 종료
			}
		}
	}
}

public class TimerRunnable extends JFrame {
	private Thread th;
	private JButton killBtn, startBtn;
	
	public TimerRunnable() {
		setTitle("Runnable 타이머 종료");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		// 타이머 출력 레이블
		JLabel timerLabel = new JLabel();
		timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));
		
		// 타이머 스레드 객체 생성. 타이머 값을 출력할 레이블을 생성자에게 전달
		Thread th = new Thread(new TimerThread(timerLabel)); // 스레드 생성
		c.add(timerLabel);
		
		killBtn = new JButton("Kill Timer");
		killBtn.setEnabled(false);
		killBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				th.interrupt(); // 타이머 강제 종료 
				JButton killBtn = (JButton)e.getSource();
				killBtn.setEnabled(false); // 버튼 비활성화
				startBtn.setEnabled(true);
			}
			
		});
		
		startBtn = new JButton("Start Timer");
		startBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TimerThread runnable = new TimerThread(timerLabel);
				th = new Thread(runnable); // 스레드 생성
				th.start(); // 스레드 실행 시작
				JButton startBtn = (JButton)e.getSource();
				startBtn.setEnabled(false); // 버튼 비활성화
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
