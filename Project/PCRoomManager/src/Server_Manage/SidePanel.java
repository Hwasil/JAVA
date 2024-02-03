package Server_Manage;
import java.awt.*;
import javax.swing.*;

public class SidePanel extends JPanel {
	JLabel seatsL = null;
	int totalSEats = 0;    // 전체 좌석 수
	int occupiedSeats = 0; // 사용 중인 좌석 수
	
	public SidePanel(int total) { // 생성자
		this.totalSEats = total;
		
		this.seatsL = new JLabel(Integer.toString(occupiedSeats) + "/" + Integer.toString(totalSEats));  // 정수를 문자로 변환
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
