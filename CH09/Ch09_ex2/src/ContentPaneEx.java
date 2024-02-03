import javax.swing.*;
import java.awt.*;

public class ContentPaneEx extends JFrame{
	public ContentPaneEx() {
		setTitle("ContentPane & JFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		// �ԷµǴ� Ű�� ���콺�� �������� ������Ʈ���� �����ϴ� '�̺�Ʈ ó��(�й�) ������'�� �ڵ����� �߰� ����, ���α׷��� ����Ǿ �̺�Ʈ ó�� �����尡 �������
		
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.YELLOW);
		//contentPane.setLayout(new FlowLayout());
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
		
		contentPane.add(new JButton("oh, Canada"));
		contentPane.add(new JButton("Got it"));
		contentPane.add(new JButton("On Your Mark"));
		contentPane.add(new JButton("It's ok"));
		contentPane.add(new JButton("Take it easy!")); //��ư�� Ŀ��Ʈ�ҿ� ����
		
		setSize(300, 300);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ContentPaneEx();
	}

}
