package Server_Manage;
import java.awt.*;
import javax.swing.*;

public class SeatButton extends JButton {
	int seatNum;
	Seat seat = null;
	
	public SeatButton() { // ������ Overloading
		super();
	}
	
	public SeatButton(int number, Seat seat) {
		super(); // �θ� �߰��� �ְڴ�??
		this.seatNum = number;
		this.seat = seat;
	}

	public int getSeatNum() {
		return seatNum;
	}
	
	public void printSeatInfo() { // ��ư�� 4���� ������ ����ϱ� ���� ���, html ���� �̿�
		this.setText("<html>" + Integer.toString(seatNum) + "<br>" 
				+  seat.getUser() + "<br>" 
				+ (seat.getStartTime() == null? "00:00": seat.getStartTime()) + "<br>" 
				+ Float.toString(seat.getFee()) + "<br>" 
				+ "</html>");
	}
}
