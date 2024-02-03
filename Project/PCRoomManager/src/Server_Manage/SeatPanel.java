package Server_Manage;
import java.awt.*;
import javax.swing.*;

public class SeatPanel extends JPanel {
	int maxSeats = 0;
	int rows = 5;
	int cols = 10;
	private SeatButton [] seatButtons = null; 
	private Seat [] seats = null; // 
	
	public SeatPanel(int total, Seat [] seatArray) {
		this.seats = seatArray;
		seatButtons = new SeatButton[total]; // seatB를 가르키는 50개의 래퍼런스, 여기까진 객체가 아직 안 만들어진 것
		maxSeats = total;
		setLayout(new GridLayout(rows, cols, 5, 5));
		
		for (int i=0; i < this.maxSeats; i++) { // 실제 객체를 만듦
			seats[i] = new Seat(i);
			
			seatButtons[i] = new SeatButton(i, seats[i]);
			seatButtons[i].printSeatInfo();
			seatButtons[i].setHorizontalAlignment(JButton.LEFT);
			seatButtons[i].setFont(new Font("Verdana", Font.PLAIN, 15));
			seatButtons[i].setPreferredSize(new Dimension(50, 40));
			seatButtons[i].setBackground(Color.lightGray);
			add(seatButtons[i]);
		}
	}

	public SeatButton[] getSeatButtons() {
		return seatButtons;
	}
	
}
