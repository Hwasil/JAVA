import javax.swing.*;
import java.awt.*;

		//7��
		// ���� - ���巹�̾ƿ� : �׸��带 ����ϸ� �ؽ�Ʈ �ʵ尡 �ٸ� ��ư�� ũ�Ⱑ �ٸ�, �÷ο�� ũ�� �ٸ� ȭ�� ũ�Ⱑ �ٲ�� �̻�����
		// boradLayout �ȿ� �� �ٸ� �����̳ʷ� ����� ��!! JPanel ���(������ 3���� Ŭ���� ����! ���� JFrame�� ���� �־�� �Ѵ�?!)
		// class NorthPanel extend JFrame {public North-() {g-. } �÷ο�, �׸���, �÷ο� : �г� 
		// ctrl+f5 : ���ͳ� �˻�

class NorthPanel extends JPanel { // ���� �Է� : �ؽ�Ʈâ
	public NorthPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel jl = new JLabel("���� �Է�");
		add(jl);
		JTextField jf = new JTextField("", 30);
		add(jf);
		setBackground(Color.CYAN);
	}
}
class CenterPanel extends JPanel { // ���� ��ư 
	public CenterPanel() {
		// ��ư �� �迭
		String btn[] = {"0", "1", "2", "3", "4", "5", "6","7", "8", "9", "CE", "���",
				"+", "-", "x", "/"}; 
		setLayout(new GridLayout(4, 4, 5, 5)); // (����, ����, ����, ����)
	
		JButton button[] = new JButton[btn.length]; //  ��ư����, �迭�� ���̸�ŭ ������
		for (int i=0; i<btn.length; i++) {
			button[i] = new JButton(btn[i]);
			// ��ư ��
			if (11 < i) { // �迭 �ε��� 11 ���� ������ ��ư�� �� 
				button[i].setBackground(Color.GREEN);
			}
			// �гο� ����
			add(button[i]);
		}
	}
}
class SouthPanel extends JPanel { // ���â, North�� ����
	public SouthPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel jl = new JLabel("��� ���");
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
