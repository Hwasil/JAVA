package Server_Manage;
import java.awt.*;
import javax.swing.*;

public class SidePanel extends JPanel {
	JLabel seatsL = null;
	int totalSEats = 0;    // ��ü �¼� ��
	int occupiedSeats = 0; // ��� ���� �¼� ��
	
	public SidePanel(int total) { // ������
		this.totalSEats = total;
		
		this.seatsL = new JLabel(Integer.toString(occupiedSeats) + "/" + Integer.toString(totalSEats));  // ������ ���ڷ� ��ȯ
		seatsL.setFont(new Font("Verdana", Font.PLAIN, 20));
		seatsL.setPreferredSize(new Dimension(50, 50));
		this.setBackground(Color.CYAN);
		add(seatsL);
	}
	
	public void addUser() {
		this.occupiedSeats++;
		this.seatsL.setText(Integer.toString(occupiedSeats) + "/" + Integer.toString(totalSEats));
	}
	public void removeUser() {
		this.occupiedSeats--;
		this.seatsL.setText(Integer.toString(occupiedSeats) + "/" + Integer.toString(totalSEats));
	}
}
