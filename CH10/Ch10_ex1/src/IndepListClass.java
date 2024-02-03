import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class IndepListClass extends JFrame{
	public IndepListClass() {
		setTitle("Action Event Listener Exmple");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		JButton btn = new JButton("Action");
		btn.addActionListener(new MyActionListener()); // Action �̹�Ʈ ������ �ޱ�
		c.add(btn);
		
		setSize(350, 150);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new IndepListClass();
	}

}

// ������ Ŭ������ �̺�Ʈ ������ �ۼ�
class MyActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton)e.getSource(); // �̺�Ʈ �ҽ� ��ư �˾Ƴ��� (p.541 ĳ����)
		if(b.getText().equals("Action"))
			b.setText("�׼�");
		else
			b.setText("Action");
	}
	
}