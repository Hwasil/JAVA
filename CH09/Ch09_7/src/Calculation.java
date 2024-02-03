import javax.swing.*;
import java.awt.*;

		//7번
		// 계산기 - 보드레이아웃 : 그리드를 사용하면 텍스트 필드가 다른 버튼과 크기가 다름, 플로우는 크기 다름 화면 크기가 바뀌면 이상해짐
		// boradLayout 안에 또 다른 컨테이너로 묶어야 함!! JPanel 사용(별도의 3개의 클래스 생성! 이후 JFrame에 집어 넣어야 한다?!)
		// class NorthPanel extend JFrame {public North-() {g-. } 플로우, 그리드, 플로우 : 패널 
		// ctrl+f5 : 인터넷 검색

class NorthPanel extends JPanel { // 수식 입력 : 텍스트창
	public NorthPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel jl = new JLabel("수식 입력");
		add(jl);
		JTextField jf = new JTextField("", 30);
		add(jf);
		setBackground(Color.CYAN);
	}
}
class CenterPanel extends JPanel { // 숫자 버튼 
	public CenterPanel() {
		// 버튼 값 배열
		String btn[] = {"0", "1", "2", "3", "4", "5", "6","7", "8", "9", "CE", "계산",
				"+", "-", "x", "/"}; 
		setLayout(new GridLayout(4, 4, 5, 5)); // (가로, 세로, 수평, 수직)
	
		JButton button[] = new JButton[btn.length]; //  버튼생성, 배열의 길이만큼 가져옴
		for (int i=0; i<btn.length; i++) {
			button[i] = new JButton(btn[i]);
			// 버튼 색
			if (11 < i) { // 배열 인덱스 11 이후 연산자 버튼에 색 
				button[i].setBackground(Color.GREEN);
			}
			// 패널에 생성
			add(button[i]);
		}
	}
}
class SouthPanel extends JPanel { // 결과창, North와 동일
	public SouthPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel jl = new JLabel("계산 결과");
		add(jl);
		JTextField jf = new JTextField("", 30);
		add(jf);
		setBackground(Color.YELLOW);
	}
}

public class Calculation extends JFrame {
	public Calculation() {
		setTitle("Calculation Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout(5, 5));
		
		c.add(new NorthPanel(), BorderLayout.NORTH);
		c.add(new CenterPanel(), BorderLayout.CENTER);
		c.add(new SouthPanel(), BorderLayout.SOUTH);
		
		setSize(400, 300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Calculation();
	}

}
