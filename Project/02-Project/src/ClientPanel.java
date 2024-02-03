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

	public ClientPanel(Client_First frame) { // ������
		this.frame = frame; 
		setLayout(new GridLayout(3, 2, 3, 3));
		
		add(new JLabel("�̸�"));
		nameTF = new JTextField();
		add(nameTF);
		
		JButton acc = new JButton("����");
		acc.addActionListener(this);
		add(acc);
		
		JButton del = new JButton("�����");
		del.addActionListener(this);
		add(del);
		
		JButton send = new JButton("������");
		send.addActionListener(this);
		add(send);
		
		// ���� Ȯ��
		setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0)); 
		
		setSize(200, 50);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand(); // � ��ư�� ���������� �˱� ����, ��ư�� Ÿ��Ʋ
		JButton btn = (JButton) e.getSource(); // ��ư�� ������ ��������
		
		switch ( cmd ) {
		case "����" : 
			name = this.nameTF.getText(); // �̸� �ޱ�
			if ( name == null || name.length() == 0) {
				this.frame.addMessages("�̸��� �Է��ϼ���.\n"); // clident_First ���� ����
				break;
			} 
			this.frame.connect(); // Ŀ��Ʈ�� �޼ҵ忡�� ����� �����ϰڴ�
			btn.setText("��������");
			break;
			
		case "���� ����" :
			this.frame.disconnect();
			this.frame.addMessages("������ ����Ǿ����ϴ�.");
			btn.setText("����");
			break;
			
		case "�����" :
			this.frame.clearMessages();
			break;
		case "������" :
			this.frame.sendMessages();
			break;
		}
	}

}
