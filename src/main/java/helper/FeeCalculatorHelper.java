package helper;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingLong;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import bean.Transaction;
import compare.TransactionComparator;
import enums.Priority;
import enums.TransactionType;

/**
 * Helper class
 * 
 * Helps in calculating processing fee and generating report.
 * 
 * @author Mihir
 *
 */
public class FeeCalculatorHelper {
	
	public List<Transaction>  processRules(List<Transaction> transactionList){
		processIntraDayTransactions(transactionList);
		processNormalTransactions(transactionList);	
		List<Transaction> transactions = prepareReport(transactionList);
		return transactions;
	}
	
	public List<Transaction>  processIntraDayTransactions(List<Transaction> transactionList){
		List<Transaction> sortedListByAttributes = transactionList.stream().sorted(Comparator
      		  .comparing(Transaction::getClientId)
      		  .thenComparing(Transaction::getSecurityId)
      		  .thenComparing(Transaction::getTransactionDate)
      		  .thenComparing(Transaction::getTransactionType))
			.collect(Collectors.toList());
		
		sortedListByAttributes.sort(TransactionComparator::compareByClientSecurityAndDate);
		return sortedListByAttributes;
	}
	
	public List<Transaction>  processNormalTransactions(List<Transaction> transactionList){
		for(Transaction transaction:transactionList){
			if(transaction.getPriorityFlag().equalsIgnoreCase(Priority.Y.name())){
				transaction.setProcessingFee(transaction.getProcessingFee()+500);
			}else if(transaction.getPriorityFlag().equalsIgnoreCase(Priority.N.name())
					&& (transaction.getTransactionType().equalsIgnoreCase(TransactionType.SELL.name())
							||transaction.getTransactionType().equalsIgnoreCase(TransactionType.WITHDRAW.name()) )){
				transaction.setProcessingFee(transaction.getProcessingFee()+100);				
			}else if(transaction.getPriorityFlag().equalsIgnoreCase(Priority.N.name())
					&& (transaction.getTransactionType().equalsIgnoreCase(TransactionType.BUY.name())
							||transaction.getTransactionType().equalsIgnoreCase(TransactionType.DEPOSIT.name()) )){
				transaction.setProcessingFee(transaction.getProcessingFee()+50);				
			}
		}
		return transactionList;
	}
	
	public List<Transaction> prepareReport(List<Transaction> transactionList){
		Map<String, Map<String, Map<LocalDate, Map<String, Long>>>> groupedTransactions
		        = transactionList
		          .stream()
		          .collect(groupingBy(Transaction::getClientId ,
						    groupingBy(Transaction::getTransactionType ,
								groupingBy(Transaction::getTransactionDate ,
										groupingBy(Transaction::getPriorityFlag ,
												summingLong(Transaction::getProcessingFee))))));
		
		List<Transaction> groupdTransactions = new ArrayList<>();
		
		for(Entry<String, Map<String, Map<LocalDate, Map<String, 
				Long>>>> entry:groupedTransactions.entrySet()){
			
			Map<String, Map<LocalDate, Map<String, Long>>> mapByTransactionType=entry.getValue();
			
			for(Entry<String, Map<LocalDate, Map<String, Long>>> entryByTransactionType
					:mapByTransactionType.entrySet()){
				
				Map<LocalDate, Map<String,Long>> mapByTransactionDate =
						entryByTransactionType.getValue();
				
				for(Entry<LocalDate, Map<String, Long>> entryByTransactionDate
						:mapByTransactionDate.entrySet()){
					Map<String, Long> mapByPriority= entryByTransactionDate.getValue();
					for(Entry<String, Long> entryByPriority:mapByPriority.entrySet()){
						Transaction transation = new Transaction();
						transation.setClientId(entry.getKey());
						transation.setTransactionType(entryByTransactionType.getKey());
						transation.setTransactionDate(entryByTransactionDate.getKey());
						transation.setPriorityFlag(entryByPriority.getKey());
						transation.setProcessingFee(entryByPriority.getValue());
						groupdTransactions.add(transation);
						}
					}
				}		
	}
	
	return groupdTransactions;
  
  }
	
}