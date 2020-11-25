package utility;

public enum PetStatus {
	AVAILABLE("available"),
	PENDING("pending"),
	SOLD("sold");

	private String status;
	PetStatus(String status){
		this.status=status;
	}

	public String getstatus() {
		return this.status;
	}
	
}
