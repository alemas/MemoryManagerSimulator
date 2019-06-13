
enum OperationType {
	ALLOC_MEMORY, FREE_MEMORY
}

public class Operation {
	
	public OperationType type;
	public int value;
	
	public Operation(OperationType type, int amount) {
		this.type = type;
		this.value = amount;
	}
	
	@Override
	public String toString() {

		String r = this.type == OperationType.ALLOC_MEMORY ? "Alloc memory - " : "Free memory - ";
		r += this.value; 
		return r;
	}
	
}
