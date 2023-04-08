package doctor;

import java.util.ArrayList;

import appointment.Appointment;

public class Doctor {
	private String id;
	private String name;
	private String spesialization;
	private int rating;
	
	private ArrayList<Appointment> appointments = new ArrayList<Appointment>(); 
	
	public Doctor() {
		// TODO Auto-generated constructor stub
	}

	public Doctor(String id, String name, String spesialization, int rating, ArrayList<Appointment> appointments) {
		super();
		this.id = id;
		this.name = name;
		this.spesialization = spesialization;
		this.rating = rating;
		this.appointments = appointments;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpesialization() {
		return spesialization;
	}

	public void setSpesialization(String spesialization) {
		this.spesialization = spesialization;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}
}
