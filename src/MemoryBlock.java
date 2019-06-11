
public class MemoryBlock {

	// Representam os blocos adjacentes
	public MemoryBlock rightBlock = null;
	public MemoryBlock leftBlock = null;
	
	// Representa o endereço inicial e final do bloco
	public int initialAddress;
	public int finalAddress;
	
	// Indica se o bloco está sendo usado ou não
	public boolean isEmpty = true;
	
	public MemoryBlock(int initialAddress, int finalAddress) {
		super();
		this.initialAddress = initialAddress;
		this.finalAddress = finalAddress;
	}

	// Retorna o tamanho do bloco
	public int getSize() {
		return finalAddress - initialAddress;
	}
	
}
