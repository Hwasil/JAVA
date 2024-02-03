import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// 프로젝트 이름 영문 이름 풀네임으로 수정하여 제출

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
		setTitle("202195027 오화실 2022-2 MidtermTest");
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
		private JRadioButton nameRadio = new JRadioButton("이름");
		private JRadioButton phoneRadio = new JRadioButton("전화번호"); 
		private ButtonGroup g = new ButtonGroup();
		
		public JTextField getTf() { //Tf getter 만들기!
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
			
			// 텍스트 필드랑 라디오버튼 추가하기 & 동작되는지 확인
			add(tf, BorderLayout.NORTH);
			add(this.nameRadio, BorderLayout.WEST);
			add(this.phoneRadio, BorderLayout.CENTER);
		}
	}
	
	class Commands extends JPanel {
		private JButton readB = new JButton("읽기"); 
		private JButton saveB = new JButton("저장"); 
		private JButton serchB = new JButton("검색"); 
		private JButton addB = new JButton("추가");
		private JButton changeB = new JButton("수정");
		private JButton DeletB = new JButton("삭제");
		
		public Commands() {
			setLayout(new GridLayout(6,1));
			
			this.readB.addActionListener(new ActionListener() { // 읽기
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					rp = new ReadPhones();
					phoneList.setListData(rp.getPhoneString());
				}
			});
			
			
			 this.saveB.addActionListener(new ActionListener() { // 저장
				 @Override 
				 public void actionPerformed(ActionEvent e) { 
					 // TODO Auto-generated method stub 
					 rp.savePhone();
					 
					 if (rp.savePhone() == true) {
						 infoLabel.setText("성공적으로 저장하였습니다.");
					 } else
						 infoLabel.setText("저장 실패하였습니다.");
				 }
			 });
			 
			 this.serchB.addActionListener(new ActionListener() { // 검색

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if ( nameSelected == true) {
						String name = inputs.getTf().getText();
						String phone = rp.searchName(name);
						if (phone != null) 
							infoLabel.setText(phone);
						else
							infoLabel.setText(name + " 은 존재하지 않습니다.");
					}
					if (phoneSelected == true) {
						String phoneNum = inputs.getTf().getText();
						String phone = rp.searchPhoneNumber(phoneNum);
						if (phone != null) 
							infoLabel.setText(phone);
						else
							infoLabel.setText(phoneNum + " 은 존재하지 않습니다.");
					}
				}
				 
			 });
			 
			 this.addB.addActionListener(new ActionListener() { // 추가

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String phone = inputs.getTf().getText();
					if (rp.addPhones(phone))
						phoneList.setListData(rp.getPhoneString());
					else
						infoLabel.setText("추가 실패하였습니다!");
				} 
			});
			 
			 this.changeB.addActionListener(new ActionListener() { // 수정

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String phone = inputs.getTf().getText();
					if (rp.editPhone(phone, index) == true) {
						infoLabel.setText("수정 성공하였습니다!");
						phoneList.setListData(rp.getPhoneString()); // J리스트에 수정 반영
					}
					else
						infoLabel.setText("수정 실패하였습니다!");
				}
			});
			 
			 this.DeletB.addActionListener(new ActionListener() { // 삭제

				@Override
				public void actionPerformed(ActionEvent e) { 
					// TODO Auto-generated method stub
					Phone p = null;
					if ( index >= 0) // index가 -1로 시작되어 확인이 필요
						p = rp.removePhone(index);
					if (p != null) {
						infoLabel.setText(p.getName() + " 은 삭제되었습니다.");
					} else
						infoLabel.setText("삭제 실패하였습니다.");
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
