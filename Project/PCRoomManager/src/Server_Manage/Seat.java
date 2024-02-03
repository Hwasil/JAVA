package Server_Manage;
import java.time.*;

//버튼에 좌석번호, 이름/ID, 시작 시간, 요금 정보가 들어가야함, 상속을 받아서 커스텀 마이징???
//위 정보를 가진 seat Class 제작, 4개의 Field를 만들고 getter,setter도 만들기 + 생성자

public class Seat {
	private int seatNum;
	boolean occupied = false; // 자리 확인
	String user = null;
	LocalDateTime startTime = null; // 언제 시작하는지 시간 확인
	float fee = 0;
	
	public Seat() {
		
	}
	
	
	public Seat(int seatNum) {
		this.seatNum = seatNum;
	}
	
	
	public int getSeatNim() {
		return seatNum;
	}
	

	public void setSeatNim(int seatNim) {
		this.seatNum = seatNim;
	}
	

	public boolean isOccupied() {
		return occupied;
	}
	

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public LocalDateTime getStartTime() { // String 형식이 아님 -> 이후에 수정
		return startTime;
	}


	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}


	public float getFee() {
		return fee;
	}


	public void setFee(float fee) {
		this.fee = fee;
	}


	
}
