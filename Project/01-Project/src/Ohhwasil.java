import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// ������Ʈ �̸� ���� �̸� Ǯ�������� �����Ͽ� ����

public class Ohhwasil extends JFrame {
	private ReadPhones rp = null; 
	private JList<String> phoneList = new JList<String>();
	
	private JLabel infoLabel = new JLabel("result"); 
	private Inputs inputs = null; 
	private Commands commands = null;
	private int index = -1;
	private boolean nameSelected = false;
	private boolean phoneSelected = false;
	 
	public Ohhwasil() {
		setTitle("202195027 ��ȭ�� 2022-2 MidtermTest");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.inputs = new Inputs();
		this.commands = new Commands();
		
		this.phoneList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				JList<String> list = (JList<String>)e.getSource();
				index = list.getSelectedIndex();
				String phone = list.getSelectedValue();
				inputs.getTf().setText(phone);
			}
			
		});
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		c.add(this.inputs, BorderLayout.NORTH);
		c.add(this.commands, BorderLayout.WEST);
		c.add(new JScrollPane(phoneList), BorderLayout.CENTER);
		c.add(this.infoLabel, BorderLayout.SOUTH);
		
		setSize(500, 500);
		setVisible(true);
	}
	
	class Inputs extends JPanel {
		private JTextField tf = new JTextField(); 
		private JRadioButton nameRadio = new JRadioButton("�̸�");
		private JRadioButton phoneRadio = new JRadioButton("��ȭ��ȣ"); 
		private ButtonGroup g = new ButtonGroup();
		
		public JTextField getTf() { //Tf getter �����!
			return tf;
		}
		
		public Inputs() {
			setLayout(new BorderLayout());
			
			g.add(this.nameRadio);
			g.add(this.phoneRadio);
			
			this.nameRadio.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					if (e.getStateChange() == ItemEvent.DESELECTED) {
						nameSelected = false;
						return;
					}
					nameSelected = true;
				}
				
			});
			
			this.phoneRadio.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					if (e.getStateChange() == ItemEvent.DESELECTED) {
						phoneSelected = false;
						return;
					}
					phoneSelected = true;
				}
				
			});
			
			// �ؽ�Ʈ �ʵ�� ������ư �߰��ϱ� & ���۵Ǵ��� Ȯ��
			add(tf, BorderLayout.NORTH);
			add(this.nameRadio, BorderLayout.WEST);
			add(this.phoneRadio, BorderLayout.CENTER);
		}
	}
	
	class Commands extends JPanel {
		private JButton readB = new JButton("�б�"); 
		private JButton saveB = new JButton("����"); 
		private JButton serchB = new JButton("�˻�"); 
		private JButton addB = new JButton("�߰�");
		private JButton changeB = new JButton("����");
		private JButton DeletB = new JButton("����");
		
		public Commands() {
			setLayout(new GridLayout(6,1));
			
			this.readB.addActionListener(new ActionListener() { // �б�
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					rp = new ReadPhones();
					phoneList.setListData(rp.getPhoneString());
				}
			});
			
			
			 this.saveB.addActionListener(new ActionListener() { // ����
				 @Override 
				 public void actionPerformed(ActionEvent e) { 
					 // TODO Auto-generated method stub 
					 rp.savePhone();
					 
					 if (rp.savePhone() == true) {
						 infoLabel.setText("���������� �����Ͽ����ϴ�.");
					 } else
						 infoLabel.setText("���� �����Ͽ����ϴ�.");
				 }
			 });
			 
			 this.serchB.addActionListener(new ActionListener() { // �˻�

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if ( nameSelected == true) {
						String name = inputs.getTf().getText();
						String phone = rp.searchName(name);
						if (phone != null) 
							infoLabel.setText(phone);
						else
							infoLabel.setText(name + " �� �������� �ʽ��ϴ�.");
					}
					if (phoneSelected == true) {
						String phoneNum = inputs.getTf().getText();
						String phone = rp.searchPhoneNumber(phoneNum);
						if (phone != null) 
							infoLabel.setText(phone);
						else
							infoLabel.setText(phoneNum + " �� �������� �ʽ��ϴ�.");
					}
				}
				 
			 });
			 
			 this.addB.addActionListener(new ActionListener() { // �߰�

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String phone = inputs.getTf().getText();
					if (rp.addPhones(phone))
						phoneList.setListData(rp.getPhoneString());
					else
						infoLabel.setText("�߰� �����Ͽ����ϴ�!");
				} 
			});
			 
			 this.changeB.addActionListener(new ActionListener() { // ����

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String phone = inputs.getTf().getText();
					if (rp.editPhone(phone, index) == true) {
						infoLabel.setText("���� �����Ͽ����ϴ�!");
						phoneList.setListData(rp.getPhoneString()); // J����Ʈ�� ���� �ݿ�
					}
					else
						infoLabel.setText("���� �����Ͽ����ϴ�!");
				}
			});
			 
			 this.DeletB.addActionListener(new ActionListener() { // ����

				@Override
				public void actionPerformed(ActionEvent e) { 
					// TODO Auto-generated method stub
					Phone p = null;
					if ( index >= 0) // index�� -1�� ���۵Ǿ� Ȯ���� �ʿ�
						p = rp.removePhone(index);
					if (p != null) {
						infoLabel.setText(p.getName() + " �� �����Ǿ����ϴ�.");
					} else
						infoLabel.setText("���� �����Ͽ����ϴ�.");
				} 
			});
			
			add(this.readB);
			add(this.saveB);
			add(this.serchB);
			add(this.addB);
			add(this.changeB);
			add(this.DeletB);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Ohhwasil();
	}

}
