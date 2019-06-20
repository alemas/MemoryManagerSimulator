import java.util.ArrayList;
import java.util.Iterator;

public class MemoryManager {

	// Lista inicial de operações do arquivo de input
	private ArrayList<Operation> operations;
	
	// Lista com operações de alocação pendentes
	private ArrayList<Operation> pendingOperations;
	
	// Memória a ser usada
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
			
			System.out.println(currentOperation);
			
			// Caso a operação seja de alocação de memória
			if (currentOperation.type == OperationType.ALLOC_MEMORY) {
				
				// Tenta alocar na memória
				if (this.memory.allocMemory(currentOperation.value)) {
					
					System.out.println(this.memory + "\n");
					
				} else {
					
					// Caso não consiga, manda a operação para uma fila de operações pendentes
					this.pendingOperations.add(currentOperation);
					
					System.out.println("\nFalhou\n");

				}
			
			// Caso a operação seja de liberação de memória
			} else {
				
				// Libera o bloco correspondente ao índice da operação
				this.memory.freeMemoryBlock(currentOperation.value);
				
				System.out.println(this.memory + "\n");
				
				// Tenta alocar as operações pendentes (caso haja alguma)
				if (!this.pendingOperations.isEmpty())
					this.allocPendingOperations();
			}
		}
	}
	
	private void allocPendingOperations() {
		
		// Operação atual
		Operation operation;
		
		// Iterador usado para percorrer a lista de operações pendentes
		Iterator<Operation> i = this.pendingOperations.iterator();
		
		System.out.println("Tentando executar operações pendentes...\n");
		
		while(i.hasNext()) {
			operation = i.next();
			
			System.out.println(operation);
			
			// Tenta alocar a operação atual
			if (this.memory.allocMemory(operation.value)) {
				
				// Caso consiga alocar, ela sai da lista de operações pendentes
				i.remove();
				
				System.out.println(this.memory + "\n");
				
			} else {
				System.out.println("\nFalhou\n");
			}
		}
	}
	
	@Override
	public String toString() {
		String r = "\nMemória:" + this.memory.toString();
		r += "\n\nOperações pendentes:";
		for (Operation o : this.pendingOperations) { r += "\n" + o; }
		return r;
	}
	
}
