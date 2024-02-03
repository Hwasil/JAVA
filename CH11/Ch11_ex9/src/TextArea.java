import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextArea extends JFrame {
	private JTextField tfGivenname = new JTextField(20);
	private JTextField tfName = new JTextField(20);
	private JTextField tfPhone = new JTextField(20);
	private JTextArea ta = new JTextArea(7, 20);
	
	 public TextArea() {
		setTitle("TextField & TextArea Ex");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel MyPanel = new MyPanel();
		MyPanel.setBackground(Color.cyan);
		
		makeListeners();
		
		c.add(MyPanel, BorderLayout.NORTH);
		c.add(ta, BorderLayout.CENTER);
		
		setSize(400, 300);
		setVisible(true);
	 }
	 
	 class MyPanel extends JPanel {
		 public MyPanel() {
			 setLayout(new GridLayout(3, 2));
			 this.add(new JLabel("��"));
			 this.add(tfGivenname);
			 this.add(new JLabel("�̸�"));
			 this.add(tfName);
			 this.add(new JLabel("��ȭ��ȣ"));
			 this.add(tfPhone);
		 }
	 }
	 
	 // �ؽ�Ʈ �ʵ忡 <Enter> Ű �Է� �� �߻��ϴ� Action �̺�Ʈ ������ ���
	 public void makeListeners() {
		 tfPhone.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					//JTextField t = (JTextField)e.getSource();
					ta.append(tfGivenname.getText()+ " " + tfName.getText()+ " " + tfPhone.getText() + "\n");
					// �ؽ�Ʈ�ʵ��� ���ڿ��� �ؽ�Ʈ������ �߰�
					
					tfGivenname.setText(""); // �ؽ�Ʈ �ʵ� �Էµ� ���� �����
					tfName.setText("");
					tfPhone.setText("");
				}
		 });
	 }
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TextArea();
	}

}
