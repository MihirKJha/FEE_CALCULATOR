package compare;

import bean.Transaction;
import enums.TransactionType;

/**
 * Comapre the field and sets value in Transaction fee 
 * 
 * @author Mihir
 *
 */
public class TransactionComparator {
    
	/**
	 * Handles intra day transactions 
	 * 
	 * @param t1
	 * @param t2
	 * @return
	 */
	public static int compareByClientSecurityAndDate(Transaction t1, Transaction t2) {
		if (t1.getClientId().equalsIgnoreCase(t2.getClientId())
			&& t1.getSecurityId().equalsIgnoreCase(t2.getSecurityId())
			&& t1.getTransactionDate().compareTo(t2.getTransactionDate())==0) {
			
			if(t1.getTransactionType().equalsIgnoreCase(TransactionType.BUY.name())
				&& t2.getTransactionType().equalsIgnoreCase(TransactionType.SELL.name())) {
				
				t1.setProcessingFee(10);
				t2.setProcessingFee(10);

			}else if(t1.getTransactionType().equalsIgnoreCase(TransactionType.SELL.name()) 
					&& t2.getTransactionType().equalsIgnoreCase(TransactionType.BUY.name())){
				
				t1.setProcessingFee(10);
				t2.setProcessingFee(10);

			}
		}
		return 0;
  }
}
