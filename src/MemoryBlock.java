
public class MemoryBlock {

	// Representam os blocos adjacentes
	public MemoryBlock rightBlock;
	public MemoryBlock leftBlock;

	// Representa o endereço inicial e tamanho do bloco
	public int initialAddress;
	public int size;

	// Indica se o bloco está sendo usado ou não
	public boolean inUse = false;

	public MemoryBlock(int initialAddress, int size) {
		super();
		this.initialAddress = initialAddress;
		this.size = size;
	}

	// Retorna o endereço final do bloco
	public int getFinalAddress() {
		return this.initialAddress + this.size - 1;
	}
	
	public void removeNeighbors() {
		this.rightBlock = null;
		this.leftBlock = null;
	}

	@Override
	public String toString() {
		return "Endereço inicial: " + this.initialAddress + "; Endereço Final: " + this.getFinalAddress() + "; Tamanho: " + this.size + "; Em uso: " + this.inUse;
	}

}
