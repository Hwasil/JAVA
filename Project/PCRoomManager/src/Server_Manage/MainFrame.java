package Server_Manage;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class MainFrame extends JFrame {
	SidePanel sidePanel = null;
	SeatPanel seatPanel = null;
	final int MAXSEATS = 50;
	private MemberDAO memberDAO = null;
	private Seat [] seats = new Seat[MAXSEATS];
	SeatDialog dialog= null;
	private Server server = null;
	
	public MainFrame() {
		setTitle("PC�� ���� ���α׷�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			memberDAO = new MemberDAO();
			memberDAO.printMembers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		creatMenu();
		
		Container c = getContentPane();
		
		this.sidePanel = new SidePanel(MAXSEATS);
		c.add(sidePanel, BorderLayout.WEST);
		
		JTabbedPane pane = createTabbedPane();
		c.add(pane, BorderLayout.CENTER);
		
	
		addSeatButtonsListener();
		
		createServer();
		
		setSize(1000, 800); // 800, 1000
		setVisible(true);
	}
	
	public void createServer() {
		server = new Server(memberDAO);
		server.start();
	}
	
	private void addSeatButtonsListener() { // button ����
		SeatButton [] seatButtons = this.seatPanel.getSeatButtons();
		dialog = new SeatDialog();
		
		for (int i=0; i<this.MAXSEATS; i++) {
			seatButtons[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					SeatButton btn = (SeatButton)e.getSource(); // ��� ��ư�� ���������� �˾ƾ���. �ڸ� ��ȣ SeatButton.java�� ����
					if (seats[btn.getSeatNum()].isOccupied() ) { // �ڸ� �̹� ������ ���
						btn.setBackground(Color.lightGray);
						seats[btn.getSeatNum()].setOccupied(false);
						sidePanel.removeUser();
					} else { // ���ο� ���
						seats[btn.getSeatNum()].setOccupied(true);
						btn.setBackground(Color.yellow);
						sidePanel.addUser();
					}
				}
				
			});
		}
	}
	
	private void creatMenu() {
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open");
		JMenuItem saveItem = new JMenuItem("Save");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		openItem.addActionListener(new FileActionListener());
		saveItem.addActionListener(new FileActionListener());
		exitItem.addActionListener(new FileActionListener());
		
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);
		
		mb.add(fileMenu);
		setJMenuBar(mb);
	}
	
	private JTabbedPane createTabbedPane() {
		JTabbedPane pane = new JTabbedPane();
		this.seatPanel = new SeatPanel(MAXSEATS, seats);
		pane.addTab("�������", seatPanel);
		pane.addTab("�������", new JLabel("���ϳ���"));
		pane.addTab("��ǰ����",  new JLabel("�����ֹ�"));
		
		return pane;
	}
	
	class FileActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String cmd = e.getActionCommand(); // open ���� ������ �� Ŀ��尡 �Ѿ��
			switch (cmd) {
			case "Open" :
				break;
			case "Save" :
				memberDAO.backup("Info/backup.txt");
				break;
			case "Exit" :
				System.exit(0);
				break;
			}
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainFrame();
	}

}
