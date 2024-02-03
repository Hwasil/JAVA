import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ClientPanel extends JPanel implements ActionListener { 
	JTextField nameTF = null;
	String name = null;
	Client_First frame = null; 

	public String getName() {
		return name;
	}

	public ClientPanel(Client_First frame) { // 생성자
		this.frame = frame; 
		setLayout(new GridLayout(3, 2, 3, 3));
		
		add(new JLabel("이름"));
		nameTF = new JTextField();
		add(nameTF);
		
		JButton acc = new JButton("접속");
		acc.addActionListener(this);
		add(acc);
		
		JButton del = new JButton("지우기");
		del.addActionListener(this);
		add(del);
		
		JButton send = new JButton("보내기");
		send.addActionListener(this);
		add(send);
		
		// 공간 확보
		setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0)); 
		
		setSize(200, 50);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand(); // 어떤 버튼이 눌려졌는지 알기 위해, 버튼의 타이틀
		JButton btn = (JButton) e.getSource(); // 버튼의 종류는 무엇인지
		
		switch ( cmd ) {
		case "접속" : 
			name = this.nameTF.getText(); // 이름 받기
			if ( name == null || name.length() == 0) {
				this.frame.addMessages("이름을 입력하세요.\n"); // clident_First 에서 구현
				break;
			} 
			this.frame.connect(); // 커넥트란 메소드에서 기능을 구현하겠다
			btn.setText("접속종료");
			break;
			
		case "접속 종료" :
			this.frame.disconnect();
			this.frame.addMessages("접속이 종료되었습니다.");
			btn.setText("접속");
			break;
			
		case "지우기" :
			this.frame.clearMessages();
			break;
		case "보내기" :
			this.frame.sendMessages();
			break;
		}
	}

}
