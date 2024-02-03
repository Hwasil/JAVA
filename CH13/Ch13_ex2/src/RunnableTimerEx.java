import java.awt.*;
import javax.swing.*;

class TimerThread implements Runnable {
	private JLabel timerLabel;
	private int minutes = 0;
	
	public TimerThread (JLabel timerLabel) {
		this.timerLabel = timerLabel;  // 타이머 카운트를 출력할 레이블
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int n=0; // 타이머 카운트 값
		while (true) { 
			timerLabel.setText(Integer.toString(n)); // 레이블에 카운트 값 출력
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
		setTitle("Runnable 타이머 스레드");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		// 타이머 출력 레이블
		JLabel timerLabel = new JLabel();
		timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));
		c.add(timerLabel);
		
		// 타이머 스레드 객체 생성. 타이머 값을 출력할 레이블을 생성자에게 전달
		Thread rt = new Thread(new TimerThread(timerLabel));
		
		setSize(300, 170);
		setVisible(true);
		
		rt.start(); // 스레드 실행 시작
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RunnableTimerEx();
	}

	

}
