import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ch10_7 extends JFrame {
	private JLabel la = new JLabel("HwaSil OH");

	public Ch10_7() {
		setTitle("���콺 ���� ���� ��Ʈ ũ�� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		la.addMouseWheelListener(new MouseWheelListener() { // �͸�
			
			public void mouseWheelMoved(MouseWheelEvent e) {
				int n = e.getWheelRotation(); 
				// �ش� �μ��� �Ѿ�� �г� ���� ���콺 ���� �аų� ���� ���� ���� 1(���), -1(����)���� ��ȯ
				Font f = la.getFont();        // ��Ʈ ��ü �Ѿ��
				int size = f.getSize();         // ������ ��ü �Ѿ��
				
				if (n < 0) { // ���� == �д�
					if (size-5 > 0) { 
						la.setFont(new Font("Arial", Font.PLAIN, size-5)); 
					}               
						
				} else { // ��� == ����
					la.setFont(new Font("Arial", Font.PLAIN, size+5));
					//("�۾�ü", ���, size)
					// �۾�ü : "Times", "Courier", "Helvetica" "Arial" ��
					// ��� : BOLD(�β���), ITALIC(��￩��), PLAIN(����ϰ�)
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
