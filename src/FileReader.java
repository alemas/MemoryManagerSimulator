/* 	Autores: Mateus Reckziegel e Fabricio Pujol
*	Data: 04/06/19
*
*	Classe responsável por ler o arquivo de entrada e retornar um objeto
*	da classe MemoryManager com a memória inicial e todas operações prontas para serem
*	executadas.
*
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
	
	static public MemoryManager readFile(String path) {
		
		int initialAddress = 0;
		int finalAddress = 0;
		ArrayList<Operation> operations = new ArrayList<Operation>();
		
		try {
			Scanner scanner = new Scanner(new File(path));
			String line = "";
			int lineIndex = 0;
			
			while(scanner.hasNextLine()) {
				
				line = scanner.nextLine();
				String[] lineValues = line.split(" ");
				
				switch(lineIndex) {
				case 0: break;
				case 1:
					initialAddress = Integer.parseInt(lineValues[0]);
					break;
				case 2:
					finalAddress = Integer.parseInt(lineValues[0]);
					break;
				default:
					if (lineValues[0].equals("S")) {
						int amount = Integer.parseInt(lineValues[1]);
						operations.add(new Operation(OperationType.ALLOC_MEMORY, amount));
					} else if (lineValues[0].equals("L")) {
						int amount = Integer.parseInt(lineValues[1]);
						operations.add(new Operation(OperationType.FREE_MEMORY, amount));
					}
				}
				
				lineIndex++;
			}
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return new MemoryManager(initialAddress, finalAddress - initialAddress + 1, operations);
	}

}
