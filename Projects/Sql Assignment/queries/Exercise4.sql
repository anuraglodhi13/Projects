/*
Exercise 4: Create a function that takes as inputs a SalesOrderID, a Currency Code, and a date, and returns a table of all the SalesOrderDetail rows 
for that Sales Order including Quantity, ProductID, UnitPrice, and the unit price converted to the target currency based on the end of 
day rate for the date provided. Exchange rates can be found in the Sales.CurrencyRate table. (Use AdventureWorks)
*/
CREATE FUNCTION OrderDetails( @SalesOrderId int, @CurrencyCode nvarchar(10), @Date date ) 
RETURNS TABLE 
AS 
RETURN 
(SELECT OrderQty, ProductID, UnitPrice *
(SELECT EndOfDayRate
FROM    Sales.CurrencyRate
WHERE (ToCurrencyCode = @CurrencyCode) AND (ModifiedDate = @Date)) AS UnitPrice
FROM   Sales.SalesOrderDetail
WHERE (SalesOrderID = @SalesOrderId));
Go

-- example query to test function
-- SELECT* FROM OrderDetails(43667,'MXN','2007-05-12')
