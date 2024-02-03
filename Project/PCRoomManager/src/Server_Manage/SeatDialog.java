package Server_Manage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Ch14_ex6 Âü°í

public class SeatDialog extends JDialog{
	JTextField tf = new JTextField(10);
	JButton okB = new JButton("OK");
	JButton noB = new JButton("NO"); 
	
	public SeatDialog() {
		setTitle("book");
		setLayout(new GridLayout(2, 2, 3, 3));
		add(new JLabel("User ID"));
		add(tf);
		add(okB);
		add(noB);
		
		okB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
			
		});
		
		noB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tf.setText("");
				setVisible(false);
			}
			
		});
		
		setSize(100, 80);
	}
}
