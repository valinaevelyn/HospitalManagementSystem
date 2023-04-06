package room;

public class Room {
	private int number;
	private boolean status = false;
	private int capacity;
	
	public Room() {
		// TODO Auto-generated constructor stub
	}

	public Room(int number, boolean status, int capacity) {
		super();
		this.number = number;
		this.status = status;
		this.capacity = capacity;
	}
	
	public String checkRoomStatus() {
		if(status == false) {
			return "Non-Available";
		}
		
		return "Available";
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
}
