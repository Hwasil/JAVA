import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*; // 파일 입출력 시 필요
import java.io.*;

// Vector 사용시 클래스 사용
public class Ohhwasil extends JFrame {
	private ReadPhones rp = null; // 래퍼런스
	private JList<String> phoneList = new JList<String>();
	
	private JLabel intfoLabel = new JLabel(); 
	private Inputs inputs = null; 
	private Commands commands = null;
	 
	public Ohhwasil() {
		setTitle("202195027 오화실");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.inputs = new Inputs();
		this.commands = new Commands();
		
		//rp = new ReadPhones(); //실제 객체
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		c.add(this.inputs, BorderLayout.NORTH);
		c.add(this.commands, BorderLayout.WEST);
		//this.phoneList.setListData(rp.getPhoneString());
		c.add(new JScrollPane(phoneList), BorderLayout.CENTER);
		c.add(this.intfoLabel, BorderLayout.SOUTH);
		
		setSize(500, 500);
		setVisible(true);
	}
	
	class Inputs extends JPanel {
		private JTextField jf = new JTextField("", 40);
		private JRadioButton [] radio = new JRadioButton [2];
		private String [] text = {"이름", "전화번호"}; 
		
		public Inputs() {
			setLayout(new GridLayout(2, 2)); // 라디오버튼 밑으로 보내기
			add(jf);
			
			ButtonGroup g = new ButtonGroup();  
			for (int i=0; i<radio.length; i++) { 
				radio[i] = new JRadioButton(text[i]); 
				g.add(radio[i]);
				add(radio[i]);
			}
		}
	}
	
	class Commands extends JPanel {
		private JButton readB = new JButton("Read"); 
		private JButton saveB = new JButton("Save"); 
		private JButton serchB = new JButton("Serch"); 
		
		public Commands() {
			setLayout(new GridLayout(3,1));
			
			this.readB.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					rp = new ReadPhones(); //실제 객체
					phoneList.setListData(rp.getPhoneString());
					
				}
			});
			
			
			 this.saveB.addActionListener(new ActionListener() {
			 
				 @Override 
				 public void actionPerformed(ActionEvent e) { // TODO Auto-generated method stub 
					 try { BufferedWriter writer = new BufferedWriter(new FileWriter("./src/newphonebook.txt")); // 파일 생성완 내용 쓰기 
					 //String line = phoneList.readLine();
					 String line = (String)phoneList.nextline();
					 
					 } catch (IOException e1) { // TODO Auto-generated catch block
						 e1.printStackTrace(); } 
				 }
			 });
			 
			 this.serchB.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					rp = new searchName();
					
				}
				 
			 });
			
			add(this.readB);
			add(this.saveB);
			add(this.serchB);
		}
	}
	
	class intfoLabel extends JPanel {
		
		public intfoLabel() {
			
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Ohhwasil();
	}

}
