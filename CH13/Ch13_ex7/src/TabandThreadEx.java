import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

	// 2022.11.10 목 집에서 완성해보기(사진 영상 참고 후 삭제)

class MyLabel extends JLabel {
	private int barSize = 0;
	private int maxBarSize;
	
	public MyLabel(int maxBarSize) {
		this.maxBarSize = maxBarSize;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.magenta);
		int width = (int) (((double)(this.getWidth()))/maxBarSize*barSize);
		if (width == 0) return;
		g.fillRect(0, 0, width, this.getHeight());
	}
	
	synchronized public void fill() {
		if (barSize == maxBarSize) {
			try {
				wait();
			} catch (InterruptedException e) { return; }
		}
		barSize++;
		repaint();
		notify();
	}
	synchronized public void consume() {
		if (barSize == 0) {
			try {
				wait();
			} catch (InterruptedException e) { return; }
		}
		barSize--;
		repaint();
		notify();
	}
}
	
class ConsumerThread extends Thread {
	private MyLabel bar;
	
	public ConsumerThread(MyLabel bar) {
		this.bar = bar;
	}
		
	public void run() {
		while(true) {
			try {
				sleep(200);
				bar.consume();
			} catch (InterruptedException e) { return; }
		}
	}
}

class ProducerThread extends Thread {
	private MaLabel bar;
}

public class TabandThreadEx extends JFrame {
	private MyLabel bar = new MyLabel(100);
	
	public TabandThreadEx(String title) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		bar.setBackground(Color.orange);
		bar.setOpaque(true);
		bar.setLocation(20, 50);
		bar.setSize(300, 20);
		c.add(bar);
		
		c.addKeyListener(new KeyAdapter() {
			public void KeyPressed(KeyEvent e) {
				bar.fill();
			}
		});
		setSize(350, 200);
		setVisible(true);
		
		c.setFocusable(true);
		c.requestFocus();
		
		ProducerThread thP = new ProducerThread(bar, c);
		thP.start();
		
		ConsumerThread thC = new ConsumerThread(bar); // 스레드 생성
		thC.start();                                  // 스레드 시작   
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TabandThreadEx("아무거나 빨리 눌러 바 채우기");
	}
}
