import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImageLabel extends JFrame {
	
	ImageIcon icons[] = new ImageIcon[5];      
	JLabel [] imageLabels = new JLabel[5];     // 레이블 객체를 가르키는 레퍼런스 5개 생성
	int currentImage = 1;                      
	// 1) 방법 : Container c;
	
	public void makeIcons() {
		for (int i=0; i<icons.length; i++) {
			icons[i] = new ImageIcon("image/" + Integer.toString(i+1) + ".jpg");
		} // 사진 이름을 1-5로 변경!!
	}
	
	public void makeLabels() {
		for (int i=0; i < imageLabels.length; i++) {
			imageLabels[i] = new JLabel(icons[i]);
		}
	}
	
	public ImageLabel() {
		setTitle("Open Challenge 11");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		this.makeIcons();
		this.makeLabels();
		
		c.add(new Control(c), BorderLayout.SOUTH); // 2) 방법
		c.add(imageLabels[0], BorderLayout.CENTER);
		
		setSize(300, 800); //(300, 500)
		setVisible(true);
	}
	
	class Control extends JPanel { // InnerClass
		JButton leftB, rightB;
		
		public Control(Container c) {
			this.setLayout(new GridLayout(1, 2));
			ImageIcon left = new ImageIcon("image/Left.jpg");  // 버튼 조작 실패!!!!
			ImageIcon right = new ImageIcon("image/Right.jpg");
			
			leftB = new JButton(left); 
			rightB = new JButton(right);
			
			leftB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					/*
					 * if (currentImage > 1) { // 컨테언에 대한 레퍼런스 가져오기 2가지 1) 내부 클래스라 앞의 클래스에 멤버 변수 선언
					 * 2) 생성할 때 c를 준다 c.remove(imageLabels[currentImage-1]); // 앞에 존재하는 사진을 삭제
					 * currentImage--; // c.add(imageLabels[currentImage-1]); // 다음 사진을 추가
					 * //rightB.setEnabled(true); c.revalidate(); c.repaint(); }
					 */
					
					c.remove(imageLabels[currentImage-1]); // 앞에 존재하는 사진을 삭제 
					if (currentImage == 1) {
						//leftB.setEnabled(false);               // button delete
						currentImage = imageLabels.length;
						
					} else {
						currentImage--;
					} 
					c.add(imageLabels[currentImage-1]);    // 다음 사진을 추가
					//rightB.setEnabled(true);
					c.revalidate();
			        c.repaint();
				}
				
			});
			
			rightB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					/*
					 * if (currentImage < imageLabels.length) {
					 * c.remove(imageLabels[currentImage-1]); // 앞에 존재하는 사진을 삭제 currentImage++; //
					 * c.add(imageLabels[currentImage-1]); // 다음 사진을 추가 //leftB.setEnabled(true);
					 * c.revalidate(); c.repaint(); }
					 */
					
					c.remove(imageLabels[currentImage-1]); // 앞에 존재하는 사진을 삭제 
					if (currentImage == imageLabels.length) {
						//rightB.setEnabled(false);              // button delete
						currentImage = 1;
					} else {
						currentImage++;
					}
					 c.add(imageLabels[currentImage-1]);
					 c.revalidate();
				     c.repaint();
				}
			});
			
			leftB.setEnabled(false); // 한번만 실행 -> 리스너 안에 코드는 여러번 실행
			this.add(leftB);
			this.add(rightB);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ImageLabel();
	}
}
