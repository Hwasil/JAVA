package Server_Manage;
import java.awt.*;
import javax.swing.*;

public class SeatButton extends JButton {
	int seatNum;
	Seat seat = null;
	
	public SeatButton() { // 생성자 Overloading
		super();
	}
	
	public SeatButton(int number, Seat seat) {
		super(); // 부모를 추가로 넣겠다??
		this.seatNum = number;
		this.seat = seat;
	}

	public int getSeatNum() {
		return seatNum;
	}
	
	public void printSeatInfo() { // 버튼의 4가지 정보를 출력하기 위해 사용, html 형식 이용
		this.setText("<html>" + Integer.toString(seatNum) + "<br>" 
				+  seat.getUser() + "<br>" 
				+ (seat.getStartTime() == null? "00:00": seat.getStartTime()) + "<br>" 
				+ Float.toString(seat.getFee()) + "<br>" 
				+ "</html>");
	}
}
