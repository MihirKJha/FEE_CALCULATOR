package fileReader;

import java.util.List;

import bean.Transaction;

/**
 * Input file reader , Strategy design pattern
 * 
 * Currently CSV implementation is provided 
 *  
 *  
 * @author Mihir
 *
 */
public interface InputReaderStrategy {

	List<Transaction> readTransactions(String file);
}