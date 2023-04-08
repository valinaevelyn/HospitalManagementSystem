package appointment;

import doctor.Doctor;
import patient.Patient;

public class Appointment {
	private String id;
	private Patient patient;
	private String complaint;
	private String date;
	private String time;
	private Doctor doctor;
	
	public Appointment() {
		// TODO Auto-generated constructor stub
	}

	public Appointment(String id, Patient patient, String complaint, String date, String time, Doctor doctor) {
		super();
		this.id = id;
		this.patient = patient;
		this.complaint = complaint;
		this.date = date;
		this.time = time;
		this.doctor = doctor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getComplaint() {
		return complaint;
	}

	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
}
