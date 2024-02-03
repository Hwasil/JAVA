import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyModaDialog extends JDialog {
	private JTextField tf = new JTextField(10);
	private JButton okB = new JButton("OK");
	
	public MyModaDialog(JFrame frame, String title) {
		super(frame, title, true);
		setLayout(new FlowLayout());
		add(tf);
		add(okB);
		setSize(200, 100);
		
		okB.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		}); 
	}
	
	public String getInput() {
		if (tf.getText().length() == 0) return null;
		else return tf.getText();
	}
}

public class DialohEx2 extends JFrame {
	private MyModaDialog dialog;
	
	public DialohEx2() {
		super("DialogEx2 예제 프레임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton btn = new JButton("Show Modal Dialog");
		
		dialog = new MyModaDialog(this, "Test Modal Dailog");
		
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dialog.setVisible(true);
				
				String text = dialog.getInput();
				
				if (text == null) return;
				JButton btn = (JButton)e.getSource();
				btn.setText(text);
			}
		});
		getContentPane().add(btn);
		setSize(250, 200);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DialohEx2();
	}

}
