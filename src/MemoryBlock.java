
public class MemoryBlock {

	// Representam os blocos adjacentes
	public MemoryBlock rightBlock;
	public MemoryBlock leftBlock;

	// Representa o endereço inicial e tamanho do bloco
	public int initialAddress;
	public int size;

	// Indica se o bloco está sendo usado ou não
	public boolean inUse = true;

	public MemoryBlock(int initialAddress, int size) {
		super();
		this.initialAddress = initialAddress;
		this.size = size;
	}

	// Retorna o endereço final
	public int getFinalAddress() {
		return this.initialAddress + this.size - 1;
	}

	@Override
	public String toString() {
		return "Endereço inicial: " + this.initialAddress + "; Tamanho: " + this.size + "; Em uso: " + this.inUse + "Endereço Final: " + this.getFinalAddress();
	}

}
