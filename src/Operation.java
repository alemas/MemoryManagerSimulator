
enum OperationType {
	ALLOC_MEMORY, FREE_MEMORY
}

public class Operation {
	
	// Tipo da operação (se aloca ou libera memória)
	public OperationType type;
	
	// Valor da operação:
	// Caso seja uma alocação, representa o tamanho que se quer alocar.
	// Caso seja uma liberação, representa o índice do bloco a ser liberado.
	public int value;
	
	public Operation(OperationType type, int value) {
		this.type = type;
		this.value = value;
	}
	
	@Override
	public String toString() {

		String r = this.type == OperationType.ALLOC_MEMORY ? "Alloc memory - " : "Free memory - ";
		r += this.value; 
		return r;
	}
	
}
