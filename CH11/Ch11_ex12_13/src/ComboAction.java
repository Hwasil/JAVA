import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class ComboAction extends JFrame {
	private String [] fruits = {"Apple", "Banana", "Kiwi", "Peach","WaterMelon"}; // 콤보박스 아이템
	private int [] prices = {300, 400, 500, 600, 700};
	private ImageIcon [] images = { // 이미지 객체 배열
			new ImageIcon("images/apple.jpg"),
			new ImageIcon("images/Banana.jpg"),
			new ImageIcon("images/Kiwi.jpg"),
			new ImageIcon("images/Peach.jpg"),
			new ImageIcon("images/WaterMelon.jpg")};
	private JLabel imgLabel = new JLabel(images[0]);                    // 이미지 레이블 컴포넌트
	private JComboBox<String> strCombo = new JComboBox<String>(fruits); // 문자열 콤보 박스
	
	private JLabel sumLabel = new JLabel();
	private Vector<String> selectedFruits = new Vector<String>();       // 콤보 박스로 선택한 문자를 리스트에 넣기 위해
	private JList<String> strList = new JList<String>();                // 선택한 문자열을 보여주기 위한
	int sum=0;
	
	public ComboAction() {
		setTitle("Combobox & Action Event Ex");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(strCombo);
		c.add(imgLabel);
		
		strList.setListData(selectedFruits); // 생략가능?
		c.add(strList);
		sumLabel.setText("선택한 상품의 총 가격 >> "); // 생략가능
		c.add(sumLabel);
		
		// Action Listener
		strCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox <String> cb = (JComboBox<String>)e.getSource(); // 콤보 박스 가져옴, 스트링 제네릭까지 함께 작성
				int index = cb.getSelectedIndex();
				imgLabel.setIcon(images[index]);   // 선택한 문자의 인덱스로 그림 출력
				selectedFruits.add(fruits[index]); // 백터에 문자 추가
				sum += prices[index];
				strList.setListData(selectedFruits); // 백터를 리스트에 넣으면 출력됨
				sumLabel.setText("선택한 상품의 총 가격은 "+ Integer.toString(sum)+"원 입니다.");
			}
			
		});
		setSize(500, 300);
		setVisible(true);
		
		// 위 콤보박스, 이미지라벨, J리스트 세개를 패널로 묶어서 위치 변도 없게 하기(BorderLayout 사용)
		// 사과 중복 선택 시 이름 2번 출력이 아니라 Apple 2로 출력되게 수정하기 -> 벡터에 넣기 전에 이미 벡터에 들어있는지 확인, 갯수를 유지하는 배열을 따로 작성
		//                                                         출력하는 내용도 달라짐...
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ComboAction();
	}

}
