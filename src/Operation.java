
enum OperationType {
	REQUEST_MEMORY, FREE_MEMORY
}

public class Operation {
	
	public OperationType type;
	public int amount;
	
	public Operation(OperationType type, int amount) {
		this.type = type;
		this.amount = amount;
	}
	
	@Override
	public String toString() {

		String r = this.type == OperationType.REQUEST_MEMORY ? "Request memory - " : "Free memory - ";
		r += this.amount; 
		return r;
	}
	
}
