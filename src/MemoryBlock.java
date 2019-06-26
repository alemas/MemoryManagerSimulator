/* 	Autores: Mateus Reckziegel e Fabricio Pujol
*	Data: 04/06/19
*
*	Classe responsável por representar os blocos de uma memória
*	em forma de lista duplamente encadeada.
*
*/

public class MemoryBlock {

	// Representam os blocos adjacentes
	public MemoryBlock rightBlock;
	public MemoryBlock leftBlock;

	// Representa o endereço inicial e tamanho do bloco
	public int initialAddress;
	public int size;
	
	// Representa o id do bloco.
	// Quando o bloco está livre seu id é -1
	public int id = -1;

	// Indica se o bloco está sendo usado ou não
	private boolean inUse = false;

	public MemoryBlock(int initialAddress, int size) {
		super();
		this.initialAddress = initialAddress;
		this.size = size;
	}

	// Retorna o endereço final do bloco
	public int getFinalAddress() {
		return this.initialAddress + this.size - 1;
	}
	
	public boolean getInUse() {
		return this.inUse;
	}
	
	// Modifica a flag inUse e reseta o id caso esteja sendo liberado
	public void setInUse(boolean inUse) {
		if (!inUse) { this.id = -1; }
		this.inUse = inUse;
	}
	
	// Remove as referências para blocos vizinhos 
	public void removeNeighbors() {
		this.rightBlock = null;
		this.leftBlock = null;
	}

	@Override
	public String toString() {
		String r = this.inUse ? "Bloco " + this.id : "Livre";
		r += ": " + this.initialAddress +  " - " + this.getFinalAddress() + "; Tamanho: " + this.size;
		return r;
	}

}
