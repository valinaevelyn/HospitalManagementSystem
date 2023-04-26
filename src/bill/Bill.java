package bill;
public class Bill {
	private String id;
	private String date_start;
	private String date_end;
	private String virtualAcc;
	private String proof;
	
	public Bill(String id, String date_start, String date_end, String proof) {
		super();
		this.id = id;
		this.date_start = date_start;
		this.date_end = date_end;
		this.proof = proof;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate_start() {
		return date_start;
	}
	public void setDate_start(String date_start) {
		this.date_start = date_start;
	}
	public String getDate_end() {
		return date_end;
	}
	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}
	public String getVirtualAcc() {
		return virtualAcc;
	}
	public void setVirtualAcc(String virtualAcc) {
		this.virtualAcc = virtualAcc;
	}
	public String getProof() {
		return proof;
	}
	public void setProof(String proof) {
		this.proof = proof;
	}
	
	

}
