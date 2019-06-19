import java.util.ArrayList;

public class MemoryManager {

	// Lista inicial de operações
	private ArrayList<Operation> operations;
	
	// Lista com operações de alocações pendentes
	private ArrayList<Operation> pendingOperations;
	
	private Memory memory;
	
	public MemoryManager(int initialAddress, int size, ArrayList<Operation> operations) {
		this.memory = new Memory(initialAddress, size);
		this.operations = operations;
		this.pendingOperations = new ArrayList<Operation>();
	}
	
	public void run() {
		
		// Operação de memória da iteração atual
		Operation currentOperation;
		
		while(!this.operations.isEmpty()) {
			
			currentOperation = this.operations.remove(0);
			
			// Caso a operação seja de alocação de memória
			if (currentOperation.type == OperationType.ALLOC_MEMORY) {
				System.out.println(currentOperation);
				
				// Tenta alocar
				if (this.memory.allocMemory(currentOperation.value)) {
					System.out.println(this.memory + "\n");
				} else {
					// Caso não consiga, manda a operação para uma fila de operações pendentes
					System.out.println("\nFailed\n");
					this.pendingOperations.add(currentOperation);
				}
			
			// Caso a operação seja de liberação de memória
			} else {
				this.memory.freeMemoryBlock(currentOperation.value);
				System.out.println(currentOperation);
				System.out.println(this.memory + "\n");
			}
		}
	}
	
	private void allocPendingOperations() {
		
		for (Operation operation : this.pendingOperations) {
			if ()
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
