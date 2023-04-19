package Payment;

public class Payment {
	private String patientName;
	private int total;
	private String keterangan;
	private String status;

	public Payment() {
		
	}

	public Payment(String patientName, int total, String keterangan, String status) {
		this.patientName = patientName;
		this.total = total;
		this.keterangan = keterangan;
		this.status = status;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
