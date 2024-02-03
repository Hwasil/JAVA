import java.awt.*;
import javax.swing.*;

// 메인, 이벤트 분배, 타이머 총 세 개의 스레드가 생성됨

class TimerThread extends Thread { // 타이머 값이 출력되는 레이블
	private JLabel timerLabel;
	private int minutes = 0;
	/*
	 * private int sceonds = 0; private int period = 0;
	 */
	
	public TimerThread (JLabel timerLabel) {
		this.timerLabel = timerLabel;  // 타이머 카운트를 출력할 레이블
		//this.period = period; // 주기를 받아옴
	}
	
	// 스레드 코드. run()이 종료하면 스레드도 종료
	public void run() {
		int n=0; // 타이머 카운트 값
		while (true) { 
			/*
			 * minutes = n / 60; sceonds = n % 60;
			 * 
			 * String min = "", sce = ""; if (this.minutes < 10 ) min = "0"; if
			 * (this.sceonds < 10 ) sce = "0"; String time = min + Integer.toString(minutes)
			 * + ":" + sce + Integer.toString(sceonds);
			 */
			
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

public class ThreadTimerEx extends JFrame {
	public ThreadTimerEx() {
		setTitle("Thread 상속 받은 타이머 스레드");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		// 타이머 출력 레이블
		JLabel timerLabel = new JLabel();
		timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));
		c.add(timerLabel);
		
		// 타이머 스레드 객체 생성. 타이머 값을 출력할 레이블을 생성자에게 전달
		TimerThread th = new TimerThread(timerLabel);
		
		setSize(300, 170);
		setVisible(true);
		
		th.start(); // 스레드 실행 시작
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ThreadTimerEx();
	}

}
