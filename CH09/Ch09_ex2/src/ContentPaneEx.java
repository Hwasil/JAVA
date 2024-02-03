import javax.swing.*;
import java.awt.*;

public class ContentPaneEx extends JFrame{
	public ContentPaneEx() {
		setTitle("ContentPane & JFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		// 입력되는 키와 마우스의 움직임을 컴포넌트에게 전달하는 '이벤트 처리(분배) 스레드'가 자동으로 추가 생성, 프로그램이 종료되어도 이벤트 처리 스레드가 살아있음
		
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.YELLOW);
		//contentPane.setLayout(new FlowLayout());
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
		
		contentPane.add(new JButton("oh, Canada"));
		contentPane.add(new JButton("Got it"));
		contentPane.add(new JButton("On Your Mark"));
		contentPane.add(new JButton("It's ok"));
		contentPane.add(new JButton("Take it easy!")); //버튼을 커텐트팬에 부착
		
		setSize(300, 300);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ContentPaneEx();
	}

}
