import java.util.ArrayList;

public class MemoryManager {

	private int initialAddress;
	private int finalAddress;
	private ArrayList<Operation> operations;
	
	public MemoryManager(int initialAddress, int finalAddress, ArrayList<Operation> operations) {
		this.initialAddress = initialAddress;
		this.finalAddress = finalAddress;
		this.operations = operations;
	}
	
	@Override
	public String toString() {
		String r = "Initial Address = " + this.initialAddress + "\nFinal Address = " + this.finalAddress;
		for (Operation o : this.operations) { r += "\n" + o; }
		return r;
	}
	
}
