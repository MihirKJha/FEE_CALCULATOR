package fileReader;


import java.util.List;

import bean.Transaction;

/**
 * 
 * @author Mihir
 *
 */
public class InputReader {
   
	private InputReaderStrategy inputStrategy;
	
	public InputReader(InputReaderStrategy inputReaderStrategy) {
		this.inputStrategy= inputReaderStrategy;
	}
	
	public List<Transaction> readFile(String file) {
		return inputStrategy.readTransactions(file);
	}
}
