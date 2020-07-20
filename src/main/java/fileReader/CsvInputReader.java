package fileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import bean.Transaction;

/**
 * CSF file reading strategy 
 * 
 * 
 * @author Mihir
 *
 */
public class CsvInputReader implements InputReaderStrategy {

	public List<Transaction> readTransactions(String file) {
		String line;
		List<String> fileContent = new ArrayList<>();

		try (BufferedReader lineReader = new BufferedReader(new FileReader(file))) {
			lineReader.readLine();
			while ((line = lineReader.readLine()) != null) {
				fileContent.add(line);
			}
		} catch (FileNotFoundException ffex) {
			ffex.printStackTrace();
		} catch (IOException iex) {
			iex.printStackTrace();
		}
		
		return convertToTransactionBean(fileContent);

	}

	public List<Transaction> convertToTransactionBean(List<String> fileList) {
		
		List<Transaction> transactionsList = new ArrayList<>();
		DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("dd-MM-yyyy");		
		for(String fileLine: fileList){
			String[] lineContent = fileLine.split(",");
			Transaction transaction = new Transaction();			
			transaction.setExternalTransactionId(lineContent[0].trim());
			transaction.setClientId(lineContent[1].trim());
			transaction.setSecurityId(lineContent[2].trim());
			transaction.setTransactionType(lineContent[3].trim());
			transaction.setTransactionDate(LocalDate.parse(lineContent[4].trim(),dateFormater));
			transaction.setMarketValue(Double.parseDouble(lineContent[5].trim()));
			transaction.setPriorityFlag(lineContent[6].trim());	
			transactionsList.add(transaction);
		}
		
		return transactionsList;
   }
}
