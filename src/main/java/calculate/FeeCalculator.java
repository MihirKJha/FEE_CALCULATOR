package calculate;



import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import bean.Transaction;
import fileReader.CsvInputReader;
import fileReader.InputReader;
import helper.FeeCalculatorHelper;

/**
 * Main classs , exeution flow starts from here .
 * 
 * Please put transaction.csv inside porject folder
 * 
 * Designed to handle csv inputs but design is open for extension
 * for other type of input file as well
 * 
 * @author Mihir
 *
 */
public class FeeCalculator {

	public static void main(String[] args) {
		InputReader inputReader = new InputReader(new CsvInputReader());
		FeeCalculator calcultor= new FeeCalculator();
		FeeCalculatorHelper helper= new FeeCalculatorHelper();
		List<Transaction> groupedTransactions = helper.processRules(inputReader.readFile("transaction.csv"));
		calcultor.sortAndPrintReport(groupedTransactions);
	}
	
	/**
	 * Sort grouped list and generate final report
	 * 
	 * @param groupedTransactions
	 */
	public void sortAndPrintReport(List<Transaction> groupedTransactions){
		List<Transaction> sortedFinalResult= groupedTransactions.stream()
				.sorted(Comparator
						.comparing(Transaction::getClientId)
						.thenComparing(Transaction::getTransactionType)
						.thenComparing(Transaction::getTransactionDate)
						.thenComparing(Transaction::getPriorityFlag))
				.collect(Collectors.toList());
		System.out.print("Client Id    ");
		System.out.print("Transaction Type    ");
		System.out.print("Transaction Date  ");
		System.out.print("Priority   ");
		System.out.print("Processing Fee  ");
		System.out.println("");
		for(Transaction transaction:sortedFinalResult){
			System.out.print(transaction.getClientId()+"           ");
			System.out.print("  "+transaction.getTransactionType()+"         ");
			System.out.print("      "+transaction.getTransactionDate()+"     ");
			System.out.print("      "+transaction.getPriorityFlag()+"      ");
			System.out.print("     "+transaction.getProcessingFee()+"       ");
			System.out.println("");
		}
	}
}