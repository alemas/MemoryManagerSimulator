/* 	Autores: Mateus Reckziegel e Fabricio Pujol
*	Data: 04/06/19
*
*	Classe responsável por iniciar a execução e mostrar o resultado final
*	do simulador.
*
*/

public class App {

	public static void main(String[] args) {

		MemoryManager memoryManager = FileReader.readFile("data/3");
		System.out.println(memoryManager);
		memoryManager.run();
		System.out.println("Resultado final:\n" + memoryManager);
		
	}

}
  