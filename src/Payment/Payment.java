package Payment;

import patient.Patient;

public class Payment {
	private Patient patient;
	private int total;
	private String keterangan;
	private boolean statusPayment = false;
	
	public Payment() {
		// TODO Auto-generated constructor stub
	}

	public Payment(Patient patient, int total, String keterangan, boolean statusPayment) {
		super();
		this.patient = patient;
		this.total = total;
		this.keterangan = keterangan;
		this.statusPayment = statusPayment;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public boolean isStatusPayment() {
		return statusPayment;
	}
	
	public String checkStatusPayment() {
		if(statusPayment == false) {
			return "Belum Dibayar";
		}else {
			return "Sudah Lunas";
		}
	}

	public void setStatusPayment(boolean statusPayment) {
		this.statusPayment = statusPayment;
	}
}
