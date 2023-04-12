package room;

public class Room {
	private String number;
	private String type;
	private int duration;
	private double charge;
	
	public Room() {
		// TODO Auto-generated constructor stub
	}


	public Room(String number, String type, int duration, double charge) {
		super();
		this.number = number;
		this.type = type;
		this.duration = duration;
		this.charge = charge;
	}


	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
		
}
