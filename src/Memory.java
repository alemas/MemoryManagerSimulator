
public class Memory {

	public MemoryBlock firstBlock;
	public int initialAddress;
	public int totalSize;
	
	public Memory(int initialAddress, int totalSize) {
		this.initialAddress = initialAddress;
		this.totalSize = totalSize;
		this.firstBlock = new MemoryBlock(initialAddress, totalSize);
	}
	
	// Método que tenta alocar um espaço na memória
	public boolean allocMemory(int requiredSize) {
		
		MemoryBlock currentBlock = this.firstBlock;
		
		// Percorre os blocos da memória
		while (currentBlock != null) {
			
			// Caso o bloco atual não esteja em uso
			if (!currentBlock.inUse) {
				
				// Caso o bloco possua espaço livre igual à quantidade de memória requisitada,
				// o bloco é marcado como ocupado
				if (currentBlock.size == requiredSize) {
					currentBlock.inUse = true;
					
					return true;
				
				// Caso o bloco possua espaço SOBRANDO para alocar a quantidade de memória requisitada,
				// é criado um novo bloco livre com o espaço que sobrar
				} else if (currentBlock.size > requiredSize) {
					MemoryBlock remainingFreeSpaceBlock = new MemoryBlock(currentBlock.initialAddress + requiredSize, currentBlock.size - requiredSize);
					remainingFreeSpaceBlock.rightBlock = currentBlock.rightBlock;
					remainingFreeSpaceBlock.leftBlock = currentBlock;
					
					currentBlock.rightBlock = remainingFreeSpaceBlock;
					currentBlock.inUse = true;
					currentBlock.size = requiredSize;
					
					return true;
				}	
			}
			currentBlock = currentBlock.rightBlock;
		}
		return false;
	}
	
	// Método para liberar um bloco de memória de acordo com o seu índice
	public void freeMemoryBlock(int index) {
		
		MemoryBlock currentBlock = this.firstBlock;
		int currentIndex = 0;
		
		// Busca pelo bloco correspondente ao índice
		while (currentBlock != null) {
			
			if (index == currentIndex) {
				currentBlock.inUse = false;
				
				MemoryBlock right = currentBlock.rightBlock;
				MemoryBlock left = currentBlock.leftBlock;
				
				// Caso o bloco à esquerda esteja livre, junta com o atual
				if (left != null && !left.inUse) {
					currentBlock.initialAddress = left.initialAddress;
					currentBlock.size += left.size;
					
					if (left.leftBlock != null) {
						left.leftBlock.rightBlock = currentBlock;
					}
					
					currentBlock.leftBlock = left.leftBlock;
					
					left.removeNeighbors();
				}
				
				// Caso o bloco à direita esteja livre, junta com o atual
				if (right != null && !right.inUse) {
					currentBlock.size += right.size;
						
					if (right.rightBlock != null) {
						right.rightBlock.leftBlock = currentBlock;
					}
					
					currentBlock.rightBlock = right.rightBlock;
					
					right.removeNeighbors();
				}
				
				return;
			}
			currentBlock = currentBlock.rightBlock;
			currentIndex++;
		}	
	}
	
	@Override
	public String toString() {
		String r = "";
		MemoryBlock currentBlock = this.firstBlock;
		int index = 0;
		while(currentBlock != null) { r += "\nBloco " + index + " - " + currentBlock; currentBlock = currentBlock.rightBlock; index++; }
		return r;
	}
}












