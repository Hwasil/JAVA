package Client_Manage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;

public class LoginPanel extends JFrame {
	private JTextField idTF;
	private JTextField pwTF;
	private Client client = null;
	private ClientFrame frame = null;
	
	public LoginPanel(ClientFrame frame) {
		this.frame = frame;
		
		setLayout(new GridLayout(3, 2, 5, 5));
		
		idTF = new JTextField();
		pwTF = new JTextField();
		
		add(new JLabel("ID"));
		add(idTF);
		add(new JLabel("PW"));
		add(pwTF);
		
		JButton okBtn = new JButton("Ȯ��");
		add(okBtn);
		okBtn.addActionListener(new LoginActionListener());
		
		JButton cancelBtn = new JButton("���");
		add(cancelBtn);
		cancelBtn.addActionListener(new LoginActionListener());
		
		setSize(100, 60); 
		//setVisible(true);
	}
	
	class LoginActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String cmd = e.getActionCommand();
			
			switch ( cmd ) {
			case "Ȯ��" :
				String id = idTF.getText();
				String pw = pwTF.getText();
				
				if (id.length() > 0 && pw.length() > 0) {
					System.out.println(id + " " + pw);
					
					client =  new Client(new Message(id, pw));
					client.startClient();
					
					String res = client.getResponse(); // Data ������ ��������, ������ wait
					switch (res) {
					case "LOHINOK" :
						frame.logged();
						break;
					case "LOHINFAIL" :
						idTF.setText("");
						pwTF.setText("");
						break;
					case "NOCUNN" :
						idTF.setText("");
						pwTF.setText("");
						break;
					}
				}
				break;
			case "���" :
				System.exit(0);
				break;
			}
		}
		
	}
}
