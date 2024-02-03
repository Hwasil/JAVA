import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RadioButtonItemEventEx extends JFrame {
	private JRadioButton [] radio = new JRadioButton [3]; //라디오버튼 배열
	private String [] text = {"사과", "배", "체리"};         //라디오버튼 문자열
	private ImageIcon [] image = {  // 이미지 객체 배열
			new ImageIcon("images/Apple.png"), 
			new ImageIcon("images/pear.jpg"), 
			new ImageIcon("images/cherry.jpg")}; 
	private JLabel imageLabel = new JLabel(); //이미지 출력 레이블
	
	public RadioButtonItemEventEx() {
		setTitle("RadioButton Item Event Ex");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel radioPanel = new JPanel(); // 3개의 라디오 버튼 부착할 패널
		radioPanel.setBackground(Color.darkGray);
		
		ButtonGroup g = new ButtonGroup();  // 버튼 그룹 객체 생성
		for (int i=0; i<radio.length; i++) { // 3개의 라디오 버튼에 대해
			radio[i] = new JRadioButton(text[i]); // 라디오버튼 생성
			g.add(radio[i]);
			radioPanel.add(radio[i]);
			radio[i].addItemListener(new MyItemListener()); // 라디오버튼 Item 리스너 등록
		}
		
		radio[2].setSelected(true); // 체리 버튼 선택상태 설정
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
			if (e.getStateChange() == ItemEvent.DESELECTED) // 해제된 경우 그냥 리턴
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
