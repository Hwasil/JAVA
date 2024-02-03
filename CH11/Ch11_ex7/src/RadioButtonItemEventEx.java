import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RadioButtonItemEventEx extends JFrame {
	private JRadioButton [] radio = new JRadioButton [3]; //������ư �迭
	private String [] text = {"���", "��", "ü��"};         //������ư ���ڿ�
	private ImageIcon [] image = {  // �̹��� ��ü �迭
			new ImageIcon("images/Apple.png"), 
			new ImageIcon("images/pear.jpg"), 
			new ImageIcon("images/cherry.jpg")}; 
	private JLabel imageLabel = new JLabel(); //�̹��� ��� ���̺�
	
	public RadioButtonItemEventEx() {
		setTitle("RadioButton Item Event Ex");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel radioPanel = new JPanel(); // 3���� ���� ��ư ������ �г�
		radioPanel.setBackground(Color.darkGray);
		
		ButtonGroup g = new ButtonGroup();  // ��ư �׷� ��ü ����
		for (int i=0; i<radio.length; i++) { // 3���� ���� ��ư�� ����
			radio[i] = new JRadioButton(text[i]); // ������ư ����
			g.add(radio[i]);
			radioPanel.add(radio[i]);
			radio[i].addItemListener(new MyItemListener()); // ������ư Item ������ ���
		}
		
		radio[2].setSelected(true); // ü�� ��ư ���û��� ����
		c.add(radioPanel, BorderLayout.NORTH);
		c.add(imageLabel, BorderLayout.CENTER);
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		setSize(250, 200);
		setVisible(true);
	}
	
	// Item Listener
	class MyItemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if (e.getStateChange() == ItemEvent.DESELECTED) // ������ ��� �׳� ����
				return;
			if (radio[0].isSelected())      // Apple
				imageLabel.setIcon(image[0]);
			else if (radio[1].isSelected()) // Pear
				imageLabel.setIcon(image[1]);
			else                            // Cherry
				imageLabel.setIcon(image[2]);	
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RadioButtonItemEventEx();
	}

}
