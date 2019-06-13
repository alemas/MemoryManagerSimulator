
public class App {

	public static void main(String[] args) {

		MemoryManager memoryManager = FileReader.readFile("data/1");
//		System.out.println(memoryManager);
		memoryManager.run();
		System.out.println(memoryManager);
		
	}

}
