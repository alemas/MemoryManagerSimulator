
public class Memory {

	public MemoryBlock firstBlock;
	public int initialAddress;
	public int totalSize;
	
	private int nextId = 0; 
	
	public Memory(int initialAddress, int totalSize) {
		this.initialAddress = initialAddress;
		this.totalSize = totalSize;
		this.firstBlock = new MemoryBlock(initialAddress, totalSize);
	}
	
	// Gerador de ids para os blocos
	private int getNextId() {
		nextId++;
		return nextId;
	}
	
	// Método que tenta alocar um espaço na memória
	// Retorna "true" caso consiga alocar e "false" caso contrário
	public boolean allocMemory(int requiredSize) {
		
		MemoryBlock currentBlock = this.firstBlock;
		
		// Percorre os blocos da memória
		while (currentBlock != null) {
			
			// Caso o bloco atual não esteja em uso
			if (!currentBlock.getInUse()) {
				
				// Caso o bloco possua espaço livre IGUAL à quantidade de memória requisitada,
				// o bloco recebe um novo id e é marcado como "em uso"
				if (currentBlock.size == requiredSize) {
					currentBlock.setInUse(true);
					currentBlock.id = this.getNextId();
					
					return true;
				
				// Caso o bloco possua espaço SOBRANDO para alocar a quantidade de memória requisitada,
				// o mesmo é alocado e o espaço excendente é colocado em um bloco livre vizinho à direita
				} else if (currentBlock.size > requiredSize) {
					
					// Cria um bloco com o espaço que vai sobrar
					MemoryBlock remainingFreeSpaceBlock = new MemoryBlock(currentBlock.initialAddress + requiredSize, currentBlock.size - requiredSize);
					
					// Realocação de referências para vizinhos
					remainingFreeSpaceBlock.rightBlock = currentBlock.rightBlock;
					remainingFreeSpaceBlock.leftBlock = currentBlock;
					
					if (currentBlock.rightBlock != null) {
						currentBlock.rightBlock.leftBlock = remainingFreeSpaceBlock;
					}
					
					currentBlock.rightBlock = remainingFreeSpaceBlock;
					
					// Dá um novo id ao bloco
					currentBlock.setInUse(true);
					currentBlock.id = this.getNextId();
					
					// Altera o tamanho do bloco para o tamanho requisitado
					currentBlock.size = requiredSize;
					
					return true;
				}	
			}
			currentBlock = currentBlock.rightBlock;
		}
		return false;
	}
	
	// Método para checar se existe fragmentação externa para certa requisição de memória.
	// Retorna o tamanho do espaço livre caso haja fragmentação e -1 caso contrário
	public int checkForExternalFramentation(int requiredSize) {
		
		// Representa o espaço ainda livre na memória
		int freeSpace = 0;
		
		MemoryBlock currentBlock = this.firstBlock;
		
		// Percorre os blocos da memória
		while (currentBlock != null) {
			
			// Caso o bloco não esteja alocado, seu tamanho é acrescentado contador
			if (!currentBlock.getInUse()) { freeSpace += currentBlock.size; }
			
			currentBlock = currentBlock.rightBlock;
		}
		
		// Caso tenha ocorrido fragmentação, retorna o tamanho do espaço livre
		// de memória
		if (freeSpace >= requiredSize) {
			return freeSpace;
		}
		
		// Caso contrário, retorna -1
		return -1;
		
	}
	
	// Método para liberar um bloco de memória de acordo com o seu índice
	public void freeMemoryBlock(int id) {
		
		MemoryBlock currentBlock = this.firstBlock;
		
		// Busca pelo bloco correspondente ao índice
		while (currentBlock != null) {
			
			if (id == currentBlock.id) {
				
				// Libera o bloco em questão
				currentBlock.setInUse(false);
				
				MemoryBlock right = currentBlock.rightBlock;
				MemoryBlock left = currentBlock.leftBlock;
				
				// Caso o bloco à esquerda esteja livre, junta com o atual
				if (left != null && !left.getInUse()) {
					currentBlock.initialAddress = left.initialAddress;
					currentBlock.size += left.size;
					
					// Atualiza referências para os vizinhos
					if (left.leftBlock != null) {
						left.leftBlock.rightBlock = currentBlock;
					}
					
					currentBlock.leftBlock = left.leftBlock;
					
					// Remove referências do bloco que se juntou ao atual
					left.removeNeighbors();
				}
				
				// Caso o bloco à direita esteja livre, junta com o atual
				if (right != null && !right.getInUse()) {
					currentBlock.size += right.size;
						
					// Atualiza referências para os vizinhos
					if (right.rightBlock != null) {
						right.rightBlock.leftBlock = currentBlock;
					}
					
					currentBlock.rightBlock = right.rightBlock;
					
					// Remove referências do bloco que se juntou ao atual
					right.removeNeighbors();
				}
				
				return;
			}
			currentBlock = currentBlock.rightBlock;
		}	
	}
	
	@Override
	public String toString() {
		String r = "";
		MemoryBlock currentBlock = this.firstBlock;
		while(currentBlock != null) { r += "\n" + currentBlock; currentBlock = currentBlock.rightBlock; }
		return r;
	}
}












