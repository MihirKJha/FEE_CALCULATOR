package bean;

import java.time.LocalDate;

/**
 * Transaction Bean
 * 
 * @author Mihir
 *
 */
public class Transaction {
	
	private String externalTransactionId;
	
	private String clientId;
	
	private String securityId;
	
	private String transactionType;
	
	private LocalDate transactionDate;
	
	private double marketValue;
	
	private String priorityFlag;
	
	private long processingFee;
	
	public String getExternalTransactionId() {
		return externalTransactionId;
	}
	public void setExternalTransactionId(String externalTransactionId) {
		this.externalTransactionId = externalTransactionId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getSecurityId() {
		return securityId;
	}
	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	public double getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}
	public String getPriorityFlag() {
		return priorityFlag;
	}
	public void setPriorityFlag(String priorityFlag) {
		this.priorityFlag = priorityFlag;
	}
	public long getProcessingFee() {
		return processingFee;
	}
	public void setProcessingFee(long processingFee) {
		this.processingFee = processingFee;
	}
}
