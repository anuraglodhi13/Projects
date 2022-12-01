/*
Exercise 6 :

Write a trigger for the Product table to ensure the list price can never be raised more than 15 Percent in a single change.
Modify the above trigger to execute its check code only if the ListPrice column is updated (Use AdventureWorks Database)
*/

Create TRIGGER Production.ListPrice
ON 
Production.Product
For UPDATE 
AS
IF Update(ListPrice)
BEGIN
IF EXISTS 
(
SELECT 'True' 
FROM inserted i JOIN deleted d ON i.ProductID = d.ProductID 
WHERE i.ListPrice > (d.ListPrice * 1.15)
) 
BEGIN 
RAISERROR('Price increase may not be greater than 15 percent.Transaction Failed.',16,1) 
ROLLBACK TRAN
END 
END

-- example query to test this trigger
--Update Production.Product
--Set ListPrice = 100
--where ProductID =514;
--select * from Production.Product;