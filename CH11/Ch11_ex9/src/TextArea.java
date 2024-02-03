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
			 this.add(new JLabel("성"));
			 this.add(tfGivenname);
			 this.add(new JLabel("이름"));
			 this.add(tfName);
			 this.add(new JLabel("전화번호"));
			 this.add(tfPhone);
		 }
	 }
	 
	 // 텍스트 필드에 <Enter> 키 입력 시 발생하는 Action 이벤트 리스너 등록
	 public void makeListeners() {
		 tfPhone.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					//JTextField t = (JTextField)e.getSource();
					ta.append(tfGivenname.getText()+ " " + tfName.getText()+ " " + tfPhone.getText() + "\n");
					// 텍스트필드의 문자열을 텍스트영역에 추가
					
					tfGivenname.setText(""); // 텍스트 필드 입력된 내용 지우기
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
