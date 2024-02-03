import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class InnerClassListener extends JFrame {
	public InnerClassListener() {
		setTitle("Action Event Listener Exmple");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		JButton btn = new JButton("Action");
		btn.addActionListener(new MyActionListener()); // Action �̺�Ʈ ������ �ޱ�
		c.add(btn);
		
		setSize(350, 150);
		setVisible(true);
	}
	
	// ���� Ŭ������ Action ������ �ۼ�
	private class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton b = (JButton)e.getSource();
			if(b.getText().equals("Action"))
				b.setText("�׼�");
			else
				b.setText("Action");
			
			// InnerClassListener�� ����� JFrame�� ����� ȣ���� �� ����
			//InnerClassListener.this.setTitle(b.getText()); // �������� Ÿ��Ʋ�� ��ư ���ڿ��� ���
			setTitle(b.getText()); // JFrame�� setTitle()ȣ��, �׳� setTitle(b.getText())�� ����
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new InnerClassListener();
	}

}
