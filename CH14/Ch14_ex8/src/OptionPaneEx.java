import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Ch14_ex8
public class OptionPaneEx extends JFrame {
	
	public OptionPaneEx() {
		setTitle("OptionPaneEX");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		setSize(500, 200);
		c.add(new MyPanel(), BorderLayout.NORTH);
		setVisible(true);
	}
	
	class MyPanel extends Panel {
		private JButton inputB = new JButton("Input Name");
		private JTextField tf = new JTextField(10);
		private JButton confirmB = new JButton("Confirm");
		private JButton messageB = new JButton("Message");
		
		public MyPanel() {
			setBackground(Color.LIGHT_GRAY);
			add(inputB);
			add(confirmB);
			add(messageB);
			add(tf);
			
			inputB.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String name = JOptionPane.showInputDialog("NAME");
					if (name != null)
						tf.setText(name);
				}
				
			}); 
			
			confirmB.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int result = JOptionPane.showConfirmDialog(null, "Continue?", 
							"Confirm", JOptionPane.YES_NO_OPTION);
					
					if (result == JOptionPane.CLOSED_OPTION)
						tf.setText("Just Closed without Selection");
					else if (result == JOptionPane.YES_OPTION)
						tf.setText("Yes");
					else
						tf.setText("No");
				}
				
			});
			
			messageB.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JOptionPane.showMessageDialog(null, "Watch Out!", 
							"Message", JOptionPane.ERROR_MESSAGE);
				}
				
			});
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new OptionPaneEx();
	}

}
