# FEE_CALCULATOR
FEE CALCULATOR
	Company has won the contract to implement processing fee calculator for a major investment bank	
	(henceforth referred as client). Client receives transactions from various external sources. These	
	transactions are received in a pre-configured format, for example, CSV, EXCEL, XML or a simple	
	pipe delimited format text file placed at a file location. The client needs to calculate the processing	
	fees for the transaction and generate a report which can be sent for invoicing.	

Objective

The objective of the system is:
	
o To read the transactions into the system. The various transaction attributes are listed below.  o To execute the processing rules (mentioned below) over input transactions 
o To provide API to get the summary report in a particular format (format mentioned below) 

Note: The code should handle csv format input, however the design should be extensible to support other formats as well in future.


Transaction Attributes:

Attribute Name	Attribute Description
	
External Transaction Id	Unique Identifier
	
Client Id	Unique Id for each client
	
Security Id	Unique  Id  for  the  security for  example  RELIND  for
	reliance industries
	
Transaction Type	Buy, Sell, Deposit, Withdraw
	
Transaction Date	Date in MM/dd/yyyy
	
Market Value	The current market value of this transaction
	
Priority Flag	Value Y,N
	
 
