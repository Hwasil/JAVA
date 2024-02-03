import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImageLabel extends JFrame {
	
	ImageIcon icons[] = new ImageIcon[5];      
	JLabel [] imageLabels = new JLabel[5];     // ���̺� ��ü�� ����Ű�� ���۷��� 5�� ����
	int currentImage = 1;                      
	// 1) ��� : Container c;
	
	public void makeIcons() {
		for (int i=0; i<icons.length; i++) {
			icons[i] = new ImageIcon("image/" + Integer.toString(i+1) + ".jpg");
		} // ���� �̸��� 1-5�� ����!!
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
		
		c.add(new Control(c), BorderLayout.SOUTH); // 2) ���
		c.add(imageLabels[0], BorderLayout.CENTER);
		
		setSize(300, 800); //(300, 500)
		setVisible(true);
	}
	
	class Control extends JPanel { // InnerClass
		JButton leftB, rightB;
		
		public Control(Container c) {
			this.setLayout(new GridLayout(1, 2));
			ImageIcon left = new ImageIcon("image/Left.jpg");  // ��ư ���� ����!!!!
			ImageIcon right = new ImageIcon("image/Right.jpg");
			
			leftB = new JButton(left); 
			rightB = new JButton(right);
			
			leftB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					/*
					 * if (currentImage > 1) { // ���׾� ���� ���۷��� �������� 2���� 1) ���� Ŭ������ ���� Ŭ������ ��� ���� ����
					 * 2) ������ �� c�� �ش� c.remove(imageLabels[currentImage-1]); // �տ� �����ϴ� ������ ����
					 * currentImage--; // c.add(imageLabels[currentImage-1]); // ���� ������ �߰�
					 * //rightB.setEnabled(true); c.revalidate(); c.repaint(); }
					 */
					
					c.remove(imageLabels[currentImage-1]); // �տ� �����ϴ� ������ ���� 
					if (currentImage == 1) {
						//leftB.setEnabled(false);               // button delete
						currentImage = imageLabels.length;
						
					} else {
						currentImage--;
					} 
					c.add(imageLabels[currentImage-1]);    // ���� ������ �߰�
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
					 * c.remove(imageLabels[currentImage-1]); // �տ� �����ϴ� ������ ���� currentImage++; //
					 * c.add(imageLabels[currentImage-1]); // ���� ������ �߰� //leftB.setEnabled(true);
					 * c.revalidate(); c.repaint(); }
					 */
					
					c.remove(imageLabels[currentImage-1]); // �տ� �����ϴ� ������ ���� 
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
			
			leftB.setEnabled(false); // �ѹ��� ���� -> ������ �ȿ� �ڵ�� ������ ����
			this.add(leftB);
			this.add(rightB);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ImageLabel();
	}
}
