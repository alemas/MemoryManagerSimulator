
public class App {

	public static void main(String[] args) {

		MemoryManager memoryManager = FileReader.readFile("data/2");
//		System.out.println(memoryManager);
		memoryManager.run();
		System.out.println("Resultado final:\n" + memoryManager);
		
	}

}
