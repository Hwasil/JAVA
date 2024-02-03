import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class ComboAction extends JFrame {
	private String [] fruits = {"Apple", "Banana", "Kiwi", "Peach","WaterMelon"}; // �޺��ڽ� ������
	private int [] prices = {300, 400, 500, 600, 700};
	private ImageIcon [] images = { // �̹��� ��ü �迭
			new ImageIcon("images/apple.jpg"),
			new ImageIcon("images/Banana.jpg"),
			new ImageIcon("images/Kiwi.jpg"),
			new ImageIcon("images/Peach.jpg"),
			new ImageIcon("images/WaterMelon.jpg")};
	private JLabel imgLabel = new JLabel(images[0]);                    // �̹��� ���̺� ������Ʈ
	private JComboBox<String> strCombo = new JComboBox<String>(fruits); // ���ڿ� �޺� �ڽ�
	
	private JLabel sumLabel = new JLabel();
	private Vector<String> selectedFruits = new Vector<String>();       // �޺� �ڽ��� ������ ���ڸ� ����Ʈ�� �ֱ� ����
	private JList<String> strList = new JList<String>();                // ������ ���ڿ��� �����ֱ� ����
	int sum=0;
	
	public ComboAction() {
		setTitle("Combobox & Action Event Ex");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(strCombo);
		c.add(imgLabel);
		
		strList.setListData(selectedFruits); // ��������?
		c.add(strList);
		sumLabel.setText("������ ��ǰ�� �� ���� >> "); // ��������
		c.add(sumLabel);
		
		// Action Listener
		strCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox <String> cb = (JComboBox<String>)e.getSource(); // �޺� �ڽ� ������, ��Ʈ�� ���׸����� �Բ� �ۼ�
				int index = cb.getSelectedIndex();
				imgLabel.setIcon(images[index]);   // ������ ������ �ε����� �׸� ���
				selectedFruits.add(fruits[index]); // ���Ϳ� ���� �߰�
				sum += prices[index];
				strList.setListData(selectedFruits); // ���͸� ����Ʈ�� ������ ��µ�
				sumLabel.setText("������ ��ǰ�� �� ������ "+ Integer.toString(sum)+"�� �Դϴ�.");
			}
			
		});
		setSize(500, 300);
		setVisible(true);
		
		// �� �޺��ڽ�, �̹�����, J����Ʈ ������ �гη� ��� ��ġ ���� ���� �ϱ�(BorderLayout ���)
		// ��� �ߺ� ���� �� �̸� 2�� ����� �ƴ϶� Apple 2�� ��µǰ� �����ϱ� -> ���Ϳ� �ֱ� ���� �̹� ���Ϳ� ����ִ��� Ȯ��, ������ �����ϴ� �迭�� ���� �ۼ�
		//                                                         ����ϴ� ���뵵 �޶���...
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ComboAction();
	}

}
