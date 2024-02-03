import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ch10_7 extends JFrame {
	private JLabel la = new JLabel("HwaSil OH");

	public Ch10_7() {
		setTitle("마우스 휠을 굴려 폰트 크기 조절");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		la.addMouseWheelListener(new MouseWheelListener() { // 익명
			
			public void mouseWheelMoved(MouseWheelEvent e) {
				int n = e.getWheelRotation(); 
				// 해당 인수로 넘어온 패널 위에 마우스 휠을 밀거나 당기는 값에 따라 1(양수), -1(음수)값을 반환
				Font f = la.getFont();        // 폰트 객체 넘어옴
				int size = f.getSize();         // 사이즈 객체 넘어옴
				
				if (n < 0) { // 음수 == 밀다
					if (size-5 > 0) { 
						la.setFont(new Font("Arial", Font.PLAIN, size-5)); 
					}               
						
				} else { // 양수 == 당기다
					la.setFont(new Font("Arial", Font.PLAIN, size+5));
					//("글씨체", 기능, size)
					// 글씨체 : "Times", "Courier", "Helvetica" "Arial" 등
					// 기능 : BOLD(두껍게), ITALIC(기울여서), PLAIN(평범하게)
				}
			}
		});
		
		c.add(la);
		setSize(400, 300);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Ch10_7();
	}

}
