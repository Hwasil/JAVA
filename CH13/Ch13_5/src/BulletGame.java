import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// term project를 위해... 코드 확실히 이해하기
// 키리스너 p.559-

class ChickenThread extends Thread { // 이미지를 동작시키는 것, 동작의 단위
	private JComponent target;
	private GamePanel panel;
	
	public ChickenThread(GamePanel panel, JComponent target) { // 생성자. 정보를 가져옴, 패널 안에 컴포넌트 있어서 패널만 가져와도 된다. 
		this.panel = panel;
		this.target = target;
		this.target.setLocation(0, 0);
		this.target.getParent().repaint(); // 부모 호출하여 다시 그리기
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			int newX = this.target.getX() + 5; // new Location
			int newY = this.target.getY();
			
			if (newX < this.panel.getWidth() )  // x의 값이 최대값(패널의 끝) 보단 작은지 확인
				this.target.setLocation(newX, newY);
			else
				this.target.setLocation(0, 0);
			this.target.getParent().repaint();  // 다시 그리는 코드
			//this.panel.repaint();
			
			try {
				sleep(20); 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//this.target.setLocation(0, 0);
				//this.target.getParent().repaint();
			}
		}
	}
}

class BulletRunnable implements Runnable {
	private JLabel bullet = null;
	private JLabel target = null;
	private Point bulletPoint = null;
	private Thread ChickenThread;
	
	public BulletRunnable(JLabel bullet, JLabel target, Point bulletPoint, Thread ChickenThread) { // gamepanel의 레퍼런스만 줘도 가능하다
		this.bullet = bullet;
		this.target = target;
		this.bulletPoint = bulletPoint;
		this.ChickenThread = ChickenThread;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			if (hit() == true) { // 타켓 명중
				System.out.println("\nNice Shoot!");
				ChickenThread.interrupt();
				bullet.setLocation(bullet.getParent().getWidth()/2 - 5, bullet.getParent().getHeight()-50);						
				return;
			} else { 
				int newX = this.bullet.getX(); 
				int newY = this.bullet.getY() - 5;
				
				if (newY + this.bullet.getHeight() > 0 )  // 
					this.bullet.setLocation(newX, newY);
				else {
					this.bullet.setLocation(this.bulletPoint);
					return; // or break;
				}
				this.bullet.getParent().repaint();
				
				try {
					Thread.sleep(20); 
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					this.bullet.setLocation(this.bulletPoint);
					this.bullet.repaint();
			}
		}
	}	
}
	public boolean hit() {
		if(isIncluded(bullet.getX(), bullet.getY()) || 
				isIncluded(bullet.getX() + bullet.getWidth() - 1, bullet.getY()) ||
				isIncluded(bullet.getX() + bullet.getWidth() - 1, bullet.getY() + bullet.getHeight() - 1) ||
				isIncluded(bullet.getX(), bullet.getY()+bullet.getHeight() - 1))
			return true;
		else
			return false;
		}

	private boolean isIncluded(int x, int y) { // bullet이 target size 안에 들어갔나 안 들어갔나
		 if(( (target.getX() <= x) && (target.getX() + target.getWidth() - 1 >= x)) && 
				 ( (target.getY() <= y) && (target.getY() + target.getHeight() - 1 >= y)) ) {
			return true; 
		} else 
			return false;
		}
}



class GamePanel extends JPanel {
	private JLabel targetL = null; // 치킨 레이블과 타겟 스레드
	private ChickenThread targetThread = null;
	private Thread bulletThread = null;
	private Thread ChickenThread;
	
	private JLabel baseL = new JLabel();
	private JLabel bulletL = new JLabel();
	private int baseWidth = 80;
	private int baseHeight = 60;
	private int bulletWidth = 30;
	private int bulletHeight = 30;
	private Point basePoint = null;
	private Point bulletPoint = null;
	
	public GamePanel() {
		setLayout(null); // 절대적 위치로 설정
		
		ImageIcon img = new ImageIcon("Chicken.jpg");
		targetL = new JLabel(img);
		targetL.setSize(img.getIconWidth()-80, img.getIconHeight()-80); // 크기 조절하면 크기만큼 잘린다, 절대적 
		
		baseL.setOpaque(true);
		baseL.setSize(baseWidth, baseHeight);
		baseL.setBackground(Color.BLACK);
		
		bulletL.setOpaque(true);
		bulletL.setSize(bulletWidth, bulletHeight);
		bulletL.setBackground(Color.RED);
		
		// Enter 키를 누르면 발사
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_ENTER) {
					System.out.print("\nEnter pressed");
					
					if (bulletThread == null || bulletThread.isAlive() == false ) { 
						bulletThread = new Thread(new BulletRunnable(bulletL, targetL, bulletPoint, ChickenThread));
						bulletThread.start();
					}
				}
			}
		});
		
		add(targetL);
		add(baseL); 
		add(bulletL); 
	}
	
	public void startGame() { 
		System.out.print(this.getWidth()); //486463
		System.out.print(this.getHeight());
		
		basePoint = new Point(getWidth() / 2 - baseWidth / 2, getHeight() - baseHeight);
		bulletPoint = new Point(basePoint.x + baseWidth / 2 - bulletWidth / 2, getHeight() - baseHeight - bulletHeight);
		
		this.targetL.setLocation(0, 0);
		this.baseL.setLocation(basePoint);
		this.bulletL.setLocation(bulletPoint);
		
		this.targetThread = new ChickenThread(this, this.targetL);
		this.targetThread.start();
	}
}

public class BulletGame extends JFrame { 
	
	public BulletGame() {
		setTitle("My Shooting Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GamePanel p = new GamePanel(); // 패널을 독자적으로 만들어 컨텐트페인으로 사용
		setContentPane(p);
		 
		setSize(500, 500);
		setVisible(true);
		setResizable(false); // 게임 창 크기 조절 불가
		
		p.startGame(); // 게임 시작
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BulletGame();
	}

}
