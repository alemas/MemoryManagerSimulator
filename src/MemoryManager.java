import java.util.ArrayList;

public class MemoryManager {

	private ArrayList<Operation> operations;
	
	private Memory memory;
	
	public MemoryManager(int initialAddress, int size, ArrayList<Operation> operations) {
		this.memory = new Memory(initialAddress, size);
		this.operations = operations;
	}
	
	public void run() {
		
		//Operação de memória da iteração atual
		Operation currentOperation;
		
		while(!this.operations.isEmpty()) {
			
			currentOperation = this.operations.remove(0);
			
			//Caso a operação seja de solicitação de memória
			if (currentOperation.type == OperationType.ALLOC_MEMORY) {
				this.memory.allocMemory(currentOperation.value);
				System.out.println(currentOperation);
				System.out.println(this.memory + "\n");
			} else {
				this.memory.freeMemoryBlock(currentOperation.value);
				System.out.println(currentOperation);
				System.out.println(this.memory + "\n");
			}
		}
	}
	
	@Override
	public String toString() {
		String r = "\nMemória:" + this.memory.toString();
		r += "\n\nOperações:";
		for (Operation o : this.operations) { r += "\n" + o; }
		return r;
	}
	
}
