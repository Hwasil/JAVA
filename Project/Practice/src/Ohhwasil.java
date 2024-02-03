import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*; // ���� ����� �� �ʿ�
import java.io.*;

// Vector ���� Ŭ���� ���
public class Ohhwasil extends JFrame {
	private ReadPhones rp = null; // ���۷���
	private JList<String> phoneList = new JList<String>();
	
	private JLabel intfoLabel = new JLabel(); 
	private Inputs inputs = null; 
	private Commands commands = null;
	 
	public Ohhwasil() {
		setTitle("202195027 ��ȭ��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.inputs = new Inputs();
		this.commands = new Commands();
		
		//rp = new ReadPhones(); //���� ��ü
		
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
		private String [] text = {"�̸�", "��ȭ��ȣ"}; 
		
		public Inputs() {
			setLayout(new GridLayout(2, 2)); // ������ư ������ ������
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
					rp = new ReadPhones(); //���� ��ü
					phoneList.setListData(rp.getPhoneString());
					
				}
			});
			
			
			 this.saveB.addActionListener(new ActionListener() {
			 
				 @Override 
				 public void actionPerformed(ActionEvent e) { // TODO Auto-generated method stub 
					 try { BufferedWriter writer = new BufferedWriter(new FileWriter("./src/newphonebook.txt")); // ���� ������ ���� ���� 
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
