/*
Exercise 3: Show the most recent five orders that were purchased from account numbers that have spent more than $70,000 with 
AdventureWorks.
*/

WITH Accountorders AS ( SELECT AccountNumber, SUM(TotalDue) AS TotalSum , OrderDate FROM Sales.SalesOrderHeader AS soh GROUP BY AccountNumber, OrderDate ) 
SELECT TOP 5 * 
FROM Accountorders 
WHERE TotalSum > 70000 
Order by OrderDate DESC;

