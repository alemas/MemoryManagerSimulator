/* 	Autores: Mateus Reckziegel e Fabricio Pujol
*	Data: 04/06/19
*
*	Classe responsável por fazer a chamada das operações na memória.
*	Tem uma fila principal de operações e outra auxiliar para as
*	operações pendentes de alocação. Toda vez que uma alocação é rejeitada,
*	verifica-se se houve fragmentação. O resultado de cada operação é impresso na tela.
*
*/

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

		while (!this.operations.isEmpty()) {

			currentOperation = this.operations.remove(0);

			System.out.println("-------------------------------------------------");

			System.out.println(currentOperation);

			// Caso a operação seja de alocação de memória
			if (currentOperation.type == OperationType.ALLOC_MEMORY) {

				// Tenta alocar na memória o espaço requisitado pela operação
				if (this.memory.allocMemory(currentOperation.value)) {

					System.out.println(this.memory + "\n");

				// Caso não consiga alocar
				} else {

					System.out.println("\nFalhou\n");

					// Verifica fragmentação
					this.printFragmentation(currentOperation.value);

					// Manda a operação para uma fila de
					// operações pendentes
					this.pendingOperations.add(currentOperation);
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

		System.out.println("-------------------------------------------------");

	}

	private void printFragmentation(int requiredSize) {

		int fragmentation = this.memory.checkForExternalFramentation(requiredSize);

		if (fragmentation != -1) {
			System.out.println("Houve fragmentação externa. Espaço livre = " + fragmentation + "\n");
		}

	}

	private void allocPendingOperations() {

		// Operação atual
		Operation operation;

		// Iterador usado para percorrer a lista de operações pendentes
		Iterator<Operation> i = this.pendingOperations.iterator();

		System.out.println("Tentando executar operações pendentes...\n");

		while (i.hasNext()) {
			operation = i.next();

			System.out.println(operation);

			// Tenta alocar a operação atual na memória
			if (this.memory.allocMemory(operation.value)) {

				// Caso consiga alocar, ela sai da lista de operações pendentes
				i.remove();

				System.out.println(this.memory + "\n");
			
			// Caso não consiga alocar
			} else {
				System.out.println("\nFalhou\n");

				// Verifica fragmentação
				this.printFragmentation(operation.value);
			}
		}
	}

	@Override
	public String toString() {
		String r = "\nMemória:" + this.memory.toString();
		r += "\n\nOperações pendentes:";
		if (this.pendingOperations.isEmpty()) {
			r += "\nNenhuma";
		} else {
			for (Operation o : this.pendingOperations) {
				r += "\n" + o;
			}
		}
		return r;
	}

}
