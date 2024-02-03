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
		btn.addActionListener(new MyActionListener()); // Action 이번트 리스너 달기
		c.add(btn);
		
		setSize(350, 150);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new IndepListClass();
	}

}

// 독립된 클래스로 이벤트 리스너 작성
class MyActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton)e.getSource(); // 이벤트 소스 버튼 알아내기 (p.541 캐스팅)
		if(b.getText().equals("Action"))
			b.setText("액션");
		else
			b.setText("Action");
	}
	
}