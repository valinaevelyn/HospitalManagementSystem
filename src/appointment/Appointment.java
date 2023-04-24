package appointment;

import doctor.Doctor;
import patient.Patient;

public class Appointment {
	private String id;
	private String patientName;
	private String complaint;
	private String date;
	private String time;
	private String doctorName;
	// private Patient patient;
	// private Doctor doctor;
	
	public Appointment() {
		// TODO Auto-generated constructor stub
	}


	public Appointment(String id, String patientName, String complaint, String date, String time, String doctorName) {
		this.id = id;
		this.patientName = patientName;
		this.complaint = complaint;
		this.date = date;
		this.time = time;
		this.doctorName = doctorName;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getComplaint() {
		return this.complaint;
	}

	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDoctorName() {
		return this.doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	
	
}
