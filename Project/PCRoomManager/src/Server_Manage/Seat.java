package Server_Manage;
import java.time.*;

//��ư�� �¼���ȣ, �̸�/ID, ���� �ð�, ��� ������ ������, ����� �޾Ƽ� Ŀ���� ����¡???
//�� ������ ���� seat Class ����, 4���� Field�� ����� getter,setter�� ����� + ������

public class Seat {
	private int seatNum;
	boolean occupied = false; // �ڸ� Ȯ��
	String user = null;
	LocalDateTime startTime = null; // ���� �����ϴ��� �ð� Ȯ��
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

	public LocalDateTime getStartTime() { // String ������ �ƴ� -> ���Ŀ� ����
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
